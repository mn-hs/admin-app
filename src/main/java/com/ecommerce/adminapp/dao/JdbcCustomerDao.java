package com.ecommerce.adminapp.dao;

import com.ecommerce.adminapp.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcCustomerDao implements CustomerDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcCustomerDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}
    @Override
    public Customer getCustomer(int customerId) {
        Customer customer = null;
        String sql = "SELECT customer_id, first_name, last_name, st_address, city, state, zip_code " +
                "FROM customer " +
                "WHERE customer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, customerId);
        if (results.next()) {
            customer = mapRowToCustomer(results);
        }
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql ="SELECT customer_id, first_name, last_name, st_address, city, state, zip_code " +
                "FROM customer;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Customer customer = mapRowToCustomer(results);
            customers.add(customer);
        }
        return customers;
    }

    @Override
    public Customer createCustomer(Customer newCustomer) {
        String sql = "INSERT INTO customer (first_name, last_name, st_address, city, state, zip_code) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING customer_id;";
        int newId = jdbcTemplate.queryForObject(sql, int.class, newCustomer.getFirstName(), newCustomer.getLastName(), newCustomer.getStAddress(),
                newCustomer.getCity(), newCustomer.getState(), newCustomer.getZipcode());
        return getCustomer(newId);
    }

    @Override
    public boolean updateCustomer(Customer updatedCustomer) {
        String sql = "UPDATE customer " +
                "SET first_name = ?, last_name = ?, st_address = ?, city = ?, state = ?, zip_code = ? " +
                "WHERE customer_id = ?";
        jdbcTemplate.update(sql, updatedCustomer.getFirstName(), updatedCustomer.getLastName(), updatedCustomer.getStAddress(),
                updatedCustomer.getCity(), updatedCustomer.getState(), updatedCustomer.getZipcode(),
                updatedCustomer.getCustomerId());
        if (getCustomer(updatedCustomer.getCustomerId()).equals(updatedCustomer)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deleteCustomer(int customerId) {
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        jdbcTemplate.update(sql, customerId);
    }

    public Customer mapRowToCustomer(SqlRowSet rs){
        Customer customer = new Customer();
        customer.setCustomerId(rs.getInt("customer_id"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setStAddress(rs.getString("st_address"));
        customer.setCity(rs.getString("city"));
        customer.setState(rs.getString("state"));
        customer.setZipcode(rs.getString("zip_code"));
        return customer;
    }
}
