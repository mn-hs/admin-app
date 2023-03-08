package com.ecommerce.adminapp.dao;

import com.ecommerce.adminapp.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcProductDao implements ProductDao{

    JdbcTemplate jdbcTemplate;

    public JdbcProductDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}
    @Override
    public Product getProduct(int productId) {
        Product product = null;
        String sql = "SELECT product_id, name, description, price, category " +
                "FROM product " +
                "WHERE product_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, productId);
        if (results.next()){
            product = mapRowToProduct(results);
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();
       String sql = "SELECT product_id, name, description, price, category " +
                "FROM product " +
                "ORDER BY product_id;";
       SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Product product = mapRowToProduct(results);
            allProducts.add(product);
        }
        return allProducts;
    }

    @Override
    public List<Product> getProductsWithNoSales() {
        List<Product> noSaleProducts = new ArrayList<>();
        String sql = "SELECT p.product_id, p.name, p.description, p.price, p.category " +
                "FROM product AS p " +
                "LEFT JOIN sale_item AS si ON p.product_id = si.product_id " +
                "WHERE sale_id IS NULL " +
                "ORDER BY p.product_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            Product product = mapRowToProduct(results);
            noSaleProducts.add(product);
        }
        return noSaleProducts;
    }

    @Override
    public Product createProduct(Product newProduct) {
        String sql = "INSERT INTO product (name, description, price, category) " +
                "VALUES(?, ?, ?, ?) RETURNING product_id;";
        int newId = jdbcTemplate.queryForObject(sql, int.class, newProduct.getName(), newProduct.getDescription(),
                newProduct.getPrice(), newProduct.getCategory());
        return getProduct(newId);
    }

    @Override
    public boolean updateProduct(Product updatedProduct) {
        String sql = "UPDATE product " +
                "SET name = ?, description = ?, price = ?, category = ? " +
                "WHERE product_id = ?;";
        jdbcTemplate.update(sql, updatedProduct.getName(), updatedProduct.getDescription(),
                updatedProduct.getPrice(), updatedProduct.getCategory(),
                updatedProduct.getProductId());
        if (getProduct(updatedProduct.getProductId()).equals(updatedProduct)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deleteProduct(int productId) {
        List<Product> deletableProducts = getProductsWithNoSales();
        for (Product product : deletableProducts){
            if (product.getProductId() == productId){
                String sql = "DELETE FROM product WHERE product_id = ?";
                jdbcTemplate.update(sql, productId);
            }
        }
    }

    public Product mapRowToProduct(SqlRowSet rs){
        Product product = new Product();
        product.setProductId(rs.getInt("product_id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setCategory(rs.getString("category"));
        return product;
    }
}
