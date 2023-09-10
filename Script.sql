-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS `practicajaweb` DEFAULT CHARACTER SET utf8;

-- Use the database
USE `practicajaweb`;

-- Table: Banco
CREATE TABLE IF NOT EXISTS `Banco` (
  `idBanco` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `nombreBanco` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `idBanco_UNIQUE` (`idBanco`)
) ENGINE = InnoDB;

-- Table: Servicios
CREATE TABLE IF NOT EXISTS `Servicios` (
  `idServicios` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `descripcion` VARCHAR(25) NOT NULL,
  `Banco_idBanco` INT NOT NULL,
  INDEX `fk_Servicios_Banco_idx` (`Banco_idBanco`)
) ENGINE = InnoDB;

-- Table: Usuario
CREATE TABLE IF NOT EXISTS `Usuario` (
  `idUsuario` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `nombre` VARCHAR(45) NOT NULL,
  `aPaterno` VARCHAR(45) NOT NULL,
  `aMaterno` VARCHAR(45) NOT NULL,
  `Banco_idBanco` INT NOT NULL,
  INDEX `fk_Usuario_Banco1_idx` (`Banco_idBanco`)
) ENGINE = InnoDB;

-- Table: Cuenta
CREATE TABLE IF NOT EXISTS `Cuenta` (
  `noCuenta` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `saldo` DECIMAL(12,2) NOT NULL,
  `Banco_idBanco` INT NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  INDEX `fk_Cuenta_Banco1_idx` (`Banco_idBanco`),
  INDEX `fk_Cuenta_Usuario1_idx` (`Usuario_idUsuario`)
) ENGINE = InnoDB;

-- Table: Movimientos
CREATE TABLE IF NOT EXISTS `Movimientos` (
  `idMovimientos` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `tipoMovimiento` VARCHAR(15) NOT NULL,
  `Importe` DECIMAL(12,2) NOT NULL,
  `fechaMovimiento` DATE NOT NULL,
  `Cuenta_noCuenta` INT NOT NULL,
  `Cuenta_Banco_idBanco` INT NOT NULL,
  `Cuenta_Usuario_idUsuario` INT NOT NULL,
  `noCuentaDestino` INT
) ENGINE = InnoDB;

-- Table: Tarjeta
CREATE TABLE IF NOT EXISTS `Tarjeta` (
  `noTarjeta` CHAR(16) NOT NULL PRIMARY KEY,
  `fechaExpiracion` DATE NOT NULL,
  `CVV` CHAR(3) NOT NULL,
  `NIP` CHAR(4) NOT NULL,
  `emisor` CHAR(2) NOT NULL,
  `Cuenta_noCuenta` INT NOT NULL,
  `Cuenta_idBanco` INT NOT NULL,
  `Cuenta_idUsuario` INT NOT NULL,
  INDEX `fk_Tarjeta_Cuenta1_idx` (`Cuenta_noCuenta`, `Cuenta_idBanco`, `Cuenta_idUsuario`)
) ENGINE = InnoDB;
