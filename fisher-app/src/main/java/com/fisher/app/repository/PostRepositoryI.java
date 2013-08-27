package com.fisher.app.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fisher.app.domain.Broad;
import com.fisher.app.domain.Post;

@Repository
public interface PostRepositoryI extends MongoRepository<Post,ObjectId>  {

	public Post findById	(String id) ;
	
	public Page<Post> findByBroad	(Broad broad,Pageable page) ;
}
