package com.example.springShop.service.impl;

import com.example.springShop.dto.ProducResponseDto;
import com.example.springShop.dto.ProductImageDto;
import com.example.springShop.dto.ProductRequestDto;
import com.example.springShop.dto.SkuDto;
import com.example.springShop.entity.*;
import com.example.springShop.mapper.ProductMapper;
import com.example.springShop.repository.ColorRepository;
import com.example.springShop.repository.ProductRepository;
import com.example.springShop.repository.SizeRepository;
import com.example.springShop.service.ProductService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;

    @Override
    public Page<ProducResponseDto> getAllProducts(String keyword, Integer categoryId, String createdBy, Pageable pageable) {
        Specification<Product> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("deleted"),0));

            if(keyword != null && !keyword.isEmpty()){
                predicates.add(criteriaBuilder.like(root.get("name"), "%"+ keyword + "%"));
            }

            if(categoryId != null){
                predicates.add(criteriaBuilder.equal(root.get("category").get("id"),categoryId));
            }

            if(createdBy != null && !createdBy.isEmpty()){
                predicates.add(criteriaBuilder.equal(root.get("createdBy"),createdBy));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        return productRepository.findAll(spec, pageable).map(productMapper::toResponseDto);

    }

    @Override
    public Page<ProducResponseDto> getAllProducts2(String keyword, Integer categoryId, String createdBy, Pageable pageable) {
        if(keyword != null && !keyword.isEmpty()){
            keyword = "%"+ keyword + "%";
        } else {
            keyword = null;
        }

        if(createdBy != null && createdBy.isEmpty()){
            createdBy = null;
        }

        if(categoryId != null && categoryId <=0){
            categoryId = null;
        }
        return productRepository.getAllProductsNativeQuery(keyword, categoryId, createdBy, pageable).map(productMapper::toResponseDto);

    }

    @Override
    public ProducResponseDto getProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Khong tim tahy san pham voi id : "+id));
        return productMapper.toResponseDto(product);
    }

    @Override
    @Transactional
    public ProducResponseDto createProduct(ProductRequestDto requestDto) {
        Product product = productMapper.toEntity(requestDto);
        product.setDeleted((byte) 0);

        if(product.getCreatedBy() == null){
            product.setCreatedBy("ADMIN");
        }
        product.setUpdatedBy(product.getCreatedBy());

        final Product savedProduct = productRepository.save(product);

        saveRelatedEntities(savedProduct, requestDto);

        return productMapper.toResponseDto(savedProduct);
    }

    @Override
    @Transactional
    public ProducResponseDto updateProduct(Integer id, ProductRequestDto requestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Khong tim thay san pham voi id : "+ id));

        product.setName(requestDto.getName());
        product.setDescription(requestDto.getDescription());
        product.setMaterialInfo(requestDto.getMaterialInfo());
        product.setAvatar(requestDto.getAvatar());

        product.setUpdatedBy("ADMIN");

        if(requestDto.getCategoryId() != null){
            Category cat = new Category();
            cat.setId(requestDto.getCategoryId());
            product.setCategory(cat);
        }

        if(product.getSkus() != null) {
            product.getSkus().clear();
        } else {
            product.setSkus(new ArrayList<>());
        }

        if(product.getImages() != null){
            product.getImages().clear();
        } else {
            product.setImages(new ArrayList<>());
        }

        productRepository.saveAndFlush(product);

        saveRelatedEntities(product, requestDto);

        return productMapper.toResponseDto(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Integer id) {
          Product product = productRepository.findById(id)
                  .orElseThrow(() -> new RuntimeException("Khong tim thay san pham voi id : "+ id));
          product.setDeleted((byte) 1);
          productRepository.save(product);
    }

    private void saveRelatedEntities(Product product,ProductRequestDto requestDto){
        if(product.getSkus() == null)
            product.setSkus(new ArrayList<>());

        if(product.getImages() == null)
            product.setImages(new ArrayList<>());

        if(requestDto.getSkus() != null){
           for(SkuDto skuDto : requestDto.getSkus()){
               ProductSku sku = new ProductSku();
               sku.setProduct(product);
               sku.setSkuCode(skuDto.getSkuCode());
               sku.setOriginalPrice(skuDto.getOriginalPrice());
               sku.setSalePrice(skuDto.getSalePrice());
               sku.setStockQuantity(skuDto.getStockQuantity());

               Color color = colorRepository.findById(skuDto.getColorId()).orElse(null);
               Size size = sizeRepository.findById(skuDto.getSizeId()).orElse(null);
               sku.setColor(color);
               sku.setSize(size);
               product.getSkus().add(sku);
           }
        }

        if(requestDto.getImages() != null){
            for(ProductImageDto imgDto : requestDto.getImages()){
                ProductImage img = new ProductImage();
                img.setProduct(product);
                img.setImageUrl(imgDto.getImageUrl());
                img.setIsMain(imgDto.getIsMain());
                img.setSortOrder(imgDto.getSortOrder());

                Color color = colorRepository.findById(imgDto.getColorId()).orElse(null);
                img.setColor(color);
                product.getImages().add(img);
            }
        }
        productRepository.save(product);
    }
}
