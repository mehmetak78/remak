package com.mak.remak.engine.rules;

public class Rule implements Comparable<Rule>{

	private String nameSpace = "com.mak.remak.rules";
	private String name = null;
	private String description = null;
	private Integer priority = 0;
	private Boolean isSubRule = null;
	private String expression = null;
	private String compiledSubRuleExpression = null;
	private String compiledExpression = null;
	private String result = null;
	private String action = null;
	private Boolean isSelected = false;
	private Boolean isCompiled = false;
	private Boolean isSubRulesCompiled = false;
	
	public Rule(String nameSpace, String name, String expression, String description, Integer priority, String result, String action) {
		super();
		this.nameSpace = nameSpace;
		this.name = name;
		this.expression = expression;
		this.description = description;
		this.priority = priority;
		this.result = result;
		this.action = action;
	}

	public Rule() {
		super();
	}
	
	public Rule(String name,  Integer priority) {
		super();
		this.name = name;
		this.priority = priority;
	}
	
	public Rule(String name, String expression, Integer priority) {
		super();
		this.name = name;
		this.expression = expression;
		this.priority = priority;
	}
	
	public Rule(Rule rule) {
		super();
		this.nameSpace = rule.nameSpace;
		this.name = rule.name;
		this.expression = rule.expression;
		this.description = rule.description;
		this.priority = rule.priority;
		this.action = rule.action;
	}
	
	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public String getCompiledExpression() {
		return compiledExpression;
	}
	
	public void setCompiledExpression(String compiledExpression) {
		this.compiledExpression = compiledExpression;
	}

	public String getCompiledSubRuleExpression() {
		return compiledSubRuleExpression;
	}

	public void setCompiledSubRuleExpression(String compiledSubRuleExpression) {
		this.compiledSubRuleExpression = compiledSubRuleExpression;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public Boolean getIsSubRule() {
		return isSubRule;
	}

	public void setIsSubRule(Boolean isSubRule) {
		this.isSubRule = isSubRule;
	}
	
	public Boolean getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public Boolean getIsCompiled() {
		return isCompiled;
	}

	public void setIsCompiled(Boolean isCompiled) {
		this.isCompiled = isCompiled;
	}
	
	public Boolean getIsSubRulesCompiled() {
		return isSubRulesCompiled;
	}

	public void setIsSubRulesCompiled(Boolean isSubRulesCompiled) {
		this.isSubRulesCompiled = isSubRulesCompiled;
	}



	
	@Override
	public int compareTo(Rule o) {
		if (this.priority < o.priority) {
			return -1;
		}
		else if (this.priority == o.priority) {
			return 0;
		}
		return 1;
	}

	@Override
	public String toString() {
		return name +" :\n\tnameSpace=" + nameSpace + ", name=" + name + ", description=" + description + ",\n\tpriority="
				+ priority + ", isSubRule=" + isSubRule + ", \n\texpression=" + expression + ", \n\tcompiledSubRuleExpression="
				+ compiledSubRuleExpression + ", \n\tcompiledExpression=" + compiledExpression + ", \n\tresult=" + result
				+ ", action=" + action ;
	}


	
}
