package com.folder.productservice.service.impl;

import com.folder.productservice.dto.request.CreateReviewRequest;
import com.folder.productservice.dto.request.UpdateReviewRequest;
import com.folder.productservice.dto.response.ReviewResponse;
import com.folder.productservice.entity.Product;
import com.folder.productservice.entity.Review;
import com.folder.productservice.enums.ReviewStatus;
import com.folder.productservice.exception.AppException;
import com.folder.productservice.exception.ErrorCode;
import com.folder.productservice.mapper.ReviewMapper;
import com.folder.productservice.repository.ProductRepository;
import com.folder.productservice.repository.ReviewRepository;
import com.folder.productservice.service.ReviewService;
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
public class ReviewServiceImpl implements ReviewService {

            private final ReviewRepository reviewRepository;

            private final ProductRepository productRepository;

            private final ReviewMapper reviewMapper;

    @Override
    public ReviewResponse create(CreateReviewRequest request,
                                 UUID userId) {

           log.info("Creating review for prodduct : {}", request.getProductId());

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(()-> {

                    log.warn("Product not found : {}", request.getProductId());

                    return new AppException(
                            ErrorCode.NOT_FOUND,
                            "Product not found"
                    );
                });

        if(reviewRepository.existsByProductIdAndUserId(
                product.getId(), userId
        )) {

            log.warn("User {} already reviewed product {}", userId, product.getId());

            throw new AppException(
                    ErrorCode.ALREADY_EXISTS,
                    "You have already reviewed this product"
            );
        }

        Review review = reviewMapper.toEntity(request);

        review.setProduct(product);
        review.setUserId(userId);
        review.setStatus(ReviewStatus.VISIBLE);

        reviewRepository.save(review);

        log.info("Reviwe created successfully : {}", review.getId());

        return reviewMapper.toResponse(review);
    }

    @Override
    public ReviewResponse update(UUID id,
                                 UpdateReviewRequest request,
                                 UUID userId) {

          log.info("Updating review : {}", id);

          Review review = reviewRepository.findById(id)
                  .orElseThrow(()-> {

                      log.warn("Review not found : {}", id);

                      return new AppException(
                              ErrorCode.NOT_FOUND,
                              "Review not found"
                      );
                  });

          if(!review.getUserId().equals(userId)) {

              log.warn("User {} is not owner of reivew {} ", userId, id);

              throw new AppException(
                      ErrorCode.FORBIDDEN,
                      "Permission denied"
              );
          }

          reviewMapper.update(request, review);

          reviewRepository.save(review);

          log.info("Review updated successfully : {}", id);

          return reviewMapper.toResponse(review);
    }

    @Override
    public void delete(UUID id,
                       UUID userId) {

           log.info("Deleting review : {}" , id);

           Review review = reviewRepository.findById(id)
                   .orElseThrow(()-> {

                       log.warn("Review not found : {}" ,id);

                       return new AppException(
                               ErrorCode.NOT_FOUND,
                               "Review not found"
                       );
                   });

           if(!review.getUserId().equals(userId)) {

               log.warn("User {} is not owner of review {}", userId, id);

               throw new AppException(
                       ErrorCode.FORBIDDEN,
                       "Permission denied"
               );
           }

           reviewRepository.delete(review);

           log.info("Review deleted successfully : {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public ReviewResponse getById(UUID id) {

         log.info("Getting review : {}", id);

         Review review = reviewRepository.findById(id)
                 .orElseThrow(()->
                         new AppException(
                                 ErrorCode.NOT_FOUND,
                                 "Review not found"
                         ));

         return reviewMapper.toResponse(review);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponse> getByProductId(UUID productId) {

           log.info("Getting reviews of product : {}", productId);

           return reviewRepository.findByProductId(productId)
                   .stream()
                   .map(reviewMapper::toResponse)
                   .toList();
    }
}
