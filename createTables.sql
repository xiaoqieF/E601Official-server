/*
 Navicat MySQL Data Transfer

 Source Server         : myblog-localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : myblog

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 30/04/2020 17:02:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog`  (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `appreciation` bit(1) NOT NULL,
                           `comment_enable` bit(1) NOT NULL,
                           `content` longtext NULL,
                           `create_time` datetime(0) NULL DEFAULT NULL,
                           `description` varchar(255) NULL DEFAULT NULL,
                           `first_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `published` bit(1) NOT NULL,
                           `recommend` bit(1) NOT NULL,
                           `share_statement` bit(1) NOT NULL,
                           `title` varchar(255) NULL DEFAULT NULL,
                           `update_time` datetime(0) NULL DEFAULT NULL,
                           `views` int(11) NULL DEFAULT NULL,
                           `type_id` bigint(20) NULL DEFAULT NULL,
                           `user_id` bigint(20) NULL DEFAULT NULL,
                           PRIMARY KEY (`id`) USING BTREE,
                           FOREIGN KEY (`type_id`) REFERENCES `t_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                           FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 62 ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `create_time` datetime(0) NULL DEFAULT NULL,
                              `blog_id` bigint(20) NULL DEFAULT NULL,
                              `parent_comment_id` bigint(20) NULL DEFAULT NULL,
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`  (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`  (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `description` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
                           `create_time` datetime(0) NULL DEFAULT NULL,
                           `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `moto` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `type` int(11) NULL DEFAULT NULL,
                           `update_time` datetime(0) NULL DEFAULT NULL,
                           `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `site` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_blog_tag, 中间表用于维护多对多关系
-- 注意其中使用了两个id的联合主键，保证了记录不重复
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_tag`;
CREATE TABLE `t_blog_tag`  (
                               `blog_id` bigint(20) NOT NULL,
                               `tag_id` bigint(20) NOT NULL,
                               PRIMARY KEY (`blog_id`, `tag_id`) USING BTREE,
                               FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                               FOREIGN KEY (`tag_id`) REFERENCES `t_tag` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_album
-- ----------------------------
DROP TABLE IF EXISTS `t_album`;
CREATE TABLE `t_album`  (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `device` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `views` int(11) NULL DEFAULT NULL,
                           `like` int(11) NULL DEFAULT NULL,
                           `description` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
                           `create_time` datetime(0) NULL DEFAULT NULL,
                           `update_time` datetime(0) NULL DEFAULT NULL,
                           `urls` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
                           `user_id` bigint(20) NULL DEFAULT NULL,
                           PRIMARY KEY (`id`) USING BTREE,
                           FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_about
-- ----------------------------
DROP TABLE IF EXISTS `t_about`;
CREATE TABLE `t_about`  (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `content` longtext NULL,
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 201 ROW_FORMAT = Dynamic;

INSERT INTO t_about(content) values ('About');

SET FOREIGN_KEY_CHECKS = 1;
