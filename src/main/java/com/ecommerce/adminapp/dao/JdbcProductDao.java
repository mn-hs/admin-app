package com.ecommerce.adminapp.dao;

import com.ecommerce.adminapp.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

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
        return null;
    }

    @Override
    public List<Product> getProductsWithNoSales() {
        return null;
    }

    @Override
    public List<Product> createProduct(Product newProduct) {
        return null;
    }

    @Override
    public void updateProduct(Product updatedProduct) {

    }

    @Override
    public void deleteProduct(int productId) {

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
