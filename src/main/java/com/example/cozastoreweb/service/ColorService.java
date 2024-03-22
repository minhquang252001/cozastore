package com.example.cozastoreweb.service;

import com.example.cozastoreweb.dto.ColorDTO;
import com.example.cozastoreweb.entity.ColorEntity;
import com.example.cozastoreweb.repository.ColorRepository;
import com.example.cozastoreweb.service.imp.ColorServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColorService implements ColorServiceIMP {
    @Autowired
    private ColorRepository colorRepository;
    @Override
    public List<ColorDTO> findAll() {
        List<ColorEntity> colorEntity = colorRepository.findAll();
        List<ColorDTO> dtoList = new ArrayList<>();
        for (ColorEntity items:colorEntity) {
            ColorDTO colorDTO = new ColorDTO();
            colorDTO.setId(items.getId());
            colorDTO.setName(items.getName());
            dtoList.add(colorDTO);
        }


        return dtoList;
    }
}
