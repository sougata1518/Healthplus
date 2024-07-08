package com.Api.HealthPlus.Service;

import com.Api.HealthPlus.Payload.CartDto;

import java.util.List;

public interface CartService {

    public CartDto saveDataInCart(CartDto cartDto,int userId);
    public List<CartDto> getAllCartByUser(int userId);
    public CartDto updateCartDto(int cart_Id,int qty);
    public void deleteCartData(int cartId);
    public void deleteCartDataAfterPayment(int userId,int catId);
}
