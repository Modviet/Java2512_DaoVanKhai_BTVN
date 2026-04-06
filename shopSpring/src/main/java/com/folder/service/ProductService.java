package com.folder.service;

import com.folder.model.Product;
import com.folder.repository.ProductRepository;

import java.util.List;

public class ProductService {

    private ProductRepository productRepository;

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Integer id){
        return productRepository.findById(id);
    }
}
