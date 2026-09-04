package com.folder.productservice.service.impl;

import com.folder.productservice.dto.request.CreateProductRequest;
import com.folder.productservice.dto.request.ProductFilterRequest;
import com.folder.productservice.dto.request.UpdateProductRequest;
import com.folder.productservice.dto.response.ProductResponse;
import com.folder.productservice.dto.response.ProductSummaryResponse;
import com.folder.productservice.entity.Brand;
import com.folder.productservice.entity.Category;
import com.folder.productservice.entity.Product;
import com.folder.productservice.exception.AppException;
import com.folder.productservice.exception.ErrorCode;
import com.folder.productservice.mapper.ProductMapper;
import com.folder.productservice.repository.BrandRepository;
import com.folder.productservice.repository.CategoryRepository;
import com.folder.productservice.repository.ProductRepository;
import com.folder.productservice.service.ProductService;
import com.folder.productservice.specification.ProductSepcification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

       private final ProductRepository productRepository;

       private final BrandRepository brandRepository;

       private final CategoryRepository categoryRepository;

       private final ProductMapper productMapper;


    @Override
    public ProductResponse create(CreateProductRequest request) {

           log.info("Creating product : {}", request.getName());

           if(productRepository.existsBySlug(request.getSlug())) {

               log.warn("Product slug alreadt exists : {}", request.getSlug());

               throw new AppException(
                       ErrorCode.NOT_FOUND,
                       "Product slug already exists"
               );
           }

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(()->
                        new AppException(
                                ErrorCode.NOT_FOUND,
                                "Brand not found"
                        ));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(()->
                        new AppException(
                                ErrorCode.NOT_FOUND,
                                "Category not found"
                        ));

        Product product = productMapper.toEntity(request);

        product.setBrand(brand);

        product.setCategory(category);

        productRepository.save(product);

        log.info("Product created successfully : {}", product.getId());

        return productMapper.toResponse(product);
    }

    @Override
    public ProductResponse update(UUID id,
                                  UpdateProductRequest request) {

           log.info("Updating product : {}", id);

           Product product = productRepository.findById(id)
                   .orElseThrow(()->
                           new AppException(
                                   ErrorCode.NOT_FOUND,
                                   "Product not found"
                           ));

           if(request.getBrandId() != null) {

               Brand brand = brandRepository.findById(request.getBrandId())
                       .orElseThrow(()->
                               new AppException(
                                       ErrorCode.NOT_FOUND,
                                       "Brand not found"
                               ));

               product.setBrand(brand);
           }

           if(request.getCategoryId() != null) {

               Category category = categoryRepository.findById(request.getCategoryId())
                       .orElseThrow(()->
                               new AppException(
                                       ErrorCode.NOT_FOUND,
                                       "Category not found"
                               ));

               product.setCategory(category);
           }

           productMapper.update(request, product);

           productRepository.save(product);

           log.info("Product updated successfully : {}", id);

           return productMapper.toResponse(product);
    }

    @Override
    public void delete(UUID id) {

           log.info("Deleting product : {}", id);

           Product product = productRepository.findById(id)
                   .orElseThrow(()->
                           new AppException(
                                   ErrorCode.NOT_FOUND,
                                   "Product not found"
                           ));

           productRepository.delete(product);

           log.info("Product deleted successfully : {}" , id);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getById(UUID id) {

          log.info("Getting product : {}" , id);

          Product product = productRepository.findById(id)
                  .orElseThrow(()->
                          new AppException(
                                  ErrorCode.NOT_FOUND,
                                  "Product not found"
                          ));

          return productMapper.toResponse(product);
    }

    @Override
    public Page<ProductSummaryResponse> getAll(ProductFilterRequest filter,
                                               Pageable pageable) {


          log.info("Getting products with filter : {}, page : {}, size : {}",
                  filter,
                  pageable.getPageNumber(),
                  pageable.getPageSize());

        Specification<Product> specification = ProductSepcification.filter(filter);

        return productRepository.findAll(specification, pageable)
                .map(productMapper::toSummaryResponse);
    }
}
