package com.mak.remak.interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {

	private Node root = null;
	private int itemCount = 0;

	private static Boolean checkExpression(List<String> strList) {
		if (strList.size() < 3) {
			System.out.println("Not enough parameters");
			return false;
		}
		return true;
	}

	public static BinaryTree parseExpression(String str) {
		System.out.println("parseExpressionToTree()");
		
		List<String> strList = new ArrayList<String>(Arrays.asList(str.split(" ")));
		strList.removeAll(Arrays.asList("", null));

		
		if (!checkExpression(strList)) {
			return null;
		}
		return parseExpressionRecursive(strList, 0);
	}
	public static BinaryTree parseExpressionRecursive(List<String> strList, int index) {
		System.out.println("parseExpressionRecursive()");
		BinaryTree bt = new BinaryTree();

		Node newNode = null;

		String preStr = null;
		String currStr = null;

		for (int i = index; i < strList.size(); i++) {
			currStr = strList.get(i);

			if (currStr.compareTo(")") == 0) {
				bt.itemCount = i;
				return bt;
			}
			
			if (newNode == null) {
				if (currStr.compareTo("(") == 0) {
					newNode = new Node("0", "+", "(");
					bt.add(newNode);
					preStr = currStr;
					newNode = null;
					BinaryTree bt2 = parseExpressionRecursive(strList, ++i);
					bt.addSubTree(bt2);
					i = bt2.itemCount;
				}
				else if (preStr == null) {
					newNode = new Node();
					newNode.left = new Node(currStr);
					preStr = currStr;
				} 
				else {
					newNode = new Node();
					newNode.left = new Node(preStr);
					newNode.value = currStr;
					preStr = currStr;
				}
			} else if (newNode.value == null) {
				newNode.value = currStr;
				preStr = currStr;
			} else if (newNode.right == null) {
				newNode.right = new Node(currStr);
				bt.add(newNode);
				preStr = currStr;
				newNode = null;
				if (currStr.compareTo("(") == 0) {
					BinaryTree bt2 = parseExpressionRecursive(strList, ++i);
					bt.addSubTree(bt2);
					i = bt2.itemCount;
				}
			}
		}
		return bt;
	}

	public Node getRoot() {
		return root;
	}

	public void add(Node newNode) {
		System.out.println("Added: "+newNode);
		if (root == null) {
			root = newNode;
		} else {
			root = addRecursive(root, newNode);
		}
//		nodeCount ++;
	}

	public void addSubTree(BinaryTree newTree) {
		if (root != null) {
			Node node = root;
			while (!node.right.isValue())
				node = root.right;
			node.right = newTree.root;
			newTree.root.parent = root;
			newTree.root.subTree = true;
		}
	}

	public int traverseCalculate(Node node) {
		int leftValueInt = 0;
		int rightValueInt = 0;
		int result = 0;
		if (node != null) {
			if (!node.left.isValue() && !node.right.isValue()) {
				leftValueInt = traverseCalculate(node.left);
				rightValueInt = traverseCalculate(node.right);

			} else if (node.left.isValue() && !node.right.isValue()) {
				leftValueInt = Integer.parseInt(node.left.value);
				rightValueInt = traverseCalculate(node.right);

			} else if (!node.left.isValue() && node.right.isValue()) {
				leftValueInt = traverseCalculate(node.left);
				rightValueInt = Integer.parseInt(node.right.value);
			} else if (node.left.isValue() && node.right.isValue()) {
				leftValueInt = Integer.parseInt(node.left.value);
				rightValueInt = Integer.parseInt(node.right.value);

			}
		}
		result = calculateNodeExpression(node.value, leftValueInt, rightValueInt);
		System.out.println("(" + leftValueInt + node.value + rightValueInt + ") = " + result);
		return result;
	}

	public void traverseInOrder(Node node) {
		if (node != null) {
			traverseInOrder(node.left);
			System.out.print(" " + node.value);
			traverseInOrder(node.right);
		}
	}

	private Node addRecursive(Node current, Node newNode) {

		if ((current == null) || (current.isValue())) {
			return newNode;
		} else if (newNode.compareTo(current) <= 0) {
			newNode.parent = current;
			return addParentToParentsLeft(current, newNode);
		} else {
			newNode.parent = current;
			if (current.isSubTree()) {
				return addParentToParentsRight(current, newNode);
			} else {
				current.right = addRecursive(current.right, newNode);
				return current;
			}
		}
	}

	private Node addParentToParentsLeft(Node current, Node newNode) {
		// if (current.parent == null) {
		root = newNode;
		// }
//		else {
//			current.parent.left = newNode;
//		}
		newNode.parent = current.parent;
		current.parent = newNode;
		newNode.left = current;

		return newNode;
	}

	private Node addParentToParentsRight(Node current, Node newNode) {
//		if (current.parent == null) {
//			root = newNode;
//		}
//		else {
		current.parent.right = newNode;
//		}
		newNode.parent = current.parent;
		current.parent = newNode;
		newNode.left = current;

		return newNode;
	}

	private int calculateNodeExpression(String operand, int leftValueInt, int rightValueInt) {
		switch (operand) {
		case "AND":
			if (leftValueInt * rightValueInt > 0) {
				return 1;
			}
			return 0;
		case "OR":
			if (leftValueInt + rightValueInt > 0) {
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
		}
		return 0;
	}

	// Functions below are for Extra Information. Not in Use Now

	public void traversePreOrder(Node node) {
		if (node != null) {
			System.out.print(" " + node.value);
			traversePreOrder(node.left);
			traversePreOrder(node.right);
		}
	}

	public void traversePostOrder(Node node) {
		if (node != null) {
			traversePostOrder(node.left);
			traversePostOrder(node.right);
			System.out.print(" " + node.value);
		}
	}

	public void traverseLevelOrder() {
		if (getRoot() == null) {
			return;
		}

		Queue<Node> nodes = new LinkedList<>();
		nodes.add(getRoot());

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
