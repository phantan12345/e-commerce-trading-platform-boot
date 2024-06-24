-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: trading
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKda8tuywtf0gb6sedwk7la1pgi` (`user_id`),
  CONSTRAINT `FKda8tuywtf0gb6sedwk7la1pgi` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'61/2 duong 379',62),(2,'61/2 đường 397, tăng nhơn phú a, tp.thủ đức, tp. hồ chính minh',62);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `is_delete` tinyint DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Phone',0),(2,'Wireless',0),(3,'Ipad',0),(4,'Watch',0),(37,'Air Pod',1),(38,'Wireless Charging',0),(39,'Laptop',1);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `message` varchar(999) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `sent_by` int NOT NULL,
  `sent_to` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_messages_user2_idx` (`sent_by`),
  KEY `fk_messages_user1_idx` (`sent_to`),
  CONSTRAINT `fk_messages_user1` FOREIGN KEY (`sent_to`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_messages_user2` FOREIGN KEY (`sent_by`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (154,'hello','R',62,59),(155,'hi','R',59,62);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order1`
--

DROP TABLE IF EXISTS `order1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order1` (
  `id` int NOT NULL AUTO_INCREMENT,
  `is_delete` tinyint DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  `payment_id` int DEFAULT NULL,
  `UserID` int DEFAULT NULL,
  `total` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_payment1_idx` (`payment_id`),
  KEY `FK_Orders_Users` (`UserID`),
  CONSTRAINT `fk_order_payment1` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`),
  CONSTRAINT `FK_Orders_Users` FOREIGN KEY (`UserID`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order1`
--

LOCK TABLES `order1` WRITE;
/*!40000 ALTER TABLE `order1` DISABLE KEYS */;
INSERT INTO `order1` VALUES (132,0,'2024-06-04',1,59,100),(133,0,'2024-06-04',1,59,100),(134,0,'2024-06-04',1,54,100),(135,0,'2024-06-09',1,62,5593),(136,0,'2024-06-09',1,62,7596),(137,0,'2024-06-09',2,59,13293),(138,0,'2024-06-10',2,62,10),(139,0,'2024-06-10',2,62,10),(140,0,'2024-06-10',2,62,10),(141,0,'2024-06-10',2,62,10),(142,0,'2024-06-10',2,62,10),(143,0,'2024-06-10',2,62,10),(144,0,'2024-06-10',2,62,10),(145,0,'2024-06-10',2,62,10),(146,0,'2024-06-10',2,62,10),(147,0,'2024-06-10',2,62,10),(148,0,'2024-06-11',2,62,10),(149,0,'2024-06-14',1,62,101),(150,0,'2024-06-14',1,62,101),(151,0,'2024-06-14',1,62,101),(152,0,'2024-06-14',1,62,101),(153,0,'2024-06-14',1,62,101),(154,0,'2024-06-14',1,62,9),(155,0,'2024-06-14',1,62,9),(156,0,'2024-06-14',1,62,9),(157,0,'2024-06-14',1,62,9),(158,0,'2024-06-19',1,62,10);
/*!40000 ALTER TABLE `order1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetail`
--

DROP TABLE IF EXISTS `orderdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quatity` int DEFAULT NULL,
  `total` decimal(10,0) DEFAULT NULL,
  `order_id` int NOT NULL,
  `is_delete` tinyint DEFAULT NULL,
  `product_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_orderdetail_order1_idx` (`order_id`),
  KEY `FKdubukg3j0fymgci0idnd72k0` (`product_id`),
  CONSTRAINT `fk_orderdetail_order1` FOREIGN KEY (`order_id`) REFERENCES `order1` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKdubukg3j0fymgci0idnd72k0` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetail`
--

LOCK TABLES `orderdetail` WRITE;
/*!40000 ALTER TABLE `orderdetail` DISABLE KEYS */;
INSERT INTO `orderdetail` VALUES (77,10,10,132,0,70),(78,10,10,133,0,70),(79,10,10,134,0,70),(80,10,10,134,0,71),(81,7,799,135,0,74),(82,4,1899,136,0,76),(83,7,1899,137,0,76),(84,1,10,138,0,83),(85,1,10,139,0,83),(86,1,10,140,0,83),(87,1,10,141,0,83),(88,1,10,142,0,83),(89,1,10,143,0,83),(90,1,10,144,0,83),(91,1,10,145,0,82),(92,1,10,146,0,82),(93,1,10,147,0,82),(94,1,10,148,0,82),(95,1011,30,149,0,73),(96,1011,30,150,0,73),(97,1011,30,151,0,73),(98,1011,30,152,0,73),(99,1011,30,153,0,73),(100,99,30,154,0,73),(101,99,30,155,0,73),(102,99,30,156,0,73),(103,99,30,157,0,73),(104,10,10,158,0,88);
/*!40000 ALTER TABLE `orderdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `payment` varchar(45) DEFAULT NULL,
  `is_delete` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,'delivery',0),(2,'VNPay',0);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `is_delete` tinyint DEFAULT NULL,
  `count` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_category1_idx` (`category_id`),
  KEY `FK979liw4xk18ncpl87u4tygx2u` (`user_id`),
  CONSTRAINT `FK979liw4xk18ncpl87u4tygx2u` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_product_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (70,'IPhone 15 Pro',679.00,1,1,100,'a',59),(71,'Iphone 14 Pro',899.00,1,1,100,'a',59),(72,'Iphone 15',999.00,1,1,100,'â',59),(73,'Iphone 15 Pro Max',999.00,1,0,1,'sp dep',59),(74,'Iphone 13 Pro Max',799.00,1,0,100,'â',59),(75,'Iphone 13 Pro',679.00,1,1,100,'a',59),(76,'Citizen',1899.00,4,0,100,'â',59),(77,'Apple Watch Ultra Titanium',1099.00,4,0,100,'a',59),(78,'Rolex Z47HR2Q',9900.00,4,0,100,'a',59),(79,'ROLEX IJ8Y6W1',8900.00,4,0,100,'a',59),(80,'ROLEX OUU78HGT',9888.00,4,0,100,'â',59),(81,'ROLEX 8JH9O',7899.00,4,0,100,'a',59),(82,'HUBLOT MX78',7800.00,4,0,100,'â',59),(83,'HUBLOT 7HU2WQ',6900.00,4,0,100,'a',59),(84,'HUBLOT IO09FEW',7980.00,4,0,100,'a',59),(88,'WIRELESS',563.00,2,0,9990,NULL,59);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_image`
--

DROP TABLE IF EXISTS `product_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `product_id` int NOT NULL,
  `is_delete` tinyint DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_product_image_product1_idx` (`product_id`),
  CONSTRAINT `fk_product_image_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_image`
--

LOCK TABLES `product_image` WRITE;
/*!40000 ALTER TABLE `product_image` DISABLE KEYS */;
INSERT INTO `product_image` VALUES (77,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1713623593/gzbeovkb3tdr3o2gucem.jpg',70,0),(78,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1715273457/uhe3qfop7h9rkj6em2xe.jpg',71,0),(79,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1715332084/acrp6cxhpootpqsaneem.jpg',72,0),(80,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1715332252/pwcshhzze6b0jjo3pkaq.jpg',73,0),(81,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1715490577/l1nkvabgahn6iyqjeubw.jpg',74,0),(82,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1715490606/vphikjl5tykizqrtoxzp.jpg',75,0),(83,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1715490858/orpz1wjaejwayllocbx1.png',76,0),(84,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1715490915/rugj6pyesn7fwgeuwrvp.jpg',77,0),(85,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1715491263/c4w2b9pczbqzpxphgeyk.jpg',78,0),(86,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1715491303/qim8l3zecksyodyg5ab0.jpg',79,0),(87,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1715491333/pvl60z7wuai0zhuaom4k.jpg',80,0),(88,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1715491369/rqyywbmbzersh6rtn8mo.webp',81,0),(89,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1715491390/d8fhln7fwtixj9beyw95.jpg',82,0),(90,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1715491416/o5f6wabxd3injaxe7wqn.jpg',83,0),(91,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1715491457/ycrfw6f13zhjteteivmm.jpg',84,0),(95,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1717521996/m4b0qgyyk1fbhbrclf5k.png',88,0);
/*!40000 ALTER TABLE `product_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `coment` varchar(45) DEFAULT NULL,
  `date_content` date DEFAULT NULL,
  `user_id` int NOT NULL,
  `product_id` int NOT NULL,
  `review_id` int DEFAULT NULL,
  `is_delete` tinyint DEFAULT '0',
  `evaluate` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reriew_user1_idx` (`user_id`),
  KEY `fk_reriew_product1_idx` (`product_id`),
  KEY `fk_review_review1_idx` (`review_id`),
  CONSTRAINT `fk_reriew_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_reriew_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_review_review1` FOREIGN KEY (`review_id`) REFERENCES `review` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (14,'phantan','2024-04-20',54,73,16,0,NULL),(15,'sssss','2024-04-21',54,73,16,0,NULL),(16,'điện thoại dễ trày vl','2024-05-10',54,73,NULL,0,NULL),(17,'tnasnd','2024-06-04',59,80,NULL,0,NULL),(18,'string','2024-06-19',62,73,NULL,0,4.5),(19,'string','2024-06-19',62,73,NULL,0,4.5);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `is_delete` tinyint DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN',0),(2,'USER',0),(3,'SALER',0);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipment`
--

DROP TABLE IF EXISTS `shipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(45) DEFAULT NULL,
  `active` varchar(99) DEFAULT NULL,
  `orderdetail_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgvqtg8osaun3g84xjh0dduc4m` (`orderdetail_id`),
  CONSTRAINT `FKgvqtg8osaun3g84xjh0dduc4m` FOREIGN KEY (`orderdetail_id`) REFERENCES `orderdetail` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipment`
--

LOCK TABLES `shipment` WRITE;
/*!40000 ALTER TABLE `shipment` DISABLE KEYS */;
INSERT INTO `shipment` VALUES (37,'string','Accepted',78),(38,'string','Accepted',79),(39,'string','Accepted',80),(40,'asd','Canceled',81),(42,'jhj','Wait for confirmation',83),(43,'string','Wait for confirmation',84),(44,'string','Wait for confirmation',85),(45,'string','Wait for confirmation',86),(46,'string','Wait for confirmation',87),(47,'string','Wait for confirmation',88),(48,'string','Wait for confirmation',89),(49,'string','Wait for confirmation',90),(50,'string','Wait for confirmation',91),(51,'string','Wait for confirmation',92),(52,'string','Wait for confirmation',93),(53,'string','Wait for confirmation',94),(54,'string','Wait for confirmation',100),(55,'string','Wait for confirmation',104);
/*!40000 ALTER TABLE `shipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `Phone` varchar(10) DEFAULT NULL,
  `is_delete` tinyint DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `refech_token` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id_idx` (`role_id`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (54,'admin','$2a$10$nLv9fFpoY4Jamw/k/VYIk.MZDe9hY4P/TFC3OZGWgLMA.CuYvnpUC','https://res.cloudinary.com/ddznsqfbo/image/upload/v1713448268/xhsyjo3unpoqeal0rchw.jpg','admin@gmail.com','037274593',0,1,NULL,'admin'),(59,'hoan','$2a$10$nLv9fFpoY4Jamw/k/VYIk.MZDe9hY4P/TFC3OZGWgLMA.CuYvnpUC','https://res.cloudinary.com/ddznsqfbo/image/upload/v1715175121/mun0a49kmurftamkhmts.jpg','hoan@gmail.com','0377092008',0,3,NULL,'hoan'),(62,'user','$2a$10$70aaIQatVw6.vtWHvYTRE.nW7XXd6pWLgG6rVXp8GUjsV4MYr54um','https://res.cloudinary.com/ddznsqfbo/image/upload/v1717522908/qqid4cnd8mta8zewzxwt.png','phannhuttan2002@gmail.com','0372745193',0,2,NULL,'phan tan');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voucher`
--

DROP TABLE IF EXISTS `voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voucher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `discount` decimal(10,1) DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  `is_delete` tinyint DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher`
--

LOCK TABLES `voucher` WRITE;
/*!40000 ALTER TABLE `voucher` DISABLE KEYS */;
INSERT INTO `voucher` VALUES (1,699.0,'user1',1,'MONEY'),(4,0.3,'string',1,'PERSENT');
/*!40000 ALTER TABLE `voucher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'trading'
--

--
-- Dumping routines for database 'trading'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-20 17:17:28
