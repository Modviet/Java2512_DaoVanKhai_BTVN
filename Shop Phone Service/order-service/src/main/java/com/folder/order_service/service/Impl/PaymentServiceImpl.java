package com.folder.order_service.service.Impl;

import com.folder.order_service.dto.request.payment.CreatePaymentRequest;
import com.folder.order_service.dto.request.payment.UpdatePaymentStatusRequest;
import com.folder.order_service.dto.response.PaymentResponse;
import com.folder.order_service.entity.Order;
import com.folder.order_service.entity.Payment;
import com.folder.order_service.enums.PaymentStatus;
import com.folder.order_service.exception.BusinessException;
import com.folder.order_service.exception.ResourceNotFoundException;
import com.folder.order_service.mapper.PaymentMapper;
import com.folder.order_service.repository.OrderRepository;
import com.folder.order_service.repository.PaymentRepository;
import com.folder.order_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

       private final PaymentRepository paymentRepository;

       private final OrderRepository orderRepository;

       private final PaymentMapper paymentMapper;

    @Override
    public PaymentResponse createPayment(CreatePaymentRequest request) {

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(()->
                        new ResourceNotFoundException("Order not found : "+ request.getOrderId()));

        if (order.getStatus().name()
                .equals("CANCELLEND")){

            throw new BusinessException("Cannot create payment for cancelled order");
        }

        Payment payment = Payment.builder()
                .order(order)
                .paymentMethod(request.getPaymentMethod())
                .paymentStatus(PaymentStatus.PENDING)
                .amount(order.getTotalPrice())
                .build();

        return paymentMapper.toResponse(paymentRepository.save(payment));
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponse getPaymentById(UUID paymentId) {

          Payment payment = paymentRepository.findById(paymentId)
                  .orElseThrow(()-> new ResourceNotFoundException("Payment not found : "+ paymentId));

          return paymentMapper.toResponse(payment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponse> getPaymentByOrderId(UUID orderId) {

           if (!orderRepository.existsById(orderId)){

               throw new ResourceNotFoundException("Order not found : "+ orderId);
           }

           return paymentRepository.findByOrderId(orderId)
                   .stream()
                   .map(paymentMapper::toResponse)
                   .toList();
    }

    @Override
    public PaymentResponse updatePaymentStatus(UUID paymentId,
                                               UpdatePaymentStatusRequest request) {

          Payment payment = paymentRepository.findById(paymentId)
                  .orElseThrow(()-> new ResourceNotFoundException("Payment not found : " + paymentId));

          PaymentStatus current = payment.getPaymentStatus();

          PaymentStatus next = request.getPaymentStatus();

           validatePaymentTransition(current, next);

           payment.setPaymentStatus(next);

           if (request.getTransactionCode() != null){
               payment.setTransactionCode(request.getTransactionCode());
           }

           if (next == PaymentStatus.SUCCESS){
               payment.setPaidAt(LocalDateTime.now());
           }

           return paymentMapper.toResponse(paymentRepository.save(payment));

    }

    private void validatePaymentTransition(
            PaymentStatus current,
            PaymentStatus next
    ) {

        if (current == next) {

            throw new BusinessException(
                    "Payment is already in this status"
            );
        }

        boolean valid =
                switch (current) {

                    case PENDING ->
                            next == PaymentStatus.SUCCESS
                                    || next == PaymentStatus.FAILED;

                    case FAILED ->
                            next == PaymentStatus.PENDING;

                    case SUCCESS ->
                            next == PaymentStatus.REFUNDED;

                    case REFUNDED -> false;
                };

        if (!valid) {

            throw new BusinessException(
                    "Invalid payment status transition: "
                            + current
                            + " -> "
                            + next
            );
        }
    }
}
