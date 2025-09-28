<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shop Bán Acc Game</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="icon" type="image/jpg" href="${pageContext.request.contextPath}/images/logo.jpg">
    <style>
        .logo {
            display: flex; /* Bật Flexbox để căn chỉnh */
            align-items: center; /* Căn giữa ảnh và chữ theo chiều dọc */
            gap: 10px; /* Khoảng cách giữa ảnh và chữ */
            font-size: 24px;
            font-weight: bold;
            color: #333;
            text-decoration: none;
        }

        /* Đặt kích thước cho ảnh logo */
        .logo img {
            height: 40px; /* Điều chỉnh chiều cao của logo nếu cần */
            border-radius: 5px; /* Bo tròn góc nhẹ cho đẹp */
}
    </style>
</head>
<body>
    <header>
    <div class="container">
        <%-- Thay thế thẻ <a> cũ bằng thẻ <a> mới có chứa cả ảnh và chữ --%>
<a href="${pageContext.request.contextPath}/trang-chu" class="logo">
    <img src="${pageContext.request.contextPath}/images/logo.jpg" alt="GameStore Logo">
    <span>GameStore</span>
</a>

        <nav>
            <ul>
                <li><a href="trang-chu">Trang Chủ</a></li>
                <li><a href="deposit">Nạp Tiền</a></li>
                <li><a href="cart">Giỏ Hàng</a></li>
                <%-- Trong file header.jsp, bên trong thẻ <ul> của nav --%>
                <c:if test="${sessionScope.account.role == 'admin'}">
                    <li><a href="admin/dashboard">Quản Trị</a></li>
                </c:if>
            </ul>
        </nav>

        <div class="auth-buttons">
            <%-- Code JSTL kiểm tra đăng nhập --%>
            <c:choose>
                <c:when test="${sessionScope.account != null}">
                    <span style="font-weight: bold;">Xin chào, ${sessionScope.account.username}</span>
                    <a href="logout" class="btn btn-register" style="margin-left: 15px;" style="transition: all 0.3s ease;" 
               onmouseover="this.style.backgroundColor='#218838'; this.style.transform='translateY(-2px)'; this.style.boxShadow='0 4px 8px rgba(0,0,0,0.2)'" 
               onmouseout="this.style.backgroundColor=''; this.style.transform=''; this.style.boxShadow=''">Đăng Xuất</a>
                </c:when>
                <c:otherwise>
                    <a href="login.jsp" class="btn btn-login" 
                       style="transition: all 0.3s ease;" 
                onmouseover="this.style.backgroundColor='#0056b3'; this.style.transform='translateY(-2px)'; this.style.boxShadow='0 4px 8px rgba(0,0,0,0.2)'" 
                onmouseout="this.style.backgroundColor=''; this.style.transform=''; this.style.boxShadow=''">Đăng Nhập</a>
                    <a href="register.jsp" class="btn btn-register" style="transition: all 0.3s ease;" 
               onmouseover="this.style.backgroundColor='#218838'; this.style.transform='translateY(-2px)'; this.style.boxShadow='0 4px 8px rgba(0,0,0,0.2)'" 
               onmouseout="this.style.backgroundColor=''; this.style.transform=''; this.style.boxShadow=''">Đăng Ký</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</header>
            <%-- Đặt ngay trước thẻ </body> trong file header.jsp --%>
    <script>
        function scrollToCategory(event) {
            // Ngăn form gửi đi và tải lại trang
            event.preventDefault();

            // Lấy từ khóa tìm kiếm và chuyển thành chữ thường, bỏ dấu cách
            const query = document.getElementById('search-input').value.toLowerCase().trim();

            if (query) {
                // Tạo ID của mục tiêu cần cuộn đến
                // Ví dụ: "Valorant" -> "category-Valorant"
                const targetId = 'category-' + query.charAt(0).toUpperCase() + query.slice(1);

                // Tìm phần tử có ID đó trên trang
                const targetElement = document.getElementById(targetId);

                if (targetElement) {
                    // Nếu tìm thấy, cuộn mượt mà đến phần tử đó
                    targetElement.scrollIntoView({ behavior: 'smooth', block: 'start' });
                } else {
                    // Nếu không tìm thấy, thông báo cho người dùng
                    alert('Không tìm thấy game: ' + query);
                }
            }
        }
    </script>
