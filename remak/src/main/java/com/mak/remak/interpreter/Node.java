package com.mak.remak.interpreter;

public class Node implements Comparable<Node> {

	public Node parent;
	public Node left;
	public String value;
	public Node right;
	public Boolean isSubTree;

	public Node() {
		super();
		this.value = null;
		this.parent = null;
		this.left = null;
		this.right = null;
		this.isSubTree = false;
	}

	public Node(String value) {
		super();
		this.value = value;
		this.parent = null;
		this.left = null;
		this.right = null;
		this.isSubTree = false;
	}

	public Node(String left, String value, String right) {
		this(value);
		this.left = new Node(left);
		this.right = new Node(right);
	}

	public Boolean isValue() {
		return Calculator.getPriority(value) == 9999;
	}

	@Override
	public int compareTo(Node other) {
		if (Calculator.getPriority(other.value) < Calculator.getPriority(this.value)) {
			return 1;
		}
		else if (Calculator.getPriority(other.value) > Calculator.getPriority(this.value)) {
			return -1;
		}

		return 0;
	}

	@Override
	public String toString() {
		return '(' + left.value + value + right.value + ')';
	}

}
