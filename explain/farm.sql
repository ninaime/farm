/*
Navicat MySQL Data Transfer

Source Server         : bbs
Source Server Version : 50718
Source Host           : cdb-iu7ogbez.gz.tencentcdb.com:10014
Source Database       : farm

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2019-03-05 17:49:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for seed
-- ----------------------------
DROP TABLE IF EXISTS `seed`;
CREATE TABLE `seed` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `seed_name` varchar(255) DEFAULT NULL,
  `price` double(10,0) DEFAULT NULL,
  `mature_time` datetime DEFAULT NULL,
  `seed_describe` varchar(255) DEFAULT NULL,
  `is_sell` tinyint(4) DEFAULT '0',
  `gmt_create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of seed
-- ----------------------------

-- ----------------------------
-- Table structure for seed_store
-- ----------------------------
DROP TABLE IF EXISTS `seed_store`;
CREATE TABLE `seed_store` (
  `id` bigint(20) NOT NULL,
  `seed_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of seed_store
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `sex` tinyint(4) DEFAULT '2',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `role_id` tinyint(4) DEFAULT '1',
  `user_lock` tinyint(4) DEFAULT '0',
  `exp` int(11) DEFAULT '0',
  `gold` int(11) DEFAULT '0',
  `ip` varchar(255) DEFAULT NULL,
  `is_disable` bigint(255) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('00000000000000000002', null, 'test', '123', null, '2', '2019-02-28 16:57:26', '1', '0', '0', '0', null, '0');

-- ----------------------------
-- Table structure for user_land
-- ----------------------------
DROP TABLE IF EXISTS `user_land`;
CREATE TABLE `user_land` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `seed_id` int(11) DEFAULT '0',
  `status` tinyint(4) DEFAULT '0',
  `mature` datetime DEFAULT NULL,
  `user_land_no` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_land
-- ----------------------------
INSERT INTO `user_land` VALUES ('00000000001', '0', '0', null, '1', '2');
INSERT INTO `user_land` VALUES ('00000000002', '0', '0', null, '2', '2');
INSERT INTO `user_land` VALUES ('00000000003', '0', '0', null, '3', '2');
INSERT INTO `user_land` VALUES ('00000000004', '0', '0', null, '4', '2');
INSERT INTO `user_land` VALUES ('00000000005', '0', '0', null, '5', '2');
INSERT INTO `user_land` VALUES ('00000000006', '0', '0', null, '6', '2');
INSERT INTO `user_land` VALUES ('00000000007', '0', '0', null, '7', '2');
INSERT INTO `user_land` VALUES ('00000000008', '0', '0', null, '8', '2');
INSERT INTO `user_land` VALUES ('00000000009', '0', '0', null, '9', '2');
INSERT INTO `user_land` VALUES ('00000000010', '0', '0', null, '10', '2');
INSERT INTO `user_land` VALUES ('00000000011', '0', '0', null, '11', '2');
INSERT INTO `user_land` VALUES ('00000000012', '0', '0', null, '12', '2');

-- ----------------------------
-- Table structure for user_ware
-- ----------------------------
DROP TABLE IF EXISTS `user_ware`;
CREATE TABLE `user_ware` (
  `id` bigint(11) NOT NULL,
  `seed_id` bigint(11) DEFAULT NULL,
  `inventory` bigint(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_ware
-- ----------------------------
DROP TRIGGER IF EXISTS `user_on_land`;
DELIMITER ;;
CREATE TRIGGER `user_on_land` AFTER INSERT ON `sys_user` FOR EACH ROW begin 
	set @i=1;
	while @i <13 do
		insert into user_land(user_id,user_land_no) values(new.id,@i);
		set @i=@i+1;
	end while;
end
;;
DELIMITER ;
