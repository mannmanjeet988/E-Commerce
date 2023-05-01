package com.example.Ecommerce.Service;

import com.example.Ecommerce.Dto.ResponseDto.OrderRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.Ecommerce.model.Card;
import com.example.Ecommerce.model.Customer;
import com.example.Ecommerce.model.Ordered;

public interface OrderService {

    public Ordered placeOrder(Customer customer, Card card) throws Exception;

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception;

    String generateMaskedCard(String cardNo);
}
