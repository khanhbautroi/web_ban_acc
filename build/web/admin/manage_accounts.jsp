<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="admin_header.jsp" />

<div class="page-header">
    <div>
        <h1>Danh sách Tài khoản Game</h1>
        <p>Quản lý tất cả tài khoản trên hệ thống của bạn</p>
    </div>
    <a href="add-account" class="btn btn-primary">+ Thêm tài khoản mới</a>
</div>

<form action="manage-accounts" method="get" class="search-bar">
    <input type="text" name="keyword" value="${keyword}" placeholder="Tìm theo tên game...">
    <button type="submit" class="btn btn-primary"
            style="transition: all 0.3s ease; cursor: pointer;" 
        onmouseover="this.style.backgroundColor='#0056b3'; this.style.transform='translateY(-2px)'; this.style.boxShadow='0 4px 8px rgba(0,0,0,0.2)'" 
        onmouseout="this.style.backgroundColor=''; this.style.transform=''; this.style.boxShadow=''">Tìm kiếm</button>
</form>

<div class="data-table-container">
    <table class="data-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Danh mục</th>
                <th>Tài khoản</th>
                <th>Mật khẩu</th>
                <th>Mô tả</th>
                <th>Giá</th>
                <th>Trạng thái</th>
                <th>Thao tác</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${accountList}" var="acc">
                <tr>
                    <td>${acc.id}</td>
                    <td>${acc.gameName}</td>
                    <td>${acc.username}</td>
                    <td>${acc.password}</td>
                    <td class="description-cell">${acc.description}</td>
                    <td>${String.format("%,.0f", acc.price)}đ</td>
                    <td>
                        <c:choose>
                            <c:when test="${acc.status == 'sold'}">
                                <span class="status-badge status-sold">Đã bán</span>
                            </c:when>
                            <c:otherwise>
                                <span class="status-badge status-available">Còn hàng</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="action-cell">
                        <a href="edit-account?id=${acc.id}" class="action-btn edit-btn" title="Sửa">✏️</a>
                        <a href="delete-account?id=${acc.id}" class="action-btn delete-btn" title="Xóa" onclick="return confirm('Bạn có chắc chắn muốn xóa tài khoản này không?');">🗑️</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="admin_footer.jsp" />