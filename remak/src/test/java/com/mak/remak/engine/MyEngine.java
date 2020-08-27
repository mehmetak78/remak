package com.mak.remak.engine;

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
		super(showCalculation,showRuleSelection);
		initializeRules();
		initializeActions();
	}
	
	private void initializeRules() {
		this.addRule(new Rule("","RULE1", "${P1} < ${P2}", "Desc for rule 1", 2, "action1"));
		this.addRule(new Rule("","RULE2", "${P3} < ${P2}", "Desc for rule 2", 4, "action2"));
	}

	private void initializeActions() {
		FIAction<MyInput, Integer> action1 = new Action<MyInput, Integer>("action1") {
			@Override
			public Integer execute(MyInput input) {
				System.out.println("action2 fired:" );
				return 100;
			}
		};
		this.putAction("action1", action1);
		
		FIAction<MyInput, Integer> action2 = new Action<MyInput, Integer>("action2") {
			@Override
			public Integer execute(MyInput input) {
				System.out.println("action2 fired:" + input);
				return 200;
			}
		};
		this.putAction("action2", action2);
	}
	

	

}
