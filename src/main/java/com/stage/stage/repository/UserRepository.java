package com.stage.stage.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stage.stage.document.Product;
import com.stage.stage.document.User;

public interface UserRepository extends MongoRepository<User, String> {

	
}
