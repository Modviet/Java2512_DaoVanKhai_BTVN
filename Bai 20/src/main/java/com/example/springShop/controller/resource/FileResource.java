package com.example.springShop.controller.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
public class FileResource {

    @Value("${storage.upload-dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file){
        if(file.isEmpty()){
            return ResponseEntity.badRequest().body("File rong");
        }

        try {
            File directory = new File(uploadDir);
            if(!directory.exists()){
                directory.mkdirs();
            }

            String fileName = UUID.randomUUID().toString()+"_"+ file.getOriginalFilename();
            Path path = Paths.get(uploadDir + fileName);

            Files.write(path, file.getBytes());
            return ResponseEntity.ok("/api/files/preview"+ fileName);
        } catch (IOException e){
            return ResponseEntity.internalServerError().body("Loi khi luu file : "+ e.getMessage());
        }
    }

    @GetMapping("/preview/{fileName}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName){
        try {
            Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if(resource.exists()){
                String contentType = Files.probeContentType(filePath);
                if(contentType == null){
                    contentType ="application/octet-stream";
                }
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
