package com.ex.dao;

import com.ex.entities.Consumer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ConsumeDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Consumer save(Consumer consumer){
        return entityManager.merge(consumer);
    }
}
