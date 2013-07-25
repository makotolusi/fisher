package com.fisher.app.service;

import java.util.Map;

import com.fisher.app.domain.Broad;
import com.fisher.app.domain.Post;

public interface BroadServiceI {

	public Map<String, Object> write(Broad broad);
	public Map<String, Object> queryBroadAll();

	
}
