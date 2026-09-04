package com.folder.productservice.service.impl;

import com.folder.productservice.dto.request.CreateProductImageRequest;
import com.folder.productservice.dto.request.UpdateProductImageRequest;
import com.folder.productservice.dto.response.ProductImageResponse;
import com.folder.productservice.entity.Product;
import com.folder.productservice.entity.ProductImage;
import com.folder.productservice.exception.AppException;
import com.folder.productservice.exception.ErrorCode;
import com.folder.productservice.mapper.ProductImageMapper;
import com.folder.productservice.repository.ProductImageRepository;
import com.folder.productservice.repository.ProductRepository;
import com.folder.productservice.service.ProductImageService;
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
public class ProductImageServiceImpl implements ProductImageService {

       private final ProductImageRepository imageRepository;

       private final ProductRepository productRepository;

       private final ProductImageMapper imageMapper;


    @Override
    public ProductImageResponse create(CreateProductImageRequest request) {

          log.info("Creating image for product : {}", request.getProductId());

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(()-> {

                    log.warn("Product not found : {}", request.getProductId());

                    return new AppException(
                            ErrorCode.NOT_FOUND,
                            "Product not found"
                    );
                });

        if(Boolean.TRUE.equals(request.getIsThumbnail())) {

            imageRepository.findByProductIdAndIsThumbnailTrue(product.getId())
                    .ifPresent(image -> image.setIsThumbnail(false));
        }

        ProductImage image = imageMapper.toEntity(request);

        image.setProduct(product);

        imageRepository.save(image);

        log.info("Product image created successfully : {}", image.getId());

        return imageMapper.toResponse(image);

    }

    @Override
    public ProductImageResponse update(UUID id,
                                       UpdateProductImageRequest request) {

           log.info("Updating product image : {}", id);

           ProductImage image = imageRepository.findById(id)
                   .orElseThrow(()-> {

                       log.warn("Product image not found : {}",id);

                       return new AppException(
                               ErrorCode.NOT_FOUND,
                               "Product image not found"
                       );
                   });

           if(Boolean.TRUE.equals(request.getIsThumbnail())) {

               imageRepository
                       .findByProductIdAndIsThumbnailTrue(image.getProduct().getId())
                       .ifPresent(oldImage -> {

                           if(!oldImage.getId().equals(image.getId())) {
                               oldImage.setIsThumbnail(false);
                           }
                       });
           }

           imageMapper.update(request, image);

           imageRepository.save(image);

           log.info("Product image updated successfully : {}", id);

           return imageMapper.toResponse(image);
    }

    @Override
    public void delete(UUID id) {

           log.info("Deleting product image : {}", id);

           ProductImage image = imageRepository.findById(id)
                   .orElseThrow(()-> {

                       log.warn("Prodct image not found : {}", id);

                       return new AppException(
                               ErrorCode.NOT_FOUND,
                               "Product image not found"
                       );
                   });

           imageRepository.delete(image);

           log.info("Product image deleted successfully : {}", id);

    }

    @Override
    @Transactional(readOnly = true)
    public ProductImageResponse getById(UUID id) {

           log.info("Getting product image : {}", id);

           ProductImage image = imageRepository.findById(id)
                   .orElseThrow(()-> {

                       log.warn("Product image not found : {}", id);

                       return new AppException(
                               ErrorCode.NOT_FOUND,
                               "Product image not found"
                       );
                   });

           return imageMapper.toResponse(image);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductImageResponse> getByProductId(UUID productId) {

          log.info("Getting imgae of product : {}" , productId);

          return imageRepository.findByProductId(productId)
                  .stream()
                  .map(imageMapper::toResponse)
                  .toList();
    }
}
