package com.shopacc.controller;

import com.shopacc.dao.UserDAO;
import com.shopacc.model.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");

    String username = request.getParameter("username");
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    UserDAO userDAO = new UserDAO();

    // 1. Kiểm tra xem username đã tồn tại chưa
    if (userDAO.getUserByUsername(username) != null) {
        request.setAttribute("error", "Tên đăng nhập đã tồn tại, vui lòng nhập lại.");
        request.getRequestDispatcher("register.jsp").forward(request, response);
        return;
    }

    // --- THÊM KHỐI KIỂM TRA EMAIL ---
    // 2. Kiểm tra xem email đã tồn tại chưa
    if (userDAO.checkEmailExists(email)) {
        request.setAttribute("error", "Email đã được sử dụng, vui lòng nhập lại.");
        request.getRequestDispatcher("register.jsp").forward(request, response);
        return;
    }
    // --- KẾT THÚC PHẦN THÊM MỚI ---

    // 3. Nếu tất cả đều hợp lệ, tiến hành đăng ký
    User newUser = new User();
    newUser.setUsername(username);
    newUser.setEmail(email);
    newUser.setPassword(password);

    if (userDAO.registerUser(newUser)) {
        HttpSession session = request.getSession();
        session.setAttribute("registerSuccess", "Đăng ký thành công! Vui lòng đăng nhập.");
        response.sendRedirect("login.jsp");
    } else {
        request.setAttribute("error", "Đã có lỗi xảy ra do trùng lặp dữ liệu. Vui lòng thử lại!");
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}
}