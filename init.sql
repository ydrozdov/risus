SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema familytree
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema familytree
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `familytree` DEFAULT CHARACTER SET utf8 ;
USE `familytree` ;

-- -----------------------------------------------------
-- Table `familytree`.`person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `familytree`.`person` ;

CREATE TABLE IF NOT EXISTS `familytree`.`person` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `birth_date` DATE NULL,
  `birth_place` VARCHAR(255) NULL,
  `sex` ENUM('UNSPECIFIED', 'MALE', 'FEMALE') NULL DEFAULT 'UNSPECIFIED',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `familytree`.`relationship_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `familytree`.`relationship_type` ;

CREATE TABLE IF NOT EXISTS `familytree`.`relationship_type` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `familytree`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `familytree`.`role` ;

CREATE TABLE IF NOT EXISTS `familytree`.`role` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `familytree`.`relationship`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `familytree`.`relationship` ;

CREATE TABLE IF NOT EXISTS `familytree`.`relationship` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `person1_id` INT UNSIGNED NULL,
  `person2_id` INT UNSIGNED NULL,
  `date_relationship_started` DATE NULL,
  `date_relationship_ended` DATE NULL,
  `relationship_type_id` INT UNSIGNED NOT NULL,
  `person1_role` INT UNSIGNED NULL,
  `person2_role` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_relationship_relationship_type_idx` (`relationship_type_id` ASC),
  INDEX `fk_relationship_person1_idx` (`person1_id` ASC),
  INDEX `fk_relationship_person2_idx` (`person2_id` ASC),
  INDEX `fk_relationship_role_person1_idx` (`person1_role` ASC),
  INDEX `fk_relationship_role_person2_idx` (`person2_role` ASC),
  CONSTRAINT `fk_relationship_relationship_type`
    FOREIGN KEY (`relationship_type_id`)
    REFERENCES `familytree`.`relationship_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_relationship_person1`
    FOREIGN KEY (`person1_id`)
    REFERENCES `familytree`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_relationship_role_person2`
    FOREIGN KEY (`person2_role`)
    REFERENCES `familytree`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_relationship_person2`
    FOREIGN KEY (`person2_id`)
    REFERENCES `familytree`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_relationship_role_person1`
    FOREIGN KEY (`person1_role`)
    REFERENCES `familytree`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `familytree`.`person`
-- -----------------------------------------------------
START TRANSACTION;
USE `familytree`;
INSERT INTO `familytree`.`person` (`id`, `name`, `birth_date`, `birth_place`, `sex`) VALUES (1, 'Adam', NULL, 'Edem', 'MALE');
INSERT INTO `familytree`.`person` (`id`, `name`, `birth_date`, `birth_place`, `sex`) VALUES (2, 'Eve', NULL, 'Edem', 'FEMALE');

COMMIT;


-- -----------------------------------------------------
-- Data for table `familytree`.`relationship_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `familytree`;
INSERT INTO `familytree`.`relationship_type` (`id`, `name`) VALUES (1, 'Marriage');
INSERT INTO `familytree`.`relationship_type` (`id`, `name`) VALUES (2, 'Divorce');
INSERT INTO `familytree`.`relationship_type` (`id`, `name`) VALUES (3, 'Parenthood');

COMMIT;


-- -----------------------------------------------------
-- Data for table `familytree`.`role`
-- -----------------------------------------------------
START TRANSACTION;
USE `familytree`;
INSERT INTO `familytree`.`role` (`id`, `name`) VALUES (1, 'Wife');
INSERT INTO `familytree`.`role` (`id`, `name`) VALUES (2, 'Husband');
INSERT INTO `familytree`.`role` (`id`, `name`) VALUES (3, 'Son');
INSERT INTO `familytree`.`role` (`id`, `name`) VALUES (4, 'Daughter');
INSERT INTO `familytree`.`role` (`id`, `name`) VALUES (5, 'Sister');
INSERT INTO `familytree`.`role` (`id`, `name`) VALUES (6, 'Brother');
INSERT INTO `familytree`.`role` (`id`, `name`) VALUES (7, 'Father');
INSERT INTO `familytree`.`role` (`id`, `name`) VALUES (8, 'Mother');
INSERT INTO `familytree`.`role` (`id`, `name`) VALUES (9, 'Parent');
INSERT INTO `familytree`.`role` (`id`, `name`) VALUES (10, 'Child');
INSERT INTO `familytree`.`role` (`id`, `name`) VALUES (11, 'Spouse');
INSERT INTO `familytree`.`role` (`id`, `name`) VALUES (12, 'Sibling');

COMMIT;


-- -----------------------------------------------------
-- Data for table `familytree`.`relationship`
-- -----------------------------------------------------
START TRANSACTION;
USE `familytree`;
INSERT INTO `familytree`.`relationship` (`id`, `person1_id`, `person2_id`, `date_relationship_started`, `date_relationship_ended`, `relationship_type_id`, `person1_role`, `person2_role`) VALUES (1, 1, 2, NULL, NULL, 1, 1, 2);

COMMIT;

