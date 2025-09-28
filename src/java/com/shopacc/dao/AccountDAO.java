package com.shopacc.dao;

import com.shopacc.model.Account;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        // Câu lệnh SQL để lấy tất cả tài khoản
        String sql = "SELECT * FROM accounts";

        // Sử dụng try-with-resources để đảm bảo kết nối luôn được đóng
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Lặp qua từng dòng kết quả trả về từ database
            while (rs.next()) {
                Account acc = new Account();
                acc.setId(rs.getInt("id"));
                acc.setGameName(rs.getString("game_name"));
                acc.setDescription(rs.getString("description"));
                acc.setPrice(rs.getBigDecimal("price"));
                acc.setImageUrl(rs.getString("image_url"));
                // Thêm tài khoản vừa tạo vào danh sách
                accounts.add(acc);
            }
        } catch (SQLException e) {
            // In ra lỗi nếu có vấn đề về kết nối hoặc truy vấn
            e.printStackTrace();
        }
        // Trả về danh sách (có thể rỗng nếu không có dữ liệu hoặc có lỗi)
        return accounts;
    }
    
    // Thêm phương thức này vào trong lớp AccountDAO.java

public List<Account> searchByName(String query) {
    List<Account> accounts = new ArrayList<>();
    // Sử dụng LIKE để tìm kiếm gần đúng
    String sql = "SELECT * FROM accounts WHERE game_name LIKE ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        // Thêm dấu % để tìm kiếm bất kỳ chuỗi nào chứa từ khóa
        ps.setString(1, "%" + query + "%");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Account acc = new Account();
            acc.setId(rs.getInt("id"));
            acc.setGameName(rs.getString("game_name"));
            acc.setDescription(rs.getString("description"));
            acc.setPrice(rs.getBigDecimal("price"));
            acc.setImageUrl(rs.getString("image_url"));
            accounts.add(acc);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return accounts;
}
public Account getAccountById(int id) {
    String sql = "SELECT a.*, c.name as game_name FROM accounts a " +
                 "JOIN categories c ON a.category_id = c.id " +
                 "WHERE a.id = ?"; // Đảm bảo điều kiện WHERE là a.id
                 
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            Account acc = new Account();
            acc.setId(rs.getInt("id"));
            acc.setGameName(rs.getString("game_name"));
            acc.setDescription(rs.getString("description"));
            acc.setPrice(rs.getBigDecimal("price"));
            acc.setImageUrl(rs.getString("image_url"));
            acc.setUsername(rs.getString("username")); // <-- Thêm dòng này
            acc.setPassword(rs.getString("password")); // <-- Thêm dòng này
            return acc; // Trả về tài khoản nếu tìm thấy
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null; // Trả về null nếu không tìm thấy hoặc có lỗi
}

public List<Account> getAccountsByCategoryId(int categoryId, int limit) {
    List<Account> accounts = new ArrayList<>();
    // Thêm AND a.status = 'available' vào câu lệnh SQL
    String sql = "SELECT a.*, c.name as game_name FROM accounts a " +
                 "JOIN categories c ON a.category_id = c.id " +
                 "WHERE a.category_id = ? AND a.status = 'available' LIMIT ?";
                 
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setInt(1, categoryId);
        ps.setInt(2, limit);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            // ... code còn lại giữ nguyên
            Account acc = new Account();
            acc.setId(rs.getInt("id"));
            acc.setGameName(rs.getString("game_name"));
            acc.setDescription(rs.getString("description"));
            acc.setPrice(rs.getBigDecimal("price"));
            acc.setImageUrl(rs.getString("image_url"));
            accounts.add(acc);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return accounts;
}

