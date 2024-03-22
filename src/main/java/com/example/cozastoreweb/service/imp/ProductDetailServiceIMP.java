package com.example.cozastoreweb.service.imp;

import com.example.cozastoreweb.dto.ProductDTO;
import com.example.cozastoreweb.dto.ProductdetailDTO;
import com.example.cozastoreweb.entity.ProductEntity;
import com.example.cozastoreweb.entity.ProductdetailEntity;

import java.util.List;

public interface ProductDetailServiceIMP {
    List<ProductdetailDTO> finAllById(int id);

    ProductdetailEntity save(int idcolor,int idproduct,int idsize,int quantity,String description);
}
