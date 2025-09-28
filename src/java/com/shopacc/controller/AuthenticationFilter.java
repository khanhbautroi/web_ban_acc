package com.shopacc.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// Áp dụng Filter cho tất cả các đường dẫn cần bảo vệ
@WebFilter(urlPatterns = {
    "/cart", "/add-to-cart", "/checkout", "/deposit", "/simulate-payment", 
    "/order-success", "/download-receipt", "/admin/*"
})
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false); // false = không tạo session mới nếu chưa có

        // Kiểm tra xem người dùng đã đăng nhập chưa (session có tồn tại và có attribute "account" không)
        boolean isLoggedIn = (session != null && session.getAttribute("account") != null);

        if (isLoggedIn) {
            // Nếu đã đăng nhập, cho phép yêu cầu đi tiếp đến servlet đích
            chain.doFilter(request, response);
        } else {
            // Nếu chưa đăng nhập, đặt thông báo và chuyển hướng đến trang đăng nhập
            HttpSession newSession = httpRequest.getSession(true); // Tạo session mới để lưu thông báo
            newSession.setAttribute("loginMessage", "Vui lòng đăng nhập vào tài khoản!");
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
        }
    }

    // Các phương thức init() và destroy() có thể để trống
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}