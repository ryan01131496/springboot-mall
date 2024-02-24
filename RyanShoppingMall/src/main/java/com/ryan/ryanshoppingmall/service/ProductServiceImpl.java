package com.ryan.ryanshoppingmall.service;


import com.ryan.ryanshoppingmall.constant.ProductCategory;
import com.ryan.ryanshoppingmall.dao.ProductDao;
import com.ryan.ryanshoppingmall.dto.ProductQueryParams;
import com.ryan.ryanshoppingmall.dto.ProductRequest;
import com.ryan.ryanshoppingmall.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }
    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        productDao.updateProduct(productId, productRequest);
    }
    @Override
    public void deleteProduct(Integer productId) {
        productDao.deleteProduct(productId);
    }

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        return productDao.getProducts(productQueryParams);
    }
}
