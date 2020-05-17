package com.example.demo.repository;

import com.example.demo.domain.basic.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends MongoRepository<Stock, Integer> {
    Stock findByName(String name);
}
