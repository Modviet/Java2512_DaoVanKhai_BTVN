package com.folder.order_service.service;

import com.folder.order_service.dto.request.payment.CreatePaymentRequest;
import com.folder.order_service.dto.request.payment.UpdatePaymentStatusRequest;
import com.folder.order_service.dto.response.PaymentResponse;

import java.util.List;
import java.util.UUID;

public interface PaymentService {

       PaymentResponse createPayment(CreatePaymentRequest request);

       PaymentResponse getPaymentById(UUID paymentId);

       List<PaymentResponse> getPaymentByOrderId(UUID orderId);

       PaymentResponse updatePaymentStatus(UUID paymentId,
                                           UpdatePaymentStatusRequest request);
}
