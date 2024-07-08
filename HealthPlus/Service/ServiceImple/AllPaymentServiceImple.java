package com.Api.HealthPlus.Service.ServiceImple;

import com.Api.HealthPlus.Entity.AllPayment;
import com.Api.HealthPlus.Entity.User;
import com.Api.HealthPlus.Exception.ResourseNotFoundException;
import com.Api.HealthPlus.Payload.AllPaymentDto;
import com.Api.HealthPlus.Repositary.AllPaymentRepositary;
import com.Api.HealthPlus.Repositary.UserRepositary;
import com.Api.HealthPlus.Service.AllPaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AllPaymentServiceImple implements AllPaymentService {

    @Autowired
    private AllPaymentRepositary allPaymentRepositary;
    @Autowired
    private UserRepositary userRepositary;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AllPaymentDto savePaymentData(AllPaymentDto allPaymentDto,int userId) {
        User user = userRepositary.findById(userId)
                .orElseThrow(() -> new ResourseNotFoundException("User", "User_Id", userId));
        AllPayment allPayment = modelMapper.map(allPaymentDto, AllPayment.class);
        allPayment.setUser(user);
        AllPayment save = allPaymentRepositary.save(allPayment);
        AllPaymentDto savePayment = modelMapper.map(save, AllPaymentDto.class);
        return savePayment;
    }

    @Override
    public List<AllPaymentDto> getAllPayment() {
        List<AllPayment> allPayment= allPaymentRepositary.findAll();
        List<AllPaymentDto> collect = allPayment
                .stream()
                .map((payment) -> modelMapper.map(payment, AllPaymentDto.class))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<AllPaymentDto> getPaymentByUserId(int userId) {
        User user = userRepositary.findById(userId)
                .orElseThrow(() -> new ResourseNotFoundException("User", "user id", userId));
        List<AllPayment> payment = allPaymentRepositary.findByUser(user);
        List<AllPaymentDto> collect = payment
                .stream()
                .map((pay) -> modelMapper.map(pay, AllPaymentDto.class))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public void deletePayment(int paymentId) {
        AllPayment allPayment = allPaymentRepositary.findById(paymentId)
                .orElseThrow(() -> new ResourseNotFoundException("All Payment", "Payment_Id", paymentId));
        allPaymentRepositary.delete(allPayment);
    }
}
