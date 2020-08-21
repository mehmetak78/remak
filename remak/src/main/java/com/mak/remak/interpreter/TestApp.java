package com.mak.remak.interpreter;

public class TestApp {

	private static void sameToCompare() {
		BinaryTree bt = new BinaryTree();

		bt.add(new Node("0", "+", "("));
		

		// For parantez
		BinaryTree bt2 = new BinaryTree();
		bt2.add(new Node("5", "+", "("));
		
		BinaryTree bt3 = new BinaryTree();
		bt3.add(new Node("2", "*", "4"));
		

		bt2.addSubTree(bt3);
		bt2.add(new Node(")", "+", "2"));
		bt.addSubTree(bt2);

		bt.traverseInOrder(bt.getRoot());
		System.out.println();
		int result = bt.traverseCalculate(bt.getRoot());
		System.out.println(result);
		
	}
	
	public static void main(String[] args) {
		
		//sameToCompare();
		
		BinaryTree bt = BinaryTree.parseExpression("1 * 2 * 3");
		
		bt.traverseInOrder(bt.getRoot());
		System.out.println();
		int result = bt.traverseCalculate(bt.getRoot());
		System.out.println(result);
	
	}
}
