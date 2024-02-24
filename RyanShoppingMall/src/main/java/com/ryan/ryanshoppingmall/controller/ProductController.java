package com.ryan.ryanshoppingmall.controller;

import com.ryan.ryanshoppingmall.constant.ProductCategory;
import com.ryan.ryanshoppingmall.dto.ProductQueryParams;
import com.ryan.ryanshoppingmall.dto.ProductRequest;
import com.ryan.ryanshoppingmall.model.Product;
import com.ryan.ryanshoppingmall.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    // Search the product
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);

        if(product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Create the product
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        Integer productId = productService.createProduct(productRequest);

        Product newProduct = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    // Update the product
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest) {

        // Check the product which exist or not
        Product checkProduct = productService.getProductById(productId);

        if (checkProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Update the product data
        productService.updateProduct(productId, productRequest);

        Product updateProduct = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }

    // Delete the product
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Search the whole products
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(@RequestParam (required = false) ProductCategory category,
                                                     @RequestParam (required = false) String search) {



        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);

        List<Product> productList = productService.getProducts(productQueryParams);

        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }
}
