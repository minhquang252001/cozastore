package com.example.cozastoreweb.service.imp;

import com.example.cozastoreweb.dto.ColorDTO;
import com.example.cozastoreweb.entity.ColorEntity;

import java.util.List;

public interface ColorServiceIMP {
    List<ColorDTO> findAll();
}
