/*
 Navicat Premium Data Transfer

 Source Server         : 徐州泉山横向
 Source Server Type    : MySQL
 Source Server Version : 80020 (8.0.20)
 Source Host           : 47.98.115.14:3306
 Source Schema         : quanshan

 Target Server Type    : MySQL
 Target Server Version : 80020 (8.0.20)
 File Encoding         : 65001

 Date: 01/12/2023 22:32:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device`  (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                           `type` int NULL DEFAULT NULL,
                           PRIMARY KEY (`id`) USING BTREE,
                           INDEX `type_fk`(`type` ASC) USING BTREE,
                           CONSTRAINT `type_fk` FOREIGN KEY (`type`) REFERENCES `device_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for device_data
-- ----------------------------
DROP TABLE IF EXISTS `device_data`;
CREATE TABLE `device_data`  (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `device_id` int NOT NULL,
                                `value` double NOT NULL,
                                `datetime` datetime NOT NULL,
                                PRIMARY KEY (`id`) USING BTREE,
                                INDEX `device_data_FK`(`device_id` ASC) USING BTREE,
                                CONSTRAINT `device_data_FK` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for device_hour_data
-- ----------------------------
DROP TABLE IF EXISTS `device_hourly_data`;
CREATE TABLE `device_hourly_data`  (
                                     `id` int NOT NULL AUTO_INCREMENT,
                                     `device_id` int NOT NULL,
                                     `value` double NOT NULL,
                                     `datetime` datetime NOT NULL,
                                     PRIMARY KEY (`id`) USING BTREE,
                                     INDEX `device_hour_data_FK`(`device_id` ASC) USING BTREE,
                                     CONSTRAINT `device_hour_data_FK` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for device_type
-- ----------------------------
DROP TABLE IF EXISTS `device_type`;
CREATE TABLE `device_type`  (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for statistic_device
-- ----------------------------
DROP TABLE IF EXISTS `statistic_device`;
CREATE TABLE `statistic_device`  (
                                     `id` int NOT NULL AUTO_INCREMENT,
                                     `device_id` int NOT NULL,
                                     `statistic_id` int NOT NULL,
                                     PRIMARY KEY (`id`) USING BTREE,
                                     INDEX `statistic_device_FK`(`device_id` ASC) USING BTREE,
                                     INDEX `statistic_device_FK_1`(`statistic_id` ASC) USING BTREE,
                                     CONSTRAINT `statistic_device_FK` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                     CONSTRAINT `statistic_device_FK_1` FOREIGN KEY (`statistic_id`) REFERENCES `statistic_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for statistic_type
-- ----------------------------
DROP TABLE IF EXISTS `statistic_type`;
CREATE TABLE `statistic_type`  (
                                   `id` int NOT NULL AUTO_INCREMENT,
                                   `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                         `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                         `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                         `createAt` datetime NOT NULL,
                         `updateAt` datetime NULL DEFAULT NULL,
                         `deleteAt` datetime NULL DEFAULT NULL,
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
