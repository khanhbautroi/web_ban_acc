<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="admin_header.jsp" />

<div class="page-header">
    <div>
        <h1>Lịch sử Nạp tiền</h1>
        <p>Theo dõi tất cả các giao dịch nạp tiền của người dùng</p>
    </div>
</div>
<%-- THÊM FORM TÌM KIẾM VÀO ĐÂY --%>
<form action="manage-deposits" method="get" class="search-bar">
    <input type="text" name="keyword" value="${keyword}" placeholder="Tìm theo tên người nạp...">
    <button type="submit" class="btn btn-primary">Tìm kiếm</button>
</form>
<div class="data-table-container">
    <table class="data-table">
    <thead>
        <tr>
            <th>ID Giao dịch</th>
            <th>Người nạp</th>
            <th>Số tiền</th>
            <th>Thời gian</th>
            <th>Ghi chú</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${depositList}" var="deposit">
            <tr>
                <td>${deposit.transactionId}</td>
                <td>${deposit.buyerUsername}</td>
                <td><fmt:formatNumber value="${deposit.price}" type="currency" currencySymbol="" maxFractionDigits="0" />đ</td>
                <td><fmt:formatDate value="${deposit.purchaseDate}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
                <td>${deposit.description}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</div>

<jsp:include page="admin_footer.jsp" />