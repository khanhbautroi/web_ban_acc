package com.shopacc.model;

import java.sql.Timestamp;

public class PurchaseHistoryDTO {
    private int transactionId;
    private String buyerUsername;
    private String gameUsername; // <-- THÊM LẠI TRƯỜNG NÀY
    private String gamePassword; // <-- THÊM TRƯỜNG MỚI
    private String categoryName;
    private double price;
    private Timestamp purchaseDate;
    private String description;

    // Getters và Setters
    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }

    public String getBuyerUsername() { return buyerUsername; }
    public void setBuyerUsername(String buyerUsername) { this.buyerUsername = buyerUsername; }

    public String getGameUsername() { return gameUsername; } // <-- THÊM LẠI
    public void setGameUsername(String gameUsername) { this.gameUsername = gameUsername; } // <-- THÊM LẠI

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Timestamp getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(Timestamp purchaseDate) { this.purchaseDate = purchaseDate; }
    public String getGamePassword() {
        return gamePassword;
    }

    public void setGamePassword(String gamePassword) {
        this.gamePassword = gamePassword;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}