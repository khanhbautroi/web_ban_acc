package com.shopacc.controller;

import com.shopacc.dao.TransactionDAO;
import com.shopacc.model.PurchaseHistoryDTO;
import com.shopacc.model.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/admin/purchase-history")
public class PurchaseHistoryController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");

        if (user == null || !"admin".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/trang-chu");
            return;
        }

        String keyword = request.getParameter("keyword");
    if (keyword == null) {
        keyword = ""; // Nếu không tìm kiếm, từ khóa là rỗng để lấy tất cả
    }

    TransactionDAO transactionDAO = new TransactionDAO();
    // Gọi phương thức tìm kiếm mới và truyền từ khóa vào
    List<PurchaseHistoryDTO> historyList = transactionDAO.getPurchaseHistory(keyword);

    request.setAttribute("historyList", historyList);
    request.setAttribute("keyword", keyword); // Gửi lại từ khóa để hiển thị trong ô input
    request.setAttribute("currentPage", "history");
    request.getRequestDispatcher("/admin/purchase_history.jsp").forward(request, response);    }
}