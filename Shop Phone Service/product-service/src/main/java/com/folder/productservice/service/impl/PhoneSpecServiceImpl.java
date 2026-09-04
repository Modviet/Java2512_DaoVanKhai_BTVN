package com.folder.productservice.service.impl;

import com.folder.productservice.dto.request.CreatePhoneSpecRequest;
import com.folder.productservice.dto.request.UpdatePhoneSpecRequest;
import com.folder.productservice.dto.response.PhoneSpecResponse;
import com.folder.productservice.entity.PhoneSpecification;
import com.folder.productservice.entity.Product;
import com.folder.productservice.exception.AppException;
import com.folder.productservice.exception.ErrorCode;
import com.folder.productservice.mapper.PhoneSpecMapper;
import com.folder.productservice.repository.PhoneSpecRepository;
import com.folder.productservice.repository.ProductRepository;
import com.folder.productservice.service.PhoneSpecService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PhoneSpecServiceImpl implements PhoneSpecService {

       private final PhoneSpecRepository phoneSpecRepository;

       private final ProductRepository productRepository;

       private final PhoneSpecMapper phoneSpecMapper;

    @Override
    public PhoneSpecResponse create(CreatePhoneSpecRequest request) {

          log.info("Creating phone specification for product :{} ", request.getProductId());

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(()-> {

                    log.warn("Product not found : {}", request.getProductId());

                    return new AppException(ErrorCode.NOT_FOUND,
                            "Product not found");
                });

        if(phoneSpecRepository.existsByProductId(product.getId())) {

            log.warn("Phone specification already exists : {}", product.getId());

            throw new AppException(
                    ErrorCode.ALREADY_EXISTS,
                    "Phone specification already exists"
            );
        }

        PhoneSpecification phoneSpec = phoneSpecMapper.toEntity(request);

        phoneSpec.setProduct(product);

        phoneSpecRepository.save(phoneSpec);

        log.info("Phone specification created successfully.");

        return phoneSpecMapper.toResponse(phoneSpec);
    }

    @Override
    public PhoneSpecResponse update(UUID productId,
                                    UpdatePhoneSpecRequest request) {

           log.info("Updating phone specification for product: {}", productId);

           PhoneSpecification phoneSpec = phoneSpecRepository.findByProductId(productId)
                   .orElseThrow(()-> {

                       log.warn("Phone specification not found : {}", productId);

                       return new AppException(
                               ErrorCode.NOT_FOUND,
                               "Phone specification not found"
                       );
                   });

           phoneSpecMapper.update(request, phoneSpec);

           phoneSpecRepository.save(phoneSpec);

           log.info("Phone specification updated successfully.");

           return phoneSpecMapper.toResponse(phoneSpec);

    }

    @Override
    @Transactional(readOnly = true)
    public PhoneSpecResponse getByProductId(UUID productId) {

           log.info("Getting phone specification of product : {}", productId);

           PhoneSpecification phoneSpec = phoneSpecRepository.findByProductId(productId)
                   .orElseThrow(()-> {

                       log.warn("Phone specification not found : {}", productId);

                       return new AppException(
                               ErrorCode.NOT_FOUND,
                               "Phone specification not found"
                       );
                   });

           return phoneSpecMapper.toResponse(phoneSpec);
    }

}
