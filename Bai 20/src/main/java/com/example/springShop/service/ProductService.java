package com.example.springShop.service;

import com.example.springShop.dto.ProducResponseDto;
import com.example.springShop.dto.ProductRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProducResponseDto> getAllProducts(String keyword, Integer categoryId, String createdBy, Pageable pageable);
    Page<ProducResponseDto> getAllProducts2(String keyword, Integer categoryId, String createdBy, Pageable pageable);
    ProducResponseDto getProductById(Integer id);
    ProducResponseDto createProduct(ProductRequestDto requestDto);
    ProducResponseDto updateProduct(Integer id, ProductRequestDto requestDto);
    void deleteProduct(Integer id);

}
