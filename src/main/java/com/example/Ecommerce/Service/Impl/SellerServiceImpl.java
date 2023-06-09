package com.example.Ecommerce.Service.Impl;

import com.example.Ecommerce.Dto.RequestDto.SellerRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.SellerResponseDto;
import com.example.Ecommerce.Exceptions.EmailAlreadyPresentException;
import com.example.Ecommerce.Repository.SellerRepository;
import com.example.Ecommerce.Service.SellerService;
import com.example.Ecommerce.model.Seller;
import com.example.Ecommerce.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException{

//        Seller seller = new Seller();
//        seller.setName(sellerRequestDto.getName());
//        seller.setEmailId(sellerRequestDto.getEmailId());
//        seller.setMobNo(sellerRequestDto.getMobNo());
//        seller.setAge(sellerRequestDto.getAge());

        if(sellerRepository.findByEmailId(sellerRequestDto.getEmailId())!=null)
            throw new EmailAlreadyPresentException("Email Id is already registered");

        Seller seller = SellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);
        Seller savedSeller = sellerRepository.save(seller);

        //prepare response Dto
        SellerResponseDto sellerResponseDto = SellerTransformer.SellerToSellerResponseDto(savedSeller);
        return sellerResponseDto;

    }

    public String deleteSeller(int id){
        sellerRepository.deleteById(id);
        return "Seller having id " + id+ " is deleted ";
    }

    public SellerResponseDto getByEmailId(String emailId){

        Seller seller = sellerRepository.findByEmailId(emailId);
        SellerResponseDto sellerResponseDto = new SellerResponseDto();

        sellerResponseDto.setName(seller.getName());
        sellerResponseDto.setAge(seller.getAge());

        return sellerResponseDto;


    }

    @Override
    public SellerResponseDto getSellerById(int id) {
        Seller seller = sellerRepository.findById(id).get();
        SellerResponseDto sellerResponseDto = new SellerResponseDto();

        sellerResponseDto.setName(seller.getName());
        sellerResponseDto.setAge(seller.getAge());

        return sellerResponseDto;
    }

}
