package com.mak.remak.engine;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mak.remak.engine.actions.FIAction;
import com.mak.remak.engine.rules.Rule;
import com.mak.remak.interpreter.BTInterpreter;
import com.mak.remak.interpreter.InterpreterException;

public class Engine {

	private ArrayList<Rule> rules;
	private ArrayList<Rule> selectedRules;

	private Map<String, FIAction<?, ?>> actions;

	protected Boolean showCalculation = false;
	protected Boolean showRuleSelection = false;

	public Engine() {
		super();
		this.rules = new ArrayList<Rule>();
		this.selectedRules = new ArrayList<Rule>();
		this.actions = new HashMap<String, FIAction<?, ?>>();
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

	public void addRulesFromFile(String fileName) throws EngineException {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			this.rules = (ArrayList<Rule>) objectMapper.readValue(Paths.get(fileName).toFile(),
					new TypeReference<List<Rule>>() {
					});
			for (Rule rule : rules) {
				rule.setCompiledExpression(rule.getExpression());
			}
		} catch (Exception e) {
			throw new EngineException("Exception while reading from the file.");
		}
	}

	private void sortRules(ArrayList<Rule> rules) {
		Collections.sort(rules, Collections.reverseOrder());
	}

	private Rule findRule(String subRule) {
		for (Rule rule : rules) {
			if (rule.getName().equals(subRule)) {
				return rule;
			}
		}
		return null;
	}

	public void compileRules(Map<String, String> facts) throws EngineException {
		Boolean areAllCompiled = false;
		while (!areAllCompiled) {
			areAllCompiled = true;
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
							areAllCompiled = false;
							String subRuleStr = str.substring(2, str.length() - 1);
							if (rule.getName().compareTo(subRuleStr) == 0) {
								throw new EngineException(
										"Cyclic rules are not allowed. Check rule: " + rule.getName());
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
		selectedRules.removeAll(selectedRules);
		for (Rule rule : rules) {
			BTInterpreter bt;
			try {
				bt = BTInterpreter.parseExpression(rule.getCompiledExpression(), this.showCalculation);
				int result = bt.traverseCalculate();
				if (result > 0) {
					rule.setIsSelected(true);
					selectedRules.add(rule);
				}
				if (this.showRuleSelection) {
					System.out.println("Expression Result: " + result + ", " + rule);
				}
			} catch (InterpreterException e) {
				e.printStackTrace();
				throw new EngineException("Exception while selecting compiled rule: " + rule.getName());
			}
		}
		if (selectedRules != null) {
			sortRules(selectedRules);
		}
	}

	public void printSelectedRules() {
		System.out.println("printSelectedRules()");
		for (Rule rule : selectedRules) {
			System.out.println(rule);
		}
	}

	public void putAction(String actionName, FIAction<?, ?> action) {
		this.actions.put(actionName, action);
	}

	private <Input, Output> Output executeAction(Rule rule, Input input) {
		if (rule.getResult() != null) {
			return (Output) rule.getResult();
		}
		else {
			@SuppressWarnings("unchecked")
			FIAction<Input, Output> action = (FIAction<Input, Output>) actions.get(rule.getAction());
			if (action != null) {
				return action.execute(input);
			}
		}
		return null;
	}

	public <Input, Output> Output executeBestAction(Map<String, String> facts, Input input) throws EngineException {
		this.compileRules(facts);
		this.selectCompiledRules();
		//this.printSelectedRules();
		return executeBestAction(input);
	}

	public <Input, Output> Output executeBestAction(Input input) {
		if (selectedRules.size() > 0) {
			Rule rule = selectedRules.get(0);
			return executeAction(rule, input);
		}
		return null;
	}

	public <Input, Output> ArrayList<Output> executeAllActions(Map<String, String> facts, Input input) throws EngineException {
		this.compileRules(facts);
		this.selectCompiledRules();
		//this.printSelectedRules();
		return executeAllActions(input);
	}

	public <Input, Output> ArrayList<Output> executeAllActions(Input input) {
		ArrayList<Output> results = new ArrayList<Output>();
		for (Rule rule : selectedRules) {
			Output result = executeAction(rule, input);
			if (result != null) {
				results.add(result);
			}
		}
		return results;
	}

}
