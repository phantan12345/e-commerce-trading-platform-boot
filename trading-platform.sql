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
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (24,'SmartPhone',NULL),(25,'dien thoai',24),(28,'ipad',24),(29,'SmartPhone',NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order1`
--

LOCK TABLES `order1` WRITE;
/*!40000 ALTER TABLE `order1` DISABLE KEYS */;
INSERT INTO `order1` VALUES (37,'2023-09-23',NULL,NULL),(38,'2023-09-23',NULL,NULL),(39,'2023-09-23',NULL,NULL),(40,'2023-09-23',NULL,NULL),(41,'2023-09-23',NULL,NULL),(42,'2023-09-23',NULL,NULL),(43,'2023-09-23',NULL,NULL),(44,'2023-09-23',NULL,NULL),(45,'2023-09-23',NULL,NULL),(46,'2023-09-23',NULL,NULL),(47,'2023-10-23',NULL,NULL),(48,'2023-09-23',NULL,NULL),(49,'2023-09-23',NULL,NULL),(50,'2023-09-23',NULL,NULL),(51,'2023-09-23',NULL,NULL),(52,'2023-09-23',NULL,NULL),(53,'2023-09-23',NULL,NULL),(54,'2023-09-23',NULL,NULL);
/*!40000 ALTER TABLE `order1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `orderdetail`
--

LOCK TABLES `orderdetail` WRITE;
/*!40000 ALTER TABLE `orderdetail` DISABLE KEYS */;
INSERT INTO `orderdetail` VALUES (17,6,600,37,4),(18,6,600,38,4),(19,6,600,39,4),(20,6,600,40,4),(21,6,600,41,4),(22,6,600,42,4),(23,6,600,43,4),(24,6,600,44,4),(25,6,600,45,4),(26,6,600,46,4),(27,6,600,47,4),(28,6,600,47,4),(29,6,600,49,4),(30,6,600,50,4),(31,6,600,51,4),(32,6,600,52,4),(33,4,400,54,4);
/*!40000 ALTER TABLE `orderdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `payment_method`
--

LOCK TABLES `payment_method` WRITE;
/*!40000 ALTER TABLE `payment_method` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (37,'tan',100.00,0,24),(38,'1',100.00,0,24),(39,'Iphone 15',699.00,0,24);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product_image`
--

LOCK TABLES `product_image` WRITE;
/*!40000 ALTER TABLE `product_image` DISABLE KEYS */;
INSERT INTO `product_image` VALUES (26,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1695459212/oayvogztzhdeoadxsgno.jpg',4),(27,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1695459216/tmbpquey2sfwhxqa0vlb.png',4),(28,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1695459312/j8abytfgjdsvpb51uabl.jpg',5),(29,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1695459316/chbxzgl1mtnc9khqinox.png',5),(30,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1695556769/bprrervkq1efwoxeqxdq.jpg',6),(31,'https://res.cloudinary.com/ddznsqfbo/image/upload/v1695556775/pgn1q3s1bp1wgadptuyb.png',6);
/*!40000 ALTER TABLE `product_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product_store`
--

LOCK TABLES `product_store` WRITE;
/*!40000 ALTER TABLE `product_store` DISABLE KEYS */;
INSERT INTO `product_store` VALUES (4,37,14,1,90),(5,38,14,NULL,100),(6,39,14,NULL,66);
/*!40000 ALTER TABLE `product_store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `reriew`
--

LOCK TABLES `reriew` WRITE;
/*!40000 ALTER TABLE `reriew` DISABLE KEYS */;
/*!40000 ALTER TABLE `reriew` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'USER'),(3,'SALER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES (14,'tan','tan',0,21);
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (21,'user','$2a$10$2frcCO4WyI9aPHBJMlz57ufOtjeOswb3Nmb6/RBiG6eLY7zsIWSRu','https://res.cloudinary.com/ddznsqfbo/image/upload/v1695397065/hup4xkrnurvqs9bvpfk9.jpg','cotogax285@ipniel.com',1,3,'132');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_voucher`
--

LOCK TABLES `user_voucher` WRITE;
/*!40000 ALTER TABLE `user_voucher` DISABLE KEYS */;
INSERT INTO `user_voucher` VALUES (21,1);
/*!40000 ALTER TABLE `user_voucher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `voucher`
--

LOCK TABLES `voucher` WRITE;
/*!40000 ALTER TABLE `voucher` DISABLE KEYS */;
INSERT INTO `voucher` VALUES (1,0.1,'user');
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

-- Dump completed on 2023-09-24 20:22:15
