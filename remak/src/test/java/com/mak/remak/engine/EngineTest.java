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
		facts.put("P1", "55");
		facts.put("P2", "44");
		facts.put("P3", "33");

		try {
			engine.compileRules(facts);
			engine.selectCompiledRules();
			engine.printSelectedRules();
			Integer result = (Integer) engine.executeBestAction(input);
			System.out.println("Result: "+result);
			assertEquals(1, engine.getSelectedRules().size());
			assertEquals(200, result);

		} catch (EngineException e) {
			e.printStackTrace();
		}
		
	}

}
