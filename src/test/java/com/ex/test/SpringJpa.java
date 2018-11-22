package com.ex.test;

import com.ex.entities.Consumer;
import com.ex.service.ConsumeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.util.Arrays;

public class SpringJpa {

    private ApplicationContext ioc;
    private ConsumeService consumeService;
    {
        ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        consumeService = ioc.getBean(ConsumeService.class);
    }

    @Test
    public void testSave(){
        Consumer consumer = new Consumer();
        consumer.setCtime("2018-10-23 14:20:45");
        consumer.setMessage("JavaEE");
        consumer.setMoney(18888l);
        consumer.setSubject(3l);
        consumer.setSubjectName("初星3");
        consumer.setType("1");
        consumeService.save(consumer);

    }

    @Test
    public void testConsumer(){
        String[] beanDefinitionNames = ioc.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(System.out::println);
    }

    @Test
    public void testDataSource(){
        DataSource dataSource = ioc.getBean(DataSource.class);
        System.out.println(dataSource);
    }
}
