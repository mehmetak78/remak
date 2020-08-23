package com.mak.remak.interpreter;

public class TestApp {

	private static void sameToCompare() {
//		BinaryTree bt = new BinaryTree();
//
//		bt.add(new Node("0", "+", "("));
//
//		// For parantez
//		BinaryTree bt2 = new BinaryTree();
//		bt2.add(new Node("5", "+", "("));
//
//		BinaryTree bt3 = new BinaryTree();
//		bt3.add(new Node("2", "*", "4"));
//
//		bt2.addSubTree(bt3);
//		bt2.add(new Node(")", "+", "2"));
//		bt.addSubTree(bt2);
//
//		bt.traverseInOrder(bt.getRoot());
//		System.out.println();
//		int result;
//		try {
//			result = bt.traverseCalculate(bt.getRoot());
//			System.out.println(result);
//		} catch (Exception e) {
//			System.out.println("Error In Expression");
//			e.printStackTrace();
//		}

	}

	public static void main(String[] args) {

		// sameToCompare();

		System.out.println("testRandom1()");

		try {
			String testStr = " ( ( 3 + 5 ) + 3 ) * 2";
			System.out.println("Test Exprsn: " + testStr);
			BinaryTree bt = BinaryTree.parseExpression(testStr, true);
			
			int result;
			result = bt.traverseCalculate();
			System.out.println("Test Result: "+bt+" = "+result+"\n");
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
