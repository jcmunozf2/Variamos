package com.cfm.productline.constants;

public class SWIPrologConstraintSymbolsConstant extends
		ConstraintSymbolsConstant {

	// OPERATION CONSTANTS FOR SWI PROLOG
	public static final String EQUIVALENT = "#<==>";
	public static final String SWI_CONSTRAINTS_SYMBOLS[] = { "#<==>" };

	// HEADER FOR GNU PROLOG
	public static final String HEADER = ":-use_module(library(clpfd)).\nproductline(L):-\n";
	
	public static final String END="labeling([ff], L).";
	
	public static final String IN=" in ";
	public static final String INS=" ins";
	public static final String DOMAIN_INTERVAL="..";
	public static final String ORDOMAIN="\\/";

}
