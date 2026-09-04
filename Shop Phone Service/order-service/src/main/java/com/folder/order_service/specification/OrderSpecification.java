package com.folder.order_service.specification;

import com.folder.order_service.dto.request.order.OrderFilter;
import com.folder.order_service.entity.Order;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public final class OrderSpecification {

       private OrderSpecification(){

       }

       public static Specification<Order> filter(OrderFilter filter){

           return ((root, query, criteriaBuilder) -> {

               List<Predicate> predicates = new ArrayList<>();

               if (filter == null){
                   return criteriaBuilder.conjunction();
               }

               if (filter.getUserId() != null){
                   predicates.add(
                           criteriaBuilder.equal(
                                   root.get("userId"),
                                   filter.getUserId()
                           )
                   );
               }

               if (StringUtils.hasText(filter.getOrderCode())){
                   predicates.add(
                           criteriaBuilder.like(
                              criteriaBuilder.lower(
                                   root.get("orderCode")
                           ),
                           "%" + filter.getOrderCode().trim()
                                   .toLowerCase() + "%"
                       )
                   );
               }

               if (filter.getStatus() != null){
                   predicates.add(
                           criteriaBuilder.equal(
                                   root.get("status"),
                                   filter.getStatus()
                           )
                   );
               }

               if (filter.getMinPrice() != null){
                   predicates.add(
                           criteriaBuilder.greaterThanOrEqualTo(
                                   root.get("totalPrice"),
                                   filter.getMinPrice()
                           )
                   );
               }

               if (filter.getMaxPrice() != null){
                   predicates.add(
                           criteriaBuilder.lessThanOrEqualTo(
                                   root.get("totalPrice"),
                                   filter.getMaxPrice()
                           )
                   );
               }

               if (filter.getFromDate() != null){
                   predicates.add(
                           criteriaBuilder.greaterThanOrEqualTo(
                                   root.get("createdAt"),
                                   filter.getFromDate()
                           )
                   );
               }

               if (filter.getToDate() != null){
                   predicates.add(
                           criteriaBuilder.lessThanOrEqualTo(
                                   root.get("createdAt"),
                                   filter.getToDate()
                           )
                   );
               }

               return criteriaBuilder.and(
                       predicates.toArray(new Predicate[0])
               );
           });
       }
}
