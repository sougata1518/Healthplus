package com.Api.HealthPlus.Service.ServiceImple;

import com.Api.HealthPlus.Entity.Cart;
import com.Api.HealthPlus.Entity.User;
import com.Api.HealthPlus.Exception.ResourseNotFoundException;
import com.Api.HealthPlus.Payload.CartDto;
import com.Api.HealthPlus.Repositary.CartRepositary;
import com.Api.HealthPlus.Repositary.UserRepositary;
import com.Api.HealthPlus.Service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImple implements CartService {

    @Autowired
    private CartRepositary cartRepositary;
    @Autowired
    private UserRepositary userRepositary;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CartDto saveDataInCart(CartDto cartDto, int userId) {
        User user = userRepositary.findById(userId)
                .orElseThrow(() -> new ResourseNotFoundException("User", "User_Id", userId));
        Cart cart = modelMapper.map(cartDto, Cart.class);
        cart.setUser(user);
        Cart save = cartRepositary.save(cart);
        CartDto map = modelMapper.map(save, CartDto.class);
        return map;
    }

    @Override
    public List<CartDto> getAllCartByUser(int userId) {
        User user = userRepositary.findById(userId)
                .orElseThrow(() -> new ResourseNotFoundException("User", "User_Id", userId));
        List<Cart> cartByUser = cartRepositary.findByUser(user);
        List<CartDto> carts = cartByUser
                .stream()
                .map((cart) -> modelMapper.map(cart, CartDto.class))
                .collect(Collectors.toList());
        return carts;
    }

    @Override
    public CartDto updateCartDto(int cart_Id,int qty) {
        Cart cart = cartRepositary.findById(cart_Id)
                .orElseThrow(() -> new ResourseNotFoundException("Cart data", "cart_Id", cart_Id));
        cart.setQuantity(String.valueOf(qty));
        cart.setTotal_price(String.valueOf(qty * Integer.parseInt(cart.getMain_price())));
        Cart save = cartRepositary.save(cart);
        CartDto map = modelMapper.map(save, CartDto.class);
        return map;
    }

    @Override
    public void deleteCartData(int cartId) {
        Cart cart = cartRepositary.findById(cartId)
                .orElseThrow(() -> new ResourseNotFoundException("Cart", "Cart_Id", cartId));
        cartRepositary.delete(cart);
    }

    @Override
    public void deleteCartDataAfterPayment(int userId, int catId) {
        User user = userRepositary.findById(userId)
                .orElseThrow(() -> new ResourseNotFoundException("User", "userId", userId));
        List<Cart> cartList = cartRepositary.findByUser(user);
        for(Cart cart : cartList){
            if(cart.getCartType().equals(String.valueOf(catId))){
                cartRepositary.delete(cart);
            }
        }
    }
}
