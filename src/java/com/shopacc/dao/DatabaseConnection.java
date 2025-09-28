package com.shopacc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // --- Thông tin cấu hình kết nối database ---
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_shopacc";
    private static final String USER = "root";
    private static final String PASS = ""; // Mật khẩu của XAMPP MySQL thường là rỗng

    /**
     * Phương thức tĩnh để tạo và trả về một kết nối đến cơ sở dữ liệu.
     * @return một đối tượng Connection hoặc null nếu có lỗi.
     */
    public static Connection getConnection() {
        try {
            // 1. Đăng ký JDBC driver
            Class.forName(JDBC_DRIVER);

            // 2. Mở một kết nối
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            // In ra lỗi để dễ dàng gỡ lỗi
            System.err.println("Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}