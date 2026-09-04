package com.folder.productservice.service.impl;

import com.folder.productservice.dto.request.CreateCategoryRequest;
import com.folder.productservice.dto.request.UpdateCategoryRequest;
import com.folder.productservice.dto.response.CategoryResponse;
import com.folder.productservice.entity.Category;
import com.folder.productservice.exception.AppException;
import com.folder.productservice.exception.ErrorCode;
import com.folder.productservice.mapper.CategoryMapper;
import com.folder.productservice.repository.CategoryRepository;
import com.folder.productservice.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

       private final CategoryRepository categoryRepository;

       private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse create(CreateCategoryRequest request) {

           log.info("Creating category :{}", request.getName());

           if(categoryRepository.existsByName(request.getName())) {

               log.warn("Category already exists: {}", request.getName());

               throw new AppException(
                       ErrorCode.ALREADY_EXISTS,
                       "Category already exists"
               );
           }

           if(categoryRepository.existsBySlug(request.getSlug())) {

               log.warn("Category slug already exists : {}", request.getSlug());

               throw new AppException(
                       ErrorCode.ALREADY_EXISTS,
                       "Category slug already exists"
               );
           }

        Category category = categoryMapper.toEntity(request);

        categoryRepository.save(category);

        log.info("Category created successfully: {}", category.getId());

        return categoryMapper.toResponse(category);
    }

    @Override
    public CategoryResponse update(UUID id,
                                   UpdateCategoryRequest request) {

          log.info("Updating category : {}", id);

          Category category = categoryRepository.findById(id)
                  .orElseThrow(()-> {

                      log.warn("Category not found : {}", id);

                      return new AppException(
                              ErrorCode.NOT_FOUND,
                              "Category not found"
                      );
                  });

             categoryMapper.update(request, category);

             categoryRepository.save(category);

             log.info("Category updated successfully: {}", id);

             return categoryMapper.toResponse(category);

    }

    @Override
    public void delete(UUID id) {

          log.info("Deleting category : {}", id);

          Category category = categoryRepository.findById(id)
                  .orElseThrow(()-> {

                      log.warn("Category not found : {}", id);

                      return new AppException(
                              ErrorCode.NOT_FOUND,
                              "Category not found"
                      );
                  });

          categoryRepository.delete(category);

          log.info("Category deleted successfully: {} ", id);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse getById(UUID id) {

           log.info("Search category : {}", id);

           Category category = categoryRepository.findById(id)
                   .orElseThrow(()-> {

                       log.warn("Category not found : {}", id);

                       return new AppException(
                            ErrorCode.NOT_FOUND,
                            "Category not found"
                       );
                   });

           return categoryMapper.toResponse(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> getAll() {

          log.info("Getting all categories");

          return categoryRepository.findAll()
                  .stream()
                  .map(categoryMapper::toResponse)
                  .toList();
    }
}
