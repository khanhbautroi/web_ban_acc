<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />

<main>
    <div class="container">
        <div class="order-success-container">
            <div class="success-icon">✓</div>
            <h1>Thanh toán thành công!</h1>
            <p>Cảm ơn bạn đã mua hàng. Thông tin chi tiết đã được gửi đến email của bạn.</p>
            <p>Mã đơn hàng của bạn là: <strong>#${param.orderId}</strong></p>
            
            <div class="action-links">
                <a href="download-receipt?id=${param.orderId}" class="link-download">Tải hóa đơn (.txt)</a>
                <a href="trang-chu" class="link-home">Quay về trang chủ</a>
            </div>
        </div>
    </div>
</main>

<jsp:include page="footer.jsp" />