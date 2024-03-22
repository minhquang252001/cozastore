package com.example.cozastoreweb.repository;

import com.example.cozastoreweb.entity.ProductEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {

    List<ProductEntity> findAllById(int id);
}
