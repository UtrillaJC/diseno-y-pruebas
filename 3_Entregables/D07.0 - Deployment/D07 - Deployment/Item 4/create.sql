start transaction;

create database `Acme-Explorer`;

use `Acme-Explorer`;

create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';
create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';

grant select, insert, update, delete 
	on `Acme-Explorer`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter,
	create temporary tables, lock tables, create view, create routine,
	alter routine, execute, trigger, show view
	on `Acme-Explorer`.* to 'acme-manager'@'%';

-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: acme-explorer
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `actor_folder`
--

DROP TABLE IF EXISTS `actor_folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_folder` (
  `Actor_id` int(11) NOT NULL,
  `folders_id` int(11) NOT NULL,
  UNIQUE KEY `UK_dp573h40udupcm5m1kgn2bc2r` (`folders_id`),
  CONSTRAINT `FK_dp573h40udupcm5m1kgn2bc2r` FOREIGN KEY (`folders_id`) REFERENCES `folder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_folder`
--

LOCK TABLES `actor_folder` WRITE;
/*!40000 ALTER TABLE `actor_folder` DISABLE KEYS */;
INSERT INTO `actor_folder` VALUES (9416,9472),(9416,9473),(9416,9474),(9416,9475),(9416,9476);
/*!40000 ALTER TABLE `actor_folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_socialidentity`
--

DROP TABLE IF EXISTS `actor_socialidentity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_socialidentity` (
  `Actor_id` int(11) NOT NULL,
  `socialIdentities_id` int(11) NOT NULL,
  UNIQUE KEY `UK_fvjtbijvsijias3suehxiq0jm` (`socialIdentities_id`),
  CONSTRAINT `FK_fvjtbijvsijias3suehxiq0jm` FOREIGN KEY (`socialIdentities_id`) REFERENCES `socialidentity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_socialidentity`
--

LOCK TABLES `actor_socialidentity` WRITE;
/*!40000 ALTER TABLE `actor_socialidentity` DISABLE KEYS */;
INSERT INTO `actor_socialidentity` VALUES (9416,9414),(9416,9415);
/*!40000 ALTER TABLE `actor_socialidentity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gfgqmtp2f9i5wsojt33xm0uth` (`userAccount_id`),
  CONSTRAINT `FK_gfgqmtp2f9i5wsojt33xm0uth` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (9416,0,'Calle Nescania nº1 3B','admin1@acmeexplorer.com','Admin 1','+34 (111) 1245','Admin 1',9404);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `cvv` int(11) DEFAULT NULL,
  `expirationMonth` int(11) DEFAULT NULL,
  `expirationYear` int(11) DEFAULT NULL,
  `holder` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `rejectReason` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `applicant_id` int(11) NOT NULL,
  `trip_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5ho5khuj5b6xfls5l7h5969xo` (`applicant_id`),
  KEY `FK_aa8ob0qnub9wp8splrk7qoh0t` (`trip_id`),
  CONSTRAINT `FK_aa8ob0qnub9wp8splrk7qoh0t` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`),
  CONSTRAINT `FK_5ho5khuj5b6xfls5l7h5969xo` FOREIGN KEY (`applicant_id`) REFERENCES `explorer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

--
-- Table structure for table `auditor`
--

DROP TABLE IF EXISTS `auditor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auditor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_img3kaubb62u0h0jujkh1tejo` (`userAccount_id`),
  CONSTRAINT `FK_img3kaubb62u0h0jujkh1tejo` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditor`
--


DROP TABLE IF EXISTS `auditrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auditrecord` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `finalVersion` bit(1) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `auditor_id` int(11) NOT NULL,
  `trip_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_td4sv8mbdf910amh5qj8kskjv` (`auditor_id`),
  KEY `FK_byvesjo01ngtojx6qbba2ayth` (`trip_id`),
  CONSTRAINT `FK_byvesjo01ngtojx6qbba2ayth` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`),
  CONSTRAINT `FK_td4sv8mbdf910amh5qj8kskjv` FOREIGN KEY (`auditor_id`) REFERENCES `auditor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditrecord`
--


--
-- Table structure for table `auditrecord_attachments`
--

DROP TABLE IF EXISTS `auditrecord_attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auditrecord_attachments` (
  `AuditRecord_id` int(11) NOT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  KEY `FK_maqb3v04iw2jvggyf0qyd0dtp` (`AuditRecord_id`),
  CONSTRAINT `FK_maqb3v04iw2jvggyf0qyd0dtp` FOREIGN KEY (`AuditRecord_id`) REFERENCES `auditrecord` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditrecord_attachments`
--


--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parentCategory_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3v34vcvwua46xp9jd0bj7rk78` (`parentCategory_id`),
  CONSTRAINT `FK_3v34vcvwua46xp9jd0bj7rk78` FOREIGN KEY (`parentCategory_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (9429,0,'CATEGORY',NULL),(9430,0,'Safari',9429),(9431,0,'Climbing',9429),(9432,0,'Ground',9430),(9433,0,'Water',9430),(9434,0,'Mountain',9431),(9435,0,'River',9431),(9436,0,'Savana',9432),(9437,0,'Mountain',9432),(9438,0,'Lake',9433),(9439,0,'River',9433);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration`
--

DROP TABLE IF EXISTS `configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `countryCode` varchar(255) DEFAULT NULL,
  `englishWelcome` varchar(255) DEFAULT NULL,
  `finderCache` int(11) NOT NULL,
  `maxTripsDisplay` int(11) NOT NULL,
  `spanishWelcome` varchar(255) DEFAULT NULL,
  `vat` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration`
--

LOCK TABLES `configuration` WRITE;
/*!40000 ALTER TABLE `configuration` DISABLE KEYS */;
INSERT INTO `configuration` VALUES (9455,0,'http://creek-tours.com/wp-content/uploads/Kenya-Tanzania-Family-Safari-banner.jpg','+34','Tanzanika is an adven-ture company that makes your explorer’s dreams true',60,10,'Tanzanika es la empresa de aven-turas que hará tus sueños de explorador realidad',21);
/*!40000 ALTER TABLE `configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration_spamwords`
--

DROP TABLE IF EXISTS `configuration_spamwords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration_spamwords` (
  `Configuration_id` int(11) NOT NULL,
  `spamWords` varchar(255) DEFAULT NULL,
  KEY `FK_71ono8c3475iofflg3gestqeq` (`Configuration_id`),
  CONSTRAINT `FK_71ono8c3475iofflg3gestqeq` FOREIGN KEY (`Configuration_id`) REFERENCES `configuration` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration_spamwords`
--

LOCK TABLES `configuration_spamwords` WRITE;
/*!40000 ALTER TABLE `configuration_spamwords` DISABLE KEYS */;
INSERT INTO `configuration_spamwords` VALUES (9455,'viagra'),(9455,'cialis'),(9455,'sex'),(9455,'jes extender');
/*!40000 ALTER TABLE `configuration_spamwords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `explorer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8ry0kxapinlsca2ubjvin3c5e` (`explorer_id`),
  CONSTRAINT `FK_8ry0kxapinlsca2ubjvin3c5e` FOREIGN KEY (`explorer_id`) REFERENCES `explorer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `curriculum`
--

DROP TABLE IF EXISTS `curriculum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `personalRecord_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mabrm092fo1ae2t2m5a3ww4en` (`personalRecord_id`),
  UNIQUE KEY `UK_cefwvqc4ixh7s51wfd1yi77yg` (`ticker`),
  CONSTRAINT `FK_mabrm092fo1ae2t2m5a3ww4en` FOREIGN KEY (`personalRecord_id`) REFERENCES `personalrecord` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum`
--


--
-- Table structure for table `curriculum_educationrecord`
--

DROP TABLE IF EXISTS `curriculum_educationrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum_educationrecord` (
  `Curriculum_id` int(11) NOT NULL,
  `educationRecords_id` int(11) NOT NULL,
  UNIQUE KEY `UK_pl4rfqren0agff76y04l3y611` (`educationRecords_id`),
  KEY `FK_oldens4o8aaajbroxyv11fo6g` (`Curriculum_id`),
  CONSTRAINT `FK_oldens4o8aaajbroxyv11fo6g` FOREIGN KEY (`Curriculum_id`) REFERENCES `curriculum` (`id`),
  CONSTRAINT `FK_pl4rfqren0agff76y04l3y611` FOREIGN KEY (`educationRecords_id`) REFERENCES `educationrecord` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum_educationrecord`
--


--
-- Table structure for table `curriculum_endorserrecord`
--

DROP TABLE IF EXISTS `curriculum_endorserrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum_endorserrecord` (
  `Curriculum_id` int(11) NOT NULL,
  `endorserRecords_id` int(11) NOT NULL,
  UNIQUE KEY `UK_bbelpqhyh6qocyclhn133ndyh` (`endorserRecords_id`),
  KEY `FK_o79rcsgjmwauctqvj9b7i4ukb` (`Curriculum_id`),
  CONSTRAINT `FK_o79rcsgjmwauctqvj9b7i4ukb` FOREIGN KEY (`Curriculum_id`) REFERENCES `curriculum` (`id`),
  CONSTRAINT `FK_bbelpqhyh6qocyclhn133ndyh` FOREIGN KEY (`endorserRecords_id`) REFERENCES `endorserrecord` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum_endorserrecord`
--


--
-- Table structure for table `curriculum_miscellaneousrecord`
--

DROP TABLE IF EXISTS `curriculum_miscellaneousrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum_miscellaneousrecord` (
  `Curriculum_id` int(11) NOT NULL,
  `miscellaneousRecords_id` int(11) NOT NULL,
  UNIQUE KEY `UK_i87gspje3t3kah740fj2aup8` (`miscellaneousRecords_id`),
  KEY `FK_qq8tboyasceyejxb5gtnyhlbx` (`Curriculum_id`),
  CONSTRAINT `FK_qq8tboyasceyejxb5gtnyhlbx` FOREIGN KEY (`Curriculum_id`) REFERENCES `curriculum` (`id`),
  CONSTRAINT `FK_i87gspje3t3kah740fj2aup8` FOREIGN KEY (`miscellaneousRecords_id`) REFERENCES `miscellaneousrecord` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum_miscellaneousrecord`
--


--
-- Table structure for table `curriculum_professionalrecord`
--

DROP TABLE IF EXISTS `curriculum_professionalrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum_professionalrecord` (
  `Curriculum_id` int(11) NOT NULL,
  `professionalRecords_id` int(11) NOT NULL,
  UNIQUE KEY `UK_fmlxwyi8ktscvd4hflcg2xerj` (`professionalRecords_id`),
  KEY `FK_cxve92cmijecu4e9ev01pp3wc` (`Curriculum_id`),
  CONSTRAINT `FK_cxve92cmijecu4e9ev01pp3wc` FOREIGN KEY (`Curriculum_id`) REFERENCES `curriculum` (`id`),
  CONSTRAINT `FK_fmlxwyi8ktscvd4hflcg2xerj` FOREIGN KEY (`professionalRecords_id`) REFERENCES `professionalrecord` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum_professionalrecord`
--

--
-- Table structure for table `educationrecord`
--

DROP TABLE IF EXISTS `educationrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `educationrecord` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `institution` varchar(255) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `educationrecord`
--


--
-- Table structure for table `endorserrecord`
--

DROP TABLE IF EXISTS `endorserrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endorserrecord` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endorserrecord`
--


--
-- Table structure for table `explorer`
--

DROP TABLE IF EXISTS `explorer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `explorer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  `finder_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gmhjk9b98bjgtxkh0gmck9ly8` (`finder_id`),
  KEY `FK_fjs8q8hlbsegiuxw4r2ko6uv6` (`userAccount_id`),
  CONSTRAINT `FK_fjs8q8hlbsegiuxw4r2ko6uv6` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`),
  CONSTRAINT `FK_gmhjk9b98bjgtxkh0gmck9ly8` FOREIGN KEY (`finder_id`) REFERENCES `finder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `explorer`
--


--
-- Table structure for table `finder`
--

DROP TABLE IF EXISTS `finder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `lastUpdate` datetime DEFAULT NULL,
  `priceMax` double DEFAULT NULL,
  `priceMin` double DEFAULT NULL,
  `startDateTripMax` datetime DEFAULT NULL,
  `startDateTripMin` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder`
--



--
-- Table structure for table `finder_trip`
--

DROP TABLE IF EXISTS `finder_trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder_trip` (
  `Finder_id` int(11) NOT NULL,
  `trips_id` int(11) NOT NULL,
  KEY `FK_banf3cvq0wb1mhnq645alwstq` (`trips_id`),
  KEY `FK_5c35eq4ikvgxcjfyq4g1l0g3e` (`Finder_id`),
  CONSTRAINT `FK_5c35eq4ikvgxcjfyq4g1l0g3e` FOREIGN KEY (`Finder_id`) REFERENCES `finder` (`id`),
  CONSTRAINT `FK_banf3cvq0wb1mhnq645alwstq` FOREIGN KEY (`trips_id`) REFERENCES `trip` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder_trip`
--


--
-- Table structure for table `folder`
--

DROP TABLE IF EXISTS `folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `predefined` bit(1) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_e6lcmpm09goh6x4n16fbq5uka` (`parent_id`),
  CONSTRAINT `FK_e6lcmpm09goh6x4n16fbq5uka` FOREIGN KEY (`parent_id`) REFERENCES `folder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder`
--

LOCK TABLES `folder` WRITE;
/*!40000 ALTER TABLE `folder` DISABLE KEYS */;
INSERT INTO `folder` VALUES (9472,0,'In Box','',NULL),(9473,0,'Trash Box','',NULL),(9474,0,'Out Box','',NULL),(9475,0,'Spam Box','',NULL),(9476,0,'Notification Box','',NULL),(9477,0,'In Box','',NULL),(9478,0,'Trash Box','',NULL),(9479,0,'Out Box','',NULL),(9480,0,'Spam Box','',NULL),(9481,0,'Notification Box','',NULL),(9482,0,'In Box','',NULL),(9483,0,'Trash Box','',NULL),(9484,0,'Out Box','',NULL),(9485,0,'Spam Box','',NULL),(9486,0,'Notification Box','',NULL),(9487,0,'In Box','',NULL),(9488,0,'Trash Box','',NULL),(9489,0,'Out Box','',NULL),(9490,0,'Spam Box','',NULL),(9491,0,'Notification Box','',NULL),(9492,0,'In Box','',NULL),(9493,0,'Trash Box','',NULL),(9494,0,'Out Box','',NULL),(9495,0,'Spam Box','',NULL),(9496,0,'Notification Box','',NULL),(9497,0,'In Box','',NULL),(9498,0,'Trash Box','',NULL),(9499,0,'Out Box','',NULL),(9500,0,'Spam Box','',NULL),(9501,0,'Notification Box','',NULL),(9502,0,'In Box','',NULL),(9503,0,'Trash Box','',NULL),(9504,0,'Out Box','',NULL),(9505,0,'Spam Box','',NULL),(9506,0,'Notification Box','',NULL),(9507,0,'In Box','',NULL),(9508,0,'Trash Box','',NULL),(9509,0,'Out Box','',NULL),(9510,0,'Spam Box','',NULL),(9511,0,'Notification Box','',NULL),(9512,0,'In Box','',NULL),(9513,0,'Trash Box','',NULL),(9514,0,'Out Box','',NULL),(9515,0,'Spam Box','',NULL),(9516,0,'Notification Box','',NULL),(9517,0,'In Box','',NULL),(9518,0,'Trash Box','',NULL),(9519,0,'Out Box','',NULL),(9520,0,'Spam Box','',NULL),(9521,0,'Notification Box','',NULL);
/*!40000 ALTER TABLE `folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hasvalue`
--

DROP TABLE IF EXISTS `hasvalue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hasvalue` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `tag_id` int(11) NOT NULL,
  `trip_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qy87vpcjds32xqjqjw8g0v0bs` (`tag_id`),
  KEY `FK_l0d67hek2l0tqj81c15yk5fut` (`trip_id`),
  CONSTRAINT `FK_l0d67hek2l0tqj81c15yk5fut` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`),
  CONSTRAINT `FK_qy87vpcjds32xqjqjw8g0v0bs` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hasvalue`
--

LOCK TABLES `hasvalue` WRITE;
/*!40000 ALTER TABLE `hasvalue` DISABLE KEYS */;
INSERT INTO `hasvalue` VALUES (9607,0,'Value 1',9440,9528),(9608,0,'Value 2',9441,9534),(9609,0,'Value 3',9442,9539),(9610,0,'Value 4',9440,9543),(9611,0,'Value 5',9441,9546),(9612,0,'Value 6',9442,9548),(9613,0,'Value 7',9440,9551),(9614,0,'Value 8',9441,9555),(9615,0,'Value 9',9442,9560),(9616,0,'Value 10',9440,9566);
/*!40000 ALTER TABLE `hasvalue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `legaltext`
--

DROP TABLE IF EXISTS `legaltext`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `legaltext` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `draft` bit(1) NOT NULL,
  `laws` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `legaltext`
--


--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  `suspicious` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_84bmmxlq61tiaoc7dy7kdcghh` (`userAccount_id`),
  CONSTRAINT `FK_84bmmxlq61tiaoc7dy7kdcghh` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--



--
-- Table structure for table `manager_survivalclass`
--

DROP TABLE IF EXISTS `manager_survivalclass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager_survivalclass` (
  `Manager_id` int(11) NOT NULL,
  `survivalClasses_id` int(11) NOT NULL,
  UNIQUE KEY `UK_ialxbswb11xwicwj88dj3mnin` (`survivalClasses_id`),
  KEY `FK_2k803htukyqf8omvqg9ndnpvy` (`Manager_id`),
  CONSTRAINT `FK_2k803htukyqf8omvqg9ndnpvy` FOREIGN KEY (`Manager_id`) REFERENCES `manager` (`id`),
  CONSTRAINT `FK_ialxbswb11xwicwj88dj3mnin` FOREIGN KEY (`survivalClasses_id`) REFERENCES `survivalclass` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager_survivalclass`
--


--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `priority` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `folder_id` int(11) NOT NULL,
  `recipient_id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7t1ls63lqb52igs4ms20cf94t` (`folder_id`),
  CONSTRAINT `FK_7t1ls63lqb52igs4ms20cf94t` FOREIGN KEY (`folder_id`) REFERENCES `folder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--


--
-- Table structure for table `miscellaneousrecord`
--

DROP TABLE IF EXISTS `miscellaneousrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `miscellaneousrecord` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `miscellaneousrecord`
--


--
-- Table structure for table `note`
--

DROP TABLE IF EXISTS `note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `note` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `reply` varchar(255) DEFAULT NULL,
  `replyMoment` datetime DEFAULT NULL,
  `auditor_id` int(11) NOT NULL,
  `trip_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_c3f5vlsshwqtjo5f74rfs8gm4` (`auditor_id`),
  KEY `FK_phngbfwt9o5v49bpgsb9mkv66` (`trip_id`),
  CONSTRAINT `FK_phngbfwt9o5v49bpgsb9mkv66` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`),
  CONSTRAINT `FK_c3f5vlsshwqtjo5f74rfs8gm4` FOREIGN KEY (`auditor_id`) REFERENCES `auditor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note`
--


--
-- Table structure for table `personalrecord`
--

DROP TABLE IF EXISTS `personalrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personalrecord` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personalrecord`
--

--
-- Table structure for table `professionalrecord`
--

DROP TABLE IF EXISTS `professionalrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professionalrecord` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professionalrecord`
--

--
-- Table structure for table `ranger`
--

DROP TABLE IF EXISTS `ranger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ranger` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  `suspicious` bit(1) NOT NULL,
  `curriculum_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_dbxchym8y9qceta3m1sn0cg6r` (`curriculum_id`),
  KEY `FK_axw07q0mobub2mvr0xeh7al5f` (`userAccount_id`),
  CONSTRAINT `FK_axw07q0mobub2mvr0xeh7al5f` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`),
  CONSTRAINT `FK_dbxchym8y9qceta3m1sn0cg6r` FOREIGN KEY (`curriculum_id`) REFERENCES `curriculum` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ranger`
--


--
-- Table structure for table `socialidentity`
--

DROP TABLE IF EXISTS `socialidentity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socialidentity` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `nameSocialNetwork` varchar(255) DEFAULT NULL,
  `nick` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socialidentity`
--


--
-- Table structure for table `sponsor`
--

DROP TABLE IF EXISTS `sponsor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sponsor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_okfx8h0cn4eidh8ng563vowjc` (`userAccount_id`),
  CONSTRAINT `FK_okfx8h0cn4eidh8ng563vowjc` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsor`
--

--
-- Table structure for table `sponsorship`
--

DROP TABLE IF EXISTS `sponsorship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sponsorship` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `cvv` int(11) DEFAULT NULL,
  `expirationMonth` int(11) DEFAULT NULL,
  `expirationYear` int(11) DEFAULT NULL,
  `holder` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `information` varchar(255) DEFAULT NULL,
  `sponsor_id` int(11) NOT NULL,
  `trip_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_e3idyfyffpufog3sjl3c2ulun` (`sponsor_id`),
  KEY `FK_p6ydvo1r10eo68cj18xvbx37q` (`trip_id`),
  CONSTRAINT `FK_p6ydvo1r10eo68cj18xvbx37q` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`),
  CONSTRAINT `FK_e3idyfyffpufog3sjl3c2ulun` FOREIGN KEY (`sponsor_id`) REFERENCES `sponsor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsorship`
--


--
-- Table structure for table `stage`
--

DROP TABLE IF EXISTS `stage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stage` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `trip_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g8uuphy1i55oxqtlti7cftxk5` (`trip_id`),
  CONSTRAINT `FK_g8uuphy1i55oxqtlti7cftxk5` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stage`
--


--
-- Table structure for table `story`
--

DROP TABLE IF EXISTS `story`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `story` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `trip_id` int(11) NOT NULL,
  `writer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h0duh0rohxwpscrd7e89cnsgv` (`trip_id`),
  KEY `FK_ngprr9omfeihxd1l3a2th2lcr` (`writer_id`),
  CONSTRAINT `FK_ngprr9omfeihxd1l3a2th2lcr` FOREIGN KEY (`writer_id`) REFERENCES `explorer` (`id`),
  CONSTRAINT `FK_h0duh0rohxwpscrd7e89cnsgv` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `story`
--


--
-- Table structure for table `story_attachments`
--

DROP TABLE IF EXISTS `story_attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `story_attachments` (
  `Story_id` int(11) NOT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  KEY `FK_ff4kbl6pm6bat35eamsxb9n51` (`Story_id`),
  CONSTRAINT `FK_ff4kbl6pm6bat35eamsxb9n51` FOREIGN KEY (`Story_id`) REFERENCES `story` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `story_attachments`
--


--
-- Table structure for table `survivalclass`
--

DROP TABLE IF EXISTS `survivalclass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `survivalclass` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `trip_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_kgmsndege9b3pa1f2gce9hb40` (`trip_id`),
  CONSTRAINT `FK_kgmsndege9b3pa1f2gce9hb40` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survivalclass`
--


--
-- Table structure for table `survivalclass_explorer`
--

DROP TABLE IF EXISTS `survivalclass_explorer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `survivalclass_explorer` (
  `survivalClasses_id` int(11) NOT NULL,
  `explorers_id` int(11) NOT NULL,
  KEY `FK_7i2xgij2tq2yt7rj87jj5obgx` (`explorers_id`),
  KEY `FK_fqf6l4e2uqot4pg17xuue2acx` (`survivalClasses_id`),
  CONSTRAINT `FK_fqf6l4e2uqot4pg17xuue2acx` FOREIGN KEY (`survivalClasses_id`) REFERENCES `survivalclass` (`id`),
  CONSTRAINT `FK_7i2xgij2tq2yt7rj87jj5obgx` FOREIGN KEY (`explorers_id`) REFERENCES `explorer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survivalclass_explorer`
--

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (9440,0,'country'),(9441,0,'expertise'),(9442,0,'dangerousness');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trip`
--

DROP TABLE IF EXISTS `trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trip` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `cancelledReason` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `endDateTrip` datetime DEFAULT NULL,
  `price` double DEFAULT NULL,
  `publicationDate` datetime DEFAULT NULL,
  `requirements` varchar(255) DEFAULT NULL,
  `startDateTrip` datetime DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `legalText_id` int(11) NOT NULL,
  `manager_id` int(11) NOT NULL,
  `ranger_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ugq799eb7dgmgmqjoxkjpx5k` (`ticker`),
  KEY `FK_mdljo2c0aixmrcx025mvfkc3x` (`category_id`),
  KEY `FK_ol2sss4qsbxlb5rhdynrl33wy` (`legalText_id`),
  KEY `FK_bt26lls0o7ykesy9ye75887rk` (`manager_id`),
  KEY `FK_65bd0l9xa0l1x5qnjg2su9tig` (`ranger_id`),
  CONSTRAINT `FK_65bd0l9xa0l1x5qnjg2su9tig` FOREIGN KEY (`ranger_id`) REFERENCES `ranger` (`id`),
  CONSTRAINT `FK_bt26lls0o7ykesy9ye75887rk` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`),
  CONSTRAINT `FK_mdljo2c0aixmrcx025mvfkc3x` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FK_ol2sss4qsbxlb5rhdynrl33wy` FOREIGN KEY (`legalText_id`) REFERENCES `legaltext` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip`
--


--
-- Table structure for table `trip_stage`
--

DROP TABLE IF EXISTS `trip_stage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trip_stage` (
  `Trip_id` int(11) NOT NULL,
  `stages_id` int(11) NOT NULL,
  UNIQUE KEY `UK_pypsdnr343h93d9c7hpugcyyv` (`stages_id`),
  KEY `FK_stea9evs7jmvh3yf9i17pw6kn` (`Trip_id`),
  CONSTRAINT `FK_stea9evs7jmvh3yf9i17pw6kn` FOREIGN KEY (`Trip_id`) REFERENCES `trip` (`id`),
  CONSTRAINT `FK_pypsdnr343h93d9c7hpugcyyv` FOREIGN KEY (`stages_id`) REFERENCES `stage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip_stage`
--


--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `enable` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (9404,0,'','e00cf25ad42683b3df678c61f42c6bda','admin1');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
/*!40000 ALTER TABLE `useraccount_authorities` DISABLE KEYS */;
INSERT INTO `useraccount_authorities` VALUES (9404,'ADMIN');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;	
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-19 18:52:02

commit;
