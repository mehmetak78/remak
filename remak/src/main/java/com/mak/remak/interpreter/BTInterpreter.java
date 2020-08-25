package com.mak.remak.interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BTInterpreter {

	private Node root = null;
	private int returnIndex = 0;
	private Boolean showCalculation = false;

	public BTInterpreter(Boolean showCalculation) {
		super();
		this.showCalculation = showCalculation;
	}

	public static BTInterpreter parseExpression(String expression, Boolean showCalculation) throws InterpreterException {
		List<String> strList = new ArrayList<String>(Arrays.asList(expression.split(" ")));
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

	private static BTInterpreter parseExpressionRecursive(List<String> strList, int index, Boolean showCalculation) {
		BTInterpreter bt = new BTInterpreter(showCalculation);

		Node newNode = null;
		String preStr = null;
		String currStr = null;

		for (int i = index; i < strList.size(); i++) {
			currStr = strList.get(i);

			if ((i == index) && (currStr.compareTo("(") == 0)) {
				newNode = new Node("0", "+", "(");
				bt.add(newNode);
				newNode = null;
				BTInterpreter bt2 = parseExpressionRecursive(strList, ++i, showCalculation);
				bt.addSubTree(bt2);
				i = bt2.returnIndex;
			}

			else if (currStr.compareTo(")") == 0) {
				bt.returnIndex = i;
				return bt;
			}

			else {
				if (newNode == null) {
					newNode = new Node();
					if (Calculator.isUnary(currStr)) {
						newNode.left = new Node("0");
						newNode.value = currStr;
						newNode.right = null;
					}
					else {
						if (preStr == null) {
							newNode.left = new Node(currStr);
						}
						else {
							newNode.left = new Node(preStr);
							newNode.value = currStr;
						}
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
						BTInterpreter bt2 = parseExpressionRecursive(strList, ++i, showCalculation);
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
		root = root == null ? newNode : addRecursive(root, newNode);
	}

	private Node addRecursive(Node current, Node newNode) {
		if (current.isValue()) {
			return newNode;
		}
		else if ((newNode.compareTo(current) < 0)
				|| ((newNode.compareTo(current) == 0) && (!Calculator.isUnary(current.value))) || (current.isSubTree)) {
			return addParent(current, newNode);
		}
		else {
			newNode.parent = current;
			current.right = addRecursive(current.right, newNode);
			return current;
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

	private void addSubTree(BTInterpreter newTree) {
		if (root != null) {
			Node node = root;
			while (!node.right.isValue()) {
				node = node.right;
			}
			node.right = newTree.root;
			newTree.root.parent = root;
			newTree.root.isSubTree = true;
		}
	}

	public int traverseCalculate() throws InterpreterException {
		if (showCalculation) {
			System.out.println("traverseCalculate()");
		}
		
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
