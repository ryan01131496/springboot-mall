package com.ryan.ryanshoppingmall.service;

import com.ryan.ryanshoppingmall.constant.ProductCategory;
import com.ryan.ryanshoppingmall.dto.ProductQueryParams;
import com.ryan.ryanshoppingmall.dto.ProductRequest;
import com.ryan.ryanshoppingmall.model.Product;

import java.util.List;

public interface ProductService {
    public Product getProductById(Integer productId);

    public Integer createProduct(ProductRequest productRequest);

    public void updateProduct(Integer productId, ProductRequest productRequest);

    public void deleteProduct(Integer productId);

    public List<Product> getProducts(ProductQueryParams productQueryParams);

    public Integer countProducts(ProductQueryParams productQueryParams);
}
