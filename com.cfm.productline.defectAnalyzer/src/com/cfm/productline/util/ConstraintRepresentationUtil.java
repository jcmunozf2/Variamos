package com.cfm.productline.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclProgram;
import com.cfm.hlcl.HlclUtil;
import com.cfm.hlcl.Identifier;
import com.cfm.productline.exceptions.FunctionalException;
import com.cfm.productline.exceptions.TechnicalException;
import com.cfm.productline.model.defectAnalyzerModel.Dependency;
import com.cfm.productline.model.enums.SolverEditorType;
import com.cfm.productline.prologEditors.Hlcl2GnuProlog;
import com.cfm.productline.prologEditors.Hlcl2SWIProlog;

public class ConstraintRepresentationUtil {

	/**
	 * Realiza la lógica de convertir la representación de constraints agnostica
	 * a el solver que corresponda y guardarlo en un archivo temporal que luego
	 * es analizado
	 * 
	 * @param prologTempPath
	 * @param constraintRepresentation
	 * @param solverEditorType
	 * @throws FunctionalException
	 */
	public static void savePrologRepresentationProgram(String prologTempPath,
			Collection<Expression> constraintRepresentation,
			SolverEditorType solverEditorType) throws FunctionalException {

		String constraintProgramString = constraintToPrologProgram(
				constraintRepresentation, solverEditorType);

		// Se guarda la representación en el archivo temporal de prolog
		FileUtils.writeFile(prologTempPath, constraintProgramString);
	}
	
	
	public static void savePrologRepresentationProgram(String prologTempPath,
			Collection<Expression> constraintRepresentation,List<String>domainList,
			SolverEditorType solverEditorType) throws FunctionalException {

		String constraintProgramString = constraintToPrologProgram(
				constraintRepresentation, domainList,solverEditorType);

		// Se guarda la representación en el archivo temporal de prolog
		FileUtils.writeFile(prologTempPath, constraintProgramString);
	}

	public static String constraintToPrologProgram(
			Collection<Expression> constraintRepresentation,
			SolverEditorType solverEditorType) throws FunctionalException {

		HlclProgram hlclProgram = new HlclProgram();
		String constraintProgramString = new String();
		if (!constraintRepresentation.isEmpty()) {
			hlclProgram = expressionToHlclProgram(constraintRepresentation);
			if (SolverEditorType.SWI_PROLOG.equals(solverEditorType)) {
				Hlcl2SWIProlog swiPrologTransformer = new Hlcl2SWIProlog();
				constraintProgramString = swiPrologTransformer
						.transform(hlclProgram);
			} else if (SolverEditorType.GNU_PROLOG.equals(solverEditorType)) {
				Hlcl2GnuProlog gnuPrologTransformer = new Hlcl2GnuProlog();
				constraintProgramString = gnuPrologTransformer
						.transform(hlclProgram);
			} else {
				throw new TechnicalException(
						"None  transformer is implemented for the solver "
								+ solverEditorType.name());
			}
		} else {
			throw new FunctionalException(
					"Not received any expression that represents the variability model");
		}
		return constraintProgramString;
	}

	
	
	public static String constraintToPrologProgram(
			Collection<Expression> constraintRepresentation,List<String>domainList,
			SolverEditorType solverEditorType) throws FunctionalException {

		HlclProgram hlclProgram = new HlclProgram();
		String constraintProgramString = new String();
		if (!constraintRepresentation.isEmpty()) {
			hlclProgram = expressionToHlclProgram(constraintRepresentation);
			if (SolverEditorType.GNU_PROLOG.equals(solverEditorType)) {
				Hlcl2GnuProlog gnuPrologTransformer = new Hlcl2GnuProlog();
				constraintProgramString = gnuPrologTransformer
						.transform(hlclProgram,domainList);
			} else {
				throw new TechnicalException(
						"None  transformer is implemented for the solver "
								+ solverEditorType.name());
			}
		} else {
			throw new FunctionalException(
					"Not received any expression that represents the variability model");
		}
		return constraintProgramString;
	}
	
	public static HlclProgram expressionToHlclProgram(
			Collection<Expression> constraintRepresentation) {
		HlclProgram hlclProgram = new HlclProgram();
		for (Expression expression : constraintRepresentation) {
			hlclProgram.add((BooleanExpression) expression);
		}
		return hlclProgram;
	}

	public static Collection<Expression> dependencyListToExpressionList(
			Collection<Dependency> dependencies) {
		Collection<Expression> variabilityModelConstraintRepresentation = new HashSet<Expression>();

		// Se recorre la lista de restricciones a incluir
		for (Dependency dependency : dependencies) {
			variabilityModelConstraintRepresentation.add(dependency
					.getConstraintExpression());
		}
		return variabilityModelConstraintRepresentation;
	}

	public static Collection<Expression> dependencyToExpressionList(
			Map<Long, Dependency> dependenciesSet,
			Map<Long, Dependency> fixedDependencies) {

		Map<Long, Dependency> allDependenciesMap = new HashMap<Long, Dependency>();
		allDependenciesMap.putAll(dependenciesSet);
		allDependenciesMap.putAll(fixedDependencies);
		return dependencyListToExpressionList(allDependenciesMap.values());

	}

	public static Collection<Expression> dependencyToExpressionList(
			List<Dependency> dependenciesSet, List<Dependency> fixedDependencies) {

		List<Dependency> allDependenciesList = new ArrayList<Dependency>();
		allDependenciesList.addAll(dependenciesSet);
		allDependenciesList.addAll(fixedDependencies);
		return dependencyListToExpressionList(allDependenciesList);

	}
	
	public static Collection<Expression> dependencyToExpressionList(
			List<Dependency> dependenciesSet,Map<Long, Dependency> fixedDependencies) {

		List<Dependency> allDependenciesList = new ArrayList<Dependency>();
		allDependenciesList.addAll(dependenciesSet);
		allDependenciesList.addAll(fixedDependencies.values());
		return dependencyListToExpressionList(allDependenciesList);

	}

	/**
	 * Se hace este método para que el resto del código no tenga que preocuparse
	 * por volver las expressions hlclcPrograms
	 * 
	 * @param constraintRepresentation
	 * @return
	 */
	public static Collection<Identifier> getIdentifiersSet(
			Collection<Expression> constraintRepresentation) {
		HlclProgram hlclProgram = new HlclProgram();
		hlclProgram
				.addAll((Collection<? extends BooleanExpression>) constraintRepresentation);
		Collection<Identifier> ids = HlclUtil.getUsedIdentifiers(hlclProgram);
		return ids;

	}
}
