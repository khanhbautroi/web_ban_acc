package com.shopacc.dao;

import com.shopacc.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    /**
     * Kiểm tra xem một người dùng đã tồn tại trong DB dựa trên username chưa.
     * @param username Tên đăng nhập cần kiểm tra.
     * @return User object nếu tìm thấy, ngược lại trả về null.
     */
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getString("role"));
                    user.setBalance(rs.getDouble("balance"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Không tìm thấy người dùng
    }

    /**
     * Thêm một người dùng mới vào cơ sở dữ liệu.
     * @param user Đối tượng User chứa thông tin đăng ký.
     * @return true nếu đăng ký thành công, false nếu thất bại.
     */
    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            // LƯU Ý BẢO MẬT: Trong thực tế, bạn PHẢI băm (hash) mật khẩu trước khi lưu.
            // Ví dụ sử dụng thư viện BCrypt: String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            // ps.setString(2, hashedPassword);
            ps.setString(2, user.getPassword()); // Tạm thời lưu mật khẩu dạng văn bản thô
            ps.setString(3, user.getEmail());

            int result = ps.executeUpdate();
            return result > 0; // Trả về true nếu có ít nhất 1 hàng được chèn
        } catch (SQLException e) {
            // Lỗi có thể do username hoặc email đã tồn tại (vi phạm ràng buộc UNIQUE)
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateBalance(int userId, double newBalance) {
        String sql = "UPDATE users SET balance = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newBalance);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // Sửa lại phương thức này trong UserDAO.java
public List<User> searchUsers(String keyword) {
    List<User> userList = new ArrayList<>();
    // Thêm điều kiện WHERE ... LIKE để tìm kiếm
    String sql = "SELECT * FROM users WHERE username LIKE ? ORDER BY id ASC";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        // Gán giá trị cho tham số trong câu lệnh LIKE
        ps.setString(1, "%" + keyword + "%");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setRole(rs.getString("role"));
            user.setBalance(rs.getDouble("balance"));
            userList.add(user);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return userList;
}

// Thêm phương thức này vào file UserDAO.java
public void addUser(User user) {
    String sql = "INSERT INTO users (username, password, email, role, balance) VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword()); // Cần mã hóa mật khẩu
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getRole());
        ps.setDouble(5, user.getBalance());
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
// Thêm phương thức getUserById vào UserDAO.java
public User getUserById(int id) {
    String sql = "SELECT * FROM users WHERE id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setRole(rs.getString("role"));
            user.setBalance(rs.getDouble("balance"));
            return user;
        }
    } catch (Exception e) { e.printStackTrace(); }
    return null;
}

// Thêm phương thức updateUser vào UserDAO.java
public void updateUser(User user) {
    String sql = "UPDATE users SET username = ?, password = ?, email = ?, role = ?, balance = ? WHERE id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getRole());
        ps.setDouble(5, user.getBalance());
        ps.setInt(6, user.getId());
        ps.executeUpdate();
    } catch (Exception e) { e.printStackTrace(); }
}

// Thêm phương thức deleteUser vào UserDAO.java
public void deleteUser(int userId) {
    // Xóa các dữ liệu liên quan trước để tránh lỗi khóa ngoại
    try (Connection conn = DatabaseConnection.getConnection()) {
        conn.prepareStatement("DELETE FROM cart_items WHERE user_id = " + userId).executeUpdate();
        conn.prepareStatement("DELETE FROM transactions WHERE user_id = " + userId).executeUpdate();
        conn.prepareStatement("DELETE FROM users WHERE id = " + userId).executeUpdate();
    } catch (Exception e) { e.printStackTrace(); }
}

// Phương thức kiểm tra email đã tồn tại hay chưa
public boolean checkEmailExists(String email) {
    String sql = "SELECT * FROM users WHERE email = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        return rs.next(); // Trả về true nếu tìm thấy, false nếu không
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

}