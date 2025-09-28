package com.shopacc.controller;

import com.shopacc.dao.AccountDAO;
import com.shopacc.dao.CategoryDAO;
import com.shopacc.model.Account;
import com.shopacc.model.Category;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/add-account")
public class AddAccountController extends HttpServlet {

    // doGet dùng để hiển thị form
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categories = categoryDAO.getAllCategories();

        request.setAttribute("categories", categories);
        request.setAttribute("currentPage", "add_account");
        request.getRequestDispatcher("/admin/add_account.jsp").forward(request, response);
    }

    // doPost dùng để xử lý việc thêm dữ liệu
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        String description = request.getParameter("description");
        String imageUrl = request.getParameter("imageUrl");

        Account acc = new Account();
        acc.setCategoryId(categoryId);
        acc.setUsername(username);
        acc.setPassword(password);
        acc.setPrice(price);
        acc.setDescription(description);
        acc.setImageUrl(imageUrl);

        AccountDAO dao = new AccountDAO();
        dao.addAccount(acc);

        response.sendRedirect("manage-accounts");
    }
}