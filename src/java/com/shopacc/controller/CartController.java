package com.shopacc.controller;

import com.shopacc.dao.CartDAO;
import com.shopacc.model.Account;
import com.shopacc.model.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class CartController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");

        // 1. Bắt buộc người dùng phải đăng nhập
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // 2. Gọi DAO để lấy danh sách sản phẩm trong giỏ
        CartDAO cartDAO = new CartDAO();
        List<Account> cartItems = cartDAO.getCartItemsByUserId(user.getId());

        // 3. (Quan trọng) Tính tổng tiền và gửi đi
        double totalPrice = 0;
        for (Account item : cartItems) {
            totalPrice += item.getPrice().doubleValue();
        }

        // 4. Gửi danh sách và tổng tiền qua cho trang JSP
        request.setAttribute("cartItems", cartItems);
        request.setAttribute("totalPrice", totalPrice);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
}