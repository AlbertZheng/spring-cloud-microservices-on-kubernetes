-- -----------------------------------------------------
-- Schema department
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `department`
  DEFAULT CHARACTER SET utf8mb4;
USE `department`;

-- -----------------------------------------------------
-- Table `department`.`t_department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `department`.`t_department` (
  `id`          BIGINT(19) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(64) DEFAULT NULL
  COMMENT '部门名称',
  `location`    VARCHAR(64) DEFAULT NULL
  COMMENT '部门所在地',
  PRIMARY KEY (`id`)
);
