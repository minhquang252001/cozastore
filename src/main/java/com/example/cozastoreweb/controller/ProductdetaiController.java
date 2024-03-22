package com.example.cozastoreweb.controller;

import com.example.cozastoreweb.dto.ProductdetailDTO;
import com.example.cozastoreweb.entity.ProductdetailEntity;
import com.example.cozastoreweb.exception.ProductNotfoundException;
import com.example.cozastoreweb.payload.response.BaseResponse;
import com.example.cozastoreweb.service.imp.ProductDetailServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/product-detail")
public class ProductdetaiController {

    @Autowired
    private ProductDetailServiceIMP productDetailServiceIMP;

    @GetMapping("/{id}")
    public ResponseEntity<?> finAll(@PathVariable int id){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(productDetailServiceIMP.finAllById(id));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/demo")
    public  String hello(){

        return "HelloWort";
    }

    @PostMapping("")
    public ResponseEntity<?> save(
            @RequestParam int idColor,
            @RequestParam int idProduct,
            @RequestParam int idSize,
            @RequestParam int quantity,
            @RequestParam String description
    ){

            ProductdetailEntity productdetailEntity= productDetailServiceIMP.save(idColor,idProduct,idSize,quantity,description);
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setData(productdetailEntity);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
}
