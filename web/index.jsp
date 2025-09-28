<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />
<style>
 /* --- CSS cho thanh Danh mục nổi bật có tên bên dưới --- */
.quick-categories {
    display: flex;
    align-items: center;
    gap: 20px;
    margin-bottom: 30px;
    padding: 10px 0;
    justify-content: center; /* Căn giữa toàn bộ nhóm */
}

.quick-categories > span { /* Chỉ áp dụng cho chữ "Danh mục nổi bật:" */
    font-weight: 600;
    color: #555;
    margin-right: 15px;
}

/* Style cho từng mục danh mục (bao gồm cả ảnh và chữ) */
.category-tag {
    display: flex;
    flex-direction: column; /* Xếp ảnh và chữ theo chiều dọc */
    align-items: center;    /* Căn giữa theo chiều ngang */
    text-decoration: none;
    width: 250px; /* Đặt chiều rộng cho mỗi mục */
}

/* Style cho ảnh icon */
.category-tag img {
    height: 60px;
    width: 60px;
    border-radius: 50%; /* Bo tròn ảnh thành hình tròn */
    border: 2px solid #eee;
    padding: 3px;
    background-color: #fff;
    transition: transform 0.2s;
}

.category-tag:hover img {
    transform: scale(1.05); /* Hiệu ứng khi di chuột */
}

/* Style cho chữ tên game */
.category-tag span {
    margin-top: 10px; /* Khoảng cách giữa ảnh và chữ */
    font-size: 14px;
    font-weight: 500;
    color: #333;
    text-align: center;
}
</style>
<main>
    <div class="container">
        <c:if test="${not empty sessionScope.successMessage}">
        <div id="toast-notification" class="toast">
            <p>${sessionScope.successMessage}</p>
        </div>
        <%-- Xóa message khỏi session để nó không hiện lại ở lần tải trang sau --%>
        <c:remove var="successMessage" scope="session" />
    </c:if>

    <div class="filter-bar">
    <form action="trang-chu" method="get" class="filter-form">
        <div class="form-group search-group">
            <label>Tìm kiếm</label>
            <input type="text" name="keyword" value="${keyword}" placeholder="Nhập tên game...">
        </div>
        <div class="form-group">
            <label>Giá tiền</label>
            <select name="priceRange">
                <option value="" ${empty priceRange ? 'selected' : ''}>Tất cả mức giá</option>
                <option value="0-100000" ${priceRange == '0-100000' ? 'selected' : ''}>Dưới 100.000đ</option>
                <option value="100000-500000" ${priceRange == '100000-500000' ? 'selected' : ''}>100.000đ - 500.000đ</option>
                <option value="500000-1000000" ${priceRange == '500000-1000000' ? 'selected' : ''}>500.000đ - 1.000.000đ</option>
                <option value="1000000-0" ${priceRange == '1000000-0' ? 'selected' : ''}>Trên 1.000.000đ</option>
            </select>
        </div>
        <div class="form-group">
            <label>Sắp xếp</label>
            <select name="sortBy">
                <option value="newest" ${empty sortBy or sortBy == 'newest' ? 'selected' : ''}>Mới nhất</option>
                <option value="price_asc" ${sortBy == 'price_asc' ? 'selected' : ''}>Giá: Thấp đến Cao</option>
                <option value="price_desc" ${sortBy == 'price_desc' ? 'selected' : ''}>Giá: Cao đến Thấp</option>
            </select>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Lọc kết quả</button>
        </div>
    </form>
</div>
            <div class="quick-categories">
    <%-- <span>Danh mục nổi bật:</span> --%>

    <a href="trang-chu?keyword=Genshin Impact" class="category-tag" title="Genshin Impact">
        <img src="${pageContext.request.contextPath}/images/genshin_icon.jpg" alt="Genshin Impact" ><p>Genshin impact</p>
    </a>
    <a href="trang-chu?keyword=Liên Minh" class="category-tag" title="Liên Minh Huyền Thoại">
        <img src="${pageContext.request.contextPath}/images/lol_icon.jpg" alt="Liên Minh Huyền Thoại"><p>Liên minh huyền thoại</p>
    </a>
    <a href="trang-chu?keyword=Valorant" class="category-tag" title="Valorant">
        <img src="${pageContext.request.contextPath}/images/valorant_icon.png" alt="Valorant"><p>Valorant</p>
    </a>
    <c:forEach items="${allCategories}" var="cat">
        <a href="trang-chu?keyword=${cat.name}" class="category-tag">
            <img src="${pageContext.request.contextPath}/images/${cat.name.toLowerCase()}_icon.png" alt="${cat.name}">
            <span>${cat.name}</span>
        </a>
    </c:forEach>
