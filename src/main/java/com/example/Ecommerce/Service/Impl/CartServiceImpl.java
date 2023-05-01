package com.example.Ecommerce.Service.Impl;

import com.example.Ecommerce.Dto.RequestDto.CheckoutCartRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.CartResponseDto;
import com.example.Ecommerce.Dto.ResponseDto.ItemResponseDto;
import com.example.Ecommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.Ecommerce.Exceptions.InvalidCardException;
import com.example.Ecommerce.Exceptions.InvalidCustomerException;
import com.example.Ecommerce.Repository.CardRepository;
import com.example.Ecommerce.Repository.CartRepository;
import com.example.Ecommerce.Repository.CustomerRepository;
import com.example.Ecommerce.Repository.OrderedRepository;
import com.example.Ecommerce.Service.CartService;
import com.example.Ecommerce.model.*;
import com.example.Ecommerce.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl  implements CartService{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    OrderedRepository orderedRepository;

    @Autowired
    CardRepository cardRepository;

    @Override
    public CartResponseDto saveCart(Integer customerId, Item item) {

        Customer customer = customerRepository.findById(customerId).get();
        Cart cart = customer.getCart();

        int newTotal = cart.getCartTotal()+ (item.getRequiredQuantity()*item.getProduct().getPrice());
        cart.setCartTotal(newTotal);
        cart.getItems().add(item);
        cart.setNumberOfItems(cart.getItems().size());
        item.setCart(cart);
        Cart savedCart = cartRepository.save(cart);

        //prepare cart response dto
        CartResponseDto cartResponseDto = CartResponseDto.builder()
                .cartTotal(savedCart.getCartTotal())
                .numberOfItems(savedCart.getNumberOfItems())
                .customerName(customer.getName())
                .build();

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
             for(Item itemEntity: savedCart.getItems()){
                 ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(itemEntity);
                 itemResponseDtoList.add(itemResponseDto);
             }

             cartResponseDto.setItems(itemResponseDtoList);

        return cartResponseDto;
    }

    @Override
    public OrderResponseDto checkOutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws Exception {
        Customer customer;
        try{
            customer = customerRepository.findById(checkoutCartRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new InvalidCustomerException("Customer id is invalid!!!");
        }

        Card card = cardRepository.findByCardNo(checkoutCartRequestDto.getCardNo());
        if(card==null || card.getCvv()!=checkoutCartRequestDto.getCvv() || card.getCustomer()!=customer){
            throw new InvalidCardException("Your card is not valid!!");
        }

        Cart cart = customer.getCart();
        if(cart.getNumberOfItems()==0){
            throw new Exception("Cart is empty!!");
        }

        try{
            Ordered order = orderService.placeOrder(customer,card);  // throw exception if product goes out of stock
            customer.getOrderList().add(order);
            resetCart(cart);
            Ordered savedOrder = orderedRepository.save(order);

            // prepare response dto
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setOrderDate((Date) savedOrder.getOrderDate());
            orderResponseDto.setCardUsed(savedOrder.getCardUsed());
            orderResponseDto.setCustomerName(customer.getName());
            orderResponseDto.setOrderNo(savedOrder.getOrderNo());
            orderResponseDto.setTotalValue(savedOrder.getTotalValue());

            List<ItemResponseDto> items = new ArrayList<>();
            for(Item itemEntity: savedOrder.getItems()){
                ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(itemEntity);
                items.add(itemResponseDto);
            }
            orderResponseDto.setItems(items);
            return orderResponseDto;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void resetCart(Cart cart) {
        cart.setCartTotal(0);
        for(Item item : cart.getItems()){
            item.setCart(null);
        }
        cart.setNumberOfItems(0);
        cart.getItems().clear();

    }
}
