package com.shopacc.controller;

import com.shopacc.dao.AccountDAO;
import com.shopacc.model.Account;
import com.shopacc.model.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/manage-accounts")
public class ManageAccountsController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");

        // Bảo mật: Chỉ admin mới được truy cập
        if (user == null || !"admin".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/trang-chu");
            return;
        }

        String keyword = request.getParameter("keyword");
    if (keyword == null) {
        keyword = "";
    }

    AccountDAO accountDAO = new AccountDAO();
    // Phương thức này sẽ lấy tất cả tài khoản nếu keyword là rỗng
    List<Account> accountList = accountDAO.searchAccountsForAdmin(keyword); 
    
    request.setAttribute("accountList", accountList);
    request.setAttribute("keyword", keyword);
    request.setAttribute("currentPage", "manage_accounts");
    request.getRequestDispatcher("/admin/manage_accounts.jsp").forward(request, response);
    }
}