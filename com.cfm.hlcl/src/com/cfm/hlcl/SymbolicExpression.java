package com.cfm.hlcl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SymbolicExpression implements BooleanExpression{
	
	protected String name;
	protected List<Identifier> args;
	
	protected SymbolicExpression() {
		super();
		args = new LinkedList<>();
	}

	protected SymbolicExpression(String name, Identifier... args) {
		super();
		this.name = name;
		this.args = Arrays.asList( args );
	}

	public String getName() {
		return name;
	}

	public List<Identifier> getArgs() {
		return args;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setArgs(List<Identifier> args) {
		this.args = args;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SymbolicExpression [name=" + name + ", args=" + args + "]";
	}

	
}
