<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="admin_header.jsp" />

<div class="page-header">
    <h1>Thêm Người Dùng Mới</h1>
</div>

<div class="form-container">
    <form action="add-user" method="post">
        <div class="form-grid">
            <%-- Cột trái --%>
            <div class="form-column">
                <div class="form-group">
                    <label>Tên tài khoản (Username):</label>
                    <input type="text" name="username" placeholder="Nhập tên đăng nhập" required>
                </div>
                <div class="form-group">
                    <label>Email:</label>
                    <input type="email" name="email" placeholder="Nhập email" required>
                </div>
            </div>
            <%-- Cột phải --%>
            <div class="form-column">
                <div class="form-group">
                    <label>Mật khẩu:</label>
                    <input type="password" name="password" placeholder="Nhập mật khẩu" required>
                </div>
                <div class="form-group">
                    <label>Cấp bậc (Role):</label>
                    <select name="role">
                        <option value="user">Member</option>
                        <option value="admin">Admin</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label>Số dư:</label>
            <input type="number" name="balance" value="0">
        </div>
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Thêm</button>
            <a href="manage-users" class="btn btn-secondary">Hủy bỏ</a>
        </div>
    </form>
</div>

<jsp:include page="admin_footer.jsp" />