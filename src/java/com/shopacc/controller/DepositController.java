package com.shopacc.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/deposit")
public class DepositController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

    // Lấy thông báo từ session (do CheckoutController gửi qua)
    if (session.getAttribute("depositMessage") != null) {
        // Đặt nó vào request để trang JSP có thể hiển thị
        request.setAttribute("depositMessage", session.getAttribute("depositMessage"));
        // Xóa khỏi session để chỉ hiển thị một lần
        session.removeAttribute("depositMessage");
    }

    request.getRequestDispatcher("/deposit.jsp").forward(request, response);
    }
}