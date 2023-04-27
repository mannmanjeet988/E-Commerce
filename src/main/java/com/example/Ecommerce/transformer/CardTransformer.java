package com.example.Ecommerce.transformer;

import com.example.Ecommerce.Dto.RequestDto.CardRequestDto;
import com.example.Ecommerce.model.Card;

public class CardTransformer {

    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto){

        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .expiryDate(cardRequestDto.getExpiryDate())
                .build();
    }
}
