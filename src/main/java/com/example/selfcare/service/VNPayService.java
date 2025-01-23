package com.example.selfcare.service;

import com.example.selfcare.configuration.VnpayConfig;
import com.example.selfcare.dto.PaymentDTO;
import com.example.selfcare.utils.VNPayUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class VNPayService {
    private final VnpayConfig vnpayConfig;
    public PaymentDTO.VNPayResponse createVnPayPayment(int price,  String orderInfo) {

        long amount =price * 100L;
        Map<String, String> vnpParamsMap = vnpayConfig.getVNPayConfig(orderInfo);
        vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
        vnpParamsMap.put("vnp_IpAddr","127.0.0.1");
        //build query url
        String queryUrl = VNPayUtil.getPaymentURL(vnpParamsMap, true);
        String hashData = VNPayUtil.getPaymentURL(vnpParamsMap, false);
        String vnpSecureHash = VNPayUtil.hmacSHA512(vnpayConfig.getSecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String paymentUrl = vnpayConfig.getVnp_PayUrl() + "?" + queryUrl;
        return PaymentDTO.VNPayResponse.builder()
                .paymentUrl(paymentUrl).build();
    }
}
