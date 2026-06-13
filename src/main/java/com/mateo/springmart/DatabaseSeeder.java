package com.mateo.springmart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseSeeder {

    private static final Logger log = LoggerFactory.getLogger(DatabaseSeeder.class);

    @Bean
    public CommandLineRunner initDatabase(ProductRepository productRepository){
        return args -> {
            if (productRepository.count() == 0){
                log.info("Initializing database with values....");
                productRepository.save(new Product("Apple", 12.99, 100, "Grocery"));
                productRepository.save(new Product("Banana", 12.99, 100, "Grocery"));
                productRepository.save(new Product("Keyboard", 89.99, 15, "Electronics"));
                productRepository.save(new Product("Laptop", 500.00, 20, "Electronics"));
                productRepository.save(new Product("Shampoo", 10.00, 100, "Personal Care"));
                productRepository.save(new Product("Soap", 4.99, 100, "Personal Care"));
                log.info("Database seeding completed!");
            } else {
                log.info("Database already contains data. Skipping database seeder.");
            }
        };
    }
}
