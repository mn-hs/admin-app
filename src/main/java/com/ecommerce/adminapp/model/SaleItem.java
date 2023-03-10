package com.ecommerce.adminapp.model;

import java.math.BigDecimal;

public class SaleItem {
    private int saleItemId;
    private int saleId;
    private int productId;
    private int quantity;
    private String productName;
    private BigDecimal price;

    private BigDecimal saleItemTotal;

    public SaleItem(){}

    public SaleItem(int saleItemId, int saleId, int productId, int quantity,
                    String productName, BigDecimal price) {
        this.saleItemId = saleItemId;
        this.saleId = saleId;
        this.productId = productId;
        this.quantity = quantity;
        this.productName = productName;
        this.price = price;
    }

    public int getSaleItemId() {
        return saleItemId;
    }

    public void setSaleItemId(int saleItemId) {
        this.saleItemId = saleItemId;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
