package com.example.my_application.Controller;

import com.example.my_application.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private List<Product> products = new ArrayList<>();

    // Create - Add a new product
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        products.add(product);
        return product;
    }

    // Read - Get all products
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return products;
    }

    // Read - Get product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Update - Update an existing product
    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        for (Product product : products) {
            if (product.getId() == id) {
                product.setName(updatedProduct.getName());
                product.setPrice(updatedProduct.getPrice());
                return product;
            }
        }
        return null;
    }

    // Delete - Remove a product
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        products.removeIf(product -> product.getId() == id);
        return "Product with ID " + id + " has been removed.";
    }
}
