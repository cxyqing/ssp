package com.ex.test;

import com.ex.dao.PersonRepository;
import com.ex.entities.Person;
import com.ex.service.PersonService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpringDataJpa {

    private ApplicationContext ctx;
    private PersonRepository personRepository;
    private PersonService personService;

    {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        personRepository = ctx.getBean(PersonRepository.class);
        personService = ctx.getBean(PersonService.class);
    }

    @Test
    public void testJpaSpecifictionExecutor(){
        List<Person> list = personRepository.findAll(new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Integer> path = root.get("id");

                Predicate predicate = criteriaBuilder.gt(path, 5);
                return predicate;
            }
        });
        list.stream().forEach(System.out::println);

    }



    @Test
    public void testJpaRepository(){
        Person person = new Person();
        person.setId(1);
        person.setBirth(new Date());
        Person person1 = personRepository.saveAndFlush(person);
        System.out.println(person == person1);
    }
    @Test
    public void testPaginAndSortingRepository(){
        int pageNo = 5;
        int page = pageNo - 1;
        int pageSize = 5;

        Pageable pageable = PageRequest.of(page,pageSize, Sort.by(Sort.Order.desc("email"),Sort.Order.asc("id")));
        Page<Person> personPage = personRepository.findAll(pageable);
        System.out.println("totalElements: " + personPage.getTotalElements());
        System.out.println("totalPages: " + personPage.getTotalPages());
        System.out.println("当前第几页: " + (personPage.getNumber() + 1));
        System.out.println("NumberOfElements: " + personPage.getNumberOfElements());
        System.out.println("Size: " + personPage.getSize());

    }

    @Test
    public void testCrudRepository(){
        List<Person> persons = new ArrayList<>();
        for (int i = 'a';i <= 'z';i++){
            Person person = new Person();
            person.setBirth(new Date());
            person.setLastName((char)i + "" + (char)i);
            person.setEmail((char)i + "" + (char)i + "@qq.com");
            persons.add(person);
        }
        Iterable<Person> personsList = personService.savePersons(persons);
        personsList.forEach(System.out::println);
    }

    @Test
    public void testSpringData() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
       /*
       Person person = personRepository.readByLastName("AA");
        System.out.println(person);
        Date birth = format.parse("2009-01-08");
        Person top = personRepository.findTopByBirth(birth);
        System.out.println(top);
        List<Person> persons = personRepository.findAllByLastNameStartingWithAndIdLessThan("A", 3);
        List<Person> persons = personRepository.testQueryParamsLike("A","a");
        persons.stream().forEach(System.out::println);
        Long totalCount = personRepository.getTotalCount();
        System.out.println(totalCount);
        List<Person> persons = personRepository.getPerson(1l);
        persons.stream().forEach(System.out::println);
        */
        Integer update = personService.updatePerson("james@qq.com", 1);
        System.out.println(update);
    }




}
