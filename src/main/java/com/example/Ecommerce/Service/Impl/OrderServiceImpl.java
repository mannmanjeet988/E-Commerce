package com.example.Ecommerce.Service.Impl;

import com.example.Ecommerce.Dto.ResponseDto.ItemResponseDto;
import com.example.Ecommerce.Dto.ResponseDto.OrderRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.Ecommerce.Exceptions.InvalidCardException;
import com.example.Ecommerce.Exceptions.InvalidCustomerException;
import com.example.Ecommerce.Exceptions.InvalidProductException;
import com.example.Ecommerce.Repository.CardRepository;
import com.example.Ecommerce.Repository.CustomerRepository;
import com.example.Ecommerce.Repository.OrderedRepository;
import com.example.Ecommerce.Repository.ProductRepository;
import com.example.Ecommerce.Service.OrderService;
import com.example.Ecommerce.Service.ProductService;
import com.example.Ecommerce.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

//    @Autowired
//    private JavaMailSender emailSender;
    @Autowired
    ProductService productService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;
    @Autowired
    private OrderedRepository orderedRepository;

    @Override
    public Ordered placeOrder(Customer customer, Card card) throws Exception {
        Cart cart = customer.getCart();

        Ordered order = new Ordered();
        Date date = new Date();
        order.setOrderDate(date);
        order.setOrderNo(String.valueOf(UUID.randomUUID()));

        String maskedCardNumber = generateMaskedCard(card.getCardNo());
        order.setCardUsed(maskedCardNumber);
        order.setCustomer(customer);

        List<Item> orderedItems = new ArrayList<>();
        for(Item item: cart.getItems()){
            try{
                productService.decreaseProductQuantity(item);
                orderedItems.add(item);
            } catch (Exception e) {
                throw new Exception("Product Out of stock");
            }
        }
        order.setItems(orderedItems);
        for(Item item: orderedItems)
            item.setOrder(order);
        order.setTotalValue(cart.getCartTotal());
        return order;
    }

    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception {
        Customer customer;
        try{
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new InvalidCustomerException("Customer Id is invalid !!");
        }

        Product product;
        try{
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch(Exception e){
            throw new InvalidProductException("Product doesn't exist");
        }

        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());
        if(card==null || card.getCvv()!=orderRequestDto.getCvv() || card.getCustomer()!=customer){
            throw new InvalidCardException("Your card is not valid!!");
        }

        Item item = Item.builder()
                .requiredQuantity(orderRequestDto.getRequiredQuantity())
                .product(product)
                .build();
        try{
            productService.decreaseProductQuantity(item);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }

        Ordered order = new Ordered();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));
        String maskedCardNo = generateMaskedCard(card.getCardNo());
        order.setCardUsed(maskedCardNo);
        order.setCustomer(customer);
        order.setTotalValue(item.getRequiredQuantity()*product.getPrice());
        order.getItems().add(item);

        customer.getOrderList().add(order);
        item.setOrder(order);
        product.getItemList().add(item);

        Ordered savedOrder = orderedRepository.save(order); // order and item

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderDate( savedOrder.getOrderDate());
        orderResponseDto.setCardUsed(savedOrder.getCardUsed());
        orderResponseDto.setCustomerName(customer.getName());
        orderResponseDto.setOrderNo(savedOrder.getOrderNo());
        orderResponseDto.setTotalValue(savedOrder.getTotalValue());

        List<ItemResponseDto> items = new ArrayList<>();
        for(Item itemEntity: savedOrder.getItems()){
            ItemResponseDto itemResponseDto = new ItemResponseDto();
            itemResponseDto.setPriceOfOneItem(itemEntity.getProduct().getPrice());
            itemResponseDto.setTotalPrice(itemEntity.getRequiredQuantity()*itemEntity.getProduct().getPrice());
            itemResponseDto.setProductName(itemEntity.getProduct().getName());
            itemResponseDto.setQuantity(itemEntity.getRequiredQuantity());

            items.add(itemResponseDto);
        }

        orderResponseDto.setItems(items);

//        String text = "Congrats! " + savedOrder.getCustomer().getName() + " You have placed order of value(Rs.)  " + savedOrder.getTotalValue();
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("acciobackend111@gmail.com");
//        message.setTo(savedOrder.getCustomer().getEmailId());
//        message.setSubject("Order Placed");
//        message.setText(text);
//        emailSender.send(message);

        return orderResponseDto;

    }

    public String generateMaskedCard(String cardNo){
        String maskedCardNo = "";
        for(int i = 0;i<cardNo.length()-4;i++)
            maskedCardNo += 'X';
        maskedCardNo += cardNo.substring(cardNo.length()-4);
        return maskedCardNo;

    }
}
