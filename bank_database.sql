/*
 Navicat Premium Data Transfer

 Source Server         : Localhost
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : Localhost:3306
 Source Schema         : bank_database

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 27/07/2021 01:08:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for banks
-- ----------------------------
DROP TABLE IF EXISTS `banks`;
CREATE TABLE `banks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_number` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `trust` double(11,2) DEFAULT NULL,
  `transaction_fee` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of banks
-- ----------------------------
BEGIN;
INSERT INTO `banks` VALUES (1, 'AA-12311', 1922.33, 30);
INSERT INTO `banks` VALUES (2, 'AB-56563', 321.32, 40);
INSERT INTO `banks` VALUES (3, 'AC-91923', 4412.11, 20);
INSERT INTO `banks` VALUES (4, 'AD-10292', 868.92, 15);
INSERT INTO `banks` VALUES (6, 'AA-123211', 1922.33, 30);
COMMIT;

-- ----------------------------
-- Table structure for favorite_beers
-- ----------------------------
DROP TABLE IF EXISTS `favorite_beers`;
CREATE TABLE `favorite_beers` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `adv` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of favorite_beers
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
