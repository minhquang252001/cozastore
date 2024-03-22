package com.example.cozastoreweb.service;

import com.example.cozastoreweb.dto.CategoryDTO;
import com.example.cozastoreweb.entity.CategoryEntity;
import com.example.cozastoreweb.repository.CategoryRepositoty;
import com.example.cozastoreweb.service.imp.CategoryServiceIMP;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class CategorySevrice implements CategoryServiceIMP {

    @Autowired
    private CategoryRepositoty categoryRepositoty;

    @Autowired
    private RedisTemplate redisTemplate;

    private Gson gson = new Gson();

    @Override
    public List<CategoryDTO> getAllCategory() {

        List<CategoryDTO> categoryDTO = new ArrayList<>();
                if(redisTemplate.hasKey("categorydemo")){
                    String data = redisTemplate.opsForValue().get("categorydemo").toString();
                    Type listType = new TypeToken<List<CategoryDTO>>(){}.getType();
                    categoryDTO = gson.fromJson(data,listType);
                    System.out.println("em có catching   ");
                }else {
                    System.out.println("em chưa có catching   ");
                    List<CategoryEntity> list = categoryRepositoty.findAll();
                    for (CategoryEntity entity : list) {
                        CategoryDTO dto = new CategoryDTO();
                        dto.setId(entity.getId());
                        dto.setName(entity.getName());
                        categoryDTO.add(dto);
                    }
                    String data = gson.toJson(categoryDTO);
                    redisTemplate.opsForValue().set("categorydemo", data);
                    redisTemplate.expire("categorydemo",8, TimeUnit.HOURS);
                }

        return categoryDTO;
    }
}
