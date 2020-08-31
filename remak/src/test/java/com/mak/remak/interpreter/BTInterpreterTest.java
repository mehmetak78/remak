package com.mak.remak.interpreter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BTInterpreterTest {

	private Boolean showCalculation = true;

	@Test
	void testExpressionErrors() {
		System.out.println("testExpressionErrors()");
		assertThrows(InterpreterException.class, () -> {
			BTInterpreter bt;
			bt = BTInterpreter.parseExpression(" + 3", showCalculation);
			bt.traverseCalculate();
		});
		assertThrows(InterpreterException.class, () -> {
			BTInterpreter bt;
			bt = BTInterpreter.parseExpression("   1  < AND 3", showCalculation);
			bt.traverseCalculate();
		});
	

		System.out.println();

	}

	@Test
	void testBasicOperations() {
		System.out.println("testBasicOperations()");
		try {
			String testStr = "   1  + 2 * 5 - 2";
			String result;
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, showCalculation);
			result = bt.traverseCalculate();
			System.out.println("Test Result: " + bt + " = " + result + "\n");
			assertEquals("9", result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}
	}

	@Test
	void testBasicOperationsWithParanthesesAtStart() {
		System.out.println("testBasicOperationsWithParanthesesAtStart()");
		try {
			String testStr = "( 2 + 3 * 2 ) + 3 * 4 + 5";
			String result;
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, showCalculation);
			result = bt.traverseCalculate();
			System.out.println("Test Result: " + bt + " = " + result + "\n");
			assertEquals("25", result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}
	}

	@Test
	void testBasicOperationsWithParantheses() {
		System.out.println("testBasicOperationsWithParantheses()");
		try {
			String testStr = "1 + ( 2 + 3 ) * 2 ";
			String result;
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, showCalculation);
			result = bt.traverseCalculate();
			System.out.println("Test Result: " + bt + " = " + result + "\n");
			assertEquals("11", result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}
	}

	@Test
	void testBasicOperationsWithMultiParantheses() {
		System.out.println("testBasicOperationsWithMultiParantheses()");
		try {
			String testStr = "1 + ( ( 2 + 3 ) * 2 ) * ( 4 * ( 3 + 2 * 4 ) )";
			String result;
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, showCalculation);
			result = bt.traverseCalculate();
			System.out.println("Test Result: " + bt + " = " + result + "\n");
			assertEquals("441", result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}
	}

	@Test
	void testLogicalOperations() {
		System.out.println("testLogicalOperations()");
		try {
			String testStr = "2 + 3 * 6 < 3 * 2 * 2 + 8";
			String result;
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, showCalculation);
			result = bt.traverseCalculate();
			System.out.println("Test Result: " + bt + " = " + result + "\n");
			assertEquals("0", result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}
	}

	@Test
	void testLogicalOperationsWithANDOR() {
		System.out.println("testLogicalOperationsWithANDOR()");
		try {
			String testStr = "3 < 3 AND 4 < 4 OR 6 < 7";
			String result;
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, showCalculation);
			result = bt.traverseCalculate();
			System.out.println("Test Result: " + bt + " = " + result + "\n");
			assertEquals("1", result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}
	}

	@Test
	void testLogicalOperationsWithANDORWithParantheses() {
		System.out.println("testLogicalOperationsWithANDORWithParantheses()");
		try {
			String testStr = "3 < 3 AND ( 4 < 4 OR 6 < 7 ) OR 8 < 9";
			String result;
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, showCalculation);
			result = bt.traverseCalculate();
			System.out.println("Test Result: " + bt + " = " + result + "\n");
			assertEquals("1", result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}
	}

	@Test
	void testPowerOperation() {
		System.out.println("testPowerOperation()");
		try {
			String testStr = "1 * 2 ** 3 * 2";
			String result;
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, showCalculation);
			result = bt.traverseCalculate();
			System.out.println("Test Result: " + bt + " = " + result + "\n");
			assertEquals("16", result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}

		try {
			String testStr = "1 * 2 * 3 ** 2";
			String result;
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, showCalculation);
			result = bt.traverseCalculate();
			System.out.println("Test Result: " + bt + " = " + result + "\n");
			assertEquals("18", result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}

		try {
			String testStr = "2 ** 3 * 2 * 5 + 8 - 2";
			String result;
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, showCalculation);
			result = bt.traverseCalculate();
			System.out.println("Test Result: " + bt + " = " + result + "\n");
			assertEquals("86", result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}

		try {
			String testStr = "2 ** ( 3 ** 2 )";
			String result;
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, showCalculation);
			result = bt.traverseCalculate();
			System.out.println("Test Result: " + bt + " = " + result + "\n");
			assertEquals("512", result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}

		try {
			String testStr = "2 + 3 * 2 - 5 + 2 * 2 * ( 3 ** 2 )";
			String result;
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, showCalculation);
			result = bt.traverseCalculate();
			System.out.println("Test Result: " + bt + " = " + result + "\n");
			assertEquals("39", result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}
	}

	@Test
	void testLogicalOperationsWithNOT() {
		System.out.println("testLogicalOperationsWithNOT()");
		try {
			String testStr = "1 AND NOT 0";
			String result;
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, showCalculation);
			result = bt.traverseCalculate();
			System.out.println("Test Result: " + bt + " = " + result + "\n");
			assertEquals("1", result);
		} catch (Exception e) {
			System.out.println(e);
			fail("Failed with exception");
		}
		
		try {
			String testStr = "NOT 1 AND 0";
			String result;
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, showCalculation);
			result = bt.traverseCalculate();
			System.out.println("Test Result: " + bt + " = " + result + "\n");
			assertEquals("0", result);
		} catch (Exception e) {
			System.out.println(e);
			fail("Failed with exception");
		}

		try {
			String testStr = "NOT 0 OR 0 AND 1 OR 0";
			String result;
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, showCalculation);
			result = bt.traverseCalculate();
			System.out.println("Test Result: " + bt + " = " + result + "\n");
			assertEquals("1", result);
		} catch (Exception e) {
			System.out.println(e);
			fail("Failed with exception");
		}

		try {
			String testStr = "1 AND 0 AND ( NOT 0 AND 1 )";
			String result;
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, showCalculation);
			result = bt.traverseCalculate();
			System.out.println("Test Result: " + bt + " = " + result + "\n");
			assertEquals("0", result);
		} catch (Exception e) {
			System.out.println(e);
			fail("Failed with exception");
		}

		try {
			String testStr = "1 AND ( NOT 0 AND ( 0 OR NOT 0 ) )";
			String result;
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, showCalculation);
			result = bt.traverseCalculate();
			System.out.println("Test Result: " + bt + " = " + result + "\n");
			assertEquals("1", result);
		} catch (Exception e) {
			System.out.println(e);
			fail("Failed with exception");
		}
	}
	
	@Test
	void testUnaryOperation() {
		System.out.println("testUnaryOperation()");
		try {
			String testStr = "NOT NOT 1 AND 0";
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, true);
			
			String result;
			result = bt.traverseCalculate();
			System.out.println("Test Result: "+bt+" = "+result+"\n");
			assertEquals("0", result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}
	}

	@Test
	void testRandom1() {
		System.out.println("testRandom1()");
		try {
			String testStr = "( 8 - 4 ) * 4 / 2 + 14 - 7 < 77";
			String result;
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, showCalculation);
			result = bt.traverseCalculate();
			System.out.println("Test Result: " + bt + " = " + result + "\n");
			assertEquals("1", result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}

		try {
			String testStr = "( ( ( 8 - 4 ) * 2 ) + 3 ) + 2 * ( 3 + 2 )";
			String result;
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, showCalculation);
			result = bt.traverseCalculate();
			System.out.println("Test Result: " + bt + " = " + result + "\n");
			assertEquals("21", result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}

		try {
			String testStr = "( ( ( 8 - 4 ) * 2 ) + 3 ) + 2 * ( 3 + 2 ) <= 21 AND 1 = 2 OR 1 = 1 AND 1 = 2";
			String result;
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, showCalculation);
			result = bt.traverseCalculate();
			System.out.println("Test Result: " + bt + " = " + result + "\n");
			assertEquals("0", result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}
		
		try {
			String testStr = "2 * 3 ** 2 ** 3 + 2";
			String result;
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, showCalculation);
			result = bt.traverseCalculate();
			System.out.println("Test Result: " + bt + " = " + result + "\n");
			assertEquals("1460", result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}
		
		try {
			String testStr = "1 + 3 ** 2 * 3 + 2";
			String result;
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, showCalculation);
			result = bt.traverseCalculate();
			System.out.println("Test Result: " + bt + " = " + result + "\n");
			assertEquals("30", result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}
		
		try {
			String testStr = "2 * 2 ** ( 2 ** ( 1 + 2 ) )";
			String result;
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, showCalculation);
			result = bt.traverseCalculate();
			System.out.println("Test Result: " + bt + " = " + result + "\n");
			assertEquals("512", result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}
	}
	
	@Test
	void testRandom2() {
		System.out.println("testRandom2()");
		try {
			String testStr = "( ( 8 - 4 ) * 2 )  < ( ( ( 1 + 2 ) * 3 ) + 2 * 3 ) ";
			String result;
			System.out.println("Test Exprsn: " + testStr);
			BTInterpreter bt = BTInterpreter.parseExpression(testStr, showCalculation);
			result = bt.traverseCalculate();
			System.out.println("Test Result: " + bt + " = " + result + "\n");
			assertEquals("1", result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}
	}
}
