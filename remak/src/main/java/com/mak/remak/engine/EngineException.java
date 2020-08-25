package com.mak.remak.engine;

public class EngineException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EngineException() {
		super("Exception in the Engine...");
		
	}

	public EngineException(String message) {
		super(message);
	}

}
