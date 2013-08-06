package com.fisher.app.repository;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fisher.app.domain.Broad;

@Repository
public interface BroadRepositoryI extends MongoRepository<Broad,ObjectId>  {

	public Page<Broad> findById	(String id,Pageable page) ;
	

}
