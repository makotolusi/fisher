package com.fisher.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.fisher.app.domain.Account;
import com.fisher.app.domain.Person;
import com.fisher.app.service.PersonServiceI;

@Service
public class PersonService implements PersonServiceI {

	@Autowired
	private MongoOperations mongoTemplate;

	public void add(Object object) {
		this.mongoTemplate.insert(object);
	}

	
	public List<Person>  test() {
		if (mongoTemplate.collectionExists(Person.class)) {
			mongoTemplate.dropCollection(Person.class);
		}

		mongoTemplate.createCollection(Person.class);

		Person p = new Person("John", 39);
		Account a = new Account("1234-59873-893-1", Account.Type.SAVINGS, 123.45D);
		p.getAccounts().add(a);

		mongoTemplate.insert(p);

		List<Person> results = mongoTemplate.findAll(Person.class);
		System.out.println("Results: " + results);
		return results;
	}
}
