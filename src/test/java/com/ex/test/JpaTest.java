package com.ex.test;

import com.ex.entities.*;
import org.hibernate.jpa.QueryHints;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JpaTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    @Before
    public void init(){
        String persistenceUnitName = "jpa-test";
        Map<String,Object> properties = new HashMap<>();
        properties.put("hibernate.format_sql",true);
        entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName, properties);
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
    }
    @After
    public void destroy(){
        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }
    @Test
    public void testLeftJoin(){
        String jpql = "select c from Customer c left outer join c.orders where c.id = ?1";
        Customer customer = (Customer) entityManager.createQuery(jpql).setParameter(1,3).getSingleResult();
        System.out.println(customer.getOrders().size());
    }
    @Test
    public void testGroupBy(){
        String jpql = "select o.cust from Order o group by o.cust having count(o.id) >= 2";
        List list = entityManager.createQuery(jpql).getResultList();
        System.out.println(list.size());
    }

    @Test
    public void testQueryCache(){
        String jpql = "from Customer c where c.id > ?1";
        List list = entityManager.createQuery(jpql).setParameter(1, 1).getResultList();
        System.out.println(list.size());
        List list2 = entityManager.createQuery(jpql).setHint(QueryHints.HINT_CACHEABLE, true).setParameter(1, 1).getResultList();
        System.out.println(list2.size());
    }

    @Test
    public void testNativeQuery(){
        String sql = "update ad_customer set email = 'cc@qq.com' where id = ?1";
        int i = entityManager.createNativeQuery(sql).setParameter(1, 3).executeUpdate();
        System.out.println(i);

        sql = "select age from ad_customer where id = :id";
        Object result = entityManager.createNativeQuery(sql).setParameter("id", 1).getSingleResult();
        System.out.println(result);
    }


    @Test
    public void testNamedQuery(){
        Customer customer = (Customer) entityManager.createNamedQuery("customerQuery").setParameter(1, 1).getSingleResult();
        System.out.println(customer);
    }
    @Test
    public void testPartlyProties(){
        String jpql = "select new Customer(c.age,c.birth) from Customer c where c.id > ?1";
        Query query = entityManager.createQuery(jpql);
        query.setParameter(1, 1);
//        Object[] object = (Object[]) query.getSingleResult();
//        System.out.println(object[0]);
//        System.out.println(object[1]);
        List<Customer> list = query.getResultList();
        System.out.println(list.get(0));
    }


    @Test
    public void testJPQL(){
        String jpql = "select c from Customer c where c.age > ?1";
        Query query = entityManager.createQuery(jpql);
        query.setParameter(1,1);
        List list = query.getResultList();
        System.out.println(list);

    }



    @Test
    public void testSecondLevelCache(){
        Customer customer = entityManager.find(Customer.class, 1);

        entityTransaction.commit();
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction =  entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer2 = entityManager.find(Customer.class, 1);
        System.out.println(customer==customer2);
    }
    @Test
    public void testMany2ManyFind(){
//        Category category = entityManager.find(Category.class, 1l);
//        System.out.println(category.getItems().size());

        Item item = entityManager.find(Item.class, 1l);
        System.out.println(item.getCates().size());

    }
    @Test
    public void testMany2Many(){
        Category category1 = new Category();
        category1.setCateName("cat_aa");
        Category category2 = new Category();
        category2.setCateName("cat_aa");

        Item item1 = new Item();
        item1.setItemName("item_aa");
        Item item2 = new Item();
        item2.setItemName("item_bb");

        category1.getItems().add(item1);
        category1.getItems().add(item2);

        category2.getItems().add(item1);
        category2.getItems().add(item2);

        item1.getCates().add(category1);
        item1.getCates().add(category2);

        item2.getCates().add(category1);
        item2.getCates().add(category2);

        entityManager.persist(category1);
        entityManager.persist(category2);
        entityManager.persist(item1);
        entityManager.persist(item2);
    }

    @Test
    public void testOne2OneFind(){
        Department department = entityManager.find(Department.class, 1l);
        entityManager.remove(department);
//        System.out.println(department.getManager());
//        Manager manager = entityManager.find(Manager.class, 1l);
//        entityManager.remove(manager);
//        System.out.println(manager);
    }

    @Test
    public void testOne2One(){
        Manager manager = new Manager();
        manager.setName("James");
        Department department = new Department();
        department.setDeptName("Sales");
        manager.setDepartment(department);
        department.setManager(manager);
        entityManager.persist(department);
        entityManager.persist(manager);
    }

    @Test
    public void testTwoWay(){
        Customer customer = new Customer();
        customer.setLastName("cu_dd");
        Order order = new Order();
        order.setOrderName("or_gg");
        Order order1 = new Order();
        order1.setOrderName("or_hh");

        customer.getOrders().add(order);
        customer.getOrders().add(order1);

        order.setCust(customer);
        order1.setCust(customer);

        entityManager.persist(customer);
        entityManager.persist(order);
        entityManager.persist(order1);
    }
    @Test
    public void testOne2ManyRemove(){
        Customer customer = entityManager.find(Customer.class, 2);
        entityManager.remove(customer);
    }
    @Test
    public void testOne2ManyFind(){
        Customer customer = entityManager.find(Customer.class, 1);
        System.out.println(customer.getLastName());
        System.out.println(customer.getOrders());
    }


    @Test
    public void testOne2Many(){
        Customer customer = new Customer();
        customer.setLastName("cust_cc");
        customer.setEmail("aa@qq.com");
        Order order = new Order();
        order.setOrderName("order_ee");
        Order order2 = new Order();
        order2.setOrderName("order_ff");
        customer.getOrders().add(order);
        customer.getOrders().add(order2);
        entityManager.persist(order);
        entityManager.persist(order2);
        entityManager.persist(customer);
    }

    @Test
    public void testMany2OneUpdate(){
        Order order = entityManager.find(Order.class, 2);
//        order.getCustomer().setBirth(new Date());


    }

    @Test
    public void testMany2OneFind(){
//        Order order = entityManager.find(Order.class, 1);
//        System.out.println(order.getOrderName());
//        entityManager.remove(order);

        Customer customer = entityManager.find(Customer.class, 4);
        entityManager.remove(customer);
    }
    @Test
    public void testMany2One(){
        Customer customer = new Customer();
        customer.setLastName("c_aa");

        Order order = new Order();
        order.setOrderName("order_aa");
        Order order1 = new Order();
        order1.setOrderName("order_bb");

//        order1.setCustomer(customer);
//        order.setCustomer(customer);

        entityManager.persist(customer);
        entityManager.persist(order);
        entityManager.persist(order1);
    }

    @Test
    public void testPersist(){
//        Customer customer = new Customer();
//        customer.setCreateTime(new Date());
//        customer.setLastName("Harden");
//        customer.setAge(29);
//        entityManager.persist(customer);
//        customer.setEmail("aa@qq.com");
//        System.out.println(customer);
        Customer customer = entityManager.find(Customer.class, 4);
        System.out.println("-------------------");
        entityManager.persist(customer);
    }
    @Test
    public void testRefrence(){
        Customer reference = entityManager.find(Customer.class, 1);
//        entityTransaction.commit();
//        entityManager.close();
        System.out.println("--------------------");
        System.out.println(reference);
    }

    @Test
    public void testFind(){
        Customer customer = entityManager.find(Customer.class, 1);
        System.out.println(customer);
    }

    @Test
    public void hello(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-test");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = new Customer();
        customer.setAge(22);
        customer.setEmail("james@qq.com");
        customer.setLastName("james");
        customer.setBirth(new Date());
        customer.setCreateTime(new Date());
        entityManager.persist(customer);
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();

    }
}
