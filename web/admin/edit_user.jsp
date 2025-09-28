<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="admin_header.jsp" />

<div class="page-header">
    <h1>Chỉnh sửa Người dùng #${user.id}</h1>
</div>

<div class="form-container">
    <form action="edit-user" method="post">
        <input type="hidden" name="id" value="${user.id}">
        <div class="form-grid">
            <%-- Cột trái --%>
            <div class="form-column">
                <div class="form-group">
                    <label>Tên tài khoản (Username):</label>
                    <input type="text" name="username" value="${user.username}" required>
                </div>
                <div class="form-group">
                    <label>Email:</label>
                    <input type="email" name="email" value="${user.email}" required>
                </div>
            </div>
            <%-- Cột phải --%>
            <div class="form-column">
                <div class="form-group">
                    <label>Mật khẩu (Để trống nếu không muốn đổi):</label>
                    <input type="password" name="password" value="${user.password}">
                </div>
                <div class="form-group">
                    <label>Cấp bậc (Role):</label>
                    <select name="role">
                        <option value="user" ${user.role == 'user' ? 'selected' : ''}>Member</option>
                        <option value="admin" ${user.role == 'admin' ? 'selected' : ''}>Admin</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label>Số dư:</label>
            <input type="number" name="balance" value="${user.balance}" placeholder="0" step="1000" min="1000">
        </div>
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Cập nhật</button>
            <a href="manage-users" class="btn btn-secondary">Hủy bỏ</a>
        </div>
    </form>
</div>

<jsp:include page="admin_footer.jsp" />