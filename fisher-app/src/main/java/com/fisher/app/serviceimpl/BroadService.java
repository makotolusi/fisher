package com.fisher.app.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.fisher.app.domain.Broad;
import com.fisher.app.repository.BroadRepositoryI;
import com.fisher.app.service.BroadServiceI;
import com.fisher.app.util.Utils;

@Service
public class BroadService implements BroadServiceI{

	@Autowired
	private MongoOperations mongoTemplate;
	@Autowired
	private BroadRepositoryI broadRepositoryI;
	
	public Map<String, Object> write(Broad broad) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> queryAll(int s,int e) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		Page<Broad>  broads=broadRepositoryI.findAll(new PageRequest(s, e));
		result.put(Utils.RESULT, broads);
		return Utils.resultSUC(result);
	}
	
	
	public Map<String, Object> queryBroadAll() {
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<Broad> broads=broadRepositoryI.findAll();
		result.put(Utils.RESULT, broads);
		return Utils.resultSUC(result);
	}
	
	

}
