package com.example.cozastoreweb.repository;

import com.example.cozastoreweb.dto.UsersDTO;
import com.example.cozastoreweb.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<UsersEntity,Integer> {

    UsersEntity findByEmail(String email);
    Optional<UsersEntity> getByEmail(String email);
}
