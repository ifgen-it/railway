-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema railwaydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema railwaydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `railwaydb` DEFAULT CHARACTER SET utf8 ;
USE `railwaydb` ;

-- -----------------------------------------------------
-- Table `railwaydb`.`Station`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `railwaydb`.`Station` (
  `station_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `station_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`station_id`),
  UNIQUE INDEX `station_name_UNIQUE` (`station_name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railwaydb`.`Arc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `railwaydb`.`Arc` (
  `arc_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `begin_station` INT UNSIGNED NOT NULL,
  `end_station` INT UNSIGNED NOT NULL,
  `length` INT UNSIGNED NULL,
  `price` DECIMAL(10,2) UNSIGNED NULL,
  PRIMARY KEY (`arc_id`),
  INDEX `fk_Arc_Station_idx` (`begin_station` ASC) VISIBLE,
  INDEX `fk_Arc_Station1_idx` (`end_station` ASC) VISIBLE,
  CONSTRAINT `fk_Arc_Station`
    FOREIGN KEY (`begin_station`)
    REFERENCES `railwaydb`.`Station` (`station_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Arc_Station1`
    FOREIGN KEY (`end_station`)
    REFERENCES `railwaydb`.`Station` (`station_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railwaydb`.`Route`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `railwaydb`.`Route` (
  `route_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `route_name` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`route_id`),
  UNIQUE INDEX `route_name_UNIQUE` (`route_name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railwaydb`.`RoutePath`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `railwaydb`.`RoutePath` (
  `route_path_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `route_id` INT UNSIGNED NOT NULL,
  `station` INT UNSIGNED NOT NULL,
  `station_next` INT UNSIGNED NOT NULL,
  `arc_time` TIME NULL,
  `stay_time` TIME NULL,
  PRIMARY KEY (`route_path_id`),
  INDEX `fk_RoutePath_Route1_idx` (`route_id` ASC) VISIBLE,
  INDEX `fk_RoutePath_Station1_idx` (`station` ASC) VISIBLE,
  INDEX `fk_RoutePath_Station2_idx` (`station_next` ASC) VISIBLE,
  CONSTRAINT `fk_RoutePath_Route1`
    FOREIGN KEY (`route_id`)
    REFERENCES `railwaydb`.`Route` (`route_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_RoutePath_Station1`
    FOREIGN KEY (`station`)
    REFERENCES `railwaydb`.`Station` (`station_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_RoutePath_Station2`
    FOREIGN KEY (`station_next`)
    REFERENCES `railwaydb`.`Station` (`station_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railwaydb`.`LocomotiveType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `railwaydb`.`LocomotiveType` (
  `locomotive_type_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `locomotive_type` VARCHAR(60) NOT NULL,
  `price_factor` DECIMAL(5,2) UNSIGNED NULL DEFAULT 1,
  PRIMARY KEY (`locomotive_type_id`),
  UNIQUE INDEX `locomotive_type_UNIQUE` (`locomotive_type` ASC) VISIBLE)
ENGINE = InnoDB
ROW_FORMAT = DYNAMIC;


-- -----------------------------------------------------
-- Table `railwaydb`.`Locomotive`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `railwaydb`.`Locomotive` (
  `locomotive_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `locomotive_type_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`locomotive_id`),
  INDEX `fk_Locomotive_LocomotiveType1_idx` (`locomotive_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_Locomotive_LocomotiveType1`
    FOREIGN KEY (`locomotive_type_id`)
    REFERENCES `railwaydb`.`LocomotiveType` (`locomotive_type_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railwaydb`.`CarriageType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `railwaydb`.`CarriageType` (
  `carriage_type_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `carriage_type` VARCHAR(60) NOT NULL,
  `seats` INT UNSIGNED NOT NULL,
  `price_factor` DECIMAL(5,2) UNSIGNED NULL DEFAULT 1,
  PRIMARY KEY (`carriage_type_id`),
  UNIQUE INDEX `carriage_type_UNIQUE` (`carriage_type` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railwaydb`.`Carriage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `railwaydb`.`Carriage` (
  `carriage_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `carriage_type_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`carriage_id`),
  INDEX `fk_Carriage_CarriageType1_idx` (`carriage_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_Carriage_CarriageType1`
    FOREIGN KEY (`carriage_type_id`)
    REFERENCES `railwaydb`.`CarriageType` (`carriage_type_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railwaydb`.`Train`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `railwaydb`.`Train` (
  `train_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `locomotive_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`train_id`),
  INDEX `fk_Train_Locomotive1_idx` (`locomotive_id` ASC) INVISIBLE,
  CONSTRAINT `fk_Train_Locomotive1`
    FOREIGN KEY (`locomotive_id`)
    REFERENCES `railwaydb`.`Locomotive` (`locomotive_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railwaydb`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `railwaydb`.`Role` (
  `role_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE INDEX `role_UNIQUE` (`role` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railwaydb`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `railwaydb`.`User` (
  `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id` INT UNSIGNED NOT NULL,
  `firstName` VARCHAR(60) NOT NULL,
  `last_name` VARCHAR(60) NOT NULL,
  `birthday` DATE NOT NULL,
  `email` VARCHAR(80) NOT NULL,
  `password` VARCHAR(80) NOT NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  INDEX `fk_User_Role1_idx` (`role_id` ASC) INVISIBLE,
  CONSTRAINT `fk_User_Role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `railwaydb`.`Role` (`role_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railwaydb`.`Timeline`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `railwaydb`.`Timeline` (
  `timeline_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `route_id` INT UNSIGNED NOT NULL,
  `train_id` INT UNSIGNED NOT NULL,
  `start_datetime` DATETIME NOT NULL,
  PRIMARY KEY (`timeline_id`),
  INDEX `fk_Timeline_Route1_idx` (`route_id` ASC) VISIBLE,
  INDEX `fk_Timeline_Train1_idx` (`train_id` ASC) VISIBLE,
  CONSTRAINT `fk_Timeline_Route1`
    FOREIGN KEY (`route_id`)
    REFERENCES `railwaydb`.`Route` (`route_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Timeline_Train1`
    FOREIGN KEY (`train_id`)
    REFERENCES `railwaydb`.`Train` (`train_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railwaydb`.`Train_Carriage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `railwaydb`.`Train_Carriage` (
  `train_carriage_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `train_id` INT UNSIGNED NOT NULL,
  `carriage_id` INT UNSIGNED NOT NULL,
  `carriage_number` INT UNSIGNED NOT NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`train_carriage_id`),
  INDEX `fk_Train_Carriage_Train1_idx` (`train_id` ASC) VISIBLE,
  INDEX `fk_Train_Carriage_Carriage1_idx` (`carriage_id` ASC) VISIBLE,
  CONSTRAINT `fk_Train_Carriage_Train1`
    FOREIGN KEY (`train_id`)
    REFERENCES `railwaydb`.`Train` (`train_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Train_Carriage_Carriage1`
    FOREIGN KEY (`carriage_id`)
    REFERENCES `railwaydb`.`Carriage` (`carriage_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railwaydb`.`SeatStatus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `railwaydb`.`SeatStatus` (
  `seat_status_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`seat_status_id`),
  UNIQUE INDEX `status_UNIQUE` (`status` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railwaydb`.`Seat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `railwaydb`.`Seat` (
  `seat_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `timeline_id` INT UNSIGNED NOT NULL,
  `train_carriage_id` INT UNSIGNED NOT NULL,
  `seat` INT UNSIGNED NOT NULL,
  `seat_status_id` INT UNSIGNED NOT NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seat_id`),
  INDEX `fk_Seat_Timeline1_idx` (`timeline_id` ASC) VISIBLE,
  INDEX `fk_Seat_Train_Carriage1_idx` (`train_carriage_id` ASC) INVISIBLE,
  INDEX `fk_Seat_SeatStatus1_idx` (`seat_status_id` ASC) VISIBLE,
  CONSTRAINT `fk_Seat_Timeline1`
    FOREIGN KEY (`timeline_id`)
    REFERENCES `railwaydb`.`Timeline` (`timeline_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Seat_Train_Carriage1`
    FOREIGN KEY (`train_carriage_id`)
    REFERENCES `railwaydb`.`Train_Carriage` (`train_carriage_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Seat_SeatStatus1`
    FOREIGN KEY (`seat_status_id`)
    REFERENCES `railwaydb`.`SeatStatus` (`seat_status_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railwaydb`.`Ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `railwaydb`.`Ticket` (
  `ticket_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `start_station` INT UNSIGNED NOT NULL,
  `finish_station` INT UNSIGNED NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  `seat_id` INT UNSIGNED NOT NULL,
  `fix_price` DECIMAL(10,2) UNSIGNED NOT NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ticket_id`),
  INDEX `fk_Ticket_Station1_idx` (`start_station` ASC) VISIBLE,
  INDEX `fk_Ticket_Station2_idx` (`finish_station` ASC) VISIBLE,
  INDEX `fk_Ticket_User1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_Ticket_Seat1_idx` (`seat_id` ASC) VISIBLE,
  CONSTRAINT `fk_Ticket_Station1`
    FOREIGN KEY (`start_station`)
    REFERENCES `railwaydb`.`Station` (`station_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Ticket_Station2`
    FOREIGN KEY (`finish_station`)
    REFERENCES `railwaydb`.`Station` (`station_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Ticket_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `railwaydb`.`User` (`user_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Ticket_Seat1`
    FOREIGN KEY (`seat_id`)
    REFERENCES `railwaydb`.`Seat` (`seat_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
