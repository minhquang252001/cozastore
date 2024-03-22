package com.example.cozastoreweb.controller;

import com.example.cozastoreweb.dto.CategoryDTO;
import com.example.cozastoreweb.payload.response.BaseResponse;
import com.example.cozastoreweb.service.imp.CategoryServiceIMP;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private Gson gson = new Gson();

    @Autowired
    private CategoryServiceIMP categoryServiceIMP;

    @GetMapping("")
    public ResponseEntity<?> getCategory(){
        List<CategoryDTO> list = categoryServiceIMP.getAllCategory();
        BaseResponse  baseResponse= new BaseResponse();
        baseResponse.setData(list);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
