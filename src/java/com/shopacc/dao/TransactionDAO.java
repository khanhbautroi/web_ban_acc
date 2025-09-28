package com.shopacc.dao;

import com.shopacc.model.PurchaseHistoryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;

public class TransactionDAO {

    /**
     * Ghi lại một giao dịch vào database.
     * @param userId ID của người dùng thực hiện.
     * @param type Loại giao dịch ('purchase', 'deposit', 'withdrawal').
     * @param amount Số tiền.
     * @param description Mô tả ngắn.
     */
    // Sửa lại phương thức này trong TransactionDAO.java
public int logTransaction(int userId, Integer accountId , String vnp_TxnRef, String type, double amount, String description) {
    String sql = "INSERT INTO transactions (user_id, account_id, vnp_TxnRef ,type, amount,  description) VALUES (?, ?, ?, ?, ?, ?)";
    int generatedId = 0; // Giá trị mặc định nếu không lấy được ID

    try (Connection conn = DatabaseConnection.getConnection();
         // Thêm Statement.RETURN_GENERATED_KEYS để yêu cầu DB trả về ID
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        ps.setInt(1, userId);
        if (accountId != null) {
            ps.setInt(2, accountId);
        } else {
            ps.setNull(2, java.sql.Types.INTEGER);
        }
        ps.setString(3, vnp_TxnRef);
        ps.setString(4, type);
        ps.setDouble(5, amount);
        ps.setString(6, description);

        ps.executeUpdate();

        // Lấy ID vừa được tạo ra
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            generatedId = rs.getInt(1);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return generatedId;
}
    
    // Thêm phương thức này vào TransactionDAO.java
// Trong file TransactionDAO.java
// Sửa lại phương thức này trong TransactionDAO.java
public List<PurchaseHistoryDTO> getPurchaseHistory(String keyword) {
    List<PurchaseHistoryDTO> historyList = new ArrayList<>();

    // Thêm điều kiện WHERE ... LIKE để tìm kiếm theo tên người mua (u.username)
    String sql = "SELECT t.id, t.amount, t.transaction_date, u.username as buyer_username, " +
                 "a.username as game_username, c.name as category_name " +
                 "FROM transactions t " +
                 "JOIN users u ON t.user_id = u.id " +
                 "JOIN accounts a ON t.account_id = a.id " +
                 "JOIN categories c ON a.category_id = c.id " +
                 "WHERE t.type = 'purchase' AND u.username LIKE ? ORDER BY t.id DESC";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        // Gán giá trị cho tham số trong câu lệnh LIKE
        ps.setString(1, "%" + keyword + "%");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            PurchaseHistoryDTO history = new PurchaseHistoryDTO();
            history.setTransactionId(rs.getInt("id"));
            history.setBuyerUsername(rs.getString("buyer_username"));
            history.setGameUsername(rs.getString("game_username"));
            history.setCategoryName(rs.getString("category_name"));
            history.setPrice(rs.getDouble("amount"));
            history.setPurchaseDate(rs.getTimestamp("transaction_date"));
            historyList.add(history);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return historyList;
}
public void deleteTransaction(int transactionId) {
    String sql = "DELETE FROM transactions WHERE id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, transactionId);
        ps.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

// Thêm phương thức này vào file TransactionDAO.java
public PurchaseHistoryDTO getPurchaseDetailsById(int transactionId) {
    PurchaseHistoryDTO history = null;
    String sql = "SELECT t.id, t.amount, t.transaction_date, u.username as buyer_username, " +
                 "a.username as game_username, a.password as game_password, c.name as category_name " +
                 "FROM transactions t " +
                 "JOIN users u ON t.user_id = u.id " +
                 "JOIN accounts a ON t.account_id = a.id " +
                 "JOIN categories c ON a.category_id = c.id " +
                 "WHERE t.id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setInt(1, transactionId);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            history = new PurchaseHistoryDTO();
            history.setTransactionId(rs.getInt("id"));
            history.setBuyerUsername(rs.getString("buyer_username"));
            history.setGameUsername(rs.getString("game_username"));
            history.setGamePassword(rs.getString("game_password"));
            history.setCategoryName(rs.getString("category_name"));
            history.setPrice(rs.getDouble("amount"));
            history.setPurchaseDate(rs.getTimestamp("transaction_date"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return history;
}
public boolean transactionExists(String vnp_TxnRef) {
    String sql = "SELECT 1 FROM transactions WHERE vnp_TxnRef = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, vnp_TxnRef);
        ResultSet rs = ps.executeQuery();
        return rs.next(); // Trả về true nếu đã tồn tại
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
public List<PurchaseHistoryDTO> getDepositHistory(String keyword) {
    List<PurchaseHistoryDTO> depositList = new ArrayList<>();
    // Thêm điều kiện WHERE ... LIKE để tìm kiếm theo tên người nạp (u.username)
    String sql = "SELECT t.id, t.amount, t.transaction_date, u.username as buyer_username, t.description " +
                 "FROM transactions t " +
                 "JOIN users u ON t.user_id = u.id " +
                 "WHERE t.type = 'deposit' AND u.username LIKE ? ORDER BY t.id DESC";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        // Gán giá trị cho tham số trong câu lệnh LIKE
        ps.setString(1, "%" + keyword + "%");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            PurchaseHistoryDTO deposit = new PurchaseHistoryDTO();
            deposit.setTransactionId(rs.getInt("id"));
            deposit.setBuyerUsername(rs.getString("buyer_username"));
            deposit.setPrice(rs.getDouble("amount"));
            deposit.setPurchaseDate(rs.getTimestamp("transaction_date"));
            deposit.setDescription(rs.getString("description"));
            depositList.add(deposit);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return depositList;
}
}