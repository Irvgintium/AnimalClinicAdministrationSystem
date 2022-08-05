-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 07, 2016 at 01:40 AM
-- Server version: 5.6.14
-- PHP Version: 5.5.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `acas_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `allergies`
--

CREATE TABLE IF NOT EXISTS `allergies` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `pet_id` int(255) NOT NULL,
  `allergy` varchar(255) NOT NULL,
  `reaction` varchar(255) NOT NULL,
  `severity` varchar(55) NOT NULL,
  `day` varchar(25) NOT NULL,
  `month` varchar(25) NOT NULL,
  `year` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `allergies`
--

INSERT INTO `allergies` (`id`, `pet_id`, `allergy`, `reaction`, `severity`, `day`, `month`, `year`) VALUES
(1, 2, 'PENICILLIN', 'RASH, DIARRHEA, SWELLING OF SKIN', 'MODERATE', '10', 'NOV', '2015'),
(2, 2, 'MILK SHAMPOOS', 'SKIN IRRITATIONS', 'MILD', '9', 'NOV', '2015'),
(3, 1, 'PENICILLIN', 'SCABIES, HAIR LOSS', 'MODERATE', '6', 'NOV', '2015'),
(4, 1, 'ANTI TICK SOAD', 'HAIR LOSS', 'MILD', '6', 'NOV', '2015'),
(5, 2, 'MEFINAMIC ACID', 'VOMITTING', 'SEVERE', '25', 'NOV', '2015'),
(6, 2, 'SAMPLE', 'SAMPLE, SAMPLE, SAMPLE', 'MODERATE', '8', 'DEC', '2015');

-- --------------------------------------------------------

--
-- Table structure for table `animal_breed`
--

CREATE TABLE IF NOT EXISTS `animal_breed` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `pet_type` varchar(75) NOT NULL,
  `breed` varchar(75) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `animal_breed`
--

INSERT INTO `animal_breed` (`id`, `pet_type`, `breed`) VALUES
(1, 'CAT', 'PERSIAN'),
(2, 'CAT', 'MUNCHKIN'),
(3, 'CAT', 'PUSAKAL'),
(4, 'DOG', 'ASKAL'),
(5, 'DOG', 'DALMATIAN'),
(6, 'DOG', 'BULLDOG'),
(7, 'DOG', 'PUG'),
(8, 'GOAT', 'PHILIPPINE GOAT'),
(9, 'SNAKE', 'ANACONDA'),
(10, 'DOG', 'HOUND'),
(11, 'FISH', 'GOLDFISH');

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE IF NOT EXISTS `appointment` (
  `appointment_id` int(255) NOT NULL AUTO_INCREMENT,
  `pet_id` int(125) NOT NULL,
  `reason` varchar(9999) NOT NULL,
  `hour` int(20) NOT NULL,
  `minute` varchar(60) DEFAULT NULL,
  `meridiem` varchar(5) NOT NULL,
  `day` int(40) NOT NULL,
  `month` varchar(10) NOT NULL,
  `year` int(255) NOT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`appointment_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=40 ;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`appointment_id`, `pet_id`, `reason`, `hour`, `minute`, `meridiem`, `day`, `month`, `year`, `status`) VALUES
(39, 2, 'DEWORMING', 8, '45', 'AM', 28, 'JAN', 2016, 'DISABLED');

-- --------------------------------------------------------

--
-- Table structure for table `archive_classification`
--

CREATE TABLE IF NOT EXISTS `archive_classification` (
  `archive_class_id` int(255) NOT NULL AUTO_INCREMENT,
  `supplier_id` int(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`archive_class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `archive_supplier`
--

CREATE TABLE IF NOT EXISTS `archive_supplier` (
  `archive_Sup_id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `title_name` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `middle_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `suffix_name` varchar(255) NOT NULL,
  `contact_number` int(255) NOT NULL,
  `prk` varchar(255) NOT NULL,
  `brgy` varchar(255) NOT NULL,
  `municipal` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `province` varchar(255) NOT NULL,
  PRIMARY KEY (`archive_Sup_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `audit_trail`
--

CREATE TABLE IF NOT EXISTS `audit_trail` (
  `audit_id` int(125) NOT NULL AUTO_INCREMENT,
  `user_id` int(125) NOT NULL,
  `action_type` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `day` varchar(25) NOT NULL,
  `month` varchar(25) NOT NULL,
  `year` varchar(25) NOT NULL,
  `hour` int(25) NOT NULL,
  `minute` int(25) NOT NULL,
  `am_pm` varchar(5) NOT NULL,
  PRIMARY KEY (`audit_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=170 ;

--
-- Dumping data for table `audit_trail`
--

INSERT INTO `audit_trail` (`audit_id`, `user_id`, `action_type`, `name`, `day`, `month`, `year`, `hour`, `minute`, `am_pm`) VALUES
(1, 2, 'SAVED - AQUIRED SERVICE HOME SERVICE  BY SCRAPPY', 'PET CHART', '7', 'DEC', '2015', 9, 17, 'AM'),
(2, 2, 'SAVED - AQUIRED SERVICE CONSULTATION/PROFESSIONAL BY SCRAPPY', 'PET CHART', '7', 'DEC', '2015', 18, 1, 'PM'),
(3, 2, 'SAVED - DIAGNOSIS INFORMATION BY SCRAPPY', 'PET CHART', '7', 'DEC', '2015', 18, 2, 'PM'),
(4, 2, 'SAVED - MEDICINE GIVEN INFORMATION BY SCRAPPY', 'PET CHART', '7', 'DEC', '2015', 18, 3, 'PM'),
(5, 2, 'SAVED - PURCHASE LIST INFORMATION BY SCRAPPY', 'PET CHART', '7', 'DEC', '2015', 18, 8, 'PM'),
(6, 2, 'SAVED - PAYMENT INFORMATION BY SCRAPPY', 'PET CHART', '7', 'DEC', '2015', 18, 9, 'PM'),
(7, 2, 'SAVED - GUINTO, IKEE  PROFILE', 'CLIENT INFORMATION ENTRY MODULE', '8', 'DEC', '2015', 12, 50, 'PM'),
(8, 2, 'SAVED - GUINTO, IKEE  PROFILE', 'CLIENT INFORMATION ENTRY MODULE', '8', 'DEC', '2015', 12, 54, 'PM'),
(9, 2, 'UPDATED - GUINTO, IRVIN INTONG PROFILE', 'CLIENT INFORMATION ENTRY MODULE', '8', 'DEC', '2015', 13, 1, 'PM'),
(10, 2, 'UPDATED - GUINTO, IRVIN INTONG PROFILE', 'CLIENT INFORMATION ENTRY MODULE', '8', 'DEC', '2015', 13, 7, 'PM'),
(11, 2, 'UPDATED - GUINTO, IRVIN INTONG PROFILE', 'CLIENT INFORMATION ENTRY MODULE', '8', 'DEC', '2015', 13, 7, 'PM'),
(12, 2, 'UPDATED - GUINTO, IRVIN INTONG PROFILE', 'CLIENT INFORMATION ENTRY MODULE', '8', 'DEC', '2015', 13, 7, 'PM'),
(13, 2, 'SAVED - ALLERGY INFORMATION BY SCRAPPY', 'PET CHART', '8', 'DEC', '2015', 13, 43, 'PM'),
(14, 2, 'SAVED - PURCHASE LIST INFORMATION BY SCRAPPY', 'PET CHART', '8', 'DEC', '2015', 13, 44, 'PM'),
(15, 2, 'SAVED - AQUIRED SERVICE CONSULTATION/PROFESSIONAL BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 9, 16, 'AM'),
(16, 2, 'SAVED - PAYMENT INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 9, 23, 'AM'),
(17, 2, 'SAVED - AQUIRED SERVICE REGULAR WASH AND CARE (S) BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 9, 24, 'AM'),
(18, 2, 'SAVED - AQUIRED SERVICE REGULAR WASH AND CARE (M) BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 9, 46, 'AM'),
(19, 2, 'SAVED - AQUIRED SERVICE REGULAR WASH AND CARE (XL) BY MIMING', 'PET CHART', '10', 'DEC', '2015', 11, 28, 'AM'),
(20, 2, 'SAVED - AQUIRED SERVICE MEDICATED WASH AND CARE (S) BY PEPE', 'PET CHART', '10', 'DEC', '2015', 11, 33, 'AM'),
(21, 2, 'SAVED - AQUIRED SERVICE SPECIAL WASH AND CARE (L) BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 11, 39, 'AM'),
(22, 2, 'SAVED - DIAGNOSIS INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 11, 42, 'AM'),
(23, 2, 'SAVED - AQUIRED SERVICE SPECIAL WASH AND CARE (M) BY MIMING', 'PET CHART', '10', 'DEC', '2015', 11, 48, 'AM'),
(24, 2, 'SAVED - DIAGNOSIS INFORMATION BY MIMING', 'PET CHART', '10', 'DEC', '2015', 11, 50, 'AM'),
(25, 2, 'SAVED - AQUIRED SERVICE MEDICATED WASH AND CARE (M) BY SPRITE', 'PET CHART', '10', 'DEC', '2015', 11, 57, 'AM'),
(26, 2, 'SAVED - DIAGNOSIS INFORMATION BY SPRITE', 'PET CHART', '10', 'DEC', '2015', 11, 58, 'AM'),
(27, 2, 'SAVED - AQUIRED SERVICE REGULAR WASH AND CARE (L) BY SPRITE', 'PET CHART', '10', 'DEC', '2015', 12, 3, 'PM'),
(28, 2, 'SAVED - DIAGNOSIS INFORMATION BY SPRITE', 'PET CHART', '10', 'DEC', '2015', 12, 4, 'PM'),
(29, 2, 'SAVED - AQUIRED SERVICE 11 TO 20 KG BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 12, 10, 'PM'),
(30, 2, 'SAVED - DIAGNOSIS INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 12, 10, 'PM'),
(31, 2, 'SAVED - MEDICAL HISTORY INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 12, 10, 'PM'),
(32, 2, 'UPDATED - SMS NOTIFICATION INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 12, 31, 'PM'),
(33, 2, 'UPDATED - APPOINTMENT INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 12, 39, 'PM'),
(34, 2, 'UPDATED - SMS NOTIFICATION INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 12, 39, 'PM'),
(35, 2, 'SAVED - APPOINTMENT INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 12, 40, 'PM'),
(36, 2, 'SAVED - SMS NOTIFICATION INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 12, 40, 'PM'),
(37, 2, 'UPDATED - SMS NOTIFICATION INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 12, 40, 'PM'),
(38, 2, 'UPDATED - SMS NOTIFICATION INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 12, 40, 'PM'),
(39, 2, 'UPDATED - APPOINTMENT INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 12, 41, 'PM'),
(40, 2, 'UPDATED - SMS NOTIFICATION INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 12, 41, 'PM'),
(41, 2, 'UPDATED - SMS NOTIFICATION INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 12, 41, 'PM'),
(42, 2, 'SAVED - APPOINTMENT INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 12, 43, 'PM'),
(43, 2, 'SAVED - SMS NOTIFICATION INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 12, 43, 'PM'),
(44, 2, 'UPDATED - SMS NOTIFICATION INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 12, 43, 'PM'),
(45, 2, 'UPDATED - SMS NOTIFICATION INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 12, 43, 'PM'),
(46, 2, 'SAVED - APPOINTMENT INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 12, 50, 'PM'),
(47, 2, 'SAVED - SMS NOTIFICATION INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 12, 50, 'PM'),
(48, 2, 'UPDATED - SMS NOTIFICATION INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 12, 50, 'PM'),
(49, 2, 'UPDATED - SMS NOTIFICATION INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 12, 50, 'PM'),
(50, 2, 'SAVED - APPOINTMENT INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 13, 2, 'PM'),
(51, 2, 'SAVED - SMS NOTIFICATION INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 13, 2, 'PM'),
(52, 2, 'SAVED - APPOINTMENT INFORMATION BY SPRITE', 'PET CHART', '10', 'DEC', '2015', 13, 4, 'PM'),
(53, 2, 'SAVED - SMS NOTIFICATION INFORMATION BY SPRITE', 'PET CHART', '10', 'DEC', '2015', 13, 4, 'PM'),
(54, 2, 'UPDATED - APPOINTMENT INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 13, 8, 'PM'),
(55, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '10', 'DEC', '2015', 13, 9, 'PM'),
(56, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '10', 'DEC', '2015', 13, 9, 'PM'),
(57, 2, 'UPDATED - APPOINTMENT INFORMATION BY SCRAPPY', 'PET CHART', '10', 'DEC', '2015', 13, 15, 'PM'),
(58, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '10', 'DEC', '2015', 13, 15, 'PM'),
(59, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '10', 'DEC', '2015', 13, 15, 'PM'),
(60, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '10', 'DEC', '2015', 13, 15, 'PM'),
(61, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '10', 'DEC', '2015', 13, 15, 'PM'),
(62, 2, 'SAVED - IMMUNIZATION HISTORY INFORMATION BY SCRAPPY', 'PET CHART', '11', 'DEC', '2015', 13, 30, 'PM'),
(63, 2, 'SAVED - IMMUNIZATION HISTORY INFORMATION BY SCRAPPY', 'PET CHART', '11', 'DEC', '2015', 13, 35, 'PM'),
(64, 2, 'SAVED - APPOINTMENT INFORMATION BY SCRAPPY', 'PET CHART', '11', 'DEC', '2015', 13, 36, 'PM'),
(65, 2, 'SAVED - SMS NOTIFICATION INFORMATION BY SCRAPPY', 'PET CHART', '11', 'DEC', '2015', 13, 36, 'PM'),
(66, 2, 'SAVED - PHYSICAL EXAMINATION INFORMATION BY SCRAPPY', 'PET CHART', '12', 'DEC', '2015', 7, 50, 'AM'),
(67, 2, 'SAVED - LABORATORY EXAMINATION INFORMATION BY SCRAPPY', 'PET CHART', '12', 'DEC', '2015', 7, 50, 'AM'),
(68, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '13', 'DEC', '2015', 17, 32, 'PM'),
(69, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '13', 'DEC', '2015', 17, 32, 'PM'),
(70, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '13', 'DEC', '2015', 17, 32, 'PM'),
(71, 2, 'SAVED - MEDICINE GIVEN INFORMATION BY SCRAPPY', 'PET CHART', '14', 'DEC', '2015', 18, 50, 'PM'),
(72, 2, 'SAVED - PHYSICAL EXAMINATION INFORMATION BY PEPE', 'PET CHART', '14', 'DEC', '2015', 19, 14, 'PM'),
(73, 2, 'SAVED - LABORATORY EXAMINATION INFORMATION BY PEPE', 'PET CHART', '14', 'DEC', '2015', 19, 14, 'PM'),
(74, 2, 'SAVED - APPOINTMENT INFORMATION BY SCRAPPY', 'PET CHART', '15', 'DEC', '2015', 7, 45, 'AM'),
(75, 2, 'SAVED - SMS NOTIFICATION INFORMATION BY SCRAPPY', 'PET CHART', '15', 'DEC', '2015', 7, 45, 'AM'),
(76, 2, 'SAVED - IMMUNIZATION HISTORY INFORMATION BY SCRAPPY', 'PET CHART', '16', 'DEC', '2015', 10, 16, 'AM'),
(77, 2, 'UPDATED - APPOINTMENT INFORMATION BY SCRAPPY', 'PET CHART', '16', 'DEC', '2015', 12, 56, 'PM'),
(78, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '16', 'DEC', '2015', 12, 57, 'PM'),
(79, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '16', 'DEC', '2015', 12, 57, 'PM'),
(80, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '16', 'DEC', '2015', 12, 57, 'PM'),
(81, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '16', 'DEC', '2015', 12, 57, 'PM'),
(82, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '16', 'DEC', '2015', 12, 57, 'PM'),
(83, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '16', 'DEC', '2015', 12, 57, 'PM'),
(84, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '16', 'DEC', '2015', 12, 57, 'PM'),
(85, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '16', 'DEC', '2015', 12, 57, 'PM'),
(86, 2, 'SAVED - APPOINTMENT INFORMATION BY SCRAPPY', 'PET CHART', '16', 'DEC', '2015', 12, 59, 'PM'),
(87, 2, 'SAVED - SMS NOTIFICATION INFORMATION BY SCRAPPY', 'PET CHART', '16', 'DEC', '2015', 12, 59, 'PM'),
(88, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '16', 'DEC', '2015', 12, 59, 'PM'),
(89, 2, 'UPDATED - APPOINTMENT INFORMATION BY SCRAPPY', 'PET CHART', '16', 'DEC', '2015', 13, 0, 'PM'),
(90, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '16', 'DEC', '2015', 13, 0, 'PM'),
(91, 2, 'UPDATED - APPOINTMENT INFORMATION BY SCRAPPY', 'PET CHART', '16', 'DEC', '2015', 13, 2, 'PM'),
(92, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '16', 'DEC', '2015', 13, 2, 'PM'),
(93, 2, 'UPDATED - APPOINTMENT INFORMATION BY SCRAPPY', 'PET CHART', '16', 'DEC', '2015', 13, 6, 'PM'),
(94, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '16', 'DEC', '2015', 13, 6, 'PM'),
(95, 2, 'SAVED - APPOINTMENT INFORMATION BY SCRAPPY', 'PET CHART', '16', 'DEC', '2015', 13, 15, 'PM'),
(96, 2, 'SAVED - SMS NOTIFICATION INFORMATION BY SCRAPPY', 'PET CHART', '16', 'DEC', '2015', 13, 15, 'PM'),
(97, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '16', 'DEC', '2015', 13, 15, 'PM'),
(98, 2, 'UPDATED - APPOINTMENT INFORMATION BY SCRAPPY', 'PET CHART', '16', 'DEC', '2015', 13, 15, 'PM'),
(99, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '16', 'DEC', '2015', 13, 15, 'PM'),
(100, 2, 'UPDATED - APPOINTMENT INFORMATION BY SCRAPPY', 'PET CHART', '16', 'DEC', '2015', 13, 15, 'PM'),
(101, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '16', 'DEC', '2015', 13, 15, 'PM'),
(102, 2, 'UPDATED - APPOINTMENT INFORMATION BY SCRAPPY', 'PET CHART', '16', 'DEC', '2015', 13, 16, 'PM'),
(103, 2, 'UPDATED - SMS NOTIFICATION', 'PET CHART', '16', 'DEC', '2015', 13, 16, 'PM'),
(104, 2, 'UPDATED - GUINTO, IRVIN INTONG PROFILE', 'CLIENT INFORMATION ENTRY MODULE', '20', 'DEC', '2015', 14, 38, 'PM'),
(105, 2, 'UPDATED - GUINTO, IRVIN INTONG PROFILE', 'CLIENT INFORMATION ENTRY MODULE', '20', 'DEC', '2015', 14, 40, 'PM'),
(106, 2, 'SAVED - AQUIRED SERVICE CONSULTATION/PROFESSIONAL BY SCRAPPY', 'PET CHART', '20', 'DEC', '2015', 22, 4, 'PM'),
(107, 2, 'CANCELLED - AQUIRED SERVICE CESARIAN SECTION (DOG/SMALL) BY SCRAPPY', 'PET CHART', '20', 'DEC', '2015', 22, 9, 'PM'),
(108, 2, 'CANCELLED - AQUIRED SERVICE HOME SERVICE  BY SCRAPPY', 'PET CHART', '20', 'DEC', '2015', 22, 9, 'PM'),
(109, 2, 'CANCELLED - AQUIRED SERVICE CONSULTATION/PROFESSIONAL BY SCRAPPY', 'PET CHART', '20', 'DEC', '2015', 22, 9, 'PM'),
(110, 2, 'CANCELLED - AQUIRED SERVICE CONSULTATION/PROFESSIONAL BY SCRAPPY', 'PET CHART', '20', 'DEC', '2015', 22, 9, 'PM'),
(111, 2, 'CANCELLED - AQUIRED SERVICE REGULAR WASH AND CARE (S) BY SCRAPPY', 'PET CHART', '20', 'DEC', '2015', 22, 9, 'PM'),
(112, 2, 'CANCELLED - AQUIRED SERVICE REGULAR WASH AND CARE (M) BY SCRAPPY', 'PET CHART', '20', 'DEC', '2015', 22, 9, 'PM'),
(113, 2, 'CANCELLED - AQUIRED SERVICE SPECIAL WASH AND CARE (L) BY SCRAPPY', 'PET CHART', '20', 'DEC', '2015', 22, 9, 'PM'),
(114, 2, 'CANCELLED - AQUIRED SERVICE 11 TO 20 KG BY SCRAPPY', 'PET CHART', '20', 'DEC', '2015', 22, 9, 'PM'),
(115, 2, 'CANCELLED - AQUIRED SERVICE CONSULTATION/PROFESSIONAL BY SCRAPPY', 'PET CHART', '20', 'DEC', '2015', 22, 9, 'PM'),
(116, 2, 'SAVED - AQUIRED SERVICE CONSULTATION/PROFESSIONAL BY SCRAPPY', 'PET CHART', '20', 'DEC', '2015', 22, 9, 'PM'),
(117, 2, 'CANCELLED - AQUIRED SERVICE CONSULTATION/PROFESSIONAL BY SCRAPPY', 'PET CHART', '20', 'DEC', '2015', 22, 18, 'PM'),
(118, 2, 'SAVED - AQUIRED SERVICE CONSULTATION/PROFESSIONAL BY SCRAPPY', 'PET CHART', '20', 'DEC', '2015', 22, 18, 'PM'),
(119, 2, 'CANCELLED - AQUIRED SERVICE CONSULTATION/PROFESSIONAL BY SCRAPPY', 'PET CHART', '20', 'DEC', '2015', 22, 24, 'PM'),
(120, 2, 'SAVED - AQUIRED SERVICE CONSULTATION/PROFESSIONAL BY SCRAPPY', 'PET CHART', '20', 'DEC', '2015', 22, 24, 'PM'),
(121, 2, 'CANCELLED - AQUIRED SERVICE CONSULTATION/PROFESSIONAL BY SCRAPPY', 'PET CHART', '20', 'DEC', '2015', 22, 27, 'PM'),
(122, 2, 'SAVED - AQUIRED SERVICE CONSULTATION/PROFESSIONAL BY SCRAPPY', 'PET CHART', '20', 'DEC', '2015', 22, 27, 'PM'),
(123, 2, 'CANCELLED - AQUIRED SERVICE CONSULTATION/PROFESSIONAL BY SCRAPPY', 'PET CHART', '20', 'DEC', '2015', 22, 30, 'PM'),
(124, 2, 'SAVED - AQUIRED SERVICE CONSULTATION/PROFESSIONAL BY SCRAPPY', 'PET CHART', '20', 'DEC', '2015', 22, 30, 'PM'),
(125, 2, 'UPDATED - GUINTO, IRVIN INTONG PROFILE', 'CLIENT INFORMATION ENTRY MODULE', '21', 'DEC', '2015', 7, 15, 'AM'),
(126, 2, 'UPDATED - SCRAPPY''S INFORMATION', 'CLIENT INFORMATION ENTRY MODULE', '21', 'DEC', '2015', 11, 30, 'AM'),
(127, 2, 'SAVED - TOYTOY''S INFORMATION', 'CLIENT INFORMATION ENTRY MODULE', '21', 'DEC', '2015', 12, 45, 'PM'),
(128, 2, 'SAVED - TOYTOY''S INFORMATION', 'CLIENT INFORMATION ENTRY MODULE', '21', 'DEC', '2015', 12, 51, 'PM'),
(129, 1, 'SAVED - AQUIRED SERVICE HOME SERVICE  BY SCRAPPY', 'PET CHART', '22', 'DEC', '2015', 1, 55, 'AM'),
(130, 2, 'SAVED - PURCHASE LIST INFORMATION BY SCRAPPY', 'PET CHART', '23', 'DEC', '2015', 9, 45, 'AM'),
(131, 2, 'SAVED - PURCHASE LIST INFORMATION BY SCRAPPY', 'PET CHART', '23', 'DEC', '2015', 9, 52, 'AM'),
(132, 2, 'SAVED - PHYSICAL EXAMINATION INFORMATION BY SCRAPPY', 'PET CHART', '23', 'DEC', '2015', 10, 45, 'PM'),
(133, 2, 'SAVED - PHYSICAL EXAMINATION INFORMATION BY SCRAPPY', 'PET CHART', '23', 'DEC', '2015', 10, 46, 'PM'),
(134, 2, 'SAVED - PHYSICAL EXAMINATION INFORMATION BY SCRAPPY', 'PET CHART', '23', 'DEC', '2015', 10, 47, 'PM'),
(135, 2, 'SAVED - PHYSICAL EXAMINATION INFORMATION BY SCRAPPY', 'PET CHART', '26', 'DEC', '2015', 5, 33, 'PM'),
(136, 2, 'SAVED - LABORATORY EXAMINATION INFORMATION BY SCRAPPY', 'PET CHART', '26', 'DEC', '2015', 5, 34, 'PM'),
(137, 2, 'SAVED - AQUIRED SERVICE MEDICATED WASH AND CARE (L) BY SCRAPPY', 'PET CHART', '26', 'DEC', '2015', 8, 4, 'PM'),
(138, 2, 'SAVED - AQUIRED SERVICE SPECIAL WASH AND CARE (L) BY SCRAPPY', 'PET CHART', '26', 'DEC', '2015', 8, 5, 'PM'),
(139, 2, 'CANCELLED - AQUIRED SERVICE CONSULTATION/PROFESSIONAL BY SCRAPPY', 'PET CHART', '26', 'DEC', '2015', 8, 7, 'PM'),
(140, 2, 'CANCELLED - AQUIRED SERVICE HOME SERVICE  BY SCRAPPY', 'PET CHART', '26', 'DEC', '2015', 8, 7, 'PM'),
(141, 2, 'CANCELLED - AQUIRED SERVICE MEDICATED WASH AND CARE (L) BY SCRAPPY', 'PET CHART', '26', 'DEC', '2015', 8, 7, 'PM'),
(142, 2, 'CANCELLED - AQUIRED SERVICE SPECIAL WASH AND CARE (L) BY SCRAPPY', 'PET CHART', '26', 'DEC', '2015', 8, 7, 'PM'),
(143, 2, 'SAVED - AQUIRED SERVICE SPECIAL WASH AND CARE (L) BY SCRAPPY', 'PET CHART', '26', 'DEC', '2015', 8, 7, 'PM'),
(144, 2, 'SAVED - PRESCRIPTION INFORMATION BY SCRAPPY', 'PET CHART', '26', 'DEC', '2015', 8, 10, 'PM'),
(145, 2, 'SAVED - MEDICAL HISTORY INFORMATION BY SCRAPPY', 'PET CHART', '26', 'DEC', '2015', 8, 10, 'PM'),
(146, 2, 'SAVED - PHYSICAL EXAMINATION INFORMATION BY SCRAPPY', 'PET CHART', '4', 'JAN', '2016', 5, 29, 'PM'),
(147, 2, 'SAVED - AQUIRED SERVICE REGULAR WASH AND CARE (M) BY SCRAPPY', 'PET CHART', '5', 'JAN', '2016', 10, 6, 'AM'),
(148, 2, 'CANCELLED - AQUIRED SERVICE REGULAR WASH AND CARE (M) BY SCRAPPY', 'PET CHART', '5', 'JAN', '2016', 10, 6, 'AM'),
(149, 2, 'SAVED - AQUIRED SERVICE EAR CROPPING BY SCRAPPY', 'PET CHART', '5', 'JAN', '2016', 10, 29, 'PM'),
(150, 2, 'SAVED - MEDICAL HISTORY INFORMATION BY SCRAPPY', 'PET CHART', '5', 'JAN', '2016', 10, 31, 'PM'),
(151, 2, 'CONFIRMED - ORDER ITEM # 160106135132 BY VETERINARIAN', 'IVENTORY', '7', 'JAN', '2016', 7, 1, 'AM'),
(152, 2, 'CONFIRMED - ORDER ITEM # 160107070553 BY VETERINARIAN', 'IVENTORY', '7', 'JAN', '2016', 7, 6, 'AM'),
(153, 2, 'CONFIRMED - ORDER ITEM # 160107070553 BY VETERINARIAN', 'IVENTORY', '7', 'JAN', '2016', 7, 6, 'AM'),
(154, 2, 'UNDONE - ORDER ITEM # NULL BY VETERINARIAN', 'IVENTORY', '7', 'JAN', '2016', 8, 23, 'AM'),
(155, 2, 'UNDONE - ORDER ITEM # NULL BY VETERINARIAN', 'IVENTORY', '7', 'JAN', '2016', 8, 23, 'AM'),
(156, 2, 'CONFIRMED - ORDER ITEM # 160107070553 BY VETERINARIAN', 'IVENTORY', '7', 'JAN', '2016', 8, 23, 'AM'),
(157, 2, 'CONFIRMED - ORDER ITEM # 160107070553 BY VETERINARIAN', 'IVENTORY', '7', 'JAN', '2016', 8, 23, 'AM'),
(158, 2, 'UNDONE - ORDER ITEM # 160107070553 BY VETERINARIAN', 'IVENTORY', '7', 'JAN', '2016', 8, 23, 'AM'),
(159, 2, 'UNDONE - ORDER ITEM # 160107070553 BY VETERINARIAN', 'IVENTORY', '7', 'JAN', '2016', 8, 23, 'AM'),
(160, 2, 'CONFIRMED - ORDER ITEM # [C@121B9A0 BY VETERINARIAN', 'IVENTORY', '7', 'JAN', '2016', 8, 29, 'AM'),
(161, 2, 'UNDONE - ORDER ITEM # [C@121B9A0 BY VETERINARIAN', 'IVENTORY', '7', 'JAN', '2016', 8, 29, 'AM'),
(162, 2, 'CONFIRMED - ORDER ITEM # 160107070553 BY VETERINARIAN', 'IVENTORY', '7', 'JAN', '2016', 8, 29, 'AM'),
(163, 2, 'CONFIRMED - ORDER ITEM # 160107070553 BY VETERINARIAN', 'IVENTORY', '7', 'JAN', '2016', 8, 29, 'AM'),
(164, 2, 'UNDONE - ORDER ITEM # 160107070553 BY VETERINARIAN', 'IVENTORY', '7', 'JAN', '2016', 8, 29, 'AM'),
(165, 2, 'UNDONE - ORDER ITEM # 160107070553 BY VETERINARIAN', 'IVENTORY', '7', 'JAN', '2016', 8, 29, 'AM'),
(166, 2, 'CONFIRMED - ORDER ITEM # [C@121B9A0 BY VETERINARIAN', 'IVENTORY', '7', 'JAN', '2016', 8, 33, 'AM'),
(167, 2, 'UNDONE - ORDER ITEM # [C@121B9A0 BY VETERINARIAN', 'IVENTORY', '7', 'JAN', '2016', 8, 33, 'AM'),
(168, 2, 'CONFIRMED - ORDER ITEM # 160107070553 BY VETERINARIAN', 'IVENTORY', '7', 'JAN', '2016', 8, 35, 'AM'),
(169, 2, 'CONFIRMED - ORDER ITEM # 160106135132 BY VETERINARIAN', 'IVENTORY', '7', 'JAN', '2016', 8, 35, 'AM');

-- --------------------------------------------------------

--
-- Table structure for table `auto_suggest_diagnosis_data`
--

CREATE TABLE IF NOT EXISTS `auto_suggest_diagnosis_data` (
  `sdc_id` int(125) NOT NULL AUTO_INCREMENT,
  `symptom` varchar(500) NOT NULL,
  `diagnosis` varchar(9999) DEFAULT NULL,
  PRIMARY KEY (`sdc_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=100 ;

--
-- Dumping data for table `auto_suggest_diagnosis_data`
--

INSERT INTO `auto_suggest_diagnosis_data` (`sdc_id`, `symptom`, `diagnosis`) VALUES
(68, 'HIGH TEMP', 'FEVER'),
(69, 'EXCESSIVE SALIVATION', 'RABIES'),
(70, 'DILATED EYES', 'RABIES'),
(71, 'AGRESSIVENESS', 'RABIES'),
(72, 'SNEEZING', 'CANCER'),
(73, 'HEADACHE', 'CANCER'),
(74, 'AGRESSIVENESS', 'PREGNANT'),
(75, 'VOMITTING', 'PARASETISM'),
(76, 'VOMITTING', 'HYPERACIDITY'),
(80, 'BLOODY GUMS', 'TOOTHACHE'),
(81, 'SALIVATION', 'TOOTHACHE'),
(82, 'NANA', 'TOOTHACHE'),
(83, 'SWOLLEN EYES', 'SOAR EYES'),
(84, 'RED EYES', 'SOAR EYES'),
(85, 'TEARY EYES', 'SOAR EYES'),
(86, 'RED PATCH', 'SKIN DISEASE'),
(87, 'SPOTS', 'SKIN DISEASE'),
(88, 'PIMPLES', 'SKIN DISEASE'),
(89, 'ITCHING', 'SKIN DISEASE'),
(90, 'HAIR LOSS', 'SKIN DISEASE'),
(91, 'SAMPLE DIAGNOSIS', 'SAMPLE'),
(92, 'SAMPLE DIAGNOSIS', 'SAMPLE SYMP2'),
(93, 'SAMPLE DIAGNOSIS', 'SAMPLE SYMP3'),
(94, 'SAMPLE DIAGNOSIS', 'SAMPLE SYMP4'),
(95, 'SAMPLE DIAGNOSIS', 'SAMPLE SYMP5'),
(96, 'SAMPLE DIAGNOSIS', 'SAMPLE SYMP6'),
(97, 'QWEQWE', 'SAMPLE DIAGNOSIS'),
(98, 'WERWER', 'SAMPLE DIAGNOSIS'),
(99, 'WWRRE', 'SAMPLE DIAGNOSIS');

-- --------------------------------------------------------

--
-- Table structure for table `billing`
--

CREATE TABLE IF NOT EXISTS `billing` (
  `billing_id` int(255) NOT NULL AUTO_INCREMENT,
  `pet_id` int(125) NOT NULL,
  `total_charged_item` varchar(255) NOT NULL,
  `total_services_acquired` varchar(255) NOT NULL,
  `bill` varchar(255) NOT NULL,
  `day` int(40) NOT NULL,
  `month` varchar(5) NOT NULL,
  `year` int(50) NOT NULL,
  PRIMARY KEY (`billing_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Dumping data for table `billing`
--

INSERT INTO `billing` (`billing_id`, `pet_id`, `total_charged_item`, `total_services_acquired`, `bill`, `day`, `month`, `year`) VALUES
(20, 7, 'PHP 706.00', 'PHP 0.00', 'PHP 706.00', 5, 'DEC', 2015),
(21, 2, 'PHP 144.00', 'PHP 000.00', 'PHP 144.00', 5, 'DEC', 2015),
(22, 2, 'PHP 000.00', 'PHP 5,200.00', 'PHP 5,200.00', 6, 'DEC', 2015),
(23, 2, 'PHP 175.00', 'PHP 600.00', 'PHP 775.00', 7, 'DEC', 2015),
(24, 2, 'PHP 75.00', 'PHP 400.00', 'PHP 475.00', 10, 'DEC', 2015);

-- --------------------------------------------------------

--
-- Table structure for table `checkup_history`
--

CREATE TABLE IF NOT EXISTS `checkup_history` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `pet_id` int(255) NOT NULL,
  `syptoms` varchar(8888) DEFAULT NULL,
  `diagnosis` varchar(8888) DEFAULT NULL,
  `medicine_vaccine_given` varchar(8888) DEFAULT NULL,
  `day` int(40) NOT NULL,
  `month` varchar(15) NOT NULL,
  `year` int(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `checkup_history`
--

INSERT INTO `checkup_history` (`id`, `pet_id`, `syptoms`, `diagnosis`, `medicine_vaccine_given`, `day`, `month`, `year`) VALUES
(11, 46, 'HIGH TEMP, VOMITTING', 'HYPERACIDITY\n', 'PAPPI FLAVET', 3, 'OCT', 2015),
(12, 1, 'VOMITTING, HIGH TEMP', 'HYPERACIDITY\n', 'PAPPI POXY', 3, 'OCT', 2015),
(13, 1, 'RED EYES, TEARY EYES', 'SOAR EYES\n', '', 8, 'OCT', 2015),
(14, 3, 'RED EYES', 'SOAR EYES\n', '', 8, 'OCT', 2015),
(15, 1, 'NANA', 'TOOTHACHE ', '', 10, 'OCT', 2015);

-- --------------------------------------------------------

--
-- Table structure for table `classification1`
--

CREATE TABLE IF NOT EXISTS `classification1` (
  `classification_id` int(255) NOT NULL AUTO_INCREMENT,
  `supplier_id` int(255) NOT NULL,
  `name` varchar(125) NOT NULL,
  `used_for` varchar(9999) DEFAULT NULL,
  PRIMARY KEY (`classification_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=37 ;

--
-- Dumping data for table `classification1`
--

INSERT INTO `classification1` (`classification_id`, `supplier_id`, `name`, `used_for`) VALUES
(16, 12, 'SYRUP', 'CHECKUP'),
(17, 12, 'TABLET-PET TABLET', 'CHECKUP'),
(18, 12, 'SPRAY', 'GROOMING'),
(19, 12, 'SHAMPOO', 'GROOMING'),
(20, 12, 'SOAP', 'GROOMING'),
(21, 13, 'TABLET', 'CHECKUP'),
(22, 13, 'EYEDROPS', 'CHECKUP'),
(23, 13, 'SYRUP', 'CHECKUP'),
(24, 13, 'DOG FOOD-CAN', 'FOODS'),
(25, 14, 'SHAMPOO', 'GROOMING'),
(26, 14, 'POWDER', 'GROOMING'),
(27, 15, 'EAR CLEANER', 'GROOMING'),
(28, 15, 'CAPSULE', 'CHECKUP'),
(29, 15, 'TABLET', 'CHECKUP'),
(30, 15, 'SACHET', 'FOODS-CHECKUP-GROOMING'),
(31, 16, 'COLOGNE', 'GROOMING'),
(32, 17, 'VACCINES', 'CHECKUP'),
(33, 18, 'SOAP', 'GROOMING'),
(34, 12, 'SAPLE CLASS', NULL),
(35, 21, 'SAMPLE CLASSIFICATION', NULL),
(36, 12, 'SAMPLE16', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `daily_data_diagnosis_medicine_given`
--

CREATE TABLE IF NOT EXISTS `daily_data_diagnosis_medicine_given` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `pet_id` int(255) NOT NULL,
  `medicine_given` varchar(255) NOT NULL,
  `volume` varchar(255) NOT NULL,
  `dose_unit` varchar(255) NOT NULL,
  `daily_usage` varchar(255) NOT NULL,
  `selected_qty` varchar(255) NOT NULL,
  `day` int(40) NOT NULL,
  `month` varchar(40) NOT NULL,
  `year` int(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `daily_data_diagnosis_symptoms`
--

CREATE TABLE IF NOT EXISTS `daily_data_diagnosis_symptoms` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `pet_id` int(255) NOT NULL,
  `diagnosis` varchar(80) NOT NULL,
  `prognosis` varchar(255) DEFAULT NULL,
  `symtoms_given` varchar(900) NOT NULL,
  `day` int(40) NOT NULL,
  `month` varchar(40) NOT NULL,
  `year` int(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `daily_data_diagnosis_symptoms`
--

INSERT INTO `daily_data_diagnosis_symptoms` (`id`, `pet_id`, `diagnosis`, `prognosis`, `symtoms_given`, `day`, `month`, `year`) VALUES
(9, 1, '', '(FAVORABLE) ALLERGIC DERMATITIS', 'VOMITTING, HAIRLOSS, HAIR LOSS, SPOTS, AGRESSIVENESS', 10, 'DEC', 2015),
(10, 2, '(DEFINITIVE) ALLERGIC DERMATITIS', '', 'VOMITTING, SPOTS, HAIR LOSS', 10, 'DEC', 2015);

-- --------------------------------------------------------

--
-- Table structure for table `diagnosis_data`
--

CREATE TABLE IF NOT EXISTS `diagnosis_data` (
  `diagnosis_id` int(125) NOT NULL AUTO_INCREMENT,
  `phex_id` int(125) NOT NULL,
  `day` varchar(25) NOT NULL,
  `month` varchar(25) NOT NULL,
  `year` varchar(25) NOT NULL,
  PRIMARY KEY (`diagnosis_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `examination_pr`
--

CREATE TABLE IF NOT EXISTS `examination_pr` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `pet_id` int(255) NOT NULL,
  `exam_number` varchar(9999) NOT NULL,
  `name` varchar(255) NOT NULL,
  `day` int(40) NOT NULL,
  `month` varchar(15) NOT NULL,
  `year` int(255) NOT NULL,
  `time_consumed` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `consumed_duration` varchar(9999) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `examination_pr`
--

INSERT INTO `examination_pr` (`id`, `pet_id`, `exam_number`, `name`, `day`, `month`, `year`, `time_consumed`, `status`, `consumed_duration`) VALUES
(1, 2, '151223224748', 'CONFINEMENT', 23, 'DEC', 2015, '12:02:03', 'RELEASED', '3 day(s) 6 hour(s) 32 minute(s) 18 second(s) '),
(2, 2, '151226173357', 'CONFINEMENT', 26, 'DEC', 2015, '17:33:57', 'RELEASED', '9 day(s), 15 hour(s), 54 minute(s), 44 second(s) '),
(3, 2, '160104172916', 'CONFINEMENT', 4, 'JAN', 2016, '17:29:16', 'ONGOING', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `immunization_history`
--

CREATE TABLE IF NOT EXISTS `immunization_history` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `pet_id` int(255) NOT NULL,
  `vaccine` varchar(800) DEFAULT NULL,
  `deworming` varchar(500) DEFAULT NULL,
  `age` varchar(30) DEFAULT NULL,
  `day` int(50) NOT NULL,
  `month` varchar(120) NOT NULL,
  `year` int(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;

--
-- Dumping data for table `immunization_history`
--

INSERT INTO `immunization_history` (`id`, `pet_id`, `vaccine`, `deworming`, `age`, `day`, `month`, `year`) VALUES
(17, 2, '5 IN 1', '', '8 WEEK(S)', 6, 'JAN', 2013),
(18, 2, 'CANINE PARVOVIRUS (CPV)', 'PARASITE, ROUNDWORM, FLEAS', '12 WEEK(S)', 12, 'JAN', 2013),
(19, 2, 'RABIES (R)', 'FLEAS', '16 WEEK(S)', 18, 'JAN', 2013),
(20, 2, 'BOOSTER VACCINE', '', '1 YEARS(S)', 18, 'JAN', 2014),
(21, 1, '5 IN 1', '', '2 Week(s)', 1, 'NOV', 2013),
(22, 1, 'CANINE PARVOVIRUS (CPV)', 'ROUNDWORM', '2 Month(s)', 1, 'FEB', 2014),
(23, 7, '5 IN 1', '', '1 Years(s)', 4, 'DEC', 2015),
(25, 2, 'BORDELLA VACCINE', '', '2 Years(s)', 31, 'DEC', 2015),
(26, 2, NULL, 'Benzimidazoles', '12 Month(s)', 14, 'DEC', 2011);

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE IF NOT EXISTS `inventory` (
  `inventory_id` int(255) NOT NULL AUTO_INCREMENT,
  `receive_id` int(255) NOT NULL,
  `total_remaining_qty` int(255) NOT NULL,
  `day` int(255) NOT NULL,
  `month` varchar(255) NOT NULL,
  `year` int(255) NOT NULL,
  PRIMARY KEY (`inventory_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `iprice_history`
--

CREATE TABLE IF NOT EXISTS `iprice_history` (
  `iprice_id` int(255) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(255) NOT NULL,
  `inc_per` int(255) NOT NULL,
  `prevsupPrice` int(255) NOT NULL,
  `prevSalePrice` int(255) NOT NULL,
  `newsupp_price` varchar(255) NOT NULL,
  `newsale_price` int(255) NOT NULL,
  `dayUpdated` int(255) NOT NULL,
  `moUpdated` varchar(255) NOT NULL,
  `yrUpdated` int(255) NOT NULL,
  PRIMARY KEY (`iprice_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `iprice_history`
--

INSERT INTO `iprice_history` (`iprice_id`, `item_name`, `inc_per`, `prevsupPrice`, `prevSalePrice`, `newsupp_price`, `newsale_price`, `dayUpdated`, `moUpdated`, `yrUpdated`) VALUES
(1, '0', 90, 0, 0, '0', 152, 23, 'Dec', 2015),
(2, '0', 67, 0, 0, '', 668, 23, 'Dec', 2015),
(3, '0', 12, 400, 668, '400', 448, 23, 'Dec', 2015),
(4, '0', 50, 900, 1080, '1000', 1500, 23, 'Dec', 2015),
(5, 'PAPPI POXY', 0, 0, 0, '', 0, 0, '', 0),
(6, 'PAPPI FLAVET', 0, 0, 0, '', 0, 0, '', 0),
(7, 'COATSHINE', 99, 900, 1710, '900', 1791, 4, 'Jan', 2016),
(8, 'LC VIT SYRUP', 99, 90, 179, '90', 179, 4, 'Jan', 2016),
(9, 'PAPPI FLAVET', 2, 900, 1710, '900', 918, 5, 'Jan', 2016);

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE IF NOT EXISTS `item` (
  `item_id` int(255) NOT NULL AUTO_INCREMENT,
  `supplier_id` int(255) NOT NULL,
  `classification_id` int(255) NOT NULL,
  `name` varchar(125) NOT NULL,
  `CliSale` varchar(255) DEFAULT NULL,
  `forCheck` varchar(255) DEFAULT NULL,
  `BigSmall` varchar(255) DEFAULT NULL,
  `qtyName` varchar(255) DEFAULT NULL,
  `volume` varchar(255) DEFAULT NULL,
  `numof` varchar(255) DEFAULT NULL,
  `quantity` varchar(255) DEFAULT NULL,
  `manufacturing_day` varchar(255) DEFAULT NULL,
  `manufacturing_month` varchar(10) DEFAULT NULL,
  `manufacturing_year` int(255) DEFAULT NULL,
  `expiration_day` varchar(255) NOT NULL,
  `expiration_month` varchar(10) NOT NULL,
  `expiration_year` int(255) NOT NULL,
  `pet_type` varchar(9999) NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=82 ;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`item_id`, `supplier_id`, `classification_id`, `name`, `CliSale`, `forCheck`, `BigSmall`, `qtyName`, `volume`, `numof`, `quantity`, `manufacturing_day`, `manufacturing_month`, `manufacturing_year`, `expiration_day`, `expiration_month`, `expiration_year`, `pet_type`) VALUES
(47, 12, 23, 'PAPPI POXY', '', '', '', '1 Sachet', '25 mg', '', '68', '1', '', 0, '1', '', 0, 'DOG-CATS'),
(48, 12, 23, 'PAPPI FLAVET', 'Sale', '', '', ' ', ' ', '', '45', '0', 'JAN', 2015, '0', 'JUN', 2017, ''),
(49, 12, 23, 'ENMALAC', 'Sale', '', 'Box', '14 Bottle', '14 cc', '14', '68', '0', 'JUL', 2015, '0', 'JUL', 2016, 'pig'),
(50, 12, 23, 'ENER-G', ' ', '', 'Box', '56 Bottle', '56 mg', '56', '68', '0', '', 0, '0', '', 0, ''),
(51, 12, 23, 'LIV-WELL', '', '', '', ' ', ' ', '', '68', '0', '', 0, '0', '', 0, ''),
(52, 12, 17, 'PAPI CALCIUM', '', '', 'Box', '9 Bottle', '9 cc', '9', '68', '0', '', 0, '0', '', 0, ''),
(53, 12, 18, 'IOZIN SPRAY', 'Sale', '', 'Box', '35 Bottle', '35 cc', '35', '68', '0', 'JUN', 2014, '0', 'JUN', 2016, 'DOG'),
(54, 12, 25, 'AMITRAZ SHAMPOO', '', '', 'Sack', '67 Bottle', '67 cc', '67', '68', '0', '', 0, '0', '', 0, ''),
(55, 12, 17, 'ENROFLOXACIN', '', '', 'Sack', '89 Bottle', '89 cc', '89', '68', '0', '', 0, '0', '', 0, ''),
(56, 12, 20, 'PAPI COAT MAINTENANCE SOAP', '', '', 'Box', '9 Bottle', '9 mm', '9', '68', '0', '', 0, '0', '', 0, ''),
(57, 12, 18, 'VENOMA', '', '', '', '12 Bottle', '12 cc', '', '68', '0', '', 0, '0', '', 0, ''),
(58, 13, 29, 'LC DOX', '', '', NULL, NULL, NULL, NULL, '68', '0', '', 0, '0', '', 0, ''),
(59, 13, 22, 'GENTIN DROPS', '', '', 'Box', '45 ', '45 ', '45', '68', '0', '', 0, '0', '', 0, ''),
(60, 13, 23, 'COATSHINE', '', '', 'Box', '45 Sachet', '45 cc', '45', '68', '0', '', 0, '0', '', 0, ''),
(61, 13, 23, 'LC VIT SYRUP', '', '', '', ' ', ' ', '', '68', '0', '', 0, '0', '', 0, ''),
(62, 13, 24, 'PYRAMID HILL', 'Sale', '', 'Sack', '45 Sachet', '1 mg', '1', '68', '0', 'JAN', 2014, '0', 'JAN', 2017, 'DOG'),
(63, 14, 25, 'MYCOCIDE', '', '', NULL, NULL, NULL, NULL, '68', '0', '', 0, '0', '', 0, ''),
(64, 14, 26, 'MONDEX BIG 340', '', '', 'Sack', '12 Bottle', '1 cc', '2', '68', '0', '', 0, '0', '', 0, ''),
(65, 14, 26, 'MONDEX SMALL 100', '', '', 'Box', '12 Bottle', '3 mg', '12', '68', '0', '', 0, '0', '', 0, ''),
(66, 15, 27, 'EAR CLEANSE', '', '', 'Sack', '12 Bottle', '12 cc', '12', '68', '0', '', 0, '0', '', 0, ''),
(67, 15, 28, 'PRO-BIOTICS', '', '', NULL, NULL, NULL, NULL, '68', '0', '', 0, '0', '', 0, ''),
(70, 15, 29, 'SHED DEFENSE', '', '', '', '12 Bottle', '13 cc', '', '68', '0', '', 0, '0', '', 0, ''),
(71, 15, 30, 'FOOD TRANSITION', '', '', 'sack', '2 cc', ' 1 bottle', '3', '68', '0', '', 0, '0', '', 0, ''),
(72, 15, 30, 'TICK & FLEA', '', '', NULL, NULL, NULL, NULL, '68', '0', '', 0, '0', '', 0, ''),
(73, 16, 30, 'CUDDLY COLOGNE', '', '', NULL, NULL, NULL, NULL, '68', '0', '', 0, '0', '', 0, ''),
(74, 13, 23, 'COLIMOXYN', 'Clinical', '', '', '2 Bottle', '3 cc', '', '68', '0', '', 0, '0', '', 0, ''),
(78, 12, 23, 'CAT 45', 'Clinical', 'CHECKUP', 'Box', '1 Bottle', '12 cc', '12', '68', '0', 'NOV', 2015, '0', 'Dec', 2019, 'cat'),
(79, 18, 33, 'BATHING', 'Clinical', 'GROOMING', 'Sack', '17 Bottle', '17 ml', '17', '68', '16', 'Dec', 2015, '16', 'Dec', 2016, 'dog'),
(80, 18, 33, 'BATH', 'Sale', '', NULL, NULL, NULL, NULL, '68', '12', 'Dec', 2015, '18', 'Jan', 2016, 'dog'),
(81, 12, 36, 'SAMPLE ITEM NAME', 'Clinical', 'CHECKUP', NULL, NULL, NULL, NULL, '68', '0', 'JUN', 2014, '0', 'JUN', 2016, 'dog');

-- --------------------------------------------------------

--
-- Table structure for table `item_charged`
--

CREATE TABLE IF NOT EXISTS `item_charged` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `pet_id` int(255) NOT NULL,
  `item_id` int(255) NOT NULL,
  `quantity` int(255) NOT NULL,
  `day` int(50) NOT NULL,
  `month` varchar(5) NOT NULL,
  `year` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `item_charged`
--

INSERT INTO `item_charged` (`id`, `pet_id`, `item_id`, `quantity`, `day`, `month`, `year`) VALUES
(1, 2, 48, 1, 23, 'Dec', 2015),
(2, 2, 48, 4, 23, 'Dec', 2015);

-- --------------------------------------------------------

--
-- Table structure for table `item_in`
--

CREATE TABLE IF NOT EXISTS `item_in` (
  `recieve_qty_id` int(255) NOT NULL AUTO_INCREMENT,
  `item_id` int(255) NOT NULL,
  `BIgSmall` varchar(255) DEFAULT NULL,
  `QtyName` varchar(255) NOT NULL,
  `VoLume` varchar(255) NOT NULL,
  `NuMoF` varchar(255) DEFAULT NULL,
  `QTY` int(255) NOT NULL,
  `rec_day` int(255) NOT NULL,
  `rec_month` varchar(255) NOT NULL,
  `rec_year` int(255) NOT NULL,
  PRIMARY KEY (`recieve_qty_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `item_in`
--

INSERT INTO `item_in` (`recieve_qty_id`, `item_id`, `BIgSmall`, `QtyName`, `VoLume`, `NuMoF`, `QTY`, `rec_day`, `rec_month`, `rec_year`) VALUES
(1, 61, 'Box', '2 Bottle', '23 cc', '6', 12, 3, 'Jan', 2016),
(2, 55, '', '80 Bottle', '12 cc', '', 80, 3, 'Jan', 2016),
(3, 53, '', '10 ', '10 ', '', 10, 3, 'Jan', 2016),
(4, 52, '', '12 ', '12 ', '', 12, 3, 'Jan', 2016),
(5, 60, '', '10 ', '10 ', '', 10, 3, 'Jan', 2016),
(6, 86, '', '45 Bottle', '12 cc', '', 45, 3, 'Jan', 2016),
(7, 87, '', '34 ', '45 ', '', 34, 3, 'Jan', 2016),
(8, 88, '', '123 Pack', '24 cc', '', 123, 3, 'Jan', 2016),
(9, 88, 'Box', '123 ', '12 ', '25', 123, 3, 'Jan', 2016),
(10, 86, '', '10 Bottle', '2 cm', '', 10, 3, 'Jan', 2016),
(11, 89, '', '30 Bottle', '12 ml', '', 30, 4, 'Jan', 2016),
(12, 83, '', '4 Bottle', '4 cc', '', 4, 4, 'Jan', 2016),
(13, 82, '', '5 Bottle', '25 cc', '', 5, 4, 'Jan', 2016);

-- --------------------------------------------------------

--
-- Table structure for table `item_out`
--

CREATE TABLE IF NOT EXISTS `item_out` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `item_id` int(255) NOT NULL,
  `quantity` int(255) NOT NULL,
  `day` int(40) NOT NULL,
  `month` varchar(5) NOT NULL,
  `year` int(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `item_out`
--

INSERT INTO `item_out` (`id`, `item_id`, `quantity`, `day`, `month`, `year`) VALUES
(9, 49, 2, 4, 'DEC', 2015),
(10, 51, 2, 4, 'DEC', 2015),
(11, 74, 1, 5, 'DEC', 2015),
(12, 49, 1, 7, 'DEC', 2015),
(13, 48, 1, 10, 'DEC', 2015);

-- --------------------------------------------------------

--
-- Table structure for table `item_price`
--

CREATE TABLE IF NOT EXISTS `item_price` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `item_id` int(255) NOT NULL,
  `supplier_price` varchar(255) NOT NULL,
  `price` varchar(800) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=54 ;

--
-- Dumping data for table `item_price`
--

INSERT INTO `item_price` (`id`, `item_id`, `supplier_price`, `price`) VALUES
(9, 47, '700', '1120'),
(10, 48, '900', '918'),
(11, 49, '550', '825'),
(12, 50, '45', '80.1'),
(13, 51, '100', '170'),
(14, 60, '900', '1710'),
(15, 61, '90', '179.1'),
(16, 74, '500', '995'),
(17, 52, '', '813.6'),
(18, 55, '0', '450.00'),
(19, 56, '7500', '12000'),
(20, 57, '12', '13.44'),
(21, 53, '900', '1440'),
(22, 54, '', '952'),
(23, 63, '0', '264.40'),
(24, 58, '0', '977.60'),
(25, 69, '0', '429.00'),
(26, 70, '100', '134'),
(27, 68, '0', '1,319.00'),
(28, 59, '', '65.25'),
(29, 75, '0', '555.00'),
(30, 76, '0', '85.00'),
(31, 92, '0', '13'),
(32, 93, '0', '180'),
(33, 94, '0', '90'),
(34, 105, '0', '99'),
(46, 78, '0', '600'),
(51, 65, '0', '20'),
(52, 71, '350', '525'),
(53, 79, '500', '950');

-- --------------------------------------------------------

--
-- Table structure for table `item_zero_qty`
--

CREATE TABLE IF NOT EXISTS `item_zero_qty` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `classification_id` int(255) NOT NULL,
  `name` varchar(9999) NOT NULL,
  `qty` int(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=29 ;

--
-- Dumping data for table `item_zero_qty`
--

INSERT INTO `item_zero_qty` (`id`, `classification_id`, `name`, `qty`) VALUES
(11, 22, 'GENTIN DROPS', 1),
(12, 24, 'PYRAMID HILL', 1),
(13, 26, 'MONDEX BIG 340', 1),
(14, 26, 'MONDEX SMALL 100', 1),
(15, 27, 'EAR CLEANSE', 1),
(16, 28, 'PRO-BIOTICS', 1),
(17, 30, 'FOOD TRANSITION', 1),
(18, 30, 'TICK & FLEA', 1),
(19, 30, 'CUDDLY COLOGNE', 1),
(20, 22, 'GENTIN DROPS', 1),
(21, 24, 'PYRAMID HILL', 1),
(22, 26, 'MONDEX BIG 340', 1),
(23, 26, 'MONDEX SMALL 100', 1),
(24, 27, 'EAR CLEANSE', 1),
(25, 28, 'PRO-BIOTICS', 1),
(26, 30, 'FOOD TRANSITION', 1),
(27, 30, 'TICK & FLEA', 1),
(28, 30, 'CUDDLY COLOGNE', 1);

-- --------------------------------------------------------

--
-- Table structure for table `laboratory_exam`
--

CREATE TABLE IF NOT EXISTS `laboratory_exam` (
  `id` int(125) NOT NULL AUTO_INCREMENT,
  `exam_number` varchar(255) DEFAULT NULL,
  `pet_id` int(125) NOT NULL,
  `stool` varchar(25) DEFAULT NULL,
  `cpv` varchar(255) DEFAULT NULL,
  `heartworm` varchar(255) DEFAULT NULL,
  `diagnosis` varchar(255) DEFAULT NULL,
  `diagnosis_p2` varchar(255) DEFAULT NULL,
  `blood` varchar(255) DEFAULT NULL,
  `ehr` varchar(255) DEFAULT NULL,
  `others` varchar(255) DEFAULT NULL,
  `prognosis` varchar(255) DEFAULT NULL,
  `prognosis_p2` varchar(255) DEFAULT NULL,
  `vscraping` varchar(255) DEFAULT NULL,
  `vsmearing` varchar(255) DEFAULT NULL,
  `medicine_given` varchar(255) DEFAULT NULL,
  `prescription` varchar(255) DEFAULT NULL,
  `attending_vet` varchar(125) DEFAULT NULL,
  `amount_paid` varchar(125) DEFAULT NULL,
  `day` int(33) NOT NULL,
  `month` varchar(5) NOT NULL,
  `year` int(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `laboratory_exam`
--

INSERT INTO `laboratory_exam` (`id`, `exam_number`, `pet_id`, `stool`, `cpv`, `heartworm`, `diagnosis`, `diagnosis_p2`, `blood`, `ehr`, `others`, `prognosis`, `prognosis_p2`, `vscraping`, `vsmearing`, `medicine_given`, `prescription`, `attending_vet`, `amount_paid`, `day`, `month`, `year`) VALUES
(9, NULL, 2, 'SOFT STOOL', 'NORMAL CDV', 'NO HEARTWORM', 'HYPERACIDITY, PARASETISM', 'TENTATIVE', 'NORMAL BLOOD', 'EHR NONE', 'NONE', 'NORMAL CONDITION', 'FAVORABLE', 'SSCRAPING NONE', 'VSMEARING NONE', 'PENICILIN / 1 MG / TABLET / STOMACHACHE', 'MEFINAMIC ACID / 1 CC / TAKE THREE TABLETS THREE TIMES A DAY FOR ONE WEEK', 'DR. JUAN DE LA CRUZ', '900', 3, 'NOV', 2015),
(11, NULL, 2, 'HARD', 'CPV NONE', 'HEARTWORM NONE', 'RABIES', 'TENTATIVE', 'BLOOD NORMAL', 'EHRLICA NONE', 'NONE', 'SEVERE CONDITION', 'FAVORABLE', 'SSCRAPING NONE', 'VSMEARING NONE', 'ANTI RABIES VACCINE / 12.6 ML / LIQUID / RABIES VACCINE FIRST AID', 'MEFINAMIC ACID / 3 CC / TAKE 3 TIMES A DAY FOR ONE WEEK', 'DR. MARIA CLARA', '1500', 3, 'NOV', 2015),
(12, '151223224748', 2, 'SAQWEQW', '', 'HEARTWORM NONE', 'HYPERACIDITY, PARASETISM', 'TENTATIVE', 'BLOOD IS NORMAL', 'EHRLICA NONE', 'NONE', 'SAMPLE', 'FAVORABLE', 'SSCRAPING NONE', 'VSMEARING NONE', 'ANTI RABIES VACCINE / 12.6 ML / LIQUID / RABIES VACCINE FIRST AID', 'MEFINAMIC ACID / 3 CC / TAKE 3 TIMES A DAY FOR ONE WEEK', 'DR. JUAN DE LA CRUZ', '1500', 12, 'DEC', 2015),
(13, '151214191436', 7, 'SOFT STOOL', '', '', '', 'TENTATIVE', '', '', '', '', 'FAVORABLE', '', '', '', '', '', '', 14, 'DEC', 2015),
(14, '151226173357', 2, '', '', '', '', 'TENTATIVE', '', '', '', '', 'FAVORABLE', '', '', '', '', '', '', 26, 'DEC', 2015);

-- --------------------------------------------------------

--
-- Table structure for table `lostdamage_items`
--

CREATE TABLE IF NOT EXISTS `lostdamage_items` (
  `lostdamage_id` int(255) DEFAULT NULL,
  `item_name` varchar(255) NOT NULL,
  `item_class` varchar(250) NOT NULL,
  `item_supp` varchar(255) NOT NULL,
  `qty` varchar(255) NOT NULL,
  `remarks` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lostdamage_items`
--

INSERT INTO `lostdamage_items` (`lostdamage_id`, `item_name`, `item_class`, `item_supp`, `qty`, `remarks`) VALUES
(NULL, 'SYRUP', 'SYRUP', 'VETMATE FARMA CORP.', '8', 'Damage'),
(NULL, 'SYRUP', 'SYRUP', 'VETMATE FARMA CORP.', '8', 'Damage'),
(NULL, 'SYRUP', 'SYRUP', 'VETMATE FARMA CORP.', '8', 'Damage'),
(NULL, 'SYRUP', 'SYRUP', 'VETMATE FARMA CORP.', '8', 'Damage'),
(NULL, 'SYRUP', 'SYRUP', 'VETMATE FARMA CORP.', '8', 'Damage'),
(NULL, 'SYRUP', 'SYRUP', 'VETMATE FARMA CORP.', '8', 'Damage'),
(NULL, 'SYRUP', 'SYRUP', 'VETMATE FARMA CORP.', '8', 'Damage'),
(NULL, 'SYRUP', 'SYRUP', 'VETMATE FARMA CORP.', '8', 'Damage'),
(NULL, 'SYRUP', 'SYRUP', 'VETMATE FARMA CORP.', '8', 'Damage'),
(NULL, 'TABLET', 'TABLET', 'NUTRATECH BIOPHARMA, INC', '5', 'Lost'),
(NULL, 'SYRUP', 'SYRUP', 'VETMATE FARMA CORP.', '10', 'Damage');

-- --------------------------------------------------------

--
-- Table structure for table `medical_history`
--

CREATE TABLE IF NOT EXISTS `medical_history` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `pet_id` int(255) NOT NULL,
  `remarks` varchar(9999) NOT NULL,
  `day` int(50) NOT NULL,
  `month` varchar(15) NOT NULL,
  `year` int(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `medical_history`
--

INSERT INTO `medical_history` (`id`, `pet_id`, `remarks`, `day`, `month`, `year`) VALUES
(7, 2, 'SERVICES: CONSULTATION/PROFESSIONAL, REGULAR WASH AND CARE (S), REGULAR WASH AND CARE (M), SPECIAL WASH AND CARE (L), 11 TO 20 KG\nSYMPTOMS: VOMITTING, SPOTS, HAIR LOSS\nDIAGNOSIS: (DEFINITIVE) ALLERGIC DERMATITIS\nPROGNOSIS: \nGIVEN MED.: \nPRESCRIPTION: ', 10, 'DEC', 2015),
(8, 2, 'SERVICES: SPECIAL WASH AND CARE (L)\nSYMPTOMS: \nDIAGNOSIS: \nPROGNOSIS: \nGIVEN MED.: \nPRESCRIPTION: SAMPLE MED 3 CC', 26, 'DEC', 2015),
(9, 2, 'SERVICES: EAR CROPPING\nSYMPTOMS: \nDIAGNOSIS: \nPROGNOSIS: \nGIVEN MED.: \nPRESCRIPTION: ', 5, 'JAN', 2016);

-- --------------------------------------------------------

--
-- Table structure for table `notes`
--

CREATE TABLE IF NOT EXISTS `notes` (
  `notes_id` int(255) NOT NULL AUTO_INCREMENT,
  `pet_id` int(255) NOT NULL,
  `note` varchar(9999) NOT NULL,
  `day` int(31) NOT NULL,
  `month` varchar(255) NOT NULL,
  `year` int(255) NOT NULL,
  `hour` int(11) NOT NULL,
  `minute` varchar(11) NOT NULL,
  `meridiem` varchar(5) NOT NULL,
  PRIMARY KEY (`notes_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `notes_archive`
--

CREATE TABLE IF NOT EXISTS `notes_archive` (
  `archive_id` int(255) NOT NULL AUTO_INCREMENT,
  `pet_id` int(255) NOT NULL,
  `note` varchar(9999) NOT NULL,
  `day` int(31) NOT NULL,
  `month` varchar(30) NOT NULL,
  `year` int(255) NOT NULL,
  `hour` int(25) NOT NULL,
  `minute` int(25) NOT NULL,
  `meridiem` varchar(5) NOT NULL,
  PRIMARY KEY (`archive_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `order_item`
--

CREATE TABLE IF NOT EXISTS `order_item` (
  `order_id` int(255) NOT NULL AUTO_INCREMENT,
  `user_id` int(255) NOT NULL,
  `order_num` varchar(255) NOT NULL,
  `supplier_id` int(255) NOT NULL,
  `classification_id` int(255) NOT NULL,
  `item_id` int(255) NOT NULL,
  `order_day` int(255) NOT NULL,
  `order_month` varchar(255) NOT NULL,
  `order_year` int(255) NOT NULL,
  `order_qty` int(255) NOT NULL,
  `kind` varchar(255) NOT NULL,
  `unit_price` float NOT NULL,
  `note` varchar(255) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=36 ;

--
-- Dumping data for table `order_item`
--

INSERT INTO `order_item` (`order_id`, `user_id`, `order_num`, `supplier_id`, `classification_id`, `item_id`, `order_day`, `order_month`, `order_year`, `order_qty`, `kind`, `unit_price`, `note`) VALUES
(4, 2, '[C@c4ecda', 0, 0, 0, 12, 'Oct', 2015, 98, 'Pack(s)', 12, 'hjakhskjQ'),
(5, 2, '[C@121b9a0', 0, 0, 0, 12, 'Oct', 2015, 144, 'Pc(s)', 89, 'hdsjahd'),
(7, 2, '[C@5c171dba', 0, 0, 0, 9, 'Dec', 2015, 23, 'Pc(s)', 345, 'dfs'),
(8, 2, '[C@1c121308', 0, 0, 0, 9, 'Dec', 2015, 23, 'Pc(s)', 30.9, 'ds'),
(11, 2, 'order_num', 0, 0, 0, 9, 'Dec', 2015, 12, 'Pack(s)', 90, 'note'),
(17, 2, '160106095007', 14, 26, 64, 6, 'Jan', 2016, 67, 'Pc(s)', 40, 'none'),
(31, 2, '[C@1c121308', 15, 28, 67, 9, 'Dec', 2015, 23, 'Pc(s)', 30.9, 'ds'),
(34, 2, '160107070553', 14, 26, 65, 7, 'Jan', 2016, 3, 'Pc(s)', 14, 'assa'),
(35, 2, '[C@121b9a0', 12, 23, 51, 9, 'Dec', 2015, 45, 'Pc(s)', 89, 'hello world\n');

-- --------------------------------------------------------

--
-- Table structure for table `order_item_confirm`
--

CREATE TABLE IF NOT EXISTS `order_item_confirm` (
  `order_id` int(255) NOT NULL AUTO_INCREMENT,
  `user_id` int(255) NOT NULL,
  `order_num` varchar(255) NOT NULL,
  `supplier_id` int(255) NOT NULL,
  `classification_id` int(255) NOT NULL,
  `item_id` int(255) NOT NULL,
  `order_day` int(255) NOT NULL,
  `order_month` varchar(255) NOT NULL,
  `order_year` int(255) NOT NULL,
  `order_qty` int(255) NOT NULL,
  `kind` varchar(255) NOT NULL,
  `unit_price` float NOT NULL,
  `note` varchar(255) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=36 ;

--
-- Dumping data for table `order_item_confirm`
--

INSERT INTO `order_item_confirm` (`order_id`, `user_id`, `order_num`, `supplier_id`, `classification_id`, `item_id`, `order_day`, `order_month`, `order_year`, `order_qty`, `kind`, `unit_price`, `note`) VALUES
(34, 2, '160107070553', 14, 25, 63, 7, 'Jan', 2016, 1, 'Pc(s)', 12, 'none'),
(35, 2, '160106135132', 12, 17, 52, 6, 'Jan', 2016, 123, '', 321, 'sample data');

-- --------------------------------------------------------

--
-- Table structure for table `owner`
--

CREATE TABLE IF NOT EXISTS `owner` (
  `owner_id` int(125) NOT NULL AUTO_INCREMENT,
  `title_name` varchar(225) DEFAULT NULL,
  `first_name` varchar(225) NOT NULL,
  `middle_name` varchar(225) DEFAULT NULL,
  `last_name` varchar(225) NOT NULL,
  `suffix_name` varchar(225) DEFAULT NULL,
  `unit_number` varchar(255) NOT NULL,
  `house_street_number` varchar(255) NOT NULL,
  `street_name` varchar(255) NOT NULL,
  `purok` varchar(255) NOT NULL,
  `brgy_name` varchar(255) NOT NULL,
  `city` varchar(225) NOT NULL,
  `province` varchar(225) NOT NULL,
  `zip_code` varchar(225) NOT NULL,
  `contact_number_1` varchar(125) NOT NULL,
  `contact_number_2` varchar(125) DEFAULT NULL,
  PRIMARY KEY (`owner_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `owner`
--

INSERT INTO `owner` (`owner_id`, `title_name`, `first_name`, `middle_name`, `last_name`, `suffix_name`, `unit_number`, `house_street_number`, `street_name`, `purok`, `brgy_name`, `city`, `province`, `zip_code`, `contact_number_1`, `contact_number_2`) VALUES
(1, '', 'BARACK', '', 'OBAMA', '', '', '', 'GUINTO ST. EXT.', 'MALAKAS', 'SAN ISIDRO (LAGAO 2ND)', 'GENERAL SANTOS CITY', 'SOUTH COTABATO', '9500', '+639999999999', ''),
(2, '', 'IRVIN', 'INTONG', 'GUINTO', '', '', '', 'GUINTO ST. EXT.', 'MALAKAS', 'SAN ISIDRO (LAGAO 2ND)', 'GENERAL SANTOS CITY', 'SOUTH COTABATO', '9500', '+639429195516', ''),
(3, '', 'MARGIE', '', 'PALOMO', '', '', '', 'LAGAO, GEN. SANTOS CITY', 'MASAGANA', 'SAN ISIDRO (LAGAO 2ND)', 'GENERAL SANTOS CITY', 'SOUTH COTABATO', '9500', '+639108543792', ''),
(7, '', 'JANET', '', 'NAPOLES', '', '', '', 'GSCHS', 'PUROK 1', 'DADIANGAS EAST', 'GENERAL SANTOS CITY', 'SOUTH COTABATO', '9500', '+638888888888', ''),
(8, '', 'RUBEN', '', 'GUINTO', '', '', '', 'GUINTO ST. EXT.', 'MALAKAS', 'SAN ISIDRO (LAGAO 2ND)', 'GENERAL SANTOS CITY', 'SOUTH COTABATO', '9500', '+639429195516', ''),
(9, '', 'JOSE', 'ALONSO', 'RIZAL', '', '', '', 'GUINTO ST. EXT.', 'PUROK 1', 'BALUAN', 'GENERAL SANTOS CITY', 'SOUTH COTABATO', '9500', '+631234567890', ''),
(11, '', 'IKEE', '', 'GUINTO', '', '', '', 'GUINTO ST. EXT.', 'MALAKAS', 'SAN ISIDRO (LAGAO 2ND)', 'GENERAL SANTOS CITY', 'SOUTH COTABATO', '9500', '+633434343434', '');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE IF NOT EXISTS `payment` (
  `payment_id` int(125) NOT NULL AUTO_INCREMENT,
  `pet_id` int(125) NOT NULL,
  `billing_id` int(255) NOT NULL,
  `amount_paid` varchar(255) NOT NULL,
  `pay_change` varchar(500) NOT NULL,
  `balance` varchar(500) NOT NULL,
  `day` int(40) NOT NULL,
  `month` varchar(5) NOT NULL,
  `year` int(50) NOT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`payment_id`, `pet_id`, `billing_id`, `amount_paid`, `pay_change`, `balance`, `day`, `month`, `year`) VALUES
(18, 7, 20, 'PHP 800.00', 'PHP 94.00', 'PHP 000.00', 5, 'DEC', 2015),
(19, 2, 21, 'PHP 500.00', 'PHP 356.00', 'PHP 000.00', 5, 'DEC', 2015),
(20, 2, 22, 'PHP 5,200.00', 'PHP 000.00', 'PHP 0.00', 6, 'DEC', 2015),
(21, 2, 23, 'PHP 800.00', 'PHP 25.00', 'PHP 000.00', 7, 'DEC', 2015),
(22, 2, 24, 'PHP 9,000.00', 'PHP 8,525.00', 'PHP 000.00', 10, 'DEC', 2015);

-- --------------------------------------------------------

--
-- Table structure for table `pet`
--

CREATE TABLE IF NOT EXISTS `pet` (
  `pet_id` int(125) NOT NULL AUTO_INCREMENT,
  `owner_id` int(125) NOT NULL,
  `name` varchar(125) NOT NULL,
  `type` varchar(125) NOT NULL,
  `breed` varchar(125) NOT NULL,
  `gender` varchar(25) NOT NULL,
  `age` int(25) NOT NULL,
  `color` varchar(25) NOT NULL,
  `b_day` varchar(25) NOT NULL,
  `b_month` varchar(25) NOT NULL,
  `b_year` varchar(25) NOT NULL,
  `profile_image` longblob,
  PRIMARY KEY (`pet_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `pet`
--

INSERT INTO `pet` (`pet_id`, `owner_id`, `name`, `type`, `breed`, `gender`, `age`, `color`, `b_day`, `b_month`, `b_year`, `profile_image`) VALUES
(1, 1, 'SPRITE', 'DOG', 'PUG', 'MALE', 2, 'RED-BLACK BRINDLE', '3', 'OCT', '2005', 0xffd8ffe000104a46494600010100000100010000ffe1006845786966000049492a000800000003001201030001000000010000003101020010000000320000006987040001000000420000000000000053686f7477656c6c20302e31382e3000020002a0090001000000e101000003a0090001000000e101000000000000ffe109f4687474703a2f2f6e732e61646f62652e636f6d2f7861702f312e302f003c3f787061636b657420626567696e3d22efbbbf222069643d2257354d304d7043656869487a7265537a4e54637a6b633964223f3e203c783a786d706d65746120786d6c6e733a783d2261646f62653a6e733a6d6574612f2220783a786d70746b3d22584d5020436f726520342e342e302d4578697632223e203c7264663a52444620786d6c6e733a7264663d22687474703a2f2f7777772e77332e6f72672f313939392f30322f32322d7264662d73796e7461782d6e7323223e203c7264663a4465736372697074696f6e207264663a61626f75743d222220786d6c6e733a657869663d22687474703a2f2f6e732e61646f62652e636f6d2f657869662f312e302f2220786d6c6e733a746966663d22687474703a2f2f6e732e61646f62652e636f6d2f746966662f312e302f2220657869663a506978656c5844696d656e73696f6e3d223438312220657869663a506978656c5944696d656e73696f6e3d223438312220746966663a496d61676557696474683d223438312220746966663a496d6167654865696768743d223438312220746966663a4f7269656e746174696f6e3d2231222f3e203c2f7264663a5244463e203c2f783a786d706d6574613e2020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020203c3f787061636b657420656e643d2277223f3effdb0043000302020302020303030304030304050805050404050a070706080c0a0c0c0b0a0b0b0d0e12100d0e110e0b0b1016101113141515150c0f171816141812141514ffdb00430103040405040509050509140d0b0d1414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414fffe003c43524541544f523a2067642d6a7065672076312e3020287573696e6720494a47204a50454720763632292c207175616c697479203d2037350a00ffc000110801e101e103012200021101031101ffc4001f0000010501010101010100000000000000000102030405060708090a0bffc400b5100002010303020403050504040000017d01020300041105122131410613516107227114328191a1082342b1c11552d1f02433627282090a161718191a25262728292a3435363738393a434445464748494a535455565758595a636465666768696a737475767778797a838485868788898a92939495969798999aa2a3a4a5a6a7a8a9aab2b3b4b5b6b7b8b9bac2c3c4c5c6c7c8c9cad2d3d4d5d6d7d8d9dae1e2e3e4e5e6e7e8e9eaf1f2f3f4f5f6f7f8f9faffc4001f0100030101010101010101010000000000000102030405060708090a0bffc400b51100020102040403040705040400010277000102031104052131061241510761711322328108144291a1b1c109233352f0156272d10a162434e125f11718191a262728292a35363738393a434445464748494a535455565758595a636465666768696a737475767778797a82838485868788898a92939495969798999aa2a3a4a5a6a7a8a9aab2b3b4b5b6b7b8b9bac2c3c4c5c6c7c8c9cad2d3d4d5d6d7d8d9dae2e3e4e5e6e7e8e9eaf2f3f4f5f6f7f8f9faffda000c03010002110311003f00e3bc66a971a2e9123f08b7046e240ea8d8fe75627d29f5ed034a45318685c4bfbccf3853d300f66079ed557c46ab2783ad8b61fca9632c0aeec12c07f235dae93696cde02d32f6380c52457925addfcc7e7054bc59f4c8debff0015d063fcc787eaf6716b1af5a3ac89143146d6b2ab83b9b0cc4638f7ef58be2a8dac2ce684b09208c7cbe5b7ddf6fa5775a8e9aba3cd71307334b752b3a0cfdc06b8c9acdf5ad3b548b23746e4fe00669591506d9d67c1fbd6d0124d46e807b75b756c44a0c87df0703f5aa3f187e2be93e2f8ecf4fb14bcb4b886504c9711a0421d70304313f5e29ff000ee6375a0a42c0eefb2cb1371c103a578fdeda6fd74367ee0e1739e474a9bbe5b0e47d79f0bb44baf0b781ad6daee48e792352fbedc93f29191d40aa7a27c59d27c75a85e68d6367a945701648da79a151182171824393fa5741e13bafb578634f25861a050707fd9c558f077c3fb2f0cdc5fcd6259dee9bce766c719eb57cd61a8df73cf3e1afc1dd6bc25e2d6d46eaef4f96dc87f961924dd82d95ea807eb53f8efe05ebde21f1b7f6cdade69d142e51b64ace0e4364f4422bdaece123ef0f9b6e318a9ed062622462e9daa3982c8c98b4792d6d61495d0322a82727070b8e38a0c786c647d7b56cccab3101b2c8bd2a299e28e1cb4581f4e69f3b2f94a3f677902fef91437dec93fe15e4bf173e0aeb1e3cd5ad2ef4dbdb08638a264717323ae496cf1b50d7b443143748bb5718f5a6acb32650c2a63071c75ac255993ca61e8ba2cfa769767673c903cd1c2a8de59254305c71902b41ad420dc64193d40ab6d334c422c4b1e06771ea69c2c637cfcd96147b5914e296e546b26cc6aa15c375e69b71a58950acc82685b87475c8c06c8a9e2bae7688c6e1d083c55f64fdd3927e43d477a7ed18f94c08749b3b68963b7112202c1401b40cf6dbe95696c76bb483600de9c9ab7f66b5b9854a955903f415189a2597cb56e7dea2ec6567855e21fbc6551f31ddd452c23ce52b1031247f2ab13c95a578a69f8ce407db91819fae6a312104091032ac7c007049a25a86e4cf6f0b7cd972eab81f36777d690485494656742328476fad325bc910470a46231ddc734d0bb2672199a354e83b52bae5b1a42cf724498dbca58c85c75c63ee8f7ab2f1a5d2a3901d597838e2abdb6a36d0825d0af1b5863393ef4971a8844510a1742b800f18fad426e3b1725727622355489543e7ab8e47b8a824fb5cc19bcc4836371b466a9b2de0944f2132211b73edf855a8523b82be53179d8ee2a7381edf5aae60f67e65b8a093cdff0048ba122b264f14e4b69d18b4cca63079651c0155e18e6964923ba5f279daa3b9a7cf3ddc78489bcc8e3fbea7bd28e92b90d252d4b735c19632207547071864c1aaf2c93a843314da9c6540c9355a5d592e9154dbb452f4f98e726ac41a4dc5fcd82db99001b40e013eb449b0494a57658688de85479d4228ced0306a25b2612f3325c9e36c6475cd6b2786a1d29637bfdc656f99c67a2f6352ea37f6b676b0fd86de205e41fbcc6480064e2a653b688e9f67732e4d224ba03cab568a4539552081fad682786da755fb56dfdd2ee608791ce39a7cbe2792e668ed47eedb0098d8e7a923afe156ec1ffb409b7c8899b01be539605b3587b46691a5dc7c3a1da44ad9573c6433135341a6d9aceb015109de0a67b8ff00eb5747686086cdcab19143854321c669fe20f0f417a25643e5c9e583191d46f1927e98a71bf72bdd87431a3d134bbabbf251fca589497c73b981c37e02ad27808dc2ccf612446268d910c884f1d9f93d6bce350d1f5ff06466ee391ee6dd0b30849c96f9b217eac39a9bc1bfb414767311addab234b237cc7250140a4a05f657463db0c0f7ae8a70f338e52bbb9afa8784752d3e410b47e7b13861180aabf5278a743e1ad46ea2f92682252785f30648fa57a9787bc61e1df1b5a0586e14cb32ee1098c95fab0ec3eb5ce78b7c3d71a14ad71604ba83832ba852bee011d2af90d15a5ba390bdf0c6b3696a736f2100edcc782ac7eb9ac4d4740d416d7cbd474d2eb32163198895750782cbfecf507d6b65fe289b2d8b3ef67462c094f919c760bd0fd738a8a7f8b3e7c1bc4e92f9ccca60301ded9edbc9c0153ca84ed1e9a1ccc7aa1964f29e1c797f7d17f87eb565a4f3446480547dd00f1516adacd96aa01b93f639e43889634d8df5c7f15640b882d1c46977f68da542a48191b27b608155a32124f63a396f24688c3e582ca7b63814c88cb73b515bc93bb257bd53b696e26994384847f03a00dbcff74fbd4d2491a481839cb9c1603a542d761f2934976d672aaed0ad8e641d73f4a6b30bb91e5c2c4e067cc518cfd4555fddacebb46ef313079cf35248a200b2c7965c63611d699949db62c3dc0589d18ac8ca396eec6abc6eb298f25b731c3839c5344bb242d2200b8c9ab09ad597caaaacd21e19403906833bb7b9a0f703e5899c3237df00f22aa603064890c601c871cd5792dfe59248cf961ff880c8aa6935d7d9dca024671ef52a4e52d47f6ae69c37888927989f338c039e699279705c2151b91d71f31a64b12f936e54f98ea33d0f1f5a30d3c8a85443b4671de8d7b9a349ec59865491a58f29b7765f8e94d9e386da271e581938572d4c684b297fba01c160386a8cdb676f9ce36a9dc1185511663552296cd5700cfbf2a41fba2811dc48446e4a05ea68fb1f9acd2c6ca1b190a0d12dccbf65632e5c6324e3a50691a4c66dff00a783f9514dfb4d8ff963452bb2f94f15be03fe106b8cbe4c7963c75c32919fc8d6745e302f66b636aefe43dc79f32927071b82fe418e3ea6b662805ce8ba9dbb4832fb93ccc7427dab8fbb55f0fc06d954cad0444c9213d48ed5dc79ef476650f10de4b766578dc9310da42f2547d2a8f832d255d4b53865380f9201ee0ae01c75a65f6a86d6d7fb616d56482e1a15f2158a98f1f7b0d8e6b76f1e2b5b1b8f104316e44544f25be538e3f8bf1a8297ba65fc349cc623b72cdbf322119eb9a3e187812cbc53e2dd41e79963bab1938b72061c573ff0fb5c0de27b783c9114735d15255c92b9e82be90f0c780748d07556bdb68545cdc1dcce08e68f31dae759a6e8b05b411dbba22840b85038c0adb4b05894885c853c0f715463b6322b4ca3055f6e01273f4ad180b08d59fe754efd31f5a722894c0d101c31cff10eb53c73408d86c06a96df5137103ef4008e981549cc529c98433ffb359580d0f2d5c8f2d073dc914c96d3ccf94a97ff00815537d496d192331e3fe0353b5db5c425e3038f4343925b9a59951b4eb8b63fb9dc57e9515b5eabb382dc81c8f43566e3539e74db240633ea8fc564db69ec67797cc2a243821ba8ac64931356dcd166de461482a3029511615dd93b875a5dde45bb3a10c17b9a4b7bb5b8cab4239ef9e282e294f52bdc8556f314809eab51c65519886df9fe126ac5c5a9810b33aed6e831c0fad469631a40409d490325b6f4a2c0da7b12b45e636c88e38fbc3d6a28ec1444d9c657be7e6a6d9b286611c9923b9eff004a5926cb9f9b7fa96e052f790ec811fed7095460841e8e3273558da452ab79ee3cc43f2f246452b9547cc4732b36428a786d920327cccdedd28b846287adc18e0526307fdeef55ee6e8b2b98d42393f3c87ae3e94b24722c7c7eec0fbb9e71f5a648c52010228677fbc4f515269cbd0883bb2a4cd1f0adc823ef5591cbc9204001ea074a89ae0431220c801b92066a3695a668d76ec46932483dbd6a9bb6e5168c9243129284a0eca719a6cb3c51c12496f1147ceedc1bbfb54117ee7cd662d26c38519e0d490486e1c864db083818ebf88a8b9336e3b0b035c5ec30cb3c83706e1b77357a3fb4493ac711f3646fe1f2f05beb53693e099f5cbbdd6e8fb0367cd0a7007a915e93a6e8f67e12548d5c3caa3e6131dd9f6527fae2adbb16a37dce3adbc0f35d6d7bdff478d5b2a9c7cdf88ad216aba5f95045fb9b7deadb9bf8c8f7f4aef1cc7ae424450c6e5bfd584f971585afe9224b48cee3b5979531e361ace522e36ec711e21bf47b890efdcd88f92783819007b7ad45320bbf3218c84570d820700941d3f3aadaff0086e6986537008ac401d5b0d803eb8a934095634857cc6628f9258741b17afe4dff007c9ae6bb523aa2916e2f03cba9c8b20de9226c6950740b85eff526a6b1d0eeac1a49d26760b2b22393d541c035d668575e659c5bdd15e560d1307190c09f97dc72a7e86ab4730b88cc3ce630e085e98dd9fceb4e42798c888cd66c3cd2de64419897f9bef7dde2ba6b7be9218925b89bcc9f77ef15c74da4aaaae3afc82a8ea96507d93309c9f34b17e4ee20028bf4e6b22e64923c0cc8c61437333039c0214657d3827f122a545adc1252dce91ee21bd902c91c73000ac31e0e22dc3009f755e457cdbf1bfe1f5c4934faad8c9e7496f1b4696f6e8426cf319db383d5e46faf5e8b803db1f537b3b5bb6576c32a87453feb5bcbe63cf65cf19f4acf536b770456860652a0cb21ddb4bb7dd2e3d401c01f8f06b652e5d8cf962ddac7cd1e08f8dd3783e79b48d591600a8639660080f201931b3e7071eb9c7a1af6cf0c7c6eba697ed0854e9cf1853149023641ff0069403fa5709f123e0269be22bcbbd4dcb9d40232d8da236c8c305c44188e30be9839af9cfc496fe35f86f722076323c854130a36c047f08e793fa7bd6d19bfb462e938ec7d61f10e64d7927d534a9de2790b492c21c6e24f6571c28ff640af1b3e20d4ecaeda2ba591edb3f32c8ac421f619ae13c23f1d1ac9dadf54436e73ccb1aef5f4236f7e78aee24f15e89e27895de58e793ef412db820a7fbe01abe4f330f6adbe568eb745f19bc36cc6da6091b0db2c44e5187ba9c9cfd08a2e7c77369964b25bdc22a0042281b921f44d84153f5c570772ff00668e29239a1650f969e31dbdc5564d67cd558a458f246c2a470dfed7d684953dc7f16c7a268ff1811ef545c148639db13796bc06fef7b57a5d95ca88e32aad2891b3bb3c115f236a7aa1d2ccf6f2301128dd1ba8c16af5ff00825f13d35881747bc7f3e78f2d0b16c168c1c13f85693b38fba8ca775b33dad608aeb3bd3cb65381b4f5a896f4dbc9f20dfe59c00c69b2247724b82620036d39e091496f0886dc1590ca85b25f1cd73deda097bdb93dbddb48a526dacc5b00e38c53a5b78c6f911b6c8afc1fef5453a25d242ea76e1b9c54325cb5abb05837a8040c91d4567768d231e4dc7bf9c22964470653f78f65fa0a4b32f1452232196e036779ebfe14417267b6c6c2b2bfde18a7c2ec9181bc027aa9e83f1a71f8ae5defd0922bc293bef468d08c1e6ac4f26efb8377180e7bd548d9a30c9215048ce3a9a496f4068cf420e02ad68673f747e650002f85c962bf4a47f2ef49914ee5036924f19a9c4b946de9fbd52df2fa83e9548b0863560c56267c3803eed1b99dd8f1732443cb0ab2853c151ce2912e649144011d46dc039ceea91668d212150007f881e69f1caa1460fce467e9f4a4f4dcd39d8dfecd4fee0fd28a6f9edfdf5fca8a5742e767cc7e11f115f5ce9f33dd3190a10e263c6f246738f4ac8d68cb7b831b19126c92c0fde27d6bb2d762d2b47b9fb3e9eb9d36dd4468cdc172170643efed593f0fad61d656207e758e591540e9feb703f4aece6f77d97e272a4d3e6dce6cc134bf0eee1064bc3b4905f907ccff000adb127db7c13aa04667090a39566dd8fbbcfe94cd434f48747d7ed0e3f772cc30460e036466ac7c32b68f588ee6d6e22696168154ec07951ebef46db94ff78ae6d7c0bf8776373a7c1aca4c92b4ac0342c0108c3ab67d6bdf6c74c824621d55581d8303a1f6ac1f03782f4ef0ee9c12c2030c336e6db8f5aea92c1a25792398b375f9877a7f66c5c742cc760ea515642147cd918a9113ca60ae0e3d31c1fad4f64ae7f7e769661b59f1d0fd2acdc5d26d0ae4aefea715128bee0429340ecc9c39271b4100e6a4f2c3bb2a054403392793f4aad1dbc0c37c6a9bfef6475ddfe1535b48f2c806c1115e4617a8a469ca32e2c05da152ac41fbac0f3542686eec15dc92d18193c56c3171fbc56da63e8bdaa1bdb57bf8b7472ec0eb8741dab37152dc9b99f15c4724231f3e5b2013ce291479c4f04333649ed5512c24d2c8594ef8b7e55c83c0f7e2afc720c02a4143deb3d8d17bdb86523c291fbb6ea0f4aa33008cea582027030b5a37512bc083cae319eb59374970d11491390dc91d699714946c8b16c6350109ca7f192738a73db35c46db5888c1ec3a8f7aacf6acc854fcb8ee3bfd6a75325bfeed1b3bc640ef537628c4592dd2de24738500e015aa57ac17ca2b1367baeee957517962e0b1c7dd23806a1b86452c436e6619a24d97ca448188ddbb6b1380d4188b87766208e869a6e15d9539c8f9b38e280abb7118dd1f62c7a7d6a6e8969adc557740361c87eb9e714db758f7499e1c1c331fe1a26dfb06d8c7dfcb853d0510b4841036966396ce39a6f4dccb5e6bdc47903feec3863bb38039aac267fb600d18fb9b36f615661b41bc48001ee3ad6d7857c2579e2cd44da5aa3972373c98dc00f5cd250e6d6e6d183332c34c9afe755b68e49897f951549cfe55ebbe16f847f6448eff005a68e10a3cc1663058fb16f5aeebc35e0dd1bc01a5e4c313dd13b65b8d98673eb8cf15ce78a7c40350494dbdbb611b9b856da41f5c771f4ad2c914bde766497de258023db58db2db1236acd6df2861e9f5af34f100d5752ba670c658d87991be71cfb574fa2cdf6c994dcc451579386e02ff007863bd43ad5bfd866745647564caf076c83d38efec338ef8aca4ee75463cba3323c19e24682e7ec9337ced8db938233e99aef249a4bc80949b1ceecb7cc18fafd2bc4b54d5b66b8b2e5616055d63d9b96603b67b1aeb2c7c6f0dcd9096d2568cc40c854b71bbd3e86a94935648728db63a1be876cd3c53c3b541f98af542cd953f435c078a11f4dbe1716e4ac4a012154e08f2f3cfd32df9d6adf78a4df4cad0b812001d067ee9fee9f5c76a4b5663f69c664558cfc8e73888f057e98efd6a656ec67195b73367f14a9b648602f094455528795214063d3a956e3dd2a1d3b5ec6a4c448de5ba16255f23792001f99ac3f1069f359c66e6d5cb5a9542e9bb856036b67db2ff00a5729a36b1e44b345b7ce49a158a420ed2cfb55f3ec4b8c67a7bd45dad0d924e37ea7bd699a8c8f64609dc2c854cd1a86ce594051faa9ab0ba5fdbe59a28b747753ef599d06484ca3851f455e7e86b81f0e5f8b492cd2ef2b08f2e4619cfee5998ec07d0efebee2bb8d26f0d9eb13cecccfe65b3491c68f858dd83a9008ebf22affdf62b551e6dcca6dad8bada5feee28a0844b6e5586d29db6aee5f7fbad8233d47ad65dce86ed30770634494a33ae0e096276263a0e0f4cd6945e22fb5451436aa5672cb189b7604286353b17d0911ecc9ec01ee7176e5ac0c0b6904ad2bdab32c86042732100841f405c9233d2a651222d98dff0008a1bf9be62a5ddbcb8a151f2ed1d5b1ea3bd71fe22f095b6a315d45f66859d14c62474076e0e300ff0009f7af49bd45b54cc43cc589480bbf05540ea0fbf43ef5ce5d6af6f633328856497702c76e42966cf951af4503b924f4359ca46d06dee7c5df1e7e0806b457d16cdd638e3669ae6538699d9b710a0636f35f2fc1adf883c0d77f22c96e9e66c2a38ddf5fef57eb5f8a3c3506b764f35cc284b231532018c0fe20bd493f4af90be32fc1cb58a5b9536cf2f983c9170ebb481fdd1fe35ad29b52b4b633a9494d5d6e78bf877e302eb13c76f78b15bef5dbb950e18fd2bbe96da3bc48e6b2c61d37185a4ddff7c9af9f7c41e0abbf0b5c998c28b0bb12850eedb8fc78fc6b57c1bf126ff439628a5b991ed412aa8cfc8c7e15dad291e7a8ba723d4b5fb792f2d884004817015ba83e9f5ac1f0d6ad2685acda4e8c229edae52543bb19c365b3ecddc57a069d3691e34f0e4979a7c823bd41b67b77e7ccff006b9fe95e6575a79b7ba943158c8ff962c3a2fd7d69257096a7dc1a66a8354d2a19e2c059e343f2918e7a915792596da38d7cbc29ec3a579afc13d7db59f03e9e930f325b42d136171f28e9debd37fb4edd53605dee1f233e95cf28ae608e846f3f9aebe5950437dd19c53a42c91ba9453f3b30e3ab1f4a18c66169c10a58e5702a8cf753b3a10adb58ee27fbb512572b9afb9761bbc408044449ea5ba531a395bcedd21507b9a462196311906473863d855b85227fdd91b81ea735718845b0550d082e720afdf23bfa52e22b6b67206c20704f2734c9a23c46acdb47ce46ee33512979254468f702983f5a4436e5b9656e37c51331108dbb82f51f9d222c8a0b3a64b1fbc5b93ee47a531c4654ee919245e3605cf153f980468db72bb36726ae3a0c84a24af33b288f1d133d6a18a4569b70c90a3693daa77dbbb7b4478e809a8977347b95305dbe651d29a8f36e162e6eff0064fe9fe34552f325fee2fe5452e403e5bd567935b58da25910aef52c0f7048e9f856a7c268dac6eaee07fdd98a76e3f0ddc51e0c58b5a96767cb6dbb9530071d437f2cd69d8d91d3bc757c8b100b989c827ae5706bab95735cc136972bdc86f2c4ddeb9e20d3a25f3269a51807a9c8c9aefbe18fc3c6f0ada2ccd30fb414c488edc01f95733147b3e25dc29394982be07fb87a11f4af6ed320916365544dc8792c3ef0ab493dc71f86c69e9b6a970aee8e1142e0af61f4abb108fcc58d65cb775c545a40843e5486cf503a55abbb08ee2432a32ec3d541c0fc0d45ec5879a628dcae2401b2ca48031eb4e797ccb77738703a1033fcaab5a59812344d22cb1c83698ce00c7f3ab5f645b568d625640ffc2ac40a901b1c3127ef14aaafaed38ab1879123915c02a7d3b5584458e31e607879c79839c1fa5249711412ac41fce593b639a5ca55c5649100dc44c8cbbb23a7d3eb55de589c858898e56ea84735ad05b2244c5536aeedc0039e6ab5c470dd952a7cb03b6dfeb47292673dc2cbe64726e739c61ba7e158babd95c5b3a491a1922f404002ba29f4d2371e0b81f211d18d516b90648e399770738208e2b2944a8cac51b6b913c49f36382092bd3156fecbba31824856c866ea7eb59977bb4949769df19dd9e3a66ac699a82cd6e16493cc07d78acf63486bb11de622561b8ed3e9554caf14d18f2f76170581ad0bc90b65f8c8ed8e2b249024112e5b7be73ea3dab2e666fbea4ef74acac5013b8e08dc6ab23832602e4f5e7d2ac240431563b5c367814eb584077523e53f286ef541710119c4654bedc671c5356011c73337ef33fc2b5681543d439ce3a62a24f3777ce02e7ee90a31f8d16467abdc8cdba2ae5721c0ce09fbd4fb2d2ee752955608b716e9b56acc51866da59a3da9f2edc126bd4fe196831a0fb5188cbb065642dc9fc2ad4548717c9ba28f84be09cda9112ea771258ab1dff272241e9fec9fa66bd66cecb47f02e91e458c51c31237efa42bf303fde27fa5676a5af43610c870c880ed0a3e5c7d2bc2fe267c50bbb2b84b54f36e37bec8c2fca31fd69ca6a0ac3e6e6d8f65d5afacfc4d19dd318549cfeed81c7eb581790430c66da362d728703037b32fb2f63f4cd72de16f184ba8688b977ddb76c96f70b960de9c01562ffc4631144e825475ddb647f302ff00bbbb257f3ac5cafb150f88d2b4b77b480ca572aa7240f9727fbd8ebf85739e22f11c36b03ac69e65bb7ef554123637f79700956f51d1bd455cbdf12bc36cd2dadcc65f6ee3148c19cfb67d6bcc357f1249753806461348fb56784630dee3b0a56bab1d0a0dcae8875fb9b7ba9fcf8b746e54b3ac6719518248edd0eee3b7bf15c3beb9341a9ceb0b02c14ef50d87c2743b3afe1d7dabb68a6bb31bb5e5a2ce2294caf3c2804ca79f3082390b8c3e5548ec715e6de21b38659ae240c4cb1bb4c648d7a2ff00cb40cbd78fef66b9da9465a1a9d568be2ab3bfba88b2c91b14678a7524ee65fbc08ed8f7aee62d5bec5790c85cb2ca36b28ff96449c07e7ae7b0af9ab48d727b6beda823962f33e40014d857ef6e19c90df4af4fd23c590dce9f1f921c4a10a18663bb3c7e7c1e95ac27cbb99d48f36c77fac5e39b49cc6e8c972cab955ce76312303df8ce6b80d550e83a95a5fc6893ac92c723c60f1b404c47feeee0d91e8456e5b300b24c9215572a649072002bb837a018fe46ac69d059dfc57335ccd1dbaab0621c0c460ed46ffc79830c7607bf15527ef5c71d343620ba6fecb8237543119ae2340bcfc9b0ec049f4e48f651f4abafab47676d69079ec66d81de48d3d542e38ebc11d3b8ae3747bbb9b88d02c867304846c2311f98accfbf1ebb23518f492b6e195ce8d2614238dcd2346798fe44542a072a0a10e29c645ca274f68ea2e6fe15f33c88b5109390df3b33ed1b78f407cb18ec41eb5d8db8d97012c638e38d660ab345c047662ec473d9650bc66bcdb43b1695ae6de59246b68e733794ac550323a958f6f5639ef9ec2bb19bc476b69a65943243b3cf73bc85c058e345daaa7f8776e209f61ef8d61aeace39b6b4475d75a07da203242d23c691b470408bd0939f333df9f5e31debccbc5305f694e12c08105b83d792cec71924fa6e3cf4e473c8a9a7f8c50bf918b9f2acd8070a1f0655233f30eaa83b822a8def8ef4dd5960baba8648f4f129165c6d56238673dc9c9e0e33d79e460a94d4f54453a9c93e566a68767aacb6e8f7d73884825a465dceee3eea86380a3d704d43e31f004bad5a2a5c465a4742ad11624a28fe3e475f6eb5d1e8dac5bb18a7b432ea0f147cbdaa88d225f45624b11ee48356dfc436d2c33cb717f05b0cb16512abe0edc95249ea3a1619e7b54469337f6d73e2cf887e049ac75316178ab6b60a5dff007802ae3af98e707248e76f5af9b3c67e001a6c93ddd8c8f34459cf0bc28f51e9f4383ed5fa2df137c136fe31d2644b36125d306906f5dcac4260364f1bb1dfd6be3bf12abe8f7d2e917afbde29593622643a9f5fef1ff6aad269d9994d29ae689e49e0af185cf872ee225df03e41b8e760feb5e8775326b701bc77123b1058e39c7e1e95c2f8f3c29fd8772b7904ab24527ccc89cf97f5ff00eb66b43c13a8c71bc43ccc6e528d11e783d6bb21d8e56ada33eb3f826f656fe0db591954c8e58b8071bb9c66bbb86ea1f33705f9ff00ba7ad792fc2bbb5834e48a17e55dd04a46fcae77631eb8aef52fe4493e609218d77b3371c7ad67287bd711baf21b99159490a3f8474a0dd3acf9fbe36e1d57a2d73d6fe27170246907cfbf2aaa30315624f10436a76e544b23e46390cbeb59d901d1bdddba3468aa15e56fcbdaa655684aca320b0c807a57272ea2c0c32a1cfcfb94b0c026ad8d76e16ebc8793a00f83c90a7bf14a2691d0e8ff00b4523b77624e59704e3afd291b528e2855d6324d600d6214d841323120247bba9ffeb54b757288b1bcaa16243862a73fa75aa71b6e666a5cdd1768e528e0277cf27eb435c5cb95210838ced3d0d639d612412186e0b439c0665c535bc551db2a24e422aa80afeb9a71b4770376277cfda644279c6c26aac9a9ed214bec2ed961e95ccddf88945bff00c7c284dc098777afbd727a8fc4fb1b2bb5826bc862798e158106889773d43fb557fe7afe868af2cff85a3a6ffcfec1ff007f57fc68aaba20e2be1ac12e9b7fa85bc9bc113a4b8ddd01520ff2aeaf56060f1c993236bc119395ea4567dbd9ae93e34ba58d71e6dac2e4673ce587f5ad1f13a94f12e992b7024840e3dbad6f1fb28c97bcf999d568fe188352f1245ac7da4e63455658fe5c615873f9d7a5e9b11964dd27cca4e0806b9bf08e88efe55cc2a046c9f32e7ef1aeead628e31105461bce5891d2b7b207a6c496d0436d39598148dfeeb74c7d6af5e69ee6091a07e07dd000c7f3a4332b485640582faaf153dd5909636f2d41731f0b9c026b293bf42acccc4d3c5e6f95d8c73272b315c71eb815a7145288a258e5f3431e5bba8f51ed4dd2ed278c12ce33b763275c0abb6b6e62c84468515b77ca3247b7d2b2f22ac30453095adddddf237e1ce0b37b669601133bc72c41658fee60127f4a9eeecd2f634579891bf0371c903d7352c4d1d8b98373b1f2f954eb9fad2111a12196548da2013053aee3ed4c9f5686370815b7138000eb4f9f51b7b631f126e7190ad9fd29f601e2f9e58f7317ebb7247d280144697d1b61994e320918dbf9d52bdb38e58805083b06cf39ad29045773bc6f23a4ccb831b1e0fd2a96a3650451049448cead93b79fe54a4af11d8e663b9886f8a65c8071861f7aa84d1a59391138f2cfddf5ad7d56c0dd4322c27648838dbd49ae5b52958f9093291247d430c5612dae0a4e3b1a16b3e03464efcf7356bec8a555d98b1fa631f4acab39942a3315f9ba12c07f3ab91dc79aa4ab92338fafbd64a29ec6b19fba5b8e38c167dc518faf34889bd4f18c2e73ef5098a40831216c8cf34c47764915002a0fae38fc6a89e62d451aa65880001dfd69caf1a44470a3b11cd504943cae9239ff0080f39a6473edbc5b5462aec718200fe78aae52e12beacee7c1d043752c42ea181e10d809282a48fae2bd5ecfc4361a4c6915bc2215418c46a58579ddbdd69be16b1432c9e65d6dc95886dd87f5ae7353f15596a2db4c6652c33b89200fd450fddd8a9f73b4f885e3cb46b5764910c8aa73238da491ed5f2ff8a75fbad5b549656b79af4c5865f28b9c67b8c0af41f104b1fd8e478dd84921765f2582927d4b1ce07d715f33ea9af79da84ca3e55f30677103a1c15ed91f4a97694ae11ee7d37e17d4e7b7d321693cab7bb68fe6b76449243c677920123f1ad092efca591ef21b58e4700058d581504678e6bc7fe19eae27d423b788d9c3e581bdcfccf9cfdd6704ee18af63be9d618e486192299772c815932721718cff0076b19ab2ba3aa295ec665fea2258f732e47501260543e71bc903afb52697a25dadd2dc2c69729328c470382d38f501b1f30f4155fecf2c883cc6758849c4306238dc6ecfdf231fad7656da1c52402416e094432a48cf898e7a93fdf3ee307da9c60cd1b71d11caeb70194a5d440029ba1f36394c12c0e391bc11fbb65e849e1811b80ae4f5a8350688249a7c6d22b131c93288271df7467a1047cc506e047209af40492c165b8985ecb69a84ea6dd1c7ef6d2e4632217520e08c0f9189039c015c1dfde2de5c5e592c8628ccd26fb246f3444d9f31405396033f30c1054f6d944d244464705aae811471452ccaa2f2264661327952364e08dc783f81ad0d090594a2e059992256763186f982fa96ec3dcf14babc33cbc3cc2e6680244c80e54267214039db91f8f6c678a9deddace19192ecb053b5e393e5708cb901c7565edb7d6b92e6b66cdad3985c4c2081ca492218f667862877aa6de87ef15e0f4229f1e26b98e232247666756ddb73b18ff0103afcab9fc2b2f4f5945e47b155a21344d190d93873f797192bc1e9d7e5e95b7a7ea76725ec5686d5d6ee3b9dc93459064505c2647664dcc141ea0738aa526f722def5cbba5da5ec1a6dd4491837ab2c6caa1bfd5956e187aabae07d0577115843a66997a5a50f6f3f953473ac782e8404619eb850883a74350784ed1d2dacae9a3fb422b92ec8bb815d8e854f7f972a69da93241a3caa6e0cf0da2ca22766c1b7ddf2b2eee840501beb9ada29c23dc995ef62b681a928b4babb907ef25bc712a01feaf73310c0751feb07407ee8a83c6ba8dbff66deb5d5fadbace1e0494370ecd1032a04ecb1c6339fef4b81922b27c33af6997367a7344cd05dcf712a8f35b0f8cf073df19524f403bd705e3ff001a6eb79a28f7288a63099588210b3b6c038e9b95db777dca4fa0de2fddb194dfbc7cf5f193c5da969be24bff002247163b5225981dad26e00329ce338cfa0acbf0b78b357f11df410cda901b541df773ec8e100ee6c64e338acdf8c9a8a9d4de16945eb8f2e57b83cf992bc2a5863a0c118fad705e1cd55a3bd8e223cc0c59541202ae571c020e6bae924e36671d5d25747d291f8cedeea4b3b6fedbbcbc7dade603e60f35bf8308b8ca7a9fe75a37de2cb5d3f5f867b7bc37516dc4298619503390ed8dc4b75c2e31debcac789b4cf07682d2c8c7fb465937adc7944cac8abb7e539f950b7451dabcf66f1dddea133baaa2c6c31189496c8fcf91fec8c7d6b49c6fb6828b67da367f19269eda4d32ea7860f200f304972980197036ec2c0fe06be7cf89dadc137885cdbdd7dadb706cc395033ea48af38b5f15dfc9ba6576560414fb3b30d83b6173c52e9f7ebaaea71e26918b6d043a92588ed5cb2a76d6e6bcdcd1b23b0bb806a7a51611a7da2301bcb438083d0fae2b821a6cd06a2ae998b9c0606bdd340d19238c2845f9b28f03801b27aa919ce7e95ca78cfc3ade1bd7dacef768db87882aedf2c1efc75ace3370969a8d527257ea6be8de3db9f0e591b81112d95775462aca76e0907d2ab5d7c6dd52e1a4305a6c7638690c9b8e3d00f4ae2ee2edeef504851cbc3b0a121b838ec7deaca69ca2340bb4311ce31d7f3a556ab5b1ad2a69ee7630fc6dbe59633f624f29131e56e38cfd6a847f1935fb794398e2dcadb97201dbedf4ac13a57c9bf76d5f56c0a43a188d2295f679722ee0cc4561ede6b5b9a7b14cec47c70d464b595e68035da1ca10e760ff0080d259fc74d443cd2cb68199b6fccae41c0fe1fa571eda4818f9fe66ebd31520d1c6240ad94033c62a3eb153b93ec699ddff00c2fdba9a501ad1e38e10591164fe23f7b9c74a44f8fb7d15c856b20212b960ce49cffbd5c30d00c5f2e7e72324350ba3eef95806e31cd52c4b96e87ec91d95ff00c6dbad924705a92d20e649242429f6acdbdf8d1aa5ec02392046d8ea400c7381f8573cda26d0182e4e71b7b0a1b440c1d7250ff7b1cd2f6fe41ec916357f1f6b1a944e8aeb6b136dca2fb7bd63c76b71a9132125d9db2c4e097fd78abcba4130c842ee03b95ab9a55b6658e38d4020e06074fad44a7396c6d0a715bab995fd8d27a37e428af5dff8555aa7f7ecbff0263ff1a2a2f2ee696a7d8eab5e71078b2cdf601e7da942d9e721f8ae9a4f0cb789353d125208863629230ed9e95cdf8a003a96853b9daae59327dd73fcebd63c31a28963895e491dbe460b182437bf15efc7e2b9e147e1b1d3dbe8b058c68b6b308d557054b715ad63148cde5c92f9b9fbdfecd55d33498ddcb30678f38dc4f06b663d1aead54496a3cd467c988a8e47b9ad0641278764b8566b492185c8cf9bc93f966a38a1bcb4922b691c5c491c79de0706b511d58c6c63f2c81b5828e2afc6639e3214a863c6fee07bd171f3148c027f2e649b6154c38e9cfbd596b891523c4bb8019631fca0fd6a92599329475047f17ef4006a24d484b0490241e6ab1d9bb903358b4f9ae5dcbf35b6e89d610bbe438c16fbabea2a78ae12c99628d1dd95806072720fbd569a43a7daa431c4d32ecec0f0bf5f5a75bdc4ed0b491ab46a428329ec47ad2b5892c3298599e46f327cfeed4f6152457b1b811ce1a2931f751b041aa760e222cd732a1df2611c9e9ec3deb40416f6a25941533919cb9ff1a5e669ca452d9dab48acf21939c28c81f99a9ddedece06dcb94eee1b1b7eb546df4b9af6632cd2ac596caa2639a9751d2e1b88a389249232465900fbd47d9b137295cc6d72fba39227c7751cfe35e75f106196c592f95095271236ee07bfd2bd41ecad74f8c1009595304a7201af3df89f211a0dca18fce8e55f2c8cf4a5249c6c4ee797ea1e2682084cad731654e36677d49a6fc52b68e32b3a4de720da262a47158ba7f84217891da3cb139385c0ae9a0f0ad84b079315a8320e4f07a7af5ae57eeec69189ab37c4fd2d2d90333bbece5624248fcf1505b78f61d42ec2436f71e5b36dcb444e7dfe948be0bb544b7611c5943f3610e587e75d04163676ea36448a51768ca9040f7e6ae3243e5b98b75abddcd27d9ed2d1e47df9573f271f866bb3f0f5c45e1cb6f33512935d8f9f622e001f53934ba2582dcb34d23a401012a4a7a57997c45f13c36375702099b6b36c0f3314cfd3353296a6d18da25ef1778f5ef75365b672b1272e91be01ff00673eb59fa4eb571793c4b2ca59bab8df918af18d57c4b29bb051fcc8106180e15dbd73eb5a1e1bd66e7f7cc8e50fde243670beb58b95f63a138b8d99ea9f153c6834df0adea6f1129842c802f24772c7be7b62bc8e6b2b3d0be1b595da13e7cb0f9fe5ecf9f736186339dbd71f5ac0f8b1abdd6a36d69630ef69ee89d89bfa44840520f439c8a93e2178aa2b3d0ad2c2ec18249911170725630aa770f6241fcaaa261ccad6b1e8bf022e2e26b982e4b247f34a035c4092c6c9d8a866396fae07bd7bc26a4351bb75bafb344e479724ad0ab8618cf08146dfc335e17f083578f4dd3e04d3ca2c8fb772ca37b85ef91b7007fb20fe35ed5633c12dd413de48a8fbfe58e3c6e6f971f330cedff816294a7af2d8de34f4e6b9d8d869aba4f932d8cf108810cf2d98f225009c7121dad525fcca9006b6492fd236c4968e6449d0ff00794c6ca3f004d36d63926848b37b78ee19538deadb47dedc431e98f4cd575b1920badb3ab5c6ec61add4a6c07f8948278fa64fb528cadb912bf36e732fabdc6a9a85d9b5824beb48b2b2c69133ce995c36fde0b6df7d99ff006ab1f54d22ef509c4132c1a943802da1bd058050d950ac01914a8fbae002a7a861c576da8f871aec497cf792b480f97279277c88bd88c92c57d8e6b3edb47bbb5b096ee1bd96460eb1ab4f018d2443d4a800e71dc0391e949fbc38b4731a5f846faf74b9a1b9df249645a4892670cd1a96c3207003aedea0162bfc58cf14e9bc342f251777b0472984184c85be7cedc8e7807e6e2b58dbdf6917ed797923b4d34902058d7e4ce32a723870ebeb8e48ec4136ef7c41657204114f225b4e4cb69b584b1ef0d9215c02090ddb3d2b9f97a9714d48e46d34678e082e5225ba8464aa3e0f991b6fc0661821c00c5481dd7a1e036cccfa7aabbc4d2db5c5c2c9213c4d1740096ef860b96382371e2ba1b0bd8629564112dcdb4858068c6d2b2091b747b7b32b28214e092c00047358fe3f96610fdbac10caca9325cc41f025438476f6dca818fa13c6691a1d1e9faf4c8922070f02cdb217de486000192001c11b734be2bd7b4c9b456b6be8c4780436dc840f801fe5ee083c7bfa57905a78ce39f5f77cab1b505a478e503780c570547b84e46475e6ac78a7c6d6b75657177149f69866616a26dc09dc1807240ce0e195b079201c6718aeb4ae73ca5ef5ce2341f1ccfa1beaba2bcd184b4ba296ff00693bdf6104218e43d015542076cf1b6a978935f4783ec77314812489f11e7fd606fe3cfb8c295ecd9c64735caf8b74e49ef25bd86e1161818c6e186e0ea795debd414f3114fa0c1e99238dbef185fd85d4c93c4659378f299dfcc030d92ca7bfafa66b6a51beaccea3b9cef8fe15492e3ca2c364859a33f741ea704f3c1e3f03599f0d74392faee7bdb9b7692d2d22691b2083800927a7b55af16b8d743456f04912966dae5b7962df3107df24fe95d37819ececbc31fd9f7c52d565c5acb2302a4c6fb4b9393e84d775248e29caf2d0e1f59b6bdd735496f6f2330976630dbc44e11036540009cfa719ebe99350b59c9a7c9bca8611f054a7087d2bed9f8efe26d27e18ebbe18f15783747d36e6e22b07b292f351449e21fbaf2615318f952408d26d7cf258138c57c53a86a373a8cf21660e59f7338e0e7dc574cadb584a2dbb5ce93c27143ad788ad56764b70ee91c922fc8aa9fdee3b51a86842cf5e99edda231abab2b00402c7b2f3dbbd6568ceb63711329dee1599b9e31b30a0fe35b56ce6ef5482258fed33491b6d6277919ead81dc571d448d61bd8f7bf86d70b73e1cb4df6f2384c792b6ee3890b6067018927ae33d08a7fc5cf055d5cf87e3bcf2144d6acde6cfbb7363b03ec3bd5bf82db2ca2b6b48eda56bb63bdd675264264e63554182708aa4498c739af5cd6b471abd8bd835a430332b4414cc106e6eabcf56f63c8ef8af3eaebf09df4f47667c2eaf7361338f2f6abb32e7674cf7fad68436975346f37912141d1d17835e8fe21f055ce8fa94f6d208e4756c85ce4d67c761770228443f29232ae0e0fb81d3f1ae58c9c7e237b29ec71d6b67a84f332c3693bb2e32cd1938cfb1c52cf617f04a23b8b5963964f9544898c7d01aee9aff0055b6b947124c2450a639194b118f6039acfd5af6f358bd2d77249210dc16e48fa54ca6b94234e5cf628695e0fbabed8c0955f55c7155353d12f6c199613e781f295c609aed34cb7b35b701e7943797bdd4740debf4aa3aaa46599a273210b90c78e6b0f68924da3b3913d91c9a9bfb857912d279760dace10f07d2a217376acaa609fcd6e47eece08ef5f46fc3b7b0b8f0b6972ccb9b89c34415541123a9c12d8e87eb5d24de1cb75531885221216574080ee56ebcf635d9182b5cf36a55e576b1f279d4ae213f306019b9ca9fcaa41a93bb3671ce00f726bdd7c49e058fec2d025b85919f72b9e39ff0adad27c0ba7ff665bbdc2a49760230930a32d8ce718e95afb24c875d23e74866be937f931cce17ef2ac64ff2a8619d8ceeecbb58b7cddb1ce315f5759f852049b36f0082e7696291a6148f738aa371f0eac5a39196cade625b7cd85c60eecd2f65e64c6a9f357f6acbff003d0fe5457d23ff00085693ff004068bf2a2a3d92ee6bed51cff8b6ddfec5a5dc6f2a12ed536819c6e5c0af5cb1d275b4d2f4c92c6f2df6155f3fce8f7874ff006482369ac2b3f0a58eab616b65a83637b89030936f3dba77af60f0cf87ed74bd296de161706355d88ddc0af7231b1e545069da1c2966669a690cabc290c704fae3d2ba1d1278a38d99a42e338049c54491dc18d1a14440836956e8055ab1b05584c573098d89dc9320ce3eb41465dd5b4f71aa31121b625b25c639fa0ab1169be779a824324adfc6383f9568c9a688d63c2f9857b914f6b3461cae0b1c0643cd5d91174ccc8b4057570f34c100c98f2303f1c54f6f6b6f2010a2f97862170dd48ab925bdc2796b34bb893cf6c8f7a72e9455bcc85159c9672b9395634591571b2dba840a1db0786e467150cda2abdaca8ccf0ca0f9806efbc7f9559659923dd73104788646c00eefca882f45c4a85633b02e369071f89a89241730d34a7368d25b2a4f93b156418e7fbc2aec56b3c2e11ccd2c922e1848c1b6fd38ad70b6f6d89f2db643c263a1f6a9dd8ca8e554853fc2c31f91a98c6d12ee73c9a4cc97a248dd4467a91cecad0dafe720f38491e369560001f8d3ee6e921650f1f94bbb6b03d0d55874fb69e294cb3160cd9c03cd128dc921689259e586342153e76f2df0b9f6ae5bc7163f6eb25b5002b4adc8d9d2b6a5b2bab62658e5468436329c923deb3af0c5753ac6859e527258b74acecb608e87129e177b340af929df68ce7e952368c612c3e704a6ddd9c1c57662d64b7c028b2e3a15240aa7710ac931429860b8c1cd65289afb4f239ab6b31246a63955e3ef267a54cfa7b4123b33ab02324e6b5ce9ec8e3622307ea557fa56078deea2d0f45babb9275b58e08c9794ae5b3d940ee4f6ace714b62e33f239ef19f8f0f8774c7b382608db4873bb272c70a3f1ff00f5d7cede25f105cea97524ed2999f3f2fcdd0fb039a83c43e2c7f116aac49f2edd32073962a5892a7fdec75eddaa1d374a92ede33b4610e718e6b097c474db4b995aa5c98e14566188bf7adc719c8ff1cd50d2fc452db5f79280ec94727fa7d2b7f55b28dbed20294973b553390df2a83ff8e8fceb8a556b6d5c485c831f442303fcff0088a7049bb2319bbf91a0f7f2ebde37b61b9a41688b12b01f2a90ac71b476c97ffbe87a5729e34d766f127c4431c930b682dc450411aa8258e36a8c13c0c939ce3b5745f0eaf6d9f53bf7ba323bf9a58f94707211eb82d381bff89570f1286124e59fcc5dc5b0fc06f53f4ada31216a7d45f0ee3686d2de18e5530bf1be370cc57ea3bd7b9785ae621e5da420bc1b893f67b632b301ea5738fc735e51e0e790c3189aee6937901f0dc6d3fc1d54a0f71935ebb6d308f4c48a212ec55692312b2ab163fc2fb9b247d6bce9cad23b6317ca77be1cd490dc5a79ba92d9c48024264560e147273bb0318f535d54d7af7b70a5f4f37f68a5513cc322292067786dcb8fa0cd79568f6d797339fb6de44617eb02386de4ae300f230307a73c719e2ba1d4fc6b77a1e96ea2ead96e2341fe8cc8b1ac49b48dcc19481c8c7ca53a8aeaa7253e873556e3b1278cbc796be14b89f17c2c22c4623704a2071d14066191eb935c058f8caf757d52e92c6ed1e6877472dac1f288f209de6324ee0307122f03b62be6ff008f9f1ab51d5f50b9b45f26ea483e499e003cb8c93811e016048ee474ef5e71f0fbe205c691a9433bdc4713a3ef11306f258ed6182412c07238e47b56bc86319d8fb6ef359b8962852d56053b1e210203b1ca0c796327e553900a0caa96f942210abc16b9a9ff00638bc8e288dbac6d1de1b6328cae32ed2458202e40391ed5ca693f109e6d322d41ae40d31ee1414da18424a70589208c21e7fbe0027078a827f182eac1a2113dedd4926264623e5c33c72f404e2442edd3002d2f65eed8da351c773b1f0df8fecafa29ae7cc2accedbc124338dd801bd4b062a1baaedce49ab9e25f112ea7ab49636f792093ecb73b260e154cb1c27613c70594c4483c7eefdebe72f08f88a286ff50b2b8b8961bc8df303e4387566521baf45da31df81ea6ba0bff11c978f2411b7d8750732c537da3e6119dbbd5495cee0c0ab67f0e951eccbf68cf36f1378aaf6c35f7ba819622b909242b83b0e498c91d5411c8fe75afa7f8b750d5ed92e4c4268a5c2ba85dcb2244ac15582e381b881df07af02af5de9b6e348be5f2d6e1f7a6f64017cac05c839fba492c0e70320f5e3389a52ad932aa431fcabb642c871313e832319ade1039e533add0efada6b09a4bfbd79ee9c3dba246fb5d1111042ee76fcf1b6e311519217a100669976fa64d6684d9446e8cc489635c2c901d989029e11f86f9bdc7cb5cfa3369969793ccf1798c17c92e4031b3a8462be9940323b1e466a1bcf1b69d6e0464825a35692007765d7a104e303dab78d1b44894ae6c5dd8bbc8b2456d1468b180bb576062571b87b83c1fa1a992d34ad42da586f2c8b471baf973c67ca67250e473918fbbdeb1b4df1a58a08ccd2f9a81640165e7ef1cf1f893fa5412789edef2e5423aca4e082cf9452171f77a761fad24b97663d11a1ae595d4963f618ee6fda0509e5c331f92303be08e31d8ae0d79cdf68132cb2932065ddcb28c679038ce0f7af40b5f12698490c1a5646de63876a2b0feee483c547abcf15cd8a2daa44e37b6fb7f2d8bc60313b99c80083c7ddcd24e4a5dc88bf7ae70965a78b3c4d33ed5c0dd93c903eed757f0f74fbbd575b8ee3cc1042c580125d1b63b1baed6639cd731ad5879dfeaa42ad93f205c8c0ad1f0eea1a858ba2417af118d09ca4a405f6f94e726a2a48d6925cc7ba78022b9b6d66d3cb2937993acb2dc4721c4ac884148989076c613717070705143609afa4af2581628678d9922319e22c624c004ab139daa0302c7af3c9078af17f04781a5d1fc33a7ea172434f7e19e252ac679a33f280c481b40c31f2e305972a4c9b86d4f4b5d434e86d859cc9e70b6566f33cdf2ff78c037ef17a738dc236231801b35cbf0ee74fdab9cef8ff0041d2ad920bed62337361321769ad808fc8c76041e4d70daedb7c3292d98e87ac6ad693a46adf67bc459448ea70db4ec1b07fdf55ebdaae9a3c49e02beb76088916e224501b7135f2eddf8175b4bc68add45c162db22e4100b66b92a26f637a714b766e5d9b0822325aeaf15c3491ee5511ba95f6e9d6b96fb42cb72cec1a424e796c54ba9f833c45a5a42d71a6b0591f62b2fcf893fba79e2b3a6d2b57b69984f6170ceadb4aac649fd2b9a6a5b5b43b20d395d1d5f875b4fba95e3bd966863313ba344325dbf80738e3d6b3ae5d0a32aca31bb18039c564477b25a92928784a8e0487040ad5f0f69b75e25d445b5ba1d8cff3c84600aca31739463629cf961747bbfc36b68adbc23a7ec8c472b9c1c0c19096c963eff957671da1988954893071d6b2bc1de1f7d32c9552426243b9a3ea1ff1ed5dbdae9a5c472f931c601c955e95ec7b37cb63c8735296a72dadf87ffb621f9c30902e508e0676e79fe5f5ae6f4cd227d06dae6212ac9712c85ed84a438cedc01ec2bd0f57d524b5b9d3ecd11e25bc67064588b00026f4e40e39eb9c55fbfb4596dde3caac920e258d01c9fc471551834426a3e672516932359c425c4974046d2b212aa1bb8c03d2ad9d38978141da10e10a9ff39ae86d2d638253030333a2ab48c13664019ab02c615d8c1008cb67273c7d28e46473a30ffb1a5fee9fca8adfdb27fcf4fd4ff8514f942e60f80fc396d7fa5db5dde10e085901f423d2bd46cb4f430c72da4a0843d70338ae7bc1fa6c5a7dbadb42ab218780ce3b7d2bb7b4d2a4452b1e090338c00a6bd8b1c77b6c56fb1ceefe731d920feeaf27ea2acc36f7d14bbe472f13280a0617ad4b6923db4a62b8b49060e0b313c55bbfb4994abec31c6aea5768c8007ad3b2052b955d3ca3bd8156ce3613567ecf246582c2238f3b81073c5488639c7ef212491c31ee6aca79d0b804a2284e138352546362a8b2f35c492260797c163de89ade7b961099443b3ef469fd4d5b69448e996dec5701fdfd314eb6ba8e55cc01778ff591edfe64d170b19b711adb43e5a466400fde53838f7a4ba98456aa595b32752b5a7740daab3a40ab2bf570727f2a6e2516eadb048e7a211d3eb59cb51142c62b698097cb61246d953bbafe15123b5dea0e22560910ced91b81f9d5d92375da65992d8c9d06d1c7d2a28f4896795a55bb0a0b7750db85472bbd8d2d71a6de4be9254b829220c111a72327dea96a1611c692bc1164af51e9562fe69744b19e48479f0abaa100ede9df3e959d69ab7db76b48ac4e3f76037dcf6f7abba1191a937f675b4b390c239461640a796f402b0f4047172f2bc8519fb0e6b4fc4baca5c2cb089a482303f7d1b0c0c7f7867a52689736f2db208447322ffcb403a7d6b0fb7602ea4be62cb1be19bb0ef516a302792ce32588ce41e6af089245578c98db382c16966b067442926c60795033c55a4dee07382d0a48d21dc8074e7e5af9fbf697f10b59f866d5edef1258e791e3b648a5570ce71927079db9ef5f538b427787814afa13c57c73fb6c69df60d5fc32b630a41a7882e56458c02a656646ec060eda89c6db8aecf1ff0005920dc3105e438c0c6719ddd7f3aeef49812cad91064b162cf26780076af38f055c3c48df33343f20081bef1e38cfaf22bb9599aca0670c5bca76520f46cf3fcb9ae6946f3b1d70ba8daf70d7dd34e43232866546391c9cb2e08ff3eb5e637723c56b753a7f7822b37ce4b1edfa0fd6bb9d65fccb756f3fcd32b6e3b136e4edcf1f9b8fa85fc395f12c915a59099a2112655515780e40ce47bfd6ad47955ccea37b195e0689a2b79a4da42872d82a7905587271db3cd5bf817a2c7a87c5f98bdbbb3d99776815773236ff00ee8e48aa1e0cd44e9da6df48eab948cecf9508249f9b3c7e35b1fb36c525df8d7559dd124b711a6e8a427c95cbf24202037afd2aa29ae6089f4de8c5ae35b8992578e17642a77125c7ae0b631efd0f6cd7abe8ba44dacc896b1cc9776982cb080d1a93dcb94232bee5801debc95967b2d525b88af2e6185b28ed12c90ae1bef281c903fd9c73df35e9fe15598d8c564de6ced29dcca5bcf0a0292036720bf040e719e80579ca3796a76c65689dc69d676764ef0db9b48a540b03fd9e2124ab83f32246a4ed6c0cb3923213f8b35e39fb4578dedfc1fe1a48ed6411dddcc4c1dda4537051b2cb8639f2c676f0a01ebc575ff143e21e8df0d3c232c2b1a9d5eecaac16d1227418c6f70a0e4b862493f390c1be5c21f90fc4ff00dbbe3e9d751d6f52fb147331992295653e428f9237dc705d8be57819ca9e3009aef85a3a2472b6e47927892e675b672c67492546134f2b6e32176272a800c631c90726b908b536d25ce172482557763a57a16b9a4e96b32c5fda92361c099d8f2c8dbb6900fddc679c573b2f82ed75abc10699a85bc9b03a826450d9fa139ae9e5bea72b76dc9346f1cb5bea0b35a48c277550ed33ee0cb9cedddf7baf7cf4e2bd12e3c649737d712bcb25acef6e57ed2f1a160eb8fdd92abd18170ac339ddce2bc7756f871ac690af7491178632a48230c40f4f5ffebd4ba1eada8ddc3142a8f34b1e04283861ce369f555f4a96adb8295dd8ea3c377293f8967022dd6c0ed5217a039db267db1d2bbc8ef592782531bb2b6503200c36f21864124ed0c4027b05ac0f0b78296d8a0ba8cf94a034e00013e60fb583960091d080715d1ea57cd69a4869268e68d8eef35d5d59f68c0e198e323afbd4d38a96e5dfa126bd35a78534f1f683fbb8b7b21988e51d40da7fbdf754e4e795f7af33bbf126b5e2a9d63d294450c8cacf26dddbd57a37b0ac8d6750b9f1a6b96b14b3b1b21b41de4b0c0ee6b4f51f14c5a6c5fd97a2c2629506269c1f99c67183d876fcc56cda5b23152b3b32bea7e13bb6469b51d4fcc19c3461b3b71d3a564c9a4792ea239d6e3728002b67b039f5e847e74fb6d3ae2f26513195c13b9c6d2b91ef5ba20d3ec229176334db9f7a22ed58d762307f33a64333823a6d51cf6a398bb9c949a65ecb38024112b63a483e5cfd69d71a3dcd82a2cb2b2b3e7192416c7a7ff5eb4358d4e39352f32d91d16355555273961d8e40ab36d62a0fda75079256954b33bb16273d300f4a8b314a573220170880169086f94b06e6b4e3d4aead0c25a56f2ca1f95d895503ee8033c7bd3257b51298a16dff00c200fef7a54b61a635c0677708f9ff0056e7afb0ad231b0ef73a2b2d592e6d52de50bb50310147273ea69f657779a75c23db04b696321d5f0ac131d074e6b0a2b286c6e7cd92431c6cb8258f15bb6524166d849d5a1948c44e33907dea15a4ecd16a4e3b1e8be14f89d76b7bf6af11ea9a86a50654f970c6a10e140c06552ea9c642060a092c436ec0fa1f4cf883e13d6fc3af1e99a53e9f692a4b1c41ee911ccb8f989673925ba6c50001fc35f1a5c5da24edbd7cc5ff00966e3807f00456d7857c417ba2cde6db7d9e50edb4f99046d2ec3d544841641fee153ef5cd529799a2a973eb1f87baf5dea37179a448618c91978d083b3ebe9f8d7783c036843ef863591e3f9261f7b3ed5e1ff01f5e12ea32cf7490d958a911c30c0bb1158f60bcb7e64d7d13fdb02dda2f26e7cc47f91a111ee676f4dddaa39117cece7a0f867672d97952c8ef2018f3718207fb43b9f7aaa9f0eece284c0503c7d4b2e41cfd735d945732881879444c7a02dd7eb59d717b7f24d1db4567e6c8137b4e0803e9f5a6a2a31b5ae4b9cba339ab8f857a65d5e2cf3c6b2cd126c666504918cf231c9f6193566c7c09a559227d96211b72015871bbdfe86b9bf0c787fc731f8eaeb51d56ee2b3d26e8386b513f99b176e10af1f2bfa9af44d2b4bfb0c31db8985d88d026f94138c7ddcf349422df35ac5caa49ae5b93dbe971c76e5500da01501132430ed53244a2161b4aab36d5ddc1fafd2aac3a45ec8248e7d422750cdb5a284298d4ff0008f9b9fa9ab5fd9d7316d125c3dc7cbb7000e45558c87243108a42c06e50d91b4aab13c72b9e78a4963536ea448a8a79058edc7381d718fc714896725c485de77f236ed311019b3fef567788fc1767e23b092d6e269ed207916675b590c664dad90ac79c8aa2ae59b5d2a6b3374f0ced2ee6ded14c7201db8db9f4ab21d3844219e3c0642debe82a49226bb8c234ec233fc00633f5359705a5de9fa8ca93ce97314aab240a176bc6c3fdaef4ec4599ab993fe79251557ed573ff003d57f23fe1453e52accedf41d3922411cb084d8bbbcc53f7ab4acae49bcb8b18a658ee50074471bb2a7be7a7eb4fb6d385adb79d22bbaf452a4118f7e6a7d32c638ae5dc30770148047503f871e9f8d7a1b1c71561977713a5aab48d124a83e6ca1f98fe756344d5a54b49158172fd118673f4aba6d2deea61e7e338ced3dfe95308104853cb31aaf4607a549a3496c56b59e1b899888cff00b84e31f4cd24ba735d4a4edf2502efdd8cf1e9f5abee81511940920271b48cd1147379e644622ddb95c03c9a077284b6804ab1a44642a7728ce083ef56a116f13a888c65c8cb9dc189fad59503e7192bbce0a63a7e355e278e39e4859d92466c16e0e07e55017191cf098b06211c71be43b3724522de4570592d63dceddcb10455a82c76f12112464ff1ae41f7a5492de49e7861285d06d2ca31835564494e48ad5c1fb6c6a40380847ca3e87ad61ead6b716b30fb1e4dac836868db76c15d195105b37db1d154bf0c39aaef702e53103abc04e08450a6a2e91516cc4b1b249ede45b9c3c327cadbdb8fff005d21d020b7264b77dab9dd907ee9f53ed5a16da6dac6cf346db58fcc03b721bfddf4a6b4b740b1c44d17dd65c0195a98251dca6adb9e61aff872fb57d5a7b68af4db82e199a10391e9ce78ae9748d2adf4eb48ad8051b53e698a727dc8aab6934f05d5de61565695d164182769fba6b6ede191618c2488037cac58f55f6f7a3975e626e3a258e1f919a41c7ddef9ab1b00daa37479f43c8fce966598a09140678fa311f7beb5138fb428db23c649c1257713f4ab1924ed141e699cc8d10ff67afd2be79fdb23c249ad7c38b0d4ad910bd8dd23b79430763a952c7e8319afa46cd4c6bb2439c0cf9aec33f9560f8b344b2d72ce7b5bab44686404b3601520f6c52694b703f2ead6e068f7e605deeaf1a901b030dbd48239ea40e2bd2747bb8f50b79d5f12208d2572a0f01976f7f41d693e2e7c279fe1b788dd2e332e98f231b2bb2bc2872428cfa8c1201fe7c572ba4eadf6278e2c968d09652add33fc24f422b0e5d798d549c773a1d52d9bef60b379a54460fddc007ff422ff0085725e3324a35b7cab180657ce0ee60b8e3d2bb582fe0bc490c726f3e63618f1c9fafd4fe95c778cac99a279570cc0383953c8a52d88727395ce67c3522b69f2442dd1cc8ace25c7276ae09c7a6dfd6ba4fd9d2caee7f1bea76361933ce881b6af2aacccb8407a310ca00ea4823ae33c5787ae9ed567440092dfbc973cb82b82b8ec2ba2f85fad1d0bc65ab4459a1b8bbb26b7628f890c4cc85e257fe1f330aaee3e668f7aff001b673d51b6e7bef8b2e755d035fdd35d4691c1947ccde661c1c1dcd9c07ff68d7a3e85e2f81b4c49a6d51155633298c1e5951436dc2f1904be5b39e9c5781f89bc402fb4ec22cad7b28c84cb2a22270a2305be639e4139da3eee2b908b57d43c2f645249c4d2994b18606dc91b75fbc32391d474f7ac146f2ba2dcad1d4f45f893e2c492f6ff00569a792db7b874bada6e0a041b80047cce4b85e87b37ad783788bc783c84b18bcf6f3151c25cc8ce579cb3f5f988cb60b67ab1e377177c5be28b8f18dba09e79679605d91a4ee19546fc294073f36e249f6c7d2b9eb8d06e617864313112a9debc929819c038e95d54a938ae66612973688e36e7ed1797f2dcc92996467c991c9238e831e95d37863c272ea665d4469f79716d6cd1b5e5c223110863b72ce06065be519ea78acc9acee7fb41a6310209ddb8f0b5d4d9f8d75fd1744d4b40b1d4ae2db4cd5da26beb58db09398db31337aec3c81dfa1e38af4a2e2a366610f33ea5f07fc22b1f197c1d1e2df0aea8f05ca5d5c4375e1fd56e56fa2df1bb84d9210b3202823c24b91cf5af9ebc3be1e5b9d4a4d421d3e6b38ef247102721387fde618fa575df05fe205ee8961e2cb48276801b28ef414009328668c1fafcc01041395159b6b631de59cb712cc206933bdd642eeec537382338627a9dc0d73d44822f4e63722b848aca60f7b2c8b002444a0bf96a0b1041ddb71b891dcf06bc87c77e33944ef06e6691d4edde41084f6c7b77aebfc45e2036d609021f3390e8b20f9564070b903191c67d324d796f892ca45b2df2aac935c48cc240bd49fbd8fe950a3ca696d39866857cc80b46c4ca63da64dbdbe95d1416d1e93a73ab2992799d4c9bd7850cd95f9bdffa0ae56caf16da1416ecc8e170475c9f4adbf0feb1186783510f25aca1b3b49750c5301f033ca9e82a7725c6caef73b7d0b415bcb18f518e459608c492b8520347b31b82e58061960bd7249e9dea0f8a562fe0cf17d95c5bc8b25a4b0613715246f521815070082dc820118e7a1c73be06f125ff008635b9218a5ba4d36e660935a09c82ec3254b05237609071f2e76735b77fe224bd377e1bd4ee160d384ed2c330dcfe54a50a3337cdb95182a131a9d8ac3e503073b72fbb7ea4f3f91c73ca6f751748d1d1b6e76c87248fef56a2e97737318326f2542ed5ddc71dbeb567c37e1d6b295279823abc292aa2c825da18676b11d08eebd7dabaa823b678594288ae77ef460013f9671458bd19c3c505dc50ba25ac62357da47f10ff006beb52c967773288d24478a56de411820fa035d9cd64b360f96ad72dceec0058fa62b26ed3fb3268d9584c9247bd90a6191a9dcc6ece7ffb0dcb223bab27752fd2b6acad238f29959186dc1da3231e954f719249a4f2c383d5986053ad7cdb96dae8238dbf8149e3ea6b18e92b9b74b9ad36986ee395e38d42796703770a4564e8ee56e8c5d262e15500eac7f847a9fa5693bad92c90a2f9b28462e87a1cfa1e958915ca4932305649559484c1cab0fe207f869c927b844fa5fe015eaddeb712dc98e2625148990a36ef505b1cd7d516f6876a32a8529ca943b4fd7eb5f247c278a78920bf449278f2a5a350495c63904e72791d2bea3f0b78a6cafb4f4001803b615a704ee3dd73d33f8d63ca8ab9bf6ead1f988ef93d98a53628e486708c84871b99f1c034db54924793fd2c8842ee3184278f5e99aba914d1a168b73332ff00ab978a394086e2dccb16ec861bf6b05c0a44b58a2030980dd483d7eb52c36934a733c4b943c2c7f2827de9af672991e3965380fc03c1c51643b1014680f091c7118f9700b0cd594d8cd1b0c86038033f9559314b143e732991b77f06071f4cd460493068cc2d1afdf1267a9a2c8a22da4a36e8763939c034055c167195236f2475a924916255fdc99222db77e79a625999a2937aa88bef061cff002a2c8634e1563da9e5e7b8c1a824830acd1c5be5e993d87b55e941d803a8551fecf4a8b64d783cd594241b42a46a4724f7fa52f22798a1f68baff9f71ff7cfff005a8abff6193fe7bc7ff7c1ff001a28d47cccf48468ae278e072aaa46cdd18da73eb8f4a72419bc78d5c23a8daadea6b45c812888c09249bb6e554640a6481c3c68b10ddbbe57ee6bba5a9cfb961edc46f0eec164f94c98e3f3a89a630dc18668b86385e793ee3daacc13a0516cf2ef77e0a63e5cff00bb5339882291165c26c56c1c8fa5473302a5a5b25b998b3128df36d3c01f4cd59f30b975cb9da32921e31f414f5ba4783821d81e430dc7159f348f3385139b4b655f958a9f9bdfff00d7480923b917e7ca71e5491c98277609a9934f3022bdae37337ccc79350269a93dc8916e04b2273d3018fbd36f7fb4d20731c5e6206c8087e6c7ae28027bd963da0c9bda68fe4deabd07d2b3edf55b2370b12428f211c6382a7f1eb566dfcfd453caba5960dad92e836efa98e9f6c03c3b46c1c82c32d8f5cd003a2b03349b9b6f9128e43afdc354ef74bb3d8d0a22c370dc21dc5493f4e95a36ae2200201b1d76a873bb71f6cd646af34b748ad09dfb5ba30ddb3f1a24931d8c46d1af6d924db26e948c9057e73f46ac8d7f584b5d2268cca8e4a61b6fde06ba7bdbab93a5c8f26e2a8b856e80fe3d6bccae678dee148559659a52763103247a67b5673f746972f5b9abe1db399edcaf98082a3240cf07b7d6b67ecd25a2e32cca832836726a24bcb5d22de091a58209dc2b2c4e42ee61db9357e0babd9eda491d5622d8319e0800d5269c42cc8945eac89234a1d5bf858e01fca9f7375340eaf8f943649cf007a8f6ab423288ab0a46aec32564e31f4cd58004b68de6a09b233b40efe9f4abb0ccababc3aaaa3dbaa4aeadf2a9180c3de8486ed91e39021e7eeb03d2ad10bb53cb80ac8ab8da830557dfdeab5feaf15ac4d34a0be738837f5c77aa692d0766711f10bc13a5f8e3c3ba9e8be248e28ac6e902ab4430432b078e553d4323aab01d0e307ad7c21f10fe166abf0c75c6b2bb6173679df6d7c9c24abeea3a7e38afbe26f15cd3c9286b086158c64cd238217d864e09fa66b89f1c78760f1e6982d9ec9268e4fddbb4709c05fcb9ae494bf94b50e6dd9f0ee97ad4f6738f330cc0e595be603deaedc6b1f6965f3d9886762641d4039ea3f0aee7e24fc10d5fe1e4fb9209af74a965f2a1bb103293d72ac08c83c77183d89af339ecd8c2c7cc68923c16c292403bb961d475ad2294cce5ee687392c2747d565ce02673b768c1fa55e96d843e2ed3e680e61b860395030c54a633f91aabe248237b3b69012d3e093ed838c1f7a75919aff46b5639cdb5da3ee3cb03bb247e1c7e75cd38bee546563d517c3f3dded8ed910cee8af22ec545098f98e483b867fbb8a8758f87977a8d9cf7226802c514a19d47dd6e1444a4637904fcc554ecfe22b5d278182a406d92794b4c856411447ee9ea198e47e58af54f0df86adb54892c608a4896d543cced2025721f6e4007bafb138e2b964a50763ae294e173e26bbf0ddde91aa46f2c2e5448ad1b95251f1c70c38231ef5e817138bad1e1b79603280723cf3bb7165c07c8c73ed5e95f147e173e9f771dd25bdd9b75224f36e0962e4f651c2aad71779a10d7ac91af648acbc866df712ce195461cab6c18cbf2bf28cf435d94aaf2e8cc25479a574cf28d4ece6b39c46a44d160816ce76ed00677467a30fa907dab397ce91c7956ff006739000b9611c7cffb4c4575facd84da75fcda7de411ce2063912488e0ee5015be562109cf404e3a1c1e2ab69da4dbc8014b579311ab9789093c6dcf2bc67e75eddc7a8cf646a72ee8e66b9742ea4f65a0784e6d1f4f97ed57378c936a7a8e0c50ba8ff57122b61b0a7e667382c7a8ab3e1bbadd62228e30638e391b7701cb13966cf5ff00601c62aa78874d7d374cbf86569239d58452205f994f3be220e7a019f539e33ce336c2e65b8d2c203b1d98202a30e405c30ddfdcef8f5a8a9539fa58714b9475d07d66fcdc471bac103160ac770660d9dfcffe81d3deb84d7f519aff00588e212edd9b9430c7cac7dabb8d5b508f4bd1483c654aae3a927a1fa5719a36906f3538ccb20894bf9b230424a9a9a7172ea2e7f76c5dd3f45b5b1f9eed7cb8e33904b1c97feed588acdf55bf9fc946b78c7f093f2afd718a76a8f15d6b5770c1b8d8d8a120b7f7b38dc7ebed5ad656d72ffeb5762b2a8771c024fad68a36dc952e6dc957c2d6b6130bd599bcb55dd1c8c76b32faee19f9b93fa53b54106bd721c5c34690426286394872a8b148cea8c00209666da483f786718abd3c8d756d25a8657400211b386cfafa7e15774ef09fda2dc88d41718c703018293c1cff00776feb4f9987b3d2f7285ad85ec8e9692c7259498cbdadc2f94c0b2fca5c1009046182fa30e474ade4f0adcd9da2cf28da5bfd5c6eb8c8c641cd7096faac9a0eb2c7cc636d3221dc58e186ddabd083f280a3af415d5ea5e3fb8bf8e35625996254505b76d0ab8068e645417bc32f845631ab02d2c8198b48738461fd2b0f50d52e27da24daac0ed2c3bd3e6d4aeb5095c63f77185c61b88cf7fad50f3248a250b0ab867e379fe7e9f8d61cd734924f434525fb4ed8d17f78e7195ebf955f100f256342a189c364fcc2b966d4dec59194242d2364b29ce3e9555b589e7791b2efb9bef6318a7e62da363a9d56f6ded636b645c964da07f0b1f6155fc37632ea9a9c5072626701c77e6b2ad2dae2eadd9f24448701598b62bd83e127870ddeab6ed12e640cb202854bc607a293cfe35126c704dec7d35f0d7c273e9fa4dacf2dad8ce563f9e58005b851b14796e7389391d4015e936569676f613451416b1da3b65d644caab49d495272a6a0f0658bff00678f3c889f6ae6352109cf5c0c7cacbdf19fc6b766d319f7ac71acf7214bc7e628259d7a007b7fc0b34cbb232458dc5922adb490cd006c88464ca9ea548e587fb1d3deb4ac6e1a6b48e68e713c2cdb4705875c60e4023f1c5491417f05b461c244c77798b2aef74ddd76b0c15fcaa6d3f4e9e0b75315b798a15bced8e01721b3b994e093485644d1bbf978f2d9108f9997924fb551bf027864113069d18e0b360f1577eda6e06d80340ec3cb767520abfa0edfad3ac61fb233079659d839c990039cfe157ca98ae7076bac6a6355685e1b886787e57cc44fc9fde1db15db590f3eda32ecf2145e515b6e3eb8ab970b2a23c96ea9e611f287fe35f42735068ef3caacb7891addc6b895603853f88e2b2b0c91e09101c158a3c6e019430151adb7931b0f9a1c9fbe1369fa107a7e35a33297cc4362214e460127e95c78d37c4d3f8c5e67bd863f0f88446b690c1b58e7f89d8939340b96fd4d79fc45a6424acf7d66a2304ccb24e8a57f32064fa75a8b47d534cd45aebfb35d26962540c718d8a7a7b64d73dabf873c3f61e27d3ede5d105d3dc86ccf18fddc4e8bb8165391c8fe2ebed5d8dbe936b68505a47143110c36c6a0633d327beded447595d9257dd71ff3ccfe9455efb037f79ffef8a2ba2c8ab9e916f72aaa48453016cb313d3f1eb5047307b8695019e52d90bd97e952811ab3200246719cb2e33f4156e2b4fb38c9b6f2b032704122b5318b2959c3247a84925c45b5ddbea57e98abb71a9a79a216563221c60e454b23081df2e704ee0e8314cbccdd2fda0230080e1dce4923d6a062234564c240a10bfddda339a6dcea2924db1507da5cec7c1dc0afbe3a7e14b61e55c26e8048efda539e3e829fe5c76f24cf3ba4923365ccc8723f2c5302296c1e6b98d9d111506711b72df5ab492848da58d1de451b4203f3e3ea6abf9a97b20368a1644e5a6c9e94337d964120b8567070d85df8a404cd7324c153cb972b55f55fb54968444151a41b1b77de0bea299264cecb1ddba6eeb0ba91fad58b486505de65f3360daa54e462803313c39235a2acd7ced223663038c551d423fec952f1168a441bdd4b655c7b1ae8e768da233c9b54c232769ac680c3aa4ce72438e3cb61f281eeb4ec55caf0491dfda4ca8a72e370dfc607a815e6fade8b30d4647b6810797b8a3b7cdc9f4af4cbd9dec123856365641bf7a2e723fbb50d9e9714f7325df961e4233c007f2149c79b70b9e59a1f8467d46fd25d5d03c58f31614182e7f1ce2bd30e9e8618d565f2caaafc8474c7ad5f492ddc4a862d93371f7305c7f4a8eed433ec16e65882e197a67f1a95492ea176673e9ad2c1f34a18a8c32c9d07e34cbbd62c745b14696e5605c676798369fa679aa5a8cf720c89f619a468c6522c111c7f56e9f9d713e23f085b35e2dcf88352b0d391c6ff002a5b90640beab19e5bbf407a1a2a370572926f637eff00e2b6916932db5b97b9ba9c3058c03b4e3b96e80571f6faf5deac65999574f82495d23372851b6f3c8079ed5534ed2f4fd17536b9d0a305a555492e51155821eabf339c2fbedcfb575b078452ea732340b1b61be76cb970431ee4b77ec0d70baf795d1d74a8df731b4a3a1644c227d4ae6293725c5ca6d7cffb025247e95e89a64177ac42ad247f67b743b879c73f83631cd73ad64b6e8ab24302a2be0cb871b3dc3b2f03e99aea74f8ae0d92882e44512904ac0c9212c7b10c54d2e6e6d86d28ec47abf846c6ff004f9ad2e624bab69862611dbaecc7cdd86e27af073f2fbd7ca7f1a7f66ed31629eef409a087cc65fdc3394759477c93f3fe3815f5f3c06dc233b3152bb77b3e0e7dd45719e37bfd32f23860bc924ba20ffaa102bb6dfee9e9f9d56b0ea43b395da3f337c45e14bbd32e2e74cb9f92eaddde43860d91bb39e3391ee322a1b584e9169a5b491ab3bccd1cb1b6402aeaca1cff00c09d7f05afb37e30fc27b5f19e926eadade3b2bbb68dc5bbb32acacbfdde3a0ff649fc6be493e1cd62f7c676fa45e4123adacb1bc8db0a8088ca40391ed56aaa7b99a85d5d1eb5e198a0d2a5fb31b7782dcab01989d43e3dabd87c317561a4db4113dcdcdbfda657616ee4246efce3098dce57736d0480bc735e5c5bed4aa4b18d55c23209779723ef7ce48c56adacb7ead6f2e8e0a909245f69f3577aa0f4662429f719ac1fbf2b949b8ae547b66bfe1ab5f1468f23c71c26c60558a7bb9d1433b670503b28cb8c1c85538c73dabc0bc57f056eb4b824bdb3820604093e4696598303950f2a6d446dbce6bd534ef10590d3d1f54b976f242450c103169ae18f0123ea06f3b73b4166e391938d0bcb8d3efa06b37867b978c05d83679519278f99b76589f97002e3be29c928045b5b9f255f5ede594b1d9f37d7368a5a377822b68edd82158dc67ae060ae36aef04e5ab3560f3ac5a5b3b009776f6ced733dbb895e6659c6f9e7dcc4ed889182ca774833f7b95facb5ef86365ae4d059cb63a6ba44b24452d64904683ef348d202a3ab365c90871ceeae47c6bf0a346b5b18b44cdc5f5db887305a4656c6d4c618aefc05cb005b01c6010028f98b057916aca5dcf937c477b1c1a2fd8c598975570d732de3bb3ed8801b22524808a1732484e4b96500aec39c0d0f53416de4c8019543ab3b0202160495c81d78af64f8dde195f05f86ae2cacad8db8bb68bce32c5be7920591d9a4793a2ab4a426d8f682428ce233bbc8ad336da3b7938768f632c60e761dac0b31ef9cf15d145bfb4655a51e863eb576352d52de38d8bcaaab14808fbbb7a6074a75cdf0d12d4188f9b3b9eaa703e87bd54b7716ef3991c0973bd517925bdcd5cb2d1b6466fae0824beedeff00def402ba97bba9c9ca997b47d1a436ad73710e44f22c84138d9c7cabee33d6b76dd84ca21907fae89c80a7818e86ab0d48a431da6d25a55650a7ae7b1a222b6517981c23e1a2604676a9ee2a652b9718d8d4b487ec86232e044ccade4a3672c3b93e95bd75e31b7d2f4f96da18de4b972a1a7dd80b193bb685c7de0ff316ebdab868f547b74565c98d46d2e7b0f4fad269527f6b6ad0c32e58175c65b1bc1fbd5316cae9ca4f6be139fc4fa8cd772abad8c25879ca38964fe14c7615d3daf8734d82cf6cb046b3a0dc84b7dfff0066996bab369d04c236fb2c51a08d622dc9c7ddf97fad7157de219c6a0cca1d6207786392169d98e29a91d0de1b14793684b5f31b6e554ed3f5ae5753b86d9f672db830ea3820ff00b5eb560de4b7c132ac78ddb58d36787ed578d950193a15e8d5318a2bdde6b94ee2d3c9b8b28f7acca78c95e09f4fad3e7d385aacb340d9dcff002a927fc2b56d74f11cf13cae720ed29b7ee7bfd6935a1f689a28636f341f97318f94b7d7a0fc7154f48ea4bd7619a6581bb58ade14647739778dcb1fcabec0f833e048fc3fa4d949751ed9e67597633a67e6e8bb47207d4d792fc20f8593cf32df4d0cc504803845da793d36f5f7fa57d27a045e6ea51dac104ed0c076185132aa474dc002cd58a6e5b9aa5caae8f4b87431776f6c21c3bc7ba42a7e5639ec5bdfbd7456b66a2140b1958c855c93d3fbc49fe2358da0b868e3559da2589b604299ddf52715d0c2b899f7ce5e20430070010df787e15a90f5d8c7ba8ee2398016af1b066669c91ca8efc9efdaa4b3996193cf724131229de332863d777ff5b357e7b6779e2992e7cc8e353fe8f28dc1987dd6cff4ae7f52d535cb4d5de74d2ad9ec21898f98652662777089180495d9dce0e7b54106d22dbac92b9b86f29ceef2df0c81bd71eb4c9d2e4cf108d164b6dae5a46c8249fbb8ac1f0e78f66d507efb4ab883cc7629b81098048fbc5703a7ad6ec1abc772af2a5bbcac8fb576b73ff7c8a232ea3b12cd6ab26f84b283e5ee40bcb2fb7d69b0d847a6431a2e447fc04642afe1de9c2e9a09e2536bf3c9c8887cb83ee69f2ea71b8651033ecfe1cfeb4142ba4aa4348b0f940ec0c01ce7df8a82e96e6189fecaaadbb69d807240f4ad049217cc614a3eef9d0bf0a719aaeb7b6f34ef0424cc369f91410063d09a7608e854997c9282489e50c412823c74e3a8cf6a55bbb769822a796243800835a50c2f68b26f44481792082bb47a9e734904b098f11b0f2b395dbc81ec0d2208bc83fdd87f23fe3454ff00655fefa5157703b8b7d3e585b7cec5d88da33c60fb55f94e2dca960d1a8c6c51b4b7e34ad117541bcbb2b70bdc7d692e6378543ed040fbd5d17318ab0fb61210cdbb6a81b7695ce6a237490ccfe6c619fe6c06ef9f6a9adf7988c870501dcb8a69916e597c9e5a3382e56a0b2249d5b78c98f1f7021c03506c9b53b6c5cbb4312f509ceefc6a7b9082f2091ad99a4ce3781803ea2a7b92be52b796369e8a0fdefa52b0ec575857ece22dfe5c68b8f2c0c6efa9a8a2d4a38a3760a8af1b73b38cfd6a796d1af62f2e3668611f7971fd6ab47a1c76d7121d9e7b487734ac71c7d2988b16d70d76cb70d6cd240dd32401fe3565156590848dd13baafcb55d45a5b2942cec50653af3f80a9acefe12c76966246786a00596cadd19a55f9262b8e48c3fe150c706d577580291fc4a3afd6ac5ccf0c91ba3c4003d182f23e951098c76ac17e607b03cd5dc76395b8d4ae25bb9edf0f1c919da0271c7afd2ade8ba3b60348ce093f204caf1ef8e9f8d63f881afa09c4f0b2c1230088c413bf3ebc55cf0bd96b568aed793dbcc92f051576843fdd27392685aea05fbb824b7b9db04670465ddcef65fa0a74f1dbc56b2492bba6d04b195b605c7f789fbbf8e2a1d73c5f69e1e8e437c6359163ca21ca6f1fefe33fa5792ebbe33bdf17bf94939b4d3d371861b7522365edbdce47fdf38ac275153d0d6149d42f78cbe225d9b86d3f4bdf0b8f912e65922552de8242b91f815ae4e1d1519e3bcb9b837da83960ac93197c9dbd55f05b03fde2bd4d74b6bf66d1b23cbdee837178603101f43b4396fa540faad8cb7cb0259dc4f718dcf2dc974504f5450c701783ca1278e9d2bcb9d494ddafa1e9538aa71b5ae2e9b0476a8cd2d8dbcaca559a4646214ff00719c0c7e59adab4b82d2b81651286f973112aa79ee09c9e3d08acd9afa111204821b788a85126f28011d783c13f863de96d75411463c811dda1180dbd5b27db9a51b22dfbde44baaeaf0d9dcc4229d6de5550a5b7a9600fa166da7f1adfb3bf8eea30ecff2471a8ce17248ee593393f4cd717aa6a0f72eaed3b2a6e8d5226652ac4923a960a3a776aded2963b4b18f37a1a390fcafe7ef2e3fd8445f96b689cf389d2fdaede40c2302495be6daa037fe3db3fa5703e22d691f5a8c6c324d1aeddb1a44a33eef8c9fc01aec218acd422b4ca85cfc8d246b26e1ff0002535c37899fed1ab346d725e30ebb543282d9f4c72315526dee610f7b464aced2db4464c99e40d84b6c6707d783bbf4af3dd7bc156569ae36ad25bbc7398f1be1da8cabf4d841fcebbc99628a3db1ca60550a0791f2601fe107fbdfe735ceea6e35090aa42a218482acfb8191b19c7271b4555972dfa894b95d8f20d66e1b4fd5a6796d60b68cab05dcebd0f7db8c6efc6ba0f0ff00882ceded95a38e3332fef9646491ddc9fe1e3839ed81f9568789fc236bacc32fda0cab1c4a5c85652011d79f4af2eb2b68bc39ac2dc1b880410b2fd9c4aa265771f7a46dc08931d8367f0a718db506d41d8f58d33c2d786297547bc95e4bb658a586191230bf367ecd1b8ce3ccc7ef0ae0fcc7e65c0ab5e3bf0fb5a1b4d4d27f36489238845696c04512f21634f9b03a12d924923ef54b078c25d6a5b18eeae0cf0a90026c3b71fdd0158051eab9dade82b7e6486f2de3b99e365b40d882dc3159246cb052ddb24b11d3f4e68e5e71c75d598de07f19db6a9a839bdd456d23d9fbab759034accbb55d154020bfca4b3618e09efc57632683693c76f7973283131778e02fe5a6d01b6c606773b924176503001dd9dac078ef8a350d4fc1d73757c96d6b13301125bcc4b28543901b041f2d58167190cf823356a6f8dba6c6266513c96d20cde6a21d629a5551be5080e42abb70146140ecd920d596c12f79dd1e79fb5cdd5bdbf86b4cd116685aff00549d7519ff007657fd1846fe49c67f7719cb948fab28c9000403e74b8b336ba4ba06f2e52aa92154e582f4e3ad75fe26f105cf8efc737faedcb492cd3ca15a6399252c482c01fbc49c01d40ea46d076d729a92f99e72a472230dd962dc8c7fb55bc236dce697bbb9cfe8fa435e5ccb70c36a67084a7246719fa56c5e5c222490469f68bc501d02f4ddff00d6a852ed7fb3a0952468be4f2f6e7b6ece69d059fd98e65dd136080bdd73f78b7a0f4cd692d6363384efb20b4b70156e67632cb8c123f8dbd0fa7e1505ebc91850e03338cb2ffb3e9f5adcb687f71208223b436c56c70c7fbc3deb2b5bbb8f48f311e259667193e61e51bd38accae64ca7ab85b0592dd82493e7613bb2604fef71d4d50b4d4bca988450c0152c3380f8f7ed469f6c6f639a79988690f99b1463cb1e99ee29f61638dc154123ee8ee6b4492dca7a1a97fa9bdc00c8877e3e62d86627fdeaa4abf66df2890b4cc9f31ce40fc2aed8daecb7495bf747696fbb9ce3b0f7aa87521336db1844932f2d2019e3dc53f3279bdeb96224f2ad84972e20511e739e4d0dacdb10a96d184954e3ceeac7f0a8534592e0892fa5f2940e1376703deab4ff0064401205e11f0cc07393d07d4f6acedd04b595ba1721beb8f30c31190dc312790496af74f84df0146b491ea3ab44d292db9204b868dc37ab946538ff007985731f077e17b788f53b1d467f2dd59e3296b2c2ea5939cb038c11c7ae7dabebe6d01b4db5096664b78da2c18e242588f5fbb95fc73d4573546f96d73aa31472f73a15b784b4d8acad0ac534e54430b92e122c6380e58e31dc9aea742b3b97b11985e62a37aa00a1318ce7e5193f9d70ba215d3bc4ee36bcd7259d889e4f31d89fbb863d17fd9e9ef5de2f892ce60f969219420692099b0402b8c0c638a516ed72baf29d37843539535036eac6d559b02146072de9939aec2fb589ac5017b7bcbc9a2750655518c16dac7a74cd790787b5f9ecb597902c16f131f941e40f707d6bd9ac1c5fd840cb7fe4c8fb1f679a1b7f7c60678cd5dd933496c51fedcbfbc50d65a73c56ec71248f09052b566be8e0486136c48986d59e3036c7f2eef9b9dc38ad1b0b6f36792ea4918b15c98b69233ee338aaf7165158aefb48dda573b8b38f971d3e6f5e2b4b7533e65d8e5c5f69e350d49ac3cbbfd5210b15c5bcd2157dbbdb2aa84e0af3f780ab5648d35b8b9834f7b6b8c832c63e5c67bee19ab977e0d8afb528f5df2601a9c712a0b90bc950d9d99ecbed8a8b5ed4752d474fb8b0d26facb4dd5119774d796a674083d5723f4cd3b2e508ebb9a169771c4881e60ae3190f87c67b66a232c77973fe89711db5c46e103851975f4c13d455bb6b0786357d462b712ec5679a1c85dc3d171d2abc1a7e9eb32dc8b71e6e4bc53480fccc7ef714acc57259eda1bfb7c4d2858dd416daa3392b8e79aa965e1c874c5016ee667c3296946464fb5694faa1fb4f97b1e28d4aa7da0104123d4629d7970f0cf1c2f0b35b39f33cd19e0fbd505cacb0db825e59de491a3c30958b657e9eb4cd454d858dc1d3ec9aeee4282b02b6cf35cfae7a0ad089adcb1064886d183b72580fa62a5b8489b8924fdd02a0b75d98f7a9b12721f6ff00157fd0374fff00bfb3515d8634ff0054fcbffb2a28b01dcc8e5612c76878cee623a91ef45a5c2dc060ac5e4719d8c38a7c902127732b9dbb30cc06f14c92e5608a3c851281842060b5745882588c5336158c33636e1793f974a8bcb1100a6464de72d93d3f2cd55852eae6da4023f2642772ab704fe356e259218043242be611962ad83400f3770a62399de46693193511b156b8799583141956c9f97f0aad24260f31e3469e790e43161b87d0524b3cf3481a5905bcc9f28127ca5bf0a12bec59a4679c7fac2a91af42a0fcdf5aacf35c5c2b4bbd65dcb85555c62a2b6d621b6548a6669250705c9f9bf2a7dbea515d2a1b780bb6fe9d302a6eb626c321b2c5dadccf13c8ceb8d8ac140fa54f76913238312c6a570a42e0d5a689120605576bff001771f4a3ca8dad963672e5464316c7f3a6162884df1a809bd41c2e5c8fe752f92b126e788c3c1393d38a7c56ff00be12c44b2839dadf303f4a8af2f2186ee262f23b95600afce8c4f60a33fad1d2e56cec64788a34bdb68e2fb40b6919d7cb9930c401df1dbf1ae4fc49e31d3fe1ed98b769e5d47579632d146f20dcc47f110060533e25fc53d37468ce9f6805dea03e77d8e02c47fbec40f9be95e3f65757baa5f7daeeae4dd5c4ecc73e601c1ed9dbc0ae6ab89e55cb1dce8a541ca5765dfb6ea5a85e3dcea66382e653e6c51942067fbbb58918fc6b6ad6dd220f3ced2a5c3fef15630ac415e8a141231ed8cfb516f6f67651c624b6844b1aeeda818e0fb124d7377af235d1724cd0f9ccd15b4b2aa46e4f708083ff000227f0ae077b5e4f53be368c6c8d46ba864b990dc292c5b72bb460b13fec9c7153d9c924eacbcc688db6349632377b9524961c0f4fbbee6b0e2d5cfda045242915c1938114464083fe04456d2c72c76f197b40ec54a8f3154e00efb11547fe3b590cd25fb558d88964bd59ca1650202b90a7f84850bf9d4567af46d2f932c3234bbf72798fbca9fa313556d498ad6790da4114aad949133fa815563d6ee91a4b694a5d84605a34711c699fef0238fc5a8ddd809350b3babd9d2f06673b95f64caa14821b1c7de38247de22b77c3f14b7087cf9023210a228f97dc7f8776d001f60735cdc972ae2492ea2b68012fbbcfba2a64c0ce32402c477c0c559d17c65aa6ad0f930dc2f951ed431ab845c0eedf2fe8327debaa3dcc2a3477b0436b6c819a3904aebb9f710d21fa28ce2bce75eba8e3f105c4d1cb2c72240004c212a3f89b39eb5dd59a816a89744cb263fd4ae6518f70b823f135c3f88a1b5875846b78d177b70f2851e61f4c1390bf5a529339e9fc573320bdb8bbb259a3c12db8209532370e84d466deda280fda20175228397994ed04ae0fca0ff003a965d4238ef04323c8b2b65fe553f2824edc718e719aa9677acc5ae7cd26142d22e4f08a3239fef9c8231d69c1b7b8e4f5d866a72c8a52d442b179e02c51c71fc888787c91c9e781c75ac4f1678474eb8b15fed482696e5a38fcb6420a01df62e06d35d568f14b79732df4b182005890194204cf1ff00d7fad3f5f8fed4641202824da02202ccc07a761f9d74ad63632945ee7cf3aaeba9e1ad59edecaee58ede36f2a652bb5211ebbfa67f1aeff42d522bf747b197ed2d090cb3c5ba6557231d8f61f28fcfad73fe28f086930eab6ecea164c637c89b9a25f50376d56ff7715813dbde783ee37e977973b49597c831863c7f7a3cb81f89ac2edec69f66c7afcba78bbd36412db209524e1ee63326ecff0019032cdb7b0c73939c5783f88f4b9fc33a9dc5cf977377629bcece23e47cbe611838563d10e0fa815ee5f0f3e2641adabc0f32585d5b90d224cab12a7fdf483007b3135e932e8d6de218238e5b7fb62bb098988a3499238d8a3d0f42722b5e4d2f7d486da767b1f01dd6d92f2e1aca0916662db1cbf98eac4e026463a6464fbfd6b1efec85edd2451b23ef88b0d8df7b69c64e71f788217d71ce060d7d4bf10bf6748eee6f334380c334cc5d124983e06ecc921dbc617272474ddec2bc33e20fc14f1468e97b766d24d9b95256890e092b9da38e0228c1f4db93c934e2a51dc96948f2ab175d3ade4be5843a9ca5bb373e528ef8ef52c5ae406193ed311498045f3b82401f7b68cf1f8e6b4bc4fe07d7343bc8acee627f3feceadfba5382bfc7c100f191938c73c13cd73da8e892c4766e08f81b9597a13d8fa1ad54ee64e37f84bd7be2c16b6ed15865401b84eebbbf2158691c9aa4e93dc0210bee6639cb1fcaa85d6fb2ba7452cf70170e47dd5fc7a5591a9de08512472d1ab1c0efc76a60acb746fd80b0449a1925d8ae7cac91c85a75cea89a547235af972281b3732f21bd0573af234570258f3b5be6639cfa7f8d5ab5b3babb4cc6032127afdd3c6720f738e78f514e5a90a3cdbb174e95b5cb968e69a48402cfb790067d2b7eda18f475b87850a3edf98edfe7fe7b8a7f87be19ddde4a6ea47fb35922191a6319739e36c60039323646100cfae2bddfc2ff00b3e7996101bc50fa83909240cbf77a651b9f95970320f4c1aae7f76d636e5e878bf87bc097fe2b896685372c99618e7701cf1f51c8cd7aefc3ff00d9a9a49adf50b88fed10302c8e392df360820ff1af5c7a57befc37f84f6ba2e91948959624850ce89b77b6d082423b70738af4dd2b474d349c448b1cae55df00212231f301d8b03f7bd7b5734af295d32e31b1c2e97e14d33c33676e9258d942cc6375b8b489e169d8807e674231df86534df18fd99e69ace58d238fc959237203cca581236f432afca3a0cfb576b7f25ac9673aef224da4301c101580da47f11e71b8739ed5e3be2cf17cb7163369b70a97a6295a2fb2aa6d9222a5be7565c92307a022b2943ccbd8e0ed2d6f74dd6267898ca3cce92fde03db19aebe7d625bdb613452b098329dcf2b33ed0790c33f28ef83838ed5c4c36f771e2ead63936b0e581dc41f71dbf1ad05d42781e15fb38176ee81a597203ae36be719d871d19bbd691568ea45df35cf40f096005b8b974de24c1464257e80f4af78f075bb456cbe5488dbf636c0aaa597d776323f2af03f06c91c37912cd06eb8ddb14347bf747fdddcac57f1c57d15e15584db471aaeddcaa58950c5703381ed554b5dc7235e2b194ee93cc6b78d9be685581c2fae7d2a13788d7af6b146d2b1453f31c2f3ee2adbc6b179ab232c310605518fcae4ff0006ea824b0f2ae1668643144a18cb1a0dfe62f62719c0adecf96c656058d118bcca12709ca86fe955750d3c34b1de3da3c658796a71ce3e9deb5a62488e5882ba4830d200091fad52105b4f21bac67195dd2960aa477c67a54dc77be862ea7f10b42d1b508f4bd4affecf3491a1473183112cdb71bb3d41eb5a8ea96fe647360c2b9c4e5b79739f41d33d6bcabe2e784d756bbb6d523d22795ad8ed6f249dfb4f3b801df3ed5e87e13b99ae746b4b9bbb3934eb868c2e1c6085030a48fef63f5a88372959ec5492e5f335ad2ce0489dedd7686e59dc1ebf435619964836a4ac100c107b7e74e481d1fcb32cb283f7a461907e8298ed2c8e519562b748f866c3331f702ba22923313ec1094dfb446e7b8c2b1fab50d12468aaa7f872c806771a985c24b09757864546da5c104557b99a68a17fb25aac97617092330dbff7cd64f4dcbb20fb48ff009f66ff00be87f85159fe7f8aff00e7dadbf25ff1a28b8ec8f4717a65cdbb9df26323710a57f4a2d2df65c34b72a1a6538593fbbf8532f2592e6445b728232d87dea09c7d7352fd9cc7221939655c9e7e5635bdcc6c364b999eef626672eb8cb8ceda4bc01f6f9d36d08b811a01f37d4d598ef1044ea6548dc86271f7b8ac6d4b558a248b2ea4138de547359ca4f702e1912111aab22cc130091deb3afd9eeefe265f9a451cb37cc01f7cd65cdaf4711c315e795909ea299fda7f69b6f3252eb16fc850bb41f624f7ac9d4e5d8b49b2ddd95375b5879cf8e5836769f7238a7e99a93d9a790241c37ce91296c7e99aaf6707dae232452b460c9c98f1b187d0707f3a69b512cb201244d2a8de619e321c8f72083f8e31ef5973fbd72f96c7450616567e5c2aee44ce41fcaac6a174b9589ad9dc32f10e377e39acdd2af648ac937619d7858d14057f6041fe78a966b99821bc9f6dadb007858c8c28edd739ade2dee67be85e7bab6d36ce469e44b7b5c7dc770003e809ef5e35e3df89536bd24963a4868ed95764d7c0ed63fec8e83f1cd73bf11fc7d71e24bb4b4b29209e0462a96aeefb881dd86ec83542c3494b8b4595aec41144be608e39046bbbd97e6cfe35cd56a36f9627552a565cd233869532cfe6348a212f82f228c86fef649e7e95bb627ec968278ede595d86d2a8c15651feee38350b1d320b75c48ca23600081369627fe9a371fa5537123c8b74674b6404c8a7cec1da3d16b925bf31dcbddd8bf25ecb3dc2a4768dbf6e0b48d8c7d7d3f1ac2be8eee3469cc219198c63ecdb84847fcf3cee38cfad598251144ed3348c0b6e0cf94623d181c66b3b52b3bebe9a18a2fb38b39486cb925f70fbac0023f5c567295c6a296e4766a8b8964616f382079325ca31da7d727ad4f6fa816ba41203765cba11029dc07a2b03c9fa55698476b2441d22b708caafe4478738f562e6b574e33492ccf14b3cd23b30892d6d63da10ff0011cabc8e7fe04b5055917e629a7449218b01bfd58681807fc4139fc335525592f6e0892e599e3db1c9120de81ffe06180edf748ea2ad96d5ae2ce57334703b295665fde48e7d4b838c7d00aa2df6f256396fe71206548e08a6298619e482dc9e3f414e3a3b904baac1a644e52549af26d8ca9208db68c2e362ee5c2e7b9abbe18bbfb246aab6af0ba8c992638917dc290327fda383ed592fa7d9452bce22fb54815d7613bcb1ce3e695c3283fee051ef57747bbf2ee9a1bb4f32eda51e55a450388901edcfcce2ba637e5b1c92477da65c3c88f24a8ac8539123fccdec58ee27f1c5729e3c9ee6228d1feed970710a089d49f52a14b574d64b398f31411b1232cedf29ddfec632107e2d5cf78b6045b4692630c10b2a955b18dcc871d8b73cd12229a67256b62662f2c8be7c291acb3c832aaf23164049cf1b41e056445a9b5c3c56b36d5f332d044060242b89198fb92a00279e5b8ae861b959171067cb7e5c0c6f2c0636efe99079e9d2b9ebff000eba5cbdf191a6214968d47caa3764927f88162171ea454c646f28fbd73a6b8b98ec60855d59dfe790c470d961f772718fd6ae299a689161e10aee90038cfb13d8fd2b9f7d4d2e522d3cc6639d8a89911b0b1ecec07a9ad98a2ff485492398447f78580c6471c91d40e7bd74466612833226f0f5beb97204d124b22b60055e107b0e87f3ae7353f0b2477522c0fba462b12bc4db24407d0f6c77db8af425b45b5768c38db216645cf381df347d8e6259519a59d94aab1c0e00cb374ebe9fd2897bdb13b1e35e21f0659db5ba1959e396da365db21c2c5eb20ce4027b0523a8a8bc3ff11750f0c59047d42f2fed8957fdeee545f65dad929fece6ba2d5bc32de26d4595c2cd6d6ede74b24c08407f8502e727a0cfe3505be836d0db5ec86dbcc86d90b9690e3cc76384500fdd1f4cd3e61af7b73a7f0a7c64b0d46cfc9b6867bbd69d5999ae5499304653e721428030550020e46e26ac788960d4b4f582357370dc96907c803958c3939c1255df705c938af2dd2fc29aa5ddbdddf1f30dd166789482a8b86dec157a9555f94671d05675bf89fc47a4a9babdf2ae1a49d4a86873271931a9cb00c4e73b981e2b6f6a8ce54da5747b6e9fe19d3f57d06fada510c258fd977c31aa068231e5c685be5dab825b05b92dec2bc33c4bf0160912796d1246b36c7992491311063392491f39e3e9ef5d8f87fc6d756f386bb1e5cb1c287ecf0a1e70b91b98e429cff740ab1a67c4179e598dfdcaa860f1ac0c599238ce370dbd077e7268e652d90a29a3e77d73e165a9b9b31691b318d62f3617014095b7f98801ea4657ae0706b56e3e07836ca4db98649893100990ec481953d180cf24138ef8af74bc4d3bc52bbacee2dd7246e334609c76c375e7b9c7e75a70e8d1790cd34c92da470e1e7111042203962e0928a0e318603d4b54dd84923e54bcf85f0c6ad1c29b25750523da39504ef6e7b1e08fe95734af0635a6957696b1c8fb664625dc6c8f6c80bb918fde10005c0c0c038cd7d1b73e18b4b889d6ce1895e70122597e6291018623be31dbae7b545a568e4dd35b59a7da2dd10c53dcae36c447ccc1090065311afb966cfad424cb8d974303c17f0f65b1b4b5b49e410c8f73992403e7caa17603b0242a024676938ce6bd82cad6df4fd3a68e025beceac86664e7cc7206d04f21cee3bd8e4fa815cf58e8cd6fa9da49e762e1d32d76ed84863e0b6d43f78e73b4fa80c73d2adf887c55068b15b069731e4c96f9f9a554de773a2ff0e78f99b25fd055735b42b99396c77163771dbd81b48f722ac621db93f337caa377af03be2a2d77c4caa9722d0472b46891abf1812232a74f438af35d33c55aa6b5750cd040f116942c5029f9628c3f2ee7f8ab5f4dd0b519afaf2290f9720786477c0fbd9c8e3eb4d6a5f3222d5b51966bfba96199e6293c4601b71805265982fa105636fad729aee9526a56d2ccf1efbdd9bf3c720f5e46369af49bbd1a3b5681f255cb8e14ff001ff163d9b8fd6b8ef15cbfd9b3204db81f36e51d4ff75b3d455492660e57d8f3b8b53fb36a703f9de524c801da3664f43b97be0fcbf5f6e6accb15c1b881da308aec5a372ff286fee83dd7fd96aa1e3ad3ade14628d3d8c8556566b752b129caec9954e720ee01b0718e464f157fc19a845adc06d6ea1701d94316fb9bd7afcbd81edf51d2b194af1b0d2b9ebfe03d2c4b386313c13a0cbc6d16e8e41fde18edf4e7dabda349b4925b220448b105c32c24aff0e38cf35e63e0ab03a0c68b26d9ed4a8746e41818ff00770491f4c1af5ed1a2f2d55f79dcc40247cd8cfb56b4f4dc26c8e5854dbc312c0e5848ab89537a60770077a5bbb2bb89f72df058dca069254dcdb47de5da2b4646bb80c8822b6c3fcc58a952bf4a74ab2cd19662ace5f2db485e3fdeebfa56b19588223024520398cc8c323e6033f419ae7fc63aa5e691a66dd234f4d4650ec92991ce6007d41187ae81ed05e046789a65ddb4bb385d9edd2992ac28ad1ee0fcfcb184ddb4fb1ed5038cad2d8e07c13e30d4b5cbeba8752d196ca48e25589d4157720e369c9ce7f0c7bd75b68234795ee5e4ddd15a4258e3e9572fe0748cc3134f148e0ed66f980c3fddc6dcfe39ec6aad8c37f6974cbb219a318d939932cd9ff648a74fdc13f8ae5e92e14a47204695188080123f13ec2a7484a0f3444a8ea72cb8c103fde14f9e686ee278a51cbc655d50f241eb9c7ddacc7ba96cc18edadccc154ac44c872aa3bf3d6aaec9b16a7548db0ffbadc782aa1327e98a1b1260456e8d6e9f7a4e41a4b7173770c2df619124dd926e1b207d0d0ed2153f33a4c412ea177e31edd29ad756512ed87d23fce8a8fec373eaff00f7e28a9d00e820b48a37658dc440b7cd9153b379d1c62449658f1b4b6c23fa536795181c92c0b7561861526f26d07ce1006c9033d3f3adb620e735529612c85652e5f6956cfb648fad79cf8bfc5bfd9766d2ac9bd626e0678cfa7d6b4fc6da9c8fa815b7ddb14316847dd995548639ec463915e1fe2ed59753965da4cb032ec099dbf36719f4c8e33cf1919ae6ab3e58e8694d293b3350fc42d635f12cd610b4ea58c7f6a8828771e8aec42ae7d4023deb7fc35e2361730db6a905c69ee24631dcb0689cb819dacc84019c1c71d8d7867877c45731dcc5a7470ab1908533d994431463a332852cff00eee57eb5ecbe0cba1ac5a279ea2defe190445a1ced120c15575248218700fa96e95c106e5bb3a6365d0f64d3905bc426fb76e0ea8cb7532ee570c3867618565edb8600f5ad38dca858ee616f2c7cd1ca097855cfdddad8caeeecca42fa815c47861db44927b6113a5b3c8f2615b023918e5800720827f840da3b57532cb1d8d8adcac6a62470d0ca090d107ff591f5c15f418e2b62651b9d0693aa47670bc5048f6885d9a4477079071d71fcb35c0fc4ff001ac92a9b4b379a489b709f631cc8e7b6eedf9551d7fc5108b7ba584f980b21589b20c60af2548ed9efd2b8733bca934ae8b73213f32aa1503df8279aa9d46a3644c61695caf6714babcf22ac5342b86df1ec0304f63c658fb6735b76105c06d9b824cbc48b80ee07b22827f4accfedb6112db43626171c002276c1f504f57f734ed4efa782dc461e3b688f51e6f07eaa302b1bd9dcec86b1b223d46fad45d246bf6897cb04926325c376524f19fc2b285ea9b9f3de1b88a5059bfd24f3267bb123e53fecd4f7badb790900d91ab9da651117766fef75e3f1aaaa845c4491e5163c4846033127fbdb98e4ff00b5fa5724a572c9d1e791cea52fda238c93b09c9f300feea9ce6a6433cd1493dbc82de071b5a3923c3614e0fcdfc3486f034e374b2111c8db5227dcee0fa7f73f1a9350bab5fb098ee43c311d9b20e4b1c9dc776705b8a65c8890daca8d35c5c5cb4008c20c1121fa81daa6b5b2babe8a499e34b7d35243febe5ddb987f110e768fc063dea95c35b9b75216e9f1f74c919646ddd4b83b5547e35241682fcc324b6cef6eac7e4997723b1e8c573807dc907de8b9363564b982582410dd3ba829191e6ef181f7b85183f5071ef504fa85b69d670db5bbcb2c932e1c311046a7e6f9142805bafde04d5b92261a646e645f2941096f0b87638ea7a2a2afb053f5aa296f2df472caa918036a8913854c82783ceee9f74607bd06777b95e246b1df286dda8306fde46cceb0a97fbbf282bf89a9f40961b1d4cc4d6cd68d3ed6de63f29ee47aab3025c7fb8298f3c93e96fb906d6f9424306513e6cf25d8ab1ff6c91feed655c5d8d1ae22b9b7866bab89b6c6160558e30e3f87cc0381ee78ae9836f63296aee7a9d9fc9f744a190e047c958cfa60719ff788c77c533568e092d660e8acc1773239dc58ff00b58231f8554d12f5b1196c4d3cc42a8823c2053fdd19c003bc8739f4ad0be66911623318a391795182587ae71d29cb513d3638084450caf11740c9b8c76ea30b116182768c9e9ebdeb66c6d64ba8aee48094485149ca9dae78217dc027731e80a804e4806beab1456f3086de08ca3b6c7040542de8ddcfe757602cbe4bab00473fbb8978671b1428c7cd26f2b8fe1040639da01c20f5b1b2f7a176626ade169639629ade4f2b055517f887ab1eff009e2b2aeee66d36582099796dee85b21995595583124120107b57a0456ee66b8b60c05c61bcc690ee3186fba0b7f11f7e9ef54af3404b91b867cb500218c732b756f98e48527af1f856a65e465dadca35b337cb901620cad9c64678faafcdf4ad8d2bfd3ad9cb821590a98fa161b70431ec7e99ae29b4db91ab37d92e4bdbc6e70c5301a461b08da78181c0c91c7a56858eb771a2868efd9bef9922f97939e99f4156a76d192e37d8e8a0484b6e7814146918314cb03fc209ef54ee741179a818d955aca79408e63cf96e4ee6931df03a0fe55774ef10dbdcb0981deb0b46eca40ebc649f6e6b5ad08b4286d9f397da90be307e5c63eb5aa9a664fddd8e5b54f0949636b728aeef1cf10b677dd963213955ddd8aa907df22b8ef12786dae2f4dbfd8c4c55648d885ff5795014fd41381db1dfb57b4d8a076884880f94ec42b9e1fb8ff0cfa0154356d36292e2dee56336de5c6e808e7e59227255ff00bc54af04d3b2279acedd0f181e1a8649618e655466051d514a8f9540e467a1cf047159775e10fb32cd2c81648f7b8544c03227cdc8effc27ad7aa78ab454b5ba8a4b757791adc1806e1f2150b8cffb2718db5c4dd492d84172b1adc3b616492dcb0de1bcbe642c47ca32edc1fcaa64dcb604ee7217da24b1582c9a74f3c6c912875f358f1ce4f5f6aaba7f893fd2202d6c6f6e14230966667f2e6fef8dc48f37d1883b7b62b6f51df7712c014a24b994327589897cc63d72391ed5cefd9da284b47989e02247e3ae33cfd4e38a706e5b84a26ecfac6a274d8e3b148ec0bab9791c6f95b201dd9ecc73c1f63d2ade8da5f896fb4c448a75b4b45194842e5598fca090719c8249f76ef8ace848b6b80adfbf6fb3a8183c64a807f2d871fef57a2dbeabf644b68eda1c79043c8a7e70008cf1f524363fdd35d508a7b19ea73afe1eb882f646bcbd9ef0a44d1869863681c2803f84e78e3357352d3a28561564595a58a23bdf9762ac4e09fc3a1c05f7ab9a816bd5ba91be66565804bbf712aae19988f52c70296f4c237c922ee30c5244047f2e1980e5bd48c30fa8a99c6d22e2ec6b78474d87cb4b85e72c10b7404b3676fe1debb3d0ec9673f6860acf2116f82396f9080c7df7035cd687e4a69d6b1aa8504e0203f78bc390c3df3c7d6b71f5016304de428e4c732459e00237e41f62833f56ada36ec6777b8ef1369f1b413b6d540187209cc73618c6bff02d8e01e995e48c8cf95789229b55b595a1409303be1de995700e08c9ee0f515ec5abb4176f1904c666b577640321d4905c03d38701b1d8b28048e6b87bd64b8b3bc9a1431ee0cb7299dca92aae0b853d3727ca71c720d4cd24117d4f04d492f35cd0ee748902dc4b6b3174041062215829073dd5baf2b951902b4be1b69f713dcdb3872f26e5db26ee0ca99f95876ced18cd5bd56d2e2c6fae66913884b49bf20152c9fbc071d5187207600d3fc0e52cf52768b02199e294e0708f90031f5ef9ae49a51d8de27bbe8b2a1680cb19867876eef2c106271d89ff00115e8969a9c32d8edfb3cbe5c8aa44a8db7a77af2bd1526b96255fc9b98dc2a4891ef6c1ff00966e037cc6bd4fc30d7d1472c37969e730747f313e65977743c743edd2b586baa26a246cdbc76b6d1bca2e167dc99694cdbc81ea4761f5a75bb472da34903477d091b83c2c1811ec41e7f0accbd9f4db7bb68d2d27791d725a37010fb31c631f5c54fa77882da064d3c58dc89976c6044a0aaa9eeaab803f3aaf222ced7e85db7be4d4461a38ed548c22ca7ef1fef1f7a9ee9a111056951909cf9f1b81fa8a91242a9299c0f2cf52e4313f5e38fc2a04927b50b30b889937e412a5188f67dd8fd2b48c92d6c232341d1b5db7d467fed2d61751d3543794ad6a91c91b96cf2630aac393d53d2a2d6b44d66f2e634b1bcb6500af9cf202b22a0fee9c75fcab6bed773e7cfba02b6f94316e7c7999fbe39ef4f6b8679847288ad9718440fb8b0f5e704fd064fb53824f603053586b09fc9d42f2dedd8168e3014abc857aee18e6affd966be8dd219d170ac88ca039da7bd41ace9f6536a113dc69905c4c54c6b73344780df7d72481f8e6afa586d89a7b33f677d9b55506d007bd2b36053d23c382c628fed1746ee73ff2d324c7f9038ab8621751298ef248e36521444c3e7cfbe7b77a74835096d4b46624731e1da68f7890fba8c05a8f4db392c2158e658838cec48d30a33f7be5ad2cb96c2b8dff008474ff0076e7fefb5ff0a2ac79117fcf34ff00bebffb2a2a2c8676456379119edd9fcd6c80c319ae7b5fb85b38dc2af9219bb7394f515d15d5cacb6aeb0869c8ef920bff00857317f716d7d67751a45b6ee36590c7249ce0f471edeb9c01df1552d4cee79278f1cd84ee65202abf9e1c3738392f20f50c181c7a66be66f18dcc96b71aa0175c2305cb4998e446398988ec5ba37f78818cd7bc78fe7b9b09a3564592d643e5889fa87e09507f81b8c6d3d4118cd7cfbe2ed6a1b4b792de3864bb8137c4d1cab8222739db2103901b8dddba3f3c573ca29c6cca8c92d56e6e7c3153ae4d737d2a7fa42404977237ee1fc248038f7af5af0be9326986dee3ca62d3f970dcc05f8c0e41cfd0900fae2b84f83da2595ce8fe6d903682e99a44dcdbda37ddb760f5507f86bd86d7478aef4467b907ef06df1fdd251b03e5cfa571a4a277a77e8753676c90f951c6e4c7215f965f986d3fc2a7f87eb54fc4baabc76f25bc0503332c8760dd83df81da82f1988a9cb1607cb97ee8de7eee14d709e3af1779905cb42c81910abc8cbc6d2db4f230393d2b15773b261e473977e248ee75ff00b207fb3c16a88a132728cc3794ce3919edd3deb4c0dc11a39a7438c90a0a67d8d71ba332d9da1757ccd72f9c45c1383c21273dbbd68ea5aac9145b5955d89dca083bb3f891c539b6b72fd9f99b0b770c436cd1848d072c14617dcb773f4cd4b71756fa84e9224533263647f3fcae7d49c631587a7dd5ddca452225b404a7324ee5a46fa7181f955dd34ac8d2cae0c251f69699b664fa927a0fa29a894bddb1b24a3b13dfcde57ee49588af0b021dbc7ab1c73f8d56874d92496768849717242875db80807524f6c771d7daab5c4711b9ba82de19161cee6922628257feeee6c851f5c1a9248af1ad114dcc76f0970a520524055eb96ce599bb938acb9bc845b368fa74259678e0f3a424fce14bb0f57eebef51e9da8fda62b8f2ee6299f251a7b77c063b701564390a9fed0c9f6aa7730c654334599c868d1dd37b73ff002cc47d3f1cd6bd9db7983ce73e7a14cb48d1876d9fdc4451803df14f99f629b2bdd5da6a2f2adbdc4b7863c095624ca861fc2d21215c7d2a7b6fb56ab6c9e7c925ad8195636895c0695d4e029ee4fe9c8a86e5a59144866105bab10a8abb091fdc51d067d7153d9cc2da2d896ca1a6d88887e7772cd9c027ee118193f5a44395b427d56ce55b78a34778ed5488dcc4a14617a441b38e7b9fe7566c112fd42a34d76843111c2865d89b400be6bfcabdfa027dab3c69d32de19de49a7919b6a15632b38f40a3a1f72a07bd6e457d782d1d24581645c2ed31ef111271c9248cff00b440aab93f66c53b9d427bb79ed208638b7285f3e29448c07b8fba31ef8aad6896d2992dee49b6b354692570c433aaf57627a8ff0067157a51f630ac89e74a77037129508a4fde0a381dc7527afd6b12eda29ef9e79616787ef932b82483d5980e83d8d5a6e3b0f97ddb1b9e1df104171e6436ccb0dbab2aaab49f3386fe038cedddc1119e70c0900648ebc2a223412ca825ff005b30575638fee67381f81ae124bb6b6d42c82422d2d0ef2b2445948670588519c8dfb7273963bf71ca8aeaac8451da88d1b2eea1fef2aac67b8c77c7a5741cf629eb690caa3ecaaaac7a8652727d4671cd57b29489a3765d8000166dc4ff001286540072580db95ce3a8e6ba3b8b111e9c4cbfbb9bfd62233067d9fcbf5ac1802da5dc6657793cdfddc422ea4eeceeda3a7e18ae597bb2b9bc358d8da8508054011a86e415e415ebcf75ff00669031103196e06f5051221fc39fbdf5f6c67f43886db51f36e54ed6458b0ec194b9c77cfd7b525ade2432dfdd4031265bca918672e481f28fe11c9fc9bd6b74efb1334a32b89636a1eecc6eaa24f9b76f1954217abfa9cf6ac7d5ad6333ce8e99491447b9c925f1d47b1ad8479ad99bc98d5f2162de0e71fc448fef36ee377b8ab56b670ce6199d373c41e309bb397f5fad672d65708a76b9e7f0693756d14f323803cb554453f700cf1ee7eed6e37da6cb7828516322358d8f463dc1f5ad5b98a3b6963416e91a46a589009cb7bfd76afe7558a4d7105a33aed9f7ac80367f76c3f9d4c65ad872b3e85cb4d7c1b41e7a10e58648ea33d853eef5786e947972ed9639158387cf3c8c11f426a2d574e48a791b790bb95b007461d8f6ac91a55c830493c3e5cc5966312e57247638ad63371dcca54cda8ee5245ccb08598989bce0b92a41ce00f4cfe95956b6508d7eee40cbb98acce221b36ab2801d739f97e53c1cf51c543746f16e5a3b7ca9f281705ba6debd69c12633cb71e5ba98d06c8c9dc17a639ef8e78ae88d4f233e5bf91cd7883c1ef63acbdd44a6258cb07efcbb31240ff67381ed5c15d5948dac5c24bf3c684abaf7f976e338fa9e95ec7a8137f020954a1023dbb1b2509fe78ef5813f86a099d1ee15778004654ed3b8677038e09e9d6872bf907b37dcf2dbcb3b8b4b9b61e532bcc42a283d403264fd3033f4ae8ae75a5d3ece39599fcb9e55774889031b5c2e4fe2df9d6e6b1a1add4f6e9b8c6fbda365752046a589c838f723e95c95de8f35f6a979a74d1c909954c0a1890aaea18a107d0839cd529b8ec2b237ec3578ef74eb78cc6ad797522dcdc4280a6ce4c8c07b038156b4d85f5abdbd9e68dc5ab1548fe7c193fd633b63ea063ea6b99f0b69f7234f4b992e15de6436ef1c6fe648a5339181dd987e55a5aaea4fa588252c218ae018d9470cc400319ec7e7cfd33f4aae7bcb522c99d4dbeab1c91c643314400292304151b93ffaf5bb1eaf15f89d0c8891c8d22ac79e471b88cfa05931f5af2e935d8e28e396063389f26452d8d8e30bc7b739fa0349a76b330b98c425f65c4af860325790b2fe4110fd0d691904a3ee9eb91eb8d0db69c4959845b9c387e3698e30c0aff7491b8fb81580fa8ad96a3756ec565825712c64f40872a63f7e54735cb278a9408e28ca136f69711796b8e4ed8f6e79ff00681fc6a0bbf101be82c8db8c35c705860fde46240fc4d54ddf622314a252f15148b504405046a4041d418c6d087df2b26ce7f1e39aa5e18b68ed9e44788bc018866638c287e3f1a9b58091e9a021f33ccd93c328e7e7ddb5c0fa80171db6835afe1eb75d46d4dcaca92ecdde7c05707eff00435cd50d62773e199ee66d4e381d84eb1e14c927df207a918e4f661cfb57bce94fe75ab430b070ab8dc5b7e08e832bc66be69f0bddcd7b7d6b00dc92db48144a8f96080e1491debe90d19e15d363171e52e10609f955bdc818191e99ada83f76c45430bc73a06aface92b1689731acead89e36711a49fefe338fc715a7e12d0ae8787ed2df5d8e3fb7431b234832c59773e109041c6c2bcf5c83f5aa771234de26fb65bf88aec2247b1ac240a203ee995df1b7fbad5af7b7baadb20f262b678838f2dccbb59bd88f5fd2aafef5c5ccedca5cb686decda4db21df8fddc6d93b7d97d7f1a82ff4d67bcb772564455dd24ce3e65fa76fd2b0e3f881616f7f67a6ead7305b6b575e6496f69bbf78fb080c471803710a324124f00d6ddfeb325c3320b39208b90d311b8b01ec6b4e65cb620af3ea96fa1eb91594f697313de6f6496385d91db82c33821783919ed4ef105936a1a64d025e5de89bbe53a8c31ec78d7d55981087fda353a89e3b410c3e5244a021e0312482c588c1c9e8bd7a55337d74b776e6de388f978591598890a9ee81081f98359a6e3b0c65e849ed1a287572924e9b12ea0753206f51c919fc2b46617763227ee96ed8a0dcf9c1e7f4a45b2b39ccb1fd99640f89897c7cc72460600c6303f3152a3193fd1d43c31a15c800b600f7eb5716d0142f22b4bd6865926b88cf9df723270cde8dfdeab66ded2ca3f39d946f2472a4b71e8334ebb1342e89632adbae406f30862a0f6527000f7ce6a8dcd9f98e93dcdd1b8910379660f5ce39038e3f3e454dd9362c7da57fe7ee5ffbf63fc28ac4fed7bbfee9fca8a57651e8da84403622670cdd76b74fa62bce3c417f72fa822c8923340d2797711481248c8e980c00dbeaa490ded5e99726dda5758d58ca4e004f9715465b0b196239b7552c3e68d971c9ebc9cd746c6174787f8e6de5d52c8b33a9bc63b079aa23f3940c8520fca7a0c1cf1cfcc6bc575bf0a2ea06daea7b79d6e2390995e6ff0047b989f38d92ecda0e7fe7a11861f7958f35f4df8eed22b1d2a6ff0047436eac183952de590b85c27cc76ffb35e3336acf77ac40b2c2591b089728c194a060553cc390a39e8ff9d61265c2377646df85f44b5d12c219ede14b5091caca36fca1b23713ef93c56bc9726db47c238fb4b44d20451b792b9e9d3ad62de6a91369b70d2b2490ae5542b75f99770fd08fad5df3926d2ccb28ca4aa25e78d994fbbeb8ae19267a108dd5ae5dd5b5c4863b52b1992e5e054f9db259d508048e80ee15e2fe3cc49a9c3a29df862b34ec1f080f54423dcd7a3ebbaad9e8fa21d4250a17c9f30873c2265b6afd59ce077af22d245eeb1a9cd79744896767b96dca1f03b64e71c76ac6ce0ae349f3d8d982dc2bc41373aaafcea1191157fba0e33f8d5db998c398c48c63953e5820011a4f72e73b47eb5957b339923662db072c3ef03ec79eb562ee4fdd00b16d56f988739287fbcc7bfd2b27272dce8e7f22ee9f27d8e754db1c729e7cc71bc8f603d6afc53dc5b482584e71c249e5ee451f5e54fe75ced85cca24122388e325847bbe672a3f8b6f5ae8ed16e23b42a1d94150c6e2e183ed53dd546028f72314e4043a9eae934aab736cdb524f358bb61e63ff003d1fd7f018f7aa533deea37702c507d9edf7b34685c65c9ee46781f5e7daa09add62b89a62253b328f24edba5793b73d133d9471ea456b0d5a6963b55895eea72a008a24ddb33fde70396ff68e2a00b3768b1ac5023cf71752bae5c0da8a4e7e55887cd818e5aaf47617125a81737527d8a36f2c40992ac7b00abf3f3e871ef8ab51c129d322f2ee6352adb4bc2e25c01bb7a2b2e494191d0d65de5c6d85998cc113cc621dcb7c9fc6ca463cb5f59493b6aae04fa6c7717135c4613c94539448f08c1179039c8e9cfafb526f1f6a9149cc8c0c384fbd9ce181270140fef67b1a7db6ab262278e08ef0bc4db368255015db82c40dcd8ee7149ab59c363706dd8af9ec88d2c6ae24c160364240fbdbcb1f97f3a9b1325790be6b5db2c315e3c1193b7613c469fde247cedfeefeb57ac6dec96ee488cd398e2dbb0b6146c1c6e2a090a73e840f7acdb273a7ee3bc2de333477374705900e3ca43d339e378cf3534718bc90c56b03aaafcacedd73bb3bdffbc391f27fb428b5cd394d8b49239a5f2d96e648b3e5325bfc8d223063b44841031b5b2541fbded552e2d6f6f770b5b486c2d15b746c5374bb7d417df86ff68e5bdeb5ad666b9536906f6f2c09258d5f00be0ab061df92704601ec69f7b68228666131f289569262799246fbb185ebcfa0ad7ccc672b6c7392585d5ddc0b5797ca5010bcee43b441b0ce01ff009e8ea3629fe0ebfecd741a54c2fa60aa5a38caed0de60611c60feed4120659ba963cf238354f56d0ee26548772c0617c1584fca92145df1c8dfc527cc372f55c1edcd675a6b516917224b875b8405d44a887cb32e30cdb7d400020e84e71eb55195f7335a9e8104134d23c9018a1b6c6642eb9d83fba3be3f0aab7f64232d2101db3f3b392589cf660063d7e9516817c6f842e588fddacc84f0ca8464163fd2b641924b39245dab66a71e711cbc98c61475231edc1eb8a1c798a5ee983a7ec7793f711ef0a794c80bb7a01cf27ebc5335188417364048774bf3b6e183b796d800ed81d7af0deabba55844f3c97d26e8e1cac31ac636f9878cc6b8fbdf793e6191f3af3cd32f2496e6f7131581b1e5c8ebf308f94f9548e09c46a38e3df8e724b91d933471e6d5968dd2cda41912558d5e5f273b7185dd9dabe9f301c8ed9a988166638a2d88a14b3c6093852701738fd7d8d525b5687cbb83c43130119979f297729df8ee7e61537da16eac8cf1c2cd147222aae71b31c05c9ea7258fd31564c56b6b93c110bdf3a49d446eb2ed511b171195fba8300e5b819ec39c91488caf76c3cccb6769623807afd4f1cf19e2a15bb371712245279b1c523a92aa4e5b38c1dbd39ea4e376474abda6c132489295479943a07ce7cb8d3960a076e08c8c9c0a49252d4642d088f31c30bcf2c8f97b8989085bd02f7a9a3d0246b80f31844a8779032707d3ad6c470c93f9b2e5e4384892420b0258676a0ed8ee6ae58e9a2081a3655014ee954f273f5f4a7e46726cc8b7d322b8bbdd2a2c68312ef65dc5f6f45fa1eff00d696ead23918c7191b133b5b1c23919703d71ef5d0c3651b4e43c5f384ddbffb8a7a6476cf6a8e4b759673b63555f2c02074ce7ef7d48eb5b2d7633db539a874248da368d415d8433050412de94907831e78c33b468e63665942e43bf62cbd8fd2baa5861b91b517ca31c8b893a703daacc922453b795f2179065cf3b580ca802ba611e6dcc5dd753cfaefc2f15d247b960619d8d14a8558febd6a8dcf8020ba65f91d380ac5db7155239527dba03e95e8b346fe748ed08ce778de3710d8cfe5540b79d3b0ba530c4e7687078673d0fd0f6aae5d6c65cd23c8f50f87b6b6d7133242e59be63b40077160490c08e7ad61789fe1e5fcd6b24aa154c655d3cef99653b83127b83c01f4af71be8a2b594a4a9f688a260db97930afaffb58f6cd676a7a6daea596b7459dc64fcb26703e83d6a6502e33f23e3ebbb1bdd235444b9410f972b1d8ad9ca9cc79fd735ab06aeda4ea36cfe588887fb4472e7239d88e3e8db33f435ea9e38f874dac923ca48d81dd1ac5f2bc6dd776eefcf6af31bdf05eb36f6c633247751eede3cd5c3236ddbb3a54c60cb96b1390d4b5792c3c4ea240a224955f70079e360cfe007fdf22b474af122b25a2bfee151d596739f94819e9fef7e9585e27cce2e8aab34a02a346721c30c8e0639e47519ea2b8e83c4c52096ddd842c1b8591b6e0fa60f39adad626ccf606f11c6d04b6ef13abaf2c99eacc3cc2a3d0eee95d4e828d0c322b48af6f7b009ade656c15746cba91d73f502be788fc5779a8b90b0bcb3a3c6e18be0301c2b67a74eb5ebfa67882ef52b0b0bd96d61b6da14eeb7cb8900ea490392ddeb9a516f76545ea7a6f84e0171729bc34572b88cb2e515817ebbba7eb5ebde1e93549213691e917ed6336fc3cf092887ba16fe153fc329c03dabcb7e1bc0d7da93c9312e8e154c85b761776438fe1da7b1e95ef96df6748bed16d70f298233be2271bd719da1b1b413df820f60b5b421c9adc99cbded88d3c251f969737534a9970f1c507c9e567df9cd4da568cfa6da4921d46e2fa7467d8caa23f94be554039eddeb434dd774ed5e3923de63da159edd958600efb88c1fc0d68dbdadb5a9dd6e187386999b7e0fa015641c7ebdf0d74ff0018ea7a4ea9ae6916f3dc69eeb2d9dc4aa7cdb7656565db8238054100e706bae92d3cd411cc652a37643700e6aedc37da5461b271b95f3f315fef6df4a3cc6681019199c0058f6e6b6e65d89b1833e87711ed0d7fe4812205f290fcdcf7f4e3d69fa9e8f7b77066158ede34da4c73fcd14ac3f880182bf404d68dc4f046cb713cceca0a6e580eec1ff6b6e735457c49a5c909ff004c899778deb23e3cb07d4f41f8d665585d2e25bbb62e0e232ee92c61cf12021644638e086c9e32300518b9372a56d5a223acca8573f519ab31b25e6e315c8f2836018e3180cc7e600f46dc7f88669d7534d1431adb46249146424a09247a919ce3f0abb8ae67ea361717096ccd7451632b27972440a330ec40278ae7755f07ea1acdfd8ea9a7eb66c7c891e4b936e018a58c8e50abef1d40f9873d78ade63a96ad1491e45b05fbbe571bbe952e95a03e8d14ed777d7778de6348259b0ed1e7b2b801587e153b0ca7ff0008b49ff4135ffbec515b5f6b83fe7e0ffdf2b453e6405fd42fcd9bbefb7cb9e15d48ebf9d63cbaf3342f92c634ce55ce718f7a35bb8b8bb578ad1537bb6589fe1fa66b02f6d9ed6df75c1678fcb6cfcdc927d6b596a6109a654f15ea725eda01697e1415c15b93249195f70181cfe35e117baf697a66a70dc6c7172b279c45bc6816418624aca543761f7857a86a5aac16370f2d992928f936b1cf3f4af31f1d5c5b6a52c6ae21d9323968a2c0018ab06627b75e82b8a6d9d095a5a1cd69de315b8f105d58450854fddb4fb979685ddcb4983d3964fc08af46b7d5ede449649a62cb6f12962c9b41c374c7af6fad7ce5e1ebc2fe36d46ea69084820589887dfbdd5b2a7e9c0e3dabd12496f3c4d632451a3dbd949cc81dbe79ceeddf7bb735c72523ae9e90bbdc9bc51acffc267a824625d9630364c708ca3c806158e7b01f281f8f5a7a453408b02315116dde5d3cbe7b92bfd056ce9de129608e0e3f70a3718917706fa919ab56fa014d464bc9525f2e2fb91c19047d460e6a1b6f7358b49f31c8eb72debc515ba916c4bf9863540a31ea4fa532eee208ed5c4f299a6f2f6b05f5febf866aeebf6726afa82c71661113ee2d21fbc3fba49c003ea45636a56124609690df49bf2be42e218bd02c838cfb0c91df14b952d469dcb5691bb5ba957f29fe5285170a3d78ec3eac0fb55cb4be5b0792e4131dc36d7590912ca5fb90d9db12fb9cb7bd610b892d25679268d82aac6d0ac807965ba6c404e1bfda6cd6a4967f698a1b7b587cb86140ec5f112387ebf78fca3d33927b0aca4db2cd2892d5ed1e47325da611d54b10bbdbef73d5d9bdf18a86ce79f54bab7b78a248e6655859465235038dcf8239cf1cf7a22bbb44925b62a4c16844724aa4f97e691960addcaf6ed51aea31b433e2cde18b04808c5d9d3a6588eb9ebf5a61e6773696be54693dc5d9922850c2249c61146edbb62461dcf049c543a8c114f05c5d4cc278f0a5219e4cc4c506417518247a8385ff007ea1d24b30b5f366311f2022daa12642db7057a12003f31620006a5bbd3c3da448fe44ab34c87224076e0e407707ee01c056c17ea3239a037205be4f256ee691e6170d88e28bf75183ecbdc7b8e2ad0b61f64dc9318a670c4b23b96c3100e64ce32369f97a0e30a6a3d46c22bc92cc4a8ca6040aaf29c6c07a63fba3fd9e6a56d421b9b9fb34018f92de42c7080646d859739240dccc73bb1c8a5760b52b5c471af9515b8790b10a06d1858870c5549e0f7cfad6f47742e9608d01b4b41034b102392abc373fc4c0e067b92d8cee6ce3db4e906a4f18851121056460dbcc8e7f831fcc0c91deadc177773dcdc5d340608861be63fbc723010c8dd1739ce001b4f4cd691761731a1a55e325c49211f2c38c2aa1c2c9bb3c9eede9d97d0d6bdbe992dd2c1777322c30248de5e065e463d554ff000b1eeddbb66abda69a90dac3362295f686dbce1594e091cfcc71cfd29c83edbb5ee999ed47eef6407972c012a31d0f3c1abfb26522492192e2de458d91e3da63554f9632a4ee603d54b28dc3f8baf079ae7afec4c734f6139448d93f79e5e46e0c982bc0e33d49ee7a6daed56de6b9950b2ac51c4a63b6b30b8f2c0dbb893ed939cd65ea7a74320bab9f34337de7301076a85c21e3fc9ed9a410d4a3a55f45142b6ecc4ef90b487bcae8a001ed8cf23d8f5aeb3ed46e82cb21c46a02222f0467f841edee7bd79fc856dd2dd177a34b385f2d573b06d667627b6e04fe55d569fabc7b5ae01132aaac2aafc0e4e18fd7381f88ab83bec128799b0da6a5a886669de33283fbdce647419e73d147ccdc003afd735a56b4b276c42ed2476e1d23030b1a16ddd3a03b78ce4f20d5f9e45be8033619b0536e7891493c7b018c9f6aaf7444c8ab24a903108d222f2b9ce7cb23e848fae294a2b9ae5736962ac536fb77675542323f7996f99c6c44231fde7cf19c04cf4a18492c1045164dacd206f9864b3a9e40ff006b673cf1ef4476535c5c453e5cc280982d8b0fbec3193ea497e73d002067ad6a58da457ed1210c6de38963de1b025dcc59fe9f282b9a5662ba65194a45149222932c9c05e830df280b8c606d50d93939fc2addb3c7a6d892803ccedb930bb7214e38fee86c0cf7f97a7269ea4dc0b91146be500eaacdc19140c03edc22fe5ef55a6b6b99ae413b9c2bc4cb86185f932cf9e9d7b54cb528d9d2afee0932dc6e4e18614ed2542807007dd1938c8e7daa583555bf9a3f2c2308902a220daa5b38c67b91ef54238a47b31120313cd10c9707e40e58b0e9ea33f4151694ad6d6571e67faab643e6cc07df72ff00316fee9a51f8ae3924cec22792622347f2d249374b228cfcc7f89bd76f614e86dc5c37fa346142148d403cb37a1fa5623df490db81ca3ca1cbc63aaa1e87f0e33f515bf637f159d8c596dc472242319f56fa9ed5d34f43964bddb0ba8698914852375c74f30e5989e7a0fc2a8c76ce15b83b519514b770cb804fbd685c6a492b4411b240001dbc16218e3f5a75dcc5d2348d0bae59a464c30c2f4e95d716998c8c99e499234531932f94597278241e87f0a8c2c375022328937fcb2a93f7b38fc8ae78ab2d27da3f72548c6e65727ae7fa555bad3e45139854ed901995875ced0adff8f29c7b629af8ae46c46fa792914d0c837460aec4e36b1eaa3d01ed5ccea1048e5ee1d630fbc2893a138e980319c77c75ed9ad28f509edee268f6b065404003ef63eebd5517924445c1d8c926e46765dd8f54607f87d08c15ee2ae5a88e72edfcb2acd6ee177ee91812e76fa82783f81aabaa692678dde08da4620b229849dc477c9c0cfe35aabf64b68ae507d97ec32498795dc7d9e293d3796db1b7fb248ad492ce489de38ae157783218a443b1e33d1b9e36fb83c77c53b241cafb9e03e35d025bf99ee1f488d2e24468a578db63b00c0e183601ce3824b1af00f1a78565b2d4a4ba3079706efde0f3b6c839c7deda46efc7f1afb13c679b38de6596dad5d8125b786c63ee939cf15e17e28826d6c2417571e6465f12451c8caa46ece76e739aca4d9a462d4773cb3464d3a5822852f66863dc5b7e46413f758ae3f4cd7ab783a0686c2575bdb5d5239250d3c925b959d9cffcb338231ff0206b1b58f07695069d17d874e9e2b8dc0098a91903b91e95d8f813c37a94b745668c296da9239186c0fef1fe2fad72ca5d4d631b4ae7aefc258d85caa5bc8d2419cac6ac0e73fc3b08ea3b76f6af6ed3ad74949fed0b1bc3791ec2c6e4946241f9b3fc3d3dabc8bc07a5da699a9dbac686577fbcc8d960bfde0cb9e6bdb6ea186f2d556f432c4a83cb798152a4fb9c66bae9fbd133a9f10f8a291a1530ac22db25bcbe190afa7fb20fe3590b677f15ddc3c4b12c2136f9625079fef7d6b4ec0a5accb15adc899020624cc3871d17663a55f918bc7f244246519186073f5c038fc6a96bb191cd490b4903c51a99df6626ba5701d97d3271fa568cb6d7b2a2aa4af1c68ab888e3e6c7a9cd59b4f2ae24674ba33bedf9a3c82b1fd38e7f1a724f1b650c2cc7eeaa22ee51f523352a2df519159dbdd162d25d461495621a0c8603e955351d36c27944f3d9da4d3a6d71706208cb8ecbdf3f526afdd5ecd6ec152d659e57cac4b0a9c1c0ce7a702b374b9750bdbb923d4edb0aa9bd58a86463ec7357662b7992b43fb95558d608bf76eaa7f76aac1b72ee1d08cf5504548f733c6d1c2f0c71c5bfcd793cc2549f51819d9fece6ad9b68d1556da3072cd955930a5474214e78a6792d67095d8a50afeef276e4f71cf6148a924ca935d2431b7d9a6595978c221e0fa0acd69f519985a086e15659190cabc3aa8f43d17f2ab3068711b833ed8628b7173b589e7f3ef5ab91751910bbc8372fcb9da33df07ad35a08c1ff0084747fcfe6adff00812bfe1456d62e3d0ffdfb3fe1453b202bdcddb48f2451911925433326ec67fc3bd73be22d3e5f27292ef3b7952300575cd632ee276b2c25810919dc1b3f7b9fe55ce788f518b4cb29ee0e4051b5c4a371cd6f6666ecb64789eb5a05c4d773b493487772bb1f9cfe15c9be85e64732200fb432c9238fb84d7677972d797d71789b8ac8dbb11aed082b2a19c5f31fde0458d8ee0b1e0291db9fbc7e99ae1aaed137a5ef3b33cc740f8690d86a134f127ccf2619fa13f857abe8de1e9a18a1dc8658c1c6d11e36d2e95a4817a67754721be527839f71d07e35d45a5aac80b6f00a8fb91c64827df9ae68ebb9d3f66c10da89acca481a6429c4618607fdf3835936ba2cadf6a9942c0833fe91855cfd06def5d0854b78c845916666e6454dbf2fa819a8a748e6dc8c935cb17071b08000e99a528dc93ce356d1df6c865824bd8941655c12880770074fc7358a964b1d9dc4316f83ce8ffd4c47e42bfed1fbd8f50080dfdd15e8fab69b1dd49e448ca429662841f981edc11c563eb1a3a4f6c8d043bfcbce238cec0c47f1e4f6f6aca4bddb1ac4f2fb9b0b885710794d0972131b5614c0c8666232dc7a9a8148925859a47bcb940de4ab6523849e8f8390a3d779c1efbabb296c5250f35ed9a3c96e1498c2e40553b9b27b927818078fcab9ad7a09eeae8496d142b1b361238a1dd1ab7a9248527fe9a1247b54f2a2a51f31b15e5bc32c934cec5613fbd9a0ea777248ec063f8803526a2f6aad147e4472c378d948e5270db4e02ae08247ad6687b7b6b89999cdfdd5ac8b3618f991962b84037e738ee4f1e8a6b6a02be259e3495963b8821c990c783991b25540c941f5f9bdab3b58bde06d687013a7b5ba431406e022e4aaab1b73fc4495ddb3d32467f8715b61668ad6da3b4505b38853614545c05dc148e7a8f9dfa2f0315c98fb2c9736f63e73cd106c0855cbbce705b73c43e6c951b8ae77e3fb878adab9bb3a75bb97296a268012379f3195b6840402c30cc385c8f538a071ec5bd2b528adc4b1cac1bf786348a27c86763876c9cfccde83a51aae9b2e9f13cc92a42862d8917015893f31dbd76eee0beee70739aa31df5adba46b1a09638dc318820dee08f95be5276c64f45049f7ad5d51e03f618a58c79d2c619220092039db9fa9c961db38e0500f4d89f4c9ed0592f9d20b891233948d30c73d46ff00e153f8b37722acddea13dcdbc523a10cca45bc6a325b6950bc76c60e739fc6ab58ddad9dd49687cb8238f12cfb70db00e89ea7f0cd457b72cad24d0c72493cb9dce4942577031c6aa390181cb1c7e741363a3d2e5fb758ac6ff34eceaa1635c0e3017611d3731c1f604d68da3a8b9925b901fecec638a3753b4baa901380381c64ff003acdd0524b68e497ccf3f50501628a26db1891c32b363fba89951dfa1c51a6dabaca0cd2869847f7d95b6151d06dfef37ad6d6bc6c1248e8db5179b7332b9f311d1158632b950cc7d4363be29c14bda858c95f9965795f04a01fc40606ef61fc3ef55fca96f350cbb188a8cbc99398e3030c5863e652dc7afb55ecc599561392a061a5e58b7f780e8b8f4e6a6e649a8ec717abe98915f2ccb02ac40924b2104853b46ee7e638fe66aa5a62d750923762b08467855fe6dcecaa899ff813eeff0080574bac439859999a428811549ddb8e0824fb12320fa5733addc4900f2611b9ac8c9239032795247fdf3863f4154bdd2d3e657677fa710d67bd4293b332b10318650083e876e7a679a8632b70cf248a0ee77c67f888c303f99ac7d27581f61b78966f35c14250a63194ebee2ba48104c8483fbdf2d9fcce3e5c290b81efc5549dc887bb22aeab285ba8612ea5caa17d9c6e7e073e8321c7e02b46de33e6c102166b7f31bcddbc798360db8f4fba9ff7cfbd635c59a585d3e25ca89912491d77b39de1b18ea39c1fa2fbd5db095f529208e1478e6632046ddf36c6182c474c0eabede951766cacfa1a76572d6db83b27dc48541f9d79dbb883d3b9ab096ad71b442a0220ca10a76c8ce490db7fdd53c7b54715e090c76d0234b6b6714bb65560a24639fe1c7d2afc37e20896147651e448c49e3739010e7d3680718fef1a09d8aefa479965108dd51d4b206e72c73b00ebe88dff007d1ac4779ad6dd810cd1c5858dff00e7a4a57a63b73fddc7515d05c03335b4519f25d230db540e983f37d72ce7e82a1b48a1655919b2ea8252581c712741ec40183d7ad55905cc0b1bb76b9925b893ef06decce7e538c96e9dfb0f61566e75b668b05888d3112e5b70c0e9535d694dba5be9c6c58c79f2e39c9da02a63a77e6b1920c84b8933294c94654da13e83bfe34e32b1124b63adb5d59435bda23168a38d0373c29db8dc4fad681bb33db3c0bcb950ce37062b1f73c63af6af339354bad3fce9ad6d9a49640551188f99bd3aff000f7fd334db6f11ea3a659196ea01e63a894c6a400580cafcc0e31ed5ac6665248f4cf3bece65404333ab46b9ec15431c7e06922d4925b5b74e5f7e6324f1b092e47f87d6bcaadfc77717937912ab47bc6e8632d8dce4b2f5f438ebd3835ab69e2b56f22241ca10065bef463e6327e61ab68cac65285cebd5bce60595c38dc073ce7f807d3d6a258049148c222cc811d80ea72db5b8faf07d723a5511e2280431343b54c9370dd78ce292f3c430dbdcc7345fbc058059b046c4c619187f10c80c09ef9ad79fc89e465b86ced62276dc792b71118d258cecc91fc2c0927fefae7fdaae7357867d36cff76f04ac8cd323c79457233c1049f2c9c7fbaddf14fbdd47ed3348d2b2239dcacff295763fc2724647bd739af3ddcd6ef14772b188f69456126e046ee170d80bcfdd39a99487ecfdebdce535ed56fb589d63b758d779e5182fe4475c557b4d3fcaba506d1e5908f9db0bbb3ec719fd2b7747b69ae1a28de2672c31e6c9205427da300b7e668f1445a8695658689027f795c16ffbe873fa56525757b95d6c71369a17f6ef8c5422958a3248dc4b00c0676919af62b4d0ad2da28da340a4ae7ca638f2cfd7bd711f0c748f2e46d44ab00cec84f51cae37d7aa2e9cf2420b32f0c0798abd41f5acdaf7469be7b1876e86d6f63db0aa244dbfccdd8c0fee9c62bd4b47be8eead16dd34e9a46608199d4ecfc0b31af34d522d8d1a0221c49bf6000f97fe22b674fb1d56f2dd2ead24631a853b14ee418f55cd2a0f5e5b9a558d9731d3ffc21d616f74cf6d7ff006598cb95118dc4b7a16ad98ade26815a58a4bb91d0970ae0631d890464fb563e8315f5ba959a38ede56183e7af00f3860a33cf158be23d6758d235c96d27804767b63956fadc7eec963830951fc5fed5763d36392c8ed25d4e0d32c9a788dbc262015e094794493f5a47d59c23c979124304a76a431ca0963ea36f6fad73fa478a4de816f2d9cb7ae8ca6dce065ff31ffa162ba1022d624952f2cfecd26ddb8621c63d720003f0a51900c4d4eeb53609613c72062de75ade13b990ae32ae318ab3641ec6c99278e50caac7ca0a42803d0d39ecdf4cb52b64916e0b8064009cfb9ebb6b32c24d601b8fed39d2d17ce06055890a2fa80c8172bf5abbb0356336f2ed70103a2b3042d8393dbe950cf6515d0532431c8086c4ee4f1bba8009a634b38d8db6c142721a1662587b92a07eb55a729af492d9c73adab6d1317521b7e7fbc33f2d000f610df4263876fd923206d8ced071f773deafdac3233bba447312e42bbf04d41e1ed006910799752b095d7e632b0009f600d4d704dacf1bf982d59fe52246dca6800ff4aff9e2bf9b514bbe3ff9fab4fcbffaf4502b94bc41ac3e9d039412296ced08a4018f4ae2ee0dff008a5394db6e5f7638dce7e9d715dfcf79697b72be65aacbb119c0e71cf6ebd6ac5cd9db243e62dac71c853218603e7d01ae8e739eecf2ebcd261d32078a78d77b290c80fa7e15e6d1c2575095599dc87611a11d01eeb9c60d6ef8f7c6a748bdb9b39cbcc8cec50119c7b123a1ae37c3faea5ecce6e096321dc8b2316083d4915c15be13b28e92b9da5859a44b26d0acfb739238cfb9fe2ad5b697ec8d1ac9b99d8f3b4851f5e2a9c1379e3cb5570a0609551b4fe1dbf1c54f2472408be58500fc80b2fcc07bd731d0b5d8b22e590cbb499599f24eee07b0f6ab96b1bea11ba3a45022f4d8fc37e39cd66442e502a074830dd59777f3abbe74a64e4a87638debf2e3e800351cd6021d4ecb32310b8c2ec5046011f5ac5b88a21e5c92fceca5942b1c82c7f87e95d4cf711c51089093237df0e376dfa5625d58c9776d2bcdb215c6efbbf757fbbffd7ab69bd822da397d474f591f79b8115c3ed0221c051dcae718ac7d4b4cfb5bc86e5c49042df2ef1bd49fef1ce46ef7c63dabad6d2a36b7062ba587e4e02a7f0fb139e6a2bdd242d88b5895ae6593e6d81b6e3ea7a7eb5916a57dcf38bcd3d92e2f258e37bc85c14486321497f4e075f41d6b0d2099a2bf8649961c1459e38255d8d9fbb1b60e481dc039f7af426d12ea1d3e489c905701960e1f23eeed61d47b74ff0066b9cd4bc36d211be1595883298a477c211d324119351268d23bf298ba52da192fbcc90c87689257f314448bb86fc804001ce7763e6238adab0d46d1eec2ac9089087924c2e77b6dc16c1e99405476523248e2b9eb902c2c7cc6b79cdc7dd1b502222e776117b1c7f17cd576d350b447b58a1866f343282b3b848a09b1b8c8ea464950005c9032bc83926b3ba45c3e23a17433f91790da34311c3a4770002c0fdc6c0c1217d066b46e22447177773b3ff008d4ed3349d1517d021ea7a0acfb7b975d405bdc6f2ad2195c400abca46705bac8178e995ad78670f7b1ff00a3c44c49e6cb34adb830439503d157a2fa9ebbb9a77b8ec11da23a952bfeab2c7ca5e41d847dfec0914cba8ae27b4b546b819900f2c86fdd1690aa1618192ab8c039ea47a8cb16492ed92cff007d70858dc4a21ca801776149ec39a945d432de1b9689ee2d923d91c16f1b941105fddaf037b87e598bb65b0dbb38f9588d5b0b8b6127956f1bcd6e36aa489cee08b2331c8e790a49fa719aea6ce578a3459a3391b89cf52c7f871d78ed5c9e91a84b777d1418891ddc5d29707e5551f20c00380c41f7c60f04d74da76a2b697fb620d347006542c32eee3f889f73f28f7fceba22c96afb1a31c7333a00ecc5951d579ced2c133d3b83f74e0edf988157ae6da1b61e5895249246dd3cb81b630dce38ee4723dbae2a21a899f4edff0036e9647ccc9f27999c9773ea58e067b2f1552442f3db47200a150bb08c636b9e59fdcb70b8edb862a95af6b1cfb683752ba13f9a554e62280855e029002c44f4ce33923bd73fab5a08ad125f9834b2348640071b91cb81ea06ddbcff0078d74c6ca66995031555914a2f38ce320ede848efcd73f74fc186200979a68e376cf0ab90081fed3922a64cb8be9d0c0d06f9e0bc954c9e437971210ff0031f5e3df1c7d6ba1b4d55e6bbb745c346ccbe6123186ddb427d01e0d72764be6ea93cb3edb797682e19870ec7e5dbea00eb5d3e9d982781959672cc9b41180772f2c7df3f37d6a16bb1d0ac9731bb78f049612cea8d82a7780d97dc410a73eb838fab2fe068d79722eee5e2f2d6595426ec60463855c67b05393fd69d60aa2c668a100bc923367aa921be4c1ff00782fe02acdd47bae52da35428bf216db92c31cfe6cc147b29fc4262d22f40cbb10db7fac446396e3192a391f546fcc55996c9de1b24864289b4387752c4f94a77678ee473514b1fd9b6e195a724428a13ee824f1ee77b03f43f856add5bc2ba85bd8c256172a732609200dec78cff11a2c4bd4a566618bed192d342ab22e5f8c3f4073ea14038e9f3d5a9d0476c5892b6910491101cef25894438e7040cf4aace12cae2e36bfda2d5536c248e32492ec3d7f7824619ec71da8bbb87322b1702673bcece367ca00e47f757f5229d82c8b174c2e74efb28977b4ee865dab8fde61895fc302b3238a19a48a346611fee50ed2480e79327d71ce3dc545125ca59b180ba9632b87eb925546e1f99a8cca6dd659e321630eead86da032011ff451f5cd69ca6524dcae54bd8edad6f23c9091c6a77484602f5dd18cf5271d47a8acfb8d54ea4a50c264562d2470a10a83e5010118e833cff5a896d0df816e5da12aeef70cbf7107cfc827383f28e99aa32b43a15bc708266721e52a4f058eec203d78c2e7ea2b78d9740924cb87c3d6b1cf234a144e4221de771c857d9f4059986d1e957ad34ab0b0b083680d20dcc198f462ac327dbe63c5602df7932b5f4ad99955c5ba8390242802fd42e5c83eb8abda3e95a8dd2a5c6192cdc672579a9317a6ecb96ef6bb2782085d4abbb13b0e1406ca633fad4735cc6a4093e67639ebed9ed525ce84a8a0918652c325890f9f7cd63dc22cb70f0a4a9b33b6401c2843b718cd114cb24bad4166de8b2152a30b90ac33fdefad642c97338092dc79b06fc8d876f1f5ad88346b5b33bf73798bd65099cfe154b5d9ac6325240a5e4ddb158f959c7b75e7b71572dae2ba639b53874f93c988abca00678040dbd9cf4fdde377e44d719e24d7e0be99e3f9cca4796b1463037fa54fa9789625b0997cf44f29198260ecdc1729c67e51d4719e54d677827436f16dd35cddca409079bb82907fdf151bc6c3b251f33bbf86b64e96d10964103c8a5c0f43b436dc7a60d7a2c31c6519d310cab1aba64e7790718c7ad71ba75b1b2b88c42e5b6853961fc402a9fc0a838f715bd673112b6f2622395cf3839cf1f87344be1b19c7e222d7df65ae5044acb859171c05f527daadf83ee668a68a08252aa18798e70320fb1354ee09d8e14836f27cf1ac83701bbaa83dff001c5334d9e5d275a89da147894a9c4838923f5e7158c7dc91d2fdf89ebb6f6ac6d7ccf31ae18eddec8bbcaedc600c67dea59848c0eeb249508dbf32018007008faf7aced36df4fbc586eac946c68f71c4a7744f9e814119f5e7b5589a7b7f325b40d70d70577b0932a147ae7a7e55e9595ae7079105fe9da83a6607b5b38b2a0abb0466fa8ea6a0834f86d36497b7bb5a36f2c3452fc8e3fddeb9a9cdae9767199c4e616455226911b7647bb5665a78b2e26d4956fa08e3519cc71860d2213847249e08effd6a6297301b22fe2bdb855d3ee2d6edfeebb17dcf8ff77d6a85feb30dbdd47143a64b2de96f2f8e883d49e83f1156248deed65ba8e1b2b69e74d8144c5e4907a9c1041fc2a91b2bab7b60bf68334efc3889b3b87b938dd4c0d85bcd492358cda98a46391e53ac9191f400d46f6bb675122b2c98593740a06fc7f09206185735e1d37b14d3c4f05b92b2ca92dbcd7592c17eebe57a37b74f7add8354685bec8d06e6c0ccf3c9be35cf600639a2e012d9eadf6e89545bcfa7484313302181f6e31c7d6a69aca484ac3e7808fc18826fd9c67767d2861b0c604f732caa71248788c6efbc42fa0acebd69534abc934d4fed1d44c527940c66352e170884938a00bbff0008eb7fcfe1fc97fc28af3efb6f8d3fe8571ff81927ff00154504d8f5eb1d2ac74f7916780c8c4e4b17e334dbeb356b295b4ed92baaee5866e403ec4d55b9bafb27946405d5d76e4ff7a99126a6d1c935bd9131023699186e20fb6735666793def8623bf9e492ff00457173e6319255601431f55fe215c06b5a45b787f555785508723e5184193fc3f4afa16f269eca0792e6d4796dcb16c727f3af32f8a0ba76bba2cbf61bb23e657640031523d38cd67562b96c694e56dccbd227965f984b182065c06181f5fef568912879242fb811feb483d7d8579cf863c688f2881558ac18411f60c7b138c935df24d2dda7cf2ec69319c37cc33e83a570791dadf2ec3e27448dcb95dc5f182dbca9f4353c533050c8996e88a46d39f7aaa15ad238cc5e66d3f231660598fae31d6a41ccb19dcee15b24b360d44d2451a3c42c9e73289075f53f5a8e68db6066882a01cae72197d690309a26908da643805ba9ab610958d372b28f99b2bdbd3e94465620cebdb0732c12346551c7215461454370316815a450e7921530a07d2ba1757bb5508db54ae085e4563dd6eb512c6ca5549ce58ee3f4fad5c5240678b2dc1240de4ae76a91c127d47f74572babd979f3ec59253236f6fddaf27ea3ae2bb7781628ddda4743b30100ce07b566cf6a4196e5b73b346cbbd4fccb9fe10dfdeaca51b9a465691e7f79a72bc31cb2b3464c986763b99576e362aff08f7eb5c9df599824ba495dd4a8411a42a37152773386c93927801707e95e9f25928589dc32ee661831e5891fde3df3dab0359d302deb33400808a4321da415390147f0f3c96eb8ed58ca1d4de3a4ae711a6dd18a690c312491267cc4f349f309dd80cd9ce391d48aee6c85c4316cd43fd3351d59952dec61942a2c79c990b01c2e48213f8428ea64c57297b0c32436b994ec4e12283e5ddb7a9c81c0fae6a7d13518649e59d9cc572a0a05030a14b6e2ea3dfa803f8b2df788208cd14b5d4eb2cb50b680dc848214b788244b22e428cfdd724905d98f01319e0920004d2585f94b4616504490dcb162f3c4b93106c601e08dc496e9c0c28e324b20ba171a99b66b75795f21b29ba3817a9813dd8fde619255981273486d16f3596967baded121664504a26c1f773f7700f5e68bdc927d335256d6d2e276f2e33932329e142a12883eae13f0cd74ba2492c91361093bd9ae1949e543ed58f38c72fce477ae1ed42cecc820324ad2a96901ce097dc73e802f2335d8691a8798d6f0a9768d10cc594fc80838518ee73f31f7ada2c7256d8ebad602f029954e7290213c7cc719007af3f4e0d4c25b7b4f36e36798210df7b9ddb4e48e3af217f01504108bd58c97220753b5b3d976efc7bbe4e4f6e319a5d48c97912a43b55f2218827caa809033ee79ad23b5ce596b22b4b0de4896fe63bf9d34a58fcd82aa000df99381eb8358979024f6d94568dd240830dbb6e18f1ce39e09faf1d6ba7dcf1b1c31057288d27cdc0c85fe791e87d6b2f5095a08e28f08f2a642fc9811b709cfab60bb7fbd834f94b8c928dac79f6a66e6eaf163d8fe66edcc72071db1c7cb5d569f06cb58d5e42a5d705986e28a1703f1ac4d567596fe395dc0524ef882e30471e67ae73c63d6b4f4eccf65bf6b13e51914b3f053fbc7d8fe7434a32ba3782bc6ccdf82e5ecb6792fe74a1b2eeca422607cbc74e4fbd490dc5c6a129512ac442ee2d9191b7201fd73f5a8acde5bad3c34846c50d1b3670a8e7b05ef8ed4c4956c2765f326281182425872b9ee71d7bfd2b9ee164751673c76a818cae2e114300fd5787c13eec5188f655e9914e8afd21fdf21ccf2a9914b75dbb1f6d61bccf3c31619a40ce236c1ea76f1cf5f9571f885f4a0bc572c56e264448c4d24b2eec042bb55147b16247e069730729ae9a979d007b78d9fcab6561213c12ae44847d09fd4d569d7cb09034dc79a58aaa850ce50ee6073cf3222e0775ac81a909da288cc6280284545c6ef2f3b9c900ff137e95a96712cb7b6f75771ee2bb5a28c9fbd950723b2f233f81a14ae16487bde4ed757306d05e18c06489b1e528cee271ef85e3b835567fb43da45047b1e498001dd777951aa0623f1773bb383941d6af9516b3ba4912c45833cbb463608e3c84c8ce77392c7f0aa20c9fd9f044032c4ac67c0e58905d03b7a871f363dc56bce6666dfb4768f2c36ef989d248919880b91bc799b7b9ca918ff006857197fe2061b6d218d5e55199672bf3a160a4903d793f90aebae2d196192599325e0062565fba090e7a679dc547e35ce59682d79785a5de6572be64c38270c78c7d315aa93663748e8bc23a22c9746f666f3656c048d06fdb194fbb8e9f8d77f731c3059441a2923c0daa5c9183ee0565430d9e9d690a3cbf2a22e55dc1270b8e3183587aa6ac669a48e00648cb9ce7dbead5ba925ba399fbd222d4aea191e222562807607cbcff2fd6a389f4fb68331857f30fef2361d7df38eb55e6ba8ede7333ca5d89c286ea7f11c7e95c6f893c4f0a25cbdb7ca102a49f37dc2c383f4ed91deb1e7e874429396acdaf13f8a469d6d1cf1d9ac8a0957226d8b91dabcbfc59e24b4d527696390a4ae32bb64f3e297b0c28f43c1c35656b5e33b87b2cf931b1894c7ba5f98367f848f6ee7ad56f0ee9635ebe8ed955511d7128db80fb9b71caf1dfd7359a4dbe565271a6ae6bf852dff00b6678f39fde8559430e32002b8ff00812c99f66af60d0f4a8f4cb18162760ea02b4a783b4e3803d39acad0b404b30b6f16236891640db792d96183f5fe86bb6f262581a366f30491ae4e3182091c7e95d71493b1cedb72ba2b3a9dc2e617dc563fdeaf652b8c0ffc78d69d9dbc924904cb28dfe50201e99ebffd6fa567dd79713b476e9868e468e3909c111e580720f5e82b52d1a5bdb78d1503dd3c723c12210370c7c83ea7a01eb44a28717ef5cb7736c86094aa18a3750d2639008193f4e08fae78cf3583aa5b35c0523ce78e19023b29321471d490395e3e6e7031ce6ba40afe709d0e7236b7f0ee20ee1c7fbe4b0f4c91ce0554d5ada359713242df266191410ea1b90a482370dff360e79f6e2b8aa1d34cdcf05dded80c4bb6394fcef24aa564624602af7248e9c75aec2e5a59e359218a4d9082449337cc08fe0dbd1bf3af1dd1357b8d3f5010da2db866631c5020283fde504955c76e2bd521b9b7b636d0dddc4c672cc89139243b1fe207a2d76d1973c4e5ad1e595d12dbdb2eb16f6f7664913f779f225b36887fc0c31257f1c54b73e469789e731a26d6591e401e455c7002f5c67da96e3578ad4c71bdc7cc4ecf924cb2ff00c0b273f8e6abce043fbb95a79d25954a4408072dc80c7190b8e7ad6e65629dbf8ab47b5bf9a7f3111551375e4c16153bbfbccd8d98ef9c54d77e29b28f117f684770b7255635b4992624919c294cf6e6b4edadd6c2de217513c2d23000231db86fef30c1e3dc9a8de6b5d2a711595adb22ceec254b6c479c2e43120fcde9ce78a90329b54b016924b7ba7ccb6cc7fd679619989ebd3150dacfa3eab18bbd3aca69921957ca9864aab8fe1cf463fa56b2add4ca57ec13189be6121f9547d29daadcdee956d0ac6a82e003e5db5c2ed6751d938001f7c52bf4020b692e2f2d6195c182ddc329b6bab630bf97bb690cac430527a1233ed536238670904efa7c69b46cc154ddddb0e2b374dd5ee7506103e8cf676ebbd8dc9731b4417907190c0e7b2e07bd685c6b86c608e2974e9ee6265cc4073e61fa9a72d45727fb42ff00d079ff0035a2b33fb5eebfe8549ffefb5a2a3519da598b59b01a313ef7dc471853fe157de0f3e46c446275db83f79b8f7a4d1747fb3da2ccc12590ae4e07cb9fe7fa53aeaf52290c72a6c2c322443b73f9d75f2989cfeab2ce59a2762e4f53bc71f5c8c7eb5e57e31f0a5ade5c3028d15c852d973b95f1f4c57addfe9ef76ec609564908caa90181fa8e9fad73f70374020bd559f6965fdda72b9ed919ac9fbdb95667cd7abf8006917736b16be64fe4bac92db8ca118fe2200fe55d0e8932ea08b28758d64da4b01cb81dcfa7e15d8f8a746d3ec12691de38446a653e636d1b476393c8af26b7f175bda6a926d70e146e7c0002738c01e95c3563695d1d349b5f11dffda70e5561d8e13ca553cb0ff68b76356e0b7fdd2ee8d647f2c798c5b001358fa6eb316a962ad1c3b54fcdc31e7fdee326b585bc71c31b4f28f98aee1d980a8694b56743d371d0dc7ef321b2036448c39ff808ad1531f93991761618601fa2ff008d67a317668d4897fbb9e3f2ab6b1411856e7827df23dfeb472906840e982c8c1a241925b8aa972a86e943461d490d96ee4d4b0dd79b88fe552c7fd581daa6332349b11b710149dc3a63d298115e45e440c8140cae03919cfe558d0dbc2b049bced512e7637f3fad6ec88d3c65572eca77a8042865f4fad56b5b4215d597112af2c40249f6a417b1cddde9ed2c8046701f2d80d939fe11fe358ba969d30b669aeae16484ee5d91a648dc30df375e07b577da99f2258cc6aaaf24858aed1f291e9588b6c974f3c53b61dd198347819e32d81fca8924e360d7b9e51e21d39208e481268ed62f9079d1f06463f7971d40ae5a15812e14c2bbc249f7f71e8a3231e8b8c1e33f7857a9eb9a3c17565868d22456939232fb5bf8437f5ae1aff406b256104262b6d8d348abc99014ce03ff0000e157f0ae39c6db1d70f86c56d3af126bcb79e44f29187ee608950348338cf03e5faf5adcd22e26d4a6fb340d15ac72ca12488393236d3871bb18e7bf15cc347e4ea05634949455dceb11404960703d07353e976cb6a934cf2487736dcafc8002e7014f520e393fcea62ec5d91bf35dcf697e968d3451445679245817b2e3058f7c93b06719adbf0b341a86a690caeccb8692608fb0151c76fb9cf3c678ae56e357b282dd90c4d2348a872a768de5d763b13c90a0640c727ae2afe95796d68cc91b4715a809b767de93239439c1eb5b45a03d9b41db736303c88518c615221d8bb82dff008e8fe552c5e55a3ca37ef36e5a6cba0c6412b19e3b16c11ed585e1fd5e4934d87fe59cd379a54af380c1071feea838cff757d4e36ed1c245b360cbb3124827701ca83ec0f4fa8ae9391e92b8eb4637f68d231df1c64306618e84851f5e39ac5d5a1f26c6628cc24fddaa6e6dc59c12cc4fe00d6cea77022b030a4837e5a631af1f79c6d1f5032c7dbf2ac2bb49352d3a51921a5c44b1a83f26f60b9cfa952f8fc29cb515d1ca6aafba667731b061248919ebb836cc161938cfcdd2ade94659626695cf96083182980c47ddfc3daa2b980b49988012846c48a98e472c3f3abda3c521088c4ed55db82bc039c71f8f1ffd6e6b964a4f63a23266fc56aa74cb68998cb2bc8ce91a7de520673bbb93ef52cd65334e0aa895e6dca24c701f18dc3fda03b74cf7a216882b074d83636e44e59f0b8523b81525dbdc5cf96a0307855982a91c39fbc4f3d4543d371912c62ce67b2628f3c6446d206fbbbb963fef0000fae6b32fade031bc16e72a8aab21dbc3bee19849f451b998fd319ad06b78ad4bac4637d918695b2088720ee6dddcee1c7b541281e4799bd4c6b90b0631b492060f762cc43f383838ec712509671f94cd7015e7915b0a6360bb8ab15501b1d320f3d2ace9ee04d2162de6a06ded9ca993040503ee800ef1d7b532d2d67c471bfef14050a01c22ed2198e3fba58be07a62b44d9c3785e2f304702c648545f9599c805f1ec0bbff00c0a88e9a1524855b9fb5da59fcbb511bcf6407055098d324f707e63f41f85685b5da5cea72168f64388d7e41852add547d1538f76150269df6d84c50ed440912b4846dc2e4aa29fae7356ac2d96d279d7eea40a0800672d9083f0c8cfd2b6b221a48cd3611cc636903a4af0346ce8d82877a3b71f8f1feed2b4105bb2eddaca85a405b8dc40c73f51d2b78c303473a3050d082f23739cae377e7f363f0ac5f1040f65613984097cb6332e1724f1945fa13f2fd48fad6f14ce5924cc8d7bc476aaa10c91177c2981e611be71ced7c60e3a11d41ed5cc1d54610c6ab283bc1de33835ce6a1afdb6a37f29b794471799e69db8552e7e667c303b18b70c46735cd6b5e291653db24019d3cc5720b63cb52bb491ebf360f38e08a7290460687893c4f7504467de0c41c06f2f00a1c67fcfd0d7996b1e2a5bf97cd87e6912555567f9728c72508ee14f43e954bc4de39f2669d52445511cd1f98067cb50b8031dfa9e7e956ff0067ed374ef89fe2fbcb71744db69d1066455deeeec50290bd580c36700f514463ed65a1aca5cb1b1a3a7f85ee354d064bc578f74a4bc706fcbbb17c9c2fd2baaf089874ab4691d996659559dd9779da3b01d7f4af5cf0cfc18314b28168e96fbd88bc91044c5477e4e47e55c3f8efc373f827598a2801ba5946e678d0efc7a938c0fc715bba2e9ebd4e252e6763aad2af21b7b589a6976caca8e81bef16566daa7dcee22ae1d723f2102282235dcf90460f98003cf6e47e75c2e91e208a4bc30b5b48c40ca23670be582c1893fed02703271efc56ed9dd4da8dd855dfe424492ccc54b02c36955e9d095142d6572e2adb9d4da44f3bc32c81a4942860ae410ca14601f6ce735d36956d1da5adb97c6c424be0701b395727a820f61ea2b9c5bc589e2895444f342c1485c94899dcaafd30579ebc1adeb1d423b6685772f9e722619ddc8fe123d781fad549a08a48d5bbb51712c2b81e511e62303d76f41f5e4fe958faa6ff00b0c6bb4119658d990e4fa56c1b9b5303796045b0165e4e10938fd3bd73be209241208e3223907cc100c893ebcf1f85704ddb73a29e8606a9aacda7de5bcc435caf9b110637004a9fc43a641af5a8fc6da37962da75ba2f2c3b8b884ba15fef7cb9c7f3af10d7edee63ba914a07badc19954f183f7bd87b60d75de0fbd7d4f44962ba609e4fcea640ca7ccf4dbc7e59a2955e57635ab0554f41b5d7acae255820b195a44fdec53cc7ca4947a72320fd40ad517eb24acd3d9c2f2301e66253941b768e31f31c5703a7e9af72dfbb2c2e1fe6e0b7cdf5073c7e55d64325fc3a7aaa2c573b4aef006c6603d1fb57642ba7b9c12a4e1b6a6acba8457977f679185ba3444ed92328261d936e7007be734d943c710922b9014315f2e201ce0ae3e538e28975a4b37c8b697ed0cbe59b70982ede8a4e47eb5662860d4151a4b4d80b650727f962b65aec65b195e5eafa9318e1be8bc946c189c6d9117d59739fcb35069d2ad8492c37f792452cacc2199f27703fdd76194fcab726728b20bab85b0b353e66f92450107fcf52c7071ec4d3f49896f049f6310dedb337cb2db9de18fa16208cfe23a8aae55cd70b0d115b5d322c573e54d8e4ab926507af2c3f9d36eee9f4c8d04b6ada996f9375b4f920fba3600fc0d59bc8d0c2d14a23816402390150a79f43dbf0aa89a75b45316559adc0c46b716b9cb29fe12bc9fc69936650fed993fe85bd57f24ff0a2b63ec0dff3d2f3fefd0ffe268a92ac74306b2d6b32a989b1c11227f16467007ad4d7d2c3ae19216b7650538b8eac3e82ac1d316489248d33cab005f182171f953a365c286c4576a36b32ff003fa5753d3732306eb44f224f3a0baf33cbf94437033fcb15897de17babd9448f750af9a7767cb27e7feef55aeaaee5334c23243b32e4b74e6b2f544952d8432e77bf2a59811bbd54e7ad637b0f98f1ff001cf8235696ed66263b9b180332a28c100ff110739fa579bea5a46997b34ab06987ed967b63763c32331c8040ea31ce7a7bd7d0c34792e3cf59db12b8e448e437d001c63f1af25f16693f65d466b4997cb56943131b6d903af28db8753db3d31dab2940b8c9a391d2aecc1a94d6a5cc726720b1c6d1ee2bacb23e74a24dcaca8bb94039007be6b89f1e4571a74f1ea398ade0c812ccb8e6327193cd4be1ed659e453e679c265dfb47f7bfbbf4ae1ebca7a0bde85dee7a0c4f12c25624e24c6f901fbd9fe55a16d6721496e25618c80231edf76b0a0920d81d72261228c8e991fc38f5fd3deb474d92512389a4242bfcb93c1a5733346056b5da830475c37a7ae694219a355dc514bed2807f5a5b6f2e4dcdb89654c26ee329fdeab0d094ba8d1f0aeca1c0cf5cd6ab515c9228c3b462375dfd1881c0f6155af2292cef1768cc1f7c64f19ad0b7400aaaa6ddcc5c7d455896d1ee776f5f940da3d28b233918d7b62f258a4ee08584b32ae47ce0fbd636a36524b0add46c23c850df281b46dc1ae8a5fdeba468048b28da131d3e950da85433acee37c5272d226ec8c67a54ec6870daddbcd35bbc3141b644656c6de8a7d6b02f205b4d1c40eacc926d692361feb10b6ee4f615e997502caa62070a519580eac3b7358dae436915a2248a5e357642e0e70b9c01f4ace51b9a46563c49629ac9dfed5229f3d98c93484e500ce0633d7a55bbcb4960636e929795523562c036d1cb18d7b0dbb8027d41eb52ebda4ca6e24ba8c29411b397906417e38c1c7afe86a869cb09b9606691dd225608558ee003166240eacc3f206b9b94daf72aee227db240248bcf399e4e73b1085503ea2b5ade559e58da04f2d77ba492b461ddc8c77ec4679ed50de472cb05b2ac02dd142b79cc7820e72fff008f0a86e1228a4465775ca8478e1623e63b7e4faf1f31fe744558bb9eada46ab1a47b220b189b68661ce514ee623d39e3e9f9574367766e4b624680ccb262727a2a81bb03dbe51f5af2ed1753558ade5848549848bf68623f7c40e70a790bd871c9e99aefb44bd898477127cf1245977183e749924a63b6e2133ed9aea836f466124cd0860223633e3c92a5d0778fe6c003d78e2a3699cc20465edf2c50b67aa95cab0f753d29d3b4f2d9dbcb3124caa58a1c0f977ff9e3ad5788bdb45e6499690b2a82dd8043d07fc04fe55766472a307579183318df108cc411070a1890e49f5e2aa7872e4cb02c5ba31e49dd90a4b38c0f7f43b79fad6a6bb1ba402ce3db2069301fa17937b647eb5c07fc242746d5a1b260023b3468a80e230a43104ff172a7ae38c7ad6735caae6b13d46d5de2b99b6dce0a6e2ec98046790a1bbf1cfd2acc5765ec3cbb6da372b238c74573d4b1e327a8c678ae774ed5e0f13cb12d9bdc4ade6bb051f2ab6ee49db8f940e83af1e95d5a5abbc2121758a2fb8ab0a848c12303039ce07ddf53509291537676452376d6f143b3cc4556de4601566c819db8e7f8b83562e2d76c8925e32c769943230f9df0090101fef6091bfd59bd734dbed3c4114380cc9b8b4b9272a3a6071dcb31cf4c0eb4b146f219cba0ddf2a2e4e329d3e8077c9c1cf6ac2432568d25b8f9f7436c83cd4854e4b0e36827d39ab56f726eae7cc288634224fddfca3692770fa6d18159f6e4b87c219a1e5e5923e3cc233b147b74cd5c80c80471c9f7a770a1547de2403b47b75eb47dab9728aee5c96e6e268e4011796008db90c48f9573ea0f4ad1323a430ec90bb6c1b98af048e496fc6a08e3648374ce15fcd2a1572503336d2c57d8f4fe956ec188cc2cdfbc977032639098cf4f6fea2aae448b53465f50325c39dfb8f96fb7191b76938eff00373f4ac1f14450dbe853a3ced6e92030b8036849181f29f3ea1d063b64d692dd7daf65cee77f36161128cfcbf39e3ea315cafc59bc8edfc377a2693c813a0b7ddbb811b6e5ddcf4f2dc2b13d7eb5d11bed732b23e6ad7759786f2595dd6372ef1cc4f6604961838ee38f6af26f1efc4f8ecae6eb4fb79b6c2517cce3799083bb6e7b0c7f21547e25fc434b69a65dde7dc0251e420b92d9009c0efd6bc7349966b8d589b9951e596507f79288f692b801b3d3f1fc715ad3a4e7a9139f2e86fdceb97bab913c9b8c8ec5238230490ddc003924d7de9fb1e7c0fbff0087de157d7757b53677fa8a00f6de5c4a36eec8f30eddd21fad7927c0ef818748f12596b3e20891afa27fddd9291fb965380c4ff17e19afb3349d511e085923c2295532138031f5207eb5df49727438a726e574cb57337882ca51269d7405bb157f28ae703ba927271f8558d6fc3f1788b4f962d4622b35c2e663261c7d1986322b660bd8a706e27737248c318e3dac9f503fa5669bf5bc856474cdb12e8626c2060adb4b6d049c66b470e695ee1ced6c8f9f357f87b73e13d4657f3a392de34c4515ba90cea769241c9d87841b89e8a7d69ba65f2595b0899150c9b4b42092bb17eee581ef5edbe295b7bbb031bc56e5641b1d53e5675e7ef6793dabe79f88d723c337515ec2816c629374891e42bede8c063f4ae69251d8d69b72dcf41b513ed5bb64cca029219b181c71f439ad3d21da4bb7b87d912ed20b22e3e73d18d79df843c66ba92346f2aee999255f9b7838dbb47d38aef34bd4a232a3b10b22cc9b8b7461f2f6ff00810fc8d73c9df43a7951d1991a399da55f2e400a7239662d9231eb54ae64568e61b51d41ddb48e557079cfe18ab76fbee64612e646501b71c70c577063f87f2357a3b7824597044990036e183bf77deffbed48f4f987e1849271b3348c97638e9f46176ae50ba6006cf3b581ed8ad7f0a683059192791d2175e54a22e5d7fbf84005689813cb70d204dc761c3e572bd971ebdb38abb66861b622d9921b8116425e26ff00938e0631c73d7a5629252d4b1d65711c5a91f2e7706442aee186180fc78adb0d25c46925bdc1b79d813e6b2eece3b67ef67f1ac6d3a517575e74ee86350ea5559c67e9cf3f8547a8ea118c1b6b86963566914310319f5e6b48262947a1a7aa5e5cdac1e4dcc51dcc72ae05c1936a93ea39c21fc6a8f85bc596fac486d575710dc5b9512c65c666278e3d707838ad55d645cd9986e205b80c3cd78d8ab2b7bedec2b84f04fc3dd1b42f8b1aaeb70ea334935fc1e5c167752aec51bf732a9e8e73c8c9271dabae94bdfe538e5f07358f51b596cafc8b2949792455598381b327bb061861ed9a5bcd123beb09ac1ec6d574adbe5b451bbc11b2ff00c07a2f03e4e9d78ab8f1cad0a1464130276b6c0cb1af618cf2477edc1e69ba9d8bac48f2b963110cf133ee52c7b9f7f6aebb3d8e5e6292dd32acb15bdcddc334615944003321071b06e1cafb9ab96d78a8821bdb9b937485a252c8ccc4766dc0019a7ac36e7ca314e6d9f1ba350c3e74c7fab27d33dead894db908ce43752a46173ec3268b3052b957327fcf69ff00efb6a2ae79f17f7dff002a28d07767672d98b556d9b906cc0973bf9fa5665e69c9296733176d9b324106adea6d1c5129f3c431e40528481cd41a65d3c6dba460e48dad316ea7d40aa4dbdc562844120dcaeab336decc370ace874f96e02cb24f2ee47ca42650768ff7315b57562fba496381e49b6e1b6c9b40fc718ac7b8d0b519acb6adec56f3a82a2dd57298ff006bbf3f5ab9448b146f2f8ef9228a5896741b429c608f66eff8579d78974f693562d340ce5baca3852de80f4aece4d2e6b4c7f6924b33baed693cc0ab9ff602838a6ea1a335dda082298b472295547e1001d80f4f7eb584a451e25f113c0efe20d2258ac2fa5b5819c48f1c8cce0386ce473d3dabcabc3f16a3e16d664d32f9c497117ee9a6073e62633bfd8d7d1fa9e89e63cb6eb70d04a387007cb9f6af9cbc7fa6dee83e27135d0de8e77158fd73d33e98ae6ab1e65cc8da8d471959ec77da76a92cd3c5e64a236dc14ffbbfe23d6ba3b4be69248a18480dbf030b9fc7e95e61a74a2701524627cb2ee5b82b9fbd5d8695ae451ccd1dba1f9d4aec0df740f426b920f95d99dd28f53d062b85462cd85476dadbc6ec2fa0f6ad080f9d21778d4c80aae48e5715cadbdc8fdcee7227c7ca77679fa56b595dc9088f201017ef31fbcde9f5ade266e296e6f4332b23151931170bef5a303e5dd9c1dfbb3e603c7e559b6d703ca04e24936b1181dcd5bb5989600fca3ba2734c8924cad732797731b04dcc5f002ff0fbfd299790b8549a304b2b7033cafcb8fc6b52e203240b82a5b6b2865519c0aa72bc8f32281bcab6581e294b5031fed22439917ca751b46477f4ae6ef50ea115cf94bb54b11b473c86cf15bdae41b8b48a5c7967cc1927e63ef58ed1c9a7432970512591a4310ec4fa1a90391f145ba5d5935b1fdd00ac8c3ef704a8ddff008e9ffbe8d79cfd8a5b3b84da3ca52825d89919619c479cff00d3439fa57a96a5323b82200d2ca02f980edcaefea07b57976bb712dbdf5c990b48772ed5f4e48761fa62b2a91e53a629929d5d6e2f7ca9333c8988d57f83202e4ede99caa8c7a9f4e6a0b99c98d6cd8f9c64574cc0368e7a8cfa8f5aa9a6bc3119e158dc4d2b31dca7732392063fef9c2ffc0d8f619d4b1b480c71334cc914722ed900e4bb7df5ff003c564b5d8a2bd86acd6af25bbdb86c6235320cb609c920f6e7a7f77debd2bc3f7d6c9692bdc5d26d4411e40d9b41385007a9eddf839c5797de32ade86594a472160c71caae3e6c7f21ef57b4bbf16e96c8a8ccea586d7f98202ff2fd5ce4e4f6e319aa8cbde096a7af49ad245e6ec2980211182a4fc85b248f7ff6bf4a9879722e59b26255c83d892a71ff007ceefccd72365ab974fdc9499546eff583703d90539359daeb1c87ccba672eb26e2047f21033eb85dc4fe1ef8e8e632f67e65dbd0f1cb717182d220dc23dc3f78f23649f63fe22bc33c61e1ebffedebc8ad243223a22a29cfca8b8c2839fe2dab93fecfbd7a9ea179e6d9cf2c72050ac3e661d1bb63e981fad739a2917be28b58e660b348c01ce40ca74524f43f5a96f99d989c5ad98cf876755f0e5c2b14cdb31689dcb19218c9ec541241fa915ee1a46ab0981c19fcc927817929f33423ef285ea919feef53dc9ae6eefc3914b7a27c411320c8290af99f5da18303f54356ec2d7ec5018cee8a3241948604391d33ea3db3594bdc91a24e4aecea65bc88b349e544c593f75090c4aed1f2f4e08249f97e9cd56b8d3de0114729dab3300d106f9a403ef2bf7e3deaab4a11602e0c4073e5c2dcb85e9cff07e19a67db56377915c2c8c02958d4aec1ebbce4b31efc565245dae695c5ddc821a28b102feed1147de6feef1dff4ab71cf25944657223236c59ea5db6e362fa7d6b0c6a7e55cc0b14522c0cbe523292b93ed9ebf5a75c6a3f2b44e81b6636963c2a81fe466915b9ad6bac2b2b5b46c0991f0199c74dd8279f46a9edafdac67bbc4bb524491519baaa15c161edf2ae3eb58af7e6749d82b43025ba108406608a301071c64fcc49c7e3576198bdecd2c920915e141e6aa71b48e768f4e00fce826cd1b1697053eceb19658a26703271c9605bf0eb5e7dfb43453df7c2ef110b75c4eb6ec638701bcdc60edce78dc735de2dd46b0c8ec5619257dde59f9b686cff2c73581f10d2deefc21790485556560504c3681b5b6f5fad75524dcb539e6d4763f3b744f807e26f1d6b731bd9a1d0438171e6ea28eecdb9b3c040471df9af64f879f022c7e1ecaf7cf74da85e04788dc6d68d007fbe11071cfa9ce3b57acdaf84a1b18d05bddcaed1a348b1496e40c1f46c91b7fce2abd9cd15ec36514576f2c574f27932db0f3226f2feffce3e5e0fcbd7a838cd7a2be1b23cd6a5295d9a1a4d9f9704442f932e0a212db4ae5b2369ed5ea3e12f3c5bb6d68dd9471241f3391fde3d463f0ae0b4fd16fae1d5668f6c6aea32ebb8f1e82ba24d064927455b97b5988e4a92182fa6062b48f32dc67a37db6eada78e5776b98c20092346921527d4041fad4773ab3c70933bca400c526fe223a91903b9ae78dd6a3a469e81e4b730e14a1b81bf763fbc4100fe758375e3f4b055b713f9814067da1b0a3b8e075a272b6c5ee6f6a1e4dc59cb3c88d2321186272c7233906b86d7c5beb36735add5bb3c4a8d1a4a472c4f603d6ad5efc44d196eada37d452d7cc650b0f52c8171f4cf4fcc5635c6b30191e6811d90b8e030f31c1fe2da7a1ae44dcb71db93a9e657fa3b780ee22bbb173fd9b13099e265e461b6951ed9aefbc1be32b1d72d0caa59249b79456520f18c673d3a0eb54f56bd86fec9b292a47264179e3dd904fa7b1eb5ca43e0ab9d127373657091c730454b6b63b0073d5ce49c9f638a392cafd4d633715767d050eb70d9c0d71713465d25d92ca47f0e0ac40e38c95dddfd2b46cf529279a48f289114922df9038da084e7bee1bb35f33ea9ae78c346d56d659614bcb0da59971b1913cd6442643c165439e95d2587c58178f247772ddf98519a54542dbc671b8f1f29dbfad63389a4667ba3dd412c6d9277b2b8991250bb8afdd39c633f4ae73c5de37b6f0f23192eb60f2d5822e19a563b7f784672ac31ce78f6af32d67e2b48b1cb0e9a8d0cd85db35c3f30bf73b48c11f8d72d7be14f116a7a77f6dbf9b7425df3caf12fcfb4f5ddfecd73ca9367446aa8ee7a6e93e34bbd6de265904164a59c47e6755ec463a56fcfab5a5dd9cb2dbc8b15c400aee5f9f91ea3faf4af98df56d66c81582da5496420a6c5d878fbae074c7b57a67c35d13c657da7ccda7f87a6d627915599249560428fd18cae40da3baff003ae9851763275a92577b9d9b7c6a5b3b5962bdbb16da95bb79436908f8ee1811d07723205727e31f8eb2e896d1ea90efb89a39965922f2c38942fcafc01c9c775c576d6ffb24db6bda835e78975f92dbcc1ff1eba7a0ca336cd8be61f90ecc36e5db86c8c9a9e6fd8b7c39a96a3e7c5e267b8b71323a59cd6884290d9647c1f9c377fbb5b2a3caf99339a55d35ca8f4f87e219b3d123d461ba93511731adcc56a1d50c68ea18affbb863c9e699acfc67f0ec1631dddde9f7715a42048ed2a98910838383d585771a568b73a759ac221b178e28fca5f291a32146d003649dc302aaeabe19b39e1b94b2d16096f2e2394c6d3c6df664900fbd205c6e527a28c0ad649ca5a3338c93dd1c7f86fe3459f8d2609a5e99737c0111a7cbb63538ce3711d47b56d5fddf8846a0a34fd36ca7475dcf110d84fc6b4342d0ac7c2da6c16ef7d62b783e5966b28042b2c8c4e58440b04e063af5ae960dcb192ca373705f6e38f7ab8ae5dd92dde57b1ccfdb7c51ff0040db1ffbe4ff008d15d5e65ff9e69f9d155cc8a135a8e49258e3dd2456e76b0050f38fc2b6ac6d964b640d793c8187dc23041f6f96aaebe14a060fba446018738507d39a8acadd6571b662d21387072027d2aecccf98af7b7087548eda3967543c1da4ed27d08c62a7be8d6cade494348f1050c36823763eef3572f6ec595b08fecb2dc349c0c74fae7d69b63a96db758ae11db70f915876f7ab0b9c52f89e367789ed99e266e33952bf89e3f5aa577a86ae61416d12aabee4562bbc2835dadde96985c2ac90b1c889f953f8560c96d0c3a9068e3921d9c6d47c1cfae3a62b1924c9383d4ded349d32792feea77bb755e3196627b0c579effc239178bfcd7bc02e5660a5500da173d81f5af53f174104f6c543169629372e010c49fbb8e3b570d66b1addba47200e8c649110f0a7b015128fbb62e3a1e45ae78653c31ab496713c42d0af9b1a86f3190eedbb3ef723356a5d49e282da6276471c6e59147f3abff152eed46bf662491269c9e12423319eb800019e7d6a95adcacd661995200f333f9813712dfde23d3dabcda91e491e9d19fbba9b1a3eb9cc723fc86560e09e4aafafe15dc5b6b1bb322811ac8b8453dbfda1ef5e5f636913c8cc4323a465b73bfcd2b1ead81c0f615a565ab4d6732c17319dcdc2f3c05f6f7a716d6e5b8a99eb3a45d9568a26ce19800c7ae0d6cdbde0c1f3004c6791d4e2bcfb4abd59590c4fe682b1966cf0a4fa5757697b6fe5dbe30243b8804e719f5aa39dab6e751149122e5fe40c14a8cf27d6a2b98b2269376e629853d066b3a3be0bb07dd695709bbb55f2e8a590b80ac06f2c71b73d855bd0460ea48f29d87795283a13cfff00aab9bbfb8b9b786433e64200319ec78cfe9fd457673ddc6a0e72087554c9eefd57eb5cd6bf711223c20804300a1fb6e7c3018f40abf9d1ccbb01c6eaf241348a9137d9cc8a6481f70c1623951df19af38bdb81adac8972812e62578c81fbb3f2e5ff001ca8c8ff001af51f10e896df6537332790e5f629c725f19e9d8579678eb45b8d4a692eed6ea2b092c6369d7ed1205e1087239ea7786fc062abd8f3f526336ba9ca47ad49a7da91732184967ddb4630173fbbe39c9e306a48fc468136c2d2bc70234610e738ec7ffafd6b9bd5b598bc556aef2009791a36e31617322fa8f535e7171e28b8855c4289fba531b019ddc7af35cf24a074c1b6aecf7886f05cdadaca1952df6a88d5ceef33e6e4fae0b7b74a48eee77bb48c9291c8e11dc7601b25abc7748f18bc71a4c4c80e43fcaa4b61070b9ec33e95d0c3f12d2dda1cb64a03b95863e53d09a8e54f52b73d2acfc5b3e977cb3bca49810944017080e367d58679feb5a2fafc5292b0b19e6b95551f3e0e5c73fc9c7d1cfa57912f8b2de53e6e55d50b6e62782d8240fc38aa91f8ba158da52edbe1249740703687231fcbeb55c9e656c7b4dc78a61b79a7d39544d1da92e595f21988cb73e959b6fe218a7d6b4f44550a1959433e4bff007998f7fa1c1f6af2a4f19431c51856cb6cc08513041db8ea3afe354ffe1307b2695cab480a0520a9c36eeb83d41ff6a9d999f323eecd29a292d2df7dac716d518899397cf7c74a74168b048b2cf02db44c7e47991b12fbaa639af36f827f10adf58d360d3ae1a7b7b90bb0c52379580be92ae3af6af52bb85bed415ae278f77ca5a69bce775f729bbf4c539c6fabdcce2edadca1757492dbcb1d9f95344c58b94127c8a7be55891f8e2abbcaa8d1212a91c6aa58bb80c5c7a7f7456cea2b0c3018da4c066df24d70d24bc7f758b901bf03546f259b600b04b216604b222461b3d8673cfe95cf2d0ea8b28cc1e49a57dd2493045850b7dd21ba282784fae692e628a58c98dc489848fc9db9c8072d93f5a74c8cbbe158dd5e43be50ae198b2fddcb1c01f80acf79177c2b0a3485f73948df6a103d0f5fd2b0936cd63145c8ef8c2f388096478c2184b6586179dcdfc5f2feb5a96db4dd41381e63c85a245dc4003ef2afa727f91ac688b476f07cd18959a4610aaf2ea4fcc73f4e952c519bab8934e562639a466760e400c514f5ec079840f7145bccb946fa9bb04ad733178b2f110a88e0672cc3271f937e62ab7882d6de6f0f6a50dc5c25ada08a42db9d576614ae4124ff0074b7d455ed39592d6da1810e7634585e8bf30dadf5ea3eb52dc5c4b75a6b470b33865f2a24248041c64739c9e0ff00df46bb29b68e3a8d72e8798e97a36aac5ade58ee5ad1c62191250c8c9b776300f031eb5d6e9fe1c371a426922da086d9550c2f0201e511cae0a71c1249e39e2b3b599359bdd19edb47beb7d33527f2d63bdb887cc445276be5460135a5a57dad74d82d2ef5117d711afefe7db859bfbc42b67f0af66325bd8f325ef68848bc082625ae35bbcf2a1391146a4303eec2b4f4fd0ad74f8336eb34f84f9ccf2ee71ff02eb54e4d6608ae62b25593329c08a056c1e33f336703f1a472f13b4b6f218d3a80e4b06153ce38a45cb8d1ece7ba5b9891a69d89cee73c01e9dbf4ac4bc782098c2aa8800cbb2260b37e5d2afddcba85832cce5563c31073d7359f72c351748a795848a768dbc163ef5cf2936546c32f7468124563676af1c91fcc1821627dbd3ffac2aaa8b1479645d3a38245237dc042e6341d4eec62ad14903089eee468e3e5890338fcab2a4d1ed7c42de669373e5292f89c9656241c1518c82077c8152fddd10d2527765a10692435d595cb99652432e04aaff3fcfdf036f3dfb1ab634fd32e951a7b6762fb809c4982a7b64639a8349d03fb060b41e5f956b3191488936a07dc4b381eadb7a7b9aead21b67b50fe6ac3194dc7e4181f9d2949b15f5b1e4fe29d25a1d495343d42dd227cbc9313b9e038fba01c8208f6eb5a3e10d12f16c180912fe001a4792388677138ded8e48f6aeca0b0b4b99ee2e6d2dd48ba71244a36ee72170a375685b5833db967892dee401bd54e5482d9ed8f97f5f6aa494b713d4ccb6f08e977f6d2b6ab60243b33b960dc5cfa0aeabc1b6b68b63369f0c2c0089b7978b04a1ed8f4aada78950bc71d825f2a7501ca156fc4f4adcb1862d3423b47169f21e47999248f439238ad34ec45a44369e01b499d5827da404dab148a3e51f523a574f178720119848b9b758092cb1280003ebc63f002ad5bea32ac113b04bd89a3e1622394f5e0d5db6b859d15d51150273160fcbf524f35bc6568dac0a37dca1a3985e4313c4e90db96884d3c7e5b4aa7b827f9e2b4608e140d1ab4004878545d8ff5ddeb53c970fe5a1400a921565cfdd07d43536e24b864409f6563d2312a6413e99c75acc7a761255b98123fb3461a52dccb2e4ec8fd40c73f8d3c5cac67333aab85033b4ed623fd9ce3f5aa51497b35cba16b1262405a6463d4f6c75c55cfb14b302af960172c9165943fb1c74a760b24452d9e97f6e69563896e1a34592431ed0fcb1cfb364fe5491dd090bbc2e596300b2b1381f5fa7b558708c1a173184655610ca72c48f7a4b784c766e164411b13b6304700fdec773f8d1b0ae41bedbfe7e61ffbf94554fec1d17fe7d87fdf5454582e69b5a5f5fed91b72c636b138eb8f5ae934c822b7822f2e362e786329c96351de6b905a6c462cca4e5c138c2fad53b9f13da46866788b841f2842793f5e95d1295ccae6ddc5d858d810db94853fec13db9aa2617b952630482df798e303dbb573773e276d4e030db5abc52b0550cefbb611dfdcd569ef358312259cf6d6f22fcaf23425a50bea589fe95176059d7e7b8b5bc823837cb3bcb8259486c7b0ff001c551d4bed05627f2443320dbb81ce47bd4ba368f2c371234e4b5c48374b3b31727e833c574f2c28c9e599125917ee88d719fad5f2dc763cd35abdb4b688cb7f2c68c7700c641f291d3a579d6a09697d6b2490ac6b2bc9b9aee152bb8fd2bbbf88112c334110b20be66e72e705883ec4572375a5cb1410b94d9096c970d8c7e1594b728e13c49e12b4d52c5eddd76dcf58a52a0bb3fa07af278351bad26f26d3b504df2428e0c8afb491ea2be83d563b7f2e088b98a6964f2561552c923fb81f74fb9e3debcb3e2568b6d6f64ed7108b69559a65b845259b233b4fb5633a6a6afd4de94fd96e3746bdb76b7cbac624da31f3648c2600fae6b5ae235fb8ce374a76160725cff4af15d23c4cd04ec551a48d7e4f28720fa9cfad767a5f8b16e6649ee400dbbe44ce706bcc8c5c1d9b3d1e9789d8c773368edbd98089892c33d154e01adcd33c4f14a891993f7872bee547a7bd710faf46d6b2bc712cd234ac4478ce540f971fec93542d75468e78f7b0572cca760c94cfd2a94efb14acf747ba59eb11b450960cf26781b8fcbee2aeaea73de09d637451c3a971bb790393f4edf5af2fd1b5633cde63c8ee80190f38c443b915db69b791cd02dcb27cacad1aee38183f371ef9ab52e6dcc651376cef1a7955a77428a4b2e4fde651907eb551edd2e6cdae14e76b4b30723a120707f1dbfad4b1884e9f2ee0995752800e40db838aad2b79af1c7b9c5a8624f380c4a923fa0fad598116b36cda85b4ab7855731871f2f12e5400dec7e6fd0d7c95f1abe203e88f2db58edb88e45226743bb6b47988a9c83cb6d0c7df1ea71f457c42f1d5b681a7dedcc922b7971b3c4bcf552a1547a8e5abe5dfecf9f5dba92ea4126f99d9e56129246704b14277751e954df2f51d9763c757c5f75b084f919b9dc99f998f527d3f1a8d75b4b9dde6e373b365b18dd9af5b3e01f0f34f32df5945b94122589197f0600fcd593e24f8225b481acf8790883efc9681fcd283fa7e35cf38396c5d3a8e2ecd6851d1a6b49b4e8d64b1c2c2eca6ea15638cf670b9d9ff0003c555d7f4db29ee1fec05fca5dac198ee2d8edf4ae4f4bcdb394316c62bb0a98860c79c60e318e7b8e6ba591e31195171e67ca7684524f4cf403bd735494a12e53ae09735cc89a39562882c8c204664423a02cb82c3d4fd6bd3be13fc301e24f0b4b78f203be6748a3452c5ca8620119fef1fcab81b3b19757bc86d21566b999c2a228c904fb76fa1e7dabe9ef047c3db8f0a5ac50413cf2b84dc1a142be71c6588ed9ae8c141c9de472e2e7ad918be1ff80ba6c1757525cdc3b205dbb49dab19f76e9c7719c8f4a5b5f0a787e79240a37c0246f26e586372f638f4ae87c546e26d1de1bd517cb66564b7b2b992348f70fee9917cb07fda20e3b015b3e18dd35ac339b936e402e619023b2100e57038e08c75e4f4c8e6bd8b44f3ecfb9c45adc43e1ef10dbdf431948626064923263047f0e1b07a77e3f3afa2b46d53ed9a75ade811490345969c7dc271fc2ebf29f4ebd48af2ef19e9f796ba5158615b94b862cc020214000b818e879e338ae97e156b7f6ab216d05b9b3ba87e52c8a18bf1bf8562197903a7bd735487bd746b0972be5dcef2d24fb480c230f1c6465c5c2ab73dbe6276ff00df2692e237b8f318c6d1e142a26e070a3a819edfed50f68b1c8b25d2c69286573e6050588f52714fbaf39a695e408eb186236f439fe1cf4dbfecd79b54f463d8c89f2d67e732493a1255bc98c98f27f438ee4122a20f0969a6f90382b12c6abfc3df152849cbab4ede74d70c7cb476dee41fbd80c4845ff648aa525aa4524844afc95ef9393e95ce74c42c6d126b8c1924f2ce19811fc23ef64f65fd7daba1b5b3b482d0c6ca18ccc245427048dc18f3db1819ce2b361be4658d64c79611c443185e7ef3b1eadf438a9e09cee4f271ba550ae24cb1289f7b77afbff5aa2f689bb0a2c50a205241c3970402007ca9fce951e19ed238f9da1731ba0fba3b37d7a7e62990b22b9b78e455915cc92cae376d4dc4e3e8706afc77313c41614fddfca830bc855c60ff00e3dcff00ba2b58b6724a28e0b54bc5b1bad5207314302b1992e67708891c872a793b7db83d6a85addc923caae213288494956419643d38f5ad1f1ee9eb2ddd809e12ee17cd523072d9c8c8c15214f4150c0b141756e7ecbba6c090bae09561d4e00c7e15e9c26dc6c79d38a52d07432dc4568c67f33605cc8ebf2abfcb8cb71cfe7574f9e2d1643fe916b2a8db228c8191ea3f2fad656abacde4f7223c23698c8ef348c486dc3ee284c631ebcd3f4bd42d16d655b333c6c0f96f1c618ab15393f2e3039abe77d83901affcb50972db773604ae3200f615a30476aeb837f1160be7ac6080c3dce7b7eb5474ebbb7b891a43244eb2a677ca76953e98355751d374dbc9a098c68fe4bf98648ce416f7db9e3daa16bb195ba1bd0e897570ad2c36d25d461485251b098ee3039fc6a95845a6caf791e98d0c77364c1ae92d661222330dea1d4709b87073dea37d42e2c5d0e9e1b4e421a32fb176007bed3567c3d6bac4091cb7b70faa46ccced3a282ca4f031c9c6d1d3158ca2f9af72e2ed1b1d7691aaa5dda0cd9a3b31679712025980627e5ea3ad646ad6b64f2caab1b424fef59198ed53fdce71c5666a7ab1b1557856e37875323c4a564033cf6f4abd03437372971009e50dfbc695c6c63edb4e4fe95a582c862d95b5a287b80b6b013b83f404ff415a1676bbe667827325a314cb2825598f4707a8cf607155e5d36ddee52eef275f2a350a41605431ec455d985e5cdbc4fa5b477a9210a4cec4051e8460648ed55132ba3645b431a796bf34a581c96c97c8cd6adb48582fda363a6cd82448f200fa9c8cd56d2e07b7b6e61018aaa80cb80182e382706b4624b87304122896075cb794db5377d3d2baa295ae1cc45a5dce99a634ad059aa396cb05906e73eb8cfe82ba38f73ceaf08f24b72c9229231ef9acfb2892d92106d162dbd11caef8ffe05deac432462e64f33c9487aef9a40491edf3531dfa172e490e8c6dd95e474c1fbe31eb8f4a8eee496d22966895b963b8019181dcfa1fa66a37b948564996397eccacbbe4438098ec3da9d6c6de57fb45a4ff6f2cacd1e251f283edd3f3a81f4b8abaa4691092dc35dcadb19442818b2fbb01d47a75a6cd0df4aa268a76b357fbd04d01703f104552beb8d4609dda2431c53154103a73213f78e463047f7bafb569c092c106f95d58468a17e6ce49f5e6aee44b5109861b6135d95da70859e367d84fd067f4a24d3e319516b2abb9c24e00da7e9cff3c531e3b9bc9d42c91db39da584792c71dc9a9146f795639248491f33b3ee527fdc3d2a65a97646679b07f76e3fefc1a2ae7f659ff9ef6fff007ed68a8b31dd7637adbc3e236792e6612492722463c9f603d2aedb787ed6e603b3729c028bfc073fecd3cdfadc4be5ca8aeb19c03b718fa55936d2b213e69c657803903dab539ac65ff65ad9dcbc53400020b878d76fcc2a4b9d3c4d20763f32fef239547cc17d187f10fad599642ee9f3154019583bfcc0fd2b264d422b7ba7f2e676f39b8dcdf2affb3f4a572ec59864c0cc663784f25d46d2a3deab5e491dbb399ade46caee596ddb83f95412682b2bf9d6e25c87f35ca3f7fef63d3daa85edeeb163149020458dfe559bee91f862aa2da28e4fc545750815da68a1b4794ba19640ce1c7fb5d73ed8c57357f70b2db3b478908009247073e82ba0d53c36d6d23cf12bc920c49233ff00191f78818c0358fa85b7da21f370fb11501288767e638a992f7ae0642b30b625417764d8eaa304afa67bfd3f5ae17c528f74f2c40a5c8fba88e7a7cb8c1e3ad7732ddec91a28d82ba9c125bf4fad63ebd6a6759e4db1add98b0aaabd4fafd69474096a7caf169f2e8bac5cc46145844ecaad1ca180ddd41f4aef34af0c5add5961846921c88983ec3c7de623ae077e2ba3d37c1f6c6e85cddc1224cf2798db48f9dbd0f186fc715afafe832dcea11dee9710446551219e5c3be7efaf238cf7ae5ab4b9b546f4eb34ecf639b5f06c56a6186dd9e5f34823e628a08fbab9c671f855cd3fc3e009d4ed5b7dcc06d386623f5a67f6d5c69b35ba5e022f24667dce73b4838c525c789eded1d9db6c8b000d803927b8dddcd704e3fca7729a7b1721f0e34104f73f32c2e5b6425cb12738196eb8f6ad08b5092dae2d60b9076c6d8f309c02ff00707e19ac76f8816532b2cb751406e250cb1a1ced50383f9d65ea5f10aca7b596dd2333c913abc518438254ee073d71bbafb5389773bcbbf12a40cf0860f1ca40dcc719f75f61ef8ae43c4ff1c6d74b8e4854f9f3c730db0a1c9c0193d3fdafd2b8b9350d67c4f968d7ec96f14443c8c73b81fbdfe4564b69da3e97731b4f3a7da1c33090b6373119dbcf7c7ad5735f40d3b18daff892fbc45e5198ba4709654476ceccb13db39ed505a5addce0b345b215508d2a9c6e5f5f5e3eb5d5691e15ff008487515937aa440865555d9b8640e475e323f3aef2c7c136c88d6f2f9724aecbb79186471938ec4fb6734945c8551f2c7638cf0b7816d2f1434eeb24ec63648e2c8c8df9646c9e98efd6ba2bff00083e9b677175a5ba59ea2a367d96271b587f75c6793593a8ea09e06f10416524ac88eee512588824738e7db0735d6b78a2ca69f2c52dee2601645debb7cd3f7467b33760715d2d382b9cb73c07e2378463beb23e22b74b11ab87696e60900689f6c7b09dac4807f8b1ea0d65782fe06f897c4f15b4b737365a3585c46ac3cf7f35feee1308bc0cfbb8aea3e28d81b6d55e28a77834ebe755b84386d8edff002d071ee7dba0ebc577ff000bbc25a8781fc351e9baa6b89aac48ccd6caaa16430b025626c8e4ae2ae108d47ccd18ca7287529fc3ef84fa6f836f0cf7525cea172a16392e54058962ede5a75443db92d5da6bc2df54b29218ae66459565882f986160b9f9994e7e538e98cd68dada45e62491bb47163779b292eb93fdec0c71d856dacd1790a8b14934a230892ec545e7af5c9fd2ba2318c748ad0c6576aeddce76cd2d2ce032a432dd4ee3e57976afcfec178c7e159de2ebed667f065f59694f1e9fa9323471795b4796cc4670c4633b72a338dbd79aee34db3b7b4b8f324b58fcf38542edd1bf971536a1a2af8b6530dcdf9b711fca2dd502a39f5638c31fc6a89bb387b0325d58be966e3ec71dbdbc23edd7332c50305183b9c9c17fdd92ca7070475aa1e03babad0bc5135bde0b68e4dfe627cac6164cf079c1391ef577c6ff000f3fe122bcb44bc45bcb5b11208a368c9126f8ca10c070e393c100fbd67cde1ad4ecececefad2302f2da302398c8634509d142a019fc6b2945b57b9bc26be67d0da4dcc97c8447711ba0c7ee83007f50718f7aab25b3492c9e660a2b702dfee11f4ae6fc09a9df7897468d6f6d1a19573fbe8761ce7aeedac49fc6bb1bcb1e16181994e02e138207a1f715c35636dceb8377b9ceddcec6f5d5cbf96c31857500afa98c65bf5aab35a82b0cb1c4402581f9cafc9d88ff006ab623b6866170a2669d62706773fbff00309fe0c28fe4d55678996e848a77ccfb4829caa83db1eb5c5c96ea7746a22a5946b142f9da0ed2e524cb1607a29f415ad650349223c30f96ca98886ff99f2c155bfde62791d3de98f1adbcc1a4648d595773020e47b55cb464826b79159422ba10e4f720b71feef18a23aee129dcbf6b62d72482576a904468bc000310b9ee39ad38fe5102c585558f6ca31ce24ce48fd2b3e1d42348c45148b1985c12c7ab2141f31f6e69afac4e72c405f3016c81c707017e99e2b68e8672d4e5bc77781357d31200d3193cc0d0a36028ce060fb5621966b49e3897cd8ee995da1b7418dd1e71b8f703f5f6abdac5c369facdb4a42a931c87d586e3b89fcaa0bad693cf88c6b289654e4888b3b1c7ce030e873d074aefa679b3d276278ec268e28def252b27cd850013239ed8ff001a963b2d4a649aeda18ad2150047bfe762bdddb04727b53acb4543135ecd2c8b3c8777196468fd3d47d7157add6f58470da8021c06911c649c7451db15ab696e657d7728e94552e658a42ece5c1548540193db241071df15af673c42e57ed96a660c3639dec36b7a614d52b5816d6fae6496578f69117944e1101fbcc07f09f4aeb95ec9632c8ea154618c78f98ff7b3d3350da8ec3fb573161d2de366b8b7836c2bf7a17214edf60d9e6a2160ca3742ed00593eebb6588f70302af59dcb431318fe525c29c0c38cfb9cd4303cd7fa93c1706191a15f34f95f3129fdee3afe19a8524f626c45143709745bc94f35b6a9965c8041f519adbb83342be5c7064443924e548f61d7f5a862b7b7f370d95768ce448772e7b726ae4d1b985912e95005c46eabb827d3b9ad22808229edafa3045d40014547dcc832c3b9078cd72f178badc6bf69a7d9c335e4370eede62c6c62257fdae833db38ad39fc1e75d9a2f3242f6a062548d8289bdcf1906b7e0d26df4a8a77b28e2b79e152567032c9b7d57386cfd2b48caa137f23a0d3ade0b9191008da3f9b0a3037fe669f792da416e3ed77b15a42ec10991846558f6e48e6b93d06efc41a9c9793eaf1be996f1bb4502b208de445eae57a8fa1e7daba336769326e9a38ef0485595e76032ddce0835b457bb60b22fa4166d66c91df19c04f94f97bc39fad36d4d8db3a9105ccb72ad8c4ea3a7be702af41691246cb1db606df9429d83f03d2b36eb5192c96e24587ce88a676c330c93e8339a99cb976147595d9b71ea92232f980c8f921632a36aafe1c1fc6a3feceb6b7321b79e4b112beefb384c077fef671c7d2b8f9fc41a9c926ec5cc56aec819213c220fbdc63ad7570dc5aea4e891aa8b6d990b272d29f43f3714e13e685da2a4eeec3e7d52d74e8246698b727285b76f27ef60f6a6c97306f8098487dccc8d18ddbc0e83156a24899da33144b22b7dd68f660fa0cf7aaba8bdc698dba2b5172ac5bcc8637c707dc74a4485f6a773786782c222dfba1c2204dc0f6073d68b358f4fb506749637caa9de37b64ff00b42b59e54b7882983c80cb1bed7e7613d89aa1736b25c98da36313f9876889f2236ec41e847d695d157136597ab7fdf068a6ff00676a5ff4159ffefe2ff8514c67690590925f365915d8ae4a8f5a6ccaaaac2391a363b7a3e471559af77bab9608318908e40fcab3635bbd4b50ff004621e046da4e7afd2acc44d5b4b6d4da02d7ac21dcc4aaa61883dcb52e9da04f6842798a47fab195c9c7f7beb5b3785902c2e02b93b1514f38a8248ae176a1983cabf308580008fae6af9456f32b42668e6223c3393c267018ff0076a86a9a81be8591a3113abe1509ddf89cf41f5a6c4b7eb2cd33cd130e8b1e3e51ee0fad7337f672ddf88ae13fb40ee64202a01c0071f373cd3e961dcbd35d5ec5a7f056e200adf2375c375e6b8a4b90f0cdb1572bbb6c68a439f4cf38fd2b73c5be30d2fc13a2aff6dddae9a5dd521bb990885cb7407f847e26b39b4f9963592206391db76e6718c54ca9dfa9574727a8222ca5a709b5800ee17a13f4fe2ae535a92eae6f2d60b472101123e3fe5a67d49e95d4f8a60f3ae837965656017113e32075723b7e35950d934b78515d6375545dac7276ff0053f4ac87b96ac524585ade6f2d2dddf767603e59fad53d574992ca195ac04735c157c46ac14cb21fba371ce2b51f4996d5d86e3229f980ced0d56575411210d089933b41931f2ffb59c6735326c68e1b55b04bdb35fed1b5025923576dabf71b1ce0f5eb58f278156681542adc411a7eee34f9703d73dcfd6bd2e4827bd79265b7c803e6f986d02a38dffb1e56710e140f9d65c1128fe953c8bb1a34decec7cffae7c3bb1b9669add8c37472ef26060e3f8719e2b3b4fb3b1d2659d6ea0582521a05ba5cb0919ba8073843fa57d0d27852d35bb692ead152262ccc5106ee719006335c76abe1c8e0b6bb493cbb8b7907972433c5bc03e9818c1f7159cd45c6d61c65286ece06cad560bab8b89c8f941f2a35c6001e801e6a8f8bf40d3f5db19bca48e147059f2bb87999df9cf51f310dc7638f5ac4f1bc1aa6851226d091796a239a004865efef9fc2b9cf107c48d4345f0cdaa4b12bced34422931f32929b447b4724302403d72ddb02b9a306a573a25579d591bbe0ed62e34099edb508d43dbc9e529925cbec6d8c58b7a600fd6b7f49f8b70ea37ef6e67c42d2b000631954e15c63e663d8822bce757d4e5d63428aee3f3235963dc33c16dac772938edc0fad79be937e74ebc9544ff2931b6cdd91bba86cfd383ef5b3a6dec1ed7963ef1f41f8e2ead2f4da4b339595243828a540de0701893e8bff007d1acad4bc4b1c5a5f93796c5cac4156e508f9917eeeee3903b77f7af1df14f8daf2f596c16ee568d6272180e15c46db2361d84b83167391bc1c715dc7c3ef046b5e28bb5d3b5e966d374b746c7ce9e7dc4a9d10465b763e82b5845a5cb2395cf9e5ee9c96bd79aefc463345e1fb1bbd6174d1be56b705818c67119238278fbab96cfcd8ed5f4d7c2e58bc4fe1fd37506496da19c2b451dec244cac140da4f507ad6b681a7f863c3b6e9a6e87a6665b74c193ca11a274f9d70a013d7be7deba9d33c8961963fec9335db9cf28dd7d411dfe95d2ad18e88c9b72dccdbdf0ba4b79e65adcc285bf792346a411edd71fa5682585e59ed80ddf9e923ae6e9a1c85c0ce3eb5ac81cf992ce23121f94471a7faa3fdd1ea6b4348d73ecf7189809226c283c6492b8e074cfe3523303fe1146927976ea49736ee4bed750a431fbd8ef55758f0e5c697a3dc4b6334136a2b8304532131190f61cfcbfad7606ff4d94ef9228e2997a46c3e6ffbea96c8adfc42d5115d4381b4f5551d467d3deb3038cb2b8b8b9448ae5ae2c582e7ca850e377b1c74ac6f16db486dae2ef4c1f695055f6b31565e32783dabd1bc531c7a26912ea3e6c7768aca88a8aca5998e00fad707e23d6f4eb1d42eec6ef4bd4e179136c77862dd1caaca002b8391b73f36e031838cd27555ec69cbcd1e64657c26d62ca4d56e649655d3f31fce26937239ce392303f2cd7b97996f776c62b7bb851554af9f1c81d933d4a633b4ff00bb8af0183c1535ea45aae933db4cf6ac418e4c46f200d9e8d8c7e55dff0084bc597ba8c525b4b6ce6e2266578a17762b8fef6deb9ed8cd72558b8eacda12bab236f5bf3195161b66312c8be5962640587728724d529642b0742918390f13f3bcfdd5e3ae3be2b6f6c3665659426c46da5a4531c5bb19c282739fc687b78111a432a45bf710426c6f65553f747b9e6b96695ae7744c08acde7963578446048e88cff7db3f778e9fad69da69d24e63f31320bb0f31ce3040c0c015a719818c41154385562c4fdfcf1bbd973c67a641a5f3a2b69c07398e3dce72782fe59f947a9c8ac2e6bccbb1660d34924a8d9249128208ebf22fcbf8e2a498c90c02656554859c11e5e71fbb618faee527ea4565c9e27b38a1568a5691e2dafd493952100e3dce7e95997da9de6b97090c4cd6b0ba05130272e402ecd8fa935add332e57dc64e6db58d519510928a821c6331c8170cbd7a7d69341fb26937b3c77317d9ef0a12548203a9e8ca49e0d491e976f63732c82400e7333938de31904fa1a5ba8ecb54b4686e2409975c4a64dc571fc59fe95bd3a88c674d547cc4edf6777778eeda3320c398db863fddc0e83e9561aededdd2d9197ca76006c39e38efed9ae2e4d67fb0b575d3a42b3a93e6c572131bcfa1c5763fd8715c3c13472ec322166c614479dbb8f5f6ae85ef6e7154872cae4d796e2181e268233e6af99b5dc3071eb9a2092cd22960d8a2399700a61541f5033d2ac0b46b6c844b7b984636b37de5071f2e3d39cd4b7365697168850c6819be5603fe5a7f747b539588bb322d2f3ecf7b3a195ae6025773796df2b019c1e319a162b5d42e63b98c32843949012bb7d48e9803d2afcb76b676bb64b787ed1bbcb124608476db8c951dea5b658edada37802c93382ade68c801fae055d92d917716ccc7653112266c9e30cbe67cfd6b6e4bbb44b759a143b320092252319f51590e4c28be63636a2857c64003d4558b74b563fb99a3df952d06d38e3d39ab24b561716a7527daa37ba6e27a00ded5a97fa45beb016482e56caf55966495393fed263a1ae7b59d35ddc3c2ed0c4872c806063eb5a9a5dbcd6f6d1a5baaba062c0632413f7b26aae415575994debe99a83401a390666442bb41ebb97251bfdec67daba0b7b3b58e648815ba99d4b027e6f3013863e8306b1db4d8a196eaec9f3a79a448957190c3d76ff00f5eab4769a8e99a805b75b74b5773239f289251c648073c0ddfa55f3303b65817c962924c5c9f2ca312ca8fd976f63fa552d3b50ba0ee9770116e87fe3e5620a4b7ae31d2b3f479e47bd2da84d044262ab0aa82bb76f40707e63ee715d3341235bb0431285e92321da57dce700fd7155651dc08239d1a36fb209630abbf1e59236fa9f4a92da78eed137d8c914a832260e061bd863a551b6bdd41266b61671a440663ba248da3fba79f987d715a76b24f731830f92fbce0c85f07fe02293d34402d9b4f76922dbde9b75506365d801dc3f8b27353c425822686edd14a12c936366e03d6a055302625452c1940da7e646ee063afe353456eaf032cb22dc47f317f306e2e0fafa7e14808a3d16d2ed84d347e67cdb89dcdb323a64e6a2b8d674bd3dd904be52676aa443e423d866a6bcb98e08844b03ec0bb8223128ff0085322d36d75185658ed13ca2bf2be41c37a7d6972809ff00092c1ff3f1fa2ff851567fb2a0ff009e29ff007c0a2995736acf41b936e8b71b953f8c2fca4d69dbd9c1021f21812a376d55e9f5ab0d35cca0b6c041ea73c546f37d9632e62f2a42b819e8df5abdccb622d4ec2299049cca547ca338e7dcd67476324f230b87447db88e353c9fc6a17bf713c84e14f68c3679f4e2953555588c732fef586514a7ddfc6ae2da0dcb366b6eebe5222bb21236b1c1e3eb5cfdd68ea6f432da8f2a3dc58e31b816cfd6ba2d3f495f237c7279a5f73379872bcfbf5a8f5712c292491db82cbf2890374fa8a7743b2397f13dce9c74f3672c42333758d49dac4746201c13f8543636b8b23183248ea31b5cae6b62f85c3a2f9b11c18f8977292a7f2a58f4f0d6ed0f9914808ce5587f3cd68458f23f885a2de59c693be76176669618cfc80ff7f19cafbd721686e5e769a40259b6ae0edc723b0cfddaf5bf16fdab49b6678ed51e04f959b9f917d1867e75ff0066bc8c6a32adec82388496f7126e58e5070a7d3358ce296c3836cdc9aee6ba31cb2a04f28fef031dc241efe94d8ac20ba2eab96976e4bb1dd93ec2afc5a3b85324c8591bef2839ab1a6e92922858df6873f7b70ca8ae49366d1666cb7a74eb79a2962f2f6c6419954b12a3d40ef594f6b71a84a93c81a7e36ab1c82c7e95d6cb6cba446d99ca150d91d981acab59ae1a2cc646436f5940e50fd2aae1ccf9ae635c69c020668a78d430732c6f8e42e070b8acfb7b391ee80264577182f211963fdec74cd74334f70e9346519909c058f8fc78ed5558dac84472ac980982ad1eddade99a84ac35ef6e729aef83cbb23496af2dac876c9e5ae73f46ed5c378c3e04787bc5822892e2feced3efb410cc02a80728c011bb2a7dfa57b6dbb811881a574b7dd90509c550beb3797504fb2aab46e3f78bb32f9fef6e38fcab6bf919b5695d33caf49f811e1e6d0bc9b66dee80b797704fce7a61b9e33d4e3bd73f6ffb2df861086bbd2e30e19a569ee5ccc99cf036b13c63b75f6af70bcf0d24723e1dd081e626e20963ef491db5f18e356b246b620b206392d8f5f7ff006a973f90dc5cb7679fe8bf0a341b0d1ee34d83c33a735a892391bc85c032a302199548562319c6319adfb1f0fb4b76ccf6e5642305ddc90ca7a82338cfb802ba6d3a6d4b51b72c96ada6b85d8f14ccae63f618c647b9e6a0974d6b3d4e186ee495fcd05d362b11b47a90300fb139a57294797625b8d2ac9163690c8f1e308146d45f723fc6aa1b1310db6b2793b7ef866d98ff007082715b7796366f14682f5a37dbe62b1258eefeeb8fe2152e9b6ba5ac46299b631c91e6292580ee338e28bb44f91873dd9b68a3f36ee0f97ef4683196fef1356bed7657cc90c310441b5447126e2b8ef9f5ad2bbb6b075492dec96e2261cb94e58fb566c72b5adf4ed67632418f9d8ed2b85ee7e839fc8d170d8a4f27d8b662df80fc89e3ddc7d699f6a7d3dd85aa980b756ce4846e86ba1d3349975b57ff004c3e5a0de62565e47d49aaf77612dacd0c56cc9a82bbb3111a8dd8ecbc6462a40c43a9dc5ee6de5f9dc7ce15c861b8364103d6abdcd9cd7370f379a15c360ab120f7c823a1073cd6cd9db4735e8135b4709cf29d80f635a134166213124cff006e5f9801179991fde6c720554acfa1516d2e5302d58e90e92f961c306ccb1c63764fb906bcf75cd1a5d2bc41ff000904900bbb4cfcff002872a4fddc29e463233e991ef5e9525ede832208e34658b21873cfd3b7e359d1bdea14bdb44091c0d26e468f72b96dbc13ebc3e78e7776c529a528d820dc644da55edbead60b2d85dbc0aa777956d8401f6e3271de964b5ba9f508e532dc4592036ce8b9f56e4b7e55c9f87f515d1fc532c772c6ddae65124388485627b0e3b77af4f7be46100459a4f2954a797115008f4cf5fc6bcaa94f5e5b9ea46aab5cc16d09d5e6791a58a42859e4de72e4f0063b6324e7a671f2d020dca0aa9174c91b0f3492410a0395cf0083915a935e9691cb432878c3ab2e72a33f779e95ccea1737733346b0795be25da9249b8820e7191ea7afb572cf4d8eba6e32dcdd568d6e9da340c9e62349e59c61064b1fcf14e9b538ec63789a4595865924180cb113c363e95c2dd6ada84125ba9b8585305558018959cf2a7dbbfd2a9ced04524827b969a5001c0247cc3a8ffeb528c99b2a69ec757adf896d1b7dc472a3ced1a961190db995387c0efedd2a14b99b582922b3456e405686265707233e60c679f6ae62e4a84824b28e0b724846d8bf38cae304d4767737cb7261856595772b34ef26dd802e3a0038a2326673826753ad5b42f6de4c29e7cc013b91b6b337bfa0fa533c1de25b8bfb49ed24529e415898c88558ab743835521d36fa3779ae6e1375b846f2a304ab01f7b902b23c4168741d734fd6209de0e519c2ab28789b3b9181c8c2e3835e9529736e70564bb1e9b77ad4f2699b2d5962b905426537c6c432f0507fb03d7ad58d36f125422e64f29133b571800f6607a64d473f9573a636a2aa24859004695b89bfdb2578154a1bc8524fb20977c5205313365861865b2b8eddb9fcaba2cae79fcace92c99ae60610a290a372e7e5e7d7eb551a0b99ae9524272ccaa9b475c516692241040aa635b7192ee725dbfc2b6b4a963d3244ba8cfdaaf720a6ff00e127f4ad3c8456b5090acbf698d9622d82dbb2a055f5b1551e646f1a86385907534a2d9a4b4f30da7daddf2095936f3f4edf8d4363a79d3c98c42501258091b2171efd2981acfa66095490fcebb1848db86292d56d619da17b830337fd34c03f4a86767b38a7c4723329dfb8a3636fa671d6adc3a8dbde5ac0c58ed5fe1601bfa6685aec418b71ab1d1f5254b9b912c523b2a4bb784f407de8d3fc6f1eb7a949a74d632ac3e67eeef205eebd7a7f0ff9c568ea76d3de425425ac8ac84850c0918fe2fad616986ff41b89beccaf04b2b2221db8d99eb9ec3f3ad2dd45756b9da6a5616b796f343b4fef536974055b3fdef63f4aa5a0e8773a6941757b3ddb42b81348c599ff001fbabff0102aec5a85cdb460dedd2ceee09200031819fd7dab422693c91ba29889700cc9f2807d02fad39304efb12de5c4af004f3a389199770386214f7193d29d6b6f889a1b76950a8f9bcc20ec3f9550991166f2c9b89e750591f033103f77a0c15ff38ab705b3232bdc5f1ba56fbb6e13cb29f8f7a063c4d2d9306952198c7c8f28e0b7b8f7a9ede7b1d400c4477a1eaa7cb6c7fb43a511d9c5e6466d0465db0591f8233d867bd3a6b2fb448b2b3a2463876073934ec05a78a2b8250169a455ced43b0aafad5678cda5ba2a20b5c36d575190a3d48f5a492eacde6758eeda3ba41f2cc14968fd8838c8a9519ae06649e3bb2ab932c43033ef480cafeccd43fe836dff0080e9ff00c4d15b5e7c7ff3d3f51454d80ea1a59a08d4a22aed196543baaac93dcddb1539423ee871d3eb5a31bc8625112a3229f9881b4b0aa7a8c440897ccf99df923a91ed5b44896a25ae916da79deea824762e0818c9151de5a4775771312254c6366719fc6992693049133ab4e84eec1797819f4aaf676f304302ca51d3eeb121e9ba96e811d342f5bc32c6eb0c48a9cfdd2d8c8aab70b289e35685a2c8e65ce413ef547507bad38472dc12ca3eebeee6ad49e24b3b8062666799d76ecda7ad4dc08b64656542c13e620a95e303d2a1f29a28c2a42ea15704f1cfd2af5bdac51db66295a472cc4c521dc39f734d9a190c5945291a7560735b8185a84697e9247e5ef88c7f75ce361fc7ad7906ab636f6daec96d0c8678d797dffc0fe833dbdfa57b55ec0f2a3c923a868793295c9c7b8ff0af22f10efb7d6250e624f3c87475cb9909fa0e9fecd44b505ee9a56f73f618cf9ca3ca74c27f09cd7316369f67d46622e008ff00854e7029d6b7124d23a3dbc3b319171265f1f419ad09e3df72d188cca4a63a01cd734accb12f7749136f78e48b681e59707767d31cd62fdae48a4558632107f067ef7d7d3f1ad9fec8b891d1e11b429579191c02aa3beec7f4a8d6049acd750b568eeedf2d16fc6ddec38eff0078678c9c734590ec42b7f74ba8200aa1b698d95136edc77f7fc3345e5c4576c5deeb255769322e7f13c75aced7f5dd3f487b77690db4bf32fd996d5a47958f64e78fceb674cdb7ba72dd790ae2400abee05db3ea05677b9562bc70c372155613260e0a86d950bc49a75d96b4709b479af6f249b883ce3df69c373fec9a64ea8f7f12c7e7465972e5c9500d3a49a68cb1f3191be58de6206e655dd85dd8fbbf31f97dcd1764d98e92287529cee7c33018ff00641ad3834486d2c36477a47ccc06f6fba3dab2a391afd7102041bf12b1500c817afd29b1da1b9b22ee864501864b632c7b53287cfa618e1691889c701e343b7249c76a63e9925b2894dc18832b334441dac0fa8cf2def4b676b247e588226768d9494ddd70d9e49e3f5abf757d7f756a02408c158a340bce3140aec82d34db79e0324a58ce0874507fd5e7d3d6a1bbb6b997f71047e6c43715f30e4f3fc233ce29cda8c85126784ac236a1771b791dab722d5e69a48dede341095ce5d5cc9bbd8eee953295c56f7ae50b6796d0c50dcc2761628ab1306c30ed81cd375bd04ebab6c9227d9d619164564639ca36f19c1e549182bd304f343d9deddaacb76d1dccf96dde4f98d1053fc211989fc734fb97ba85e212ab4083aedca93f9d50a5a950dbdc59165919c65724a800fd0f1c1fad579ad6469fcc30ce1188057693b73e8462b4278e7b8f3676f365641c79876a96f463dead5dc9736312f992232c91a36228f049f41cf5a570305ed15668e6b5399c7569323ff1d3c7eb57ae6fafe28a37bcb78d89fe2040674fee96182dfa5402e20d56ed84514a2558cf98ae1b1330eca7185a962d267be47937a0dbf22ab12403edebf8532fa5c492f6c0db4ebf656918f04a0c903fdaf5ac237f70b6856351f654607e56eaa7f88fa1ae86fde7b78a5591525da151de15c6e6233d456669d6b7114524a6cd237642aab8c970465723f8a95d2d00e67c55a3cb7b02ba189a40e268813c484fde50d9e0fa55f87c596315842cea591c6cdfb8b61bd073d6b4e7b27bc2a668a5b351953f32f96ea3fe5a01dbf9d707e28b69b48b89269638e2b598e3746b8dd263e5e0742dd07f4a89c1495d1d1467c8ed25a1d67fc24569741d9ee94c519cc31af083ea067f5ae7754d7ece3f3954e18b16658d4e10fa061ebebd3deb81b7f15be95770432440c26460e40c631d9b1d6baeb2bd8aef4cf3e245dc53f77e5f022914b00a57b8f95b8e9c75af3e74adb9dd1aa96a8e5f59d6ee2e6f64b1b3b520f0b20703e62148dc39e0ed1f9d45a05a6a734c10824b0dbe5c8dbf3bba366af58599b6f1aded9ba298d04385dfd0956c927a77cfd2bd1ec74450d0aa19846432c823214a0c90074ebc1fca92a76dcbf6ce5b1c3dcdb3695a634ef2f92cb8910042c5981c631d793c74ae93c191c77999de330cace37349c8c9e8841ae91fc2e9a869b7b6f2aec6650be5e70ae809c371920f04f07ad71bf0e9eea3d4aeac2e8112a3bc51c99dff002abed4523fbe7aeef4ad68d1527639e759a3d1ed74996d199e48b6161b197183b6bc8fe30f87aff5051a0e973c64dc126192e24da60de010a0b119cf38af7884795a42aca0fda530431e430f5cf4207b66bcb3e21c96d25c22b93f688dbed012120b6d0d827a13800363fdd6e98ad52509f290db9c3989be06f80b5df0ff0083edb4df127889b5e7b477f2dbca08d1447eec4d27df71ef9aefaefc13a524e3cb892df7c8b24606e2785c6d1cf4fad6669f7c9f648224f9d0306460790a403ce383d6b56ef5292285ca5b486471ba4627b7ae4f02ba1d94b5382cfb8eb386d30f1c0b2dcaab88ddc03b413ef5b8b0b3304236208d721a3c6d23d4d7371789e4b796ccd985cbb2c65a365648947fcb42b9e6b563bcd46e2d64f32612c4704c878241f5a7f6ae3b153548e6b1bab66b599510ce8255c9c003ef67fbd5d5caf6cc857cd62550ed6182873ea2b1218a1587cb48b792f9f24f3b8fd692ea068de38ed94261f636240c01f4e0d56e1634043731471ca97e995f97ca6c9473e9b73d6ad8895a05b8898437464f9805e00fa5532238e5455992360ff32b21f957fbc335a36d35a5dcde534b244c07eecff7cfbd527cba22059ada486ca37329625da50f1ed1d7f878ed5cc378bd6da56b7b9dace302e1246cab2b8f91b07bf51c7706ba0b93716adb04115cc0e36b4c8db4c67e95937fe1c6b7b88f7980a1188a5740e76e723ea54ff334e4ea4a364c69d9d9ad0af1be87e21f12e9bac3adc43ab69a925bc26266313c6eb82a50f076f626ba3975c96f5cd859b45348c858b44a4b90f9f2d9d73f2e7079e9c75aa1a3e81a380c1efae0cec774a547df1fd2b62c747d234bbf96f2c6da25bd9d363cab1aab48a376c5738fbabb8fcb5a4528ee2724b6443a94f2585a0569678942aa92aa7ee8ec48cf153da6a52ea314634e31c6aabb9fcc6273f42456aadcc820425f72e3cbd8a3923df34eb2b24b43be165c0e191949e2900792d7964f15bb289d1c095641cab0ebef8fc2a4b6d3d63f950385392cae7ab0ee3da886e6db5595822e1d1190329dafb4f6dddfea6a5b6b7fb3efb7dd870afe5991b7123d39ef4ee0431da450b1b886530aba6d7761e6122acc09602dd944c843264aa8f2c9a827610caacd3c514aeb878e338327e1d053209a3967f3112e1da31b1a59181f97e98a9bdc083cfd2bfe7b7fe3868abbe65bff00cf23ff007c51401da41ff1e0d5967fe3eedffdea28ad224136adff001f09597a3ffad34515120fb43bc59fea9ea9e8bf7ed3fdfa28a00e8e2fbd552e7fd737fbd4515b8191aa7fad6ff76bcb3e25f54ff7568a2809193a47de4ff76b4a6ff9092d1457123465cbaff8f1bbff00ae159307fc806d3febd8ff00e8745141471dacff00c86744ff00ae15d1f82ba597fbab4514a05fd935b5aff90acbfeed51f13ffc81f4aff7e8a298886cbfd69ff70d5d7ff902c1fef514504995a5ff00aab8ab9a2ffc7fddff00bed45140177c43ff00b56974dff909c1f4a28a802f7877efd5ab3ff908cdf4a28ab20cbf17ff00c8b771ff005deb093fe3dad3fde4a28a802f5aff00c8353e8f5d0691ff0020c83febe28a2acbfb072df177fe44fd4bfdc1ff00a056c49ff1fb69feea7fe8145158cbe20327c49ff1e961ff005d5eb8bf897ff22cc7f4b6ff00d1b45157119e1da8ff00c8466ffaf535dff84bfe40763ff5cd7ff43b8a28ac6a9d30f84d55ff0091ab57ff00af0ffda15de45ff216b8ff00797ff439a8a2a245c4dad13fe3cadbfebaff00f175e710ff00c8fb7dff005f91ff002a28adb0ff0011353e13d46ebfe455b7ff00ae72d7cb5f143fe43907fd874ffe8628a294bf8a52fe11ef1e18ff005117d53ff405aee6f7fe45db8ffae14515a4fe23891cbf80fa4bff005c6ba5bcff009059ff00ae69451565c89d3eedbfd6ab783bfe4217ff00f5de8a28888d2d6bfe42771feed5ab0ff8f4828a28fb6640ff00eb6fbfdea82ebfe3eec3e92d1455c42469e8bff1e0dff5c6b66cbef27fbb45154405ff00fad8beb55a2ff5b35145596473ff00af5ffae14e9ffe3fed3feba9a28a8029f897fe3eedaba5f0ff00fc79b51454440daa28a2ac0fffd9);
INSERT INTO `pet` (`pet_id`, `owner_id`, `name`, `type`, `breed`, `gender`, `age`, `color`, `b_day`, `b_month`, `b_year`, `profile_image`) VALUES
(2, 2, 'SCRAPPY', 'DOG', 'DALMATIAN', 'MALE', 2, 'RED TICK', '12', 'JUL', '2000', 0xffd8ffe000104a46494600010100000100010000ffe1006845786966000049492a000800000003001201030001000000010000003101020010000000320000006987040001000000420000000000000053686f7477656c6c20302e31382e3000020002a00900010000001f01000003a00900010000001f01000000000000ffe109f4687474703a2f2f6e732e61646f62652e636f6d2f7861702f312e302f003c3f787061636b657420626567696e3d22efbbbf222069643d2257354d304d7043656869487a7265537a4e54637a6b633964223f3e203c783a786d706d65746120786d6c6e733a783d2261646f62653a6e733a6d6574612f2220783a786d70746b3d22584d5020436f726520342e342e302d4578697632223e203c7264663a52444620786d6c6e733a7264663d22687474703a2f2f7777772e77332e6f72672f313939392f30322f32322d7264662d73796e7461782d6e7323223e203c7264663a4465736372697074696f6e207264663a61626f75743d222220786d6c6e733a657869663d22687474703a2f2f6e732e61646f62652e636f6d2f657869662f312e302f2220786d6c6e733a746966663d22687474703a2f2f6e732e61646f62652e636f6d2f746966662f312e302f2220657869663a506978656c5844696d656e73696f6e3d223238372220657869663a506978656c5944696d656e73696f6e3d223238372220746966663a496d61676557696474683d223238372220746966663a496d6167654865696768743d223238372220746966663a4f7269656e746174696f6e3d2231222f3e203c2f7264663a5244463e203c2f783a786d706d6574613e2020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020203c3f787061636b657420656e643d2277223f3effed005850686f746f73686f7020332e30003842494d040400000000003b1c0200000200021c0250001753746576656e2050616d203034313220323036203433371c0241000853686f7477656c6c1c02460006302e31382e3000ffdb0043000302020302020303030304030304050805050404050a070706080c0a0c0c0b0a0b0b0d0e12100d0e110e0b0b1016101113141515150c0f171816141812141514ffdb00430103040405040509050509140d0b0d1414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414ffc0001108011f011f03012200021101031101ffc4001f0000010501010101010100000000000000000102030405060708090a0bffc400b5100002010303020403050504040000017d01020300041105122131410613516107227114328191a1082342b1c11552d1f02433627282090a161718191a25262728292a3435363738393a434445464748494a535455565758595a636465666768696a737475767778797a838485868788898a92939495969798999aa2a3a4a5a6a7a8a9aab2b3b4b5b6b7b8b9bac2c3c4c5c6c7c8c9cad2d3d4d5d6d7d8d9dae1e2e3e4e5e6e7e8e9eaf1f2f3f4f5f6f7f8f9faffc4001f0100030101010101010101010000000000000102030405060708090a0bffc400b51100020102040403040705040400010277000102031104052131061241510761711322328108144291a1b1c109233352f0156272d10a162434e125f11718191a262728292a35363738393a434445464748494a535455565758595a636465666768696a737475767778797a82838485868788898a92939495969798999aa2a3a4a5a6a7a8a9aab2b3b4b5b6b7b8b9bac2c3c4c5c6c7c8c9cad2d3d4d5d6d7d8d9dae2e3e4e5e6e7e8e9eaf2f3f4f5f6f7f8f9faffda000c03010002110311003f00f0d82e08418fceac9b9c275acbb693e40326a7209ce0926be2161a2c7caca5a94f953819fad729a926e39c9cfad75975031058fe958b7767b98e7afa576d3a6a0b41d9a39d58f3c1279ed5d068fa7cae5481907af6a7695a699e70bb771f4039af4ef0c784a5be9112185a4191b8282401ef8e83deb9f115d43445a8b665681a6caf22a2a31f60335e99a4e9161616c936a3300c2269fca3d1d571903f0cfe555f5bd4f4bf0e09349d3985cc91365c1f959fb9d8700823938efcf1cd79378f3c55ab6b4b27f67a99d914218e1e5d3aa9381d010791d38f7c53a397fb592955d8eb54543591ef9e0cf1868de24f3e216ec6689549108f9997705040fc89f4c1af41d3ee348b3291c976d1c98cb295c1e9903f207fce2be18f04f8b6e7c2f7b73334af6d70cc8918cf3905b23e8430cffba2bb6d37e25ea9acca6396679252abb1c1ce48cfe631d7e95df3ca70759de70d4a959f43ecf82d2d2700a5fdb9206763123f0ce3b7f9eb573fe11c1723e59ada43d76a4a09c0eb5f29e8dac6b9a9cf24a6e9f1d9df3e586ce793ce00e01e7a63d2bafb5f176b5630cb6e970cd748c0c850612107a9c776c0e33e87d6b28e538483f76ebe60a373decf829a4eb129cf7c8a8ffe15f92c098080dc0c8eb5e3f17c4492c0e0bca564dadf6991c8dc4a36dc0ecb956e9eb9f7aeabc3bf122eed602f6f3bc819be657ddd3680a17b75071cf739c75aefa7878535641eccf4eb0f034302026319f515a51f85e25030a4fe35c3e91f1ae4867792e500b6feecbd4b6070b8ec38c9fafa576fa47c53d1b569846dfb821b6312410a7d09ad5d0a6f73270922d41e1c8c118535ab6be1f8d06718ad4b6315cc6248995e36190cbd2ad84017904fe15ac28c23b23232d34f58c600cfe1599ab078909c0005744fc1e14d646ab0ee8c8c6477ac2bd34e3a0ee79bea7af7d8a46c90bf5acf1e2c0ea7f7828f1b696c03baae71eb5e6f79e642a4a1c375eb5f1b8a9ce0da32e668f409bc4a921c17159f77afa104060735e6d26b92c6c43b648aadfdbdfbdc1720fad785ed6526c5cc763ac5ebb46595eb8ad52f9e40c1989c8a9ee75edf1e37647a8ac3bcbc521981fa576508bb89b399d695d8b1038ee0d713a8cfb18860463bd77f765a707bd70de21b5d8c4edeb5ecd357d19252b0be0675da73cf5aed34e9da540181c7ad79cd98f2a7c1e067ad769a45d95555278e9cd7362e92e852d0b3acdba88cb0c7ae2b12ca716f3007e5c9e476ae82fd0b4395efeb5ce4b19493d715cb4be1b32b73b6d3a60ca0f200fcaadbdd043b78f5ac0d1ef5d54291c74c54f7b295f991b8f4ae754ef2b099d15beac218b96e6b1756d6c499c38c7a935cfdd6a642f535ceea9ac6c1c9ae9a5834ddc2e7ac4be0ab6b78ff00d48c7b5541e16b4e7e4c7e35e837aa9e51c7a7e75cdcf22a39c75e95f57879c2a2d44d9cd5cf852d9b38523e86b2e6f03c73a34825091860a5a470b827b57a22e942d6c66d47540f6ba74281d9d863703d315e1ff143e28b7896def34f820fb3d8ee052e4c443138ebfede7a11fcbad752a4a5a451d10a77d646bcb2d8f869cad84c97da90f2d9837dc0a4904afae0e323d8d5df12fc5dbab0d0e5b68d84cb23b3b4e80c4c063eee40f980e39c578f78256faff5458ae59e4f2c990e22c21c7dd571bbbfa1aed2c16d2ee4d4350d4ae62b8d3ac40370f1a61667c9db18e00033c003aedf4ade1868a7768ea52508da28737893fb27c3a758d7ee2354941fb2420319653d860e303bee27bf039cd797de78df54f115f34505d5c0c92542fcbc9e8bc1e79e06293c43a95efc46f1233dc4acd12111c6a061500fe15ed9c76386fd2ba5b1f03456d64a0203281bd9b6f2e07439f51ebedeb5d96b6c6376d9856ba6dd5ef953efc79a85b71cf209f4fae3f3af46f0fe9be647139dc2245076f42d94e073d09cfe845515b1582108e025c46c48ddf749c8c8f6c8e9d41e0fad6cd8eb31c9039c21b848e390ab6396196edd0707a76e7ad4d86bb9dadbebeda7c661c6c468e35886ddac8cca183120f5c000f6ce4639ad11e2b684adc230426289d9b600a369ce7dfaf19f5c579caeb4978d0995bfd19102649032a571b73c8c0ce7d703dea07d5d3ecaf2f9a40de11806c6408c607b0ebc1f4fad435d8d948f50ff00849e09efe5b96706dd221b239187ca4e4ed00ff0f4c9f7f73565af2782ccb25f99cc7899238c61f904856fc0f41d4e05795dc6b07ec7976655b94ca64f6c90bd3d73d7ae315b51f88bfb3e34568564280aeedbc48dbd4293f5c1007d3d6a0d2e8ed63f16f9027334e197cc0608c395db9cee507b0e314ebaf18dc694124b698aa8250b6776189c9d808ebc724f6e9deb899aea3d46e62bc691e19b688a44403e639ce33d0633ebd4d56bad66e74bb77490e5e3667627f8431c0fc300f39e80faf2d2b12ddd1f41fc37f8edabe8f0c0ede6dc5b1cfcad97661db233f857bc7857e3869da98f2b54034d755f9e6932133e9edf43cd7e6d45f116eac7548cf9ed12264119dbc0ec07e5cfd2babd3fe37dec02386e61b6b9248c6f38e4f39e3b0c77f41806b4f4327cb2dcfd39b5d420bd8566865f3226190c0f04517062963255b70f51cd7e7b699f1a75812430fdb9e1879db6f012067a9cf3c93f5f635f597c25f89f67e22d32d6c65b907503186f28b02d8c724e3a7f9c566e77d1933a3a5e2cdef145aac88e301b22bcbf57d3d222c1501f506bd6b5cb51216fde15ae3350f0fb4fc89327dc57cfe32873bd11c763c6bc47a436c6962500f7c570b749740b657907ad7d03a9784649e2601d7763d2b90bef87973962a509fa5787f529296c64f43c9d66ba036b7ff5e9923c8400cc6bbebaf87f7ca490b191f5ac8bbf04ea111c8854e3d0d7ad430bcbd0499cfc710f2f20e33deb1f56d305d2b64135d91f0ddfc69836c73d3008a85bc3d7ec71f656fc066bd0f62a3ad8bba3cc468043e0822b534fd29a19546e279aef21f08ddc8dcdabfbf15ad63e0b7dc19eddf3feed70d770b6a0718fa6c8d174cf1deb22ef4df20e48c7d6bd61fc34f6ea730b1f622b97f10e947630f2ce7fdd35e5d3a5193b22dbd0e42de22a01c6292e8654e3d39a9446d6e369dc07a60d55bb9c796dd41ef5bfd56cee89b9992dbb30ce339f4ae7357d36594e304e0d77d6d6e2e208ce074edde993e8c243c8fad7ab4e928203d2eee199e36c48e3e959335e2f85f4997569945cb9cac4862f308e79fe639e456f5c4cb21dbc0cf735e6df1b7c4de54aba1c05da1d3e143273feb2661bb07d867f4f7af1326a73ad55a7b237825cd7ec6678a3c6dab6b1a6ceef32fd9c0d8f71202a2307a8c0cfcdc7418af9f75fbbb8bcb892e144f6f6818901db6129dc927382df527a7a8af57bdd623b1d1adaf6e2e05e5c88de616e142c31e302351c0072dc923b0c67d7cbe466f11ea90c310324f2b8447719da0364b63f857927e9c57df462a2b4359c9b3b3f0ee9b70be06b2fb346f1dc6a0cd3cc391f216db1c61b3b9b3dbb9c9e82b33c74d3c7f67f0c5860dad9399ae674880492e9befed51c606768ce7a1cf518eeaf87fc231a3cfaccee628ed21db6c84643391b62cfab757c71d0578d78656eaeb5a8dfce9f75c96237e1ddf0d8e41ce01a1f615cf43f08e8aba7db2cccb1b6e5c08b672a71c853938e9d32463a56bdd5d244a25cbc328c9dbc672061b3d8e4751df83576c7c3d74f6e5123666908cb98fe61ef85ebf9f6fc2abea9e155d8b8b8693241388d873ec33fd011ea2aac2b9cdea17a183e18b7ca0abf664ea33fd0d62ddeaed6e1802158801274e3186c83fae33ef5b1ac69b0db2ed8a5cb11b42a8ee7ae33d33dd7bf51cf35c25dcad633cb2392e909e7cc1f79b9db91e99fe5f5a97a06e7557d7e0db8314ac3e652e15b8c9182d8e9d78fa8f7a5d3b551e508d5632929f32421b3955040e3ea39ae46d355dd34e10e53723a861ca8dd9fd3fcf5a9a0bd8cb657f74c72d8fc73fc89152d14a476b26a525ddf5b89959ad94f0ab8e4851951f88ff3c5689f13c60185197e628d8538c00d923dc019fceb851aaab5c0954fcaa780339c9e491dbfce7b5321d45564770570bcae5777e3efc93f954d8ae63d224d58c06316ec0a329777c753b8f3fa80077c9ed4c7bc791241e629488ee62dc0dc07520f53fe35c4c776ff0062854c8eb8c798cc4fd7681ebc8fceb62d2ed25548d71230c3ff00136d1ee3d73fae69d82e607886196cd49c03295ea320a83ce78e9f8f5278155fc2face98f732477e248eecfcb14d18531f4e8723823d47d2ba0d5ec9e7880de5c30c95031ce3a8c7d319fc2bce359b0b9d3ee1668e2dcf8c12a708a0fbf4000a7625b3d1ac04b63ab9fb64b2273856b60bb644ebd4d7ab781fe2558f87356b7f26fa41f3076513ec5c7b955ebd7e9ea2bc67c1d35b789fc3370331cd3583f08df26371007cc3900918ce3af6359fa26ba60d4271b044c0e36ca777b1f987461ebd2a650be85c2763f557c35e36b1f1868b1dcdacd1bb60091509c29f6cf38f7357848adc83815f0afc28f8d971e07bd4532c7776b263742bb5249147041271b88cfd6bec9d3fc4716b3a641796c3689630fe53b0deb919e715e2e25ba2f99ec1529a6b9a26cce633cff3acfb878b9ed9ac5d435e7849629c1ef9c573979e32d84ee8dbf035cb0c4c25d4f39e8757318896e2b36e6249090545725378f115b94707bd471f8f6d9db0778fa8af4215e3b93a1d4369e8403c1fc2aac962b1b6718359f0f8cad587df38fa52cfe27b46507cde7dc552c4c1bb5c7a1bb6102381c0ce6b6a2b48c2818c570b078a2d91ff00d78ad38fc656e89cccb91ef5cf565196cc2e8e8ee6de30a720571badc11331c818fa54b77e2e85d18f9cb8fad731a9f892239fde2907dfad3a54d3770ba28dee9f0c84908b9f522b0752d1e12bf714fe156ee3c411eec6e18fad52b8d5a3938ddc7ad76b82452248b4e860b74da8a38f4acdbf5442480339e6b791965b4420f6ae5f5eb9f23352d292b22dec6d8d4774832715e5ff001989bcd7ee022ed79ad137b740c4fde3f5202ae7d8e2bb28ee7328009cd723f13d964d574f761856b375661d48563dfb7515e2e511e4acfcd154dea79bf8ae6548a1b288b34823532cac7a903818f618f6fca9bf0df463717ed711901db10ab1ea067925bb773497101d46113edc0906ee4e0edcf19f4e87f1aeefc19a5a69f6b1b6dd8634dca31fc447afa9240fc4d7d79adaece77e37eaef6fa5695a75ae4bdc4866c038c81f246067a0c0639edbbdeb9af85f6726b7e22bd9d2444b3b354b5072bf3951838cfa92c7383daa1f8bf7d249e32790b2b9d3adc205c632cb9e3dce73d2a0f851ad368fe139e73e60692676574404e7ebd7d695aeee2b9eb3e23f15d9e810ac719c391d049f303eb8c1faf1eb5e61aafc41bc32908cd20208dd206507ea31b7d79e2b98bbd67ed9a9348f652df5c492ec8d11cbe589e005504b1e40c03dc5751a57837c55aadddf5869de17fed3bab48e46bdd36c479b716c88c158ba23961c9001f9b273c7070f7d896cade1db9d73c577f243045f2fdd7552b891739c771d39fc09ed5d5f8e3c14b6ba024c495246d247a1c9e49e9c8e87d6b94f0678bedfc2b34fe67ee63b988ec7958eedca70413ea0f1f55ed922abf8a3e294bae5b95f98a87c81bba9191d3a63d3d33ed5e0d658a9e297269147b349e1e386f7be2671c931b5b8e5b0549527356964ddb94e1b24e4e73cf435948ed3379a7058b67f1ebfd6a73210187ae3815edd8f1fa9ad6f3ac6b900720fd07bd2ff006a000704b70c587191e99f6ac7131c31e464601ee07b7e55d1f857c2773e204f3e2042890280a39036939c1f61c7bd6739c69479a4f42e11954972c7563a0d42eae2512267e5f94339e07b8fd2b5ad2fee2d060aee0cd9ca0fc8fd2ac6a1e1993c2f7ca2ee010c4c716f1b80e17a125bfcf27915b70e9715fdb9f26551bb9dad9001fa60d2a738d58f345dd1538ca9be5921d61a83dfda18ced084e4a1006e3d80359de27d2e0b9d349bbfbbe91a1551d816e87e83bd5bb7b1bbd2e5c4d1bc918f4daa173cf76cfe7576fe49044fe5a326e19011f711f8f38fc056b6b109dd1e7bf0da5b7d3fc5eb66c5962ba4685bcd1b47238047619c7d0806b4ae2cec2c7c413dbce3ca749c868e71c32b13d0f4e873f98e2b9f17683c576d386995a19d732f05d0e41ee391ec7a8ae9bc5861bad57518a5611c91203e7152430ce703ba91f28cf7c73d33474d411a5e2ab6fecbb3b1922cc91b4846f538936f519f520e39fcf35d8fece7f153c51a278dad34ad3fed5adc578c2116b182eadce471db1d771e983cd79c6bd753cfe18d3a1918f9a19d901046f03827ea3d3be2a7f867e22baf0eeb715fe9b772da5f3e12683703214cf3b0f5238e40fafd31a94d4e2d1ac656763f443c44af0052f198c91974273b4fa5703aadc7cc71dbf5adbf03f8a1bc7de0b9a57bbfb6dedb105d872446471823923b926b9dd66de485db767dc1ed5f2bf57f6751c59cd5a1cb2d0c99a7dfd462a8f3e665464524d29dfd7f0cd59b184ca6bd050518dcc0b966aee00c56888372f380714db6b368c0278ab0e42f3939af3e6af2d0772a496aa01dc31f5aa772c23c8db8abf7374aa84719ac2bfb9e0f3f9574d3a5cdb923257f3335957d62640ddbe86a417a01209c54cb7027040e95d32fddec071ba9da342c7e6618f43d6a9c12c9c00cc3f1ae9f56814ad73775fe8eea40c67dab48d473562d1dde910bb594677b7dd1c66b335db6e724f39ef5bda036fb28c91d5466a96bb1065e01eb5d305a8fa1850b626cf358ff0011236b8d2ac2e940fdccad13b0ebf360a807ea0d68893caf6f7a8353b63ace95776498f35d43420f4f314e57f3e9f8d7ce60aaaa55a2dec11766798698c2f9d2d117e5de3710a0860a3803db27393fe35d95b5d02f02060419b18e318183f9703dbd3df99b3558a62c14ab480a671b98678cfd783c7b01daba3f0ddb99668a568caac718c83c861bb38fd0fe55f6e7423c53e2a9377ad6ac72c1da558c6e192486c9fd79fc2ae6976af69e10b1802be76b31c7a939e47a56278caebed5afca819be79886de720b7a7bf535d95c16874fb14607091853f372b818f5cf5cf73cd4a6ee4bb15bc2ba54b73a5dfadacc52fed248ef6d1f2530d1b873c8e8723a8390066ba3d13c5be1bb0bf1a95b699ad3eba0e45a465c309339c97fba403939efd6b2b4e692cae92f2dee522950efdacc724f6e9ce7e95d3c7a81d6ada784595a4b24b9dde55baa93c72371e9f5c0ee45315afa1e75f11f4a82e56e6fadca3092e19e6c3ee0b232879003fc5827af73935c1d9d8bbb608caf524f7ff39af5cf17f8745e5cdb594261b7b4807986dd58ed2c792c3239e40ebcd667fc22b1473112f298dc7cb3c9078041f6230476fc452486ec71515918f271f7541c9f7eff00e7d2925b630293c20cf4c7d71fc8d77a3408a28313363c96704819dc003f2fd3a9c567ea5a44037eddc14f3b5cf2bd481fc87e14023887ca951d10718ef5ecff00063c41656507d95b1ba40aecce703073c83ebdbe99f5af22d56c5e14da796cf6ef54348d5353d3af44b60b891405c85cf7edf8d70e330ef1345d34cebc257587aaaa347bbfc747b6b6b3826b729f6a9004c10014c7b6783c8e7d0d798787bc5579a3300f27968ffc65d917f22318fae2ba6f097813c49f126eae669f53d1b4b92dce04facdc25bdbab919f290364173ee31907a75aa7e21b1bdf09dc5bda6b66ca48ae137417fa7ca92c6e324754e18641ea030ebf30a9c0e1a585a2a127763c6621622b394743d2347bcfedad3a313c05be5cef39e3df2a723ebc8ac4d660fb1a346a8919da76aa1c9e3d3775cd739e0ad624d335b367be33175879f94e39f94e783efc75fcba1f1b6a70c36c2e10aa9390eacd82a7e9dcfb8e2bd039133cbaf8c53ea73ca565453322c85d3694f94f38fc3ebc5773e29d2fed1e3e36ae4bc5750444a0e012ca84007d0fa1e327d6bcf66bd175a7dddd2381baf61dbb4ff0ed6e33e9922bdab5ab076bbb4ba75daeda55a313bba36cc363b8c633f87be6a50d1c178b63167fd9b06c4899e32e587cc09c9058771f77a7b715534ad3d22bf85de37c48e151c3ed313fcc432fd31fae3e9d878e34f4be5d3e40bb98c72ba647070c1bfa918f4355adad17eca83698593f7a1578c01907ff001d39cffb269d87d4f6ef843e3797c0b7b0e15a78a5043b44a37cd1ff001654919dbfc8f22be8e5f0f69de36b01aae964fd9e639d8460a9ea78c9e2be0cd7356b9692d96ce7f2cd987b9653c15c3b6fc77e0056e32319c7a57df5fb24f88dbc71e029e59c65a170a081f23291c11d81fa13d8d7cde72dd0c3baf1dd1ea60e94315515299ca5efc2d943920b714db6f87b736c7764903dabe979fc3d0b11f28fae2aacbe1b8bcb3f20cd7c3acfaadaccf7a591526b43e7a9f417b38c8209e39e2b9cbdc2646307debdcfc4da1ac292616bc2fc4ec60bf28062bdbc0e2d6259f2d8ec13c23f230afa6299c9e3d6b9cd4ae8e0e4fe02b72e36caa4b1e6b12fadfe5273f535f4f49ad99e4a30cddb07ce7033d2af58df057e4fe754a78c0623ad54f3191811f8574ce0a48ab1bd752f9a06319ac4be8c381eb9ef5a56a3ccc03dc669f3d82b6d3d0835c0bdc760474fa10296b183c2ed14dd5a3dd19e2af69b6de5dba0639e2a1d4b022f6cd77c58f7471ac8b3c58ee7b5557b478577e48dbce2a6d3a4f3554f5abd71186880af8bbb8bb12709afd8a477b1dd8411c33b066dab8db28ceee9ea39fa935a1a7136da3df6a0f98a3581e51cf190bf2ff3353eab6ab776d35ab100483e52580c3039079ed5c8f8ff00c44ba378061b25cfdbf526c346a3a226011ebcb71f857dbe0abfb5a4afba3aa2ee8f189226d4353b29ce76c93b0c93b4f18cd777aa4f26d4669144606d01cafca7d8920fe952eb1e147d034af0fc7b4acb123894271f3100b73f527f2ac9bb92e2672536c51ae1703e6627dbdebaa94fda47991538723b3f22f697025c5cc10024990f010e73cf3b540c7e3fad7776f662cd16dada352c4ee60abc938c1c64feb585e03d0c059ae6496492561b1b61e540c6406ce01ce3d6bac16ccea16dd5634270550fe1824f5fceb5d056763265b58436d105bb90720bbeee0e720e395ab56b140408ded563033f74ee1923d7af20fb54c90c704a4cb7710dddbcb018f1f4c7ebcd5982f74ddc0339dc720fc9f2e073d3b77c7d69824664363017526363952e5fcb1927bfe2703ae3bd63ea5e1f8a55674c310480067e639ec7d073f5cd7697578bb6444188d1b38039c77c9c753c0e9ea056649a8469b23fb32c88171b8119031d3d403edd690d9e7babf85848a711ee97a7320007b71d7b551f01f87e65bdbdde8913479024931c67ae33d4f5c7a62bbb9ecedae1d92091a39082576af98074c704e4743cff009142d74f3134db5483d59908f97d3703823ffd5410b466bc7a96a9a44f332e8b2eb7a36adb19adace3265b5940c37963ba9dbb8753d881d4f1ff0011239bc4da3c33fd826d1f47b2dd2acb7a9b5ee1c8c128b9cf4c0f4cf7c9c56dd978a350d311e08a5e4e4ed61f293ebf9f6f7ae7b5fd427d6656975098caddb7333600edcf61ce3af1424819cd784fcb3ab96b7769041211119380e31fa1fa55af885aaccf04492874604aa8dc78ad4d034b8aca52f1a364f2153ef01d8f24e2b0bc7b6b2485e7ccb271ca9fb83fcfad4b928bd46a2da7639cd1f6ff0060dc9602461708082381d724fe9fe735f4bb5bc77763a248efe691a4c396c9e72a40c9f518feb5f34f87d3cdb278b71657b840ca3a9e0e07f3fcfbd7d3daa9369a3e8d1c7b702ce3db1b0e36853c71e99ebf5a6b7634705acb11a559db9dfe7dbcdbd01e086006f1f438e7ebef4ff0fb2ddf87ed6e06125917c88f71f724027ea31fa541acc69a8ea56b31dd93f3796a7925460e47b1c83db9acc73e46976f611076680821507562fd47b81e9e94c3a974248ba9bde22304920912e102e3e529b72befd4ff00c0403debf437f63cf8753fc3df86b34d7703da49ab4cb731dbb382ab104011947f0839638f4232062be29f86de0cbbf18f8a61d32d70e19b33c8a32b1a039673ed966e3dc0afd178b5db6d22c6dace02120b789614527385550a3f402be178ab1528e1e386a7bcb57e8bfccfa2c9a9c1d57566ed6d8edbcd563cd12b27964f15c32f8c909cef18a5b8f19c2223f38040afcae34ea5f547db73d3e9219e347416f260f6af967c697fff001399573d2bd97c69e358cc120f3074af9cfc47a81bdd4e5907e38afbdc830f34dca47c5e7b5613b28931b92c719e6a39e3675393938e9556c642ce73ce2afb21652470057daa5cacf9048c2ba83676c1eb55554120606735b135bf9e429e2a4b7b058c8063041f5aeaf6894752ac57b681940c6323b54f3feef631e39c1cd599ad0c43747c7a8159d77724b2023bd7993973487b1dbe9f89625fa551d5e36d9c1ef56f4c53e52107b52ea50964cd7a11d113d0f28d1ee8a01b8e7dab69ef418cf3cd71d6ad70847ee9faff0076af1bab8dbcc2e3df06bc19e1ef2b8c76a0f24ee465bcbefb0ff91f9d5ff05f826caffe25da6bdaaceb7b1da9cda59247c4120e159c37de03b6318273c900557d2e2bcb8bd4f2e163b8ed2aaa19bfef9cff00857a3e91a5db585daffa3cb72c1c6e0df2b4790464061c81cf0739f5af429374e36476508f7394f13f8264d734a4fb3440184c82272981d4e17af19e471c1c66bc76ff0041bb4b88edd202650db4b440907b75c63b1afaabc38b0dde917d6ec499edaecc2ea41c8c93d8e71d7a127eb591e22f022dac49f68859e691ce718f953b9c762d8f7a8a58c951aae93d8fa09e0a35e92a89ea70be08f0a11a5aa2c61cb602990ee239ea571ea7ad3bc7de0ebcf0ee86751d4cefb503fd5a90aca3fbdcf03eb5ecfe1fb4b79b4a7b7b63e5903fd634787f6c9c67af635e4bfb43eabafc5a65bdbaac5721f6af95cef72481b402703dba561f5f9d4c4460b4bb3abea14e9e1dd496e91e6b65ac6966292e60d29a42c301673f2e0f7db9f5ed8fe759d7ff00122eed1731c30408c01c476607e5c7a57bafc39f835e1cb4d243789f5586d6e671be3858e76a8e08278393c73d07bf5ae82f7f67bf056b76f24da76acbe79040427729f42a47f238afa77ccb5b1f2e927d4f9aecfc7f0eacd147a8429303c0951763a376e475fe75a17512d9843249f68d3e41f24e631f2fa6f00e47f9ef5d5f89ff00671bbd1019ada3c6d0c730a901806ebb73d3debcdbfb6ae3498ffb36fadda753205519cf078e300e7ebdf14d4fb89c4dc364648960590a83cc538f9829fc79c7d2ab7993c53a9ba422e51721d480244e9d39041fad74d6be1fbbb54486486e3ecf22ee46963394e339c918e955f5dd3fecfa6c85c33bc5964c282064723e87df14a15a33d10e74650dd1c1eb4be5dcccb18dd193b9403c8e33efd8d73b23c9f680269485ce4300323e9cf6fa76ef5a9aac8f768b3c04a90a1648c7de03b30279e391fcea182e924219d54ae0072178fa95fea3d3b56e73a348660d3164116fc0077347c11fef6ec7e949e28d19a7f0bdcccbe6072376d923201f6e3bfd6963856e4456e2057f3186d318ebcf5c7e75bde361691f86a4b58a0315c42857875538c71c7a7f8d78f8da9cb52115dcf53090bc272678b782e1579a34c16779d00ce38e7b7af5afa6bc6f27f67e99a76cc2b0890039fbac003c8fa01c77c915f39783a3c6ad6f1ba965372849e8474e95ef7f13ee256d567b520f931e0c78e01c28e7e9fe35eadcf39238ad4a30d1ef88957cb95c372012ac707fef9fc28d22cee6f6fe2f2e169667631c7122fccee5f2368f5f9b1c7f8d5dd32cee358bb82ceda292eee242218ad6dd77492364aed551c92723f2afad3e0ffecfade08b28f5ad66212788e68f0b08c34762a47407a190ff0013741d077278f178ba7848734f7e88a8c5cb623f863e1187e18e82db821d66e9435dcaa72140fb9103e8bdf1d4e4fa56a6a1e27bc9e4da92647d6ba5bcf09cb72cd843f95518fc153ab9dc8719f4af839d5f6f3752aead9839d58bd1d91cfb6bb7ea9f78d606a9e33d52df21727f1af4aff8451dd71e59c7b560eb3e019646f923ada9fb16fde8a34588acb6933c9f53f11df6a0c7cc6239c706b3520672093926bd065f86b765c911e79e98a913e1d5da203e51c91d315eed2ad4a0ad0d0ce6e751de47156560c801c1cd6aa43b21c91dabacb4f035e20c344c067d2ad4de09bb643b626e47a54cb111bee38c1d8f39813ccb960471db1dab563d3cb302173d38ae920f015dc6ec5e339cfa55ab7f05df46c02c6d8f6a89e213d992a2ce3ef5162c271923b573ba95b66642bd322bd1759f03df97cac4d9c74ac097c19a934c80c0c57239a88d457bdc969f62f6920f9033c74c558bd61e51c815b561e12bc8e200c473e950eabe15d43cac884e3dabd47520d6e2b3b1f42b7ecdfa4a9cfd8d0fd145427f672d2f27168a07d2bea83a4c6c3ee8a6b6911765af45e1a1d8dae8f96d3f67dd36dbe75b658c67965c8cfe5cd57baf84f6f6a9e58b72c87b160bfa6e18fa9c0afaa4e8b131e541f6c7eb58daaf84e3d451fcc9b6267a63b83c639c0c7ae393ed49e1e3d8b8cb94f84fc1fe06bdf0b7c4df125b5f583a59044ba855650d19c93c02092318e9eff4ae7fe22442f3c40b1dc05b584a168dcee6cca3eef2403d4fbf5e457d1bf167c211f825f51d58c2d1457b0794263b54bb28edb7b63f5c9c0c57cfde22bab4bfbbd3635b82f213b368705958afcad9207a0e79e6be3f1b3953c5b56b5bfc8fb8c028cf069f7ff339d96ee292ea3fecd8d21d5635db748ee7018f04b71cfb115cb78fae254823d5eee28a4b8b3b889e28219010fb4eee09fa0e07a8aeab59d72dee18c17b672b5fc0bb8a07da59bfde07a1ea476cd79d788f5a8c5ab42d025a2b28668148c4649c96ddce4e7dfbd6741fef233b6c5e21feee51ee787789fe2aea7e2bf15a5bdddfccb789911c254ecdc493b029c1079e37763f9f57a07c62d53458e385c24c220417076e71c76fa7e35c0f8a21fb66b82eefa259ae207668ae0210f8ce54647de009cf39383f4aa0ce5f4f78e19d6d279a53b2508495503a918f51c1afb853d134cf8771d5dcfa2ac3f684bdd4a35b1bb883f9830c9210c31d8f1c83fe15c4eb3a5dd8f88d01b4d3bcf10a0794070d9673bb68e3b023dc1cf35c0f82be186a7652c57136a76663964322c9192ee154804f518ce78079273d706be8fb4b0874dbc8aea670669c095da56f9b248c727db1f5ea3d2bcbccb13ece9b8c756cf532ec3fb5a8a52d123b8d17534d7b47b6b2934d9e196138f31d3e51efe87f0f4af3cf899a42e95a77da239b0e64da816339c771ea4576f77e229ede1816daca5b8591f0668f1c2fbe0e4807fc79ae57c4724fad3ad9904dd07dcf091b8f2475c1e463bf6af9fc0ca54e4a5d0fa4c7463561cb6d4f018f4c93ce91238c80d970a7907b103db18e3f4aab1f87eee4904b6c8c636c8208ddf5040fe7f4afa1eefe1ec56ab1cb0c11b3c439524723dfd7daad69de18b7b88c5b5ad944a80ee62b116c91d718af5a79bc63a2478d1c9e4f56cf13f0f787a48f55b4332e5036f001e7df9acbf8b1ab437af22421028ce633b5cb678e304f1efef5dc7c45d6f4ef0ac925a45710cd7d9c6c8dd5597fde07a751d39c5780ea13cbae6b1e4c2aa659d865c1255b3d8f19fe7fa574518cf1959546bd0e4ace386a6e927ea56f0f96b7be8e609b0f985828e3a62be8cf1e249aaea5008dd23778e19bcfec03c48c42fae7238fcebc3f5cd01b4c9a2b4b45f38dac63718f9dcdb72dd07f9c5772bafddcba843f68882182de24cb36d601635ea3afe3c57b7513a72707bad0f2e9da7af43d7fe11dd587807598ae6ce12b7d9c9bb721a46dc7f10173d857de9f0bf5bd37c7ba7209de04be03e78d70a7eb83ce3fcf15f9d1e15d496fec531f34b1364b326d241ee08ff3f515ec7e0bf155e69724535a4f25bdc45878a789f0723a8c8ea3be3deb8ead18555ef23a2da72a3ef73f0eadd80c46a7dc0a85fe1d40ac7f743d3a579efc3bfda8964b48e0d76146280033a02ccddb915eb5a5fc68f066aa171aa45131e9e6a9507d3ad63f53a4fa1cb24e3b9863e1c5be706218fa5327f86b03ff00cb21f957a9595d586a31092dae22990f428c0d5cfb1a1ed4fea34d7433bd8f193f0b2df27f723f2a51f0bedc75817fef9af65fb028c7ca315e75f177e32f85fe10697e7eaf7682ea4e21b553992427d07f5a5f53a71d6c68a473e7e17db9ff00964303d074a61f85d6e78f281fc2b9cd7bf6c2f04e882de18a46beba92212bac0372c7c6704d6e7817f698f07f8bdb6b5d2d948464ace40a9faad27d0d2f244dff000ab6dd8f310c7b8a70f85b6c98c42bf857a4e83e24d13c4d1efd32f62ba50704c6c0d6e7d814f21462a960a9ef633e73c465f8536f2107c9150ffc2a6b6dc0f920e3dabdd069ea7f846290e9880e714fea50ec1ce78ca7c30b6007ee463dc5366f85f6c571e48fcabda3fb3d40fba293fb350f6155f548f60e73442fca3d293cbc669e1b819e2919b9ed5e9180d2b9e3907d6a368d5718c2e07191536e19a8a7917610c383d690cf1ffda5bc1b278cfe1a6ad656abbf5048fcfb76701937af3839f94038ff0038afcdbd53e22cd74f25b5fced15dc522a389004daea392b8edefc75afd54f1a6b169a668d7b3ddb916a91b1755dcacc31d31c64fe55f941f1d7c3561e259354f11f85a29ee11ee249a482753b9933c9007380c003fd6bc5c6d2a529c5cd7cfb1ed60ab548424a0fe458d7bc516be21649e6266d9f2abab9dc38e5b6e7f5cfeb5c67882066b38c47f689f3cbf39e8320f3e9d38e95e6765e3596ce7f2e3b6b8f38e508c03d472060f1cd10fc52bfbe98c2da6471070799646c83df3c60e715c71c24a0fddd8ea9632335ef6e7470e8963757b0b5d85d81f7b190e633b7248383df819e0d63cde1f834cb4895266b9675329990900e493851ff00ebcd1a4f8fa493524fb7da5b5ce9e7227b788159447c026339c6403c6eeb9ea2bd3fc59f0a6786d20d4f4974d5743ba8d5ed6fad4654a760ebce0807047620e6bb1ce54adcdb331a7877884e54f56ba75ff8270be0d8fecd724dbc1f28c36e6e48638f9892319c123f3e95ed1a14e8fa6bc9147f690ac6368243b5d0e48241239eb9f4c74af31d0ecded098305e161b14a955da7ee92b93e9fa8aeb3c3d672cabba3d42e210dfbb04b61832f0081ebc1e7b8af1b14f99dcf5309eeab1e91a6e91aa1d1f13dcc5a72449f7e350ccc00f940dc4f38ed8f4e6a97877417d47c4b6b35a23b5a458590dd67cd57ee0927afd3d6a5b597cd922b5824926d42601c19f88eddb1cb27a9c76c77cf4aeff424b1d2563b116e8d22a86339ced6fea0fea7b75af1a55654938add9ee4211a8d49ec893c4de1673a1f9c23f3867686c61939c02475233dc1c8e339eb5e7ff107c5765f0bfc26504c92ea33465943a9c0c0ebf5f6fd6bd1759f1ad869fe0dbfbd3338b489f7056701081d073dfaf439af89be21fc46ff0084c75b796e2edfecd23610bc2645247738cf4f420f1cfb57460f0cf1551396c8e5c76295085a3bb38df11ea16daeb4d766e6e26b9b8627729f955bd4820923e9f5f6aeef49f87b6fe02f029f10ea335aff006d34f25acd67e6edb881c6194a8008646561c82396006707107c2ff0cdadca78a350d42d6df5536b665adccb30254b1dab3c2ea7276c8d1e54e01dfb49ed577e387c43b3bcb8874cb5d38416b005ba92f25769a69cac1146aac4f000f2895030543e0e4f35fa865f1a9848ac6535749b5d37e5d3cfadf6e9eacfce318e18893c3c9d9bb3ebb5f5ff002dfa9c08d4ff00b41e6bc964758600649d837f781c8c75ea179f43d2a99d66e2eef91e0966376ca8c73200c723aa9efd01c77c9ae546a32da0b8b747644970ae83e61b46580c9f43b7f2a974c6775886d66119dabf2e481ea31f8d70c95f73aa2ec7b8fc3ad59aedfcb721d8a8df9f94a90721b8e07e98c7bd7ab6937125ba200cdd776437523bf3c67b7af4af12f01c6d652ade46ed23418f3636f9b2a4f7c8c8183d3b8af5b851e3848b7942c2c19959086f2ce339c71f2e3191c76e9591d173d2b43d55a584c8ac3cc8fef1c60fe5f5fe75d25aeb4644c1b8f9180cabf233dc74af31f0bea8659821654b84cfca32707a9c1ee3a7a9faf5adf7ba7560e9e6445bf84901491d7d39a51dca96c7a5d8f8ef57d0248e5d3ef6540a7fe5839c67e99af57f0a7ed33aed9ac6b74e970071fbf1cfe2457cd76b7b3c90910c6c57fda383c7414fb5be314e524492052790dc1ff3c56e8e5714cfa9fe22fed37e21b5f085c5c78774d8eeafc8c0272429f503bd7e6b7c58f8a7e26f1778b2f6ff00c47773dc6a39db9906d0a3b003a015f54e95e277b1900598c919071ce3f3acef881f0cfc3df1534c29322d95e8198ae611b413fed0ef4a51b84572bd0f8ded7c68f6ec419998b718cf4adfd27c79324a920b86538c9c1c0150fc4ff803aff8025692389ee6dff865886411ee6bcc8ddcb737cc9c40a3e53cf03159389aa99f60fc29f8fdabf83b528e7b0bd9180219d49f95857e817c11fda474af88b671dbdecf1da6a7de36c00df43debf1c3c3f762d91185cb48bf74a93c81ed5e9be19f125ce952c13e9d7ecbb4821837239fad3574128a91fb630c8b228618208ea2a5623d335f237ecbff00b500d7a2b5f0ff0088ae87dab0160bb73c49fec93eb5f592c819032b64119cd6a9dce469a7664d8c5373cfa0a66e27b628c9c7b53241a518e4d33cec704d45212464530668b95627f3bb5432ca7d4123b6718a43c71d01aaf781d2076dbbb8cfcc7007b9ef5370b1f207edabf175bc3ad068e3517f26485bed5616f398de543d012548553c027afa57c576df16e7b7bffb55b0d3b47b08888ae3cd94ccd703760c7b5812aa132bc05ebd72715d37ed8baf6afabfc61d5fedcd0131481572e331a8e464027071cf278e338e95f3ed9ea1169d7e6e26d92cf19cc42560a9bb3c6dca90a077382c7b11cd724a0a5ef3573ba32704a28f55f15f8134fd61a2f117862cb7d85d219121ba6549a588e0abaa0fe303e538efc8ea2bccb5df0db35cf990abe0b1198b01a218cf4fc3f0e2ba8f0b7c4663ad8f36655b24134b36a13cdbdde5d8312ed39f907395c921028186c5755ade8fa7f8915e7d2ae921be6413ac2ca3c9bb561f2ba7619f6e9d0f3903cc9c2549f32dbf2f27fa1df1946a46cf7fcfd0f097865b47b848d631e6ae249581df26dedbbdcf2474cfd281a96a36493a5a5edfdb4774b99218ee58292339dc030cfe59ec78ae9f53f0e4cb766deeed9e198617246e4273c918f73d7155a4d12d9e384ab248e1981520fcbdb39c60fa7ad6aab592b997b3d74d0e7f42f11eb1a3c815145dd96ec1865c313c9c953dbffaf5dc693f1216e0c6eb300e483e54adb4a7cd9c01efcfe5542e6c20b1b236f688d34f90338ca2f1ebdfd6b3f4af86f14c9e65c2cccc4fcccab8c7b8f5e4f7ac65568bbcaa591a469d68b4a0ee7a6597c58bbb788e648ed84a4911b91fbb507d49fae0f607db9d8d3be26eabe2a66d3eda43baeb30c97061c2448a7823dfbfae7f3ae73c2bf0c34fbaba83ce8279151b79271b9987538f4fd3dabbff0018ea569e04f0fbcb6d6c96f26c65511e18924718cf538edcf41c1e95e454af426f92943de7b33d7a74eb4173559e8ba1cbfc62f14c91e8d63e1bb4d411becea376f9447bce3a93c8ddec6bcfbe067c25b8f887f14e0d0f5211c3a3dada4dab6b6d0ec26deca0532cb865e164650406e70587bd70faef89ef2579a7824954ed6777760871ea155003cf53fad7d0bfb12bc1a8e99f1e6662a97971f0df51b75924240572a78279c12a0e7d877afa0c1e19d085a4cf0b158955a778993f02bc2965a869f7fe7402d7edf6c63b5894798d1238de0a9237139c0231f36d6c8e0d784fc449eeb4bf1d6ada65e8f2af22b8920b95b85246f0ecbb5c6327680006fbc338e82bed2fd90fe1e4da7f892c35ad5eef4cbeb3b520c567677466965988668785014e591c8049fde478e8f5f3f7edf16ba237ed27afdf68d7b05dadfc16d7d3bc1b42f9b24609c6def8c649c3647cc33927ec7199865f38432dcbe7cea9ae694bbb6ed6f55ebb69ad9dbe4f0985c6c6acf198d8f2b9bb25d925fa9e0b716b2857902a858caf991efdd80df74823aa9e79f719ea2b4b45b78e77456710b673bbb6473ce3a71df9eb59966bf32b03b01528f8e84672474aebb4cd177211801c80e18118273c60fe3d0fe19af119edc4f4cf05318e683cd4575651b65520ee07a83ea3dbb115ddc0ada5ced12a9f204840121c989bb81df07b7ff5eb90f08db9b79a3592278be5575931c67dc0f71db20e7f0aeca5b80d7a258a3cc72828c03803705fba4f41c720fd2b3362cdaceda66a5048c0c4928ca861d1bd3d31f956ebea6eaa9b82a9386193953eb9f4c64573d181a8466352d1b2679083e538fe25ee38ea3f955196e9a355864c248a48f91b68627db919a561a76563b33ab4f6cd9d81636ea00f7fcbf2ad11a8b5d44d32cd96c64a162c0015cf68f7cc47912e1c0180cd807f306a6bab6b6da628644df2745721581cf635b199d15ade4dd5424ab9e597208fc3ff00d55ada7eb4d02ef8e46281b952393ebf5af3c835496ca6f29e76f97ef2dc72c2b6ed75b5899583ee6dd9c2a8c8e2988f5cb3d42d7c41a5cb6d7004d03a61e094126bc03e2efc00d3e2b39750d0ed02a13b8a8ce54fd2bbed375bb656f3a3b89524e491eb5dae95ac2eb560503a3b81868fa923e95371b8a67e7a5c5add693752413a3ab8e9d8e2bb3f09eab03f971c795700651bbd7a6fc7cf850d086d56cd82c44ee3b4648af12d2257b6bb058ed901c2951834ec4aba3dd3c37accd68d1cb6f298674604738e47f5f7afd14fd943f6818be206929a06ab2ffc4e6d53e5663ccaa3bfb915f98ba3eb11b6c132bc4e7fe5a018cd7a9fc35f1adcf82bc4763ac58cbfbfb690306ce091e87d88a97a6a535cc8fd7800fad3b6f4feb5ccfc3bf1ada78efc25a7eb16722c8b71106600fdd6c723f3ae90cd93d2a93b9858428290c630715213ce3a5358e28023d8319e0d65f88f578f45d0ef6f186441133f2768e067926b4f39e075af32fda3f579742f835e26b98675b7716c54c8594100f50327a9e9d0f5e86a1bb22a2aed23f213e33eb73f8d3e216a7a8891a213dcbca510e77658faf6fe5cd7986b93244db2370513e679981f9b071ffeaf615d5f8baf1e4d4255f9913712e58e78ec3ebe95c2ebb7cd2ca96f6f1c6a8ae49c20db81db3d7d7bfafb54c51b49a39dd4ef2579a48cca12395c3322f43c8c038fff00557b17c1bf196816ff0008fc636be2cd62071a6bc73e8da746447a8b4f29db2bdb39c654614b2b1dbc67824578aeac50b2b42ae93160061f7649eb9e01cfa7e3f5aa10481e64898ed50d8cfa01fe4d39439e36bdbfafe9192972bb9f637873c45a2f8874f8c1b88751b54863822be0a4c910ff00a79cf313b72d9270700296c54f0784f4996092e6cca496eece55b3c92324fe19e715f2a784bc597fe1fd5a2d42ca47505d5a780b656e10306f29c7f12120707eb5ed50fc61f08ebde16bd1aad8af87b5c5321b61a709120972b9539190391821860e14838e9e4e2b054a4d38a69b767cbf9daebe767a76b1ebe1f1b28ab4eceddffccf4ab5f00c51ee2aaa7603d7839c904fbe09addd3bc1d0a402478964c2fce87b823dfa57cffe0bf897335e882f7529c4b717416daf1275d96eeea776f562015391fc4b83ce7b57a3eb9e3dbdd1ecacd2d7518f527b94261b8b79a37f30a9c365519b68271b49fbd93c002bc89e4988f6ef0f169bfcfd1edfaf91e853cd283a6aab5647a7c91e9fe14d364b9b8114704208324808dad8e0377c63a7e35f34fc50f8a72eafa9cc05b5ba476e58a4b15e06dc3a6e5da46e1eb8fceabf8dfe21f8b22bc96de495ad2e5623e758b36d70bbba75c83b4f28c0e0838cd7936a1aa3dd2b192456121dfb5542b83df701c03db8c66baf01962a5fbc9d99c98cc7ba9ee42e9152eaf66bcbc7b83cc858b003240fd4fae3bf15f487ec15e38d2fc33f16b59d0754996dec3c4fa449a6b3b38db1e5be6c9ff75dff002af9827ba67668e3385c9e17fa9ef56347bfb8d1af60d42d64f2ae61712472633cf4208f4c6463dcd7d1ca37872a3c34fde4cf5df837e0ebc5f8dcde02d5ef2eb455d35f508efeead4ec9e06b5b79d9ca93c03fb9ea79c13eb5e55a9ddc9abddcd7d35c1b89a60267690056c951918e9c648e3f2af4fbbf8f3fdab6faeea5fd8ab1f8d357d39b49bad75642035bba08e5629de568c042fc6e1d79c93e54b0797c360283c71c0ff003fe35d33af56ac52a926ec631a50836e28d3d31becde5b15254fca7b11fe7b7af35da68d7132cc5400fb8642b1c11ec3af18cfb7d2b8ed3cf90fb82820705194952bf9ff00faaba5d22649a4b65631864c88a462171ecc4763cfcc3f2eb5caf53a91edfe12816f74f86589769fbc5082aa4f7c1ec7e9c74e2b5da008a52624ab739ce3bf0738c03fa7f2ac2f87dab0b5985bba4bb24c0647c313ce1b23ae7f2c8aecf59b5304d2a4677fc87629046e5c70335269d0e7a2df6531632b6c6cf9721e0ab7753fcf9aced5b514729e68c22908ebb4385f438e4e2b5936cf652452a1c85c167ee9ce083ea0f5efd6b96bfb68ae1d977149c2901d78da47ae3d7deaec6773a1d1efcf991b473acaa31b4ec0a71ec09fe47f0ae966ba90a6e70924278fba1b0dfd2bccb40bb8ee235513879a0624a0631f1fe7bd7796b3349691cb0ba946521830dc0fe7dfeb4d683dcb8da889cbc1730c88a47c8e40254fb1f4f634fb594c4e0bc7b933d768561f9564b4d69f3925637e8cb183807fddee3e956a09ae7ecbf25c43711a8c98d980ea7b03fc855926edacd0ee5904d2c241c619416c5751a74c2cff00d26dae1a57fbdb09c7d6bcf6da40d843e6c4c7903939fa1adfd22f763982762327008c9c1fc6a0a47793490789f4d78665063990ab06fe16ec6be40f89be1697c33e229e395488c3f0f12f03d2be9bb3bdfecfbc31bc8a5186173c35731f1e3c2ada868d1eab6912bbae12620678ec692761b574782787f5970d1c4d2b01d99b915ea1a0de996356745745f94bc7cafe3585e04f0e9bf785624732211c6ce09afa53c2de15b2d3f44963bbd3a1937ae5cae01af36b63e14dd8eea3839d4d51d57ec97f1beefc09e2b87c3da9ca468f7ee02993a46e78047b1e95fa0d14a9322b2b6e5619073d457e3cf88ee878775f8a2b6f9ed33ba370c495e7a7b57e89fecb3f17d7c79e0786ceea7f3b52b1509216eacb8f95bf2aeea73ba52ee7055872cadd4f7c7e9511723352b1ddd7ad318023b66b6b1ce33700338eb5f35fede5e2cfec3f83a6d42b7fa6dc2445c600039247b92076e706be93c70475c7506be33ff8291da9b8f0168b3994a986e8aaa0c9e1812c78e01e00e6b39ec690dcfcd9f10cbf6b91a69480b927cb1d739ea7d07f85715a99fddc920c0694e391d1467f215d16af791a485634c449c0ddd18fa9ed5cb5f304690962d18e00ea4f7e7d327f2c5522a473d7b879c00446a4860cc381cf538e7b7f2accbd591266660632c727ef0ea33df9ef57eee3666dcdf3023e5cfeb54a281eee60bbd89279663db3ea7eb5665d47584bcaa10dc9e41fe557b516f9339e471d381ed59859d6e00903bc8a39e72477ce6ae9fdf44a4a9007f0f7a4c11523b9689d82632dc107a608c1e3e848fc6ba2d1fc6d2da594113bb3080af94ca3f791e33b403dc0c9ebdb8ae7daca679184519727baf38e7d6a16b596d10b6095248618c7f9fad6f4ab4e8be6a6ecc89d38d4569a35f58f10b6ab7ef79299e7ba6043c92b86dfc924927249e71c9e9598904ba95d796a52156c9f9db855c7249f403269d130920248cb67b0e4faff009fad46b3795148a872d28c48d8edc1da3db2067d718e99a87272777b949595911bac4b2308999a2c00a5860b0e99c76cfa529c155e991c000f4f5a8cafddc70060827d6951b7027ea78f4a904588e5dbc13b81e720104d4eb38046dc9e79dc3be7d71558472055631baab74241f9ba74f5ea2b7345f0e4fabdcac0248a29b6e764a5b713e8154163f9715126a3ab34577a223b677da7727eed7bb74f6e7dbebdeba8d160bb688dc471aec0de5c8cf1fc80f5c1cf19f63d7b66ba0d2f46f0b694889a8ea5a5fda36aa496de5cd2bab1e0b79cae36af7e14918e4119aaba8dae9ba6ea4ef63731aee41b23fb6ba381e9920ef18208c039ce7241ac7da27b235506b73b5f095c5a011b4a4c536dceec617d995cf1d3a83e9dc74f659638f50d2ad5ceef3a10109270707dbd33f873c57cc5a7de4f6f224d048902820b44acedb87a94c63dc1e2bd97c01e3b4587c899166b57e1a04231eb8e4f1fca9ad762ee9686c5bc3225c491e44c5fe68dbfbc33d08f5ebc8e7b1af3fd5ed9ad75690c2cd9563b76ae1d4673f88af54b8892de58a685f7c520054f5607b06c719fe75c4f8d74f8edf5849de05f26e57900b2fcd8e981f87d3b1ad518b47097f7dfd83ad5bde09a48239c71cee427b800676fd2bbdd12fed350888cb45329122b4195273df68ebf5c1ae3d2c6cf58b2b8d3a5dfe5f2d148ec0b211e9ee3d3b8eb59da2bdce837ad672a2ca91fca92230c7b0c7519ebe94582e7a65d32320c4de72f1f3b95279efc8e2aa866b6942a807070519739fa638a844a9a9daadcf96f1c8061810491df8f5fff005527db4a210254c28f99491907bf18cd508e8348d54e369884678e02631cfad7511dc43344665463220ce0b73f5ae1b46bb17270d25c6c273c3023fc6b7a18df18499d980c2ee909cfe0050523a5bfd418dbc4305a41fc40751ee6b56ce69358d06e2c67f984911c6ec1cfe3581a465e236f212d291d19b6e7e99ad1d264f224688a82809ca0c9dbee0e6b2669131be0e5cc5a5ea13c32958a48a52b86e8707d6bdfafb53b7934ccdc8dd6c073e527cc38eb9ee2be7c9124d3bc57e641208c4ff311d8d7a269fe22786c5e07f35a22a4955e807a823ad7c2e63094310d9f67809295048f3df89b0d93c4b73a748b2296cb6dfbf806b77e057c58baf87de204bdb69582b44c92460e378238e3d8e2b91f1cc56c9be6b59c9b7933b918720fae6b8ab4d50dacd90f823a3a9ea2beb3032f69451f2f8d8f25667ee12ce1f3c7e74e121db93f956624d824027ad5a12138e726bbaecf3ac87f9bb5b26be5cfdbdbc2f1f8a3e17c67374d3dadc1786dace232cb3c8548da00c9550392ded5f50bc6ccb938e6ab5c69705eda4b0ca8aeb22ed6c8cf07f9d66d390d351d4fe7f7c430c96779241242622ad8d927f0e3b11f5ebf8d7372464a3cb23308cb12a5b82e7d47d7f402bdb3f690d04689f15bc596e9179622d46750bd7037fcbebdbd6bc42f6e19a43bfe6da70147f2ff003eb5ac257412566674d186563803a0001ebed59b9314c0af014f4ec48e9fd3f5ad279cbc87b0ebf29c0aa5282324e06075c703fcff005ad0cca276487749850073c609fc0568d9496eaa19503b819c48781e9c77fc6ab342e10b141851925f03f4aaf1ab44fbb691cf240e696e25a17ae3579b0c11be4ce7e40139edf771f4aad690a5e4b2b5c9648c00eee58e147af20e7d8719e829e8b1a1fde299037521f0df81e9f9ff00f5e9c10baac6a36c6a77608ea7d4faff009f7a76b06acaf737a8eec96d1b430e36aee396033edd723fad421581c6f071df9c115af158c2e5898586400a023900fa820fe8688f4cfb48db1b2e40cee208c0fc33fa5473a45d9995c80bec3f11cd49636335e4c22442ecdc009d781fe7f3ae8ecbc1b7b7f71040b04913487683229cb71c919f61fad7aff85fc11a7f84e38af35442d736ec240c8e30154e371e382401f5031ed5c588c7430ebbb3aa8612759f64729a17c3486ce2b7bcd5f735bdbc518fb2b655581e5d99b3c82d950063a77c60f65a7f8746b0265b5b6874385c0f36e2290c6f2ab672b8249da7ae0919e322bb4d374d5d62e15f50b230dbcb2acf0402401b711f348ea0e7001e87b9278c574f2c50da412f996c9e542c0125d946e3c02ed8639e9f747d2be5ab663393d77fc11f454b0314b4dbf13c9f50f0947e19d3c47a6896eb218493324414b81ca02c5483ce4851dbf2f35f13d85c5d465c5dc7059be5f632cad021030487656383c9e49e72338c1af4bf166b565ab7dabed1e6db5a1dd11bcb58e528920c118451923a7ca4a96049079c1f38bff1ea5ab4623b674d56d89113cfe5b452751ce0139208ee14f19e726bdfc1aa9cb79eacf1714e0a5cb13374c86e6228d198e55076b4f0bf9800f5c8cf1d3b0edef5de7861e65f9639a399948601db0c07e7d0f4e6bcccf8b2f757702f2442a40da96e8b1247cff0a200a07fb207d315d1e89aac716d2f18278072a486ff000eb5eb2bf5382eba1f44784b5b544f2a69196dcf0e3ccde011dbbf7ed57bc79e1b5d5b434313095e301c61429dbdb91d0fe9fcab81f0f6b2d2952ea5626c02536e09c71cb1e579ed5e81e1fd4daf2de5b633a245202b98d400a7f3e9d3b5581e313dec9617aa672b18c6e591c6d3e85587a7b8cf5e2b5eeed1358b58eed632b3c0363142010bd4023b8f4359de39d32e12eeeedda37dc5cb6d2a02b7b819c7e58fa0ae5f41d5751d0ef226d8044a3690c4804775fcb3f4a2e4edb9dff86efe033bdac8c543fca6173b307d8671536a9a5449704ec44987dd2c369fd2aac96f0eb966979e4c697209fdf479278e7071f9e715b31dc34fa6c4b217799095fde2e770faf7fad341b95b4fbc914ed92112b020e4f51f8f7aea6d1e2911185b3638041c823e9fe35c9489246c3e458588c65171c7d3a56c695a80b41e5bdc71df770690d1d6db969650a9e52b20f9497c9c7bd6b593996440e044c17e5e71bbf1ae6a5bcf3521900ce3fbbc6456ddab0bdd3fcd761bd0fdd273fa5448d63b987e2e7c359cf2486328db1a48c118f4cd755e1dd42e201079570d1ee5e558e518fa83dab89f16cc916932300b8122f7e87eb567c2faa3cd6eb14532b903708dce0e3d8fad7ca66d4db6a48fa4cbaa28dd1e95abf85edbc5b62f09d915e63e5fe10fff00d7af12f16f846ef43d41e0921cdc4679403820f715ee7a05cc57b1b5bcfbadefd0031cb9c67dfdeb5fc4d6163afdb5b9d52d512ea25c7da230403eb9fad70603307867c92d8eec6e063895cf1d19fa411c5d0e39cf3c524db41c66a95ddfdc06d91c47763a01d6aaacf304669b7231ecd5f76ec8f83df634e5b8d8386e40c673516a7aed8685a34f7da9dfc16166a3e79ee2408a07d7bfe15e7bf10fe219f05e9121802dd6af3a136b68f9c01d3cd931922353ed9270075af976f2cae35bb89f52f11dfbf88752930c5aeb732c7ce72b1b1200ecb800003a66bcac6e614b0b1ef2ec7a982cbeae2e5a691ee703fb60f813c2be38f11cba9f8467cced1e657891b64d2679700807d33c57c41e21f875ab6917462921629b8e1c0c03f8d7dd9e20be8eccc8331c4bb0bbaaa33ca3938cf191d39fa5799f8aac2cf53b592796dca20dd146a50ed7f7c7a761ee78af98a39c57e66da563e96b651479572bd4f9247832eb717254a28ce3fbbc71c5578bc2b773cacab1b310c17a1233d871f9e2bd83c4fa0f95399a3616e036429609bd47519e71d3d6b57c3d616f6ba5c135df936f34c5992366512609e081db770013d00ce0922bdea798394798f0e781509729e28de08999d1230cc4e4b10b93d700607273d80ea481d4d759e3df8636be0bb18b4036466f1342de66ad7ad3164b494ffcb9c6ab842631812c8724c8591701327dcb4cd3f4ef0868d16bc6eed6df5a92266d1955c016ec927966f18918c46c19622721a50cfc88b9f1ed7358d3923b98632248c8091c818b1624fccd9ea49e7249e735a2c6547a2464f0b05adcf179a0f2672afb8b01f78f1cfb0ff1abb6564b2c810b2e40dcc41279e807b9ae96f7c2f2ea216ee355323312002114e3fda38e727a0e783d6b53c3fe198c5c7d995e09ae9332cb121f92150392eec40ce3d71f4af4dd6bc2e8e054ad2b3295af873788a05b679cb9006f7daa73fdde99f735daf873e1d2cd71f644f9e4c8373222332af711a8fe2278e38fa574da75cf853c2e6daeaff53b3bcbd9b26148a433c9211c171d0603600ced0483d40e75352f8a9a368963b6d76280d8ce55a46e980ec01032792179e32481d7c4ad5eb3d21167ab4a8d2de4d17f46b1d3bc172ace8bbe4554548a5656566ddf2e3230403939e7a551d52d1b5cf1118eea25b3b4b5c4e12df005c383c46aa40f946727d3a8e95cc47ae5d78c9fed125cc56f14ccc0bb49f337382016c10339191ce7201ce6b6e1bad1bc3d6125bda132c30be1d9e40ef2b60fca1feeaa8c77249e839e2bc69c2a26dcb591eac65071b47e13b77164da62485e332bc623901c32807a2904671c741eb9e706bcbfc67f10ef841752c19b09e108914b10c0890671b48e070460e3a01e955f53f89af677af73f69116c4f2865b7090672181cf19fd33f5af2bf19fc49b8d79f6c53bac580a4b603119cf61ff00d6eb5d183c04e73529231c5e32318b8c59cf6b9afcd777d7256f667f3589660e407cf241e7a64938acd5d8f9dd1972782ced9cff008d496ba35ddfca2448592324fef1f2aa3e87fc9e6b462f0e2c4e55ee138fbc8ab93eb8ebfad7d9a518a5147cabbc9dca96c9029fb8a539c92a41ad7d395532639e58f6f03cbe8a3af7e9517f65456811fccc8c13fbb6071c91d3b723bf5ab16d656e420569109fef30031f5edf8d171a4745a4ea6d121944f1c9b4705a0dc08e3ae4f5fa715e95e12f1e343306756c01866c28f97a71fe4d79440b0ca709724b1f9639186e39cfb77e95b76864b748668f63f232a8d93e98d87193f4a9bd8b48f71f165945ad6891ea9038200f9ddd7208ec300679af33d47432dba480bb2b64b468838fc0f5e3daba6f875e2a8fed4f6b772bc90c83634729f971c67701f78f3d47a7e15735ff000aa69b70ed6389e06cbaf96cc063d71d4534ee371b1c1e9b2cf6b2116e3e507057675fae6ba6d3272d13a8021393caf407fc2a27b660a0bb49031eabc007f31ffeba6acb14673b5b7123e68dbf9e3fc2ac834c5d5c845020898e73bd581fe9fd29d1ea326f55745009c64460607a13505bb45286ddb95cfdddc46d6a9d6d30cdb6407193b152901d0c579334614062a38200e33db8ad8b4ba1696241750ec7001e0938e6b92d3ee244b84522411e73f32804fd68d6ee23decd6ecc1631d01cfe3d6a19ac5997e3ed41bfe11dbc60db78c900f7cd709e01f1b84bcfb3decc40439420f4fa57a0a783dfc6b6d1db99e48e173962a324d6ccbfb2e7846d618af2f7c493e8b3f0158956dc7fdd35e1637198583746adefe4ae7b185c26267fbda76b79bb1d1f86fc68ba8218a60b3c6a30587df03b30aeee3f1494b48d22944ab8c127906b91b2fd9bbc47a35aa5ee8fa841ad5bc6376633b25231e8783c7a563697a9dd4334d64f098ae2273b848a508f6c57c7d5506dce93ba3eaa939c528d55667eb9bebba6bdc1c6afa7b305c802e53f3eb5ce7897c77a6d90db117be95793e4636a9c77627f957cfb75a2bf9f35e36c8277232916177b0e85bb67d33d2a9cde2a96192640728abc9986c1b071b816efc7d2bd9a99ed6946d0859fde7994b20a309734e6dafb8d7f14f8de6d4af259de5dad29db1ae03b939230a149381ec3bf6af36f104e2f2e9a317291b2e4b30c2160700b7b727a93ed4ff11f8baff4e848de91c4d9dc04633d472029c0fbc3008c91827358175a847a8adcce91305033995812ed9232473d4f03d369c01dfe72a4a7525cd27767d245469a518ab1ce6b766d7568eec59ddcb18a02492c3d7839039fc71dab95934d592e479ce711b334b304daa3e6fbbeb95c607be7ad77373751f9845b290c76ab338c3ab283dfafdee067be7be0d73ba9c30c4ca433481101ce7804f2fc77c003f43f5a85f6319d8f1cf1ba84d4a55dcce926416076e7e63ce3a7273f4cd60691a7c37babdeea5ac48e9a269b1a4b7d711b6d91b24ac56f09ed2ca43286fe05577e89cf4de2882637d6cf1c6f73248046218c65d998ed11a8ee49e07a9c5727f18ed5747b88fc23677505e5b69a43dd4d63279914f7ae8be6b2b0e1847c40a790046e47df39fa3c0d93bcb63e6b1977a2391f1efc53ff00848af25bb9a481034a02c76b1948a358d42450429d4471200aa09e0609e49cf057be288af2350524dc38c83c95fae723bfe7536b1a1a3060981e5ae00cfe631f5fe75ccdc5ab4208da4e0f24f4afa8a30a72f7b767cd559d48e8f63afd2bc6cb6f69875792e84661420f96b6e84f09181f701c8c91c9c7d4d54d5b589ade37d311e14c0f32e1621c161f753b8207a74cfad725b87504e7f954fd21f30851b9b680a319c0f6edcd75fb28a77399d493562e477f7292bce9262e42054919be68873920f63efd7e638a8d2e1f529208e7918c4809258e38ee73ea7007d71554fccd81c863c6075afa53f679fd9035df1bdd47adf8c74cb9d17c298caa5d3b5bdcde1ec234c6e0a4e32c46300e3b5655f114b0b0f6955dbf37e9dcdf0f87ab899aa7495ff25ea79d783bc31e2bf887fda57ba2edd2b4e8224496f6e5da3851410a234201ced1fc2bd3924e4e6bd1ac7e182e950ac50f9bac35c603b5cc9b0919e76a2ed03919efd7ad7d49adfc34b3d5563d36daeecacac2ce316f6f656511582d40cb05080727fdac12706b951f0c6e2dee3eceef05d408a763479200da542924657d08c700e7a57cd53cd6355b9592f2ff00827d14f2a9d3495dbf33e65bcf0ad879c43e8d145264a011c5b8a1c92bd4f24e0e7f2c62b2aff461a3b21b748228509daf1c1b1d4939cfcc39538e3e82be8abdf84b776862bd8d4e25ca2b390e377f78919c8c1c67d3a8ef5c7789fc1575a46a06d6f2378ad2760b1a9e84603852d8c8192c47f857bb471342b2b45a3c5ab86ad47e25a1e0f77b9e45c969429395662029e73edffebe2b1ae22df248bbca8c1c293b863d8fd2bd3fc43e11992e24689760e50f9bc1675f954e7b1e40ff008163a1ae7e4f0d492c44918183b51b00ae3a96c0e074e9ebed5dc92e870bbf53903149104676568cff0012fca7193cfbd5ab68093bda376889c654720fb827afd3ad6a59db411e1240c10805fcd5dddf8e9d7914e974d96d8318d58c6491283d073c75ea3dfe9d3a53b013585904db3f98ce4a80a64dbb739fe58f5e95bb671b3dc42fb98b4990aaca1829e9807278c9e8463d08aa5a65d5b053b94452b1c160f9423df81ebce7a66ba2d1b4f632c56d71024f692b1dafd4672095cf18fa67e98a9b1572a5ac434e321264888c1780b978d0772a71c8f6e7afd2bd6ece48755f09297ba2d240a092ca071ea7dffc7bd79ccb6d25bc923ac8f3b8668992423e6e30327be3907ebb873907abf044f752e87a8413471a9543c3b105bb8ca804640f6e9d2a3666cb54606a718b661bb74b11c956452573ef9e9fe715516fa04843f96a14b7208e87f9d4ba8fda26763760a81c064908efc0c0c7f2154a3b59951de3733ae720e72cbf8f515a9ce6ac7a8471822411a960001fde1ea3d6ba1d3745d4afe04b9b5b6926b355dcfe5f71d720639ae422591c26d42c371dd1b0e3f4afae7f6779f47d6fc3096abb16eed405960241cfa37e35cd88aae8c7992b94b4d59f389bd8537b864207dddddab9bbdd4c35eb6e3b6365f98a9e2bd73f68af86d2f813516d62de13fd9172f960a3fd5484f4fa1ede86be6ed72ee4d42e3ecb61c4ce0e173d87269c271a90e64527b347b3fc2ff15e996fa82adf5eb5b41e66c4f2ce09ff00eb57b96a7fb3fb7c4bb17d434bd4ccb288f300942b2e7b720e41af876db49d76d162bdb67696285779957e6d849c7cc0741ef5ef5f007e35ebb6bab1d3754d526b3550020c60139ed5f338ac14e555e228caf2ec7d261b1b0f66b0f595977261f17bc77f0a3577d13c4f6179098241186fb3b60203c32951f30afa7bc0b2f843e2568eff006d9ac5a6940317da232b2b1182486e0e31daa18fc5bfdb8c2dd6f46ab77caf9524437a719c906b8af8bda8c9a4ea5a0436e6dd4490c8f279718492371c75f439ac68c613f7eae1d25d5dedf81d55a53a7ee53afcdd95aff89db4fadf91e68bed46f252771f3c1e58ae4127e5ced24363a750075cd63de6b3208fce79a5b9e1f644c70a98ced07d86e5cffba2b8df14eb6c91dda636f9d20c9ce55546481f9eee3dfd456941a8462c16691f799071ddb8c0c7be401d6bc1a9095aecf7a135771ec63f8e75a7b7b3b8bcd46459608011e4383e58e7e620672c7b7e1f8561f82bc7d6d7b1884ceea61cb6d2a114940d85fd4f27a9aabf14ae957c2dac4aacc6e177790846efbacac4f3dfa9fd0578f782256b4d5acf52590cf6d323ef79188c10c434672792b9523d88ed8aeec360957a33a8ddac79b89c6ba35a104af73e848b5a4f34b12ade648183633908300e3d3731ebeb5caeb5e24f20c879902400c8482d9182093e87e651ce3f962b6a9aed9e9364866bb8ec824524e64b8ddb1f6ab10ac1016f98ed5e3bb751d6be9e8bf654f87dac69161e3cfb6ea7a5e97a94304975a1dbea456c6275c31cfcc64286440442cecca49079e175c2e0655b5dbd4c3178e851d0f86756f144f677915dc1295bbb7c4914aa9d09180c3d080720f6383d45725a7e916f7ba9d926a17a741b0b8457174d0193f77b880eb1820bae411f2fa36338afd39d17e04f81a2b4b59d7c1be1dd8d379b0de4b60b713c8f8ce53396621b04ef6201ef9e0741aa47e1af08db5a4b716f6a97766592231c310c2100855006e0bc8c803bfae6be929e5ce0be23e72a66319bbf29f91d7964ad23912aba872a241d1f04f2338eb8efcfaf43585a968d15e630a3232783dbbd7e9dfc42f839e11f195cdbf8cb46d3ad2d750ba95ed751923b662f38c6432a641128db8dc31907e6c815cd7863f64ff85dadcf73adbe89a949046f9fb24da862dde4e4b7caa0607b03803f2ae7ad5160e56bddf91d54293c647992b2f33f3b3c07f01fc67f15b587b0f0968771ab488d89a75c24100ffa69237ca98f739afaafc01ff04ca324224f1af8da2b799577c965a1c62409c77998638ef85afa4345f19d8f876d9747f0e4d059594195b7d32c6dd638d5876c0e4fa673938c9ae0fc7ff157c43e1bd46d91422eb1a930885ac72852cc013cf6c01cb76c71d6b1a98cc6556a10f77fafeb63ae9e130745734fdefebb7f99abe06fd8a7c11f07f5e8756b4d3c788ae5155eda7d527dcd0371f3818d848ea09e87b57a0f8a081134cc043f3f96f25e33a871ed22e4038ee7f3ac0f07fc51f114b6db757d52ca68dc888cd6e09457c7f16e03a60f02bbe913529111efbec779632aeec18f8424023f03eff857cee2615653bd6777f79f41869528c39682b2f4b1e7f2e836d7760123692d99d8159101613b1cfde6c727a73535bc52496db24b69d2488078e36605245c7214e7ef02383ea315d859f85234d4d750b0650b33012dbb72a72319f4078eb8ab53f873fb36499951e5b7380db4f29ea31dd724f19cf4af364dec7a50827b9c969da5c71436e65f2fec97040990f4f3390ae4741cf5208e7d2a978abc051f8934cbd81adfcbb8452d1cac73b70720103af7fc8f7ebe896d6ca63335bb2ccdb83c8abfc63a648fe63f9d69ad843b0384ddbbb30c11c60e477e39c8e78e6baa854945de2f530ad46325691f07f8ff00c0173a235dc461516a63f3212cbb9429030481c80ac0061d86d2338c579a0d311eec09205f99c9040e4a9e08620f2572467bf19afd05f1b785b4fd7ace4b831e48c97c2e79036c80823b03923f11d6be32f19e8f0785b55162ead018c8564d9b91f27e565273f2baedc107a8f7e3ecf2fc7fb75c92dcf8bcc301ec1f34763c9f53d0f0ec8ca1e523cb660461813c36de9db903bd1636c9716e4b1315cae048bc6d90741ce79fff005e09aeca38a3bf79a39986e8e50a31dd09c85e73ea71ce41045729ad68b2c5770cb66886e77105b185986791b4f43c678ebd7ad7d01e0688cd8f4d5b52e4c31a988e488b2411fdecfa63dab7ac5daca2592224db6558a29ce06319c77f4fa679e98892e5c45bcab2bc792f1481b72e7ebfcc1fcfa55ed398c513bdbdaa4f038cb44a4f1ebc639ebd71fad21a11ac5a4999433426401936825770f4fc31c7ff00aaba5f0ddd9804ab2c0a929531bb797b4838eb8e3fc6b99fed0896e3cb82dd80db805e26f941c738e73db91fe35b36b7522ce2466d9142598e5305b8ec0f2a071f874aca48da2d1ceeb9771dcdccbbb0992338cf3e99ce47e231552da5c8044b238191b95b38fc2aaeb9aafda6e6569115376481b001c9ec7a9e7df9aa81d74fd3cea17aaf0db07f2d19949dcd8c80a4fa8ed56e4a2b539dee74905e3a405e495b6afb93fa1ae97e0df8af59d2be23d84ba3c0f73348db4dbc6bf34ab9e78cd785def88eeb50ba68edd0ca84feef39240edd2be9bfd8b3c2faa6a9e390f730b41e4813c12e70eb8ea02f439191cd6755a70698e366f43ecdd6be1f5d7c4af064b6d73a3ced05e438689d436323a75ea2be06f197ec83f15bc27e2ab8169e09d7f56d2642df64d42c6cda6575032436dc956033d40cf6ce6bf52a5bfb9d12eed9d434b6b2b224916cf9941e3771d7b7f915e8fa26a92408f140a170bf74e7739e9c67eb8af2a847d949c1bd1893e47eeec7e13687e22d57c07e288a68e168c441e29e0753891790c8eaddc1c820f3f4ae8f5cb1d3b58b3b7d5f4d9d74b8ee98910c529288c0f23d579e9dabdfbfe0a4fe1c82cfe34699ac69290bdcea3a606d5ed2355531c88fb1256c1e59d783dcec06bc17e1c78812d5dad56ce3ba973b7c82bb9b27fd93d2b5694e57ebdd7ea77c27cb1b6ebb3fd06afc41d4747916e61d5ef1ef41026579773b81c02a47503d2bd4be1978ba3f897a84abaadc6d5b38b735d5c9c2c6ac700127b938ad0f06fc123f122d2eb576bfb0b0b912b5824135bb3f96c477dbd0f35cfea3f0eaff00e116af73e10d7859caf35b45711df59484a5d2827e63b865483d8d63898b745c5b7d0df0b512c445a48d8f11f8eadade1389b7f2b2798bc8771b9703924e43631cf38c52cbe2dbb1a5daa4769341e5380f1c9f318d892acbc1e7185e7d6bd6b59fd90bc6d776163e20d725b6b2f116972acd69ab5b69d2a196239dcb72fb615f3564f2da3902a9550c8410571dcf867e0bf8aec2fa288e97a0f8ce1925f2ee757b1bd6b59e270ecaed2412a02003cb1524b15040c9358d4c3519b492b3eda9a4717569eeee99f1cf8cbc6b742330dcc51da4ac15f2fcfccc79c8feee02fe46b90f03f8635bf16ddea37da3b41fd9ba75c79d159bc9b5ae5f014b479fbd801723fddeb5f63fc4efd86a4f15f8a9f52bff00143430c102446dad74b99e163197243c8194a212dfc28c4e082476dff07fec65e10f87504d047aff00889af6e60f26f2c2ca7825b56463b810248c3a3649c0dcd8c950c6b7a3461454a37dcc6b622759a95b63cabe047c0ff1141e324f18f89afc5b7872c552e238e78fe692e8366389a36cab9859b7b73805403ce31f4b689e22d2b48d4f50892e52d7c2f3c68753c119fb4e14adced3f725cae40c65ba313c620d626f00f8625d36cfc56fa86a36b6812d6d23b8d46195554200125455475755e70c4e48dd92c055ad4174ff0014cc748d3ae73a3e8b19b68f48b99d4db5c2cb1a7952aca30f1a852cc5cee193b4e4935acab52a5051464a955af3bb31bc4de3fbff00096bf2deea8ab3690eac969756774eb34e8e0f972ac8c76792e377a6092a4a9041e4b5dd74f8baeed2ced2cefe3d5a485a6bebc9d5962464550ac8ccb8719d8bc6001cfcd822bdc63f85fa35bd8e9967736d6dad69da7dc4777a7db4855374a1c385651c15ddf39041059508048c572d756de0db5f15cf1ea1a18b1d4b5385c5c25d46f1fdb562f9cca4eede542904838072c3e61926278f6a3f09a472f4dbb3392f893e291e14d1f41b0b5773e65b00b990310300373c0393c64000e3a0ad5d535cbad53e1a5a69f6131b59ae2d5a496e1083b4725f9e99238c8f5f6af99aefc4dac7c74f8bb1d968d6096368a8b02c84b95445277cb2bb6061540c2a803903d6bd33e25fc49b6b0b097c35e19b79b50bbb3c5a988a111ed5c6407e9b8839c579388849d58c61abddfdff00a1ee61aa4634a529e915a2fbbfa671ff000b906b9e2449983490da8dc1227f9d9db239383803a9358be23315df8b35dd674f9622964eb651c9bf31a7cc0ba81dc951927bd1f0dfc53a8417fa95d6a963f61b54c46562511ef238c103185e98ee4f5ae6e29bc3f6d6ba4e8b71a8cf64d7b24d7b3dc44448cf33b1da1b3fc2a38c0ae894d57c75a4f45a7eace58c7d8e0af1df7fd11ee1f0c7c510eb9733e9ef144a246595e7563957fba4ff00857a9fc4ad6ae7c21f0d7547b480c9a95b44a2da399be466620063df0339fc3deb8cf03787749b7f0d69f791011dc3c2a8238c601edb8fab1c64fe15e55fb567c40d56ebfe119d0609654b7bb8da5bd6b7621d8a3285553f5e71ef5e65471c4d7f674d5933d582961b0fed26eed1e87f0abe34dc68d6a96fe21926d62ef399ae61b711a647f71072b8c818ea7af6afa5749bed3bc4ba48bbb1db3c72aeef46c8e7041e87d8d7c57f0cbc217736932cf349709756722318e68cabc641dc09079ce0f5afab7c01aadb3aa430ed89892ae8bc176c7ffac54622851a153d9c77ea6b86c456ad4fda4b6e85dd5b487b09d24858792c42f96a7691f4ed9f6a7dcffc792ca308c9b5d801ce3a671d88ff00d96ac789ee565d29f322baa328c1fae066b8bbaf11269f14a5a5d88b8c8079c6791ee707ff001daf21c542565b1ec29ca714e452f1a6b7058dc1dce0974f31b636d7424ed2ca7a7dec71f9d7c8bf14bc4315f3dcc334b0caaad9899be568c1cee5183caeee40fe1cf18e45757fb477c4596df55fb2e9e448ef1330ea40de764807b10030f4ea2be60336a12831ddb379992c778c853939fc0f7fad7d5e5984697b693b5cf8fccf19ccfd9456c7576575f688279f1b274215e238292ae0640f41d0fd6b3f5bba864f3009010c43b4121f9fae4153d1b9e7d739a14cba6e9923089e23e58c30e4af1c123b8ed9f4f5ae2ee56ea6b959a7955c1eae10051cf5fad7d4a3e5ee74975acc70f967f797386386076633c64923bd5ab3d5d6e30f146e083f33f9b9c63b8c0cfe22b0ad1893e5dd2f9fb8ee0a473ec456c2e830c36d1dc41e5bc44f1213b720f6c75ea08f6c5513734124b8bb9923b67f35e77558d371cb127001dd5e8de08d1edf5399f492915ddedd32244e65f2a343b4860559721812bc8c0183d73c796dc5dcda64715c5bafdce7230c38e091d41fa1fcabd83f67cd37c57a878a348bfb3f0f6a92c7348611716b66ec6e001ca2ee5c12002303238f5ac64edb9ac196ae3f631f1f49a943717fa6476f087f30324cb246e3ef0c302c30403f5ed5da5cfec91e22f1478775bd0af2de1b5b0bf293a27cc3c8b9439478f1918c6411df35f65697a36a9e2bf0d9d28693ade8b1c3f37d9b57b40d6f9049c6e4c329c96014ae7047183ceae8fe1fbcd0b4686d6e74996de0c87761231490701954ae0a8c11c638c91eb53ec6155a9f334d7f5d4c3da4a0da6ae7e6b782bf631f1a7827c75661b4cfed4b77670482a9c2e0ef5c9c1c8c8c6739afb73c23f0b74bd32de475f00a5b3bff00ae325a4cae14f2307767f235e87e3fd2ae35cf04a477fa5e8fa46a3e7002d7582678826e0aaeaf8dd1b30cfcd904771deb99f00f8f7c67e10d7ae74ed734dd4f49d3d6493c9b7d4916536d6f1100badd2604f1e19587ca5f0719241358ca97b46d396c6bed793ecee617c5cd6ac6d347d22df4dbfbcb1ba867fdf5b34a4bed0b90487e473df3cfbd747f0abc69737b67e4c974d2f96368948f9b07b1c93fcebaaf1d7c3fb4d62de4d5b51d3f44b99106e6bfba8e692489000518f9646f1ce3070707a6697c19f0a16deccdd6853d80d3a57ded6f6f75e7283dd77ba6e0383804f15e754c2d7954ba921fb44d599e4df18ff66fd2bc63af5a78e965924d7348985ea42e431b9db92118f7e7a13d08acaf19f8296e9ecf54f17f846d2dd6e22f27fe125d3c432c96a48180d2c7dc71f2b8c13debe99d274fbc818a5cc31c3324b24615db70214f18e3e6c8c647bd705a57c2ff0011f857c7c2e34310dd68da8864bdd36e240d6eca73862b8caf3c3601078ae9a4dd3b45c49e9b9e03f087e1178d7e1a5bebd75a3dd5878afc33acde2dd09a16f2e53b7e5dfb0e7048e383d4579a7ed23a7ea7e32b7812c345d4df52b39094864b36370109c30381c8ef9afbdd3e17e9de1e9a7bab3b0bbd26694612db4d769acd1b6f554c803fe04075aa57bf0ee3d6614336ada95b488a118ac71ee1df27839fa7a1aea9d36d5ba0e13b6ab73e65d63fe0a37e1ff12a7fc23f79e0fd62d24bd50515b5382d1ba864c895402871824fca41390457b67c14bfd06cfc32fafb456ba0e8f732991749b29a2d4220c400d335c41bb7a9618541f2803dc8af5bd22d8f8885b6a9e23f0d2daeabe4796b6f766dee7c952dca875ce7b13ce3dabadb7b0b0d1221f65b4b7b348d02830c0a9850380028e001d856ce3795da1dd25648cfd1e13a8586eb46458948c129b57a67006322bc97e2d687613dfcd27897c2377ac69c8a54dc0888b511e777ef0237ce00ce4951df18af53b4f1a8bab911ac06285f85c373cf4cfa55b66bfbab6903304b77f97321ddc74e40eb9a7eec97733f7a2fb1f2f681e01f861e37582f65f028d6749590da4773358cb32039e238e770ce578c6d27600318af58b1f873e0ed2e0b68748f01d969db487824bad3d1a280a907773f305caae40233d8035dfdba2e8767e543e5c2ea0c71b4518509df000e00c9ed5c66bde3d8b4832596ae5632922ac92a06977b13f29da46369efdc5633f66b78abfa1b45d57a293b7a9c827837c40f72b7507867c3da2e9f146d17da46b32bcf6e093bde10032aa1c2b0c6d61bb185dbcc971e17d0747be4bbbcd2cdfba44a86f7509c969c28380541e5724e3713c9cf5358de2ef12476777235d5ccff00d953cb1a3b464a82ac40c80bcf0083ee45737e32d0b54f0c58c779a4f8cb57fb2a9333dbdd4114be6a2925cc7938538e36b0fc6b894a32ba847f2fcb43a2f2ba7297e67c99e35d735af84de33f11c4f6333ea17825fb1df31f9040e772941dcf419ec05733078a9759b686eaddc326e01d178646ee1bdf3939af6df88563e3ef17594769ad43a3c4972c5ad63987ef4a15dc0968f2012bd467b115f2e78abe1f7893c0fa68d5efd3ecb25c83228b5910a2479f9377cdf364724638e07ad4c60e1539d7536954e7a7c8fa7dc59d5bc4d73a7c975a68b867b4ba7f309eeadd1b3dcfd6a9aeaba7ebfe278638eda37d374b0155f6e0bc81718cfeb5c05a78e1b57bf8833460a063bc2103207a62b32c3e20d9f87aeeead5a6697f7ad26e5561f78e79e3eb531c32f6fcfcbaee5bc44bd8f25fc8fb33c21f126e6736f34f30548d4a7964f118c7cbb7d87535cb7c7cd7adb56f0f69d7fa60097f6773e6a46ca4f04fcc011ee338f7af07d1be357db24d9636af34aab8deefb540fa534789754be94c9a8cc275bcba5923b7886150f0028cfaf1d6b9ea60e0aaa9d3567d8e9a58d9ba6e9d4d53ea7d7ff067c57ae5f431dff89a4926babc304493901965400f2e7df23f05af6ad2e28ed6f5353b690c76cb217742780a33823d457cb5ad787fc5df08bc51a1689e224fecdd5753883dbdb24a92ab838dbf32120633ce71ed9afa7be12785c78cfc3c1355bd92ded016892080025981c14c9078c82dd2b1c560a55e5ed293bdff00adcdf098f8d08fb3aaad6d8e67e257c46302358472a4171297708cf861b46417cfddef806bcc75df88c2ea2f2a29f74ae8ef28404ed55cf391dcf07f0afb6741d0ed34fd42fac743b3f0d99a4b6d865bed3a4691240320380712a6d249f994e48aa117ecfb75ab6969a6dcbf87468b2cff00689ec62d2cb45725860945de86060a760c3302b8c9c8a50c9b54dc8b9e75ba51b1f9b77ba6ea9e3cf19cb6d6b672dc46acb0a1f25817627958cf7c97fc8d7d0f71fb18e91e31d19346b83aae95ac5b249e56a96eabf2c83385c93b48e394639031c835f57f82ff00654f057807588754d3bc2fa5d8ea36b1916f35a195b6123049496475e3800f24724115abe21b2b2b2bbd3ae3551716b1cc5615b3b5d8d04af8270cbc77727391d31cd7b90c3469ab1f3f5312ea3b9f06f857f60df107847c4b169de3cd7e3d47c2c8c92b49a45ced94863909861ba256c10d838ce369ce48fa724fd817e09f8a74db6baff846e4d2ad3ca0c25d2f53b942ca4966f31d9d849d7018fdd038e057d0379e10d13c6ba58b7d42c2daefc842a1d15a175f41b94823247a91593ac7812d74ab2b0b78b4fd27fb11f70bbb6bbf39d11b18f9231f2919c8e471dbad75463289cce699f319ff008269fc28b3d584b1ea7ae49a5091665864d677ee5c02503050402bc96e7838eb835d0fc44fd8a3e086b9616ffd93770f802fc8f2e3bad3a72d15e2823779a92b30723a7980ab027927a57d112d94c2c6d62b24d3edee5485b455b62a88887e55003631838edf4f4e69fc192f89bc4b697da968b652c96c5e60f2ca1b6c8e02be62dbb1bee2609c9ea7835a2645cf11f1bfeca3f0be4f1635edc691a9d9457d0430c26c2fd12c1275423700b1ec2e020055b0182e71926bb0f0af83bc3d269ada34377ab5afd995da1bcd436ccb1072033c2030d836e502e70a1b81835eeb77a44b656a6e1d229b83bc44814b8ee0e7835c56a27c33711dddd5c6831c532c00c9245f236c0e4ff0e07527df93cf35cd52367be9d8b8c93d6c6fdaeb1a7699a1ae99ab4c2ed6d22d8d744b451b63385dd9e1b1b7001fc6b1ad353d6ae2e01b77bcd2f4bb9b48a7b799d7edb2c63a112bb168d5b1821533d3e6639e28e8dadf87638e36bdd3eead23b6b978043705668c9da0a4a4024b00b8e09cf19393927bbd67c4abe19d13fb764b77bbb08d55e59607c3469fdf0ac4640f41f37d7a56aa70496bb0941b76b6e71d77a0f8cd14f97e21b1beb5605d278adfc895bb7cc4074e7a1e00fa639e73c4ff001d74df0bda5b69f7b74f6bae2ca6dddadada57560a325a44460c016c8539192081c735dff83be20f86fc63a6e99af68b3f976baac5e62b490c91b3203fdd1c03f36738e73ce6aac096f7135e598b46b1b9d5e33e618266c9246d0e1b20a9c7b8c1e456bcda5d321c6cf964bf438a3f183c3d74d0db6a3733de5ce1657966b18d63ce0fca43be72bb0a9ce587735b1a3fed01e0096f56d2d757b085b66c952265d9129384c9202301c93f370067a56f69fe11b3bdd2ee747b893ce8249024d15c42b26f1ce7797dfe61ebc9ebdeb4e0f01f853c296cec747b3f2a3519f32d63202fa00abea7dbad4b6dea86a31dae65789fc69a2d8acb72e1a45310906a3008dede10728ace7233f38db8fbd9c819156742f10dc369bbe48c484e73244a4027b9524703fcf6a967d20cda6ddd85b684b342ede6c4934ea22395047c87701e8460034369be20b7b9136a06d638641931dba863c6077238fd6b9aa29a7cc96be46ca30e5b737de25e5c5fdddbdd259c972ceb0798b3249e5a039f9543f01bdc1c11938c8ac89356ba7d2a5bbd1752b1d5f54c2c5f3cd1c8218c39c178d5d72d8f973907919e95dddad95bcb0209104ea7e65dc3183f4a48b42d3c6f53a7dbc258e4b431aa16fa9039fa5745e538d96e609453f78fffd9);
INSERT INTO `pet` (`pet_id`, `owner_id`, `name`, `type`, `breed`, `gender`, `age`, `color`, `b_day`, `b_month`, `b_year`, `profile_image`) VALUES
(4, 7, 'BABOY', 'DOG', 'ASKAL', 'MALE', 2, 'TAN BRINDLE', '27', 'OCT', '2015', 0xffd8ffe000104a46494600010100000100010000ffe1006845786966000049492a000800000003001201030001000000010000003101020010000000320000006987040001000000420000000000000053686f7477656c6c20302e31382e3000020002a00900010000000003000003a00900010000000003000000000000ffe109f4687474703a2f2f6e732e61646f62652e636f6d2f7861702f312e302f003c3f787061636b657420626567696e3d22efbbbf222069643d2257354d304d7043656869487a7265537a4e54637a6b633964223f3e203c783a786d706d65746120786d6c6e733a783d2261646f62653a6e733a6d6574612f2220783a786d70746b3d22584d5020436f726520342e342e302d4578697632223e203c7264663a52444620786d6c6e733a7264663d22687474703a2f2f7777772e77332e6f72672f313939392f30322f32322d7264662d73796e7461782d6e7323223e203c7264663a4465736372697074696f6e207264663a61626f75743d222220786d6c6e733a657869663d22687474703a2f2f6e732e61646f62652e636f6d2f657869662f312e302f2220786d6c6e733a746966663d22687474703a2f2f6e732e61646f62652e636f6d2f746966662f312e302f2220657869663a506978656c5844696d656e73696f6e3d223736382220657869663a506978656c5944696d656e73696f6e3d223736382220746966663a496d61676557696474683d223736382220746966663a496d6167654865696768743d223736382220746966663a4f7269656e746174696f6e3d2231222f3e203c2f7264663a5244463e203c2f783a786d706d6574613e2020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020203c3f787061636b657420656e643d2277223f3effdb0043000302020302020303030304030304050805050404050a070706080c0a0c0c0b0a0b0b0d0e12100d0e110e0b0b1016101113141515150c0f171816141812141514ffdb00430103040405040509050509140d0b0d1414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414ffc00011080300030003012200021101031101ffc4001f0000010501010101010100000000000000000102030405060708090a0bffc400b5100002010303020403050504040000017d01020300041105122131410613516107227114328191a1082342b1c11552d1f02433627282090a161718191a25262728292a3435363738393a434445464748494a535455565758595a636465666768696a737475767778797a838485868788898a92939495969798999aa2a3a4a5a6a7a8a9aab2b3b4b5b6b7b8b9bac2c3c4c5c6c7c8c9cad2d3d4d5d6d7d8d9dae1e2e3e4e5e6e7e8e9eaf1f2f3f4f5f6f7f8f9faffc4001f0100030101010101010101010000000000000102030405060708090a0bffc400b51100020102040403040705040400010277000102031104052131061241510761711322328108144291a1b1c109233352f0156272d10a162434e125f11718191a262728292a35363738393a434445464748494a535455565758595a636465666768696a737475767778797a82838485868788898a92939495969798999aa2a3a4a5a6a7a8a9aab2b3b4b5b6b7b8b9bac2c3c4c5c6c7c8c9cad2d3d4d5d6d7d8d9dae2e3e4e5e6e7e8e9eaf2f3f4f5f6f7f8f9faffda000c03010002110311003f00f37a29de5fd296bdc47e541454b4be5fd69810d4dfeb292a5a4c08aa5a28a1005153514c028a2ac5001453a8a006d3a8a2800a28a282241454d45520890d1454d4cb21a28a2b481121b453a8ad0823f2ea1ab5450055ab14ea6d5c40af4558a2a80af4558a2800aaf5628a00af5628a2802bd15628a00af451562802bd15628a00af4558a2802bd15628a99015e8a2ac54015e8ab1450057a2ac557a0028a9bcba86800a86ae79750d0043454d50d0014514500455155aa8aae20454cab150f97f4aa01b453e994015e8ab1455c40a550d5ba8aac086a1f2fe953514015e8a7d32aa2057a2ac5156052a2a5a8a8022a2a5a8a8021a2a6a86801b453a8a00ab4558aaf512021a2a6ff96350d48054552d1401154552d14011532ac545401ea1454b5157908d8968a28a604b45153500145145001451b2ac50014ea28a0890514514101535145001451e5fd6a5a008a8a968aa45c48a8a968a61222a2a5a8ab4804428a28ad024150d5ba8aae2410d1535154043454d50d001454d50d00145145001454d50d0014da751400da29d4500368a7514005369d454c8028a28a800a28a2800a28a2800a3cbfa514505c428a28a0b0a6d3a8a006d57ab54da08915e8ab155e8202a1a9a8ab8810d145154055a96a5a8a8022a8e7ff47a928a006557ab1455c40af5079757ae2a0ab02a51525c5474011547e5d59a8aaa2032abff00b156e994480a5454b51511022a86add454480868a9a8a9021a6d3a8a006d14514015e6a86a6a28021a28a28022a8aa5a2a2407a8514515e5a2e44b45153530890d4d45141614558a6f97400ea751450014514504489a8a2a5a0822a968a2800a28a282e21451450585152d154889115152d14c822a2a5a2b4801151454b5a0115152d4540054552d1571022a2a5a2890115152d1504c88a8a968a02245454b4505115152d4540051451400514514005152d4540051451400514514005145159cc028a28accb890d4d451e5fd682c868a9aa1a0028a28ad200368a7515a1122ad1562abd0410d15354341710a8aa5a2ae21222a8aa5a59ea82256aaf56e994048af5154b450411543535157102a5153797f5a86ac06557ab1450057a8aa5a2802ad152d4540054353514010f97f4a6d3a8f2fe9400daaf56a9b4015ea1abd55e8021a2a6a86803d428a28af211b13514514c0b14514ea0028a28a002a6a28a0028a2a5a0890514514104b451450014514505c428a28a02414558aaf54880a28ab14c0af4558a2b48015e8a28ad0028a28a002a2a968a0028ab155e800a28a2800a8aa5a28022a2a5a28022a2a5d9450045454b456732e245454b45665915152d140115152d140115152d140115152d140115152d1498115152d1e5bf93be84055a2ad6ca87cba6047454b51500150d4d4500434da7515a4006d57ab54dad00af50d4d45004351cf5251571022a8aa5a8aa8890caaf5628a082bd4552d157102a5153543560454cab15150032abd5baa9350045454b5150045454b51500150d4d4500434da751400daaf5628a00af50d4d45007a554d4515e43362c514514807514514013514514d0054b51514c0968a28a08912d14514101451450014558a2800a28a2800a29f4caa401453e8a60328a7d1400ca29f4500328a7d15a4006514fa2b4019451450014514fa006557ab14face45c4651453eb32c6525bd3fcbfa568e95e1fb9d63cff00b3c3e66ca0a89994574fa07c33d6fc59e7a69f0fce95af69f06fc43f639ef25d366f92a5cac6f1a52a9b1c0d3bec6fe4fda7fe5d7fe7a57b0f813e09dcf8f341bafdcf96e9f72bd27c07f01ee6ce6d4744d6e1f32cbefc325652ab637860eacf63e669fc2f79fb87ff009f9a82eb4bb9d2e69d25fbe95f7669df012c06822da5fe0f9d2a1d47f677d1f54d4a7bc97f8eb2788b1dd0cb6a496a7c2d1e8f79713793159d3f4dd3fed90dd3ff0073fe59d7e88e81f08fc3de1bf3bc9b3fbf5c26a3fb2fe95ff0927f69597eed1e6def1d2fac94f2da90d8f873cba7c166f715f735d7ecbfa27dfb7ff9edbeb919ff00667f33c61abf95fbbb5787e4a6ab7318cf03591f227974b5efd3fecdfaadc7892d6ce287fd16dbefc950687fb37eb1ac78927fdcf976a9355739ceb0753ac6e7854167f689b64553ff0061dcf9db3c9afac74afd9afecfaf7ef7fe3d6da1ff00c7ebbfff00867fd1f50b383cd87cb93f8ea5d6e537a797cea3dac7c47a77c3fd5750f9fc9ff42fe37adbf13f80dfed96b676f0fc95f7d41f0ef47b3b386da2b3fdda551baf871a7e2692187fd2685594b73a6596ce28fceed7347fb1cdf66fee5627fb15f5a78e3f677bfbcb3babc8bfe3eae6b97baf80efe0bd077f93f6bd5dff00f1cad6328bd8f3ea61254f73e7dfb1bdbffaeaabb2bd6b52f86f79a5e8ff00bdfe3fbf25715aae9f6da3ff00aafde5d7f1bd6872ca1c8729e5d475a3a8efb7aa7548c644545145324869b5667a82b4800daaf562abd680150d4db2a1ab88054552d45544488a99562a2a081955eac557ab88054552d45560434514500454ca7d32800aaf5628a00a5454b5150045454b450054a2add45512021a6d3a8a801b55ead536803d228a2ac579e6c14ea6d3a800a9aa1a9a9300a28a2840152d1453025a28a29300a28a28444828a2ac532028a28a0029f451548028a28a60145145001454b45004545152d0045454b450045454b4505c48a8a968a0b22a2a6f2feb4ff2ff007db282e256a9bec6fef5b7a7785ef2f2f3ec7fc7fc15dfe87f05fc437179025ed9ff00a2bffcb7a8955f668da142559e8797e9da3bea937d9a2fbf5a9ff0adfc496737ef74d9b67fcf4afb23e1efecef61e1f8607beff4bba4fb9257aec1a3d9f93b3c9ae19e24f7a9654e6b53e0cf0ffc17f124936fb7b3fb5a3fdfaf75f825f0afcbff0049bdd37ec17d6dff008fd7d131d9a47f72a7ae79d6e647a586cb234dbb9c4687f0fecfc39abcf7965fc75d63e9f6d243b3c9f92add377572f35cf5e3878c74467e9ba3d9e9ff00f1ef0f9757e933ed525334e4500a29945218fa29945003e8a6514c07d328a2800a7d328a4014514500279759da8e9ff6c8767fdf75a545529f219ce9c67b9e27e31f84f73e209ffd7797a5db7dff00f6ebcbf52fd9eeff00c617bff409d12dbfefb7afaee78fed147975ac2bd8f36ae5f0a9a9f9fde3cf84f79a5de7eeacfcbb54fb95e73a94691ffaafde57e85f8c7e19a78b26fdecd5c9c1fb3e68d6179f6cfb1fc90ffa982bba35b9cf171196ce1aa3e20ff844df4bb3fb4eabfe89ff004c2b2e7bcff9f787e4afa67e287ecf7aaea937f695c7dcaf25ff008567aade433fd9f4dfb25aa7fcb4ade32b9e3d5a52a76b9e5f50d749ff00087dfdc7faa87e4fe39eb3f52d3fec75d34ce591953d2558f2fed150d6c41568ab1450051a2a6a86ae20455154b455015e8a7d32802bd14515712244550d4d45590435154d3d43400ca28a2802bd145140054552d4540054552d45400b3d254b5154480868a28a803d1aac557ab15e732e414ea28a110153543535300a968a2800a28a96800a28a2800a2ac5140051453e80194fa28aa401451453025a28a2800a28a2800a28a2800a28a2800a2a6a282e2434549b1ebd0fc1da3d878b3fd1a5ff00447a4e56358ef6394d2b474f3b7cbff1eb5ec9ff000ccfff0009269b06aba55e7c8f5d5f81ff0067bb6b8ff5bfeaff00b95ef7e07f01db782ecfecd6537fa2ff0072b92b56e5b1ef61703edf53e69d2bf64bbf8e6fb4cb37989fdcaf57d03f66bd1f4b9b7cbfbcaf6ec52d70cebdcfa0a796d286a8e674df01e8f67ff2e70d745f62b6ff009e22a4a2b073e73d18528d3d1051451526814ca2abd005ba6557ab14005145140051451400514514005145140051451400514514015eac557ab14005145140051451400fa651450057bbb34bcf925ac4d47c0f61790fd9bc9f92ba6a419aa52b194e8c6aee788f8e3e19a5e69bb2cacfcb44fb8907f1d7cf5e2ef81fff0008dd9ff696b7ff0000812bef0f2eb93f16f81ecfc5b0ec96ba6955b1e462b0316ae8fcddd72f3ed1e7a450f968959775a7bc7f257d21f10be07ffc237a97fa143f6b7fe0af2bbbf09dfdbffcb9fdaeebf8dff82bd3a756e7cc55c3b8c8f33a8fcbaf50ff008576fa1d9ff6aeb7fc7f723ae7a0f03eb7e2cd4b65bd9f975af35cc7d958e43fd6549fd9ef710eff00e0af6ab4f817fd8ff3defdcfee570fe38fee45fc15a40ce50383d950d755a6f84ee6e21fb4cd597aae9e96ff00eaab7898ca3639fa6558a8aa891955ea7f2e96802bd4552d454010d153543571224454ca7d15640caaf562abd001451450045454b515448028a28a8022f2feb454b5154c80f40a9bfd5d3a8ae101d4514500153543535004b45145260152d14508028a28a6058a28a2800a7d1455200a968a29805145140051454d4010d4d454b417122a2a5f2ffe7ad4f3e9efff0000ff009e941653a2ae5de8f73a7fcf2fef2d7fe7a56f68167a56a936c961f2ddea5cac0a327b1cd4f1bdbffada9f4dfb4c736fb787ccff00a675ec9a57ecff0079ac43fe8537fa2ffcf392baef077ece1e20b79bfd23c9b4d9fea64fe3ace55b90eea5879d46792f846cd2f2f2d7cd876595e7fe38f5f41687f01d2cef20f366ff00724af4dd2be13e891d9ffa6e9b0ef7fbf5d95a69e9670ecfe0ae2ad5b9ec7d150cb2f6948ced0f4bfecbb3d95b94515e7f3731f4308c60b9505145141a05329f4ca0028aaf450015154b4544c08aa5a8aa5acc02ac552a961ab801628aaf562b402bd1451401628a2abd0058a28aaf401628aaf562800a28a2800aaf5628a0028a28a0028aaf450058a28a2801f4ca28a00af3e9f6d71ff2c6b12efc1f6179ff002c61ae9a9954a56339528d4dce0aefe11e95aa6a5f6cb8fde7f712b534af01e95a3ffc7bc3f3ff007ebaca656b19984f09068f16f89be13b9bcff8f7fb95e4b3fc07fdf7da753ffbf15f5df969715c878e3c3ff6cb3fdd7f1d75d2adc973cbaf8381f0efc4dbcf2e6fecad3eb979fc0f73fd8ff6996bea3b4fd9fee750d4bed371557e237c33bcb3d1fecda7d9d75c6bf39e3d5c34a1b9f1a6a567f63f92b3bcbfb457aeea5f0cdfc3ff00f1fbfbc7ae7b55d3edadeceb5533cf74ac701f63fa51f63fa56d4f67556efb56b195cca51b18d55ead797f67a65c56f130914ea1ab7515512435154b450448af55eac5141057a28a2800a8aa5a8a800a28a2a64015154b4541713bbab14515c84053a9b4ea009a8a28a002a5a28a00968a28a0028a2ac50014514fa002a5a8aa5a0028a28a009a8a6dbd3a82e24bb2882cfed156b4dff4c9b6577969f09df50f9e29a8e6b1bc2129bd0e0e0f0bdfde7faaaeb34af87f7faa7fa34b67f257a4f863e0df89f4b9a078a686eed6be8ff07784fec7ff001f1675cd5ab72d8f5f0f81956bdfa1f1d7fc29bf13e8f37fc837edf655d67863e0ddff00fc7cd97ef2d7fe5b69d3ff00ac4ff72becb82cd2de1a7fd8edbfe78fe95c8f127ab0ca6e7ccd07c07d56ce6fdd43fe82ff007e0ae853f66bd1350ff5b0fd81ff00bf057bf640ed4eaca75b98ec8e590a7f11c7f837c009e13b3d9e77995d8514564e573d1a54a14d7ba145149e67d2a4e8168a651400fa6514500155eac557a0028a8aa5a0028a28a00278ea2abb55eb39805151514400bb4557a2b402c557a28a002ac557a2800a2ac514005155e8a002ac552a96802c5155eac50014557a2802c557a28a002ac557a2802c557a2ac50014557a2802dd14ca2801f51f9752514011f975467d3d2e3fd6d695154a5626518cb4679f78c7e15e95e24b3fb3793ff03af1fd57f66bd2b4bb3df2fef2bea1acdd4acfed90ecad613d4e0ab848491f0b7883e19db5bcdbfec75e4be23b3fdf6c8acebf407c4ff09d35087fd1e6f2ebe7df1c7c27b9f0fc3be2fde5774267ced6c3ca9bd4f96eef4ff2ff00d6d67cf5daf8bacde3ff005b0d7073ff00a3cd5d54e679356362ad4552d15af39811553ff57572a1ad232b91222a653e995648557ab155e800a28a28022a2a5a8ab39805145159303bba28ab1526214ea6d1400ea9aa1a9a800a96a2a968025a28a2800a28a282e258a7d14505854b5154b498055bfb1bd106cb8ad4ff0049d2fe7f27cc4ace4544cbab569a7fdb26aed60f01ff00c2410c1a9699fbcaf4df027c0fb9d53fe3f61f92b19cac8eba342555e879e7867c2761a85e41e6fee1ff0082bea0f865e0f7b387f7b0c32256de87f04fc31a7887fe25bf3a577f6b669670ec8ab86733e9f0d8070d5956d747b3b7ff009635a5484d2d64e573e8143912414514548c28a28a0028a65153200a2abd1444028a8a8aa025a2a0ff00574eace604b45413c74799ff003d6b3027a28a8a801be654f5567a3ccfad17025a2a2f33eb49401354345433d005ca96aa515a4009aa5aab1ffa3d4b5a005145434016ea2a2a1ace604d51799f5a86a3f32b9e72b0d16bccfad2557a74125473944d53550824ab35a42572596e8a8a8ade0225a28aabfeb2b401fe654750cffebb64553ffaba00968a8bccfb3d1f6cfa5003ee28f32aaffd75acf9f58fdf6c8ab3981d0efab15ce7f6c256a41795080d2a28a2b5880514515401451450054d95cd789fc269ac59fd9abb0a2a94ac673a51a9a33e54d57f66fbcfb64f737d37fa2d79cf883c3fa5786ffd1a5d36bedfba8fccf92bcdbc63f0cecf54f3e6b8ae9a533c4c660d46d63e16d7f50d2a39bfd4d713a95e598afa27e21fc3348e6ff47b3af1ed47c1f7367ff2e75e8465747cf56a1c8d1e7b751ffcb68aa8dc5745a959dcff00cf1ae76e2b44714a3623a8aa6f2feb50d5a206514fa65322457a2ac557a4c90a28a2a180543e5d4d4549513b6a9bfd5d43562ace70a75369d40054d50d4d40054b451499710a968a2a4b0ab155eac54c8029f53411bdc55afec7b9b8ff00555948a8952a4f2ea7fecbbfff009e3bebd93e15fc0bbff187cf710fd923ff00a6f594a56378509567689e4ba569773a85e7d9ade1f32be96f857f00f55f277ea7ff001ebff3cebd83c07f05344f03fcf0fef2e7d6bd2eb9ea4f63e9f0997595e6725a07c3fd2bc3f0ecb787e4aea7ec691d494560e573dda54a34d6814514549d0145328a007d32abd1401628a2abff00abace72b013f99547ed954aef54ac8fed4fb47fcb6ae6a93d868e87ed9f4a83ccfa573706a0f27faefddc95a16923c7feaa6f328a72b8336a8aadf6ca3ccad445aff008f7ff7292a1f33e94b4016ea29e3f33e7a83ed9ff7dff72ad7994011d433c953cf5913c9e5ff00adac6acb950d134f7946fac59ef3f735a16b79fb9af1d4fdf28d1f33ed147fabaa56927d9ea6df5eaf3e8809fcca5aceff00ae549f6cfa54f392cd1824a7f99f4acfff0096b51fdb3e95b426235693ccfa555b5bcab15af35c0b14567cd46fa39ac05af33e94c9e4fb3d529ef3ecf5575293cc86b0ab3d068b5f6cfe3a8e793ecf0d67c1fea7f7b537db3cc86b879ae516bccfb4549e67d9ea8da77ab53ffa45340496f576deb9eb592b43cc7b886b6801a9e67d2a6ac9f33ec7fefd4dbeba612b12cd0a2ab799566b652b888a927ff47ff55f7ea6a77c95ac40a9e5ff00db4a92a5a87ed9f5aa027f2fed1feb6b22eecffe7de1ad0fb627b532ef50fb3c3e779de5a27df92b1a8071baade5cd9d6241e30bf8e6dffb9d95c5789fe34587893529ecf4c866bfd9ff002d20ac1fed04b7ff0090b4d0da7fd33fe3af36a68ee347d1fe1ff167f6c7fcb6aeb2093ed15f2c787fc6173f6cff0047bcfb25aa7dc8ebdc7c25ae3de43febaae8d6dd0d9dd5329f457a71dae49537d58a4f2ea0aa02c53eb3ea5a00b75527b3fb4558a7d52339ab9cfea5e07d2b54ff005b0d78cfc4df84f6167673dcd943f3d7d0d595a96869aa7fadad612b1c95b0fed11f9d5e2df0fdff009dfea6b8ad47c3fe5ffadfbf5f6b7c62f847797967e7699fbbaf913c4da1dce8f34e92d6ca67ccd7a3ec9fa9e65a947f67acaad5d464f2e6aa15b42573c9a9b9528a96a2ad4c86514fa2a64032abd58a2a00af4558a2a24075d0d58a28aed320a751454c809a8a28aca404b45152d400514515122a258a7d1535a59bff00cb2a92cb569677971feaabbcf07785ff00b626d92de7975cf687a1eaba84db22b3f3ebd83e1efc18f16eaf37fa4c3f648ffe7a56733a28d075647a5781fc1e9a5ffad87ccaf60f0ff87edacfe78aaaf837c11ff08e59ecb89bcf7aec2b167da60e87b24145145433d57b05151f99547cca422f7994b55fe5a2800df451ff005c6ac54c802abd1beaa542959816fcc4b7ae2bc41e384d3ffd559cd775a9e2df10268f675f37f8c7e385cc979f63d126f9ff008e4af2b175b9248da9c6e7a6ddf8a2f350ff00a744fe3827fbf5cf6a3ae5b68ff3cb35e7d97ff1caf249f5c7bcff004cbdd4bfdcaad69f1135bd1ef3fe7ef4bff9e73fdcae0f6bed0738d8fa2343f105b5c43fbad4a1911ffe59d76da6fd9adffe99d78df81fc59a26b9feab4dfb25d7f1ff0072bd86093cc87f75fbc4af4f0fb331669f99ff003d689ab2fe7ffed73d49049f67aeb1135aff00a3cdb289ef1f4b9a0ff9696aff00f8e549fedd1e65302eff00d3cc547fc7bc3427fa3d2d0027db2a96a5fe910efa9a7b34ff009655560fee572d7574690dce6ffe3e26ab5691bdbfc9ff007c555d4acdf4bbcdf5a96bb3c9af254353a19369579fda90ff00d344fbf56bed9f4ae7a7ff00479bed96ff00f03ad7fb67da21df157646575632910da49f679a8ff8f79ab3bed9f67a9e7bcfdcc0f59f5333520bc4aab3c9fe99b2a0ff006e2a66a3225c7cf5d1d0966a5a4953799fb9ae7ad2f2b47ed9e64d5d14ba88b5f6cfad4d07fa9ae7a093ecf37ef6b6e092b596c055ba92b2eeb54f2e6d94f9ef3fd32b127ff48d63ecd17fc0ebceabd068e8bccff893efa20a35cff4786048ab1e0bcf326f26b03681d141feb68fb67ee67ace824aab06b15a4053352d23fb456bf99f67f92b220bcfb3c3f69aa5e63dc4dbeb420bbfda1f68abb049fc1591f279d5693fd1e8ea0743feae883fe9ad55b4ff005556abba96c4b2dd2797f4a679952575c04676a579fc11547047ff004c6b47cba7ff00aba73019f63aced5743b6d6219edafbfe3d7fe79d68cff00f5dab3e7b3493fd6fef2b303cffc5de1fb3d1f4dd96f670c7ff4ced3efd7ce7ae47a5787ef37cb67e47fe3f3d7d55e27d1def219d22bcfec94fe3f23efd7cbff0011bc2f6da1f9ff00d89fbbfefc907fac7ae5ad1ba4544e6bfb613ed9f6c8beff00f0413d7abfc32f888f6779025ec33475f3d6ab1f97e43cb0cd27fb75e93e03f105b5bc3079be74963ff4dfefd7972f7248eb5b1f6fe87a87db2cf7d6cd793fc32f1659f882cf7d94d5ea1049f68af668caf139a7b96e9955f7d1bebaa26641771fd9e9904956ab12effd1e6a9981b7beac561c17956bccfad42606953eaa54f6f5ac40a377a7fdb3e4af11f8a9f01edb50ff0049b7afa02aa5dd9a5c43fbdad6272d7a31ab1d4fcd3f1e784d34bbc9edbc9af2fd474b7b7afd0bf89bfb3fa6b1e7de597dfaf92fc79f0eef347f925ad632b1f255b0ee9bd4f149e3aa95bf77e1fb9fbf597e5fd9eaf9ae79ee36655f2feb49535435123299151454b526657a29f454b03b3a751535751910d4d454b41510a968d9454b2c28a2ac567202bd5ba2acc3b2a4a51b827fa3d7a1f80f4fb9d52f20fb3e9b56be1cf83dfc4979fba87ccafadfc1df07f4dd1fc8b9ff0096f499ea61b0f2ab2b219f0cbc076d6767f69bdd361824af5082348feed3208fecf525433ec28518d18d905151f994b59c8ea0aa1714c9e4aabe6573ce561a2cd4d5437bd5a8294257063ffebb54f507fb72d559f58abe6b08d1f33fe79541e625bd55f9fc9df597fdb1e64db2dff79ff4d2b29cf61a2deabac7970d25a6a1fb9ac59ff793793feb1eb4351bcb6d1ecf67fdf759f35e2cd23b9e67f162cef35887f7b79f60d2ff00f1faf9b3c41ae68fa3ff00c4b744b3f9ff008e49ebd43e3178e2f358bcfb1e9ff73fe7bd7cfb77a859d9de7fcfdecfbeff00edd7852f899d483c5be30b9f260fb143fbbad7f865e2c7fb66c8a6f2ff00e9857917f6e3de6bdbe2b39abe82f8797967f63fdef87a693fe9a56b4a3730abd0f6ef08c6971f3cb0fcff00f3d2bb282cefecff00d26caf3fe015c6e8767fe87f69d2a6f93fb93d743a6eb17f67ff001fba6ffc0e0af4e0ac8e73afb5d43fb521fdefeedeaecf1f99feb7fe0158b049f6c87ce8bfef8ab569f69ad00d0b592e7ee4b56fcbf33e7ffbee3aa9f6c4ab569795ad302d4353f98f6f54a7fefc5fbca8fed9fc74e72b01a3ff001f1f3d529e4ff9eb51f99f67f9e8fb67da2b272b8d05d69e9790ec97fe0159106fb39b65c7fdf7577ed9f67aa53de7990d633d8a0bad967fee3d65fdb3fb2fe4fe0a9eeff790ec96b9df33fe5da5ae19cac6913535593fd45cd105e27fc79d62cffea67497f78947fcb9c0f59f35c26765a549fb9d9351f63ff97696b1f4dd412b6e7fde43be2aefa5b18b32fec74412568c7fe91505dd9ffcf2aec8089e793f7d47db3f73557528ff00d4549ff2c69cb6023ff96de7563e95fe99ac4ef53ddc9f67d1e77acef077fa47fa4d714be24346dea579fe99556d3fd75559ef1ee26ab5ff002dbceac27b941f6cff005f50687fea77cbfc7556ea4fb468f7555743bcf32b2606ddd5e7d9fe4a7fdb3fd320acbbbff48bca9f4a93f7dffb3d3881bd7779f6387fdb7a3e7f3bfd77eeeb127d43ccbca5d56f3ecffeb7ee56a9d80e87fb43ed137fb15a1e67da2b8dd2a4f321df5d141bfc9fded745395d92cdeb7abbf3d644127d9e1a9a093ed15e844468fc96f47facff0096de454153ef4ad62041fd9f6d7137faea3ec7f67a3fb47deaafdb1e4ab132adde976127fadfde571bae687e1eb7b39ffe24f5d9dd5e3c7feabee5705e2db3bcd52ce74b786f3e7ff9e158d427a9e03f12bc61fd87a96f8b4d87ecbff4deb8d83c60ff00b8ff00531ffb15d978e3e07bd9c3fda5aade797ff4ceebfd65727a569f6d2433e9b143e67fb7257935f63aa1b1ec9f083c50f67a97fa9f2f7ffcb3afa5b4dd412be20f0cea1736f36cb8ff0044ba49be4afa83c31e28fb1f9097bfc7558626a743d569b3c6927faaa65a489710efa4af696c64496f557518fed156bccfb3d13ffa454c95d01c05d49796737fb15afa76a9ff003d6a4d4bfb92d677fabaf1a5784868eae0bcfb455cae66d3fd22b5e091edebb68caf7066e53eb360bcabde65764443fcbfa5795fc42f81f61e30f9ff00d5bd7aad26735a231a9455547c17f137e09de785ff00e9a257cf5ae69e91cdfedffcf3afd5bd7343b3d62cff00d221af8b7e3a7c37d1e3bc9de286867cf6270fecec7cab35455d56a5a1a475833c75278f38d999b455ba8aa652b184886a2ab750d66e571c4edaa5a2a5ae9e739028a2ac51ce057ab1453eb394c06559823fb452569695a3bea1493b9a421cecb5a7784ee750f93ceaeffc23f0aff7d07fcb47aeafe147c27fedb9bfd4cdff005d2bea0f097c37d2bc37043e4c3fbcad11edd0c1caaeaba183f0cfe1a7fc23d69bee3afa57a6f97f4a318a5a19f51428c68ad02994566cf25672d8e91f3c9f67aab3de7da2993c954aeef123ae29cac344d3de7fd36a2b3e0d927fa4ff00e44a8ffe3f3fe5b564e5728bd05e7da3fd556bff00abac883fe25ff25bfef1ea6add6c4b09e4a87ccfdf6caabe5bfdf96aac127da269ff00e7d7ff0043ae59ee0835cd43ccff0046b7a27d9a7e9be4d41049f68bcff62a97db1f54bcfb4ffcbaa7c90c7fdfa828dab4912cfc848a1f9eb94f176b09279e92cd5d2ea3a87d8e1d9fdffbf5e4b06b89ae7f68dcff00abd2d3e44ff6eb9ab4f96c8a8ee792fc57d71fced917f1d78a7c4dd42db47d37ec717fc0e4af49d5750ff84b3c5575a97fabd134afb9fedbd78ffc43d61fcefb4ff7eb823f15ceae865f81366b1a97fc7e7cff00f3cebe88f03e97adc7feabf77fee578af83b5cd2adfc8fb6f87bccff00a69695ee3e0ed5347bcff5b34d69fdcaf521b1c93dcf64f0cff6ad9fee6e21ff00b695d5da6a8971f27efa3ae474afb67fcb2bcade823f10c7ff004f71d7440ccdaf2dee3fe3de687fedbd4fe5dcfdf8a6f9ea082f2cff00e5e26f2eb46d74f4b8ff008f29a8980c8350fdf6cbdb3f2ffe9a56a797fc70d67799791fc971fbc4a9fe4a2005a8350fb3d687c9790feeab227d979557e7b3ff005544e56d0027df67ff005ceb3bfb73cbad8fb67da3fd7563eab1ff001c55cb356d4689eeef12f21df156241a87d9ef3ecd3555f33ecff3c5fbbff62aaeabff00130b3dff00c695cb53a1b40dbfed0fe09ab13ed8ff006cff0072a969ba87f6a59fef7efa54ff00f1f1feaab234269ffd226dff00c0f57ad24ff43d9fdcacb824fdcd105e7fa65202d4179fe995d25a6a1e5d701a95e2697357433c9fea2e62ada9caccc6a743afd1ff00d4d5af32b134ad43ed16753f99f68f92bde84ae8c59a977fe9167be2aa56b253e0bcff0043acf9ef3ec70efa27b0228eadff008e552f0749fe873bd5dd57fe3cf7d52d0ffd1f4dae390c8ffe3ce6dffdfad4d4af3cbb3aab06c93fd26e2b175fd53fd0e7ac67b0d07fc7be8ffbdace8350f2e1a2091ef2181ff82b2f5593ec75c323681b76bac7fa1ffbf5b56b27fa84fe37ae36d24ff43ae8a093ec7e43cbf7eaa0299a93de269736ca8e0912f3fe9a57377779fbed96ff007deb6b4ab34b7f93cef9ff008deb520ded2bf79e7bff0002568ff68247fbeae6bfb53f7dfe8ff712a1fed4fe0b7fde3d691958967656979fbedf2d5a8ef1e4f93fd5a573505e5cdbfc9fc7fc6f5a905e7d8ffd6d74d3988e97e4b7a9ab9efb67fdf7fdcad782f3fe7ad765395ee265ae7fe78d1e67d6882f12e289ff00eb8d7544110cfb3fe7b5727ae497ff00f2cbfd113fe7bd6c6a31a7fcbbd9fcf5c6f8824b9f27fd36f3ccff00a67533067cf3e3bf09d85c4d3bcbadea5ab5ebd792cff60f09ea5f6cb7bc9a4ba4afa0bc5d7967ac433db7fc7a2578278bb4bd1fc3f37ef6f21bb47ff9675e7627645d3dceafc31ae3dc79e9feb3f8d2be86f865e38b3d421fecad56be5ff873a87d8e6d971ff1ebff002ed257ad69578fff001f3ff7dc95e6c65cb23adec7d75e1993f73b2b6e7d95e73e0ebcfdcc17917fc0ebd27e4921af769cb991c9223a92dea941febb655a9eba224906a566979fefd72975a7fd8ffdcaecbcc4b8ac8d4acde4ac6aec346041fbcff96d5b769fe8f5cdea5a7fd9ff00d262a7e9dac3c7ff001f1fbc4ae25b833b2b7ad4824ae7a0bc4fbf5a10495df4c46cd3eaa412558ad802bcc7e26fc37b6f1269b3f950fcf5e9be652d063568c6aad4fccbf89be07b9d1e69ff00d8af2fb8afd40f89bf0cec3c61a6cffb9ff4aaf833e217c37ff844f529d2e3efd6553a1f375f0f2a6f53c97feb8d415a97567ff3caa9d72ce563ca9ab32beca8aa5a2b3e6b999dad14558aebe6b1c414fa2b420bcfb3d272b9a40cfa96b56d343b0d43fe5b7975daf867e1bd85e5e6cfb6549bc21cf233fc25f0ceff00c49e47d9ebea6f873fb35e95a3ff00a4ea1fe9127f72ba5f85ff000df47f0dd9c2f0c3fbfaf51ae98ec7d4e0f071b36ca7a769d6d6117936f17962adf99f4a5a8fed9547b5182868892aa515154c8a229eab525d4954a78ee64ae5a9d06866a579fc1156741a1fda26df2d687fa369759177ac3dc79fff002cd2b966513dd6cf3bf7b3799ff4ce8824fb47c917dcaaba559a49ff00c72b7a08d3fe59510008237b8a9a7ff4786a1babc4d1e1ff006ea8cf79fb9ffa7a7ab72b458191e27f107d9e1d917dfacef095e5cde79f7971f73fe58d65ea5a826a9a97d8edff00e072574ba7489e76c8bee25796a579804f27d9e6df2fdf7fb91d32d7fe7e65ff008f5b6fb91d677cf79793ff00cfd7fe815b7fe8de1bd0604ff58ef5bc376544c4f1c6b1f67d1ffe79dd3c35e5faaffc537f0de7d36dff007f7573ff008e5757e3192e63fb55cffcbd3feeeb83f106f8fc2b027fcfcd7257e86f03c5752ff893f827645fc7f7ebc7b55912f26d3acee3f83fe7857b0f8ee44b3d1ff75f72be69d3bed9e20d6277fb679759d32a5b1ef7e0ed3ecece1df63ac4d1bff72eebd9340fb65bfcf2c30dfd78df83a4bcd2e1ff005df6baf5ad03c51f6787f750d7a30d8e391eafa56a1fb9ff0053f64ae920bcb9f26b83d0f5cbfbc87f750c35d441ae5e47ff001f1a6ffdfbad0936fccff9ebfbcabb07fa1ffc7bcd54ad6f2daf21ff009e7fefd5d823f2ff00e9a27fcf3a00d882f3ed9feb689ecffe79563fc96ffee56c799f5a00ab3c89ff005cea0bb91e3a2792a0fb627fcb5ace7b0197fda1e65e7ef6b6e093ccff0096d58977a7f995cf3c773a5ffaaae643474377fbbf92b2fcb493e78aad41a87996758bf3d9de7fb14328c4bbdfa3eb1be2fb8f5a306a1f63d4bf75f71ead6ab1db5e43ff00b52b1ed64ff976aca6690346793fd33fe79bd626ab27efbed3ff002d2b46793ecf37efab2f52d9e4fee7ee5632342d788ecffe120b3826fe3adad3750ff896c0958b6b27da2ce0aaba8ea1f63bcd94e06733b5d2af3f82b534dd41239ab91d0b7fdb29f05e3dbc33bff72bd0818b3b282f3cc867a7f99f68b3ac182f3ed10efab56979fe995d0f62586b9ff3ed45ade797a6eca87fd66b1ffa054dfeafcfa408352ff906c15c5789af1ff716d15757a96a1e5d9c15ca6a51fefb7d6153a1487c12797f2552f137d9a49a0a351ff47ac8824fed4bcd95814749a6533ed9f6c9bfdcaced46f3cbb39d2b2ffe7859c3401bda6de7db2f3656c6a5a87d8fe4b7fbef58fa76cd2eceaadafda7549bed317dfaa44b36ed7fd4ec8ab52d244d2e1df2fdfa65ae9ff6787f75fbcfefd1069eff00f2d6a908d482f1ee3e7968ff008483f82dff00efe5624f227fc7b45fbc7ab569a3bffd73ad220743a7489ffdb2ba2b4d412b8df2dedeb6ad7f77f257453dc4ce97fb613d28fb63fdff003be4ac482448fe7f27e7abdfda1797bfea61f2ebd2a6484f1bde7fcbe5727a8c7611ff00c7c7fa5ffb11d74bf63b3ff978acebabcd2a3aa981e37e31bc7b887f7b0fd82cbfb95f39fc46d51ecfcffec1d1fec8ff00f3deeebeb1f135e5b5e4dfbafde57827c46f07bf883e78a1ff0081c95c55fa1a44f0af87be387bcd4bec77d79f6bbdff009e95f47e95a83ff6969d736fff00034af9735cf0ba68fe24ff0047fbf5effe03f16249a3efff0097ab6af2e7b1d5d0fa67c09e204b3bcfb35bff00c7ad7bc687225c59feebee57cbfa55e2793a75e45fc75eff00e07d42bd2c2fc2ce691b5aad9bf9de74556bccfb659d4f77feaab3abd18924f07fa4434412573d3ea1e5ea5fedff001d4d3ea1e5fcff00c1533024d474ff00f96dfc1589fd9ff68ad7f31e487679d5ca6a31dcd9ff00cb6ae6a9d00dbd3b50fb1de7d9ae2b7ad3bd79add6a1ff0009243f6697f77755d2e8178fe4ec97efd14c0eeea4f32b2e0bcab75d30026f33eb4fb7acb9e4a2093ecf5a01b75e3df1d3e1dd878a347dff00c75eb50495c3fc468ffe25b3d673d8e5af1bc4fcfef13f83fec736cae427d0ebdafc77e1fb9b886778a6af18bbdf1d78b5773e4eb2b48c49ecfecf556b467aad587358c0ee28a968aee94ce40a2a6a9608dee3fd554735cb8857b5fc05f03de6b97903f93f27f1c95cd781fc2f61a7ff00a4ebbfbc7fe082bea3f857aa5cde43fe8fa6fd82cbf82ba68ee7a1848dea23d434ab34b387655ef32a9d45e67d6bb65d0fb38c7950ff00b654750cf225bd33ed959dca2ef9955aaa7db123acf9f50fb47ff1153295901a1556ef50f2eb1751f10259ff00a345ff001f55893fdbf54ffa744ae1a93d4d606a5deb89ff0003acb837eb936c8bee7f1d41069ffbef27f82ba2fb65b6970c0914358f35d95334208fec70fd9a2ab5e67d8e1f3a5aabfda16da3c3f6997eff00fcf3ae7aeef2e750f9ef66f2d3fb95ab9591996bcb7d6352fb4cbf713f82aaf89e4fec7d1e77ff005974f53ff68259d9fd9bfbf5467ff8fc83fe5a5eff0007fb1585497368052f0ce87ff08de8f3ea57bff1f4f5d2ff00a3687a3fda65fe3fb958f7779e679fff002d3656c411fee6d7cdfde3d14e3baec063fd8ff73b21fbf79f3cd5d15dfeee1df2fdc4a82d63fdf4f735575fdffd9bfedff1d6ae368b0479ccfbf50fb55cea7f72e7ee27fb15c078bb507b89a7497ee27c895dfcfae7db34dd5ee7fe7dbe448ebcb27fddf856e9e5ff008fafbf5e4cbe23a3a1e29f17f54fb1f86e7ff63ee5792f83acdedfe796cfccae97e317fc4c21fb37f03fce9557c1de1fb3ff0096b5d10d83a1e85e18d42c249bfe79bd7b0f83b43fb67cff00bed95c87862cedadff00d6f93fec57a0695269571febaf268eba207348eff4df0ba7df8aba5b5fb659ff00aaae5f4db3ff009f7d62ba4ff498ff00e7b7fdb0ad5121771dcc9ff1f1fb8aabf3e9ff00f2dab7a0d43ed1feb7ee5559e3b6ff009f3f9ffbf59d4e804306b89ff2d68fed0fdf7fa3cdff006ceb2eebecd59df634b8f93cef9ffe7a562c68e86ef507ac8babcfb45529fed9a5ff00adff004b4a82092daf3fe3de6f9ffb8f594c19b706b1e5d13de7da3feb9d677fc7c7c92fdface9fed96f0feeab3045efb63d9cd56bfb412e3fd6d73bfda8f243fbdff8faa9ed2f3ed10fef6828b5fda1fbefdeff00c7ad68dd69f6d79f3dbcd5c9ea5fe8ff00eaaa7d0f58fdcd202d4ffe910fd9a5fbe959d6b79ff2c65ab575796de77ef7efd67f97f679bed317dca00208decfcf4fe0a35293ed967b3f8eaeeabfbc8764559da96fb7f21ea96e074be1f93f7353cffea677ae774ad43ec70d74b06cb8f3eba496320d63f730253ed64fb3cdbeb90f33fe27d07f72bacbbd412de1ad2022d69bac7da358ff00629ff6cf3269d2b0743bcfb3cd3d1f6c4b7bcad00d1babcfb443b2b1f5293ed134094cfed0fb45674f27da26ace63455f13de797f27f1d49e1f8ff0073beb067912f3529eba4b493ec7f25732dca0d57fd33c8b68aa4f92dfe7fee555d72f3ecf37eebefd4d3c9f63b3fdefcef5a22584f27f6c4d024bfbb4aeb20fb359c3b3f82b89d0ecef2f3f7d2d6f5d7d9ace1ff009e8f5ac04745fdb8967ffc4541059eabaa4de75c7fdf8ac1d2a44f3bf7b0f99755d27db2f2b542668dac7fd97feb6a19358ff9e5505a7ef3e7966a9e7fb07df9bf79544957fb42fee3e48aad69525cff00cbc4d557fe128b6ff8f6b7fdda51050523aeb4d412b464d73f73febab90d377dc7fadadeb5fb1ffd31ae9a3d4193f98979feaa1a8278ff00e98d4f3de5675d5e5cd7498cce7b51bcfb47c9159d797f8c743b9fdff9b790c69ff3cebd4355fed5921ff8fcf2ebc53c79a7dcff00d04be7ff009e95856d821b9e4be31d4347f0dcd07da3f78f5b7f0cbc51a57f6f4f0c567f25e579b7c43b378fcfb9bd9a1ad0f84127ee60bcff00a6d5e5d4dcee82ba3eaef865e284d43fb474af27cbd95ebbf09fc4173aa59ecb8fbf6df257cd3e11d967e3683fe7d6e6bdafc3fac3d9ebdf6cff00569ffa1d5529598aa46d63e85ff5959d7767f68ff7ea7d36f12f2cea0d4bfd4ef8bf82bde8bbc4c4e0fc6379fd970c1792c3fec3d1a76a9f67f23fe5a5abd6deb91a789347bab6fe3af2fd0f5c78e6fb36a10f96f6df7eb17b81d97f68259ffd7ad68dd49e67cf5c9eb9ff0012bbcffa75b9fb9251a549e5d9fd8ee3fe012565300d4b4ffdf7eebefd6dda7ef21ff9e6f5ca6a579fc171ff000092b5343d6135487ecd2feeff00e9a51003b58247b886b5f4ed43ccae02d35c7d2ef3ecd71f7ff82b7a0d71239bf7b5b440eae7d97159ff003d9ffb9597aaea1e5ffa645f72b434dd5135486a981a9a75e7995c8fc54f14269fa3ce92d6f411fefbf755e5ff001c3fd4fd9a5ff80563525689c989f80f957c4de20bcd1f529d3cefb4593d707ae6c93e7aea3c5dbecef3ec771ff00ae36eabc1ab3f78f98a9d0caa8aad5455cb29989ddd14515dae67212d68e9d79fd9ff003d674353da7faef3a5a14cb89ea7e0ebcb6f3a0fb443e63d7d4fe07d72dae218122fbf5f1d7866f3ecff00efff001d7a9f81fc417367fe93fc6f5d34a7a9e8e11da47d3b3de51f6cf2e1ac8d2a4ff896c3732d676a5a83ff00c0ff00f40aeb9cf43eba0ef145d9ef3fefba920df589f6c4b7f9289f50f2fe4fe37ae652b9a1a1a95e25bffa345599f3dc7faaff00beeb3aeb504b3f93f8e9f75a83f93b3f8ff823a19a403cbb6d2e6ff47fde5d7f7e8d56f3ec7ff1f1fbbffd9eb2e7d43fe11fb3fdefef2f6b2e0bc7b89b7cbf7eb0a92b58d0dbd3b507fbff00c1ff003c2aeffcbe7da6e26f9ffe79d72306b9febee7ff002254ff00db09793409fc1fdfacd4c0ed6d647bcff49ffc895ceff6e5b7db27b6b7fde3a7fcb4ff006eaaeabae5cea9ff0012dd33fe07505a69f6de1f87f7bfeb3ff43a1cae6733a2d2b4f7f267b997fe3e9ffe5a555fed07fb1cff0067fbff00711ead6a5a87d8f47ff7fee5625aff00c4ae1fb4ff00cbd568f633353cc4d2fc8d362fde3d6de9bfbcb3dffdcff969597a759bfee3fbeffc75a977fbcff438bfe3d6dbefff00bf5ad22596a0fde6a5027fcbaa7fe8754bc5d79f639bf75fbc7adad3bfd1eb8af16ea1fbe82e7fbf3574d5f8471383f13469a7e83f66ff0097a7f9e6af2ff1c6a1ff00127d3becff00ee3d75fe26dffd8fa8dccdf7de6ae1bc63ff00201d96ff007edbe74af223bb3a7a1f387c46b3bcbcd62d5ede6ff7e3abba047f63ff005b5893ea0fe20d4a7797f775d6787f50b0f3b64be7495a0743d33c1dac695ff5d2bd7b40d3f44d53fe61bff6d2bcd7c2567e1efdc7d9ebd5f43d3f47ff0096537cff00f3d2baa1b1cd23aeb5d1f4af27fd1fceb4ae8b4dd3fcbff96de656269567fd9f67ff001f9e655afed0492b4219b7f634ff00ae725413fda6deb2eef5c48eb3fed8979ff1ef79e5d6353a02352ef58ff9f887ccacb9ef2c2e28f9ff00e5e2a09e3b6bcff96de5bd62c6320d9e77eeaa0d4bc2f6127cf14de5bd13f85ee7fe5de99e5de5bffc7c4352063cf1dfd9cdfe91fbc4fe092ad41ac27fcb5a9ed750f2e6d92cd556ea3b693fd6ff00df74006a5a7a7fc7cc5ff7c555b48fecff00ee53ff00d3ece6ff009e96b59f3ea096f37eebfe071d004f3feefe4acebbd3dedffe26565ff034ab5ff1f90eca8f4ad41ffe597fc0e3ace6344369b358ff005bfc753da46f6734e9505dff00a1cdfbaad7d4a4ff0097cffbeeb3287cf25b5c43fedd65c1fe91f24b525ade279dbeaaeabfbb9b7d690033848f6f36caea34ad43ecf79b3fbf5ca4f79f68f9eb435293f736b79156c8965aba93ecfa9568ea3beb9ef13ea1e5f9173fdfad0bbd53fd32d7fb8f4c41a55e7efb651a8de7d9ef2b3ffe3cf58ff62b4352fefd00647db3ec747cf6f58905e7db352f266abdaade7d9e6a4c682ebfd1e6df47f6c7fa651a97fa44358fe67fc4ca0aca651d2f97f6c877cb4687ff00134f3ee6e3ee54f049e659cf59da1ffc4d21d9fdcaaa7d496757e67d9e1fdd573d77ae7ee765105e27efee65ff008f54ae1a7fb4ea9af6cfefff00cb3ae9888f50d0f50fe3adeb4d62daf3fe9a495c8da59dcdbf9367feaec53efbd687f6c5b69ffeaab44075fe5dcde7fd334a9e0d1edaf21d92fdcac4d37c417371feaa1ad49ef2fee3c8ff0043ad602668f97a56970fee7c9aab3ea16dff003dbcca83ec7f6c9bf7b675a3069ff6787fd1ecfcc4ad0920b4fb4d68f95ff4dab2fec7f639bfe3ce6f9ebabb5d0fecf0ef97fd656b003220b3fb455a9ecdeaeddde259d647f6e3ff00cf9d68673307c41bf4b87ceff58ffdcaf0df176b97f71e7f9b0c3615effa8e869a87fcb1af25f1c784ec3ee4bf7eb2a9f090b747cbfe3bff00a78bcfb5d55f841e204fdfdb7f726ff5756be26e9f6da3de7ee61f2ebce7c0fa87fc56dfefd78dd59ea2d91f60da5e7fc83b558bf83e4af57b4d412f2183cdff007ebcbfc31fbcd07fdbb6877d77106a1f689b48f37f77bffd755c3e2444f63e9df0749e5e8f027fdf15b7049f68f392b94f08c5ff0014dffe815a3a55e7fa1ef97f82be8a1f0a397a905dd9ff00af9abcbe78ff00e27d3db5c7f1fdc7af56ff008f8f3edbfef8af3dd4a4fdf40f2c3f73e49aa6a74028cf79ff0012dfecab8ff803d52d37fd23feddaa7f137fc7e57357578fa5ea505cff00cbadcfdfae69948d4d5637bc87ed365589a75e7db26fb34bfbb74ad8fed0f2ef3f75589e27d3fecfe46ab65598cebffb43fb63e4b8ff008fab6ff96956a7d43ecf0fef7efa572106a1f6cf22f2bab8244d521ad2005a83c5091c3ff4eb5a3e1fbcf2fe7b7fb8ff007ebccaebfd1ef274ae8bc23a83d9cdf6697ee55899ec9a749ff7c570df1d2cfccd077d751a4d65f8e366a9a3cf672d3abf01c788f80f8abc771ff6e69bf698befa5797d7a4f8c6cee7c2faf4e9fc0f5e7f3d7c96265691f3553a15aa1a9a8ae173313b5a28a2bbdcac73c42ad41fe99fee533cbfb455a82f28532cd4824fb47fa1d97f1d7ba7c28f07bde790f7bfc1fc15e17a548fff002d7f7695ec1e03f145e6b1ff004e9a5d76e1e5791d986f88fa33ed89e4ecb7ae535cbc7b7f92dfefbff1d5ad0ef3ed167b22fe0acebbff00a65fc7fc75d55b747d752f84669bfe87f3ff001d729aff008c2db4b9bf75fbc7ab5e26d63ecf67b3fd5d707a569fff000926a5be5fb89ff2d27ae467540ecbc331de5e7fc4d7559bfeb8c15bd77a87efbf73fbfba7ff00c72a8ff685b7fc013fe5a572f3eb9fda179fe8ff00f1ebff00a1d68cd0d8d4bfd76ff3be7a82effb9e77fd76a67fc79ffa4dc7dffe08ea0d2acfcc9b7ea1ff001f573f720a903460b37d53c8797f776bfc11d3e7dfaa5e6cb7ff0072a7f136b1fd970fd9a2ff008fafe3a4d0ff00d1f47fb4ff00cb47a00d1ff43f0dff00a35bff00c0eb3ffe431a97db2f7fe3d6da8d4644b3b3fb4ff1bd677fa4eb137d9a2fdddaa7faeff6e802d5dde7f6a7cffdcff5295afa8c7f63f212e26fb9f3cd50f87f47f32f3fd84f9d2b13c6378f7934e96fff002f3f2506533b5f03ea1fdb1e7eabff002ebf721ad883fd1ffe075078634b4f0df856d6cff8ea0bad43fb2f52ff00b63ff8fd7752d8c99b13c9febebce7c63a8797fd9d34bf72bb59e47fecd82697efbfdfae2bc7127fc7adccb0d6589d91a40e6b51bcb3d42f204ff596be4fcf1d78c78ee4f33418122fe0ff009695e8da8efb3d62d7cafb9735e6bf162f2cfc37a3ea36d71fc75e62363e70d374fd56df529fca9abd27c3f789ff002d7479b7ff001bd79ce95f698ef37c3f72bd6bc31a83dbff00ad9be4ada21d0f4df08de59f9db3c9af49d2a3fb3ffadd36bce7c31aa68927fad86bd0f4dd52c2dffe5b576c7639a475f6925cff00cf6ab5ff000903ff00cb5861ac4d3750b3bcff005b5b1fda1a57dc8aa89127d713fe5b59d644fa85b7fcf1f2eb47fd1ae3fe5f2aafcf795954e80677fc2417367ff2daa0ff0084a12e3fe3e21ad8fecb4b8aabfd87f63ff96358818ffda0ff00f2ef7953ff00c241736fff001f1f72b53cbd2bfe7cfe7ad1b58d3eff00f07fcf3aa4065c17967a87fcb6a7cff63921d94c9f4fb6f3b7c50d1fec4df7298147ed8967f2552bbd9ff1f3fc74fd563f2fe78bfefdd65ff687da21dffeadff008e802d3ecb8f21e2a9ff00b3d24f9e2fddbd72ff006c4ff96b5d5daffa9df14d4018be2dfddf91fb9ad79ff776705cff007e9fa9469aa7fbf58b05e7da3fe25b52c68cebb93ec736ff00e07a2eef3ccf21eb2f5fff0053505aff00c79d65328e8b52b3b6f260b9b7fe3a3e4bcd36b2ff00b43ecf67469b79f68b39e8812c2ea3fed0ff008053ff00e3e2ced7fd8aaba6c9fea3caad48365bcdb2b64227d73fd7541f6cfdcd335193cc86aafdb3f734c02d634926fb4d175b3cefded107eefe4a355bcf334ddf49819d049f68b3a27d97136cff0096956ad7fd4c0f506a31ff00c4cad6f2dffe0742027b5bcff5f6d56b438ffd0e748ab12d637b7d627a27f107d8e6d95ac00d19f50b3b79a7b397ee2565ff00c7bffa67fcbd3fdc4ac49f544d1f52bab997f79bfee54306a8fe4fda65fbff00dcad501de69d25cfd8f7ea1fbc7ac4d4b58b9fb66c8a1aa569e28ff97697f77576ee4f2fe7fe0ace7d068ded2a47ff009ed5d9687e284b7ff98957875deb896f37faead4d0fc51ff005c644a852b03573e9cd3b5cb6d421adbb4d1ff00e7debc1343f1c7fd39d7a4f87fc41f68ff00e375e852927b92d58ed678f558ff00d543f3d529f4fbfb8ff8f89a68eb52d358ff009e5fbcad1f33ed10fef6bd2828b5a18cce4fec7f67ff00555973ea97ff00f5cebb9bbd3ecee3fe58d60eaba1a7fcf1fb5d29c6d633394bbf143ffc0eb8df13eb89710ffa6c3f3d7653d9a7fd73ae6bc4fa7fee7fd7435c55763486e7ce7f10ecedb58f3fcdfb95e0169669a7f8da0f2abe93f1c59a7fcf6af11d4a4fecfd7ad7fb95e5d4dcee86c7d0ba1c896fa0ff00aeff00963f3d76df6cfb47f646a52ff1ff00a2d79478677fd8eebfe5a23d7a35a4773fd9b05b45f7121ffc7ea11353a1f507c28d43ed1a6cf6dff3ed5d0c167f63d4a7fee3d79cfc20d412dffd1bfe59bfdcaf509ffe583ff73ee57bf43e0462ce7bccfb3cdb3fef8ae6b55ff4cd4bf7bf7eb6f5cff895eb105e7fcbadcfdfaabaae8ffe99ff00a03d5cc938ad4af3ccf925ff00bf959d77b2e34d9eda6a9e7df1ebdb2e2b3b558fec70cffdcae6a9d00c4d5637f260f2befa56a69b79ff00090785674ff97a4acb83fe261a6ffb6959fa75e5cdbd6404fa6de7fdf1fdcae92d358f2ffdcae52093fe26544fa87f67cdb25fb9401d0fdb12f2f3ecd2ff00c024abb69fe8f79f66b8ff00805701aade797340f17f057a35a5e2788347b5b9fe3ab89323d6bc2379f6886b94f89baa7f637fb95b5e18ff00535cbfc46d62da4f3ecee3efd5d6f80e1c46c7cdbe3c92db58f9ebcaf52b3fb3cd5db78c64ff009e55c1ff00acaf86c57c4780fe2636abd5aa6d7088eb6ac514faedf6872851454de5fda28e72a25ab5ff0048aeff0040d41e3ff468beff00f05701e67d8ffd57dfaeafc251fd9e6fb4dc7fc023ae9a13d4d16e7d33e18bcb6fb1ff0066dbff00db69299acecff80564783b584f3bec765f73fe5b3d6bf883fd23e48bee7f1c95ec5495e28facc1fc079cea5bf58d4bf7bf729ffda891de7d9ade99aac8f71f25bfdcac49e44f0fe9bb3f8ee7efd7353ea7add09355d43ecf0ef97fe011d68687a7ff0065e9bfda57bf7ffe58c75cde9df66fb67db2f7fed8c15b505e5cea979be5fe0ff9675b01a33ea89670ef97fe3e9ffe58568f87f7dbcdfdab7bff001f4ffea6b9d8234bcd4b7ffcbd3d5ad47584b7bcfb37f73efd001a95e3ea179feffc895b7ae6b896f35ae8917fc0eb9482f3ecf793eab71fc1fea60aab04773ff1f317fc7ede567203a49f50fed49a77ff009754fb95a3a1de7fc49e7b9ae6a093cc9becd17fc7afdcff0081d745aac8967fe8d6ff0072dbee7fbf444ce6751a1c9ff12dfded5582cd2e2f204fe0aab3de5cdbf86eba1d0f4ffdcc1f68aea333a5f33f8e5ff80573b3d9a4936fb8ff007eb46793fe7ad676a5795b3d8964f7527db2f20ae53e217fae834dfee7fcb4aeaf4d912de6813fefbae6bc71b2df52df5cf5be134a6715e27fde69bfbafe0af9cfe3f5e7da3fb3bfbff7ebdef5593fe25b75ff002cddebc13e3d7d9bfb4ad6da5ffbff005c31d8d8e2b4393fe7959d779a57fa47faa86b89b5b3bcb3ff005bfbc4fe0aea34ad42f2dffd5435b40ce67a368766f6ff003ffabaedb4ebcfb47fcb9c35c3687a83c9feb6bd0f43d62dbfe5ac35b23166d69b67ff004c7e7ad1f2ee7ced9f63a641a8587fcb5a83fb713fe594d5ac041e6ffd31aab77789ff003e7f3ffd30aded3758fb67fcf1adbb48d3fe5afeeea9c6e2672169f69f27f7bfbc4ae8743d9710feebceff00b6f5d47996d1d32eff00e3cff75f7ffe7a56bc849cf4f669e4fef6b2e7fb1dbfc914dff7feb7a78de387fe7a3d73575e204fb97159ce364522add6a0ff00f5cd2b2f52d43f73feba9f75ae5b7fcb286b2eef50b693fe58f995ca5224f935487f7bfbcac4ba8fcbbcfdd4df27f1d1e5db5bff00c7bcdb28d4af2daf2cff00e7a3d521b3135cb3b9d3ecfed317ef13f8eb6f4ad73ec7e1bfb4cbfbcaab06a1f67b39fed1fbcfefd65e95796d796775e4fdca608ee7ed9f68f22e6b2f5cdf6f37da6aae9579fb982e7f82a0bad73ed9673ffb1498c66a327db3c87aced4a4fdf6ca7f87ecee6e2cff007b58baaffc863f735cf53a01760d43ecff00eb6aee9b225bff00adacb9ff00d551a76ffb67ef7ee514fa92ce8b4cab5047f689ab3ad24ff896cd6d56a0fde4307f7eba1083fe5b4fe6d327ff00535a9aad9f97f3c55cecf27efa9804f27fa8a3ed9e6433db4bf72aaffc7c69b469b27da3fd6d260320d43cbbc82dbf82ad4ffbbbcac4d4bff1fa3fb63cc87fdb4a903475291ece699eb979f7ea1e45cd4faaea0f7137fb159706a1f638767f7ea90d1ceea5f6c9358dff00c158b75aa5cfdcb79ab6b5293ecff3ff00e395cdff00a35c4dff003ce99477169ae7ee607b8fbf597e20f891fb99eb1752bcf2e1fdd571306fbc9bf7dfbca2d703d0340fb66a90fda66fdda5779a547f6cff0055fbbb5fe392b83b5d43f73b22aeaf4ebcfdf409710ffc4ad3efd5c6007a4e9566f71f3c5ff1ebfdfaecb4effaedf257016baa7fc241341f689bec965fc10575f69f60f3bf7bfbcfee56c958967a3687ae2597c91576ba76a9f68ff5b0d792da7d82dffd6de56f41e204d2e6d9e4ff00db4ae9a52b18d4e87aef976de950ddc7735cd687ae5b5c43fba9aba5fb67da21ff0062bd18cae62705e26f0fa6a9f3ff00ab7af36d47c2e9ff002f1a97fdf8af61d563b693fd57dfaf2fd7fc3f796ff3f9d5955d83a9e3de27f0fa5bf9ff00f2d2bc2fc63a5dff009dbfc9f2d2be88f1359a7df966af11f18d9db5c435e3d6e877523a5f87978ffd8ffefd7b5cf79ff127b5b9ff009f6af0af87325b49a0dd7efbfe3dabd4fc23f6cff8f6ff00975fb36f7ae6349ec7bafc27d971340ffdff009ebd867ff48b3fdd7df4af14f86527fa8f2befa7f057b0c1febb7d7bd87f80e5ea55d57fe261a0cf58f7527db2ced5ff00efbad1824f2e6d46b13fe3e3479ffbe95d12d8899c3fc43b37b7f23528befa5676a5aa799675d7f996de24f3ece5af35d73fd0e6fb1cbff00ae490a06469579fd8f793ff00cfabfdca66a579f67bc9de8f2ffe5da5ff008055583fd3219d25fbf582342ecffe91e45cc555752d43fb62cffdb4acbd0f50ff00976fee51aaff00aefb4c5f7294b62905a49f6cb3ff00d0ebb9f08c8ff63fdd7fdfbaf3cb4ff479bed317dcaf49f08c758295983d99ea106a1f63d36079ab83f8b1789a869b05cc5f7ea7f1c6a1ff0014acef6ff7ecebca355f187db34d81ff0081eb9ebd4f74f0311b9e6de20bcff5fe757355bde268ff00d337ff007eb06be42bcaf2390869b562a1ae3901db51454b5b9c41441be8a92de8e6b013c1fe87f3ff001d68787fed3a86b15935d069dfe87fea7fe075ad29fbc6903d874ad512387ecda57fdfcaee67912e34dfb345f7ff008ebc5343d712de6ff612bd1b4dd53ed1e479d5f434e5cd13d5a12b331355df1fc9157373ff00c7e6fb8fe0aef3528ff7335e7f7ffd4d79ceb9bee26fb1c5f7ff008e4a967d4465cd1441a56fd62f27b9f27e44adafed0fb1ff00a35bfef2eae6ab5dc9fd8f6706956ff7ff00e5b49597f6cb6d2ffd27fef8ada9f528e92eb504f0de8ffbafde5d5cd55d2a3fb3ff00a65ed62ffc7e7fa4cb54755d41e4ff00468bf8eaa606dda48fe20bcfb4ff00cbaa55dd575c7ff977ff0059441b34fd1e048aa1ba912dfc8fefa511035343b3ff004c83cdff00976ad7f924d63ed32ffc7aa573d6b78ff73f8ee6ba49ff00793416717fc0eb43399a33ff00c4c2f2d53fe5d7efbd74b6b78fac6bd3ff007121f92b06d23fecbd1fed92fef1dffd4d757e188fec7a6eff00f97a7ad69ee62cbb691f996759706f93e7fefcd5a36927faff00f6e89ffd1e6d9ff8e574cb6408ced2a3f33c493dcff02573df137fe3f2ba8d0f7dbe9ba8dcff00cb4af3cf18f883fd33484bdfbf795c95be13681e6da95e27f66cfe57ef1ebc03e2beb8faa6b1027f72bdbbc4127fc23f35d3ff0007f1d7cd3afc9fda9af4effc1e7570743436f4a8de487f7535779a1e9f79ff002ca6ae0f4afb357a1e87f66ff9655b53ea6353a1dfe951fd9ffe3e26ae96d24fb3ff00d34ae774393fea1bf257656ba7de5c7faa86ba11916a0d4347ff0096b5d1695aa68f79ff001ef4fd2b434fbf710d6dc1259dbd74521323fb1db79dfba87feda475b76b66fe4d5dd3bec7e4d5e835cd2a3f92de6af463b1252fb15b7fcf1a8eeffd1ffe99d68f98979f3c5589aade25bffc7c567525629104f227fcb29bfe015ca6a5225c4dfea699aaf8a1ecff00e5cffeda257273f8b2db50f922bcf2debcda92b948cff137eeef37d7213ea0f5bdaaeb8ff72b80f107882dbfe5e2b9e66d03aed2a4b3ff0096554a793ec7ff004cebcbe7f163e8f37fa3cde6256f41e2c4d621ff009e9597358d0d1ff84a2e6de6d9feb13f8e8b4d52cff7e96ffbb4ae275fd51f4fff0049b2fb9fdfab569aa27d8ffb4a586b484ae07a3787f58ff439d22ff575a3047f68f25eb83f0cea097979bffbf5d0c1a87d9f589e8999ccded4af3ec7f2562cf27da3f7d557fb43ed936c968d4bfe3cff0075f712b308137fb156a093ff001fa853fd1ecff7b51e95ff00130ff49fe0ad201336fcb7f3a9ff003d9cd05327fee568f97f689a0ad918b37bfe3e21d95c4eabfe87a94095d2da6ffed2aabe2eb3f321fb4c5fc14c4627cfa7de6c97ee3fdca67fc7bd9d6f5d47fda90dabd55bbb3fb47c9498d1c86ab79ff132df1552d4af3ecf79bff82a7d4a3fb1cdfbdfb956bcb4bcb3d92ffc024a928c19e4ff005e9fc0ff0072b1e7ff0048b3ff0072b460d3de3f3edbfef8aaba97fcb7f2beff00f1a5520394badffda5be5fb9589fd9ff00ebdeb6eee478e1ff00d02b9abbbcfb3de5301fe20d41ff00b360fefd7153f883fe5da1ad7f176a096fa6ccff00f7c5785eabe2c4fb67fcf3ad2316f63489efd69ae5e7fd73fee574ba57fc2497167fe8ff00bcaf9a7fe16c7f65c3f66ff5895de7857f698b0b38765c79dbeb4e59a14cf64f33c4f6ff00f1fba25e7fd74aef34dd1ee6f21df71ac4d689ff003ceb9ef03fed81a27ee3f7de65aff1c13d7b0695f113c31e2cf9f42bcb3bbb57ff00986cf47bdd4833fe1e69fa568fe7dccbe76ac9ff004c2bd5b4ad6344d62cff00d0b52f2dff00e79bd62f847c1f676f793ea5a57fc4a6ebf8e0ae8755f0bdb5c4dbee2cff00dfbeb4ad60448820d61f43d4bfd361ff00b695e8169aa25c7cf15e7d91ff00f1c7af1ebb91f47ff8f7bcfb7e97ff00a05751e1fbcfb459feebfefdff000575c2563099e87a96b1e643b353b3ff00b691d713afd9a5c43febbe4a3ed97367ff0020cbcffb613d72faaf882e6e3fd6feee4a7395d040c8d73ecdf72bc13e2359db7eff00f7dff0082bd435591ee3fe9a5797f8bbfe9afeeebc8c4ec7440e5be1ceb96d1ff68a7fcb47af79f076a1f68d7b7cbff3edb2be5fb4d9a5f8920b9f3be4fe3af7bf03eb1f6cff0049ff00be2b961b1b4f647d19e03bcfb3ebd03c5fc7f7ebdd608fed1e43d7caba1f88134ff23fdbafa8fc31ac26a1e1bfb657b585d99848ab771fee67f2ab120ff43d4bfd87fbf5e81f63fb443fbaac19ecd2e21d95e8c483cbfc5dbfc2fe36b5b9ff00972b9ff969589f10f4ff00b64d3ff7fefa576be318ff00b52ce07ff9f6fbf5c57883fd32181ea2a740383b5d43ed967feddb555fed04fed2df53eb9fe8736ffe07fbf59177ff002c1e2ae2abd00ced72f3fb2f5edffc1577fe5f3fd8b9fb958975bef2f27b696b534e8dfec7fee7dcae1a9d0a468f87e37f3becd5ea7e1f8fcbb3dfff002d12b8af0ce9fe67fa4d751ae6a1fd87ff00b3d67cd64ce6af2b233b5cd612486ebff45d7825d49fd97e7db7f05779e26d53ecf36f8bee3d7995dc9fbeaf9dc5d4d4f0252bb617527db21aa152d15e34a5ccc92ad152d4550077545145448e70a4b7a5a2a79ac05cf312de8fb63fdfa8684ff48a39c0dad3af1ee3e4fe0af4082f3ed1e45b7f1bff00e815e6b6b27d9e6dff00c095d2da6b0f67673dccbfbcbdb9fb95ea61ab725cd21f15cf64f312f2181ffb9f27975c84f67f639b7ff1bd43a56b89a5d9ecff0058ff00c75b5f25e69bbffbf5eb46a7b43e930f5b4b1c3cf1ff00c4cb7cb589ff001f9a94f79ff2ea9f72babd574ffb3c3fedd72fa8efb387fd8ae8a67a88352d412f2ce048bfe07597a1c9fdb1e24fb345f713efbd433ffa3e83fedbd68784bfe2570dd3ff007eb528dbd4750f2e1fb35bd327ff0047860b6ff97a7a34e8d2dfc87b8fb8944fa879734f73fc6ff72803534a912df58ff721aeaf4eff0048d36e9e2ff8fa7fe3af39d364fecff9ff008ee6bd27c3367ff14dc16dfdff00bf5b40ce66c5ac7f6cb3b5497fe5dababff49b3877d73de12b37bcbcff00a724ae86d64fed8fb53ff079db2baa998b2d7fc82fc8acef33ecf79be5fbf5a33c9ff13eb54acef1c49fd9f0daf95fc73554f6044fe67f67e83bff00bf5e31f11bfd226b54fe349b7ffc02bd37e236a9fd9fa0dac3ff007dd79c78c637fed2ba7ffa63f2579d50d23b9e73e31d412e341d5ebe59b4fb4dc5e57b8fc5ed43fb3fc2b0795fc73578f69dbeb956e7433a5d0fecdff2d6bd1bc23bedff00e3deb86d2f7d7a1e87fdcf27feda5775339ea743d1b4a93fe7acd5dadade799ff2dab91d0f47b6b7ff008f89abb8d2af344b3ff973f31ebaa062cd8b5b3b9b8ff96d5bd69669ff003daa8dade7da21fdec3e5a55efed048e1fdd7dcfe0ae9a649af059a7fd74ad1f2ecf4b86b1e0bcfb3c3fbdac5d47c4173ff03ab94ac05ad47c61e5ff00c7bc35c85df8e1ff00e5e3f7e953cf1ffcfc6a55cbeabac5b68ff24b0f9958295ca44d3f8e34492f3679df64ae47c41a859dbffd3dc758fe2df89967f63ff903c326cff9693d79b6abf112e750ff008f287cc93fe9d2a666b0352efc41736734ff0062fb9fedd55d575cfdcffa47dc7ae6b52ff8486f2cff007b0d737a947791d9ec9ab166a8b53fd9a3d4b67fab47aabe19bcfb3ebd75675e73ae7882e7cefdecdf3a541a57883ed1ac7da659ab1a9d0a3daeef50fdccfe6fdca8ed3c40927fa37f0573d6baa25e43bffbf50695fe8f5cf203d1b4ed61239bff006a56c69527da2f3ed3e77c95e7969a8249e42576ba57d9a3b3a2206c4fb3efc5f7ead7fc7c59ecff00beeaada7fa4568411fda2a8093518dee21ff0047ad7b5b37d2f4dfb3567695bece6ad4b4ff0048d4ab4819ccd1fb1ffa1fefab6ffb3fecf675041a3fdb2ceb460dfe74ff00dcaeaa662cab07fa44d5041fea6eada5ae87fe5b555d4b4f4bcbcad908cf834ffdcc1fec51a959fefb65687d8dfc9ff72aaeabff002c1ea67b12f7383d46cfed1e7a7f72b2ff00d8ff00575dcfd8d2e2f2b127b34fdfc337fc02b991af4391d474f7fbf17dfac7d734f7b8b3ff00a7aaf43fecff00b459d73da968f73f72a80f2fd56cdfc9ff005dff0000af35bbd63cb9bf755e95e2a8fec779bffd5d7857c46d61ff007fe555234819de31f105b5c69b5e09a95e7efaba5d57ed3243fbeac49f4ffb1de41e6cd5e950ea4cfa1afa768767ff0008dcfaacb0cd76e9ff007c27fbf527f65fda3478352b7f27fdcae87c39f163c49e03f87be22f0ae95e4be89e21ff008f9fefff00c02bce6d3fd1e1ff005d5d46468da5e279d5d269da83e9736fb2d4a68dff00e7a57153c95a3a56fb8a5cb703e96f03fed79e39f0dd9c1f6d9bfb5ad7fe7a57d41f05bf6e8f0f6a136cd7bf77bfefd7e76eab227f63daf95fbb77fbe94cd0e3fb65e7fb9594e3ca07ea1f8f2cf4ad526fedef03ea5f7fefc153f867c58fff002d61fb3bd7c55f0a3c59add9cd05b595e4db3f82bebed2a3bfb88607961ff81d734c4cf469f54fb6560ea3225c7fadfbff00f3d20ac7f31edffe9a3d55fb67fcf5acc932f518ee649bf75795e67e2e8ffe7afdff00e3aef35c93fe797f1d79978b637af3311d0d69ee79b6abb3cefdd57a37c39f143c90fd9bfb95e7fa959f9957bc3323e9fa96fae186e75b3ea6d2af3ed1a3c16dfc75f4d7c3993fe25b7567e77fc7cc3bd2be44f08ea89aa683f69af78f873ae7fa1c1f629be7fe3af668cacce7a9d0fa3f43bc7bcd1e1797fe3e93efd67ea567fbedf17f1d67781f58fdcec96ba5bbfddcd057d045dd231679feab1a49e7a4bfc75e37afc973a7d9c09fdcb9af7fd7347fdf6f8abc97c63a3fdb3cf4a8a9d093ce7c63b3ce83fb8f589049f639bec72d6c6ab27db2682da5fbf5c6fcfe4fef7fe3eada6ae2ad2b245209ecff007dbff8d2ba4f0fc7e6562ffc7c4d5de7862cff00b3e6fded795526372b26749a1c696736cae03c71ae7da3529edab47c5daa3e97ac5adcc33579cf8e2f3fe271bebcac556e448f02bcaeccbd4b50ac19ff00d755d9ff00d22ab57cbceafb4672b2bd1562abd664915152d4557103b5a2a6a2b8a52b805145152a5600ab14514f9c0741fdff00e04ff9675a1048fe77da65acda75690ab6036b4d91fcef27f8debd5f4393cbff0046f3be44af25d2a4fb1cdff4d2ba8d2b5c7ff4ab9ffc875ec61b116b9ad3abecd9d5ea366979f3d72975a7bdc4dfec56dc1a87da2182a6bab3f33fd57fc0ebd7856e73e8a8d5f688f39bafde6a5ff4eb6d4cb4bcfdf7fbf56b518dee3e4fefd624f27fa67da7fe59a7dcaeaa72b9dfd0d1d56f3ecf340956a0ff0089a5e7fd3ad73577fe91a941735d2e951f9935d79b5b016b4aff00489bed32fdcfe0af49d377d9fd96da5ae4740b3fb66a5027fcb95b5757049fea2e6b4898d4e876ba6ef8ecf645fc75bda6c76da7e9bb22fe0ae6fc33fe91feb6b6b4a93ed90eafff005dabba1b191a369b2e2f3ed359de31d3ff00b53c8497fe015a3a6c9f679ab3bc41fbc9ad7febb7faca27b0d1c3fc46912e219ece5fbf5c6eb9a83fd8e07b8adbf1dde7fc4cbff1cae43e21de3dbde6cfe0fb3579d50da07ce7f17b5cf32f3ec75c4da49731ff00cb1ab5ae7fa66a53dccb4cb592da3ae3ea6874ba748f79feabf775e93e198ef3fe7b5797e9dfbc9bf73357a1f87ffd1ffe3e26ff00b675d547a98d4e87a9da5e7fd39fdaebabd3647fbf17931d721a1eb896f5b7fdb9611ffbf5d26476d6ba9ffcf5ad482f13efcb3579ac178979f3cb57a0bcfb1d529582d73d1aee4b6b7ff4997ee579ff0089bc58979f245ff1eb58bae78c2f248767fabaf1ef1e78f2e747876450fcef49cae16b1eb53f8b3c3de1bb3ff48fddbffe44ae6ad34bf10f8f26ff0042f3b49d13fe7fa7fbf47c1df01db49e47893c6137fd71827ad1f8bffb50783fc07e7fda2f3cc7ff00963631d74476009fe13e89e4ff00a6fdfb6fbffedd627883e2c784bc07e1bfb345670c75f336a3fb4a6b1f1535ebad36df5887c356af0eff003eeebe62d7fc59aaeb137d9af6f3ccd956a1ce690958fa77e217ed496767ff001ef3799fec415e0fe26fda02ff0058ff00550f97bebcbfccfdf5773f16fc1fa5780e6f0f3e9fe27b3f12a5e5b6f9bec9ff002edfec57453a117b9a73991ff0b1dffe5afdfad0d0fc60f71795cd6a379a55e43ff1e7e4543ff08ddcda69bfdab6ff00f1eb572c343946a573e96d0f50b3d534d81e2ada82f3ecff00279df7ebe7ef0af8f3ec567f66fe0aeaf4ef147f685e7faef2d2bc49d295396a6913dd343d43ec7febabb6f0946fac433dccb3578df86350fb64dff3d12bd83c31ac7d9e1dfe4f97fdc8e88048ee74dd3ff735afa6ecb7aaba5efae974af0ffefbfd22b424ab06ff003b656de87a7fda21ba9aaeff0063f9734f5a3a77fa3e9b57131a9d0b5a559ff055db4ff5d3a51a549e655df2ebae9753219e5fd6a0bbb3ff0051fec568da7fc4d3cf4fee55a9f4ff00b47915bf2dc4ccbd4bfd321fdd5737a959bff66ffb95d74fa7fd9e1ff61eaaff0067ff00afa89c011c6ddc7fb982e62fb95897767fe995d96a367e5f9144fa1a79dbe2ac5c067373c7f67b3d958907fa9df2d7733f87ff00e265fbdac4f10687fe99f66ff96753cb603c97e23781ff00b52cfed96ffbcb57fe0af00f1c784fec7ffc6ebee0ff0046d2e1fb35781fc46f09ff00c7d5e7f1d3ea347c8be27d1ebce7c4d27d9f52af7bf18f87dee34d9ee6bc535cd0ee7fe5e2bb29ec0ccb8247f277ff00ac4aa53c74cf9f4fff008f79bfe01507db1fdab7113f97f5ab5047e5cdbeb3a0bc7ff9e35a3f63b9bc9a0f37fefdd3405afb67db26fb4ff07f05779a1e97fd9fa3c09710ff00a55cd1e18f03fd8ffd335087cb44ff0053057a4f847477d52f37ff0073fe7a56753a01e8df09fc0ef6f0dadcff00abdf5f5069b1a793fbd9ab80f87be1ff00dcc17334d0c95e933ecb8f93f82b9a626626a51a7fcbbd60ea5fe875d44f1db5bffcb6f33fb954aeacedadfe7ae799279e6a327ee7f750d70faac9ff003d6bd6aee44f277ff0579af89e44b8ff009635c15f64691383baff00a6553e951f9735417723ff00cb2ad1d2a3ae186e75743d1bc0727d9fcfb3fe07af64f857a87f63ea5059dc7dcfe0af25f0ae96f2433bc5f7ebb9b5d43fb534dffa7ab6f9d2bd1a5b9cd23ea0f03c9fbe9d2bd334d93fb434d83fbf5e1da06b89e20f07cfa95bfeeeebc9af50f03eb9f6cb3b57ff00beebdfa7b126bdd475e53e3b8ee7efc5fc15ed5a8c75e6df10f4ff00b469bfbaa7536133e7af88d6773e4c17317dfac4bab3fb44df69ff009f9af43d72cffb6347ff0072b97d3acfed167b3fb95e5d525cac8a3a0687f68ff805759ae496dfd9b07fb15341ff00127877ff0005707e2dd63f822fe3af1abd5f668f3ab4cc5f176a1ff132ac1d564fb643053f55bcfb44d59d3ffe395f2956afb491e5ca5723a8aa5a2b966410f97f4a65c5494566054a2ac515a400eba8a2ac579d2290557ab14ea818da29d4500147faca2a6a0093fd5d6bda49f63b383fbef59107f7e5a3ccfb44dbeb584b940ea34abcff004cdf2fdc4aebb4091ef2ceeae65af2ff00f62bacd3758fb1d9c29fdfaf670d53736a72b334355d3fccf9e2ae36eb4baf46b4ff008f3d9fc159f7567f68f9e2af6e94ae7bf4e57479cc1a7fda358ff62daba8d363fdcffbf56bec7e5d9cf505a7fc7e4fff007dd77d2ea7540d1b5bcfecfd63645fc7fc15d24f1f97a6daa7fc0eb94f03d9ff00686a53de5c57a341f66fed2d95d90099d0f87ecfec70fda65ff8055a824fb3e8f3f95557fb43ed952411fdb347fdd7fabf3ab4e6b199b50489fb8ff96695cd6b979ff3d7fdfadbd4a4fb3ffdf9ae1fc4f789243fbafbf57295d2039df1c4891ff673ff00d36df5e53f153c41fe87fedff0576be38bcfb6790f2ff057917c4d93ed1f65ae4add0d20791d3608ff007d57a7d3ff007d427fa3d712342eda47f67aeaf4a8dee3fe7b49583a74895d7daeb973f72186ba2998d4e8745a6c9e5ffc7c7eeeb6a0d41ffe5acd0ecfee573d6b796d71ff001f1f7ead7f67db5c7cf17dfad8c8eca0d43f83cea3ccac7b4df6ff00f1f15d1411a5c43fec52033a7b3f33fd57eedebcbfc79f69d1e6fb4cb0f995ed7069ff006cff00af5ae43c71e174bc867497f795bd21a3e48f88df1e3c4327fa369f3797b2be6fd5754bcd52f37decde6495f487c4df01a79d3bc50fdcaf14f16e87f6c87ed96ff7d3fe59d7a70d819cd6a327da3c87acf9e44f26a3fb63dbfc9fc150fc927faaad0441ff002d68fb1db79d4797f67aabf6cfb3d6b4c0b5a8ea1fb9af50f0affc4c3c07fbeaf1b9e4af6bf0cde7f63f827fdfa2a6c544e4355d0edacfe7b7ff0081d6a41a3bfee1e2aab3de7990cf5dafc3c8fed90fef6bcbabb9d91d8ebfc0f79791c3fba87cc8ebd5fc31a85b6a1340917fc7d5735e0ef0fa59cdfbaaf64f873e134b8d4bed3e4f97b2b151b8a47a4f8674f4b8b3f3ae3efd75107fae812b3e7d1ff7dbedeb6a0d3fed90ef8befd6b181cf3e813ffc7e7fbff7eb520b3fb1c3b2a7d3acfed9675bda568ffda10f93fc695d54e06465dae87fea5eb6fcbfb3cdfec56a41a1ff00df75767b3fb4435d708099cbda69ff0067d4bcefefd6df969f6c812b42eb4ffb443054ff00634fb641356ca3624c7fec7fb459dd2562cfa7ff00a9f2abbcd4a3ff009e559d3e8f55cb70391fecbfed49a8fec74b3ff4997ee2577f6ba3a5bffadacbd57434d526ff00a754acaa43603cf27b3fdcfdb25fbef5cdfd8ffd7dccbf7ebd4352d0ff00b526dfff002ea95c4eaba1dcdc7faafb95838148f3983c27fda937da6f66ad8f18fc3fb3d6341df6f3796e95d5daf84dede1fb4dc7dcadbb4b3b3bcb3fb3793594e3613958f823c63e0ffb44d7567e77cef5e2977a1de4937d8ee3efa7dfafbabe2a7c2b4bc9a7fb3feeeeabe6fd57e13dfe8f793dcdc7ef1ffe7a562f735bdcf01d47e1ff00efb7f93ff6de9e9e0fb0b887fd221ffb695f42e9ba3ffcbb4b0fdffe0ac5d4be1bbc70cef65fbcae984ac89678a69de07d1fed9fea6babb4d0edb4ff0092cacfccaf408347b6b8b381e587cbfefc95d4687e17fb67fcb1f93fbf5b465711e79a07c3bbcd626ff48fe0fbf1d7d0be11f09db59d9ffc79fc9ff4de99a5785ee7479a09a287ccfefd7a4daffa883fd4ecacaaf41327d2bfd4ecf2618eb53e48ff00e5f3cfff00a675567fb07dc96a783ec71ffc7bc35ce633209ffe9de1f9eb2eeb4bbcb8ff008f8adef9ff00eb9d60ddef8fff008e54b33394f10787ee6cff00e5b57996b9a7bffcb5bcaf50bb8edbed9fe91fbc7ae6fc4d669ff3c6b92bf43486e79aff0067a7fcf6adbd0e34a83ec7f5adbd2a3fdf57047e23aba1e99f0cacfed1a941fdcadbf11f87dfc0fe309d3fe5cae7e74a3e1ef87ee6e2f20fb3d7ad7c5ed0ff00b53c136be57ef2f6c3e7af617c072cf739af865e20493529f4dff9757877c35ea1e03d43f73f63ff009f69abe63f0fea9fd9fa941fec4dbebd9fc31a87d8f5e9de5ff8f2b9fb95b529591ac4fa4fccfb659d735e20b3fb469b53e95a827fcb29aad6a3debb94ae86cf0a9ffe25fe24993c9ff45b9ae7ad6cff00b3e69ebd1bc71a7ffa641fec579af8f354fb1ebd0793f71e1af0f1953d9a38b112b58e7b55f107da2cf64b5c06a578fe75686ab79fe995cf4d5f1788abed24793395c26a8aa5a82e2bc9a9b9991d15354353100a8aa5a2a808a994fa2803b3a28a7579b201b4ea28a800a9a8a2800a28a96800a28a96800ab5feb2f204fee555a9ed7fd1eb6a73e466903a89f5cfdf7fb095b7a1de3de43fefd79fc1fe9137fb15db69baa268fe457bf87ab73a69cacc9b52d3fccff55fc1f7eb9bb5ff0097aff6ebaeb5ff0047b3ba797efd62cfa7bdbfcffc15ecd2a87d05395d0687fe917905b45f72bbfd0e3fdf5d5cff00c02b86d2bf77e7bff1d7a068722470d7a7095cd8db836470ce9fc74ff2ff0073a759c551ff00b7ff007dd13ea1fbedff00dcad39ac067f89f58fb3cd750ff02579fcf79ff1eb73526a3aa799f6a7fe3f3ab120913c983fbf4735cce6735aac9e65e4d6d5e6de2dff004cf22bb5f33fe271a8bcb5c6ea31d6553a199c55c51068f5b73c751f97f5ac80920b34b7ad782471ff002c6b393fd1eb520d42e6dffd552ea4b3474dfb37fcf9d77fa5469e4d7156bf6cb8aeaed6f3fe98d76d3d896745fd9e91cdbe5f269f691db7fcb2aa506cbcf93c9f2e9f059ffcf586aa608d7f33ecff00f2f9526a525b6a90fd9a5fbf557fd67fad87ccd95a107883ecff0027f66d541d8678c78f3c2e925e6cf266b44ff9e95f3378c7e1ff00fc22fa94ff00f2d12bef09fed327fcc36bcd7c77f0decfc41673db7ee637aeaa7303e09d73c076dac7fa4dbfeedff8eb83d4bc0f7f67ff002c6beb0f18fc23b9d3e683cafddc7ff3d12b87d57c07ac69ff00f2dbccff0062ba6131a3e69bad2efedffd7541ff0008bdfdc7fcb1afa33fb0efef21fdec35041e13b993e4ad956e528f0483437b3ff5b0fcff00dcae860fed5bcf9259be4fee57b0ff00c2b3fdf7faeff81d74b07c37b6f27f750d6752a7b446d4cf28d0f4ff00b443fea7e7af4df857e1ff00b46a55a9ff00083a59ff00c7bd7ad7c39f03a59c303c5f7eb859dbd0d7f07784ff00e7ac35ef7e18d2ec2df4dd92d67f83bc1e9ff2d6bb8bbd3ecfc9fb3549c550e46eecdece6fdd575de1f8ee7efcb469de13bcb7ff0055fbc4aeda0f0fa7ee3fe59d74d18ddb39d869d1a79dbffbf5d47f61a7df8a9fff0008ff00ee6b46d6cdedebd7a51b12c83cbaab75a3fda3e7ae87cbfb451f63fa5741273d6bfea7655a82cd2e2b5ed6cfecf45a4740191f63a208ff00735afe5fd6a1f2ff00e795005582cfed1537d8ead411d4b401873e87f68ff72b2e7f0ff9937ee7ee57593d54f2e822671baae8ff00f2ed1567ff0065dcc75dacf66f71feaaa94f1a59ff00d347ace71b8a279cf8c7c2f73710efaf25f13787fed9ff002c6be929ff00d22b8ad57c3ff68f9eb8671b33647c89aaf85fec779fea7fd17f81eaaff65dcfdb3679df3ff07fb75ee5ae785fec737ef7f7f6af5c1ea5a7db7f694f6771fbbd9fea64a819c85de8f6771feb61f2ff00e7b51a5787fec7feaa6f93f82bd1bfe1074b8b3ff9fbfefd624fe137d3fe7b79bfd16b3981269d66f6736cff00589ff3d2ad7976167ff4cf7d68681a7bdc7fcbe7fdb0ad1bbff53b3ec7e6566265583ecd79f2568c167fb9fdcd675a69f79ff2c6b6ff00b3de3ff96df3d690319907cf1c3fea7ccac4bafb4ffcb5adb9ef1fee555bad3ee63fdf4bf7289999c86a3669ff002cab8af1059fd9ff00e5b57a06a5f63ffae95c9ea3669ff2c66ae5a9b1a40e4208ee63ae97c3f67f689ab9efb1fefabbff0008c7e5d7247e22a5b1ec3f0af43fdf415ecf77a5a59c3fea7eff00dfaf28f87323f9dfec57b0eabf69bcb3ff0047af59fc08e496ccf93bc71e1f7f07f8aa74fe07f9e1adbf097881ee21fdeffcbb7dfaebfe2a784eff005cb3dffc76d5e43e19d43fb2f529ecee3f715e773599bd09f32b1f55f84b50fdcffb0ff3c35dff0099f6886be68f879ae3dbd9ff00aeff008f09be4ff72be86d0f50fb47fadff979aeaa12bb379189e3bb3fdcfdb22ff81d7cbff10f50ff0089c415f5df8834ff00b468f756d5f1d78bacfccf3fcaff008fab39be78ebcacdfe147975f7472977feb69b4515f01537386657a28a2b23322a28a28021a2a6a2ae204345145501d854d52d27975e7d4e87411d152d4b590115152eca28022a2a5a2800a28ab14015eac514526025bd5df33fd32ab54b5a5397281b906a1fb9ff00a67e7575d77b3ec7bffbf5e6dff2c7c9aeb20bcff4cb54fe0af670b53537a52b31ff0063fdf6caed6d644f2607ff00569589771f990efa9f4aff0048f925fb895f514a7a1f410973237aee47b3ac1bad63ecf793ff00df14fd5750fdccf735c56bf2797f3c5fefd6dcd73419e63dc7f68ff7126ac7d56f3fd33f75f7128d3750ff00896ceffc6f557cbfb64dbeae267333f52b3f2eb9abb8ff00e7ad765a97faeac4bb8fed15662ce53cbff9e5fbbaabe5fda2b6eefb551ff57512045511bdbfee62ff00beea7ff4c8ff00eb9d1f6cfa51e67d6a3a833acf0c496dff002d7fd1ebaefb65b7935e73e1f97ecff27facaecbec7f6cff005b37975db4b6259b7047f68ff5579e5d6d69d1ff00d36f32b9eb5d0dff00e595e5743059bdc7fad9ab6044975fbbff005b0f97ff005c2aafdb2e63ff005b56bcbb98ff00e9a2555d4bfe98d6731957ccb9bcf92b12ebc276d27fcb1f2ffe9a568ff687d9fe7f2693fb42db54f925ff0044a232b01ca78834fb6fecdd9143e65715aaf80fecff00e932ff001d7a35de8f73a85e6cb2fde7fd34a827f0fa47e45b7fac74fbf5b465703c6ffe103b6fb640971f7dff00e59d41ff0008bd9d9cdf66af57bad3edacff00d27ff22573d6b25b6a979be287fdfada2079aff61a6b179e4dbffa2595b7fe3f5d4f873c2ff639bed3fc15d5e87a3ff6c4d3cd2feeecadab6ed347fed0ff00ae69f723ab3481cd695e0ffed4f9fc9fddbd7a4f873c1e9e4ecb7ae8b40f0bfd8ecf6576be1fd3fecfff0000a0d0b5a5687fd970ecad1d37c3ff006cab56b67f6cae934eb3b6b386b5a71b99cc343d0fecf5d47d8eda487f7b59f048f5a9f63fb457a74a36326107fa1fc953cf507d8fed1febaadd741227fb751ffacad1f2fecf506ca0086d3bd4d53f974b4019b3ff00a3d41f3f935b1f63fdcd1e5d00675a47fb9a96ae7974797401493fd1ea6a9fcba5a00af55678d2b4ab1e7b3fb450672312ee3fb47c915625d47f67f925aeb278d2deb12eff00d1ff00d6fdcacaa74081c56b9e17fb643fbaaf39f10686971fe872ff00c7d27dc92bdc7ca4ae7b5cd1ecee26fdeffdfcae4a9d0d0f119e3fb1ff00a4cbfc145de9ef790fda74a9bccdff007ec6bb5d4b43fb1f9ff6d87fd09ffe5a5715aae87ff08fde417316a5e5daff000495cd3028e9da7fdb26df0feeef53fe59d745059dcdbffc7ecde5d4175669ff001f3ff8fc14cfed07ff0096bfe914400b52469f7ede6f31eaaf97f5a820df79feb66f2e8ff8f79bf755a19cc9e7d43fb2ff00d543fee5677cfaa7cf7b356dc1fea7fd23f78f5ceeab1a7fcf6f9e9ad8c998baad9fd9ff00d55e7fdb3ae5eee34b8ff7eb53528fcb9bfe7a565cf1ff00d36ae2abd0119d069ef1d75fe18d3ffe7ad6269b66f5de7846cffe7ac3fbbac16e867a878134fb6af5af31ececeb8af01e8ffb9ae93c4ffbbb3af4e4ed4cc2aec7967c43d7358fb96f5e31a8e9f737137da6587fd2abd1be21ef8fe7ff00be2bcd7fb42f3fe7b57c462313ec6aa67146a7b391b7e12d53ecf36fafa1743d51fec707efbfdcaf9f7c3ff66bcff7ebd6bc331bdbc3f6696bddc2e2399731ecc6b73c4f68bbd43ed1a6fda6be3af887f69d2fc6175fedfdfafa4eebc4167a7d9fd9ae26ff0045bcff00c72bc2bc71e17b9d63e7b1d4a1bb7ac3349f3d35f33cfaf2bb479b5decb8f9ff008eab55cbad3ee6cffe3e21f22a9d7c2cb7394af4558a2b29015ea2a96a2a800a28a2ae2043454d50d501e81454fe5d2d7148028a7d32b2900514fa2a006514fa28023f2e9fe5fd2a6a28022a4f2fe95728a00a952d4d450341572093fd32a9d5cb493ecf5b5295a451d25a6a1e6574be5fd8eb90d2b504b3ff009635dcdac9f68b3df5f578495d33bb0b2b367357527ee77cbff3dab91f177fae9d3fb90d7513d9bdbffb95cd6a5e13d5754ff963f3dcd7a718dd9eb73e860e8766faa59dadb45fc75d7ff61ff65d9ecaef3c3ff0dd3c1fa6ff00a47fc7d5626ab67e6575a8d9185eecf3cbbed597771fda2bacd4a3ac79e3a9901c55d475973c7ff3c61aed6ea3acbbbd9594868e37cb793fe58d55fecfb9ff00ae75d0cffebab3e7ff00ae34477066df84a3b6b3ff00a695e9369a7a5c7fc7c5701e11b3b9f27f75fbb4ff009e95d5c1b2dffd6de7995df4f6259d2c1a1db7fcf1a9bcbfb3ff00adfb958b6b79ff003ef5bd049f68f92e26ad8923f9ff00e5de6f32b3fcbb992b7bfd1bee793557cbb6cecace6523067b3a83cbb9b8ff005b5b777a1db490fee66acbfecf7b7ff97cacc63e0ff53ff3c3feb8565ea5f66b3f921a7ddfd9acff00d74de5d320d534a8ef37ff00ac7ff9e95a400e6aefc277fe20bc8125fdddaff72ad4fa5a7db3fb36c7f77fdfaeb352d73cb8765bff00c7d3d62695a3a68f79f69b8fde5d3d6c8687ff0061a4767fbafdddaa56df87f4b7fb1c173fdfaabfd9ff00db1a97da65fb89ff002cebbfd36cdef3c9797f7694ca3474ab34adbf312de1aceba93fe5dade8b4b3fb44dfbdaa44b35f4db3b9bcf9e29aba582cdeaada47fc1156f5ae8ff0067f9ebd0a51b92c9ed24b9ad4b491ffe5ad16bb2b43657a5056448558aaffeaea7f32b4024a96ab41253fccfa505216a6f2feb557cca3cca0659a2ab799479940125328aaf4099628aa33de547e67d6824d4f33e94cff5954ea5df4d010cf669546eaceb527ff48aad3d574038cd4b4baa5f25c43f66b8aebeeecfed15c86b91fd9e6df5c9537039ebafb369ff00e8da87fc7ad65dd687a56a9a6ecff588f5d25d4767a859f93715c56a5a3a69ff0024b37975cb3038dfec74d1fe48bf77fec4ff007289f58fe0fb1d74577a7bf93fbaff004bacef32dbc9df2ffdfba8033a0dff00f2caaefd8fcbff00a68f5a96925b490d433ffa47faaf92832a9d0c4fb1dcdc565dd59bd74b75225bff00adfde3d735a8eb0f71ff002c6682832390d4acd23ac7f92b4aea3b9b89b7ff00ab7aab047f689bfdbac6a741a2d69b1a577fa0475c869b67735e81a046971feb6a23ba067b57862449218218abac9f4ffdcfef6b9df03c696f0d74b77227935eabe5f67a9cf5763c03e26e87f6c9abc6eeecfcb9b657bc7c42d42dbf7f5e2baaff00aedf5f98e65cbed7438fa997048f6737da62aeff004df889fea3fbe95c0515e646b4a96c076b3f8e3fb53cfb3b8ff8057157523f9d507fc7c7cf47facaa788954f88ce65d835cbfb3ff96df6b4fee4f47fc49f50ff008f8ff407ff009e954ea3ff0059517b8407ea5a1dcd9ffd34b5ff009e90553ab305e5cd9ffaafb9ff003ce89fecd71f3ffab7ace66856aaf562abd66045454b51500145145007a2797f4a9a8a582b9e45895155ba2b290117faca968a96a008a8a968d95323480515628a80995e8ab14506657a2ac514015ea7b7a5a7799f67a16e8d2068e9d67fbeaf50d0fc3f737167027f0579ce9dae7d9ffe58d7a67863c50fff002cabeb32de5e64997d4bdff081f99feb6bbcb5f0bdb79df69f27ee7dcad0d2af3ed10ffcf4ad5afd0e8518f2e876d33ce3c5d675e6daac75ed7e20b3b3f27f7b5e5fae69ff0067ae5c4c392c6c79aea5a7d647d8dfdabb2ba8eb12ef65796ca473d3c7fc158975676d5d14f1a563cfa7a567219833d9fd9ea97d8fcc9bf7b37fa2d7433d9d5682cd2cff00e9a511034208fccffa689ff3ceb53fb2ee7fe5de1f2d2aac1f69ff00965f7e9ff63b9ff96ba956d11327823fe0f3ab6ed2cdedff00dbaab6b1ff00dbdffb749e5a7fcf6f2eb68126dfdb3fe9b7fdb3a27d971f3d4369a7a5e7fec9e656bc11d6852397fecfb9ff009655567b3b9b8ff973f9ebb29f7ffcb586a95d4759cc6735fd8767ff002f16756a0d2ece3f9e2868bbd43fe78fdca9e0d2fed9fe932de79744068cebbfb368f36f97f797bfc15574ed3ff7dfbaff008fa7ad49ecf478ff00e3dffd2eeab6f4dd3ffb1e1dff00f2f4f5a141a1e869e77ef61f31ebacac4fed07fb9154d048f7935520353cbfdceffe3ad7d03474b8ff007ea97d8fecf5d5e87a7fd8e1fdd7dfada9ee4b362d6cfecffeb6b53e7b8ff5559df63ff9ed45a495ec436259b5055bff005759705e7da2a6f32b68925a9f50a87ed9f5acefb627fcb5a8e7ff00a655406afdb3eb53799f5ac8b7a9f7d052343ccfad1e67d6b9e9f5c4aab77e204b7ff5b59cc674bf6cfad4df6cfa5797ddf8f2dab23fe16654a76133d9fccfad43f6cfad795dafc44f33fe5b55eff84e2b58bb99c8f46a9ab8d83c589e4d6c5aeb89795a19b36bed9f4a87ed9f5ac4bbd6123877d6241e28f3282a0779e67d68fb67d2b9eb7d43ed9feaab461a68264d3ffa45646a567f6886b66b367a8a9b040f39bb8ee74b9bfd8acefed0d2b5cff43d42b6fc7167736ffe936f5e73a8c9677936ff00f57755e7c8d09f52d1ee7c2736cf3bccb27fb8f5069dae5b79df66ff00969ff4deb42d6f2e6387fd77989fdca9e7d0d2f3e7fb1d419cc27b37ff0096b0c3b2b227fb1dbfcff6cad0b5f0ff00d8ff00d57ef12ad4fa3d9c9feb6b4819339b9f54ff009e53560eab78f795d75de87616ff00eaa6ae535c912cff00d4d9ff00db4a262394ba8edae2b2e0d1fecffeaab6aeff00d33e7ff56f53e9b6773715cf310fd3b4f78ebd43c331db495c5695ff004f15ea1e12d3eae91133bcd0e3ff0043ae7bc63ae5b5bc3f66966aef2d36793b2b9ef13787f4ab8a9c67c08e799f32f8baf2f3ed9b26fb9fc15cf57abf8bb47b6b787fe7a25798ddc7f679abf34c67c662ca9453a8af3c455a86af557d9419cc868a28a0cc8e7a86a69ea1a006514557a0d2014514506815151450347a851451591b402a5a2886a2458514558aca4057a9ff00d653fcbfa52d40c28a28a0029945153210514515001490474f82b5e0d0def3fdcaa8c799d8649a5697f68ff555e8de19f03dcc9feb7f7694780fc3e9a7cdfea7ccaf74d2a3fdcffa9afbdca32c8cd734cb846e60f867c269a3ff00aaae92add675d57df4210a71b44ed8ab231755df5e5fae577fae5e5cd701aac8f5e5e2fa1a238ad46b026d95d2ea51d647fabff9635e3b19893ffa3d56ad69ff00eb8d433ecff96bfbba4073577ff4caa08343f326df7137fdb3ae8bfd1bfe5952500647d8d2dfe4fe0a3fb32b63cbff00beeaaff6edb79db3fd7bff00cf3ab8816a0ff4787fd8ad4d3b50b6ff00977fbf5893fd8ffe5e26ff00b6753c1b33fbaada0266f799f6cff9635a3581059dfdc7faa9bcc4adbd2a4fb3fc92cd5b22420dff00f3da8f2dff00e7b7975a9e5fda3fe78d559f4bfb3fcfe77cf43291913d9db49feaa1ff0081d55d463b3bcf93ff0040ae860d3ef2f3fd6feed2ad41a7a69fff001eff00f7f2a46729a6f84ffb1fe7ff0057fec56bfcf710d6a41a7ffcb6b89ab1355d413c9d96f40104faa2697fe8d6ff00bf7ad8f0c477371feb6b9ed2b4348e1fb4ff001ffcf4aed74afb3470d52036f4dff47ff5b5d25ac9e5fcf5c54f27d9ff007d5d469bfe990efae8a406df99f68ace9e4fb3cd52d457766f715e9c361320fb67d6ad7f687d9e1f3ab9bb5d3efedef3f7d5a1a959bdc4356490dd7882b434dd73ed10d79b6a5a3dce97f3d647fc25979670d44e5603d8352f165b59d701affc58b6b7ff0096d5e4be20f8a95e51e20f1c7f6a51095c0f6bd4be3427fcf6ac4bbf8b1f6c87fd757cf5fda0f27fcb6ab56b7956523d5e7f881ff4daaa7fc271ff004dab809b7d52823b9a067a869de3c7b79aba183e227da2bc62d37ffcb5adeb58deb4819c95cf5dff008591f68ff96d5db7867e2425bffcb6af9e7ec6ff00f2ca996bac5ce97f3cb5a193563eb49fc59fdb10feeaba1f0e59a793fbdaf04f01f8e2dae3fd6d7b5e95e20b6b886a91277f69b2dffd556dc1b2e2bce6d7c5891d765a76a94c0da9ecfed1557fb3fecf527f687da2a3fb6552d80c8d734ffb659ecaf00d7347fb3de6cff8f4baafa5aeacfed15e53e3cf0ffda3fd26b86b6e6903cff4edf67feb6b7bfb42dadffe3e269a3aa56bf69ff8f68bee53fcb7f2765c7fdfcae60993f96927cf15e5559ffd1ffd57cf524f67ff003eff00f7eeb3bccb9f3b67935a40c9934127da3fd6c358bae69fff004dbfed9d684fbffe0746a3be4ff5bff7f29cb63299c04fbe39bfd4fc95a9a5476d71ff004c1ead797e67fcb6ad7d374f4ae6e5bb3337b4af0bfda3e7ff00595e9ba569e96ffeaab9ef08e97ff4dbe4af46b4b3aea846c073b7525cc75c56b9a83d77faaf87ee6e3fe5b5701a8f81fecf0ffaeaf0730e6e86733ce75591ee3fd6d721a8f7aeb35fd2d2ceb9a9ecfed1feaabe0f11cdcda99991454dff001eff0025435ca4b1b55eac557a0ca6150d4d456903321aab567cba64f44c0ad4525c54159805145141a402a2a96aa506a8f56a968a2b23a82ac5145448029f4ca7d480ca7d145448028a28a8019453e8a002994fa4f2fed145ecd202d5a47f68af46f07686f589e18d0fed9e457bdf81fc2ffd9f0d7d465797bad539d742e11b9a3e1ff0fdb59ffcb1aeb208e8b7a92bf53a308d38f2a3ba11b0566dd475a559b7727d9eb696c68737aad9d79feb967e5d7a36a5795c1eb95e26200e0351ef589b2ba5d46b94d463af227b811cff00f5dab3e78d2e2a09e34a6797ff003cab302d5c59a7dcacb9e37ff965fbbad4f312b3aea8019771f970ff00aeaa306a096fff001ef0ff00db7aab3c69ff005d1ead41a7bdbc3fbdfb9ff3ce8019fda09255a834fbff003b7ffabab506cb7ff9635af05e259ffd34ade9f5132f695a5ff1d684f225bffc7c4358b06a17324dfba86ba1b5ff004cff008f8ae8448fb5fb1fdf96b474db3b6b8f9e5aab3c961ff3e7556792e6e3e48a981b7f6cfb3ffc7bd675d6b1ff003ef597fd8f736ff3d3ff00d27feb9d2652193fda6f3fe58d416ba5fefbf7b3568ffa7ffd734a82d247bcf922fb95232d5ad9a47ff1f157bccfb47fc7bd626a323dc4df668ab6fc3f66f401b569a7a5c4dfbdae960aab6b67ff002c6ad7fabaeea5b00f82ceb62d74fac7824ad1fed8fdcd76c2561327bbff0047ae4f52d73ecffeaaad6aba8570fa8eb9f67a729923f55d43fb52bce7c4faa269f0cf5afa978812e2bc2fe2c78c3cba517725c6ece27c71e28f3352d96f58bfd8f7971f3cb4ff000759ff006e5e6f96bbcd724b6d2eceb589ba868701fd9ef1d3ed24fdf555d4bc5095893f8812b7819a8d8f46fed048eaaff6c27a5799cfe28ffa6d557fe12cf6aa651eb3f6c4ff009635a369a87975e4b078b3f735241e38fb3d080f6ab5f1025759a1e9f61ae43b2be6f83c71fbeaedbc25e3cfb3de7faea240779ae59bf82ef37c3f72bd0bc0ff001112f2ceb9bd73678b3c37bebc5343f165cf87f5e9eceb9ba99ca373ec1d375c7b89bf755dce87a85cff00cb5af07f01f883ccaf6ed024fb651d4c9ab1dfe95795d1573ba77fa3d6dc17895d31249e0df5cef8823ae8ab3b51a99ec6903c7aea3b68ef3fd1fee7f7299fda09e75773e20d0d2f21df6f0fcf5cd4f676d79ff1fb0f96f5c32099893c96d79feb7f714c9ecff73febaaecf6696736cff975aa53de25bffcb1a7039aa74297cfe4d473c7e5ff00ec95a9f6cb9fb1d625d7d8ef3fe5b79775ff003ce899915a7fb37fc0ff008eba4d02cd24ac5d374b7b89bfe7a57a6687a7fd9e9440dbd0ecff00e79575f07fa3c35cd47fe8f5a9feb2bb16c6902d6a3a87970efae1b52f105b7fcbc7ef12baf9f47fb47fadaf39f18e8f67a5d7818e6d2d0caaf4386f13f8b2c24f922b3ae0e793fe795759a959d85c7fcbe573ba8f85ff00e795e57c0d66dcf53999833c9f68a86b5e7f0fde47ff004d2b3a7d3ee6dffe58d7388af453a9b419ccaf50d4dff5daa1a0cc2aad59b8aab3d0036abd58aaf59cc08aa5a8a8accd204b515152d06a8f4ba96a2a6dbd4c8ee813d58aaf562b1904c29f4ca2a4cc7d24f4b4500145145448028a28a94eda0056869b66f795369da3fda2bd53c07f0edee3fd6d7a782c1cb1352c86a373a1f865e134b7877cb5eb5691fd9eaae95a7a59c3e4d6ad7eb183c2470b4ec8eea71b0514515e944d86550b8abf542e2890197a96cae2b558ebb5d4b65729aaecae1c46c079cea3675cd5d5779aac75c6ea75f3b3dc0e5eefb5529e4ad7ba8fecf5cf4f1fda26a800fed048e8824fed4ff5559d3ff728fb63dbff00aaff00bee81336fec76da5ff00d7d555f93ed9be5a9fed9f6887f7b493eca0917ec6979feabee53ffb3ee6cfe7aa5fda1f67f92b520bc7ff009ede64957102d7f685cdbd5a83544b8ff55fbcacbfb1de49f3cb56ad64fb3ffaaada0074306a0fe4d68c1a87fcf2ac7ff6e5fde533febde6ad007cfa87d9e6fded55f33fe7af9d50c1a7fefbf7b35684166f67ff004d2829157cb4bca9e08ee6de1d9feaeaedac7f6c9bf7b5d6695a5a5521989a6e87f67b3ae86d63fb3d5af2d2dea1f312e2ad01a1692555d4bfd23fd554ff0063fb3d41fda3ef5db0d809e093ecf50798959777ae5b5644fac7fcb68ab413347c4125707a8c7fb9df5d0ff687f68565ddf6a093c53c7778f67f3c55f31f8f3c5973aa6a5f66afa83e26ff00a457cabe268fcbf12505c4f49f87bff12bd37ceac1f1e78c13ceff005d46a579f63d1ebe73f1c78b1fceab81bcf6476bae78c2b91baf1c7975e6b77e207ac89f53f32b749991ea13f8c3ed1507fc259fe735e67f6f93d4533ed8f4f95b03d5bfe137aabff09c1af33fb63d47e63fad5c2362e2ae7a87fc2706bacd03e247d9ebc1a07adbd2f7f9d5155d91a2a3ce7de1f0afe2426a10fd9bceacbf18e9fe5f893ed9fdfaf1ef84125cd9ea55ec3e27d412f3c8ae7a52bb64ce9fb347a4fc32d43ecf5f49f86350af9b3c0767f6886bda340ff4786bae07054dcf6ad3b54ad1fed84f4af39d2b544aeaf4db3fb656c8c8edb4ad612e2b53fd65727a5687f67f9ebacf2ff734a434624fb2b94d563fb3ff00d3dc75d26a3fe8f597f6c792b95ee33949f4ff00fbe2aadde9f6de4d743e65433ffa9fdec348caa74390f2fcbff5535674ff00e91fe8d2c35a3aae9ffbefdd4de5d3ed7fd321d92fdfff009e959ccc8d1d2a37b3877ffacae974eff4cff5b58369a1de7fcb2adab5df1c3b25ac652b203a583fd0e9ff006cae5e7d43ecf36ca93fb62e7c9d95c35311ecd3035eeaf2e6b9ed7ef13508765c573ba95e5ff9dfebab91d5750ffa6d5f3789c65f4339997e20d2d23ff555cecf1d6dcf27da2b3a78ebe6aa4f9ddccccbf9e3ff0096d53ff685e7fcf6a679751d28194fa16bfb63ed1ff1f167556eacd2e3e7b1ff00bf750d36b789895ea1abd546ac08aa2a28a8900caaf562abd613022a28a2b334812d15154b41aa3d2ea5a8aa5ad267a41562abd58ac6403e9945158c8ce63e8a28a9330a4f33e94b52d16b811559b5d3ee649aaeda69ff00c7157aa7c3cf0bfdb3e7961af430783faccf946a3723f01f83ee6e3fd6c35edda1e969670d4fa769e96f0d6ad7ea581c27d56163ba9c6c1451457ad1360a65155e6aa00df515154e7a996c055bbd925729aac695d2ddf6ae5f5293ecf5e5623a01cbea31d735771d743a94958b77257952039dbb8d23ae6f51ff0048ff00a675d5ddc75cf5dc758c80e6aeb65bff00aaacb9ef1ffe78d751fd96f7159775a7db59ff00d377a8033e0bcfb3ff00adfbf5a1fe9371fee565cf79f67fdf793557fb62e681337bcb7ad4b4ff0047ff005b5c87f68fbd4f6978f1d5c493b5ff005954bcb4b7aa569ae7fcf6ad482f3ed15b400b56ba856befb6ae793fd1ead5a6ff00f9e35a01aff27fcb2ab56b67f67ff5b35416bf66b8ae8ad2cd2a900fb593cb87fd4f995af049591ff1ef354f0494ca4684f27fcf2aab6bb3fe5ad4304956a0b37b8aa432d7db2e6487f755cd6b978fa5c3fea6ba19ef12ceb16ee4fb657492cf399f50b9b89aa0bad51edebb5fecfb6b8ac5d4bc3f6d715d14ba889b4dd43ecf0fef6a7babc4f26b3a7ff4386b8dbaf1627dcadcce6737f10ffd23cf78abe5bf88727d8f58df5f576abfbc86be79f8a9a1a5c54ca37410dce1f51f107db347fdd5786ebfa7bde4d5d2eab78fa5ff00aafb95833f8812b826a4a4ac7a14f96da9e757da7bd99acead7d6358fed09ab22bdba77e5d4557974b05329f4568738ca7d328a00b30576be15d52ce3f926ae12aedac7e7d61569fb446f46a7b367d11e1ff001459d9ff00aaaedb43bc7f105e57ce5a1de25bff00cb6af72f01effdc3d7053a7ecdb231153da33eb4f8731f9767057a3799fe87fbaaf1bf0c7882e63d36babf03ea0f24dfe91f72bd18ec79a751a55e5e5bcd5ebde18d73cbff005b5c4ffa1ffcb2abdf6c48e1ad11133dab4dd412b6e0bcaf22d0f5caf42d2af3ed94c206c6a5fe915c9cf1f99fee574ba947fb9ae52791fceff5d5954e8591ff0067a5bcdfbaa93cb493fd6fdfa3e7a3e7acd6c6157a1c36abb3fe3da59aaada59d9dc7fcbe7cf5b777a87d9e6d9a859d51fb1d879dfbaae6919c0d1d374fbcff965795d2cfac6b7a5ff00cb1fb5a7fd34acbb4d1edbefc544f1de7fcb2bcae5ab2b2342d7f6e695a87c97b67f647fefa533fe11fd2a49bfd0b5bffb673d519e34bcff0090843ff6d23ac1d47c3f73e4efb29bede9fdcfe3af9daf59c2f65703475cf09de5bc3fba9bed7ff5c2bcbf528ee6ce6fdec3347577ccfb3ffc7bcd35a4f5760f186b16ff00f1f1e4dda7fb75f375a70aaf6b01ccd435d37f6e68f79ff1fba3f97feda541fd8fa25e7fc796a5e5bffcf392b9f923d0ce672f7154ebaad47c277f6ffeabfd2d3fe985735771bdbffad87cba396c62ccfa6d58a86aa26532ad417153d435664ca93d369d3d3680457a289e4a8aa2430a28a6dbd41a40754b4558a0d51e87454353507a44b4514567300ab155ff00dba2b19016e8a653ea0ce6153411d1047f68aeafc33e1ffb64d5bd1a5ed6563336fc0fe13bcbcf9ff82be85d0347fb1c35cf783b434b386bb586bf4fcb307f568299d34772dd14515f409f33b9dcf60a65155e9b242a0b8a75453efac660413c9556792ac563cfbeb9aa7400baae6b51ef5b77525625dd7057d901ca5dc7589755b7755893c695e6bdc0ce9e4fb4552bad96ff00eb6b6ee2b12ef7d672039ed4b54bcb8fdcf935ceddfda6e2ba8badff00f2cab227d3de4a8039abab37accfb1bd75cfa3ff00cf5ac49f67fcb2fddd0065f977fe76ff00f5757ad64ffb69547ccfdf7eeaad7cff007ef66ffb674099b706ff003ab460df795cbff6e7d9ff00d57dcad1d375cb9b8f93fe3deb7a7d4ca6757f63b9adab48d3fe7b573dfda29eb5a306cb886ba62666d411a5c7fa9ad8f9edff00d5572ff6cfb3d5dfed04ff009ed5ac4b81d7daec93fe5b55afb62495c341ac25c7ff001cadad2a4792acd0dbf92deaf5aea1583e67f04b5a16b67e5c35ad33399cf789af3f7dfbaae7a7d42e74faef278ecffe78d721ae489256a66625d78c2b17fe130f326ad4bab34fb1ff00a9ae4ed343fdf56b00363cc7b8ae3752d0fecf79bebb282f12ceb13c4dae5b7935aa03cf3c47ae797f25797f8c647bcaf43d73649feb6bcf3c4122470d290753c2fc5b1fd9fcfaf2bd56f12bdafc4f67f68f3ebc53c4f67f67ae65f11d31395f92a0a28af596c8a0a653e9940051451401652f29ff006caa74fa960743e1ff00f48bc82be88f01efb786bc1fc31a7fefabde3c311bdbd9d737532a9d0f68f0fde7fcf5af60d0f67935e2be1fdff63aeffc3178f5a40c19ea1a6de7995d5e875ca787f67fcb5aebed7fe9956c8ca6765a5489ff003c6bb8d3bfe995797dac9f63aedbc3f27994cccecb51ff008f3ae36eb6575775fea7f755ca5dc75954e804f69febab4351b3b3d42cfecdf6cfb25d7f04954b4a8fec70f9d5c4f8e358b3f104dfe85fbb7fee573c9d809e7d2f55b79a7b3d4ffe00f56b4dd0ef239bc9b8ff00beeb3bc23a85e7fc79eab37c9fc13d7a3e9bbedfe4b8fde3a5609dd8181a8ecb7876573d3de5cd9ffaaaef355d1edaf2b94bbf09dcdbff00c7bde57ce631cb9b43481560f145cffcbc69b4ff00ed0493e7b2fdc3d625deb1736737fa4562dd5e25c7cf1578556bf2bb4c266d6b9a5ff6c7cf2c3f64bdfe093fbf5c3dd46f6f36cb8fddba7fcb3adaff008482e63f93fd6255a9f58b3f1243f66bdfdddd7fcb19eb8aa4e356d63338fa8a7d95a9a8e9ef6f5893d73b8d8ce6105e5cdbff00aa9bcbab5ff0945e7dcb8fde553ace9e852b18b343ccb393fd6feeea0f2eda4ff553567d36b68cae6532c4fa3ffd36ac8bbed45c5559eaccc27aab562abcd512020b8a8e8a5ff59594804a9a8a96a0d2058a28a4f2eae274c0ee6a5a8a8ab3bd96e8a86a6a8908968a83cca7561302ed3eaa4356a0a51dcce668dac75e8be11ae3b4ab34b8af60f07783d24f21ebe832da5ed2a5fb19753d0343ff00535d4d50d3acd2de1abf5fa6538f2c523be90fa653e9956cdc2abd58aaf52045514f44f25559e4ace60413555baa96a84fff004c6b9e7b014aefb5624f1bdc56c799f67ac7bbbcb9b8ff0055f72bcfa8065dd59d62dd6c8ffe5b5686a3fe91feb66f2eb9ebbd413ee5bc35e7c80cb9e4793e48aaacf225bffc7c4d56aeef1fee79d591f6cb6ffae959811cfa827fcb2b3ffb6959f3ff00d35ada9f6793ff003ceb1751ff0048a990189751a5624966f71ff4cd2ba29eceb23518d3ff00b5d401953de7d9ff00e3dffefe5647facad79e3b6ff96d59f3de5b5001049fbead08365bfee7fe5eab23fe9e7c9ffae34cf33ec7f27facbafe37a04ced74e93cbff5bfbcaeae0d43ed1feb6bcced2f2ba4d3750fb3cdff003d2b6a66333acffaebfbbaabfda17327c96f53797f68ff008f8ac5d7358fecb87f755d8f6081b56ba7a7dfff00575d2da59a7fcb29abc520f165e5bcdfe9b79ff6cebd43c25a825e43fbafbf5701ccebff00dba9ed6f3ed158ff003d68da5e5b47ff002dabaa06455d46f3ecf36cac49ff00d22b5355d9aa4dfbaae475591f4fff00555a006ab27975896979fb9fb4d55d57507ff978ac89f5078eb4801a1a9496d25729ae7ef2b47fb42dae2b2f52bc4ad00e0fc411bd79ceabfbb9bf7b5ea1ae6a09670d797789a4fb67faaace6690391f106ce2bc97c4fa7ff1d7a9ead5e7bafecae794f95a3789e4b7767e5d67d75da8d9f995cd5d57a34a7ce86cad4514574087d14cab3691f994010c31f986ba1d2b4f4358fe5d751a1c75cd59e83474be1f8ff7d5ed7a06cfb1d78be95fbb9abd0fc3f78f5c51958523dbbc3179e5c3fbdaeff43bc4b7af11d37587af46d0ef3ccae884ae73d4e87aa69dae3dc4db2bbfd0f58fdcd78de951dcdbcd5e93a55e7975bc0e699e87a55e7da3fe58d7a16811a475e7ba1ffa643fb9aee743bcfb3ffadad9199d2ddffd31aa9ff5d699f6cfb67c94799f63ff008f8a89ec067789b5cb68ecf67fabaf39ff00845dfcefb4ff00acad1f13d9ff006a4dbeca6f93fe79d3fc311dce9f37fec95c32dd1a40dbf0c69eff0072e21aef2d7fd4fd9bc9ff0072a7d2b507bc87f7b67e5d3eeecee6e3fd55397c25983a96ff00fae7fdf8eb1278eb7aee3fb47c9ff2f5fc0f5c06b91dcd9ffaaaf9ac64f90668ddde7fcff59fdad2b8dd47434fbfa67ef13fe7854dfdb173ff002d6b2eee47b79bed36f5f2b527cec0c9a656cf996dae7fadff0044bdfeff00f7eb167ff47f925fbf5cf233987db1fc9ff6ea8cfb2f3fdfa3fe3dff00d5555bbd979f3ffabbafe3a2273cccf9ff00d1eaad5abb93ed1546e28918b1d55e8aabe67d6ae9994c7dc5559ea6aaf5a9995ea2a96a2a008bcbfad254d45069025a28ab151235414fa28a226d03b1f328f32a9d4b448ee2dd4de67d6ab5159484cb1451454089aae5bd53ae9bc3f67571873bb01b7e0ed0dee26afa27c2367f67b3ae1bc0fe177fbf2d7ad69d1fd9ebf41cab0fece0d8d6e5d4a968a2be953bab1d510a28aaf4d9413c9557ccfad2556f32a5804f25674f79524f2555ae3981567bcaceba93ecf56ae2b12ef7d79f506827d62b16eef1ee3fe5b55d9f4f792a0ff845d2dffe5b57172dc19c8ddc6f2563ea5677f6f0feeabd027b3ff96359775fe8f5128d84707fd9f7927cf71fbba8e7bc7b7ae9752ffa6d589a949fb9ac6407293c89e77ef66ad1b4d9710feeab2eeecd3fe5ad1a6c8ff73f828880fba8de4ac4bab3fb3d75907ef219eb3aeecd2a2a740386ba8dee3fdcac8fecff00326aecb51d9591f25bc3f6cffbe2b2032eeff77ffb25677c96f53cf2799fefbd3f4dd0d2dffd26e2aa22668e9dfbcff55fbb4ad483504b3ff55583a8ea9fc11555b4df5b4493d6b4ad413fe5ad55d563f32b93d2af1e39b7dc4dff006cebb6ba93ed9675b400f1ed7343fb3f9f732d1e07f8b16da3de7d9a2ad4f13e86faa7c95c541e0fb3d2e6fb4dc56f1067d4fa56b9ff0009043be2ab33e969ff002da6af2ff879f112dbfe3cff00d5d7a07996d71f3f9d5d673f5342d7f77fee7f0567ead53796ff00f00ac5ba92b4801cd6ab79ff003cab9ed464fb3d9d6d5d6fac1d4765bd6f003220ae6b55d41e3bcaeb3cc4b7ff005b5c9ea579f689b7d6869039bd724f32b83d4a4aeafc4da87f057077527fcf2ac2af4364721e2dd53fe58d79ccff00e91357a3789ecd2b90ba8fecf0d73948e52ea3fe0ae6eef4f7f3aba89e4fdf547e5fd6b4854f6651c54f1fd9e89e3ae9751d0f15520d2dfc9d95df1aca689662dac7f689ab520d3ff7d53e9d1a47f25749a759f9959d59f6118b6ba77fcf5adad2bfd1eb46ee3fb3c3557ec6ff00f7dd724e571a352d644f3abafd0ef3cbae27fb39fd2ba8d2a37b7ff5b59833d434dd9f7ebd0343912486bcbf4dff0048aebb4391eba297531a9d0f57d3b50f2ffd6d773e1fbcaf3cb5ff004c8764b5dcf8663fb1ff00d34ae95b9cd3d8f5af0c69ff00beaf5dd3b6793fbeaf22f0c5e797feb6bb29f5078ffdcaea89cc8eafcb4ff977a7dd7fc79fef6b3fc33b350877c3f7ea3d5758fb64df63ff00577495153a1471b6ba7ffc4cabdafc31a7d9f93bfc9ae1b4eb3b6bcffafaaf4ad2b4ff00b3d281ad336fcb4acebb8fecf5b56f47975538dd1a9c3dd59a5c7fbf5ca6ab1ffcf6fbf5e9fa969e95c56a31fd9ffe58d7818fa3cf1133c6f5cd9ff2cab9edf5e8de31d0ff00e5e6de1af39bbafcfabd3f673119d3d1f6cfed08765c7dff00f962f4cb8ace9eb9d19cc827dfff002d7efd67cf25685dc9f6c877ff001a562cf25339e615577d1557ccfad5c4c584f50d1552b58994cb155e8df5156d13325a8a8a2b589a4028a2a5a246a82ac557ab15948da03ea5a8aa5a227440dba96a2a2a0d8b5562abd58a890053e99566d3fd1eb296e84cd4d36cff008e5af46f08c69e75707a77fa45757a6ea9f67ff555e9615da423df3c3fb39aebadebc97c0ff6cb8af57d3abf47c1bbc0da9f534a8a2abd779b8541714ea8bccfad26041e67d2aad58aa33de7d9eb19811f989f72a19f6547f6caa53ea15cd53a013ecaa950cfae3ffcb2acb9f50493fdfae4a92b580d4bbbcfb3d60dd49ff3d689e4ac8ff595cb277026bb92b22ea4b9b8a9eeff00f1facb9e47acd805d495893eff00f9e3f253eeef3fe7e299f6cb993fe5b7c958cc0c4bbb3ff9eb5916bbfceff62ba1d46b16eef13ee56603e7d62dadfcfac1fed04bc9ab3b5593fe78d16b789ff2ca1f9ea6404f77a7f99591a959bde4dff3cd12ba49ff00e9b565ddc97327c958c80c582cff00ef8a827ff48f922fb95775291edfe48ab167d52e68880ff29ea7f2d2dfe79a6aab059f97f3dc4de6551ff8f89aac4cbde63ffcb2aee340bc7f27fd22b91f32cf47877cbf7ffe79d55fed4b9ff8f9ff005157124efeee3ae2b51d3fcc9ab6b4dd71ef21ad1fb1d6d0291c1c1a7ff67cde77fac7af50f0978b2cfc9d9fc75c1eb9fe8f0ffa17dfae5ed23b9d3ef3ed32cd5b2958523e85bbd41ef2a95dff00a3c3b3ceac4d0fc409fd9bb3f8ea7ff6eb6a72b9cdd4b5f634f26b90d73ecd1d6ddd6a096ffeb6bce75f91ef26ff0062b6032f55d43ed15cf4f23d685dde571ba96b0f47358da9997aae9fe64d556eacfecf0d433f883ccaab3ea1fb9a39cd4e4f518fed15c8eb9227dcaeaeea4ff5f5c6dd496dff002d6a5cae06741a7d4ffd9ded507fc2516d1d65eabe2cff009e5428ca5b01b7a9476dff002d6b94d575cb6b7ff55597aa7882e6f21ac3aedc3d1e5bb9016e0d43cb9b7d76b69e28b68ecebceaacec7aebab4e2d2b01ebd6bac59ea10d6a7fa37935e3da71b9b335d2da49735e5d4872303bcb48edab53fd8af3cff4baebf408ef2f2b9a72b099d769bf69b7aef2d3fd221ae534adff00f2d61aef34ad3ea6333199d2f84754793e496bd1b4092e6dff00dcae37c3fe1ffdf57a6687a3bfdcaf4694ae734fa1d7e9b23f93bebd1bc1d27da3fd1af7ee5721e12b3fb3fc971f72bb6fed0b6f0fff00d34ad652b191d26aba7ff61c3fe855574a8ffe120f9e5fbf58bfda17379fee7f05751e1fd3fccf9ff8e9c25766b03b5d0f4fb68ffdfaea2deb234db34ad7f2ff008ebb6274c0bb6f4ea2a5aa3433aeab8af1048fff002cabb5baae4755d1edae2bccc745b8ab18d43ccaeaf2e6ce6fdeff001d715ae6cbc9bfe79bd7a1788347b6fbfe7579cf883f79feaabf3cc545a96a7175394bb8deceb3a792b47fb43f82e3ee565ea31ff1c5f72bc69ee329799f67aa375fe8f4f9eb3e793f735999cc6799f5aab3c944f25415a40c58541e6547456f11127faca8ea1f33e94b5d14c0968a8aa5ae888d1354b5154b566d00a9edea0ab15123a203ea5a8a8a226a8daa9aa4f2e96b9c64552d15154480b50d5ab492a854b0d475133acd364f326af49f0768e97135796687a7bdc4d5efdf0f3c3eff007ebe8f0147dabbf624f46f0fe97f67aeb6a85ac7fb9abf5f7b4a1c9148ea86c155e6a2a2ad8d086a2a4f33e95567bcace6ec8086ea4acb9f7d17525677db3eb5e6d59807d8ff008eb3a793ecf53dc6a1ff003dab3bed9f68ae39caf60193c8ff00f2ca8f92e2a0df53c1ff004dab1024f2bfe9b547f634b7ff005b47db2dae2a97db3f821a008f558d2b1678fed15a1e5ffcf5aa577225bffaafbf4981cbddd9bc7ff1f159d3ff00a3d6ddd7fa47fc7c5626abf66ff9635cf53a019177be4ff96d583a8de3c74fd4a4fb3fcf583a95e5e5c7fb9580d19f3de279d56b4ed43f822fddecfbf25675dfd9ade6ff0047fbf557ed9e5c3b3fd5d00ce86d2f124f3de59aa4b4d613c9fdd7fdfcae43fb43ed167f668bf7744fac5b5c7fa1c5ff007f2811d0cfb3fe5acd59ff00634fbf4fd363fb454f3ffa67c9fc1512039dbbfb4ea9351e65b697f245f7eb62ef669767fedbd71977bfefd481a3ff001f137da65a64fa87db26ff0062b22eef3f82a6b4b3fb4502674ba6ea1ff7c5749a56b9f6cf93fd5a5701e67da3fdca9f4dd63f7dfee56b095893d27cb4ae7b52d3d2aada78a1e4f93f82b6f4d912f21df5af381c86a57973a5ff00aaadbf0e78b3f825aabafe9ff68ae5f52d3de39bf7552e7a81e99e625c562eb959da55e3e9ff00256dff00c7e57446573399e7faae9f735c06aba7dcd7b75d59fd9eb9ad474fff00a63533958207877fc23ef4cd73fd1e1d9157ad5d787eb2ff00e10f4ff96b59f39aa3e73d73ed92435c4cfa3de495f467883c0ff6cff55591ff00083fd9e1d9551adc8ca3e6dbad0ee7ceabb0784ee7c9df5ef707c334b8f9ead5df85edadff00d1abd0facbb2b05ec7ce10785de4ad483c27fb9af67ff845d239ab427f09a51f5998739f3f41e0f7ae92d7c1ff00b9af57d2bc2f56bfe117fb3cdfbda8955954dc39ae79b5af81fccaeaed3e1ffd8ebaf82cd2cffd5575d69669aa435998d495ac70f6bf0efed15d5e95e0f4b3876575fa6e9ffc15d0da697e656918dccb9ce5ed3c2ffb9ff535d5e87e0fae974ad0ebd43c25a1db79dfbdae8a70329bb983e19f03f97feb6ba59e3ff847e683cdff008f57fe3ad4d73544d1e6fb345ff00ae1b52f103de7c9ff0090eaa4b945137bfe120fdcec8a8b4d41ee3e497ee560dad757a7469506a8ebfc3ffbbaf46d0e4af39d2a4aef34a8eb7a433b9b5adbac0d3b65bd6a41257a50d8d206854d54e092ae5686836e2b9af106ff0026ba1acfbbacea2bc598d43c13c5d25cf9dfbdae02eaf12bdd3c5ba7a495e2be27d3eda3ff00555f9c63d5a6ce2ea70d7525677db3ecf56aee3fb3d62cd5f30f7644c35192b2e7929f3c959d3c9512330f32993c9504f2533cca8258b49e6553a4f32ba68f5113558aafbea7b7aed88d0f82a6a8aa5aee81b409a8a868a723a204d52d45572deb291aa24a28a288833aaa28a37d738c2a2a28a890055cb4ef54eb5b43d3fed9353846ec4ced7c07a3fdb2f2bea0f0cd9fd8e1af1ff01e9ff63f22bdc746ff00535fa26531b5360b7352ac557a2bde3aa245514f52d43499457ace9eb42e2a9d672d84cca9ab2eea3adabb93ecf5913de5705404625defff009e3e654f047ff4c68bbd43f73fbaaabe65722dc63fe4a3fd5d3693fb3ffe7acd431320f96b3be4ad1f312b16eeb09f404413ea16de77ef6b12ef50b68eaf5dc89f73ff0022571b772573cca44daade7f1f9d5ca5d5e7efaa0d4644f3b6565cfa7dcffcb5fb95848a27fed07f3ab3aeaf12cecff7bf7eadf99e5c3bff00ef8acafb1bde4dfbd87ccfefd112599df63b9b7f9fcef337d737a8d9eabe77fa9f32bbff00ec74b7ff007e882cdedea847290686fe4fef68f2fecf36c8abaf9b65735e5bc937eea8035e09134bb3fdd5177a87d9e182a1d563ff004383fd8acbd46f2a64017727da21df2d63ddc7e5c3b2b63fe58ffe8159d059bde5e6c97f82a00c4d3b4ffb44d56a7bcfe0a9e7bcff005fe55627d8ff00e7e3f8ea2401f6cfb67c917dc4fe3abdf269f67fefd51fb625bfc91543a8de3d4816bed9ff007c56be9de207ff0080572fe67ee76555f33ccf3ea24077ff00f09027ee2a7f312e21fb4d701e63fad13eb9f6787645429582d73b5fb67fa6574ba55e2578dff6e5cfdcadab4f147d8eb45333940f5d9f4f492b16eb4fae6b4df1e7da26fdefdcaeca0f105b5e7c95ac257326ac62cfa7d63cf675de4f67ff003cab167d3eaa42389fecf8eaacfa7d75f77a7d676a3a7fda21a512e0715771fd9ffd4d60cfa7fda26df5d7cfa1bc7355afec7f2e1ade06879fda697ff3daad7f678ae96eacfecf4cfb1fda2b4039a82cfecf5a9a8e9ff688607ab53e8f5b7069ff0068b3aa4673393ff844fccfdf56de95a1bdbcd5d5e9b675d2da785fed15ac0c998b69a1fda2ba7d0f437b89ab520d2d34bff5b56b55d53fb1eb5423a283474b3d377ff72b93d4bc51796f7905cdbd65dd78c2e6de6dff00c0f54bccfb67cf431a36eef5c7d63fdfabb06cbcf92e3fe3ebfbf5cba7fa456bff00c7c43594cb46f41fbbf92b62d64fb3d7356b78927c92d6f5a6f8eb33489d9695ac57a4f87ef3f735e4ba757a068127975d787dc999eada7495a90d729a55e574bf6caf6e3b0409aac551f32a6aa34279e4ae5f5cf10259d6dcf25795f8e23b9bcff555e6e367284558c6a06abe2c4b8af33f106a0944fa3fd9ff00d6d60ea5796d6fff004d2be0b1537396a7175312effd22b94d47bd6a6a527da2b9a9ebc3ac32acf25559ead4d59d3d79f2dcce6559aa2a966aab4477316150d153576c044b52c35154b5e9521a2dd4b50c14b5d313aa97525a28a2891d0496f53d54ab70d6521a2c5155f7d1594819dad45451506b30a28abf691d5c4cc641675dcf847437b8aced36cd2bd43c0f67fbeaefc2d2f693133d0fc1da1ffa87af42823acbd0ecfcb86b6ebf46a14fd9c11ad3ea15151514f5d06e413d368aafbe931322a8e7a49e4ac8babc7ae7a8ec493cf79589752254177a879758bfdb16de7579b526522ecfb2e2a0fb17fd3e5559f58b3acfbbff004cff00553563ce33a1f32da3ff0096d5913ea0927fcb6acbff00963fba9ab3bcbb6b79bfd4d44a5703527bc4acbbbfde7fada27ffa65f72aaf99f5ac98156ef7d72f3e9efaa7faaaef3fe58ffa9aabfda0f1fc96f67e65481ca7f61db59d559f4ffb64dfec56f4f66f7137ef68f92dff00d5500735fd87e64dbea7ff008f7f92b6ab3e7ffa6b401cecf27efbf7545df6abd3fd9adeb1351b8ff9ed59cc0c8bb91ee3fdca9ad74ffe3acf9ef2b460bc4a202613ff00a9ff006eb93fb1f9935779aac7fc75cf79696ff3d1304677fc7c7fc028bbff0047a35293ec737fb0f59de27d43ecf0ef8bee563219837779e64df66ff56895913c9f6caa5a1ea1fda179b26adaba8ffe795632021b4b3fb3fcf2d413c9f67abb3ffa3de7ef6a1fb1feff00fd8a9032fcbfb3fcf2d5d8ecfecf0ef97fefdd1f25c7fa4d55bbdf710d4480ab7779f68aa9527fd76a6695ff002dde5a81a1ff00f2e955aadd4b047f688693288ab5b43d63ecf37ef6b127a2d3fd755c0967a841e3cfdf7d9ab47fe12cb692f2bca2ebfd74fe556741ac3d686528dcf72fed08e9fe5a5c57964fae3ff694095b5ff0987d8ef3ecd551326ac75f3e9f5567b3ae7a0f187da2b42d7c409243beac44f3e8fe65410687e5d5ad37c416d79ff03fb94cff008482dbceff0072ae201fd975a369a1d41fda09790feeaaaffc250f710f93fc75d1003a5b48edacead5df882da3876455c34979f68ff96d507facae9808eaf52f103eb167b3f8ea4d0f7f892cfec771ff001f49fea6b9a83fd1e6df5a3f3dbde4179156a8a45afecf7f267b6961a23ff47ae87cbfed4f9ff8eb23cbfe0a1971346092b43fb3fed1597e5d5eb591e9164ffd8ef5a16b1fd9e9ff00eb2a7837d00743a548f5d7e9527975c869bff4caba8d3b50f2eb5a7b99ccf46d2af3ecff00eb6bac82f3ed15c4e95796d257456f5e9533336bccfdf55aae77e7ad2b592b4901a13c75c6f8823fdcd765feb2b8df13eff27fe7a57062be132a9d0f25f1069ef5c36aba1fd9fe7af40d72f1ff007f5c1eb978f715f0589f88c8e52ef6475cd6a35afa9d73d75fe8f5e2d6020b8aab3d5ab8aa5715e7cb70209ab3eadd45447733990d4d50d4d5e8523325a96a2a2bd281a40b96f52554ab75b44e88054b4545448d512d49e6556a2b9ea74193799f5a3ccfad56a2b9e4267a1d14515d07492d68da7fa3d55b7abb6b67549db43399d668166f79357d05e07f0bfd8e1df5e51f0e6cd3ceafa174a8ff00735f619652bc5b33ea6a411d3a8a2bea56d63a10553f33e944f2565cf7949816aa2aa9f6cfad1f6cfad48093d67cf1bdc55df33ed154a792b2a9d00a53e9e927fadac7bad3edbfe5956df98959d715c7576291833e9e91d644f1dcff00cb2adbbbed59775675e7cf7199d05e5cff00cf9d5afecffb3ffa4cb4cf9edfe4b7aab3c9aaff00cb6acc0827bcff004cd914d557fb1dff00e5e26f329f0496dfbff361a27bcb3ffae74005ac696ff3dc7dcaad3ea1ff003ef379695a13e9e9e4efacb9e3fb3fcffc149819da96a959f06fa9aeacfed159f77b2dfc84fe3a901f77aa7fcf6aabf6cfb45559f50b68e6aabe5a49ff002daa26055babcfb3fcf2cd5cf5dc97971f3c5f72ba1fb1db5c7c9feb2b3a7d1d3fe597dcacc0e6de3b9b7ff7deb534d8ff00d0f7d5abbfddff00bf56bfe3cf47ab8005dde7db2a8ddc9f68f22a1babc4b387f75fc7556793ccb3a2626175b2e26dff00dcaf39d73c50f710dd27f056d6b9aa7d9ecf67f7eb809ffe4253ff0072b190e268f83acfcbf9ebae9e3acbd0ecfcbb3ff62b6e792a06ce7bcbfb64d56aeaa69e34b7acf9ff00d22a24232eebfb94fbbff47f93f829975ff1f956aee3a90313cbfb47c956bfd8ab507fd32a5a008fcba7f97fb9a3fd8a9a80322efb555d0e4ff4ca35593cca6787ff00d1e6a068b53fef3cface823fdf4156aee3fb47cf4cb48fed13414145d823fb46bdfee555bbff004cf3dead7fc79cd3bff7e99ff1ef350041692568c127efbf7547d8d3efd1b2aa24b346093cbf9e2fdc3d6a6ab67e64df6cb7fb9735855b3a55e7fcb9cbfc7fea7fdfaeaa7d486169fbb9a07fee55ad47fd0f52d9fc0ff72a19f6568796979a6c0fff003edf256c22afd8d3efd5a86882cead7d8fecf5480b569b2b62d364958f0efabd6b256b03399d26951fee767f1a568cf67fc75916b27d9e6ae92d2f3ecf36c97ee56a8cca5069ff0068a7fd8de3adb83fd77eeab47656d0032ed37d6dda7fd36a82ad4166f27fa99ab4035ed34f4ff9635b769a5f9959da6d9d74b6bfe8f4d46e055823b9d2eb7b4dd72b420b34bc86993e87e5d6d056036ed6f3ed15a90d73ba759d7456b1d6a8027bc7fb95cd7882f2b6f528debccbc5bfdab1ff00aaaf3f1b2b40caa74397f1348fff003e75e73aac9ff3d6baf9f50bff00f96d5cd6bf27990d7c5d677464707a8de565cd56b528ff007d542be7aa6e690229eb3a6ad19eab56120994aa2ab5515246454a482ac4f50576d333996e8a8aa5af4223812d5baa9456d13a205ba49e9699566827994b4552ae1a804b515151579957703d6b6558a28af44ea2cda475bda1f87def3fd55626995ec3f0f3c3ef795dd84a3eda763399d97c39f07fd8ebd6ad63fb3d67687a7fd8e1ad6afd270f47d8c120805453c944f55aba4d0af3543e5d5eaa33c94980c9eab536e2a94f25b563300f33fe79555f2dee28fb67fcf2fb94cf33fe9bd6601feaea8cf23d5e9ffd22b3a78eb2a9d0083fd6555ba8ea1bad52e6deaacfaa3fdfb8ae4a9d0a44775fbbff005b54a7bcfb47faaacb9fc4097937efab3bfb42c2de6fdd4dff006ceb9a60cd89e3ff00b66f5041f66b7ff55ff7dd55fedc4bcff55556ea47ac98233b52d52e639aaacfac5433c8f790d54fb659ff00cb2fde548c93fe3f26fdd565ea3fe8f5b7fda9f68ff55fbbff00a69597e5a5c4dbff00d65006769da7fdb3ff00b6568f976d6ff2450ffdb4a7cf2565cf23ff00d74a00827d9f72b3e7d3eaecdb2a0ba93fb2fe797eff00f025004fe5fd9e182a96a5fe8f351e63de7fecf51fcffdb13f9b4018bae7eeeaacff00bcf92ad6abfebb7d55f33ecf0efa996c071bafff00a3cd5833d9fefbfdfaea278fed1fe932d51d577f9307955cbd40d4f2fecfa6d1f63fb3d68dac7ff7c555baff008fcad9ec0674f27fdf758ba6c9f68d4ab4355bcfb1d9feeab3fccfb1ff00bf59c8027fddeb13bcb506a579fea1e993c9fbefb4d51fb67ee7ecd50068c1fe8f0d107eefe7aab049fc156bcc4b78767faca00b577fbbff007ea94ffe8f0d5abb92b1e0ff005d5121a296a5fea68d0ff77353f55fee7f05169fe8752516bcbfdcd320ff005db2b520ff00488689e3fdf415512585dc89e74092d33cbfb3ff00aafb94fd4bfd226df59da6ea9fbed95b44468f97f68a3cbfa54ffedc547fc7c7cf5401ff005d6a4a9be4b887fdba820fb4dbfc92d6901335e7fde7fa4dbffdb6ad1d2bfd23cfb6ff009f9aced3a47b39b7ff0007f1c756a7b3fecfbcfdd7dcfe07ad9121f63fb3d68e9d79ff003d6a0bbff47bcdff00c0f53fc9e75302d417895a9feb2b2fec7f68a9e0df6f5a400da824adbb4ef5cbff00a4d4f6b25e475aa3399dadac95b76927da2bcfed35cb9ae974dd73ed15b40ccebe08eb4208d2b16d750ad78365c56f01337ad23ff9e55b76b5c9dac75d15affa456c8474969fbbff00555b706cb8ff005b5c841f69b7ad4b5d41eb7801d2ff00aba9f7d675ac9f68ad7823add018bae48f675e5fae6b17327fcb1af50f13ea1f6386bcbf52d52c354ff8f8af9bc76e072777e28b6fb95cdddde59c9ff2dab6b5cd0edbfe59579eeaba3d7c856dc465eab669ff002c6b9db8ad19e37b7aa15e3d7e8321aad715668ae533999f4558a2b7a673cca55155a9aaaffabaeea5d4cc2a5a8a8aeb89a40968a8aa5ab3a204b454551799f5ac6a1aa24f32a3a868af32a0c9aa1f33e94799f4aab5e7cf703dd6a682cfed14f823ad8d2acd3ceaf7610e7763499d2781fc2fe64dfbdafa1bc33e1f4b386bcffc07a7d7b0e9b1fd9ebeef2cc37247982059a9a8a86be9252e6491a10cf4daaf51540093c95973efab33d2f97f4a4c0cefb1bffcb5aab3d9a56bcf1d67cff66b7ff5b5854e80677fa3d1f2d13c89ff002ef0d675dffd76ae69806a52247feb6b2eea44ff009ed50ddd9fda2b3a7d0d2b9aa74019f6cfb45674fbee26d92d743069fe5ffb947f683ffc02b1291c6cfe1fbcbcaab3f85dedff00e5b576d77227fcb2ac49f7c9ff002c6b398ce5278efece1d9e4d627f6a5e4737d9ae21f92bbff2fed1ff001f137fb958b771bdbffb9593039093fb9feb37d1f259d9ec8ab46ef4b7b7ff0049fe0ffa615893fdb249b67935205a9e4fb47fa35bd4f07fc4ae1aa377fbb9b679df3ffcf4a65dc9f63f925a0027bcbc93e78689f50fb1c3fbdfbf5553fd4cf73351059fda3e79bf78940104178ff7fc9a9ffb3fed937db2e3fe015a93ecfdc79bff007eea0bbd512387f750d5213294ff00e8f0d413d9a7fc7ccb3516979f68fdf4b45dff00a459d324e7b5cfde7fb954bcbff43ff6eaeeb9fe997902455575193cb8765265232e7ff4786b23cbfb455df13ea1f678764554ad7fe41b0d483342d3fe3cf6513d2daffd32a8f5193ecf673a5049cf411fdb2f365675d7faeff72b6ed3fd1e1ae4f52ff48bcac6a74290cf33ed1a97fb145decfbf441b2de1aab3ffa64dbeb964522d7f687fcf2aabf6cff004ca7cf1fd9e1ac5ff96d51d4a3b59ffd2299e5fd9ea0b4ff008f382b46effd22ba63b12cc7ba8ffd45327fddde7fcf4adbf2ff00e795676a51fd9e6a24082093ecff00ee55ab5912e26acb9e3ff9e54798f6f0dd511066a7db13cefdec3589751a4936fb79bcba67db3f73fbaa27ff0048aa116b4dbcbc8e6fded6a4f1bdc43bedeb120a9e0df6ff00eaa6a00b5048f6f356a7f685b6a1fefd60c179fbead19e3fdf7da6de802efcf6ff00eb6ba1d3750fb443f6397fe015cdfcff007e1fde569c15b404ce8a08ff00b634d9edbf8eda882cdffe5ad41a6ffaefb4ff00df75a33c7f639b67f07f0574c092782cdffe5955a8237aab05e3d6a41795aa2261fe91562ac55a8234b8aa322af97f5ab569a7ff00cf1a9e0d3e9f047f67a00b769f69b3ae86d350f2eb3ad3fd22b4608fed15a400e92d750ae974ed42b868ff00d1eb534dbcf2eb783b3133d1ad24fb3c35a31ffa45735a56a15d2da7fa4577425711a3047f67adab4ef59f6967ff003d6b53cbfb3d6c80c4f10496d71f24b5e4be23f07d9ea9ff001ef3576be3cd61f4faf15d57c4173f7ede6af97cc2494b533985d69779a3d735aaea173ff2d6ad4fe3cd563ffa695cf5d78b3ed9feb61f9ebe52b4e3d020519b6562dd475bd7722495973d797525cc6863dc54756e6aab3d626732affb14b4515b5339e657a8aae5c553af4601022a86927a5ae8816145368a8a9d0d6058a868a6d7155d8d50ea6d57a2bcaabb8309aa1a9a8ac946e08fa32deba8f0ce8ef71f3d72f6b257a8781f4f7b8afa8c2c6f33599ea1e11d3fcb86bb882b17438fecf0d743057e8d423cb041025aa73d2d549aba0d02792aacf2513c959f352667326de9527db3eb557febb51bea4cc827df71feaaaaff0063bfdf96b477d559ff00d22b399a40ce9e34ff009ed557ec7f4ad1f2e8bbed5933439df2deac53aefed3ff002cab2eef7dbffadac66017779fc1591752799ff2c6a79f504b3acb9f5c7b8ff555c55ba148a33e9fe67fd33a67fa659ffcb6a827f145b7fcb59bf79597ff0009027fcb2fdfd72b19a33ea16de4fef6a97f6827fcb2fbf55752d43fe7ac3589fda9fbefdd43f64baa9035eea47ac5bbd413ee4b37cf45dde5cffcb686b1351bc4b8f9ff008ea2604175ac7fc4cab46d3ecd7136c96b12eecef2e21fdd7fc7d3d2e95677f6fe7fda26f31de9400d0bbff4cbcff9e7fdca7cff00dc8bf83efd33e4b7bcfded52bbdf71feaa6f92b640687fa37fd74baacfd4a47f3bff0040a9bcbf2fc8497f8eb2eeff00e78dbffdf74313248247ff0081d4d7779f67f213f8e8d3634ff8f9fee51f3dc59cf734224ab2ff00ae9eb227ff0048877d6dff00cb9ffbf59777fdca181ceea367e679144f67fea12b46d3fb950797e5de4f5203208ffe5e61acef13ff00a3fd96b6ff00e5b7fbf589aaff00a9ff007282918b77febab1278ff735d2cf1fee77d65cf66fe4d673198fe5d4f6b6753f97fbed94f823ff004cacc0cef2eb1678eba89ffd22b1e78fec75321a347c3fff001e73db7f1d6a5aff00a459d60daffcb07aded364ff005e95a53ea0cbb6bb3c9d950eab67fe87534ffbbf9eadcfff001e7fec56c239afb1fd9ecea0823ff439ead5d47f68a3cbff0043d959cc0c1d4acfcb869f6bff001e7fbdadbfecff00b659ecacbd37fd1eb3009ecfed1feaaaada5e3dbfc95bde5fd2b3a78fecf40d13c1b2e28f96ad69d1fd9ead7d8ff008e80652f2eb534d91eaafd8ff7345a4954847496bfe8f5d1799fda9a6ffb76dffa0573b695a96b27d9ef37ff00df75d2261e5d5eb491ecff00e9a53fec7fbefdd54fe5fd6b4812685a6a89715a904958b0475a11ff00a3d6a8ce66f7989715a36b1fda2b060bcfb3d6a41fe91f3c5f7ea8ccd4fecf7b7ff5553c1ff4d6a0d3af2f2deaefdb3ed1ff005d2b4801a90d5a8234acbb4d41edff00d6d6dc1225c574404c7c11bdbffaaaecb4abc7ae6fcb7ff9655d2695fbc9ab55b88ee74eff0048adbf2eb074aff47aeaedebba1b31a38df136976d710ffa4578278b7c27a3feff00ecf37cf5f49789e3fdcd7cb3f10a3ff4cff535f2d9bfc28cea743cff0055d2ee6cff00d54d58bff5d6b43ed9736ff2567cff00e915f9f541404a867a9aa2acd04ca935559eb46e2a8dc56f0332affaba5a2995d54cce6154aa5a8aba2266539e968a6d59a40af45150d6553a1d1026a28a86b9e46815351562b0946e014ef2fe949f254f4463603e90f0fd9fda26af75f03e87fc75e5de07d0ee649abdf3c3167e5c35f719752bbb947436b67566a2a49ebeb36d0d2032792a9d4b35674f404c59e4fb3d67799f68abbe5d3a8331bff2ca9d55fe7ff963507fabff0096d59cc026d95567d9ff002d69977a825bd63ddea0927fadac981b704894cffaed589f6cb6b8a82eb537ff0096559ca56196b51d43cbff005558b77a83dc5674f79ff4dbcc7aabf6cb9b8ae1a92b9700d4a4b6ff0096b5cd6abf668eba29e3fb1fcf71fbc7aceb5d3ee64bc9fed1f72b9a6b98d91ca7fa1ffcbc7ef28fec3b6b8f9ff7db2bafff00844ecfefff001d473ffa3fc96f59dac33120d3fecffeb7fd2ed697cbb6ff009f2a7cf78f79ff004cea3f32e7c9a00aba97d9ae2b16eb4f48e1ff0053f3d5dd464fb1ff00bf5833eb9f67ff00963f3d26055fb1bc950cf25cff00df1505deb97979ff004cd2aac125b793be19be4fe3a902d4125b4979bffd655d9fecdf63ff0072b13ed96dff002caa19ef124bcfb37faba00b5f6cfb6791e6d676a527fa64fe6fdc4aa575a85b793be2fe0a7eb9fbbb3d3ae7fbff007ea901afa8ea091e9bfe8ff7289e4fb1e8ff00fb2562cffe87fefd13de7da347ff006e9899a9049f68f212b3aeaf3ecf79fbaa20bc4b3861b6ff0097da2d63fb44d4121047f67f3eaacffebb656dfc9fbf4ae7aeff00e98d0027fcb9ff00b69597a8ff007eb520fee7f1d3208d2f34da00c4f2ff00734797fc1525aff72a6d37fe5ba7f7293039efb1fefaa7d95a1f63fdf4f59fe67fa66ca92919d3ecb7aab7766f790d68ff0067ff001cb56bcbfb44359cc6674167f6786a7fb1fd8ead411fefaad7faba200107fa9df2d5583fe5ba54dff2ca8f2ff7d5a0124f1fee6b0fe7ae9bfe3e21ac1d37fd2269ff00bf401a09fe915cf797f63bcae9608fecf59d751fda26a002d63a27b3fb451a76fb79aaeddc7fb9fb4c54019f1ffa3d685bd1f25c549e5fd9e8026f2ffe79543fd969715a16947cfe6d0067da6f8e6ae87fd6555f2fed15760ff47ff5b5484c9ed6f3f73fee568da6a1e6567796947fc7bfcf15324db86b42d2f13fe5ad73769aa56f798971fbead2067335e0d97156be4f3bfe79d627fb756be6ad5199d641fe91feaaad7facae6ad24ad1b7ada007496b2247feb6b52d76572969beba1d3b65c574404ce974d92babd3634ae46d7fd1ebaed2a4ff009ed5d54fa88ebf4cae861ae7b4cadb4ff47adc68352ff4886bc03e236869715ef7aaeff27f755f39fc42bcbcb79a7af0736fe1a33a9d0f1fd4a3f2ff00d6d625c56dea51fda2b13cbafcdeaee6456a2a6f2feb50d4400a9505c55faa1715b440ab3d55a9ee2a0ae8a66730aab4b3d413c95d11330aab5379950d1234810d14515cf53a1d100a2a6a86b9a52b1a13558aaf562a54b9807514da2981fa2de12f0ffd9ebd0ed6cfecf591a1ff00a8adbafd86952f67046d025aad714b55e792b40990cf2551f32adcf59dbe83326824a867bca86a39e801fe65625dfda6e289ef3fe79565cf27fd36ac2a74027b8ace9fed927fcb1a93ccfb3d47f6c7f7ae5980799fb9ae6f52bcff00f775a9a95e573b7727fd36ae79ec69032e7bc7b7ff00a67441aa5b47feaa8bb8de4ae6f51b3fb3ff00aa9ab899b23a49f58b9b89bf755a9fda096f67f69b8fddff00712b809fed36ff00eb7f78ff00c15973de5cde4dfec54f358892b9dccfaa5e49fee5559f5c4fb915735e63fdcf3aaaff00683c752e628ab1d2ff006c7fcf586aaff683c95ceffc2409f725a9e0d43fb426fdd50a5728d4fb627fcb6a67d8ecef21ff006ea8c125b7fcb5fde7fd34a9ff00b62dbfeb9d360626a5a7a59f9f5cf6a51fd9fe4aea27fde5e7fa9ac8d463b9b7f922fe3a929189069ff67a8eef4ffb1c3f66fe37fe3adefb1db69f0fef6b175291ee2f27a061a6c767670eca65dde7da3cf4968fed0fb3ff00bf507db3ecff003cb4013fd8def2cffe9eadbff1faa304696f53cf27db35283fe59a555d4b67d8f7c5400ffb67d9ef37ff007eb46093ecf0ef8bee5715f3de69bfeda56be95ae7fa1cd6d71f7ea909976eb50f2e6ff7e8ff00974aa53ecbcf9e9f3c9e64db3f83f829924f3ffa1de40f5069db3ed9fefd5ad57fd334dff6d2b17e7b39bfdfa00d0d4b7c779be2a3e4b89b7c553cff00e91f3c5ff03ace837d9de5005af2ff00733d737f63fb3d7493c9f6886aadaecf2764bf7e932919fe5bf9d56a08e8ff0096b5a1691fefa84333ed3bd4d3c756a0b3fdccf556d3fd23ce4a626559e3aaf5a5752797673ff7eb2ffe5952608b559fe5fefbfdbab5ff002e7fedd55ff6ea466841fea7f7b59f751fee6b6a0ff489ab3e78ff0082802adac756bccfb3fc945afef3fe014797f4a00208ff007353797f67f9ea1d37fd76cab56bfbbf92a90985ac95af3ecb886b23cbff004cdf577fd6532413fd1eb53cbfdcd735049ff2ed2d6fe9dff1e740195fda0f6f37fb15a96b795973c7f679a8f31e80353ec7f689bf7557bcb7b7ac78354b9a9e0d71ff00e5ad69003a5d2a4b9b7ff96df256a26b1ff3dab909e4fb47efaa78350ad0ce677306cb8f9eacdbd723a6eb1f68ae92d2f124a68cce96d7fd22ba1b4ae6f4dbcada83fe9957552ea26759a65749a6ff00a3d71ba65759a757544476ba749f68ae96d3bd72fa5495d25a495bc06892ebfd4d7857c46d3fccaf71d4a4fdcd7cf5f11b5cfb3cdb2bcbccff00846753a1e4ba947f679ab3a78fed15a377a87db2b3abf3397c4cc8cfff005750d5f9ea85202ad54ab53d674f256b4c064f59d56bccfa565cf2574440827aad3513c9552ac44b49e654752d67234813514515c150d42a1a9a8ae17b8d10d153515ad3ea30a26a28aeca6267ea869d5a5546d3bd4d5fb3b5648ba61515279951cf25495333e6a86a6a2793ecf41930ace9e44a9e7bcac4baa9908aba8ea1e67faaac8fb449ff003e756679123ff554cfb67da3fd7572bdc6887ccfb4555ffb6d44f25b47ff002c6b3a7bcffa6359cb6185dc7fc72d65f9767e77eebefd13c89ff2f147db2dadeb92469033aef4bb9b8ff5b355583474ff00aef5a377aa27fcb1aa53eb9fc159b3432aef43fb3d737a946f5d5ddea0f591a8de5cfdfac27d0a4715a969ff00f4dbcb7aceff004cff0096b5d0ddde5cdc7fcb1f31eb9eba8f5593fe58d643295d49731cdff3ce8835cb9b3867fee7fcf4a3cbbf8ffe3e3ee5519ecffe78ff00aba00db9ef2e6f2cf7d8cde65334e93fb426d92fdfac48247b3f9e2ad482f2f2e26fb4d97fc0ea901dae9da7db5bff00cb6a65d7d9adff00f8bac7fb65e7fcf1ad8fb67ee6989989a96fb8ff0072b3fcbfb3ff00c0fefd686a327da3e4a3fd0ede1a4c118b3d9fda21f3aa0d4bfd77eea1ab507fc4d352ff009e76a94cf32840c83cb4ff0096bf7ff82aaf97febe9fff002f9be5ff0080517727da21fdd7fc0e8608c182cffd33fd8fe0acfba8dee2f3c9fe34adab58ff00733d4f3c7fea1ffefba9199105e797f27f03d5aff57f27f72a3f2ff7d525dffa44db3ff225521335ff00e3f2cffdb4aabe63de59feebefa541f6c7d2ea79ff00d1ff00d322a192677db1edff00dc7a3fb512dfe4b8ff0080568fc9243fefd71b3c9fe99b2a4a47433de3dbc3fbafde568da496d71f3c5f7ffe79d72f06a1fc12fdcad083fe78f934033a2f2fec779fbda7ea31bdbcdf69aa5049f68b3ff48fe0ad4b5bcfdcecaa44905d7fcf68aa97fcb6fb653eeffd1eaac127d9e981575292aac7ff002deb52eacffefdd65ffc7b4dfec526522186acf97fb9d94c83fb92d5eb5ff48876508191e9d525dffa47faaa641feba9f3ecb79a99255ff5779bff0082ad5dff00a3cd44ff00e910efaab049f68b3fded2604d3ffa3cdbe8f352a18237b886a3b5fee5080bd07fa3ff00b9537fc7c7faaacfb493f7356bcb7fb1efa60473ff00a3ff00adad4d2af12deb3e7928ff00575a400d0baaab69b2adc1b2aa5de9ff00679b7c54480b5e5fda2aaf97f679aa7b593f7d5b7f25c51002082cead7f67f990d4f059d6a5ad6a80cb834fadad3ab460b3493fd553e0b3fb3d519ccb5a6ff00aeaecad3fd1eb9b834fadab4ef5a43732676da56cb8aea2d63ae1b4d92bafd2b58ff009eb5e85324eaf4eed5d2dad735a6c895d0dac95d6f602d5dff00aaaf0ef88da7a5c57ad788f7c70feeabc47c63f6cff96b5e3e63fc32267945de8fe5cd59fe5fd9fe4ad0badfff002caaaf9773257e7353e23333eaacd5a33d63dc56120295df6ac79eb46eeb3a7aba60559ab2ee2b526acbbbed5a819f3d56a74f556803460a92a9558a991a4096a6a86af56328dcd0af5629d5354a8010f97f4a2a79ea0a6e3ca0368a2abd388d1faab514f251556e2bf6796c8b7b12524f4955eea4a8443209e4aa5f6ca2792b1e7bca1994cb7f6c7acababca9e7d42b12eaf3ed1594f60810cff69a3fd22a0fb625bffaaa6798f27fadfb95cc8d082ee37acebbfb655e9f504b7f921ac79e4ffbeeb0add068cc9ecef2b3bfb3ee649aba1fb1dcc9feb66a8278ee7fd4d7372dcd6073dfd9ef67feaaa94ffe8ffeb6b7bfb0d2dfe7f3ab12ef4f7b7aca6ad6343127d53ecfff004d29905e7fcf59bcba7ff635cffcf1a67f65a5bffc7c7f1d6480b53ea0ff00f2ef0ffdb4acebabcfdcfefbf7756bfe3dff00d57eed2993c967ff002f1430312792e6e2b1752b3b9b8ff80574975225c7c96ffbbacff96972dca47213d9bdbd416bf69b3ff9ed1a575ff63f321ff9e69fdfaab77669e4ff00cf4a971b0c6695ae797ffc72b6a791ef3e7fe0ae2a7b3ff9f79be4ab5a6ea8f6ff0024b37fb94d099b73c7f679aa0d474ff2fc87ab5048ff007fc9f3ff00dba9e0fb35e5e7fb74c933a08eaacffdcfe07fb953cfa87d9f52fb35177b3cea00cef9fcea27d41347ff00a69fdfa3ccfb44d556793ed135005af2dffe597fc7abd413c7f67fdcd4fe67d9fe4aaba8c9f67ff7e8033a7d971fe8dff912b46efb5626a327fcf2abda6c8f250047a97fe81f7e882f3ec736cad19ecffe5e7f83fe5b5645d46f6ffeabee7f0526068565eaba7a49fe936fff0003ad482f2daf21d9feadeb167ff43ffe2ea4a44105e7f07f1d55bbd42f2ce6ad1824ff009ebfbca351b3b6fbf6f40c9bfe421ff4c1ea1f32e7ee7f1d65f99796f356d5a6b9f68ff8f886a90997ad354fb443f66b8fbf47fc7b4dfec551fecf4f3b7c5525a6a091fc9feb3fd8a6496ad3fd1e6d92fdcacbd4b7c779fbdfb95a9047f68ff8f79be4ff00d02a8ea5bedef367fdf74148b550c127d9e6df557ed9fc156bec7e643be8065ad463ff009798aa8fdb3ccf925ab507fa3ffaeaab3c7f67a090ff005744127d9e1acff33f7db25fb947cf1cdb293291a1fda1fe8754a7ff0053bff8ea7ff8f786886840c20bc7f3bf735a16b7892567ff00c7bfcf53797f6cf9e2fbf4c92f5d6ff27f75556d3fd228f9fced951dad9bdbd006e569ff00c7c43beb3bfd5d68e9dff4d7ee3d6b002acf669f7e2ab56bff004d68f92487f7545a6fad8ce66a5ac7ff004dab5fcbfad62c127d9e6adab593ed15a40ccd4b4bcad1ff0057ff002c6b12b52d6f3ecfff004d2ac4cd8b4bcff9eb5bd059a56241789715af69795b533299a9056c5ac959df6c4ad1b4d95d54ccce934a92bafd2af2b864ff0047ae974aadd01d5cff00e910d79cf8b6cffe98d76d77feaabc5fc6325e7dfb7af371ced140737ae697f67ae42ee4b6b7ff0096d4fd5754b9fbf5cdcf79f68ff5b5f03897790134fa824759177a8251752565dd495c3200bbbcfb4565cf2513c959de67d6888125c5625d49576792b22ee4aa0197159de67d69f3c954e8034e1ab50565c156a092a64690352dea7aab055aa82c9bfd65494da6f994012799f4a8fccaa5e6533ccfad4480b53c950d47e67d6a1a903f55ae2a1a827bcaa5f6cafda066a54175254759175254b10cbb92b227bc7a9a6acf9ef2a5913239ef2b227df56a792b2eeaf1ff00e594358cc204fe67fcf2ace9fed327fcb6a3e6ff009e34f8367fcbc564cd0abe5dcffcf6a67fc7bffadab53de25c7faaac8babc7ff0096558cc689be6aab7579ff006d2aacfac3ff00cb5ac8fedc7f3ab9e633467d42f2b2fed9ff003ca9ff00da1f6ca64fa7bc90ff00cf3ac99a40a33de5fc9f279de4565ddea16d67f279de63d5a9f474f26b3e7b3b6ff9e348d0c4bad42da4ff005b53d5af313fe5ac35341fe91f3dc5001069f6d790fef6ad7f63db793f66a9fed96d67feabf78f54bed8fe77eea8028ea5b3fe7b573dfdb1f679bfe99d5abbd927c92cdff00acbd57fd1ff00ebd6b39948b50496daa7fadff44a82ef47f2fe78aaac1796744fae3c759831907882f34bf925fb95a9fda1fc7143589f6cb693fd6d43fda9f63f9edfee5049d0ff00b74797f68f9eb2e0f107da21dffc14fd36f3f7df6997ee569002d7fcb1d90d65c1fbcf3eb47fe5f3ceace9e3fb3d120347fe3e26df1541aaecaab05e7fa66fff00c7289ffd326ff9e75981973c7f6886a4b4fddc303d477727f055ad0ffd4ec9680352d7fe5ba4bf71eaacfa7fd9e1ff00a75a86793ecf577ed8ff007e2ffbf7401913e9ff0068ff0055fbb7aab06f8e1fb1deff00df75d0fd8d354b3df6f59d77fe8f37ef6802af96f243fee552d2a4f2ff00d6d5df33ec7fee51aae9e9f7e2a4c0b53e9fe67cf0d55f923ff5b0d49a6ea1f679b67f1d687cf71feb7eff00fcf4a101560ffa6550cf67ff006cea6f9e3ff963557fd1ae26ff005dff0000a6067ff6a3e9737fa9adafb627883c8f37f77755567d2edaf21aa507fa1fc9e4d26522941f69b7bc9d2e3efd6f4127fa1feeab135593fd337d68695fe91f250819abe67ee7f7b51ddffa443beaac1fe8ff00e8d2d3e0ff0053f66a6499fe5fda289ecfed14cf2ffd7a569da7fa9a0a4334dff8f3d9351e5d1e67d8e6ff0062adf97fc76f40320ff9635574eff47ad4ff005959d07faead2049bde5ff001d41049fbeab5049f6886993c7f67ad00b7f2559d2bfd22b3a78e8d364fb3cd4d0172a5aab1ffa3d68c1fea6a8ce65ab5ad48237b7ac482b46092b4819336be7ff0096d53fd8fcbf9e2acff9e92d6f2f2dff00d6d588d1fed8fb3ffadad4b5f10565ff00687fcf5a83ccff009e54c0ef2d3c4fff003d6ad47ac7fcf2ae36d3bd745a559d5c4ce6779a1de7da3e7f3abbfd0e4af39d0e3f2ebd1bc3f5e852332d6b9225bd9d7857883c596de74e95eebae47f68b3af97fc71225bea55e1e692b440cbd735cfdf7fa9ae5eeaf3ed153dd49597715f0752576013c958f77254f7725644f795cd20239e4aab3c944f25529e4aba6032792b3aeea7b8ace9ebae022adc541be89aaaf99f5aa90cb5bead412562f99f4a9a0bcaca4074b05e568fdb3e95c9c17956bed9f5aca40745f6cfa541f6cfad60ff00687b513de56320353ed9507db2b2fed947db2b2901a9e651f6cacbfb651f6cac2607eadcf25416f4cf33eb4f824afdbcd265fac6bb92ad79958ba9d06647772567cd45d5e555fb6279349804f25674fa824744ff00e915973c96de77fcf4ac666902eff6c5cdc7faaa3fe3dffe3e3f794cfed07ff9e359da96a17358c8d09f52d53ecffeaa1ae7a791ee2a0badf71feb6b12ef50fe0ae1a9b951279e3a83ed8967ff004d2b167d53fe9b7ef28f32b23689b5fdb9e67fd30a65deb1ff006d2b9efb67d6aacfae25bfc915051d24fac5b5bfcffc759ffdb97327faa86b2fed9ffeeeb460d9e77fcf4a680d0b5fb4dc7cf71505decb7ff5537fbf59fa8eb9ff003d66f912aac1a825c7fd33aa02d7db3fe7943589e5dcf9dbee26ad79fed37154a7bc4b7ff96d52c0c4fb1f970feebeff00fcf4a65a68f73e755a9ef2dbeff935973efb8ff55594c0351d0d3cedf5917778f67ff4dd289ef1fced92d1e5fefb64b3564ca447fda0971feaaa19e4f2ff00d57eeea0fecfb9f3bfd1ef2a8c1a85fd9cdf66b8b3f32a465bb4d53ecf79056d3eb8924d3d9c5f712b94d5747b6f277c50f96ffc75574a8ee6cf4dfdd552133bfd2b58fb47c971f72a79ff0077341ff8e5625a47f68860a93ccfb66a503ffabfee56b0244ff97cfded1e67efb67f1d2cfbee269fcdaceba93ecffe9344c0bb751d49a6de79744f1fda2cff007559dbdeb3036fc4166924305476927d9e1d92ff00dfca3fb6124d367f37f789fc695560ff0047b3ff009e9655a40093fe3cfe78ab43fe4290cfff002cdeb3f51ff43f23fb951cf251302183fb9504f23dbffadabb3ffa47fbf59f77bede6dfe77c8f593013ed9ff003c6addaf881ecffd6d54fb627fcf1f9297cb4fbf17ef12a40e93fe120b6b8ff9635567bcb693fe5ceb9e83655a837ffcb29aa901a3fda09e77eea1ab5f25c7cf557ed9f67ff5b0fc95a106cfbf5480e7aebecd790feea8d3bfd1ea7d4acd3ce9fcaace9e378ffd550c0dbbbff4cb3dff00c75574ebcfdf55982f12f21ff6eb33fe3def2901a975ff001f9bff0082920ff535a3ff002dbf7bf72aaddc7f679a8021baff0053fbdab56bfe8f51ff00acab506c92ceb48005a7fa3f9cf59707fcb77ad49ef123b3ac583fd6d6806a69b27d9eb42793ecf79fec5627fd72ad1f33ed10ff00b9419ccb53ff00a4567f9951da5e7d9ead4fbfff00b650665ab4bcff00be2b477d72169a83dbcd5a969a83d3406dff0068fee6a783587ac7fb67d688350aa036e0d41ea7fed0bc92b1e0bcad1824a00b506fb8ff005b5d0e9ddab2f4ed97157a08d2b48099d5da495d5e9bf66ae0ed7fd1eba2b592ba69ee6533d1b4e8fccff555d7695fe8f5e73a55e7d9ff00d5576da55e57744ccea358ff008f3af9e7c71e1ffb44d3d7bfea3789f63af04f1778c2dbed9b2bc7cce29c15c0f1ed4b4bb9d3eb2e78ee6bb5d4750fb67faa9ab9bd4bfebb57c3d682d2c073b771bd62ddff00a3d6bddf6ae7aeeb826ac0417155aa69e4ace9a9d3339904f2552b8a7cf2555ae8899956abd2cf59f715ac4d204f50d55a9bccfad1234343ccfe3a3cc7b7acff0033f734ff0033ed13573d4e805df32a1df557ccfad1e67d6b9e405adf53799591e67d2a7f33eb59480d0f32a4acfabd5cd57a01ffd9);
INSERT INTO `pet` (`pet_id`, `owner_id`, `name`, `type`, `breed`, `gender`, `age`, `color`, `b_day`, `b_month`, `b_year`, `profile_image`) VALUES
INSERT INTO `pet` (`pet_id`, `owner_id`, `name`, `type`, `breed`, `gender`, `age`, `color`, `b_day`, `b_month`, `b_year`, `profile_image`) VALUES
(7, 9, 'PEPE', 'DOG', 'HOUND', 'MALE', 1, 'ORANGE & WHITE', '1', 'JUN', '2012', 0xffd8ffe000104a46494600010100000100010000ffe1006845786966000049492a000800000003001201030001000000010000003101020010000000320000006987040001000000420000000000000053686f7477656c6c20302e31382e3000020002a00900010000000003000003a00900010000000003000000000000ffe109f4687474703a2f2f6e732e61646f62652e636f6d2f7861702f312e302f003c3f787061636b657420626567696e3d22efbbbf222069643d2257354d304d7043656869487a7265537a4e54637a6b633964223f3e203c783a786d706d65746120786d6c6e733a783d2261646f62653a6e733a6d6574612f2220783a786d70746b3d22584d5020436f726520342e342e302d4578697632223e203c7264663a52444620786d6c6e733a7264663d22687474703a2f2f7777772e77332e6f72672f313939392f30322f32322d7264662d73796e7461782d6e7323223e203c7264663a4465736372697074696f6e207264663a61626f75743d222220786d6c6e733a657869663d22687474703a2f2f6e732e61646f62652e636f6d2f657869662f312e302f2220786d6c6e733a746966663d22687474703a2f2f6e732e61646f62652e636f6d2f746966662f312e302f2220657869663a506978656c5844696d656e73696f6e3d223736382220657869663a506978656c5944696d656e73696f6e3d223736382220746966663a496d61676557696474683d223736382220746966663a496d6167654865696768743d223736382220746966663a4f7269656e746174696f6e3d2231222f3e203c2f7264663a5244463e203c2f783a786d706d6574613e2020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020203c3f787061636b657420656e643d2277223f3effdb0043000302020302020303030304030304050805050404050a070706080c0a0c0c0b0a0b0b0d0e12100d0e110e0b0b1016101113141515150c0f171816141812141514ffdb00430103040405040509050509140d0b0d1414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414ffc00011080300030003012200021101031101ffc4001f0000010501010101010100000000000000000102030405060708090a0bffc400b5100002010303020403050504040000017d01020300041105122131410613516107227114328191a1082342b1c11552d1f02433627282090a161718191a25262728292a3435363738393a434445464748494a535455565758595a636465666768696a737475767778797a838485868788898a92939495969798999aa2a3a4a5a6a7a8a9aab2b3b4b5b6b7b8b9bac2c3c4c5c6c7c8c9cad2d3d4d5d6d7d8d9dae1e2e3e4e5e6e7e8e9eaf1f2f3f4f5f6f7f8f9faffc4001f0100030101010101010101010000000000000102030405060708090a0bffc400b51100020102040403040705040400010277000102031104052131061241510761711322328108144291a1b1c109233352f0156272d10a162434e125f11718191a262728292a35363738393a434445464748494a535455565758595a636465666768696a737475767778797a82838485868788898a92939495969798999aa2a3a4a5a6a7a8a9aab2b3b4b5b6b7b8b9bac2c3c4c5c6c7c8c9cad2d3d4d5d6d7d8d9dae2e3e4e5e6e7e8e9eaf2f3f4f5f6f7f8f9faffda000c03010002110311003f00f37a29de5fd296bdc47e541454b4be5fd69810d4dfeb292a5a4c08aa5a28a1005153514c028a2ac5001453a8a006d3a8a2800a28a282241454d45520890d1454d4cb21a28a2b481121b453a8ad0823f2ea1ab5450055ab14ea6d5c40af4558a2a80af4558a2800aaf5628a00af5628a2802bd15628a00af451562802bd15628a00af4558a2802bd15628a99015e8a2ac54015e8ab1450057a2ac557a0028a9bcba86800a86ae79750d0043454d50d0014514500455155aa8aae20454cab150f97f4aa01b453e994015e8ab1455c40a550d5ba8aac086a1f2fe953514015e8a7d32aa2057a2ac5156052a2a5a8a8022a2a5a8a8021a2a6a86801b453a8a00ab4558aaf512021a2a6ff96350d48054552d1401154552d14011532ac545401ea1454b5157908d8968a28a604b45153500145145001451b2ac50014ea28a0890514514101535145001451e5fd6a5a008a8a968aa45c48a8a968a61222a2a5a8ab4804428a28ad024150d5ba8aae2410d1535154043454d50d001454d50d00145145001454d50d0014da751400da29d4500368a7514005369d454c8028a28a800a28a2800a28a2800a3cbfa514505c428a28a0b0a6d3a8a006d57ab54da08915e8ab155e8202a1a9a8ab8810d145154055a96a5a8a8022a8e7ff47a928a006557ab1455c40af5079757ae2a0ab02a51525c5474011547e5d59a8aaa2032abff00b156e994480a5454b51511022a86add454480868a9a8a9021a6d3a8a006d14514015e6a86a6a28021a28a28022a8aa5a2a2407a8514515e5a2e44b45153530890d4d45141614558a6f97400ea751450014514504489a8a2a5a0822a968a2800a28a282e21451450585152d154889115152d14c822a2a5a2b4801151454b5a0115152d4540054552d1571022a2a5a2890115152d1504c88a8a968a02245454b4505115152d4540051451400514514005152d4540051451400514514005145159cc028a28accb890d4d451e5fd682c868a9aa1a0028a28ad200368a7515a1122ad1562abd0410d15354341710a8aa5a2ae21222a8aa5a59ea82256aaf56e994048af5154b450411543535157102a5153797f5a86ac06557ab1450057a8aa5a2802ad152d4540054353514010f97f4a6d3a8f2fe9400daaf56a9b4015ea1abd55e8021a2a6a86803d428a28af211b13514514c0b14514ea0028a28a002a6a28a0028a2a5a0890514514104b451450014514505c428a28a02414558aaf54880a28ab14c0af4558a2b48015e8a28ad0028a28a002a2a968a0028ab155e800a28a2800a8aa5a28022a2a5a28022a2a5d9450045454b456732e245454b45665915152d140115152d140115152d140115152d140115152d1498115152d1e5bf93be84055a2ad6ca87cba6047454b51500150d4d4500434da7515a4006d57ab54dad00af50d4d45004351cf5251571022a8aa5a8aa8890caaf5628a082bd4552d157102a5153543560454cab15150032abd5baa9350045454b5150045454b51500150d4d4500434da751400daaf5628a00af50d4d45007a554d4515e43362c514514807514514013514514d0054b51514c0968a28a08912d14514101451450014558a2800a28a2800a29f4caa401453e8a60328a7d1400ca29f4500328a7d15a4006514fa2b4019451450014514fa006557ab14face45c4651453eb32c6525bd3fcbfa568e95e1fb9d63cff00b3c3e66ca0a89994574fa07c33d6fc59e7a69f0fce95af69f06fc43f639ef25d366f92a5cac6f1a52a9b1c0d3bec6fe4fda7fe5d7fe7a57b0f813e09dcf8f341bafdcf96e9f72bd27c07f01ee6ce6d4744d6e1f32cbefc325652ab637860eacf63e669fc2f79fb87ff009f9a82eb4bb9d2e69d25fbe95f7669df012c06822da5fe0f9d2a1d47f677d1f54d4a7bc97f8eb2788b1dd0cb6a496a7c2d1e8f79713793159d3f4dd3fed90dd3ff0073fe59d7e88e81f08fc3de1bf3bc9b3fbf5c26a3fb2fe95ff0927f69597eed1e6def1d2fac94f2da90d8f873cba7c166f715f735d7ecbfa27dfb7ff9edbeb919ff00667f33c61abf95fbbb5787e4a6ab7318cf03591f227974b5efd3fecdfaadc7892d6ce287fd16dbefc950687fb37eb1ac78927fdcf976a9355739ceb0753ac6e7854167f689b64553ff0061dcf9db3c9afac74afd9afecfaf7ef7fe3d6da1ff00c7ebbfff00867fd1f50b383cd87cb93f8ea5d6e537a797cea3dac7c47a77c3fd5750f9fc9ff42fe37adbf13f80dfed96b676f0fc95f7d41f0ef47b3b386da2b3fdda551baf871a7e2692187fd2685594b73a6596ce28fceed7347fb1cdf66fee5627fb15f5a78e3f677bfbcb3babc8bfe3eae6b97baf80efe0bd077f93f6bd5dff00f1cad6328bd8f3ea61254f73e7dfb1bdbffaeaabb2bd6b52f86f79a5e8ff00bdfe3fbf25715aae9f6da3ff00aafde5d7f1bd6872ca1c8729e5d475a3a8efb7aa7548c644545145324869b5667a82b4800daaf562abd680150d4db2a1ab88054552d45544488a99562a2a081955eac557ab88054552d45560434514500454ca7d32800aaf5628a00a5454b5150045454b450054a2add45512021a6d3a8a801b55ead536803d228a2ac579e6c14ea6d3a800a9aa1a9a9300a28a2840152d1453025a28a29300a28a28444828a2ac532028a28a0029f451548028a28a60145145001454b45004545152d0045454b450045454b4505c48a8a968a0b22a2a6f2feb4ff2ff007db282e256a9bec6fef5b7a7785ef2f2f3ec7fc7fc15dfe87f05fc437179025ed9ff00a2bffcb7a8955f668da142559e8797e9da3bea937d9a2fbf5a9ff0adfc496737ef74d9b67fcf4afb23e1efecef61e1f8607beff4bba4fb9257aec1a3d9f93b3c9ae19e24f7a9654e6b53e0cf0ffc17f124936fb7b3fb5a3fdfaf75f825f0afcbff0049bdd37ec17d6dff008fd7d131d9a47f72a7ae79d6e647a586cb234dbb9c4687f0fecfc39abcf7965fc75d63e9f6d243b3c9f92add377572f35cf5e3878c74467e9ba3d9e9ff00f1ef0f9757e933ed525334e4500a29945218fa29945003e8a6514c07d328a2800a7d328a4014514500279759da8e9ff6c8767fdf75a545529f219ce9c67b9e27e31f84f73e209ffd7797a5db7dff00f6ebcbf52fd9eeff00c617bff409d12dbfefb7afaee78fed147975ac2bd8f36ae5f0a9a9f9fde3cf84f79a5de7eeacfcbb54fb95e73a94691ffaafde57e85f8c7e19a78b26fdecd5c9c1fb3e68d6179f6cfb1fc90ffa982bba35b9cf171196ce1aa3e20ff844df4bb3fb4eabfe89ff004c2b2e7bcff9f787e4afa67e287ecf7aaea937f695c7dcaf25ff008567aade433fd9f4dfb25aa7fcb4ade32b9e3d5a52a76b9e5f50d749ff00087dfdc7faa87e4fe39eb3f52d3fec75d34ce591953d2558f2fed150d6c41568ab1450051a2a6a86ae20455154b455015e8a7d32802bd14515712244550d4d45590435154d3d43400ca28a2802bd145140054552d4540054552d45400b3d254b5154480868a28a803d1aac557ab15e732e414ea28a110153543535300a968a2800a28a96800a28a2800a2ac5140051453e80194fa28aa401451453025a28a2800a28a2800a28a2800a28a2800a2a6a282e2434549b1ebd0fc1da3d878b3fd1a5ff00447a4e56358ef6394d2b474f3b7cbff1eb5ec9ff000ccfff0009269b06aba55e7c8f5d5f81ff0067bb6b8ff5bfeaff00b95ef7e07f01db782ecfecd6537fa2ff0072b92b56e5b1ef61703edf53e69d2bf64bbf8e6fb4cb37989fdcaf57d03f66bd1f4b9b7cbfbcaf6ec52d70cebdcfa0a796d286a8e674df01e8f67ff2e70d745f62b6ff009e22a4a2b073e73d18528d3d1051451526814ca2abd005ba6557ab14005145140051451400514514005145140051451400514514015eac557ab14005145140051451400fa651450057bbb34bcf925ac4d47c0f61790fd9bc9f92ba6a419aa52b194e8c6aee788f8e3e19a5e69bb2cacfcb44fb8907f1d7cf5e2ef81fff0008dd9ff696b7ff0000812bef0f2eb93f16f81ecfc5b0ec96ba6955b1e462b0316ae8fcddd72f3ed1e7a450f968959775a7bc7f257d21f10be07ffc237a97fa143f6b7fe0af2bbbf09dfdbffcb9fdaeebf8dff82bd3a756e7cc55c3b8c8f33a8fcbaf50ff008576fa1d9ff6aeb7fc7f723ae7a0f03eb7e2cd4b65bd9f975af35cc7d958e43fd6549fd9ef710eff00e0af6ab4f817fd8ff3defdcfee570fe38fee45fc15a40ce50383d950d755a6f84ee6e21fb4cd597aae9e96ff00eaab7898ca3639fa6558a8aa891955ea7f2e96802bd4552d454010d153543571224454ca7d15640caaf562abd001451450045454b515448028a28a8022f2feb454b5154c80f40a9bfd5d3a8ae101d4514500153543535004b45145260152d14508028a28a6058a28a2800a7d1455200a968a29805145140051454d4010d4d454b417122a2a5f2ffe7ad4f3e9efff0000ff009e941653a2ae5de8f73a7fcf2fef2d7fe7a56f68167a56a936c961f2ddea5cac0a327b1cd4f1bdbffada9f4dfb4c736fb787ccff00a675ec9a57ecff0079ac43fe8537fa2ffcf392baef077ece1e20b79bfd23c9b4d9fea64fe3ace55b90eea5879d46792f846cd2f2f2d7cd876595e7fe38f5f41687f01d2cef20f366ff00724af4dd2be13e891d9ffa6e9b0ef7fbf5d95a69e9670ecfe0ae2ad5b9ec7d150cb2f6948ced0f4bfecbb3d95b94515e7f3731f4308c60b9505145141a05329f4ca0028aaf450015154b4544c08aa5a8aa5acc02ac552a961ab801628aaf562b402bd1451401628a2abd0058a28aaf401628aaf562800a28a2800aaf5628a0028a28a0028aaf450058a28a2801f4ca28a00af3e9f6d71ff2c6b12efc1f6179ff002c61ae9a9954a56339528d4dce0aefe11e95aa6a5f6cb8fde7f712b534af01e95a3ffc7bc3f3ff007ebaca656b19984f09068f16f89be13b9bcff8f7fb95e4b3fc07fdf7da753ffbf15f5df969715c878e3c3ff6cb3fdd7f1d75d2adc973cbaf8381f0efc4dbcf2e6fecad3eb979fc0f73fd8ff6996bea3b4fd9fee750d4bed371557e237c33bcb3d1fecda7d9d75c6bf39e3d5c34a1b9f1a6a567f63f92b3bcbfb457aeea5f0cdfc3ff00f1fbfbc7ae7b55d3edadeceb5533cf74ac701f63fa51f63fa56d4f67556efb56b195cca51b18d55ead797f67a65c56f130914ea1ab7515512435154b450448af55eac5141057a28a2800a8aa5a8a800a28a2a64015154b4541713bbab14515c84053a9b4ea009a8a28a002a5a28a00968a28a0028a2ac50014514fa002a5a8aa5a0028a28a009a8a6dbd3a82e24bb2882cfed156b4dff4c9b6577969f09df50f9e29a8e6b1bc2129bd0e0e0f0bdfde7faaaeb34af87f7faa7fa34b67f257a4f863e0df89f4b9a078a686eed6be8ff07784fec7ff001f1675cd5ab72d8f5f0f81956bdfa1f1d7fc29bf13e8f37fc837edf655d67863e0ddff00fc7cd97ef2d7fe5b69d3ff00ac4ff72becb82cd2de1a7fd8edbfe78fe95c8f127ab0ca6e7ccd07c07d56ce6fdd43fe82ff007e0ae853f66bd1350ff5b0fd81ff00bf057bf640ed4eaca75b98ec8e590a7f11c7f837c009e13b3d9e77995d8514564e573d1a54a14d7ba145149e67d2a4e8168a651400fa6514500155eac557a0028a8aa5a0028a28a00278ea2abb55eb39805151514400bb4557a2b402c557a28a002ac557a2800a2ac514005155e8a002ac552a96802c5155eac50014557a2802c557a28a002ac557a2802c557a2ac50014557a2802dd14ca2801f51f9752514011f975467d3d2e3fd6d695154a5626518cb4679f78c7e15e95e24b3fb3793ff03af1fd57f66bd2b4bb3df2fef2bea1acdd4acfed90ecad613d4e0ab848491f0b7883e19db5bcdbfec75e4be23b3fdf6c8acebf407c4ff09d35087fd1e6f2ebe7df1c7c27b9f0fc3be2fde5774267ced6c3ca9bd4f96eef4ff2ff00d6d67cf5daf8bacde3ff005b0d7073ff00a3cd5d54e679356362ad4552d15af39811553ff57572a1ad232b91222a653e995648557ab155e800a28a28022a2a5a8ab39805145159303bba28ab1526214ea6d1400ea9aa1a9a800a96a2a968025a28a2800a28a282e258a7d14505854b5154b498055bfb1bd106cb8ad4ff0049d2fe7f27cc4ace4544cbab569a7fdb26aed60f01ff00c2410c1a9699fbcaf4df027c0fb9d53fe3f61f92b19cac8eba342555e879e7867c2761a85e41e6fee1ff0082bea0f865e0f7b387f7b0c32256de87f04fc31a7887fe25bf3a577f6b669670ec8ab86733e9f0d8070d5956d747b3b7ff009635a5484d2d64e573e8143912414514548c28a28a0028a65153200a2abd1444028a8a8aa025a2a0ff00574eace604b45413c74799ff003d6b3027a28a8a801be654f5567a3ccfad17025a2a2f33eb49401354345433d005ca96aa515a4009aa5aab1ffa3d4b5a005145434016ea2a2a1ace604d51799f5a86a3f32b9e72b0d16bccfad2557a74125473944d53550824ab35a42572596e8a8a8ade0225a28aabfeb2b401fe654750cffebb64553ffaba00968a8bccfb3d1f6cfa5003ee28f32aaffd75acf9f58fdf6c8ab3981d0efab15ce7f6c256a41795080d2a28a2b5880514515401451450054d95cd789fc269ac59fd9abb0a2a94ac673a51a9a33e54d57f66fbcfb64f737d37fa2d79cf883c3fa5786ffd1a5d36bedfba8fccf92bcdbc63f0cecf54f3e6b8ae9a533c4c660d46d63e16d7f50d2a39bfd4d713a95e598afa27e21fc3348e6ff47b3af1ed47c1f7367ff2e75e8465747cf56a1c8d1e7b751ffcb68aa8dc5745a959dcff00cf1ae76e2b44714a3623a8aa6f2feb50d5a206514fa65322457a2ac557a4c90a28a2a180543e5d4d4549513b6a9bfd5d43562ace70a75369d40054d50d4d40054b451499710a968a2a4b0ab155eac54c8029f53411bdc55afec7b9b8ff00555948a8952a4f2ea7fecbbfff009e3bebd93e15fc0bbff187cf710fd923ff00a6f594a56378509567689e4ba569773a85e7d9ade1f32be96f857f00f55f277ea7ff001ebff3cebd83c07f05344f03fcf0fef2e7d6bd2eb9ea4f63e9f0997595e6725a07c3fd2bc3f0ecb787e4aea7ec691d494560e573dda54a34d6814514549d0145328a007d32abd1401628a2abff00abace72b013f99547ed954aef54ac8fed4fb47fcb6ae6a93d868e87ed9f4a83ccfa573706a0f27faefddc95a16923c7feaa6f328a72b8336a8aadf6ca3ccad445aff008f7ff7292a1f33e94b4016ea29e3f33e7a83ed9ff7dff72ad7994011d433c953cf5913c9e5ff00adac6acb950d134f7946fac59ef3f735a16b79fb9af1d4fdf28d1f33ed147fabaa56927d9ea6df5eaf3e8809fcca5aceff00ae549f6cfa54f392cd1824a7f99f4acfff0096b51fdb3e95b426235693ccfa555b5bcab15af35c0b14567cd46fa39ac05af33e94c9e4fb3d529ef3ecf5575293cc86b0ab3d068b5f6cfe3a8e793ecf0d67c1fea7f7b537db3cc86b879ae516bccfb4549e67d9ea8da77ab53ffa45340496f576deb9eb592b43cc7b886b6801a9e67d2a6ac9f33ec7fefd4dbeba612b12cd0a2ab799566b652b888a927ff47ff55f7ea6a77c95ac40a9e5ff00db4a92a5a87ed9f5aa027f2fed1feb6b22eecffe7de1ad0fb627b532ef50fb3c3e779de5a27df92b1a8071baade5cd9d6241e30bf8e6dffb9d95c5789fe34587893529ecf4c866bfd9ff002d20ac1fed04b7ff0090b4d0da7fd33fe3af36a68ee347d1fe1ff167f6c7fcb6aeb2093ed15f2c787fc6173f6cff0047bcfb25aa7dc8ebdc7c25ae3de43febaae8d6dd0d9dd5329f457a71dae49537d58a4f2ea0aa02c53eb3ea5a00b75527b3fb4558a7d52339ab9cfea5e07d2b54ff005b0d78cfc4df84f6167673dcd943f3d7d0d595a96869aa7fadad612b1c95b0fed11f9d5e2df0fdff009dfea6b8ad47c3fe5ffadfbf5f6b7c62f847797967e7699fbbaf913c4da1dce8f34e92d6ca67ccd7a3ec9fa9e65a947f67acaad5d464f2e6aa15b42573c9a9b9528a96a2ad4c86514fa2a64032abd58a2a00af4558a2a24075d0d58a28aed320a751454c809a8a28aca404b45152d400514515122a258a7d1535a59bff00cb2a92cb569677971feaabbcf07785ff00b626d92de7975cf687a1eaba84db22b3f3ebd83e1efc18f16eaf37fa4c3f648ffe7a56733a28d075647a5781fc1e9a5ffad87ccaf60f0ff87edacfe78aaaf837c11ff08e59ecb89bcf7aec2b167da60e87b24145145433d57b05151f99547cca422f7994b55fe5a2800df451ff005c6ac54c802abd1beaa542959816fcc4b7ae2bc41e384d3ffd559cd775a9e2df10268f675f37f8c7e385cc979f63d126f9ff008e4af2b175b9248da9c6e7a6ddf8a2f350ff00a744fe3827fbf5cf6a3ae5b68ff3cb35e7d97ff1caf249f5c7bcff004cbdd4bfdcaad69f1135bd1ef3fe7ef4bff9e73fdcae0f6bed0738d8fa2343f105b5c43fbad4a1911ffe59d76da6fd9adffe99d78df81fc59a26b9feab4dfb25d7f1ff0072bd86093cc87f75fbc4af4f0fb331669f99ff003d689ab2fe7ffed73d49049f67aeb1135aff00a3cdb289ef1f4b9a0ff9696aff00f8e549fedd1e65302eff00d3cc547fc7bc3427fa3d2d0027db2a96a5fe910efa9a7b34ff009655560fee572d7574690dce6ffe3e26ab5691bdbfc9ff007c555d4acdf4bbcdf5a96bb3c9af254353a19369579fda90ff00d344fbf56bed9f4ae7a7ff00479bed96ff00f03ad7fb67da21df157646575632910da49f679a8ff8f79ab3bed9f67a9e7bcfdcc0f59f5333520bc4aab3c9fe99b2a0ff006e2a66a3225c7cf5d1d0966a5a4953799fb9ae7ad2f2b47ed9e64d5d14ba88b5f6cfad4d07fa9ae7a093ecf37ef6b6e092b596c055ba92b2eeb54f2e6d94f9ef3fd32b127ff48d63ecd17fc0ebceabd068e8bccff893efa20a35cff4786048ab1e0bcf326f26b03681d141feb68fb67ee67ace824aab06b15a4053352d23fb456bf99f67f92b220bcfb3c3f69aa5e63dc4dbeb420bbfda1f68abb049fc1591f279d5693fd1e8ea0743feae883fe9ad55b4ff005556abba96c4b2dd2797f4a679952575c04676a579fc11547047ff004c6b47cba7ff00aba73019f63aced5743b6d6219edafbfe3d7fe79d68cff00f5dab3e7b3493fd6fef2b303cffc5de1fb3d1f4dd96f670c7ff4ced3efd7ce7ae47a5787ef37cb67e47fe3f3d7d55e27d1def219d22bcfec94fe3f23efd7cbff0011bc2f6da1f9ff00d89fbbfefc907fac7ae5ad1ba4544e6bfb613ed9f6c8beff00f0413d7abfc32f888f6779025ec33475f3d6ab1f97e43cb0cd27fb75e93e03f105b5bc3079be74963ff4dfefd7972f7248eb5b1f6fe87a87db2cf7d6cd793fc32f1659f882cf7d94d5ea1049f68af668caf139a7b96e9955f7d1bebaa26641771fd9e9904956ab12effd1e6a9981b7beac561c17956bccfad42606953eaa54f6f5ac40a377a7fdb3e4af11f8a9f01edb50ff0049b7afa02aa5dd9a5c43fbdad6272d7a31ab1d4fcd3f1e784d34bbc9edbc9af2fd474b7b7afd0bf89bfb3fa6b1e7de597dfaf92fc79f0eef347f925ad632b1f255b0ee9bd4f149e3aa95bf77e1fb9fbf597e5fd9eaf9ae79ee36655f2feb49535435123299151454b526657a29f454b03b3a751535751910d4d454b41510a968d9454b2c28a2ac567202bd5ba2acc3b2a4a51b827fa3d7a1f80f4fb9d52f20fb3e9b56be1cf83dfc4979fba87ccafadfc1df07f4dd1fc8b9ff0096f499ea61b0f2ab2b219f0cbc076d6767f69bdd361824af5082348feed3208fecf525433ec28518d18d905151f994b59c8ea0aa1714c9e4aabe6573ce561a2cd4d5437bd5a8294257063ffebb54f507fb72d559f58abe6b08d1f33fe79541e625bd55f9fc9df597fdb1e64db2dff79ff4d2b29cf61a2deabac7970d25a6a1fb9ac59ff793793feb1eb4351bcb6d1ecf67fdf759f35e2cd23b9e67f162cef35887f7b79f60d2ff00f1faf9b3c41ae68fa3ff00c4b744b3f9ff008e49ebd43e3178e2f358bcfb1e9ff73fe7bd7cfb77a859d9de7fcfdecfbeff00edd7852f899d483c5be30b9f260fb143fbbad7f865e2c7fb66c8a6f2ff00e9857917f6e3de6bdbe2b39abe82f8797967f63fdef87a693fe9a56b4a3730abd0f6ef08c6971f3cb0fcff00f3d2bb282cefecff00d26caf3fe015c6e8767fe87f69d2a6f93fb93d743a6eb17f67ff001fba6ffc0e0af4e0ac8e73afb5d43fb521fdefeedeaecf1f99feb7fe0158b049f6c87ce8bfef8ab569f69ad00d0b592e7ee4b56fcbf33e7ffbee3aa9f6c4ab569795ad302d4353f98f6f54a7fefc5fbca8fed9fc74e72b01a3ff001f1f3d529e4ff9eb51f99f67f9e8fb67da2b272b8d05d69e9790ec97fe0159106fb39b65c7fdf7577ed9f67aa53de7990d633d8a0bad967fee3d65fdb3fb2fe4fe0a9eeff790ec96b9df33fe5da5ae19cac6913535593fd45cd105e27fc79d62cffea67497f78947fcb9c0f59f35c26765a549fb9d9351f63ff97696b1f4dd412b6e7fde43be2aefa5b18b32fec74412568c7fe91505dd9ffcf2aec8089e793f7d47db3f73557528ff00d4549ff2c69cb6023ff96de7563e95fe99ac4ef53ddc9f67d1e77acef077fa47fa4d714be24346dea579fe99556d3fd75559ef1ee26ab5ff002dbceac27b941f6cff005f50687fea77cbfc7556ea4fb468f7555743bcf32b2606ddd5e7d9fe4a7fdb3fd320acbbbff48bca9f4a93f7dffb3d3881bd7779f6387fdb7a3e7f3bfd77eeeb127d43ccbca5d56f3ecffeb7ee56a9d80e87fb43ed137fb15a1e67da2b8dd2a4f321df5d141bfc9fded745395d92cdeb7abbf3d644127d9e1a9a093ed15e844468fc96f47facff0096de454153ef4ad62041fd9f6d7137faea3ec7f67a3fb47deaafdb1e4ab132adde976127fadfde571bae687e1eb7b39ffe24f5d9dd5e3c7feabee5705e2db3bcd52ce74b786f3e7ff9e158d427a9e03f12bc61fd87a96f8b4d87ecbff4deb8d83c60ff00b8ff00531ffb15d978e3e07bd9c3fda5aade797ff4ceebfd65727a569f6d2433e9b143e67fb7257935f63aa1b1ec9f083c50f67a97fa9f2f7ffcb3afa5b4dd412be20f0cea1736f36cb8ff0044ba49be4afa83c31e28fb1f9097bfc7558626a743d569b3c6927faaa65a489710efa4af696c64496f557518fed156bccfb3d13ffa454c95d01c05d49796737fb15afa76a9ff003d6a4d4bfb92d677fabaf1a5784868eae0bcfb455cae66d3fd22b5e091edebb68caf7066e53eb360bcabde65764443fcbfa5795fc42f81f61e30f9ff00d5bd7aad26735a231a9455547c17f137e09de785ff00e9a257cf5ae69e91cdfedffcf3afd5bd7343b3d62cff00d221af8b7e3a7c37d1e3bc9de286867cf6270fecec7cab35455d56a5a1a475833c75278f38d999b455ba8aa652b184886a2ab750d66e571c4edaa5a2a5ae9e739028a2ac51ce057ab1453eb394c06559823fb452569695a3bea1493b9a421cecb5a7784ee750f93ceaeffc23f0aff7d07fcb47aeafe147c27fedb9bfd4cdff005d2bea0f097c37d2bc37043e4c3fbcad11edd0c1caaeaba183f0cfe1a7fc23d69bee3afa57a6f97f4a318a5a19f51428c68ad02994566cf25672d8e91f3c9f67aab3de7da2993c954aeef123ae29cac344d3de7fd36a2b3e0d927fa4ff00e44a8ffe3f3fe5b564e5728bd05e7da3fd556bff00abac883fe25ff25bfef1ea6add6c4b09e4a87ccfdf6caabe5bfdf96aac127da269ff00e7d7ff0043ae59ee0835cd43ccff0046b7a27d9a7e9be4d41049f68bcff62a97db1f54bcfb4ffcbaa7c90c7fdfa828dab4912cfc848a1f9eb94f176b09279e92cd5d2ea3a87d8e1d9fdffbf5e4b06b89ae7f68dcff00abd2d3e44ff6eb9ab4f96c8a8ee792fc57d71fced917f1d78a7c4dd42db47d37ec717fc0e4af49d5750ff84b3c5575a97fabd134afb9fedbd78ffc43d61fcefb4ff7eb823f15ceae865f81366b1a97fc7e7cff00f3cebe88f03e97adc7feabf77fee578af83b5cd2adfc8fb6f87bccff00a69695ee3e0ed5347bcff5b34d69fdcaf521b1c93dcf64f0cff6ad9fee6e21ff00b695d5da6a8971f27efa3ae474afb67fcb2bcade823f10c7ff004f71d7440ccdaf2dee3fe3de687fedbd4fe5dcfdf8a6f9ea082f2cff00e5e26f2eb46d74f4b8ff008f29a8980c8350fdf6cbdb3f2ffe9a56a797fc70d67799791fc971fbc4a9fe4a2005a8350fb3d687c9790feeab227d979557e7b3ff005544e56d0027df67ff005ceb3bfb73cbad8fb67da3fd7563eab1ff001c55cb356d4689eeef12f21df156241a87d9ef3ecd3555f33ecff3c5fbbff62aaeabff00130b3dff00c695cb53a1b40dbfed0fe09ab13ed8ff006cff0072a969ba87f6a59fef7efa54ff00f1f1feaab234269ffd226dff00c0f57ad24ff43d9fdcacb824fdcd105e7fa65202d4179fe995d25a6a1e5d701a95e2697357433c9fea2e62ada9caccc6a743afd1ff00d4d5af32b134ad43ed16753f99f68f92bde84ae8c59a977fe9167be2aa56b253e0bcff0043acf9ef3ec70efa27b0228eadff008e552f0749fe873bd5dd57fe3cf7d52d0ffd1f4dae390c8ffe3ce6dffdfad4d4af3cbb3aab06c93fd26e2b175fd53fd0e7ac67b0d07fc7be8ffbdace8350f2e1a2091ef2181ff82b2f5593ec75c323681b76bac7fa1ffbf5b56b27fa84fe37ae36d24ff43ae8a093ec7e43cbf7eaa0299a93de269736ca8e0912f3fe9a57377779fbed96ff007deb6b4ab34b7f93cef9ff008deb520ded2bf79e7bff0002568ff68247fbeae6bfb53f7dfe8ff712a1fed4fe0b7fde3d691958967656979fbedf2d5a8ef1e4f93fd5a573505e5cdbfc9fc7fc6f5a905e7d8ffd6d74d3988e97e4b7a9ab9efb67fdf7fdcad782f3fe7ad765395ee265ae7fe78d1e67d6882f12e289ff00eb8d7544110cfb3fe7b5727ae497ff00f2cbfd113fe7bd6c6a31a7fcbbd9fcf5c6f8824b9f27fd36f3ccff00a67533067cf3e3bf09d85c4d3bcbadea5ab5ebd792cff60f09ea5f6cb7bc9a4ba4afa0bc5d7967ac433db7fc7a2578278bb4bd1fc3f37ef6f21bb47ff9675e7627645d3dceafc31ae3dc79e9feb3f8d2be86f865e38b3d421fecad56be5ff873a87d8e6d971ff1ebff002ed257ad69578fff001f3ff7dc95e6c65cb23adec7d75e1993f73b2b6e7d95e73e0ebcfdcc17917fc0ebd27e4921af769cb991c9223a92dea941febb655a9eba224906a566979fefd72975a7fd8ffdcaecbcc4b8ac8d4acde4ac6aec346041fbcff96d5b769fe8f5cdea5a7fd9ff00d262a7e9dac3c7ff001f1fbc4ae25b833b2b7ad4824ae7a0bc4fbf5a10495df4c46cd3eaa412558ad802bcc7e26fc37b6f1269b3f950fcf5e9be652d063568c6aad4fccbf89be07b9d1e69ff00d8af2fb8afd40f89bf0cec3c61a6cffb9ff4aaf833e217c37ff844f529d2e3efd6553a1f375f0f2a6f53c97feb8d415a97567ff3caa9d72ce563ca9ab32beca8aa5a2b3e6b999dad14558aebe6b1c414fa2b420bcfb3d272b9a40cfa96b56d343b0d43fe5b7975daf867e1bd85e5e6cfb6549bc21cf233fc25f0ceff00c49e47d9ebea6f873fb35e95a3ff00a4ea1fe9127f72ba5f85ff000df47f0dd9c2f0c3fbfaf51ae98ec7d4e0f071b36ca7a769d6d6117936f17962adf99f4a5a8fed9547b5182868892aa515154c8a229eab525d4954a78ee64ae5a9d06866a579fc1156741a1fda26df2d687fa369759177ac3dc79fff002cd2b966513dd6cf3bf7b3799ff4ce8824fb47c917dcaaba559a49ff00c72b7a08d3fe59510008237b8a9a7ff4786a1babc4d1e1ff006ea8cf79fb9ffa7a7ab72b458191e27f107d9e1d917dfacef095e5cde79f7971f73fe58d65ea5a826a9a97d8edff00e072574ba7489e76c8bee25796a579804f27d9e6df2fdf7fb91d32d7fe7e65ff008f5b6fb91d677cf79793ff00cfd7fe815b7fe8de1bd0604ff58ef5bc376544c4f1c6b1f67d1ffe79dd3c35e5faaffc537f0de7d36dff007f7573ff008e5757e3192e63fb55cffcbd3feeeb83f106f8fc2b027fcfcd7257e86f03c5752ff893f827645fc7f7ebc7b55912f26d3acee3f83fe7857b0f8ee44b3d1ff75f72be69d3bed9e20d6277fb679759d32a5b1ef7e0ed3ecece1df63ac4d1bff72eebd9340fb65bfcf2c30dfd78df83a4bcd2e1ff005df6baf5ad03c51f6787f750d7a30d8e391eafa56a1fb9ff0053f64ae920bcb9f26b83d0f5cbfbc87f750c35d441ae5e47ff001f1a6ffdfbad0936fccff9ebfbcabb07fa1ffc7bcd54ad6f2daf21ff009e7fefd5d823f2ff00e9a27fcf3a00d882f3ed9feb689ecffe79563fc96ffee56c799f5a00ab3c89ff005cea0bb91e3a2792a0fb627fcb5ace7b0197fda1e65e7ef6b6e093ccff0096d58977a7f995cf3c773a5ffaaae643474377fbbf92b2fcb493e78aad41a87996758bf3d9de7fb14328c4bbdfa3eb1be2fb8f5a306a1f63d4bf75f71ead6ab1db5e43ff00b52b1ed64ff976aca6690346793fd33fe79bd626ab27efbed3ff002d2b46793ecf37efab2f52d9e4fee7ee5632342d788ecffe120b3826fe3adad3750ff896c0958b6b27da2ce0aaba8ea1f63bcd94e06733b5d2af3f82b534dd41239ab91d0b7fdb29f05e3dbc33bff72bd0818b3b282f3cc867a7f99f68b3ac182f3ed10efab56979fe995d0f62586b9ff3ed45ade797a6eca87fd66b1ffa054dfeafcfa408352ff906c15c5789af1ff716d15757a96a1e5d9c15ca6a51fefb7d6153a1487c12797f2552f137d9a49a0a351ff47ac8824fed4bcd95814749a6533ed9f6c9bfdcaced46f3cbb39d2b2ffe7859c3401bda6de7db2f3656c6a5a87d8fe4b7fbef58fa76cd2eceaadafda7549bed317dfaa44b36ed7fd4ec8ab52d244d2e1df2fdfa65ae9ff6787f75fbcfefd1069eff00f2d6a908d482f1ee3e7968ff008483f82dff00efe5624f227fc7b45fbc7ab569a3bffd73ad220743a7489ffdb2ba2b4d412b8df2dedeb6ad7f77f257453dc4ce97fb613d28fb63fdff003be4ac482448fe7f27e7abdfda1797bfea61f2ebd2a6484f1bde7fcbe5727a8c7611ff00c7c7fa5ffb11d74bf63b3ff978acebabcd2a3aa981e37e31bc7b887f7b0fd82cbfb95f39fc46d51ecfcffec1d1fec8ff00f3deeebeb1f135e5b5e4dfbafde57827c46f07bf883e78a1ff0081c95c55fa1a44f0af87be387bcd4bec77d79f6bbdff009e95f47e95a83ff6969d736fff00034af9735cf0ba68fe24ff0047fbf5effe03f16249a3efff0097ab6af2e7b1d5d0fa67c09e204b3bcfb35bff00c7ad7bc687225c59feebee57cbfa55e2793a75e45fc75eff00e07d42bd2c2fc2ce691b5aad9bf9de74556bccfb659d4f77feaab3abd18924f07fa4434412573d3ea1e5ea5fedff001d4d3ea1e5fcff00c1533024d474ff00f96dfc1589fd9ff68ad7f31e487679d5ca6a31dcd9ff00cb6ae6a9d00dbd3b50fb1de7d9ae2b7ad3bd79add6a1ff0009243f6697f77755d2e8178fe4ec97efd14c0eeea4f32b2e0bcab75d30026f33eb4fb7acb9e4a2093ecf5a01b75e3df1d3e1dd878a347dff00c75eb50495c3fc468ffe25b3d673d8e5af1bc4fcfef13f83fec736cae427d0ebdafc77e1fb9b886778a6af18bbdf1d78b5773e4eb2b48c49ecfecf556b467aad587358c0ee28a968aee94ce40a2a6a9608dee3fd554735cb8857b5fc05f03de6b97903f93f27f1c95cd781fc2f61a7ff00a4ebbfbc7fe082bea3f857aa5cde43fe8fa6fd82cbf82ba68ee7a1848dea23d434ab34b387655ef32a9d45e67d6bb65d0fb38c7950ff00b654750cf225bd33ed959dca2ef9955aaa7db123acf9f50fb47ff1153295901a1556ef50f2eb1751f10259ff00a345ff001f55893fdbf54ffa744ae1a93d4d606a5deb89ff0003acb837eb936c8bee7f1d41069ffbef27f82ba2fb65b6970c0914358f35d95334208fec70fd9a2ab5e67d8e1f3a5aabfda16da3c3f6997eff00fcf3ae7aeef2e750f9ef66f2d3fb95ab9591996bcb7d6352fb4cbf713f82aaf89e4fec7d1e77ff005974f53ff68259d9fd9bfbf5467ff8fc83fe5a5eff0007fb1585497368052f0ce87ff08de8f3ea57bff1f4f5d2ff00a3687a3fda65fe3fb958f7779e679fff002d3656c411fee6d7cdfde3d14e3baec063fd8ff73b21fbf79f3cd5d15dfeee1df2fdc4a82d63fdf4f735575fdffd9bfedff1d6ae368b0479ccfbf50fb55cea7f72e7ee27fb15c078bb507b89a7497ee27c895dfcfae7db34dd5ee7fe7dbe448ebcb27fddf856e9e5ff008fafbf5e4cbe23a3a1e29f17f54fb1f86e7ff63ee5792f83acdedfe796cfccae97e317fc4c21fb37f03fce9557c1de1fb3ff0096b5d10d83a1e85e18d42c249bfe79bd7b0f83b43fb67cff00bed95c87862cedadff00d6f93fec57a0695269571febaf268eba207348eff4df0ba7df8aba5b5fb659ff00aaae5f4db3ff009f7d62ba4ff498ff00e7b7fdb0ad5121771dcc9ff1f1fb8aabf3e9ff00f2dab7a0d43ed1feb7ee5559e3b6ff009f3f9ffbf59d4e804306b89ff2d68fed0fdf7fa3cdff006ceb2eebecd59df634b8f93cef9ffe7a562c68e86ef507ac8babcfb45529fed9a5ff00adff004b4a82092daf3fe3de6f9ffb8f594c19b706b1e5d13de7da3feb9d677fc7c7c92fdface9fed96f0feeab3045efb63d9cd56bfb412e3fd6d73bfda8f243fbdff8faa9ed2f3ed10fef6828b5fda1fbefdeff00c7ad68dd69f6d79f3dbcd5c9ea5fe8ff00eaaa7d0f58fdcd202d4ffe910fd9a5fbe959d6b79ff2c65ab575796de77ef7efd67f97f679bed317dca00208decfcf4fe0a35293ed967b3f8eaeeabfbc8764559da96fb7f21ea96e074be1f93f7353cffea677ae774ad43ec70d74b06cb8f3eba496320d63f730253ed64fb3cdbeb90f33fe27d07f72bacbbd412de1ad2022d69bac7da358ff00629ff6cf3269d2b0743bcfb3cd3d1f6c4b7bcad00d1babcfb443b2b1f5293ed134094cfed0fb45674f27da26ace63455f13de797f27f1d49e1f8ff0073beb067912f3529eba4b493ec7f25732dca0d57fd33c8b68aa4f92dfe7fee555d72f3ecf37eebefd4d3c9f63b3fdefcef5a22584f27f6c4d024bfbb4aeb20fb359c3b3f82b89d0ecef2f3f7d2d6f5d7d9ace1ff009e8f5ac04745fdb8967ffc4541059eabaa4de75c7fdf8ac1d2a44f3bf7b0f99755d27db2f2b542668dac7fd97feb6a19358ff9e5505a7ef3e7966a9e7fb07df9bf79544957fb42fee3e48aad69525cff00cbc4d557fe128b6ff8f6b7fdda51050523aeb4d412b464d73f73febab90d377dc7fadadeb5fb1ffd31ae9a3d4193f98979feaa1a8278ff00e98d4f3de5675d5e5cd7498cce7b51bcfb47c9159d797f8c743b9fdff9b790c69ff3cebd4355fed5921ff8fcf2ebc53c79a7dcff00d04be7ff009e95856d821b9e4be31d4347f0dcd07da3f78f5b7f0cbc51a57f6f4f0c567f25e579b7c43b378fcfb9bd9a1ad0f84127ee60bcff00a6d5e5d4dcee82ba3eaef865e284d43fb474af27cbd95ebbf09fc4173aa59ecb8fbf6df257cd3e11d967e3683fe7d6e6bdafc3fac3d9ebdf6cff00569ffa1d5529598aa46d63e85ff5959d7767f68ff7ea7d36f12f2cea0d4bfd4ef8bf82bde8bbc4c4e0fc6379fd970c1792c3fec3d1a76a9f67f23fe5a5abd6deb91a789347bab6fe3af2fd0f5c78e6fb36a10f96f6df7eb17b81d97f68259ffd7ad68dd49e67cf5c9eb9ff0012bbcffa75b9fb9251a549e5d9fd8ee3fe012565300d4b4ffdf7eebefd6dda7ef21ff9e6f5ca6a579fc171ff000092b5343d6135487ecd2feeff00e9a51003b58247b886b5f4ed43ccae02d35c7d2ef3ecd71f7ff82b7a0d71239bf7b5b440eae7d97159ff003d9ffb9597aaea1e5ffa645f72b434dd5135486a981a9a75e7995c8fc54f14269fa3ce92d6f411fefbf755e5ff001c3fd4fd9a5ff80563525689c989f80f957c4de20bcd1f529d3cefb4593d707ae6c93e7aea3c5dbecef3ec771ff00ae36eabc1ab3f78f98a9d0caa8aad5455cb29989ddd14515dae67212d68e9d79fd9ff003d674353da7faef3a5a14cb89ea7e0ebcb6f3a0fb443e63d7d4fe07d72dae218122fbf5f1d7866f3ecff00efff001d7a9f81fc417367fe93fc6f5d34a7a9e8e11da47d3b3de51f6cf2e1ac8d2a4ff896c3732d676a5a83ff00c0ff00f40aeb9cf43eba0ef145d9ef3fefba920df589f6c4b7f9289f50f2fe4fe37ae652b9a1a1a95e25bffa345599f3dc7faaff00beeb3aeb504b3f93f8e9f75a83f93b3f8ff823a19a403cbb6d2e6ff47fde5d7f7e8d56f3ec7ff1f1fbbffd9eb2e7d43fe11fb3fdefef2f6b2e0bc7b89b7cbf7eb0a92b58d0dbd3b507fbff00c1ff003c2aeffcbe7da6e26f9ffe79d72306b9febee7ff002254ff00db09793409fc1fdfacd4c0ed6d647bcff49ffc895ceff6e5b7db27b6b7fde3a7fcb4ff006eaaeabae5cea9ff0012dd33fe07505a69f6de1f87f7bfeb3ff43a1cae6733a2d2b4f7f267b997fe3e9ffe5a555fed07fb1cff0067fbff00711ead6a5a87d8f47ff7fee5625aff00c4ae1fb4ff00cbd568f633353cc4d2fc8d362fde3d6de9bfbcb3dffdcff969597a759bfee3fbeffc75a977fbcff438bfe3d6dbefff00bf5ad22596a0fde6a5027fcbaa7fe8754bc5d79f639bf75fbc7adad3bfd1eb8af16ea1fbe82e7fbf3574d5f8471383f13469a7e83f66ff0097a7f9e6af2ff1c6a1ff00127d3becff00ee3d75fe26dffd8fa8dccdf7de6ae1bc63ff00201d96ff007edbe74af223bb3a7a1f387c46b3bcbcd62d5ede6ff7e3abba047f63ff005b5893ea0fe20d4a7797f775d6787f50b0f3b64be7495a0743d33c1dac695ff5d2bd7b40d3f44d53fe61bff6d2bcd7c2567e1efdc7d9ebd5f43d3f47ff0096537cff00f3d2baa1b1cd23aeb5d1f4af27fd1fceb4ae8b4dd3fcbff96de656269567fd9f67ff001f9e655afed0492b4219b7f634ff00ae725413fda6deb2eef5c48eb3fed8979ff1ef79e5d6353a02352ef58ff9f887ccacb9ef2c2e28f9ff00e5e2a09e3b6bcff96de5bd62c6320d9e77eeaa0d4bc2f6127cf14de5bd13f85ee7fe5de99e5de5bffc7c4352063cf1dfd9cdfe91fbc4fe092ad41ac27fcb5a9ed750f2e6d92cd556ea3b693fd6ff00df74006a5a7a7fc7cc5ff7c555b48fecff00ee53ff00d3ece6ff009e96b59f3ea096f37eebfe071d004f3feefe4acebbd3dedffe26565ff034ab5ff1f90eca8f4ad41ffe597fc0e3ace6344369b358ff005bfc753da46f6734e9505dff00a1cdfbaad7d4a4ff0097cffbeeb3287cf25b5c43fedd65c1fe91f24b525ade279dbeaaeabfbb9b7d690033848f6f36caea34ad43ecf79b3fbf5ca4f79f68f9eb435293f736b79156c8965aba93ecfa9568ea3beb9ef13ea1e5f9173fdfad0bbd53fd32d7fb8f4c41a55e7efb651a8de7d9ef2b3ffe3cf58ff62b4352fefd00647db3ec747cf6f58905e7db352f266abdaade7d9e6a4c682ebfd1e6df47f6c7fa651a97fa44358fe67fc4ca0aca651d2f97f6c877cb4687ff00134f3ee6e3ee54f049e659cf59da1ffc4d21d9fdcaaa7d496757e67d9e1fdd573d77ae7ee765105e27efee65ff008f54ae1a7fb4ea9af6cfefff00cb3ae9888f50d0f50fe3adeb4d62daf3fe9a495c8da59dcdbf9367feaec53efbd687f6c5b69ffeaab44075fe5dcde7fd334a9e0d1edaf21d92fdcac4d37c417371feaa1ad49ef2fee3c8ff0043ad602668f97a56970fee7c9aab3ea16dff003dbcca83ec7f6c9bf7b675a3069ff6787fd1ecfcc4ad0920b4fb4d68f95ff4dab2fec7f639bfe3ce6f9ebabb5d0fecf0ef97fd656b003220b3fb455a9ecdeaeddde259d647f6e3ff00cf9d68673307c41bf4b87ceff58ffdcaf0df176b97f71e7f9b0c3615effa8e869a87fcb1af25f1c784ec3ee4bf7eb2a9f090b747cbfe3bff00a78bcfb5d55f841e204fdfdb7f726ff5756be26e9f6da3de7ee61f2ebce7c0fa87fc56dfefd78dd59ea2d91f60da5e7fc83b558bf83e4af57b4d412f2183cdff007ebcbfc31fbcd07fdbb6877d77106a1f689b48f37f77bffd755c3e2444f63e9df0749e5e8f027fdf15b7049f68f392b94f08c5ff0014dffe815a3a55e7fa1ef97f82be8a1f0a397a905dd9ff00af9abcbe78ff00e27d3db5c7f1fdc7af56ff008f8f3edbfef8af3dd4a4fdf40f2c3f73e49aa6a74028cf79ff0012dfecab8ff803d52d37fd23feddaa7f137fc7e57357578fa5ea505cff00cbadcfdfae69948d4d5637bc87ed365589a75e7db26fb34bfbb74ad8fed0f2ef3f75589e27d3fecfe46ab65598cebffb43fb63e4b8ff008fab6ff96956a7d43ecf0fef7efa572106a1f6cf22f2bab8244d521ad2005a83c5091c3ff4eb5a3e1fbcf2fe7b7fb8ff007ebccaebfd1ef274ae8bc23a83d9cdf6697ee55899ec9a749ff7c570df1d2cfccd077d751a4d65f8e366a9a3cf672d3abf01c788f80f8abc771ff6e69bf698befa5797d7a4f8c6cee7c2faf4e9fc0f5e7f3d7c96265691f3553a15aa1a9a8ae173313b5a28a2bbdcac73c42ad41fe99fee533cbfb455a82f28532cd4824fb47fa1d97f1d7ba7c28f07bde790f7bfc1fc15e17a548fff002d7f7695ec1e03f145e6b1ff004e9a5d76e1e5791d986f88fa33ed89e4ecb7ae535cbc7b7f92dfefbff1d5ad0ef3ed167b22fe0acebbff00a65fc7fc75d55b747d752f84669bfe87f3ff001d729aff008c2db4b9bf75fbc7ab5e26d63ecf67b3fd5d707a569fff000926a5be5fb89ff2d27ae467540ecbc331de5e7fc4d7559bfeb8c15bd77a87efbf73fbfba7ff00c72a8ff685b7fc013fe5a572f3eb9fda179fe8ff00f1ebff00a1d68cd0d8d4bfd76ff3be7a82effb9e77fd76a67fc79ffa4dc7dffe08ea0d2acfcc9b7ea1ff001f573f720a903460b37d53c8797f776bfc11d3e7dfaa5e6cb7ff0072a7f136b1fd970fd9a2ff008fafe3a4d0ff00d1f47fb4ff00cb47a00d1ff43f0dff00a35bff00c0eb3ffe431a97db2f7fe3d6da8d4644b3b3fb4ff1bd677fa4eb137d9a2fdddaa7faeff6e802d5dde7f6a7cffdcff5295afa8c7f63f212e26fb9f3cd50f87f47f32f3fd84f9d2b13c6378f7934e96fff002f3f2506533b5f03ea1fdb1e7eabff002ebf721ad883fd1ffe075078634b4f0df856d6cff8ea0bad43fb2f52ff00b63ff8fd7752d8c99b13c9febebce7c63a8797fd9d34bf72bb59e47fecd82697efbfdfae2bc7127fc7adccb0d6589d91a40e6b51bcb3d42f204ff596be4fcf1d78c78ee4f33418122fe0ff009695e8da8efb3d62d7cafb9735e6bf162f2cfc37a3ea36d71fc75e62363e70d374fd56df529fca9abd27c3f789ff002d7479b7ff001bd79ce95f698ef37c3f72bd6bc31a83dbff00ad9be4ada21d0f4df08de59f9db3c9af49d2a3fb3ffadd36bce7c31aa68927fad86bd0f4dd52c2dffe5b576c7639a475f6925cff00cf6ab5ff000903ff00cb5861ac4d3750b3bcff005b5b1fda1a57dc8aa89127d713fe5b59d644fa85b7fcf1f2eb47fd1ae3fe5f2aafcf795954e80677fc2417367ff2daa0ff0084a12e3fe3e21ad8fecb4b8aabfd87f63ff96358818ffda0ff00f2ef7953ff00c241736fff001f1f72b53cbd2bfe7cfe7ad1b58d3eff00f07fcf3aa4065c17967a87fcb6a7cff63921d94c9f4fb6f3b7c50d1fec4df7298147ed8967f2552bbd9ff1f3fc74fd563f2fe78bfefdd65ff687da21dffeadff008e802d3ecb8f21e2a9ff00b3d24f9e2fddbd72ff006c4ff96b5d5daffa9df14d4018be2dfddf91fb9ad79ff776705cff007e9fa9469aa7fbf58b05e7da3fe25b52c68cebb93ec736ff00e07a2eef3ccf21eb2f5fff0053505aff00c79d65328e8b52b3b6f260b9b7fe3a3e4bcd36b2ff00b43ecf67469b79f68b39e8812c2ea3fed0ff008053ff00e3e2ced7fd8aaba6c9fea3caad48365bcdb2b64227d73fd7541f6cfdcd335193cc86aafdb3f734c02d634926fb4d175b3cefded107eefe4a355bcf334ddf49819d049f68b3a27d97136cff0096956ad7fd4c0f506a31ff00c4cad6f2dffe0742027b5bcff5f6d56b438ffd0e748ab12d637b7d627a27f107d8e6d95ac00d19f50b3b79a7b397ee2565ff00c7bffa67fcbd3fdc4ac49f544d1f52bab997f79bfee54306a8fe4fda65fbff00dcad501de69d25cfd8f7ea1fbc7ac4d4b58b9fb66c8a1aa569e28ff97697f77576ee4f2fe7fe0ace7d068ded2a47ff009ed5d9687e284b7ff98957875deb896f37faead4d0fc51ff005c644a852b03573e9cd3b5cb6d421adbb4d1ff00e7debc1343f1c7fd39d7a4f87fc41f68ff00e375e852927b92d58ed678f558ff00d543f3d529f4fbfb8ff8f89a68eb52d358ff009e5fbcad1f33ed10fef6bd2828b5a18cce4fec7f67ff00555973ea97ff00f5cebb9bbd3ecee3fe58d60eaba1a7fcf1fb5d29c6d633394bbf143ffc0eb8df13eb89710ffa6c3f3d7653d9a7fd73ae6bc4fa7fee7fd7435c55763486e7ce7f10ecedb58f3fcdfb95e0169669a7f8da0f2abe93f1c59a7fcf6af11d4a4fecfd7ad7fb95e5d4dcee86c7d0ba1c896fa0ff00aeff00963f3d76df6cfb47f646a52ff1ff00a2d79478677fd8eebfe5a23d7a35a4773fd9b05b45f7121ffc7ea11353a1f507c28d43ed1a6cf6dff3ed5d0c167f63d4a7fee3d79cfc20d412dffd1bfe59bfdcaf509ffe583ff73ee57bf43e0462ce7bccfb3cdb3fef8ae6b55ff4cd4bf7bf7eb6f5cff895eb105e7fcbadcfdfaabaae8ffe99ff00a03d5cc938ad4af3ccf925ff00bf959d77b2e34d9eda6a9e7df1ebdb2e2b3b558fec70cffdcae6a9d00c4d5637f260f2befa56a69b79ff00090785674ff97a4acb83fe261a6ffb6959fa75e5cdbd6404fa6de7fdf1fdcae92d358f2ffdcae52093fe26544fa87f67cdb25fb9401d0fdb12f2f3ecd2ff00c024abb69fe8f79f66b8ff00805701aade797340f17f057a35a5e2788347b5b9fe3ab89323d6bc2379f6886b94f89baa7f637fb95b5e18ff00535cbfc46d62da4f3ecee3efd5d6f80e1c46c7cdbe3c92db58f9ebcaf52b3fb3cd5db78c64ff009e55c1ff00acaf86c57c4780fe2636abd5aa6d7088eb6ac514faedf6872851454de5fda28e72a25ab5ff0048aeff0040d41e3ff468beff00f05701e67d8ffd57dfaeafc251fd9e6fb4dc7fc023ae9a13d4d16e7d33e18bcb6fb1ff0066dbff00db69299acecff80564783b584f3bec765f73fe5b3d6bf883fd23e48bee7f1c95ec5495e28facc1fc079cea5bf58d4bf7bf729ffda891de7d9ade99aac8f71f25bfdcac49e44f0fe9bb3f8ee7efd7353ea7add09355d43ecf0ef97fe011d68687a7ff0065e9bfda57bf7ffe58c75cde9df66fb67db2f7fed8c15b505e5cea979be5fe0ff9675b01a33ea89670ef97fe3e9ffe58568f87f7dbcdfdab7bff001f4ffea6b9d8234bcd4b7ffcbd3d5ad47584b7bcfb37f73efd001a95e3ea179feffc895b7ae6b896f35ae8917fc0eb9482f3ecf793eab71fc1fea60aab04773ff1f317fc7ede567203a49f50fed49a77ff009754fb95a3a1de7fc49e7b9ae6a093cc9becd17fc7afdcff0081d745aac8967fe8d6ff0072dbee7fbf444ce6751a1c9ff12dfded5582cd2e2f204fe0aab3de5cdbf86eba1d0f4ffdcc1f68aea333a5f33f8e5ff80573b3d9a4936fb8ff007eb46793fe7ad676a5795b3d8964f7527db2f20ae53e217fae834dfee7fcb4aeaf4d912de6813fefbae6bc71b2df52df5cf5be134a6715e27fde69bfbafe0af9cfe3f5e7da3fb3bfbff7ebdef5593fe25b75ff002cddebc13e3d7d9bfb4ad6da5ffbff005c31d8d8e2b4393fe7959d779a57fa47faa86b89b5b3bcb3ff005bfbc4fe0aea34ad42f2dffd5435b40ce67a368766f6ff003ffabaedb4ebcfb47fcb9c35c3687a83c9feb6bd0f43d62dbfe5ac35b23166d69b67ff004c7e7ad1f2ee7ced9f63a641a8587fcb5a83fb713fe594d5ac041e6ffd31aab77789ff003e7f3ffd30aded3758fb67fcf1adbb48d3fe5afeeea9c6e2672169f69f27f7bfbc4ae8743d9710feebceff00b6f5d47996d1d32eff00e3cff75f7ffe7a56bc849cf4f669e4fef6b2e7fb1dbfc914dff7feb7a78de387fe7a3d73575e204fb97159ce364522add6a0ff00f5cd2b2f52d43f73feba9f75ae5b7fcb286b2eef50b693fe58f995ca5224f935487f7bfbcac4ba8fcbbcfdd4df27f1d1e5db5bff00c7bcdb28d4af2daf2cff00e7a3d521b3135cb3b9d3ecfed317ef13f8eb6f4ad73ec7e1bfb4cbfbcaab06a1f67b39fed1fbcfefd65e95796d796775e4fdca608ee7ed9f68f22e6b2f5cdf6f37da6aae9579fb982e7f82a0bad73ed9673ffb1498c66a327db3c87aced4a4fdf6ca7f87ecee6e2cff007b58baaffc863f735cf53a01760d43ecff00eb6aee9b225bff00adacb9ff00d551a76ffb67ef7ee514fa92ce8b4cab5047f689ab3ad24ff896cd6d56a0fde4307f7eba1083fe5b4fe6d327ff00535a9aad9f97f3c55cecf27efa9804f27fa8a3ed9e6433db4bf72aaffc7c69b469b27da3fd6d260320d43cbbc82dbf82ad4ffbbbcac4d4bff1fa3fb63cc87fdb4a903475291ece699eb979f7ea1e45cd4faaea0f7137fb159706a1f638767f7ea90d1ceea5f6c9358dff00c158b75aa5cfdcb79ab6b5293ecff3ff00e395cdff00a35c4dff003ce99477169ae7ee607b8fbf597e20f891fb99eb1752bcf2e1fdd571306fbc9bf7dfbca2d703d0340fb66a90fda66fdda5779a547f6cff0055fbbb5fe392b83b5d43f73b22aeaf4ebcfdf409710ffc4ad3efd5c6007a4e9566f71f3c5ff1ebfdfaecb4effaedf257016baa7fc241341f689bec965fc10575f69f60f3bf7bfbcfee56c958967a3687ae2597c91576ba76a9f68ff5b0d792da7d82dffd6de56f41e204d2e6d9e4ff00db4ae9a52b18d4e87aef976de950ddc7735cd687ae5b5c43fba9aba5fb67da21ff0062bd18cae62705e26f0fa6a9f3ff00ab7af36d47c2e9ff002f1a97fdf8af61d563b693fd57dfaf2fd7fc3f796ff3f9d5955d83a9e3de27f0fa5bf9ff00f2d2bc2fc63a5dff009dbfc9f2d2be88f1359a7df966af11f18d9db5c435e3d6e877523a5f87978ffd8ffefd7b5cf79ff127b5b9ff009f6af0af87325b49a0dd7efbfe3dabd4fc23f6cff8f6ff00975fb36f7ae6349ec7bafc27d971340ffdff009ebd867ff48b3fdd7df4af14f86527fa8f2befa7f057b0c1febb7d7bd87f80e5ea55d57fe261a0cf58f7527db2ced5ff00efbad1824f2e6d46b13fe3e3479ffbe95d12d8899c3fc43b37b7f23528befa5676a5aa799675d7f996de24f3ece5af35d73fd0e6fb1cbff00ae490a06469579fd8f793ff00cfabfdca66a579f67bc9de8f2ffe5da5ff008055583fd3219d25fbf582342ecffe91e45cc555752d43fb62cffdb4acbd0f50ff00976fee51aaff00aefb4c5f7294b62905a49f6cb3ff00d0ebb9f08c8ff63fdd7fdfbaf3cb4ff479bed317dcaf49f08c758295983d99ea106a1f63d36079ab83f8b1789a869b05cc5f7ea7f1c6a1ff0014acef6ff7ecebca355f187db34d81ff0081eb9ebd4f74f0311b9e6de20bcff5fe757355bde268ff00d337ff007eb06be42bcaf2390869b562a1ae3901db51454b5b9c41441be8a92de8e6b013c1fe87f3ff001d68787fed3a86b15935d069dfe87fea7fe075ad29fbc6903d874ad512387ecda57fdfcaee67912e34dfb345f7ff008ebc5343d712de6ff612bd1b4dd53ed1e479d5f434e5cd13d5a12b331355df1fc9157373ff00c7e6fb8fe0aef3528ff7335e7f7ffd4d79ceb9bee26fb1c5f7ff008e4a967d4465cd1441a56fd62f27b9f27e44adafed0fb1ff00a35bfef2eae6ab5dc9fd8f6706956ff7ff00e5b49597f6cb6d2ffd27fef8ada9f528e92eb504f0de8ffbafde5d5cd55d2a3fb3ff00a65ed62ffc7e7fa4cb54755d41e4ff00468bf8eaa606dda48fe20bcfb4ff00cbaa55dd575c7ff977ff0059441b34fd1e048aa1ba912dfc8fefa511035343b3ff004c83cdff00976ad7f924d63ed32ffc7aa573d6b78ff73f8ee6ba49ff00793416717fc0eb43399a33ff00c4c2f2d53fe5d7efbd74b6b78fac6bd3ff007121f92b06d23fecbd1fed92fef1dffd4d757e188fec7a6eff00f97a7ad69ee62cbb691f996759706f93e7fefcd5a36927faff00f6e89ffd1e6d9ff8e574cb6408ced2a3f33c493dcff02573df137fe3f2ba8d0f7dbe9ba8dcff00cb4af3cf18f883fd33484bdfbf795c95be13681e6da95e27f66cfe57ef1ebc03e2beb8faa6b1027f72bdbbc4127fc23f35d3ff0007f1d7cd3afc9fda9af4effc1e7570743436f4a8de487f7535779a1e9f79ff002ca6ae0f4afb357a1e87f66ff9655b53ea6353a1dfe951fd9ffe3e26ae96d24fb3ff00d34ae774393fea1bf257656ba7de5c7faa86ba11916a0d4347ff0096b5d1695aa68f79ff001ef4fd2b434fbf710d6dc1259dbd74521323fb1db79dfba87feda475b76b66fe4d5dd3bec7e4d5e835cd2a3f92de6af463b1252fb15b7fcf1a8eeffd1ffe99d68f98979f3c5589aade25bffc7c567525629104f227fcb29bfe015ca6a5225c4dfea699aaf8a1ecff00e5cffeda257273f8b2db50f922bcf2debcda92b948cff137eeef37d7213ea0f5bdaaeb8ff72b80f107882dbfe5e2b9e66d03aed2a4b3ff0096554a793ec7ff004cebcbe7f163e8f37fa3cde6256f41e2c4d621ff009e9597358d0d1ff84a2e6de6d9feb13f8e8b4d52cff7e96ffbb4ae275fd51f4fff0049b2fb9fdfab569aa27d8ffb4a586b484ae07a3787f58ff439d22ff575a3047f68f25eb83f0cea097979bffbf5d0c1a87d9f589e8999ccded4af3ec7f2562cf27da3f7d557fb43ed936c968d4bfe3cff0075f712b308137fb156a093ff001fa853fd1ecff7b51e95ff00130ff49fe0ad201336fcb7f3a9ff003d9cd05327fee568f97f689a0ad918b37bfe3e21d95c4eabfe87a94095d2da6ffed2aabe2eb3f321fb4c5fc14c4627cfa7de6c97ee3fdca67fc7bd9d6f5d47fda90dabd55bbb3fb47c9498d1c86ab79ff132df1552d4af3ecf79bff82a7d4a3fb1cdfbdfb956bcb4bcb3d92ffc024a928c19e4ff005e9fc0ff0072b1e7ff0048b3ff0072b460d3de3f3edbfef8aaba97fcb7f2beff00f1a5520394badffda5be5fb9589fd9ff00ebdeb6eee478e1ff00d02b9abbbcfb3de5301fe20d41ff00b360fefd7153f883fe5da1ad7f176a096fa6ccff00f7c5785eabe2c4fb67fcf3ad2316f63489efd69ae5e7fd73fee574ba57fc2497167fe8ff00bcaf9a7fe16c7f65c3f66ff5895de7857f698b0b38765c79dbeb4e59a14cf64f33c4f6ff00f1fba25e7fd74aef34dd1ee6f21df71ac4d689ff003ceb9ef03fed81a27ee3f7de65aff1c13d7b0695f113c31e2cf9f42bcb3bbb57ff00986cf47bdd4833fe1e69fa568fe7dccbe76ac9ff004c2bd5b4ad6344d62cff00d0b52f2dff00e79bd62f847c1f676f793ea5a57fc4a6ebf8e0ae8755f0bdb5c4dbee2cff00dfbeb4ad60448820d61f43d4bfd361ff00b695e8169aa25c7cf15e7d91ff00f1c7af1ebb91f47ff8f7bcfb7e97ff00a05751e1fbcfb459feebfefdff000575c2563099e87a96b1e643b353b3ff00b691d713afd9a5c43febbe4a3ed97367ff0020cbcffb613d72faaf882e6e3fd6feee4a7395d040c8d73ecdf72bc13e2359db7eff00f7dff0082bd435591ee3fe9a5797f8bbfe9afeeebc8c4ec7440e5be1ceb96d1ff68a7fcb47af79f076a1f68d7b7cbff3edb2be5fb4d9a5f8920b9f3be4fe3af7bf03eb1f6cff0049ff00be2b961b1b4f647d19e03bcfb3ebd03c5fc7f7ebdd608fed1e43d7caba1f88134ff23fdbafa8fc31ac26a1e1bfb657b585d99848ab771fee67f2ab120ff43d4bfd87fbf5e81f63fb443fbaac19ecd2e21d95e8c483cbfc5dbfc2fe36b5b9ff00972b9ff969589f10f4ff00b64d3ff7fefa576be318ff00b52ce07ff9f6fbf5c57883fd32181ea2a740383b5d43ed967feddb555fed04fed2df53eb9fe8736ffe07fbf59177ff002c1e2ae2abd00ced72f3fb2f5edffc1577fe5f3fd8b9fb958975bef2f27b696b534e8dfec7fee7dcae1a9d0a468f87e37f3becd5ea7e1f8fcbb3dfff002d12b8af0ce9fe67fa4d751ae6a1fd87ff00b3d67cd64ce6af2b233b5cd612486ebff45d7825d49fd97e7db7f05779e26d53ecf36f8bee3d7995dc9fbeaf9dc5d4d4f0252bb617527db21aa152d15e34a5ccc92ad152d4550077545145448e70a4b7a5a2a79ac05cf312de8fb63fdfa8684ff48a39c0dad3af1ee3e4fe0af4082f3ed1e45b7f1bff00e815e6b6b27d9e6dff00c095d2da6b0f67673dccbfbcbdb9fb95ea61ab725cd21f15cf64f312f2181ffb9f27975c84f67f639b7ff1bd43a56b89a5d9ecff0058ff00c75b5f25e69bbffbf5eb46a7b43e930f5b4b1c3cf1ff00c4cb7cb589ff001f9a94f79ff2ea9f72babd574ffb3c3fedd72fa8efb387fd8ae8a67a88352d412f2ce048bfe07597a1c9fdb1e24fb345f713efbd433ffa3e83fedbd68784bfe2570dd3ff007eb528dbd4750f2e1fb35bd327ff0047860b6ff97a7a34e8d2dfc87b8fb8944fa879734f73fc6ff72803534a912df58ff721aeaf4eff0048d36e9e2ff8fa7fe3af39d364fecff9ff008ee6bd27c3367ff14dc16dfdff00bf5b40ce66c5ac7f6cb3b5497fe5dababff49b3877d73de12b37bcbcff00a724ae86d64fed8fb53ff079db2baa998b2d7fc82fc8acef33ecf79be5fbf5a33c9ff13eb54acef1c49fd9f0daf95fc73554f6044fe67f67e83bff00bf5e31f11bfd226b54fe349b7ffc02bd37e236a9fd9fa0dac3ff007dd79c78c637fed2ba7ffa63f2579d50d23b9e73e31d412e341d5ebe59b4fb4dc5e57b8fc5ed43fb3fc2b0795fc73578f69dbeb956e7433a5d0fecdff2d6bd1bc23bedff00e3deb86d2f7d7a1e87fdcf27feda5775339ea743d1b4a93fe7acd5dadade799ff2dab91d0f47b6b7ff008f89abb8d2af344b3ff973f31ebaa062cd8b5b3b9b8ff96d5bd69669ff003daa8dade7da21fdec3e5a55efed048e1fdd7dcfe0ae9a649af059a7fd74ad1f2ecf4b86b1e0bcfb3c3fbdac5d47c4173ff03ab94ac05ad47c61e5ff00c7bc35c85df8e1ff00e5e3f7e953cf1ffcfc6a55cbeabac5b68ff24b0f9958295ca44d3f8e34492f3679df64ae47c41a859dbffd3dc758fe2df89967f63ff903c326cff9693d79b6abf112e750ff008f287cc93fe9d2a666b0352efc41736734ff0062fb9fedd55d575cfdcffa47dc7ae6b52ff8486f2cff007b0d737a947791d9ec9ab166a8b53fd9a3d4b67fab47aabe19bcfb3ebd75675e73ae7882e7cefdecdf3a541a57883ed1ac7da659ab1a9d0a3daeef50fdccfe6fdca8ed3c40927fa37f0573d6baa25e43bffbf50695fe8f5cf203d1b4ed61239bff006a56c69527da2f3ed3e77c95e7969a8249e42576ba57d9a3b3a2206c4fb3efc5f7ead7fc7c59ecff00beeaada7fa4568411fda2a8093518dee21ff0047ad7b5b37d2f4dfb3567695bece6ad4b4ff0048d4ab4819ccd1fb1ffa1fefab6ffb3fecf675041a3fdb2ceb460dfe74ff00dcaeaa662cab07fa44d5041fea6eada5ae87fe5b555d4b4f4bcbcad908cf834ffdcc1fec51a959fefb65687d8dfc9ff72aaeabff002c1ea67b12f7383d46cfed1e7a7f72b2ff00d8ff00575dcfd8d2e2f2b127b34fdfc337fc02b991af4391d474f7fbf17dfac7d734f7b8b3ff00a7aaf43fecff00b459d73da968f73f72a80f2fd56cdfc9ff005dff0000af35bbd63cb9bf755e95e2a8fec779bffd5d7857c46d61ff007fe555234819de31f105b5c69b5e09a95e7efaba5d57ed3243fbeac49f4ffb1de41e6cd5e950ea4cfa1afa768767ff0008dcfaacb0cd76e9ff007c27fbf527f65fda3478352b7f27fdcae87c39f163c49e03f87be22f0ae95e4be89e21ff008f9fefff00c02bce6d3fd1e1ff005d5d46468da5e279d5d269da83e9736fb2d4a68dff00e7a57153c95a3a56fb8a5cb703e96f03fed79e39f0dd9c1f6d9bfb5ad7fe7a57d41f05bf6e8f0f6a136cd7bf77bfefd7e76eab227f63daf95fbb77fbe94cd0e3fb65e7fb9594e3ca07ea1f8f2cf4ad526fedef03ea5f7fefc153f867c58fff002d61fb3bd7c55f0a3c59add9cd05b595e4db3f82bebed2a3bfb88607961ff81d734c4cf469f54fb6560ea3225c7fadfbff00f3d20ac7f31edffe9a3d55fb67fcf5acc932f518ee649bf75795e67e2e8ffe7afdff00e3aef35c93fe797f1d79978b637af3311d0d69ee79b6abb3cefdd57a37c39f143c90fd9bfb95e7fa959f9957bc3323e9fa96fae186e75b3ea6d2af3ed1a3c16dfc75f4d7c3993fe25b7567e77fc7cc3bd2be44f08ea89aa683f69af78f873ae7fa1c1f629be7fe3af668cacce7a9d0fa3f43bc7bcd1e1797fe3e93efd67ea567fbedf17f1d67781f58fdcec96ba5bbfddcd057d045dd231679feab1a49e7a4bfc75e37afc973a7d9c09fdcb9af7fd7347fdf6f8abc97c63a3fdb3cf4a8a9d093ce7c63b3ce83fb8f589049f639bec72d6c6ab27db2682da5fbf5c6fcfe4fef7fe3eada6ae2ad2b245209ecff007dbff8d2ba4f0fc7e6562ffc7c4d5de7862cff00b3e6fded795526372b26749a1c696736cae03c71ae7da3529edab47c5daa3e97ac5adcc33579cf8e2f3fe271bebcac556e448f02bcaeccbd4b50ac19ff00d755d9ff00d22ab57cbceafb4672b2bd1562abd664915152d4557103b5a2a6a2b8a52b805145152a5600ab14514f9c0741fdff00e04ff9675a1048fe77da65acda75690ab6036b4d91fcef27f8debd5f4393cbff0046f3be44af25d2a4fb1cdff4d2ba8d2b5c7ff4ab9ffc875ec61b116b9ad3abecd9d5ea366979f3d72975a7bdc4dfec56dc1a87da2182a6bab3f33fd57fc0ebd7856e73e8a8d5f688f39bafde6a5ff4eb6d4cb4bcfdf7fbf56b518dee3e4fefd624f27fa67da7fe59a7dcaeaa72b9dfd0d1d56f3ecf340956a0ff0089a5e7fd3ad73577fe91a941735d2e951f9935d79b5b016b4aff00489bed32fdcfe0af49d377d9fd96da5ae4740b3fb66a5027fcb95b5757049fea2e6b4898d4e876ba6ef8ecf645fc75bda6c76da7e9bb22fe0ae6fc33fe91feb6b6b4a93ed90eafff005dabba1b191a369b2e2f3ed359de31d3ff00b53c8497fe015a3a6c9f679ab3bc41fbc9ad7febb7faca27b0d1c3fc46912e219ece5fbf5c6eb9a83fd8e07b8adbf1dde7fc4cbff1cae43e21de3dbde6cfe0fb3579d50da07ce7f17b5cf32f3ec75c4da49731ff00cb1ab5ae7fa66a53dccb4cb592da3ae3ea6874ba748f79feabf775e93e198ef3fe7b5797e9dfbc9bf73357a1f87ffd1ffe3e26ff00b675d547a98d4e87a9da5e7fd39fdaebabd3647fbf17931d721a1eb896f5b7fdb9611ffbf5d26476d6ba9ffcf5ad482f13efcb3579ac178979f3cb57a0bcfb1d529582d73d1aee4b6b7ff4997ee579ff0089bc58979f245ff1eb58bae78c2f248767fabaf1ef1e78f2e747876450fcef49cae16b1eb53f8b3c3de1bb3ff48fddbffe44ae6ad34bf10f8f26ff0042f3b49d13fe7fa7fbf47c1df01db49e47893c6137fd71827ad1f8bffb50783fc07e7fda2f3cc7ff00963631d74476009fe13e89e4ff00a6fdfb6fbffedd627883e2c784bc07e1bfb345670c75f336a3fb4a6b1f1535ebad36df5887c356af0eff003eeebe62d7fc59aaeb137d9af6f3ccd956a1ce690958fa77e217ed496767ff001ef3799fec415e0fe26fda02ff0058ff00550f97bebcbfccfdf5773f16fc1fa5780e6f0f3e9fe27b3f12a5e5b6f9bec9ff002edfec57453a117b9a73991ff0b1dffe5afdfad0d0fc60f71795cd6a379a55e43ff1e7e4543ff08ddcda69bfdab6ff00f1eb572c343946a573e96d0f50b3d534d81e2ada82f3ecff00279df7ebe7ef0af8f3ec567f66fe0aeaf4ef147f685e7faef2d2bc49d295396a6913dd343d43ec7febabb6f0946fac433dccb3578df86350fb64dff3d12bd83c31ac7d9e1dfe4f97fdc8e88048ee74dd3ff735afa6ecb7aaba5efae974af0ffefbfd22b424ab06ff003b656de87a7fda21ba9aaeff0063f9734f5a3a77fa3e9b57131a9d0b5a559ff055db4ff5d3a51a549e655df2ebae9753219e5fd6a0bbb3ff0051fec568da7fc4d3cf4fee55a9f4ff00b47915bf2dc4ccbd4bfd321fdd5737a959bff66ffb95d74fa7fd9e1ff61eaaff0067ff00afa89c011c6ddc7fb982e62fb95897767fe995d96a367e5f9144fa1a79dbe2ac5c067373c7f67b3d958907fa9df2d7733f87ff00e265fbdac4f10687fe99f66ff96753cb603c97e23781ff00b52cfed96ffbcb57fe0af00f1c784fec7ffc6ebee0ff0046d2e1fb35781fc46f09ff00c7d5e7f1d3ea347c8be27d1ebce7c4d27d9f52af7bf18f87dee34d9ee6bc535cd0ee7fe5e2bb29ec0ccb8247f277ff00ac4aa53c74cf9f4fff008f79bfe01507db1fdab7113f97f5ab5047e5cdbeb3a0bc7ff9e35a3f63b9bc9a0f37fefdd3405afb67db26fb4ff07f05779a1e97fd9fa3c09710ff00a55cd1e18f03fd8ffd335087cb44ff0053057a4f847477d52f37ff0073fe7a56753a01e8df09fc0ef6f0dadcff00abdf5f5069b1a793fbd9ab80f87be1ff00dcc17334d0c95e933ecb8f93f82b9a626626a51a7fcbbd60ea5fe875d44f1db5bffcb6f33fb954aeacedadfe7ae799279e6a327ee7f750d70faac9ff003d6bd6aee44f277ff0579af89e44b8ff009635c15f64691383baff00a6553e951f9735417723ff00cb2ad1d2a3ae186e75743d1bc0727d9fcfb3fe07af64f857a87f63ea5059dc7dcfe0af25f0ae96f2433bc5f7ebb9b5d43fb534dffa7ab6f9d2bd1a5b9cd23ea0f03c9fbe9d2bd334d93fb434d83fbf5e1da06b89e20f07cfa95bfeeeebc9af50f03eb9f6cb3b57ff00beebdfa7b126bdd475e53e3b8ee7efc5fc15ed5a8c75e6df10f4ff00b469bfbaa7536133e7af88d6773e4c17317dfac4bab3fb44df69ff009f9af43d72cffb6347ff0072b97d3acfed167b3fb95e5d525cac8a3a0687f68ff805759ae496dfd9b07fb15341ff00127877ff0005707e2dd63f822fe3af1abd5f668f3ab4cc5f176a1ff132ac1d564fb643053f55bcfb44d59d3ffe395f2956afb491e5ca5723a8aa5a2b966410f97f4a65c5494566054a2ac515a400eba8a2ac579d2290557ab14ea818da29d4500147faca2a6a0093fd5d6bda49f63b383fbef59107f7e5a3ccfb44dbeb584b940ea34abcff004cdf2fdc4aebb4091ef2ceeae65af2ff00f62bacd3758fb1d9c29fdfaf670d53736a72b334355d3fccf9e2ae36eb4baf46b4ff008f3d9fc159f7567f68f9e2af6e94ae7bf4e57479cc1a7fda358ff62daba8d363fdcffbf56bec7e5d9cf505a7fc7e4fff007dd77d2ea7540d1b5bcfecfd63645fc7fc15d24f1f97a6daa7fc0eb94f03d9ff00686a53de5c57a341f66fed2d95d90099d0f87ecfec70fda65ff8055a824fb3e8f3f95557fb43ed952411fdb347fdd7fabf3ab4e6b199b50489fb8ff96695cd6b979ff3d7fdfadbd4a4fb3ffdf9ae1fc4f789243fbafbf57295d2039df1c4891ff673ff00d36df5e53f153c41fe87fedff0576be38bcfb6790f2ff057917c4d93ed1f65ae4add0d20791d3608ff007d57a7d3ff007d427fa3d712342eda47f67aeaf4a8dee3fe7b49583a74895d7daeb973f72186ba2998d4e8745a6c9e5ffc7c7eeeb6a0d41ffe5acd0ecfee573d6b796d71ff001f1f7ead7f67db5c7cf17dfad8c8eca0d43f83cea3ccac7b4df6ff00f1f15d1411a5c43fec52033a7b3f33fd57eedebcbfc79f69d1e6fb4cb0f995ed7069ff006cff00af5ae43c71e174bc867497f795bd21a3e48f88df1e3c4327fa369f3797b2be6fd5754bcd52f37decde6495f487c4df01a79d3bc50fdcaf14f16e87f6c87ed96ff7d3fe59d7a70d819cd6a327da3c87acf9e44f26a3fb63dbfc9fc150fc927faaad0441ff002d68fb1db79d4797f67aabf6cfb3d6b4c0b5a8ea1fb9af50f0affc4c3c07fbeaf1b9e4af6bf0cde7f63f827fdfa2a6c544e4355d0edacfe7b7ff0081d6a41a3bfee1e2aab3de7990cf5dafc3c8fed90fef6bcbabb9d91d8ebfc0f79791c3fba87cc8ebd5fc31a85b6a1340917fc7d5735e0ef0fa59cdfbaaf64f873e134b8d4bed3e4f97b2b151b8a47a4f8674f4b8b3f3ae3efd75107fae812b3e7d1ff7dbedeb6a0d3fed90ef8befd6b181cf3e813ffc7e7fbff7eb520b3fb1c3b2a7d3acfed9675bda568ffda10f93fc695d54e06465dae87fea5eb6fcbfb3cdfec56a41a1ff00df75767b3fb4435d708099cbda69ff0067d4bcefefd6df969f6c812b42eb4ffb443054ff00634fb641356ca3624c7fec7fb459dd2562cfa7ff00a9f2abbcd4a3ff009e559d3e8f55cb70391fecbfed49a8fec74b3ff4997ee2577f6ba3a5bffadacbd57434d526ff00a754acaa43603cf27b3fdcfdb25fbef5cdfd8ffd7dccbf7ebd4352d0ff00b526dfff002ea95c4eaba1dcdc7faafb95838148f3983c27fda937da6f66ad8f18fc3fb3d6341df6f3796e95d5daf84dede1fb4dc7dcadbb4b3b3bcb3fb3793594e3613958f823c63e0ffb44d7567e77cef5e2977a1de4937d8ee3efa7dfafbabe2a7c2b4bc9a7fb3feeeeabe6fd57e13dfe8f793dcdc7ef1ffe7a562f735bdcf01d47e1ff00efb7f93ff6de9e9e0fb0b887fd221ffb695f42e9ba3ffcbb4b0fdffe0ac5d4be1bbc70cef65fbcae984ac89678a69de07d1fed9fea6babb4d0edb4ff0092cacfccaf408347b6b8b381e587cbfefc95d4687e17fb67fcb1f93fbf5b465711e79a07c3bbcd626ff48fe0fbf1d7d0be11f09db59d9ffc79fc9ff4de99a5785ee7479a09a287ccfefd7a4daffa883fd4ecacaaf41327d2bfd4ecf2618eb53e48ff00e5f3cfff00a675567fb07dc96a783ec71ffc7bc35ce633209ffe9de1f9eb2eeb4bbcb8ff008f8adef9ff00eb9d60ddef8fff008e54b33394f10787ee6cff00e5b57996b9a7bffcb5bcaf50bb8edbed9fe91fbc7ae6fc4d669ff3c6b92bf43486e79aff0067a7fcf6adbd0e34a83ec7f5adbd2a3fdf57047e23aba1e99f0cacfed1a941fdcadbf11f87dfc0fe309d3fe5cae7e74a3e1ef87ee6e2f20fb3d7ad7c5ed0ff00b53c136be57ef2f6c3e7af617c072cf739af865e20493529f4dff9757877c35ea1e03d43f73f63ff009f69abe63f0fea9fd9fa941fec4dbebd9fc31a87d8f5e9de5ff8f2b9fb95b529591ac4fa4fccfb659d735e20b3fb469b53e95a827fcb29aad6a3debb94ae86cf0a9ffe25fe24993c9ff45b9ae7ad6cff00b3e69ebd1bc71a7ffa641fec579af8f354fb1ebd0793f71e1af0f1953d9a38b112b58e7b55f107da2cf64b5c06a578fe75686ab79fe995cf4d5f1788abed24793395c26a8aa5a82e2bc9a9b9991d15354353100a8aa5a2a808a994fa2803b3a28a7579b201b4ea28a800a9a8a2800a28a96800a28a96800ab5feb2f204fee555a9ed7fd1eb6a73e466903a89f5cfdf7fb095b7a1de3de43fefd79fc1fe9137fb15db69baa268fe457bf87ab73a69cacc9b52d3fccff55fc1f7eb9bb5ff0097aff6ebaeb5ff0047b3ba797efd62cfa7bdbfcffc15ecd2a87d05395d0687fe917905b45f72bbfd0e3fdf5d5cff00c02b86d2bf77e7bff1d7a068722470d7a7095cd8db836470ce9fc74ff2ff0073a759c551ff00b7ff007dd13ea1fbedff00dcad39ac067f89f58fb3cd750ff02579fcf79ff1eb73526a3aa799f6a7fe3f3ab120913c983fbf4735cce6735aac9e65e4d6d5e6de2dff004cf22bb5f33fe271a8bcb5c6ea31d6553a199c55c51068f5b73c751f97f5ac80920b34b7ad782471ff002c6b393fd1eb520d42e6dffd552ea4b3474dfb37fcf9d77fa5469e4d7156bf6cb8aeaed6f3fe98d76d3d896745fd9e91cdbe5f269f691db7fcb2aa506cbcf93c9f2e9f059ffcf586aa608d7f33ecff00f2f9526a525b6a90fd9a5fbf557fd67fad87ccd95a107883ecff0027f66d541d8678c78f3c2e925e6cf266b44ff9e95f3378c7e1ff00fc22fa94ff00f2d12bef09fed327fcc36bcd7c77f0decfc41673db7ee637aeaa7303e09d73c076dac7fa4dbfeedff8eb83d4bc0f7f67ff002c6beb0f18fc23b9d3e683cafddc7ff3d12b87d57c07ac69ff00f2dbccff0062ba6131a3e69bad2efedffd7541ff0008bdfdc7fcb1afa33fb0efef21fdec35041e13b993e4ad956e528f0483437b3ff5b0fcff00dcae860fed5bcf9259be4fee57b0ff00c2b3fdf7faeff81d74b07c37b6f27f750d6752a7b446d4cf28d0f4ff00b443fea7e7af4df857e1ff00b46a55a9ff00083a59ff00c7bd7ad7c39f03a59c303c5f7eb859dbd0d7f07784ff00e7ac35ef7e18d2ec2df4dd92d67f83bc1e9ff2d6bb8bbd3ecfc9fb3549c550e46eecdece6fdd575de1f8ee7efcb469de13bcb7ff0055fbc4aeda0f0fa7ee3fe59d74d18ddb39d869d1a79dbffbf5d47f61a7df8a9fff0008ff00ee6b46d6cdedebd7a51b12c83cbaab75a3fda3e7ae87cbfb451f63fa5741273d6bfea7655a82cd2e2b5ed6cfecf45a4740191f63a208ff00735afe5fd6a1f2ff00e795005582cfed1537d8ead411d4b401873e87f68ff72b2e7f0ff9937ee7ee57593d54f2e822671baae8ff00f2ed1567ff0065dcc75dacf66f71feaaa94f1a59ff00d347ace71b8a279cf8c7c2f73710efaf25f13787fed9ff002c6be929ff00d22b8ad57c3ff68f9eb8671b33647c89aaf85fec779fea7fd17f81eaaff65dcfdb3679df3ff07fb75ee5ae785fec737ef7f7f6af5c1ea5a7db7f694f6771fbbd9fea64a819c85de8f6771feb61f2ff00e7b51a5787fec7feaa6f93f82bd1bfe1074b8b3ff9fbfefd624fe137d3fe7b79bfd16b3981269d66f6736cff00589ff3d2ad7976167ff4cf7d68681a7bdc7fcbe7fdb0ad1bbff53b3ec7e6566265583ecd79f2568c167fb9fdcd675a69f79ff2c6b6ff00b3de3ff96df3d690319907cf1c3fea7ccac4bafb4ffcb5adb9ef1fee555bad3ee63fdf4bf7289999c86a3669ff002cab8af1059fd9ff00e5b57a06a5f63ffae95c9ea3669ff2c66ae5a9b1a40e4208ee63ae97c3f67f689ab9efb1fefabbff0008c7e5d7247e22a5b1ec3f0af43fdf415ecf77a5a59c3fea7eff00dfaf28f87323f9dfec57b0eabf69bcb3ff0047af59fc08e496ccf93bc71e1f7f07f8aa74fe07f9e1adbf097881ee21fdeffcbb7dfaebfe2a784eff005cb3dffc76d5e43e19d43fb2f529ecee3f715e773599bd09f32b1f55f84b50fdcffb0ff3c35dff0099f6886be68f879ae3dbd9ff00aeff008f09be4ff72be86d0f50fb47fadff979aeaa12bb379189e3bb3fdcfdb22ff81d7cbff10f50ff0089c415f5df8834ff00b468f756d5f1d78bacfccf3fcaff008fab39be78ebcacdfe147975f7472977feb69b4515f01537386657a28a2b23322a28a28021a2a6a2ae204345145501d854d52d27975e7d4e87411d152d4b590115152eca28022a2a5a2800a28ab14015eac514526025bd5df33fd32ab54b5a5397281b906a1fb9ff00a67e7575d77b3ec7bffbf5e6dff2c7c9aeb20bcff4cb54fe0af670b53537a52b31ff0063fdf6caed6d644f2607ff00569589771f990efa9f4aff0048f925fb895f514a7a1f410973237aee47b3ac1bad63ecf793ff00df14fd5750fdccf735c56bf2797f3c5fefd6dcd73419e63dc7f68ff7126ac7d56f3fd33f75f7128d3750ff00896ceffc6f557cbfb64dbeae267333f52b3f2eb9abb8ff00e7ad765a97faeac4bb8fed15662ce53cbff9e5fbbaabe5fda2b6eefb551ff57512045511bdbfee62ff00beea7ff4c8ff00eb9d1f6cfa51e67d6a3a833acf0c496dff002d7fd1ebaefb65b7935e73e1f97ecff27facaecbec7f6cff005b37975db4b6259b7047f68ff5579e5d6d69d1ff00d36f32b9eb5d0dff00e595e5743059bdc7fad9ab6044975fbbff005b0f97ff005c2aafdb2e63ff005b56bcbb98ff00e9a2555d4bfe98d6731957ccb9bcf92b12ebc276d27fcb1f2ffe9a568ff687d9fe7f2693fb42db54f925ff0044a232b01ca78834fb6fecdd9143e65715aaf80fecff00e932ff001d7a35de8f73a85e6cb2fde7fd34a827f0fa47e45b7fac74fbf5b465703c6ffe103b6fb640971f7dff00e59d41ff0008bd9d9cdf66af57bad3edacff00d27ff22573d6b25b6a979be287fdfada2079aff61a6b179e4dbffa2595b7fe3f5d4f873c2ff639bed3fc15d5e87a3ff6c4d3cd2feeecadab6ed347fed0ff00ae69f723ab3481cd695e0ffed4f9fc9fddbd7a4f873c1e9e4ecb7ae8b40f0bfd8ecf6576be1fd3fecfff0000a0d0b5a5687fd970ecad1d37c3ff006cab56b67f6cae934eb3b6b386b5a71b99cc343d0fecf5d47d8eda487f7b59f048f5a9f63fb457a74a36326107fa1fc953cf507d8fed1febaadd741227fb751ffacad1f2fecf506ca0086d3bd4d53f974b4019b3ff00a3d41f3f935b1f63fdcd1e5d00675a47fb9a96ae7974797401493fd1ea6a9fcba5a00af55678d2b4ab1e7b3fb450672312ee3fb47c915625d47f67f925aeb278d2deb12eff00d1ff00d6fdcacaa74081c56b9e17fb643fbaaf39f10686971fe872ff00c7d27dc92bdc7ca4ae7b5cd1ecee26fdeffdfcae4a9d0d0f119e3fb1ff00a4cbfc145de9ef790fda74a9bccdff007ec6bb5d4b43fb1f9ff6d87fd09ffe5a5715aae87ff08fde417316a5e5daff000495cd3028e9da7fdb26df0feeef53fe59d745059dcdbffc7ecde5d4175669ff001f3ff8fc14cfed07ff0096bfe914400b52469f7ede6f31eaaf97f5a820df79feb66f2e8ff8f79bf755a19cc9e7d43fb2ff00d543fee5677cfaa7cf7b356dc1fea7fd23f78f5ceeab1a7fcf6f9e9ad8c998baad9fd9ff00d55e7fdb3ae5eee34b8ff7eb53528fcb9bfe7a565cf1ff00d36ae2abd0119d069ef1d75fe18d3ffe7ad6269b66f5de7846cffe7ac3fbbac16e867a878134fb6af5af31ececeb8af01e8ffb9ae93c4ffbbb3af4e4ed4cc2aec7967c43d7358fb96f5e31a8e9f737137da6587fd2abd1be21ef8fe7ff00be2bcd7fb42f3fe7b57c462313ec6aa67146a7b391b7e12d53ecf36fafa1743d51fec707efbfdcaf9f7c3ff66bcff7ebd6bc331bdbc3f6696bddc2e2399731ecc6b73c4f68bbd43ed1a6fda6be3af887f69d2fc6175fedfdfafa4eebc4167a7d9fd9ae26ff0045bcff00c72bc2bc71e17b9d63e7b1d4a1bb7ac3349f3d35f33cfaf2bb479b5decb8f9ff008eab55cbad3ee6cffe3e21f22a9d7c2cb7394af4558a2b29015ea2a96a2a800a28a2ae2043454d50d501e81454fe5d2d7148028a7d32b2900514fa2a006514fa28023f2e9fe5fd2a6a28022a4f2fe95728a00a952d4d450341572093fd32a9d5cb493ecf5b5295a451d25a6a1e6574be5fd8eb90d2b504b3ff009635dcdac9f68b3df5f578495d33bb0b2b367357527ee77cbff3dab91f177fae9d3fb90d7513d9bdbffb95cd6a5e13d5754ff963f3dcd7a718dd9eb73e860e8766faa59dadb45fc75d7ff61ff65d9ecaef3c3ff0dd3c1fa6ff00a47fc7d5626ab67e6575a8d9185eecf3cbbed597771fda2bacd4a3ac79e3a9901c55d475973c7ff3c61aed6ea3acbbbd9594868e37cb793fe58d55fecfb9ff00ae75d0cffebab3e7ff00ae34477066df84a3b6b3ff00a695e9369a7a5c7fc7c5701e11b3b9f27f75fbb4ff009e95d5c1b2dffd6de7995df4f6259d2c1a1db7fcf1a9bcbfb3ff00adfb958b6b79ff003ef5bd049f68f92e26ad8923f9ff00e5de6f32b3fcbb992b7bfd1bee793557cbb6cecace6523067b3a83cbb9b8ff005b5b777a1db490fee66acbfecf7b7ff97cacc63e0ff53ff3c3feb8565ea5f66b3f921a7ddfd9acff00d74de5d320d534a8ef37ff00ac7ff9e95a400e6aefc277fe20bc8125fdddaff72ad4fa5a7db3fb36c7f77fdfaeb352d73cb8765bff00c7d3d62695a3a68f79f69b8fde5d3d6c8687ff0061a4767fbafdddaa56df87f4b7fb1c173fdfaabfd9ff00db1a97da65fb89ff002cebbfd36cdef3c9797f7694ca3474ab34adbf312de1aceba93fe5dade8b4b3fb44dfbdaa44b35f4db3b9bcf9e29aba582cdeaada47fc1156f5ae8ff0067f9ebd0a51b92c9ed24b9ad4b491ffe5ad16bb2b43657a5056448558aaffeaea7f32b4024a96ab41253fccfa505216a6f2feb557cca3cca0659a2ab799479940125328aaf4099628aa33de547e67d6824d4f33e94cff5954ea5df4d010cf669546eaceb527ff48aad3d574038cd4b4baa5f25c43f66b8aebeeecfed15c86b91fd9e6df5c9537039ebafb369ff00e8da87fc7ad65dd687a56a9a6ecff588f5d25d4767a859f93715c56a5a3a69ff0024b37975cb3038dfec74d1fe48bf77fec4ff007289f58fe0fb1d74577a7bf93fbaff004bacef32dbc9df2ffdfba8033a0dff00f2caaefd8fcbff00a68f5a96925b490d433ffa47faaf92832a9d0c4fb1dcdc565dd59bd74b75225bff00adfde3d735a8eb0f71ff002c6682832390d4acd23ac7f92b4aea3b9b89b7ff00ab7aab047f689bfdbac6a741a2d69b1a577fa0475c869b67735e81a046971feb6a23ba067b57862449218218abac9f4ffdcfef6b9df03c696f0d74b77227935eabe5f67a9cf5763c03e26e87f6c9abc6eeecfcb9b657bc7c42d42dbf7f5e2baaff00aedf5f98e65cbed7438fa997048f6737da62aeff004df889fea3fbe95c0515e646b4a96c076b3f8e3fb53cfb3b8ff8057157523f9d507fc7c7cf47facaa788954f88ce65d835cbfb3ff96df6b4fee4f47fc49f50ff008f8ff407ff009e954ea3ff0059517b8407ea5a1dcd9ffd34b5ff009e90553ab305e5cd9ffaafb9ff003ce89fecd71f3ffab7ace66856aaf562abd66045454b51500145145007a2797f4a9a8a582b9e45895155ba2b290117faca968a96a008a8a968d95323480515628a80995e8ab14506657a2ac514015ea7b7a5a7799f67a16e8d2068e9d67fbeaf50d0fc3f737167027f0579ce9dae7d9ffe58d7a67863c50fff002cabeb32de5e64997d4bdff081f99feb6bbcb5f0bdb79df69f27ee7dcad0d2af3ed10ffcf4ad5afd0e8518f2e876d33ce3c5d675e6daac75ed7e20b3b3f27f7b5e5fae69ff0067ae5c4c392c6c79aea5a7d647d8dfdabb2ba8eb12ef65796ca473d3c7fc158975676d5d14f1a563cfa7a567219833d9fd9ea97d8fcc9bf7b37fa2d7433d9d5682cd2cff00e9a511034208fccffa689ff3ceb53fb2ee7fe5de1f2d2aac1f69ff00965f7e9ff63b9ff96ba956d11327823fe0f3ab6ed2cdedff00dbaab6b1ff00dbdffb749e5a7fcf6f2eb68126dfdb3fe9b7fdb3a27d971f3d4369a7a5e7fec9e656bc11d6852397fecfb9ff009655567b3b9b8ff973f9ebb29f7ffcb586a95d4759cc6735fd8767ff002f16756a0d2ece3f9e2868bbd43fe78fdca9e0d2fed9fe932de79744068cebbfb368f36f97f797bfc15574ed3ff7dfbaff008fa7ad49ecf478ff00e3dffd2eeab6f4dd3ffb1e1dff00f2f4f5a141a1e869e77ef61f31ebacac4fed07fb9154d048f7935520353cbfdceffe3ad7d03474b8ff007ea97d8fecf5d5e87a7fd8e1fdd7dfada9ee4b362d6cfecffeb6b53e7b8ff5559df63ff9ed45a495ec436259b5055bff005759705e7da2a6f32b68925a9f50a87ed9f5acefb627fcb5a8e7ff00a655406afdb3eb53799f5ac8b7a9f7d052343ccfad1e67d6b9e9f5c4aab77e204b7ff5b59cc674bf6cfad4df6cfa5797ddf8f2dab23fe16654a76133d9fccfad43f6cfad795dafc44f33fe5b55eff84e2b58bb99c8f46a9ab8d83c589e4d6c5aeb89795a19b36bed9f4a87ed9f5ac4bbd6123877d6241e28f3282a0779e67d68fb67d2b9eb7d43ed9feaab461a68264d3ffa45646a567f6886b66b367a8a9b040f39bb8ee74b9bfd8acefed0d2b5cff43d42b6fc7167736ffe936f5e73a8c9677936ff00f57755e7c8d09f52d1ee7c2736cf3bccb27fb8f5069dae5b79df66ff00969ff4deb42d6f2e6387fd77989fdca9e7d0d2f3e7fb1d419cc27b37ff0096b0c3b2b227fb1dbfcff6cad0b5f0ff00d8ff00d57ef12ad4fa3d9c9feb6b4819339b9f54ff009e53560eab78f795d75de87616ff00eaa6ae535c912cff00d4d9ff00db4a262394ba8edae2b2e0d1fecffeaab6aeff00d33e7ff56f53e9b6773715cf310fd3b4f78ebd43c331db495c5695ff004f15ea1e12d3eae91133bcd0e3ff0043ae7bc63ae5b5bc3f66966aef2d36793b2b9ef13787f4ab8a9c67c08e799f32f8baf2f3ed9b26fb9fc15cf57abf8bb47b6b787fe7a25798ddc7f679abf34c67c662ca9453a8af3c455a86af557d9419cc868a28a0cc8e7a86a69ea1a006514557a0d2014514506815151450347a851451591b402a5a2886a2458514558aca4057a9ff00d653fcbfa52d40c28a28a0029945153210514515001490474f82b5e0d0def3fdcaa8c799d8649a5697f68ff555e8de19f03dcc9feb7f7694780fc3e9a7cdfea7ccaf74d2a3fdcffa9afbdca32c8cd734cb846e60f867c269a3ff00aaae92add675d57df4210a71b44ed8ab231755df5e5fae577fae5e5cd701aac8f5e5e2fa1a238ad46b026d95d2ea51d647fabff9635e3b19893ffa3d56ad69ff00eb8d433ecff96bfbba4073577ff4caa08343f326df7137fdb3ae8bfd1bfe5952500647d8d2dfe4fe0a3fb32b63cbff00beeaaff6edb79db3fd7bff00cf3ab8816a0ff4787fd8ad4d3b50b6ff00977fbf5893fd8ffe5e26ff00b6753c1b33fbaada0266f799f6cff9635a3581059dfdc7faa9bcc4adbd2a4fb3fc92cd5b22420dff00f3da8f2dff00e7b7975a9e5fda3fe78d559f4bfb3fcfe77cf43291913d9db49feaa1ff0081d55d463b3bcf93ff0040ae860d3ef2f3fd6feed2ad41a7a69fff001eff00f7f2a46729a6f84ffb1fe7ff0057fec56bfcf710d6a41a7ffcb6b89ab1355d413c9d96f40104faa2697fe8d6ff00bf7ad8f0c477371feb6b9ed2b4348e1fb4ff001ffcf4aed74afb3470d52036f4dff47ff5b5d25ac9e5fcf5c54f27d9ff007d5d469bfe990efae8a406df99f68ace9e4fb3cd52d457766f715e9c361320fb67d6ad7f687d9e1f3ab9bb5d3efedef3f7d5a1a959bdc4356490dd7882b434dd73ed10d79b6a5a3dce97f3d647fc25979670d44e5603d8352f165b59d701affc58b6b7ff0096d5e4be20f8a95e51e20f1c7f6a51095c0f6bd4be3427fcf6ac4bbf8b1f6c87fd757cf5fda0f27fcb6ab56b7956523d5e7f881ff4daaa7fc271ff004dab809b7d52823b9a067a869de3c7b79aba183e227da2bc62d37ffcb5adeb58deb4819c95cf5dff008591f68ff96d5db7867e2425bffcb6af9e7ec6ff00f2ca996bac5ce97f3cb5a193563eb49fc59fdb10feeaba1f0e59a793fbdaf04f01f8e2dae3fd6d7b5e95e20b6b886a91277f69b2dffd556dc1b2e2bce6d7c5891d765a76a94c0da9ecfed1557fb3fecf527f687da2a3fb6552d80c8d734ffb659ecaf00d7347fb3de6cff8f4baafa5aeacfed15e53e3cf0ffda3fd26b86b6e6903cff4edf67feb6b7bfb42dadffe3e269a3aa56bf69ff8f68bee53fcb7f2765c7fdfcae60993f96927cf15e5559ffd1ffd57cf524f67ff003eff00f7eeb3bccb9f3b67935a40c9934127da3fd6c358bae69fff004dbfed9d684fbffe0746a3be4ff5bff7f29cb63299c04fbe39bfd4fc95a9a5476d71ff004c1ead797e67fcb6ad7d374f4ae6e5bb3337b4af0bfda3e7ff00595e9ba569e96ffeaab9ef08e97ff4dbe4af46b4b3aea846c073b7525cc75c56b9a83d77faaf87ee6e3fe5b5701a8f81fecf0ffaeaf0730e6e86733ce75591ee3fd6d721a8f7aeb35fd2d2ceb9a9ecfed1feaabe0f11cdcda99991454dff001eff0025435ca4b1b55eac557a0ca6150d4d456903321aab567cba64f44c0ad4525c54159805145141a402a2a96aa506a8f56a968a2b23a82ac5145448029f4ca7d480ca7d145448028a28a8019453e8a002994fa4f2fed145ecd202d5a47f68af46f07686f589e18d0fed9e457bdf81fc2ffd9f0d7d465797bad539d742e11b9a3e1ff0fdb59ffcb1aeb208e8b7a92bf53a308d38f2a3ba11b0566dd475a559b7727d9eb696c68737aad9d79feb967e5d7a36a5795c1eb95e26200e0351ef589b2ba5d46b94d463af227b811cff00f5dab3e78d2e2a09e34a6797ff003cab302d5c59a7dcacb9e37ff965fbbad4f312b3aea8019771f970ff00aeaa306a096fff001ef0ff00db7aab3c69ff005d1ead41a7bdbc3fbdfb9ff3ce8019fda09255a834fbff003b7ffabab506cb7ff9635af05e259ffd34ade9f5132f695a5ff1d684f225bffc7c4358b06a17324dfba86ba1b5ff004cff008f8ae8448fb5fb1fdf96b474db3b6b8f9e5aab3c961ff3e7556792e6e3e48a981b7f6cfb3ffc7bd675d6b1ff003ef597fd8f736ff3d3ff00d27feb9d2652193fda6f3fe58d416ba5fefbf7b3568ffa7ffd734a82d247bcf922fb95232d5ad9a47ff1f157bccfb47fc7bd626a323dc4df668ab6fc3f66f401b569a7a5c4dfbdae960aab6b67ff002c6ad7fabaeea5b00f82ceb62d74fac7824ad1fed8fdcd76c2561327bbff0047ae4f52d73ecffeaaad6aba8570fa8eb9f67a729923f55d43fb52bce7c4faa269f0cf5afa978812e2bc2fe2c78c3cba517725c6ece27c71e28f3352d96f58bfd8f7971f3cb4ff000759ff006e5e6f96bbcd724b6d2eceb589ba868701fd9ef1d3ed24fdf555d4bc5095893f8812b7819a8d8f46fed048eaaff6c27a5799cfe28ffa6d557fe12cf6aa651eb3f6c4ff009635a369a87975e4b078b3f735241e38fb3d080f6ab5f1025759a1e9f61ae43b2be6f83c71fbeaedbc25e3cfb3de7faea240779ae59bf82ef37c3f72bd0bc0ff001112f2ceb9bd73678b3c37bebc5343f165cf87f5e9eceb9ba99ca373ec1d375c7b89bf755dce87a85cff00cb5af07f01f883ccaf6ed024fb651d4c9ab1dfe95795d1573ba77fa3d6dc17895d31249e0df5cef8823ae8ab3b51a99ec6903c7aea3b68ef3fd1fee7f7299fda09e75773e20d0d2f21df6f0fcf5cd4f676d79ff1fb0f96f5c32099893c96d79feb7f714c9ecff73febaaecf6696736cff975aa53de25bffcb1a7039aa74297cfe4d473c7e5ff00ec95a9f6cb9fb1d625d7d8ef3fe5b79775ff003ce899915a7fb37fc0ff008eba4d02cd24ac5d374b7b89bfe7a57a6687a7fd9e9440dbd0ecff00e79575f07fa3c35cd47fe8f5a9feb2bb16c6902d6a3a87970efae1b52f105b7fcbc7ef12baf9f47fb47fadaf39f18e8f67a5d7818e6d2d0caaf4386f13f8b2c24f922b3ae0e793fe795759a959d85c7fcbe573ba8f85ff00e795e57c0d66dcf53999833c9f68a86b5e7f0fde47ff004d2b3a7d3ee6dffe58d7388af453a9b419ccaf50d4dff5daa1a0cc2aad59b8aab3d0036abd58aaf59cc08aa5a8a8accd204b515152d06a8f4ba96a2a6dbd4c8ee813d58aaf562b1904c29f4ca2a4cc7d24f4b4500145145448028a28a94eda0056869b66f795369da3fda2bd53c07f0edee3fd6d7a782c1cb1352c86a373a1f865e134b7877cb5eb5691fd9eaae95a7a59c3e4d6ad7eb183c2470b4ec8eea71b0514515e944d86550b8abf542e2890197a96cae2b558ebb5d4b65729aaecae1c46c079cea3675cd5d5779aac75c6ea75f3b3dc0e5eefb5529e4ad7ba8fecf5cf4f1fda26a800fed048e8824fed4ff5559d3ff728fb63dbff00aaff00bee81336fec76da5ff00d7d555f93ed9be5a9fed9f6887f7b493eca0917ec6979feabee53ffb3ee6cfe7aa5fda1f67f92b520bc7ff009ede64957102d7f685cdbd5a83544b8ff55fbcacbfb1de49f3cb56ad64fb3ffaaada0074306a0fe4d68c1a87fcf2ac7ff6e5fde533febde6ad007cfa87d9e6fded55f33fe7af9d50c1a7fefbf7b35684166f67ff004d2829157cb4bca9e08ee6de1d9feaeaedac7f6c9bf7b5d6695a5a5521989a6e87f67b3ae86d63fb3d5af2d2dea1f312e2ad01a1692555d4bfd23fd554ff0063fb3d41fda3ef5db0d809e093ecf50798959777ae5b5644fac7fcb68ab413347c4125707a8c7fb9df5d0ff687f68565ddf6a093c53c7778f67f3c55f31f8f3c5973aa6a5f66afa83e26ff00a457cabe268fcbf12505c4f49f87bff12bd37ceac1f1e78c13ceff005d46a579f63d1ebe73f1c78b1fceab81bcf6476bae78c2b91baf1c7975e6b77e207ac89f53f32b749991ea13f8c3ed1507fc259fe735e67f6f93d4533ed8f4f95b03d5bfe137aabff09c1af33fb63d47e63fad5c2362e2ae7a87fc2706bacd03e247d9ebc1a07adbd2f7f9d5155d91a2a3ce7de1f0afe2426a10fd9bceacbf18e9fe5f893ed9fdfaf1ef84125cd9ea55ec3e27d412f3c8ae7a52bb64ce9fb347a4fc32d43ecf5f49f86350af9b3c0767f6886bda340ff4786bae07054dcf6ad3b54ad1fed84f4af39d2b544aeaf4db3fb656c8c8edb4ad612e2b53fd65727a5687f67f9ebacf2ff734a434624fb2b94d563fb3ff00d3dc75d26a3fe8f597f6c792b95ee33949f4ff00fbe2aadde9f6de4d743e65433ffa9fdec348caa74390f2fcbff5535674ff00e91fe8d2c35a3aae9ffbefdd4de5d3ed7fd321d92fdfff009e959ccc8d1d2a37b3877ffacae974eff4cff5b58369a1de7fcb2adab5df1c3b25ac652b203a583fd0e9ff006cae5e7d43ecf36ca93fb62e7c9d95c35311ecd3035eeaf2e6b9ed7ef13508765c573ba95e5ff9dfebab91d5750ffa6d5f3789c65f4339997e20d2d23ff555cecf1d6dcf27da2b3a78ebe6aa4f9ddccccbf9e3ff0096d53ff685e7fcf6a679751d28194fa16bfb63ed1ff1f167556eacd2e3e7b1ff00bf750d36b789895ea1abd546ac08aa2a28a8900caaf562abd613022a28a2b334812d15154b41aa3d2ea5a8aa5ad267a41562abd58ac6403e9945158c8ce63e8a28a9330a4f33e94b52d16b811559b5d3ee649aaeda69ff00c7157aa7c3cf0bfdb3e7961af430783faccf946a3723f01f83ee6e3fd6c35edda1e969670d4fa769e96f0d6ad7ea581c27d56163ba9c6c1451457ad1360a65155e6aa00df515154e7a996c055bbd925729aac695d2ddf6ae5f5293ecf5e5623a01cbea31d735771d743a94958b77257952039dbb8d23ae6f51ff0048ff00a675d5ddc75cf5dc758c80e6aeb65bff00aaacb9ef1ffe78d751fd96f7159775a7db59ff00d377a8033e0bcfb3ff00adfbf5a1fe9371fee565cf79f67fdf793557fb62e681337bcb7ad4b4ff0047ff005b5c87f68fbd4f6978f1d5c493b5ff005954bcb4b7aa569ae7fcf6ad482f3ed15b400b56ba856befb6ae793fd1ead5a6ff00f9e35a01aff27fcb2ab56b67f67ff5b35416bf66b8ae8ad2cd2a900fb593cb87fd4f995af049591ff1ef354f0494ca4684f27fcf2aab6bb3fe5ad4304956a0b37b8aa432d7db2e6487f755cd6b978fa5c3fea6ba19ef12ceb16ee4fb657492cf399f50b9b89aa0bad51edebb5fecfb6b8ac5d4bc3f6d715d14ba889b4dd43ecf0fef6a7babc4f26b3a7ff4386b8dbaf1627dcadcce6737f10ffd23cf78abe5bf88727d8f58df5f576abfbc86be79f8a9a1a5c54ca37410dce1f51f107db347fdd5786ebfa7bde4d5d2eab78fa5ff00aafb95833f8812b826a4a4ac7a14f96da9e757da7bd99acead7d6358fed09ab22bdba77e5d4557974b05329f4568738ca7d328a00b30576be15d52ce3f926ae12aedac7e7d61569fb446f46a7b367d11e1ff001459d9ff00aaaedb43bc7f105e57ce5a1de25bff00cb6af72f01effdc3d7053a7ecdb231153da33eb4f8731f9767057a3799fe87fbaaf1bf0c7882e63d36babf03ea0f24dfe91f72bd18ec79a751a55e5e5bcd5ebde18d73cbff005b5c4ffa1ffcb2abdf6c48e1ad11133dab4dd412b6e0bcaf22d0f5caf42d2af3ed94c206c6a5fe915c9cf1f99fee574ba947fb9ae52791fceff5d5954e8591ff0067a5bcdfbaa93cb493fd6fdfa3e7a3e7acd6c6157a1c36abb3fe3da59aaada59d9dc7fcbe7cf5b777a87d9e6d9a859d51fb1d879dfbaae6919c0d1d374fbcff965795d2cfac6b7a5ff00cb1fb5a7fd34acbb4d1edbefc544f1de7fcb2bcae5ab2b2342d7f6e695a87c97b67f647fefa533fe11fd2a49bfd0b5bffb673d519e34bcff0090843ff6d23ac1d47c3f73e4efb29bede9fdcfe3af9daf59c2f65703475cf09de5bc3fba9bed7ff5c2bcbf528ee6ce6fdec3347577ccfb3ffc7bcd35a4f5760f186b16ff00f1f1e4dda7fb75f375a70aaf6b01ccd435d37f6e68f79ff1fba3f97feda541fd8fa25e7fc796a5e5bffcf392b9f923d0ce672f7154ebaad47c277f6ffeabfd2d3fe985735771bdbffad87cba396c62ccfa6d58a86aa26532ad417153d435664ca93d369d3d3680457a289e4a8aa2430a28a6dbd41a40754b4558a0d51e87454353507a44b4514567300ab155ff00dba2b19016e8a653ea0ce6153411d1047f68aeafc33e1ffb64d5bd1a5ed6563336fc0fe13bcbcf9ff82be85d0347fb1c35cf783b434b386bb586bf4fcb307f568299d34772dd14515f409f33b9dcf60a65155e9b242a0b8a75453efac660413c9556792ac563cfbeb9aa7400baae6b51ef5b77525625dd7057d901ca5dc7589755b7755893c695e6bdc0ce9e4fb4552bad96ff00eb6b6ee2b12ef7d672039ed4b54bcb8fdcf935ceddfda6e2ba8badff00f2cab227d3de4a8039abab37accfb1bd75cfa3ff00cf5ac49f67fcb2fddd0065f977fe76ff00f5757ad64ffb69547ccfdf7eeaad7cff007ef66ffb674099b706ff003ab460df795cbff6e7d9ff00d57dcad1d375cb9b8f93fe3deb7a7d4ca6757f63b9adab48d3fe7b573dfda29eb5a306cb886ba62666d411a5c7fa9ad8f9edff00d5572ff6cfb3d5dfed04ff009ed5ac4b81d7daec93fe5b55afb62495c341ac25c7ff001cadad2a4792acd0dbf92deaf5aea1583e67f04b5a16b67e5c35ad33399cf789af3f7dfbaae7a7d42e74faef278ecffe78d721ae489256a66625d78c2b17fe130f326ad4bab34fb1ff00a9ae4ed343fdf56b00363cc7b8ae3752d0fecf79bebb282f12ceb13c4dae5b7935aa03cf3c47ae797f25797f8c647bcaf43d73649feb6bcf3c4122470d290753c2fc5b1fd9fcfaf2bd56f12bdafc4f67f68f3ebc53c4f67f67ae65f11d31395f92a0a28af596c8a0a653e9940051451401652f29ff006caa74fa960743e1ff00f48bc82be88f01efb786bc1fc31a7fefabde3c311bdbd9d737532a9d0f68f0fde7fcf5af60d0f67935e2be1fdff63aeffc3178f5a40c19ea1a6de7995d5e875ca787f67fcb5aebed7fe9956c8ca6765a5489ff003c6bb8d3bfe995797dac9f63aedbc3f27994cccecb51ff008f3ae36eb6575775fea7f755ca5dc75954e804f69febab4351b3b3d42cfecdf6cfb25d7f04954b4a8fec70f9d5c4f8e358b3f104dfe85fbb7fee573c9d809e7d2f55b79a7b3d4ffe00f56b4dd0ef239bc9b8ff00beeb3bc23a85e7fc79eab37c9fc13d7a3e9bbedfe4b8fde3a5609dd8181a8ecb7876573d3de5cd9ffaaaef355d1edaf2b94bbf09dcdbff00c7bde57ce631cb9b43481560f145cffcbc69b4ff00ed0493e7b2fdc3d625deb1736737fa4562dd5e25c7cf1578556bf2bb4c266d6b9a5ff6c7cf2c3f64bdfe093fbf5c3dd46f6f36cb8fddba7fcb3adaff008482e63f93fd6255a9f58b3f1243f66bdfdddd7fcb19eb8aa4e356d63338fa8a7d95a9a8e9ef6f5893d73b8d8ce6105e5cdbff00aa9bcbab5ff0945e7dcb8fde553ace9e852b18b343ccb393fd6feeea0f2eda4ff553567d36b68cae6532c4fa3ffd36ac8bbed45c5559eaccc27aab562abcd512020b8a8e8a5ff59594804a9a8a96a0d2058a28a4f2eae274c0ee6a5a8a8ab3bd96e8a86a6a8908968a83cca7561302ed3eaa4356a0a51dcce668dac75e8be11ae3b4ab34b8af60f07783d24f21ebe832da5ed2a5fb19753d0343ff00535d4d50d3acd2de1abf5fa6538f2c523be90fa653e9956cdc2abd58aaf52045514f44f25559e4ace60413555baa96a84fff004c6b9e7b014aefb5624f1bdc56c799f67ac7bbbcb9b8ff0055f72bcfa8065dd59d62dd6c8ffe5b5686a3fe91feb66f2eb9ebbd413ee5bc35e7c80cb9e4793e48aaacf225bffc7c4d56aeef1fee79d591f6cb6ffae959811cfa827fcb2b3ffb6959f3ff00d35ada9f6793ff003ceb1751ff0048a990189751a5624966f71ff4cd2ba29eceb23518d3ff00b5d401953de7d9ff00e3dffefe5647facad79e3b6ff96d59f3de5b5001049fbead08365bfee7fe5eab23fe9e7c9ffae34cf33ec7f27facbafe37a04ced74e93cbff5bfbcaeae0d43ed1feb6bcced2f2ba4d3750fb3cdff003d2b6a66333acffaebfbbaabfda17327c96f53797f68ff008f8ac5d7358fecb87f755d8f6081b56ba7a7dfff00575d2da59a7fcb29abc520f165e5bcdfe9b79ff6cebd43c25a825e43fbafbf5701ccebff00dba9ed6f3ed158ff003d68da5e5b47ff002dabaa06455d46f3ecf36cac49ff00d22b5355d9aa4dfbaae475591f4fff00555a006ab27975896979fb9fb4d55d57507ff978ac89f5078eb4801a1a9496d25729ae7ef2b47fb42dae2b2f52bc4ad00e0fc411bd79ceabfbb9bf7b5ea1ae6a09670d797789a4fb67faaace6690391f106ce2bc97c4fa7ff1d7a9ead5e7bafecae794f95a3789e4b7767e5d67d75da8d9f995cd5d57a34a7ce86cad4514574087d14cab3691f994010c31f986ba1d2b4f4358fe5d751a1c75cd59e83474be1f8ff7d5ed7a06cfb1d78be95fbb9abd0fc3f78f5c51958523dbbc3179e5c3fbdaeff43bc4b7af11d37587af46d0ef3ccae884ae73d4e87aa69dae3dc4db2bbfd0f58fdcd78de951dcdbcd5e93a55e7975bc0e699e87a55e7da3fe58d7a16811a475e7ba1ffa643fb9aee743bcfb3ffadad9199d2ddffd31aa9ff5d699f6cfb67c94799f63ff008f8a89ec067789b5cb68ecf67fabaf39ff00845dfcefb4ff00acad1f13d9ff006a4dbeca6f93fe79d3fc311dce9f37fec95c32dd1a40dbf0c69eff0072e21aef2d7fd4fd9bc9ff0072a7d2b507bc87f7b67e5d3eeecee6e3fd55397c25983a96ff00fae7fdf8eb1278eb7aee3fb47c9ff2f5fc0f5c06b91dcd9ffaaaf9ac64f90668ddde7fcff59fdad2b8dd47434fbfa67ef13fe7854dfdb173ff002d6b2eee47b79bed36f5f2b527cec0c9a656cf996dae7fadff0044bdfeff00f7eb167ff47f925fbf5cf233987db1fc9ff6ea8cfb2f3fdfa3fe3dff00d5555bbd979f3ffabbafe3a2273cccf9ff00d1eaad5abb93ed1546e28918b1d55e8aabe67d6ae9994c7dc5559ea6aaf5a9995ea2a96a2a008bcbfad254d45069025a28ab151235414fa28a226d03b1f328f32a9d4b448ee2dd4de67d6ab5159484cb1451454089aae5bd53ae9bc3f67571873bb01b7e0ed0dee26afa27c2367f67b3ae1bc0fe177fbf2d7ad69d1fd9ebf41cab0fece0d8d6e5d4a968a2be953bab1d510a28aaf4d9413c9557ccfad2556f32a5804f25674f79524f2555ae3981567bcaceba93ecf56ae2b12ef7d79f506827d62b16eef1ee3fe5b55d9f4f792a0ff845d2dffe5b57172dc19c8ddc6f2563ea5677f6f0feeabd027b3ff96359775fe8f5128d84707fd9f7927cf71fbba8e7bc7b7ae9752ffa6d589a949fb9ac6407293c89e77ef66ad1b4d9710feeab2eeecd3fe5ad1a6c8ff73f828880fba8de4ac4bab3fb3d75907ef219eb3aeecd2a2a740386ba8dee3fdcac8fecff00326aecb51d9591f25bc3f6cffbe2b2032eeff77ffb25677c96f53cf2799fefbd3f4dd0d2dffd26e2aa22668e9dfbcff55fbb4ad483504b3ff55583a8ea9fc11555b4df5b4493d6b4ad413fe5ad55d563f32b93d2af1e39b7dc4dff006cebb6ba93ed9675b400f1ed7343fb3f9f732d1e07f8b16da3de7d9a2ad4f13e86faa7c95c541e0fb3d2e6fb4dc56f1067d4fa56b9ff0009043be2ab33e969ff002da6af2ff879f112dbfe3cff00d5d7a07996d71f3f9d5d673f5342d7f77fee7f0567ead53796ff00f00ac5ba92b4801cd6ab79ff003cab9ed464fb3d9d6d5d6fac1d4765bd6f003220ae6b55d41e3bcaeb3cc4b7ff005b5c9ea579f689b7d6869039bd724f32b83d4a4aeafc4da87f057077527fcf2ac2af4364721e2dd53fe58d79ccff00e91357a3789ecd2b90ba8fecf0d73948e52ea3fe0ae6eef4f7f3aba89e4fdf547e5fd6b4854f6651c54f1fd9e89e3ae9751d0f15520d2dfc9d95df1aca689662dac7f689ab520d3ff7d53e9d1a47f25749a759f9959d59f6118b6ba77fcf5adad2bfd1eb46ee3fb3c3557ec6ff00f7dd724e571a352d644f3abafd0ef3cbae27fb39fd2ba8d2a37b7ff5b59833d434dd9f7ebd0343912486bcbf4dff0048aebb4391eba297531a9d0f57d3b50f2ffd6d773e1fbcaf3cb5ff004c8764b5dcf8663fb1ff00d34ae95b9cd3d8f5af0c69ff00beaf5dd3b6793fbeaf22f0c5e797feb6bb29f5078ffdcaea89cc8eafcb4ff977a7dd7fc79fef6b3fc33b350877c3f7ea3d5758fb64df63ff00577495153a1471b6ba7ffc4cabdafc31a7d9f93bfc9ae1b4eb3b6bcffafaaf4ad2b4ff00b3d281ad336fcb4acebb8fecf5b56f47975538dd1a9c3dd59a5c7fbf5ca6ab1ffcf6fbf5e9fa969e95c56a31fd9ffe58d7818fa3cf1133c6f5cd9ff2cab9edf5e8de31d0ff00e5e6de1af39bbafcfabd3f673119d3d1f6cfed08765c7dff00f962f4cb8ace9eb9d19cc827dfff002d7efd67cf25685dc9f6c877ff001a562cf25339e615577d1557ccfad5c4c584f50d1552b58994cb155e8df5156d13325a8a8a2b589a4028a2a5a246a82ac557ab15948da03ea5a8aa5a227440dba96a2a2a0d8b5562abd58a890053e99566d3fd1eb296e84cd4d36cff008e5af46f08c69e75707a77fa45757a6ea9f67ff555e9615da423df3c3fb39aebadebc97c0ff6cb8af57d3abf47c1bbc0da9f534a8a2abd779b8541714ea8bccfad26041e67d2aad58aa33de7d9eb19811f989f72a19f6547f6caa53ea15cd53a013ecaa950cfae3ffcb2acb9f50493fdfae4a92b580d4bbbcfb3d60dd49ff3d689e4ac8ff595cb277026bb92b22ea4b9b8a9eeff00f1facb9e47acd805d495893eff00f9e3f253eeef3fe7e299f6cb993fe5b7c958cc0c4bbb3ff9eb5916bbfceff62ba1d46b16eef13ee56603e7d62dadfcfac1fed04bc9ab3b5593fe78d16b789ff2ca1f9ea6404f77a7f99591a959bde4dff3cd12ba49ff00e9b565ddc97327c958c80c582cff00ef8a827ff48f922fb95775291edfe48ab167d52e68880ff29ea7f2d2dfe79a6aab059f97f3dc4de6551ff8f89aac4cbde63ffcb2aee340bc7f27fd22b91f32cf47877cbf7ffe79d55fed4b9ff8f9ff005157124efeee3ae2b51d3fcc9ab6b4dd71ef21ad1fb1d6d0291c1c1a7ff67cde77fac7af50f0978b2cfc9d9fc75c1eb9fe8f0ffa17dfae5ed23b9d3ef3ed32cd5b2958523e85bbd41ef2a95dff00a3c3b3ceac4d0fc409fd9bb3f8ea7ff6eb6a72b9cdd4b5f634f26b90d73ecd1d6ddd6a096ffeb6bce75f91ef26ff0062b6032f55d43ed15cf4f23d685dde571ba96b0f47358da9997aae9fe64d556eacfecf0d433f883ccaab3ea1fb9a39cd4e4f518fed15c8eb9227dcaeaeea4ff5f5c6dd496dff002d6a5cae06741a7d4ffd9ded507fc2516d1d65eabe2cff009e5428ca5b01b7a9476dff002d6b94d575cb6b7ff55597aa7882e6f21ac3aedc3d1e5bb9016e0d43cb9b7d76b69e28b68ecebceaacec7aebab4e2d2b01ebd6bac59ea10d6a7fa37935e3da71b9b335d2da49735e5d4872303bcb48edab53fd8af3cff4baebf408ef2f2b9a72b099d769bf69b7aef2d3fd221ae534adff00f2d61aef34ad3ea6333199d2f84754793e496bd1b4092e6dff00dcae37c3fe1ffdf57a6687a3bfdcaf4694ae734fa1d7e9b23f93bebd1bc1d27da3fd1af7ee5721e12b3fb3fc971f72bb6fed0b6f0fff00d34ad652b191d26aba7ff61c3fe855574a8ffe120f9e5fbf58bfda17379fee7f05751e1fd3fccf9ff8e9c25766b03b5d0f4fb68ffdfaea2deb234db34ad7f2ff008ebb6274c0bb6f4ea2a5aa3433aeab8af1048fff002cabb5baae4755d1edae2bccc745b8ab18d43ccaeaf2e6ce6fdeff001d715ae6cbc9bfe79bd7a1788347b6fbfe7579cf883f79feaabf3cc545a96a7175394bb8deceb3a792b47fb43f82e3ee565ea31ff1c5f72bc69ee329799f67aa375fe8f4f9eb3e793f735999cc6799f5aab3c944f25415a40c58541e6547456f11127faca8ea1f33e94b5d14c0968a8aa5ae888d1354b5154b566d00a9edea0ab15123a203ea5a8a8a226a8daa9aa4f2e96b9c64552d15154480b50d5ab492a854b0d475133acd364f326af49f0768e97135796687a7bdc4d5efdf0f3c3eff007ebe8f0147dabbf624f46f0fe97f67aeb6a85ac7fb9abf5f7b4a1c9148ea86c155e6a2a2ad8d086a2a4f33e95567bcace6ec8086ea4acb9f7d17525677db3eb5e6d59807d8ff008eb3a793ecf53dc6a1ff003dab3bed9f68ae39caf60193c8ff00f2ca8f92e2a0df53c1ff004dab1024f2bfe9b547f634b7ff005b47db2dae2a97db3f821a008f558d2b1678fed15a1e5ffcf5aa577225bffaafbf4981cbddd9bc7ff1f159d3ff00a3d6ddd7fa47fc7c5626abf66ff9635cf53a019177be4ff96d583a8de3c74fd4a4fb3fcf583a95e5e5c7fb9580d19f3de279d56b4ed43f822fddecfbf25675dfd9ade6ff0047fbf557ed9e5c3b3fd5d00ce86d2f124f3de59aa4b4d613c9fdd7fdfcae43fb43ed167f668bf7744fac5b5c7fa1c5ff007f2811d0cfb3fe5acd59ff00634fbf4fd363fb454f3ffa67c9fc1512039dbbfb4ea9351e65b697f245f7eb62ef669767fedbd71977bfefd481a3ff001f137da65a64fa87db26ff0062b22eef3f82a6b4b3fb4502674ba6ea1ff7c5749a56b9f6cf93fd5a5701e67da3fdca9f4dd63f7dfee56b095893d27cb4ae7b52d3d2aada78a1e4f93f82b6f4d912f21df5af381c86a57973a5ff00aaadbf0e78b3f825aabafe9ff68ae5f52d3de39bf7552e7a81e99e625c562eb959da55e3e9ff00256dff00c7e57446573399e7faae9f735c06aba7dcd7b75d59fd9eb9ad474fff00a63533958207877fc23ef4cd73fd1e1d9157ad5d787eb2ff00e10f4ff96b59f39aa3e73d73ed92435c4cfa3de495f467883c0ff6cff55591ff00083fd9e1d9551adc8ca3e6dbad0ee7ceabb0784ee7c9df5ef707c334b8f9ead5df85edadff00d1abd0facbb2b05ec7ce10785de4ad483c27fb9af67ff845d239ab427f09a51f5998739f3f41e0f7ae92d7c1ff00b9af57d2bc2f56bfe117fb3cdfbda8955954dc39ae79b5af81fccaeaed3e1ffd8ebaf82cd2cffd5575d69669aa435998d495ac70f6bf0efed15d5e95e0f4b3876575fa6e9ffc15d0da697e656918dccb9ce5ed3c2ffb9ff535d5e87e0fae974ad0ebd43c25a1db79dfbdae8a70329bb983e19f03f97feb6ba59e3ff847e683cdff008f57fe3ad4d73544d1e6fb345ff00ae1b52f103de7c9ff0090eaa4b945137bfe120fdcec8a8b4d41ee3e497ee560dad757a7469506a8ebfc3ffbbaf46d0e4af39d2a4aef34a8eb7a433b9b5adbac0d3b65bd6a41257a50d8d206854d54e092ae5686836e2b9af106ff0026ba1acfbbacea2bc598d43c13c5d25cf9dfbdae02eaf12bdd3c5ba7a495e2be27d3eda3ff00555f9c63d5a6ce2ea70d7525677db3ecf56aee3fb3d62cd5f30f7644c35192b2e7929f3c959d3c9512330f32993c9504f2533cca8258b49e6553a4f32ba68f5113558aafbea7b7aed88d0f82a6a8aa5aee81b409a8a868a723a204d52d45572deb291aa24a28a288833aaa28a37d738c2a2a28a890055cb4ef54eb5b43d3fed9353846ec4ced7c07a3fdb2f2bea0f0cd9fd8e1af1ff01e9ff63f22bdc746ff00535fa26531b5360b7352ac557a2bde3aa245514f52d43499457ace9eb42e2a9d672d84cca9ab2eea3adabb93ecf5913de5705404625defff009e3e654f047ff4c68bbd43f73fbaaabe65722dc63fe4a3fd5d3693fb3ffe7acd431320f96b3be4ad1f312b16eeb09f404413ea16de77ef6b12ef50b68eaf5dc89f73ff0022571b772573cca44daade7f1f9d5ca5d5e7efaa0d4644f3b6565cfa7dcffcb5fb95848a27fed07f3ab3aeaf12cecff7bf7eadf99e5c3bff00ef8acafb1bde4dfbd87ccfefd112599df63b9b7f9fcef337d737a8d9eabe77fa9f32bbff00ec74b7ff007e882cdedea847290686fe4fef68f2fecf36c8abaf9b65735e5bc937eea8035e09134bb3fdd5177a87d9e182a1d563ff004383fd8acbd46f2a64017727da21df2d63ddc7e5c3b2b63fe58ffe8159d059bde5e6c97f82a00c4d3b4ffb44d56a7bcfe0a9e7bcff005fe55627d8ff00e7e3f8ea2401f6cfb67c917dc4fe3abdf269f67fefd51fb625bfc91543a8de3d4816bed9ff007c56be9de207ff0080572fe67ee76555f33ccf3ea24077ff00f09027ee2a7f312e21fb4d701e63fad13eb9f6787645429582d73b5fb67fa6574ba55e2578dff6e5cfdcadab4f147d8eb45333940f5d9f4f492b16eb4fae6b4df1e7da26fdefdcaeca0f105b5e7c95ac257326ac62cfa7d63cf675de4f67ff003cab167d3eaa42389fecf8eaacfa7d75f77a7d676a3a7fda21a512e0715771fd9ffd4d60cfa7fda26df5d7cfa1bc7355afec7f2e1ade06879fda697ff3daad7f678ae96eacfecf4cfb1fda2b4039a82cfecf5a9a8e9ff688607ab53e8f5b7069ff0068b3aa4673393ff844fccfdf56de95a1bdbcd5d5e9b675d2da785fed15ac0c998b69a1fda2ba7d0f437b89ab520d2d34bff5b56b55d53fb1eb5423a283474b3d377ff72b93d4bc51796f7905cdbd65dd78c2e6de6dff00c0f54bccfb67cf431a36eef5c7d63fdfabb06cbcf92e3fe3ebfbf5cba7fa456bff00c7c43594cb46f41fbbf92b62d64fb3d7356b78927c92d6f5a6f8eb33489d9695ac57a4f87ef3f735e4ba757a068127975d787dc999eada7495a90d729a55e574bf6caf6e3b0409aac551f32a6aa34279e4ae5f5cf10259d6dcf25795f8e23b9bcff555e6e367284558c6a06abe2c4b8af33f106a0944fa3fd9ff00d6d60ea5796d6fff004d2be0b1537396a7175312effd22b94d47bd6a6a527da2b9a9ebc3ac32acf25559ead4d59d3d79f2dcce6559aa2a966aab4477316150d153576c044b52c35154b5e9521a2dd4b50c14b5d313aa97525a28a2891d0496f53d54ab70d6521a2c5155f7d1594819dad45451506b30a28abf691d5c4cc641675dcf847437b8aced36cd2bd43c0f67fbeaefc2d2f693133d0fc1da1ffa87af42823acbd0ecfcb86b6ebf46a14fd9c11ad3ea15151514f5d06e413d368aafbe931322a8e7a49e4ac8babc7ae7a8ec493cf79589752254177a879758bfdb16de7579b526522ecfb2e2a0fb17fd3e5559f58b3acfbbff004cff00553563ce33a1f32da3ff0096d5913ea0927fcb6acbff00963fba9ab3bcbb6b79bfd4d44a5703527bc4acbbbfde7fada27ffa65f72aaf99f5ac98156ef7d72f3e9efaa7faaaef3fe58ffa9aabfda0f1fc96f67e65481ca7f61db59d559f4ffb64dfec56f4f66f7137ef68f92dff00d5500735fd87e64dbea7ff008f7f92b6ab3e7ffa6b401cecf27efbf7545df6abd3fd9adeb1351b8ff9ed59cc0c8bb91ee3fdca9ad74ffe3acf9ef2b460bc4a202613ff00a9ff006eb93fb1f9935779aac7fc75cf79696ff3d1304677fc7c7fc028bbff0047a35293ec737fb0f59de27d43ecf0ef8bee563219837779e64df66ff56895913c9f6caa5a1ea1fda179b26adaba8ffe795632021b4b3fb3fcf2d413c9f67abb3ffa3de7ef6a1fb1feff00fd8a9032fcbfb3fcf2d5d8ecfecf0ef97fefdd1f25c7fa4d55bbdf710d4480ab7779f68aa9527fd76a6695ff002dde5a81a1ff00f2e955aadd4b047f688693288ab5b43d63ecf37ef6b127a2d3fd755c0967a841e3cfdf7d9ab47fe12cb692f2bca2ebfd74fe556741ac3d686528dcf72fed08e9fe5a5c57964fae3ff694095b5ff0987d8ef3ecd551326ac75f3e9f5567b3ae7a0f187da2b42d7c409243beac44f3e8fe65410687e5d5ad37c416d79ff03fb94cff008482dbceff0072ae201fd975a369a1d41fda09790feeaaaffc250f710f93fc75d1003a5b48edacead5df882da3876455c34979f68ff96d507facae9808eaf52f103eb167b3f8ea4d0f7f892cfec771ff001f49fea6b9a83fd1e6df5a3f3dbde4179156a8a45afecf7f267b6961a23ff47ae87cbfed4f9ff8eb23cbfe0a1971346092b43fb3fed1597e5d5eb591e9164ffd8ef5a16b1fd9e9ff00eb2a7837d00743a548f5d7e9527975c869bff4caba8d3b50f2eb5a7b99ccf46d2af3ecff00eb6bac82f3ed15c4e95796d257456f5e9533336bccfdf55aae77e7ad2b592b4901a13c75c6f8823fdcd765feb2b8df13eff27fe7a57062be132a9d0f25f1069ef5c36aba1fd9fe7af40d72f1ff007f5c1eb978f715f0589f88c8e52ef6475cd6a35afa9d73d75fe8f5e2d6020b8aab3d5ab8aa5715e7cb70209ab3eadd45447733990d4d50d4d5e8523325a96a2a2bd281a40b96f52554ab75b44e88054b4545448d512d49e6556a2b9ea74193799f5a3ccfad56a2b9e4267a1d14515d07492d68da7fa3d55b7abb6b67549db43399d668166f79357d05e07f0bfd8e1df5e51f0e6cd3ceafa174a8ff00735f619652bc5b33ea6a411d3a8a2bea56d63a10553f33e944f2565cf7949816aa2aa9f6cfad1f6cfad48093d67cf1bdc55df33ed154a792b2a9d00a53e9e927fadac7bad3edbfe5956df98959d715c7576291833e9e91d644f1dcff00cb2adbbbed59775675e7cf7199d05e5cff00cf9d5afecffb3ffa4cb4cf9edfe4b7aab3c9aaff00cb6acc0827bcff004cd914d557fb1dff00e5e26f329f0496dfbff361a27bcb3ffae74005ac696ff3dc7dcaad3ea1ff003ef379695a13e9e9e4efacb9e3fb3fcffc149819da96a959f06fa9aeacfed159f77b2dfc84fe3a901f77aa7fcf6aabf6cfb45559f50b68e6aabe5a49ff002daa26055babcfb3fcf2cd5cf5dc97971f3c5f72ba1fb1db5c7c9feb2b3a7d1d3fe597dcacc0e6de3b9b7ff7deb534d8ff00d0f7d5abbfddff00bf56bfe3cf47ab8005dde7db2a8ddc9f68f22a1babc4b387f75fc7556793ccb3a2626175b2e26dff00dcaf39d73c50f710dd27f056d6b9aa7d9ecf67f7eb809ffe4253ff0072b190e268f83acfcbf9ebae9e3acbd0ecfcbb3ff62b6e792a06ce7bcbfb64d56aeaa69e34b7acf9ff00d22a24232eebfb94fbbff47f93f829975ff1f956aee3a90313cbfb47c956bfd8ab507fd32a5a008fcba7f97fb9a3fd8a9a80322efb555d0e4ff4ca35593cca6787ff00d1e6a068b53fef3cface823fdf4156aee3fb47cf4cb48fed13414145d823fb46bdfee555bbff004cf3dead7fc79cd3bff7e99ff1ef350041692568c127efbf7547d8d3efd1b2aa24b346093cbf9e2fdc3d6a6ab67e64df6cb7fb9735855b3a55e7fcb9cbfc7fea7fdfaeaa7d486169fbb9a07fee55ad47fd0f52d9fc0ff72a19f6568796979a6c0fff003edf256c22afd8d3efd5a86882cead7d8fecf5480b569b2b62d364958f0efabd6b256b03399d26951fee767f1a568cf67fc75916b27d9e6ae92d2f3ecf36c97ee56a8cca5069ff0068a7fd8de3adb83fd77eeab47656d0032ed37d6dda7fd36a82ad4166f27fa99ab4035ed34f4ff9635b769a5f9959da6d9d74b6bfe8f4d46e055823b9d2eb7b4dd72b420b34bc86993e87e5d6d056036ed6f3ed15a90d73ba759d7456b1d6a8027bc7fb95cd7882f2b6f528debccbc5bfdab1ff00aaaf3f1b2b40caa74397f1348fff003e75e73aac9ff3d6baf9f50bff00f96d5cd6bf27990d7c5d677464707a8de565cd56b528ff007d542be7aa6e690229eb3a6ad19eab56120994aa2ab5515246454a482ac4f50576d333996e8a8aa5af4223812d5baa9456d13a205ba49e9699566827994b4552ae1a804b515151579957703d6b6558a28af44ea2cda475bda1f87def3fd55626995ec3f0f3c3ef795dd84a3eda763399d97c39f07fd8ebd6ad63fb3d67687a7fd8e1ad6afd270f47d8c120805453c944f55aba4d0af3543e5d5eaa33c94980c9eab536e2a94f25b563300f33fe79555f2dee28fb67fcf2fb94cf33fe9bd6601feaea8cf23d5e9ffd22b3a78eb2a9d0083fd6555ba8ea1bad52e6deaacfaa3fdfb8ae4a9d0a44775fbbff005b54a7bcfb47faaacb9fc4097937efab3bfb42c2de6fdd4dff006ceb9a60cd89e3ff00b66f5041f66b7ff55ff7dd55fedc4bcff55556ea47ac98233b52d52e639aaacfac5433c8f790d54fb659ff00cb2fde548c93fe3f26fdd565ea3fe8f5b7fda9f68ff55fbbff00a69597e5a5c4dbff00d65006769da7fdb3ff00b6568f976d6ff2450ffdb4a7cf2565cf23ff00d74a00827d9f72b3e7d3eaecdb2a0ba93fb2fe797eff00f025004fe5fd9e182a96a5fe8f351e63de7fecf51fcffdb13f9b4018bae7eeeaacff00bcf92ad6abfebb7d55f33ecf0efa996c071bafff00a3cd5833d9fefbfdfaea278fed1fe932d51d577f9307955cbd40d4f2fecfa6d1f63fb3d68dac7ff7c555baff008fcad9ec0674f27fdf758ba6c9f68d4ab4355bcfb1d9feeab3fccfb1ff00bf59c8027fddeb13bcb506a579fea1e993c9fbefb4d51fb67ee7ecd50068c1fe8f0d107eefe7aab049fc156bcc4b78767faca00b577fbbff007ea94ffe8f0d5abb92b1e0ff005d5121a296a5fea68d0ff77353f55fee7f05169fe8752516bcbfdcd320ff005db2b520ff00488689e3fdf415512585dc89e74092d33cbfb3ff00aafb94fd4bfd226df59da6ea9fbed95b44468f97f68a3cbfa54ffedc547fc7c7cf5401ff005d6a4a9be4b887fdba820fb4dbfc92d6901335e7fde7fa4dbffdb6ad1d2bfd23cfb6ff009f9aced3a47b39b7ff0007f1c756a7b3fecfbcfdd7dcfe07ad9121f63fb3d68e9d79ff003d6a0bbff47bcdff00c0f53fc9e75302d417895a9feb2b2fec7f68a9e0df6f5a400da824adbb4ef5cbff00a4d4f6b25e475aa3399dadac95b76927da2bcfed35cb9ae974dd73ed15b40ccebe08eb4208d2b16d750ad78365c56f01337ad23ff9e55b76b5c9dac75d15affa456c8474969fbbff00555b706cb8ff005b5c841f69b7ad4b5d41eb7801d2ff00aba9f7d675ac9f68ad7823add018bae48f675e5fae6b17327fcb1af50f13ea1f6386bcbf52d52c354ff8f8af9bc76e072777e28b6fb95cdddde59c9ff2dab6b5cd0edbfe59579eeaba3d7c856dc465eab669ff002c6b9db8ad19e37b7aa15e3d7e8321aad715668ae533999f4558a2b7a673cca55155a9aaaffabaeea5d4cc2a5a8a8aeb89a40968a8aa5ab3a204b454551799f5ac6a1aa24f32a3a868af32a0c9aa1f33e94799f4aab5e7cf703dd6a682cfed14f823ad8d2acd3ceaf7610e7763499d2781fc2fe64dfbdafa1bc33e1f4b386bcffc07a7d7b0e9b1fd9ebeef2cc37247982059a9a8a86be9252e6491a10cf4daaf51540093c95973efab33d2f97f4a4c0cefb1bffcb5aab3d9a56bcf1d67cff66b7ff5b5854e80677fa3d1f2d13c89ff002ef0d675dffd76ae69806a52247feb6b2eea44ff009ed50ddd9fda2b3a7d0d2b9aa74019f6cfb45674fbee26d92d743069fe5ffb947f683ffc02b1291c6cfe1fbcbcaab3f85dedff00e5b576d77227fcb2ac49f7c9ff002c6b398ce5278efece1d9e4d627f6a5e4737d9ae21f92bbff2fed1ff001f137fb958b771bdbffb9593039093fb9feb37d1f259d9ec8ab46ef4b7b7ff0049fe0ffa615893fdb249b67935205a9e4fb47fa35bd4f07fc4ae1aa377fbb9b679df3ffcf4a65dc9f63f925a0027bcbc93e78689f50fb1c3fbdfbf5553fd4cf73351059fda3e79bf78940104178ff7fc9a9ffb3fed937db2e3fe015a93ecfdc79bff007eea0bbd512387f750d5213294ff00e8f0d413d9a7fc7ccb3516979f68fdf4b45dff00a459d324e7b5cfde7fb954bcbff43ff6eaeeb9fe997902455575193cb8765265232e7ff4786b23cbfb455df13ea1f678764554ad7fe41b0d483342d3fe3cf6513d2daffd32a8f5193ecf673a5049cf411fdb2f365675d7faeff72b6ed3fd1e1ae4f52ff48bcac6a74290cf33ed1a97fb145decfbf441b2de1aab3ffa64dbeb964522d7f687fcf2aabf6cff004ca7cf1fd9e1ac5ff96d51d4a3b59ffd2299e5fd9ea0b4ff008f382b46effd22ba63b12cc7ba8ffd45327fddde7fcf4adbf2ff00e795676a51fd9e6a24082093ecff00ee55ab5912e26acb9e3ff9e54798f6f0dd511066a7db13cefdec3589751a4936fb79bcba67db3f73fbaa27ff0048aa116b4dbcbc8e6fded6a4f1bdc43bedeb120a9e0df6ff00eaa6a00b5048f6f356a7f685b6a1fefd60c179fbead19e3fdf7da6de802efcf6ff00eb6ba1d3750fb443f6397fe015cdfcff007e1fde569c15b404ce8a08ff00b634d9edbf8eda882cdffe5ad41a6ffaefb4ff00df75a33c7f639b67f07f0574c092782cdffe5955a8237aab05e3d6a41795aa2261fe91562ac55a8234b8aa322af97f5ab569a7ff00cf1a9e0d3e9f047f67a00b769f69b3ae86d350f2eb3ad3fd22b4608fed15a400e92d750ae974ed42b868ff00d1eb534dbcf2eb783b3133d1ad24fb3c35a31ffa45735a56a15d2da7fa4577425711a3047f67adab4ef59f6967ff003d6b53cbfb3d6c80c4f10496d71f24b5e4be23f07d9ea9ff001ef3576be3cd61f4faf15d57c4173f7ede6af97cc2494b533985d69779a3d735aaea173ff2d6ad4fe3cd563ffa695cf5d78b3ed9feb61f9ebe52b4e3d020519b6562dd475bd7722495973d797525cc6863dc54756e6aab3d626732affb14b4515b5339e657a8aae5c553af4601022a86927a5ae8816145368a8a9d0d6058a868a6d7155d8d50ea6d57a2bcaabb8309aa1a9a8ac946e08fa32deba8f0ce8ef71f3d72f6b257a8781f4f7b8afa8c2c6f33599ea1e11d3fcb86bb882b17438fecf0d743057e8d423cb041025aa73d2d549aba0d02792aacf2513c959f352667326de9527db3eb557febb51bea4cc827df71feaaaaff0063bfdf96b477d559ff00d22b399a40ce9e34ff009ed557ec7f4ad1f2e8bbed5933439df2deac53aefed3ff002cab2eef7dbffadac66017779fc1591752799ff2c6a79f504b3acb9f5c7b8ff555c55ba148a33e9fe67fd33a67fa659ffcb6a827f145b7fcb59bf79597ff0009027fcb2fdfd72b19a33ea16de4fef6a97f6827fcb2fbf55752d43fe7ac3589fda9fbefdd43f64baa9035eea47ac5bbd413ee4b37cf45dde5cffcb686b1351bc4b8f9ff008ea2604175ac7fc4cab46d3ecd7136c96b12eecef2e21fdd7fc7d3d2e95677f6fe7fda26f31de9400d0bbff4cbcff9e7fdca7cff00dc8bf83efd33e4b7bcfded52bbdf71feaa6f92b640687fa37fd74baacfd4a47f3bff0040a9bcbf2fc8497f8eb2eeff00e78dbffdf74313248247ff0081d4d7779f67f213f8e8d3634ff8f9fee51f3dc59cf734224ab2ff00ae9eb227ff0048877d6dff00cb9ffbf59777fdca181ceea367e679144f67fea12b46d3fb950797e5de4f5203208ffe5e61acef13ff00a3fd96b6ff00e5b7fbf589aaff00a9ff007282918b77febab1278ff735d2cf1fee77d65cf66fe4d673198fe5d4f6b6753f97fbed94f823ff004cacc0cef2eb1678eba89ffd22b1e78fec75321a347c3fff001e73db7f1d6a5aff00a459d60daffcb07aded364ff005e95a53ea0cbb6bb3c9d950eab67fe87534ffbbf9eadcfff001e7fec56c239afb1fd9ecea0823ff439ead5d47f68a3cbff0043d959cc0c1d4acfcb869f6bff001e7fbdadbfecff00b659ecacbd37fd1eb3009ecfed1feaaaada5e3dbfc95bde5fd2b3a78fecf40d13c1b2e28f96ad69d1fd9ead7d8ff008e80652f2eb534d91eaafd8ff7345a4954847496bfe8f5d1799fda9a6ffb76dffa0573b695a96b27d9ef37ff00df75d2261e5d5eb491ecff00e9a53fec7fbefdd54fe5fd6b4812685a6a89715a904958b0475a11ff00a3d6a8ce66f7989715a36b1fda2b060bcfb3d6a41fe91f3c5f7ea8ccd4fecf7b7ff5553c1ff4d6a0d3af2f2deaefdb3ed1ff005d2b4801a90d5a8234acbb4d41edff00d6d6dc1225c574404c7c11bdbffaaaecb4abc7ae6fcb7ff9655d2695fbc9ab55b88ee74eff0048adbf2eb074aff47aeaedebba1b31a38df136976d710ffa4578278b7c27a3feff00ecf37cf5f49789e3fdcd7cb3f10a3ff4cff535f2d9bfc28cea743cff0055d2ee6cff00d54d58bff5d6b43ed9736ff2567cff00e915f9f541404a867a9aa2acd04ca935559eb46e2a8dc56f0332affaba5a2995d54cce6154aa5a8aba2266539e968a6d59a40af45150d6553a1d1026a28a86b9e46815351562b0946e014ef2fe949f254f4463603e90f0fd9fda26af75f03e87fc75e5de07d0ee649abdf3c3167e5c35f719752bbb947436b67566a2a49ebeb36d0d2032792a9d4b35674f404c59e4fb3d67799f68abbe5d3a8331bff2ca9d55fe7ff963507fabff0096d59cc026d95567d9ff002d69977a825bd63ddea0927fadac981b704894cffaed589f6cb6b8a82eb537ff0096559ca56196b51d43cbff005558b77a83dc5674f79ff4dbcc7aabf6cb9b8ae1a92b9700d4a4b6ff0096b5cd6abf668eba29e3fb1fcf71fbc7aceb5d3ee64bc9fed1f72b9a6b98d91ca7fa1ffcbc7ef28fec3b6b8f9ff7db2bafff00844ecfefff001d473ffa3fc96f59dac33120d3fecffeb7fd2ed697cbb6ff009f2a7cf78f79ff004cea3f32e7c9a00aba97d9ae2b16eb4f48e1ff0053f3d5dd464fb1ff00bf5833eb9f67ff00963f3d26055fb1bc950cf25cff00df1505deb97979ff004cd2aac125b793be19be4fe3a902d4125b4979bffd655d9fecdf63ff0072b13ed96dff002caa19ef124bcfb37faba00b5f6cfb6791e6d676a527fa64fe6fdc4aa575a85b793be2fe0a7eb9fbbb3d3ae7fbff007ea901afa8ea091e9bfe8ff7289e4fb1e8ff00fb2562cffe87fefd13de7da347ff006e9899a9049f68f212b3aeaf3ecf79fbaa20bc4b3861b6ff0097da2d63fb44d4121047f67f3eaacffebb656dfc9fbf4ae7aeff00e98d0027fcb9ff00b69597a8ff007eb520fee7f1d3208d2f34da00c4f2ff00734797fc1525aff72a6d37fe5ba7f7293039efb1fefaa7d95a1f63fdf4f59fe67fa66ca92919d3ecb7aab7766f790d68ff0067ff001cb56bcbfb44359cc6674167f6786a7fb1fd8ead411fefaad7faba200107fa9df2d5583fe5ba54dff2ca8f2ff7d5a0124f1fee6b0fe7ae9bfe3e21ac1d37fd2269ff00bf401a09fe915cf797f63bcae9608fecf59d751fda26a002d63a27b3fb451a76fb79aaeddc7fb9fb4c54019f1ffa3d685bd1f25c549e5fd9e8026f2ffe79543fd969715a16947cfe6d0067da6f8e6ae87fd6555f2fed15760ff47ff5b5484c9ed6f3f73fee568da6a1e6567796947fc7bfcf15324db86b42d2f13fe5ad73769aa56f798971fbead2067335e0d97156be4f3bfe79d627fb756be6ad5199d641fe91feaaad7facae6ad24ad1b7ada007496b2247feb6b52d76572969beba1d3b65c574404ce974d92babd3634ae46d7fd1ebaed2a4ff009ed5d54fa88ebf4cae861ae7b4cadb4ff47adc68352ff4886bc03e236869715ef7aaeff27f755f39fc42bcbcb79a7af0736fe1a33a9d0f1fd4a3f2ff00d6d625c56dea51fda2b13cbafcdeaee6456a2a6f2feb50d4400a9505c55faa1715b440ab3d55a9ee2a0ae8a66730aab4b3d413c95d11330aab5379950d1234810d14515cf53a1d100a2a6a86b9a52b1a13558aaf562a54b9807514da2981fa2de12f0ffd9ebd0ed6cfecf591a1ff00a8adbafd86952f67046d025aad714b55e792b40990cf2551f32adcf59dbe83326824a867bca86a39e801fe65625dfda6e289ef3fe79565cf27fd36ac2a74027b8ace9fed927fcb1a93ccfb3d47f6c7f7ae5980799fb9ae6f52bcff00f775a9a95e573b7727fd36ae79ec69032e7bc7b7ff00a67441aa5b47feaa8bb8de4ae6f51b3fb3ff00aa9ab899b23a49f58b9b89bf755a9fda096f67f69b8fddff00712b809fed36ff00eb7f78ff00c15973de5cde4dfec54f358892b9dccfaa5e49fee5559f5c4fb915735e63fdcf3aaaff00683c752e628ab1d2ff006c7fcf586aaff683c95ceffc2409f725a9e0d43fb426fdd50a5728d4fb627fcb6a67d8ecef21ff006ea8c125b7fcb5fde7fd34a9ff00b62dbfeb9d360626a5a7a59f9f5cf6a51fd9fe4aea27fde5e7fa9ac8d463b9b7f922fe3a929189069ff67a8eef4ffb1c3f66fe37fe3adefb1db69f0fef6b175291ee2f27a061a6c767670eca65dde7da3cf4968fed0fb3ff00bf507db3ecff003cb4013fd8def2cffe9eadbff1faa304696f53cf27db35283fe59a555d4b67d8f7c5400ffb67d9ef37ff007eb46093ecf0ef8bee5715f3de69bfeda56be95ae7fa1cd6d71f7ea909976eb50f2e6ff7e8ff00974aa53ecbcf9e9f3c9e64db3f83f829924f3ffa1de40f5069db3ed9fefd5ad57fd334dff6d2b17e7b39bfdfa00d0d4b7c779be2a3e4b89b7c553cff00e91f3c5ff03ace837d9de5005af2ff00733d737f63fb3d7493c9f6886aadaecf2764bf7e932919fe5bf9d56a08e8ff0096b5a1691fefa84333ed3bd4d3c756a0b3fdccf556d3fd23ce4a626559e3aaf5a5752797673ff7eb2ffe5952608b559fe5fefbfdbab5ff002e7fedd55ff6ea466841fea7f7b59f751fee6b6a0ff489ab3e78ff0082802adac756bccfb3fc945afef3fe014797f4a00208ff007353797f67f9ea1d37fd76cab56bfbbf92a90985ac95af3ecb886b23cbff004cdf577fd6532413fd1eb53cbfdcd735049ff2ed2d6fe9dff1e740195fda0f6f37fb15a96b795973c7f679a8f31e80353ec7f689bf7557bcb7b7ac78354b9a9e0d71ff00e5ad69003a5d2a4b9b7ff96df256a26b1ff3dab909e4fb47efaa78350ad0ce677306cb8f9eacdbd723a6eb1f68ae92d2f124a68cce96d7fd22ba1b4ae6f4dbcada83fe9957552ea26759a65749a6ff00a3d71ba65759a757544476ba749f68ae96d3bd72fa5495d25a495bc06892ebfd4d7857c46d3fccaf71d4a4fdcd7cf5f11b5cfb3cdb2bcbccff00846753a1e4ba947f679ab3a78fed15a377a87db2b3abf3397c4cc8cfff005750d5f9ea85202ad54ab53d674f256b4c064f59d56bccfa565cf2574440827aad3513c9552ac44b49e654752d67234813514515c150d42a1a9a8ae17b8d10d153515ad3ea30a26a28aeca6267ea869d5a5546d3bd4d5fb3b5648ba61515279951cf25495333e6a86a6a2793ecf41930ace9e44a9e7bcac4baa9908aba8ea1e67faaac8fb449ff003e756679123ff554cfb67da3fd7572bdc6887ccfb4555ffb6d44f25b47ff002c6b3a7bcffa6359cb6185dc7fc72d65f9767e77eebefd13c89ff2f147db2dadeb92469033aef4bb9b8ff5b355583474ff00aef5a377aa27fcb1aa53eb9fc159b3432aef43fb3d737a946f5d5ddea0f591a8de5cfdfac27d0a4715a969ff00f4dbcb7aceff004cff0096b5d0ddde5cdc7fcb1f31eb9eba8f5593fe58d643295d49731cdff3ce8835cb9b3867fee7fcf4a3cbbf8ffe3e3ee5519ecffe78ff00aba00db9ef2e6f2cf7d8cde65334e93fb426d92fdfac48247b3f9e2ad482f2f2e26fb4d97fc0ea901dae9da7db5bff00cb6a65d7d9adff00f8bac7fb65e7fcf1ad8fb67ee6989989a96fb8ff0072b3fcbfb3ff00c0fefd686a327da3e4a3fd0ede1a4c118b3d9fda21f3aa0d4bfd77eea1ab507fc4d352ff009e76a94cf32840c83cb4ff0096bf7ff82aaf97febe9fff002f9be5ff0080517727da21fdd7fc0e8608c182cffd33fd8fe0acfba8dee2f3c9fe34adab58ff00733d4f3c7fea1ffefba9199105e797f27f03d5aff57f27f72a3f2ff7d525dffa44db3ff225521335ff00e3f2cffdb4aabe63de59feebefa541f6c7d2ea79ff00d1ff00d322a192677db1edff00dc7a3fb512dfe4b8ff0080568fc9243fefd71b3c9fe99b2a4a47433de3dbc3fbafde568da496d71f3c5f7ffe79d72f06a1fc12fdcad083fe78f934033a2f2fec779fbda7ea31bdbcdf69aa5049f68b3ff48fe0ad4b5bcfdcecaa44905d7fcf68aa97fcb6fb653eeffd1eaac127d9e981575292aac7ff002deb52eacffefdd65ffc7b4dfec526522186acf97fb9d94c83fb92d5eb5ff48876508191e9d525dffa47faaa641feba9f3ecb79a99255ff5779bff0082ad5dff00a3cd44ff00e910efaab049f68b3fded2604d3ffa3cdbe8f352a18237b886a3b5fee5080bd07fa3ff00b9537fc7c7faaacfb493f7356bcb7fb1efa60473ff00a3ff00adad4d2af12deb3e7928ff00575a400d0baaab69b2adc1b2aa5de9ff00679b7c54480b5e5fda2aaf97f679aa7b593f7d5b7f25c51002082cead7f67f990d4f059d6a5ad6a80cb834fadad3ab460b3493fd553e0b3fb3d519ccb5a6ff00aeaecad3fd1eb9b834fadab4ef5a43732676da56cb8aea2d63ae1b4d92bafd2b58ff009eb5e85324eaf4eed5d2dad735a6c895d0dac95d6f602d5dff00aaaf0ef88da7a5c57ad788f7c70feeabc47c63f6cff96b5e3e63fc32267945de8fe5cd59fe5fd9fe4ad0badfff002caaaf9773257e7353e23333eaacd5a33d63dc56120295df6ac79eb46eeb3a7aba60559ab2ee2b526acbbbed5a819f3d56a74f556803460a92a9558a991a4096a6a86af56328dcd0af5629d5354a8010f97f4a2a79ea0a6e3ca0368a2abd388d1faab514f251556e2bf6796c8b7b12524f4955eea4a8443209e4aa5f6ca2792b1e7bca1994cb7f6c7acababca9e7d42b12eaf3ed1594f60810cff69a3fd22a0fb625bffaaa6798f27fadfb95cc8d082ee37acebbfb655e9f504b7f921ac79e4ffbeeb0add068cc9ecef2b3bfb3ee649aba1fb1dcc9feb66a8278ee7fd4d7372dcd6073dfd9ef67feaaa94ffe8ffeb6b7bfb0d2dfe7f3ab12ef4f7b7aca6ad6343127d53ecfff004d29905e7fcf59bcba7ff635cffcf1a67f65a5bffc7c7f1d6480b53ea0ff00f2ef0ffdb4acebabcfdcfefbf7756bfe3dff00d57eed2993c967ff002f1430312792e6e2b1752b3b9b8ff80574975225c7c96ffbbacff96972dca47213d9bdbd416bf69b3ff9ed1a575ff63f321ff9e69fdfaab77669e4ff00cf4a971b0c6695ae797ffc72b6a791ef3e7fe0ae2a7b3ff9f79be4ab5a6ea8f6ff0024b37fb94d099b73c7f679aa0d474ff2fc87ab5048ff007fc9f3ff00dba9e0fb35e5e7fb74c933a08eaacffdcfe07fb953cfa87d9f52fb35177b3cea00cef9fcea27d41347ff00a69fdfa3ccfb44d556793ed135005af2dffe597fc7abd413c7f67fdcd4fe67d9fe4aaba8c9f67ff7e8033a7d971fe8dff912b46efb5626a327fcf2abda6c8f250047a97fe81f7e882f3ec736cad19ecffe5e7f83fe5b5645d46f6ffeabee7f0526068565eaba7a49fe936fff0003ad482f2daf21d9feadeb167ff43ffe2ea4a44105e7f07f1d55bbd42f2ce6ad1824ff009ebfbca351b3b6fbf6f40c9bfe421ff4c1ea1f32e7ee7f1d65f99796f356d5a6b9f68ff8f886a90997ad354fb443f66b8fbf47fc7b4dfec551fecf4f3b7c5525a6a091fc9feb3fd8a6496ad3fd1e6d92fdcacbd4b7c779fbdfb95a9047f68ff8f79be4ff00d02a8ea5bedef367fdf74148b550c127d9e6df557ed9fc156bec7e643be8065ad463ff009798aa8fdb3ccf925ab507fa3ffaeaab3c7f67a090ff005744127d9e1acff33f7db25fb947cf1cdb293291a1fda1fe8754a7ff0053bff8ea7ff8f786886840c20bc7f3bf735a16b7892567ff00c7bfcf53797f6cf9e2fbf4c92f5d6ff27f75556d3fd228f9fced951dad9bdbd006e569ff00c7c43beb3bfd5d68e9dff4d7ee3d6b002acf669f7e2ab56bff004d68f92487f7545a6fad8ce66a5ac7ff004dab5fcbfad62c127d9e6adab593ed15a40ccd4b4bcad1ff0057ff002c6b12b52d6f3ecfff004d2ac4cd8b4bcff9eb5bd059a56241789715af69795b533299a9056c5ac959df6c4ad1b4d95d54ccce934a92bafd2af2b864ff0047ae974aadd01d5cff00e910d79cf8b6cffe98d76d77feaabc5fc6325e7dfb7af371ced140737ae697f67ae42ee4b6b7ff0096d4fd5754b9fbf5cdcf79f68ff5b5f03897790134fa824759177a8251752565dd495c3200bbbcfb4565cf2513c959de67d6888125c5625d49576792b22ee4aa0197159de67d69f3c954e8034e1ab50565c156a092a64690352dea7aab055aa82c9bfd65494da6f994012799f4a8fccaa5e6533ccfad4480b53c950d47e67d6a1a903f55ae2a1a827bcaa5f6cafda066a54175254759175254b10cbb92b227bc7a9a6acf9ef2a5913239ef2b227df56a792b2eeaf1ff00e594358cc204fe67fcf2ace9fed327fcb6a3e6ff009e34f8367fcbc564cd0abe5dcffcf6a67fc7bffadab53de25c7faaac8babc7ff0096558cc689be6aab7579ff006d2aacfac3ff00cb5ac8fedc7f3ab9e633467d42f2b2fed9ff003ca9ff00da1f6ca64fa7bc90ff00cf3ac99a40a33de5fc9f279de4565ddea16d67f279de63d5a9f474f26b3e7b3b6ff9e348d0c4bad42da4ff005b53d5af313fe5ac35341fe91f3dc5001069f6d790fef6ad7f63db793f66a9fed96d67feabf78f54bed8fe77eea8028ea5b3fe7b573dfdb1f679bfe99d5abbd927c92cdff00acbd57fd1ff00ebd6b39948b50496daa7fadff44a82ef47f2fe78aaac1796744fae3c759831907882f34bf925fb95a9fda1fc7143589f6cb693fd6d43fda9f63f9edfee5049d0ff00b74797f68f9eb2e0f107da21dffc14fd36f3f7df6997ee569002d7fcb1d90d65c1fbcf3eb47fe5f3ceace9e3fb3d120347fe3e26df1541aaecaab05e7fa66fff00c7289ffd326ff9e75981973c7f6886a4b4fddc303d477727f055ad0ffd4ec9680352d7fe5ba4bf71eaacfa7fd9e1ff00a75a86793ecf577ed8ff007e2ffbf7401913e9ff0068ff0055fbb7aab06f8e1fb1deff00df75d0fd8d354b3df6f59d77fe8f37ef6802af96f243fee552d2a4f2ff00d6d5df33ec7fee51aae9e9f7e2a4c0b53e9fe67cf0d55f923ff5b0d49a6ea1f679b67f1d687cf71feb7eff00fcf4a101560ffa6550cf67ff006cea6f9e3ff963557fd1ae26ff005dff0000a6067ff6a3e9737fa9adafb627883c8f37f77755567d2edaf21aa507fa1fc9e4d26522941f69b7bc9d2e3efd6f4127fa1feeab135593fd337d68695fe91f250819abe67ee7f7b51ddffa443beaac1fe8ff00e8d2d3e0ff0053f66a6499fe5fda289ecfed14cf2ffd7a569da7fa9a0a4334dff8f3d9351e5d1e67d8e6ff0062adf97fc76f40320ff9635574eff47ad4ff005959d07faead2049bde5ff001d41049fbeab5049f6886993c7f67ad00b7f2559d2bfd22b3a78e8d364fb3cd4d0172a5aab1ffa3d68c1fea6a8ce65ab5ad48237b7ac482b46092b4819336be7ff0096d53fd8fcbf9e2acff9e92d6f2f2dff00d6d588d1fed8fb3ffadad4b5f10565ff00687fcf5a83ccff009e54c0ef2d3c4fff003d6ad47ac7fcf2ae36d3bd745a559d5c4ce6779a1de7da3e7f3abbfd0e4af39d0e3f2ebd1bc3f5e852332d6b9225bd9d7857883c596de74e95eebae47f68b3af97fc71225bea55e1e692b440cbd735cfdf7fa9ae5eeaf3ed153dd49597715f0752576013c958f77254f7725644f795cd20239e4aab3c944f25529e4aba6032792b3aeea7b8ace9ebae022adc541be89aaaf99f5aa90cb5bead412562f99f4a9a0bcaca4074b05e568fdb3e95c9c17956bed9f5aca40745f6cfa541f6cfad60ff00687b513de56320353ed9507db2b2fed947db2b2901a9e651f6cacbfb651f6cac2607eadcf25416f4cf33eb4f824afdbcd265fac6bb92ad79958ba9d06647772567cd45d5e555fb6279349804f25674fa824744ff00e915973c96de77fcf4ac666902eff6c5cdc7faaa3fe3dffe3e3f794cfed07ff9e359da96a17358c8d09f52d53ecffeaa1ae7a791ee2a0badf71feb6b12ef50fe0ae1a9b951279e3a83ed8967ff004d2b167d53fe9b7ef28f32b23689b5fdb9e67fd30a65deb1ff006d2b9efb67d6aacfae25bfc915051d24fac5b5bfcffc759ffdb97327faa86b2fed9ffeeeb460d9e77fcf4a680d0b5fb4dc7cf71505decb7ff5537fbf59fa8eb9ff003d66f912aac1a825c7fd33aa02d7db3fe7943589e5dcf9dbee26ad79fed37154a7bc4b7ff96d52c0c4fb1f970feebeff00fcf4a65a68f73e755a9ef2dbeff935973efb8ff55594c0351d0d3cedf5917778f67ff4dd289ef1fced92d1e5fefb64b3564ca447fda0971feaaa19e4f2ff00d57eeea0fecfb9f3bfd1ef2a8c1a85fd9cdf66b8b3f32a465bb4d53ecf79056d3eb8924d3d9c5f712b94d5747b6f277c50f96ffc75574a8ee6cf4dfdd552133bfd2b58fb47c971f72a79ff0077341ff8e5625a47f68860a93ccfb66a503ffabfee56b0244ff97cfded1e67efb67f1d2cfbee269fcdaceba93ecffe9344c0bb751d49a6de79744f1fda2cff007559dbdeb3036fc4166924305476927d9e1d92ff00dfca3fb6124d367f37f789fc695560ff0047b3ff009e9655a40093fe3cfe78ab43fe4290cfff002cdeb3f51ff43f23fb951cf251302183fb9504f23dbffadabb3ffa47fbf59f77bede6dfe77c8f593013ed9ff003c6addaf881ecffd6d54fb627fcf1f9297cb4fbf17ef12a40e93fe120b6b8ff9635567bcb693fe5ceb9e83655a837ffcb29aa901a3fda09e77eea1ab5f25c7cf557ed9f67ff5b0fc95a106cfbf5480e7aebecd790feea8d3bfd1ea7d4acd3ce9fcaace9e378ffd550c0dbbbff4cb3dff00c75574ebcfdf55982f12f21ff6eb33fe3def2901a975ff001f9bff0082920ff535a3ff002dbf7bf72aaddc7f679a8021baff0053fbdab56bfe8f51ff00acab506c92ceb48005a7fa3f9cf59707fcb77ad49ef123b3ac583fd6d6806a69b27d9eb42793ecf79fec5627fd72ad1f33ed10ff00b9419ccb53ff00a4567f9951da5e7d9ead4fbfff00b650665ab4bcff00be2b477d72169a83dbcd5a969a83d3406dff0068fee6a783587ac7fb67d688350aa036e0d41ea7fed0bc92b1e0bcad1824a00b506fb8ff005b5d0e9ddab2f4ed97157a08d2b48099d5da495d5e9bf66ae0ed7fd1eba2b592ba69ee6533d1b4e8fccff555d7695fe8f5e73a55e7d9ff00d5576da55e57744ccea358ff008f3af9e7c71e1ffb44d3d7bfea3789f63af04f1778c2dbed9b2bc7cce29c15c0f1ed4b4bb9d3eb2e78ee6bb5d4750fb67faa9ab9bd4bfebb57c3d682d2c073b771bd62ddff00a3d6bddf6ae7aeeb826ac0417155aa69e4ace9a9d3339904f2552b8a7cf2555ae8899956abd2cf59f715ac4d204f50d55a9bccfad1234343ccfe3a3cc7b7acff0033f734ff0033ed13573d4e805df32a1df557ccfad1e67d6b9e405adf53799591e67d2a7f33eb59480d0f32a4acfabd5cd57a01ffd9);
INSERT INTO `pet` (`pet_id`, `owner_id`, `name`, `type`, `breed`, `gender`, `age`, `color`, `b_day`, `b_month`, `b_year`, `profile_image`) VALUES
(8, 3, 'BANTAY', 'DOG', 'DALMATIAN', 'MALE', 1, 'WHITE', '6', 'DEC', '2014', NULL),
(10, 11, 'TOYTOY', 'DOG', 'DALMATIAN', 'MALE', 1, 'NEARLY BLACK', '1', 'DEC', '2015', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pet_type`
--

CREATE TABLE IF NOT EXISTS `pet_type` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `pet_type_name` varchar(75) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `pet_type`
--

INSERT INTO `pet_type` (`id`, `pet_type_name`) VALUES
(1, 'DOG'),
(2, 'CAT'),
(3, 'SNAKE'),
(4, 'MOUSES'),
(5, 'GOAT'),
(6, 'RABBIT'),
(7, 'BIRD'),
(8, 'HORSE'),
(9, 'PIG'),
(10, 'FISH');

-- --------------------------------------------------------

--
-- Table structure for table `physical_exam`
--

CREATE TABLE IF NOT EXISTS `physical_exam` (
  `id` int(125) NOT NULL AUTO_INCREMENT,
  `exam_number` varchar(255) DEFAULT NULL,
  `pet_id` int(125) NOT NULL,
  `crh` varchar(255) NOT NULL,
  `findings` varchar(255) NOT NULL,
  `weight` varchar(25) NOT NULL,
  `temp` varchar(25) NOT NULL,
  `doa` varchar(255) DEFAULT NULL,
  `hour` int(13) DEFAULT NULL,
  `minute` int(61) DEFAULT NULL,
  `meridiem` varchar(3) DEFAULT NULL,
  `day` int(25) NOT NULL,
  `month` varchar(25) NOT NULL,
  `year` int(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `physical_exam`
--

INSERT INTO `physical_exam` (`id`, `exam_number`, `pet_id`, `crh`, `findings`, `weight`, `temp`, `doa`, `hour`, `minute`, `meridiem`, `day`, `month`, `year`) VALUES
(1, '151212075011', 2, 'SOFT STOOL, WATERY STOOL (BROWN YELLOWISH)', 'MUCOUS MEMBRANE - PALE, HEAD/NECK - OK', '11.9 KG', '41.04 °C', 'PALE MEMBRANE, SEVERE DEHYDRATION', 0, 0, '', 2, 'NOV', 2015),
(2, '0', 1, 'SOFT STOOL, WATERY STOOL (BROWN YELLOWISH)', 'MUCOUS MEMBRANE - PALE, HEAD/NECK - BRUSIES', '26 LB', '38 °F', 'PALE MEMBRANE, SEVERE DEHYDRATION', 0, 0, '', 6, 'NOV', 2015),
(3, '0', 2, 'HARD STOOL', 'MUCOUS MEMBRANE - PALE', '4.6 KG', '96.8 °F', 'HELLO WORLD', 0, 0, '', 14, 'NOV', 2015),
(4, '0', 2, 'ASDASDAS, ASDASD, ASDAS', 'HYDRATION - OK', '56 LB', '89.6 °F', 'NO SAMPLE', 12, 20, 'PM', 1, 'DEC', 2015),
(5, '151212075011', 2, 'ASDASD, QWEWQ, ERTRET, TYUYT', 'EYES - OK, MUCOUS MEMBRANE - OK, RESPIRATORY - OK', '12 lb', '12 °F', '', 7, 50, 'AM', 12, 'DEC', 2015),
(6, '151214191436', 7, 'SOFT STOOL, VOMITTING', 'MUCOUS MEMBRANE - PALE, EYES - DILATED', '50 kg', '30 °C', 'THE PET IS EXPERIENCING WET FECES', 19, 14, 'PM', 14, 'DEC', 2015),
(9, '151223224748', 2, 'COMPLAINT SAMPLE', 'GENERAL CONDITION - OK FINE', '36 lb', '25 °C', 'FINE CONDITION, ONLY HAS CYST ON THE THROAT.', 22, 47, 'PM', 23, 'DEC', 2015),
(10, '151226173357', 2, 'SAMPLE1, SAMPLE2', 'HEAD/NECK - SAMPLE OK', '12 lb', '12 °C', 'OK SAMPLE DOA', 17, 33, 'PM', 26, 'DEC', 2015),
(11, '160104172916', 2, 'SAMPLE, SAMJSK', 'EYES - OK', '12 lb', '12 °C', 'YTYRTRT', 17, 29, 'PM', 4, 'JAN', 2016);

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

CREATE TABLE IF NOT EXISTS `prescription` (
  `prescription_id` int(255) NOT NULL AUTO_INCREMENT,
  `prescription_number` varchar(9999) NOT NULL,
  `pet_id` int(255) NOT NULL,
  `medicine` varchar(800) NOT NULL,
  `volume` varchar(800) NOT NULL,
  `volume_p2` varchar(25) NOT NULL,
  `signa` varchar(800) NOT NULL,
  `day` int(255) NOT NULL,
  `month` varchar(80) NOT NULL,
  `year` int(255) NOT NULL,
  PRIMARY KEY (`prescription_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `prescription`
--

INSERT INTO `prescription` (`prescription_id`, `prescription_number`, `pet_id`, `medicine`, `volume`, `volume_p2`, `signa`, `day`, `month`, `year`) VALUES
(7, '1120153518', 2, 'ANTI DEMATITIS PYROXINE', '1', 'MG', 'TAKE TWO TABLETS FOR ONE WEEK', 11, 'Nov', 2015),
(8, '2620154327', 6, 'PRX 233', '6', 'CC', 'FDGFFXVCXV', 26, 'Nov', 2015),
(9, '2620154526', 2, 'MEFINAMIC ACID', '7', 'CC', 'FGFGGFDGDF', 26, 'Nov', 2015),
(10, '2620154717', 6, 'MEFINAMIC ACID', '2', 'CC', 'SFDFDFGFGD', 26, 'Nov', 2015),
(11, '2720153436', 2, 'MYROXINE', '12', 'CC', 'TAKE EVERY NIGHT', 27, 'NOV', 2015),
(12, '272015150', 2, 'PAPPI POXY 12', '4', 'MG', 'TAKE ONE FOR A YEAR', 27, 'NOV', 2015),
(13, '27201526', 2, 'PAPPII POXY 16', '2', 'CC', 'TAKE ONCE', 27, 'NOV', 2015),
(14, '262015108', 2, 'SAMPLE MED', '3', 'CC', 'TAKE TWO TIME A DAY', 26, 'DEC', 2015);

-- --------------------------------------------------------

--
-- Table structure for table `purchase_item`
--

CREATE TABLE IF NOT EXISTS `purchase_item` (
  `purchase_id` int(255) NOT NULL AUTO_INCREMENT,
  `item_id` int(255) NOT NULL,
  `owner_id` int(255) NOT NULL,
  `payment_id` int(255) NOT NULL,
  PRIMARY KEY (`purchase_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `receive_item`
--

CREATE TABLE IF NOT EXISTS `receive_item` (
  `receive_id` int(255) NOT NULL AUTO_INCREMENT,
  `supplier_id` int(255) NOT NULL,
  `order_id` int(255) NOT NULL,
  `deliver` varchar(255) NOT NULL,
  `invoice` varchar(255) NOT NULL,
  `classification_id` int(255) NOT NULL,
  `item_id` int(255) NOT NULL,
  `CliSale` varchar(255) NOT NULL,
  `ForCheck` int(255) NOT NULL,
  `bigSmall` varchar(255) NOT NULL,
  `qtyname` varchar(255) NOT NULL,
  `Volume` varchar(255) NOT NULL,
  `Numof` int(255) NOT NULL,
  `qty` int(255) NOT NULL,
  `remainQty` int(255) NOT NULL,
  `manu_day` int(255) NOT NULL,
  `expi_day` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `receive_day` int(255) NOT NULL,
  `receive_month` varchar(255) NOT NULL,
  `receive_year` int(255) NOT NULL,
  PRIMARY KEY (`receive_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `receive_item_confirm`
--

CREATE TABLE IF NOT EXISTS `receive_item_confirm` (
  `confirm_id` int(255) NOT NULL AUTO_INCREMENT,
  `order_id` int(255) NOT NULL,
  `receive_day` int(255) NOT NULL,
  `receive_month` varchar(255) NOT NULL,
  `receive_year` int(255) NOT NULL,
  `qty` int(255) NOT NULL,
  `unit_price` float DEFAULT NULL,
  `retail_price` float DEFAULT NULL,
  `note` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`confirm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `services_acquired`
--

CREATE TABLE IF NOT EXISTS `services_acquired` (
  `srv_acq_id` int(125) NOT NULL AUTO_INCREMENT,
  `pet_id` int(255) NOT NULL,
  `owner_id` int(255) DEFAULT NULL,
  `acq_name` varchar(30) NOT NULL,
  `acq_price` decimal(10,0) NOT NULL,
  `day` int(255) NOT NULL,
  `month` varchar(9999) NOT NULL,
  `year` int(255) NOT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`srv_acq_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `services_acquired`
--

INSERT INTO `services_acquired` (`srv_acq_id`, `pet_id`, `owner_id`, `acq_name`, `acq_price`, `day`, `month`, `year`, `status`) VALUES
(8, 6, NULL, 'Regular Wash and Care (XL)', '300', 10, 'DEC', 2015, 'ONGOING'),
(9, 7, NULL, 'Medicated Wash and Care (S)', '250', 10, 'DEC', 2015, 'ONGOING'),
(11, 6, NULL, 'Special Wash and Care (M)', '300', 10, 'DEC', 2015, 'ONGOING'),
(12, 1, NULL, 'Medicated Wash and Care (M)', '300', 10, 'DEC', 2015, 'ONGOING'),
(13, 1, NULL, 'Regular Wash and Care (L)', '250', 10, 'DEC', 2015, 'ONGOING'),
(14, 2, NULL, 'Ear cropping', '1500', 5, 'JAN', 2016, 'ONGOING');

-- --------------------------------------------------------

--
-- Table structure for table `services_classifications`
--

CREATE TABLE IF NOT EXISTS `services_classifications` (
  `classification_id` int(20) NOT NULL AUTO_INCREMENT,
  `classification_name` varchar(50) NOT NULL,
  PRIMARY KEY (`classification_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;

--
-- Dumping data for table `services_classifications`
--

INSERT INTO `services_classifications` (`classification_id`, `classification_name`) VALUES
(1, 'GROOMING'),
(9, 'CHECK-UP'),
(16, 'DEWORMING'),
(17, 'VACCINATION'),
(18, 'SURGERY'),
(19, 'HYGIENE AND SANITATION'),
(20, 'EUTHANASIA'),
(21, 'VACCINE'),
(22, 'hi'),
(23, 'hairstyle'),
(24, 'bath'),
(25, 'CONFINEMENT'),
(26, 'other packages');

-- --------------------------------------------------------

--
-- Table structure for table `services_data`
--

CREATE TABLE IF NOT EXISTS `services_data` (
  `service_id` int(125) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `prices` decimal(50,0) NOT NULL,
  `classification` varchar(50) NOT NULL,
  `day_updated` int(20) NOT NULL,
  `month_updated` varchar(20) NOT NULL,
  `year_updated` int(20) NOT NULL,
  `old_price` decimal(20,0) NOT NULL,
  PRIMARY KEY (`service_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=80 ;

--
-- Dumping data for table `services_data`
--

INSERT INTO `services_data` (`service_id`, `name`, `prices`, `classification`, `day_updated`, `month_updated`, `year_updated`, `old_price`) VALUES
(29, 'Consultation/Professional', '200', 'Check-up', 28, 'Nov', 2015, '210'),
(30, 'Emergency', '300', 'Check-up', 5, 'Nov', 2015, '400'),
(31, 'Home Service ', '200', 'Check-up', 0, '', 0, '0'),
(32, '1 to 5 kg', '100', 'Deworming', 0, '', 0, '0'),
(33, '6 to 10 kg', '150', 'Deworming', 0, '', 0, '0'),
(34, '11 to 20 kg', '375', 'Deworming', 3, 'JAN', 2016, '250'),
(35, '21 to 30 kg', '400', 'Deworming', 0, '', 0, '0'),
(36, '31 to 40 kg', '500', 'Deworming', 0, '', 0, '0'),
(37, '5 in 1 (DHPPIL)', '390', 'Vaccination', 0, '', 0, '0'),
(38, 'Rabies', '250', 'Vaccination', 0, '', 0, '0'),
(39, 'Kennel Cough', '350', 'Vaccination', 0, '', 0, '0'),
(40, 'Mange Treatment (S-M dog)', '250', 'Hygiene and Sanitation', 0, '', 0, '0'),
(41, 'Ear Cleaning', '150', 'Hygiene and Sanitation', 0, '', 0, '0'),
(42, 'Ear Cleaning w/ infection', '350', 'Hygiene and Sanitation', 0, '', 0, '0'),
(43, 'Nail clipping ', '50', 'Hygiene and Sanitation', 0, '', 0, '0'),
(44, 'Nail clipping w/ ingrown nail', '250', 'Hygiene and Sanitation', 0, '', 0, '0'),
(45, 'Tail docking (less than 1 week old)', '200', 'Hygiene and Sanitation', 0, '', 0, '0'),
(46, 'Tail docking (1 to 4 weeks old)', '350', 'Hygiene and Sanitation', 0, '', 0, '0'),
(47, 'Tail docking 4 weeks up', '550', 'Hygiene and Sanitation', 0, '', 0, '0'),
(48, 'Wound dressing ', '250', 'Hygiene and Sanitation', 0, '', 0, '0'),
(49, 'Wound dressing w/ suturing (S)', '500', 'Hygiene and Sanitation', 0, '', 0, '0'),
(50, 'Wound dressing (Medium wound)', '800', 'Hygiene and Sanitation', 0, '', 0, '0'),
(51, 'Wound dressing (Large wound)', '1000', 'Hygiene and Sanitation', 0, '', 0, '0'),
(52, 'Ear cropping', '1500', 'Surgery', 0, '', 0, '0'),
(53, 'Castration (cat)', '1200', 'Surgery', 0, '', 0, '0'),
(54, 'Castration (dog / S-M)', '1500', 'Surgery', 0, '', 0, '0'),
(55, 'Whelping assistance (normal delivery)', '2500', 'Surgery', 0, '', 0, '0'),
(56, 'Cesarian section (cat)', '3000', 'Surgery', 0, '', 0, '0'),
(57, 'Cesarian section (dog/small)', '5000', 'Surgery', 0, '', 0, '0'),
(58, 'Cesarian section (dog/medium)', '7000', 'Surgery', 0, '', 0, '0'),
(59, 'Ear hematoma', '1500', 'Surgery', 0, '', 0, '0'),
(60, 'Euthanasia', '500', 'Euthanasia', 0, '', 0, '0'),
(61, 'Regular Wash and Care (S)', '150', 'Grooming', 0, '', 0, '0'),
(62, 'Regular Wash and Care (M)', '150', 'Grooming', 0, '', 0, '0'),
(63, 'Special Wash and Care (L)', '350', 'Grooming', 0, '', 0, '0'),
(64, 'Regular Wash and Care (XL)', '300', 'Grooming', 0, '', 0, '0'),
(65, 'Special Wash and Care (S)', '250', 'Grooming', 0, '', 0, '0'),
(66, 'Special Wash and Care (M)', '300', 'Grooming', 0, '', 0, '0'),
(67, 'Regular Wash and Care (L)', '250', 'Grooming', 0, '', 0, '0'),
(68, 'Special Wash and Care (XL)', '400', 'Grooming', 0, '', 0, '0'),
(69, 'Medicated Wash and Care (S)', '250', 'Grooming', 0, '', 0, '0'),
(70, 'Medicated Wash and Care (M)', '300', 'Grooming', 0, '', 0, '0'),
(71, 'Medicated Wash and Care (L)', '350', 'Grooming', 0, '', 0, '0'),
(72, 'Medicated Wash and Care (XL)', '350', 'Grooming', 0, '', 0, '0'),
(73, 'Nail Cutting XL', '200', 'Grooming', 0, '', 0, '0'),
(74, 'Haircut', '200', 'Grooming', 0, '', 0, '0'),
(75, 'asdas', '123', 'Vaccination', 0, '', 0, '0'),
(76, 'testing', '1234', 'classtesting', 0, '', 0, '0'),
(77, 'ANTI-RABIES VACCINE', '1000', 'VACCINE', 0, '', 0, '0'),
(78, 'mohawk', '60', 'hairstyle', 16, 'Nov', 2015, '50'),
(79, 'bubble', '330', 'bath', 4, 'JAN', 2016, '300');

-- --------------------------------------------------------

--
-- Table structure for table `sms_notification`
--

CREATE TABLE IF NOT EXISTS `sms_notification` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `owner_id` int(255) NOT NULL,
  `pet_id` int(255) NOT NULL,
  `message` varchar(9999) NOT NULL,
  `day` int(255) NOT NULL,
  `month` varchar(9999) NOT NULL,
  `year` int(255) NOT NULL,
  `hour` int(255) NOT NULL,
  `minute` varchar(60) NOT NULL,
  `meridiem` varchar(60) NOT NULL,
  `status` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

--
-- Dumping data for table `sms_notification`
--

INSERT INTO `sms_notification` (`id`, `owner_id`, `pet_id`, `message`, `day`, `month`, `year`, `hour`, `minute`, `meridiem`, `status`) VALUES
(25, 2, 2, 'Good day Mr/Mrs GUINTO, IRVIN INTONG , This is Daddiangas Heights Veterinary Clinic informing you on 28 JAN 2016 at 1:00 AM will be SCRAPPY''s next appointment for DEWORMING. Thank you.', 25, 'JAN', 2016, 1, '00', 'AM', 'ONGOING');

-- --------------------------------------------------------

--
-- Table structure for table `sprice_history`
--

CREATE TABLE IF NOT EXISTS `sprice_history` (
  `sprice_id` int(11) NOT NULL AUTO_INCREMENT,
  `service_name` varchar(200) NOT NULL,
  `price` int(20) NOT NULL,
  `inc` int(255) NOT NULL,
  `dayUpdated` int(20) NOT NULL,
  `monthUpdated` varchar(20) NOT NULL,
  `yearUpdated` int(20) NOT NULL,
  PRIMARY KEY (`sprice_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `sprice_history`
--

INSERT INTO `sprice_history` (`sprice_id`, `service_name`, `price`, `inc`, `dayUpdated`, `monthUpdated`, `yearUpdated`) VALUES
(1, 'Emergency', 300, 0, 5, 'Nov', 2015),
(2, 'Consultation/Professional', 210, 0, 7, 'Nov', 2015),
(3, 'Consultation/Professional', 200, 0, 10, 'Nov', 2015),
(4, 'mohawk', 60, 0, 16, 'Nov', 2015),
(5, 'Consultation/Professional', 210, 5, 28, 'Nov', 2015),
(6, '11 to 20 kg', 375, 50, 3, 'JAN', 2016),
(7, 'bubble', 330, 10, 4, 'JAN', 2016);

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE IF NOT EXISTS `supplier` (
  `supplier_id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(125) NOT NULL,
  `title_name` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `middle_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `suffix_name` varchar(255) NOT NULL,
  `contact_number` varchar(255) NOT NULL,
  `street` varchar(255) NOT NULL,
  `prk` varchar(255) NOT NULL,
  `brgy` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `province` varchar(255) NOT NULL,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`supplier_id`, `name`, `title_name`, `first_name`, `middle_name`, `last_name`, `suffix_name`, `contact_number`, `street`, `prk`, `brgy`, `city`, `province`) VALUES
(12, 'VETMATE FARMA CORP.', 'JUAN DE LA CRUZ', 'UNKNOWN', 'MIDDLE NAME', 'UNKNOWN', 'SUFFIX NAME', '+630000000011', 'MALAYO STREET', 'UNKNOWN', 'UNKNOWN', 'GENERAL SANTOS CITY', 'SOUTH COTABATO'),
(13, 'NUTRATECH BIOPHARMA, INC', '', 'UNKNOWN', '', 'UNKNOWN', '', '+630000000000000000', '', 'UNKNOWN', 'UNKNOWN', 'UNKNOWN', 'UNKNOWN'),
(14, 'CAM MARKETING', '', 'UNKNOWN', 'MIDDLE NAME', 'UNKNOWN', 'SUFFIX NAME', '+630000000000', '', 'UNKNOWN', 'UNKNOWN', 'UNKNOWN', 'UNKNOWN'),
(15, 'NUTRI-VET', '', 'UNKNOWN', '', 'UNKNOWN', '', '+63000000000000', '', 'UNKNOWN', 'UNKNOWN', 'UNKNOWN', 'UNKNOWN'),
(16, 'HL DISTRIBUTIONS', '', 'UNKNOWN', '', 'UNKNOWN', '', '+630000000000', '', 'UNKNOWN', 'UNKNOWN', 'UNKNOWN', 'UNKNOWN'),
(17, 'DMHS', ' ', 'IRVIN ', ' ', 'GUINTO', ' ', '+639108250936', '', 'CEBU', 'QUEZON', 'GENERAL SANTOS CITY', 'SOUTH COTABATO'),
(18, 'MANILA', 'TITLE NAME', 'IRVIN', 'MIDDLE NAME', 'GUINTO', 'SUFFIX NAME', '+639999999999', '', 'GUINTO ST.', 'MALAKAS', 'GENERAL SANTOS CITY', 'SOUTH COTABATO'),
(19, 'SAMPLE SUPLIER', 'SNAMEN', 'ASDASD', 'ASDASDAS', 'ASDASDSAD', 'ASDASD', '123222344545', 'ASDASDAS', 'ASDASD', 'ASDASDAS', 'ASDASD', 'ASDASDAS'),
(20, 'QWEWQE', 'QWEQWE', 'QWEQWE', 'QWEQWEW', 'QWEQWEQ', 'QWEQWEWQ', '+6354645654645', 'ASDSADASDASDA', 'DASDASDASD', 'DASDASDASD', 'ADASDASDAS', 'ASDADASDA'),
(21, 'ZXCZXCXZC', 'ZXCZXCXZ', 'ZXCZXCXZ', 'ZXCZXCZX', 'ZXCZXCZXC', 'ZXCZXCZXC', '+6312321312312', 'XVXCVXXCVXCV', 'CZXCZXCZXCZX', 'VBCVBCVBVC', 'ZXCZXCZXCZXC', 'XXCBXBCVBCVBV');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(125) NOT NULL AUTO_INCREMENT,
  `username` varchar(225) NOT NULL,
  `password` varchar(225) NOT NULL,
  `type` varchar(50) NOT NULL,
  `title_name` varchar(125) DEFAULT NULL,
  `first_name` varchar(125) NOT NULL,
  `middle_name` varchar(125) NOT NULL,
  `last_name` varchar(125) NOT NULL,
  `suffix_name` varchar(125) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `username`, `password`, `type`, `title_name`, `first_name`, `middle_name`, `last_name`, `suffix_name`) VALUES
(1, 'av', 'av123', 'ASISSTANT VETERINARIAN', 'NA', 'NA', 'NA', 'NA', 'NA'),
(2, 'vet', 'vet123', 'VETERINARIAN', 'na', 'na', 'na', 'na', 'na');

-- --------------------------------------------------------

--
-- Table structure for table `user_logs`
--

CREATE TABLE IF NOT EXISTS `user_logs` (
  `log_id` int(255) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(255) DEFAULT NULL,
  `user_id` int(255) NOT NULL,
  `hour` int(20) NOT NULL,
  `minute` int(60) NOT NULL,
  `am_pm` varchar(10) NOT NULL,
  `day` varchar(50) NOT NULL,
  `month` varchar(50) NOT NULL,
  `year` varchar(50) NOT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1840 ;

--
-- Dumping data for table `user_logs`
--

INSERT INTO `user_logs` (`log_id`, `login_name`, `user_id`, `hour`, `minute`, `am_pm`, `day`, `month`, `year`) VALUES
(2, 'Login', 2, 2, 37, 'PM', '2', 'NOV', '2015'),
(3, 'Logout', 2, 2, 38, 'PM', '2', 'NOV', '2015'),
(4, 'Login', 2, 2, 39, 'PM', '2', 'NOV', '2015'),
(5, 'Logout', 2, 2, 40, 'PM', '2', 'NOV', '2015'),
(6, 'Login', 2, 2, 51, 'PM', '2', 'NOV', '2015'),
(7, 'Login', 2, 3, 4, 'PM', '2', 'NOV', '2015'),
(8, 'Logout', 2, 3, 4, 'PM', '2', 'NOV', '2015'),
(9, 'Login', 2, 3, 11, 'PM', '2', 'NOV', '2015'),
(10, 'Logout', 2, 3, 11, 'PM', '2', 'NOV', '2015'),
(11, 'Login', 2, 3, 19, 'PM', '2', 'NOV', '2015'),
(12, 'Logout', 2, 3, 19, 'PM', '2', 'NOV', '2015'),
(13, 'Login', 2, 3, 21, 'PM', '2', 'NOV', '2015'),
(14, 'Logout', 2, 3, 21, 'PM', '2', 'NOV', '2015'),
(15, 'Login', 2, 3, 23, 'PM', '2', 'NOV', '2015'),
(16, 'Login', 2, 3, 27, 'PM', '2', 'NOV', '2015'),
(17, 'Logout', 2, 3, 27, 'PM', '2', 'NOV', '2015'),
(18, 'Login', 2, 3, 27, 'PM', '2', 'NOV', '2015'),
(19, 'Logout', 2, 3, 28, 'PM', '2', 'NOV', '2015'),
(20, 'Login', 2, 3, 28, 'PM', '2', 'NOV', '2015'),
(21, 'Logout', 2, 3, 28, 'PM', '2', 'NOV', '2015'),
(22, 'Login', 2, 4, 16, 'PM', '2', 'NOV', '2015'),
(23, 'Login', 2, 4, 18, 'PM', '2', 'NOV', '2015'),
(24, 'Login', 2, 4, 23, 'PM', '2', 'NOV', '2015'),
(25, 'Login', 2, 4, 37, 'PM', '2', 'NOV', '2015'),
(26, 'Login', 2, 4, 38, 'PM', '2', 'NOV', '2015'),
(27, 'Login', 2, 4, 39, 'PM', '2', 'NOV', '2015'),
(28, 'Login', 2, 4, 50, 'PM', '2', 'NOV', '2015'),
(29, 'Login', 2, 11, 57, 'AM', '3', 'NOV', '2015'),
(30, 'Login', 2, 12, 7, 'PM', '3', 'NOV', '2015'),
(31, 'Login', 2, 12, 43, 'PM', '3', 'NOV', '2015'),
(32, 'Login', 2, 12, 44, 'PM', '3', 'NOV', '2015'),
(33, 'Login', 2, 2, 23, 'PM', '3', 'NOV', '2015'),
(34, 'Login', 2, 2, 46, 'PM', '3', 'NOV', '2015'),
(35, 'Login', 2, 2, 48, 'PM', '3', 'NOV', '2015'),
(36, 'Login', 1, 2, 54, 'PM', '3', 'NOV', '2015'),
(37, 'Login', 2, 6, 52, 'AM', '4', 'NOV', '2015'),
(38, 'Login', 2, 6, 54, 'AM', '4', 'NOV', '2015'),
(39, 'Login', 2, 6, 58, 'AM', '4', 'NOV', '2015'),
(40, 'Login', 2, 7, 13, 'AM', '4', 'NOV', '2015'),
(41, 'Login', 2, 7, 19, 'AM', '4', 'NOV', '2015'),
(42, 'Login', 2, 7, 21, 'AM', '4', 'NOV', '2015'),
(43, 'Logout', 2, 7, 21, 'AM', '4', 'NOV', '2015'),
(44, 'Login', 2, 7, 22, 'AM', '4', 'NOV', '2015'),
(45, 'Logout', 2, 7, 22, 'AM', '4', 'NOV', '2015'),
(46, 'Login', 2, 7, 26, 'AM', '4', 'NOV', '2015'),
(47, 'Login', 2, 7, 28, 'AM', '4', 'NOV', '2015'),
(48, 'Login', 2, 7, 29, 'AM', '4', 'NOV', '2015'),
(49, 'Login', 2, 7, 31, 'AM', '4', 'NOV', '2015'),
(50, 'Login', 2, 7, 33, 'AM', '4', 'NOV', '2015'),
(51, 'Login', 2, 7, 35, 'AM', '4', 'NOV', '2015'),
(52, 'Login', 2, 9, 27, 'PM', '4', 'NOV', '2015'),
(53, 'Logout', 2, 9, 32, 'PM', '4', 'NOV', '2015'),
(54, 'Login', 2, 9, 36, 'PM', '4', 'NOV', '2015'),
(55, 'Login', 2, 9, 38, 'PM', '4', 'NOV', '2015'),
(56, 'Logout', 2, 9, 40, 'PM', '4', 'NOV', '2015'),
(57, 'Login', 2, 7, 20, 'AM', '5', 'NOV', '2015'),
(58, 'Login', 2, 8, 2, 'AM', '5', 'NOV', '2015'),
(59, 'Login', 2, 8, 10, 'AM', '5', 'NOV', '2015'),
(60, 'Login', 2, 8, 13, 'AM', '5', 'NOV', '2015'),
(61, 'Login', 2, 8, 31, 'AM', '5', 'NOV', '2015'),
(62, 'Logout', 2, 8, 37, 'AM', '5', 'NOV', '2015'),
(63, 'Login', 2, 8, 39, 'AM', '5', 'NOV', '2015'),
(64, 'Logout', 2, 8, 40, 'AM', '5', 'NOV', '2015'),
(65, 'Login', 2, 8, 53, 'AM', '5', 'NOV', '2015'),
(66, 'Logout', 2, 8, 54, 'AM', '5', 'NOV', '2015'),
(67, 'Login', 2, 8, 55, 'AM', '5', 'NOV', '2015'),
(68, 'Login', 2, 8, 56, 'AM', '5', 'NOV', '2015'),
(69, 'Logout', 2, 8, 57, 'AM', '5', 'NOV', '2015'),
(70, 'Login', 2, 9, 3, 'AM', '5', 'NOV', '2015'),
(71, 'Login', 2, 9, 10, 'AM', '5', 'NOV', '2015'),
(72, 'Login', 2, 8, 49, 'AM', '6', 'NOV', '2015'),
(73, 'Logout', 2, 8, 51, 'AM', '6', 'NOV', '2015'),
(74, 'Login', 2, 9, 6, 'AM', '6', 'NOV', '2015'),
(75, 'Login', 2, 9, 8, 'AM', '6', 'NOV', '2015'),
(76, 'Login', 2, 9, 19, 'AM', '6', 'NOV', '2015'),
(77, 'Logout', 2, 9, 20, 'AM', '6', 'NOV', '2015'),
(78, 'Login', 2, 9, 21, 'AM', '6', 'NOV', '2015'),
(79, 'Login', 2, 9, 22, 'AM', '6', 'NOV', '2015'),
(80, 'Logout', 2, 9, 24, 'AM', '6', 'NOV', '2015'),
(81, 'Login', 2, 9, 37, 'AM', '6', 'NOV', '2015'),
(82, 'Login', 2, 9, 39, 'AM', '6', 'NOV', '2015'),
(83, 'Login', 2, 9, 45, 'AM', '6', 'NOV', '2015'),
(84, 'Logout', 2, 9, 46, 'AM', '6', 'NOV', '2015'),
(85, 'Login', 2, 9, 49, 'AM', '6', 'NOV', '2015'),
(86, 'Login', 2, 9, 51, 'AM', '6', 'NOV', '2015'),
(87, 'Logout', 2, 9, 54, 'AM', '6', 'NOV', '2015'),
(88, 'Login', 2, 9, 55, 'AM', '6', 'NOV', '2015'),
(89, 'Logout', 2, 9, 56, 'AM', '6', 'NOV', '2015'),
(90, 'Login', 2, 9, 58, 'AM', '6', 'NOV', '2015'),
(91, 'Login', 2, 10, 11, 'AM', '6', 'NOV', '2015'),
(92, 'Login', 2, 10, 13, 'AM', '6', 'NOV', '2015'),
(93, 'Login', 2, 10, 17, 'AM', '6', 'NOV', '2015'),
(94, 'Login', 2, 10, 20, 'AM', '6', 'NOV', '2015'),
(95, 'Logout', 2, 10, 20, 'AM', '6', 'NOV', '2015'),
(96, 'Login', 2, 11, 1, 'AM', '6', 'NOV', '2015'),
(97, 'Login', 2, 11, 29, 'AM', '6', 'NOV', '2015'),
(98, 'Logout', 2, 11, 29, 'AM', '6', 'NOV', '2015'),
(99, 'Login', 2, 7, 2, 'PM', '6', 'NOV', '2015'),
(100, 'Login', 2, 7, 10, 'PM', '6', 'NOV', '2015'),
(101, 'Login', 2, 7, 16, 'PM', '6', 'NOV', '2015'),
(102, 'Login', 2, 7, 26, 'PM', '6', 'NOV', '2015'),
(103, 'Logout', 2, 7, 30, 'PM', '6', 'NOV', '2015'),
(104, 'Login', 2, 8, 59, 'PM', '6', 'NOV', '2015'),
(105, 'Login', 2, 9, 1, 'PM', '6', 'NOV', '2015'),
(106, 'Logout', 2, 9, 5, 'PM', '6', 'NOV', '2015'),
(107, 'Login', 2, 9, 29, 'PM', '6', 'NOV', '2015'),
(108, 'Login', 2, 6, 25, 'PM', '7', 'NOV', '2015'),
(109, 'Logout', 2, 6, 26, 'PM', '7', 'NOV', '2015'),
(110, 'Login', 2, 10, 4, 'AM', '9', 'NOV', '2015'),
(111, 'Login', 2, 10, 6, 'AM', '9', 'NOV', '2015'),
(112, 'Login', 2, 10, 10, 'AM', '9', 'NOV', '2015'),
(113, 'Logout', 2, 10, 12, 'AM', '9', 'NOV', '2015'),
(114, 'Login', 2, 10, 13, 'AM', '9', 'NOV', '2015'),
(115, 'Login', 2, 10, 16, 'AM', '9', 'NOV', '2015'),
(116, 'Login', 2, 10, 22, 'AM', '9', 'NOV', '2015'),
(117, 'Login', 2, 10, 24, 'AM', '9', 'NOV', '2015'),
(118, 'Login', 2, 10, 26, 'AM', '9', 'NOV', '2015'),
(119, 'Logout', 2, 10, 35, 'AM', '9', 'NOV', '2015'),
(120, 'Login', 2, 10, 52, 'AM', '9', 'NOV', '2015'),
(121, 'Logout', 2, 10, 54, 'AM', '9', 'NOV', '2015'),
(122, 'Login', 2, 10, 58, 'AM', '9', 'NOV', '2015'),
(123, 'Login', 2, 11, 0, 'AM', '9', 'NOV', '2015'),
(124, 'Login', 2, 11, 1, 'AM', '9', 'NOV', '2015'),
(125, 'Logout', 2, 11, 5, 'AM', '9', 'NOV', '2015'),
(126, 'Login', 2, 1, 31, 'PM', '9', 'NOV', '2015'),
(127, 'Login', 2, 1, 35, 'PM', '9', 'NOV', '2015'),
(128, 'Login', 2, 1, 46, 'PM', '9', 'NOV', '2015'),
(129, 'Login', 2, 1, 49, 'PM', '9', 'NOV', '2015'),
(130, 'Login', 2, 1, 51, 'PM', '9', 'NOV', '2015'),
(131, 'Logout', 2, 1, 52, 'PM', '9', 'NOV', '2015'),
(132, 'Login', 2, 1, 53, 'PM', '9', 'NOV', '2015'),
(133, 'Login', 2, 2, 19, 'PM', '9', 'NOV', '2015'),
(134, 'Logout', 2, 2, 20, 'PM', '9', 'NOV', '2015'),
(135, 'Login', 2, 2, 23, 'PM', '9', 'NOV', '2015'),
(136, 'Logout', 2, 2, 23, 'PM', '9', 'NOV', '2015'),
(137, 'Login', 2, 3, 54, 'PM', '9', 'NOV', '2015'),
(138, 'Logout', 2, 3, 55, 'PM', '9', 'NOV', '2015'),
(139, 'Login', 2, 3, 56, 'PM', '9', 'NOV', '2015'),
(140, 'Logout', 2, 4, 0, 'PM', '9', 'NOV', '2015'),
(141, 'Login', 2, 4, 2, 'PM', '9', 'NOV', '2015'),
(142, 'Login', 2, 4, 4, 'PM', '9', 'NOV', '2015'),
(143, 'Logout', 2, 4, 7, 'PM', '9', 'NOV', '2015'),
(144, 'Login', 2, 4, 28, 'PM', '9', 'NOV', '2015'),
(145, 'Logout', 2, 4, 29, 'PM', '9', 'NOV', '2015'),
(146, 'Login', 2, 8, 35, 'AM', '10', 'NOV', '2015'),
(147, 'Login', 2, 8, 36, 'AM', '10', 'NOV', '2015'),
(148, 'Login', 2, 8, 38, 'AM', '10', 'NOV', '2015'),
(149, 'Logout', 2, 8, 45, 'AM', '10', 'NOV', '2015'),
(150, 'Login', 2, 9, 10, 'AM', '10', 'NOV', '2015'),
(151, 'Login', 2, 9, 17, 'AM', '10', 'NOV', '2015'),
(152, 'Login', 2, 9, 19, 'AM', '10', 'NOV', '2015'),
(153, 'Login', 2, 10, 29, 'AM', '10', 'NOV', '2015'),
(154, 'Login', 2, 10, 39, 'AM', '10', 'NOV', '2015'),
(155, 'Logout', 2, 10, 40, 'AM', '10', 'NOV', '2015'),
(156, 'Login', 2, 10, 41, 'AM', '10', 'NOV', '2015'),
(157, 'Logout', 2, 10, 42, 'AM', '10', 'NOV', '2015'),
(158, 'Login', 2, 12, 9, 'PM', '10', 'NOV', '2015'),
(159, 'Login', 2, 12, 11, 'PM', '10', 'NOV', '2015'),
(160, 'Logout', 2, 12, 12, 'PM', '10', 'NOV', '2015'),
(161, 'Login', 2, 12, 13, 'PM', '10', 'NOV', '2015'),
(162, 'Login', 2, 12, 14, 'PM', '10', 'NOV', '2015'),
(163, 'Logout', 2, 12, 15, 'PM', '10', 'NOV', '2015'),
(164, 'Login', 2, 12, 16, 'PM', '10', 'NOV', '2015'),
(165, 'Logout', 2, 12, 17, 'PM', '10', 'NOV', '2015'),
(166, 'Login', 2, 12, 20, 'PM', '10', 'NOV', '2015'),
(167, 'Login', 2, 12, 28, 'PM', '10', 'NOV', '2015'),
(168, 'Login', 2, 12, 31, 'PM', '10', 'NOV', '2015'),
(169, 'Login', 2, 12, 35, 'PM', '10', 'NOV', '2015'),
(170, 'Logout', 2, 12, 36, 'PM', '10', 'NOV', '2015'),
(171, 'Login', 2, 12, 42, 'PM', '10', 'NOV', '2015'),
(172, 'Login', 2, 12, 46, 'PM', '10', 'NOV', '2015'),
(173, 'Login', 2, 12, 57, 'PM', '10', 'NOV', '2015'),
(174, 'Logout', 2, 12, 59, 'PM', '10', 'NOV', '2015'),
(175, 'Login', 2, 1, 1, 'PM', '10', 'NOV', '2015'),
(176, 'Logout', 2, 1, 3, 'PM', '10', 'NOV', '2015'),
(177, 'Login', 2, 1, 5, 'PM', '10', 'NOV', '2015'),
(178, 'Logout', 2, 1, 16, 'PM', '10', 'NOV', '2015'),
(179, 'Login', 2, 2, 36, 'PM', '10', 'NOV', '2015'),
(180, 'Login', 2, 2, 38, 'PM', '10', 'NOV', '2015'),
(181, 'Login', 2, 2, 40, 'PM', '10', 'NOV', '2015'),
(182, 'Login', 2, 3, 4, 'PM', '10', 'NOV', '2015'),
(183, 'Logout', 2, 3, 5, 'PM', '10', 'NOV', '2015'),
(184, 'Login', 2, 3, 13, 'PM', '10', 'NOV', '2015'),
(185, 'Logout', 2, 3, 13, 'PM', '10', 'NOV', '2015'),
(186, 'Login', 2, 3, 14, 'PM', '10', 'NOV', '2015'),
(187, 'Login', 2, 3, 16, 'PM', '10', 'NOV', '2015'),
(188, 'Logout', 2, 3, 17, 'PM', '10', 'NOV', '2015'),
(189, 'Login', 2, 4, 0, 'PM', '10', 'NOV', '2015'),
(190, 'Logout', 2, 4, 0, 'PM', '10', 'NOV', '2015'),
(191, 'Login', 2, 4, 23, 'PM', '10', 'NOV', '2015'),
(192, 'Logout', 2, 4, 27, 'PM', '10', 'NOV', '2015'),
(193, 'Login', 2, 7, 24, 'AM', '11', 'NOV', '2015'),
(194, 'Logout', 2, 7, 24, 'AM', '11', 'NOV', '2015'),
(195, 'Login', 2, 7, 37, 'AM', '11', 'NOV', '2015'),
(196, 'Login', 2, 7, 41, 'AM', '11', 'NOV', '2015'),
(197, 'Login', 2, 7, 43, 'AM', '11', 'NOV', '2015'),
(198, 'Login', 2, 7, 44, 'AM', '11', 'NOV', '2015'),
(199, 'Login', 2, 7, 53, 'AM', '11', 'NOV', '2015'),
(200, 'Login', 2, 7, 53, 'AM', '11', 'NOV', '2015'),
(201, 'Login', 2, 7, 54, 'AM', '11', 'NOV', '2015'),
(202, 'Login', 2, 7, 58, 'AM', '11', 'NOV', '2015'),
(203, 'Login', 2, 8, 3, 'AM', '11', 'NOV', '2015'),
(204, 'Login', 2, 8, 5, 'AM', '11', 'NOV', '2015'),
(205, 'Logout', 2, 8, 6, 'AM', '11', 'NOV', '2015'),
(206, 'Login', 2, 8, 7, 'AM', '11', 'NOV', '2015'),
(207, 'Login', 2, 1, 20, 'PM', '11', 'NOV', '2015'),
(208, 'Login', 2, 1, 25, 'PM', '11', 'NOV', '2015'),
(209, 'Login', 2, 1, 32, 'PM', '11', 'NOV', '2015'),
(210, 'Login', 2, 4, 18, 'PM', '13', 'NOV', '2015'),
(211, 'Login', 2, 4, 19, 'PM', '13', 'NOV', '2015'),
(212, 'Login', 2, 6, 43, 'AM', '14', 'NOV', '2015'),
(213, 'Login', 2, 6, 59, 'AM', '14', 'NOV', '2015'),
(214, 'Login', 2, 7, 2, 'AM', '14', 'NOV', '2015'),
(215, 'Login', 2, 7, 16, 'AM', '14', 'NOV', '2015'),
(216, 'Logout', 2, 7, 16, 'AM', '14', 'NOV', '2015'),
(217, 'Login', 2, 7, 30, 'AM', '14', 'NOV', '2015'),
(218, 'Login', 2, 7, 31, 'AM', '14', 'NOV', '2015'),
(219, 'Login', 2, 7, 33, 'AM', '14', 'NOV', '2015'),
(220, 'Login', 2, 7, 34, 'AM', '14', 'NOV', '2015'),
(221, 'Login', 2, 7, 35, 'AM', '14', 'NOV', '2015'),
(222, 'Login', 2, 7, 35, 'AM', '14', 'NOV', '2015'),
(223, 'Login', 2, 7, 37, 'AM', '14', 'NOV', '2015'),
(224, 'Login', 2, 7, 38, 'AM', '14', 'NOV', '2015'),
(225, 'Login', 2, 7, 39, 'AM', '14', 'NOV', '2015'),
(226, 'Login', 2, 7, 41, 'AM', '14', 'NOV', '2015'),
(227, 'Login', 2, 7, 43, 'AM', '14', 'NOV', '2015'),
(228, 'Login', 2, 7, 46, 'AM', '14', 'NOV', '2015'),
(229, 'Logout', 2, 7, 49, 'AM', '14', 'NOV', '2015'),
(230, 'Login', 2, 7, 54, 'AM', '14', 'NOV', '2015'),
(231, 'Login', 2, 7, 56, 'AM', '14', 'NOV', '2015'),
(232, 'Login', 2, 8, 0, 'AM', '14', 'NOV', '2015'),
(233, 'Login', 2, 8, 11, 'AM', '14', 'NOV', '2015'),
(234, 'Login', 2, 11, 5, 'AM', '14', 'NOV', '2015'),
(235, 'Login', 2, 11, 23, 'AM', '14', 'NOV', '2015'),
(236, 'Login', 2, 11, 24, 'AM', '14', 'NOV', '2015'),
(237, 'Login', 2, 11, 27, 'AM', '14', 'NOV', '2015'),
(238, 'Logout', 2, 11, 28, 'AM', '14', 'NOV', '2015'),
(239, 'Login', 2, 11, 31, 'AM', '14', 'NOV', '2015'),
(240, 'Logout', 2, 11, 42, 'AM', '14', 'NOV', '2015'),
(241, 'Login', 2, 11, 44, 'AM', '14', 'NOV', '2015'),
(242, 'Logout', 2, 11, 46, 'AM', '14', 'NOV', '2015'),
(243, 'Login', 2, 8, 34, 'AM', '16', 'NOV', '2015'),
(244, 'Login', 2, 8, 35, 'AM', '16', 'NOV', '2015'),
(245, 'Login', 2, 8, 37, 'AM', '16', 'NOV', '2015'),
(246, 'Logout', 2, 8, 38, 'AM', '16', 'NOV', '2015'),
(247, 'Login', 2, 8, 40, 'AM', '16', 'NOV', '2015'),
(248, 'Logout', 2, 8, 46, 'AM', '16', 'NOV', '2015'),
(249, 'Login', 2, 10, 33, 'AM', '16', 'NOV', '2015'),
(250, 'Logout', 2, 10, 38, 'AM', '16', 'NOV', '2015'),
(251, 'Login', 2, 4, 6, 'PM', '16', 'NOV', '2015'),
(252, 'Logout', 2, 4, 7, 'PM', '16', 'NOV', '2015'),
(253, 'Login', 2, 4, 7, 'PM', '16', 'NOV', '2015'),
(254, 'Logout', 2, 4, 9, 'PM', '16', 'NOV', '2015'),
(255, 'Login', 2, 4, 15, 'PM', '16', 'NOV', '2015'),
(256, 'Logout', 2, 4, 16, 'PM', '16', 'NOV', '2015'),
(257, 'Login', 2, 3, 46, 'PM', '18', 'NOV', '2015'),
(258, 'Logout', 2, 3, 47, 'PM', '18', 'NOV', '2015'),
(259, 'Login', 2, 3, 52, 'PM', '18', 'NOV', '2015'),
(260, 'Logout', 2, 3, 53, 'PM', '18', 'NOV', '2015'),
(261, 'Login', 2, 3, 53, 'PM', '18', 'NOV', '2015'),
(262, 'Logout', 2, 3, 54, 'PM', '18', 'NOV', '2015'),
(263, 'Login', 2, 3, 55, 'PM', '18', 'NOV', '2015'),
(264, 'Logout', 2, 3, 56, 'PM', '18', 'NOV', '2015'),
(265, 'Login', 2, 3, 57, 'PM', '18', 'NOV', '2015'),
(266, 'Logout', 2, 3, 57, 'PM', '18', 'NOV', '2015'),
(267, 'Login', 2, 3, 57, 'PM', '18', 'NOV', '2015'),
(268, 'Logout', 2, 3, 58, 'PM', '18', 'NOV', '2015'),
(269, 'Login', 2, 3, 59, 'PM', '18', 'NOV', '2015'),
(270, 'Logout', 2, 4, 0, 'PM', '18', 'NOV', '2015'),
(271, 'Login', 2, 4, 17, 'PM', '18', 'NOV', '2015'),
(272, 'Logout', 2, 4, 17, 'PM', '18', 'NOV', '2015'),
(273, 'Login', 2, 7, 10, 'PM', '19', 'NOV', '2015'),
(274, 'Login', 2, 3, 24, 'PM', '21', 'NOV', '2015'),
(275, 'Logout', 2, 3, 25, 'PM', '21', 'NOV', '2015'),
(276, 'Login', 2, 3, 26, 'PM', '21', 'NOV', '2015'),
(277, 'Logout', 2, 3, 27, 'PM', '21', 'NOV', '2015'),
(278, 'Login', 2, 6, 36, 'PM', '21', 'NOV', '2015'),
(279, 'Login', 2, 6, 38, 'PM', '21', 'NOV', '2015'),
(280, 'Logout', 2, 6, 39, 'PM', '21', 'NOV', '2015'),
(281, 'Login', 2, 6, 40, 'PM', '21', 'NOV', '2015'),
(282, 'Logout', 2, 6, 40, 'PM', '21', 'NOV', '2015'),
(283, 'Login', 2, 6, 41, 'PM', '21', 'NOV', '2015'),
(284, 'Logout', 2, 6, 41, 'PM', '21', 'NOV', '2015'),
(285, 'Login', 2, 6, 42, 'PM', '21', 'NOV', '2015'),
(286, 'Logout', 2, 6, 42, 'PM', '21', 'NOV', '2015'),
(287, 'Login', 2, 8, 41, 'AM', '25', 'NOV', '2015'),
(288, 'Logout', 2, 8, 59, 'AM', '25', 'NOV', '2015'),
(289, 'Login', 2, 9, 2, 'AM', '25', 'NOV', '2015'),
(290, 'Logout', 2, 9, 3, 'AM', '25', 'NOV', '2015'),
(291, 'Login', 2, 9, 5, 'AM', '25', 'NOV', '2015'),
(292, 'Logout', 2, 9, 5, 'AM', '25', 'NOV', '2015'),
(293, 'Login', 2, 9, 6, 'AM', '25', 'NOV', '2015'),
(294, 'Login', 2, 9, 7, 'AM', '25', 'NOV', '2015'),
(295, 'Logout', 2, 9, 8, 'AM', '25', 'NOV', '2015'),
(296, 'Login', 2, 9, 9, 'AM', '25', 'NOV', '2015'),
(297, 'Logout', 2, 9, 9, 'AM', '25', 'NOV', '2015'),
(298, 'Login', 2, 9, 11, 'AM', '25', 'NOV', '2015'),
(299, 'Logout', 2, 9, 12, 'AM', '25', 'NOV', '2015'),
(300, 'Login', 2, 1, 34, 'PM', '25', 'NOV', '2015'),
(301, 'Logout', 2, 1, 34, 'PM', '25', 'NOV', '2015'),
(302, 'Login', 2, 1, 35, 'PM', '25', 'NOV', '2015'),
(303, 'Logout', 2, 1, 35, 'PM', '25', 'NOV', '2015'),
(304, 'Login', 2, 1, 36, 'PM', '25', 'NOV', '2015'),
(305, 'Logout', 2, 1, 36, 'PM', '25', 'NOV', '2015'),
(306, 'Login', 2, 1, 37, 'PM', '25', 'NOV', '2015'),
(307, 'Logout', 2, 1, 37, 'PM', '25', 'NOV', '2015'),
(308, 'Login', 2, 1, 41, 'PM', '25', 'NOV', '2015'),
(309, 'Logout', 2, 1, 42, 'PM', '25', 'NOV', '2015'),
(310, 'Login', 2, 1, 43, 'PM', '25', 'NOV', '2015'),
(311, 'Logout', 2, 1, 45, 'PM', '25', 'NOV', '2015'),
(312, 'Login', 2, 1, 46, 'PM', '25', 'NOV', '2015'),
(313, 'Logout', 2, 1, 46, 'PM', '25', 'NOV', '2015'),
(314, 'Login', 2, 3, 53, 'PM', '25', 'NOV', '2015'),
(315, 'Login', 2, 3, 54, 'PM', '25', 'NOV', '2015'),
(316, 'Logout', 2, 3, 55, 'PM', '25', 'NOV', '2015'),
(317, 'Login', 2, 4, 5, 'PM', '25', 'NOV', '2015'),
(318, 'Logout', 2, 4, 6, 'PM', '25', 'NOV', '2015'),
(319, 'Login', 2, 4, 9, 'PM', '25', 'NOV', '2015'),
(320, 'Logout', 2, 4, 9, 'PM', '25', 'NOV', '2015'),
(321, 'Login', 2, 4, 10, 'PM', '25', 'NOV', '2015'),
(322, 'Logout', 2, 4, 11, 'PM', '25', 'NOV', '2015'),
(323, 'Login', 2, 4, 14, 'PM', '25', 'NOV', '2015'),
(324, 'Logout', 2, 4, 14, 'PM', '25', 'NOV', '2015'),
(325, 'Login', 2, 4, 15, 'PM', '25', 'NOV', '2015'),
(326, 'Logout', 2, 4, 15, 'PM', '25', 'NOV', '2015'),
(327, 'Login', 2, 4, 16, 'PM', '25', 'NOV', '2015'),
(328, 'Logout', 2, 4, 17, 'PM', '25', 'NOV', '2015'),
(329, 'Login', 2, 6, 58, 'AM', '26', 'NOV', '2015'),
(330, 'Logout', 2, 6, 59, 'AM', '26', 'NOV', '2015'),
(331, 'Login', 2, 7, 2, 'AM', '26', 'NOV', '2015'),
(332, 'Logout', 2, 7, 3, 'AM', '26', 'NOV', '2015'),
(333, 'Login', 2, 7, 3, 'AM', '26', 'NOV', '2015'),
(334, 'Logout', 2, 7, 4, 'AM', '26', 'NOV', '2015'),
(335, 'Login', 2, 7, 4, 'AM', '26', 'NOV', '2015'),
(336, 'Login', 2, 7, 5, 'AM', '26', 'NOV', '2015'),
(337, 'Login', 2, 7, 8, 'AM', '26', 'NOV', '2015'),
(338, 'Logout', 2, 7, 9, 'AM', '26', 'NOV', '2015'),
(339, 'Login', 2, 7, 13, 'AM', '26', 'NOV', '2015'),
(340, 'Logout', 2, 7, 14, 'AM', '26', 'NOV', '2015'),
(341, 'Login', 2, 7, 17, 'AM', '26', 'NOV', '2015'),
(342, 'Logout', 2, 7, 18, 'AM', '26', 'NOV', '2015'),
(343, 'Login', 2, 7, 19, 'AM', '26', 'NOV', '2015'),
(344, 'Logout', 2, 7, 19, 'AM', '26', 'NOV', '2015'),
(345, 'Login', 2, 7, 20, 'AM', '26', 'NOV', '2015'),
(346, 'Login', 2, 7, 21, 'AM', '26', 'NOV', '2015'),
(347, 'Logout', 2, 7, 22, 'AM', '26', 'NOV', '2015'),
(348, 'Login', 2, 7, 23, 'AM', '26', 'NOV', '2015'),
(349, 'Logout', 2, 7, 28, 'AM', '26', 'NOV', '2015'),
(350, 'Login', 2, 7, 28, 'AM', '26', 'NOV', '2015'),
(351, 'Logout', 2, 7, 29, 'AM', '26', 'NOV', '2015'),
(352, 'Login', 2, 7, 32, 'AM', '26', 'NOV', '2015'),
(353, 'Logout', 2, 7, 32, 'AM', '26', 'NOV', '2015'),
(354, 'Login', 2, 7, 33, 'AM', '26', 'NOV', '2015'),
(355, 'Logout', 2, 7, 33, 'AM', '26', 'NOV', '2015'),
(356, 'Login', 2, 7, 39, 'AM', '26', 'NOV', '2015'),
(357, 'Logout', 2, 7, 40, 'AM', '26', 'NOV', '2015'),
(358, 'Login', 2, 7, 44, 'AM', '26', 'NOV', '2015'),
(359, 'Logout', 2, 7, 44, 'AM', '26', 'NOV', '2015'),
(360, 'Login', 2, 7, 45, 'AM', '26', 'NOV', '2015'),
(361, 'Logout', 2, 7, 47, 'AM', '26', 'NOV', '2015'),
(362, 'Login', 2, 7, 50, 'AM', '26', 'NOV', '2015'),
(363, 'Login', 2, 7, 51, 'AM', '26', 'NOV', '2015'),
(364, 'Logout', 2, 7, 52, 'AM', '26', 'NOV', '2015'),
(365, 'Login', 2, 7, 54, 'AM', '26', 'NOV', '2015'),
(366, 'Logout', 2, 7, 54, 'AM', '26', 'NOV', '2015'),
(367, 'Login', 2, 7, 57, 'AM', '26', 'NOV', '2015'),
(368, 'Logout', 2, 7, 58, 'AM', '26', 'NOV', '2015'),
(369, 'Login', 2, 7, 59, 'AM', '26', 'NOV', '2015'),
(370, 'Logout', 2, 8, 0, 'AM', '26', 'NOV', '2015'),
(371, 'Login', 2, 8, 4, 'AM', '26', 'NOV', '2015'),
(372, 'Logout', 2, 8, 5, 'AM', '26', 'NOV', '2015'),
(373, 'Login', 2, 8, 7, 'AM', '26', 'NOV', '2015'),
(374, 'Logout', 2, 8, 7, 'AM', '26', 'NOV', '2015'),
(375, 'Login', 2, 8, 8, 'AM', '26', 'NOV', '2015'),
(376, 'Logout', 2, 8, 8, 'AM', '26', 'NOV', '2015'),
(377, 'Login', 2, 8, 8, 'AM', '26', 'NOV', '2015'),
(378, 'Logout', 2, 8, 9, 'AM', '26', 'NOV', '2015'),
(379, 'Login', 2, 8, 9, 'AM', '26', 'NOV', '2015'),
(380, 'Logout', 2, 8, 11, 'AM', '26', 'NOV', '2015'),
(381, 'Login', 2, 8, 15, 'AM', '26', 'NOV', '2015'),
(382, 'Login', 2, 8, 19, 'AM', '26', 'NOV', '2015'),
(383, 'Logout', 2, 8, 20, 'AM', '26', 'NOV', '2015'),
(384, 'Login', 2, 8, 23, 'AM', '26', 'NOV', '2015'),
(385, 'Logout', 2, 8, 24, 'AM', '26', 'NOV', '2015'),
(386, 'Login', 2, 8, 24, 'AM', '26', 'NOV', '2015'),
(387, 'Logout', 2, 8, 26, 'AM', '26', 'NOV', '2015'),
(388, 'Login', 2, 9, 31, 'AM', '26', 'NOV', '2015'),
(389, 'Logout', 2, 9, 32, 'AM', '26', 'NOV', '2015'),
(390, 'Login', 2, 9, 43, 'AM', '26', 'NOV', '2015'),
(391, 'Logout', 2, 9, 47, 'AM', '26', 'NOV', '2015'),
(392, 'Login', 2, 9, 48, 'AM', '26', 'NOV', '2015'),
(393, 'Login', 2, 9, 50, 'AM', '26', 'NOV', '2015'),
(394, 'Logout', 2, 9, 50, 'AM', '26', 'NOV', '2015'),
(395, 'Login', 2, 9, 53, 'AM', '26', 'NOV', '2015'),
(396, 'Logout', 2, 9, 53, 'AM', '26', 'NOV', '2015'),
(397, 'Login', 2, 9, 54, 'AM', '26', 'NOV', '2015'),
(398, 'Logout', 2, 9, 54, 'AM', '26', 'NOV', '2015'),
(399, 'Login', 2, 9, 55, 'AM', '26', 'NOV', '2015'),
(400, 'Login', 2, 9, 55, 'AM', '26', 'NOV', '2015'),
(401, 'Logout', 2, 9, 55, 'AM', '26', 'NOV', '2015'),
(402, 'Login', 2, 9, 56, 'AM', '26', 'NOV', '2015'),
(403, 'Logout', 2, 9, 56, 'AM', '26', 'NOV', '2015'),
(404, 'Login', 2, 9, 57, 'AM', '26', 'NOV', '2015'),
(405, 'Logout', 2, 9, 57, 'AM', '26', 'NOV', '2015'),
(406, 'Login', 2, 9, 59, 'AM', '26', 'NOV', '2015'),
(407, 'Logout', 2, 9, 59, 'AM', '26', 'NOV', '2015'),
(408, 'Login', 2, 10, 0, 'AM', '26', 'NOV', '2015'),
(409, 'Login', 2, 10, 0, 'AM', '26', 'NOV', '2015'),
(410, 'Login', 2, 10, 1, 'AM', '26', 'NOV', '2015'),
(411, 'Logout', 2, 10, 1, 'AM', '26', 'NOV', '2015'),
(412, 'Login', 2, 10, 1, 'AM', '26', 'NOV', '2015'),
(413, 'Logout', 2, 10, 2, 'AM', '26', 'NOV', '2015'),
(414, 'Login', 2, 10, 5, 'AM', '26', 'NOV', '2015'),
(415, 'Logout', 2, 10, 6, 'AM', '26', 'NOV', '2015'),
(416, 'Login', 2, 10, 22, 'AM', '26', 'NOV', '2015'),
(417, 'Login', 2, 10, 24, 'AM', '26', 'NOV', '2015'),
(418, 'Logout', 2, 10, 24, 'AM', '26', 'NOV', '2015'),
(419, 'Login', 2, 10, 33, 'AM', '26', 'NOV', '2015'),
(420, 'Logout', 2, 10, 33, 'AM', '26', 'NOV', '2015'),
(421, 'Login', 2, 10, 34, 'AM', '26', 'NOV', '2015'),
(422, 'Logout', 2, 10, 35, 'AM', '26', 'NOV', '2015'),
(423, 'Login', 2, 10, 36, 'AM', '26', 'NOV', '2015'),
(424, 'Logout', 2, 10, 37, 'AM', '26', 'NOV', '2015'),
(425, 'Login', 2, 10, 48, 'AM', '26', 'NOV', '2015'),
(426, 'Login', 2, 10, 49, 'AM', '26', 'NOV', '2015'),
(427, 'Login', 2, 10, 51, 'AM', '26', 'NOV', '2015'),
(428, 'Logout', 2, 10, 52, 'AM', '26', 'NOV', '2015'),
(429, 'Login', 2, 10, 58, 'AM', '26', 'NOV', '2015'),
(430, 'Login', 2, 11, 0, 'AM', '26', 'NOV', '2015'),
(431, 'Login', 2, 11, 5, 'AM', '26', 'NOV', '2015'),
(432, 'Logout', 2, 11, 5, 'AM', '26', 'NOV', '2015'),
(433, 'Login', 2, 11, 7, 'AM', '26', 'NOV', '2015'),
(434, 'Logout', 2, 11, 9, 'AM', '26', 'NOV', '2015'),
(435, 'Login', 2, 11, 16, 'AM', '26', 'NOV', '2015'),
(436, 'Logout', 2, 11, 16, 'AM', '26', 'NOV', '2015'),
(437, 'Login', 2, 11, 19, 'AM', '26', 'NOV', '2015'),
(438, 'Logout', 2, 11, 20, 'AM', '26', 'NOV', '2015'),
(439, 'Login', 2, 11, 22, 'AM', '26', 'NOV', '2015'),
(440, 'Logout', 2, 11, 22, 'AM', '26', 'NOV', '2015'),
(441, 'Login', 2, 11, 23, 'AM', '26', 'NOV', '2015'),
(442, 'Login', 2, 11, 24, 'AM', '26', 'NOV', '2015'),
(443, 'Logout', 2, 11, 25, 'AM', '26', 'NOV', '2015'),
(444, 'Login', 2, 11, 25, 'AM', '26', 'NOV', '2015'),
(445, 'Login', 2, 11, 27, 'AM', '26', 'NOV', '2015'),
(446, 'Logout', 2, 11, 27, 'AM', '26', 'NOV', '2015'),
(447, 'Login', 2, 11, 34, 'AM', '26', 'NOV', '2015'),
(448, 'Logout', 2, 11, 34, 'AM', '26', 'NOV', '2015'),
(449, 'Login', 2, 11, 43, 'AM', '26', 'NOV', '2015'),
(450, 'Login', 2, 11, 44, 'AM', '26', 'NOV', '2015'),
(451, 'Login', 2, 11, 46, 'AM', '26', 'NOV', '2015'),
(452, 'Login', 2, 11, 46, 'AM', '26', 'NOV', '2015'),
(453, 'Login', 2, 11, 47, 'AM', '26', 'NOV', '2015'),
(454, 'Login', 2, 11, 52, 'AM', '26', 'NOV', '2015'),
(455, 'Logout', 2, 11, 52, 'AM', '26', 'NOV', '2015'),
(456, 'Login', 2, 11, 57, 'AM', '26', 'NOV', '2015'),
(457, 'Login', 2, 11, 58, 'AM', '26', 'NOV', '2015'),
(458, 'Login', 2, 12, 0, 'PM', '26', 'NOV', '2015'),
(459, 'Logout', 2, 12, 0, 'PM', '26', 'NOV', '2015'),
(460, 'Login', 2, 12, 6, 'PM', '26', 'NOV', '2015'),
(461, 'Logout', 2, 12, 7, 'PM', '26', 'NOV', '2015'),
(462, 'Login', 2, 12, 8, 'PM', '26', 'NOV', '2015'),
(463, 'Login', 2, 12, 11, 'PM', '26', 'NOV', '2015'),
(464, 'Logout', 2, 12, 12, 'PM', '26', 'NOV', '2015'),
(465, 'Login', 2, 12, 13, 'PM', '26', 'NOV', '2015'),
(466, 'Logout', 2, 12, 14, 'PM', '26', 'NOV', '2015'),
(467, 'Login', 2, 12, 26, 'PM', '26', 'NOV', '2015'),
(468, 'Login', 2, 12, 27, 'PM', '26', 'NOV', '2015'),
(469, 'Logout', 2, 12, 28, 'PM', '26', 'NOV', '2015'),
(470, 'Login', 2, 12, 30, 'PM', '26', 'NOV', '2015'),
(471, 'Logout', 2, 12, 31, 'PM', '26', 'NOV', '2015'),
(472, 'Login', 2, 12, 32, 'PM', '26', 'NOV', '2015'),
(473, 'Login', 2, 12, 33, 'PM', '26', 'NOV', '2015'),
(474, 'Login', 2, 12, 35, 'PM', '26', 'NOV', '2015'),
(475, 'Login', 2, 12, 37, 'PM', '26', 'NOV', '2015'),
(476, 'Login', 2, 12, 39, 'PM', '26', 'NOV', '2015'),
(477, 'Login', 2, 12, 40, 'PM', '26', 'NOV', '2015'),
(478, 'Logout', 2, 12, 41, 'PM', '26', 'NOV', '2015'),
(479, 'Login', 2, 12, 41, 'PM', '26', 'NOV', '2015'),
(480, 'Login', 2, 12, 58, 'PM', '26', 'NOV', '2015'),
(481, 'Logout', 2, 12, 59, 'PM', '26', 'NOV', '2015'),
(482, 'Login', 2, 1, 1, 'PM', '26', 'NOV', '2015'),
(483, 'Logout', 2, 1, 2, 'PM', '26', 'NOV', '2015'),
(484, 'Login', 2, 1, 6, 'PM', '26', 'NOV', '2015'),
(485, 'Login', 2, 1, 9, 'PM', '26', 'NOV', '2015'),
(486, 'Logout', 2, 1, 10, 'PM', '26', 'NOV', '2015'),
(487, 'Login', 2, 1, 12, 'PM', '26', 'NOV', '2015'),
(488, 'Login', 2, 1, 13, 'PM', '26', 'NOV', '2015'),
(489, 'Logout', 2, 1, 13, 'PM', '26', 'NOV', '2015'),
(490, 'Login', 2, 1, 14, 'PM', '26', 'NOV', '2015'),
(491, 'Logout', 2, 1, 15, 'PM', '26', 'NOV', '2015'),
(492, 'Login', 2, 1, 21, 'PM', '26', 'NOV', '2015'),
(493, 'Logout', 2, 1, 22, 'PM', '26', 'NOV', '2015'),
(494, 'Login', 2, 1, 22, 'PM', '26', 'NOV', '2015'),
(495, 'Logout', 2, 1, 23, 'PM', '26', 'NOV', '2015'),
(496, 'Login', 2, 1, 26, 'PM', '26', 'NOV', '2015'),
(497, 'Logout', 2, 1, 27, 'PM', '26', 'NOV', '2015'),
(498, 'Login', 2, 1, 33, 'PM', '26', 'NOV', '2015'),
(499, 'Logout', 2, 1, 34, 'PM', '26', 'NOV', '2015'),
(500, 'Login', 2, 1, 35, 'PM', '26', 'NOV', '2015'),
(501, 'Logout', 2, 1, 38, 'PM', '26', 'NOV', '2015'),
(502, 'Login', 2, 1, 41, 'PM', '26', 'NOV', '2015'),
(503, 'Login', 2, 1, 42, 'PM', '26', 'NOV', '2015'),
(504, 'Logout', 2, 1, 44, 'PM', '26', 'NOV', '2015'),
(505, 'Login', 2, 1, 45, 'PM', '26', 'NOV', '2015'),
(506, 'Logout', 2, 1, 47, 'PM', '26', 'NOV', '2015'),
(507, 'Login', 2, 1, 54, 'PM', '26', 'NOV', '2015'),
(508, 'Logout', 2, 1, 55, 'PM', '26', 'NOV', '2015'),
(509, 'Login', 2, 1, 55, 'PM', '26', 'NOV', '2015'),
(510, 'Logout', 2, 1, 56, 'PM', '26', 'NOV', '2015'),
(511, 'Login', 2, 1, 57, 'PM', '26', 'NOV', '2015'),
(512, 'Logout', 2, 1, 59, 'PM', '26', 'NOV', '2015'),
(513, 'Login', 2, 3, 34, 'PM', '26', 'NOV', '2015'),
(514, 'Login', 2, 3, 46, 'PM', '26', 'NOV', '2015'),
(515, 'Logout', 2, 3, 47, 'PM', '26', 'NOV', '2015'),
(516, 'Login', 2, 3, 47, 'PM', '26', 'NOV', '2015'),
(517, 'Logout', 2, 3, 48, 'PM', '26', 'NOV', '2015'),
(518, 'Login', 2, 3, 49, 'PM', '26', 'NOV', '2015'),
(519, 'Logout', 2, 3, 49, 'PM', '26', 'NOV', '2015'),
(520, 'Login', 2, 3, 51, 'PM', '26', 'NOV', '2015'),
(521, 'Logout', 2, 3, 51, 'PM', '26', 'NOV', '2015'),
(522, 'Login', 2, 3, 54, 'PM', '26', 'NOV', '2015'),
(523, 'Logout', 2, 3, 54, 'PM', '26', 'NOV', '2015'),
(524, 'Login', 2, 3, 55, 'PM', '26', 'NOV', '2015'),
(525, 'Logout', 2, 3, 56, 'PM', '26', 'NOV', '2015'),
(526, 'Login', 2, 4, 13, 'PM', '26', 'NOV', '2015'),
(527, 'Login', 2, 9, 43, 'AM', '27', 'NOV', '2015'),
(528, 'Login', 2, 9, 45, 'AM', '27', 'NOV', '2015'),
(529, 'Login', 2, 9, 48, 'AM', '27', 'NOV', '2015'),
(530, 'Login', 2, 9, 50, 'AM', '27', 'NOV', '2015'),
(531, 'Login', 2, 9, 58, 'AM', '27', 'NOV', '2015'),
(532, 'Logout', 2, 9, 59, 'AM', '27', 'NOV', '2015'),
(533, 'Login', 2, 9, 59, 'AM', '27', 'NOV', '2015'),
(534, 'Login', 2, 10, 1, 'AM', '27', 'NOV', '2015'),
(535, 'Login', 2, 10, 2, 'AM', '27', 'NOV', '2015'),
(536, 'Login', 2, 10, 5, 'AM', '27', 'NOV', '2015'),
(537, 'Login', 2, 10, 8, 'AM', '27', 'NOV', '2015'),
(538, 'Login', 2, 10, 9, 'AM', '27', 'NOV', '2015'),
(539, 'Login', 2, 10, 10, 'AM', '27', 'NOV', '2015'),
(540, 'Login', 2, 10, 12, 'AM', '27', 'NOV', '2015'),
(541, 'Logout', 2, 10, 14, 'AM', '27', 'NOV', '2015'),
(542, 'Login', 2, 10, 15, 'AM', '27', 'NOV', '2015'),
(543, 'Logout', 2, 10, 16, 'AM', '27', 'NOV', '2015'),
(544, 'Login', 2, 10, 17, 'AM', '27', 'NOV', '2015'),
(545, 'Logout', 2, 10, 18, 'AM', '27', 'NOV', '2015'),
(546, 'Login', 2, 10, 24, 'AM', '27', 'NOV', '2015'),
(547, 'Logout', 2, 10, 25, 'AM', '27', 'NOV', '2015'),
(548, 'Login', 2, 10, 27, 'AM', '27', 'NOV', '2015'),
(549, 'Logout', 2, 10, 27, 'AM', '27', 'NOV', '2015'),
(550, 'Login', 2, 10, 29, 'AM', '27', 'NOV', '2015'),
(551, 'Logout', 2, 10, 30, 'AM', '27', 'NOV', '2015'),
(552, 'Login', 2, 10, 34, 'AM', '27', 'NOV', '2015'),
(553, 'Login', 2, 10, 35, 'AM', '27', 'NOV', '2015'),
(554, 'Logout', 2, 10, 36, 'AM', '27', 'NOV', '2015'),
(555, 'Login', 2, 1, 59, 'PM', '27', 'NOV', '2015'),
(556, 'Logout', 2, 2, 0, 'PM', '27', 'NOV', '2015'),
(557, 'Login', 2, 2, 1, 'PM', '27', 'NOV', '2015'),
(558, 'Login', 2, 2, 2, 'PM', '27', 'NOV', '2015'),
(559, 'Login', 2, 2, 2, 'PM', '27', 'NOV', '2015'),
(560, 'Logout', 2, 2, 3, 'PM', '27', 'NOV', '2015'),
(561, 'Login', 2, 2, 13, 'PM', '27', 'NOV', '2015'),
(562, 'Login', 2, 3, 14, 'PM', '27', 'NOV', '2015'),
(563, 'Logout', 2, 3, 15, 'PM', '27', 'NOV', '2015'),
(564, 'Login', 2, 3, 23, 'PM', '27', 'NOV', '2015'),
(565, 'Login', 2, 3, 25, 'PM', '27', 'NOV', '2015'),
(566, 'Logout', 2, 3, 26, 'PM', '27', 'NOV', '2015'),
(567, 'Login', 2, 3, 29, 'PM', '27', 'NOV', '2015'),
(568, 'Login', 2, 3, 30, 'PM', '27', 'NOV', '2015'),
(569, 'Logout', 2, 3, 37, 'PM', '27', 'NOV', '2015'),
(570, 'Login', 2, 3, 49, 'PM', '27', 'NOV', '2015'),
(571, 'Logout', 2, 3, 50, 'PM', '27', 'NOV', '2015'),
(572, 'Login', 2, 3, 50, 'PM', '27', 'NOV', '2015'),
(573, 'Logout', 2, 3, 51, 'PM', '27', 'NOV', '2015'),
(574, 'Login', 2, 3, 54, 'PM', '27', 'NOV', '2015'),
(575, 'Logout', 2, 3, 55, 'PM', '27', 'NOV', '2015'),
(576, 'Login', 2, 3, 57, 'PM', '27', 'NOV', '2015'),
(577, 'Logout', 2, 4, 3, 'PM', '27', 'NOV', '2015'),
(578, 'Login', 2, 4, 51, 'PM', '27', 'NOV', '2015'),
(579, 'Logout', 2, 4, 53, 'PM', '27', 'NOV', '2015'),
(580, 'Login', 2, 4, 54, 'PM', '27', 'NOV', '2015'),
(581, 'Login', 2, 5, 13, 'PM', '27', 'NOV', '2015'),
(582, 'Login', 2, 5, 15, 'PM', '27', 'NOV', '2015'),
(583, 'Logout', 2, 5, 16, 'PM', '27', 'NOV', '2015'),
(584, 'Login', 2, 5, 20, 'PM', '27', 'NOV', '2015'),
(585, 'Logout', 2, 5, 21, 'PM', '27', 'NOV', '2015'),
(586, 'Login', 2, 5, 24, 'PM', '27', 'NOV', '2015'),
(587, 'Logout', 2, 5, 25, 'PM', '27', 'NOV', '2015'),
(588, 'Login', 2, 9, 16, 'AM', '1', 'DEC', '2015'),
(589, 'Logout', 2, 9, 17, 'AM', '1', 'DEC', '2015'),
(590, 'Login', 2, 9, 24, 'AM', '1', 'DEC', '2015'),
(591, 'Logout', 2, 9, 27, 'AM', '1', 'DEC', '2015'),
(592, 'Login', 2, 12, 20, 'PM', '1', 'DEC', '2015'),
(593, 'Login', 2, 12, 44, 'PM', '1', 'DEC', '2015'),
(594, 'Logout', 2, 12, 45, 'PM', '1', 'DEC', '2015'),
(595, 'Login', 2, 12, 50, 'PM', '1', 'DEC', '2015'),
(596, 'Logout', 2, 12, 50, 'PM', '1', 'DEC', '2015'),
(597, 'Login', 2, 12, 52, 'PM', '1', 'DEC', '2015'),
(598, 'Logout', 2, 12, 53, 'PM', '1', 'DEC', '2015'),
(599, 'Login', 2, 1, 14, 'PM', '1', 'DEC', '2015'),
(600, 'Login', 2, 1, 15, 'PM', '1', 'DEC', '2015'),
(601, 'Logout', 2, 1, 15, 'PM', '1', 'DEC', '2015'),
(602, 'Login', 2, 1, 16, 'PM', '1', 'DEC', '2015'),
(603, 'Login', 2, 1, 16, 'PM', '1', 'DEC', '2015'),
(604, 'Logout', 2, 1, 17, 'PM', '1', 'DEC', '2015'),
(605, 'Login', 2, 1, 22, 'PM', '1', 'DEC', '2015'),
(606, 'Login', 2, 1, 23, 'PM', '1', 'DEC', '2015'),
(607, 'Login', 2, 1, 25, 'PM', '1', 'DEC', '2015'),
(608, 'Logout', 2, 1, 25, 'PM', '1', 'DEC', '2015'),
(609, 'Login', 2, 1, 27, 'PM', '1', 'DEC', '2015'),
(610, 'Login', 2, 1, 30, 'PM', '1', 'DEC', '2015'),
(611, 'Login', 2, 1, 31, 'PM', '1', 'DEC', '2015'),
(612, 'Login', 2, 1, 33, 'PM', '1', 'DEC', '2015'),
(613, 'Login', 2, 1, 34, 'PM', '1', 'DEC', '2015'),
(614, 'Login', 2, 1, 35, 'PM', '1', 'DEC', '2015'),
(615, 'Logout', 2, 1, 36, 'PM', '1', 'DEC', '2015'),
(616, 'Login', 2, 1, 37, 'PM', '1', 'DEC', '2015'),
(617, 'Login', 2, 1, 41, 'PM', '1', 'DEC', '2015'),
(618, 'Logout', 2, 1, 42, 'PM', '1', 'DEC', '2015'),
(619, 'Login', 2, 1, 44, 'PM', '1', 'DEC', '2015'),
(620, 'Logout', 2, 1, 44, 'PM', '1', 'DEC', '2015'),
(621, 'Login', 2, 1, 46, 'PM', '1', 'DEC', '2015'),
(622, 'Logout', 2, 1, 47, 'PM', '1', 'DEC', '2015'),
(623, 'Login', 2, 1, 47, 'PM', '1', 'DEC', '2015'),
(624, 'Logout', 2, 1, 47, 'PM', '1', 'DEC', '2015'),
(625, 'Login', 2, 1, 47, 'PM', '1', 'DEC', '2015'),
(626, 'Logout', 2, 1, 48, 'PM', '1', 'DEC', '2015'),
(627, 'Login', 2, 1, 56, 'PM', '1', 'DEC', '2015'),
(628, 'Logout', 2, 1, 56, 'PM', '1', 'DEC', '2015'),
(629, 'Login', 2, 9, 26, 'AM', '4', 'DEC', '2015'),
(630, 'Logout', 2, 9, 27, 'AM', '4', 'DEC', '2015'),
(631, 'Login', 2, 9, 42, 'AM', '4', 'DEC', '2015'),
(632, 'Login', 2, 9, 44, 'AM', '4', 'DEC', '2015'),
(633, 'Logout', 2, 9, 46, 'AM', '4', 'DEC', '2015'),
(634, 'Login', 2, 9, 52, 'AM', '4', 'DEC', '2015'),
(635, 'Login', 2, 9, 56, 'AM', '4', 'DEC', '2015'),
(636, 'Logout', 2, 9, 59, 'AM', '4', 'DEC', '2015'),
(637, 'Login', 2, 10, 17, 'AM', '4', 'DEC', '2015'),
(638, 'Login', 2, 10, 19, 'AM', '4', 'DEC', '2015'),
(639, 'Login', 2, 10, 20, 'AM', '4', 'DEC', '2015'),
(640, 'Login', 2, 10, 21, 'AM', '4', 'DEC', '2015'),
(641, 'Logout', 2, 10, 21, 'AM', '4', 'DEC', '2015'),
(642, 'Login', 2, 10, 22, 'AM', '4', 'DEC', '2015'),
(643, 'Logout', 2, 10, 22, 'AM', '4', 'DEC', '2015'),
(644, 'Login', 2, 10, 25, 'AM', '4', 'DEC', '2015'),
(645, 'Logout', 2, 10, 26, 'AM', '4', 'DEC', '2015'),
(646, 'Login', 2, 10, 27, 'AM', '4', 'DEC', '2015'),
(647, 'Login', 2, 10, 28, 'AM', '4', 'DEC', '2015'),
(648, 'Logout', 2, 10, 30, 'AM', '4', 'DEC', '2015'),
(649, 'Login', 2, 10, 34, 'AM', '4', 'DEC', '2015'),
(650, 'Login', 2, 10, 35, 'AM', '4', 'DEC', '2015'),
(651, 'Login', 2, 10, 37, 'AM', '4', 'DEC', '2015'),
(652, 'Logout', 2, 10, 37, 'AM', '4', 'DEC', '2015'),
(653, 'Login', 2, 10, 38, 'AM', '4', 'DEC', '2015'),
(654, 'Login', 2, 10, 39, 'AM', '4', 'DEC', '2015'),
(655, 'Logout', 2, 10, 40, 'AM', '4', 'DEC', '2015'),
(656, 'Login', 2, 10, 41, 'AM', '4', 'DEC', '2015'),
(657, 'Logout', 2, 10, 41, 'AM', '4', 'DEC', '2015'),
(658, 'Login', 2, 10, 47, 'AM', '4', 'DEC', '2015'),
(659, 'Login', 2, 10, 48, 'AM', '4', 'DEC', '2015'),
(660, 'Login', 2, 10, 49, 'AM', '4', 'DEC', '2015'),
(661, 'Login', 2, 10, 50, 'AM', '4', 'DEC', '2015'),
(662, 'Login', 2, 10, 51, 'AM', '4', 'DEC', '2015'),
(663, 'Login', 2, 10, 52, 'AM', '4', 'DEC', '2015'),
(664, 'Login', 2, 10, 57, 'AM', '4', 'DEC', '2015'),
(665, 'Login', 2, 10, 59, 'AM', '4', 'DEC', '2015'),
(666, 'Login', 2, 11, 0, 'AM', '4', 'DEC', '2015'),
(667, 'Login', 2, 11, 1, 'AM', '4', 'DEC', '2015'),
(668, 'Login', 2, 11, 3, 'AM', '4', 'DEC', '2015'),
(669, 'Login', 2, 11, 4, 'AM', '4', 'DEC', '2015'),
(670, 'Logout', 2, 11, 5, 'AM', '4', 'DEC', '2015'),
(671, 'Login', 2, 11, 7, 'AM', '4', 'DEC', '2015'),
(672, 'Logout', 2, 11, 7, 'AM', '4', 'DEC', '2015'),
(673, 'Login', 2, 11, 8, 'AM', '4', 'DEC', '2015'),
(674, 'Login', 2, 11, 9, 'AM', '4', 'DEC', '2015'),
(675, 'Login', 2, 11, 9, 'AM', '4', 'DEC', '2015'),
(676, 'Logout', 2, 11, 9, 'AM', '4', 'DEC', '2015'),
(677, 'Login', 2, 11, 10, 'AM', '4', 'DEC', '2015'),
(678, 'Logout', 2, 11, 10, 'AM', '4', 'DEC', '2015'),
(679, 'Login', 2, 11, 12, 'AM', '4', 'DEC', '2015'),
(680, 'Logout', 2, 11, 13, 'AM', '4', 'DEC', '2015'),
(681, 'Login', 2, 11, 13, 'AM', '4', 'DEC', '2015'),
(682, 'Logout', 2, 11, 13, 'AM', '4', 'DEC', '2015'),
(683, 'Login', 2, 11, 21, 'AM', '4', 'DEC', '2015'),
(684, 'Logout', 2, 11, 22, 'AM', '4', 'DEC', '2015'),
(685, 'Login', 2, 11, 22, 'AM', '4', 'DEC', '2015'),
(686, 'Logout', 2, 11, 23, 'AM', '4', 'DEC', '2015'),
(687, 'Login', 2, 11, 24, 'AM', '4', 'DEC', '2015'),
(688, 'Logout', 2, 11, 24, 'AM', '4', 'DEC', '2015'),
(689, 'Login', 2, 11, 25, 'AM', '4', 'DEC', '2015'),
(690, 'Logout', 2, 11, 26, 'AM', '4', 'DEC', '2015'),
(691, 'Login', 2, 11, 26, 'AM', '4', 'DEC', '2015'),
(692, 'Logout', 2, 11, 26, 'AM', '4', 'DEC', '2015'),
(693, 'Login', 2, 11, 27, 'AM', '4', 'DEC', '2015'),
(694, 'Logout', 2, 11, 27, 'AM', '4', 'DEC', '2015'),
(695, 'Login', 2, 11, 30, 'AM', '4', 'DEC', '2015'),
(696, 'Login', 2, 11, 33, 'AM', '4', 'DEC', '2015'),
(697, 'Login', 2, 11, 35, 'AM', '4', 'DEC', '2015'),
(698, 'Login', 2, 11, 37, 'AM', '4', 'DEC', '2015'),
(699, 'Logout', 2, 11, 37, 'AM', '4', 'DEC', '2015'),
(700, 'Login', 2, 11, 38, 'AM', '4', 'DEC', '2015'),
(701, 'Logout', 2, 11, 38, 'AM', '4', 'DEC', '2015'),
(702, 'Login', 2, 11, 40, 'AM', '4', 'DEC', '2015'),
(703, 'Logout', 2, 11, 42, 'AM', '4', 'DEC', '2015'),
(704, 'Login', 2, 1, 45, 'PM', '4', 'DEC', '2015'),
(705, 'Logout', 2, 1, 48, 'PM', '4', 'DEC', '2015'),
(706, 'Login', 2, 1, 56, 'PM', '4', 'DEC', '2015'),
(707, 'Logout', 2, 1, 56, 'PM', '4', 'DEC', '2015'),
(708, 'Login', 2, 1, 57, 'PM', '4', 'DEC', '2015'),
(709, 'Logout', 2, 2, 4, 'PM', '4', 'DEC', '2015'),
(710, 'Login', 2, 2, 9, 'PM', '4', 'DEC', '2015'),
(711, 'Logout', 2, 2, 9, 'PM', '4', 'DEC', '2015'),
(712, 'Login', 2, 2, 10, 'PM', '4', 'DEC', '2015'),
(713, 'Logout', 2, 2, 13, 'PM', '4', 'DEC', '2015'),
(714, 'Login', 2, 2, 34, 'PM', '4', 'DEC', '2015'),
(715, 'Logout', 2, 2, 35, 'PM', '4', 'DEC', '2015'),
(716, 'Login', 2, 2, 35, 'PM', '4', 'DEC', '2015'),
(717, 'Login', 2, 2, 38, 'PM', '4', 'DEC', '2015'),
(718, 'Logout', 2, 2, 38, 'PM', '4', 'DEC', '2015'),
(719, 'Login', 2, 2, 42, 'PM', '4', 'DEC', '2015'),
(720, 'Logout', 2, 2, 44, 'PM', '4', 'DEC', '2015'),
(721, 'Login', 2, 2, 49, 'PM', '4', 'DEC', '2015'),
(722, 'Logout', 2, 2, 50, 'PM', '4', 'DEC', '2015'),
(723, 'Login', 2, 2, 51, 'PM', '4', 'DEC', '2015'),
(724, 'Logout', 2, 2, 52, 'PM', '4', 'DEC', '2015'),
(725, 'Login', 2, 2, 53, 'PM', '4', 'DEC', '2015'),
(726, 'Logout', 2, 2, 53, 'PM', '4', 'DEC', '2015'),
(727, 'Login', 2, 2, 54, 'PM', '4', 'DEC', '2015'),
(728, 'Logout', 2, 2, 55, 'PM', '4', 'DEC', '2015'),
(729, 'Login', 2, 2, 58, 'PM', '4', 'DEC', '2015'),
(730, 'Logout', 2, 2, 59, 'PM', '4', 'DEC', '2015'),
(731, 'Login', 2, 3, 0, 'PM', '4', 'DEC', '2015'),
(732, 'Login', 2, 3, 1, 'PM', '4', 'DEC', '2015'),
(733, 'Logout', 2, 3, 1, 'PM', '4', 'DEC', '2015'),
(734, 'Login', 2, 3, 2, 'PM', '4', 'DEC', '2015'),
(735, 'Logout', 2, 3, 2, 'PM', '4', 'DEC', '2015'),
(736, 'Login', 2, 3, 10, 'PM', '4', 'DEC', '2015'),
(737, 'Logout', 2, 3, 13, 'PM', '4', 'DEC', '2015'),
(738, 'Login', 2, 3, 17, 'PM', '4', 'DEC', '2015'),
(739, 'Logout', 2, 3, 18, 'PM', '4', 'DEC', '2015'),
(740, 'Login', 2, 7, 20, 'PM', '4', 'DEC', '2015'),
(741, 'Logout', 2, 7, 21, 'PM', '4', 'DEC', '2015'),
(742, 'Login', 2, 7, 28, 'PM', '4', 'DEC', '2015'),
(743, 'Login', 2, 7, 32, 'PM', '4', 'DEC', '2015'),
(744, 'Login', 2, 7, 36, 'PM', '4', 'DEC', '2015'),
(745, 'Logout', 2, 7, 36, 'PM', '4', 'DEC', '2015'),
(746, 'Login', 2, 7, 38, 'PM', '4', 'DEC', '2015'),
(747, 'Login', 2, 7, 45, 'PM', '4', 'DEC', '2015'),
(748, 'Logout', 2, 7, 46, 'PM', '4', 'DEC', '2015'),
(749, 'Login', 2, 7, 47, 'PM', '4', 'DEC', '2015'),
(750, 'Logout', 2, 7, 48, 'PM', '4', 'DEC', '2015'),
(751, 'Login', 2, 7, 50, 'PM', '4', 'DEC', '2015'),
(752, 'Logout', 2, 7, 50, 'PM', '4', 'DEC', '2015'),
(753, 'Login', 2, 7, 52, 'PM', '4', 'DEC', '2015'),
(754, 'Logout', 2, 7, 53, 'PM', '4', 'DEC', '2015'),
(755, 'Login', 2, 7, 57, 'PM', '4', 'DEC', '2015'),
(756, 'Logout', 2, 7, 58, 'PM', '4', 'DEC', '2015'),
(757, 'Login', 2, 8, 7, 'PM', '4', 'DEC', '2015'),
(758, 'Login', 2, 8, 16, 'PM', '4', 'DEC', '2015'),
(759, 'Login', 2, 8, 19, 'PM', '4', 'DEC', '2015'),
(760, 'Login', 2, 8, 21, 'PM', '4', 'DEC', '2015'),
(761, 'Logout', 2, 8, 33, 'PM', '4', 'DEC', '2015'),
(762, 'Login', 2, 8, 48, 'PM', '4', 'DEC', '2015'),
(763, 'Login', 2, 8, 54, 'PM', '4', 'DEC', '2015'),
(764, 'Login', 2, 8, 56, 'PM', '4', 'DEC', '2015'),
(765, 'Logout', 2, 8, 58, 'PM', '4', 'DEC', '2015'),
(766, 'Login', 2, 9, 1, 'PM', '4', 'DEC', '2015'),
(767, 'Login', 2, 9, 3, 'PM', '4', 'DEC', '2015'),
(768, 'Logout', 2, 9, 3, 'PM', '4', 'DEC', '2015'),
(769, 'Login', 2, 9, 3, 'PM', '4', 'DEC', '2015'),
(770, 'Logout', 2, 9, 4, 'PM', '4', 'DEC', '2015'),
(771, 'Login', 2, 9, 5, 'PM', '4', 'DEC', '2015'),
(772, 'Login', 2, 9, 7, 'PM', '4', 'DEC', '2015'),
(773, 'Login', 2, 9, 7, 'PM', '4', 'DEC', '2015'),
(774, 'Logout', 2, 9, 8, 'PM', '4', 'DEC', '2015'),
(775, 'Login', 2, 9, 9, 'PM', '4', 'DEC', '2015'),
(776, 'Login', 2, 9, 10, 'PM', '4', 'DEC', '2015'),
(777, 'Logout', 2, 9, 12, 'PM', '4', 'DEC', '2015'),
(778, 'Login', 2, 9, 13, 'PM', '4', 'DEC', '2015'),
(779, 'Logout', 2, 9, 14, 'PM', '4', 'DEC', '2015'),
(780, 'Login', 2, 9, 40, 'PM', '4', 'DEC', '2015'),
(781, 'Login', 2, 9, 42, 'PM', '4', 'DEC', '2015'),
(782, 'Login', 2, 9, 55, 'PM', '4', 'DEC', '2015'),
(783, 'Login', 2, 11, 10, 'PM', '4', 'DEC', '2015'),
(784, 'Login', 2, 11, 12, 'PM', '4', 'DEC', '2015'),
(785, 'Login', 2, 11, 21, 'PM', '4', 'DEC', '2015'),
(786, 'Logout', 2, 11, 23, 'PM', '4', 'DEC', '2015'),
(787, 'Login', 2, 11, 25, 'PM', '4', 'DEC', '2015'),
(788, 'Logout', 2, 11, 26, 'PM', '4', 'DEC', '2015'),
(789, 'Login', 2, 11, 27, 'PM', '4', 'DEC', '2015'),
(790, 'Logout', 2, 11, 28, 'PM', '4', 'DEC', '2015'),
(791, 'Login', 2, 11, 28, 'PM', '4', 'DEC', '2015'),
(792, 'Login', 2, 11, 37, 'PM', '4', 'DEC', '2015'),
(793, 'Login', 2, 11, 43, 'PM', '4', 'DEC', '2015'),
(794, 'Login', 2, 12, 1, 'AM', '5', 'DEC', '2015'),
(795, 'Logout', 2, 12, 4, 'AM', '5', 'DEC', '2015'),
(796, 'Login', 2, 12, 9, 'AM', '5', 'DEC', '2015'),
(797, 'Logout', 2, 12, 14, 'AM', '5', 'DEC', '2015'),
(798, 'Login', 2, 12, 15, 'AM', '5', 'DEC', '2015'),
(799, 'Login', 2, 12, 46, 'AM', '5', 'DEC', '2015'),
(800, 'Login', 2, 12, 49, 'AM', '5', 'DEC', '2015'),
(801, 'Login', 2, 12, 50, 'AM', '5', 'DEC', '2015'),
(802, 'Login', 2, 12, 52, 'AM', '5', 'DEC', '2015'),
(803, 'Login', 2, 12, 56, 'AM', '5', 'DEC', '2015'),
(804, 'Logout', 2, 12, 58, 'AM', '5', 'DEC', '2015'),
(805, 'Login', 2, 1, 1, 'AM', '5', 'DEC', '2015'),
(806, 'Login', 2, 1, 3, 'AM', '5', 'DEC', '2015'),
(807, 'Logout', 2, 1, 4, 'AM', '5', 'DEC', '2015'),
(808, 'Login', 2, 6, 26, 'AM', '5', 'DEC', '2015'),
(809, 'Login', 2, 6, 28, 'AM', '5', 'DEC', '2015'),
(810, 'Login', 2, 6, 31, 'AM', '5', 'DEC', '2015'),
(811, 'Login', 2, 6, 32, 'AM', '5', 'DEC', '2015'),
(812, 'Login', 2, 6, 33, 'AM', '5', 'DEC', '2015'),
(813, 'Logout', 2, 6, 33, 'AM', '5', 'DEC', '2015'),
(814, 'Login', 2, 6, 36, 'AM', '5', 'DEC', '2015'),
(815, 'Logout', 2, 6, 37, 'AM', '5', 'DEC', '2015'),
(816, 'Login', 2, 6, 45, 'AM', '5', 'DEC', '2015'),
(817, 'Login', 2, 6, 45, 'AM', '5', 'DEC', '2015'),
(818, 'Logout', 2, 6, 46, 'AM', '5', 'DEC', '2015'),
(819, 'Login', 2, 6, 52, 'AM', '5', 'DEC', '2015'),
(820, 'Logout', 2, 6, 53, 'AM', '5', 'DEC', '2015'),
(821, 'Login', 2, 6, 53, 'AM', '5', 'DEC', '2015'),
(822, 'Logout', 2, 6, 54, 'AM', '5', 'DEC', '2015'),
(823, 'Login', 2, 6, 55, 'AM', '5', 'DEC', '2015'),
(824, 'Login', 2, 6, 58, 'AM', '5', 'DEC', '2015'),
(825, 'Logout', 2, 6, 59, 'AM', '5', 'DEC', '2015'),
(826, 'Login', 2, 7, 8, 'AM', '5', 'DEC', '2015'),
(827, 'Login', 2, 7, 10, 'AM', '5', 'DEC', '2015'),
(828, 'Login', 2, 7, 20, 'AM', '5', 'DEC', '2015'),
(829, 'Login', 2, 7, 23, 'AM', '5', 'DEC', '2015'),
(830, 'Logout', 2, 7, 24, 'AM', '5', 'DEC', '2015'),
(831, 'Login', 2, 7, 48, 'AM', '5', 'DEC', '2015'),
(832, 'Login', 2, 7, 49, 'AM', '5', 'DEC', '2015'),
(833, 'Logout', 2, 7, 49, 'AM', '5', 'DEC', '2015'),
(834, 'Login', 2, 7, 50, 'AM', '5', 'DEC', '2015'),
(835, 'Login', 2, 7, 51, 'AM', '5', 'DEC', '2015'),
(836, 'Login', 2, 7, 53, 'AM', '5', 'DEC', '2015'),
(837, 'Login', 2, 7, 55, 'AM', '5', 'DEC', '2015'),
(838, 'Logout', 2, 7, 55, 'AM', '5', 'DEC', '2015'),
(839, 'Login', 2, 7, 57, 'AM', '5', 'DEC', '2015'),
(840, 'Login', 2, 8, 0, 'AM', '5', 'DEC', '2015'),
(841, 'Login', 2, 8, 2, 'AM', '5', 'DEC', '2015'),
(842, 'Login', 2, 8, 3, 'AM', '5', 'DEC', '2015'),
(843, 'Login', 2, 8, 3, 'AM', '5', 'DEC', '2015'),
(844, 'Logout', 2, 8, 4, 'AM', '5', 'DEC', '2015'),
(845, 'Login', 2, 8, 4, 'AM', '5', 'DEC', '2015'),
(846, 'Login', 2, 8, 5, 'AM', '5', 'DEC', '2015'),
(847, 'Logout', 2, 8, 5, 'AM', '5', 'DEC', '2015'),
(848, 'Login', 2, 8, 6, 'AM', '5', 'DEC', '2015'),
(849, 'Login', 2, 8, 8, 'AM', '5', 'DEC', '2015'),
(850, 'Logout', 2, 8, 8, 'AM', '5', 'DEC', '2015'),
(851, 'Login', 2, 8, 15, 'AM', '5', 'DEC', '2015'),
(852, 'Login', 2, 8, 15, 'AM', '5', 'DEC', '2015'),
(853, 'Login', 2, 8, 16, 'AM', '5', 'DEC', '2015'),
(854, 'Logout', 2, 8, 16, 'AM', '5', 'DEC', '2015'),
(855, 'Login', 2, 8, 28, 'AM', '5', 'DEC', '2015'),
(856, 'Logout', 2, 8, 29, 'AM', '5', 'DEC', '2015'),
(857, 'Login', 2, 8, 29, 'AM', '5', 'DEC', '2015'),
(858, 'Logout', 2, 8, 30, 'AM', '5', 'DEC', '2015'),
(859, 'Login', 2, 8, 35, 'AM', '5', 'DEC', '2015'),
(860, 'Login', 2, 8, 36, 'AM', '5', 'DEC', '2015'),
(861, 'Logout', 2, 8, 37, 'AM', '5', 'DEC', '2015'),
(862, 'Login', 2, 8, 37, 'AM', '5', 'DEC', '2015'),
(863, 'Logout', 2, 8, 38, 'AM', '5', 'DEC', '2015'),
(864, 'Login', 2, 8, 40, 'AM', '5', 'DEC', '2015'),
(865, 'Logout', 2, 8, 41, 'AM', '5', 'DEC', '2015'),
(866, 'Login', 2, 8, 45, 'AM', '5', 'DEC', '2015'),
(867, 'Logout', 2, 8, 45, 'AM', '5', 'DEC', '2015'),
(868, 'Login', 2, 8, 47, 'AM', '5', 'DEC', '2015'),
(869, 'Logout', 2, 8, 47, 'AM', '5', 'DEC', '2015'),
(870, 'Login', 2, 8, 47, 'AM', '5', 'DEC', '2015'),
(871, 'Logout', 2, 8, 48, 'AM', '5', 'DEC', '2015'),
(872, 'Login', 2, 8, 51, 'AM', '5', 'DEC', '2015'),
(873, 'Logout', 2, 8, 51, 'AM', '5', 'DEC', '2015'),
(874, 'Login', 2, 8, 53, 'AM', '5', 'DEC', '2015'),
(875, 'Logout', 2, 8, 53, 'AM', '5', 'DEC', '2015'),
(876, 'Login', 2, 8, 55, 'AM', '5', 'DEC', '2015'),
(877, 'Login', 2, 8, 56, 'AM', '5', 'DEC', '2015'),
(878, 'Login', 2, 8, 56, 'AM', '5', 'DEC', '2015'),
(879, 'Logout', 2, 9, 2, 'AM', '5', 'DEC', '2015'),
(880, 'Login', 2, 9, 2, 'AM', '5', 'DEC', '2015'),
(881, 'Logout', 2, 9, 3, 'AM', '5', 'DEC', '2015'),
(882, 'Login', 2, 9, 7, 'AM', '5', 'DEC', '2015'),
(883, 'Logout', 2, 9, 7, 'AM', '5', 'DEC', '2015'),
(884, 'Login', 2, 9, 8, 'AM', '5', 'DEC', '2015'),
(885, 'Logout', 2, 9, 9, 'AM', '5', 'DEC', '2015'),
(886, 'Login', 2, 9, 9, 'AM', '5', 'DEC', '2015'),
(887, 'Login', 2, 9, 23, 'AM', '5', 'DEC', '2015'),
(888, 'Login', 2, 9, 25, 'AM', '5', 'DEC', '2015'),
(889, 'Logout', 2, 9, 26, 'AM', '5', 'DEC', '2015'),
(890, 'Login', 2, 9, 30, 'AM', '5', 'DEC', '2015'),
(891, 'Login', 2, 9, 32, 'AM', '5', 'DEC', '2015'),
(892, 'Logout', 2, 9, 32, 'AM', '5', 'DEC', '2015'),
(893, 'Login', 2, 9, 35, 'AM', '5', 'DEC', '2015'),
(894, 'Logout', 2, 9, 40, 'AM', '5', 'DEC', '2015'),
(895, 'Login', 2, 9, 40, 'AM', '5', 'DEC', '2015'),
(896, 'Logout', 2, 9, 42, 'AM', '5', 'DEC', '2015'),
(897, 'Login', 2, 10, 59, 'AM', '5', 'DEC', '2015'),
(898, 'Logout', 2, 11, 4, 'AM', '5', 'DEC', '2015'),
(899, 'Login', 2, 11, 13, 'AM', '5', 'DEC', '2015'),
(900, 'Logout', 2, 11, 14, 'AM', '5', 'DEC', '2015'),
(901, 'Login', 2, 11, 18, 'AM', '5', 'DEC', '2015'),
(902, 'Logout', 2, 11, 20, 'AM', '5', 'DEC', '2015'),
(903, 'Login', 2, 11, 25, 'AM', '5', 'DEC', '2015'),
(904, 'Logout', 2, 11, 26, 'AM', '5', 'DEC', '2015'),
(905, 'Login', 2, 11, 32, 'AM', '5', 'DEC', '2015'),
(906, 'Logout', 2, 11, 32, 'AM', '5', 'DEC', '2015'),
(907, 'Login', 2, 11, 38, 'AM', '5', 'DEC', '2015'),
(908, 'Login', 2, 11, 39, 'AM', '5', 'DEC', '2015'),
(909, 'Logout', 2, 11, 40, 'AM', '5', 'DEC', '2015'),
(910, 'Login', 2, 11, 40, 'AM', '5', 'DEC', '2015'),
(911, 'Login', 2, 11, 43, 'AM', '5', 'DEC', '2015'),
(912, 'Logout', 2, 11, 46, 'AM', '5', 'DEC', '2015'),
(913, 'Login', 2, 11, 46, 'AM', '5', 'DEC', '2015'),
(914, 'Login', 2, 11, 48, 'AM', '5', 'DEC', '2015'),
(915, 'Logout', 2, 11, 55, 'AM', '5', 'DEC', '2015'),
(916, 'Login', 2, 12, 4, 'PM', '5', 'DEC', '2015'),
(917, 'Logout', 2, 12, 12, 'PM', '5', 'DEC', '2015'),
(918, 'Login', 2, 3, 32, 'PM', '6', 'DEC', '2015'),
(919, 'Login', 2, 3, 33, 'PM', '6', 'DEC', '2015'),
(920, 'Logout', 2, 3, 34, 'PM', '6', 'DEC', '2015'),
(921, 'Login', 2, 3, 36, 'PM', '6', 'DEC', '2015'),
(922, 'Login', 2, 3, 37, 'PM', '6', 'DEC', '2015'),
(923, 'Login', 2, 3, 43, 'PM', '6', 'DEC', '2015'),
(924, 'Logout', 2, 3, 44, 'PM', '6', 'DEC', '2015'),
(925, 'Login', 2, 3, 46, 'PM', '6', 'DEC', '2015'),
(926, 'Logout', 2, 3, 47, 'PM', '6', 'DEC', '2015'),
(927, 'Login', 2, 3, 47, 'PM', '6', 'DEC', '2015'),
(928, 'Logout', 2, 3, 48, 'PM', '6', 'DEC', '2015'),
(929, 'Login', 2, 3, 49, 'PM', '6', 'DEC', '2015'),
(930, 'Logout', 2, 3, 50, 'PM', '6', 'DEC', '2015'),
(931, 'Login', 2, 5, 15, 'PM', '6', 'DEC', '2015'),
(932, 'Logout', 2, 5, 16, 'PM', '6', 'DEC', '2015'),
(933, 'Login', 2, 5, 35, 'PM', '6', 'DEC', '2015'),
(934, 'Logout', 2, 5, 36, 'PM', '6', 'DEC', '2015'),
(935, 'Login', 2, 5, 38, 'PM', '6', 'DEC', '2015'),
(936, 'Logout', 2, 5, 38, 'PM', '6', 'DEC', '2015'),
(937, 'Login', 2, 5, 42, 'PM', '6', 'DEC', '2015'),
(938, 'Logout', 2, 5, 43, 'PM', '6', 'DEC', '2015'),
(939, 'Login', 2, 6, 16, 'PM', '6', 'DEC', '2015'),
(940, 'Login', 2, 6, 18, 'PM', '6', 'DEC', '2015'),
(941, 'Logout', 2, 6, 21, 'PM', '6', 'DEC', '2015'),
(942, 'Login', 2, 6, 42, 'PM', '6', 'DEC', '2015'),
(943, 'Logout', 2, 6, 49, 'PM', '6', 'DEC', '2015'),
(944, 'Login', 2, 6, 51, 'PM', '6', 'DEC', '2015'),
(945, 'Logout', 2, 6, 53, 'PM', '6', 'DEC', '2015'),
(946, 'Login', 2, 7, 23, 'PM', '6', 'DEC', '2015'),
(947, 'Logout', 2, 7, 24, 'PM', '6', 'DEC', '2015'),
(948, 'Login', 2, 7, 30, 'PM', '6', 'DEC', '2015'),
(949, 'Login', 2, 7, 32, 'PM', '6', 'DEC', '2015'),
(950, 'Logout', 2, 7, 33, 'PM', '6', 'DEC', '2015'),
(951, 'Login', 2, 7, 35, 'PM', '6', 'DEC', '2015'),
(952, 'Login', 2, 7, 36, 'PM', '6', 'DEC', '2015'),
(953, 'Login', 2, 7, 37, 'PM', '6', 'DEC', '2015'),
(954, 'Logout', 2, 7, 37, 'PM', '6', 'DEC', '2015'),
(955, 'Login', 2, 7, 48, 'PM', '6', 'DEC', '2015'),
(956, 'Logout', 2, 7, 48, 'PM', '6', 'DEC', '2015'),
(957, 'Login', 2, 7, 50, 'PM', '6', 'DEC', '2015'),
(958, 'Logout', 2, 7, 50, 'PM', '6', 'DEC', '2015'),
(959, 'Login', 2, 7, 51, 'PM', '6', 'DEC', '2015'),
(960, 'Logout', 2, 7, 51, 'PM', '6', 'DEC', '2015'),
(961, 'Login', 2, 7, 54, 'PM', '6', 'DEC', '2015'),
(962, 'Logout', 2, 7, 54, 'PM', '6', 'DEC', '2015'),
(963, 'Login', 2, 7, 58, 'PM', '6', 'DEC', '2015'),
(964, 'Login', 2, 8, 11, 'PM', '6', 'DEC', '2015'),
(965, 'Logout', 2, 8, 11, 'PM', '6', 'DEC', '2015'),
(966, 'Login', 2, 8, 38, 'PM', '6', 'DEC', '2015'),
(967, 'Logout', 2, 8, 39, 'PM', '6', 'DEC', '2015'),
(968, 'Login', 2, 8, 39, 'PM', '6', 'DEC', '2015'),
(969, 'Logout', 2, 8, 40, 'PM', '6', 'DEC', '2015'),
(970, 'Login', 2, 10, 44, 'PM', '6', 'DEC', '2015'),
(971, 'Logout', 2, 10, 47, 'PM', '6', 'DEC', '2015'),
(972, 'Login', 2, 11, 9, 'PM', '6', 'DEC', '2015'),
(973, 'Logout', 2, 11, 10, 'PM', '6', 'DEC', '2015'),
(974, 'Login', 2, 11, 11, 'PM', '6', 'DEC', '2015'),
(975, 'Logout', 2, 11, 11, 'PM', '6', 'DEC', '2015'),
(976, 'Login', 2, 11, 14, 'PM', '6', 'DEC', '2015'),
(977, 'Login', 2, 11, 16, 'PM', '6', 'DEC', '2015'),
(978, 'Login', 2, 7, 9, 'AM', '7', 'DEC', '2015'),
(979, 'Logout', 2, 7, 9, 'AM', '7', 'DEC', '2015'),
(980, 'Login', 2, 7, 10, 'AM', '7', 'DEC', '2015'),
(981, 'Logout', 2, 7, 10, 'AM', '7', 'DEC', '2015'),
(982, 'Login', 2, 7, 10, 'AM', '7', 'DEC', '2015');
INSERT INTO `user_logs` (`log_id`, `login_name`, `user_id`, `hour`, `minute`, `am_pm`, `day`, `month`, `year`) VALUES
(983, 'Logout', 2, 7, 10, 'AM', '7', 'DEC', '2015'),
(984, 'Login', 2, 7, 11, 'AM', '7', 'DEC', '2015'),
(985, 'Logout', 2, 7, 11, 'AM', '7', 'DEC', '2015'),
(986, 'Login', 2, 7, 13, 'AM', '7', 'DEC', '2015'),
(987, 'Login', 2, 7, 16, 'AM', '7', 'DEC', '2015'),
(988, 'Login', 2, 7, 36, 'AM', '7', 'DEC', '2015'),
(989, 'Logout', 2, 7, 36, 'AM', '7', 'DEC', '2015'),
(990, 'Login', 2, 7, 37, 'AM', '7', 'DEC', '2015'),
(991, 'Logout', 2, 7, 37, 'AM', '7', 'DEC', '2015'),
(992, 'Login', 2, 7, 37, 'AM', '7', 'DEC', '2015'),
(993, 'Logout', 2, 7, 38, 'AM', '7', 'DEC', '2015'),
(994, 'Login', 2, 7, 38, 'AM', '7', 'DEC', '2015'),
(995, 'Logout', 2, 7, 38, 'AM', '7', 'DEC', '2015'),
(996, 'Login', 2, 9, 15, 'AM', '7', 'DEC', '2015'),
(997, 'Login', 2, 9, 17, 'AM', '7', 'DEC', '2015'),
(998, 'Logout', 2, 9, 19, 'AM', '7', 'DEC', '2015'),
(999, 'Login', 2, 9, 29, 'AM', '7', 'DEC', '2015'),
(1000, 'Login', 2, 9, 31, 'AM', '7', 'DEC', '2015'),
(1001, 'Login', 2, 3, 34, 'PM', '7', 'DEC', '2015'),
(1002, 'Logout', 2, 3, 35, 'PM', '7', 'DEC', '2015'),
(1003, 'Login', 2, 3, 35, 'PM', '7', 'DEC', '2015'),
(1004, 'Logout', 2, 3, 37, 'PM', '7', 'DEC', '2015'),
(1005, 'Login', 2, 3, 41, 'PM', '7', 'DEC', '2015'),
(1006, 'Logout', 2, 3, 42, 'PM', '7', 'DEC', '2015'),
(1007, 'Login', 2, 3, 46, 'PM', '7', 'DEC', '2015'),
(1008, 'Logout', 2, 3, 46, 'PM', '7', 'DEC', '2015'),
(1009, 'Login', 2, 3, 48, 'PM', '7', 'DEC', '2015'),
(1010, 'Login', 2, 3, 49, 'PM', '7', 'DEC', '2015'),
(1011, 'Login', 2, 3, 53, 'PM', '7', 'DEC', '2015'),
(1012, 'Login', 2, 3, 55, 'PM', '7', 'DEC', '2015'),
(1013, 'Login', 2, 3, 59, 'PM', '7', 'DEC', '2015'),
(1014, 'Logout', 2, 4, 1, 'PM', '7', 'DEC', '2015'),
(1015, 'Login', 2, 4, 2, 'PM', '7', 'DEC', '2015'),
(1016, 'Logout', 2, 4, 2, 'PM', '7', 'DEC', '2015'),
(1017, 'Login', 2, 4, 5, 'PM', '7', 'DEC', '2015'),
(1018, 'Logout', 2, 4, 5, 'PM', '7', 'DEC', '2015'),
(1019, 'Login', 2, 4, 5, 'PM', '7', 'DEC', '2015'),
(1020, 'Logout', 2, 4, 6, 'PM', '7', 'DEC', '2015'),
(1021, 'Login', 2, 4, 8, 'PM', '7', 'DEC', '2015'),
(1022, 'Login', 2, 4, 11, 'PM', '7', 'DEC', '2015'),
(1023, 'Logout', 2, 4, 11, 'PM', '7', 'DEC', '2015'),
(1024, 'Login', 2, 4, 12, 'PM', '7', 'DEC', '2015'),
(1025, 'Logout', 2, 4, 12, 'PM', '7', 'DEC', '2015'),
(1026, 'Login', 2, 4, 13, 'PM', '7', 'DEC', '2015'),
(1027, 'Logout', 2, 4, 16, 'PM', '7', 'DEC', '2015'),
(1028, 'Login', 2, 4, 19, 'PM', '7', 'DEC', '2015'),
(1029, 'Logout', 2, 4, 23, 'PM', '7', 'DEC', '2015'),
(1030, 'Login', 2, 4, 56, 'PM', '7', 'DEC', '2015'),
(1031, 'Logout', 2, 4, 56, 'PM', '7', 'DEC', '2015'),
(1032, 'Login', 2, 4, 56, 'PM', '7', 'DEC', '2015'),
(1033, 'Logout', 2, 4, 57, 'PM', '7', 'DEC', '2015'),
(1034, 'Login', 2, 5, 0, 'PM', '7', 'DEC', '2015'),
(1035, 'Logout', 2, 5, 1, 'PM', '7', 'DEC', '2015'),
(1036, 'Login', 2, 5, 6, 'PM', '7', 'DEC', '2015'),
(1037, 'Logout', 2, 5, 6, 'PM', '7', 'DEC', '2015'),
(1038, 'Login', 2, 5, 7, 'PM', '7', 'DEC', '2015'),
(1039, 'Login', 2, 5, 59, 'PM', '7', 'DEC', '2015'),
(1040, 'Logout', 2, 6, 19, 'PM', '7', 'DEC', '2015'),
(1041, 'Login', 2, 8, 9, 'AM', '8', 'DEC', '2015'),
(1042, 'Logout', 2, 8, 9, 'AM', '8', 'DEC', '2015'),
(1043, 'Login', 2, 8, 10, 'AM', '8', 'DEC', '2015'),
(1044, 'Logout', 2, 8, 12, 'AM', '8', 'DEC', '2015'),
(1045, 'Login', 2, 8, 20, 'AM', '8', 'DEC', '2015'),
(1046, 'Login', 2, 8, 33, 'AM', '8', 'DEC', '2015'),
(1047, 'Logout', 2, 8, 33, 'AM', '8', 'DEC', '2015'),
(1048, 'Login', 2, 8, 39, 'AM', '8', 'DEC', '2015'),
(1049, 'Logout', 2, 8, 40, 'AM', '8', 'DEC', '2015'),
(1050, 'Login', 2, 8, 45, 'AM', '8', 'DEC', '2015'),
(1051, 'Login', 2, 8, 46, 'AM', '8', 'DEC', '2015'),
(1052, 'Login', 2, 8, 47, 'AM', '8', 'DEC', '2015'),
(1053, 'Login', 2, 8, 48, 'AM', '8', 'DEC', '2015'),
(1054, 'Login', 2, 8, 51, 'AM', '8', 'DEC', '2015'),
(1055, 'Login', 2, 8, 53, 'AM', '8', 'DEC', '2015'),
(1056, 'Logout', 2, 8, 54, 'AM', '8', 'DEC', '2015'),
(1057, 'Login', 2, 8, 55, 'AM', '8', 'DEC', '2015'),
(1058, 'Login', 2, 9, 11, 'AM', '8', 'DEC', '2015'),
(1059, 'Logout', 2, 9, 12, 'AM', '8', 'DEC', '2015'),
(1060, 'Login', 2, 9, 15, 'AM', '8', 'DEC', '2015'),
(1061, 'Logout', 2, 9, 16, 'AM', '8', 'DEC', '2015'),
(1062, 'Login', 2, 9, 17, 'AM', '8', 'DEC', '2015'),
(1063, 'Logout', 2, 9, 18, 'AM', '8', 'DEC', '2015'),
(1064, 'Login', 2, 9, 19, 'AM', '8', 'DEC', '2015'),
(1065, 'Logout', 2, 9, 19, 'AM', '8', 'DEC', '2015'),
(1066, 'Login', 2, 10, 11, 'AM', '8', 'DEC', '2015'),
(1067, 'Logout', 2, 10, 11, 'AM', '8', 'DEC', '2015'),
(1068, 'Login', 2, 10, 36, 'AM', '8', 'DEC', '2015'),
(1069, 'Logout', 2, 10, 36, 'AM', '8', 'DEC', '2015'),
(1070, 'Login', 2, 10, 36, 'AM', '8', 'DEC', '2015'),
(1071, 'Login', 2, 11, 1, 'AM', '8', 'DEC', '2015'),
(1072, 'Logout', 2, 11, 1, 'AM', '8', 'DEC', '2015'),
(1073, 'Login', 2, 11, 2, 'AM', '8', 'DEC', '2015'),
(1074, 'Logout', 2, 11, 3, 'AM', '8', 'DEC', '2015'),
(1075, 'Login', 2, 11, 3, 'AM', '8', 'DEC', '2015'),
(1076, 'Logout', 2, 11, 4, 'AM', '8', 'DEC', '2015'),
(1077, 'Login', 2, 11, 4, 'AM', '8', 'DEC', '2015'),
(1078, 'Logout', 2, 11, 5, 'AM', '8', 'DEC', '2015'),
(1079, 'Login', 2, 11, 6, 'AM', '8', 'DEC', '2015'),
(1080, 'Login', 2, 11, 12, 'AM', '8', 'DEC', '2015'),
(1081, 'Login', 2, 11, 19, 'AM', '8', 'DEC', '2015'),
(1082, 'Login', 2, 11, 34, 'AM', '8', 'DEC', '2015'),
(1083, 'Login', 2, 11, 35, 'AM', '8', 'DEC', '2015'),
(1084, 'Logout', 2, 11, 36, 'AM', '8', 'DEC', '2015'),
(1085, 'Login', 2, 11, 39, 'AM', '8', 'DEC', '2015'),
(1086, 'Logout', 2, 11, 39, 'AM', '8', 'DEC', '2015'),
(1087, 'Login', 2, 11, 40, 'AM', '8', 'DEC', '2015'),
(1088, 'Logout', 2, 11, 40, 'AM', '8', 'DEC', '2015'),
(1089, 'Login', 2, 11, 42, 'AM', '8', 'DEC', '2015'),
(1090, 'Logout', 2, 11, 42, 'AM', '8', 'DEC', '2015'),
(1091, 'Login', 2, 11, 44, 'AM', '8', 'DEC', '2015'),
(1092, 'Login', 2, 11, 47, 'AM', '8', 'DEC', '2015'),
(1093, 'Logout', 2, 11, 47, 'AM', '8', 'DEC', '2015'),
(1094, 'Login', 2, 11, 50, 'AM', '8', 'DEC', '2015'),
(1095, 'Login', 2, 11, 50, 'AM', '8', 'DEC', '2015'),
(1096, 'Logout', 2, 11, 51, 'AM', '8', 'DEC', '2015'),
(1097, 'Login', 2, 11, 52, 'AM', '8', 'DEC', '2015'),
(1098, 'Login', 2, 11, 52, 'AM', '8', 'DEC', '2015'),
(1099, 'Login', 2, 11, 54, 'AM', '8', 'DEC', '2015'),
(1100, 'Logout', 2, 11, 54, 'AM', '8', 'DEC', '2015'),
(1101, 'Login', 2, 11, 55, 'AM', '8', 'DEC', '2015'),
(1102, 'Login', 2, 11, 59, 'AM', '8', 'DEC', '2015'),
(1103, 'Logout', 2, 11, 59, 'AM', '8', 'DEC', '2015'),
(1104, 'Login', 2, 12, 2, 'PM', '8', 'DEC', '2015'),
(1105, 'Login', 2, 12, 3, 'PM', '8', 'DEC', '2015'),
(1106, 'Login', 2, 12, 3, 'PM', '8', 'DEC', '2015'),
(1107, 'Logout', 2, 12, 3, 'PM', '8', 'DEC', '2015'),
(1108, 'Login', 2, 12, 4, 'PM', '8', 'DEC', '2015'),
(1109, 'Logout', 2, 12, 5, 'PM', '8', 'DEC', '2015'),
(1110, 'Login', 2, 12, 7, 'PM', '8', 'DEC', '2015'),
(1111, 'Login', 2, 12, 8, 'PM', '8', 'DEC', '2015'),
(1112, 'Login', 2, 12, 10, 'PM', '8', 'DEC', '2015'),
(1113, 'Logout', 2, 12, 11, 'PM', '8', 'DEC', '2015'),
(1114, 'Login', 2, 12, 14, 'PM', '8', 'DEC', '2015'),
(1115, 'Logout', 2, 12, 15, 'PM', '8', 'DEC', '2015'),
(1116, 'Login', 2, 12, 17, 'PM', '8', 'DEC', '2015'),
(1117, 'Login', 2, 12, 19, 'PM', '8', 'DEC', '2015'),
(1118, 'Logout', 2, 12, 19, 'PM', '8', 'DEC', '2015'),
(1119, 'Login', 2, 12, 20, 'PM', '8', 'DEC', '2015'),
(1120, 'Logout', 2, 12, 21, 'PM', '8', 'DEC', '2015'),
(1121, 'Login', 2, 12, 23, 'PM', '8', 'DEC', '2015'),
(1122, 'Login', 2, 12, 24, 'PM', '8', 'DEC', '2015'),
(1123, 'Logout', 2, 12, 25, 'PM', '8', 'DEC', '2015'),
(1124, 'Login', 2, 12, 25, 'PM', '8', 'DEC', '2015'),
(1125, 'Logout', 2, 12, 26, 'PM', '8', 'DEC', '2015'),
(1126, 'Login', 2, 12, 50, 'PM', '8', 'DEC', '2015'),
(1127, 'Login', 2, 12, 52, 'PM', '8', 'DEC', '2015'),
(1128, 'Login', 2, 12, 53, 'PM', '8', 'DEC', '2015'),
(1129, 'Login', 2, 1, 0, 'PM', '8', 'DEC', '2015'),
(1130, 'Logout', 2, 1, 2, 'PM', '8', 'DEC', '2015'),
(1131, 'Login', 2, 1, 6, 'PM', '8', 'DEC', '2015'),
(1132, 'Logout', 2, 1, 7, 'PM', '8', 'DEC', '2015'),
(1133, 'Login', 2, 1, 28, 'PM', '8', 'DEC', '2015'),
(1134, 'Login', 2, 1, 33, 'PM', '8', 'DEC', '2015'),
(1135, 'Login', 2, 1, 34, 'PM', '8', 'DEC', '2015'),
(1136, 'Login', 2, 1, 35, 'PM', '8', 'DEC', '2015'),
(1137, 'Login', 2, 1, 39, 'PM', '8', 'DEC', '2015'),
(1138, 'Logout', 2, 1, 39, 'PM', '8', 'DEC', '2015'),
(1139, 'Login', 2, 1, 43, 'PM', '8', 'DEC', '2015'),
(1140, 'Logout', 2, 1, 45, 'PM', '8', 'DEC', '2015'),
(1141, 'Login', 2, 1, 49, 'PM', '8', 'DEC', '2015'),
(1142, 'Logout', 2, 1, 50, 'PM', '8', 'DEC', '2015'),
(1143, 'Login', 2, 1, 50, 'PM', '8', 'DEC', '2015'),
(1144, 'Logout', 2, 1, 51, 'PM', '8', 'DEC', '2015'),
(1145, 'Login', 2, 1, 51, 'PM', '8', 'DEC', '2015'),
(1146, 'Logout', 2, 1, 52, 'PM', '8', 'DEC', '2015'),
(1147, 'Login', 2, 1, 52, 'PM', '8', 'DEC', '2015'),
(1148, 'Logout', 2, 1, 54, 'PM', '8', 'DEC', '2015'),
(1149, 'Login', 2, 1, 55, 'PM', '8', 'DEC', '2015'),
(1150, 'Logout', 2, 1, 55, 'PM', '8', 'DEC', '2015'),
(1151, 'Login', 2, 1, 55, 'PM', '8', 'DEC', '2015'),
(1152, 'Login', 2, 1, 56, 'PM', '8', 'DEC', '2015'),
(1153, 'Logout', 2, 1, 57, 'PM', '8', 'DEC', '2015'),
(1154, 'Login', 2, 7, 1, 'AM', '9', 'DEC', '2015'),
(1155, 'Logout', 2, 7, 3, 'AM', '9', 'DEC', '2015'),
(1156, 'Login', 2, 12, 46, 'PM', '9', 'DEC', '2015'),
(1157, 'Logout', 2, 12, 47, 'PM', '9', 'DEC', '2015'),
(1158, 'Login', 2, 12, 57, 'PM', '9', 'DEC', '2015'),
(1159, 'Login', 2, 9, 15, 'AM', '10', 'DEC', '2015'),
(1160, 'Logout', 2, 9, 18, 'AM', '10', 'DEC', '2015'),
(1161, 'Login', 2, 9, 23, 'AM', '10', 'DEC', '2015'),
(1162, 'Logout', 2, 9, 26, 'AM', '10', 'DEC', '2015'),
(1163, 'Login', 2, 9, 27, 'AM', '10', 'DEC', '2015'),
(1164, 'Login', 2, 9, 28, 'AM', '10', 'DEC', '2015'),
(1165, 'Login', 2, 9, 31, 'AM', '10', 'DEC', '2015'),
(1166, 'Login', 2, 9, 32, 'AM', '10', 'DEC', '2015'),
(1167, 'Login', 2, 9, 34, 'AM', '10', 'DEC', '2015'),
(1168, 'Login', 2, 9, 35, 'AM', '10', 'DEC', '2015'),
(1169, 'Login', 2, 9, 37, 'AM', '10', 'DEC', '2015'),
(1170, 'Login', 2, 9, 41, 'AM', '10', 'DEC', '2015'),
(1171, 'Login', 2, 9, 45, 'AM', '10', 'DEC', '2015'),
(1172, 'Logout', 2, 9, 48, 'AM', '10', 'DEC', '2015'),
(1173, 'Login', 2, 9, 56, 'AM', '10', 'DEC', '2015'),
(1174, 'Login', 2, 10, 1, 'AM', '10', 'DEC', '2015'),
(1175, 'Logout', 2, 10, 3, 'AM', '10', 'DEC', '2015'),
(1176, 'Login', 2, 10, 4, 'AM', '10', 'DEC', '2015'),
(1177, 'Login', 2, 10, 6, 'AM', '10', 'DEC', '2015'),
(1178, 'Logout', 2, 10, 6, 'AM', '10', 'DEC', '2015'),
(1179, 'Login', 2, 10, 8, 'AM', '10', 'DEC', '2015'),
(1180, 'Login', 2, 10, 9, 'AM', '10', 'DEC', '2015'),
(1181, 'Logout', 2, 10, 10, 'AM', '10', 'DEC', '2015'),
(1182, 'Login', 2, 10, 10, 'AM', '10', 'DEC', '2015'),
(1183, 'Login', 2, 10, 12, 'AM', '10', 'DEC', '2015'),
(1184, 'Login', 2, 10, 14, 'AM', '10', 'DEC', '2015'),
(1185, 'Login', 2, 10, 16, 'AM', '10', 'DEC', '2015'),
(1186, 'Login', 2, 10, 17, 'AM', '10', 'DEC', '2015'),
(1187, 'Login', 2, 10, 18, 'AM', '10', 'DEC', '2015'),
(1188, 'Logout', 2, 10, 19, 'AM', '10', 'DEC', '2015'),
(1189, 'Login', 2, 10, 29, 'AM', '10', 'DEC', '2015'),
(1190, 'Login', 2, 11, 28, 'AM', '10', 'DEC', '2015'),
(1191, 'Login', 2, 11, 33, 'AM', '10', 'DEC', '2015'),
(1192, 'Login', 2, 11, 39, 'AM', '10', 'DEC', '2015'),
(1193, 'Login', 2, 11, 48, 'AM', '10', 'DEC', '2015'),
(1194, 'Logout', 2, 11, 50, 'AM', '10', 'DEC', '2015'),
(1195, 'Login', 2, 11, 56, 'AM', '10', 'DEC', '2015'),
(1196, 'Login', 2, 12, 2, 'PM', '10', 'DEC', '2015'),
(1197, 'Logout', 2, 12, 8, 'PM', '10', 'DEC', '2015'),
(1198, 'Login', 2, 12, 9, 'PM', '10', 'DEC', '2015'),
(1199, 'Login', 2, 12, 21, 'PM', '10', 'DEC', '2015'),
(1200, 'Logout', 2, 12, 22, 'PM', '10', 'DEC', '2015'),
(1201, 'Login', 2, 12, 23, 'PM', '10', 'DEC', '2015'),
(1202, 'Logout', 2, 12, 32, 'PM', '10', 'DEC', '2015'),
(1203, 'Login', 2, 12, 33, 'PM', '10', 'DEC', '2015'),
(1204, 'Login', 2, 12, 35, 'PM', '10', 'DEC', '2015'),
(1205, 'Login', 2, 12, 36, 'PM', '10', 'DEC', '2015'),
(1206, 'Login', 2, 12, 42, 'PM', '10', 'DEC', '2015'),
(1207, 'Logout', 2, 12, 49, 'PM', '10', 'DEC', '2015'),
(1208, 'Login', 2, 12, 50, 'PM', '10', 'DEC', '2015'),
(1209, 'Login', 2, 1, 2, 'PM', '10', 'DEC', '2015'),
(1210, 'Login', 2, 1, 8, 'PM', '10', 'DEC', '2015'),
(1211, 'Logout', 2, 1, 9, 'PM', '10', 'DEC', '2015'),
(1212, 'Login', 2, 1, 13, 'PM', '10', 'DEC', '2015'),
(1213, 'Logout', 2, 1, 16, 'PM', '10', 'DEC', '2015'),
(1214, 'Login', 2, 1, 29, 'PM', '11', 'DEC', '2015'),
(1215, 'Login', 2, 1, 32, 'PM', '11', 'DEC', '2015'),
(1216, 'Logout', 2, 1, 33, 'PM', '11', 'DEC', '2015'),
(1217, 'Login', 2, 1, 34, 'PM', '11', 'DEC', '2015'),
(1218, 'Logout', 2, 1, 36, 'PM', '11', 'DEC', '2015'),
(1219, 'Login', 2, 7, 47, 'AM', '12', 'DEC', '2015'),
(1220, 'Logout', 2, 7, 51, 'AM', '12', 'DEC', '2015'),
(1221, 'Login', 2, 7, 55, 'AM', '12', 'DEC', '2015'),
(1222, 'Login', 2, 7, 56, 'AM', '12', 'DEC', '2015'),
(1223, 'Logout', 2, 7, 59, 'AM', '12', 'DEC', '2015'),
(1224, 'Login', 2, 3, 7, 'PM', '13', 'DEC', '2015'),
(1225, 'Logout', 2, 3, 8, 'PM', '13', 'DEC', '2015'),
(1226, 'Login', 2, 3, 31, 'PM', '13', 'DEC', '2015'),
(1227, 'Logout', 2, 3, 33, 'PM', '13', 'DEC', '2015'),
(1228, 'Login', 2, 5, 13, 'PM', '13', 'DEC', '2015'),
(1229, 'Login', 2, 5, 22, 'PM', '13', 'DEC', '2015'),
(1230, 'Logout', 2, 5, 23, 'PM', '13', 'DEC', '2015'),
(1231, 'Login', 2, 5, 24, 'PM', '13', 'DEC', '2015'),
(1232, 'Logout', 2, 5, 25, 'PM', '13', 'DEC', '2015'),
(1233, 'Login', 2, 5, 28, 'PM', '13', 'DEC', '2015'),
(1234, 'Login', 2, 5, 29, 'PM', '13', 'DEC', '2015'),
(1235, 'Logout', 2, 5, 30, 'PM', '13', 'DEC', '2015'),
(1236, 'Login', 2, 5, 31, 'PM', '13', 'DEC', '2015'),
(1237, 'Logout', 2, 5, 33, 'PM', '13', 'DEC', '2015'),
(1238, 'Login', 2, 5, 40, 'PM', '13', 'DEC', '2015'),
(1239, 'Logout', 2, 5, 40, 'PM', '13', 'DEC', '2015'),
(1240, 'Login', 2, 5, 41, 'PM', '13', 'DEC', '2015'),
(1241, 'Login', 2, 5, 41, 'PM', '13', 'DEC', '2015'),
(1242, 'Logout', 2, 5, 45, 'PM', '13', 'DEC', '2015'),
(1243, 'Login', 2, 5, 56, 'PM', '13', 'DEC', '2015'),
(1244, 'Login', 2, 6, 3, 'PM', '13', 'DEC', '2015'),
(1245, 'Login', 2, 6, 5, 'PM', '13', 'DEC', '2015'),
(1246, 'Login', 2, 6, 7, 'PM', '13', 'DEC', '2015'),
(1247, 'Login', 2, 6, 9, 'PM', '13', 'DEC', '2015'),
(1248, 'Logout', 2, 6, 9, 'PM', '13', 'DEC', '2015'),
(1249, 'Login', 2, 6, 10, 'PM', '13', 'DEC', '2015'),
(1250, 'Logout', 2, 6, 10, 'PM', '13', 'DEC', '2015'),
(1251, 'Login', 2, 6, 11, 'PM', '13', 'DEC', '2015'),
(1252, 'Logout', 2, 6, 11, 'PM', '13', 'DEC', '2015'),
(1253, 'Login', 2, 6, 21, 'PM', '13', 'DEC', '2015'),
(1254, 'Login', 2, 6, 22, 'PM', '13', 'DEC', '2015'),
(1255, 'Logout', 2, 6, 22, 'PM', '13', 'DEC', '2015'),
(1256, 'Login', 2, 6, 23, 'PM', '13', 'DEC', '2015'),
(1257, 'Logout', 2, 6, 23, 'PM', '13', 'DEC', '2015'),
(1258, 'Login', 2, 6, 24, 'PM', '13', 'DEC', '2015'),
(1259, 'Login', 2, 6, 25, 'PM', '13', 'DEC', '2015'),
(1260, 'Login', 2, 6, 27, 'PM', '13', 'DEC', '2015'),
(1261, 'Login', 2, 6, 36, 'PM', '13', 'DEC', '2015'),
(1262, 'Login', 2, 6, 41, 'PM', '13', 'DEC', '2015'),
(1263, 'Logout', 2, 6, 41, 'PM', '13', 'DEC', '2015'),
(1264, 'Login', 2, 6, 43, 'PM', '13', 'DEC', '2015'),
(1265, 'Logout', 2, 6, 44, 'PM', '13', 'DEC', '2015'),
(1266, 'Login', 2, 6, 45, 'PM', '13', 'DEC', '2015'),
(1267, 'Logout', 2, 6, 45, 'PM', '13', 'DEC', '2015'),
(1268, 'Login', 2, 6, 46, 'PM', '13', 'DEC', '2015'),
(1269, 'Logout', 2, 6, 46, 'PM', '13', 'DEC', '2015'),
(1270, 'Login', 2, 6, 46, 'PM', '13', 'DEC', '2015'),
(1271, 'Login', 2, 6, 48, 'PM', '13', 'DEC', '2015'),
(1272, 'Logout', 2, 6, 49, 'PM', '13', 'DEC', '2015'),
(1273, 'Login', 2, 6, 49, 'PM', '13', 'DEC', '2015'),
(1274, 'Login', 2, 6, 50, 'PM', '13', 'DEC', '2015'),
(1275, 'Login', 2, 6, 52, 'PM', '13', 'DEC', '2015'),
(1276, 'Login', 2, 6, 54, 'PM', '13', 'DEC', '2015'),
(1277, 'Logout', 2, 6, 55, 'PM', '13', 'DEC', '2015'),
(1278, 'Login', 2, 6, 55, 'PM', '13', 'DEC', '2015'),
(1279, 'Login', 2, 6, 59, 'PM', '13', 'DEC', '2015'),
(1280, 'Logout', 2, 6, 59, 'PM', '13', 'DEC', '2015'),
(1281, 'Login', 2, 7, 1, 'PM', '13', 'DEC', '2015'),
(1282, 'Logout', 2, 7, 2, 'PM', '13', 'DEC', '2015'),
(1283, 'Login', 2, 7, 2, 'PM', '13', 'DEC', '2015'),
(1284, 'Logout', 2, 7, 3, 'PM', '13', 'DEC', '2015'),
(1285, 'Login', 2, 7, 3, 'PM', '13', 'DEC', '2015'),
(1286, 'Logout', 2, 7, 5, 'PM', '13', 'DEC', '2015'),
(1287, 'Login', 2, 7, 6, 'PM', '13', 'DEC', '2015'),
(1288, 'Login', 2, 7, 13, 'PM', '13', 'DEC', '2015'),
(1289, 'Logout', 2, 7, 13, 'PM', '13', 'DEC', '2015'),
(1290, 'Login', 2, 7, 13, 'PM', '13', 'DEC', '2015'),
(1291, 'Logout', 2, 7, 14, 'PM', '13', 'DEC', '2015'),
(1292, 'Login', 2, 7, 17, 'PM', '13', 'DEC', '2015'),
(1293, 'Login', 2, 7, 18, 'PM', '13', 'DEC', '2015'),
(1294, 'Logout', 2, 7, 19, 'PM', '13', 'DEC', '2015'),
(1295, 'Login', 2, 12, 45, 'PM', '14', 'DEC', '2015'),
(1296, 'Login', 2, 12, 48, 'PM', '14', 'DEC', '2015'),
(1297, 'Login', 2, 12, 52, 'PM', '14', 'DEC', '2015'),
(1298, 'Login', 2, 3, 40, 'PM', '14', 'DEC', '2015'),
(1299, 'Login', 2, 3, 49, 'PM', '14', 'DEC', '2015'),
(1300, 'Login', 2, 3, 51, 'PM', '14', 'DEC', '2015'),
(1301, 'Login', 2, 4, 3, 'PM', '14', 'DEC', '2015'),
(1302, 'Logout', 2, 4, 4, 'PM', '14', 'DEC', '2015'),
(1303, 'Login', 2, 4, 4, 'PM', '14', 'DEC', '2015'),
(1304, 'Login', 2, 6, 46, 'PM', '14', 'DEC', '2015'),
(1305, 'Logout', 2, 6, 48, 'PM', '14', 'DEC', '2015'),
(1306, 'Login', 2, 6, 48, 'PM', '14', 'DEC', '2015'),
(1307, 'Logout', 2, 6, 49, 'PM', '14', 'DEC', '2015'),
(1308, 'Login', 2, 6, 49, 'PM', '14', 'DEC', '2015'),
(1309, 'Login', 2, 6, 56, 'PM', '14', 'DEC', '2015'),
(1310, 'Logout', 2, 6, 57, 'PM', '14', 'DEC', '2015'),
(1311, 'Login', 2, 6, 57, 'PM', '14', 'DEC', '2015'),
(1312, 'Logout', 2, 7, 3, 'PM', '14', 'DEC', '2015'),
(1313, 'Login', 2, 7, 6, 'PM', '14', 'DEC', '2015'),
(1314, 'Logout', 2, 7, 7, 'PM', '14', 'DEC', '2015'),
(1315, 'Login', 2, 7, 7, 'PM', '14', 'DEC', '2015'),
(1316, 'Logout', 2, 7, 8, 'PM', '14', 'DEC', '2015'),
(1317, 'Login', 2, 7, 9, 'PM', '14', 'DEC', '2015'),
(1318, 'Login', 2, 7, 9, 'PM', '14', 'DEC', '2015'),
(1319, 'Logout', 2, 7, 11, 'PM', '14', 'DEC', '2015'),
(1320, 'Login', 2, 7, 12, 'PM', '14', 'DEC', '2015'),
(1321, 'Login', 2, 7, 23, 'AM', '15', 'DEC', '2015'),
(1322, 'Login', 2, 7, 23, 'AM', '15', 'DEC', '2015'),
(1323, 'Login', 2, 7, 25, 'AM', '15', 'DEC', '2015'),
(1324, 'Login', 2, 7, 29, 'AM', '15', 'DEC', '2015'),
(1325, 'Login', 2, 7, 30, 'AM', '15', 'DEC', '2015'),
(1326, 'Login', 2, 7, 39, 'AM', '15', 'DEC', '2015'),
(1327, 'Login', 2, 7, 42, 'AM', '15', 'DEC', '2015'),
(1328, 'Login', 2, 7, 44, 'AM', '15', 'DEC', '2015'),
(1329, 'Login', 2, 7, 50, 'AM', '15', 'DEC', '2015'),
(1330, 'Logout', 2, 7, 58, 'AM', '15', 'DEC', '2015'),
(1331, 'Login', 2, 7, 59, 'AM', '15', 'DEC', '2015'),
(1332, 'Login', 2, 11, 21, 'AM', '15', 'DEC', '2015'),
(1333, 'Logout', 2, 11, 22, 'AM', '15', 'DEC', '2015'),
(1334, 'Login', 2, 9, 6, 'AM', '16', 'DEC', '2015'),
(1335, 'Logout', 2, 9, 9, 'AM', '16', 'DEC', '2015'),
(1336, 'Login', 2, 9, 13, 'AM', '16', 'DEC', '2015'),
(1337, 'Logout', 2, 9, 16, 'AM', '16', 'DEC', '2015'),
(1338, 'Login', 2, 9, 59, 'AM', '16', 'DEC', '2015'),
(1339, 'Login', 2, 10, 1, 'AM', '16', 'DEC', '2015'),
(1340, 'Login', 2, 10, 7, 'AM', '16', 'DEC', '2015'),
(1341, 'Login', 2, 10, 12, 'AM', '16', 'DEC', '2015'),
(1342, 'Login', 2, 10, 14, 'AM', '16', 'DEC', '2015'),
(1343, 'Login', 2, 10, 15, 'AM', '16', 'DEC', '2015'),
(1344, 'Login', 2, 12, 23, 'PM', '16', 'DEC', '2015'),
(1345, 'Logout', 2, 12, 23, 'PM', '16', 'DEC', '2015'),
(1346, 'Login', 2, 12, 29, 'PM', '16', 'DEC', '2015'),
(1347, 'Logout', 2, 12, 29, 'PM', '16', 'DEC', '2015'),
(1348, 'Login', 2, 12, 30, 'PM', '16', 'DEC', '2015'),
(1349, 'Logout', 2, 12, 30, 'PM', '16', 'DEC', '2015'),
(1350, 'Login', 2, 12, 32, 'PM', '16', 'DEC', '2015'),
(1351, 'Logout', 2, 12, 32, 'PM', '16', 'DEC', '2015'),
(1352, 'Login', 2, 12, 35, 'PM', '16', 'DEC', '2015'),
(1353, 'Login', 2, 12, 51, 'PM', '16', 'DEC', '2015'),
(1354, 'Login', 2, 12, 56, 'PM', '16', 'DEC', '2015'),
(1355, 'Logout', 2, 1, 1, 'PM', '16', 'DEC', '2015'),
(1356, 'Login', 2, 1, 1, 'PM', '16', 'DEC', '2015'),
(1357, 'Logout', 2, 1, 2, 'PM', '16', 'DEC', '2015'),
(1358, 'Login', 2, 1, 3, 'PM', '16', 'DEC', '2015'),
(1359, 'Logout', 2, 1, 7, 'PM', '16', 'DEC', '2015'),
(1360, 'Login', 2, 1, 14, 'PM', '16', 'DEC', '2015'),
(1361, 'Logout', 2, 1, 16, 'PM', '16', 'DEC', '2015'),
(1362, 'Login', 2, 9, 32, 'PM', '19', 'DEC', '2015'),
(1363, 'Login', 2, 11, 29, 'AM', '20', 'DEC', '2015'),
(1364, 'Login', 2, 11, 34, 'AM', '20', 'DEC', '2015'),
(1365, 'Logout', 2, 11, 37, 'AM', '20', 'DEC', '2015'),
(1366, 'Login', 2, 11, 44, 'AM', '20', 'DEC', '2015'),
(1367, 'Login', 2, 11, 45, 'AM', '20', 'DEC', '2015'),
(1368, 'Login', 2, 11, 47, 'AM', '20', 'DEC', '2015'),
(1369, 'Login', 2, 11, 57, 'AM', '20', 'DEC', '2015'),
(1370, 'Login', 2, 11, 59, 'AM', '20', 'DEC', '2015'),
(1371, 'Login', 2, 12, 6, 'PM', '20', 'DEC', '2015'),
(1372, 'Login', 2, 12, 9, 'PM', '20', 'DEC', '2015'),
(1373, 'Logout', 2, 12, 10, 'PM', '20', 'DEC', '2015'),
(1374, 'Login', 2, 1, 31, 'PM', '20', 'DEC', '2015'),
(1375, 'Login', 2, 1, 32, 'PM', '20', 'DEC', '2015'),
(1376, 'Logout', 2, 1, 33, 'PM', '20', 'DEC', '2015'),
(1377, 'Login', 2, 1, 41, 'PM', '20', 'DEC', '2015'),
(1378, 'Logout', 2, 1, 41, 'PM', '20', 'DEC', '2015'),
(1379, 'Login', 2, 1, 42, 'PM', '20', 'DEC', '2015'),
(1380, 'Logout', 2, 1, 42, 'PM', '20', 'DEC', '2015'),
(1381, 'Login', 2, 1, 47, 'PM', '20', 'DEC', '2015'),
(1382, 'Logout', 2, 1, 47, 'PM', '20', 'DEC', '2015'),
(1383, 'Login', 2, 1, 55, 'PM', '20', 'DEC', '2015'),
(1384, 'Logout', 2, 1, 56, 'PM', '20', 'DEC', '2015'),
(1385, 'Login', 2, 1, 59, 'PM', '20', 'DEC', '2015'),
(1386, 'Login', 2, 1, 59, 'PM', '20', 'DEC', '2015'),
(1387, 'Login', 2, 2, 0, 'PM', '20', 'DEC', '2015'),
(1388, 'Logout', 2, 2, 1, 'PM', '20', 'DEC', '2015'),
(1389, 'Login', 2, 2, 5, 'PM', '20', 'DEC', '2015'),
(1390, 'Logout', 2, 2, 5, 'PM', '20', 'DEC', '2015'),
(1391, 'Login', 2, 2, 5, 'PM', '20', 'DEC', '2015'),
(1392, 'Logout', 2, 2, 6, 'PM', '20', 'DEC', '2015'),
(1393, 'Login', 2, 2, 6, 'PM', '20', 'DEC', '2015'),
(1394, 'Logout', 2, 2, 6, 'PM', '20', 'DEC', '2015'),
(1395, 'Login', 2, 2, 10, 'PM', '20', 'DEC', '2015'),
(1396, 'Logout', 2, 2, 10, 'PM', '20', 'DEC', '2015'),
(1397, 'Login', 2, 2, 11, 'PM', '20', 'DEC', '2015'),
(1398, 'Login', 2, 2, 15, 'PM', '20', 'DEC', '2015'),
(1399, 'Login', 2, 2, 16, 'PM', '20', 'DEC', '2015'),
(1400, 'Login', 2, 2, 18, 'PM', '20', 'DEC', '2015'),
(1401, 'Login', 2, 2, 19, 'PM', '20', 'DEC', '2015'),
(1402, 'Login', 2, 2, 20, 'PM', '20', 'DEC', '2015'),
(1403, 'Login', 2, 2, 26, 'PM', '20', 'DEC', '2015'),
(1404, 'Logout', 2, 2, 27, 'PM', '20', 'DEC', '2015'),
(1405, 'Login', 2, 2, 29, 'PM', '20', 'DEC', '2015'),
(1406, 'Login', 2, 2, 31, 'PM', '20', 'DEC', '2015'),
(1407, 'Login', 2, 2, 32, 'PM', '20', 'DEC', '2015'),
(1408, 'Logout', 2, 2, 32, 'PM', '20', 'DEC', '2015'),
(1409, 'Login', 2, 2, 34, 'PM', '20', 'DEC', '2015'),
(1410, 'Logout', 2, 2, 35, 'PM', '20', 'DEC', '2015'),
(1411, 'Login', 2, 2, 37, 'PM', '20', 'DEC', '2015'),
(1412, 'Login', 2, 2, 40, 'PM', '20', 'DEC', '2015'),
(1413, 'Logout', 2, 2, 40, 'PM', '20', 'DEC', '2015'),
(1414, 'Login', 2, 3, 3, 'PM', '20', 'DEC', '2015'),
(1415, 'Logout', 2, 3, 4, 'PM', '20', 'DEC', '2015'),
(1416, 'Login', 2, 3, 7, 'PM', '20', 'DEC', '2015'),
(1417, 'Logout', 2, 3, 8, 'PM', '20', 'DEC', '2015'),
(1418, 'Login', 2, 3, 8, 'PM', '20', 'DEC', '2015'),
(1419, 'Logout', 2, 3, 9, 'PM', '20', 'DEC', '2015'),
(1420, 'Login', 2, 3, 11, 'PM', '20', 'DEC', '2015'),
(1421, 'Login', 2, 3, 29, 'PM', '20', 'DEC', '2015'),
(1422, 'Logout', 2, 3, 30, 'PM', '20', 'DEC', '2015'),
(1423, 'Login', 2, 3, 31, 'PM', '20', 'DEC', '2015'),
(1424, 'Logout', 2, 3, 32, 'PM', '20', 'DEC', '2015'),
(1425, 'Login', 2, 3, 35, 'PM', '20', 'DEC', '2015'),
(1426, 'Login', 2, 3, 37, 'PM', '20', 'DEC', '2015'),
(1427, 'Login', 2, 3, 40, 'PM', '20', 'DEC', '2015'),
(1428, 'Logout', 2, 3, 41, 'PM', '20', 'DEC', '2015'),
(1429, 'Login', 2, 3, 42, 'PM', '20', 'DEC', '2015'),
(1430, 'Logout', 2, 3, 47, 'PM', '20', 'DEC', '2015'),
(1431, 'Login', 2, 3, 54, 'PM', '20', 'DEC', '2015'),
(1432, 'Login', 2, 4, 26, 'PM', '20', 'DEC', '2015'),
(1433, 'Logout', 2, 4, 27, 'PM', '20', 'DEC', '2015'),
(1434, 'Login', 2, 4, 28, 'PM', '20', 'DEC', '2015'),
(1435, 'Logout', 2, 4, 29, 'PM', '20', 'DEC', '2015'),
(1436, 'Login', 2, 4, 31, 'PM', '20', 'DEC', '2015'),
(1437, 'Login', 2, 4, 32, 'PM', '20', 'DEC', '2015'),
(1438, 'Login', 2, 4, 36, 'PM', '20', 'DEC', '2015'),
(1439, 'Login', 2, 4, 40, 'PM', '20', 'DEC', '2015'),
(1440, 'Logout', 2, 4, 41, 'PM', '20', 'DEC', '2015'),
(1441, 'Login', 2, 4, 43, 'PM', '20', 'DEC', '2015'),
(1442, 'Login', 2, 4, 52, 'PM', '20', 'DEC', '2015'),
(1443, 'Login', 2, 4, 53, 'PM', '20', 'DEC', '2015'),
(1444, 'Logout', 2, 4, 54, 'PM', '20', 'DEC', '2015'),
(1445, 'Login', 2, 5, 3, 'PM', '20', 'DEC', '2015'),
(1446, 'Login', 2, 5, 5, 'PM', '20', 'DEC', '2015'),
(1447, 'Login', 2, 5, 11, 'PM', '20', 'DEC', '2015'),
(1448, 'Login', 2, 5, 12, 'PM', '20', 'DEC', '2015'),
(1449, 'Logout', 2, 5, 13, 'PM', '20', 'DEC', '2015'),
(1450, 'Login', 2, 5, 25, 'PM', '20', 'DEC', '2015'),
(1451, 'Logout', 2, 5, 26, 'PM', '20', 'DEC', '2015'),
(1452, 'Login', 2, 5, 28, 'PM', '20', 'DEC', '2015'),
(1453, 'Login', 2, 5, 33, 'PM', '20', 'DEC', '2015'),
(1454, 'Login', 2, 5, 40, 'PM', '20', 'DEC', '2015'),
(1455, 'Logout', 2, 5, 44, 'PM', '20', 'DEC', '2015'),
(1456, 'Login', 2, 5, 44, 'PM', '20', 'DEC', '2015'),
(1457, 'Logout', 2, 5, 45, 'PM', '20', 'DEC', '2015'),
(1458, 'Login', 2, 5, 49, 'PM', '20', 'DEC', '2015'),
(1459, 'Logout', 2, 5, 50, 'PM', '20', 'DEC', '2015'),
(1460, 'Login', 2, 6, 3, 'PM', '20', 'DEC', '2015'),
(1461, 'Logout', 2, 6, 6, 'PM', '20', 'DEC', '2015'),
(1462, 'Login', 2, 6, 11, 'PM', '20', 'DEC', '2015'),
(1463, 'Logout', 2, 6, 11, 'PM', '20', 'DEC', '2015'),
(1464, 'Login', 2, 6, 12, 'PM', '20', 'DEC', '2015'),
(1465, 'Login', 2, 6, 15, 'PM', '20', 'DEC', '2015'),
(1466, 'Login', 2, 6, 17, 'PM', '20', 'DEC', '2015'),
(1467, 'Logout', 2, 6, 18, 'PM', '20', 'DEC', '2015'),
(1468, 'Login', 2, 6, 20, 'PM', '20', 'DEC', '2015'),
(1469, 'Logout', 2, 6, 20, 'PM', '20', 'DEC', '2015'),
(1470, 'Login', 2, 6, 24, 'PM', '20', 'DEC', '2015'),
(1471, 'Login', 2, 6, 26, 'PM', '20', 'DEC', '2015'),
(1472, 'Logout', 2, 6, 27, 'PM', '20', 'DEC', '2015'),
(1473, 'Login', 2, 6, 28, 'PM', '20', 'DEC', '2015'),
(1474, 'Login', 2, 6, 29, 'PM', '20', 'DEC', '2015'),
(1475, 'Login', 2, 6, 31, 'PM', '20', 'DEC', '2015'),
(1476, 'Logout', 2, 6, 35, 'PM', '20', 'DEC', '2015'),
(1477, 'Login', 2, 6, 35, 'PM', '20', 'DEC', '2015'),
(1478, 'Logout', 2, 6, 36, 'PM', '20', 'DEC', '2015'),
(1479, 'Login', 2, 6, 36, 'PM', '20', 'DEC', '2015'),
(1480, 'Login', 2, 6, 38, 'PM', '20', 'DEC', '2015'),
(1481, 'Logout', 2, 6, 39, 'PM', '20', 'DEC', '2015'),
(1482, 'Login', 2, 6, 39, 'PM', '20', 'DEC', '2015'),
(1483, 'Login', 2, 6, 41, 'PM', '20', 'DEC', '2015'),
(1484, 'Logout', 2, 6, 43, 'PM', '20', 'DEC', '2015'),
(1485, 'Login', 2, 7, 14, 'PM', '20', 'DEC', '2015'),
(1486, 'Logout', 2, 7, 15, 'PM', '20', 'DEC', '2015'),
(1487, 'Login', 2, 7, 16, 'PM', '20', 'DEC', '2015'),
(1488, 'Login', 2, 7, 24, 'PM', '20', 'DEC', '2015'),
(1489, 'Login', 2, 10, 1, 'PM', '20', 'DEC', '2015'),
(1490, 'Logout', 2, 10, 5, 'PM', '20', 'DEC', '2015'),
(1491, 'Login', 2, 10, 8, 'PM', '20', 'DEC', '2015'),
(1492, 'Login', 2, 10, 11, 'PM', '20', 'DEC', '2015'),
(1493, 'Login', 2, 10, 11, 'PM', '20', 'DEC', '2015'),
(1494, 'Login', 2, 10, 13, 'PM', '20', 'DEC', '2015'),
(1495, 'Login', 2, 10, 14, 'PM', '20', 'DEC', '2015'),
(1496, 'Logout', 2, 10, 15, 'PM', '20', 'DEC', '2015'),
(1497, 'Login', 2, 10, 17, 'PM', '20', 'DEC', '2015'),
(1498, 'Logout', 2, 10, 18, 'PM', '20', 'DEC', '2015'),
(1499, 'Login', 2, 10, 23, 'PM', '20', 'DEC', '2015'),
(1500, 'Logout', 2, 10, 25, 'PM', '20', 'DEC', '2015'),
(1501, 'Login', 2, 10, 26, 'PM', '20', 'DEC', '2015'),
(1502, 'Login', 2, 10, 29, 'PM', '20', 'DEC', '2015'),
(1503, 'Login', 2, 7, 6, 'AM', '21', 'DEC', '2015'),
(1504, 'Login', 2, 7, 8, 'AM', '21', 'DEC', '2015'),
(1505, 'Login', 2, 7, 10, 'AM', '21', 'DEC', '2015'),
(1506, 'Logout', 2, 7, 11, 'AM', '21', 'DEC', '2015'),
(1507, 'Login', 2, 7, 13, 'AM', '21', 'DEC', '2015'),
(1508, 'Logout', 2, 7, 14, 'AM', '21', 'DEC', '2015'),
(1509, 'Login', 2, 7, 15, 'AM', '21', 'DEC', '2015'),
(1510, 'Login', 2, 7, 25, 'AM', '21', 'DEC', '2015'),
(1511, 'Login', 2, 7, 27, 'AM', '21', 'DEC', '2015'),
(1512, 'Login', 2, 7, 28, 'AM', '21', 'DEC', '2015'),
(1513, 'Login', 2, 7, 30, 'AM', '21', 'DEC', '2015'),
(1514, 'Login', 2, 7, 35, 'AM', '21', 'DEC', '2015'),
(1515, 'Login', 2, 7, 37, 'AM', '21', 'DEC', '2015'),
(1516, 'Login', 2, 7, 40, 'AM', '21', 'DEC', '2015'),
(1517, 'Login', 2, 7, 45, 'AM', '21', 'DEC', '2015'),
(1518, 'Login', 2, 7, 50, 'AM', '21', 'DEC', '2015'),
(1519, 'Login', 2, 7, 58, 'AM', '21', 'DEC', '2015'),
(1520, 'Login', 2, 8, 2, 'AM', '21', 'DEC', '2015'),
(1521, 'Login', 2, 8, 3, 'AM', '21', 'DEC', '2015'),
(1522, 'Logout', 2, 8, 5, 'AM', '21', 'DEC', '2015'),
(1523, 'Login', 2, 8, 7, 'AM', '21', 'DEC', '2015'),
(1524, 'Login', 2, 8, 9, 'AM', '21', 'DEC', '2015'),
(1525, 'Login', 2, 8, 11, 'AM', '21', 'DEC', '2015'),
(1526, 'Login', 2, 8, 14, 'AM', '21', 'DEC', '2015'),
(1527, 'Login', 2, 8, 15, 'AM', '21', 'DEC', '2015'),
(1528, 'Login', 2, 8, 17, 'AM', '21', 'DEC', '2015'),
(1529, 'Login', 2, 8, 20, 'AM', '21', 'DEC', '2015'),
(1530, 'Login', 2, 10, 32, 'AM', '21', 'DEC', '2015'),
(1531, 'Login', 2, 10, 36, 'AM', '21', 'DEC', '2015'),
(1532, 'Login', 2, 10, 38, 'AM', '21', 'DEC', '2015'),
(1533, 'Login', 2, 10, 45, 'AM', '21', 'DEC', '2015'),
(1534, 'Logout', 2, 11, 8, 'AM', '21', 'DEC', '2015'),
(1535, 'Login', 2, 11, 8, 'AM', '21', 'DEC', '2015'),
(1536, 'Login', 2, 11, 30, 'AM', '21', 'DEC', '2015'),
(1537, 'Logout', 2, 11, 31, 'AM', '21', 'DEC', '2015'),
(1538, 'Login', 2, 11, 33, 'AM', '21', 'DEC', '2015'),
(1539, 'Login', 2, 11, 37, 'AM', '21', 'DEC', '2015'),
(1540, 'Login', 2, 12, 31, 'PM', '21', 'DEC', '2015'),
(1541, 'Login', 2, 12, 38, 'PM', '21', 'DEC', '2015'),
(1542, 'Login', 2, 12, 40, 'PM', '21', 'DEC', '2015'),
(1543, 'Login', 2, 12, 42, 'PM', '21', 'DEC', '2015'),
(1544, 'Login', 2, 12, 44, 'PM', '21', 'DEC', '2015'),
(1545, 'Login', 2, 12, 50, 'PM', '21', 'DEC', '2015'),
(1546, 'Login', 2, 12, 54, 'PM', '21', 'DEC', '2015'),
(1547, 'Logout', 2, 1, 5, 'PM', '21', 'DEC', '2015'),
(1548, 'Login', 2, 1, 51, 'PM', '21', 'DEC', '2015'),
(1549, 'Logout', 2, 1, 53, 'PM', '21', 'DEC', '2015'),
(1550, 'Login', 2, 2, 4, 'PM', '21', 'DEC', '2015'),
(1551, 'Login', 2, 2, 7, 'PM', '21', 'DEC', '2015'),
(1552, 'Logout', 2, 2, 8, 'PM', '21', 'DEC', '2015'),
(1553, 'Login', 2, 2, 24, 'PM', '21', 'DEC', '2015'),
(1554, 'Logout', 2, 2, 25, 'PM', '21', 'DEC', '2015'),
(1555, 'Login', 2, 2, 31, 'PM', '21', 'DEC', '2015'),
(1556, 'Logout', 2, 2, 35, 'PM', '21', 'DEC', '2015'),
(1557, 'Login', 2, 3, 25, 'PM', '21', 'DEC', '2015'),
(1558, 'Logout', 2, 3, 25, 'PM', '21', 'DEC', '2015'),
(1559, 'Login', 1, 3, 26, 'PM', '21', 'DEC', '2015'),
(1560, 'Logout', 1, 3, 26, 'PM', '21', 'DEC', '2015'),
(1561, 'Login', 2, 1, 50, 'AM', '22', 'DEC', '2015'),
(1562, 'Login', 2, 1, 51, 'AM', '22', 'DEC', '2015'),
(1563, 'Login', 1, 1, 54, 'AM', '22', 'DEC', '2015'),
(1564, 'Login', 2, 9, 37, 'AM', '22', 'DEC', '2015'),
(1565, 'Login', 2, 9, 43, 'AM', '22', 'DEC', '2015'),
(1566, 'Login', 2, 9, 44, 'AM', '22', 'DEC', '2015'),
(1567, 'Logout', 2, 9, 45, 'AM', '22', 'DEC', '2015'),
(1568, 'Login', 2, 9, 48, 'AM', '22', 'DEC', '2015'),
(1569, 'Logout', 2, 9, 51, 'AM', '22', 'DEC', '2015'),
(1570, 'Login', 2, 10, 7, 'AM', '22', 'DEC', '2015'),
(1571, 'Logout', 2, 10, 7, 'AM', '22', 'DEC', '2015'),
(1572, 'Login', 2, 10, 10, 'AM', '22', 'DEC', '2015'),
(1573, 'Logout', 2, 10, 11, 'AM', '22', 'DEC', '2015'),
(1574, 'Login', 2, 10, 13, 'AM', '22', 'DEC', '2015'),
(1575, 'Login', 2, 10, 14, 'AM', '22', 'DEC', '2015'),
(1576, 'Login', 2, 10, 16, 'AM', '22', 'DEC', '2015'),
(1577, 'Login', 2, 10, 17, 'AM', '22', 'DEC', '2015'),
(1578, 'Logout', 2, 10, 18, 'AM', '22', 'DEC', '2015'),
(1579, 'Login', 2, 10, 21, 'AM', '22', 'DEC', '2015'),
(1580, 'Login', 2, 10, 22, 'AM', '22', 'DEC', '2015'),
(1581, 'Logout', 2, 10, 22, 'AM', '22', 'DEC', '2015'),
(1582, 'Login', 2, 9, 44, 'AM', '23', 'DEC', '2015'),
(1583, 'Logout', 2, 9, 46, 'AM', '23', 'DEC', '2015'),
(1584, 'Login', 2, 9, 47, 'AM', '23', 'DEC', '2015'),
(1585, 'Logout', 2, 9, 55, 'AM', '23', 'DEC', '2015'),
(1586, 'Login', 2, 9, 55, 'AM', '23', 'DEC', '2015'),
(1587, 'Logout', 2, 10, 8, 'AM', '23', 'DEC', '2015'),
(1588, 'Login', 2, 10, 12, 'AM', '23', 'DEC', '2015'),
(1589, 'Login', 2, 10, 17, 'AM', '23', 'DEC', '2015'),
(1590, 'Login', 2, 10, 19, 'AM', '23', 'DEC', '2015'),
(1591, 'Logout', 2, 10, 21, 'AM', '23', 'DEC', '2015'),
(1592, 'Login', 2, 11, 23, 'AM', '23', 'DEC', '2015'),
(1593, 'Logout', 2, 11, 26, 'AM', '23', 'DEC', '2015'),
(1594, 'Login', 2, 11, 29, 'AM', '23', 'DEC', '2015'),
(1595, 'Login', 2, 11, 35, 'AM', '23', 'DEC', '2015'),
(1596, 'Login', 2, 11, 39, 'AM', '23', 'DEC', '2015'),
(1597, 'Logout', 2, 11, 40, 'AM', '23', 'DEC', '2015'),
(1598, 'Login', 2, 12, 8, 'PM', '23', 'DEC', '2015'),
(1599, 'Login', 2, 12, 8, 'PM', '23', 'DEC', '2015'),
(1600, 'Logout', 2, 12, 13, 'PM', '23', 'DEC', '2015'),
(1601, 'Login', 2, 12, 16, 'PM', '23', 'DEC', '2015'),
(1602, 'Logout', 2, 12, 19, 'PM', '23', 'DEC', '2015'),
(1603, 'Login', 2, 12, 19, 'PM', '23', 'DEC', '2015'),
(1604, 'Login', 2, 12, 23, 'PM', '23', 'DEC', '2015'),
(1605, 'Logout', 2, 12, 24, 'PM', '23', 'DEC', '2015'),
(1606, 'Login', 2, 12, 25, 'PM', '23', 'DEC', '2015'),
(1607, 'Logout', 2, 12, 28, 'PM', '23', 'DEC', '2015'),
(1608, 'Login', 2, 12, 40, 'PM', '23', 'DEC', '2015'),
(1609, 'Login', 2, 12, 44, 'PM', '23', 'DEC', '2015'),
(1610, 'Login', 2, 12, 48, 'PM', '23', 'DEC', '2015'),
(1611, 'Login', 2, 12, 52, 'PM', '23', 'DEC', '2015'),
(1612, 'Login', 2, 12, 54, 'PM', '23', 'DEC', '2015'),
(1613, 'Logout', 2, 12, 56, 'PM', '23', 'DEC', '2015'),
(1614, 'Login', 2, 12, 57, 'PM', '23', 'DEC', '2015'),
(1615, 'Login', 2, 1, 2, 'PM', '23', 'DEC', '2015'),
(1616, 'Login', 2, 1, 10, 'PM', '23', 'DEC', '2015'),
(1617, 'Logout', 2, 1, 13, 'PM', '23', 'DEC', '2015'),
(1618, 'Login', 2, 1, 29, 'PM', '23', 'DEC', '2015'),
(1619, 'Logout', 2, 1, 31, 'PM', '23', 'DEC', '2015'),
(1620, 'Login', 2, 1, 32, 'PM', '23', 'DEC', '2015'),
(1621, 'Logout', 2, 1, 34, 'PM', '23', 'DEC', '2015'),
(1622, 'Login', 2, 1, 45, 'PM', '23', 'DEC', '2015'),
(1623, 'Logout', 2, 1, 46, 'PM', '23', 'DEC', '2015'),
(1624, 'Login', 2, 1, 48, 'PM', '23', 'DEC', '2015'),
(1625, 'Login', 2, 1, 53, 'PM', '23', 'DEC', '2015'),
(1626, 'Logout', 2, 1, 55, 'PM', '23', 'DEC', '2015'),
(1627, 'Login', 2, 2, 40, 'PM', '23', 'DEC', '2015'),
(1628, 'Login', 2, 2, 42, 'PM', '23', 'DEC', '2015'),
(1629, 'Login', 2, 2, 47, 'PM', '23', 'DEC', '2015'),
(1630, 'Login', 2, 2, 50, 'PM', '23', 'DEC', '2015'),
(1631, 'Login', 2, 2, 55, 'PM', '23', 'DEC', '2015'),
(1632, 'Login', 2, 3, 4, 'PM', '23', 'DEC', '2015'),
(1633, 'Login', 2, 3, 10, 'PM', '23', 'DEC', '2015'),
(1634, 'Logout', 2, 4, 3, 'PM', '23', 'DEC', '2015'),
(1635, 'Login', 2, 5, 31, 'PM', '23', 'DEC', '2015'),
(1636, 'Logout', 2, 5, 38, 'PM', '23', 'DEC', '2015'),
(1637, 'Login', 2, 5, 39, 'PM', '23', 'DEC', '2015'),
(1638, 'Logout', 2, 5, 39, 'PM', '23', 'DEC', '2015'),
(1639, 'Login', 2, 10, 19, 'PM', '23', 'DEC', '2015'),
(1640, 'Login', 2, 10, 28, 'PM', '23', 'DEC', '2015'),
(1641, 'Logout', 2, 10, 30, 'PM', '23', 'DEC', '2015'),
(1642, 'Login', 2, 10, 43, 'PM', '23', 'DEC', '2015'),
(1643, 'Login', 2, 5, 17, 'PM', '24', 'DEC', '2015'),
(1644, 'Logout', 2, 5, 21, 'PM', '24', 'DEC', '2015'),
(1645, 'Login', 2, 6, 32, 'AM', '26', 'DEC', '2015'),
(1646, 'Login', 2, 6, 39, 'AM', '26', 'DEC', '2015'),
(1647, 'Login', 2, 6, 44, 'AM', '26', 'DEC', '2015'),
(1648, 'Login', 2, 6, 46, 'AM', '26', 'DEC', '2015'),
(1649, 'Logout', 2, 6, 48, 'AM', '26', 'DEC', '2015'),
(1650, 'Login', 2, 7, 2, 'AM', '26', 'DEC', '2015'),
(1651, 'Logout', 2, 7, 4, 'AM', '26', 'DEC', '2015'),
(1652, 'Login', 2, 5, 32, 'PM', '26', 'DEC', '2015'),
(1653, 'Login', 2, 5, 40, 'PM', '26', 'DEC', '2015'),
(1654, 'Login', 2, 5, 42, 'PM', '26', 'DEC', '2015'),
(1655, 'Logout', 2, 5, 43, 'PM', '26', 'DEC', '2015'),
(1656, 'Login', 2, 5, 47, 'PM', '26', 'DEC', '2015'),
(1657, 'Login', 2, 5, 59, 'PM', '26', 'DEC', '2015'),
(1658, 'Login', 2, 6, 1, 'PM', '26', 'DEC', '2015'),
(1659, 'Logout', 2, 6, 1, 'PM', '26', 'DEC', '2015'),
(1660, 'Login', 2, 6, 12, 'PM', '26', 'DEC', '2015'),
(1661, 'Login', 2, 6, 15, 'PM', '26', 'DEC', '2015'),
(1662, 'Logout', 2, 6, 16, 'PM', '26', 'DEC', '2015'),
(1663, 'Login', 2, 6, 25, 'PM', '26', 'DEC', '2015'),
(1664, 'Logout', 2, 6, 27, 'PM', '26', 'DEC', '2015'),
(1665, 'Login', 2, 6, 33, 'PM', '26', 'DEC', '2015'),
(1666, 'Logout', 2, 6, 35, 'PM', '26', 'DEC', '2015'),
(1667, 'Login', 2, 8, 3, 'PM', '26', 'DEC', '2015'),
(1668, 'Login', 2, 8, 6, 'PM', '26', 'DEC', '2015'),
(1669, 'Login', 2, 9, 26, 'PM', '26', 'DEC', '2015'),
(1670, 'Logout', 2, 9, 28, 'PM', '26', 'DEC', '2015'),
(1671, 'Login', 2, 1, 13, 'PM', '2', 'JAN', '2016'),
(1672, 'Login', 2, 1, 18, 'PM', '2', 'JAN', '2016'),
(1673, 'Logout', 2, 1, 19, 'PM', '2', 'JAN', '2016'),
(1674, 'Login', 2, 1, 21, 'PM', '2', 'JAN', '2016'),
(1675, 'Logout', 2, 1, 25, 'PM', '2', 'JAN', '2016'),
(1676, 'Login', 2, 2, 43, 'PM', '3', 'JAN', '2016'),
(1677, 'Logout', 2, 2, 46, 'PM', '3', 'JAN', '2016'),
(1678, 'Login', 2, 4, 22, 'PM', '3', 'JAN', '2016'),
(1679, 'Logout', 2, 4, 24, 'PM', '3', 'JAN', '2016'),
(1680, 'Login', 2, 9, 5, 'PM', '3', 'JAN', '2016'),
(1681, 'Login', 2, 9, 7, 'PM', '3', 'JAN', '2016'),
(1682, 'Logout', 2, 9, 9, 'PM', '3', 'JAN', '2016'),
(1683, 'Login', 2, 9, 18, 'PM', '3', 'JAN', '2016'),
(1684, 'Login', 2, 9, 21, 'PM', '3', 'JAN', '2016'),
(1685, 'Logout', 2, 9, 21, 'PM', '3', 'JAN', '2016'),
(1686, 'Login', 2, 11, 26, 'PM', '3', 'JAN', '2016'),
(1687, 'Login', 2, 11, 36, 'PM', '3', 'JAN', '2016'),
(1688, 'Login', 2, 11, 39, 'PM', '3', 'JAN', '2016'),
(1689, 'Login', 2, 11, 53, 'PM', '3', 'JAN', '2016'),
(1690, 'Login', 2, 11, 58, 'PM', '3', 'JAN', '2016'),
(1691, 'Login', 2, 12, 2, 'AM', '4', 'JAN', '2016'),
(1692, 'Login', 2, 12, 12, 'AM', '4', 'JAN', '2016'),
(1693, 'Logout', 2, 7, 34, 'AM', '4', 'JAN', '2016'),
(1694, 'Login', 2, 7, 38, 'AM', '4', 'JAN', '2016'),
(1695, 'Login', 2, 7, 51, 'AM', '4', 'JAN', '2016'),
(1696, 'Login', 2, 7, 54, 'AM', '4', 'JAN', '2016'),
(1697, 'Login', 2, 7, 56, 'AM', '4', 'JAN', '2016'),
(1698, 'Login', 2, 8, 0, 'AM', '4', 'JAN', '2016'),
(1699, 'Login', 2, 8, 23, 'AM', '4', 'JAN', '2016'),
(1700, 'Login', 2, 8, 26, 'AM', '4', 'JAN', '2016'),
(1701, 'Login', 2, 8, 30, 'AM', '4', 'JAN', '2016'),
(1702, 'Login', 2, 8, 32, 'AM', '4', 'JAN', '2016'),
(1703, 'Login', 2, 3, 30, 'PM', '4', 'JAN', '2016'),
(1704, 'Login', 2, 3, 33, 'PM', '4', 'JAN', '2016'),
(1705, 'Login', 2, 3, 37, 'PM', '4', 'JAN', '2016'),
(1706, 'Login', 2, 4, 26, 'PM', '4', 'JAN', '2016'),
(1707, 'Logout', 2, 4, 27, 'PM', '4', 'JAN', '2016'),
(1708, 'Login', 2, 4, 44, 'PM', '4', 'JAN', '2016'),
(1709, 'Login', 2, 4, 49, 'PM', '4', 'JAN', '2016'),
(1710, 'Login', 2, 4, 56, 'PM', '4', 'JAN', '2016'),
(1711, 'Logout', 2, 5, 17, 'PM', '4', 'JAN', '2016'),
(1712, 'Login', 2, 5, 24, 'PM', '4', 'JAN', '2016'),
(1713, 'Logout', 2, 5, 32, 'PM', '4', 'JAN', '2016'),
(1714, 'Login', 2, 9, 1, 'AM', '5', 'JAN', '2016'),
(1715, 'Login', 2, 9, 3, 'AM', '5', 'JAN', '2016'),
(1716, 'Login', 2, 9, 4, 'AM', '5', 'JAN', '2016'),
(1717, 'Logout', 2, 9, 4, 'AM', '5', 'JAN', '2016'),
(1718, 'Login', 2, 9, 5, 'AM', '5', 'JAN', '2016'),
(1719, 'Logout', 2, 9, 8, 'AM', '5', 'JAN', '2016'),
(1720, 'Login', 2, 9, 23, 'AM', '5', 'JAN', '2016'),
(1721, 'Login', 2, 10, 5, 'AM', '5', 'JAN', '2016'),
(1722, 'Login', 2, 10, 13, 'AM', '5', 'JAN', '2016'),
(1723, 'Login', 2, 10, 15, 'AM', '5', 'JAN', '2016'),
(1724, 'Login', 2, 10, 16, 'AM', '5', 'JAN', '2016'),
(1725, 'Login', 2, 10, 18, 'AM', '5', 'JAN', '2016'),
(1726, 'Login', 2, 10, 22, 'AM', '5', 'JAN', '2016'),
(1727, 'Login', 2, 10, 30, 'AM', '5', 'JAN', '2016'),
(1728, 'Login', 2, 11, 11, 'AM', '5', 'JAN', '2016'),
(1729, 'Login', 2, 1, 23, 'PM', '5', 'JAN', '2016'),
(1730, 'Logout', 2, 2, 24, 'PM', '5', 'JAN', '2016'),
(1731, 'Login', 2, 2, 25, 'PM', '5', 'JAN', '2016'),
(1732, 'Logout', 2, 2, 25, 'PM', '5', 'JAN', '2016'),
(1733, 'Login', 2, 9, 59, 'PM', '5', 'JAN', '2016'),
(1734, 'Login', 2, 10, 28, 'PM', '5', 'JAN', '2016'),
(1735, 'Logout', 2, 10, 31, 'PM', '5', 'JAN', '2016'),
(1736, 'Login', 2, 6, 27, 'AM', '6', 'JAN', '2016'),
(1737, 'Login', 2, 6, 32, 'AM', '6', 'JAN', '2016'),
(1738, 'Login', 2, 6, 39, 'AM', '6', 'JAN', '2016'),
(1739, 'Login', 2, 6, 47, 'AM', '6', 'JAN', '2016'),
(1740, 'Login', 2, 6, 57, 'AM', '6', 'JAN', '2016'),
(1741, 'Login', 2, 7, 2, 'AM', '6', 'JAN', '2016'),
(1742, 'Login', 2, 7, 6, 'AM', '6', 'JAN', '2016'),
(1743, 'Login', 2, 7, 14, 'AM', '6', 'JAN', '2016'),
(1744, 'Login', 2, 7, 16, 'AM', '6', 'JAN', '2016'),
(1745, 'Login', 2, 7, 22, 'AM', '6', 'JAN', '2016'),
(1746, 'Login', 2, 7, 23, 'AM', '6', 'JAN', '2016'),
(1747, 'Logout', 2, 7, 25, 'AM', '6', 'JAN', '2016'),
(1748, 'Login', 2, 8, 7, 'AM', '6', 'JAN', '2016'),
(1749, 'Login', 2, 8, 14, 'AM', '6', 'JAN', '2016'),
(1750, 'Logout', 2, 8, 15, 'AM', '6', 'JAN', '2016'),
(1751, 'Login', 2, 8, 48, 'AM', '6', 'JAN', '2016'),
(1752, 'Login', 2, 8, 50, 'AM', '6', 'JAN', '2016'),
(1753, 'Login', 2, 8, 51, 'AM', '6', 'JAN', '2016'),
(1754, 'Login', 2, 8, 53, 'AM', '6', 'JAN', '2016'),
(1755, 'Login', 2, 8, 55, 'AM', '6', 'JAN', '2016'),
(1756, 'Login', 2, 8, 57, 'AM', '6', 'JAN', '2016'),
(1757, 'Login', 2, 9, 12, 'AM', '6', 'JAN', '2016'),
(1758, 'Login', 2, 9, 20, 'AM', '6', 'JAN', '2016'),
(1759, 'Login', 2, 9, 32, 'AM', '6', 'JAN', '2016'),
(1760, 'Logout', 2, 9, 36, 'AM', '6', 'JAN', '2016'),
(1761, 'Login', 2, 9, 39, 'AM', '6', 'JAN', '2016'),
(1762, 'Login', 2, 9, 41, 'AM', '6', 'JAN', '2016'),
(1763, 'Login', 2, 9, 42, 'AM', '6', 'JAN', '2016'),
(1764, 'Logout', 2, 9, 43, 'AM', '6', 'JAN', '2016'),
(1765, 'Login', 2, 9, 44, 'AM', '6', 'JAN', '2016'),
(1766, 'Login', 2, 9, 47, 'AM', '6', 'JAN', '2016'),
(1767, 'Login', 2, 9, 49, 'AM', '6', 'JAN', '2016'),
(1768, 'Login', 2, 9, 53, 'AM', '6', 'JAN', '2016'),
(1769, 'Login', 2, 9, 57, 'AM', '6', 'JAN', '2016'),
(1770, 'Login', 2, 9, 58, 'AM', '6', 'JAN', '2016'),
(1771, 'Logout', 2, 9, 59, 'AM', '6', 'JAN', '2016'),
(1772, 'Login', 2, 10, 2, 'AM', '6', 'JAN', '2016'),
(1773, 'Login', 2, 10, 4, 'AM', '6', 'JAN', '2016'),
(1774, 'Login', 2, 10, 6, 'AM', '6', 'JAN', '2016'),
(1775, 'Login', 2, 10, 15, 'AM', '6', 'JAN', '2016'),
(1776, 'Login', 2, 10, 19, 'AM', '6', 'JAN', '2016'),
(1777, 'Login', 2, 10, 23, 'AM', '6', 'JAN', '2016'),
(1778, 'Login', 2, 10, 24, 'AM', '6', 'JAN', '2016'),
(1779, 'Login', 2, 10, 26, 'AM', '6', 'JAN', '2016'),
(1780, 'Login', 2, 10, 41, 'AM', '6', 'JAN', '2016'),
(1781, 'Login', 2, 10, 43, 'AM', '6', 'JAN', '2016'),
(1782, 'Login', 2, 10, 46, 'AM', '6', 'JAN', '2016'),
(1783, 'Login', 2, 10, 47, 'AM', '6', 'JAN', '2016'),
(1784, 'Login', 2, 10, 50, 'AM', '6', 'JAN', '2016'),
(1785, 'Login', 2, 10, 52, 'AM', '6', 'JAN', '2016'),
(1786, 'Login', 2, 10, 57, 'AM', '6', 'JAN', '2016'),
(1787, 'Login', 2, 11, 0, 'AM', '6', 'JAN', '2016'),
(1788, 'Logout', 2, 11, 2, 'AM', '6', 'JAN', '2016'),
(1789, 'Login', 2, 1, 48, 'PM', '6', 'JAN', '2016'),
(1790, 'Logout', 2, 1, 50, 'PM', '6', 'JAN', '2016'),
(1791, 'Login', 2, 1, 51, 'PM', '6', 'JAN', '2016'),
(1792, 'Login', 2, 1, 54, 'PM', '6', 'JAN', '2016'),
(1793, 'Logout', 2, 1, 54, 'PM', '6', 'JAN', '2016'),
(1794, 'Login', 2, 1, 57, 'PM', '6', 'JAN', '2016'),
(1795, 'Login', 2, 1, 59, 'PM', '6', 'JAN', '2016'),
(1796, 'Login', 2, 2, 2, 'PM', '6', 'JAN', '2016'),
(1797, 'Login', 2, 2, 13, 'PM', '6', 'JAN', '2016'),
(1798, 'Login', 2, 2, 15, 'PM', '6', 'JAN', '2016'),
(1799, 'Login', 2, 2, 18, 'PM', '6', 'JAN', '2016'),
(1800, 'Login', 2, 2, 22, 'PM', '6', 'JAN', '2016'),
(1801, 'Logout', 2, 2, 23, 'PM', '6', 'JAN', '2016'),
(1802, 'Login', 2, 2, 27, 'PM', '6', 'JAN', '2016'),
(1803, 'Logout', 2, 2, 28, 'PM', '6', 'JAN', '2016'),
(1804, 'Login', 2, 3, 31, 'PM', '6', 'JAN', '2016'),
(1805, 'Login', 2, 3, 32, 'PM', '6', 'JAN', '2016'),
(1806, 'Logout', 2, 3, 33, 'PM', '6', 'JAN', '2016'),
(1807, 'Login', 1, 3, 42, 'PM', '6', 'JAN', '2016'),
(1808, 'Logout', 1, 3, 42, 'PM', '6', 'JAN', '2016'),
(1809, 'Login', 2, 3, 42, 'PM', '6', 'JAN', '2016'),
(1810, 'Login', 2, 3, 44, 'PM', '6', 'JAN', '2016'),
(1811, 'Logout', 2, 3, 44, 'PM', '6', 'JAN', '2016'),
(1812, 'Login', 2, 3, 45, 'PM', '6', 'JAN', '2016'),
(1813, 'Logout', 2, 3, 45, 'PM', '6', 'JAN', '2016'),
(1814, 'Login', 1, 3, 45, 'PM', '6', 'JAN', '2016'),
(1815, 'Logout', 1, 3, 45, 'PM', '6', 'JAN', '2016'),
(1816, 'Login', 2, 4, 13, 'PM', '6', 'JAN', '2016'),
(1817, 'Login', 2, 4, 15, 'PM', '6', 'JAN', '2016'),
(1818, 'Login', 2, 5, 3, 'PM', '6', 'JAN', '2016'),
(1819, 'Login', 2, 6, 56, 'AM', '7', 'JAN', '2016'),
(1820, 'Logout', 2, 6, 56, 'AM', '7', 'JAN', '2016'),
(1821, 'Login', 2, 6, 58, 'AM', '7', 'JAN', '2016'),
(1822, 'Login', 2, 7, 1, 'AM', '7', 'JAN', '2016'),
(1823, 'Logout', 2, 7, 3, 'AM', '7', 'JAN', '2016'),
(1824, 'Login', 2, 7, 4, 'AM', '7', 'JAN', '2016'),
(1825, 'Logout', 2, 7, 8, 'AM', '7', 'JAN', '2016'),
(1826, 'Login', 2, 7, 9, 'AM', '7', 'JAN', '2016'),
(1827, 'Login', 2, 7, 14, 'AM', '7', 'JAN', '2016'),
(1828, 'Login', 2, 7, 15, 'AM', '7', 'JAN', '2016'),
(1829, 'Login', 2, 7, 16, 'AM', '7', 'JAN', '2016'),
(1830, 'Login', 2, 7, 32, 'AM', '7', 'JAN', '2016'),
(1831, 'Login', 2, 7, 33, 'AM', '7', 'JAN', '2016'),
(1832, 'Login', 2, 8, 22, 'AM', '7', 'JAN', '2016'),
(1833, 'Login', 2, 8, 28, 'AM', '7', 'JAN', '2016'),
(1834, 'Login', 2, 8, 34, 'AM', '7', 'JAN', '2016'),
(1835, 'Logout', 2, 8, 34, 'AM', '7', 'JAN', '2016'),
(1836, 'Login', 2, 8, 34, 'AM', '7', 'JAN', '2016'),
(1837, 'Logout', 2, 8, 36, 'AM', '7', 'JAN', '2016'),
(1838, 'Login', 2, 8, 36, 'AM', '7', 'JAN', '2016'),
(1839, 'Logout', 2, 8, 37, 'AM', '7', 'JAN', '2016');

-- --------------------------------------------------------

--
-- Table structure for table `vaccine_types`
--

CREATE TABLE IF NOT EXISTS `vaccine_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vaccine` varchar(255) DEFAULT NULL,
  `deworm` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `vaccine_types`
--

INSERT INTO `vaccine_types` (`id`, `vaccine`, `deworm`) VALUES
(1, '5 IN 1', 'Benzimidazoles'),
(2, 'CANINE PARVOVIRUS (CPV)', 'Imidazothiazoles'),
(3, 'PARAINFLUENZA (PI)', 'Macrolides'),
(4, 'FELINE DISTEMPER VIRUS (FDV)', 'Natural Dewormers'),
(5, 'DISTEMPER HEPATITIS LEPTOSPIROSIS (DHL)', NULL),
(6, 'RABIES (R)', NULL),
(7, 'BOOSTER VACCINE', NULL),
(8, 'BORDELLA VACCINE', NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;