package com.example.Ecommerce.Service;

import com.example.Ecommerce.Dto.RequestDto.ProductRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.ProductResponseDto;
import com.example.Ecommerce.Enum.ProductCategory;
import com.example.Ecommerce.Exceptions.InvalidSellerException;

import java.util.List;

public interface ProductService {

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException;
    public List<ProductResponseDto> getAllProductsByCategory(ProductCategory category);
}
