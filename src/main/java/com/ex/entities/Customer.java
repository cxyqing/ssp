package com.ex.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NamedQuery(name = "customerQuery",query = "from Customer c where c.id = ?1")
@Cacheable
@Table(name = "ad_customer")
@Entity
public class Customer {
//    @TableGenerator(name = "id_generator", table = "ad_generators",
//            pkColumnName = "pk_name", pkColumnValue = "customer_id", valueColumnName = "pk_value",
//    initialValue = 10,allocationSize = 30)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    public Customer(){

    }
    public Customer(Integer age, Date birth) {
        this.age = age;
        this.birth = birth;
    }

    @Column(name = "last_name", length = 50, columnDefinition = "varchar(56) COMMENT '姓名'")
    private String lastName;

    private Integer age;

    private String email;
    @Temporal(TemporalType.DATE)
    private Date birth;
    @Column(name="create_time",columnDefinition = "datetime DEFAULT NULL COMMENT '创建时间'")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

//    @JoinColumn(name = "customer_id")
    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE},mappedBy = "cust")
    private Set<Order> orders = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInfo() {
        return lastName + "---" + email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", createTime=" + createTime +
                '}';
    }
}
