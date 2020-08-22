package com.mak.remak.interpreter;

public class InterpreterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InterpreterException() {
		super("Exception while calculating expression...");
		
	}

	public InterpreterException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InterpreterException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InterpreterException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InterpreterException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
