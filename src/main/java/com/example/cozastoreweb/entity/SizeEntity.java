package com.example.cozastoreweb.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "size")
public class SizeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "idSize")
    private List<ProductdetailEntity> listProductdetailSize;

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

    public List<ProductdetailEntity> getListProductdetailSize() {
        return listProductdetailSize;
    }

    public void setListProductdetailSize(List<ProductdetailEntity> listProductdetailSize) {
        this.listProductdetailSize = listProductdetailSize;
    }
}
