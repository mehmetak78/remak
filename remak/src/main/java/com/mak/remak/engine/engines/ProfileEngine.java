package com.mak.remak.engine.engines;

import com.mak.remak.engine.Engine;
import com.mak.remak.engine.EngineException;

public class ProfileEngine extends Engine {

	public ProfileEngine(Boolean showCalculation, Boolean showRuleSelection) throws EngineException {
		super(showCalculation, showRuleSelection);
		this.addRulesFromFile("target/rules/ProfileRules.json");
	}

	public ProfileEngine() throws EngineException {
		this(false,false);
	}
	

}
