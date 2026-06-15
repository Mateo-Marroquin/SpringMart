package com.mateo.springmart;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> index() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product with ID: " + id + " not found."));
    }

    @PostMapping
    public Product newProduct(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {

        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setPrice(newProduct.getPrice());
                    product.setStock(newProduct.getStock());
                    product.setArea(newProduct.getArea());
                    return productRepository.save(product);
                })
                .orElseGet(() -> {
                    return productRepository.save(newProduct);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
