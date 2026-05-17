package com.example.springShop.service;

import com.example.springShop.dto.CategoryFormDto;
import com.example.springShop.dto.CategoryListItemDto;
import com.example.springShop.dto.CategoryTreeDto;

import java.util.List;

public interface CategoryService {

    List<CategoryListItemDto> getCategories(String keyword, Integer parentId, int page , int size);

    long countCategories(String keyword, Integer parentId);

    List<CategoryTreeDto> getCategoryTree(String keyword, Integer parentId);

    CategoryFormDto getCategoryForm(Integer id);

    void createCategory(CategoryFormDto dto);

    void updateCategory(Integer id, CategoryFormDto dto);

    void deleteCategory(Integer id);
}
