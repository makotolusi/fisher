package com.fisher.app.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.fisher.app.dao.CommonDaoI;

@Repository
public class CommonDao implements CommonDaoI{

	@Autowired
	private MongoOperations mongoTemplate;
	
	public Object findById(String id,Class clazz){
		Query query=new Query();
		query.addCriteria(Criteria.where(id).is(id));
		return mongoTemplate.findOne(query,clazz);
		
	}
}
