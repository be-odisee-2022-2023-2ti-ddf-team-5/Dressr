-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Gegenereerd op: 27 apr 2023 om 20:28
-- Serverversie: 10.4.27-MariaDB
-- PHP-versie: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dressr`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `kledingstukken`
--

CREATE TABLE `kledingstukken` (
  `id` int(11) NOT NULL,
  `naam` varchar(50) NOT NULL,
  `merk` varchar(50) NOT NULL,
  `kledingstukspecificaties` text NOT NULL,
  `gereserveerd` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Gegevens worden geëxporteerd voor tabel `kledingstukken`
--

INSERT INTO `kledingstukken` (`id`, `naam`, `merk`, `kledingstukspecificaties`, `gereserveerd`) VALUES
(3, 'pull', 'superdry ', 'hehe', 0),
(4, 'pet', 'tommy ', 'niet vergeten', 1),
(5, 'rok', 'H&M', 'lelijk', 0);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `personen`
--

CREATE TABLE `personen` (
  `id` int(11) NOT NULL,
  `emailadress` varchar(255) NOT NULL,
  `voornaam` varchar(255) NOT NULL,
  `familienaam` varchar(255) NOT NULL,
  `paswoord` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Gegevens worden geëxporteerd voor tabel `personen`
--

INSERT INTO `personen` (`id`, `emailadress`, `voornaam`, `familienaam`, `paswoord`) VALUES
(1, 'manal.moussaoui@student.odisee.be', 'Manal', 'Moussaoui', '$2a$10$MGWF2FmPdIk9DCT2VUen/uiV7VJth9zhvjFWlI6XBuWpKERFoKEJK'),
(2, 'mikail.cassata@student.odisee.be', 'Mikail', 'Cassata', '$2a$10$e45cHqbD.sGBAPWov6oShuHViKoEggHLfqEHXZcF8eSY6mSU0TY1m'),
(3, 'kenan.yavas@student.odisee.be', 'Kenan', 'Yavas', '$2a$10$ynPSZOHqQqEW7OvqsLFa0OmrDi..nz7AaTlek8vZwsRdAbcYcfzkG'),
(4, 'batuhan.tektas@student.odisee.be', 'Batuhan', 'Tektas', '$2a$10$Xt1/koA5xaTPgLkjqdfoQOTGwX0c6HLrldUiXVoPJc3CrAwnRFeJu'),
(5, 'charles.clotuche@student.odisee.be', 'Charles', 'Clotuche', '$2a$10$4VMLc8PAN2X5aIqThlIGz..emlnRulwDODGXZibCoIqqTVbHEOZA.'),
(6, 'hamza.kassou@student.odisee.be', 'Hamza', 'Kassou', '$2a$10$4g0H4ZDmT0NfWLve9sg5YeHdhoDYwKtOt7yavM7iGN.l4eWAM4sBW');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `rollen`
--

CREATE TABLE `rollen` (
  `type` varchar(31) NOT NULL,
  `id` int(11) NOT NULL,
  `usernaam` varchar(255) NOT NULL,
  `persoon_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Gegevens worden geëxporteerd voor tabel `rollen`
--

INSERT INTO `rollen` (`type`, `id`, `usernaam`, `persoon_id`) VALUES
('Kledingmanager', 1, 'ManalKledingmanager', 1),
('Kledingmanager', 2, 'MikailKledingmanager', 2),
('Kledingmanager', 3, 'CharlesKledingmanager', 5),
('Klant', 4, 'BatuhanKlant', 4),
('Wasserijmedewerker', 5, 'KenanWasserijmedewerker', 3),
('Klant', 6, 'HamzaKlant', 6);

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `kledingstukken`
--
ALTER TABLE `kledingstukken`
  ADD PRIMARY KEY (`id`);

--
-- Indexen voor tabel `personen`
--
ALTER TABLE `personen`
  ADD PRIMARY KEY (`id`);

--
-- Indexen voor tabel `rollen`
--
ALTER TABLE `rollen`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `persoon_id` (`persoon_id`);

--
-- AUTO_INCREMENT voor geëxporteerde tabellen
--

--
-- AUTO_INCREMENT voor een tabel `kledingstukken`
--
ALTER TABLE `kledingstukken`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT voor een tabel `personen`
--
ALTER TABLE `personen`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT voor een tabel `rollen`
--
ALTER TABLE `rollen`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Beperkingen voor geëxporteerde tabellen
--

--
-- Beperkingen voor tabel `rollen`
--
ALTER TABLE `rollen`
  ADD CONSTRAINT `rollen_ibfk_1` FOREIGN KEY (`persoon_id`) REFERENCES `personen` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
