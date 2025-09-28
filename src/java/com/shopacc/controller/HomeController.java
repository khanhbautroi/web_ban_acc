package com.shopacc.controller;

import com.shopacc.dao.AccountDAO;
import com.shopacc.model.Account;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/trang-chu")
public class HomeController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Luôn lấy các tham số lọc từ request
        String keyword = request.getParameter("keyword");
        String priceRange = request.getParameter("priceRange");
        String sortBy = request.getParameter("sortBy");

        // 2. Luôn gọi phương thức filterAccounts từ DAO
        AccountDAO accountDAO = new AccountDAO();
        List<Account> accountList = accountDAO.filterAccounts(keyword, priceRange, sortBy);

        // 3. Luôn gửi danh sách kết quả và các giá trị lọc về cho JSP
        request.setAttribute("accountList", accountList);
        request.setAttribute("keyword", keyword);
        request.setAttribute("priceRange", priceRange);
        request.setAttribute("sortBy", sortBy);
        
        // 4. Chuyển tiếp đến index.jsp
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}