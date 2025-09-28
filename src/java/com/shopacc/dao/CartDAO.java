package com.shopacc.dao;

import com.shopacc.model.Account;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    // Thêm sản phẩm vào giỏ hàng
        public boolean addToCart(int userId, int accountId, int quantity) {
        String sql = "INSERT INTO cart_items (user_id, account_id, quantity) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, accountId);
            ps.setInt(3, quantity);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            // In lỗi ra console để bạn có thể thấy nó trong Nhật ký Server
            System.err.println("Lỗi SQL khi thêm vào giỏ hàng: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateCartQuantity(int userId, int accountId, int quantity) {
        // Phương thức này có thể chưa được dùng đến nhưng nên có sẵn
        String sql = "UPDATE cart_items SET quantity = quantity + ? WHERE user_id = ? AND account_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, userId);
            ps.setInt(3, accountId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy tất cả sản phẩm trong giỏ hàng của một người dùng
// Trong file CartDAO.java
public List<Account> getCartItemsByUserId(int userId) {
    List<Account> cartItems = new ArrayList<>();
    
    // SỬA LẠI CÂU LỆNH SQL Ở ĐÂY
    String sql = "SELECT a.*, c.name as game_name FROM accounts a " +
                 "JOIN cart_items ci ON a.id = ci.account_id " +
                 "JOIN categories c ON a.category_id = c.id " + // Thêm JOIN với categories
                 "WHERE ci.user_id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Account acc = new Account();
            acc.setId(rs.getInt("id"));
            acc.setDescription(rs.getString("description"));
            acc.setPrice(rs.getBigDecimal("price"));
            acc.setImageUrl(rs.getString("image_url"));
            
            // THÊM LẠI DÒNG NÀY ĐỂ LẤY TÊN GAME
            acc.setGameName(rs.getString("game_name"));
            
            cartItems.add(acc);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return cartItems;
}
public boolean removeFromCart(int userId, int accountId) {
    // Xóa một tài khoản cụ thể khỏi giỏ hàng của một người dùng
    String sql = "DELETE FROM cart_items WHERE user_id = ? AND account_id = ? LIMIT 1";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, userId);
        ps.setInt(2, accountId);

        // Trả về true nếu có ít nhất 1 dòng bị xóa
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
public boolean clearCart(int userId) {
    String sql = "DELETE FROM cart_items WHERE user_id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, userId);
        ps.executeUpdate(); // Không cần kiểm tra kết quả, chỉ cần xóa
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

}