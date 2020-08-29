package com.mak.remak.engine.engines;


import com.mak.remak.engine.Engine;
import com.mak.remak.engine.EngineException;
import com.mak.remak.engine.actions.Action;
import com.mak.remak.engine.actions.ActionParams;
import com.mak.remak.engine.actions.FIAction;

public class MyEngine extends Engine {

	public MyEngine() throws EngineException {
		super();
		initializeActions();
		initializeRules();
	}

	public MyEngine(Boolean showCalculation, Boolean showRuleSelection) throws EngineException {
		super(showCalculation, showRuleSelection);
		initializeRules();
		initializeActions();
	}

	private void initializeRules() throws EngineException {
		this.addRulesFromFile("target/myRules.json");
//			this.addRule(new Rule("", "RULE1", "${P1} < ${P2}", "Desc for rule 1", 2, "action1"));
	}

	private void initializeActions() {
		
		FIAction<ActionParams, String> action1 = new Action<ActionParams, String>("action1") {
			@Override
			public String execute(ActionParams input) {
			 System.out.println("action1 fired:" + input.getParam("MESSAGE"));
				return "100";
			}
		};
		this.putAction("action1", action1);

		FIAction<ActionParams, String> action2 = new Action<ActionParams, String>("action2") {
			@Override
			public String execute(ActionParams input) {
			 System.out.println("action2 fired:" + input.getParam("MESSAGE"));
				return "200";
			}
		};
		this.putAction("action2", action2);

		FIAction<ActionParams, String> action3 = new Action<ActionParams, String>("action3") {
			@Override
			public String execute(ActionParams input) {
			 System.out.println("action3 fired:" + input.getParam("MESSAGE"));
				return "300";
			}
		};
		this.putAction("action3", action3);
	}

}

