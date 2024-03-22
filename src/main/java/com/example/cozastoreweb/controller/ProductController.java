package com.example.cozastoreweb.controller;

import com.example.cozastoreweb.dto.ProductDTO;
import com.example.cozastoreweb.entity.ColorEntity;
import com.example.cozastoreweb.entity.ProductEntity;
import com.example.cozastoreweb.entity.SizeEntity;
import com.example.cozastoreweb.exception.ProductNotfoundException;
import com.example.cozastoreweb.payload.response.BaseResponse;
import com.example.cozastoreweb.service.imp.ColorServiceIMP;
import com.example.cozastoreweb.service.imp.FileServiceIMP;
import com.example.cozastoreweb.service.imp.ProductServiceIMP;
import com.example.cozastoreweb.service.imp.SizeServiceIMP;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductServiceIMP productServiceIMP;

    @Autowired
    private ColorServiceIMP colorServiceIMP;

    @Autowired
    private SizeServiceIMP sizeServiceIMP;

    private Gson gson = new Gson();

    @Autowired
    private FileServiceIMP fileServiceIMP;

    @GetMapping("")
    public ResponseEntity<?> fillAll(){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(productServiceIMP.getAll());

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/files/{filename}")
    public ResponseEntity<?> loadFile(@PathVariable String filename){
        Resource resource = fileServiceIMP.load(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"").body(resource);
    }

    @PostMapping("")
    public ResponseEntity<?> inserProduct(
            @RequestParam MultipartFile file,
            @RequestParam String title,
            @RequestParam double price,
            @RequestParam int idCategory,
            @RequestParam(required = false) String tags
    ){
        ProductEntity productEntity = productServiceIMP.save(file,title,price,idCategory,tags);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(productEntity);

        logger.info("Insert Success Product " + file);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(
            @RequestParam MultipartFile file,
            @RequestParam int id,
            @RequestParam String title,
            @RequestParam double price,
            @RequestParam int idCatogery,
            @RequestParam(required = false) String tags
    ){
        ProductEntity productEntity = productServiceIMP.update(file,id,title,price,idCatogery,tags);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(productEntity);

        logger.info("Update Success Product " + id);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
            ProductEntity productEntity = productServiceIMP.delete(id);
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("delete Success Product");
            baseResponse.setData(id);

            logger.info("delete Success Product id: " + id);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    @GetMapping("/demo/{id}")
    public ResponseEntity<?> finbyId(@PathVariable int id){


        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(productServiceIMP.finByID(id));
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    @PostMapping("/test")
    public ResponseEntity<?> test(){
        return new ResponseEntity<>("demo nhỏ tí",HttpStatus.OK);
    }

}
