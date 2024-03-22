package com.example.cozastoreweb.service.imp;

import com.example.cozastoreweb.dto.UsersDTO;
import com.example.cozastoreweb.entity.UsersEntity;

import java.util.Optional;

public interface LoginServiceIMP {

    UsersEntity checklogin(String username,String password);

    Boolean save(String email,String password,String password2);

    Boolean update(String email,String password,String password1,String password2);

    Optional<UsersEntity> checkEmail(String email);

    UsersDTO findByEmail(String email);



}
