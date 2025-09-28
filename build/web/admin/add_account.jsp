<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="admin_header.jsp" />

<div class="page-header">
    <h1>Thêm tài khoản game mới</h1>
    <p>Nhập thông tin chi tiết cho tài khoản</p>
</div>

<div class="form-container">
    <form action="add-account" method="post">
        <div class="form-grid">
            <%-- Cột trái --%>
            <div class="form-column">
                <div class="form-group">
                    <label>Danh mục game *</label>
                    <select name="categoryId" required>
                        <option value="">-- Chọn danh mục --</option>
                        <c:forEach items="${categories}" var="cat">
                            <option value="${cat.id}">${cat.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Tên tài khoản *</label>
                    <input type="text" name="username" placeholder="Nhập tên tài khoản game" required>
                </div>
            </div>
            <%-- Cột phải --%>
            <div class="form-column">
                <div class="form-group">
                    <label>Mật khẩu *</label>
                    <input type="text" name="password" placeholder="Nhập mật khẩu của tài khoản game" required>
                </div>
                <div class="form-group">
                    <label>Giá tiền *</label>
                    <input type="number" name="price" placeholder="0" required>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label>Mô tả</label>
            <textarea name="description" rows="4" placeholder="Mô tả các vật phẩm, rank, thông tin nổi bật..."></textarea>
        </div>
        <div class="form-group">
            <label>URL Hình ảnh</label>
            <input type="text" name="imageUrl" placeholder="Ví dụ: images/ten_anh.jpg">
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Thêm tài khoản</button>
            <a href="manage-accounts" class="btn btn-secondary">Hủy bỏ</a>
        </div>
    </form>
</div>

<jsp:include page="admin_footer.jsp" />