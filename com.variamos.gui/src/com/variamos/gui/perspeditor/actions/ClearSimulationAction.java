package com.variamos.gui.perspeditor.actions;

import java.awt.event.ActionEvent;

import com.mxgraph.util.mxResources;
import com.variamos.dynsup.translation.ModelExpr2HLCL;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.VariamosGraphComponent;
import com.variamos.gui.maineditor.VariamosGraphEditor;

@SuppressWarnings("serial")
public class ClearSimulationAction extends AbstractEditorAction {

	public ClearSimulationAction() {

		this.putValue(SHORT_DESCRIPTION, mxResources.get("resetSimulation"));
	}

	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {
		VariamosGraphEditor editor = getEditor(e);
		((VariamosGraphComponent) editor.getGraphComponent())
				.setSimulationStarted(false);
		editor.clearQueryMonitor();
		editor.clearElementState(ModelExpr2HLCL.SIMUL_EXEC);
	}
}