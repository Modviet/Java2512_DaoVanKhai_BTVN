package com.folder.order_service.repository;

import com.folder.order_service.entity.Payment;
import com.folder.order_service.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

         List<Payment> findByOrderId(UUID orderId);

         List<Payment> findByPaymentStatus(PaymentStatus paymentStatus);
}
