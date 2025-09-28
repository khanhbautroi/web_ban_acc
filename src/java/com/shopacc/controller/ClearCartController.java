/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopacc.controller;

import com.shopacc.dao.CartDAO;
import com.shopacc.model.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/clear-cart")
public class ClearCartController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");

        // Bắt buộc người dùng phải đăng nhập
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Gọi DAO để xóa tất cả item trong giỏ hàng của user này
        CartDAO cartDAO = new CartDAO();
        cartDAO.clearCart(user.getId());

        // Chuyển hướng người dùng về lại trang giỏ hàng
        response.sendRedirect("cart");
    }
}