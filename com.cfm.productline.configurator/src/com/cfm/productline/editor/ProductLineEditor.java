package com.cfm.productline.editor;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import com.cfm.productline.Asset;
import com.cfm.productline.Editable;
import com.cfm.productline.ProductLine;
import com.cfm.productline.VariabilityElement;
import com.cfm.productline.Variable;
import com.cfm.productline.configurator.Configurator;
import com.cfm.productline.constraints.GenericConstraint;
import com.cfm.productline.constraints.GroupConstraint;
import com.cfm.productline.editor.PaletteDatabase.NaturalDeserializer;
import com.cfm.productline.editor.PaletteDatabase.PaletteDefinition;
import com.cfm.productline.editor.PaletteDatabase.PaletteEdge;
import com.cfm.productline.editor.PaletteDatabase.PaletteNode;
import com.cfm.productline.editor.PaletteDatabase.ScriptedVariabilityElement;
import com.cfm.productline.editor.widgets.Widget;
import com.cfm.productline.editor.widgets.WidgetFactory;
import com.cfm.productline.type.DomainRegister;
import com.cfm.productline.type.IntegerType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.examples.swing.GraphEditor;
import com.mxgraph.examples.swing.editor.BasicGraphEditor;
import com.mxgraph.examples.swing.editor.EditorPalette;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.shape.mxStencilShape;
import com.mxgraph.swing.util.mxGraphTransferable;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.util.mxResources;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxGraphSelectionModel;

@SuppressWarnings("serial")
public class ProductLineEditor extends BasicGraphEditor{

	static
	{
		try
		{
			mxResources.add("com/cfm/productline/editor/resources/editor");
		}
		catch (Exception e)
		{
			// ignore
		}
	}
	
	protected DomainRegister domainRegister = new DomainRegister();
	protected ProductLineIndex productLineIndex;
	protected Configurator configurator;
	protected JTextArea messagesArea;
	protected JPanel propertiesPanel;
	protected JTabbedPane extensionTabs;
	
	protected int mode = 0;
	
	public ProductLineEditor(String appTitle, ProductLineGraphComponent component) {
		super(appTitle, component);
		loadRegularPalette();
		loadScriptedPalettes();
		//loadPalettes();
		registerEvents();
	}

	private void registerEvents() {
		mxGraphSelectionModel selModel = getGraphComponent().getGraph().getSelectionModel();
		selModel.addListener(mxEvent.CHANGE, new mxIEventListener() {
			
			@Override
			public void invoke(Object sender, mxEventObject evt) {
				Collection<mxCell> added = (Collection<mxCell>) evt.getProperty("added");
				//System.out.println("Added: " + added);
				
				Collection<mxCell> removed = (Collection<mxCell>)evt.getProperty("removed");
				//System.out.println("Removed: " + removed);
				
				editProperties(null);
				
				if( removed == null )
					return;
				
				mxCell cell = null;
				if( removed.size() == 1 )
					cell = removed.iterator().next();
				
				//Multiselection case
				if( cell == null )					
					return;
				
				if( cell.getValue() instanceof Editable ){
					Editable elm = (Editable) cell.getValue();
					editProperties(elm);
					getGraphComponent().scrollCellToVisible(cell, true);
				}
			}
		});
	}

