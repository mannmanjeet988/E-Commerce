package com.example.Ecommerce.Dto.ResponseDto;

import com.example.Ecommerce.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class CardResponseDto {

    String cardNo;

    String customerName;
}
