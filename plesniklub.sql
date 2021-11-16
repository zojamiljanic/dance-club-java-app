CREATE DATABASE /*!32312 IF NOT EXISTS*/`plesniklub` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `plesniklub`;

/*Table structure for table `Administrator` */

DROP TABLE IF EXISTS `Administrator`;

CREATE TABLE `Administrator` (
  `AdministratorID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(120) DEFAULT NULL,
  `Prezime` VARCHAR(120) DEFAULT NULL,
  `Username` VARCHAR(40) DEFAULT NULL,
  `Password` VARCHAR(40) DEFAULT NULL,
  PRIMARY KEY (`AdministratorID`)
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `Administrator` */

INSERT  INTO `Administrator`(`AdministratorID`,`Ime`,`Prezime`,`Username`,`Password`) VALUES 
(1,'Zoja','Miljanic','zoja','zoja123');

/*Table structure for table `trener` */

DROP TABLE IF EXISTS `trener`;

CREATE TABLE `trener` (
  `TrenerID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(120) DEFAULT NULL,
  `Prezime` VARCHAR(120) DEFAULT NULL,
  `BrojTelefona` VARCHAR(120) DEFAULT NULL,
  `Email` VARCHAR(120) DEFAULT NULL,
  PRIMARY KEY (`TrenerID`)
) ENGINE=INNODB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

/*Data for the table `trener` */

INSERT  INTO `trener`(`TrenerID`,`Ime`,`Prezime`,`BrojTelefona`,`Email`) VALUES 
(1,'Jovan','Ilic','0648276372','jole@gmail.com'),
(2,'Pera','Perovic','0654335372','pera@gmail.com'),
(3,'Marko','Markovic','063123372','mare@gmail.com');

/*Table structure for table `TipPlesa` */

DROP TABLE IF EXISTS `TipPlesa`;

CREATE TABLE `TipPlesa` (
  `TipPlesaID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `NazivPlesa` VARCHAR(120) DEFAULT NULL,
  PRIMARY KEY (`TipPlesaID`)
) ENGINE=INNODB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

/*Data for the table `TipPlesa` */

INSERT  INTO `TipPlesa`(`TipPlesaID`,`NazivPlesa`) VALUES 
(1,'Samba'),
(2,'Tango'),
(3,'Rumba'),
(4,'Salsa'),
(5,'Bachata');

/*Table structure for table `Plesac` */

DROP TABLE IF EXISTS `Plesac`;

CREATE TABLE `Plesac` (
  `PlesacID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(120) DEFAULT NULL,
  `Prezime` VARCHAR(120) DEFAULT NULL,
  `BrojTelefona` VARCHAR(120) DEFAULT NULL,
  `Pol` VARCHAR(10) DEFAULT NULL,
  PRIMARY KEY (`PlesacID`)
) ENGINE=INNODB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

/*Data for the table `Plesac` */

INSERT  INTO `Plesac`(`PlesacID`,`Ime`,`Prezime`,`BrojTelefona`,`Pol`) VALUES 
(1,'Milica','Ilic','0648276372', 'zenski'),
(2,'Petar','Stefanovic','061235272', 'muski'),
(3,'Vanja','Petrovic','0654321232', 'muski'),
(4,'Filip','Dramlic','0654333123', 'muski'),
(5,'Visnja','Hadziprodanovic','067436733', 'zenski'),
(6,'Jagoda','Dimitroski','0664432211', 'zenski');

DROP TABLE IF EXISTS `DnevniRaspored`;

CREATE TABLE `DnevniRaspored` (
  `DnevniRasporedID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `Datum` DATE DEFAULT NULL,
  PRIMARY KEY (`DnevniRasporedID`)
) ENGINE=INNODB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

INSERT  INTO `DnevniRaspored`(`DnevniRasporedID`,`Datum`) VALUES 
(1,'2021-05-20'),
(2,'2021-05-21');

DROP TABLE IF EXISTS `Trening`;

CREATE TABLE `Trening` (
  `DnevniRasporedID` BIGINT(20) NOT NULL,
  `RedniBroj` INTEGER NOT NULL,
  `Vreme` VARCHAR(20) DEFAULT NULL,
  `TrenerID` BIGINT(20) NOT NULL,
  `TipPlesaID` BIGINT(20) NOT NULL,
  PRIMARY KEY (`DnevniRasporedID`, `RedniBroj`),
  CONSTRAINT `trener1_ibfk_1` FOREIGN KEY (`TrenerID`) REFERENCES `Trener` (`TrenerID`) ON DELETE CASCADE,
  CONSTRAINT `tipPlesa1_ibfk_1` FOREIGN KEY (`TipPlesaID`) REFERENCES `TipPlesa` (`TipPlesaID`) ON DELETE CASCADE
) ENGINE=INNODB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

INSERT  INTO `Trening`(`DnevniRasporedID`,`RedniBroj`,`Vreme`,`TrenerID`,`TipPlesaID`) VALUES 
(1,1,'10h',1,1),
(1,2,'17h',2,2),
(2,1,'18h',3,4);


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
