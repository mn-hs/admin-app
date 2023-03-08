package com.ecommerce.adminapp.dao;

import com.ecommerce.adminapp.model.Sale;

import java.util.List;

public interface SaleDao {
    Sale getSale(int saleId);

    List<Sale> getSalesByCustomerId(int customerId);

    List<Sale> getSalesByProductId(int productId);

    List<Sale> getSalesUnshipped();

    Sale createSale(Sale newSale);

    boolean updateSale(Sale updatedSale);

    void deleteSale(int saleId);
}
