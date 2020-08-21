package com.mak.remak.interpreter;

public class Node implements Comparable<Node> {

	protected String value;
	protected Boolean subTree;

	Node parent;
	Node left;
	Node right;
	
	private int getPriority(String str) {
		switch (str) {
		case "OR":
			return 5;
		case "AND":
			return 10;
		case "<":
		case ">":
		case "<=":
		case ">=":
			return 15;
		case "+":
		case "-":
			return 20;
		case "*":
		case "/":
			return 25;
		case "**":
			return 30;

		}
		return 99;
	}

	public Node() {
		super();
		this.value = null;
		this.parent = null;
		this.left = null;
		this.right = null;
		this.subTree = false;
	}
	
	public Node(String value) {
		super();
		this.value = value;
		this.parent = null;
		this.left = null;
		this.right = null;
		this.subTree = false;
	}
	
	public Node(String left, String value, String right) {
		this(value);
		this.left = new Node(left);
		this.right = new Node(right);
	}


	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean isValue() {
		return getPriority(value) == 99;
	}
	
	public Boolean isSubTree() {
		return subTree;
	}

	@Override
	public int compareTo(Node o) {

		if (getPriority(o.getValue()) < getPriority(this.getValue())) {
			return 1;
		} else if (getPriority(o.getValue()) > getPriority(this.getValue())) {
			return -1;
		}

		return 0;
	}
	
	@Override
	public String toString() {
		return '"'+left.value+" "+value+" "+right.value+'"';
	}

}
