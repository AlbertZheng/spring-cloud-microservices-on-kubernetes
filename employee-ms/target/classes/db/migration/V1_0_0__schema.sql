-- -----------------------------------------------------
-- Schema employee
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `employee`
  DEFAULT CHARACTER SET utf8mb4;
USE `employee`;

-- -----------------------------------------------------
-- Table `employee`.`t_employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employee`.`t_employee` (
  `id`            BIGINT(19) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`          VARCHAR(32)         NOT NULL
  COMMENT '姓名',
  `mobile`        VARCHAR(20)         NOT NULL
  COMMENT '手机号',
  `login_pwd`     VARCHAR(128)        NOT NULL
  COMMENT '登录密码',
  `pwd_salt`      VARCHAR(128)        NOT NULL
  COMMENT '密码盐',
  `department_id` BIGINT(19)          NOT NULL DEFAULT 0
  COMMENT '部门',
  PRIMARY KEY (`id`)
);
