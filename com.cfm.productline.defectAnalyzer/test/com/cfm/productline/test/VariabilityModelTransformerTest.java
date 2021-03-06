package com.cfm.productline.test;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.Test;

import com.cfm.productline.ProductLine;
import com.cfm.productline.dto.VMTransformerInDTO;
import com.cfm.productline.exceptions.TransformerException;
import com.cfm.productline.io.SXFMReader;
import com.cfm.productline.model.defectAnalyzerModel.VariabilityModel;
import com.cfm.productline.model.enums.NotationType;
import com.cfm.productline.model.enums.SolverEditorType;
import com.cfm.productline.transformer.VariabilityModelTransformer;

import fm.FeatureModelException;

public class VariabilityModelTransformerTest {


	public void transformProductLine() {
		// Variable del modelo a analizar
		SXFMReader reader = new SXFMReader();
		ProductLine pl = null;

		try {
			pl = reader
					.readFile("test/testModels/Betty5Features_FeatureModel10.sxfm");
		} catch (FeatureModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Se instancia el transformador
		VMTransformerInDTO transformerInDTO = new VMTransformerInDTO();
		transformerInDTO
				.setNotationType(NotationType.PRODUCT_LINE);
		transformerInDTO.setProductLineConfigurator(pl);
		VariabilityModel variabilityModel = null;
		VariabilityModelTransformer transformer = new VariabilityModelTransformer(
				transformerInDTO);
		try {
			variabilityModel = transformer
					.transformToVariabilityModel();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertNotNull(variabilityModel);
		Assert.assertNotNull(variabilityModel.getDependencies());
		System.out.println("The Product Line was correctly transformed \n");
		transformer.printTransformedModelSWIProlog(variabilityModel);
	}


	public void transformFeatureModel() {

		String modelInputPath = "test/testModels/Betty5Features_FeatureModel10.sxfm";
		// Se instancia el transformador
		VMTransformerInDTO transformerInDTO = new VMTransformerInDTO();
		transformerInDTO.setNotationType(NotationType.FEATURES_MODELS);
		transformerInDTO.setPathToTransform(modelInputPath);

		VariabilityModel variabilityModel = null;
		VariabilityModelTransformer transformer = new VariabilityModelTransformer(
				transformerInDTO);
		try {
			variabilityModel = transformer
					.transformToVariabilityModel();
			Assert.assertTrue(variabilityModel != null);
			System.out.println("The FM was correctly transformed \n");
			System.out.println("FEATURE MODEL:");
			transformer.printTransformedModelSWIProlog(variabilityModel);
			Assert.assertNotNull(variabilityModel);
			Assert.assertNotNull(variabilityModel.getDependencies());
		} catch (TransformerException e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void transformPrologFile() {
		// Ruta absoluta del modelo a transformar
		// String modelInputPath =
		// "F:\\LINEASPRODUC_WORKSPACE\\com.cfm.productline.defectAnalyzer\\test\\testModels\\Bycicle_27_fm.pl";
		// String modelInputPath =
		// "F:\\LINEASPRODUC_WORKSPACE\\com.cfm.productline.defectAnalyzer\\test\\testModels\\prologFiles\\UNIX_model_1.pl";

		String modelInputPath = "F:\\LINEASPRODUC_WORKSPACE\\com.cfm.productline.defectAnalyzer\\test\\testModels\\prologFiles\\Rexel_raul_29_06_2013.pl";

		// Se instancia el transformador
		VMTransformerInDTO transformerInDTO = new VMTransformerInDTO();

		// Se asignan los parámetros
		transformerInDTO.setNotationType(NotationType.PROLOG);
		transformerInDTO
				.setPrologEditorTypeFileToTransform(SolverEditorType.GNU_PROLOG);
		transformerInDTO.setPathToTransform(modelInputPath);

		try {

			// Se crea un nuevo objeto transformador con los parámetros de
			// entrada
			VariabilityModelTransformer transformer = new VariabilityModelTransformer(
					transformerInDTO);
			VariabilityModel variabilityModel = null;

			// Se invoca al método que realiza la transformación
			variabilityModel = transformer.transformToVariabilityModel();

			Assert.assertTrue(variabilityModel != null);
			System.out.println("The FM was correctly transformed \n");
			System.out.println("PROLOG FILE:");
			transformer.printTransformedModelGNUProlog(variabilityModel);
			Assert.assertNotNull(variabilityModel);
			Assert.assertNotNull(variabilityModel.getDependencies());
		} catch (TransformerException e) {
			e.printStackTrace();
			fail();
		}

	}
	
	@Test
	public void transformBooleanPrologFile() {

		String modelInputPath="F:\\LINEASPRODUC_WORKSPACE\\com.cfm.productline.defectAnalyzer\\test\\testModels\\Bycicle_27_fm.pl";
		
		// Se instancia el transformador
		VMTransformerInDTO transformerInDTO = new VMTransformerInDTO();
		transformerInDTO.setNotationType(NotationType.PROLOG);
		transformerInDTO.setPrologEditorTypeFileToTransform(SolverEditorType.GNU_PROLOG);
		transformerInDTO.setPathToTransform(modelInputPath);

		VariabilityModel variabilityModel = null;
		VariabilityModelTransformer transformer = new VariabilityModelTransformer(
				transformerInDTO);

		try {
			variabilityModel = transformer
					.transformToVariabilityModel();

		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

	}
}
