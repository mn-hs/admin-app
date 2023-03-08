package com.ecommerce.adminapp.dao;

import com.ecommerce.adminapp.model.Customer;

import java.util.List;

public interface CustomerDao {
    Customer getCustomer(int customerId);

    List<Customer> getAllCustomers();

    Customer createCustomer(Customer newCustomer);

    boolean updateCustomer(Customer updatedCustomer);

    void deleteCustomer(int customerId);
}
