<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />

<main>
    <%-- Áp dụng lại cấu trúc và class từ trang deposit --%>
    <div class="container deposit-page-container">
        <div class="deposit-form-wrapper">
            <h2 style="font-size: 22px;">Đang chờ xác nhận thanh toán</h2>
            <p style="color: #555; max-width: 450px; margin: 0 auto 30px auto;">
            </p>
            <img src="images/qr_code.jpg" alt="Mô phỏng QR Code" style="max-width: 280px; margin-bottom: 20px;">
            
            

            <form action="process-deposit" method="post">
                <button type="submit" class="btn-checkout" style="width: 100%; max-width: 300px;">
                    Tôi đã hoàn tất thanh toán
                </button>
            </form>

        </div>
    </div>
</main>

<jsp:include page="footer.jsp" />