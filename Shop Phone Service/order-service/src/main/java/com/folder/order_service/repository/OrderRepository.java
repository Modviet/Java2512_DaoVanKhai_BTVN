package com.folder.order_service.repository;

import com.folder.order_service.entity.Order;
import com.folder.order_service.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID>,
        JpaSpecificationExecutor<Order> {

       Optional<Order> findByOrderCode(String orderCode);

       boolean existsByOrderCode(String orderCode);

       List<Order> findByUserId(UUID userId);

       List<Order> findByUserIdAndStatus(UUID userId,
                                         OrderStatus status);
}
