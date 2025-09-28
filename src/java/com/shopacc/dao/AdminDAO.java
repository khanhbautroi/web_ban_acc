package com.shopacc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class AdminDAO {

    /**
     * Phương thức chung để đếm số dòng trong một bảng với điều kiện.
     * @param query Câu lệnh SQL COUNT(*), ví dụ: "SELECT COUNT(*) FROM users"
     * @return Số lượng đếm được.
     */
    private int getCount(String query) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Phương thức chung để tính tổng một cột.
     * @param query Câu lệnh SQL SUM(amount), ví dụ: "SELECT SUM(amount) FROM transactions WHERE type = 'deposit'"
     * @return Tổng giá trị.
     */
    private double getSum(String query) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    // --- Các phương thức lấy thống kê cụ thể ---

    public int getTotalAccountCount() {
        return getCount("SELECT COUNT(*) FROM accounts");
    }

    public int getSoldCount() {
        return getCount("SELECT COUNT(*) FROM accounts WHERE status = 'sold'");
    }

    public int getTotalUserCount() {
        return getCount("SELECT COUNT(*) FROM users");
    }

    public double getTotalDeposit() {
        return getSum("SELECT SUM(amount) FROM transactions WHERE type = 'deposit'");
    }
    
    public double getTotalWithdraw() {
        return getSum("SELECT SUM(amount) FROM transactions WHERE type = 'withdrawal'");
    }

    public double getTotalPurchase() {
        return getSum("SELECT SUM(amount) FROM transactions WHERE type = 'purchase'");
    }
    
    // Thêm phương thức này vào AdminDAO.java
public double getRevenueByPeriod(String period) {
    String dateCondition = "";

    // Dùng hàm WEEK(date, 1) của MySQL để tuần bắt đầu từ Thứ Hai
    if ("week".equals(period)) {
        dateCondition = " AND YEAR(transaction_date) = YEAR(CURDATE()) AND WEEK(transaction_date, 1) = WEEK(CURDATE(), 1)";
    } 
    // Bạn có thể thêm các trường hợp khác như "month", "year" ở đây

    String sql = "SELECT SUM(amount) FROM transactions WHERE type = 'purchase'" + dateCondition;

    // Giả sử bạn đã có phương thức getSum(String sql) từ trước
    // Nếu chưa, hãy tạo nó hoặc thay thế bằng code truy vấn trực tiếp
    return getSum(sql); 
}
public Map<String, Double> getDailyRevenueLast7Days() {
    // Dùng LinkedHashMap để đảm bảo thứ tự các ngày là đúng
    Map<String, Double> dailyRevenue = new LinkedHashMap<>();

    // Câu lệnh SQL này sẽ nhóm các giao dịch theo ngày và tính tổng doanh thu mỗi ngày
    // Chỉ lấy dữ liệu của 7 ngày gần nhất
    String sql = "SELECT DATE(transaction_date) as sale_date, SUM(amount) as daily_total " +
                 "FROM transactions " +
                 "WHERE type = 'purchase' AND transaction_date >= CURDATE() - INTERVAL 6 DAY " +
                 "GROUP BY DATE(transaction_date) " +
                 "ORDER BY sale_date ASC";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            // Lấy ngày và doanh thu, sau đó thêm vào Map
            dailyRevenue.put(rs.getString("sale_date"), rs.getDouble("daily_total"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return dailyRevenue;
}
}