package com.ecommerce.adminapp.controller;

import com.ecommerce.adminapp.dao.JdbcSaleDao;
import com.ecommerce.adminapp.model.Sale;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// http://localhost:8080
@RestController
@RequestMapping("/ecommerce/admin/sales")
public class SaleController {
    JdbcSaleDao saleDao;

    public SaleController(JdbcSaleDao saleDao) {
        this.saleDao = saleDao;
    }

    @RequestMapping(path = "/{saleId}", method = RequestMethod.GET)
    public Sale getSale(@PathVariable int saleId){
        return saleDao.getSale(saleId);
    }

    //combine getSalesByCustomer/Product ID into one method in controller & jdbcDao
    @RequestMapping(method = RequestMethod.GET)
    public List<Sale> getSalesByCustomerId(@RequestParam(name = "customerId") int customerId){
        return saleDao.getSalesByCustomerId(customerId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Sale> getSalesFiltered(@RequestParam(name = "productId") int productId){
        return saleDao.getSalesByProductId(productId);
    }

    @RequestMapping(path = "/unshipped", method = RequestMethod.GET)
    public List<Sale> getUnshippedSales(){
        return saleDao.getSalesUnshipped();
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Sale createSale(@RequestBody Sale newSale){
        return saleDao.createSale(newSale);
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT)
    public boolean updateSale(@RequestBody Sale updatedSale){
        return saleDao.updateSale(updatedSale);
    }

    @RequestMapping(path = "delete/{saleId}", method = RequestMethod.DELETE)
    public void deleteSale(@PathVariable int saleId){
        saleDao.deleteSale(saleId);
    }
}
