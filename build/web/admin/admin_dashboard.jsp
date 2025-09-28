<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="admin_header.jsp" />

<h1>Admin Dashboard</h1>
<h2>Thống kê tổng quan hệ thống</h2>

<div class="stat-cards-container">
    <div class="stat-card">
        <div class="info">
            <h3>${totalAccounts}</h3>
            <p>Tài khoản game</p>
        </div>
        <div class="icon card-blue">👤</div>
    </div>
    <div class="stat-card">
        <div class="info">
            <h3>${soldAccounts}</h3>
            <p>Đã bán</p>
        </div>
        <div class="icon card-green">🛒</div>
    </div>
    <div class="stat-card">
        <div class="info">
            <h3>${availableAccounts}</h3>
            <p>Còn lại</p>
        </div>
        <div class="icon card-orange">📦</div>
    </div>
    <div class="stat-card">
        <div class="info">
            <h3>${totalUsers}</h3>
            <p>Người dùng</p>
        </div>
        <div class="icon card-purple">👥</div>
    </div>
</div>

<h2 style="margin-top: 40px;">Thống kê tài chính</h2>
<div class="stat-cards-container">
    <div class="stat-card">
        <div class="info">
            <h3>${String.format("%,.0f", totalDeposit)}đ</h3>
            <p>Tổng nạp tiền</p>
        </div>
        <div class="icon card-green">⬆️</div>
    </div>
     <%-- <div class="stat-card">
        <div class="info">
            <h3>${String.format("%,.0f", totalWithdraw)}đ</h3>
            <p>Tổng rút tiền</p>
        </div> 
        <div class="icon card-red">⬇️</div>
    </div>--%>
    <div class="stat-card">
        <div class="info">
            <h3>${String.format("%,.0f", totalPurchase)}đ</h3>
            <p>Tổng doanh thu</p>
        </div>
        <div class="icon card-blue">🛍️</div>
    </div>
            <div class="stat-card">
        <div class="info">
            <h3>${String.format("%,.0f", weeklyRevenue)}đ</h3>
            <p>Doanh thu tuần này</p>
        </div>
        <div class="icon card-orange">📅</div>
    </div>
            
</div>
<div class="chart-container">
        <h2>Doanh thu 7 ngày gần nhất</h2>
        <canvas id="revenueChart"></canvas>
        </div>

    <script>
    // Lấy dữ liệu từ controller đã gửi qua
    const labels = ${chartLabels};
    const data = ${chartData};

    const ctx = document.getElementById('revenueChart').getContext('2d');
    const revenueChart = new Chart(ctx, {
        type: 'line', // Loại biểu đồ: đường
        data: {
            labels: labels,
            datasets: [{
                label: 'Doanh thu (VNĐ)',
                data: data,
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 2,
                tension: 0.3 // Làm cho đường cong mượt hơn
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
</script>
<jsp:include page="admin_footer.jsp" />