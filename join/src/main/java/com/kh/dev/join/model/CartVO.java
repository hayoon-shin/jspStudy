package com.kh.dev.join.model;

public class CartVO {
    private int id;           // 장바구니 항목 고유 ID
    private int userId;       // 사용자 ID
    private int productId;    // 상품 ID
    private int quantity;     // 수량
    private String productName; // 상품 이름 (JOIN 결과)
    private double price;     // 상품 가격 (JOIN 결과)

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

