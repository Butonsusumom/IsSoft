package com.tsybulka.entity;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private String productId;
    private int quantity;

    public Order(String orderId, String productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
