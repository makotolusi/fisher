package com.fisher.app.mongo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.fisher.app.domain.Account;
import com.fisher.app.domain.Person;

@Repository
public class HelloMongo {

	@Autowired
	MongoOperations mongoOperations;

	public void run() {

		if (!mongoOperations.collectionExists(Person.class)) {
			mongoOperations.createCollection(Person.class);
		}

	}

}
