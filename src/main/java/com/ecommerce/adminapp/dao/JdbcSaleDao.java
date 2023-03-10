package com.ecommerce.adminapp.dao;

import com.ecommerce.adminapp.model.Sale;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSaleDao implements SaleDao{

    JdbcTemplate jdbcTemplate;

    public JdbcSaleDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public Sale getSale(int saleId) {
        Sale sale = null;
        String sql = "SELECT c.first_name || c.last_name AS name, s.sale_id, s.customer_id, s.sale_date, s.ship_date " +
                "FROM sale AS s " +
                "JOIN customer AS c ON s.customer_id = c.customer_id " +
                "WHERE s.sale_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, saleId);
        if (results.next()){
            sale = mapRowToSale(results);
        }
        return sale;
    }

    @Override
    public List<Sale> getSalesByCustomerId(int customerId) {
        List<Sale> customerSales = new ArrayList<>();
        String sql = "SELECT c.first_name || c.last_name AS name, s.sale_id, s.customer_id, s.sale_date, s.ship_date " +
                "FROM sale AS s " +
                "JOIN customer AS c ON s.customer_id = c.customer_id " +
                "WHERE s.customer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, customerId);
        while (results.next()){
            Sale sale = mapRowToSale(results);
            customerSales.add(sale);
        }
        return customerSales;
    }

    @Override
    public List<Sale> getSalesByProductId(int productId) {
        List<Sale> productSales = new ArrayList<>();
        String sql = "SELECT c.first_name || c.last_name AS name, s.sale_id, s.customer_id, s.sale_date, s.ship_date " +
                "FROM sale AS s " +
                "LEFT JOIN sale_item AS si ON s.sale_id = si.sale_id " +
                "JOIN customer AS c ON s.customer_id = c.customer_id " +
                "WHERE si.product_id = ? " +
                "ORDER BY si.product_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, productId);
        while (results.next()){
            Sale sale = mapRowToSale(results);
            productSales.add(sale);
        }
        return productSales;
    }

    @Override
    public List<Sale> getSalesUnshipped(){
        List<Sale> unshippedSales = new ArrayList<>();
        String sql = "SELECT c.first_name || c.last_name AS name, s.sale_id, s.customer_id, s.sale_date, s.ship_date " +
                "FROM sale AS s " +
                "JOIN customer AS c ON s.customer_id = c.customer_id " +
                "WHERE ship_date IS NULL;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            Sale sale = mapRowToSale(results);
            unshippedSales.add(sale);
        }
        return unshippedSales;
    }

    @Override
    public Sale createSale(Sale newSale) {
        String sql = "INSERT INTO sale (customer_id, sale_date, ship_date) " +
                "VALUES(?, ?, ?) RETURNING sale_id;";
        int newId = jdbcTemplate.queryForObject(sql, int.class, newSale.getCustomerId(), newSale.getSaleDate(),
                newSale.getShipDate());
        return getSale(newId);
    }

    @Override
    public boolean updateSale(Sale updatedSale) {
        String sql = "Update sale " +
                "SET customer_id = ?, sale_date = ?, ship_date = ? " +
                "WHERE sale_id = ?;";
        jdbcTemplate.update(sql, updatedSale.getCustomerId(), updatedSale.getSaleDate(),
                updatedSale.getShipDate(), updatedSale.getSaleId());
        if (getSale(updatedSale.getSaleId()).equals(updatedSale)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deleteSale(int saleId) {
        List<Sale> deletableSales = getSalesUnshipped();
        for (Sale sale : deletableSales) {
            if (sale.getSaleId() == saleId){
                String sql = "DELETE FROM sale_item WHERE sale_id = ?; " +
                        "DELETE FROM sale WHERE sale_id = ?;";
                jdbcTemplate.update(sql, saleId, saleId);
            }
        }
    }

    public Sale mapRowToSale(SqlRowSet rs){
        Sale sale = new Sale();
        sale.setSaleId(rs.getInt("sale"));
        sale.setCustomerId(rs.getInt("customer_id"));
        sale.setSaleDate(rs.getDate("sale_date").toLocalDate());
        sale.setShipDate(rs.getDate("ship_date").toLocalDate());
        sale.setCustomerName(rs.getString("name"));
        return sale;
    }
}
