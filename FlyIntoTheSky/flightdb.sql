-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: flightdb
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `agent`
--

DROP TABLE IF EXISTS `agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agent` (
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agent`
--

LOCK TABLES `agent` WRITE;
/*!40000 ALTER TABLE `agent` DISABLE KEYS */;
/*!40000 ALTER TABLE `agent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airport`
--

DROP TABLE IF EXISTS `airport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `airport` (
  `id` int NOT NULL,
  `location` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airport`
--

LOCK TABLES `airport` WRITE;
/*!40000 ALTER TABLE `airport` DISABLE KEYS */;
INSERT INTO `airport` VALUES (1,'HN'),(2,'SG'),(3,'DL'),(4,'PQ');
/*!40000 ALTER TABLE `airport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_UNIQUE` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'linkcute','214124124'),(2,'linkcute','342352');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flight` (
  `id` int NOT NULL,
  `origin` varchar(50) DEFAULT NULL,
  `destination` varchar(50) DEFAULT NULL,
  `day` date DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  `plane_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_flight_plane_idx` (`plane_id`) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_flight_plane` FOREIGN KEY (`plane_id`) REFERENCES `plane` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES (1,'HN','SG','2021-04-28','12pm',1),(2,'SG','PQ','2021-04-29','3pm',2),(3,'SG','HN','2021-04-30','7am',3),(4,'SG','DL','2021-05-01','8am',2),(5,'PQ','DL','2021-05-02','13pm',3),(7,'SG','DL','2021-05-03','5pm',3);
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plane`
--

DROP TABLE IF EXISTS `plane`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plane` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `seat_total` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plane`
--

LOCK TABLES `plane` WRITE;
/*!40000 ALTER TABLE `plane` DISABLE KEYS */;
INSERT INTO `plane` VALUES (1,'BB2503',10),(2,'PTA0611',9),(3,'TL0304',8);
/*!40000 ALTER TABLE `plane` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT 'BLANK',
  `plane_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_seat_plane_idx` (`plane_id`),
  CONSTRAINT `fk_seat_plane` FOREIGN KEY (`plane_id`) REFERENCES `plane` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES (1,'A1','Null',2),(2,'A2','Null',2),(3,'A3','Null',2),(4,'A4','Null',2),(5,'A5','Null',2),(6,'A6','Null',2),(7,'A7','Null',2),(8,'A8','Null',2),(9,'A9','Null',2),(10,'B1','Null',1),(11,'B2','Null',1),(12,'B3','Null',1),(13,'B4','Null',1),(14,'B5','Null',1),(15,'B6','Null',1),(16,'B7','Null',1),(17,'B8','Null',1),(18,'B9','Null',1),(19,'B10','Null',1),(20,'L1','Null',3),(21,'L2','Null',3),(22,'L3','Null',3),(23,'L4','Null',3),(24,'L5','Null',3),(25,'L6','Null',3),(26,'L7','Null',3),(27,'L8','Null',3);
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `id` int NOT NULL AUTO_INCREMENT,
  `price` decimal(10,0) DEFAULT '0',
  `seat_id` int NOT NULL,
  `customer_id` int DEFAULT NULL,
  `agent_id` int DEFAULT NULL,
  `flight_id` int NOT NULL,
  `status` varchar(45) DEFAULT 'Null',
  `date_of_issue` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ticket_customer_idx` (`customer_id`),
  KEY `fk_ticket_agent_idx` (`agent_id`),
  KEY `fk_ticket_flight_idx` (`flight_id`),
  KEY `fk_ticket_seat1_idx` (`seat_id`),
  CONSTRAINT `fk_ticket_agent` FOREIGN KEY (`agent_id`) REFERENCES `agent` (`id`),
  CONSTRAINT `fk_ticket_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `fk_ticket_flight` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`id`),
  CONSTRAINT `fk_ticket_seat1` FOREIGN KEY (`seat_id`) REFERENCES `seat` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (81,0,23,2,NULL,7,'Booked',NULL),(82,0,24,NULL,NULL,7,'Null',NULL),(83,0,25,NULL,NULL,7,'Null',NULL),(84,0,26,NULL,NULL,7,'Null',NULL),(85,0,27,NULL,NULL,7,'Null',NULL);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-03 14:18:46
