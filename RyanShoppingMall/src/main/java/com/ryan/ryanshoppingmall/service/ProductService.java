package com.ryan.ryanshoppingmall.service;

import com.ryan.ryanshoppingmall.dto.ProductRequest;
import com.ryan.ryanshoppingmall.model.Product;

public interface ProductService {
    public Product getProductById(Integer productId);

    public Integer createProduct(ProductRequest productRequest);

    public void updateProduct(Integer productId, ProductRequest productRequest);

    public void deleteProduct(Integer productId);
}
