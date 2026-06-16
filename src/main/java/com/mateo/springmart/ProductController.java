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

    @PatchMapping("/{id}")
    public Product partialUpdateProduct(@RequestBody Product newData, @PathVariable Long id) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    if (newData.getName() != null) {
                        existingProduct.setName(newData.getName());
                    }

                    if (newData.getPrice() != null && newData.getPrice() > 0) {
                        existingProduct.setPrice(newData.getPrice());
                    }

                    if (newData.getStock() != null && newData.getStock() >= 0) {
                        existingProduct.setStock(newData.getStock());
                    }

                    if (newData.getArea() != null) {
                        existingProduct.setArea(newData.getArea());
                    }

                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Product with ID: " + id + " not found to modify."));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
