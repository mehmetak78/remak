package com.mak.remak.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import com.mak.remak.interpreter.BTInterpreter;
import com.mak.remak.interpreter.InterpreterException;
import com.mak.remak.rules.Rule;

public class Engine {

	private ArrayList<Rule> rules;
	private ArrayList<Rule> compiledRules;
	private ArrayList<Rule> selectedRules;
	
	private Boolean showCalculation = false;

	public Engine() {
		super();
		this.rules = new ArrayList<Rule>();
		this.compiledRules = new ArrayList<Rule>();
		this.selectedRules = new ArrayList<Rule>();
	}
	
	public Engine(Boolean showCalculation) {
		this();
		this.showCalculation = showCalculation;
	}

	public ArrayList<Rule> getRules() {
		return rules;
	}

	public void addRule(Rule rule) {
		this.rules.add(rule);
	}

	private void sortRules(ArrayList<Rule> rules) {
		Collections.sort(rules, Collections.reverseOrder());
	}

	public void compileRules(Map<String, String> facts) {
		for (Rule rule : rules) {
			Rule compiledRule = new Rule(rule);
			String[] strArr = rule.getExpression().split(" ");
			String newExpression = "";
			for (String str : strArr) {
				if (str.startsWith("${")) {
					String key = str.substring(2, str.length() - 1);
					String value = facts.get(key);
					newExpression += " " + value + " ";
				}
				else {
					newExpression += " " + str + " ";
				}
			}
			compiledRule.setExpression(newExpression);
			compiledRules.add(compiledRule);
		}
	}

	public void selectCompiledRules() {
		for (Rule compiledRule : compiledRules) {
			BTInterpreter bt;
			try {
				bt = BTInterpreter.parseExpression(compiledRule.getExpression(), this.showCalculation);
				int result = bt.traverseCalculate();
				if (result > 0) {
					selectedRules.add(compiledRule);
				}
				if (this.showCalculation) {
					System.out.println(compiledRule + ":" + result);
				}
			} catch (InterpreterException e) {
				e.printStackTrace();
			}
		}
	}

	public void printSelectedRules() {
		sortRules(selectedRules);
		for (Rule selectedRule : selectedRules) {
			System.out.println(selectedRule);
		}
	}

}
