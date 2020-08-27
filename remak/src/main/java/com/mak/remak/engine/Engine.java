package com.mak.remak.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.mak.remak.engine.actions.FIAction;
import com.mak.remak.engine.rules.Rule;
import com.mak.remak.interpreter.BTInterpreter;
import com.mak.remak.interpreter.InterpreterException;

public class Engine {

	private ArrayList<Rule> rules;
	ArrayList<Rule> selectedRules;
	
	private Map<String, FIAction<?, ?>> actions;

	protected Boolean showCalculation = false;
	protected Boolean showRuleSelection = false;

	public Engine() {
		super();
		this.rules = new ArrayList<Rule>();
		this.selectedRules = new ArrayList<Rule>();
		this.actions = new HashMap<String, FIAction<?,?>>();
	}

	public Engine(Boolean showCalculation, Boolean showRuleSelection) {
		this();
		this.showCalculation = showCalculation;
		this.showRuleSelection = showRuleSelection;
	}

	public ArrayList<Rule> getRules() {
		return rules;
	}
	
	public ArrayList<Rule> getSelectedRules() {
		return selectedRules;
	}


	public void addRule(Rule rule) {
		rule.setCompiledExpression(rule.getExpression());
		this.rules.add(rule);
	}

	private void sortRules(ArrayList<Rule> rules) {
		Collections.sort(rules, Collections.reverseOrder());
	}

	private Rule findRule(String subRule) {
		for (Rule rule : rules) {
			if (rule.getName().compareTo(subRule) == 0) {
				return rule;
			}
		}
		return null;
	}

	public void compileRules(Map<String, String> facts) throws EngineException {
		Boolean areAllCompiled = true;
		while (areAllCompiled) {
			areAllCompiled = false;
			for (Rule rule : rules) {
				if (!rule.getIsCompiled()) {
					String[] strArr = rule.getCompiledExpression().split(" ");
					String newExpression = "";
					rule.setIsCompiled(true);
					for (String str : strArr) {
						if (str.startsWith("${")) {
							String key = str.substring(2, str.length() - 1);
							String value = facts.get(key);
							if (value == null) {
								throw new EngineException("Invalid parameter in the rule: " + rule.getName());
							}
							newExpression += " " + value + " ";
						}
						else if (str.startsWith("@{")) {
							areAllCompiled = true;
							String subRuleStr = str.substring(2, str.length() - 1);
							if (rule.getName().compareTo(subRuleStr) == 0) {
								throw new EngineException("Cyclic rule is not allowed. Check rule: " + rule.getName());
							}
							Rule subRule = findRule(subRuleStr);
							if (subRule != null) {
								newExpression += "( " + subRule.getCompiledExpression() + ") ";
							}
							else {
								throw new EngineException("Exception while compiling rule: " + rule.getName());
							}
							rule.setIsCompiled(false);
						}
						else {
							newExpression += "" + str + " ";

						}
					}
					rule.setCompiledExpression(newExpression);
				}

			}
		}
	}

	public void selectCompiledRules() throws EngineException {
		
		if (this.showRuleSelection) {
			System.out.println("\nselectCompiledRules()");
		}
		for (Rule rule : rules) {
			BTInterpreter bt;
			try {
				bt = BTInterpreter.parseExpression(rule.getCompiledExpression(), this.showCalculation);
				int result = bt.traverseCalculate();
				if (result > 0) {
					rule.setIsSelected(true);
					getSelectedRules().add(rule);
				}
				if (this.showRuleSelection) {
					System.out.println("Result: "+ result +", " + rule );
				}
			} catch (InterpreterException e) {
				e.printStackTrace();
				throw new EngineException("Exception while selecting compiled rule: " + rule.getName());
			}
		}
		if (getSelectedRules() != null) {
			sortRules(getSelectedRules());
		}
	}

	public void printSelectedRules() {
		System.out.println("printSelectedRules()");
		for (Rule rule : getSelectedRules()) {
			System.out.println(rule);
		}
	}
	
	
	public void putAction(String actionName, FIAction<?, ?> action) {
		this.actions.put(actionName, action);
	}
	
	public Object executeBestAction(Object input) {
		if (selectedRules.size()>0) {
			Rule rule = this.selectedRules.get(0);
			FIAction action = actions.get(rule.getAction());
			if (action != null) {
				return action.execute(input);
			}
		}
		return null;
	}
	
	public Object executeAllActions(Object input) {
		ArrayList<Integer> results = new ArrayList<Integer>();
		for (Rule rule : getSelectedRules()) {
			FIAction action = actions.get(rule.getAction());
			if (action != null) {
				results.add((Integer) action.execute(input));
			}
		}
		return results;
	}



	

}
