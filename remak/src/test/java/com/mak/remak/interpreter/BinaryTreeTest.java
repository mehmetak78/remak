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
			bt.traverseCalculate(bt.getRoot());
		});

	}

	@Test
	void testBasicOperations() {
		System.out.println("testBasicOperations()");

		try {
			BinaryTree bt = BinaryTree.parseExpression("   1  + 2 * 5 - 2");
			bt.traverseInOrder(bt.getRoot());
			System.out.println();
			int result = bt.traverseCalculate(bt.getRoot());
			System.out.println(result);
			assertEquals(9, result);
		} catch (Exception e) {
			System.out.println("Error in Expression....");
			e.printStackTrace();
		}
	}

	@Test
	void testBasicOperationsWithParanthesesAtStart() {
		System.out.println("testBasicOperationsWithParanthesesAtStart()");

		try {
			BinaryTree bt = BinaryTree.parseExpression("( 2 + 3 * 2 ) + 3 * 4 + 5");
			bt.traverseInOrder(bt.getRoot());
			System.out.println();
			int result = bt.traverseCalculate(bt.getRoot());
			System.out.println(result);
			assertEquals(25, result);
		} catch (Exception e) {
			System.out.println("Error in Expression....");
			e.printStackTrace();
		}
	}

	@Test
	void testBasicOperationsWithParantheses() {
		System.out.println("testBasicOperationsWithParantheses()");

		try {
			BinaryTree bt = BinaryTree.parseExpression("1 + ( 2 + 3 ) * 2 ");
			bt.traverseInOrder(bt.getRoot());
			System.out.println();
			int result = bt.traverseCalculate(bt.getRoot());
			System.out.println(result);
			assertEquals(11, result);
		} catch (Exception e) {
			System.out.println("Error in Expression....");
			e.printStackTrace();
		}
	}

	@Test
	void testBasicOperationsWithMultiParantheses() {
		System.out.println("testBasicOperationsWithMultiParantheses()");

		try {
			BinaryTree bt = BinaryTree.parseExpression("1 + ( ( 2 + 3 ) * 2 ) * ( 4 * ( 3 + 2 * 4 ) )");
			bt.traverseInOrder(bt.getRoot());
			System.out.println();
			int result = bt.traverseCalculate(bt.getRoot());
			System.out.println(result);
			assertEquals(441, result);
		} catch (Exception e) {
			System.out.println("Error in Expression....");
			e.printStackTrace();
		}
	}

	@Test
	void testLogicalOperations() {
		System.out.println("testLogicalOperations()");

		try {
			BinaryTree bt = BinaryTree.parseExpression("2 + 3 * 6 < 3 * 2 * 2 + 8");
			bt.traverseInOrder(bt.getRoot());
			System.out.println();
			int result = bt.traverseCalculate(bt.getRoot());
			System.out.println(result);
			assertEquals(0, result);
		} catch (Exception e) {
			System.out.println("Error in Expression....");
			e.printStackTrace();
		}
	}

	@Test
	void testLogicalOperationsWithANDOR() {
		System.out.println("testLogicalOperationsWithANDOR()");

		try {
			BinaryTree bt = BinaryTree.parseExpression("3 < 3 AND 4 < 4 OR 6 < 7");
			bt.traverseInOrder(bt.getRoot());
			System.out.println();
			int result = bt.traverseCalculate(bt.getRoot());
			System.out.println(result);
			assertEquals(1, result);
		} catch (Exception e) {
			System.out.println("Error in Expression....");
			e.printStackTrace();
		}
	}

	@Test
	void testLogicalOperationsWithANDORWithParantheses() {
		System.out.println("testLogicalOperationsWithANDORWithParantheses()");

		try {
			BinaryTree bt = BinaryTree.parseExpression("3 < 3 AND ( 4 < 4 OR 6 < 7 ) OR 8 < 9");
			bt.traverseInOrder(bt.getRoot());
			System.out.println();
			int result = bt.traverseCalculate(bt.getRoot());
			System.out.println(result);
			assertEquals(1, result);
		} catch (Exception e) {
			System.out.println("Error in Expression....");
			e.printStackTrace();
		}
	}

	@Test
	void testPowerOperation() {
		System.out.println("testPowerOperation()");

		try {
			BinaryTree bt = BinaryTree.parseExpression("1 * 2 ** 3 * 2");
			bt.traverseInOrder(bt.getRoot());
			System.out.println();
			int result = bt.traverseCalculate(bt.getRoot());
			System.out.println(result);
			assertEquals(16, result);
		} catch (Exception e) {
			System.out.println("Error in Expression....");
			e.printStackTrace();
		}

		try {
			BinaryTree bt = BinaryTree.parseExpression("1 * 2 * 3 ** 2 ");
			bt.traverseInOrder(bt.getRoot());
			System.out.println();
			int result = bt.traverseCalculate(bt.getRoot());
			System.out.println(result);
			assertEquals(18, result);
		} catch (Exception e) {
			System.out.println("Error in Expression....");
			e.printStackTrace();
		}

		try {
			BinaryTree bt = BinaryTree.parseExpression("2 ** 3 * 2 * 5 + 8 - 2");
			bt.traverseInOrder(bt.getRoot());
			System.out.println();
			int result = bt.traverseCalculate(bt.getRoot());
			System.out.println(result);
			assertEquals(86, result);
		} catch (Exception e) {
			System.out.println("Error in Expression....");
			e.printStackTrace();
		}

		try {
			BinaryTree bt = BinaryTree.parseExpression("2 ** ( 3 ** 2 )");
			bt.traverseInOrder(bt.getRoot());
			System.out.println();
			int result = bt.traverseCalculate(bt.getRoot());
			System.out.println(result);
			assertEquals(512, result);
		} catch (Exception e) {
			System.out.println("Error in Expression....");
			e.printStackTrace();
		}

		try {
			BinaryTree bt = BinaryTree.parseExpression("2 + 3 * 2 - 5 + 2 * 2 * ( 3 ** 2 )");
			bt.traverseInOrder(bt.getRoot());
			System.out.println();
			int result = bt.traverseCalculate(bt.getRoot());
			System.out.println(result);
			assertEquals(39, result);
		} catch (Exception e) {
			System.out.println("Error in Expression....");
			e.printStackTrace();
		}
	}

	@Test
	void testLogicalOperationsWithNOT() {
		System.out.println("testLogicalOperationsWithNOT()");
		try {
			BinaryTree bt = BinaryTree.parseExpression("NOT 1 AND 0");
			bt.traverseInOrder(bt.getRoot());
			System.out.println();
			int result;
			result = bt.traverseCalculate(bt.getRoot());
			System.out.println(result);
			assertEquals(0, result);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			BinaryTree bt = BinaryTree.parseExpression("NOT 0 OR 0 AND 1 OR 0");
			bt.traverseInOrder(bt.getRoot());
			System.out.println();
			int result;
			result = bt.traverseCalculate(bt.getRoot());
			System.out.println(result);
			assertEquals(1, result);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			BinaryTree bt = BinaryTree.parseExpression("1 AND 0 AND ( NOT 0 AND 1 )");
			bt.traverseInOrder(bt.getRoot());
			System.out.println();
			int result;
			result = bt.traverseCalculate(bt.getRoot());
			System.out.println(result);
			assertEquals(0, result);
		} catch (Exception e) {
			System.out.println(e);
		}
				
		try {
			BinaryTree bt = BinaryTree.parseExpression("1 AND ( NOT 0 AND ( 0 OR NOT 0 ) )");
			bt.traverseInOrder(bt.getRoot());
			System.out.println();
			int result;
			result = bt.traverseCalculate(bt.getRoot());
			System.out.println(result);
			assertEquals(1, result);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	void testRandom1() {
		System.out.println("testRandom1()");

		try {
			BinaryTree bt = BinaryTree.parseExpression("( 8- 4 ) * 4 / 2 + 14 - 7 < 77");
			bt.traverseInOrder(bt.getRoot());
			System.out.println();
			int result = bt.traverseCalculate(bt.getRoot());
			System.out.println(result);
			assertEquals(9, result);
		} catch (Exception e) {
			System.out.println("Error in Expression....");
			e.printStackTrace();
		}
	}

}
