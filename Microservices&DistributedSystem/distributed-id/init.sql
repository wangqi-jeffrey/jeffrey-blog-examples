CREATE TABLE `t_id_builder_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `des` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `init_num` bigint(13) DEFAULT NULL COMMENT 'id起步值',
  `current_threshold` bigint(16) DEFAULT NULL COMMENT '当前id所在阶段的阈值',
  `step` int(8) DEFAULT NULL COMMENT 'id递增区间',
  `biz_code` varchar(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '业务前缀码，如果没有则返回时不携带',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '乐观锁版本号',
  `is_seq` smallint(2) NOT NULL DEFAULT '0' COMMENT 'id连续递增，0 是  1 不是',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO t_id_builder_config (id, des, init_num, current_threshold, step, biz_code, version, is_seq, create_time, update_time) VALUES (1, '订单id生成', 0, 0, 4000, 'order_', 0, 1, '2020-12-23 12:17:10', '2020-12-23 12:17:10');
INSERT INTO t_id_builder_config (id, des, init_num, current_threshold, step, biz_code, version, is_seq, create_time, update_time) VALUES (2, '支付流水id生成', 0, 0, 2000, 'pay_', 0, 1, '2020-12-23 12:17:10', '2020-12-23 12:17:10');
INSERT INTO t_id_builder_config (id, des, init_num, current_threshold, step, biz_code, version, is_seq, create_time, update_time) VALUES (3, 'IM序列号', 0, 0, 10, 'im_', 0, 0, '2020-12-23 12:17:10', '2020-12-23 12:17:10');
INSERT INTO t_id_builder_config (id, des, init_num, current_threshold, step, biz_code, version, is_seq, create_time, update_time) VALUES (4, '合同序列号', 10000, 10000, 2000, 'contract_', 0, 1, '2020-12-23 12:17:10', '2020-12-23 12:17:10');