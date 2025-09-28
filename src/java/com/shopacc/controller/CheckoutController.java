package com.shopacc.controller;

import com.shopacc.dao.CartDAO;
import com.shopacc.dao.UserDAO;
import com.shopacc.model.Account;
import com.shopacc.model.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import com.shopacc.dao.AccountDAO;      // <-- Thêm dòng này
import com.shopacc.dao.TransactionDAO;  // <-- Thêm dòng này

@WebServlet("/checkout")
public class CheckoutController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("account");

    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    CartDAO cartDAO = new CartDAO();
    UserDAO userDAO = new UserDAO();
    AccountDAO accountDAO = new AccountDAO();
    TransactionDAO transactionDAO = new TransactionDAO();

    List<Account> cartItems = cartDAO.getCartItemsByUserId(user.getId());
    double totalPrice = 0;
    for (Account item : cartItems) {
        totalPrice += item.getPrice().doubleValue();
    }

    if (user.getBalance() >= totalPrice) {
        // TRƯỜNG HỢP THÀNH CÔNG
        double newBalance = user.getBalance() - totalPrice;
        userDAO.updateBalance(user.getId(), newBalance);
        
        Integer orderId = null; 
        for (Account item : cartItems) {
            accountDAO.updateAccountStatus(item.getId(), "sold");
            String description = "Mua tài khoản game: " + item.getUsername();
            int currentTransactionId = transactionDAO.logTransaction(user.getId(), item.getId(), null, "purchase", item.getPrice().doubleValue(), description);
            if (orderId == null) {
                orderId = currentTransactionId;
            }
        }
        
        cartDAO.clearCart(user.getId());
        
        // ---- PHẦN QUAN TRỌNG ĐỂ KHÔNG BỊ ĐĂNG XUẤT ----
        // 1. Cập nhật lại số dư cho đối tượng user hiện tại
        user.setBalance(newBalance);
        // 2. Đặt lại đối tượng user đã cập nhật vào session
        session.setAttribute("account", user); 
        // ----------------------------------------------
        
        // Chuyển hướng đến trang thành công
        response.sendRedirect("order-success?orderId=" + orderId);

    } else {
        // TRƯỜNG HỢP THẤT BẠI
        session.setAttribute("depositMessage", "Tài khoản của bạn không đủ để thực hiện giao dịch.");
        response.sendRedirect("deposit");
    }
}
}