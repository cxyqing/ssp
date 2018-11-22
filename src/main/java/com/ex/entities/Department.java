package com.ex.entities;

import javax.persistence.*;

@Table(name = "ad_department")
@Entity
public class Department {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "dept_name")
    private String deptName;

    @JoinColumn(name = "mgr_id",unique = true)
    @OneToOne
    private Manager manager;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
