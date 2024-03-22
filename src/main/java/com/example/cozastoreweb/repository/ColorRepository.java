package com.example.cozastoreweb.repository;

import com.example.cozastoreweb.entity.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<ColorEntity,Integer> {
}
