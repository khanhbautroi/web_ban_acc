<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="admin_header.jsp" />

<div class="page-header">
    <div>
        <h1>Danh sách Người dùng</h1>
        <p>Quản lý tất cả người dùng trên hệ thống</p>
    </div>
    <a href="add-user" class="btn btn-primary">+ Thêm người dùng</a>
</div>

<form action="manage-users" method="get" class="search-bar">
    <input type="text" name="keyword" value="${keyword}" placeholder="Tìm theo tên tài khoản...">
    <button type="submit" class="btn btn-primary"
            style="transition: all 0.3s ease; cursor: pointer;" 
        onmouseover="this.style.backgroundColor='#0056b3'; this.style.transform='translateY(-2px)'; this.style.boxShadow='0 4px 8px rgba(0,0,0,0.2)'" 
        onmouseout="this.style.backgroundColor=''; this.style.transform=''; this.style.boxShadow=''">Tìm kiếm</button>
</form>

<div class="data-table-container">
    <table class="data-table">
        <thead>
            <tr>
                <th><input type="checkbox"></th>
                <th>ID</th>
                <th>Tài khoản</th>
                <th>Email</th>
                <th>Cấp bậc</th>
                <th>Số dư</th>
                <th>Trạng thái</th>
                <th>Thao tác</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td><input type="checkbox"></td>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.role == 'admin'}">
                                <span class="role-badge role-admin">Admin</span>
                            </c:when>
                            <c:otherwise>
                                <span class="role-badge role-member">Member</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${String.format("%,.0f", user.balance)}đ</td>
                    <td><span class="status-badge status-available">Active</span></td>
                    <td class="action-cell">
                        <a href="edit-user?id=${user.id}" class="action-btn edit-btn" title="Sửa">✏️</a>
                        <a href="delete-user?id=${user.id}" class="action-btn delete-btn" title="Xóa" onclick="return confirm('Bạn có chắc chắn muốn xóa người dùng này? Mọi dữ liệu liên quan (giỏ hàng, lịch sử giao dịch) cũng sẽ bị xóa!');">🗑️</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="admin_footer.jsp" />