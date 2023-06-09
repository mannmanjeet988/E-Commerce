package com.example.Ecommerce.Repository;

import com.example.Ecommerce.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {

    Card findByCardNo(String cardNo);

}
