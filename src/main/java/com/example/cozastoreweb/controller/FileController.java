package com.example.cozastoreweb.controller;

import com.example.cozastoreweb.service.imp.FileServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileServiceIMP fileServiceIMP;

    @GetMapping("/{filename}")
    public ResponseEntity<?> file(@PathVariable String filename){

        Resource resource = fileServiceIMP.load(filename);

        return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"").body(resource);
    }
}
