-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (i386)
--
-- Host: 91.121.193.238    Database: APPDB
-- ------------------------------------------------------
-- Server version	5.7.6-m16

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
-- Table structure for table `Calendars`
--

USE `APPDB`;

DROP TABLE IF EXISTS `Calendars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Calendars` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_group` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `id_group_UNIQUE` (`id_group`),
  CONSTRAINT `key_calendar_group` FOREIGN KEY (`id_group`) REFERENCES `Groups` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Calendars`
--

LOCK TABLES `Calendars` WRITE;
/*!40000 ALTER TABLE `Calendars` DISABLE KEYS */;
INSERT INTO `Calendars` VALUES (51,1437),(52,1438),(53,1439),(54,1440),(55,1441),(56,1442),(57,1443),(58,1444),(59,1445),(60,1446),(61,1447),(62,1448),(63,1449),(64,1450),(65,1451),(66,1452);
/*!40000 ALTER TABLE `Calendars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Dates`
--

DROP TABLE IF EXISTS `Dates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Dates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_calendar` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `key_date_calendar_idx` (`id_calendar`),
  CONSTRAINT `key_date_calendar` FOREIGN KEY (`id_calendar`) REFERENCES `Calendars` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1435 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Dates`
--

LOCK TABLES `Dates` WRITE;
/*!40000 ALTER TABLE `Dates` DISABLE KEYS */;
/*!40000 ALTER TABLE `Dates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Deadlines`
--

DROP TABLE IF EXISTS `Deadlines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Deadlines` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` mediumtext COLLATE utf8_unicode_ci,
  `date_creation` date DEFAULT NULL,
  `date_limit` datetime DEFAULT NULL,
  `id_createur` int(11) DEFAULT NULL,
  `Status` tinyint(1) DEFAULT NULL,
  `id_group` int(11) DEFAULT NULL,
  `cross` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `key_deadline_group_idx` (`id_group`),
  CONSTRAINT `key_deadline_group` FOREIGN KEY (`id_group`) REFERENCES `Groups` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=236 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Deadlines`
--

LOCK TABLES `Deadlines` WRITE;
/*!40000 ALTER TABLE `Deadlines` DISABLE KEYS */;
/*!40000 ALTER TABLE `Deadlines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Delivery`
--

DROP TABLE IF EXISTS `Delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Delivery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_owner_group` int(11) DEFAULT NULL,
  `id_deadline` int(11) DEFAULT NULL,
  `path` text COLLATE utf8_unicode_ci,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `key_delivery_deadline_idx` (`id_deadline`),
  CONSTRAINT `key_delivery_deadline` FOREIGN KEY (`id_deadline`) REFERENCES `Deadlines` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Delivery`
--

LOCK TABLES `Delivery` WRITE;
/*!40000 ALTER TABLE `Delivery` DISABLE KEYS */;
/*!40000 ALTER TABLE `Delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Groups`
--

DROP TABLE IF EXISTS `Groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_tutor` int(11) DEFAULT NULL,
  `class` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `idx_tutors_groups` (`id_tutor`),
  CONSTRAINT `key_tutors_groups` FOREIGN KEY (`id_tutor`) REFERENCES `Users` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1453 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Groups`
--

LOCK TABLES `Groups` WRITE;
/*!40000 ALTER TABLE `Groups` DISABLE KEYS */;
INSERT INTO `Groups` VALUES (1437,'G5A',NULL,'G5'),(1438,'G5B',NULL,'G5'),(1439,'G5C',NULL,'G5'),(1440,'G5D',NULL,'G5'),(1441,'G6A',NULL,'G6'),(1442,'G6B',NULL,'G6'),(1443,'G6C',NULL,'G6'),(1444,'G6D',NULL,'G6'),(1445,'G7A',NULL,'G7'),(1446,'G7B',NULL,'G7'),(1447,'G7C',NULL,'G7'),(1448,'G7D',NULL,'G7'),(1449,'G8A',NULL,'G8'),(1450,'G8B',NULL,'G8'),(1451,'G8C',NULL,'G8'),(1452,'G8D',NULL,'G8');
/*!40000 ALTER TABLE `Groups` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`lmdadm`@`%`*/ /*!50003 TRIGGER `APPDB`.`Groups_AFTER_INSERT` AFTER INSERT ON `Groups` FOR EACH ROW
BEGIN
	INSERT INTO `Calendars` (`id_group`) VALUE (NEW.`id`);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `Marks`
--

DROP TABLE IF EXISTS `Marks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Marks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_student` int(11) DEFAULT NULL,
  `id_value` int(11) DEFAULT NULL,
  `id_sub_skill` int(11) DEFAULT NULL,
  `cross` tinyint(1) DEFAULT '0',
  `date` datetime DEFAULT NULL,
  `group_mark` tinyint(1) DEFAULT '1',
  `id_tutor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_mark_owner` (`id_student`,`id_sub_skill`,`id_tutor`),
  KEY `idx_mark_sub_skills` (`id_sub_skill`),
  KEY `idx_marks_tutors_idx` (`id_tutor`),
  KEY `idx_marks_values_idx` (`id_value`),
  CONSTRAINT `key_marks_skills` FOREIGN KEY (`id_sub_skill`) REFERENCES `Sub_skills` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `key_marks_students` FOREIGN KEY (`id_student`) REFERENCES `Users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `key_marks_tutors` FOREIGN KEY (`id_tutor`) REFERENCES `Users` (`id`) ON UPDATE NO ACTION,
  CONSTRAINT `key_marks_values` FOREIGN KEY (`id_value`) REFERENCES `Values` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3709 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Marks`
--

LOCK TABLES `Marks` WRITE;
/*!40000 ALTER TABLE `Marks` DISABLE KEYS */;
/*!40000 ALTER TABLE `Marks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Missing`
--

DROP TABLE IF EXISTS `Missing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Missing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_student` int(11) DEFAULT NULL,
  `id_tutor` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `late` tinyint(1) DEFAULT '0',
  `supporting` mediumtext COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`),
  KEY `idx_missings_users` (`id_student`),
  KEY `idx_missing_tutor` (`id_tutor`),
  CONSTRAINT `key_missing_tutor` FOREIGN KEY (`id_tutor`) REFERENCES `Users` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `key_missings_student` FOREIGN KEY (`id_student`) REFERENCES `Users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3784 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Missing`
--

LOCK TABLES `Missing` WRITE;
/*!40000 ALTER TABLE `Missing` DISABLE KEYS */;
/*!40000 ALTER TABLE `Missing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Positions`
--

DROP TABLE IF EXISTS `Positions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Positions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Positions`
--

LOCK TABLES `Positions` WRITE;
/*!40000 ALTER TABLE `Positions` DISABLE KEYS */;
INSERT INTO `Positions` VALUES (1,'administration'),(2,'respo'),(3,'tutor'),(4,'student');
/*!40000 ALTER TABLE `Positions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Skills`
--

DROP TABLE IF EXISTS `Skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Skills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_respo` int(11) DEFAULT NULL,
  `coefficient` int(11) DEFAULT NULL,
  `creation_date` date DEFAULT NULL,
  `modification_date` date DEFAULT NULL,
  `sub_title` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Skills`
--

LOCK TABLES `Skills` WRITE;
/*!40000 ALTER TABLE `Skills` DISABLE KEYS */;
INSERT INTO `Skills` VALUES (0,'Evaluation croisée',NULL,1,NULL,NULL,'2015-06-23','Evaluation croisée '),(1,'Travail en groupe',NULL,1,NULL,NULL,'2015-06-22','Agir en acteur dynamique et efficace dans un groupe'),(2,'Communication',NULL,5,NULL,NULL,'2015-05-27','Agir en bon communicant dans un environnement scientifique et technique'),(3,'Conduite de projet',NULL,5,NULL,NULL,'2015-05-26','Conduite de projet'),(4,'Conception / réalisation',NULL,5,NULL,NULL,'2015-05-25','Concevoir et réaliser une application informatique (site web)'),(5,'Professionnel responsable',NULL,5,NULL,NULL,'2015-05-27','Agir en professionnel responsable');
/*!40000 ALTER TABLE `Skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sub_skills`
--

DROP TABLE IF EXISTS `Sub_skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Sub_skills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_skill` int(11) DEFAULT NULL,
  `title` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_respo` int(11) DEFAULT NULL,
  `note` mediumtext COLLATE utf8_unicode_ci,
  `creation_date` date DEFAULT NULL,
  `modification_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sub_skills_skills` (`id_skill`),
  CONSTRAINT `id` FOREIGN KEY (`id_skill`) REFERENCES `Skills` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sub_skills`
--

LOCK TABLES `Sub_skills` WRITE;
/*!40000 ALTER TABLE `Sub_skills` DISABLE KEYS */;
INSERT INTO `Sub_skills` VALUES (1,1,'Travailler en équipe',5,NULL,NULL,'2015-06-22'),(2,1,'Animer une équipe',5,NULL,NULL,'2015-06-22'),(3,1,'Gérer les conflits',5,NULL,NULL,'2015-06-22'),(4,1,'Être force de proposition',5,NULL,NULL,'2015-06-22'),(5,2,'Écouter et se faire écouter',5,NULL,NULL,NULL),(6,2,'Mener un dialogue',5,NULL,NULL,NULL),(7,2,'Documentation efficace',5,NULL,NULL,NULL),(8,2,'Avoir une approche communicative adaptée',5,NULL,NULL,NULL),(9,3,'Méthodologie',5,NULL,NULL,'2015-05-26'),(10,3,'Planifier un projet',5,NULL,NULL,'2015-05-26'),(11,3,'Suivre un projet',5,NULL,NULL,'2015-05-26'),(12,3,'Utiliser des outils de suivi de projets',5,NULL,NULL,'2015-05-26'),(13,3,'Répondre aux attentes du client',5,NULL,NULL,'2015-05-26'),(14,4,'Spécification des besoins',5,NULL,NULL,'2015-05-25'),(15,4,'Conception visuelle',5,NULL,NULL,'2015-05-25'),(16,4,'Conception des données',5,NULL,NULL,'2015-05-25'),(17,4,'Conception technique',5,NULL,NULL,'2015-05-25'),(18,4,'Développement HTML',5,NULL,NULL,'2015-05-25'),(19,4,'Développement CSS',5,NULL,NULL,'2015-05-25'),(20,4,'Développement PHP',5,NULL,NULL,'2015-05-25'),(21,4,'Développement Javascript',5,NULL,NULL,'2015-05-25'),(22,4,'Développement SQL',5,NULL,NULL,'2015-05-25'),(23,4,'Organisation du code',5,NULL,NULL,'2015-05-25'),(24,4,'Test et validation',5,NULL,NULL,'2015-05-25'),(25,5,'Professionnalisme',5,NULL,NULL,'2015-05-27'),(26,5,'Autonomie',5,NULL,NULL,'2015-05-27'),(63,0,'Travail en groupe',NULL,NULL,NULL,'2015-06-23'),(64,0,'Communication ',5,NULL,NULL,'2015-06-23'),(66,0,'Conduite de projet ',1,'		','2015-06-23',NULL),(67,0,'Conception / réalisation',1,'		','2015-06-23',NULL),(68,0,'Professionnel responsable',1,'		','2015-06-23',NULL);
/*!40000 ALTER TABLE `Sub_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pseudo` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_group` int(11) DEFAULT NULL,
  `promo` int(11) DEFAULT NULL,
  `last_name` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `first_name` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `mail` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `add_date` date DEFAULT NULL,
  `id_post` int(11) DEFAULT NULL,
  `isep_no` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `speudo_UNIQUE` (`pseudo`),
  KEY `idx_users_groups` (`id_group`),
  CONSTRAINT `key_users_groups` FOREIGN KEY (`id_group`) REFERENCES `Groups` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6695 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'slefebvr',NULL,NULL,'Lefebvre','Sylvain','sylvain.lefebvre@isep.fr','*673FBA270E0BA557EFA09B68E73879B842E3C8CC',NULL,2,0),(6,'nlefebvr',NULL,NULL,'Lefebvre','Natacha','natacha.lefebvre@isep.fr',NULL,NULL,1,0),(8,'mmanceny',NULL,NULL,'Matthieu','Manceny','mmanceny@isep.fr','*CB5E8D7195832C5F233D5B228EDDE5A3F4C83785',NULL,3,0),(3771,'zkazi',NULL,NULL,'zakia','kazi','zkazi@isep.fr','*70F9265FA1236967062FBB0D6B411CF6D0A2E2D5','2015-06-14',3,0),(4530,'jwinandy',NULL,NULL,'Jonathan ','Winandy','jwinandy@gmail.com','*8C8875FB9CCD09E534232C97C1D19B57DCC42B7F','2015-06-15',3,0),(4631,'tdebroca',NULL,NULL,'De Broca','Thibaut','tdebroca@gmail.com','*951EA0EC5CDBD25C3ED644325A61A274ADCCABD4','2015-06-20',3,0),(6596,'sdupont-',1437,NULL,'DUPONT-NIVET','Simon','sdupont-nivet@isep.fr',NULL,'2015-06-30',4,7174),(6597,'cedme',1437,NULL,'EDME','Capucine','cedme@isep.fr',NULL,'2015-06-30',4,7176),(6598,'zkaneswa',1437,NULL,'KANESWARAN','Zovena','zkaneswaran@isep.fr',NULL,'2015-06-30',4,8358),(6599,'ztang',1437,NULL,'TANG','Zhaochen','ztang@isep.fr',NULL,'2015-06-30',4,7980),(6600,'hwan',1437,NULL,'WAN','Hong','hwan@isep.fr',NULL,'2015-06-30',4,7981),(6601,'ali',1437,NULL,'LI','Ang','ali@isep.fr',NULL,'2015-06-30',4,7977),(6602,'dchantha',1438,NULL,'CHANTHAVONG','Delphine','dchanthavong@isep.fr',NULL,'2015-06-30',4,8350),(6603,'tvecchio',1438,NULL,'VECCHIO','Thibaud','tvecchio@isep.fr',NULL,'2015-06-30',4,7270),(6604,'sfellahi',1438,NULL,'FELLAHI','Soufiane','sfellahi@isep.fr',NULL,'2015-06-30',4,8412),(6605,'pdu',1438,NULL,'DU','Pengzhan','pdu@isep.fr',NULL,'2015-06-30',4,7974),(6606,'yhe',1438,NULL,'HE','Yuhai','yhe@isep.fr',NULL,'2015-06-30',4,7975),(6607,'nzhang',1438,NULL,'ZHANG','Nan','nzhang@isep.fr',NULL,'2015-06-30',4,7983),(6608,'xjeannen',1439,NULL,'JEANNENEY','Xavier','xjeanneney@isep.fr',NULL,'2015-06-30',4,7184),(6609,'fcombes',1439,NULL,'COMBES','Fanny','fcombes@isep.fr',NULL,'2015-06-30',4,8408),(6610,'gdemontg',1439,NULL,'DEMONTGRAND','Gauthier','gdemontgrand@isep.fr',NULL,'2015-06-30',4,8342),(6611,'akapoor',1439,NULL,'KAPOOR','Anuraag','akapoor@isep.fr',NULL,'2015-06-30',4,8032),(6612,'sjiang',1439,NULL,'JIANG','Shufan','sjiang@isep.fr',NULL,'2015-06-30',4,8001),(6613,'lzhang',1439,NULL,'ZHANG','Li','lzhang@isep.fr',NULL,'2015-06-30',4,7982),(6614,'pfrelot',1440,NULL,'FRELOT','Pauline','pfrelot@isep.fr',NULL,'2015-06-30',4,7241),(6615,'smazard',1440,NULL,'MAZARD','Sylvain','smazard@isep.fr',NULL,'2015-06-30',4,7193),(6616,'slaurett',1440,NULL,'LAURETTE','Simon','slaurette@isep.fr',NULL,'2015-06-30',4,7247),(6617,'tjiang',1440,NULL,'JIANG','Ting','tjiang@isep.fr',NULL,'2015-06-30',4,7976),(6618,'pli',1440,NULL,'LI','Peiyun','pli@isep.fr',NULL,'2015-06-30',4,7978),(6619,'mtauch',1441,NULL,'TAUCH','Mickael','mtauch@isep.fr',NULL,'2015-06-30',4,7209),(6620,'ioussdid',1441,NULL,'OUSSDID','Imane','ioussdid@isep.fr',NULL,'2015-06-30',4,8398),(6621,'groubeix',1441,NULL,'ROUBEIX','Gervais','groubeix@isep.fr',NULL,'2015-06-30',4,7264),(6622,'ecomazzi',1441,NULL,'COMAZZI','Enzo','ecomazzi@isep.fr',NULL,'2015-06-30',4,7164),(6623,'bdebuye',1441,NULL,'DE BUYER','Benoît','bdebuyer@isep.fr',NULL,'2015-06-30',4,7298),(6624,'cheissel',1441,NULL,'HEISSEL','Camille','cheissel@isep.fr',NULL,'2015-06-30',4,8407),(6625,'smangann',1441,NULL,'MANGANNEAU','Stéphane','smanganneau@isep.fr',NULL,'2015-06-30',4,8329),(6626,'gthevene',1442,NULL,'THEVENET','Guillaume','gthevenet@isep.fr',NULL,'2015-06-30',4,7210),(6627,'vgauch',1442,NULL,'GAUCH','Victorien','vgauch@isep.fr',NULL,'2015-06-30',4,7335),(6628,'cforgeon',1442,NULL,'FORGEON','Cécilia','cforgeon@isep.fr',NULL,'2015-06-30',4,7178),(6629,'ddelettr',1442,NULL,'DELETTRE','Damien','ddelettre@isep.fr',NULL,'2015-06-30',4,8410),(6630,'asampiet',1442,NULL,'SAMPIETRO','Alexandra','asampietro@isep.fr',NULL,'2015-06-30',4,8382),(6631,'tperrin',1442,NULL,'PERRIN','Thomas','tperrin@isep.fr',NULL,'2015-06-30',4,6911),(6632,'tsoumbou',1442,NULL,'SOUMBOU','Terrence','tsoumbou@isep.fr',NULL,'2015-06-30',4,8326),(6633,'pmathieu',1443,NULL,'MATHIEU','Paul','pmathieu@isep.fr',NULL,'2015-06-30',4,8365),(6634,'ppestel',1443,NULL,'PESTEL','Pierre','ppestel@isep.fr',NULL,'2015-06-30',4,7200),(6635,'oturbé',1443,NULL,'TURBÉ','Olivier','oturbé@isep.fr',NULL,'2015-06-30',4,7213),(6636,'mberail',1443,NULL,'BERAIL','Maxime','mberail@isep.fr',NULL,'2015-06-30',4,7334),(6637,'fbento',1443,NULL,'BENTO','Florent','fbento@isep.fr',NULL,'2015-06-30',4,8357),(6638,'nrasolom',1443,NULL,'RASOLOMALALA','Narisely','nrasolomalala@isep.fr',NULL,'2015-06-30',4,7206),(6639,'wruggier',1444,NULL,'RUGGIERO','William','wruggiero@isep.fr',NULL,'2015-06-30',4,8374),(6640,'abourget',1444,NULL,'BOURGET','Amandine','abourget@isep.fr',NULL,'2015-06-30',4,7221),(6641,'usariogl',1444,NULL,'SARIOGLU','Umut','usarioglu@isep.fr',NULL,'2015-06-30',4,7207),(6642,'scasanov',1444,NULL,'CASANOVA','Stéphane','scasanova@isep.fr',NULL,'2015-06-30',4,7160),(6643,'clechelo',1444,NULL,'LECHELON','Constance','clechelon@isep.fr',NULL,'2015-06-30',4,7186),(6644,'ffohrer',1444,NULL,'FOHRER','Florian','ffohrer@isep.fr',NULL,'2015-06-30',4,8370),(6645,'mfouille',1445,NULL,'FOUILLEN','Morgan','mfouillen@isep.fr',NULL,'2015-06-30',4,8360),(6646,'lalegoët',1445,NULL,'ALEGOËT','Louis-Marie','lalegoët@isep.fr',NULL,'2015-06-30',4,7332),(6647,'jroussel',1445,NULL,'ROUSSEL','Jeanne','jroussel@isep.fr',NULL,'2015-06-30',4,8390),(6648,'mdupont',1445,NULL,'DUPONT','Maxime','mdupont@isep.fr',NULL,'2015-06-30',4,8417),(6649,'yelmaymo',1445,NULL,'ELMAYMOUNY','Yamina','yelmaymouny@isep.fr',NULL,'2015-06-30',4,8402),(6650,'lgerma',1445,NULL,'GERMA','Loïc','lgerma@isep.fr',NULL,'2015-06-30',4,6887),(6651,'fhodzic',1446,NULL,'HODZIC','Fiona','fhodzic@isep.fr',NULL,'2015-06-30',4,7340),(6652,'pkalenga',1446,NULL,'KALENGA','Prunella','pkalenga@isep.fr',NULL,'2015-06-30',4,8334),(6653,'xdembedy',1446,NULL,'DEMBEDYDY','Xavier','xdembedydy@isep.fr',NULL,'2015-06-30',4,8354),(6654,'acastell',1446,NULL,'CASTELLON','Alban','acastellon@isep.fr',NULL,'2015-06-30',4,8385),(6655,'vaguilar',1446,NULL,'AGUILAR','Victor','vaguilar@isep.fr',NULL,'2015-06-30',4,8351),(6656,'bmartins',1446,NULL,'MARTINS','Brandone','bmartins@isep.fr',NULL,'2015-06-30',4,7192),(6657,'nbrami',1447,NULL,'BRAMI','Nathan','nbrami@isep.fr',NULL,'2015-06-30',4,8397),(6658,'dtaleb',1447,NULL,'TALEB','Djalil','dtaleb@isep.fr',NULL,'2015-06-30',4,8366),(6659,'yedahman',1447,NULL,'EDAHMANI','Yassine','yedahmani@isep.fr',NULL,'2015-06-30',4,8369),(6660,'ykinda',1447,NULL,'KINDA','Yves','ykinda@isep.fr',NULL,'2015-06-30',4,8395),(6661,'zmassoum',1447,NULL,'MASSOUM','Zinedine','zmassoum@isep.fr',NULL,'2015-06-30',4,8414),(6662,'mdescham',1447,NULL,'DESCHAMPS','Mikael','mdeschamps@isep.fr',NULL,'2015-06-30',4,6795),(6663,'ptomasin',1448,NULL,'TOMASINA','Pierre','ptomasina@isep.fr',NULL,'2015-06-30',4,7211),(6664,'cquelenn',1448,NULL,'QUELENNEC','Charles','cquelennec@isep.fr',NULL,'2015-06-30',4,8400),(6665,'ngayral',1448,NULL,'GAYRAL','Nicolas','ngayral@isep.fr',NULL,'2015-06-30',4,8340),(6666,'mgranier',1448,NULL,'GRANIER','Mathieu','mgranier@isep.fr',NULL,'2015-06-30',4,8380),(6667,'efaiz',1448,NULL,'FAIZ','Elias','efaiz@isep.fr',NULL,'2015-06-30',4,7177),(6668,'dbiteghe',1448,NULL,'BITEGHE','David','dbiteghe@isep.fr',NULL,'2015-06-30',4,8331),(6669,'jmombo',1448,NULL,'MOMBO','Jacques','jmombo@isep.fr',NULL,'2015-06-30',4,8324),(6670,'amartine',1449,NULL,'MARTINET','Aurélie','amartinet@isep.fr',NULL,'2015-06-30',4,7251),(6671,'pmulsant',1449,NULL,'MULSANT','Pierre','pmulsant@isep.fr',NULL,'2015-06-30',4,6830),(6672,'rphilipp',1449,NULL,'PHILIPPE','Romain','rphilippe@isep.fr',NULL,'2015-06-30',4,7201),(6673,'rdaugy',1449,NULL,'DAUGY','Romain','rdaugy@isep.fr',NULL,'2015-06-30',4,7716),(6674,'cmacia',1449,NULL,'MACIA','Céline','cmacia@isep.fr',NULL,'2015-06-30',4,7187),(6675,'fhamdane',1449,NULL,'HAMDANE','Farouk','fhamdane@isep.fr',NULL,'2015-06-30',4,7300),(6676,'tdelevoy',1450,NULL,'DELEVOYE','Thibault','tdelevoye@isep.fr',NULL,'2015-06-30',4,7169),(6677,'ldivad',1450,NULL,'DIVAD','Loïc','ldivad@isep.fr','*DC9D5A406E4B41DDA75625FD05C0FCC495D1CE32','2015-06-30',4,8333),(6678,'jsportes',1450,NULL,'SPORTES','Jordan','jsportes@isep.fr',NULL,'2015-06-30',4,8355),(6679,'mpuibara',1450,NULL,'PUIBARAUD','Matthieu','mpuibaraud@isep.fr',NULL,'2015-06-30',4,7204),(6680,'sbenazzo',1450,NULL,'BENAZZOUZ','Sara','sbenazzouz@isep.fr',NULL,'2015-06-30',4,8404),(6681,'mfertini',1450,NULL,'FERTINI','Meryem','mfertini@isep.fr',NULL,'2015-06-30',4,8387),(6682,'llemoy',1451,NULL,'LEMOY','Louis','llemoy@isep.fr',NULL,'2015-06-30',4,7273),(6683,'mmarco',1451,NULL,'MARCO','Maxime','mmarco@isep.fr',NULL,'2015-06-30',4,8388),(6684,'calindre',1451,NULL,'ALINDRET','César','calindret@isep.fr',NULL,'2015-06-30',4,7331),(6685,'mbontemp',1451,NULL,'BONTEMPS','Marie-Thérèse','mbontemps@isep.fr',NULL,'2015-06-30',4,6893),(6686,'csigal',1451,NULL,'SIGAL','Chloe','csigal@isep.fr',NULL,'2015-06-30',4,8409),(6687,'maudin',1451,NULL,'AUDIN','Matthieu','maudin@isep.fr',NULL,'2015-06-30',4,7149),(6688,'abesseng',1451,NULL,'BESSENGA','Aboubacar','abessenga@isep.fr',NULL,'2015-06-30',4,8327),(6689,'smkhinin',1452,NULL,'MKHININI','Sahar','smkhinini@isep.fr',NULL,'2015-06-30',4,8339),(6690,'cbrajer',1452,NULL,'BRAJER','Charles','cbrajer@isep.fr',NULL,'2015-06-30',4,7339),(6691,'dkoch',1452,NULL,'KOCH','David','dkoch@isep.fr',NULL,'2015-06-30',4,7244),(6692,'ahounnit',1452,NULL,'HOUNNITE','Anouar','ahounnite@isep.fr',NULL,'2015-06-30',4,7275),(6693,'dalitono',1452,NULL,'ALITONOU','Dorian','dalitonou@isep.fr',NULL,'2015-06-30',4,8335),(6694,'rebang',1452,NULL,'EBANG','Rudolph','rebang@isep.fr',NULL,'2015-06-30',4,8403);
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Values`
--

DROP TABLE IF EXISTS `Values`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Values` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `points` int(11) DEFAULT NULL,
  `cross` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Values`
--

LOCK TABLES `Values` WRITE;
/*!40000 ALTER TABLE `Values` DISABLE KEYS */;
INSERT INTO `Values` VALUES (1,'Non Acquis',0,0),(2,'Niveau de Base en cours d\'acquisition',2,0),(3,'Niveau de Base acquis',3,0),(4,'Niveau Intermédiaire en cours d\'acquisition',4,0),(5,'Niveau Intermédiaire acquis',5,0),(20,'Loin',1,1),(21,'Proche',2,1),(22,'Attendu',3,1),(23,'Dépassé',5,1);
/*!40000 ALTER TABLE `Values` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'APPDB'
--
/*!50106 SET @save_time_zone= @@TIME_ZONE */ ;
/*!50106 DROP EVENT IF EXISTS `DEADLINE_EVENT` */;
DELIMITER ;;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;;
/*!50003 SET character_set_client  = utf8 */ ;;
/*!50003 SET character_set_results = utf8 */ ;;
/*!50003 SET collation_connection  = utf8_general_ci */ ;;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;;
/*!50003 SET @saved_time_zone      = @@time_zone */ ;;
/*!50003 SET time_zone             = 'SYSTEM' */ ;;
/*!50106 CREATE*/ /*!50117 DEFINER=`root`@`localhost`*/ /*!50106 EVENT `DEADLINE_EVENT` ON SCHEDULE EVERY 1 MINUTE STARTS '2015-06-07 09:12:08' ON COMPLETION NOT PRESERVE ENABLE DO CALL DEADLINE_CLOSER() */ ;;
/*!50003 SET time_zone             = @saved_time_zone */ ;;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;;
/*!50003 SET character_set_client  = @saved_cs_client */ ;;
/*!50003 SET character_set_results = @saved_cs_results */ ;;
/*!50003 SET collation_connection  = @saved_col_connection */ ;;
DELIMITER ;
/*!50106 SET TIME_ZONE= @save_time_zone */ ;

--
-- Dumping routines for database 'APPDB'
--
/*!50003 DROP PROCEDURE IF EXISTS `DEADLINE_CLOSER` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`lmdadm`@`%` PROCEDURE `DEADLINE_CLOSER`()
BEGIN
	DECLARE skip INT DEFAULT FALSE;
	DECLARE tmpId INT;
    DECLARE tmpStatus TINYINT(1);

    DECLARE C CURSOR FOR SELECT id, `Status` FROM APPDB.Deadlines WHERE date_limit < NOW();
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET skip = TRUE;
    OPEN C;

    reader: LOOP
		FETCH C INTO tmpId, tmpStatus;
        IF skip THEN
			LEAVE reader;
		END IF;
        IF tmpStatus = 1 THEN
			UPDATE APPDB.Deadlines SET `Status` =  0 WHERE id = tmpId;
        END IF;

    END LOOP;

    CLOSE C;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-30 22:12:00