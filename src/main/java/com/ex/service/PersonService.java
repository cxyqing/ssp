package com.ex.service;

import com.ex.dao.PersonRepository;
import com.ex.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public Iterable<Person> savePersons(Iterable<Person> persons){
        return personRepository.saveAll(persons);
    }

    @Transactional
    public Integer updatePerson(String email, Integer id){
        return personRepository.update(email,id);
    }

}
