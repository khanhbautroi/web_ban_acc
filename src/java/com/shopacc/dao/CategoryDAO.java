package com.shopacc.dao;

import com.shopacc.model.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Category cat = new Category();
                cat.setId(rs.getInt("id"));
                cat.setName(rs.getString("name"));
                categories.add(cat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
    
    public String getCategoryNameById(int categoryId) {
    String categoryName = ""; // Giá trị mặc định nếu không tìm thấy
    String sql = "SELECT name FROM categories WHERE id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setInt(1, categoryId);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            categoryName = rs.getString("name");
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return categoryName;
}
}