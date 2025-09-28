package com.shopacc.controller;

import com.shopacc.dao.TransactionDAO;
import com.shopacc.dao.UserDAO;
import com.shopacc.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/edit-user")
public class EditUserController extends HttpServlet {

    // Hiển thị form chỉnh sửa với thông tin của người dùng
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserById(id);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/admin/edit_user.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("manage-users");
        }
    }

    // Nhận dữ liệu từ form và thực hiện cập nhật
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        double newBalance = Double.parseDouble(request.getParameter("balance"));

        UserDAO userDAO = new UserDAO();
        
        // ---- PHẦN NÂNG CẤP LOGIC ----
        // 1. Lấy thông tin người dùng CŨ từ database để biết số dư cũ
        User oldUser = userDAO.getUserById(id);
        
        if (oldUser != null) {
            double oldBalance = oldUser.getBalance();

            // 2. Kiểm tra xem admin có TĂNG số dư không
            if (newBalance > oldBalance) {
                // Nếu có, tính ra khoản chênh lệch
                double depositAmount = newBalance - oldBalance;
                
                // Ghi lại giao dịch này vào bảng transactions
                TransactionDAO transactionDAO = new TransactionDAO();
                String description = "Admin cập nhật số dư.";
                transactionDAO.logTransaction(id, null, null, "deposit", depositAmount, description);
            }
            
            // 3. Xử lý việc không thay đổi mật khẩu
            String finalPassword = password;
            if (password == null || password.trim().isEmpty()) {
                // Nếu ô mật khẩu để trống, giữ lại mật khẩu cũ
                finalPassword = oldUser.getPassword();
            }
            
            // 4. Cập nhật thông tin người dùng với số dư và mật khẩu mới
            User updatedUser = new User();
            updatedUser.setId(id);
            updatedUser.setUsername(username);
            updatedUser.setEmail(email);
            updatedUser.setPassword(finalPassword);
            updatedUser.setRole(role);
            updatedUser.setBalance(newBalance);

            userDAO.updateUser(updatedUser);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    response.sendRedirect("manage-users");
    }
}