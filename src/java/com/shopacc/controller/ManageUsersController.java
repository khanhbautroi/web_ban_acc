package com.shopacc.controller;

import com.shopacc.dao.UserDAO;
import com.shopacc.model.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/manage-users")
public class ManageUsersController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User adminUser = (User) session.getAttribute("account");

        // Bảo mật: Chỉ admin mới được truy cập
        if (adminUser == null || !"admin".equals(adminUser.getRole())) {
            response.sendRedirect(request.getContextPath() + "/trang-chu");
            return;
        }

        String keyword = request.getParameter("keyword");
        if (keyword == null) {
            keyword = ""; // Nếu không tìm kiếm, từ khóa là rỗng để lấy tất cả
        }

        UserDAO userDAO = new UserDAO();
        // Gọi phương thức tìm kiếm mới
        List<User> userList = userDAO.searchUsers(keyword); 

        request.setAttribute("userList", userList);
        request.setAttribute("keyword", keyword); // Gửi lại từ khóa để hiển thị trong ô input
        request.setAttribute("currentPage", "manage_users");
        request.getRequestDispatcher("/admin/manage_users.jsp").forward(request, response);
    }
}