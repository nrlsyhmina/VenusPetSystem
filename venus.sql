-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 12, 2020 at 04:22 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `venus`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `adminID` int(14) NOT NULL,
  `adminName` varchar(50) DEFAULT NULL,
  `adminNum` varchar(50) DEFAULT NULL,
  `adminPassword` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `bookingID` int(11) NOT NULL,
  `bDate` date DEFAULT NULL,
  `bTime` time DEFAULT NULL,
  `bTotalPrice` decimal(10,0) DEFAULT NULL,
  `custID` int(11) NOT NULL,
  `serviceID` int(11) NOT NULL,
  `staffID` int(14) NOT NULL,
  `adminID` int(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `custID` int(11) NOT NULL,
  `custName` varchar(255) DEFAULT NULL,
  `custNum` varchar(11) DEFAULT NULL,
  `cEmail` varchar(255) DEFAULT NULL,
  `cPassword` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `forum`
--

CREATE TABLE `forum` (
  `forumID` int(11) NOT NULL,
  `forumTitle` varchar(255) DEFAULT NULL,
  `forumDescription` varchar(255) DEFAULT NULL,
  `forumDate` date DEFAULT NULL,
  `staffID` int(14) NOT NULL,
  `custID` int(11) NOT NULL,
  `adminID` int(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `homepage`
--

CREATE TABLE `homepage` (
  `vID` int(11) NOT NULL,
  `vName` varchar(255) DEFAULT NULL,
  `vLocation` varchar(255) DEFAULT NULL,
  `vPhone` varchar(11) DEFAULT NULL,
  `vDetails` varchar(255) DEFAULT NULL,
  `adminID` int(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `rating`
--

CREATE TABLE `rating` (
  `ratingID` int(11) NOT NULL,
  `adminID` int(11) DEFAULT NULL,
  `staffiD` int(11) DEFAULT NULL,
  `custID` int(11) DEFAULT NULL,
  `bookingID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE `service` (
  `serviceID` int(11) NOT NULL,
  `svName` varchar(255) DEFAULT NULL,
  `svPrice` decimal(10,0) DEFAULT NULL,
  `svDescription` varchar(255) DEFAULT NULL,
  `staffID` int(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `staffID` int(14) NOT NULL,
  `sName` varchar(255) DEFAULT NULL,
  `sNum` varchar(11) DEFAULT NULL,
  `sPassword` varchar(50) DEFAULT NULL,
  `adminID` int(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`adminID`);

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`bookingID`),
  ADD KEY `fk_booking_customer` (`custID`),
  ADD KEY `fk_booking_service` (`serviceID`),
  ADD KEY `fk_booking_staff` (`staffID`),
  ADD KEY `fk_booking_admin` (`adminID`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`custID`);

--
-- Indexes for table `forum`
--
ALTER TABLE `forum`
  ADD PRIMARY KEY (`forumID`),
  ADD KEY `fk_forum_customer` (`custID`),
  ADD KEY `fk_forum_staff` (`staffID`),
  ADD KEY `fk_forum_admin` (`adminID`);

--
-- Indexes for table `homepage`
--
ALTER TABLE `homepage`
  ADD PRIMARY KEY (`vID`),
  ADD KEY `fk_homepage_admin` (`adminID`);

--
-- Indexes for table `rating`
--
ALTER TABLE `rating`
  ADD PRIMARY KEY (`ratingID`),
  ADD KEY `fk_rating_admin` (`adminID`),
  ADD KEY `fk_rating_staff` (`staffiD`),
  ADD KEY `fk_rating_cust` (`custID`),
  ADD KEY `fk_rating_booking` (`bookingID`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`serviceID`),
  ADD KEY `fk_service_staff` (`staffID`) USING BTREE;

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`staffID`),
  ADD KEY `fk_staff_admin` (`adminID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `bookingID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `custID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `forum`
--
ALTER TABLE `forum`
  MODIFY `forumID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `homepage`
--
ALTER TABLE `homepage`
  MODIFY `vID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `fk_booking_admin` FOREIGN KEY (`adminID`) REFERENCES `admin` (`adminID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_booking_customer` FOREIGN KEY (`custID`) REFERENCES `customer` (`custID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_booking_service` FOREIGN KEY (`serviceID`) REFERENCES `service` (`serviceID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_booking_staff` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `forum`
--
ALTER TABLE `forum`
  ADD CONSTRAINT `fk_forum_admin` FOREIGN KEY (`adminID`) REFERENCES `admin` (`adminID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_forum_customer` FOREIGN KEY (`custID`) REFERENCES `customer` (`custID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_forum_staff` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `homepage`
--
ALTER TABLE `homepage`
  ADD CONSTRAINT `fk_homepage_admin` FOREIGN KEY (`adminID`) REFERENCES `admin` (`adminID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `rating`
--
ALTER TABLE `rating`
  ADD CONSTRAINT `fk_rating_admin` FOREIGN KEY (`adminID`) REFERENCES `admin` (`adminID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_rating_booking` FOREIGN KEY (`bookingID`) REFERENCES `booking` (`bookingID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_rating_cust` FOREIGN KEY (`custID`) REFERENCES `customer` (`custID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_rating_staff` FOREIGN KEY (`staffiD`) REFERENCES `staff` (`staffID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `service`
--
ALTER TABLE `service`
  ADD CONSTRAINT `FK_service_staff` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `staff`
--
ALTER TABLE `staff`
  ADD CONSTRAINT `fk_staff_admin` FOREIGN KEY (`adminID`) REFERENCES `admin` (`adminID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
