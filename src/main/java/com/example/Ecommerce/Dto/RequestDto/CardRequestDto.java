package com.example.Ecommerce.Dto.RequestDto;

import com.example.Ecommerce.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.sql.Date;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class CardRequestDto {
    String mobNo;

    String cardNo;

    int cvv;

    Date expiryDate;

    CardType cardType;
}
