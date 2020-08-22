package com.mak.remak.interpreter;

public class Calculator {

	protected static int getPriority(String str) {
		switch (str) {
		case "OR":
			return 5;
		case "AND":
			return 10;
		case "<":
		case ">":
		case "<=":
		case ">=":
		case "=":
			return 15;
		case "+":
		case "-":
			return 20;
		case "*":
		case "/":
			return 25;
		case "**":
			return 30;
		case "NOT":
			return 40;

		}
		return 9999;
	}
	
	protected static Boolean getBoolVal(int val) {
		return val > 0;
		
	}
	
	protected static int calculate(String operand, int leftValueInt, int rightValueInt) throws InterpreterException {
		try {
			switch (operand) {
			case "AND":
				if ( getBoolVal(leftValueInt) && getBoolVal(rightValueInt) ){
					return 1;
				}
				return 0;
			case "OR":
				if ( getBoolVal(leftValueInt) || getBoolVal(rightValueInt) ){
					return 1;
				}
				return 0;
			case "=":
				if (leftValueInt == rightValueInt) {
					return 1;
				}
				return 0;
			case "<":
				if (leftValueInt < rightValueInt) {
					return 1;
				}
				return 0;
			case "<=":
				if (leftValueInt <= rightValueInt) {
					return 1;
				}
				return 0;
			case ">":
				if (leftValueInt > rightValueInt) {
					return 1;
				}
				return 0;
			case ">=":
				if (leftValueInt >= rightValueInt) {
					return 1;
				}
				return 0;
			case "+":
				return leftValueInt + rightValueInt;
			case "-":
				return leftValueInt - rightValueInt;
			case "*":
				return leftValueInt * rightValueInt;
			case "**":
				return (int) Math.pow(leftValueInt, rightValueInt);
			case "/":
				return leftValueInt / rightValueInt;
			case "NOT":
				if ( getBoolVal(rightValueInt) ){
					return 0;
				}
				return 1;
			}
		} catch (Exception e) {
			System.out.println("Exception Here");
			throw new InterpreterException();
		}
		return 0;
	}
	
	
}
