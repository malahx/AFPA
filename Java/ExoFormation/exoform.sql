-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Jeu 02 Février 2017 à 09:11
-- Version du serveur :  5.7.17-0ubuntu0.16.04.1
-- Version de PHP :  7.0.15-1+deb.sury.org~xenial+1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `exoform`
--

-- --------------------------------------------------------

--
-- Structure de la table `ecf`
--

CREATE TABLE `ecf` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `formation_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `ecf`
--

INSERT INTO `ecf` (`id`, `nom`, `formation_id`) VALUES
(8, 'Web', 2),
(9, 'Bdd', 2);

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE `formation` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `formation`
--

INSERT INTO `formation` (`id`, `nom`) VALUES
(1, 'Mateur de chien'),
(2, 'Développeur Logiciel');

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

CREATE TABLE `personne` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `personne`
--

INSERT INTO `personne` (`id`, `nom`, `prenom`) VALUES
(1, 'Alfred', 'George'),
(2, 'Duduche', 'Jean'),
(9, 'DUCHOU', 'Fred'),
(10, 'PATAGUEULE', 'James');

-- --------------------------------------------------------

--
-- Structure de la table `promo`
--

CREATE TABLE `promo` (
  `formation_id` int(11) NOT NULL,
  `stagiaire_code` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `promo`
--

INSERT INTO `promo` (`formation_id`, `stagiaire_code`) VALUES
(1, 'A101'),
(2, 'A100');

-- --------------------------------------------------------

--
-- Structure de la table `resultat`
--

CREATE TABLE `resultat` (
  `ecf_id` int(11) NOT NULL,
  `stagiaire_code` varchar(10) NOT NULL,
  `obtenu` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `stagiaire`
--

CREATE TABLE `stagiaire` (
  `code` varchar(10) NOT NULL,
  `personne_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `stagiaire`
--

INSERT INTO `stagiaire` (`code`, `personne_id`) VALUES
('A100', 1),
('A101', 2),
('A110', 9);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `ecf`
--
ALTER TABLE `ecf`
  ADD PRIMARY KEY (`id`),
  ADD KEY `formation_id` (`formation_id`);

--
-- Index pour la table `formation`
--
ALTER TABLE `formation`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `promo`
--
ALTER TABLE `promo`
  ADD UNIQUE KEY `formation_id_2` (`formation_id`,`stagiaire_code`),
  ADD KEY `formation_id` (`formation_id`),
  ADD KEY `stagiaire_id` (`stagiaire_code`);

--
-- Index pour la table `resultat`
--
ALTER TABLE `resultat`
  ADD UNIQUE KEY `ecf_id` (`ecf_id`,`stagiaire_code`),
  ADD KEY `ressta` (`stagiaire_code`);

--
-- Index pour la table `stagiaire`
--
ALTER TABLE `stagiaire`
  ADD PRIMARY KEY (`code`),
  ADD KEY `personne_id` (`personne_id`),
  ADD KEY `code` (`code`),
  ADD KEY `personne_id_2` (`personne_id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `ecf`
--
ALTER TABLE `ecf`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT pour la table `formation`
--
ALTER TABLE `formation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `personne`
--
ALTER TABLE `personne`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `ecf`
--
ALTER TABLE `ecf`
  ADD CONSTRAINT `ecfforma` FOREIGN KEY (`formation_id`) REFERENCES `formation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `promo`
--
ALTER TABLE `promo`
  ADD CONSTRAINT `promoforma` FOREIGN KEY (`formation_id`) REFERENCES `formation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `promosta` FOREIGN KEY (`stagiaire_code`) REFERENCES `stagiaire` (`code`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `resultat`
--
ALTER TABLE `resultat`
  ADD CONSTRAINT `resecf` FOREIGN KEY (`ecf_id`) REFERENCES `ecf` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ressta` FOREIGN KEY (`stagiaire_code`) REFERENCES `stagiaire` (`code`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `stagiaire`
--
ALTER TABLE `stagiaire`
  ADD CONSTRAINT `perssta` FOREIGN KEY (`personne_id`) REFERENCES `personne` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