	private void loadRegularPalette() {
		EditorPalette palette = insertPalette(mxResources.get("productLinePalette"));
		
		palette
			.addTemplate(
					mxResources.get("varElementIconTitle"),
					new ImageIcon(
							GraphEditor.class
									.getResource("/com/cfm/productline/editor/images/plnode.png")),
					"plnode", 80, 40, new VariabilityElement());
		
		palette
		.addEdgeTemplate(
				mxResources.get("optionalIconTitle"),
				new ImageIcon(
						GraphEditor.class
						.getResource("/com/cfm/productline/editor/images/ploptional.png")),
						"ploptional", 80, 40, ConstraintMode.Optional);
		
		palette
		.addEdgeTemplate(
				mxResources.get("mandatoryIconTitle"),
				new ImageIcon(
						GraphEditor.class
						.getResource("/com/cfm/productline/editor/images/plmandatory.png")),
						"plmandatory", 80, 40, ConstraintMode.Mandatory);
		palette
		.addEdgeTemplate(
				mxResources.get("requiresIconTitle"),
				new ImageIcon(
						GraphEditor.class
						.getResource("/com/cfm/productline/editor/images/plrequires.png")),
						"plrequires", 80, 40, ConstraintMode.Requires);
		palette
		.addEdgeTemplate(
				mxResources.get("excludesIconTitle"),
				new ImageIcon(
						GraphEditor.class
						.getResource("/com/cfm/productline/editor/images/plexcludes.png")),
						"plexcludes", 80, 40, ConstraintMode.Excludes);
		
		palette
		.addTemplate(
				mxResources.get("groupIconTitle"),
				new ImageIcon(
						GraphEditor.class
						.getResource("/com/cfm/productline/editor/images/plgroup.png")),
						"plgroup", 20, 20, new GroupConstraint());
		
		palette
		.addTemplate(
				mxResources.get("constraintIconTitle"),
				new ImageIcon(
						GraphEditor.class
						.getResource("/com/cfm/productline/editor/images/plcons.png")),
						"plcons", 60, 30, new GenericConstraint());
		
		//For the assets
		palette.addTemplate("Asset", new ImageIcon(
				GraphEditor.class
				.getResource("/com/cfm/productline/editor/images/plcons.png")), 
					"plasset", 60, 30, new Asset());
		
		final ProductLineGraph graph = (ProductLineGraph)getGraphComponent().getGraph();
		
		palette.addListener(mxEvent.SELECT, new mxIEventListener()
		{
			public void invoke(Object sender, mxEventObject evt)
			{
				Object tmp = evt.getProperty("transferable");
				graph.setConsMode(ConstraintMode.None);
				
				if (tmp instanceof mxGraphTransferable)
				{
					mxGraphTransferable t = (mxGraphTransferable) tmp;
					Object obj = t.getCells()[0];

					if (graph.getModel().isEdge(obj))
					{
						mxCell cell = (mxCell)obj;
						((ProductLineGraph) graph).setConsMode( (ConstraintMode) cell.getValue());
					}
				}
			}

		});
		
	}
	
