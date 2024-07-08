package com.Api.HealthPlus.Controller;

import com.Api.HealthPlus.Payload.CartDto;
import com.Api.HealthPlus.Payload.DeleteResponse;
import com.Api.HealthPlus.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/save/user/{userId}")
    public ResponseEntity<CartDto> saveCartData(
            @PathVariable("userId") int id,
            @RequestBody CartDto cartDto
    ){
        CartDto card = cartService.saveDataInCart(cartDto, id);
        return new ResponseEntity<>(card, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getAllCart/user/{userId}")
    public ResponseEntity<List<CartDto>> getAllCartData(
            @PathVariable("userId") int id
    ){
        List<CartDto> allCartByUser = cartService.getAllCartByUser(id);
        return ResponseEntity.ok(allCartByUser);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/updateCartData/{cart_Id}/{qty}")
    public ResponseEntity<CartDto> updateCartData(
            @PathVariable("cart_Id") int id,
            @PathVariable("qty") int qty
    ){
        CartDto cart = cartService.updateCartDto(id,qty);
        return ResponseEntity.ok(cart);
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("/delete/cart/{cartId}")
    public ResponseEntity<DeleteResponse> deleteCartData(
            @PathVariable("cartId") int id
    ){
        cartService.deleteCartData(id);
        return new ResponseEntity<>(new DeleteResponse
                ("Cart deleted successfully", HttpStatus.OK.value(), true)
        ,HttpStatus.OK);
    }
}