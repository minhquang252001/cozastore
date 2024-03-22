package com.example.cozastoreweb.service;

import com.example.cozastoreweb.dto.SizeDTO;
import com.example.cozastoreweb.entity.SizeEntity;
import com.example.cozastoreweb.repository.SizeRepository;
import com.example.cozastoreweb.service.imp.SizeServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SizeService implements SizeServiceIMP {
    @Autowired
    private SizeRepository sizeRepository;
    @Override
    public List<SizeDTO> findAll() {
        List<SizeEntity> list = sizeRepository.findAll();
        List<SizeDTO> dtoList = new ArrayList<>();
        for (SizeEntity items:list) {
            SizeDTO sizeDTO = new SizeDTO();
            sizeDTO.setId(items.getId());
            sizeDTO.setName(items.getName());
            dtoList.add(sizeDTO);
        }
        return dtoList;
    }
}
