package com.folder.productservice.specification;


import com.folder.productservice.dto.request.ProductFilterRequest;
import com.folder.productservice.entity.Product;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public final class ProductSepcification {

      private ProductSepcification() {
      }

      public static Specification<Product> filter(
              ProductFilterRequest filter) {

          return ((root, query, cb) -> {

              List<Predicate> predicates = new ArrayList<>();

              if (filter.getKeyword() != null && !filter.getKeyword().isBlank()){

                  String keyword = "%" + filter.getKeyword().trim().toLowerCase() + "%";

                  Predicate namePredicate = cb.like(cb.lower(root.get("name")),
                          keyword);

                  Predicate slugPredicate = cb.like(
                          cb.lower(root.get("slug")),
                                  keyword);

                  predicates.add(cb.or(namePredicate, slugPredicate));

              }

              if(filter.getBrandId() != null ){

                  predicates.add(cb.equal(
                          root.get("brand").get("id"),
                          filter.getBrandId()
                  ));
              }

              if(filter.getCategoryId() != null) {

                  predicates.add(cb.equal(root.get("category").get("id"),
                          filter.getCategoryId()));
              }

              if (filter.getStatus() != null){

                  predicates.add(cb.equal(
                          root.get("status"),
                          filter.getStatus()
                  ));
              }

              if(filter.getMinPrice() != null || filter.getMaxPrice() != null)
              {

                  Join<Product, ?> variantJoin = root.join("variants", JoinType.INNER);

                  if (filter.getMinPrice() != null){

                      predicates.add(cb.greaterThanOrEqualTo(
                              variantJoin.get("price"),
                              filter.getMinPrice()
                      ));
                  }

                  if(filter.getMaxPrice() != null){

                      predicates.add(cb.lessThanOrEqualTo(
                              variantJoin.get("price"),
                              filter.getMaxPrice()
                      ));
                  }

                  query.distinct(true);
              }

              return cb.and(predicates.toArray(new Predicate[0]));

          });
      }
}
