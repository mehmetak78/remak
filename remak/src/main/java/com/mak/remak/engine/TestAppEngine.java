package com.mak.remak.engine;

import java.util.HashMap;
import java.util.Map;

import com.mak.remak.rules.Rule;

public class TestAppEngine {

	public static void main(String[] args) {
		System.out.println("TestAppEngine started...");
		
		Engine engine = new Engine(true);
//		engine.addRule(new Rule("RULE1","${PARAM1} < ${PARAM2}",2));
//		engine.addRule(new Rule("RULE2","${PARAM3} < ${PARAM2}",2));
//		engine.addRule(new Rule("RULE3","${PARAM3} >= ${PARAM1} AND ${PARAM1} < ${PARAM2}",2));
//		engine.addRule(new Rule("RULE4","@{RULE1} OR @{RULEý2}",2));
//		engine.addRule(new Rule("RULE5","@{RULE4} AND @{RULE1}",2));
		
		engine.addRule(new Rule("RULE1","${P1} < @{RULE2}",2));
		engine.addRule(new Rule("RULE2","@{RULE1} < ${P2}",2));
		engine.addRule(new Rule("RULE3","11 < ${P3}",2));
		
		Map<String, String> facts = new HashMap<String, String>();
		facts.put("P1", "11");
		facts.put("P2", "22");
		facts.put("P3", "11");
		
		
		try {
			engine.compileRules(facts);
			engine.selectCompiledRules();
			engine.printSelectedRules();
		} catch (EngineException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("TestAppEngine finished...");
	}

}
