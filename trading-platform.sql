-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema trading
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema trading
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `trading` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `trading` ;

-- -----------------------------------------------------
-- Table `trading`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trading`.`category` (
  `id` INT AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_category_category1_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `fk_category_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `trading`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `trading`.`voucher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trading`.`voucher` (
  `id` INT AUTO_INCREMENT,
  `discount` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `trading`.`voucher_code`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trading`.`voucher_code` (
  `code` INT NOT NULL,
  `voucher_id` INT NOT NULL,
  INDEX `fk_voucher_code_voucher1_idx` (`voucher_id` ASC) VISIBLE,
  PRIMARY KEY (`voucher_id`),
  CONSTRAINT `fk_voucher_code_voucher1`
    FOREIGN KEY (`voucher_id`)
    REFERENCES `trading`.`voucher` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `trading`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trading`.`role` (
  `id` INT AUTO_INCREMENT,
  `role_name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `trading`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trading`.`user` (
  `id` INT AUTO_INCREMENT,
  `username` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `avatar` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `active` TINYINT(1) NULL DEFAULT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_role_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `trading`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `trading`.`code-user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trading`.`code-user` (
  `id` INT AUTO_INCREMENT,
  `voucher_code_voucher_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_code-user_voucher_code1_idx` (`voucher_code_voucher_id` ASC) VISIBLE,
  INDEX `fk_code-user_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_code-user_voucher_code1`
    FOREIGN KEY (`voucher_code_voucher_id`)
    REFERENCES `trading`.`voucher_code` (`voucher_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_code-user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `trading`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `trading`.`store`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trading`.`store` (
  `id` INT AUTO_INCREMENT,
  `store_name` VARCHAR(45) NULL DEFAULT NULL,
  `adress` VARCHAR(45) NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_store_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_store_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `trading`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `trading`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trading`.`product` (
  `id` INT AUTO_INCREMENT,
  `product_name` VARCHAR(45) NULL DEFAULT NULL,
  `pricee` DECIMAL(10,2) NULL DEFAULT NULL,
  `image` VARCHAR(45) NULL DEFAULT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product_category1_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `trading`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `trading`.`product-store`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trading`.`product-store` (
  `id` INT AUTO_INCREMENT,
  `count` INT NULL DEFAULT NULL,
  `store_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `voucher_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product-store_store1_idx` (`store_id` ASC) VISIBLE,
  INDEX `fk_product-store_product1_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_product-store_voucher1_idx` (`voucher_id` ASC) VISIBLE,
  CONSTRAINT `fk_product-store_store1`
    FOREIGN KEY (`store_id`)
    REFERENCES `trading`.`store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product-store_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `trading`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product-store_voucher1`
    FOREIGN KEY (`voucher_id`)
    REFERENCES `trading`.`voucher` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `trading`.`orderdetail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trading`.`orderdetail` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quatity` INT NULL DEFAULT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  `product-store_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_orderdetail_product-store1_idx` (`product-store_id` ASC) VISIBLE,
  CONSTRAINT `fk_orderdetail_product-store1`
    FOREIGN KEY (`product-store_id`)
    REFERENCES `trading`.`product-store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `trading`.`payment_method`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trading`.`payment_method` (
  `id` INT AUTO_INCREMENT,
  `discount` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `trading`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trading`.`payment` (
  `id` INT AUTO_INCREMENT,
  `payment` VARCHAR(45) NULL DEFAULT NULL,
  `payment_method_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_payment_payment_method1_idx` (`payment_method_id` ASC) VISIBLE,
  CONSTRAINT `fk_payment_payment_method1`
    FOREIGN KEY (`payment_method_id`)
    REFERENCES `trading`.`payment_method` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `trading`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trading`.`order` (
  `id` INT AUTO_INCREMENT,
  `order_date` DATE NULL DEFAULT NULL,
  `total` DOUBLE NULL DEFAULT NULL,
  `orderdetail_id` INT NOT NULL,
  `payment_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_orderdetail1_idx` (`orderdetail_id` ASC) VISIBLE,
  INDEX `fk_order_payment1_idx` (`payment_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_orderdetail1`
    FOREIGN KEY (`orderdetail_id`)
    REFERENCES `trading`.`orderdetail` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_payment1`
    FOREIGN KEY (`payment_id`)
    REFERENCES `trading`.`payment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `trading`.`product_image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trading`.`product_image` (
  `id` INT AUTO_INCREMENT,
  `url` VARCHAR(45) NULL DEFAULT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product_image_product1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_image_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `trading`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `trading`.`reriew`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trading`.`reriew` (
  `id` INT AUTO_INCREMENT,
  `coment` VARCHAR(45) NULL DEFAULT NULL,
  `date_content` DATE NULL DEFAULT NULL,
  `evaluate` INT NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `reriew_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reriew_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_reriew_product1_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_reriew_reriew1_idx` (`reriew_id` ASC) VISIBLE,
  CONSTRAINT `fk_reriew_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `trading`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reriew_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `trading`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reriew_reriew1`
    FOREIGN KEY (`reriew_id`)
    REFERENCES `trading`.`reriew` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `trading`.`voucher_money`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trading`.`voucher_money` (
  `money` DOUBLE NOT NULL,
  `voucher_id` INT NOT NULL,
  INDEX `fk_voucher_money_voucher1_idx` (`voucher_id` ASC) VISIBLE,
  PRIMARY KEY (`voucher_id`),
  CONSTRAINT `fk_voucher_money_voucher1`
    FOREIGN KEY (`voucher_id`)
    REFERENCES `trading`.`voucher` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;