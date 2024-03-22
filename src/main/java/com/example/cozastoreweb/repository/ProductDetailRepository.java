package com.example.cozastoreweb.repository;

import com.example.cozastoreweb.entity.ProductdetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductDetailRepository extends JpaRepository<ProductdetailEntity,Integer> {
    List<ProductdetailEntity> findAllById(int id);
}
