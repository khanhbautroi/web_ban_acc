package com.shopacc.controller;

import com.shopacc.dao.AccountDAO;
import com.shopacc.model.Account;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/product-detail") // Đảm bảo URL mapping đúng
public class ProductDetailController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1. Lấy ID từ URL
            int id = Integer.parseInt(request.getParameter("id"));
            
            // 2. Gọi DAO để tìm tài khoản
            AccountDAO dao = new AccountDAO();
            Account account = dao.getAccountById(id);
            
            // 3. Đặt đối tượng tài khoản vào request để gửi cho JSP
            // Tên attribute phải là "accountDetail" để JSP có thể nhận
            request.setAttribute("accountDetail", account);
            
            // 4. Chuyển tiếp đến trang JSP
            request.getRequestDispatcher("product-detail.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            // Xử lý trường hợp id không phải là số
            response.sendRedirect("trang-chu");
            e.printStackTrace();
        }
    }
}