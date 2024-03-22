package com.example.cozastoreweb.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role")
    private List<UsersEntity> listUser;

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

    public List<UsersEntity> getListUser() {
        return listUser;
    }

    public void setListUser(List<UsersEntity> listUser) {
        this.listUser = listUser;
    }
}
