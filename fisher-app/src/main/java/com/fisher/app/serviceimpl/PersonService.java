package com.fisher.app.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.fisher.app.domain.Account;
import com.fisher.app.domain.Person;
import com.fisher.app.service.PersonServiceI;
import com.fisher.app.util.StateEnum;

@Service
public class PersonService implements PersonServiceI {

	@Autowired
	private MongoOperations mongoTemplate;

	public Map<String, String> login(Person person) {
		Map<String, String> result = new HashMap<String, String>();
		if (isExist(person)) {
			result.put(StateEnum.SUC.name(), StateEnum.SUC.getMsg());
			return result;
		} else {
			result.put(StateEnum.FAL.name(), StateEnum.FAL.getMsg());
			return result;
		}
	}

	public List<Person> queryAll() {

		return mongoTemplate.findAll(Person.class);
	}

	public Map<String, String> register(Person person) {
		Map<String, String> result = new HashMap<String, String>();

		if (isExist(person)) {
			result.put(StateEnum.EXIST.name(), StateEnum.EXIST.getMsg());
			return result;
		} else {
			mongoTemplate.insert(person);
			result.put(StateEnum.SUC.name(), StateEnum.SUC.getMsg());
			return result;
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
	
	public static void main(String[] args) {
		
		System.out.println(StateEnum.FAL.getMsg());
	}
}
