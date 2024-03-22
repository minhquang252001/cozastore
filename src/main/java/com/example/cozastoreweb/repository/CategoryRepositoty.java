package com.example.cozastoreweb.repository;

import com.example.cozastoreweb.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositoty extends JpaRepository<CategoryEntity,Integer> {
}
