-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 12, 2025 at 10:02 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_shopacc`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE `accounts` (
  `id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `description` text DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'available',
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`id`, `category_id`, `description`, `price`, `image_url`, `status`, `username`, `password`) VALUES
(1, 1, 'Tài khoản Valorant rank Kim Cương, nhiều skin súng hiếm.', 500000.00, 'images/valorant.jpg', 'sold', 'user1', '123456'),
(2, 2, 'Tài khoản rank Cao Thủ, full tướng và nhiều trang phục.', 1200000.00, 'images/lol3.jpg', 'sold', 'user2', '123456'),
(3, 3, 'Tài khoản AR 58, sở hữu nhiều nhân vật 5 sao.', 800000.00, 'images/genshin.jpg', 'sold', 'user3', '123456'),
(5, 1, 'Tài khoản Valorant rank Bất Tử, full skin Vandal, Phantom, Operator.', 1500000.00, 'images/valorant2.jpg', 'sold', 'user5', '123456'),
(6, 1, 'Tài khoản rank Kim Cương 2, có skin Reaver Vandal và Prime Classic.', 750000.00, 'images/valorant3.jpg', 'available', 'user6', '123456'),
(7, 1, 'Tài khoản phụ (smurf) rank Vàng, sẵn sàng để leo rank.', 120000.00, 'images/valorant3.jpg', 'sold', 'user7', '123456'),
(8, 2, 'Tài khoản rank Cao Thủ 150đ, full tướng, 300+ trang phục.', 2500000.00, 'images/lol2.jpg', 'available', 'user8', '123456'),
(9, 2, 'Tài khoản sở hữu trang phục PAX Twisted Fate cực hiếm, nhiều skin di sản.', 800000.00, 'images/lol.jpg', 'sold', 'user9', '123456'),
(10, 2, 'Tài khoản full tướng, rank Bạch Kim, phù hợp để bắt đầu mùa giải mới.', 450000.00, 'images/lol.jpg', 'sold', 'user10', '123456'),
(11, 3, 'Tài khoản AR 60, sở hữu C6 Raiden, C6 Yelan và nhiều vũ khí 5 sao.', 3000000.00, 'images/genshin2.jpg', 'sold', 'user11', '123456'),
(12, 3, 'Tài khoản AR 57, có Kẻ Lang Thang (Wanderer) và vũ khí trấn, đi la hoàn dễ dàng.', 900000.00, 'images/genshin3.jpg', 'sold', 'user12', '123456'),
(13, 3, 'Tài khoản AR 55, hệ Phong sưu tập đủ Kazuha, Venti, Jean.', 590000.00, 'images/genshin.jpg', 'available', 'zabawizk', '123456'),
(14, 2, 'Tài Khoản full tướng, có skin hiếm', 850000.00, 'images/lol.jpg', 'sold', 'user14', '123456'),
(15, 3, 'Tài khoản AR 60, sở hữu C6 Raiden, C6 Yelan và nhiều vũ khí 4 sao.', 120000.00, 'images/genshin.jpg', 'available', 'user16', '123456'),
(16, 1, 'Tài khoản rank Bất Tử 2, nhiều skin Vandal, Phantom, Operator.', 1800000.00, 'images/valorant.jpg', 'available', 'valorant_user01', 'pass123'),
(17, 1, 'Tài khoản rank Kim Cương 3, có skin Reaver Vandal và Prime Classic.', 750000.00, 'images/valorant2.jpg', 'sold', 'valorant_user02', 'pass123'),
(18, 1, 'Tài khoản phụ (smurf) rank Vàng, sẵn sàng để leo rank.', 120000.00, 'images/valorant3.jpg', 'sold', 'valorant_user03', 'pass123'),
(26, 2, 'Tài khoản rank Cao Thủ 150đ, full tướng, 300+ trang phục.', 2500000.00, 'images/lol.jpg', 'sold', 'lol_user01', 'pass123'),
(27, 2, 'Tài khoản sở hữu trang phục PAX Twisted Fate cực hiếm.', 1800000.00, 'images/lol2.jpg', 'sold', 'lol_user02', 'pass123'),
(28, 2, 'Tài khoản full tướng, rank Bạch Kim, phù hợp để bắt đầu.', 450000.00, 'images/lol3.jpg', 'sold', 'lol_user03', 'pass123'),
(29, 2, 'Acc rank Thách Đấu top 100, KDA cao, thông tin có thể thay đổi.', 3500000.00, 'images/lol3.jpg', 'available', 'lol_user04', 'pass123'),
(34, 2, 'Tài khoản có skin Annie Công Nghệ.', 900000.00, 'images/lol3.jpg', 'sold', 'lol_user09', 'pass123'),
(35, 2, 'Tài khoản rank Sắt IV, elo cực thấp, thích hợp để giải trí.', 50000.00, 'images/lol.jpg', 'sold', 'lol_user10', 'pass123'),
(36, 3, 'Tài khoản AR 60, sở hữu C6 Raiden, C6 Yelan và nhiều vũ khí 5 sao.', 3000000.00, 'images/genshin.jpg', 'sold', 'genshin_user01', 'pass123'),
(38, 3, 'Tài khoản AR 55, hệ Phong sưu tập đủ Kazuha, Venti, Jean.', 600000.00, 'images/genshin3.jpg', 'sold', 'genshin_user03', 'pass123'),
(39, 3, 'Tài khoản AR 58, full nhân vật 4 sao C6, la hoàn full sao.', 1100000.00, 'images/genshin.jpg', 'available', 'genshin_user04', 'pass123'),
(47, 1, 'Tài khoản rank Diamond, skin odin vandal prime 2.0', 400000.00, 'images/valorant2.jpg', 'sold', 'valorant_01', '123456'),
(48, 2, 'Tài khoản liên minh sở hữu Oriana trán hoa linh ngọc tí nị', 300000.00, 'images/lol2.jpg', 'sold', 'lienminh_01', '123456'),
(49, 1, 'Tài khoản valorant full skin vandal , odin', 6000000.00, 'images/valorant2.jpg', 'sold', 'Valorant_02', '123456'),
(50, 1, 'Tài khoản valorant có skin vandal prime 2.0', 260000.00, 'images/valorant2.jpg', 'available', 'valorant_03', '123456'),
(51, 2, 'Tài khoản liên minh có trang phục yasuo thần thoại.', 350000.00, 'images/lol.jpg', 'sold', 'lienminh_02', '123456'),
(52, 1, 'Tài khoản valorant có skin vandal Araxys, phantom Araxys.', 400000.00, 'images/valorant3.jpg', 'sold', 'valorant_03', '123456'),
(53, 1, 'Tài khoản valorant có skin vandal , phantom Araxys', 370000.00, 'images/valorant2.jpg', 'available', 'valorant_04', '123456'),
(54, 2, 'Tài khoản liên minh rank vàng, 147 tướng, 76 trang phục', 400000.00, 'images/lol.jpg', 'available', 'lienminh_03', '123456'),
(55, 3, 'Tài khoản genshin AR 40, có 4 tướng 5*.', 600000.00, 'images/genshin3.jpg', 'sold', 'genshin_user05', '123456'),
(56, 1, 'Tài khoản valorant có full đặc vụ, skin súng vandal, phantom RGX', 550000.00, 'images/valorant3.jpg', 'sold', 'valorant_05', '123456'),
(57, 2, 'Tài khoản liên minh có linh thú Oriana Trán hoa linh ngọc.', 260000.00, 'images/lol1.jpg', 'available', 'lol_user07', '123456'),
(58, 2, 'Tài khoản liên minh có linh thú yone song ảnh tý nị', 310000.00, 'images/lol4.jpg', 'available', 'lol_user08', '123456'),
(59, 1, 'Tài khoản genshin AR 59, 15 nhân vật, 8 vũ khí. ', 640000.00, 'images/genshin1.jpg', 'sold', 'valorant_06', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `cart_items`
--

CREATE TABLE `cart_items` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart_items`
--

INSERT INTO `cart_items` (`id`, `user_id`, `account_id`, `quantity`) VALUES
(77, 4, 48, 1);

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(3, 'Genshin Impact'),
(2, 'Liên Minh Huyền Thoại'),
(1, 'Valorant');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `account_id` int(11) DEFAULT NULL,
  `type` varchar(20) NOT NULL,
  `amount` decimal(15,2) NOT NULL,
  `transaction_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `description` varchar(255) DEFAULT NULL,
  `vnp_TxnRef` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`id`, `user_id`, `account_id`, `type`, `amount`, `transaction_date`, `description`, `vnp_TxnRef`) VALUES
