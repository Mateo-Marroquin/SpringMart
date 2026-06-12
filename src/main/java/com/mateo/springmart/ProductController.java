package com.mateo.springmart;

import java.util.concurrent.atomic.AtomicLong;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private final ArrayList<Product> products = new ArrayList<>();

    public ProductController() {
        AtomicLong counter = new AtomicLong();
        products.add(new Product(counter.incrementAndGet(), "Apple", 12.99, 100, "Grocery"));
        products.add(new Product(counter.incrementAndGet(), "Banana", 12.99, 100, "Grocery"));
        products.add(new Product(counter.incrementAndGet(), "Orange", 12.99, 100, "Grocery"));
        products.add(new Product(counter.incrementAndGet(), "Laptop", 500.00, 10, "Electronics"));
        products.add(new Product(counter.incrementAndGet(), "Tv", 299.99, 20, "Electronics"));
        products.add(new Product(counter.incrementAndGet(), "Shampoo", 10.00, 100, "Personal Care"));
        products.add(new Product(counter.incrementAndGet(), "Soap", 4.99, 100, "Personal Care"));
    }

    @GetMapping("/products")
    public ArrayList<Product> products(){
        return products;
    }

}
