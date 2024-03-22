package com.example.cozastoreweb.dto;

public class UsersDTO {
    private int id;
    private String email;
    private String password;
    private int idRole;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdRole() {
        return this.idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
}
