<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="admin_header.jsp" />

<div class="page-header">
    <h1>Lịch sử mua tài khoản</h1>
    <p>Xem tất cả lịch sử mua tài khoản game của người dùng</p>
</div>

<%-- Form tìm kiếm lịch sử --%>
<form action="purchase-history" method="get" class="search-bar">
    <input type="text" name="keyword" value="${keyword}" placeholder="Tìm theo tên người mua...">
    <button type="submit" class="btn btn-primary"
            style="transition: all 0.3s ease; cursor: pointer;" 
        onmouseover="this.style.backgroundColor='#0056b3'; this.style.transform='translateY(-2px)'; this.style.boxShadow='0 4px 8px rgba(0,0,0,0.2)'" 
        onmouseout="this.style.backgroundColor=''; this.style.transform=''; this.style.boxShadow=''">Tìm kiếm</button>
</form>

<div class="data-table-container">
    <table class="data-table">
    <thead>
        <tr>
            <th>ID Giao dịch</th>
            <th>Người mua</th>
            <th>Tài khoản</th> <%-- Thêm lại cột này --%>
            <th>Danh mục</th>
            <th>Giá</th>
            <th>Thời gian mua</th>
            <th>Thao tác</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${historyList}" var="history">
            <tr>
                <td>${history.transactionId}</td>
                <td>${history.buyerUsername}</td>
                <td>${history.gameUsername}</td> <%-- Thêm lại dữ liệu này --%>
                <td>${history.categoryName}</td>
                <td><fmt:formatNumber value="${history.price}" type="currency" currencySymbol="" maxFractionDigits="0" />đ</td>
                <td><fmt:formatDate value="${history.purchaseDate}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
                <td class="action-cell">
                    <a href="delete-transaction?id=${history.transactionId}" class="action-btn delete-btn" title="Xóa" onclick="return confirm('Bạn có chắc chắn muốn xóa giao dịch này không?');">🗑️</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</div>

<jsp:include page="admin_footer.jsp" />