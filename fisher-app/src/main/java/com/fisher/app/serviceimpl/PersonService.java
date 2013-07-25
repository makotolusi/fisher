package com.fisher.app.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.fisher.app.domain.Person;
import com.fisher.app.service.PersonServiceI;
import com.fisher.app.util.Utils;

@Service
public class PersonService implements PersonServiceI {

	@Autowired
	private MongoOperations mongoTemplate;
	

	public Map<String, Object> login(Person person) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (isExist(person)) {
			return Utils.resultSUC(result);
		} else {
			return Utils.resultFAL(result);
		}
	}

	

	public List<Person> queryAll() {

		return mongoTemplate.findAll(Person.class);
	}

	public Map<String, Object> register(Person person) {
		Map<String, Object> result = new HashMap<String, Object>();

		if (isExist(person)) {
			
			return Utils.resultExist(result);
		} else {
			mongoTemplate.insert(person);
			return Utils.resultSUC(result);
		}
	}

	private boolean isExist(Person person) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(person.getEmail()));
		query.addCriteria(Criteria.where("password").is(person.getPassword()));
		Person p = mongoTemplate.findOne(query, Person.class);
		if (p == null)
			return false;
		else
			return true;
	}
	

}
