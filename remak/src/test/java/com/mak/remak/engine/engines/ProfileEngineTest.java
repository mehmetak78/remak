package com.mak.remak.engine.engines;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.mak.remak.engine.Engine;
import com.mak.remak.engine.EngineException;
import com.mak.remak.engine.actions.ActionParams;
import com.mak.remak.engine.rules.Rule;
import com.sun.tools.javac.util.List;

class ProfileEngineTest {

	@Test
	void testProfileEngine() {
		System.out.println("\ntestProfileEngine()...");

		try {
			Engine engine = new ProfileEngine();
		
			Map<String, String> facts = new HashMap<String, String>();
			facts.put("P1", "11");
			facts.put("P2", "22");
			facts.put("P3", "33");
			facts.put("P4", "MEHMET");
					
			System.out.println("\nExecute Best Selected Action...");
			String result = engine.executeBestAction(facts,null);
			
			System.out.println("Result: " + result);
			assertEquals("PROFILE4", result);
			
			engine.addRule(new Rule("com.mak.remak.rules", "RULE5", "${P5} = PROFILE4", "Desc for rule 5", 6, "RESULT5", null));
			facts.put("P5", result);
			System.out.println("\nExecute Best Selected Action with New Rule and Fact...");
			String result2 = engine.executeBestAction(facts,null);
			System.out.println("Result2: " + result2);
			assertEquals("RESULT5", result2);
			
			System.out.println("\nExecute All Selected Actions");
			ArrayList<String> results = engine.executeAllActions(facts,null);
			System.out.println(results);
			assertTrue(results.equals(new ArrayList<String>(Arrays.asList("RESULT5","PROFILE4","PROFILE3"))));

			engine.printSelectedRules();
		
		} catch (EngineException e) {
			e.printStackTrace();
			fail("Exception:"+e.getMessage());
		}
	}

}
