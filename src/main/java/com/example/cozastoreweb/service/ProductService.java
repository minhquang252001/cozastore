package com.example.cozastoreweb.service;

import com.example.cozastoreweb.dto.ProductDTO;
import com.example.cozastoreweb.entity.CategoryEntity;
import com.example.cozastoreweb.entity.ProductEntity;
import com.example.cozastoreweb.exception.ProductNotfoundException;
import com.example.cozastoreweb.repository.ProductRepository;
import com.example.cozastoreweb.service.imp.FileServiceIMP;
import com.example.cozastoreweb.service.imp.ProductServiceIMP;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductServiceIMP {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FileServiceIMP fileServiceIMP;

    @Autowired
    private HttpServletRequest request;

    //@Transactional: dùng để coi bên trong có đúng hay không nếu exception thì trong try sẽ không chạy.
    // và chỉ hoạt động RuntimeException
//    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Transactional
    @Override
    public ProductEntity save(MultipartFile file, String title, double price,int idCategory, String tag) {
        fileServiceIMP.save(file);
        ProductEntity productEntity = new ProductEntity();
        productEntity.setImages(file.getOriginalFilename());
        productEntity.setTitle(title);
        productEntity.setPrice(price);
        productEntity.setTags(tag);

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(idCategory);
        productEntity.setCategoryEntity(categoryEntity);

        try {
            productRepository.save(productEntity);
        }catch (Exception e){
            throw new ProductNotfoundException("lỗi inser product ");
        }

        return productEntity;
    }

    @Override
    public ProductEntity update(MultipartFile file,int id , String title, double price, int idCategory, String tag) {
        fileServiceIMP.save(file);
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(id);
        productEntity.setImages(file.getOriginalFilename());
        productEntity.setTitle(title);
        productEntity.setPrice(price);
        productEntity.setTags(tag != null ?  tag : "");

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(idCategory);
        productEntity.setCategoryEntity(categoryEntity);

        productRepository.save(productEntity);
        return productEntity;
    }

    @Override
    public ProductEntity delete(int id) {
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);
        if(optionalProductEntity.isPresent()){
            ProductEntity productEntity = optionalProductEntity.get();
            productRepository.delete(productEntity);
        }else {
            throw new RuntimeException("id không có trong database");
        }
        return null;
    }
    //Dùng Get Dynamic domain name: đẻ xét đường dẫn của images.
    @Override
    public List<ProductDTO> getAll() {
        String domain = request.getRequestURL().toString();
        String conTextPath= request.getContextPath();
        String dynamicDomain = domain.replace(conTextPath,"");

        List<ProductEntity> listFindAllProduct = productRepository.findAll();
        List<ProductDTO> dtoList = new ArrayList<>();
        for (ProductEntity items: listFindAllProduct) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(items.getId());
            productDTO.setImages(dynamicDomain + "/files/" + items.getImages());
            productDTO.setPrice(items.getPrice());
            productDTO.setTitle(items.getTitle());
            productDTO.setTags(items.getTags());
            productDTO.setIdCategory(items.getCategoryEntity().getId());

            dtoList.add(productDTO);
        }

        return dtoList;
    }

    @Override
    public List<ProductDTO> finByID(int id) {
        String domain = request.getRequestURL().toString();
        String conTextPath= request.getContextPath();
        String dynamicDomain = domain.replace(conTextPath,"");

        List<ProductEntity> list = productRepository.findAllById(id);
        List<ProductDTO> dtoList = new ArrayList<>();
        // In ra thông tin sản phẩm
        for (ProductEntity product : list) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setImages("http://localhost:8080/product" + "/files/" + product.getImages());
            productDTO.setPrice(product.getPrice());
            productDTO.setTitle(product.getTitle());
            productDTO.setTags(product.getTags());
            productDTO.getQuantity();
            productDTO.setIdCategory(product.getCategoryEntity().getId());
           dtoList.add(productDTO);
        }

        return  dtoList;
    }


}
