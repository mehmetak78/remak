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

	protected static Boolean isUnary(String str) {
		switch (str) {
			case "NOT":
				return true;

		}
		return false;
	}

	protected static String calculate(String operand, String leftValue, String rightValue) throws InterpreterException {
		Integer leftValueInt = null;
		Integer rightValueInt = null;
		try {
			leftValueInt = Integer.parseInt(leftValue);
			rightValueInt = Integer.parseInt(rightValue);
		}
		catch (NumberFormatException e) {
			leftValueInt = null;
			rightValueInt = null;
		}
		Integer result = 0;
		try {
			switch (operand) {
				case "AND":
					if (getBoolVal(leftValueInt) && getBoolVal(rightValueInt)) {
						result = 1;
					}
					break;
				case "OR":
					if (getBoolVal(leftValueInt) || getBoolVal(rightValueInt)) {
						result = 1;
					}
					break;
				case "=":
					if (leftValueInt != null) {
						if (leftValueInt == rightValueInt) {
							result = 1;
						}
					}
					else {
						if (leftValue.compareTo(rightValue) == 0) {
							result = 1;
						}
					}
					break;
				case "<":
					if (leftValueInt != null) {
						if (leftValueInt < rightValueInt) {
							result = 1;
						}
					}
					else {
						if (leftValue.compareTo(rightValue) < 0) {
							result = 1;
						}
					}
					break;
				case "<=":
					if (leftValueInt != null) {
						if (leftValueInt <= rightValueInt) {
							result = 1;
						}
					}
					else {
						if (leftValue.compareTo(rightValue) <= 0) {
							result = 1;
						}
					}
					break;
				case ">":
					if (leftValueInt != null) {
						if (leftValueInt > rightValueInt) {
							result = 1;
						}
					}
					else {
						if (leftValue.compareTo(rightValue) > 0) {
							result = 1;
						}
					}
					break;
				case ">=":
					if (leftValueInt != null) {
						if (leftValueInt >= rightValueInt) {
							result = 1;
						}
					}
					else {
						if (leftValue.compareTo(rightValue) >= 0) {
							result = 1;
						}
					}
					break;
				case "+":
					result = leftValueInt + rightValueInt;
					break;
				case "-":
					result = leftValueInt - rightValueInt;
					break;
				case "*":
					result = leftValueInt * rightValueInt;
					break;
				case "**":
					result = (int) Math.pow(leftValueInt, rightValueInt);
					break;
				case "/":
					result = leftValueInt / rightValueInt;
					break;
				case "NOT":
					if (getBoolVal(rightValueInt)) {
						result = 0;
					}
					result = 1;
					break;
			}
		} catch (Exception e) {
			System.out.println("Exception Here");
			throw new InterpreterException();
		}
		return result.toString();
	}

}
