package com.shopacc.controller;

import com.shopacc.dao.AccountDAO;
import com.shopacc.model.Account;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Hỗ trợ tiếng Việt cho từ khóa tìm kiếm
        request.setCharacterEncoding("UTF-8");

        // 1. Lấy từ khóa tìm kiếm từ form
        String query = request.getParameter("search_query");

        // 2. Gọi DAO để thực hiện tìm kiếm
        AccountDAO dao = new AccountDAO();
        List<Account> searchResults = dao.searchByName(query);

        // 3. Gửi danh sách kết quả đến trang JSP
        // Ta đặt tên attribute là "listAccounts" để trang index.jsp có thể tái sử dụng
        request.setAttribute("listAccounts", searchResults);

        // 4. Chuyển hướng đến trang index.jsp để hiển thị kết quả
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}