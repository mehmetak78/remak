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
import com.sun.tools.javac.util.List;

class ProfileEngineTest {

	@Test
	void testProfileEngine() {
		System.out.println("\ntestProfileEngine()...");

		try {
			Engine engine = new ProfileEngine(false, false, true);
		
			Map<String, String> facts = new HashMap<String, String>();
			facts.put("P1", "11");
			facts.put("P2", "22");
			facts.put("P3", "33");
			facts.put("P4", "14");
					
			System.out.println("\nExecute Best Selected Action...");
			String result = engine.executeBestAction(facts,null);
			System.out.println("Result: " + result);
			assertEquals("PROFILE3", result);

			System.out.println("\nExecute All Selected Actions");
			ArrayList<String> results = engine.executeAllActions(facts,null);
			System.out.println(results);
			assertTrue(results.equals(new ArrayList<String>(Arrays.asList("PROFILE3","PROFILE1","PROFILE2"))));

		
		} catch (EngineException e) {
			e.printStackTrace();
			fail("Exception:"+e.getMessage());
		}
	}

}
