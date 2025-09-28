package com.shopacc.controller;

import com.shopacc.dao.UserDAO;
import com.shopacc.model.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
    String pass = request.getParameter("password");

    UserDAO dao = new UserDAO();
    User user = dao.getUserByUsername(username);

    if (user != null && user.getPassword().equals(pass)) {
        // Đăng nhập thành công
        HttpSession session = request.getSession();
        session.setAttribute("account", user);
        response.sendRedirect("trang-chu");
    } else {
        // Đăng nhập thất bại
        HttpSession session = request.getSession();
        // Đặt thông báo lỗi vào SESSION
        session.setAttribute("loginError", "Bạn đã nhập sai tài khoản hoặc mật khẩu.");
        // Dùng sendRedirect để tải lại trang
        response.sendRedirect("login.jsp");
        }
    }
}