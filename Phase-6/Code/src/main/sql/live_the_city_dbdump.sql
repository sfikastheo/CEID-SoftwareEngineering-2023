-- MariaDB dump 10.19  Distrib 10.10.2-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: LIVE_THE_CITY_SEPR2023
-- ------------------------------------------------------
-- Server version	10.10.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dates4tour`
--

DROP TABLE IF EXISTS `dates4tour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dates4tour` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tourid` int(11) NOT NULL,
  `datetime4tour` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_dates4tour_tour` (`tourid`),
  CONSTRAINT `fk_dates4tour_tour` FOREIGN KEY (`tourid`) REFERENCES `tour` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dates4tour`
--

LOCK TABLES `dates4tour` WRITE;
/*!40000 ALTER TABLE `dates4tour` DISABLE KEYS */;
INSERT INTO `dates4tour` VALUES
(1,2,'2023-06-08 10:00-10:10'),
(2,2,'2023-06-08 11:00-11:10'),
(3,2,'2023-06-08 12:00-12:10'),
(4,3,'2023-06-08 10:00-10:10'),
(5,3,'2023-06-08 11:00-11:10'),
(6,3,'2023-06-08 12:00-12:10'),
(7,5,'2023-06-08 10:00-10:10'),
(8,6,'2023-06-15 10:00-10:10'),
(9,6,'2023-06-15 11:00-11:10'),
(10,6,'2023-06-15 12:00-12:10'),
(11,7,'2023-06-16 10:00-10:10'),
(12,8,'2023-06-15 10:00-10:10'),
(13,9,'2023-06-13 10:00-10:10'),
(14,9,'2023-06-13 11:00-11:10'),
(15,9,'2023-06-13 12:00-12:10'),
(16,9,'2023-06-14 10:00-10:10');
/*!40000 ALTER TABLE `dates4tour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `planner` int(11) NOT NULL,
  `descr` varchar(400) DEFAULT NULL,
  `event_date` datetime NOT NULL,
  `event_location` varchar(200) NOT NULL,
  `date_uploaded` datetime DEFAULT current_timestamp(),
  `rating` float(1,1) DEFAULT 0.0,
  `attends` smallint(6) DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `fk_event_host` (`planner`),
  CONSTRAINT `fk_event_guide` FOREIGN KEY (`planner`) REFERENCES `tourguide` (`tgid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_event_host` FOREIGN KEY (`planner`) REFERENCES `host` (`hid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `host`
--

DROP TABLE IF EXISTS `host`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `host` (
  `hid` int(11) NOT NULL,
  `iban` varchar(27) NOT NULL,
  `hname` varchar(100) NOT NULL,
  `haddress` varchar(100) NOT NULL,
  PRIMARY KEY (`hid`),
  CONSTRAINT `fk_host_user` FOREIGN KEY (`hid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `host`
--

LOCK TABLES `host` WRITE;
/*!40000 ALTER TABLE `host` DISABLE KEYS */;
INSERT INTO `host` VALUES
(2,'012345678901234567890123456','Trinity Museum Services','some adress 551');
/*!40000 ALTER TABLE `host` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobapplication`
--

DROP TABLE IF EXISTS `jobapplication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobapplication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `responding_to` int(11) DEFAULT NULL,
  `applicant` int(11) NOT NULL,
  `apply_date` datetime DEFAULT current_timestamp(),
  `status` enum('Sent','Pending','Accepted','Denied') DEFAULT NULL,
  `files_path` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_application_offer` (`responding_to`),
  KEY `fk_JApp_SimUser` (`applicant`),
  CONSTRAINT `fk_JApp_SimUser` FOREIGN KEY (`applicant`) REFERENCES `simpleuser` (`suid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_JApp_TourGuide` FOREIGN KEY (`applicant`) REFERENCES `tourguide` (`tgid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_application_offer` FOREIGN KEY (`responding_to`) REFERENCES `joboffer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobapplication`
--

LOCK TABLES `jobapplication` WRITE;
/*!40000 ALTER TABLE `jobapplication` DISABLE KEYS */;
/*!40000 ALTER TABLE `jobapplication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `joboffer`
--

DROP TABLE IF EXISTS `joboffer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `joboffer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employer` int(11) NOT NULL,
  `date_opened` datetime DEFAULT current_timestamp(),
  `expires` datetime DEFAULT NULL,
  `title` varchar(40) NOT NULL,
  `descr` varchar(500) DEFAULT NULL,
  `num_employees` tinyint(4) DEFAULT 1,
  `salary` float(6,2) DEFAULT NULL,
  `files_path` varchar(250) NOT NULL,
  `public` tinyint(1) DEFAULT 0,
  `type` enum('Full-Time','Part-Time') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_joffer_host` (`employer`),
  CONSTRAINT `fk_joffer_host` FOREIGN KEY (`employer`) REFERENCES `host` (`hid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `joboffer`
--

LOCK TABLES `joboffer` WRITE;
/*!40000 ALTER TABLE `joboffer` DISABLE KEYS */;
INSERT INTO `joboffer` VALUES
(1,2,'2023-06-07 23:12:42','2023-08-22 00:00:00','test 1','1st effort',3,650.00,'./src/main/saved_data/Host/Triada/JOffers/test 1/',0,'Part-Time'),
(2,2,'2023-06-07 23:14:00','2023-08-22 00:00:00','test 2','2nd try',3,1500.00,'./src/main/saved_data/Host/Triada/JOffers/test 2/',0,'Full-Time'),
(3,2,'2023-06-07 23:15:13','2023-08-31 00:00:00','test 3','private',5,500.00,'./src/main/saved_data/Host/Triada/JOffers/test 3/',0,'Full-Time'),
(4,2,'2023-06-07 23:16:38','2023-06-20 00:00:00','test 4','private ',1,600.00,'./src/main/saved_data/Host/Triada/JOffers/test 4/',0,'Part-Time'),
(5,2,'2023-06-07 23:18:37','2023-06-08 00:00:00','test 5','private ',1,642.00,'./src/main/saved_data/Host/Triada/JOffers/test 5/',0,'Full-Time'),
(6,2,'2023-06-07 23:20:42','2023-07-13 00:00:00','test 6','private',1,900.00,'./src/main/saved_data/Host/Triada/JOffers/test 6/',0,'Part-Time'),
(7,2,'2023-06-07 23:24:24','2023-07-05 00:00:00','test 7 ','private',2,500.00,'./src/main/saved_data/Host/Triada/JOffers/test 7 /',0,'Full-Time'),
(8,2,'2023-06-07 23:29:04','2023-07-04 00:00:00','test 8 ','private',2,100.00,'./src/main/saved_data/Host/Triada/JOffers/test 8 /',0,'Part-Time'),
(9,2,'2023-06-08 00:12:14','2023-06-23 00:00:00','test 9?','public',2,900.00,'./src/main/saved_data/Host/Triada/JOffers/test 9?/',0,'Part-Time'),
(10,2,'2023-06-08 00:13:32','2023-06-28 00:00:00','test 10 ','PUBLIC?',6,800.00,'./src/main/saved_data/Host/Triada/JOffers/test 10 /',0,'Part-Time'),
(11,2,'2023-06-08 00:14:51','2023-06-28 00:00:00','test 11','public joffer',2,480.00,'./src/main/saved_data/Host/Triada/JOffers/test 11/',1,'Part-Time'),
(12,2,'2023-06-09 19:45:59',NULL,'sample-title2','sample-description2',1,99.99,'sample-filepath2',0,NULL),
(13,2,'2023-06-09 19:46:04',NULL,'sample-title2','sample-description2',1,99.99,'sample-filepath2',0,NULL);
/*!40000 ALTER TABLE `joboffer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jotags`
--

DROP TABLE IF EXISTS `jotags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jotags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `joid` int(11) NOT NULL,
  `tid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_JOtags_JobOffer` (`joid`),
  KEY `fk_JOtags_Tags` (`tid`),
  CONSTRAINT `fk_JOtags_JobOffer` FOREIGN KEY (`joid`) REFERENCES `joboffer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_JOtags_Tags` FOREIGN KEY (`tid`) REFERENCES `tags` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jotags`
--

LOCK TABLES `jotags` WRITE;
/*!40000 ALTER TABLE `jotags` DISABLE KEYS */;
/*!40000 ALTER TABLE `jotags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participation`
--

DROP TABLE IF EXISTS `participation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `partype` enum('Tour','Event') DEFAULT NULL,
  `participant` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_participation_simpleuser` (`participant`),
  CONSTRAINT `fk_participation_simpleuser` FOREIGN KEY (`participant`) REFERENCES `simpleuser` (`suid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participation`
--

LOCK TABLES `participation` WRITE;
/*!40000 ALTER TABLE `participation` DISABLE KEYS */;
/*!40000 ALTER TABLE `participation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `suid` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `amount` float(2,2) NOT NULL,
  `payment_date` datetime DEFAULT current_timestamp(),
  `receipt_path` varchar(250) NOT NULL,
  `certificate_no` int(11) DEFAULT NULL,
  `certificate_path` varchar(250) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `certificate_no` (`certificate_no`),
  KEY `fk_payment_simuser` (`suid`),
  KEY `fk_payment_tour` (`product_id`),
  CONSTRAINT `fk_payment_simuser` FOREIGN KEY (`suid`) REFERENCES `simpleuser` (`suid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_payment_tour` FOREIGN KEY (`product_id`) REFERENCES `tour` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz`
--

DROP TABLE IF EXISTS `quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quiz` (
  `qid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `on_tour` int(11) NOT NULL,
  `descr` varchar(200) DEFAULT NULL,
  `succes_point` tinyint(2) DEFAULT NULL,
  `qpath` varchar(250) DEFAULT NULL,
  `date_uploaded` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`qid`),
  KEY `fk_quiz_tour` (`on_tour`),
  CONSTRAINT `fk_quiz_tour` FOREIGN KEY (`on_tour`) REFERENCES `tour` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz`
--

LOCK TABLES `quiz` WRITE;
/*!40000 ALTER TABLE `quiz` DISABLE KEYS */;
/*!40000 ALTER TABLE `quiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quizquestion`
--

DROP TABLE IF EXISTS `quizquestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quizquestion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qtext` varchar(500) DEFAULT NULL,
  `answer` varchar(50) DEFAULT NULL,
  `on_quiz` int(11) NOT NULL,
  `qpath` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_question_quiz` (`on_quiz`),
  CONSTRAINT `fk_question_quiz` FOREIGN KEY (`on_quiz`) REFERENCES `quiz` (`qid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quizquestion`
--

LOCK TABLES `quizquestion` WRITE;
/*!40000 ALTER TABLE `quizquestion` DISABLE KEYS */;
/*!40000 ALTER TABLE `quizquestion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `simpleuser`
--

DROP TABLE IF EXISTS `simpleuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `simpleuser` (
  `suid` int(11) NOT NULL,
  `score` int(11) DEFAULT 0,
  PRIMARY KEY (`suid`),
  CONSTRAINT `fk_simpleuser_user` FOREIGN KEY (`suid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `simpleuser`
--

LOCK TABLES `simpleuser` WRITE;
/*!40000 ALTER TABLE `simpleuser` DISABLE KEYS */;
INSERT INTO `simpleuser` VALUES
(3,0);
/*!40000 ALTER TABLE `simpleuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tagname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
INSERT INTO `tags` VALUES
(1,'Live Music'),
(2,'Wine Tasting'),
(3,'Ancient Times'),
(4,'Foreign Language');
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tetags`
--

DROP TABLE IF EXISTS `tetags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tetags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tagid` int(11) NOT NULL,
  `on_tour` int(11) DEFAULT NULL,
  `on_event` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_TEtags_tag` (`tagid`),
  KEY `fk_TEtags_tour` (`on_tour`),
  KEY `fk_TEtags_event` (`on_event`),
  CONSTRAINT `fk_TEtags_event` FOREIGN KEY (`on_event`) REFERENCES `event` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_TEtags_tag` FOREIGN KEY (`tagid`) REFERENCES `tags` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_TEtags_tour` FOREIGN KEY (`on_tour`) REFERENCES `tour` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tetags`
--

LOCK TABLES `tetags` WRITE;
/*!40000 ALTER TABLE `tetags` DISABLE KEYS */;
INSERT INTO `tetags` VALUES
(1,1,8,NULL),
(2,2,8,NULL),
(3,1,9,NULL),
(4,3,9,NULL);
/*!40000 ALTER TABLE `tetags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour`
--

DROP TABLE IF EXISTS `tour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tour` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `virtual` tinyint(1) DEFAULT 0,
  `descr` varchar(400) DEFAULT NULL,
  `duration` tinyint(4) DEFAULT NULL,
  `startlocation` varchar(40) NOT NULL,
  `groups_per_date` tinyint(4) DEFAULT 3,
  `spots_per_group` tinyint(4) DEFAULT 12,
  `public` tinyint(1) DEFAULT 0,
  `datepublished` datetime DEFAULT NULL,
  `price` float(4,2) NOT NULL,
  `rating` float(1,1) DEFAULT 0.0,
  `times_bought` smallint(5) unsigned DEFAULT 0,
  `offered_by` int(11) NOT NULL,
  `files_path` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `title` (`title`),
  KEY `fk_tour_guide` (`offered_by`),
  CONSTRAINT `fk_tour_guide` FOREIGN KEY (`offered_by`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour`
--

LOCK TABLES `tour` WRITE;
/*!40000 ALTER TABLE `tour` DISABLE KEYS */;
INSERT INTO `tour` VALUES
(2,'test 2',0,'2nd test tour',10,'myhome',3,12,0,'2023-06-07 19:01:01',0.20,0.0,0,1,'./src/main/saved_data/TourGuide/Maria/Tours/test 2/'),
(3,'test 3',0,'hoping for success',10,'myhome',3,12,1,'2023-06-07 19:02:50',0.20,0.0,0,1,'./src/main/saved_data/TourGuide/Maria/Tours/test 3/'),
(4,'test 4',0,'4th test tour',10,'myhome',3,12,0,'2023-06-07 19:04:05',0.20,0.0,0,1,'./src/main/saved_data/TourGuide/Maria/Tours/test 4/'),
(5,'test 5',0,'',10,'home',3,12,0,'2023-06-07 19:09:01',0.20,0.0,0,1,'./src/main/saved_data/TourGuide/Maria/Tours/test 5/'),
(6,'test 6',0,'success??',10,'home',3,12,0,'2023-06-07 19:10:31',0.20,0.0,0,1,'./src/main/saved_data/TourGuide/Maria/Tours/test 6/'),
(7,'tesy 7',0,'success?',10,'home',3,12,0,'2023-06-07 19:12:48',0.20,0.0,0,1,'./src/main/saved_data/TourGuide/Maria/Tours/tesy 7/'),
(8,'test 8',0,'iu bet this is success',10,'home',3,12,1,'2023-06-07 19:18:12',5.00,0.0,0,1,'./src/main/saved_data/TourGuide/Maria/Tours/test 8/'),
(9,'test 9',0,'let\'s see if this works',10,'home',3,12,1,'2023-06-07 21:07:48',0.20,0.0,0,1,'./src/main/saved_data/TourGuide/Maria/Tours/test 9/');
/*!40000 ALTER TABLE `tour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tourguide`
--

DROP TABLE IF EXISTS `tourguide`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tourguide` (
  `tgid` int(11) NOT NULL,
  `iban` varchar(27) NOT NULL,
  PRIMARY KEY (`tgid`),
  CONSTRAINT `fk_tourguide_user` FOREIGN KEY (`tgid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tourguide`
--

LOCK TABLES `tourguide` WRITE;
/*!40000 ALTER TABLE `tourguide` DISABLE KEYS */;
INSERT INTO `tourguide` VALUES
(1,'123456789012345678901234567');
/*!40000 ALTER TABLE `tourguide` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `upass` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES
(1,'Maria','12345','up1072494@upnet.gr'),
(2,'Triada','abcd','up1072476@upnet.gr'),
(3,'sample-username3','sample-password3','sample-email3');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `virtualtour`
--

DROP TABLE IF EXISTS `virtualtour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `virtualtour` (
  `vtid` int(11) NOT NULL,
  `vtpath` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`vtid`),
  CONSTRAINT `fk_virtualtour_tour` FOREIGN KEY (`vtid`) REFERENCES `tour` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `virtualtour`
--

LOCK TABLES `virtualtour` WRITE;
/*!40000 ALTER TABLE `virtualtour` DISABLE KEYS */;
/*!40000 ALTER TABLE `virtualtour` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-09 20:17:18
