package com.mak.remak.engine.engines;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.mak.remak.engine.Engine;
import com.mak.remak.engine.EngineException;
import com.mak.remak.engine.actions.ActionParams;

class ProfileEngineTest {

	@Test
	void testProfileEngine() {
		System.out.println("\ntestProfileEngine()...");

		try {
			Engine engine = new ProfileEngine(false, true);
		
			Map<String, String> facts = new HashMap<String, String>();
			facts.put("P1", "11");
			facts.put("P2", "22");
			facts.put("P3", "33");
					
			System.out.println("\nExecute Best Selected Action...");
			String result = engine.executeBestAction(facts,null);
			System.out.println("Result: " + result);
			assertEquals(3, engine.getSelectedRules().size());
			assertEquals("PROFILE2", result);

			System.out.println("\nExecute All Selected Actions");
			ArrayList<String> results = engine.executeAllActions(facts,null);
			assertEquals(3, engine.getSelectedRules().size());
			assertEquals(3, results.size());
			assertEquals("PROFILE2", results.get(0));
			assertEquals("PROFILE3", results.get(1));
			assertEquals("PROFILE1", results.get(2));
			for (String result1 : results) {
				System.out.println("Result: " + result1);
			}

		} catch (EngineException e) {
			e.printStackTrace();
		}


		
		
	}

}
