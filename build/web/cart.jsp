<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />

<main>
    <%-- Đặt ngay sau thẻ <main> để thông báo hiển thị --%>
    <c:if test="${not empty sessionScope.depositSuccessMessage}">
        <div id="toast-notification" class="toast">
            <p>${sessionScope.depositSuccessMessage}</p>
        </div>
        <%-- Xóa message khỏi session để nó không hiện lại --%>
        <c:remove var="depositSuccessMessage" scope="session" />
    </c:if>

    <div class="container cart-page-container">
        
        <%-- Hiển thị thông báo lỗi (nếu có) --%>
        <c:if test="${not empty errorMessage}">
            <p class="error-message">${errorMessage}</p>
        </c:if>

        <div class="cart-items-list">
            <div class="cart-header">
                <h2>Giỏ hàng <span>(${not empty cartItems ? cartItems.size() : 0} sản phẩm)</span></h2>
                <a href="clear-cart" class="remove-all-btn">Xoá tất cả</a>
            </div>

            <c:choose>
                <c:when test="${not empty cartItems}">
                    <c:forEach items="${cartItems}" var="item">
                        <div class="cart-item-card">
                            <img src="${item.imageUrl}" alt="${item.gameName}" class="item-image">
                            <div class="item-details">
                                <p class="item-name">${item.gameName}</p>
                                <%-- THÊM DÒNG NÀY ĐỂ HIỂN THỊ MÔ TẢ --%>
                                <p class="item-description">${item.description}</p>
                                <span class="item-status">Tình trạng: Còn hàng</span>
                            </div>
                            <div class="item-price">
                                ${String.format("%,.0f", item.price)}đ
                            </div>
                            <form action="remove-from-cart" method="post" style="margin:0;">
                                <input type="hidden" name="accountId" value="${item.id}">
                                <button type="submit" class="item-remove-btn">🗑️</button>
                            </form>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>Giỏ hàng của bạn đang trống.</p>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="checkout-summary">
            <h2>Thanh toán</h2>
            <div class="summary-row">
                <span>Tổng giá trị sản phẩm</span>
                <span>${String.format("%,.0f", totalPrice)}đ</span>
            </div>
            <hr>
            <div class="summary-row total">
                <span>Tổng giá trị phải thanh toán</span>
                <span>${String.format("%,.0f", totalPrice)}đ</span>
            </div>
            <div class="summary-row">
                <span>Số dư hiện tại</span>
                <span>${String.format("%,.0f", sessionScope.account.balance)}đ</span>
            </div>

            <form action="checkout" method="post">
                <button type="submit" class="btn-checkout" ${empty cartItems ? 'disabled' : ''}>Thanh Toán</button>
            </form>
        </div>

    </div>
</main>

<%-- Thêm script điều khiển thông báo vào cuối trang --%>
<script>
    // Lấy phần tử thông báo bằng ID
    const toast = document.getElementById('toast-notification');

    // Nếu phần tử này tồn tại trên trang
    if (toast) {
        // Sau 5000 mili-giây (5 giây), thực hiện hàm sau
        setTimeout(() => {
            // Thêm một class để bắt đầu hiệu ứng mờ dần
            toast.classList.add('fade-out');
            
            // Đợi hiệu ứng mờ dần kết thúc (0.5s) rồi mới ẩn hoàn toàn
            setTimeout(() => {
                toast.style.display = 'none';
            }, 500);

        }, 5000);
    }
</script>

<jsp:include page="footer.jsp" />