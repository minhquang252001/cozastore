package com.example.cozastoreweb.service.imp;

import com.example.cozastoreweb.dto.SizeDTO;
import com.example.cozastoreweb.entity.SizeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SizeServiceIMP {
    List<SizeDTO> findAll();
}
