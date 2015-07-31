package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.Person;
import com.test.service.PersonService;

@RestController
public class PersonRestController {

    private PersonService personService;

	public PersonService getPersonService() {
		return personService;
	}

    @Autowired(required=true)
    @Qualifier(value="personService")
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping(value = "/api/person/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Person> personForm(@PathVariable int id) {
		Person person = personService.getPersonById(id);
		if(person==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(person, HttpStatus.OK);
		}
	}
}