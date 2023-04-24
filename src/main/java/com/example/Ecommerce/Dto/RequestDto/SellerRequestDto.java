package com.example.Ecommerce.Dto.RequestDto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class SellerRequestDto {

    String name;

    String emailId;

    Integer age;

    String mobNo;
}
