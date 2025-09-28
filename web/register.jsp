<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />

<main>
    <div class="container auth-form">
        <h2>Đăng Ký Tài Khoản</h2>
         <c:if test="${error != null}">
            <p class="error-message">${error}</p>
        </c:if>
        <form action="register" method="post">
            <div class="form-group">
                <label for="username">Tên đăng nhập:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Mật khẩu:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-register">Đăng Ký</button>
        </form>
        <p class="auth-switch">Đã có tài khoản? <a href="login.jsp">Đăng nhập ngay</a></p>
    </div>
</main>

<jsp:include page="footer.jsp" />