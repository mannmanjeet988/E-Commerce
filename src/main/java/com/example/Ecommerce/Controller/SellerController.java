package com.example.Ecommerce.Controller;

import com.example.Ecommerce.Dto.RequestDto.SellerRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.SellerResponseDto;
import com.example.Ecommerce.Service.Impl.SellerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerServiceImpl sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        try{
            SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);
            return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public String deleteSeller(@RequestParam("id") int id){
        sellerService.deleteSeller(id);
        return "Seller having id " + id+ " is deleted ";
    }

    @GetMapping("/get_by_email")
    public SellerResponseDto getSellerByEmail(@RequestParam String emailId){
        return sellerService.getByEmailId(emailId);
    }

    @GetMapping("/get_by_id")
    public SellerResponseDto getSellerById(@RequestParam int id){
        return sellerService.getSellerById(id);
    }

    //  GET a seller by email

    // get by id

    // get all seller

    // update seller info based on email id

    // delete a seller based on email

    //delete by id

    // get all sellers of a particular age
}
