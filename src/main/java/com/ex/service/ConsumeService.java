package com.ex.service;


import com.ex.dao.ConsumeDao;
import com.ex.entities.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConsumeService {

    @Autowired
    private ConsumeDao consumeDao;

    @Transactional
    public void save(Consumer consumer){
        consumeDao.save(consumer);
//        int i  = 10 / 0;
        consumeDao.save(consumer);
    }
}
