CREATE DATABASE  IF NOT EXISTS `car_workshop` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci */;
USE `car_workshop`;
-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: localhost    Database: car_workshop
-- ------------------------------------------------------
-- Server version	5.7.25-0ubuntu0.18.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `surname` varchar(45) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `birthDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Jan','Kowalski',NULL),(2,'Rafał','Nowak','2000-01-01'),(3,'Jarosław','Kurczyński','1991-03-22'),(4,'Joann','Wasilewska',NULL),(5,'Adam','Miauczyński','1980-02-11');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `recived` date NOT NULL,
  `plannedRepairDate` date DEFAULT NULL,
  `repairDate` date DEFAULT NULL,
  `workerId` int(11) DEFAULT NULL,
  `problemDescription` varchar(255) DEFAULT NULL,
  `repairDescription` varchar(255) DEFAULT NULL,
  `status` enum('accepted','repairCostApproved','underRepair','readyToPickup','cancelled','completed') NOT NULL,
  `vehicleId` int(11) DEFAULT '0',
  `repairCost` decimal(8,2) DEFAULT '0.00',
  `partsCost` decimal(8,2) DEFAULT '0.00',
  `ratePerHour` decimal(8,2) DEFAULT '0.00',
  `workHours` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `workerId_idx` (`workerId`),
  KEY `vehicleId_idx` (`vehicleId`),
  CONSTRAINT `vehicleId` FOREIGN KEY (`vehicleId`) REFERENCES `vehicle` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `workerId` FOREIGN KEY (`workerId`) REFERENCES `worker` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'2019-03-10',NULL,NULL,NULL,'Uszkodzona skrzynia biegów',NULL,'accepted',1,0.00,0.00,0.00,0),(2,'2019-03-11','2019-03-30','2019-03-30',2,'Wymiana kolcków hamulcowych','Bezproblemowa wymiana','readyToPickup',2,450.00,200.00,17.50,5),(3,'2019-03-01','2019-04-05',NULL,1,'Wymiana silnika',NULL,'repairCostApproved',3,1500.00,750.00,37.50,10),(4,'2019-03-15','2019-03-17','2019-03-16',3,'Wymiana kół','Zmieniono na komplet letni','readyToPickup',4,50.00,0.00,18.00,1),(5,'2019-03-14','2019-03-18',NULL,2,'Lakierowanie karoserii',NULL,'underRepair',5,500.00,100.00,17.50,10);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehicle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(45) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `model` varchar(45) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `manfuctureYear` int(10) unsigned NOT NULL,
  `registrationNumber` varchar(15) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `serviceDate` date NOT NULL,
  `customerId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `customerId_idx` (`customerId`),
  CONSTRAINT `customerId` FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (1,'Audi','A3',1999,'LBL33111','2019-04-01',1),(2,'Ford','Fiesta',2005,'KR22334','2019-05-22',2),(3,'Fiat','Stilo',2001,'DW20112','2020-01-01',3),(4,'BMW','1',2016,'MAZ99999','2019-09-30',4),(5,'Mercedes','S Class',1990,'K1SPEED','2019-10-11',5);
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worker`
--

DROP TABLE IF EXISTS `worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `worker` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `address` varchar(90) NOT NULL,
  `phoneNumber` varchar(20) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `ratePerHour` decimal(7,2) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worker`
--

LOCK TABLES `worker` WRITE;
/*!40000 ALTER TABLE `worker` DISABLE KEYS */;
INSERT INTO `worker` VALUES (1,'Piotr','Janik','Warszawska 1, Kraków','921 011 222','Bardzo dobry pracownik',35.00),(2,'Adam','Bielski','Janowska 22, Kraków','231 011 224','Pomocnik',17.50),(3,'Wojtek','Miś','Mazowiecka 3, Kraków','222 444 221','Pomocnik',18.00);
/*!40000 ALTER TABLE `worker` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-15 16:36:16
