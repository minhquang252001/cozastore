package com.example.cozastoreweb.service.imp;

import com.example.cozastoreweb.dto.ProductDTO;
import com.example.cozastoreweb.entity.ProductEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductServiceIMP {

    ProductEntity save(MultipartFile file, String title, double price,int idCategory, String tag);

    ProductEntity update(MultipartFile file,int id, String title, double price, int idCategory, String tag);

    ProductEntity delete(int id);

    List<ProductDTO> getAll();

    List<ProductDTO> finByID(int id);
}
