package com.example.Ecommerce.Repository;

import com.example.Ecommerce.Enum.ProductCategory;
import com.example.Ecommerce.model.Customer;
import com.example.Ecommerce.model.Product;
import com.example.Ecommerce.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer,Integer> {

     Customer findByMobNo(String mobNo);

    Customer findByEmailId(String emailId);

    }

