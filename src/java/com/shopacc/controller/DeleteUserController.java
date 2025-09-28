package com.shopacc.controller;

import com.shopacc.dao.UserDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/delete-user")
public class DeleteUserController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            UserDAO userDAO = new UserDAO();
            userDAO.deleteUser(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        response.sendRedirect("manage-users");
    }
}