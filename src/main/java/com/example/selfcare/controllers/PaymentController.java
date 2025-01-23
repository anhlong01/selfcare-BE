package com.example.selfcare.controllers;

import com.example.selfcare.dto.PaymentDTO;
import com.example.selfcare.dto.response.ApiResponse;
import com.example.selfcare.entity.Appointment;
import com.example.selfcare.entity.Patient;
import com.example.selfcare.service.AppointmentService;
import com.example.selfcare.service.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/vnpay")
@RequiredArgsConstructor
public class PaymentController {
    private final VNPayService paymentService;
    private final AppointmentService appointmentService;
    @PostMapping("/create-payment")
    public ApiResponse<PaymentDTO.VNPayResponse> pay(@RequestParam("amount") int amount, @RequestParam("order_info") String orderInfo){
        ApiResponse<PaymentDTO.VNPayResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(paymentService.createVnPayPayment(amount, orderInfo));
        return apiResponse;
    }
    @GetMapping("/vn-pay-callback")
    public String payCallbackHandler(HttpServletRequest request) {
        String orderInfo= request.getParameter("vnp_OrderInfo");
        String[] arr = orderInfo.split(" ");
        int pid = Integer.parseInt(arr[0]);
        int scheduleId = Integer.parseInt(arr[1]);
        String responseCode = request.getParameter("vnp_ResponseCode");
        if(Objects.equals(responseCode, "00")){
            appointmentService.createAppointment(pid, scheduleId);
            return "Thanh toán thành công";
        }else{
            return "Thanh toán không thành công";
        }

    }
}