package com.shopacc.controller;

import com.shopacc.dao.TransactionDAO;
import com.shopacc.dao.UserDAO;
import com.shopacc.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/vnpay-return")
public class VNPayReturnServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");

        // Bắt buộc phải có người dùng đăng nhập
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String status = request.getParameter("vnp_ResponseCode");
        String message;

        // BƯỚC 1: KIỂM TRA TRẠNG THÁI GIAO DỊCH TỪ VNPAY
        if ("00".equals(status)) {
            // Giao dịch thành công
            String vnp_TxnRef = request.getParameter("vnp_TxnRef");
            long amount = Long.parseLong(request.getParameter("vnp_Amount")) / 100;
            
            TransactionDAO transactionDAO = new TransactionDAO();
            
            // BƯỚC 2: KIỂM TRA XEM GIAO DỊCH NÀY ĐÃ ĐƯỢC XỬ LÝ TRƯỚC ĐÓ CHƯA
            if (!transactionDAO.transactionExists(vnp_TxnRef)) {
                // Nếu chưa, tiến hành cộng tiền và ghi log
                UserDAO userDAO = new UserDAO();
                double newBalance = user.getBalance() + amount;
                userDAO.updateBalance(user.getId(), newBalance);
                
                // Ghi lại giao dịch
                transactionDAO.logTransaction(user.getId(), null, vnp_TxnRef, "deposit", amount, "Nạp tiền thành công qua VNPAY");

                // BƯỚC 3: CẬP NHẬT LẠI SESSION ĐỂ KHÔNG BỊ ĐĂNG XUẤT
                user.setBalance(newBalance);
                session.setAttribute("account", user);
                
                message = "Giao dịch thành công! Số dư của bạn đã được cập nhật.";
            } else {
                message = "Giao dịch đã được xử lý trước đó.";
            }
        } else {
            // Giao dịch thất bại
            message = "Giao dịch thất bại. Vui lòng thử lại.";
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("/payment_result.jsp").forward(request, response);
    }
}