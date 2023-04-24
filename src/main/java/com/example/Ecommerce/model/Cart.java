package com.example.Ecommerce.model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cart")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Cart {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
}
