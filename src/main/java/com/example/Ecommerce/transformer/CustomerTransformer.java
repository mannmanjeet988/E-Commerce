package com.example.Ecommerce.transformer;

import com.example.Ecommerce.Dto.RequestDto.CustomerRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.CustomerResponseDto;
import com.example.Ecommerce.model.Customer;

public class CustomerTransformer {
    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto) {

        return Customer.builder()
                .name(customerRequestDto.getName())
                .emailId(customerRequestDto.getEmailId())
                .age(customerRequestDto.getAge())
                .mobNo(customerRequestDto.getMobNo())
                .address(customerRequestDto.getAddress())
                .build();
    }

    public static CustomerResponseDto CustomerToCustomerResponseDto(Customer customer){
       return  CustomerResponseDto.builder()
               .name(customer.getName())
               .message("Welcome " + customer.getName()+ " to Amazon !!!")
               .build();
    }
    }

