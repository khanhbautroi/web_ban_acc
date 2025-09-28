package com.shopacc.controller;

import com.shopacc.dao.CartDAO;
import com.shopacc.model.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/remove-from-cart")
public class RemoveFromCartController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");

        // Bắt buộc người dùng phải đăng nhập
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // 1. Lấy accountId từ hidden input trong form
            int accountId = Integer.parseInt(request.getParameter("accountId"));
            
            // 2. Gọi DAO để xóa sản phẩm khỏi CSDL
            CartDAO cartDAO = new CartDAO();
            cartDAO.removeFromCart(user.getId(), accountId);
            
        } catch (NumberFormatException e) {
            // Xử lý nếu accountId không phải là số
            e.printStackTrace();
        }
        
        // 3. Chuyển hướng người dùng trở lại trang giỏ hàng để cập nhật giao diện
        response.sendRedirect("cart");
    }
}