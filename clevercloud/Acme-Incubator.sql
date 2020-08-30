-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: starter-project
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `accounting_record`
--

DROP TABLE IF EXISTS `accounting_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounting_record` (
  `id` int NOT NULL,
  `version` int NOT NULL,
  `body` varchar(1024) DEFAULT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `bookkeeper_id` int NOT NULL,
  `investment_round_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK41jm4vk7runvmg5tderffrele` (`bookkeeper_id`),
  KEY `FKk1pmfnppwk0kav7xloy8u71uq` (`investment_round_id`),
  CONSTRAINT `FK41jm4vk7runvmg5tderffrele` FOREIGN KEY (`bookkeeper_id`) REFERENCES `bookkeeper` (`id`),
  CONSTRAINT `FKk1pmfnppwk0kav7xloy8u71uq` FOREIGN KEY (`investment_round_id`) REFERENCES `investment_round` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounting_record`
--

LOCK TABLES `accounting_record` WRITE;
/*!40000 ALTER TABLE `accounting_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `accounting_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator` (
  `id` int NOT NULL,
  `version` int NOT NULL,
  `user_account_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2a5vcjo3stlfcwadosjfq49l1` (`user_account_id`),
  CONSTRAINT `FK_2a5vcjo3stlfcwadosjfq49l1` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (4,0,3);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `anonymous`
--

DROP TABLE IF EXISTS `anonymous`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anonymous` (
  `id` int NOT NULL,
  `version` int NOT NULL,
  `user_account_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6lnbc6fo3om54vugoh8icg78m` (`user_account_id`),
  CONSTRAINT `FK_6lnbc6fo3om54vugoh8icg78m` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anonymous`
--

LOCK TABLES `anonymous` WRITE;
/*!40000 ALTER TABLE `anonymous` DISABLE KEYS */;
INSERT INTO `anonymous` VALUES (2,0,1);
/*!40000 ALTER TABLE `anonymous` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application` (
  `id` int NOT NULL,
  `version` int NOT NULL,
  `justification` varchar(1024) DEFAULT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `offer_amount` double DEFAULT NULL,
  `offer_currency` varchar(255) DEFAULT NULL,
  `statement` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `investment_round_id` int NOT NULL,
  `investor_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk5ibe41quxsif8im882xv4afo` (`investment_round_id`),
  KEY `FKl4fp0cd8c008ma79n6w58xhk9` (`investor_id`),
  CONSTRAINT `FKk5ibe41quxsif8im882xv4afo` FOREIGN KEY (`investment_round_id`) REFERENCES `investment_round` (`id`),
  CONSTRAINT `FKl4fp0cd8c008ma79n6w58xhk9` FOREIGN KEY (`investor_id`) REFERENCES `investor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authenticated`
--

DROP TABLE IF EXISTS `authenticated`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authenticated` (
  `id` int NOT NULL,
  `version` int NOT NULL,
  `user_account_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h52w0f3wjoi68b63wv9vwon57` (`user_account_id`),
  CONSTRAINT `FK_h52w0f3wjoi68b63wv9vwon57` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authenticated`
--

LOCK TABLES `authenticated` WRITE;
/*!40000 ALTER TABLE `authenticated` DISABLE KEYS */;
INSERT INTO `authenticated` VALUES (5,0,3);
/*!40000 ALTER TABLE `authenticated` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookkeeper`
--

DROP TABLE IF EXISTS `bookkeeper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookkeeper` (
  `id` int NOT NULL,
  `version` int NOT NULL,
  `user_account_id` int DEFAULT NULL,
  `firm_name` varchar(255) DEFAULT NULL,
  `responsibility_statement` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_krvjp9eaqyapewl2igugbo9o8` (`user_account_id`),
  CONSTRAINT `FK_krvjp9eaqyapewl2igugbo9o8` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookkeeper`
--

LOCK TABLES `bookkeeper` WRITE;
/*!40000 ALTER TABLE `bookkeeper` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookkeeper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `challenge`
--

DROP TABLE IF EXISTS `challenge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `challenge` (
  `id` int NOT NULL,
  `version` int NOT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `goal_average` varchar(255) DEFAULT NULL,
  `goal_expert` varchar(255) DEFAULT NULL,
  `goal_rookie` varchar(255) DEFAULT NULL,
  `reward_average_amount` double DEFAULT NULL,
  `reward_average_currency` varchar(255) DEFAULT NULL,
  `reward_expert_amount` double DEFAULT NULL,
  `reward_expert_currency` varchar(255) DEFAULT NULL,
  `reward_rookie_amount` double DEFAULT NULL,
  `reward_rookie_currency` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `challenge`
--

LOCK TABLES `challenge` WRITE;
/*!40000 ALTER TABLE `challenge` DISABLE KEYS */;
/*!40000 ALTER TABLE `challenge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumer`
--

DROP TABLE IF EXISTS `consumer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consumer` (
  `id` int NOT NULL,
  `version` int NOT NULL,
  `user_account_id` int DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `sector` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6cyha9f1wpj0dpbxrrjddrqed` (`user_account_id`),
  CONSTRAINT `FK_6cyha9f1wpj0dpbxrrjddrqed` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumer`
--

LOCK TABLES `consumer` WRITE;
/*!40000 ALTER TABLE `consumer` DISABLE KEYS */;
/*!40000 ALTER TABLE `consumer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customisation`
--

DROP TABLE IF EXISTS `customisation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customisation` (
  `id` int NOT NULL,
  `version` int NOT NULL,
  `activity_sectors` varchar(1024) DEFAULT NULL,
  `spamwords` varchar(1024) DEFAULT NULL,
  `threshold` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customisation`
--

LOCK TABLES `customisation` WRITE;
/*!40000 ALTER TABLE `customisation` DISABLE KEYS */;
/*!40000 ALTER TABLE `customisation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discussion_forum`
--

DROP TABLE IF EXISTS `discussion_forum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discussion_forum` (
  `id` int NOT NULL,
  `version` int NOT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `users` varchar(1024) DEFAULT NULL,
  `author_id` int NOT NULL,
  `investment_round_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgkgyrl9xew6uucn4vldachbih` (`author_id`),
  KEY `FKmcgrpw22g3baap51wq319v1bp` (`investment_round_id`),
  CONSTRAINT `FKgkgyrl9xew6uucn4vldachbih` FOREIGN KEY (`author_id`) REFERENCES `authenticated` (`id`),
  CONSTRAINT `FKmcgrpw22g3baap51wq319v1bp` FOREIGN KEY (`investment_round_id`) REFERENCES `investment_round` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discussion_forum`
--

LOCK TABLES `discussion_forum` WRITE;
/*!40000 ALTER TABLE `discussion_forum` DISABLE KEYS */;
/*!40000 ALTER TABLE `discussion_forum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrepreneur`
--

DROP TABLE IF EXISTS `entrepreneur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrepreneur` (
  `id` int NOT NULL,
  `version` int NOT NULL,
  `user_account_id` int DEFAULT NULL,
  `activity_sector` varchar(255) DEFAULT NULL,
  `qualification_record` varchar(255) DEFAULT NULL,
  `skills_record` varchar(255) DEFAULT NULL,
  `startup_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_r6tqltqvrlh1cyy8rsj5pev1q` (`user_account_id`),
  CONSTRAINT `FK_r6tqltqvrlh1cyy8rsj5pev1q` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrepreneur`
--

LOCK TABLES `entrepreneur` WRITE;
/*!40000 ALTER TABLE `entrepreneur` DISABLE KEYS */;
/*!40000 ALTER TABLE `entrepreneur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (6);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inquiry`
--

DROP TABLE IF EXISTS `inquiry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inquiry` (
  `id` int NOT NULL,
  `version` int NOT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `description` varchar(1024) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `max_amount` double DEFAULT NULL,
  `max_currency` varchar(255) DEFAULT NULL,
  `min_amount` double DEFAULT NULL,
  `min_currency` varchar(255) DEFAULT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inquiry`
--

LOCK TABLES `inquiry` WRITE;
/*!40000 ALTER TABLE `inquiry` DISABLE KEYS */;
/*!40000 ALTER TABLE `inquiry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `investment_round`
--

DROP TABLE IF EXISTS `investment_round`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `investment_round` (
  `id` int NOT NULL,
  `version` int NOT NULL,
  `amount_amount` double DEFAULT NULL,
  `amount_currency` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_final_mode` bit(1) DEFAULT NULL,
  `kind` int DEFAULT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `more_info` varchar(255) DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `entrepreneur_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkj1l8c2ftn9c65y061me6t37j` (`entrepreneur_id`),
  CONSTRAINT `FKkj1l8c2ftn9c65y061me6t37j` FOREIGN KEY (`entrepreneur_id`) REFERENCES `entrepreneur` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `investment_round`
--

LOCK TABLES `investment_round` WRITE;
/*!40000 ALTER TABLE `investment_round` DISABLE KEYS */;
/*!40000 ALTER TABLE `investment_round` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `investor`
--

DROP TABLE IF EXISTS `investor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `investor` (
  `id` int NOT NULL,
  `version` int NOT NULL,
  `user_account_id` int DEFAULT NULL,
  `activity_sector` varchar(255) DEFAULT NULL,
  `firm_name` varchar(255) DEFAULT NULL,
  `profile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_dcek5rr514s3rww0yy57vvnpq` (`user_account_id`),
  CONSTRAINT `FK_dcek5rr514s3rww0yy57vvnpq` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `investor`
--

LOCK TABLES `investor` WRITE;
/*!40000 ALTER TABLE `investor` DISABLE KEYS */;
/*!40000 ALTER TABLE `investor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` int NOT NULL,
  `version` int NOT NULL,
  `body` varchar(1024) DEFAULT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `author_id` int NOT NULL,
  `forum_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe1edpykjs39o98sfkjafa0dtn` (`author_id`),
  KEY `FK7ju7uxmh5mdbjgrfwgoem3eqd` (`forum_id`),
  CONSTRAINT `FK7ju7uxmh5mdbjgrfwgoem3eqd` FOREIGN KEY (`forum_id`) REFERENCES `discussion_forum` (`id`),
  CONSTRAINT `FKe1edpykjs39o98sfkjafa0dtn` FOREIGN KEY (`author_id`) REFERENCES `authenticated` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `molina_bulletin`
--

DROP TABLE IF EXISTS `molina_bulletin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `molina_bulletin` (
  `id` int NOT NULL,
  `version` int NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `molina_bulletin`
--

LOCK TABLES `molina_bulletin` WRITE;
/*!40000 ALTER TABLE `molina_bulletin` DISABLE KEYS */;
/*!40000 ALTER TABLE `molina_bulletin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `id` int NOT NULL,
  `version` int NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `header_picture` varchar(255) DEFAULT NULL,
  `link1` varchar(255) DEFAULT NULL,
  `link2` varchar(255) DEFAULT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `overture`
--

DROP TABLE IF EXISTS `overture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `overture` (
  `id` int NOT NULL,
  `version` int NOT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `description` varchar(1024) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `max_amount` double DEFAULT NULL,
  `max_currency` varchar(255) DEFAULT NULL,
  `min_amount` double DEFAULT NULL,
  `min_currency` varchar(255) DEFAULT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `overture`
--

LOCK TABLES `overture` WRITE;
/*!40000 ALTER TABLE `overture` DISABLE KEYS */;
/*!40000 ALTER TABLE `overture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provider`
--

DROP TABLE IF EXISTS `provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provider` (
  `id` int NOT NULL,
  `version` int NOT NULL,
  `user_account_id` int DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `sector` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_b1gwnjqm6ggy9yuiqm0o4rlmd` (`user_account_id`),
  CONSTRAINT `FK_b1gwnjqm6ggy9yuiqm0o4rlmd` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provider`
--

LOCK TABLES `provider` WRITE;
/*!40000 ALTER TABLE `provider` DISABLE KEYS */;
/*!40000 ALTER TABLE `provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storage`
--

DROP TABLE IF EXISTS `storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `storage` (
  `id` int NOT NULL,
  `version` int NOT NULL,
  `firm_name` varchar(255) DEFAULT NULL,
  `responsibility_statement` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `authenticated_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_efthk7gn1p8761xs9lnetdfh5` (`authenticated_id`),
  CONSTRAINT `FKbak20tfheetwsi0t2ammfwip0` FOREIGN KEY (`authenticated_id`) REFERENCES `authenticated` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storage`
--

LOCK TABLES `storage` WRITE;
/*!40000 ALTER TABLE `storage` DISABLE KEYS */;
/*!40000 ALTER TABLE `storage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technology_record`
--

DROP TABLE IF EXISTS `technology_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `technology_record` (
  `id` int NOT NULL,
  `version` int NOT NULL,
  `activity_sector` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `investor` varchar(255) DEFAULT NULL,
  `rate` int DEFAULT NULL,
  `source` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `technology_record`
--

LOCK TABLES `technology_record` WRITE;
/*!40000 ALTER TABLE `technology_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `technology_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tool_record`
--

DROP TABLE IF EXISTS `tool_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tool_record` (
  `id` int NOT NULL,
  `version` int NOT NULL,
  `activity_sector` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `investor` varchar(255) DEFAULT NULL,
  `rate` int DEFAULT NULL,
  `source` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tool_record`
--

LOCK TABLES `tool_record` WRITE;
/*!40000 ALTER TABLE `tool_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `tool_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_account` (
  `id` int NOT NULL,
  `version` int NOT NULL,
  `enabled` bit(1) NOT NULL,
  `identity_email` varchar(255) DEFAULT NULL,
  `identity_name` varchar(255) DEFAULT NULL,
  `identity_surname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (1,0,_binary '\0','john.doe@acme.com','John','Doe','$2a$05$a/Z4X0dx0aTSkajk6reJ6uN8OGe1Ms3jGM7STYTofG4TH7m.gvJpS','anonymous'),(3,0,_binary '','administrator@acme.com','Administrator','Acme.com','$2a$05$MZkztDAedeWhdYEwAb8an.rFJ9.tSgopzqI5h4PX8DT2xXHhVwdli','administrator');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_programme`
--

DROP TABLE IF EXISTS `work_programme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `work_programme` (
  `id` int NOT NULL,
  `version` int NOT NULL,
  `budget_amount` double DEFAULT NULL,
  `budget_currency` varchar(255) DEFAULT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `investment_round_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3nxyaik1cnvfdg02p9a8ibiko` (`investment_round_id`),
  CONSTRAINT `FK3nxyaik1cnvfdg02p9a8ibiko` FOREIGN KEY (`investment_round_id`) REFERENCES `investment_round` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_programme`
--

LOCK TABLES `work_programme` WRITE;
/*!40000 ALTER TABLE `work_programme` DISABLE KEYS */;
/*!40000 ALTER TABLE `work_programme` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-30 22:58:15