public void updateAccountStatus(int accountId, String status) {
    String sql = "UPDATE accounts SET status = ? WHERE id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, status);
        ps.setInt(2, accountId);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public List<Account> searchAccountsForAdmin(String keyword) {
    List<Account> accounts = new ArrayList<>();
    // Câu lệnh SQL linh hoạt hơn với điều kiện LIKE
    String sql = "SELECT a.*, c.name as category_name FROM accounts a " +
                 "JOIN categories c ON a.category_id = c.id " +
                 "WHERE c.name LIKE ? ORDER BY a.id DESC";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        // Đặt tham số cho điều kiện LIKE
        ps.setString(1, "%" + keyword + "%");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Account acc = new Account();
            acc.setId(rs.getInt("id"));
            acc.setGameName(rs.getString("category_name"));
            acc.setDescription(rs.getString("description"));
            acc.setPrice(rs.getBigDecimal("price"));
            acc.setImageUrl(rs.getString("image_url"));
            acc.setStatus(rs.getString("status")); // Lấy trạng thái từ DB
            acc.setUsername(rs.getString("username"));
            acc.setPassword(rs.getString("password"));
            accounts.add(acc);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return accounts;
}

public void deleteAccount(int accountId) {
    // Trước tiên phải xóa các tham chiếu trong cart_items
    String deleteCartSQL = "DELETE FROM cart_items WHERE account_id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(deleteCartSQL)) {
        ps.setInt(1, accountId);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    // Sau đó mới xóa tài khoản trong bảng accounts
    String deleteAccountSQL = "DELETE FROM accounts WHERE id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(deleteAccountSQL)) {
        ps.setInt(1, accountId);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void updateAccount(Account acc) {
    String sql = "UPDATE accounts SET username = ?, password = ?, price = ?, status = ?, image_url = ?,  description = ? WHERE id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, acc.getUsername());
        ps.setString(2, acc.getPassword());
        ps.setBigDecimal(3, acc.getPrice());
        ps.setString(4, acc.getStatus());
        ps.setString(5, acc.getImageUrl());
        ps.setString(6, acc.getDescription());
        ps.setInt(7, acc.getId());
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public void addAccount(Account acc) {
    String sql = "INSERT INTO accounts (category_id, username, password, description, price, image_url, status) VALUES (?, ?, ?, ?, ?, ?, 'available')";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setInt(1, acc.getCategoryId());
        ps.setString(2, acc.getUsername());
        ps.setString(3, acc.getPassword());
        ps.setString(4, acc.getDescription());
        ps.setBigDecimal(5, acc.getPrice());
        ps.setString(6, acc.getImageUrl());
        ps.executeUpdate();
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}

// Lấy TẤT CẢ tài khoản còn hàng theo một danh mục
public List<Account> getAllAvailableAccountsByCategoryId(int categoryId) {
    List<Account> accounts = new ArrayList<>();
    String sql = "SELECT a.*, c.name as game_name FROM accounts a " +
                 "JOIN categories c ON a.category_id = c.id " +
                 "WHERE a.category_id = ? AND a.status = 'available' ORDER BY a.id DESC";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, categoryId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            // ... code gán giá trị cho đối tượng Account giữ nguyên ...
            Account acc = new Account();
            acc.setId(rs.getInt("id"));
            acc.setGameName(rs.getString("game_name"));
            acc.setDescription(rs.getString("description"));
            acc.setPrice(rs.getBigDecimal("price"));
            acc.setImageUrl(rs.getString("image_url"));
            acc.setStatus(rs.getString("status"));
            acc.setUsername(rs.getString("username"));
            acc.setPassword(rs.getString("password"));
            accounts.add(acc);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return accounts;
}

public List<Account> filterAccounts(String keyword, String priceRange, String sortBy) {
    List<Account> accounts = new ArrayList<>();
    // Bắt đầu câu lệnh SQL cơ bản
    StringBuilder sql = new StringBuilder("SELECT a.*, c.name as game_name FROM accounts a " +
                                       "JOIN categories c ON a.category_id = c.id " +
                                       "WHERE a.status = 'available' ");
    List<Object> params = new ArrayList<>();

    // Thêm điều kiện tìm kiếm theo tên game (keyword)
    if (keyword != null && !keyword.trim().isEmpty()) {
        sql.append("AND c.name LIKE ? ");
        params.add("%" + keyword + "%");
    }

    // Thêm điều kiện lọc theo giá
    if (priceRange != null && !priceRange.isEmpty()) {
        String[] prices = priceRange.split("-");
        if (prices.length == 2) {
            try {
                double minPrice = Double.parseDouble(prices[0]);
                double maxPrice = Double.parseDouble(prices[1]);
                if (maxPrice == 0) { // Xử lý trường hợp "Trên x triệu" (vd: 1000000-0)
                    sql.append("AND a.price >= ? ");
                    params.add(minPrice);
                } else {
                    sql.append("AND a.price BETWEEN ? AND ? ");
                    params.add(minPrice);
                    params.add(maxPrice);
                }
            } catch (NumberFormatException e) { /* Bỏ qua nếu giá trị không hợp lệ */ }
        }
    }

    // Thêm điều kiện sắp xếp
    if (sortBy != null && !sortBy.isEmpty()) {
        switch (sortBy) {
            case "price_asc":
                sql.append("ORDER BY a.price ASC");
                break;
            case "price_desc":
                sql.append("ORDER BY a.price DESC");
                break;
            default:
                sql.append("ORDER BY a.id DESC"); // Mặc định là mới nhất
                break;
        }
    } else {
        sql.append("ORDER BY a.id DESC");
    }

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql.toString())) {

        // Gán các giá trị trong danh sách params vào câu lệnh PreparedStatement
        for (int i = 0; i < params.size(); i++) {
            ps.setObject(i + 1, params.get(i));
        }

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            // ... code gán dữ liệu cho đối tượng Account giữ nguyên như cũ ...
            Account acc = new Account();
            acc.setId(rs.getInt("id"));
            acc.setGameName(rs.getString("game_name"));
            acc.setDescription(rs.getString("description"));
            acc.setPrice(rs.getBigDecimal("price"));
            acc.setImageUrl(rs.getString("image_url"));
            acc.setStatus(rs.getString("status"));
            acc.setUsername(rs.getString("username"));
            acc.setPassword(rs.getString("password"));
            accounts.add(acc);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return accounts;
}

}