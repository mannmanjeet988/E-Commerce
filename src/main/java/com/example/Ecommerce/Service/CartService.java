package com.example.Ecommerce.Service;

import com.example.Ecommerce.Dto.RequestDto.CheckoutCartRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.CartResponseDto;
import com.example.Ecommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.Ecommerce.model.Cart;
import com.example.Ecommerce.model.Item;

public interface CartService {

    public CartResponseDto saveCart(Integer customerId, Item item);

    public OrderResponseDto checkOutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws Exception;

    public void resetCart(Cart cart);
}
