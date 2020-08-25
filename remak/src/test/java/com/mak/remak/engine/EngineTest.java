package com.mak.remak.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class EngineTest {

	@Test
	void testEngine1() {
		System.out.println("testEngine1()...");

		Engine engine = new Engine(false, true);
		Rule rule1 = new Rule("RULE1", "${P1} < ${P2}", 2);
		Rule rule2 = new Rule("RULE2", "${P3} < ${P2}", 2);
		Rule rule3 = new Rule("RULE3", "${P3} >= ${P1} AND ${P1} < ${P2}", 2);
		Rule rule4 = new Rule("RULE4", "@{RULE1} OR @{RULE2}", 3);
		Rule rule5 = new Rule("RULE5", "@{RULE4} AND @{RULE1}", 2);
		
		engine.addRule(rule1);
		engine.addRule(rule2);
		engine.addRule(rule3);
		engine.addRule(rule4);
		engine.addRule(rule5);

		Map<String, String> facts = new HashMap<String, String>();
		facts.put("P1", "11");
		facts.put("P2", "22");
		facts.put("P3", "33");

		try {
			engine.compileRules(facts);
			engine.selectCompiledRules();
			engine.printSelectedRules();
			
			assertEquals(4, engine.getSelectedRules().size());
			assertEquals(rule4, engine.getSelectedRules().get(0));
			assertEquals(rule1, engine.getSelectedRules().get(1));
			assertEquals(rule3, engine.getSelectedRules().get(2));
			assertEquals(rule5, engine.getSelectedRules().get(3));
		} catch (EngineException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testEngine2() {
		System.out.println("\n\ntestEngine2()...");

		Engine engine = new Engine(false,true);
		Rule rule1 = new Rule("RULE1", "${P1} < ${P2}", 2);
		Rule rule2 = new Rule("RULE2", "${P3} < ${P2}", 4);
		Rule rule3 = new Rule("RULE3", "${P3} >= ${P1} AND ${P1} < ${P2}", 2);
		Rule rule4 = new Rule("RULE4", "@{RULE1} OR @{RULE2}", 5);
		Rule rule5 = new Rule("RULE5", "@{RULE4} AND @{RULE1}", 3);
		
		engine.addRule(rule1);
		engine.addRule(rule2);
		engine.addRule(rule3);
		engine.addRule(rule4);
		engine.addRule(rule5);

		Map<String, String> facts = new HashMap<String, String>();
		facts.put("P1", "55");
		facts.put("P2", "44");
		facts.put("P3", "33");

		try {
			engine.compileRules(facts);
			engine.selectCompiledRules();
			engine.printSelectedRules();
			
			assertEquals(2, engine.getSelectedRules().size());
			assertEquals(rule4, engine.getSelectedRules().get(0));
			assertEquals(rule2, engine.getSelectedRules().get(1));
		} catch (EngineException e) {
			e.printStackTrace();
		}
	}

}
