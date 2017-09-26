CREATE DATABASE `electronics` /*!40100 DEFAULT CHARACTER SET latin1 */;
CREATE TABLE `desktop` (
  `dimension` int(11) NOT NULL,
  `processorType` text NOT NULL,
  `ram` int(11) NOT NULL,
  `weight` double NOT NULL,
  `cpu` int(11) NOT NULL,
  `hardDrive` int(11) NOT NULL,
  `brand` text NOT NULL,
  `price` double NOT NULL,
  `model#` float NOT NULL,
  PRIMARY KEY (`model#`),
  UNIQUE KEY `model_UNIQUE` (`model#`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `inventory` (
  `id` int(11) NOT NULL,
  `model#` float NOT NULL,
  `type` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `laptop` (
  `size` double NOT NULL,
  `processorType` mediumtext NOT NULL,
  `ram` int(11) NOT NULL,
  `weight` double NOT NULL,
  `cpu` int(11) NOT NULL,
  `hardDrive` int(11) NOT NULL,
  `battery` int(11) NOT NULL,
  `brand` text NOT NULL,
  `bos` text NOT NULL,
  `price` double NOT NULL,
  `camera` tinyint(1) DEFAULT NULL,
  `tscreen` tinyint(1) DEFAULT NULL,
  `model#` float NOT NULL,
  PRIMARY KEY (`model#`),
  UNIQUE KEY `model#_UNIQUE` (`model#`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `monitor` (
  `size` double NOT NULL,
  `weight` double NOT NULL,
  `brand` text NOT NULL,
  `price` double NOT NULL,
  `model` float NOT NULL,
  PRIMARY KEY (`model`),
  UNIQUE KEY `model#_UNIQUE` (`model`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `tablet` (
  `size` double NOT NULL,
  `dimension` int(11) NOT NULL,
  `weight` double NOT NULL,
  `processorType` text NOT NULL,
  `ram` int(11) NOT NULL,
  `cpu` int(11) NOT NULL,
  `hardDrive` int(11) NOT NULL,
  `battery` int(11) NOT NULL,
  `brand` text NOT NULL,
  `bos` text NOT NULL,
  `camera` decimal(10,0) NOT NULL,
  `price` double NOT NULL,
  `model#` float NOT NULL,
  PRIMARY KEY (`model#`),
  UNIQUE KEY `model#_UNIQUE` (`model#`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `television` (
  `dimension` int(11) NOT NULL,
  `weight` double NOT NULL,
  `brand` text NOT NULL,
  `price` double NOT NULL,
  `model#` float NOT NULL,
  PRIMARY KEY (`model#`),
  UNIQUE KEY `model#_UNIQUE` (`model#`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `address` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phoneNumber` varchar(10) NOT NULL,
  `isAdmin` tinyint(4) NOT NULL,
  `password` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idClients_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=latin1;


