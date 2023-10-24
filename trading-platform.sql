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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category_category1_idx` (`category_id`),
  CONSTRAINT `fk_category_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'SmartPhone',NULL),(2,'Iphone',1),(3,'SamSung',1),(4,'Sofa',NULL),(5,'Watch',NULL),(6,'Iphong 11',2),(7,'Iphone 12',2),(8,'Chair',NULL),(9,'Double Sofa',NULL),(10,'Single Sofa',NULL),(11,'wireless',NULL),(12,'wireless Pro',NULL),(13,'Iphone 15',2),(14,'Iphone 15 Pro',13),(15,'Iphone 11 Pro',6);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order1`
--

DROP TABLE IF EXISTS `order1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order1` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_date` date DEFAULT NULL,
  `payment_id` int DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `voucher_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_payment1_idx` (`payment_id`),
  KEY `fk_order1_voucher1_idx` (`voucher_id`),
  CONSTRAINT `fk_order1_voucher1` FOREIGN KEY (`voucher_id`) REFERENCES `voucher` (`id`),
  CONSTRAINT `fk_order_payment1` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order1`
--

LOCK TABLES `order1` WRITE;
/*!40000 ALTER TABLE `order1` DISABLE KEYS */;
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
  `product_store_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_orderdetail_order1_idx` (`order_id`),
  KEY `fk_orderdetail_product_store1_idx` (`product_store_id`),
  CONSTRAINT `fk_orderdetail_order1` FOREIGN KEY (`order_id`) REFERENCES `order1` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_orderdetail_product_store1` FOREIGN KEY (`product_store_id`) REFERENCES `product_store` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetail`
--

LOCK TABLES `orderdetail` WRITE;
/*!40000 ALTER TABLE `orderdetail` DISABLE KEYS */;
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
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
  `active` tinyint(1) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_category1_idx` (`category_id`),
  CONSTRAINT `fk_product_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (44,'Modern Arm Sofa',679.00,1,4),(45,'Baltsar Chair',679.00,1,8),(46,'Baltsar Chair',679.00,1,8),(47,'Helmar Chair',679.00,1,8),(48,'iphone 11',679.00,1,8),(49,'iphone 11',679.00,1,6),(50,'iphone 15',679.00,1,13),(51,'iphone 15',679.00,1,14),(52,'Fllufy Sheep Sofa',679.00,1,9),(53,'Fllufy Sheep Sofa',356.00,1,10),(54,'Beat Studio Wireless',356.00,1,11),(55,'Beat Studio Wireless',356.00,1,11),(56,'Beat Studio Wireless',356.00,1,11),(57,'Beat Studio Wireless',356.00,1,12),(58,'Beat Studio Wireless',356.00,1,12);
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
  PRIMARY KEY (`id`),
  KEY `fk_product_image_product1_idx` (`product_id`),
  CONSTRAINT `fk_product_image_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_image`
--

LOCK TABLES `product_image` WRITE;
/*!40000 ALTER TABLE `product_image` DISABLE KEYS */;
INSERT INTO `product_image` VALUES (39,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696518131/jehacnjpibuwekr9p4ii.png',44),(40,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696518778/m7dkvmwcvnsrjanledfl.png',45),(41,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696518860/rm1pjhaodry8ru38f0sy.png',46),(42,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696518880/ip4pfhpo9mixuzxkun9m.png',47),(43,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696518902/fbyhkd72ntero9uhwfrh.jpg',48),(44,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696518906/fuetacqblsuf0hp1nb5h.jpg',48),(45,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696518927/t3pigsksfao2lekl8eai.jpg',49),(46,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696518930/plimmc56oxsqzhgi2pkp.jpg',49),(47,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696518951/sqykgo0r20fcgskafetq.jpg',50),(48,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696518953/tvawztmpbfayumpx0iqn.jpg',50),(49,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696518969/st23rrgdzcqg6fgsefwt.jpg',51),(50,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696518971/mfv7zkuffxutjzfx5fhs.jpg',51),(51,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696519015/zhhwu82iuqfmxh0vawb4.png',52),(52,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696519018/eruzjg2kyrycqfe8dmoc.png',52),(53,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696519088/yy5yzdwxzyd8c7ax9b1s.png',53),(54,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696519104/dflzh0wsebxvepjv4pnl.png',54),(55,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696519106/bqf9xctkjxfvzt0ntmgk.png',54),(56,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696519129/up5ux31q7pr07dmmdnpt.png',55),(57,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696519131/tjozzgtes60p3bxhdgtv.png',55),(58,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696519160/psblowjk8dlomnmcopl9.png',56),(59,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696519162/mjk2afpnbxyfelyulncw.png',56),(60,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696519167/awpvld7h7gydcjgicyrq.png',57),(61,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696519169/jttw6wpfvlzrxsrprrr9.png',57),(62,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696519175/b2c9cdtx4b7zbccexvam.png',58),(63,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1696519178/tnmogdyvoevnmgwwhwbu.png',58);
/*!40000 ALTER TABLE `product_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_store`
--

DROP TABLE IF EXISTS `product_store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_store` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `store_id` int NOT NULL,
  `count` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_has_store_store1_idx` (`store_id`),
  KEY `fk_product_has_store_product1_idx` (`product_id`),
  CONSTRAINT `fk_product_has_store_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_product_has_store_store1` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_store`
--

LOCK TABLES `product_store` WRITE;
/*!40000 ALTER TABLE `product_store` DISABLE KEYS */;
INSERT INTO `product_store` VALUES (1,44,1,111),(2,45,1,100),(3,46,1,100),(4,47,1,100),(5,48,1,100),(6,49,1,NULL),(7,50,1,100),(8,51,1,100),(9,52,1,100),(10,53,1,100),(11,54,1,100),(12,55,1,111),(13,56,1,111),(14,57,1,111),(15,58,1,111);
/*!40000 ALTER TABLE `product_store` ENABLE KEYS */;
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
  `evaluate` int DEFAULT NULL,
  `user_id` int NOT NULL,
  `product_id` int NOT NULL,
  `review_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reriew_user1_idx` (`user_id`),
  KEY `fk_reriew_product1_idx` (`product_id`),
  KEY `fk_review_review1_idx` (`review_id`),
  CONSTRAINT `fk_reriew_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_reriew_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_review_review1` FOREIGN KEY (`review_id`) REFERENCES `review` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
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
  `role_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'USER'),(3,'SELLER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store` (
  `id` int NOT NULL AUTO_INCREMENT,
  `store_name` varchar(45) DEFAULT NULL,
  `adress` varchar(45) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_store_user1_idx` (`user_id`),
  CONSTRAINT `fk_store_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES (1,'Iphone Store','145 Nguyn Oanh',1,27);
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
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
  `active` tinyint(1) DEFAULT NULL,
  `Phone` varchar(10) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_role_idx` (`role_id`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (25,'user','$2a$10$VwdOFxmmizbEr1i7eJ2k9.OwA85OOg.uB/h7X7UHRaJ4rdNp.iG26','https://res.cloudinary.com/ddznsqfbo/image/upload/v1696515598/cgttzknzwjj9riejx173.png','wahodo1861@htoal.com',1,'0372745193',2),(26,'admin','$2a$10$jzElKY2EQuQHE7/rxVyGoesNQaRI9rQyH5GiCj0PKOh.mrNFb3IfG','https://res.cloudinary.com/ddznsqfbo/image/upload/v1696515707/oazgkehdppgqduiq2wmt.png','wahodo1861@htoal.com',1,'0372745193',2),(27,'seller','$2a$10$RBcH0WKf.ErWzCrgEZ.P7uIc6UosWtGj20Brmmlgyuc2cZMY/BQoC','https://res.cloudinary.com/ddznsqfbo/image/upload/v1696515791/kwnuvsg3rvq0qnk3vbgz.png','wahodo1861@htoal.com',1,'0372745193',3);
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher`
--

LOCK TABLES `voucher` WRITE;
/*!40000 ALTER TABLE `voucher` DISABLE KEYS */;
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

-- Dump completed on 2023-10-23 22:51:21
