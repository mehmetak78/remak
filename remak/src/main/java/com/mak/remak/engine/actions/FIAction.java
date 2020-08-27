package com.mak.remak.engine.actions;

/**
 * A functional interface, so that we can write actions using java 8 lambdas.
 */

@FunctionalInterface
public interface FIAction<Input, Output> {
	Output execute(Input input);
}
