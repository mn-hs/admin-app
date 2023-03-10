package com.ecommerce.adminapp.dao;

import com.ecommerce.adminapp.model.Sale;

import java.util.List;

public interface SaleDao {
    Sale getSale(int saleId);

    List<Sale> getSalesUnshipped();

    List<Sale> getSalesFiltered(int productId, int customerId);

    Sale createSale(Sale newSale);

    boolean updateSale(Sale updatedSale);

    void deleteSale(int saleId);
}
