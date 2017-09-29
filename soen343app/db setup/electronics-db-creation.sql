CREATE DATABASE `electronics` /*!40100 DEFAULT CHARACTER SET latin1 */;
CREATE TABLE `desktop` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `model` varchar(100) NOT NULL,
    `weight` double NOT NULL,
    `price` double NOT NULL,
    `brand` text NOT NULL,
    `processorType` text NOT NULL,
    `cpuCores` int(11) NOT NULL,
    `ram` int(11) NOT NULL,
    `hardDriveSize` varchar(100) NOT NULL,
    `dimensions` varchar(100) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `model_UNIQUE` (`model`),
    UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `inventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(45) NOT NULL,
  `type` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `laptop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(100) NOT NULL,
  `weight` double NOT NULL,
  `price` double NOT NULL,
  `brand` text NOT NULL,
  `processorType` mediumtext NOT NULL,
  `cpuCores` int(11) NOT NULL,
  `ram` int(11) NOT NULL,
  `hardDriveSize` int(11) NOT NULL,
  `size` double NOT NULL,
  `batteryInfo` varchar(100) NOT NULL,
  `operatingSystem` text NOT NULL,
  `camera` tinyint(1) NOT NULL,
  `touchScreen` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `model#_UNIQUE` (`model`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `monitor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(100) NOT NULL,
  `weight` double NOT NULL,
  `price` double NOT NULL,
  `brand` text NOT NULL,
  `size` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `model#_UNIQUE` (`model`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `tablet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(45) NOT NULL,
  `weight` double NOT NULL,
  `price` double NOT NULL,
  `brand` text NOT NULL,
  `processorType` text NOT NULL,
  `cpuCores` int(11) NOT NULL,
  `ram` int(11) NOT NULL,
  `hardDriveSize` int(11) NOT NULL,
  `dimensions` int(11) NOT NULL,
  `batteryInfo` int(11) NOT NULL,
  `operatingSystem` text NOT NULL,
  `cameraInfo` decimal(10,0) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `model#_UNIQUE` (`model`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `television` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(100) NOT NULL,
  `weight` double NOT NULL,
  `price` double NOT NULL,
  `brand` text NOT NULL,
  `dimensions` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `model#_UNIQUE` (`model`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `address` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phoneNumber` varchar(10) NOT NULL,
  `isAdmin` tinyint(1) NOT NULL,
  `password` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idClients_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=latin1;
