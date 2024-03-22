package com.example.cozastoreweb.service;

import com.example.cozastoreweb.service.imp.FileServiceIMP;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class FilesService implements FileServiceIMP {

    @Value("${file.path}")
    private Path root;

    @Override
    public void save(MultipartFile file) {
        try{
            if (!Files.exists(root)){
                Files.createDirectories(root);
            }                                                                        //Nếu tên file trùng thì ghi dè
            Files.copy(file.getInputStream(),root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            throw new RuntimeException("Lỗi save file " + e.getMessage());
        }
    }

    @Override
//    @PreAuthorize("hasRole('ROLE_ADMIN')") chỉ khi ADMIN mới được sử dụng phương thức này còn USER không được sử dụng
    public Resource load(String filename) {
        try{
            Path  file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable() ){
                return resource;
            }else {
                throw new RuntimeException("Cannot read file");
            }
        }catch (Exception e){
            throw new RuntimeException("Lỗi load file "+ e.getMessage());
        }
    }
}
