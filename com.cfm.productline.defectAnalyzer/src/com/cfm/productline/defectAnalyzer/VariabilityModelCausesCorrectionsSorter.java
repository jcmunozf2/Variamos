package com.cfm.productline.defectAnalyzer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cfm.productline.model.defect.Defect;
import com.cfm.productline.model.defectAnalyzerModel.Dependency;
import com.cfm.productline.model.diagnostic.ClassifiedDiagnosis;
import com.cfm.productline.model.diagnostic.DefectsByMCSMUSes;
import com.cfm.productline.model.diagnostic.Diagnostic;
import com.cfm.productline.model.enums.ClassificationType;
import com.cfm.productline.util.SetUtil;

public class VariabilityModelCausesCorrectionsSorter {

	public VariabilityModelCausesCorrectionsSorter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClassifiedDiagnosis classifyDiagnosis(
			List<Diagnostic> allDiagnostics,
			ClassificationType classsificationType) {

		// Almacena la colecci�n completa de todos los MUSes o de todos los
		// MCSes seg�n el par�metro de entrada
		List<List<Dependency>> collectionALLDiagnosisElements = new ArrayList<List<Dependency>>();
		Set<List<Dependency>> collectionALLDiagnosisElementsSet = new HashSet<List<Dependency>>();
		List<DefectsByMCSMUSes> commonDiagnosis = new ArrayList<DefectsByMCSMUSes>();
		List<DefectsByMCSMUSes> noCommonDiagnosis = new ArrayList<DefectsByMCSMUSes>();
		

		// Se obtiene la colecci�n de todos los MUSES o de todas las causas
		// seg�n el classificationType
		for (Diagnostic diagnosticElement : allDiagnostics) {
			if (classsificationType.equals(ClassificationType.CAUSES)) {
				collectionALLDiagnosisElements.addAll(diagnosticElement
						.getCauses());
			} else if (classsificationType
					.equals(ClassificationType.CORRECTIONS)) {
				collectionALLDiagnosisElements.addAll(diagnosticElement
						.getCorrectionSubsets());
			}

		}

		
		//Se pasa a Set para eliminar repetidos
		for(List<Dependency> listDependency:collectionALLDiagnosisElements){
			collectionALLDiagnosisElementsSet.add(listDependency);
		}

		// Se eliminan los repetidos que ten�an orden distinto y no eran detectados por el equals de la clase Dependency
		collectionALLDiagnosisElements.clear();
		collectionALLDiagnosisElements.addAll(SetUtil.maintainNoEqualsSets(collectionALLDiagnosisElementsSet));
		
		// Se recorre la uni�n anteriormente obtenida ( es una causa una
		// correci�n)
		long id=1;
		for (List<Dependency> set : collectionALLDiagnosisElements) {

			List<Defect> defects = searchDiagnosisOnDefects(allDiagnostics,
					set, classsificationType);
			DefectsByMCSMUSes defectsByMCSMUSes = new DefectsByMCSMUSes(set,id,
					defects);
			if (defects != null && defects.size()>1) {
				commonDiagnosis.add(defectsByMCSMUSes);
			} else {
				noCommonDiagnosis.add(defectsByMCSMUSes);
			}
			id++;
		}

		// Se construye el elemento clasificado a retornar
		ClassifiedDiagnosis classifiedDiagnosis = new ClassifiedDiagnosis(
				commonDiagnosis, noCommonDiagnosis);

		return classifiedDiagnosis;
	}

	/**
	 * Busca los defectos para los que se encuentra ese MCS igual
	 * 
	 * @param defectsList
	 * @param MCS
	 * @return
	 */
	private List<Defect> searchDiagnosisOnDefects(
			List<Diagnostic> allDiagnostics, List<Dependency> MCS,
			ClassificationType classsificationType) {

		// Lista de defectos en los que se encuentra el MCS
		List<Defect> defects = new ArrayList<Defect>();
		for (Diagnostic diagnostic : allDiagnostics) {

			if (classsificationType.equals(ClassificationType.CAUSES)) {
				// Se verifica si en la colecci�n de causas de este diagnostico
				// esta ese MCS
				if (SetUtil.verifyExistSetInSetofSets(MCS,
						diagnostic.getCauses())) {
					defects.add(diagnostic.getDefect());
				}
			} else if (classsificationType
					.equals(ClassificationType.CORRECTIONS)) {

				// Se verifica si en la colecci�n de causas de este diagnostico
				// esta ese MCS
				if (SetUtil.verifyExistSetInSetofSets(MCS,
						diagnostic.getCorrectionSubsets())) {
					defects.add(diagnostic.getDefect());

				}

			}

		}
		return defects;
	}
	

}
