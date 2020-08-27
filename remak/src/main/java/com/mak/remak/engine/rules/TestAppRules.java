package com.mak.remak.engine.rules;

public class TestAppRules {

	public static void main(String[] args) {
		System.out.println("TestAppRules");
		
		Rule r1 = new Rule("Rule 1",1);
		Rule r2 = new Rule("Rule 2",2);
		
		if (r1.compareTo(r2) < 0 ) {
			System.out.println("r1<r2");
		}
	}

}
