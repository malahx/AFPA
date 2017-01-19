-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Lun 28 Novembre 2016 à 11:47
-- Version du serveur :  5.7.16-0ubuntu0.16.04.1
-- Version de PHP :  7.0.8-0ubuntu0.16.04.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `tracklist2`
--

-- --------------------------------------------------------

--
-- Structure de la table `author`
--

CREATE TABLE `author` (
  `id` int(11) NOT NULL,
  `author_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `author`
--

INSERT INTO `author` (`id`, `author_name`) VALUES
(1, 'New World Revolution'),
(2, 'Sven Väth'),
(3, 'Beatles'),
(4, 'Extrawelt'),
(5, 'Marek Hemmann'),
(6, 'machin');

-- --------------------------------------------------------

--
-- Structure de la table `genre`
--

CREATE TABLE `genre` (
  `id` int(11) NOT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `genre_url` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `genre`
--

INSERT INTO `genre` (`id`, `genre`, `descr`, `genre_url`) VALUES
(1, 'Pop', 'descri_pop', 'https://fr.wikipedia.org/wiki/Pop_(musique)'),
(2, 'Rock', 'descri_rock', ''),
(3, 'Rap', 'descri_rap', ''),
(4, 'Metal', 'descri_metal', ''),
(7, 'qehge', 'qegert', 'http://qger.com'),
(8, 'blaaaaaaaaaaa', 'ladesc', 'http://127.0.0.1/Track/index.php?action=genres');

-- --------------------------------------------------------

--
-- Structure de la table `playlist`
--

CREATE TABLE `playlist` (
  `playlistid` int(11) NOT NULL,
  `trackid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `playlist`
--

INSERT INTO `playlist` (`playlistid`, `trackid`) VALUES
(42, 18),
(47, 18),
(42, 20),
(47, 20),
(42, 24),
(47, 24);

-- --------------------------------------------------------

--
-- Structure de la table `playlist_header`
--

CREATE TABLE `playlist_header` (
  `id` int(11) NOT NULL,
  `name` varchar(120) NOT NULL,
  `desc` varchar(120) NOT NULL,
  `jaqurl` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `playlist_header`
--

INSERT INTO `playlist_header` (`id`, `name`, `desc`, `jaqurl`, `user_id`) VALUES
(42, 'Sven Väth', 'Discographie complète', 'uploads/JAQ_58242b5f9572c.jpg', 8),
(47, 'Sven Väth', 'Discographie complète', 'uploads/JAQ_58242b5f9572c.jpg', 9);

-- --------------------------------------------------------

--
-- Structure de la table `track`
--

CREATE TABLE `track` (
  `id` int(11) NOT NULL,
  `title` varchar(120) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `author_id` int(11) NOT NULL,
  `genre_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `track`
--

INSERT INTO `track` (`id`, `title`, `duration`, `author_id`, `genre_id`) VALUES
(18, 'Illuminate Me', 272, 1, 2),
(19, 'Robot', 161, 1, 3),
(20, 'Ritual of Life', 608, 2, 4),
(22, 'I Want to Hold Your Hand', 146, 3, 3),
(23, 'Zu Fuss', 358, 4, 1),
(24, 'Endless', 339, 5, 2),
(31, 'dfdsf', 120, 3, 3),
(32, 'truc', 12, 2, 3);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(120) NOT NULL,
  `email` varchar(120) NOT NULL,
  `password` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `username`, `email`, `password`) VALUES
(8, 'root', 'sdd@sqd', '$2y$10$UoT8fDwx6KoEl6CoUnI0fewMsCz1jeh1O/NpPfbgIUSV.J/aJ.wFS'),
(9, 'rootsd', 'sqd@sdd', '$2y$10$7rNiUfD9Dxhr07ro404bQeDuxICqJE1.J.tNTv89v2IvtGXkjqqqq'),
(10, 'rootdsfdsf', 'sd@sd', '$2y$10$KsOEqemu6j657OKoxZEgZOrYJiv1sDoKAPw1sa4/uEOx9GzGFYk1K'),
(11, 'rootdf', 'dfs@df', '$2y$10$QqWYTg6e5wCfvRw70p6f6OhfnoErvtjERKOixuuT520b2FUSs44ee');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `playlist`
--
ALTER TABLE `playlist`
  ADD PRIMARY KEY (`playlistid`,`trackid`),
  ADD KEY `Delete from track` (`trackid`);

--
-- Index pour la table `playlist_header`
--
ALTER TABLE `playlist_header`
  ADD PRIMARY KEY (`id`,`user_id`),
  ADD KEY `Delete from user` (`user_id`);

--
-- Index pour la table `track`
--
ALTER TABLE `track`
  ADD PRIMARY KEY (`id`,`author_id`,`genre_id`),
  ADD KEY `manage_author_id` (`author_id`),
  ADD KEY `manage_genre_id` (`genre_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `author`
--
ALTER TABLE `author`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT pour la table `genre`
--
ALTER TABLE `genre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT pour la table `playlist_header`
--
ALTER TABLE `playlist_header`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;
--
-- AUTO_INCREMENT pour la table `track`
--
ALTER TABLE `track`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `playlist`
--
ALTER TABLE `playlist`
  ADD CONSTRAINT `Delete from playlist` FOREIGN KEY (`playlistid`) REFERENCES `playlist_header` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Delete from track` FOREIGN KEY (`trackid`) REFERENCES `track` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `playlist_header`
--
ALTER TABLE `playlist_header`
  ADD CONSTRAINT `Delete from user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `track`
--
ALTER TABLE `track`
  ADD CONSTRAINT `manage_author_id` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `manage_genre_id` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
