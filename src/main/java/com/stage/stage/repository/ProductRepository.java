package com.stage.stage.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stage.stage.document.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
