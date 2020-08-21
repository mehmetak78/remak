package com.mak.remak.interpreter;

public class Operand {
	
	int priority;
	String value;
	
	public Operand(int priority, String value) {
		super();
		this.priority = priority;
		this.value = value;
	}

	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
