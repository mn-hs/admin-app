package com.ecommerce.adminapp.dao;

import com.ecommerce.adminapp.model.Product;

import java.util.List;

public interface ProductDao {
    Product getProduct(int productId);

    List<Product> getAllProducts();

    List<Product> getProductsWithNoSales();

    Product createProduct(Product newProduct);

    boolean updateProduct(Product updatedProduct);

    void deleteProduct(int productId);
}
