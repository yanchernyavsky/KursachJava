CREATE DATABASE  IF NOT EXISTS `electricshop` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `electricshop`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: electricshop
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `category` (
  `categoryname` varchar(45) NOT NULL,
  `productnumber` varchar(45) DEFAULT NULL,
  `description` varchar(100) NOT NULL,
  `availability` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`categoryname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('Обогрев','13','Приборы для обогрева и утепления','высокая'),('Освещение','50','Приборы для освещения','высокая'),('Электричество','22','Провода, разетки и всё связанное с электропроводкой','низкая');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `client` (
  `idclient` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `phonenumber` varchar(45) NOT NULL,
  PRIMARY KEY (`idclient`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (111,'Виталий','Волинов','Васильевич','+375 44 222 11 00'),(112,'Руперт','Рупоров','Дмитриевич','+375 53 234 66 77'),(113,'Миша','Богомолов','Сергеевчи','+3756528345');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders` (
  `idorder` int(11) NOT NULL,
  `idclient` int(11) NOT NULL,
  `idproductorder` int(11) NOT NULL,
  `idprovider` int(11) NOT NULL,
  `productnumber` int(11) DEFAULT NULL,
  `finalprice` int(111) DEFAULT NULL,
  `isready` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idorder`,`idprovider`,`idproductorder`,`idclient`),
  KEY `fk_idclient_idx` (`idclient`),
  KEY `fk_idproduct_idx` (`idproductorder`),
  KEY `fk_idprovider_idx` (`idprovider`),
  CONSTRAINT `fk_client` FOREIGN KEY (`idclient`) REFERENCES `client` (`idclient`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_idproduct` FOREIGN KEY (`idproductorder`) REFERENCES `product` (`idproduct`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_iproviderfororder` FOREIGN KEY (`idprovider`) REFERENCES `provider` (`idprovider`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1111,111,11,1,5,50,0),(1112,112,12,2,15,30,0),(1113,113,14,4,5,NULL,1),(1114,113,12,2,1,NULL,0),(1115,113,13,2,2,NULL,0);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product` (
  `idproduct` int(11) NOT NULL,
  `idprovider` int(11) NOT NULL,
  `productname` varchar(45) NOT NULL,
  `number` int(40) DEFAULT NULL,
  `price` int(40) DEFAULT NULL,
  `categoryname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idproduct`,`idprovider`),
  KEY `idprovider_idx` (`idprovider`),
  KEY `fk_categoryname_idx` (`categoryname`),
  CONSTRAINT `fk_categoryname` FOREIGN KEY (`categoryname`) REFERENCES `category` (`categoryname`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_idprovider` FOREIGN KEY (`idprovider`) REFERENCES `provider` (`idprovider`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (11,1,'Радиатор',13,10,'Обогрев'),(12,2,'Фонарик',50,2,'Освещение'),(13,2,'Подсвечник',35,5,'Освещение'),(14,4,'Сетевой кабель',100,3,'Электричество');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provider`
--

DROP TABLE IF EXISTS `provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `provider` (
  `idprovider` int(11) NOT NULL,
  `companyname` varchar(45) NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  `phonenumber` varchar(45) NOT NULL,
  PRIMARY KEY (`idprovider`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provider`
--

LOCK TABLES `provider` WRITE;
/*!40000 ALTER TABLE `provider` DISABLE KEYS */;
INSERT INTO `provider` VALUES (1,'Royal Thermo','Октябрьска, 19б','+375 29 605-88-09'),(2,'Яркий Луч','Минск,12-34','+375 69 828 29 29'),(3,'СветлыйДомм','Пушкина,42','+375291842356'),(4,'Электромощь','Веснянкина,85','+375291568434'),(5,'MakeItClear','Свободы,34','+375298653456');
/*!40000 ALTER TABLE `provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `idusers` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `admin` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idusers`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'bob','baobab','123',1),(2,'charlie','vortex','54rf',0),(3,'MikeTest','mike','3',0),(4,'robert','rob23','42',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-09 22:05:19
