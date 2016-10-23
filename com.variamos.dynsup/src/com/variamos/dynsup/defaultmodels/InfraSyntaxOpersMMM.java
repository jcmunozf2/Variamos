package com.variamos.dynsup.defaultmodels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.ModelInstance;
import com.variamos.dynsup.model.OpersConcept;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.types.AttributeType;
import com.variamos.dynsup.types.OperationActionType;
import com.variamos.dynsup.types.OperationSubActionDefectsVerifierMethodType;
import com.variamos.dynsup.types.OperationSubActionExecType;
import com.variamos.dynsup.types.OperationSubActionType;
import com.variamos.hlcl.IntervalDomain;

public class InfraSyntaxOpersMMM {

	public static void createSyntaxOpersMetaMetaModel(
			ModelInstance modelInstance) {

		Map<String, InstElement> variabilityInstVertex = modelInstance
				.getVariabilityVertex();

		Map<String, InstPairwiseRel> constraintInstEdges = modelInstance
				.getConstraintInstEdges();

		InstElement basicOpersSyntaxM3Concept = modelInstance.getSyntaxModel()
				.getVertex("OMMConcept");

		List<Integer> dom = new ArrayList<Integer>();
		dom.add(2);
		dom.add(4);
		IntervalDomain d = new IntervalDomain();
		d.setRangeValues(dom);

		// Begin Opers M2 Model

		SyntaxElement infraSyntaxOpersM2InfraConcept = new SyntaxElement(
				'C',
				"OMnmConcept",
				false,
				false,
				"OMnmConcept",
				"infrasyntaxm2biggrayconcept",
				"Operations Non-Modifiable MetaMetaConcept: Defines an element "
						+ "with  the attributes for meta-concepts to support the visual "
						+ "representation of states and operation results",
				100, 250, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2InfraConcept.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "<<OMnmConcept>>\n"
						+ "#" + SyntaxElement.VAR_USERIDENTIFIER + "#all#\n"
						+ "<<non-modifiable>>" + "\n\n", "");

		InstConcept instInfraSyntaxOpersM2nmConcept = new InstConcept(
				"OMnmConcept", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2InfraConcept);

		variabilityInstVertex.put("OMnmConcept",
				instInfraSyntaxOpersM2nmConcept);

		SyntaxElement infraSyntaxOpersM2Concept = new SyntaxElement(
				'C',
				"OMConcept",
				true,
				true,
				"OMConcept",
				"infrasyntaxm2concept",
				"Operations MetaMetaConcept: Define a MMConcept for the operations",
				100, 150, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2Concept.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "<<OMConcept>>\n"
						+ "#" + SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n",
				"");

		InstConcept instInfraSyntaxOpersM2Concept = new InstConcept(
				"OMConcept", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2Concept);

		variabilityInstVertex.put("OMConcept", instInfraSyntaxOpersM2Concept);

		// SyntaxPairwiseRel metaMetaPairwiseRelExtends = new SyntaxPairwiseRel(
		// "ExtendsRelation",
		// false,
		// true,
		// "Extends Relation",
		// "refasextends",
		// "Extends relation: relates to concepts to extend attributes and operation constraints",
		// 50, 50, "/com/variamos/gui/pl/editor/images/plnode.png", 1,
		// null);

		// InstPairwiseRel rel = new
		// InstPairwiseRel(basicOpersM2ExtendsRelation);
		// rel.setEditableMetaElement(metaMetaPairwiseRelExtends);
		// rel.setIdentifier("ExtendsCCRel");
		// rel.setTargetRelation(instBasicOpersM2Concept, true);
		// rel.setSourceRelation(instBasicOpersM2PWRel, true);

		SyntaxElement infraSyntaxOpersM2PWRel = new SyntaxElement('P',
				"OMnmPWRel", false, false, "OMnmPWRel",
				"infrasyntaxm2minigrayconcept",
				"Operations Non-Modifiable MMPairWise Relation: Defines a "
						+ "type attribute for meta-pairwise-relations.", 150,
				150, "/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2PWRel.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "<<OMnmRel>>\n"
						+ "#" + SyntaxElement.VAR_USERIDENTIFIER + "#all#\n"
						+ "<<non-modifiable>>" + "\n\n", "");

		infraSyntaxOpersM2PWRel.addModelingAttribute(
				"relTypesAttr",
				new ElemAttribute("relTypesAttr", "Set", AttributeType.SYNTAX,
						false, "relationTypes", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		infraSyntaxOpersM2PWRel.addModelingAttribute("opersExprs",
				new ElemAttribute("opersExprs", "Set", AttributeType.SYNTAX,
						false, "Operations Meta-Model Expr.",
						InstAttribute.class.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		InstConcept instInfraSyntaxOpersM2PWRel = new InstConcept("OMnmPWRel",
				basicOpersSyntaxM3Concept, infraSyntaxOpersM2PWRel);
		// semOverTwoRelations.add(semanticAssetOperGroupRelation);
		variabilityInstVertex.put("OMnmPWRel", instInfraSyntaxOpersM2PWRel);

		SyntaxElement metaMetaPairwiseRel = new SyntaxElement(
				'P',
				"OMPWRel",
				true,
				true,
				"OMPWRel",
				"infrasyntaxopersm2miniconcept",
				"Operations MMPairWise Relation: Defines a direct relation for the operations meta-model",
				150, 150, "/com/variamos/gui/perspeditor/images/assump.png",
				true, Color.BLUE.toString(), 3, null, true);

		metaMetaPairwiseRel.addModelingAttribute(
				"relTypesAttr",
				new ElemAttribute("relTypesAttr", "Set", AttributeType.SYNTAX,
						false, "relationTypes", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		metaMetaPairwiseRel.addModelingAttribute("opersExprs",
				new ElemAttribute("opersExprs", "Set", AttributeType.SYNTAX,
						false, "Operations Meta-Model Expr.",
						InstAttribute.class.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		metaMetaPairwiseRel.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "<<OMPWRel>>\n"
						+ "#" + SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n",
				"");

		InstConcept instPairWiseRelation = new InstConcept("OMPWRel",
				basicOpersSyntaxM3Concept, metaMetaPairwiseRel);
		// semOverTwoRelations.add(semanticAssetOperGroupRelation);
		variabilityInstVertex.put("OMPWRel", instPairWiseRelation);

		/*
		 * SemanticConcept semEnumeration = new SemanticConcept(semConcept,
		 * "Enumeration"); semEnumeration.setSemanticAttribute("value", new
		 * SemanticAttribute("value", "Set", false, "value",
		 * InstAttribute.class.getCanonicalName(), new
		 * ArrayList<InstAttribute>(), 0, 1, "", "", -1, "", ""));
		 * semEnumeration.addPropEditableAttribute("01#" + "value");
		 * semEnumeration.addPropVisibleAttribute("01#" + "value");
		 * 
		 * InstConcept instSemEnumeration = new InstConcept("Enumeration", null,
		 * semEnumeration);
		 */
		// MetaEnumeration enumeration = new MetaEnumeration(
		// "TypeEnumeration",
		// true,
		// "TypeEnumeration",
		// "refasenumeration",
		// "Semantic Enumeration Type: Define an enumeration of types used in paiwise and overtwo relations",
		// 100, 150, "/com/variamos/gui/perspeditor/images/assump.png",
		// true, Color.BLUE.toString(), 3, true);
		//
		// enumeration.addPanelVisibleAttribute("04#"
		// + MetaConcept.VAR_USERIDENTIFIER);
		// enumeration.addPanelSpacersAttribute("#"
		// + MetaConcept.VAR_USERIDENTIFIER + "#\n\n");
		//
		// enumeration.addModelingAttribute("value", "Set", false, "value",
		// InstAttribute.class.getCanonicalName(),
		// new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "", "");
		// enumeration.addPropEditableAttribute("01#" + "value");
		// enumeration.addPropVisibleAttribute("01#" + "value");

		SyntaxElement infraSyntaxOpersM2InfraOTRel = new SyntaxElement('T',
				"OMnmOTRel", false, false, "OMnmOTRel",
				"infrasyntaxm2biggrayconcept",
				"Operations Non-Modifiable MMOverTwo Relation: Defines a "
						+ "type attribute for meta-pairwise-relations", 100,
				150, "/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2InfraOTRel.addModelingAttribute(
				"relTypesAttr",
				new ElemAttribute("relTypesAttr", "Set", AttributeType.SYNTAX,
						false, "relationTypes", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		infraSyntaxOpersM2InfraOTRel.addModelingAttribute(
				"opersExprs",
				new ElemAttribute("opersExprs", "Set", AttributeType.SYNTAX,
						false, "semanticExpressions", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		infraSyntaxOpersM2InfraOTRel.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "<<OMnmOTRel>>\n"
						+ "#" + SyntaxElement.VAR_USERIDENTIFIER + "#all#\n"
						+ "<<non-modifiable>>" + "\n\n", "");

		InstConcept instInfraSyntaxOpersM2nmOTRel = new InstConcept(
				"OMnmOTRel", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2InfraOTRel);
		variabilityInstVertex.put("OMnmOTRel", instInfraSyntaxOpersM2nmOTRel);

		SyntaxElement infraSyntaxOpersM2OTRel = new SyntaxElement('O',
				"OMOTRel", true, true, "OMOTRel",
				"infrasyntaxopersm2miniconcept", "Over Two Relation", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2OTRel.addModelingAttribute(
				"relTypesAttr",
				new ElemAttribute("relTypesAttr", "Set", AttributeType.SYNTAX,
						false, "relationTypes", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		infraSyntaxOpersM2OTRel.addModelingAttribute(
				"opersExprs",
				new ElemAttribute("opersExprs", "Set", AttributeType.SYNTAX,
						false, "semanticExpressions", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1, "",
						""));

		infraSyntaxOpersM2OTRel.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "<<OMOTRel>>\n"
						+ "#" + SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n",
				"");

		InstConcept instInfraSyntaxOpersM2OTRel = new InstConcept("OMOTRel",
				basicOpersSyntaxM3Concept, infraSyntaxOpersM2OTRel);
		variabilityInstVertex.put("OMOTRel", instInfraSyntaxOpersM2OTRel);

		OpersConcept basicOpersM2ExtRel = new OpersConcept("ExtRel");

		InstConcept instBasicOpersM2ExtRel = new InstConcept("ExtendsRelation",
				basicOpersSyntaxM3Concept, basicOpersM2ExtRel);

		OpersConcept basicOpersM2AsoRel = new OpersConcept("AsoRel");

		InstConcept instBasicOpersM2AsoRel = new InstConcept("OMAsoEdge",
				basicOpersSyntaxM3Concept, basicOpersM2AsoRel);

		// variabilityInstVertex.put("TypeEnumeration", new InstConcept(
		// "TypeEnumeration", metaBasicConcept, enumeration));

		SyntaxElement infraSyntaxOpersM2AsoRel = new SyntaxElement('P',
				"OMAsoEdge", false, true, "Association Relation",
				"defaultAsso", "Association Relation: ", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				instBasicOpersM2AsoRel);

		infraSyntaxOpersM2AsoRel.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n", "");

		infraSyntaxOpersM2AsoRel.addModelingAttribute(
				InstPairwiseRel.VAR_METAPAIRWISE, new ElemAttribute(
						InstPairwiseRel.VAR_METAPAIRWISE, "Class",
						AttributeType.OPERATION, true,
						InstPairwiseRel.VAR_METAPAIRWISE_NAME, "",
						InstPairwiseRel.VAR_METAPAIRWISE_CLASS,
						new SyntaxElement('P'), 0, 2, "", "", -1, "", ""));

		constraintInstEdges.put("OMAsoEdge", new InstPairwiseRel(
				infraSyntaxOpersM2AsoRel));

		SyntaxElement infraSyntaxOpersM2ExtRel = new SyntaxElement(
				'P',
				"ExtendsRelation",
				false,
				true,
				"Extends Relation",
				"refasextends",
				"Extends relation: relates to concepts to extend attributes and operation constraints",
				50, 50, "/com/variamos/gui/pl/editor/images/plnode.png", 1,
				instBasicOpersM2ExtRel);

		infraSyntaxOpersM2ExtRel.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n", "");

		InstPairwiseRel rel = new InstPairwiseRel(basicOpersM2ExtRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("OMExtCEdge");
		rel.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2Concept, true);
		constraintInstEdges.put("OMExtCEdge", rel);

		infraSyntaxOpersM2ExtRel.addModelingAttribute(
				InstPairwiseRel.VAR_METAPAIRWISE, new ElemAttribute(
						InstPairwiseRel.VAR_METAPAIRWISE, "Class",
						AttributeType.OPERATION, true,
						InstPairwiseRel.VAR_METAPAIRWISE_NAME, "",
						InstPairwiseRel.VAR_METAPAIRWISE_CLASS,
						new SyntaxElement('P'), 0, 2, "", "", -1, "", ""));

		rel = new InstPairwiseRel(basicOpersM2ExtRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("OMExtnmCEdge");
		rel.setTargetRelation(instInfraSyntaxOpersM2nmConcept, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2Concept, true);
		constraintInstEdges.put("OMExtnmCEdge", rel);

		rel = new InstPairwiseRel(basicOpersM2ExtRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("OMExtOTCEdge");
		rel.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OTRel, true);
		constraintInstEdges.put("OMExtOTCEdge", rel);

		rel = new InstPairwiseRel(basicOpersM2ExtRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("OMExtOTOdge");
		rel.setTargetRelation(instInfraSyntaxOpersM2OTRel, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OTRel, true);
		constraintInstEdges.put("OMExtOTEdge", rel);

		rel = new InstPairwiseRel(basicOpersM2ExtRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("OMExtnmOTEdge");
		rel.setTargetRelation(instInfraSyntaxOpersM2nmOTRel, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OTRel, true);
		constraintInstEdges.put("OMExtnmOTEdge", rel);

		rel = new InstPairwiseRel(basicOpersM2ExtRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2ExtRel);
		rel.setIdentifier("ExtendsPIPRel");
		rel.setTargetRelation(instInfraSyntaxOpersM2PWRel, true);
		rel.setSourceRelation(instPairWiseRelation, true);
		constraintInstEdges.put("ExtendsPIPRel", rel);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoCPWRel");
		rel.setTargetRelation(instPairWiseRelation, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2Concept, true);
		constraintInstEdges.put("AssoCPWRel", rel);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoPWCRel");
		rel.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		rel.setSourceRelation(instPairWiseRelation, true);
		constraintInstEdges.put("AssoPWCRel", rel);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoOCPWRel");
		rel.setTargetRelation(instInfraSyntaxOpersM2Concept, true);
		rel.setSourceRelation(instPairWiseRelation, true);
		constraintInstEdges.put("AssoOCPWRel", rel);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoPWOCRel");
		rel.setTargetRelation(instPairWiseRelation, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OTRel, true);
		constraintInstEdges.put("AssoPWOCRel", rel);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoCOPWRel");
		rel.setTargetRelation(instInfraSyntaxOpersM2OTRel, true);
		rel.setSourceRelation(instPairWiseRelation, true);
		constraintInstEdges.put("AssoCOPWRel", rel);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoPWCORel");
		rel.setTargetRelation(instPairWiseRelation, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2Concept, true);
		constraintInstEdges.put("AssoPWCORel", rel);

		SyntaxElement infraSyntaxOpersM2OperGroup = new SyntaxElement('M',
				"OMOperGroup", true, true, "OMOperGroup",
				"sinfrasyntaxopersm2opergroup", "Operation Group", 110, 55,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2OperGroup.addModelingAttribute("menuType",
				new ElemAttribute("menuType", "String",
						AttributeType.OPERATION, "Oper Group Type", "", "4",
						false, d, 0, 5, "", "", -1, "", ""));
		infraSyntaxOpersM2OperGroup
				.addPropEditableAttribute("05#" + "menuType");
		infraSyntaxOpersM2OperGroup.addPropVisibleAttribute("05#" + "menuType");

		infraSyntaxOpersM2OperGroup.addModelingAttribute("visible",
				new ElemAttribute("visible", "Boolean",
						AttributeType.OPERATION, false, "Visible", "", true, 0,
						8, "", "", -1, "", ""));
		infraSyntaxOpersM2OperGroup.addPropEditableAttribute("08#" + "visible");
		infraSyntaxOpersM2OperGroup.addPropVisibleAttribute("08#" + "visible");

		infraSyntaxOpersM2OperGroup.addModelingAttribute("clearButton",
				new ElemAttribute("clearButton", "Boolean",
						AttributeType.OPERATION, false,
						"Show clear errors on menu", "", false, 0, 9,
						"menuType#==#2#false", "", -1, "", ""));
		infraSyntaxOpersM2OperGroup.addPropEditableAttribute("09#"
				+ "clearButton");
		infraSyntaxOpersM2OperGroup.addPropVisibleAttribute("09#"
				+ "clearButton");

		infraSyntaxOpersM2OperGroup.addModelingAttribute("execAll",
				new ElemAttribute("execAll", "Boolean",
						AttributeType.OPERATION, false,
						"Show execute all menu with selection", "", false, 0,
						10, "menuType#==#2#false", "", -1, "", ""));
		infraSyntaxOpersM2OperGroup.addPropEditableAttribute("10#" + "execAll");
		infraSyntaxOpersM2OperGroup.addPropVisibleAttribute("10#" + "execAll");

		infraSyntaxOpersM2OperGroup.addModelingAttribute("name",
				new ElemAttribute("name", "String", AttributeType.OPERATION,
						false, "Name", "", null, 0, 6, "", "", 6, "", ""));
		infraSyntaxOpersM2OperGroup.addPropEditableAttribute("06#" + "name");
		infraSyntaxOpersM2OperGroup.addPropVisibleAttribute("06#" + "name");

		infraSyntaxOpersM2OperGroup.addModelingAttribute("shortcut",
				new ElemAttribute("shortcut", "String",
						AttributeType.OPERATION, false, "Shortcut", "", null,
						0, 7, "", "", -1, "", ""));
		infraSyntaxOpersM2OperGroup
				.addPropEditableAttribute("07#" + "shortcut");
		infraSyntaxOpersM2OperGroup.addPropVisibleAttribute("07#" + "shortcut");

		infraSyntaxOpersM2OperGroup.addModelingAttribute("Index",
				new ElemAttribute("Index", "Integer", AttributeType.OPERATION,
						false, "Position", "", 1, 0, 9, "", "", -1, "", ""));
		infraSyntaxOpersM2OperGroup.addPropEditableAttribute("09#" + "Index");
		infraSyntaxOpersM2OperGroup.addPropVisibleAttribute("09#" + "Index");

		infraSyntaxOpersM2OperGroup.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4,
				"<<OMOperGroup>>\n#" + SyntaxElement.VAR_USERIDENTIFIER
						+ "#all#\n", "");

		InstConcept instInfraSyntaxOpersM2OperGroup = new InstConcept(
				"OMOperGroup", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2OperGroup);
		variabilityInstVertex.put("OMOperGroup",
				instInfraSyntaxOpersM2OperGroup);

		SyntaxElement infraSyntaxOpersM2MetaModel = new SyntaxElement('C',
				"OMModel", true, true, "OMModel",
				"infrasyntaxopersm2miniconcept", "Semantic Model", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2MetaModel.addModelingAttribute("name",
				new ElemAttribute("name", "String", AttributeType.OPERATION,
						false, "Name", "", null, 0, 6, "", "", 4, "", ""));
		infraSyntaxOpersM2MetaModel.addPropEditableAttribute("06#" + "name");
		infraSyntaxOpersM2MetaModel.addPropVisibleAttribute("06#" + "name");

		infraSyntaxOpersM2MetaModel.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "<<OMModel>>\n"
						+ "#" + SyntaxElement.VAR_USERIDENTIFIER + "#all#\n\n",
				"");

		InstConcept instInfraSyntaxOpersM2MetaModel = new InstConcept(
				"OMModel", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2MetaModel);
		variabilityInstVertex.put("OMModel", instInfraSyntaxOpersM2MetaModel);

		SyntaxElement infraSyntaxOpersM2OperAction = new SyntaxElement('A',
				"OMOperation", true, true, "OMOperation",
				"sinfrasyntaxopersm2oper", "Operation Action", 100, 75,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2OperAction.addModelingAttribute("operType",
				new ElemAttribute("operType", "Enumeration",
						AttributeType.OPERATION, false, "Operation Type", "",
						OperationActionType.class.getCanonicalName(),
						OperationActionType.Validation_with_Dashboard, "", 0,
						5, "", "", -1, "", ""));
		infraSyntaxOpersM2OperAction.addPropEditableAttribute("05#"
				+ "operType");
		infraSyntaxOpersM2OperAction
				.addPropVisibleAttribute("05#" + "operType");
		infraSyntaxOpersM2OperAction.addModelingAttribute("name",
				new ElemAttribute("name", "String", AttributeType.OPERATION,
						false, "Name", "", null, 0, 6, "", "", -1, "", ""));
		infraSyntaxOpersM2OperAction.addPropEditableAttribute("06#" + "name");
		infraSyntaxOpersM2OperAction.addPropVisibleAttribute("06#" + "name");

		infraSyntaxOpersM2OperAction.addModelingAttribute("shortcut",
				new ElemAttribute("shortcut", "String",
						AttributeType.OPERATION, false, "Shortcut", "", null,
						0, 7, "", "", -1, "", ""));
		infraSyntaxOpersM2OperAction.addPropEditableAttribute("07#"
				+ "shortcut");
		infraSyntaxOpersM2OperAction
				.addPropVisibleAttribute("07#" + "shortcut");

		infraSyntaxOpersM2OperAction.addModelingAttribute("Index",
				new ElemAttribute("Index", "Integer", AttributeType.OPERATION,
						false, "Position", "", 1, 0, -1, "", "", -1, "", ""));
		infraSyntaxOpersM2OperAction.addPropEditableAttribute("08#" + "Index");
		infraSyntaxOpersM2OperAction.addPropVisibleAttribute("08#" + "Index");

		infraSyntaxOpersM2OperAction.addModelingAttribute("iteration",
				new ElemAttribute("iteration", "Boolean",
						AttributeType.OPERATION, false, "Iterate Button", "",
						false, 0, 9, "", "", -1, "", ""));
		infraSyntaxOpersM2OperAction.addPropEditableAttribute("09#"
				+ "iteration");
		infraSyntaxOpersM2OperAction.addPropVisibleAttribute("09#"
				+ "iteration");

		infraSyntaxOpersM2OperAction.addModelingAttribute("iterationName",
				new ElemAttribute("iterationName", "String",
						AttributeType.OPERATION, false, "Iterate Name", "",
						null, 0, 6, "", "", -1, "", ""));
		infraSyntaxOpersM2OperAction.addPropEditableAttribute("09#"
				+ "iterationName");
		infraSyntaxOpersM2OperAction.addPropVisibleAttribute("09#"
				+ "iterationName");

		infraSyntaxOpersM2OperAction.addModelingAttribute("prevSpacer",
				new ElemAttribute("prevSpacer", "Boolean",
						AttributeType.OPERATION, false, "Add Previous Spacer",
						"", false, 0, 9, "", "", -1, "", ""));
		infraSyntaxOpersM2OperAction.addPropEditableAttribute("10#"
				+ "prevSpacer");
		infraSyntaxOpersM2OperAction.addPropVisibleAttribute("10#"
				+ "prevSpacer");

		infraSyntaxOpersM2OperAction.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4,
				"<<OMOperation>>\n#" + SyntaxElement.VAR_USERIDENTIFIER
						+ "#all#\n", "");

		InstConcept instInfraSyntaxOpersM2OperAction = new InstConcept(
				"OMOperation", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2OperAction);
		variabilityInstVertex.put("OMOperation",
				instInfraSyntaxOpersM2OperAction);

		SyntaxElement infraSyntaxOpersM2OperSubAction = new SyntaxElement('S',
				"OMSubOper", true, true, "OMSubOper",
				"sinfrasyntaxopersm2suboper", "Operation Action", 100, 55,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2OperSubAction.addModelingAttribute("name",
				new ElemAttribute("name", "String", AttributeType.OPERATION,
						false, "Name", "", null, 0, 6, "", "", -1, "", ""));
		infraSyntaxOpersM2OperSubAction
				.addPropEditableAttribute("06#" + "name");
		infraSyntaxOpersM2OperSubAction.addPropVisibleAttribute("06#" + "name");

		infraSyntaxOpersM2OperSubAction.addModelingAttribute("Index",
				new ElemAttribute("Index", "Integer", AttributeType.OPERATION,
						false, "Position", "", 1, 0, 8, "", "", -1, "", ""));
		infraSyntaxOpersM2OperSubAction.addPropEditableAttribute("08#"
				+ "Index");
		infraSyntaxOpersM2OperSubAction
				.addPropVisibleAttribute("08#" + "Index");

		infraSyntaxOpersM2OperSubAction.addModelingAttribute("iteration",
				new ElemAttribute("iteration", "Boolean",
						AttributeType.OPERATION, false, "Iterate Sub-Oper", "",
						false, 0, 6, "", "", -1, "", ""));
		infraSyntaxOpersM2OperSubAction.addPropEditableAttribute("09#"
				+ "iteration");
		infraSyntaxOpersM2OperSubAction.addPropVisibleAttribute("09#"
				+ "iteration");

		infraSyntaxOpersM2OperSubAction.addModelingAttribute("type",
				new ElemAttribute("type", "Enumeration",
						AttributeType.OPERATION, true, "Type", "",
						OperationSubActionType.class.getCanonicalName(),
						OperationSubActionType.Single_Update, "", 0, 10, "",
						"", -1, "", ""));
		infraSyntaxOpersM2OperSubAction
				.addPropEditableAttribute("10#" + "type");
		infraSyntaxOpersM2OperSubAction.addPropVisibleAttribute("10#" + "type");
		infraSyntaxOpersM2OperSubAction.addModelingAttribute(
				"defectsVerifierMethod",
				new ElemAttribute("defectsVerifierMethod", "Enumeration",
						AttributeType.OPERATION, false, "DefectsVerif. Method",
						"", OperationSubActionDefectsVerifierMethodType.class
								.getCanonicalName(), "", "", 0, 11,
						"type#==#Defects verifier#\"\"", "", -1, "", ""));
		infraSyntaxOpersM2OperSubAction.addPropEditableAttribute("11#"
				+ "defectsVerifierMethod");
		infraSyntaxOpersM2OperSubAction.addPropVisibleAttribute("11#"
				+ "defectsVerifierMethod");
		infraSyntaxOpersM2OperSubAction
				.addModelingAttribute(
						"reuseFreeIds",
						new ElemAttribute(
								"reuseFreeIds",
								"Boolean",
								AttributeType.OPERATION,
								false,
								"Reuse Free Identif.",
								"Update Free Identifiers to optimize execution "
										+ "of consecutive verifications using the"
										+ " DefectsVerifier (all verification "
										+ "must share the same set of free identifiers) ",
								false, 0, 12, "type#==#Defects verifier#false",
								"", -1, "", ""));
		infraSyntaxOpersM2OperSubAction.addPropEditableAttribute("12#"
				+ "reuseFreeIds");
		infraSyntaxOpersM2OperSubAction.addPropVisibleAttribute("12#"
				+ "reuseFreeIds");
		infraSyntaxOpersM2OperSubAction
				.addModelingAttribute(
						"updateFreeIds",
						new ElemAttribute(
								"updateFreeIds",
								"Boolean",
								AttributeType.OPERATION,
								false,
								"Update Free Identif.",
								"Update Free Identifiers to optimize execution "
										+ "of consecutive verifications using the"
										+ " DefectsVerifier (all verification "
										+ "must share the same set of free identifiers) ",
								false, 0, 13, "type#==#Defects verifier#false",
								"", -1, "", ""));
		infraSyntaxOpersM2OperSubAction.addPropEditableAttribute("13#"
				+ "updateFreeIds");
		infraSyntaxOpersM2OperSubAction.addPropVisibleAttribute("13#"
				+ "updateFreeIds");

		infraSyntaxOpersM2OperSubAction.addModelingAttribute("errorTitle",
				new ElemAttribute("errorTitle", "String",
						AttributeType.OPERATION, false, "Error title", "", "",
						0, 14, "", "", -1, "", ""));
		infraSyntaxOpersM2OperSubAction.addPropEditableAttribute("14#"
				+ "errorTitle");
		infraSyntaxOpersM2OperSubAction.addPropVisibleAttribute("14#"
				+ "errorTitle");

		infraSyntaxOpersM2OperSubAction.addModelingAttribute("errorText",
				new ElemAttribute("errorText", "String",
						AttributeType.OPERATION, false, "Error general text",
						"", "", 0, 15, "", "", -1, "", ""));
		infraSyntaxOpersM2OperSubAction.addPropEditableAttribute("15#"
				+ "errorText");
		infraSyntaxOpersM2OperSubAction.addPropVisibleAttribute("15#"
				+ "errorText");

		infraSyntaxOpersM2OperSubAction.addModelingAttribute("errorHint",
				new ElemAttribute("errorHint", "String",
						AttributeType.OPERATION, false, "Error Hint", "", "",
						0, 16, "", "", -1, "", ""));
		infraSyntaxOpersM2OperSubAction.addPropEditableAttribute("16#"
				+ "errorHint");
		infraSyntaxOpersM2OperSubAction.addPropVisibleAttribute("16#"
				+ "errorHint");

		infraSyntaxOpersM2OperSubAction.addModelingAttribute("errorMsg",
				new ElemAttribute("errorMsg", "String",
						AttributeType.OPERATION, false, "Error Message", "",
						"", 0, 17, "", "", -1, "", ""));
		infraSyntaxOpersM2OperSubAction.addPropEditableAttribute("17#"
				+ "errorMsg");
		infraSyntaxOpersM2OperSubAction.addPropVisibleAttribute("17#"
				+ "errorMsg");

		infraSyntaxOpersM2OperSubAction.addModelingAttribute(
				"exptype",
				new ElemAttribute("exptype", "Set", AttributeType.SYNTAX,
						false, "exptype", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, 2, "", "", 6, "#\n",
						""));

		infraSyntaxOpersM2OperSubAction.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "<<OMSubOper>>\n#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n", "");

		InstConcept instInfraSyntaxOpersM2OperSubAction = new InstConcept(
				"OMSubOper", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2OperSubAction);
		variabilityInstVertex.put("OMSubOper",
				instInfraSyntaxOpersM2OperSubAction);

		SyntaxElement infraSyntaxOpersM2OperLabeling = new SyntaxElement('S',
				"OMLabeling", true, true, "OMLabeling",
				"sinfrasyntaxopersm2label", "Operation Labeling", 100, 45,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2OperLabeling.addModelingAttribute("labelId",
				new ElemAttribute("labelId", "String", AttributeType.OPERATION,
						false, "Label ID", "", null, 0, 5, "", "", -1, "", ""));
		infraSyntaxOpersM2OperLabeling.addPropEditableAttribute("05#"
				+ "labelId");
		infraSyntaxOpersM2OperLabeling.addPropVisibleAttribute("05#"
				+ "labelId");

		infraSyntaxOpersM2OperLabeling
				.addModelingAttribute(
						"outputSet",
						new ElemAttribute(
								"outputSet",
								"Boolean",
								AttributeType.OPERATION,
								false,
								"Output Set",
								"Include set of variables in the constraint program call to obtain the variables",
								true, 0, 6, "", "", -1, "", ""));
		infraSyntaxOpersM2OperLabeling.addPropEditableAttribute("06#"
				+ "outputSet");
		infraSyntaxOpersM2OperLabeling.addPropVisibleAttribute("06#"
				+ "outputSet");

		infraSyntaxOpersM2OperLabeling
				.addModelingAttribute(
						"includeLabel",
						new ElemAttribute(
								"includeLabel",
								"Boolean",
								AttributeType.OPERATION,
								true,
								"Include Label",
								"Include label in the constraint program (or only define the set of variables)",
								true, 0, 7, "", "", -1, "", ""));
		infraSyntaxOpersM2OperLabeling.addPropEditableAttribute("07#"
				+ "includeLabel");
		infraSyntaxOpersM2OperLabeling.addPropVisibleAttribute("07#"
				+ "includeLabel");

		infraSyntaxOpersM2OperLabeling.addModelingAttribute("position",
				new ElemAttribute("position", "Integer",
						AttributeType.OPERATION, false, "Position", "", 1, 0,
						8, "includeLabel" + "#==#" + "true", "", -1, "", ""));
		infraSyntaxOpersM2OperLabeling.addPropEditableAttribute("08#"
				+ "position");
		infraSyntaxOpersM2OperLabeling.addPropVisibleAttribute("08#"
				+ "position");

		infraSyntaxOpersM2OperLabeling.addModelingAttribute("once",
				new ElemAttribute("once", "Boolean", AttributeType.OPERATION,
						false, "Once", "", false, 0, 8, "includeLabel" + "#==#"
								+ "true", "", -1, "", ""));
		infraSyntaxOpersM2OperLabeling.addPropEditableAttribute("08#" + "once");
		infraSyntaxOpersM2OperLabeling.addPropVisibleAttribute("08#" + "once");

		infraSyntaxOpersM2OperLabeling.addModelingAttribute("order",
				new ElemAttribute("order", "Boolean", AttributeType.OPERATION,
						false, "Order", "", false, 0, 9, "includeLabel"
								+ "#==#" + "true", "", -1, "", ""));
		infraSyntaxOpersM2OperLabeling
				.addPropEditableAttribute("09#" + "order");
		infraSyntaxOpersM2OperLabeling.addPropVisibleAttribute("09#" + "order");

		infraSyntaxOpersM2OperLabeling.addModelingAttribute(
				"sortorder",
				new ElemAttribute("sortorder", "Set", AttributeType.SYNTAX,
						false, "sortorder", InstAttribute.class
								.getCanonicalName(),
						new ArrayList<InstAttribute>(), 0, -1, "", "", -1,
						"#\n", ""));

		infraSyntaxOpersM2OperLabeling.addModelingAttribute(
				SyntaxElement.VAR_USERIDENTIFIER, "String", false,
				"User Identifier", "", "", 0, 4, "", "", 4, "<<OMLabeling>>\n#"
						+ SyntaxElement.VAR_USERIDENTIFIER + "#all#\n", "");

		InstConcept instInfraSyntaxOpersM2OperLabeling = new InstConcept(
				"OMLabeling", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2OperLabeling);
		variabilityInstVertex.put("OMLabeling",
				instInfraSyntaxOpersM2OperLabeling);

		SyntaxElement infraSyntaxOpersM2ExpType = new SyntaxElement('S',
				"OMExpType", false, true, "OMExpType", "whitebox",
				"Operation Expression Type", 100, 75,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.BLUE.toString(), 3, null, true);

		infraSyntaxOpersM2ExpType.addModelingAttribute(
				"suboperexptype",
				new ElemAttribute("suboperexptype", "Enumeration",
						AttributeType.OPERATION, false, "Expression Type", "",
						OperationSubActionExecType.class.getCanonicalName(),
						new InstAttribute("enumName", new ElemAttribute(
								"EnumNameValue", "Enumeration",
								AttributeType.SYNTAX, false, "Value Name", "",
								OperationSubActionExecType.class
										.getCanonicalName(), "", "RELAXABLE",
								1, -1, "", "", -1, "", ""), ""), "", 0, 6, "",
						"", 7, "", ""));

		InstConcept instInfraSyntaxOpersM2ExpType = new InstConcept(
				"OMExpType", basicOpersSyntaxM3Concept,
				infraSyntaxOpersM2ExpType);
		variabilityInstVertex.put("OMExpType", instInfraSyntaxOpersM2ExpType);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoMenu-Act");
		rel.setTargetRelation(instInfraSyntaxOpersM2OperAction, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OperGroup, true);
		constraintInstEdges.put("AssoMenu-Act", rel);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoAct-SubAct");
		rel.setTargetRelation(instInfraSyntaxOpersM2OperSubAction, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OperAction, true);
		constraintInstEdges.put("AssoAct-SubAct", rel);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoSubAct-Lab");
		rel.setTargetRelation(instInfraSyntaxOpersM2OperLabeling, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OperSubAction, true);
		constraintInstEdges.put("AssoSubAct-Lab", rel);

		rel = new InstPairwiseRel(basicOpersM2AsoRel);
		rel.setEdSyntaxEle(infraSyntaxOpersM2AsoRel);
		rel.setIdentifier("AssoSubAct-ExpType");
		rel.setTargetRelation(instInfraSyntaxOpersM2ExpType, true);
		rel.setSourceRelation(instInfraSyntaxOpersM2OperSubAction, true);
		constraintInstEdges.put("AssoSubAct-ExpType", rel);
	}
}
