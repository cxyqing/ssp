package com.ex.entities;

import javax.persistence.*;

@Table(name = "ad_manager")
@Entity
public class Manager {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;

//    @Transient
//    @JoinColumn(name = "mgr_id")
    @OneToOne(mappedBy = "manager",fetch = FetchType.LAZY)
    private Department department;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
