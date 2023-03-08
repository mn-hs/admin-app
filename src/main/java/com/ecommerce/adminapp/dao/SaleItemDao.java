package com.ecommerce.adminapp.dao;

import com.ecommerce.adminapp.model.SaleItem;

import java.util.List;

public interface SaleItemDao {
    List<SaleItem> getSaleItemsById(int saleId);
}
