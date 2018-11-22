package com.ex.dao;


import com.ex.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Integer>, JpaSpecificationExecutor<Person> {

    /*
    Person readByLastName(String lastName);
    Person findTopByBirth(Date birth);

    List<Person> findAllByLastNameStartingWithAndIdLessThan(String lastName,Integer id);

    List<Person> getAllByLastNameIn(List<String> list);
    List<Person> getAllByLastNameGreaterThan(String lastName);
    List<Person> findAllByBirthBetween(Date birth1,Date birth2);
    List<Person> findAllByLastNameIsNotNullOrEmail(String email);
    List<Person> findAllByLastNameNot(String lastName);
    List<Person> findAllByLastNameNotLike(String lastName);
    @Query("select p from Person p where p.id = :id and p.lastName = :lastName")
    List<Person> testQueryParams(@Param("lastName") String lastName,@Param("id") Integer id);
    @Query("select  p from Person p where p.lastName like %:lastName% and p.email like %:email%")
    List<Person> testQueryParamsLike(@Param("lastName") String lastName,@Param("email") String email);
    @Query(value = "select count(*) from ad_person ",nativeQuery = true)
    Long getTotalCount();
    @Query(value = "select * from ad_person where id <> :id",nativeQuery = true)
    List<Person> getPerson(@Param("id") Long id);
    */

    @Modifying
    @Query("update Person p set p.email = :email where p.id = :id")
    Integer update(@Param("email") String email,@Param("id") Integer id);
}
