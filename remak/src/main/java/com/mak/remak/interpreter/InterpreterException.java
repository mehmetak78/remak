package com.mak.remak.interpreter;

public class InterpreterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InterpreterException() {
		super("Exception while calculating expression...");
		
	}

	public InterpreterException(String message) {
		super(message);
	}

}
