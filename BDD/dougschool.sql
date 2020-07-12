-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 12 juil. 2020 à 17:55
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `dougschool`
--

-- --------------------------------------------------------

--
-- Structure de la table `course`
--

DROP TABLE IF EXISTS `course`;
CREATE TABLE IF NOT EXISTS `course` (
  `themeCourse` varchar(50) NOT NULL,
  `numberHours` int(3) NOT NULL,
  PRIMARY KEY (`themeCourse`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `course`
--

INSERT INTO `course` (`themeCourse`, `numberHours`) VALUES
('GTM', 120),
('Communication', 50);

-- --------------------------------------------------------

--
-- Structure de la table `person`
--

DROP TABLE IF EXISTS `person`;
CREATE TABLE IF NOT EXISTS `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `mailAdresse` varchar(50) NOT NULL,
  `adress` varchar(50) NOT NULL,
  `numberPhone` varchar(50) NOT NULL,
  `dateOfBirth` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `person`
--

INSERT INTO `person` (`id`, `firstName`, `lastName`, `mailAdresse`, `adress`, `numberPhone`, `dateOfBirth`) VALUES
(1, 'Fatima', 'Bamba', 'bamba@yahoo.fr', '6 rue des technos de simon', '01 05 88 99 65', 'Bamba'),
(2, 'toto', 'tata', 'toto@gmail.com', 'paris deschamps', '01 25 48 79 63', 'tata');

-- --------------------------------------------------------

--
-- Structure de la table `studentcourse`
--

DROP TABLE IF EXISTS `studentcourse`;
CREATE TABLE IF NOT EXISTS `studentcourse` (
  `idStudent` int(11) NOT NULL,
  `themeCourse` varchar(50) NOT NULL,
  KEY `idStudent` (`idStudent`),
  KEY `themeCourse` (`themeCourse`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `profil` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `login`, `password`, `profil`) VALUES
(1, 'admin', 'admin', 'D'),
(2, 'de', 'de', 'R');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
