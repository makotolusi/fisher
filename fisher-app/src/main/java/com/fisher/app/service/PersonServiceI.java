package com.fisher.app.service;

import java.util.List;
import java.util.Map;

import com.fisher.app.domain.Person;

public interface PersonServiceI {

	public Map<String, Object> login(Person person);

	public Map<String, Object> register(Person person);
	public List<Person> queryAll();
}