	private void loadScriptedPalettes() {
		
		try {
			FileReader reader;
			reader = new FileReader(new File("palettes.pal"));
			Gson gson = new GsonBuilder().setPrettyPrinting()
					.serializeNulls()
					.registerTypeAdapter(Object.class, new NaturalDeserializer())
					.create();
			PaletteDatabase db = gson.fromJson(reader, PaletteDatabase.class);
			loadPaletteDatabase(db);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String loadShape(EditorPalette palette, File f) throws IOException{
		String nodeXml = mxUtils.readFile(f.getAbsolutePath());
		addStencilShape(palette, nodeXml, f.getParent() + File.separator);
		return nodeXml;
	}
	
	/**
	 * Loads and registers the shape as a new shape in mxGraphics2DCanvas and
	 * adds a new entry to use that shape in the specified palette
	 * @param palette The palette to add the shape to.
	 * @param nodeXml The raw XML of the shape
	 * @param path The path to the directory the shape exists in
	 * @return the string name of the shape
	 */
	public static String addStencilShape(EditorPalette palette,
			String nodeXml, String path)
	{

		// Some editors place a 3 byte BOM at the start of files
		// Ensure the first char is a "<"
		int lessthanIndex = nodeXml.indexOf("<");
		nodeXml = nodeXml.substring(lessthanIndex);
		mxStencilShape newShape = new mxStencilShape(nodeXml);
		String name = newShape.getName();
		ImageIcon icon = null;

		if (path != null)
		{
			String iconPath = path + newShape.getIconPath();
			icon = new ImageIcon(iconPath);
		}

		// Registers the shape in the canvas shape registry
		mxGraphics2DCanvas.putShape(name, newShape);

		if (palette != null && icon != null)
		{
			palette.addTemplate(name, icon, "shape=" + name, 80, 80, "");
		}

		return name;
	}

	@Override
	protected Component getLeftComponent() {
		productLineIndex = new ProductLineIndex();
		productLineIndex.bind( (ProductLineGraph) getGraphComponent().getGraph());
		
		JSplitPane inner = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				productLineIndex, null);
		inner.setDividerLocation(250);
		inner.setResizeWeight(1);
		inner.setDividerSize(6);
		inner.setBorder(null);
		
		return inner;
	}
	
	@Override
	public Component getExtensionsTab() {
		if( extensionTabs != null)
			return extensionTabs;
		
		messagesArea = new JTextArea("Output");
		messagesArea.setEditable(false);
		
		propertiesPanel = new JPanel();
		propertiesPanel.setLayout(new SpringLayout());
		
		configurator = new Configurator();
		
		extensionTabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		extensionTabs.addTab(mxResources.get("propertiesTab"), new JScrollPane(propertiesPanel) );
		extensionTabs.addTab(mxResources.get("messagesTab"), new JScrollPane(messagesArea) );
		extensionTabs.addTab(mxResources.get("configurationTab"), new JScrollPane(configurator) );
		
		return extensionTabs;
	}
	
	public void bringUpExtension(String name){
		for(int i = 0; i < extensionTabs.getTabCount(); i++){
			if( extensionTabs.getTitleAt(i).equals(name) ){
				extensionTabs.setSelectedIndex(i);
				return;
			}
		}
	}
	
	public void bringUpTab(String name){
		for(int i = 0; i < extensionTabs.getTabCount(); i++){
			if( extensionTabs.getTitleAt(i).equals(name) ){
				extensionTabs.setSelectedIndex(i);
				return;
			}
		}
	}
	
	public JTextArea getMessagesArea() {
		return messagesArea;
	}
	
	public Configurator getConfigurator() {
		return configurator;
	}
	
	public void editProductLineReset(){
		productLineIndex.reset();
	}
	
	public void populateIndex(ProductLine pl){
		
		//productLineIndex.populate(pl);
		ProductLineGraph plGraph = (ProductLineGraph)getGraphComponent().getGraph();
		plGraph.buildFromProductLine2(pl,productLineIndex);
		//((mxGraphModel) plGraph.getModel()).clear();
		//plGraph.setProductLine(pl);
		
	}
		

	public void editProductLine(ProductLine pl){
		//productLineIndex.reset();
		
		ProductLineGraph plGraph = (ProductLineGraph)getGraphComponent().getGraph();
		((mxGraphModel) plGraph.getModel()).clear();
		plGraph.setProductLine(pl);
		
		//productLineIndex.populate(pl);
		
	}
	
	public ProductLine getEditedProductLine(){
		return ((ProductLineGraph)getGraphComponent().getGraph()).getProductLine();
	}
	
	public void editProperties(final Editable elm){
		propertiesPanel.removeAll();
		
		if( elm == null ){
			bringUpTab("Properties");
			propertiesPanel.repaint();
			return;
		}
		
		JPanel variablesPanel = new JPanel(new SpringLayout());
		
		Variable[] editables = elm.getEditableVariables();
		
		WidgetFactory factory = new WidgetFactory(this);
		for(Variable v : editables){
			final Widget w = factory.getWidgetFor(v);
			if( w == null )
				//Check the problem and/or raise an exception
				return;
			
			//TODO: Add listeners to w.
			w.getEditor().addFocusListener(new FocusListener() {
				@Override
				public void focusLost(FocusEvent arg0) {
					//Makes it pull the values.
					Variable v = w.getVariable();
					System.out.println("Focus Lost: " + v.hashCode() + " val: " + v.getValue());
					//v.setVariableValue("hola");
					onVariableEdited(elm);
				}
				
				@Override
				public void focusGained(FocusEvent arg0) {
					
				}
			});
			
			w.getEditor().addPropertyChangeListener(new PropertyChangeListener() {
				
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					if( Widget.PROPERTY_VALUE.equals( evt.getPropertyName() ) ){
						w.getVariable();
						onVariableEdited(elm);
					}
				}
			});
			w.getEditor().setMinimumSize(new Dimension(50, 30));
			w.getEditor().setMaximumSize(new Dimension(200, 30));
			w.getEditor().setPreferredSize(new Dimension(200, 30));
			w.editVariable(v);
			
			//GARA
			//variablesPanel.add(new JLabel(v.getName() + ":: "));
			variablesPanel.add(new JLabel(v.getName() + ": "));
			variablesPanel.add(w);
		}
		//variablesPanel.setPreferredSize(new Dimension(250, 25 * editables.length));
		SpringUtilities.makeCompactGrid(variablesPanel,
										editables.length, 2,
										4, 4,
										4, 4
										);
		
		propertiesPanel.add(variablesPanel);
		
		JPanel attPanel = new JPanel(new SpringLayout());
		//Fill Attributes Panel (Only for VariabilityElements )
		if( elm instanceof VariabilityElement ){
			attPanel.setPreferredSize(new Dimension(150, 150));
			attPanel.add( new JLabel(mxResources.get("attributesPanel")) );
			
			AttributeListForm attList = new AttributeListForm(this, (VariabilityElement)elm);
			attPanel.add( new JScrollPane(attList) );
			
			SpringUtilities.makeCompactGrid(attPanel,
					2, 1,
					4, 4,
					4, 4
					);
			
			propertiesPanel.add(attPanel);
			
			SpringUtilities.makeCompactGrid(propertiesPanel,
					1, 2,
					4, 4,
					4, 4
					);
		}
		
		propertiesPanel.revalidate();
	}
	
