package com.mak.remak.interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {

	private Node root = null;
	private int returnIndex = 0;
	private Boolean showCalculation = false;

	public BinaryTree(Boolean showCalculation) {
		super();
		this.showCalculation = showCalculation;
	}

	public static BinaryTree parseExpression(String str, Boolean showCalculation) throws InterpreterException {
		List<String> strList = new ArrayList<String>(Arrays.asList(str.split(" ")));
		strList.removeAll(Arrays.asList("", null));

		if (!checkExpression(strList)) {
			return null;
		}
		try {
			return parseExpressionRecursive(strList, 0, showCalculation);
		} catch (Exception ex) {
			System.out.println("Error in expression...");
			return null;
		}
	}

	private static Boolean checkExpression(List<String> strList) throws InterpreterException {
		if (strList.size() < 3) {
			throw new InterpreterException("Error in expression... Invalid number of arguments");
		}

		int paranthesesCount = 0;
		for (String str : strList) {
			if (str.compareTo("(") == 0) {
				paranthesesCount++;
			}
			else if (str.compareTo(")") == 0) {
				paranthesesCount--;
			}
		}
		if (paranthesesCount != 0) {
			throw new InterpreterException("Error in expression... Invalid number of parantheses");
		}

		return true;

	}

	private static BinaryTree parseExpressionRecursive_OLD(List<String> strList, int index, Boolean showCalculation) {
		BinaryTree bt = new BinaryTree(showCalculation);

		Node newNode = null;
		String preStr = null;
		String currStr = null;

		for (int i = index; i < strList.size(); i++) {
			currStr = strList.get(i);
			if (newNode == null) {
				if (currStr.compareTo(")") == 0) {
					bt.returnIndex = i;

					return bt;
				}
				else if ((i == index) && (currStr.compareTo("NOT") == 0)) {

				}
				else if (currStr.compareTo("(") == 0) {
					newNode = new Node("0", "+", "(");
					bt.add(newNode);
					newNode = null;
					BinaryTree bt2 = parseExpressionRecursive(strList, ++i, showCalculation);
					bt.addSubTree(bt2);
					i = bt2.returnIndex;
				}
				else if (preStr == null) {
					newNode = new Node();
					newNode.left = new Node(currStr);
				}
				else if (preStr.compareTo("NOT") == 0) {
					newNode = new Node();
					newNode.left = new Node("0");
					newNode.value = "NOT";
					newNode.right = new Node(currStr);
					bt.add(newNode);
					newNode = null;
				}
				else {
					newNode = new Node();
					newNode.left = new Node(preStr);
					newNode.value = currStr;
				}
			}
			else if (newNode.value == null) {
				newNode.value = currStr;
			}
			else if (newNode.right == null) {
				if (currStr.compareTo("NOT") == 0) {
					newNode.right = new Node("0");
				}
				else {
					newNode.right = new Node(currStr);
				}
				bt.add(newNode);
				newNode = null;
				if (currStr.compareTo("(") == 0) {
					BinaryTree bt2 = parseExpressionRecursive(strList, ++i, showCalculation);
					bt.addSubTree(bt2);
					i = bt2.returnIndex;
				}
			}
			preStr = currStr;
		}
		return bt;
	}

	private static BinaryTree parseExpressionRecursive(List<String> strList, int index, Boolean showCalculation) {
		BinaryTree bt = new BinaryTree(showCalculation);

		Node newNode = null;
		String preStr = null;
		String currStr = null;

		for (int i = index; i < strList.size(); i++) {
			currStr = strList.get(i);

			if (currStr.compareTo(")") == 0) {
				bt.returnIndex = i;
				return bt;
			}
			
			else if ((i == index) && (currStr.compareTo("(") == 0)) {
				newNode = new Node("0", "+", "(");
				bt.add(newNode);
				newNode = null;
				BinaryTree bt2 = parseExpressionRecursive(strList, ++i, showCalculation);
				bt.addSubTree(bt2);
				i = bt2.returnIndex;
			}
			else {
				if (newNode == null) {

					if (Calculator.isUnary(currStr)) {
						newNode = new Node();
						newNode.left = new Node("0");
						newNode.value = currStr;
						newNode.right = null;
					}
					else if (preStr == null) {
						newNode = new Node();
						newNode.left = new Node(currStr);
					}
					else {
						newNode = new Node();
						newNode.left = new Node(preStr);
						newNode.value = currStr;
					}
				}
				else if (newNode.value == null) {
					newNode.value = currStr;
				}
				else if (newNode.right == null) {
					if (Calculator.isUnary(currStr)) {
						newNode.right = new Node("0");
						bt.add(newNode);
						newNode = new Node();
						newNode.left = new Node("0");
						newNode.value = currStr;
						newNode.right = null;
						new Node(currStr);
					}
					else {
						newNode.right = new Node(currStr);
						bt.add(newNode);
						newNode = null;
					}
					
					if (currStr.compareTo("(") == 0) {
						BinaryTree bt2 = parseExpressionRecursive(strList, ++i, showCalculation);
						bt.addSubTree(bt2);
						i = bt2.returnIndex;
					}
				}
			}
			preStr = currStr;
		}
		return bt;
	}

	private void add(Node newNode) {
		// System.out.println("Added: " + newNode);
		if (root == null) {
			root = newNode;
		}
		else {
			root = addRecursive(root, newNode);
		}
	}

	private void addSubTree(BinaryTree newTree) {
		if (root != null) {
			Node node = root;
			while (!node.right.isValue())
				node = root.right;
			node.right = newTree.root;
			newTree.root.parent = root;
			newTree.root.isSubTree = true;
		}
	}

	public int traverseCalculate() throws InterpreterException {
		return traverseCalculateRecursive(this.root);
	}

	private int traverseCalculateRecursive(Node node) throws InterpreterException {
		int leftValueInt = 0;
		int rightValueInt = 0;
		int result = 0;

		try {
			if (node != null) {
				if (!node.left.isValue() && !node.right.isValue()) {
					leftValueInt = traverseCalculateRecursive(node.left);
					rightValueInt = traverseCalculateRecursive(node.right);

				}
				else if (node.left.isValue() && !node.right.isValue()) {
					leftValueInt = Integer.parseInt(node.left.value);
					rightValueInt = traverseCalculateRecursive(node.right);

				}
				else if (!node.left.isValue() && node.right.isValue()) {
					leftValueInt = traverseCalculateRecursive(node.left);
					rightValueInt = Integer.parseInt(node.right.value);
				}
				else if (node.left.isValue() && node.right.isValue()) {
					leftValueInt = Integer.parseInt(node.left.value);
					rightValueInt = Integer.parseInt(node.right.value);

				}
			}

			result = Calculator.calculate(node.value, leftValueInt, rightValueInt);
			if (showCalculation) {
				System.out.println("(" + leftValueInt + node.value + rightValueInt + ") = " + result);
			}

		} catch (Exception e) {
			throw new InterpreterException();
		}
		return result;
	}

	private Node addRecursive(Node current, Node newNode) {

		if ((current == null) || (current.isValue())) {
			return newNode;
		}
		else if (newNode.compareTo(current) < 0) {
			return addParent(current, newNode);
		}
		else if (newNode.compareTo(current) == 0) {

			if (Calculator.isUnary(current.value)) {
				current.right = addRecursive(current.right, newNode);
				return current;
			}
			else {
				return addParent(current, newNode);
			}
		}
		else {
			newNode.parent = current;
			if (current.isSubTree) {
				return addParent(current, newNode);
			}
			else {
				current.right = addRecursive(current.right, newNode);
				return current;
			}
		}
	}
	
	private Node addRecursive_OLD(Node current, Node newNode) {

		if ((current == null) || (current.isValue())) {
			return newNode;
		}
		else if (newNode.compareTo(current) <= 0) {
			return addParent(current, newNode);
		}
		else {
			newNode.parent = current;
			if (current.isSubTree) {
				return addParent(current, newNode);
			}
			else {
				current.right = addRecursive(current.right, newNode);
				return current;
			}
		}
	}

	private Node addParent(Node current, Node newNode) {
		if (current.parent == null) {
			root = newNode;
		}
		else {
			current.parent.right = newNode;
		}

		newNode.parent = current.parent;
		current.parent = newNode;

		newNode.left = current;

		return newNode;
	}

	@Override
	public String toString() {
		return traverseInOrder("", root);
	}

	private String traverseInOrder(String outStr, Node node) {

		if (node != null) {
			if (node.isSubTree) {
				outStr += " (";
			}
			outStr = traverseInOrder(outStr, node.left);
			outStr = outStr + " " + node.value;
			outStr = traverseInOrder(outStr, node.right);
			if (node.isSubTree) {
				outStr += " )";
			}
		}
		return outStr;
	}

	/*********************************
	 * 
	 * 
	 * Functions below are for Extra Information. Not in Use Now
	 * 
	 * 
	 * 
	 ********************************/

	private void traversePreOrder(Node node) {
		if (node != null) {
			System.out.print(" " + node.value);
			traversePreOrder(node.left);
			traversePreOrder(node.right);
		}
	}

	private void traversePostOrder(Node node) {
		if (node != null) {
			traversePostOrder(node.left);
			traversePostOrder(node.right);
			System.out.print(" " + node.value);
		}
	}

	private void traverseLevelOrder() {
		if (root == null) {
			return;
		}

		Queue<Node> nodes = new LinkedList<>();
		nodes.add(root);

		while (!nodes.isEmpty()) {

			Node node = nodes.remove();

			System.out.print(" " + node.value);

			if (node.left != null) {
				nodes.add(node.left);
			}

			if (node.right != null) {
				nodes.add(node.right);
			}
		}
	}

}
