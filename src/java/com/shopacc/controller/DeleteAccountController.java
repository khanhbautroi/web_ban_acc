package com.shopacc.controller;

import com.shopacc.dao.AccountDAO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/delete-account")
public class DeleteAccountController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.deleteAccount(id);
        response.sendRedirect("manage-accounts");
    }
}