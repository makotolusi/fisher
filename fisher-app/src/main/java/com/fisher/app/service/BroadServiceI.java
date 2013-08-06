package com.fisher.app.service;

import java.util.Map;

import com.fisher.app.domain.Broad;

public interface BroadServiceI {

	public Map<String, Object> queryAll(int s,int e);
	public Map<String, Object> write(Broad broad);
	public Map<String, Object> queryBroadAll();

	
}
