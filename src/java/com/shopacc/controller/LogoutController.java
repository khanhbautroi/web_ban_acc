package com.shopacc.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate(); // Hủy toàn bộ session hiện tại
        response.sendRedirect("trang-chu"); // Chuyển hướng người dùng về trang chủ
    }
}