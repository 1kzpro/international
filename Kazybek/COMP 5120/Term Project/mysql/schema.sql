-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema db_term_project
-- -----------------------------------------------------
-- Database for Term Project COMP 5120 Spring 2023 Auburn University by Kazybek Mizam

-- -----------------------------------------------------
-- Schema db_term_project
--
-- Database for Term Project COMP 5120 Spring 2023 Auburn University by Kazybek Mizam
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_term_project` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `db_term_project` ;

-- -----------------------------------------------------
-- Table `db_term_project`.`suppliers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_term_project`.`suppliers` (
  `SupplierID` INT NOT NULL,
  `CompanyName` VARCHAR(45) NOT NULL,
  `ContactLastName` VARCHAR(45) NOT NULL,
  `ContactFirstName` VARCHAR(45) NOT NULL,
  `Phone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`SupplierID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_term_project`.`subjects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_term_project`.`subjects` (
  `SubjectID` INT NOT NULL,
  `CategoryName` VARCHAR(45) NULL,
  PRIMARY KEY (`SubjectID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_term_project`.`books`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_term_project`.`books` (
  `BookID` INT NOT NULL,
  `Title` VARCHAR(45) NOT NULL,
  `UnitPrice` FLOAT NOT NULL,
  `Author` VARCHAR(45) NOT NULL,
  `Quantity` INT NOT NULL,
  `SupplierID` INT NOT NULL,
  `SubjectID` INT NOT NULL,
  PRIMARY KEY (`BookID`),
  INDEX `SupplierID_idx` (`SupplierID` ASC) VISIBLE,
  INDEX `SubjectID_idx` (`SubjectID` ASC) VISIBLE,
  CONSTRAINT `SupplierID`
    FOREIGN KEY (`SupplierID`)
    REFERENCES `db_term_project`.`suppliers` (`SupplierID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `SubjectID`
    FOREIGN KEY (`SubjectID`)
    REFERENCES `db_term_project`.`subjects` (`SubjectID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_term_project`.`customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_term_project`.`customers` (
  `CustomerID` INT NOT NULL,
  `LastName` VARCHAR(45) NOT NULL,
  `FirstName` VARCHAR(45) NOT NULL,
  `Phone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`CustomerID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_term_project`.`employees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_term_project`.`employees` (
  `EmployeeID` INT NOT NULL,
  `LastName` VARCHAR(45) NOT NULL,
  `FirstName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`EmployeeID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_term_project`.`shippers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_term_project`.`shippers` (
  `ShipperID` INT NOT NULL,
  `ShipperName` VARCHAR(45) NULL,
  PRIMARY KEY (`ShipperID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_term_project`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_term_project`.`orders` (
  `OrderID` INT NOT NULL,
  `CustomerID` INT NOT NULL,
  `EmployeeID` INT NOT NULL,
  `OrderDate` DATETIME NOT NULL,
  `ShippedDate` DATETIME NOT NULL,
  `ShipperID` INT NULL,
  PRIMARY KEY (`OrderID`),
  INDEX `CustomerID_idx` (`CustomerID` ASC) VISIBLE,
  INDEX `EmployeeID_idx` (`EmployeeID` ASC) VISIBLE,
  INDEX `ShipperID_idx` (`ShipperID` ASC) VISIBLE,
  CONSTRAINT `CustomerID`
    FOREIGN KEY (`CustomerID`)
    REFERENCES `db_term_project`.`customers` (`CustomerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `EmployeeID`
    FOREIGN KEY (`EmployeeID`)
    REFERENCES `db_term_project`.`employees` (`EmployeeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ShipperID`
    FOREIGN KEY (`ShipperID`)
    REFERENCES `db_term_project`.`shippers` (`ShipperID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_term_project`.`order_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_term_project`.`order_details` (
  `BookID` INT NOT NULL,
  `OrderID` INT NOT NULL,
  `Quantity` INT NOT NULL,
  PRIMARY KEY (`BookID`, `OrderID`),
  INDEX `OrderID_idx` (`OrderID` ASC) VISIBLE,
  CONSTRAINT `BookID`
    FOREIGN KEY (`BookID`)
    REFERENCES `db_term_project`.`books` (`BookID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `OrderID`
    FOREIGN KEY (`OrderID`)
    REFERENCES `db_term_project`.`orders` (`OrderID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
