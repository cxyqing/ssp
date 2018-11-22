package com.ex.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "ad_item")
@Entity
public class Item {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "item_name")
    private String itemName;
    @ManyToMany(mappedBy = "items")
    private Set<Category> cates = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Set<Category> getCates() {
        return cates;
    }

    public void setCates(Set<Category> cates) {
        this.cates = cates;
    }
}
