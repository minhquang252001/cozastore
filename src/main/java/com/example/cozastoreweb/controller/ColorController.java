package com.example.cozastoreweb.controller;

import com.example.cozastoreweb.payload.response.BaseResponse;
import com.example.cozastoreweb.service.imp.ColorServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/color")
public class ColorController {

    @Autowired
    private ColorServiceIMP colorServiceIMP;

    @GetMapping("")
    public ResponseEntity<?> findAll(){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(colorServiceIMP.findAll());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
