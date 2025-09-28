package com.shopacc.model;
import java.math.BigDecimal;

public class Account {
    private int id;
    private String gameName;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private String status;
    private String username;
    private String password;
    private int categoryId; 
    
    public Account() {
    }

    public Account(int id, String gameName, String description, BigDecimal price, String imageUrl) {
        this.id = id;
        this.gameName = gameName;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getUsername() { 
        return username; 
    }
    public void setUsername(String username) { 
        this.username = username; 
    }
    public String getPassword() {
        return password; 
    }
    public void setPassword(String password) { 
        this.password = password; 
    }
    
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}