package com.example.cozastoreweb.service;

import com.example.cozastoreweb.dto.ProductdetailDTO;
import com.example.cozastoreweb.entity.ColorEntity;
import com.example.cozastoreweb.entity.ProductEntity;
import com.example.cozastoreweb.entity.ProductdetailEntity;
import com.example.cozastoreweb.entity.SizeEntity;
import com.example.cozastoreweb.exception.ProductNotfoundException;
import com.example.cozastoreweb.repository.ProductDetailRepository;
import com.example.cozastoreweb.service.imp.ProductDetailServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDetailService implements ProductDetailServiceIMP {
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Override
    public List<ProductdetailDTO> finAllById(int id) {
        List<ProductdetailEntity> list = productDetailRepository.findAllById(id);
        List<ProductdetailDTO> dtoList = new ArrayList<>();
        for (ProductdetailEntity items:list) {
            ProductdetailDTO productdetailDTO = new ProductdetailDTO();
            productdetailDTO.setIdProductDetail(items.getId());

            ColorEntity colorEntity = new ColorEntity();
            colorEntity.setId(items.getIdColor().getId());
            productdetailDTO.setIdColor(colorEntity.getId());

            ProductEntity productEntity = new ProductEntity();
            productEntity.setId(items.getIdProduct().getId());
            productdetailDTO.setIdProduct(productEntity.getId());

            SizeEntity sizeEntity= new SizeEntity();
            sizeEntity.setId(items.getIdSize().getId());
            productdetailDTO.setIdSize(sizeEntity.getId());
            productdetailDTO.setQuantity(items.getQuantity());
            productdetailDTO.setDescription(items.getDescription());
            dtoList.add(productdetailDTO);
        }

        return dtoList;
    }

    @Override
    public ProductdetailEntity save( int idcolor, int idproduct, int idsize, int quantity, String description) {
        ProductdetailEntity productdetailEntity = new ProductdetailEntity();


        ColorEntity colorEntity = new ColorEntity();
        colorEntity.setId(idcolor);
        productdetailEntity.setIdColor(colorEntity);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(idproduct);
        productEntity.getImages();
        productEntity.getPrice();
        productEntity.getTags();
        productdetailEntity.setIdProduct(productEntity);

        SizeEntity sizeEntity = new SizeEntity();
        sizeEntity.setId(idsize);
        productdetailEntity.setIdSize(sizeEntity);
        productdetailEntity.setQuantity(quantity);
        productdetailEntity.setDescription(description);

        return productDetailRepository.save(productdetailEntity);


    }
}
