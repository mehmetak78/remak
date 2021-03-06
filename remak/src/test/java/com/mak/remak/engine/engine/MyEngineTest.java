package com.mak.remak.engine.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.mak.remak.engine.EngineException;
import com.mak.remak.engine.actions.ActionParams;
import com.mak.remak.engine.engines.MyEngine;

class MyEngineTest {

	@Test
	void testMyEngine() {
		System.out.println("\ntestMyEngine()...");

		try {
			MyEngine engine = new MyEngine(false, false, true);
		
			ActionParams actionParams1 = new ActionParams();
			actionParams1.putParam("MESSAGE", "Hello");
			
			Map<String, String> facts = new HashMap<String, String>();
			facts.put("P1", "11");
			facts.put("P2", "22");
			facts.put("P3", "33");
		
			
			engine.compileRules(facts);
			engine.selectCompiledRules();
			engine.printSelectedRules();

			
			System.out.println("\nExecute Best Selected Action...");
			String result = engine.executeBestAction(actionParams1);
			System.out.println("Result: " + result);
			assertEquals(1, engine.getSelectedRules().size());
			assertEquals("300", result);

			System.out.println("\nExecute All Selected Actions");
			ArrayList<String> results = engine.executeAllActions(actionParams1);
			assertEquals(1, engine.getSelectedRules().size());
			assertEquals(1, results.size());
			assertEquals("300", results.get(0));
			for (String result1 : results) {
				System.out.println("Result: " + result1);
			}

			
			engine.printAllRules(true);
		} catch (EngineException e) {
			e.printStackTrace();
		}


		
		
	}

}
