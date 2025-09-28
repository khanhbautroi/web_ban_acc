package com.shopacc.controller;

import com.shopacc.dao.AccountDAO;
import com.shopacc.model.Account;
import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/edit-account")
public class EditAccountController extends HttpServlet {

    // Hiển thị form chỉnh sửa
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            AccountDAO dao = new AccountDAO();
            Account account = dao.getAccountById(id);
            request.setAttribute("account", account);
            request.getRequestDispatcher("/admin/edit_account.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("manage-accounts");
        }
    }

    // Nhận dữ liệu từ form và cập nhật
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            
            AccountDAO dao = new AccountDAO();
            // 1. Lấy thông tin tài khoản CŨ từ database
            Account oldAccount = dao.getAccountById(id);

            if (oldAccount != null) {
                // 2. Lấy thông tin MỚI từ form
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String description = request.getParameter("description");
                String imageUrl = request.getParameter("imageUrl");
                String priceStr = request.getParameter("price");
                String status = request.getParameter("status");

                // 3. KIỂM TRA VÀ GIỮ LẠI DỮ LIỆU CŨ NẾU CẦN
                // Nếu người dùng không nhập mật khẩu mới, giữ lại mật khẩu cũ
                if (password == null || password.trim().isEmpty()) {
                    password = oldAccount.getPassword();
                }
                
                BigDecimal price = (priceStr != null && !priceStr.trim().isEmpty()) ? new BigDecimal(priceStr) : oldAccount.getPrice();

                // 4. Tạo đối tượng đã được cập nhật
                Account updatedAccount = new Account();
                updatedAccount.setId(id);
                updatedAccount.setUsername(username);
                updatedAccount.setPassword(password);
                updatedAccount.setDescription(description);
                updatedAccount.setImageUrl(imageUrl);
                updatedAccount.setPrice(price);
                updatedAccount.setStatus(status);

                // 5. Gọi DAO để lưu vào database
                dao.updateAccount(updatedAccount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        response.sendRedirect("manage-accounts");
    }
}