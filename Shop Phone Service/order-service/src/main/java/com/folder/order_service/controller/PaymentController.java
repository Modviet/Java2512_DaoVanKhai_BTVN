package com.folder.order_service.controller;

import com.folder.order_service.dto.request.payment.CreatePaymentRequest;
import com.folder.order_service.dto.request.payment.UpdatePaymentStatusRequest;
import com.folder.order_service.dto.response.PaymentResponse;
import com.folder.order_service.exception.common.ApiResponse;
import com.folder.order_service.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/payments")
@RestController
public class PaymentController {

       private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<ApiResponse<PaymentResponse>> createPayment(
            @Valid @RequestBody CreatePaymentRequest request
    ) {

        log.info(
                "Creating payment for order: {}",
                request.getOrderId()
        );

        PaymentResponse response =
                paymentService.createPayment(request);

        return ResponseEntity.ok(
                ApiResponse.success(response)
        );
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<ApiResponse<PaymentResponse>> getPaymentById(
            @PathVariable UUID paymentId
    ) {

        log.info(
                "Getting payment: {}",
                paymentId
        );

        PaymentResponse response =
                paymentService.getPaymentById(paymentId);

        return ResponseEntity.ok(
                ApiResponse.success(response)
        );
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<ApiResponse<List<PaymentResponse>>> getPaymentsByOrderId(
            @PathVariable UUID orderId
    ) {

        log.info(
                "Getting payments for order: {}",
                orderId
        );

        List<PaymentResponse> response =
                paymentService.getPaymentByOrderId(orderId);

        return ResponseEntity.ok(
                ApiResponse.success(response)
        );
    }

    @PatchMapping("/{paymentId}/status")
    public ResponseEntity<ApiResponse<PaymentResponse>> updatePaymentStatus(
            @PathVariable UUID paymentId,
            @Valid @RequestBody UpdatePaymentStatusRequest request
    ) {

        log.info(
                "Updating payment {} status to {}",
                paymentId,
                request.getPaymentStatus()
        );

        PaymentResponse response =
                paymentService.updatePaymentStatus(
                        paymentId,
                        request
                );

        return ResponseEntity.ok(
                ApiResponse.success(response)
        );
    }
}
