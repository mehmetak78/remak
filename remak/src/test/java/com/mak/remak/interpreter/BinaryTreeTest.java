package com.mak.remak.interpreter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinaryTreeTest {

	@Test
	void testBasicOperations() {
		System.out.println("testBasicOperations()");

		BinaryTree bt = BinaryTree.parseExpression("   1  + 2 * 5 - 2");
		
		bt.traverseInOrder(bt.getRoot());
		System.out.println();
		int result = bt.traverseCalculate(bt.getRoot());
		System.out.println(result);

		assertEquals(9, result);
	}

	@Test
	void testBasicOperationsWithParanthesesAtStart() {
		System.out.println("testBasicOperationsWithParanthesesAtStart()");

		System.out.println("testBasicOperations()");

		BinaryTree bt = BinaryTree.parseExpression("( 2 + 3 * 2 ) + 3 * 4 + 5");
		
		bt.traverseInOrder(bt.getRoot());
		System.out.println();
		int result = bt.traverseCalculate(bt.getRoot());
		System.out.println(result);

		assertEquals(25, result);
		
	
	}
	
	@Test
	void testBasicOperationsWithParantheses() {
		System.out.println("testBasicOperationsWithParantheses()");

		BinaryTree bt = BinaryTree.parseExpression("1 + ( 2 + 3 ) * 2 )");
		
		bt.traverseInOrder(bt.getRoot());
		System.out.println();
		int result = bt.traverseCalculate(bt.getRoot());
		System.out.println(result);

		assertEquals(11, result);
	}
	
	@Test
	void testBasicOperationsWithMultiParantheses() {
		System.out.println("testBasicOperationsWithMultiParantheses()");

		BinaryTree bt = BinaryTree.parseExpression("1 + ( ( 2 + 3 ) * 2 ) * ( 4 * ( 3 + 2 * 4 ) )");
		
		bt.traverseInOrder(bt.getRoot());
		System.out.println();
		int result = bt.traverseCalculate(bt.getRoot());
		System.out.println(result);

		assertEquals(441, result);
	}


	@Test
	void testLogicalOperations() {
		System.out.println("testLogicalOperations()");

		BinaryTree bt = BinaryTree.parseExpression("2 + 3 * 6 < 3 * 2 * 2 + 8");
		bt.traverseInOrder(bt.getRoot());
		System.out.println();
		int result = bt.traverseCalculate(bt.getRoot());
		System.out.println(result);
		assertEquals(0, result);
	}
	
	@Test
	void testLogicalOperationsWithANDOR() {
		System.out.println("testLogicalOperationsWithANDOR()");
		
		BinaryTree bt = BinaryTree.parseExpression("3 < 3 AND 4 < 4 OR 6 < 7");
		bt.traverseInOrder(bt.getRoot());
		System.out.println();
		int result = bt.traverseCalculate(bt.getRoot());
		System.out.println(result);
		
		assertEquals(1, result);
	}
	
	@Test
	void testLogicalOperationsWithANDORWithParantheses() {
		System.out.println("testLogicalOperationsWithANDORWithParantheses()");
		
		BinaryTree bt = BinaryTree.parseExpression("3 < 3 AND ( 4 < 4 OR 6 < 7 ) OR 8 < 9");
		
		bt.traverseInOrder(bt.getRoot());
		System.out.println();
		int result = bt.traverseCalculate(bt.getRoot());
		System.out.println(result);
		
		assertEquals(1, result);
	}
	
	@Test
	void testPowerOperation() {
		System.out.println("testPowerOperation()");

		BinaryTree bt = BinaryTree.parseExpression("1 * 2 ** 3 * 2");
		
		bt.traverseInOrder(bt.getRoot());
		System.out.println();
		int result = bt.traverseCalculate(bt.getRoot());
		System.out.println(result);

		assertEquals(16, result);
		
		bt = BinaryTree.parseExpression("1 * 2 * 3 ** 2 ");
		
		bt.traverseInOrder(bt.getRoot());
		System.out.println();
		result = bt.traverseCalculate(bt.getRoot());
		System.out.println(result);

		assertEquals(18, result);
		
		bt = BinaryTree.parseExpression("2 ** 3 * 2 * 5 + 8 - 2");
		
		bt.traverseInOrder(bt.getRoot());
		System.out.println();
		result = bt.traverseCalculate(bt.getRoot());
		System.out.println(result);

		assertEquals(86, result);
		
		bt = BinaryTree.parseExpression("2 ** ( 3 ** 2 )");
		
		bt.traverseInOrder(bt.getRoot());
		System.out.println();
		result = bt.traverseCalculate(bt.getRoot());
		System.out.println(result);

		assertEquals(512, result);
	}
	
	@Test
	void testLogicalOperationsWithNOT() {
		System.out.println("testLogicalOperationsWithNOT()");
		fail();
	}

}
