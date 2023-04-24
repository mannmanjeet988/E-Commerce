package com.example.Ecommerce.model;


import com.example.Ecommerce.Enum.CardType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="card")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Card {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;

    @Column(unique = true, nullable = false)
    String cardNo;

    Integer CVV;

    Date expiryDate;
        @Enumerated(EnumType.STRING)
        CardType cardType;

    @ManyToOne
    @JoinColumn
    Customer customer;
}
