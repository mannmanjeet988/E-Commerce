package com.example.Ecommerce.Controller;


import com.example.Ecommerce.Dto.RequestDto.CustomerRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.CustomerResponseDto;
import com.example.Ecommerce.Dto.ResponseDto.SellerResponseDto;
import com.example.Ecommerce.Exceptions.MobileNoAlreadyPresentException;
import com.example.Ecommerce.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws MobileNoAlreadyPresentException {

        return customerService.addCustomer(customerRequestDto);
    }

    @GetMapping("/get_by_email")
    public CustomerResponseDto getCustomerByEmail(@RequestParam String emailId){
        return customerService.getByEmailId(emailId);
    }


    // view all customers

    // get a customer by email/mob

    // get all customers whose age is greater than 25

    // get all customers who use VISA card

    // update a customer info by email

    // delete a customer by email/mob
}
