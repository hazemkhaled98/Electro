-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema electro_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema electro_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `electro_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `electro_db` ;

-- -----------------------------------------------------
-- Table `electro_db`.`customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `electro_db`.`customers` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `customer_name` VARCHAR(255) NOT NULL,
  `birthday` DATE NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `job` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `credit_limit` DECIMAL(15,2) NULL DEFAULT '0.00',
  `city` VARCHAR(255) NOT NULL,
  `country` VARCHAR(255) NOT NULL,
  `street_no` VARCHAR(255) NOT NULL,
  `street_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE INDEX `email` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `electro_db`.`carts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `electro_db`.`carts` (
  `cart_id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`cart_id`),
  UNIQUE INDEX `customer_id` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `carts_ibfk_1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `electro_db`.`customers` (`customer_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `electro_db`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `electro_db`.`products` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(255) NOT NULL,
  `stock_quantity` INT NOT NULL DEFAULT '0',
  `product_description` VARCHAR(255) NULL DEFAULT NULL,
  `product_price` DECIMAL(15,2) NOT NULL,
  `category` VARCHAR(255) NOT NULL,
  `product_pic` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE INDEX `product_name_UNIQUE` (`product_name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 53
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `electro_db`.`cart_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `electro_db`.`cart_items` (
  `cart_item_id` INT NOT NULL AUTO_INCREMENT,
  `cart_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  `amount` DECIMAL(15,2) NOT NULL,
  PRIMARY KEY (`cart_item_id`),
  INDEX `cart_id` (`cart_id` ASC) VISIBLE,
  INDEX `product_id` (`product_id` ASC) VISIBLE,
  CONSTRAINT `cart_item_ibfk_1`
    FOREIGN KEY (`cart_id`)
    REFERENCES `electro_db`.`carts` (`cart_id`),
  CONSTRAINT `cart_item_ibfk_2`
    FOREIGN KEY (`product_id`)
    REFERENCES `electro_db`.`products` (`product_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `electro_db`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `electro_db`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `ordered_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`),
  INDEX `customer_id` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `orders_ibfk_1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `electro_db`.`customers` (`customer_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 49
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `electro_db`.`order_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `electro_db`.`order_items` (
  `order_item_id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  `amount` DECIMAL(15,2) NOT NULL,
  PRIMARY KEY (`order_item_id`),
  INDEX `order_id` (`order_id` ASC) VISIBLE,
  INDEX `product_id` (`product_id` ASC) VISIBLE,
  CONSTRAINT `orders_items_ibfk_1`
    FOREIGN KEY (`order_id`)
    REFERENCES `electro_db`.`orders` (`order_id`),
  CONSTRAINT `orders_items_ibfk_2`
    FOREIGN KEY (`product_id`)
    REFERENCES `electro_db`.`products` (`product_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
