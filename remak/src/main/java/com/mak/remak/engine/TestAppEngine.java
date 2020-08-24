package com.mak.remak.engine;

import java.util.HashMap;
import java.util.Map;

import com.mak.remak.rules.Rule;

public class TestAppEngine {

	public static void main(String[] args) {
		System.out.println("TestAppEngine");
		
		Engine engine = new Engine(true);
		engine.addRule(new Rule("rule1","${PARAM1} >= ${PARAM2}",1));
		engine.addRule(new Rule("rule2","${PARAM3} > ${PARAM2}",2));
		engine.addRule(new Rule("rule3","${PARAM3} >= ${PARAM1} AND ${PARAM1} < ${PARAM2}",3));

		
		Map<String, String> facts = new HashMap<String, String>();
		facts.put("PARAM1", "11");
		facts.put("PARAM2", "22");
		facts.put("PARAM3", "11");
		
		engine.compileRules(facts);
		engine.selectCompiledRules();
		engine.printSelectedRules();
		
	}

}
