package com.ecommerce.adminapp.controller;

import com.ecommerce.adminapp.dao.JdbcSaleItemDao;
import com.ecommerce.adminapp.model.SaleItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// http://localhost:8080
@RestController
@RequestMapping("/ecommerce/admin/saleItems")
public class SaleItemController {
    JdbcSaleItemDao saleItemDao;

    public SaleItemController(JdbcSaleItemDao saleItemDao) {
        this.saleItemDao = saleItemDao;
    }

    @RequestMapping(path = "/{saleId}", method = RequestMethod.GET)
    public List<SaleItem> getSaleItemsById(@PathVariable int saleId){
        return saleItemDao.getSaleItemsById(saleId);
    }
}
