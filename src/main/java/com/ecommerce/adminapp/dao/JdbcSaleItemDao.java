package com.ecommerce.adminapp.dao;

import com.ecommerce.adminapp.model.SaleItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSaleItemDao implements SaleItemDao{

    JdbcTemplate jdbcTemplate;

    public JdbcSaleItemDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}
    @Override
    public List<SaleItem> getSaleItemsById(int saleId) {
        List<SaleItem> saleItems = new ArrayList<>();
        String sql = "SELECT si.sale_item_id, si.sale_id, si.product_id, si.quantity, p.name, p.price " +
                "FROM sale_item AS si " +
                "JOIN product AS p ON si.product_id = p.product_id " +
                "WHERE si.sale_id = ? " +
                "ORDER BY si.sale_item_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, saleId);
        while (results.next()){
            SaleItem saleItem = mapRowToSaleItem(results);
            saleItems.add(saleItem);
        }
        return saleItems;
    }

    public SaleItem mapRowToSaleItem(SqlRowSet rs){
        SaleItem saleItem = new SaleItem();
        saleItem.setSaleItemId(rs.getInt("sale_item_id"));
        saleItem.setSaleId(rs.getInt("sale_id"));
        saleItem.setProductId(rs.getInt("product_id"));
        saleItem.setQuantity(rs.getInt("quantity"));
        saleItem.setPrice(rs.getBigDecimal("price"));
        return saleItem;
    }
}
