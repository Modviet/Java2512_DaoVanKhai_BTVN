package com.example.springShop.controller.resource;

import com.example.springShop.dto.CategoryListItemDto;
import com.example.springShop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryResource {


    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryListItemDto>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getCategories(null,null, 0 ,1000));
    }
}
