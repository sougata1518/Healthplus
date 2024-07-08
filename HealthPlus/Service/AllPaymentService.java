package com.Api.HealthPlus.Service;

import com.Api.HealthPlus.Payload.AllPaymentDto;

import java.util.List;

public interface AllPaymentService {

    public AllPaymentDto savePaymentData(AllPaymentDto allPaymentDto,int userId);
    public List<AllPaymentDto> getAllPayment();
    public List<AllPaymentDto> getPaymentByUserId(int userId);
    public void deletePayment(int paymentId);
}
