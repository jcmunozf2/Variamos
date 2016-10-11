package com.variamos.hlcl;

public class NumericIdentifier implements NumericExpression {
	protected int intValue;
	protected float floatValue;

	protected NumericIdentifier(int value) {
		super();
		this.intValue = value;
	}

	protected NumericIdentifier(float value) {
		super();
		this.floatValue = value;
	}

	/**
	 * jcmunoz: added to validate expressions in editor
	 * 
	 * @return true if the expression has all the components
	 */
	@Override
	public boolean isValidExpression() {
		return true;
	};

	public int getValue() {
		return intValue;
	}

	public float getFloatValue() {
		return floatValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NumericIdentifier [value=" + intValue + "]";
	}

	public String toFloatString() {
		return "NumericIdentifier [value=" + floatValue + "]";
	}

}
