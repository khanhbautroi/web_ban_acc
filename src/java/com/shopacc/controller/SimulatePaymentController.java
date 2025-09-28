package com.shopacc.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/simulate-payment")
public class SimulatePaymentController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Bảo mật: Đảm bảo người dùng đã đăng nhập
        if (session.getAttribute("account") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // 1. Lấy số tiền từ form nạp tiền
            double amount = Double.parseDouble(request.getParameter("amount"));
            
            // 2. Lưu tạm số tiền này vào session
            session.setAttribute("depositAmount", amount);
            
            // 3. Chuyển người dùng đến trang thanh toán giả lập
            request.getRequestDispatcher("fake_payment_page.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("deposit.jsp"); // Quay lại nếu có lỗi
        }
    }
}