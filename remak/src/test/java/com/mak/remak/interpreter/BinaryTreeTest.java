package com.mak.remak.interpreter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinaryTreeTest {

	@Test
	void testExpressionErrors() {
		System.out.println("testExpressionErrors()");
		
		assertThrows(InterpreterException.class, () -> {
			BinaryTree bt;
			bt = BinaryTree.parseExpression(" 3");
			bt = BinaryTree.parseExpression("   1  < AND 3");
			bt.traverseCalculate();
		});

	}

	@Test
	void testBasicOperations() {
		System.out.println("testBasicOperations()");

		try {
			BinaryTree bt = BinaryTree.parseExpression("   1  + 2 * 5 - 2");
			System.out.println(bt);
			int result = bt.traverseCalculate();
			System.out.println(result);
			assertEquals(9, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}
	}

	@Test
	void testBasicOperationsWithParanthesesAtStart() {
		System.out.println("testBasicOperationsWithParanthesesAtStart()");

		try {
			BinaryTree bt = BinaryTree.parseExpression("( 2 + 3 * 2 ) + 3 * 4 + 5");
			System.out.println(bt);
			int result = bt.traverseCalculate();
			System.out.println(result);
			assertEquals(25, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}
	}

	@Test
	void testBasicOperationsWithParantheses() {
		System.out.println("testBasicOperationsWithParantheses()");

		try {
			BinaryTree bt = BinaryTree.parseExpression("1 + ( 2 + 3 ) * 2 ");
			System.out.println(bt);
			int result = bt.traverseCalculate();
			System.out.println(result);
			assertEquals(11, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}
	}

	@Test
	void testBasicOperationsWithMultiParantheses() {
		System.out.println("testBasicOperationsWithMultiParantheses()");

		try {
			BinaryTree bt = BinaryTree.parseExpression("1 + ( ( 2 + 3 ) * 2 ) * ( 4 * ( 3 + 2 * 4 ) )");
			System.out.println(bt);
			int result = bt.traverseCalculate();
			System.out.println(result);
			assertEquals(441, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}
	}

	@Test
	void testLogicalOperations() {
		System.out.println("testLogicalOperations()");

		try {
			BinaryTree bt = BinaryTree.parseExpression("2 + 3 * 6 < 3 * 2 * 2 + 8");
			System.out.println(bt);
			int result = bt.traverseCalculate();
			System.out.println(result);
			assertEquals(0, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}
	}

	@Test
	void testLogicalOperationsWithANDOR() {
		System.out.println("testLogicalOperationsWithANDOR()");

		try {
			BinaryTree bt = BinaryTree.parseExpression("3 < 3 AND 4 < 4 OR 6 < 7");
			System.out.println(bt);
			int result = bt.traverseCalculate();
			System.out.println(result);
			assertEquals(1, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}
	}

	@Test
	void testLogicalOperationsWithANDORWithParantheses() {
		System.out.println("testLogicalOperationsWithANDORWithParantheses()");

		try {
			BinaryTree bt = BinaryTree.parseExpression("3 < 3 AND ( 4 < 4 OR 6 < 7 ) OR 8 < 9");
			System.out.println(bt);
			int result = bt.traverseCalculate();
			System.out.println(result);
			assertEquals(1, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}
	}

	@Test
	void testPowerOperation() {
		System.out.println("testPowerOperation()");

		try {
			BinaryTree bt = BinaryTree.parseExpression("1 * 2 ** 3 * 2");
			System.out.println(bt);
			int result = bt.traverseCalculate();
			System.out.println(result);
			assertEquals(16, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}

		try {
			BinaryTree bt = BinaryTree.parseExpression("1 * 2 * 3 ** 2 ");
			System.out.println(bt);
			int result = bt.traverseCalculate();
			System.out.println(result);
			assertEquals(18, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}

		try {
			BinaryTree bt = BinaryTree.parseExpression("2 ** 3 * 2 * 5 + 8 - 2");
			System.out.println(bt);
			int result = bt.traverseCalculate();
			System.out.println(result);
			assertEquals(86, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}

		try {
			BinaryTree bt = BinaryTree.parseExpression("2 ** ( 3 ** 2 )");
			System.out.println(bt);
			int result = bt.traverseCalculate();
			System.out.println(result);
			assertEquals(512, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}

		try {
			BinaryTree bt = BinaryTree.parseExpression("2 + 3 * 2 - 5 + 2 * 2 * ( 3 ** 2 )");
			System.out.println(bt);
			int result = bt.traverseCalculate();
			System.out.println(result);
			assertEquals(39, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}
	}

	@Test
	void testLogicalOperationsWithNOT() {
		System.out.println("testLogicalOperationsWithNOT()");
		try {
			BinaryTree bt = BinaryTree.parseExpression("NOT 1 AND 0");
			System.out.println(bt);
			int result;
			result = bt.traverseCalculate();
			System.out.println(result);
			assertEquals(0, result);
		} catch (Exception e) {
			System.out.println(e);
			fail("Failed with exception");
		}
		
		try {
			BinaryTree bt = BinaryTree.parseExpression("NOT 0 OR 0 AND 1 OR 0");
			System.out.println(bt);
			int result;
			result = bt.traverseCalculate();
			System.out.println(result);
			assertEquals(1, result);
		} catch (Exception e) {
			System.out.println(e);
			fail("Failed with exception");
		}
		
		try {
			BinaryTree bt = BinaryTree.parseExpression("1 AND 0 AND ( NOT 0 AND 1 )");
			System.out.println(bt);
			int result;
			result = bt.traverseCalculate();
			System.out.println(result);
			assertEquals(0, result);
		} catch (Exception e) {
			System.out.println(e);
			fail("Failed with exception");
		}
				
		try {
			BinaryTree bt = BinaryTree.parseExpression("1 AND ( NOT 0 AND ( 0 OR NOT 0 ) )");
			System.out.println(bt);
			int result;
			result = bt.traverseCalculate();
			System.out.println(result);
			assertEquals(1, result);
		} catch (Exception e) {
			System.out.println(e);
			fail("Failed with exception");
		}
	}

	@Test
	void testRandom1() {
		System.out.println("testRandom1()");

		try {
			BinaryTree bt = BinaryTree.parseExpression("( 8 - 4 ) * 4 / 2 + 14 - 7 < 77");
			System.out.println(bt);
			int result = bt.traverseCalculate();
			System.out.println(result);
			assertEquals(1, result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed with exception");
		}
	}

}
