<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />

<main>
    <div class="container">
        <c:choose>
            <c:when test="${not empty accountDetail}">
                <div class="product-detail-container">

                    <div class="product-image-container">
                        <img src="${pageContext.request.contextPath}/${accountDetail.imageUrl}" alt="Hình ảnh ${accountDetail.gameName}">
                    </div>

                    <div class="product-info-container">
                        <div class="breadcrumb">
                            <a href="trang-chu">Trang Chủ</a> / <span>${accountDetail.gameName}</span>
                        </div>

                        <h1 class="product-title">Tài khoản ${accountDetail.gameName}</h1>
                        
                        <div class="product-price">
                            ${String.format("%,.0f", accountDetail.price)} VNĐ
                        </div>

                        <div class="description-section">
                            <h4>Mô tả chi tiết</h4>
                            <p>${accountDetail.description}</p>
                        </div>
                        
                        <form action="${pageContext.request.contextPath}/add-to-cart" method="post" class="add-to-cart-form">
                            <input type="hidden" name="accountId" value="${accountDetail.id}">
                            <input type="hidden" name="quantity" value="1">
                            <button type="submit" class="btn btn-buy">Thêm vào giỏ hàng</button>
                        </form>
                    </div>

                </div>
            </c:when>
            <c:otherwise>
                <h1 style="text-align: center;">Không tìm thấy tài khoản.</h1>
            </c:otherwise>
        </c:choose>
    </div>
</main>

<jsp:include page="footer.jsp" />