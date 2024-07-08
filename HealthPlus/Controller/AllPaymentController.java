package com.Api.HealthPlus.Controller;

import com.Api.HealthPlus.Payload.AllPaymentDto;
import com.Api.HealthPlus.Payload.CartDto;
import com.Api.HealthPlus.Payload.DeleteResponse;
import com.Api.HealthPlus.Service.AllPaymentService;
import com.Api.HealthPlus.Service.CartService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/payment")
public class AllPaymentController {

    @Autowired
    private AllPaymentService allPaymentService;
    
    @Autowired
    private CartService cartService;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/save/user/{userId}/{type}")
    public ResponseEntity<?> savePaymentData(
            @PathVariable("userId") int id,
            @PathVariable("type") String typeId,
            @RequestBody Map<String,Object> data
            ) throws RazorpayException {
        int amount = Integer.parseInt(data.get("amount").toString());
        RazorpayClient razorpayClient = new RazorpayClient("rzp_test_ayVY180uYv1UJf","Wio4Vip1FpNSXglHDmtzbZKM");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amount",amount*100);
        jsonObject.put("currency","INR");
        jsonObject.put("receipt","txn_123456");
        Order order = razorpayClient.orders.create(jsonObject);

        List<CartDto> allCartByUser = cartService.getAllCartByUser(id);
        StringBuilder stringBuilder = new StringBuilder(200);
        String details = "";

        for(CartDto cartDto : allCartByUser){
            if(cartDto.getCartType().equals(typeId)){
                details = stringBuilder.append(cartDto.getName()).toString() + ",";
            }
        }

        AllPaymentDto paymentDto = new AllPaymentDto();
        paymentDto.setDetail(details);
        paymentDto.setPrice(String.valueOf(amount));
        paymentDto.setStatus("Paid");
        paymentDto.setCartType(typeId);

        AllPaymentDto saveData = allPaymentService.savePaymentData(paymentDto, id);
        cartService.deleteCartDataAfterPayment(id,Integer.parseInt(typeId));

        return new ResponseEntity<>(order.toString(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/saveDocData/{userId}")
    public ResponseEntity<?> saveDocDataAfterPayment(
            @PathVariable("userId") int id,
            @RequestBody AllPaymentDto allPaymentDto
    ) throws RazorpayException {
        int amount = Integer.parseInt(allPaymentDto.getPrice());
        RazorpayClient razorpayClient = new RazorpayClient("rzp_test_ayVY180uYv1UJf","Wio4Vip1FpNSXglHDmtzbZKM");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amount",amount*100);
        jsonObject.put("currency","INR");
        jsonObject.put("receipt","txn_123456");
        Order order = razorpayClient.orders.create(jsonObject);
        allPaymentService.savePaymentData(allPaymentDto,id);

        return ResponseEntity.ok(order.toString());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAllPayment")
    public ResponseEntity<List<AllPaymentDto>> getAllData(){
        List<AllPaymentDto> allPayment = allPaymentService.getAllPayment();
        return ResponseEntity.ok(allPayment);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/payment/{payId}")
    public ResponseEntity<DeleteResponse> deleteData(
            @PathVariable("payId") int id
    ){
        allPaymentService.deletePayment(id);
        return new ResponseEntity<>(new DeleteResponse
                ("Payment deleted successfully", HttpStatus.OK.value(), true)
        ,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getAllOrder/{userId}")
    public ResponseEntity<List<AllPaymentDto>> getAllOrder(
            @PathVariable("userId") int id
    ){
        List<AllPaymentDto> allPayment = allPaymentService.getPaymentByUserId(id);
        List<AllPaymentDto> orderList = new ArrayList<>();
        for(AllPaymentDto allPaymentDto : allPayment){
            if(allPaymentDto.getCartType().equals("1") || allPaymentDto.getCartType().equals("2")){
                orderList.add(allPaymentDto);
            }
        }
        return ResponseEntity.ok(orderList);
    }
}