</div>

            
            
        <div class="category-section">
            <h2 class="category-title">Danh sách tài khoản</h2>
            <div class="account-grid full-grid">
                <c:forEach items="${accountList}" var="acc">
                    <div class="account-card">
                        <img src="${acc.imageUrl}" alt="${acc.gameName}">
                        <div class="card-body">
                            <h3>${acc.gameName}</h3>
                            <p>${acc.description}</p>
                            <div class="price">${String.format("%,.0f", acc.price)} VNĐ</div>
                            <a href="product-detail?id=${acc.id}" class="btn btn-buy" style="margin-left: 15px; transition: all 0.3s ease;" 
               onmouseover="this.style.backgroundColor='#d32f2f'; this.style.transform='translateY(-2px)'; this.style.boxShadow='0 4px 8px rgba(0,0,0,0.2)'" 
               onmouseout="this.style.backgroundColor=''; this.style.transform=''; this.style.boxShadow=''">CHI TIẾT</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
            <%-- BẮT ĐẦU KHỐI GIỚI THIỆU --%>
<div class="intro-section">
    <div class="container">
        <%-- Phần nội dung có thể ẩn/hiện --%>
        <div id="intro-content" class="intro-content collapsed">
            <h2>Giới thiệu về GameStore</h2>
            <p>
                GameStore - Nâng Tầm Trải Nghiệm, Khẳng Định Đẳng Cấp Game Thủ Việt
Trong kỷ nguyên số, khi cộng đồng game thủ Việt Nam ngày càng lớn mạnh và chuyên nghiệp, nhu cầu về một trải nghiệm chơi game đỉnh cao chưa bao giờ lớn hơn thế. Việc "cày cuốc" từ con số không để có được một tài khoản game ưng ý đòi hỏi rất nhiều thời gian, công sức và đôi khi là cả may mắn. Thấu hiểu điều đó, GameStore đã ra đời với sứ mệnh trở thành cầu nối uy tín, giúp bạn sở hữu ngay những tài khoản game chất lượng nhất một cách nhanh chóng, an toàn và tiết kiệm.
            </p>
            <p>
                GameStore không chỉ là một cửa hàng, mà là ngôi nhà chung của những con tim đam mê các tựa game eSports và thế giới mở hàng đầu hiện nay. Chúng tôi tự hào mang đến một kho tài khoản khổng lồ và đa dạng, sẵn sàng đáp ứng mọi nhu cầu của bạn.
            </p>
        </div>
        <%-- Nút bấm để ẩn/hiện --%>
        <button id="toggle-intro-btn" class="btn-toggle-intro">Xem thêm</button>
    </div>
</div>
<%-- KẾT THÚC KHỐI GIỚI THIỆU --%>
    <script>
    // Lấy phần tử thông báo bằng ID
    const toast = document.getElementById('toast-notification');

    // Nếu phần tử này tồn tại trên trang
    if (toast) {
        // Sau 5000 mili-giây (5 giây), thực hiện hàm sau
        setTimeout(() => {
            // Thêm một class để bắt đầu hiệu ứng mờ dần
            toast.classList.add('fade-out');
            
            // Đợi hiệu ứng mờ dần kết thúc (0.5s) rồi mới ẩn hoàn toàn
            setTimeout(() => {
                toast.style.display = 'none';
            }, 500);

        }, 5000);
    }
</script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const toggleBtn = document.getElementById('toggle-intro-btn');
        const content = document.getElementById('intro-content');

        toggleBtn.addEventListener('click', function() {
            // Chuyển đổi qua lại class 'collapsed'
            content.classList.toggle('collapsed');

            // Đổi chữ của nút bấm
            if (content.classList.contains('collapsed')) {
                toggleBtn.textContent = 'Xem thêm';
            } else {
                toggleBtn.textContent = 'Thu gọn';
            }
        });
    });
</script>
</main>

<jsp:include page="footer.jsp" />