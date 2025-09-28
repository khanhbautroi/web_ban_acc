package com.shopacc.controller;

import com.shopacc.dao.TransactionDAO;
import com.shopacc.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/delete-transaction")
public class DeleteTransactionController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");

        // Bảo mật: Chỉ admin mới được xóa
        if (user == null || !"admin".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/trang-chu");
            return;
        }

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            TransactionDAO transactionDAO = new TransactionDAO();
            transactionDAO.deleteTransaction(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // Sau khi xóa, chuyển hướng về lại trang lịch sử
        response.sendRedirect("purchase-history");
    }
}