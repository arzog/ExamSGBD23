-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 28 fév. 2023 à 14:57
-- Version du serveur : 5.7.36
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `construction`
--

-- --------------------------------------------------------

--
-- Structure de la table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
CREATE TABLE IF NOT EXISTS `addresses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `zip_code` int(11) NOT NULL,
  `street` varchar(255) NOT NULL,
  `number` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `addresses`
--

INSERT INTO `addresses` (`id`, `country`, `city`, `zip_code`, `street`, `number`) VALUES
(1, "Belgique", "Braine-Le-Comte", 7090, "Place des postes", "46/14"),
(2, "Belgique", "Bois-d Haine", 7170, "rue de la troupette", "11"),
(3, "Belgique", "Bois-de-Lessines", 7866, "rue de la loge", "21"),
(6, "Belgique", "Bois-de-Lessines", 7866, "rue de la loge", "78"),
(7, "Belgique", "Meslin-l\'Eveque", 7822, "rue de la sille", "14");

-- --------------------------------------------------------

--
-- Structure de la table `articles`
--

DROP TABLE IF EXISTS `articles`;
CREATE TABLE IF NOT EXISTS `articles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `current_stock` int(11) NOT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT "1",
  `min_stock` int(11) NOT NULL DEFAULT "0",
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `articles`
--

INSERT INTO `articles` (`id`, `label`, `price`, `current_stock`, `isActive`, `min_stock`) VALUES
(1, "poutre 2m", 15.84, 5000, 1, 0),
(2, "poutre 1m", 7.84, 5000, 1, 0),
(3, "clous", 1, 5000, 1, 0),
(4, "vis", 1, 5000, 1, 1000),
(5, "équerre", 10, 12000, 1, 5000);



-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

DROP TABLE IF EXISTS `clients`;
CREATE TABLE IF NOT EXISTS `clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `id_address` int(11) NOT NULL,
  `isActive` tinyint(1) DEFAULT "1",
  PRIMARY KEY (`id`),
  KEY `id_address` (`id_address`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`id`, `firstname`, `lastname`, `mail`, `phone`, `id_address`, `isActive`) VALUES
(1, "Brice", "Beumier", "brice.beumier@hotmail.fr", "+32 471 89 36 39", 1, 1),
(2, "Rémi", "Potvin", "potvin.remi1@gmail.com", "+32 470 04 81 29", 2, 1),
(3, "Emeline", "Beghin", "embegh@mail.be", "+32 145 67 89 45", 7, 1);

-- --------------------------------------------------------

--
-- Structure de la table `companies`
--

DROP TABLE IF EXISTS `companies`;
CREATE TABLE IF NOT EXISTS `companies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `vat` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT "1",
  `id_address` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_address` (`id_address`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `companies`
--

INSERT INTO `companies` (`id`, `name`, `vat`, `mail`, `phone`, `isActive`, `id_address`) VALUES
(1, "Wapit & co", "some vat number", "wapiti@email.be", "+32 12 34 56 78", 1, 1),
(6, "blc test", "apozihd", "wapiti@email.be", "+32 12 34 56 78", 1, 3);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `passwd` varchar(255) NOT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT "1",
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `firstname`, `lastname`, `username`, `passwd`, `isActive`) VALUES
(1, "Amandine", "Herion", "AmHe", "BrBe181222", 1),
(5, "Brice", "Beumier", "BrBeumier", "e6e_03sHCj3Y", 1),
(7, "Raphael", "Palmeri", "RaPal", "Rapha3!Pa!m3r1", 1),
(8, "admin", "user", "root", "root", 1),
(13, "Rémi", "Potvin", "fennec", "R3m!Potvin", 1),
(18, "Emeline", "Beghin", "embe", "Rapha3!Pa!m3r1", 1);

-- --------------------------------------------------------

--
-- Structure de la table `bills`
--

DROP TABLE IF EXISTS `bills`;
CREATE TABLE IF NOT EXISTS `bills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sold_date` date NOT NULL,
  `id_article` int(11) NOT NULL,
  `qtt` int(11) NOT NULL,
  `bill_num` varchar(255) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_client` int(11) DEFAULT NULL,
  `id_company` int(11) DEFAULT NULL,
  `for_company` tinyint(1) NOT NULL DEFAULT "0",
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  KEY `id_client` (`id_client`),
  KEY `id_company` (`id_company`),
  KEY `id_article` (`id_article`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `bills`
--

INSERT INTO `bills` (`id`, `sold_date`, `id_article`,`qtt`,`bill_num`, `id_user`, `id_client`, `id_company`, `for_company`) VALUES
(1, "2023-01-06",1, 500, "BeumierBrice_060123_0001", 1, 1, NULL, 0);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `bills`
--
ALTER TABLE `bills`
  ADD CONSTRAINT `bills_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `bills_ibfk_3` FOREIGN KEY (`id_client`) REFERENCES `clients` (`id`),
  ADD CONSTRAINT `bills_ibfk_4` FOREIGN KEY (`id_company`) REFERENCES `companies` (`id`),
  ADD CONSTRAINT `bills_ibfk_5` FOREIGN KEY (`id_article`) REFERENCES `addresses`(`id`);

--
-- Contraintes pour la table `clients`
--
ALTER TABLE `clients`
  ADD CONSTRAINT `clients_ibfk_1` FOREIGN KEY (`id_address`) REFERENCES `addresses` (`id`);

--
-- Contraintes pour la table `companies`
--
ALTER TABLE `companies`
  ADD CONSTRAINT `companies_ibfk_1` FOREIGN KEY (`id_address`) REFERENCES `addresses` (`id`);


COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
