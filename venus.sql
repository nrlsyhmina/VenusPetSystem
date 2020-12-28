-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 28, 2020 at 02:43 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

SET FOREIGN_KEY_CHECKS=0;
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
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `bookingID` int(11) NOT NULL,
  `bDate` date DEFAULT NULL,
  `bTime` time DEFAULT NULL,
  `bTotalPrice` decimal(10,0) DEFAULT NULL,
  `rating` int(5) DEFAULT NULL,
  `custID` int(11) NOT NULL,
  `serviceID` int(11) NOT NULL,
  `staffID` varchar(14) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `custID` int(11) NOT NULL,
  `cName` varchar(255) DEFAULT NULL,
  `cNum` varchar(11) DEFAULT NULL,
  `cEmail` varchar(255) DEFAULT NULL,
  `cPassword` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`custID`, `cName`, `cNum`, `cEmail`, `cPassword`) VALUES
(1, 'ABU', '1234567899', 'abu@gmail.com', '9562'),
(2, 'ALI', '1234567899', 'ali@gmail.com', '9562'),
(3, 'AHMAD', '5974131343', 'ahmad@gmail.com', '3654'),
(4, 'MUTHU', '6685623325', 'muthu@gmail.com', '7963'),
(5, 'ALI', '1234567899', 'ali@gmail.com', '9562');

-- --------------------------------------------------------

--
-- Table structure for table `forum`
--

CREATE TABLE `forum` (
  `forumID` int(11) NOT NULL,
  `forumTitle` varchar(255) DEFAULT NULL,
  `forumDescription` varchar(255) DEFAULT NULL,
  `forumDate` date DEFAULT NULL,
  `custID` int(11) NOT NULL,
  `staffID` varchar(14) DEFAULT NULL
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
  `staffID` varchar(14) DEFAULT NULL
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
  `staffID` varchar(14) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `staffID` varchar(14) NOT NULL DEFAULT '',
  `sName` varchar(255) DEFAULT NULL,
  `sNum` varchar(11) DEFAULT NULL,
  `sPassword` varchar(50) DEFAULT NULL,
  `adminID` varchar(14) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`staffID`, `sName`, `sNum`, `sPassword`, `adminID`) VALUES
('990508565498', 'ALI', '1234567899', '1234', '990508565498'),
('99232623232', 'ABU', '1234567899', '9562', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`bookingID`),
  ADD KEY `fk_booking_customer` (`custID`),
  ADD KEY `fk_booking_service` (`serviceID`),
  ADD KEY `staffID8` (`staffID`);

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
  ADD KEY `staffID` (`staffID`);

--
-- Indexes for table `homepage`
--
ALTER TABLE `homepage`
  ADD PRIMARY KEY (`vID`),
  ADD KEY `staffID` (`staffID`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`serviceID`),
  ADD KEY `staffID` (`staffID`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`staffID`) USING BTREE,
  ADD KEY `FK_staff_staff` (`adminID`) USING BTREE;

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
  MODIFY `custID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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
  ADD CONSTRAINT `_booking_staff` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_booking_customer` FOREIGN KEY (`custID`) REFERENCES `customer` (`custID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_booking_service` FOREIGN KEY (`serviceID`) REFERENCES `service` (`serviceID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `forum`
--
ALTER TABLE `forum`
  ADD CONSTRAINT `_forum_staff` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_forum_customer` FOREIGN KEY (`custID`) REFERENCES `customer` (`custID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `homepage`
--
ALTER TABLE `homepage`
  ADD CONSTRAINT `FK1_homepage_staff` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `service`
--
ALTER TABLE `service`
  ADD CONSTRAINT `FK1_service_staff` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `staff`
--
ALTER TABLE `staff`
  ADD CONSTRAINT `adminID_fk` FOREIGN KEY (`adminID`) REFERENCES `staff` (`staffID`) ON DELETE NO ACTION ON UPDATE NO ACTION;


--
-- Metadata
--
USE `phpmyadmin`;

--
-- Metadata for table booking
--

--
-- Metadata for table customer
--

--
-- Metadata for table forum
--

--
-- Metadata for table homepage
--

--
-- Metadata for table service
--

--
-- Metadata for table staff
--

--
-- Metadata for database venus
--
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
