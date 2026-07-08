

-- =============================================
-- 数据库：ticket_mall (电影商城)
-- 表结构：tb_film（影片）、tb_cinema（影院）、tb_show（场次）、tb_seat（座位）、tb_user（用户）、tb_order（订单）
-- =============================================

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 1. 影片表 tb_film
-- ----------------------------
DROP TABLE IF EXISTS `tb_film`;
CREATE TABLE `tb_film` (
                           `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `name` varchar(128) NOT NULL COMMENT '影片名称',
                           `description` varchar(1024) DEFAULT NULL COMMENT '影片简介',
                           `director` varchar(64) DEFAULT NULL COMMENT '导演',
                           `actors` varchar(256) DEFAULT NULL COMMENT '主演',
                           `duration` int(8) NOT NULL COMMENT '时长（分钟）',
                           `release_date` date NOT NULL COMMENT '上映日期',
                           `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态：0-未上映 1-上映中 2-已下架',
                           `poster` varchar(512) DEFAULT NULL COMMENT '海报URL',
                           `rating` decimal(2,1) DEFAULT '0.0' COMMENT '评分',
                           `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

INSERT INTO `tb_film` VALUES
                          (1, '阿凡达：水之道', '詹姆斯·卡梅隆执导的科幻巨制，讲述杰克·萨利和奈蒂莉带领纳美人对抗人类侵略的故事。', '詹姆斯·卡梅隆', '萨姆·沃辛顿,佐伊·索尔达娜,西格妮·韦弗', 192, '2022-12-16', 1, '/posters/avatar2.jpg', 8.3, NOW(), NOW()),
                          (2, '流浪地球2', '太阳即将毁灭，人类启动“流浪地球”计划，寻找新家园。', '郭帆', '吴京,刘德华,李雪健,沙溢', 173, '2023-01-22', 1, '/posters/wanderingearth2.jpg', 8.7, NOW(), NOW()),
                          (3, '满江红', '南宋绍兴年间，岳飞死后四年，秦桧率兵与金国会谈。', '张艺谋', '沈腾,易烊千玺,张译,雷佳音', 159, '2023-01-22', 1, '/posters/manjianghong.jpg', 7.8, NOW(), NOW()),
                          (4, '奥本海默', '讲述“原子弹之父”罗伯特·奥本海默研发原子弹的历程。', '克里斯托弗·诺兰', '基里安·墨菲,艾米莉·布朗特,马特·达蒙', 180, '2023-08-30', 1, '/posters/oppenheimer.jpg', 8.9, NOW(), NOW()),
                          (5, '芭比', '芭比发现自己并非完美，于是前往现实世界寻找答案。', '格蕾塔·葛韦格', '玛格特·罗比,瑞恩·高斯林,艾玛·麦基', 114, '2023-07-21', 1, '/posters/barbie.jpg', 7.5, NOW(), NOW()),
                          (6, '热辣滚烫', '乐莹在人生的低谷期，通过拳击重新找回自己的故事。', '贾玲', '贾玲,雷佳音,张小斐,杨紫', 129, '2024-02-10', 1, '/posters/yolo.jpg', 7.9, NOW(), NOW()),
                          (7, '飞驰人生2', '曾经的冠军车手张驰沦为驾校教练，再度挑战巴音布鲁克。', '韩寒', '沈腾,范丞丞,尹正,张本煜', 121, '2024-02-10', 1, '/posters/pegasus2.jpg', 7.7, NOW(), NOW()),
                          (8, '沙丘2', '保罗·厄崔迪的传奇之旅继续，他将在沙漠星球上对抗哈克南家族。', '丹尼斯·维伦纽瓦', '提莫西·柴勒梅德,赞达亚,丽贝卡·弗格森', 166, '2024-03-08', 1, '/posters/dune2.jpg', 8.4, NOW(), NOW()),
                          (9, '哥斯拉大战金刚2', '哥斯拉和金刚联手对抗来自地心世界的巨大威胁。', '亚当·温加德', '丽贝卡·豪尔,布莱恩·泰里·亨利,丹·史蒂文斯', 115, '2024-03-29', 0, '/posters/godzillavskong.jpg', 0.0, NOW(), NOW());

-- ----------------------------
-- 2. 影院表 tb_cinema
-- ----------------------------
DROP TABLE IF EXISTS `tb_cinema`;
CREATE TABLE `tb_cinema` (
                             `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                             `name` varchar(128) NOT NULL COMMENT '影院名称',
                             `address` varchar(256) NOT NULL COMMENT '影院地址',
                             `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
                             `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

INSERT INTO `tb_cinema` VALUES
                            (1, '万达影城（国贸店）', '北京市朝阳区建国门外大街1号国贸商城3楼', '010-88886666', NOW(), NOW()),
                            (2, 'CGV影城（三里屯店）', '北京市朝阳区三里屯路19号太古里南区B1', '010-88887777', NOW(), NOW()),
                            (3, '博纳国际影城（大悦城店）', '北京市朝阳区朝阳北路101号朝阳大悦城5楼', '010-88888888', NOW(), NOW()),
                            (4, '中影国际影城（五棵松店）', '北京市海淀区复兴路69号华熙LIVE·五棵松B1', '010-88889999', NOW(), NOW());

-- ----------------------------
-- 3. 放映场次表 tb_show
-- ----------------------------
DROP TABLE IF EXISTS `tb_show`;
CREATE TABLE `tb_show` (
                           `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                           `film_id` bigint(20) UNSIGNED NOT NULL COMMENT '影片ID',
                           `cinema_id` bigint(20) UNSIGNED NOT NULL COMMENT '影院ID',
                           `hall_name` varchar(64) NOT NULL COMMENT '影厅名称',
                           `hall_type` varchar(32) DEFAULT '普通厅' COMMENT '影厅类型（IMAX/杜比/普通厅）',
                           `start_time` datetime NOT NULL COMMENT '开始时间',
                           `end_time` datetime NOT NULL COMMENT '结束时间',
                           `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '票价',
                           `total_seats` int(8) DEFAULT 0 COMMENT '总座位数',
                           `remaining_seats` int(8) DEFAULT 0 COMMENT '剩余座位数',
                           `status` tinyint(1) DEFAULT 1 COMMENT '状态：0-已取消 1-待上映 2-正在上映 3-已结束',
                           `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id`),
                           KEY `idx_film_id` (`film_id`),
                           KEY `idx_cinema_id` (`cinema_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

INSERT INTO `tb_show` VALUES
-- 阿凡达2
(1, 1, 1, '1号杜比厅', '杜比影院', '2026-07-08 10:00:00', '2026-07-08 13:12:00', 89.00, 120, 120, 1, NOW(), NOW()),
(2, 1, 2, '3号IMAX厅', 'IMAX', '2026-07-08 14:30:00', '2026-07-08 17:42:00', 99.00, 150, 150, 1, NOW(), NOW()),
(3, 1, 3, '2号激光厅', '激光厅', '2026-07-08 19:00:00', '2026-07-08 22:12:00', 79.00, 100, 100, 1, NOW(), NOW()),

-- 流浪地球2
(4, 2, 1, '5号IMAX厅', 'IMAX', '2026-07-08 13:00:00', '2026-07-08 15:53:00', 99.00, 150, 150, 1, NOW(), NOW()),
(5, 2, 2, '1号杜比厅', '杜比影院', '2026-07-08 16:30:00', '2026-07-08 19:23:00', 89.00, 120, 120, 1, NOW(), NOW()),
(6, 2, 4, '3号普通厅', '普通厅', '2026-07-08 20:00:00', '2026-07-08 22:53:00', 69.00, 80, 80, 1, NOW(), NOW()),

-- 满江红
(7, 3, 1, '2号激光厅', '激光厅', '2026-07-08 11:00:00', '2026-07-08 13:39:00', 69.00, 100, 100, 1, NOW(), NOW()),
(8, 3, 3, '1号杜比厅', '杜比影院', '2026-07-08 15:00:00', '2026-07-08 17:39:00', 79.00, 120, 120, 1, NOW(), NOW()),

-- 奥本海默
(9, 4, 2, '6号IMAX厅', 'IMAX', '2026-07-08 12:00:00', '2026-07-08 15:00:00', 99.00, 150, 150, 1, NOW(), NOW()),
(10, 4, 4, '2号激光厅', '激光厅', '2026-07-08 16:00:00', '2026-07-08 19:00:00', 79.00, 100, 100, 1, NOW(), NOW()),

-- 芭比
(11, 5, 3, '3号普通厅', '普通厅', '2026-07-08 14:00:00', '2026-07-08 15:54:00', 59.00, 80, 80, 1, NOW(), NOW()),
(12, 5, 1, '4号普通厅', '普通厅', '2026-07-08 18:30:00', '2026-07-08 20:24:00', 65.00, 80, 80, 1, NOW(), NOW()),

-- 热辣滚烫
(13, 6, 1, '3号普通厅', '普通厅', '2026-07-08 09:30:00', '2026-07-08 11:39:00', 69.00, 80, 80, 1, NOW(), NOW()),
(14, 6, 2, '2号激光厅', '激光厅', '2026-07-08 15:30:00', '2026-07-08 17:39:00', 79.00, 100, 100, 1, NOW(), NOW()),

-- 飞驰人生2
(15, 7, 3, '1号杜比厅', '杜比影院', '2026-07-08 13:30:00', '2026-07-08 15:31:00', 89.00, 120, 120, 1, NOW(), NOW()),
(16, 7, 4, '4号普通厅', '普通厅', '2026-07-08 19:30:00', '2026-07-08 21:31:00', 69.00, 80, 80, 1, NOW(), NOW()),

-- 沙丘2
(17, 8, 1, '6号IMAX厅', 'IMAX', '2026-07-08 16:00:00', '2026-07-08 18:46:00', 109.00, 150, 150, 1, NOW(), NOW()),
(18, 8, 2, '1号杜比厅', '杜比影院', '2026-07-08 20:30:00', '2026-07-08 23:16:00', 99.00, 120, 120, 1, NOW(), NOW()),

-- 哥斯拉大战金刚2（未上映）
(19, 9, 1, '5号IMAX厅', 'IMAX', '2026-07-15 10:00:00', '2026-07-15 11:55:00', 99.00, 150, 150, 0, NOW(), NOW());

-- ----------------------------
-- 4. 座位表（每个场次生成座位数据）
-- ----------------------------
DROP TABLE IF EXISTS `tb_seat`;
CREATE TABLE `tb_seat` (
                           `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                           `show_id` bigint(20) UNSIGNED NOT NULL COMMENT '场次ID',
                           `row_num` int(4) NOT NULL COMMENT '排号',
                           `col_num` int(4) NOT NULL COMMENT '列号',
                           `seat_name` varchar(16) DEFAULT NULL COMMENT '座位名称（如 A1、B3）',
                           `status` tinyint(1) DEFAULT 0 COMMENT '0-空闲 1-锁定 2-已售',
                           `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id`),
                           KEY `idx_show_id` (`show_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 为场次 1（阿凡达2，万达国贸 10:00）生成座位数据（8排 × 15列 = 120座）
-- 注意：这里只生成一个场次的示例，实际项目中可用脚本批量生成
INSERT INTO `tb_seat` (`show_id`, `row_num`, `col_num`, `seat_name`, `status`) VALUES
                                                                                   (1, 1, 1, 'A1', 0), (1, 1, 2, 'A2', 0), (1, 1, 3, 'A3', 0), (1, 1, 4, 'A4', 0), (1, 1, 5, 'A5', 0),
                                                                                   (1, 1, 6, 'A6', 0), (1, 1, 7, 'A7', 0), (1, 1, 8, 'A8', 0), (1, 1, 9, 'A9', 0), (1, 1, 10, 'A10', 0),
                                                                                   (1, 1, 11, 'A11', 0), (1, 1, 12, 'A12', 0), (1, 1, 13, 'A13', 0), (1, 1, 14, 'A14', 0), (1, 1, 15, 'A15', 0),
                                                                                   (1, 2, 1, 'B1', 0), (1, 2, 2, 'B2', 0), (1, 2, 3, 'B3', 0), (1, 2, 4, 'B4', 0), (1, 2, 5, 'B5', 0),
                                                                                   (1, 2, 6, 'B6', 0), (1, 2, 7, 'B7', 0), (1, 2, 8, 'B8', 0), (1, 2, 9, 'B9', 0), (1, 2, 10, 'B10', 0),
                                                                                   (1, 2, 11, 'B11', 0), (1, 2, 12, 'B12', 0), (1, 2, 13, 'B13', 0), (1, 2, 14, 'B14', 0), (1, 2, 15, 'B15', 0),
                                                                                   (1, 3, 1, 'C1', 0), (1, 3, 2, 'C2', 0), (1, 3, 3, 'C3', 0), (1, 3, 4, 'C4', 0), (1, 3, 5, 'C5', 0),
                                                                                   (1, 3, 6, 'C6', 0), (1, 3, 7, 'C7', 0), (1, 3, 8, 'C8', 0), (1, 3, 9, 'C9', 0), (1, 3, 10, 'C10', 0),
                                                                                   (1, 3, 11, 'C11', 0), (1, 3, 12, 'C12', 0), (1, 3, 13, 'C13', 0), (1, 3, 14, 'C14', 0), (1, 3, 15, 'C15', 0),
                                                                                   (1, 4, 1, 'D1', 0), (1, 4, 2, 'D2', 0), (1, 4, 3, 'D3', 0), (1, 4, 4, 'D4', 0), (1, 4, 5, 'D5', 0),
                                                                                   (1, 4, 6, 'D6', 0), (1, 4, 7, 'D7', 0), (1, 4, 8, 'D8', 0), (1, 4, 9, 'D9', 0), (1, 4, 10, 'D10', 0),
                                                                                   (1, 4, 11, 'D11', 0), (1, 4, 12, 'D12', 0), (1, 4, 13, 'D13', 0), (1, 4, 14, 'D14', 0), (1, 4, 15, 'D15', 0),
                                                                                   (1, 5, 1, 'E1', 0), (1, 5, 2, 'E2', 0), (1, 5, 3, 'E3', 0), (1, 5, 4, 'E4', 0), (1, 5, 5, 'E5', 0),
                                                                                   (1, 5, 6, 'E6', 0), (1, 5, 7, 'E7', 0), (1, 5, 8, 'E8', 0), (1, 5, 9, 'E9', 0), (1, 5, 10, 'E10', 0),
                                                                                   (1, 5, 11, 'E11', 0), (1, 5, 12, 'E12', 0), (1, 5, 13, 'E13', 0), (1, 5, 14, 'E14', 0), (1, 5, 15, 'E15', 0),
                                                                                   (1, 6, 1, 'F1', 0), (1, 6, 2, 'F2', 0), (1, 6, 3, 'F3', 0), (1, 6, 4, 'F4', 0), (1, 6, 5, 'F5', 0),
                                                                                   (1, 6, 6, 'F6', 0), (1, 6, 7, 'F7', 0), (1, 6, 8, 'F8', 0), (1, 6, 9, 'F9', 0), (1, 6, 10, 'F10', 0),
                                                                                   (1, 6, 11, 'F11', 0), (1, 6, 12, 'F12', 0), (1, 6, 13, 'F13', 0), (1, 6, 14, 'F14', 0), (1, 6, 15, 'F15', 0),
                                                                                   (1, 7, 1, 'G1', 0), (1, 7, 2, 'G2', 0), (1, 7, 3, 'G3', 0), (1, 7, 4, 'G4', 0), (1, 7, 5, 'G5', 0),
                                                                                   (1, 7, 6, 'G6', 0), (1, 7, 7, 'G7', 0), (1, 7, 8, 'G8', 0), (1, 7, 9, 'G9', 0), (1, 7, 10, 'G10', 0),
                                                                                   (1, 7, 11, 'G11', 0), (1, 7, 12, 'G12', 0), (1, 7, 13, 'G13', 0), (1, 7, 14, 'G14', 0), (1, 7, 15, 'G15', 0),
                                                                                   (1, 8, 1, 'H1', 0), (1, 8, 2, 'H2', 0), (1, 8, 3, 'H3', 0), (1, 8, 4, 'H4', 0), (1, 8, 5, 'H5', 0),
                                                                                   (1, 8, 6, 'H6', 0), (1, 8, 7, 'H7', 0), (1, 8, 8, 'H8', 0), (1, 8, 9, 'H9', 0), (1, 8, 10, 'H10', 0),
                                                                                   (1, 8, 11, 'H11', 0), (1, 8, 12, 'H12', 0), (1, 8, 13, 'H13', 0), (1, 8, 14, 'H14', 0), (1, 8, 15, 'H15', 0);

-- 可以再插入场次2的座位（类似方式，此处省略详细数据，生产环境需批量生成）

-- ----------------------------
-- 5. 用户表 tb_user（简化版）
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
                           `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                           `phone` varchar(11) NOT NULL COMMENT '手机号',
                           `password` varchar(128) DEFAULT '' COMMENT '密码（加密存储）',
                           `nick_name` varchar(64) DEFAULT '' COMMENT '昵称',
                           `avatar` varchar(512) DEFAULT '' COMMENT '头像',
                           `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `uniq_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

INSERT INTO `tb_user` VALUES
                          (1, '13600000001', '', '电影迷小明', '/avatars/user1.jpg', NOW(), NOW()),
                          (2, '13600000002', '', '影评人小张', '/avatars/user2.jpg', NOW(), NOW()),
                          (3, '13600000003', '', '观影达人', '/avatars/user3.jpg', NOW(), NOW()),
                          (4, '13600000004', '', '周末看电影', '/avatars/user4.jpg', NOW(), NOW()),
                          (5, '13600000005', '', 'IMAX爱好者', '/avatars/user5.jpg', NOW(), NOW());

-- ----------------------------
-- 6. 订单表 tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
                            `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                            `order_no` varchar(32) NOT NULL COMMENT '订单号（唯一）',
                            `user_id` bigint(20) UNSIGNED NOT NULL,
                            `show_id` bigint(20) UNSIGNED NOT NULL,
                            `seat_ids` varchar(256) DEFAULT NULL COMMENT '选中的座位ID（逗号分隔）',
                            `seat_names` varchar(256) DEFAULT NULL COMMENT '选中的座位名称（逗号分隔）',
                            `total_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '总价',
                            `status` tinyint(1) DEFAULT 0 COMMENT '0-待支付 1-已支付 2-已取消 3-已超时',
                            `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
                            `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uniq_order_no` (`order_no`),
                            KEY `idx_user_id` (`user_id`),
                            KEY `idx_show_id` (`show_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

INSERT INTO `tb_order` VALUES
                           (1, 'ORD20260708001', 1, 1, '1,2', 'A1,A2', 178.00, 1, '2026-07-08 10:15:00', NOW(), NOW()),
                           (2, 'ORD20260708002', 2, 2, '10,11,12', 'B10,B11,B12', 297.00, 0, NULL, NOW(), NOW()),
                           (3, 'ORD20260708003', 3, 4, '5,6,7,8', 'A5,A6,A7,A8', 396.00, 1, '2026-07-08 13:30:00', NOW(), NOW()),
                           (4, 'ORD20260708004', 1, 7, '20,21', 'B5,B6', 138.00, 2, NULL, NOW(), NOW()),
                           (5, 'ORD20260708005', 4, 9, '3,4,5', 'C3,C4,C5', 297.00, 0, NULL, NOW(), NOW()),
                           (6, 'ORD20260708006', 5, 13, '1,2,3,4', 'A1,A2,A3,A4', 276.00, 1, '2026-07-08 09:45:00', NOW(), NOW()),
                           (7, 'ORD20260708007', 2, 17, '15,16', 'D1,D2', 218.00, 1, '2026-07-08 16:30:00', NOW(), NOW()),
                           (8, 'ORD20260708008', 3, 5, '8,9', 'E3,E4', 178.00, 3, NULL, NOW(), NOW());

SET FOREIGN_KEY_CHECKS = 1;