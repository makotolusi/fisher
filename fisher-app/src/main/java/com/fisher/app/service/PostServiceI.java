package com.fisher.app.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.fisher.app.domain.Post;


public interface PostServiceI {


	
	public Map<String, Object> writeByBroadId(Post post,String id) ;

	
}
