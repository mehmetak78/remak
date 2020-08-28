package com.mak.remak.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class MyEngineTest {

	@Test
	void testMyEngine() {
		System.out.println("\ntestMyEngine()...");

		try {
			MyEngine engine = new MyEngine(false, false);
		
			Map<String, String> actionParams = new HashMap<String, String>();
			actionParams.put("MESSAGE", "Hello");
			
			Map<String, String> facts = new HashMap<String, String>();
			facts.put("P1", "11");
			facts.put("P2", "22");
			facts.put("P3", "33");
		
			engine.compileRules(facts);
			engine.selectCompiledRules();
			engine.printSelectedRules();
			Integer result = engine.executeBestAction(actionParams);
			System.out.println("Result: " + result);
			assertEquals(3, engine.getSelectedRules().size());
			assertEquals(200, result);

			ArrayList<Integer> results = engine.executeAllActions(actionParams);
			assertEquals(3, engine.getSelectedRules().size());
			assertEquals(3, results.size());
			assertEquals(200, results.get(0));
			assertEquals(300, results.get(1));
			assertEquals(100, results.get(2));
			for (Integer result1 : results) {
				System.out.println("Result: " + result1);
			}

		} catch (EngineException e) {
			e.printStackTrace();
		}


		
		
	}

}
