package com.shopacc.controller;

import com.shopacc.dao.TransactionDAO;
import com.shopacc.model.PurchaseHistoryDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download-receipt")
public class DownloadReceiptController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
            int transactionId = Integer.parseInt(request.getParameter("id"));
            TransactionDAO transactionDAO = new TransactionDAO();
            PurchaseHistoryDTO receiptDetails = transactionDAO.getPurchaseDetailsById(transactionId);
            
            if (receiptDetails != null) {
                String fileName = "hoadon_" + transactionId + ".txt";
                
                // ---- THAY ĐỔI QUAN TRỌNG: GHI VÀO THƯ MỤC TẠM ----
                // Lấy đường dẫn đến thư mục tạm của hệ điều hành
                String tempDir = System.getProperty("java.io.tmpdir");
                File file = new File(tempDir, fileName);

                // Xây dựng nội dung hóa đơn
                StringBuilder content = new StringBuilder();
                content.append("--- HOA DON MUA HANG ---\r\n\r\n");
                content.append("Ma Giao Dich: #").append(receiptDetails.getTransactionId()).append("\r\n");
                content.append("Ngay Mua: ").append(receiptDetails.getPurchaseDate()).append("\r\n");
                content.append("Nguoi Mua: ").append(receiptDetails.getBuyerUsername()).append("\r\n");
                content.append("-------------------------\r\n");
                content.append("San pham da mua:\r\n");
                content.append("- Ten game: ").append(receiptDetails.getCategoryName()).append("\r\n");
                content.append("- Tai khoan: ").append(receiptDetails.getGameUsername()).append("\r\n");
                content.append("- Mat khau: ").append(receiptDetails.getGamePassword()).append("\r\n");
                content.append("-------------------------\r\n");
                content.append("Thanh tien: ").append(String.format("%,.0f", receiptDetails.getPrice())).append(" VND\r\n");
                content.append("-------------------------\r\n\r\n");
                content.append("Cam on ban da mua hang!");

                // Ghi nội dung vào file tạm
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(content.toString());
                }

                // Đọc file và gửi cho người dùng tải về
                response.setContentType("text/plain");
                response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

                try (FileInputStream in = new FileInputStream(file);
                     OutputStream out = response.getOutputStream()) {
                    
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                }
                
                // Xóa file tạm sau khi đã gửi đi
                file.delete();
            } else {
                 response.getWriter().println("Khong tim thay thong tin giao dich.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // In ra lỗi để người dùng biết
            response.setContentType("text/html");
            response.getWriter().println("<h1>Da co loi xay ra khi xuat file.</h1><p>Vui long kiem tra log cua server.</p>");
        }
    }
}