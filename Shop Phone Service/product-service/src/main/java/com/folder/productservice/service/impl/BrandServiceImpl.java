package com.folder.productservice.service.impl;

import com.folder.productservice.dto.request.CreateBrandRequest;
import com.folder.productservice.dto.request.UpdateBrandRequest;
import com.folder.productservice.dto.response.BrandResponse;
import com.folder.productservice.entity.Brand;
import com.folder.productservice.exception.AppException;
import com.folder.productservice.exception.ErrorCode;
import com.folder.productservice.mapper.BrandMapper;
import com.folder.productservice.repository.BrandRepository;
import com.folder.productservice.service.BrandService;
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
public class BrandServiceImpl implements BrandService {

      private final BrandRepository brandRepository;

      private final BrandMapper brandMapper;

    @Override
    public BrandResponse create(CreateBrandRequest request) {

        log.info("Creating brand: {}", request.getName());

        if(brandRepository.existsByName(request.getName())){
            log.warn("Brand already exists : {}", request.getName());

            throw new AppException(
                    ErrorCode.ALREADY_EXISTS,
                    "Brand already exists"
            );
        }

        if(brandRepository.existsBySlug(request.getSlug())) {
            log.warn("Brand slug already exists: {} ", request.getSlug());

            throw new AppException(
                    ErrorCode.ALREADY_EXISTS,
                    "Brand slug already exists"
            );
           }

            Brand brand = brandMapper.toEntity(request);

            brandRepository.save(brand);

            log.info("Brand create succesfully: {}", brand.getId());

            return brandMapper.toResponse(brand);

    }

    @Override
    public BrandResponse update(UUID id, UpdateBrandRequest request) {

         log.info("Updating brand : {}", id);

         Brand brand = brandRepository.findById(id)
                 .orElseThrow(()-> {

                     log.warn("Brand not found :{} ",id);

                     return new AppException(
                             ErrorCode.NOT_FOUND,
                             "Brand not found"
                     );
                 });

         brandMapper.update(request, brand);

         brandRepository.save(brand);

         log.info("Brand update succesfully : {}", id);

         return brandMapper.toResponse(brand);
    }

    @Override
    public void delete(UUID id) {

            log.info("Deleting brand : {}", id);

            Brand brand = brandRepository.findById(id)
                    .orElseThrow(()-> {

                        log.warn("Brand not found : ", id);

                        return new AppException(ErrorCode.NOT_FOUND,
                                "Brand not found");
                    });

            brandRepository.delete(brand);

            log.info("Brand deleted successfully : {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public BrandResponse getById(UUID id) {

          log.info("Search brand by ID : {}", id);

          Brand brand = brandRepository.findById(id)
                  .orElseThrow(()-> {

                      log.warn("Brand not found : {}",id);

                      return new AppException(
                              ErrorCode.NOT_FOUND,
                              "Brand not found");
                  });

          return brandMapper.toResponse(brand);

    }

    @Override
    @Transactional(readOnly = true)
    public List<BrandResponse> getAll() {

        log.info("Getting all brands.");

        return brandRepository.findAll()
                .stream()
                .map(brandMapper::toResponse)
                .toList();
    }
}
