package com.mak.remak.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import com.mak.remak.interpreter.BTInterpreter;
import com.mak.remak.interpreter.InterpreterException;
import com.mak.remak.rules.Rule;

public class Engine {

	private ArrayList<Rule> rules;

	private Boolean showCalculation = false;

	public Engine() {
		super();
		this.rules = new ArrayList<Rule>();
	}

	public Engine(Boolean showCalculation) {
		this();
		this.showCalculation = showCalculation;
	}

	public ArrayList<Rule> getRules() {
		return rules;
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
		Boolean hasSubRule = true;
		while (hasSubRule) {
			hasSubRule = false;
			for (Rule rule : rules) {
				String[] strArr = rule.getCompiledExpression().split(" ");
				String newExpression = "";
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
						hasSubRule = true;
						String subRuleStr = str.substring(2, str.length() - 1);
						if (rule.getName().compareTo(subRuleStr) == 0) {
							throw new EngineException("Cyclic rule is not allowed. Check rule: " + rule.getName());
						}
						Rule subRule = findRule(subRuleStr);
						if (subRule != null) {
							newExpression += " ( " + subRule.getCompiledExpression()+ " ) ";
						}
						else {
							throw new EngineException("Exception while compiling rule: " + rule.getName());
						}
					}
					else {
						newExpression += " " + str + " ";
					}
				}
				rule.setCompiledExpression(newExpression);
			}
		}
	}

	public void selectCompiledRules() throws EngineException {
		for (Rule rule : rules) {
			BTInterpreter bt;
			try {
				bt = BTInterpreter.parseExpression(rule.getCompiledExpression(), this.showCalculation);
				int result = bt.traverseCalculate();
				if (result > 0) {
					rule.setIsSelected(true);
				}
				if (this.showCalculation) {
					System.out.println(rule + ":" + result);
				}
			} catch (InterpreterException e) {
				e.printStackTrace();
				throw new EngineException("Exception while selecting compiled rule: " + rule.getName());
			}
		}
	}

	public void printSelectedRules() {
		System.out.println("printSelectedRules()");
		sortRules(rules);
		for (Rule rule : rules) {
			if (rule.getIsSelected()) {
				System.out.println(rule);
			}
		}
	}

}
