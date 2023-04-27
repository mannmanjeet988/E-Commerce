package com.example.Ecommerce.Service.Impl;

import com.example.Ecommerce.Dto.RequestDto.CardRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.CardResponseDto;
import com.example.Ecommerce.Exceptions.InvalidCustomerException;
import com.example.Ecommerce.Repository.CustomerRepository;
import com.example.Ecommerce.Service.CardService;
import com.example.Ecommerce.model.Card;
import com.example.Ecommerce.model.Customer;
import com.example.Ecommerce.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CustomerRepository customerRepository;

    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException{

        Customer customer = customerRepository.findByMobNo(cardRequestDto.getMobNo());
        if(customer==null){
            throw new InvalidCustomerException("Sorry! The customer doesn't exists");
        }

    Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);

        customer.getCards().add(card);

        //response dto

        return CardResponseDto.builder()
        .customerName(customer.getName())
                .cardNo(card.getCardNo())
                .build();
    }

}
