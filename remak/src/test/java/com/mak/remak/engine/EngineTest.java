package com.mak.remak.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.mak.remak.engine.rules.Rule;

class EngineTest {

	@Test
	void testMyEngine() {
		System.out.println("\ntestMyEngine()...");
		
		MyEngine engine = new MyEngine(false,false);
		MyInput input = new MyInput("Input For Test", 111);
		
		Map<String, String> facts = new HashMap<String, String>();
		facts.put("P1", "11");
		facts.put("P2", "22");
		facts.put("P3", "33");

		try {
			engine.compileRules(facts);
			engine.selectCompiledRules();
			engine.printSelectedRules();
			Integer result = engine.executeBestAction(input);
			System.out.println("Result: "+result);
			assertEquals(2, engine.getSelectedRules().size());
			assertEquals(200, result);
			
			ArrayList<Integer> results = engine.executeAllActions(input);
			assertEquals(2, engine.getSelectedRules().size());
			assertEquals(2, results.size());
			assertEquals(200, results.get(0));
			assertEquals(100, results.get(1));
			for (Integer result1 : results) {
				System.out.println("Result: " + result1);
			}

		} catch (EngineException e) {
			e.printStackTrace();
		}
		
	}

}
