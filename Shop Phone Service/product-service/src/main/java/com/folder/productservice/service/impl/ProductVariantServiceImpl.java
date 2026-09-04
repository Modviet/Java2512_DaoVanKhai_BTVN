package com.folder.productservice.service.impl;

import com.folder.productservice.dto.request.CreateProductVariantRequest;
import com.folder.productservice.dto.request.UpdateProductVariantRequest;
import com.folder.productservice.dto.response.ProductVariantResponse;
import com.folder.productservice.entity.Product;
import com.folder.productservice.entity.ProductVariant;
import com.folder.productservice.exception.AppException;
import com.folder.productservice.exception.ErrorCode;
import com.folder.productservice.mapper.ProductVariantMapper;
import com.folder.productservice.repository.ProductRepository;
import com.folder.productservice.repository.ProductVariantRepository;
import com.folder.productservice.service.ProductVariantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ProductVariantServiceImpl implements ProductVariantService {

        private final ProductVariantRepository productVariantRepository;

        private final ProductRepository productRepository;

        private final ProductVariantMapper productVariantMapper;

    @Override
    public ProductVariantResponse create(CreateProductVariantRequest request) {

          log.info("Creating product variant with SKU : {}", request.getSku());

          Product product = productRepository.findById(request.getProductId())
                  .orElseThrow(()-> {

                      log.warn("Product not found : {}", request.getProductId());

                      return new AppException(
                              ErrorCode.NOT_FOUND,
                              "Product not found"
                      );
                  });

          if(productVariantRepository.existsBySku(request.getSku())){

              log.warn("SKU already exists :{} ", request.getSku());

              throw new AppException(
                      ErrorCode.ALREADY_EXISTS,
                      "SKU already exists"
              );
          }

        ProductVariant variant = productVariantMapper.toEntity(request);

        variant.setProduct(product);

        productVariantRepository.save(variant);

        log.info("Product variant creadted successfully : {}", variant.getId());

        return productVariantMapper.toResponse(variant);
    }

    @Override
    public ProductVariantResponse update(UUID id,
                                         UpdateProductVariantRequest request) {

           log.info("Updating product variant : {}", id);

           ProductVariant variant = productVariantRepository.findById(id)
                   .orElseThrow(()-> {

                       log.warn("Product variant not found : {}", id);

                       return new AppException(
                               ErrorCode.NOT_FOUND,
                               "Product variant not found"
                       );
                   });

           productVariantMapper.update(request, variant);

           productVariantRepository.save(variant);

           log.info("Product variant updated successfully : {}", id);

           return productVariantMapper.toResponse(variant);
    }

    @Override
    public void delete(UUID id) {

          log.info("Deleting product variant : {}", id);

          ProductVariant variant = productVariantRepository.findById(id)
                  .orElseThrow(() -> {

                      log.warn("Product variant not found : {}", id);

                      return new AppException(
                              ErrorCode.NOT_FOUND,
                              "Product variant not found"
                      );
                  });

          productVariantRepository.delete(variant);

          log.info("Product variant deleted successfully : {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductVariantResponse getById(UUID id) {

          log.info("Getting product variant : {}", id);

          ProductVariant variant = productVariantRepository.findById(id)
                  .orElseThrow(()-> {

                      log.warn("Product variant not found : {}", id);

                      return new AppException(
                              ErrorCode.NOT_FOUND,
                              "Product variant not found"
                      );
                  });

          return productVariantMapper.toResponse(variant);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductVariantResponse> getByProductId(UUID productId) {

           log.info("Getting variant of product : {}", productId);

           return productVariantRepository.findByProductId(productId)
                   .stream()
                   .map(productVariantMapper::toResponse)
                   .toList();
    }
}
