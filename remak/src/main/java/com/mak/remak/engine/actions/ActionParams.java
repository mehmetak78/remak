package com.mak.remak.engine.actions;

import java.util.HashMap;
import java.util.Map;

public class ActionParams {
	private Map<String, String> params;

	public ActionParams() {
		super();
		this.setParams(new HashMap<String, String>());
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	
	public void putParam(String key, String value) {
		params.put(key,  value);
	}
	
	public String getParam(String key) {
		return params.get(key);
	}

}
