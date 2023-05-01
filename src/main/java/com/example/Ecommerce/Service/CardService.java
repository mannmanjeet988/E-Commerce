package com.example.Ecommerce.Service;

import com.example.Ecommerce.Dto.RequestDto.CardRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.CardResponseDto;
import com.example.Ecommerce.Exceptions.InvalidCustomerException;



public interface CardService {

    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws  InvalidCustomerException;
}
