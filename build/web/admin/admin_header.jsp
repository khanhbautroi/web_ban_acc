<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <div class="admin-sidebar">
    <h2>GameStore</h2>
    <ul>
        <%-- Trong file admin_header.jsp --%>
<ul>
    <li><a href="${pageContext.request.contextPath}/trang-chu">Trang Chủ</a></li>
    
    <%-- Thêm class="active" nếu currentPage là 'dashboard' --%>
    <li class="${currentPage == 'dashboard' ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
    </li>

    <%-- Thêm class="active" nếu currentPage là 'manage_accounts' --%>
    <li class="${currentPage == 'manage_accounts' ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/admin/manage-accounts">Danh sách game</a>
    </li>

    <%-- Thêm class="active" nếu currentPage là 'manage_users' --%>
    <li class="${currentPage == 'manage_users' ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/admin/manage-users">Quản lý người dùng</a>
    </li>
    
    <%-- Thêm class="active" nếu currentPage là 'history' --%>
    <li class="${currentPage == 'history' ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/admin/purchase-history">Lịch sử mua hàng</a>
    </li>
    <li class="${currentPage == 'manage_deposits' ? 'active' : ''}">
    <a href="${pageContext.request.contextPath}/admin/manage-deposits">Quản lý nạp tiền</a>
</li>
</ul>
    </ul>
</div>
<div class="admin-main-content">
    