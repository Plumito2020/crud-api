package com.stage.stage.config;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.stage.stage.document.Product;
import com.stage.stage.repository.ProductRepository;

@EnableMongoRepositories(basePackageClasses = ProductRepository.class)
@Configuration
public class MongoDBConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository userRepository) {
        return strings -> {
            userRepository.save(new Product("1", "Pants", 200));
            userRepository.save(new Product("2", "Shoes", 100));
        };
    }
}
