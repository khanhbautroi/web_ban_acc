<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />
<style>
    .supported-methods {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 10px; /* Khoảng cách giữa các mục */
    margin-top: 25px; /* Khoảng cách với form ở trên */
    color: #555;
    font-size: 15px;
}

.supported-methods img {
    height: 24px; /* Chỉnh lại chiều cao icon cho phù hợp */
    margin-left: 15px; /* Tạo khoảng cách với chữ phía trước */
}
</style>

<main>
    <div class="container deposit-page-container">
        <div class="deposit-form-wrapper">
            <h1>Nạp tiền vào tài khoản</h1>
            <p class="current-balance">Số dư hiện tại: <strong>${String.format("%,.0f", sessionScope.account.balance)} VNĐ</strong></p>

            <%-- Hiển thị thông báo lỗi (nếu có) --%>
            <c:if test="${not empty depositMessage}">
                <p class="error-message">${depositMessage}</p>
                <c:remove var="depositMessage" scope="session"/>
            </c:if>

            <form action="create-payment" method="post" id="deposit-form">
                <div class="deposit-options">
                    <h3>Chọn nhanh mệnh giá</h3>
                    <div class="price-grid">
                        <button type="button" class="price-btn" onclick="selectAmount(100000)">100.000đ</button>
                        <button type="button" class="price-btn" onclick="selectAmount(200000)">200.000đ</button>
                        <button type="button" class="price-btn" onclick="selectAmount(500000)">500.000đ</button>
                        <button type="button" class="price-btn" onclick="selectAmount(1000000)">1.000.000đ</button>
                        <button type="button" class="price-btn" onclick="selectAmount(2000000)">2.000.000đ</button>
                        <button type="button" class="price-btn" onclick="selectAmount(5000000)">5.000.000đ</button>
                    </div>
                    <hr>
                    <label for="deposit-amount-input" class="custom-amount-label">Hoặc nhập số tiền khác:</label>
                    <input type="number" id="deposit-amount-input" name="amount" class="amount-input" placeholder="0" step="1000" min="1000"required>
                </div>
                <button type="submit" class="btn-checkout">Xác nhận nạp tiền</button>
            </form>
                <%-- THÊM KHỐI MÃ MỚI NÀY VÀO --%>
        <div class="supported-methods">
            <span>Hỗ trợ các phương thức nạp:</span>
            <img src="images/the-cao-icon.jpg" alt="Thẻ cào">
            <span>Thẻ cào</span>
            <img src="images/ngan-hang-icon.jpg" alt="Ngân hàng">
            <span>Ngân hàng</span>
            <img src="images/vnpay-icon.png" alt="VNPAY">
            <span>VNPAY</span>
        </div>
        </div>
    </div>
</main>

<script>
    const amountInput = document.getElementById('deposit-amount-input');
    function selectAmount(amount) {
        amountInput.value = amount;
    }
</script>

<jsp:include page="footer.jsp" />