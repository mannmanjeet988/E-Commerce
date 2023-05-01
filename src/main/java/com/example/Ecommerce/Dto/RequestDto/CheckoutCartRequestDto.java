package com.example.Ecommerce.Dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class CheckoutCartRequestDto {

    int customerId;

    String cardNo;

    int cvv;
}
