package com.mak.remak.engine;

import java.util.Map;

import com.mak.remak.engine.actions.Action;
import com.mak.remak.engine.actions.FIAction;
import com.mak.remak.engine.rules.Rule;

public class MyEngine extends Engine {

	public MyEngine() {
		super();
		initializeActions();
		initializeRules();
	}

	public MyEngine(Boolean showCalculation, Boolean showRuleSelection) {
		super(showCalculation, showRuleSelection);
		initializeRules();
		initializeActions();
	}

	private void initializeRules() {
		this.addRulesFromFile("target/myRules.json");//			this.addRule(new Rule("", "RULE1", "${P1} < ${P2}", "Desc for rule 1", 2, "action1"));
	}

	private void initializeActions() {
		FIAction<Map<String,String>, Integer> action1 = new Action<Map<String,String>, Integer>("action1") {
			@Override
			public Integer execute(Map<String,String> input) {
				//System.out.println("action1 fired:" + input.get("PROFILE"));
				return 100;
			}
		};
		this.putAction("action1", action1);

		FIAction<Map<String,String>, Integer> action2 = new Action<Map<String,String>, Integer>("action2") {
			@Override
			public Integer execute(Map<String,String> input) {
				System.out.println("action2 fired:" + input);
				return 200;
			}
		};
		this.putAction("action2", action2);
		
		FIAction<Map<String,String>, Integer> action3 = new Action<Map<String,String>, Integer>("action3") {
			@Override
			public Integer execute(Map<String,String> input) {
				System.out.println("action3 fired:" + input);
				return 300;
			}
		};
		this.putAction("action3", action3);
	}

}
