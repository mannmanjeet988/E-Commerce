package com.example.Ecommerce.Service;

import com.example.Ecommerce.Dto.RequestDto.SellerRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.SellerResponseDto;
import com.example.Ecommerce.Exceptions.EmailAlreadyPresentException;

public interface SellerService {
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException;

    public String deleteSeller(int id);

    public SellerResponseDto getByEmailId(String emailId);

    public SellerResponseDto getSellerById(int id);
}
