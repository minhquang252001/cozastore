package com.example.cozastoreweb.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;


@Entity(name = "category")
public class CategoryEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "categoryEntity")
    private List<ProductEntity> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductEntity> getList() {
        return list;
    }

    public void setList(List<ProductEntity> list) {
        this.list = list;
    }
}