(10, 4, 9, 'purchase', 800000.00, '2025-07-02 09:17:49', 'Mua tài khoản game: null', NULL),
(11, 3, 13, 'purchase', 590000.00, '2025-07-02 09:22:55', 'Mua tài khoản game: null', NULL),
(14, 3, 39, 'purchase', 1100000.00, '2025-07-02 13:53:30', 'Mua tài khoản game: null', NULL),
(15, 4, NULL, 'deposit', 1000000.00, '2025-07-02 14:04:24', 'Nạp tiền vào tài khoản.', NULL),
(16, 4, 36, 'purchase', 3000000.00, '2025-07-02 14:04:26', 'Mua tài khoản game: null', NULL),
(17, 4, 35, 'purchase', 50000.00, '2025-07-02 14:09:55', 'Mua tài khoản game: null', NULL),
(20, 4, 34, 'purchase', 900000.00, '2025-07-02 17:24:31', 'Mua tài khoản game: null', NULL),
(22, 3, NULL, 'deposit', 2000000.00, '2025-07-03 04:48:39', 'Nạp tiền vào tài khoản.', NULL),
(23, 3, NULL, 'deposit', 100000.00, '2025-07-03 08:50:32', 'Nạp tiền vào tài khoản.', NULL),
(24, 3, NULL, 'deposit', 100000.00, '2025-07-03 08:52:11', 'Nạp tiền vào tài khoản.', NULL),
(25, 3, 38, 'purchase', 600000.00, '2025-07-03 08:52:48', 'Mua tài khoản game: null', NULL),
(26, 3, 28, 'purchase', 450000.00, '2025-07-03 08:52:48', 'Mua tài khoản game: null', NULL),
(27, 3, NULL, 'deposit', 100000.00, '2025-07-03 08:57:18', 'Nạp tiền vào tài khoản.', NULL),
(28, 3, 6, 'purchase', 750000.00, '2025-07-03 10:20:14', 'Mua tài khoản game: null', NULL),
(29, 3, NULL, 'deposit', 2000000.00, '2025-07-03 10:26:42', 'Nạp tiền vào tài khoản.', NULL),
(30, 3, 1, 'purchase', 500000.00, '2025-07-03 10:26:45', 'Mua tài khoản game: null', NULL),
(31, 3, 1, 'purchase', 500000.00, '2025-07-03 10:30:48', 'Mua tài khoản game: null', NULL),
(32, 3, NULL, 'deposit', 200000.00, '2025-07-03 10:34:05', 'Nạp tiền vào tài khoản.', NULL),
(33, 3, 2, 'purchase', 1200000.00, '2025-07-03 10:34:06', 'Mua tài khoản game: null', NULL),
(34, 7, NULL, 'deposit', 5000000.00, '2025-07-03 14:23:57', 'Nạp tiền vào tài khoản.', NULL),
(35, 7, 38, 'purchase', 600000.00, '2025-07-03 14:24:06', 'Mua tài khoản game: null', NULL),
(36, 3, NULL, 'deposit', 5000000.00, '2025-07-04 01:31:47', 'Nạp tiền vào tài khoản.', NULL),
(37, 3, 36, 'purchase', 3000000.00, '2025-07-04 01:31:53', 'Mua tài khoản game: null', NULL),
(38, 3, NULL, 'deposit', 2000000.00, '2025-07-04 02:13:40', 'Nạp tiền vào tài khoản.', NULL),
(39, 3, 26, 'purchase', 2500000.00, '2025-07-04 02:13:43', 'Mua tài khoản game: null', NULL),
(40, 3, NULL, 'deposit', 200000.00, '2025-07-08 14:40:59', 'Nạp tiền vào tài khoản.', NULL),
(41, 3, NULL, 'deposit', 200000.00, '2025-07-09 16:19:38', 'Nạp tiền vào tài khoản.', NULL),
(42, 3, 35, 'purchase', 50000.00, '2025-07-11 00:42:59', 'Mua tài khoản game: null', NULL),
(43, 3, 34, 'purchase', 900000.00, '2025-07-11 00:42:59', 'Mua tài khoản game: null', NULL),
(44, 3, 38, 'purchase', 600000.00, '2025-07-11 00:42:59', 'Mua tài khoản game: null', NULL),
(45, 3, NULL, 'deposit', 200000.00, '2025-07-11 01:10:50', 'Nạp tiền vào tài khoản.', NULL),
(46, 3, 38, 'purchase', 600000.00, '2025-07-11 01:10:52', 'Mua tài khoản game: null', NULL),
(47, 3, NULL, 'deposit', 2000000.00, '2025-07-11 01:15:59', 'Nạp tiền vào tài khoản.', NULL),
(48, 4, NULL, 'deposit', 2000000.00, '2025-07-11 01:19:32', 'Nạp tiền vào tài khoản.', NULL),
(49, 4, 38, 'purchase', 600000.00, '2025-07-11 01:19:39', 'Mua tài khoản game: null', NULL),
(50, 4, 27, 'purchase', 1800000.00, '2025-07-11 01:19:39', 'Mua tài khoản game: null', NULL),
(51, 3, NULL, 'deposit', 2000000.00, '2025-07-11 17:05:43', 'Nạp tiền vào tài khoản.', NULL),
(52, 3, 11, 'purchase', 3000000.00, '2025-07-11 17:05:44', 'Mua tài khoản game: null', NULL),
(53, 3, NULL, 'deposit', 5000000.00, '2025-07-11 17:09:30', 'Nạp tiền vào tài khoản.', NULL),
(54, 3, 49, 'purchase', 6000000.00, '2025-07-11 17:09:31', 'Mua tài khoản game: null', NULL),
(55, 5, NULL, 'deposit', 2000000.00, '2025-07-14 12:00:35', 'Nạp tiền vào tài khoản.', NULL),
(56, 5, 26, 'purchase', 2500000.00, '2025-07-14 12:01:06', 'Mua tài khoản game: null', NULL),
(57, 8, NULL, 'deposit', 1000000.00, '2025-07-14 12:25:23', 'Nạp tiền vào tài khoản.', NULL),
(58, 10, NULL, 'deposit', 2000000.00, '2025-07-14 12:25:41', 'Nạp tiền vào tài khoản.', NULL),
(59, 1, 48, 'purchase', 300000.00, '2025-07-14 17:39:38', 'Mua tài khoản game: null', NULL),
(60, 12, NULL, 'deposit', 300000.00, '2025-07-15 03:20:06', 'Admin cập nhật số dư.', NULL),
(61, 3, NULL, 'deposit', 200000.00, '2025-07-15 06:23:04', 'Nạp tiền thành công qua VNPAY', '1752560559226'),
(62, 3, NULL, 'deposit', 200000.00, '2025-07-15 06:24:09', 'Nạp tiền thành công qua VNPAY', '1752560619871'),
(64, 3, NULL, 'deposit', 200000.00, '2025-07-15 06:43:54', 'Nạp tiền thành công qua VNPAY', '1752561811393'),
(65, 13, NULL, 'deposit', 500000.00, '2025-07-15 06:52:03', 'Admin cập nhật số dư.', NULL),
(67, 3, NULL, 'deposit', 200000.00, '2025-07-15 06:57:35', 'Nạp tiền thành công qua VNPAY', '1752562634852'),
(68, 3, NULL, 'deposit', 200000.00, '2025-07-15 07:18:43', 'Nạp tiền thành công qua VNPAY', '1752563907817'),
(69, 3, NULL, 'deposit', 100000.00, '2025-07-15 07:19:17', 'Nạp tiền thành công qua VNPAY', '1752563942180'),
(70, 3, NULL, 'deposit', 100000.00, '2025-07-15 07:20:10', 'Nạp tiền thành công qua VNPAY', '1752563986954'),
(71, 3, NULL, 'deposit', 100000.00, '2025-07-15 07:22:00', 'Nạp tiền thành công qua VNPAY', '1752564096661'),
(72, 5, NULL, 'deposit', 100000.00, '2025-07-15 07:22:38', 'Nạp tiền thành công qua VNPAY', '1752564139889'),
(73, 12, NULL, 'deposit', 200000.00, '2025-07-15 07:47:30', 'Admin cập nhật số dư.', NULL),
(74, 5, NULL, 'deposit', 200000.00, '2025-07-15 07:50:36', 'Admin cập nhật số dư.', NULL),
(75, 3, NULL, 'deposit', 200000.00, '2025-07-15 14:23:32', 'Nạp tiền thành công qua VNPAY', '1752589387853'),
(76, 5, NULL, 'deposit', 200000.00, '2025-07-15 14:25:16', 'Nạp tiền thành công qua VNPAY', '1752589493804'),
(77, 5, NULL, 'deposit', 200000.00, '2025-07-15 14:25:57', 'Nạp tiền thành công qua VNPAY', '1752589538456'),
(78, 5, 52, 'purchase', 400000.00, '2025-07-15 14:26:15', 'Mua tài khoản game: null', NULL),
(79, 3, 5, 'purchase', 1500000.00, '2025-07-21 13:46:16', 'Mua tài khoản game: null', NULL),
(80, 3, NULL, 'deposit', 200000.00, '2025-07-21 16:29:14', 'Nạp tiền thành công qua VNPAY', '1753115336217'),
(81, 3, NULL, 'deposit', 200000.00, '2025-07-22 12:51:00', 'Nạp tiền thành công qua VNPAY', '1753188633544'),
(82, 3, NULL, 'deposit', 200000.00, '2025-07-23 13:40:19', 'Nạp tiền thành công qua VNPAY', '1753277995443'),
(83, 9, NULL, 'deposit', 200000.00, '2025-07-23 13:41:09', 'Admin cập nhật số dư.', NULL),
(84, 3, NULL, 'deposit', 200000.00, '2025-07-23 13:47:33', 'Nạp tiền thành công qua VNPAY', '1753278424184'),
(85, 3, 9, 'purchase', 800000.00, '2025-07-23 13:47:39', 'Mua tài khoản game: null', NULL),
(86, 5, 51, 'purchase', 350000.00, '2025-07-23 13:56:07', 'Mua tài khoản game: null', NULL),
(87, 5, NULL, 'deposit', 1000000.00, '2025-07-24 00:47:16', 'Nạp tiền thành công qua VNPAY', '1753317999611'),
(88, 5, 59, 'purchase', 640000.00, '2025-07-24 01:11:46', 'Mua tài khoản game: null', NULL),
(89, 1, 56, 'purchase', 550000.00, '2025-07-24 01:18:14', 'Mua tài khoản game: null', NULL),
(90, 17, NULL, 'deposit', 200000.00, '2025-07-24 01:22:46', 'Nạp tiền thành công qua VNPAY', '1753320119106'),
(91, 6, 55, 'purchase', 600000.00, '2025-07-24 01:26:05', 'Mua tài khoản game: null', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `role` varchar(10) NOT NULL DEFAULT 'user',
  `balance` decimal(15,2) NOT NULL DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`, `role`, `balance`) VALUES
(1, 'admin', 'admin123', 'admin@shop.com', 'admin', 4250000.00),
(3, 'trannamkhanh', '123456', 'khanh@gmail.com', 'admin', 70000.00),
(4, 'nguyenxuanhoan', '123456', 'hoan@gmail.com', 'admin', 500000.00),
(5, 'buihoanghiep', '123456', 'hiep@gmail.com', 'user', 1510000.00),
(6, 'legiakhanh', '123456', 'lekhanh@gmail.com', 'user', 400000.00),
(7, 'luongminhson', '123456', 'son@gmail.com', 'user', 4400000.00),
(8, 'letrungtoan', '123456', 'toan@gmail.com', 'user', 1000000.00),
(9, 'nguyentienbinh', '123456', 'binh@gmail.com', 'user', 400000.00),
(10, 'ngoquochuy', '123456', 'huy1@gmail.com', 'user', 2000000.00),
(12, 'nguyentuanminh', '123456', 'minh@gmail.com', 'user', 500000.00),
(13, 'vutrunghieu', '123456', 'hieu@gmail.com', 'user', 500000.00),
(15, 'Nguyen Duy Nien', '123456', 'nien@gmail.com', 'user', 0.00),
(16, 'tranducthinh', '123456', 'thinh@gmail.com', 'user', 0.00),
(17, 'buiducnam', '123456', 'nam@gmail.com', 'user', 200000.00);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `cart_items`
--
ALTER TABLE `cart_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `account_id` (`account_id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accounts`
--
ALTER TABLE `accounts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT for table `cart_items`
--
ALTER TABLE `cart_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=94;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=92;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `accounts`
--
ALTER TABLE `accounts`
  ADD CONSTRAINT `accounts_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

--
-- Constraints for table `cart_items`
--
ALTER TABLE `cart_items`
  ADD CONSTRAINT `cart_items_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `cart_items_ibfk_2` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`);

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
