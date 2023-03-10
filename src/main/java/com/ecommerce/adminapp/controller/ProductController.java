package com.ecommerce.adminapp.controller;

import com.ecommerce.adminapp.dao.JdbcProductDao;
import com.ecommerce.adminapp.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// http://localhost:8080
@RestController
@RequestMapping("/ecommerce/admin/products")
public class ProductController {
    JdbcProductDao productDao;

    public ProductController(JdbcProductDao productDao){
        this.productDao = productDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> getAllProducts(){
        return productDao.getAllProducts();
    }

    @RequestMapping(path = "/noSales", method = RequestMethod.GET)
    public List<Product> getProductsWithNoSales(){
        return productDao.getProductsWithNoSales();
    }

    @RequestMapping(path = "/{productId}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable int productId){
        return productDao.getProduct(productId);
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product newProduct){
        return productDao.createProduct(newProduct);
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT)
    public boolean updateProduct(@RequestBody Product updatedProduct){
        return productDao.updateProduct(updatedProduct);
    }

    @RequestMapping(path = "delete/{productId}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable int productId){
        productDao.deleteProduct(productId);
    }
}
