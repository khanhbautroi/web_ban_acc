<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />

<main>
    <%-- KHỐI HIỂN THỊ CÁC LOẠI THÔNG BÁO --%>
    <%-- Thông báo đăng ký thành công --%>
    <c:if test="${not empty sessionScope.registerSuccess}">
        <div id="toast-notification" class="toast success">
            <p>${sessionScope.registerSuccess}</p>
        </div>
        <c:remove var="registerSuccess" scope="session" />
    </c:if>

    <%-- Thông báo đăng nhập thất bại --%>
    <c:if test="${not empty sessionScope.loginError}">
        <div id="toast-notification" class="toast error">
            <p>${sessionScope.loginError}</p>
        </div>
        <c:remove var="loginError" scope="session" />
    </c:if>

    <div class="container auth-form">
        <h2>Đăng Nhập</h2>

        <form action="login" method="post">
            <div class="form-group">
                <label for="username">Tên đăng nhập:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Mật khẩu:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-login">Đăng nhập</button>
        </form>
        <p class="auth-switch">Chưa có tài khoản? <a href="register.jsp">Đăng ký ngay</a></p>
    </div>
</main>

<%-- SCRIPT ĐỂ TỰ ĐỘNG ẨN THÔNG BÁO --%>
<script>
    const toast = document.getElementById('toast-notification');
    if (toast) {
        setTimeout(() => {
            toast.classList.add('fade-out');
            setTimeout(() => {
                toast.style.display = 'none';
            }, 500);
        }, 5000); // 5 giây
    }
</script>

<jsp:include page="footer.jsp" />