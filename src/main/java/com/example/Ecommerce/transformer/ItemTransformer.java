package com.example.Ecommerce.transformer;

import com.example.Ecommerce.Dto.RequestDto.ItemRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.ItemResponseDto;
import com.example.Ecommerce.model.Item;

public class ItemTransformer {

    public static Item ItemRequestDtoToItem(ItemRequestDto itemRequestDto){
        return Item.builder()
                .requiredQuantity(itemRequestDto.getRequiredQuantity())
                .build();
    }

    public static ItemResponseDto ItemToItemResponseDto(Item item){
          return ItemResponseDto.builder()
                  .priceOfOneItem(item.getProduct().getPrice())
                  .productName(item.getProduct().getName())
                  .quantity(item.getRequiredQuantity())
                  .totalPrice(item.getRequiredQuantity()*item.getProduct().getPrice())
                  .build();
    }
}
