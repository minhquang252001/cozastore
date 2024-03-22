package com.example.cozastoreweb.repository;

import com.example.cozastoreweb.entity.ColorEntity;
import com.example.cozastoreweb.entity.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<SizeEntity,Integer> {
}
