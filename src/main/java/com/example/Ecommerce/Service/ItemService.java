package com.example.Ecommerce.Service;

import com.example.Ecommerce.Dto.RequestDto.ItemRequestDto;
import com.example.Ecommerce.model.Item;

public interface ItemService {

    public Item addItem(ItemRequestDto itemRequestDto) throws Exception;
}
