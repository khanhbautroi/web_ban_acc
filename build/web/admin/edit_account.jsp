<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="admin_header.jsp" />

<div class="page-header">
    <h1>Chỉnh sửa Tài khoản #${account.id}</h1>
    <p>Cập nhật thông tin chi tiết cho tài khoản</p>
</div>

<div class="form-container">
    <form action="edit-account" method="post">
        <input type="hidden" name="id" value="${account.id}">
        
        <div class="form-grid">
            <%-- Cột trái --%>
            <div class="form-column">
                <div class="form-group">
                    <label>Tài khoản (Username) *</label>
                    <input type="text" name="username" value="${account.username}" required>
                </div>
                <div class="form-group">
                    <label>Trạng thái *</label>
                    <select name="status">
                        <option value="available" ${account.status == 'available' ? 'selected' : ''}>Còn hàng</option>
                        <option value="sold" ${account.status == 'sold' ? 'selected' : ''}>Đã bán</option>
                    </select>
                </div>
            </div>
            <%-- Cột phải --%>
            <div class="form-column">
                <div class="form-group">
                    <label>Mật khẩu *</label>
                    <input type="text" name="password" value="${account.password}" required>
                </div>
                <div class="form-group">
                    <label>Giá tiền *</label>
                    <input type="number" name="price" value="${account.price}" placeholder="0" step="1000" min="1000" required>
                </div>
            </div>
        </div>
            <div class="form-group">
                <label>URL Hình ảnh:</label>
                <input type="text" name="imageUrl" value="${account.imageUrl}" placeholder="images/ten_anh.jpg">
            </div>
        <div class="form-group">
            <label>Mô tả</label>
            <textarea name="description" rows="4">${account.description}</textarea>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Cập nhật</button>
            <a href="manage-accounts" class="btn btn-secondary">Hủy bỏ</a>
        </div>
    </form>
</div>

<jsp:include page="admin_footer.jsp" />