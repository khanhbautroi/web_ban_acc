package com.shopacc.controller;

import com.google.gson.Gson;
import com.shopacc.dao.AdminDAO; // Import AdminDAO
import com.shopacc.model.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/admin/dashboard")
public class AdminDashboardController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");

        // BẢO MẬT: Kiểm tra xem người dùng có phải là admin không
        if (user == null || !"admin".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/trang-chu");
            return;
        }
        
        // --- Bắt đầu lấy dữ liệu thật từ DAO ---
        AdminDAO adminDAO = new AdminDAO();
        
        int totalAccounts = adminDAO.getTotalAccountCount();
        int soldAccounts = adminDAO.getSoldCount();
        int availableAccounts = totalAccounts - soldAccounts;
        int totalUsers = adminDAO.getTotalUserCount();
        double totalDeposit = adminDAO.getTotalDeposit();
        //double totalWithdraw = adminDAO.getTotalWithdraw();
        double totalPurchase = adminDAO.getTotalPurchase();
        double weeklyRevenue = adminDAO.getRevenueByPeriod("week");
        
        
        // Bước 1: Lấy dữ liệu doanh thu thực tế từ database (có thể bị thiếu ngày)
        Map<String, Double> dbRevenueData = adminDAO.getDailyRevenueLast7Days();

        // Bước 2: Tạo một map đầy đủ 7 ngày gần nhất với doanh thu mặc định là 0
        Map<String, Double> finalRevenueData = new LinkedHashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();

        for (int i = 6; i >= 0; i--) {
            LocalDate date = today.minusDays(i);
            finalRevenueData.put(date.format(formatter), 0.0);
        }

        // Bước 3: Cập nhật doanh thu thực tế từ database vào map đầy đủ
        for (Map.Entry<String, Double> entry : dbRevenueData.entrySet()) {
            finalRevenueData.put(entry.getKey(), entry.getValue());
        }

        // Bước 4: Chuyển đổi map hoàn chỉnh thành JSON cho biểu đồ
        Gson gson = new Gson();
        String chartLabels = gson.toJson(finalRevenueData.keySet());
        String chartData = gson.toJson(finalRevenueData.values());

        request.setAttribute("chartLabels", chartLabels);
        request.setAttribute("chartData", chartData);
        // Đặt các thuộc tính để JSP có thể truy cập
        request.setAttribute("totalAccounts", totalAccounts);
        request.setAttribute("soldAccounts", soldAccounts);
        request.setAttribute("availableAccounts", availableAccounts);
        request.setAttribute("totalUsers", totalUsers);
        request.setAttribute("totalDeposit", totalDeposit);
        //request.setAttribute("totalWithdraw", totalWithdraw);
        request.setAttribute("totalPurchase", totalPurchase);
        request.setAttribute("weeklyRevenue", weeklyRevenue);
        request.setAttribute("currentPage", "dashboard"); 
        // Chuyển tiếp đến trang dashboard
        
        request.getRequestDispatcher("/admin/admin_dashboard.jsp").forward(request, response);
    }
}