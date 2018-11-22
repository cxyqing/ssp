package com.ex.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Table(name = "ad_category")
@Entity
public class Category {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "cate_name")
    private String cateName;

    @JoinTable(name = "ad_item_cate",joinColumns = {@JoinColumn(name = "cate_id",referencedColumnName = "id")},inverseJoinColumns = {
            @JoinColumn(name = "item_id",referencedColumnName = "id")})
    @ManyToMany
    private Set<Item> items = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
