package com.ryan.ryanshoppingmall.service;


import com.ryan.ryanshoppingmall.dao.ProductDao;
import com.ryan.ryanshoppingmall.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