	protected void onVariableEdited(Editable e){
		((ProductLineGraph)getGraphComponent().getGraph()).refreshVariable(e);
	}
	
//	public DomainRegister getDomainRegister(){
//		return domainRegister;
//	}
	
	public void loadPalettes(){
		PaletteDefinition pl = new PaletteDefinition();
		pl.name = "Product Lines";
		
		PaletteNode node = new PaletteNode();
		ScriptedVariabilityElement elm = new ScriptedVariabilityElement();
		List<Variable> atts = new ArrayList<>();
		atts.add(new Variable("height", 0, IntegerType.IDENTIFIER));
		elm.setVarAttributes(atts);
		node.prototype = elm;
		node.width = 80;
		node.height = 40;
		node.icon = "/com/cfm/productline/editor/images/plnode.png";
		node.name = "Variability Element";
		node.styleName = "plnode";
		pl.nodes.add(node);
		
		PaletteEdge edge = new PaletteEdge();
		edge.name = "Optional";
		edge.icon = "/com/cfm/productline/editor/images/ploptional.png";
		edge.styleName = "ploptional";
		edge.width = 80;
		edge.height = 40;
		edge.value = ConstraintMode.Optional;
		
		pl.edges.add(edge);

		PaletteDatabase db = new PaletteDatabase();
		db.palettes.add(pl);
		
		loadPaletteDatabase(db);
		
		try {
			FileWriter writer;
			writer = new FileWriter(new File("palettes.pal"));
			Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
			gson.toJson(db, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void loadPaletteDatabase(PaletteDatabase db) {
		for(PaletteDefinition pal : db.palettes){
			EditorPalette palette = insertPalette(pal.name);
			for(PaletteNode node : pal.nodes ){
				palette.addTemplate(
						node.name,
						new ImageIcon(
								GraphEditor.class
										.getResource(node.icon)),
						node.styleName, node.width, node.height, node.prototype);
			}
			
			for(PaletteEdge edge : pal.edges){
				palette.addEdgeTemplate(
						edge.name,
						new ImageIcon(
								GraphEditor.class
										.getResource(edge.icon)),
						edge.styleName, edge.width, edge.height, edge.value);
			}
		}
	}

}
