-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 04, 2023 at 02:07 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tracking`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `login`, `password`) VALUES
(1, 'admin1', 'password1'),
(2, 'admin2', 'password2'),
(3, 'admin3', 'password3');

-- --------------------------------------------------------

--
-- Table structure for table `commande`
--

CREATE TABLE `commande` (
  `id_commande` int(11) NOT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `distance` float DEFAULT NULL,
  `client` varchar(255) NOT NULL,
  `livreur` varchar(255) NOT NULL,
  `etat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `commande`
--

INSERT INTO `commande` (`id_commande`, `date_debut`, `date_fin`, `distance`, `client`, `livreur`, `etat`) VALUES
(1, '2023-03-30', '2023-03-31', 2.5, 'Client 1', 'Livreur 1', 'Livrée'),
(2, '2023-04-01', '2023-04-03', 7, 'Client 2', 'Livreur 1', 'En cours'),
(3, '2023-04-04', '2023-04-05', 5.2, 'Client 3', 'Livreur 3', 'En attente'),
(4, '2023-04-04', '2023-04-04', 3, 'Client 4', 'Livreur 4', 'En attente'),
(5, '2023-04-05', '2023-04-07', 10, 'Client 5', 'Livreur 5', 'En attente'),
(7, '2023-04-07', '2023-04-07', 3, 'Client 7', 'Livreur 7', 'En attente'),
(8, '2023-04-07', '2023-04-08', 5.9, 'Client 8', 'Livreur 8', 'En attente'),
(10, '2023-04-04', '2023-04-06', 23, 'Client 9', 'Livreur 10', 'En cours');

-- --------------------------------------------------------

--
-- Table structure for table `commande_produit`
--

CREATE TABLE `commande_produit` (
  `id_commande` int(11) NOT NULL,
  `id_produit` int(11) NOT NULL,
  `quantite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `commande_produit`
--

INSERT INTO `commande_produit` (`id_commande`, `id_produit`, `quantite`) VALUES
(2, 1, 4),
(3, 4, 9),
(1, 3, 5),
(8, 5, 5),
(4, 2, 8),
(10, 5, 7),
(3, 4, 3);

-- --------------------------------------------------------

--
-- Table structure for table `livreur`
--

CREATE TABLE `livreur` (
  `id_livreur` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `nombre_commande` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `livreur`
--

INSERT INTO `livreur` (`id_livreur`, `nom`, `telephone`, `nombre_commande`) VALUES
(1, 'Livreur 1', '0611223344', 2),
(2, 'Livreur 2', '0622334455', 0),
(3, 'Livreur 3', '0633445566', 1),
(4, 'Livreur 4', '0644556677', 1),
(5, 'Livreur 5', '0655667788', 2),
(6, 'Livreur 6', '0666778899', 0),
(7, 'Livreur 7', '0677889911', 1),
(8, 'Livreur 8', '0688991122', 1),
(10, 'Livreur 10', '0600112233', 1),
(16, 'Livreur 1222', '0622998877', 0),
(17, 'Livreur 11', '0611223344', 0);

-- --------------------------------------------------------

--
-- Table structure for table `produit`
--

CREATE TABLE `produit` (
  `id_produit` int(11) NOT NULL,
  `nom_produit` varchar(255) DEFAULT NULL,
  `prix` float DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `produit`
--

INSERT INTO `produit` (`id_produit`, `nom_produit`, `prix`, `description`) VALUES
(1, 'Huile de table Lesieur', 91.8, 'Huile de table équilibrée 5L'),
(2, 'Spaghetti Panzani', 18.5, 'Spaghetti n°7 500g'),
(3, 'Farine fleur Kenz', 35.8, 'Farine fleur pâtissière de blé tendre 5Kg'),
(4, 'Couscous Dari', 25.9, 'Couscous Fin 1kg'),
(5, 'Sucre Saint Louis', 52, 'Sucre petits morceaux spécial café 1Kg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id_commande`);

--
-- Indexes for table `livreur`
--
ALTER TABLE `livreur`
  ADD PRIMARY KEY (`id_livreur`);

--
-- Indexes for table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id_produit`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `commande`
--
ALTER TABLE `commande`
  MODIFY `id_commande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `livreur`
--
ALTER TABLE `livreur`
  MODIFY `id_livreur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `produit`
--
ALTER TABLE `produit`
  MODIFY `id_produit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
