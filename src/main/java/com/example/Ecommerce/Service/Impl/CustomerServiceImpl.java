package com.example.Ecommerce.Service.Impl;

import com.example.Ecommerce.Dto.RequestDto.CustomerRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.CustomerResponseDto;
import com.example.Ecommerce.Dto.ResponseDto.SellerResponseDto;
import com.example.Ecommerce.Exceptions.MobileNoAlreadyPresentException;
import com.example.Ecommerce.Repository.CustomerRepository;
import com.example.Ecommerce.Service.CustomerService;
import com.example.Ecommerce.model.Cart;
import com.example.Ecommerce.model.Customer;
import com.example.Ecommerce.model.Seller;
import com.example.Ecommerce.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobileNoAlreadyPresentException {

        if (customerRepository.findByMobNo(customerRequestDto.getMobNo()) != null)
            throw new MobileNoAlreadyPresentException("Sorry! Customer already exists.");

        // request dto to customer
        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);
        Cart cart = Cart.builder()
                .cartTotal(0)
                .numberOfItems(0)
                .customer(customer)
                .build();
        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer);  // customer and cart

        // prepare response dto
        return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);

    }

    @Override
    public CustomerResponseDto getByEmailId(String emailId) {
        {
            Customer customer = customerRepository.findByEmailId(emailId);
            CustomerResponseDto customerResponseDto = new CustomerResponseDto();

            customerResponseDto.setName(customer.getName());
            customerResponseDto.setMessage("Welcome " + customer.getName() + " to Amazon !!!");

            return customerResponseDto;
        }
    }
}
