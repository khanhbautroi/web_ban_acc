package com.shopacc.controller;

import com.google.gson.JsonObject;
import com.shopacc.config.Config;
import com.shopacc.dao.TransactionDAO;
import com.shopacc.dao.UserDAO;
import com.shopacc.model.User;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/vnpay-ipn")
public class IPNController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject job = new JsonObject();
        try {
            // Bước 1: Đọc tất cả tham số từ VNPAY gửi sang
            Map<String, String> fields = new HashMap<>();
            for (Enumeration<String> params = req.getParameterNames(); params.hasMoreElements();) {
                String fieldName = params.nextElement();
                String fieldValue = req.getParameter(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    fields.put(fieldName, fieldValue);
                }
            }

            String vnp_SecureHash = req.getParameter("vnp_SecureHash");
            fields.remove("vnp_SecureHashType");
            fields.remove("vnp_SecureHash");

            // Bước 2: Xác thực chữ ký (Checksum)
            // Sắp xếp các trường theo thứ tự alphabet
            List<String> fieldNames = new ArrayList<>(fields.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            Iterator<String> itr = fieldNames.iterator();
            while (itr.hasNext()) {
                String fieldName = itr.next();
                String fieldValue = fields.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLDecoder.decode(fieldValue, StandardCharsets.UTF_8.toString()));
                    if (itr.hasNext()) {
                        hashData.append('&');
                    }
                }
            }
            String signValue = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());

            if (signValue.equals(vnp_SecureHash)) {
                // Bước 3: Kiểm tra logic nghiệp vụ
                String vnp_TxnRef = req.getParameter("vnp_TxnRef");
                String vnp_ResponseCode = req.getParameter("vnp_ResponseCode");
                long amount = Long.parseLong(req.getParameter("vnp_Amount")) / 100;
                
                TransactionDAO transactionDAO = new TransactionDAO();
                boolean transactionExists = transactionDAO.transactionExists(vnp_TxnRef);

                if (transactionExists) {
                    // Lỗi: Giao dịch đã được xử lý rồi
                    job.addProperty("RspCode", "02");
                    job.addProperty("Message", "Order already confirmed");
                } else {
                    if ("00".equals(vnp_ResponseCode)) {
                        // Giao dịch thành công từ VNPAY, tiến hành cộng tiền
                        String orderInfo = req.getParameter("vnp_OrderInfo");
                        String username = orderInfo.substring(orderInfo.lastIndexOf(":") + 2); // Trích xuất username
                        
                        UserDAO userDAO = new UserDAO();
                        User user = userDAO.getUserByUsername(username);
                        
                        if (user != null) {
                            // Cập nhật số dư và ghi log
                            double newBalance = user.getBalance() + amount;
                            userDAO.updateBalance(user.getId(), newBalance);
                            transactionDAO.logTransaction(user.getId(), null, vnp_TxnRef, "deposit", amount, "Nạp tiền thành công qua VNPAY");
                            
                            System.out.println("LOG: Da cong " + amount + " cho user " + username);
                            job.addProperty("RspCode", "00");
                            job.addProperty("Message", "Confirm Success");
                        } else {
                            job.addProperty("RspCode", "01");
                            job.addProperty("Message", "User not found");
                        }
                    } else {
                        // Giao dịch thất bại từ VNPAY
                        job.addProperty("RspCode", "01");
                        job.addProperty("Message", "Transaction failed");
                    }
                }
            } else {
                // Chữ ký không hợp lệ
                job.addProperty("RspCode", "97");
                job.addProperty("Message", "Invalid Checksum");
            }

        } catch (Exception e) {
            e.printStackTrace();
            job.addProperty("RspCode", "99");
            job.addProperty("Message", "Unknown error");
        }
        
        resp.getWriter().write(job.toString());
    }
}