package com.shopacc.controller;

import com.shopacc.dao.UserDAO;
import com.shopacc.model.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import com.shopacc.dao.AccountDAO;      // <-- Thêm dòng này
import com.shopacc.dao.TransactionDAO;  // <-- Thêm dòng này

@WebServlet("/process-deposit")
public class ProcessDepositController extends HttpServlet {
    // Trong file ProcessDepositController.java
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("account");

    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Double amount = (Double) session.getAttribute("depositAmount");

    if (amount == null) {
        response.sendRedirect("trang-chu");
        return;
    }

    session.removeAttribute("depositAmount");

    UserDAO userDAO = new UserDAO();
    TransactionDAO transactionDAO = new TransactionDAO();

    double newBalance = user.getBalance() + amount;
    userDAO.updateBalance(user.getId(), newBalance);

    // ---- SỬA LẠI DÒNG NÀY ----
    // Truyền null cho vnp_TxnRef vì đây là giao dịch mô phỏng
    transactionDAO.logTransaction(user.getId(), null, null, "deposit", amount, "Nạp tiền thành công (mô phỏng).");

    user.setBalance(newBalance);
    session.setAttribute("account", user);

    session.setAttribute("depositSuccessMessage", "Bạn đã nạp tiền thành công!");
    response.sendRedirect("cart");
}
}