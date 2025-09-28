package com.shopacc.controller;

import com.shopacc.dao.CartDAO;
import com.shopacc.model.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/add-to-cart")
public class AddToCartController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // Đảm bảo tên "accountId" và "quantity" khớp với form trong product-detail.jsp
            int accountId = Integer.parseInt(request.getParameter("accountId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            CartDAO cartDAO = new CartDAO();
            cartDAO.addToCart(user.getId(), accountId, quantity);

            response.sendRedirect("cart");
            
        } catch (NumberFormatException e) {
            System.err.println("Lỗi: Không lấy được accountId hoặc quantity từ form. Tên tham số có thể bị sai.");
            e.printStackTrace();
            response.sendRedirect("trang-chu");
        }
    }
}