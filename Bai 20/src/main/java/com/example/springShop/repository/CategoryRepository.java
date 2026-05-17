package com.example.springShop.repository;

import com.example.springShop.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    List<Category> findAll(String keyword,Integer parentId,int page , int size);

    long countAll(String keyword,Integer parentId);

    Optional<Category> findById(Integer id);

    Category save(Category category);

    int deleteById(Integer id);
}
