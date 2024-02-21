package com.ryan.ryanshoppingmall.dao;

import com.ryan.ryanshoppingmall.model.Product;

public interface ProductDao {
    public Product getProductById(Integer productId);
}
