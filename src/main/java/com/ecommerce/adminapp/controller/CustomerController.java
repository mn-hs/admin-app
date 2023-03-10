package com.ecommerce.adminapp.controller;

import com.ecommerce.adminapp.dao.JdbcCustomerDao;
import com.ecommerce.adminapp.model.Customer;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// http://localhost:8080
@RestController
@RequestMapping("/ecommerce/admin/customers")
public class CustomerController {
    JdbcCustomerDao customerDao;

    public CustomerController(JdbcCustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> getAllCustomers(){
        return customerDao.getAllCustomers();
    }

    @RequestMapping(path = "/{customerId}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable int customerId){
        return customerDao.getCustomer(customerId);
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Customer createCustomer(@RequestBody Customer newCustomer){
        return customerDao.createCustomer(newCustomer);
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT)
    public boolean updateCustomer(@RequestBody Customer updatedCustomer){
        return customerDao.updateCustomer(updatedCustomer);
    }

    @RequestMapping(path = "/delete/{customerId}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable int customerId){
        customerDao.deleteCustomer(customerId);
    }
}
