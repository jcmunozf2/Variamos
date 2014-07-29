package com.cfm.productline.configurator;

import java.util.Map;

import com.cfm.hlcl.HlclProgram;
import com.cfm.jgprolog.core.PrologContext;
import com.cfm.jgprolog.core.PrologEngine;
import com.cfm.jgprolog.core.PrologException;
import com.cfm.jgprolog.core.PrologTermFactory;
import com.cfm.jgprolog.gnuprolog.GNUPrologContext;
import com.cfm.productline.ProductLine;
import com.cfm.productline.configurator.treetable.ConfigurationNode;
import com.cfm.productline.productLine.Pl2Hlcl;
import com.cfm.productline.prologEditors.Hlcl2GnuPrologExact;
import com.cfm.productline.prologEditors.PrologTransformParameters;
import com.cfm.productline.solver.PrologSolver;

public class ConfiguratorUtils {
	
	public static void reduceDomain(Map<String, ConfigurationNode> nodes, ProductLine pl){
		
		PrologContext ctx = new GNUPrologContext();
		
		PrologEngine prolog = ctx.getEngine();
		PrologTermFactory ptf = ctx.getTermFactory();
		
		HlclProgram prog = Pl2Hlcl.transformExact(pl);
		Hlcl2GnuPrologExact t = new Hlcl2GnuPrologExact();
		
		PrologTransformParameters params = new PrologTransformParameters();
		params.setFdLabeling(false);
		String code = t.transform(prog, params);
		
		try {
			PrologSolver.consultString(prolog, code);
		} catch (PrologException e) {
			e.printStackTrace();
		}
		
		//Query the pl.
		
		
	}
}
