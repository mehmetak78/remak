package com.mak.remak.engine;

public class MyInput {
	private String inputName;
	private Integer inputValue;
	
	public MyInput(String inputName, int i) {
		super();
		this.inputName = inputName;
		this.inputValue = i;
	}
	public String getInputName() {
		return inputName;
	}
	public void setInputName(String inputName) {
		this.inputName = inputName;
	}
	public Integer getInputValue() {
		return inputValue;
	}
	public void setInputValue(Integer inputValue) {
		this.inputValue = inputValue;
	}
	@Override
	public String toString() {
		return "MyInput [inputName=" + inputName + ", inputValue=" + inputValue + "]";
	}
}
