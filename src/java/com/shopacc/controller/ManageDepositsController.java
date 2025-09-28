package com.shopacc.controller;

import com.shopacc.dao.TransactionDAO;
import com.shopacc.model.PurchaseHistoryDTO; // Tái sử dụng DTO này
import com.shopacc.model.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/manage-deposits")
public class ManageDepositsController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User adminUser = (User) session.getAttribute("account");

        if (adminUser == null || !"admin".equals(adminUser.getRole())) {
            response.sendRedirect(request.getContextPath() + "/trang-chu");
            return;
        }
        
        String keyword = request.getParameter("keyword");
        if (keyword == null) {
            keyword = ""; // Nếu không tìm kiếm, từ khóa là rỗng để lấy tất cả
        }

        TransactionDAO transactionDAO = new TransactionDAO();
        // Gọi phương thức mới để lấy lịch sử nạp tiền
        List<PurchaseHistoryDTO> depositList = transactionDAO.getDepositHistory(keyword);

        request.setAttribute("depositList", depositList);
        request.setAttribute("keyword", keyword);
        request.setAttribute("currentPage", "manage_deposits");
        request.getRequestDispatcher("/admin/manage_deposits.jsp").forward(request, response);
    }
}