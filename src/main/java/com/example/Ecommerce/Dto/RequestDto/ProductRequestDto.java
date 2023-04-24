package com.example.Ecommerce.Dto.RequestDto;

import com.example.Ecommerce.Enum.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class ProductRequestDto {

    int sellerId;

    String productName;

    int price;

    int quantity;

    ProductCategory productCategory;
}
