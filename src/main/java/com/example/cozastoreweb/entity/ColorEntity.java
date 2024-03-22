package com.example.cozastoreweb.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "color")
public class ColorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "idColor")
    private List<ProductdetailEntity> listProductdetailColor;

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

    public List<ProductdetailEntity> getListProductdetailColor() {
        return listProductdetailColor;
    }

    public void setListProductdetailColor(List<ProductdetailEntity> listProductdetailColor) {
        this.listProductdetailColor = listProductdetailColor;
    }
}
