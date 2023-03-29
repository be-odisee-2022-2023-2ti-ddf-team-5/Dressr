-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Gegenereerd op: 29 mrt 2023 om 17:25
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

CREATE TABLE IF NOT EXISTS `kledingstukken` (
  `id` int(11) NOT NULL,
  `naam` varchar(50) NOT NULL,
  `kledingstukspecificaties` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Gegevens worden geëxporteerd voor tabel `kledingstukken`
--

INSERT INTO `kledingstukken` (`id`, `naam`, `kledingstukspecificaties`) VALUES
(2, 'gucci broek', 'niet drogen'),
(3, 'superdry pull', 'niet wassen'),
(4, 'tommy pet', 'niet vergeten'),
(5, 'rok h&m', 'mooi'),
(6, 'broek zeb', 'mooi'),
(8, 'RAPH', 'mooi');

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `kledingstukken`
--
ALTER TABLE `kledingstukken`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT voor geëxporteerde tabellen
--

--
-- AUTO_INCREMENT voor een tabel `kledingstukken`
--
ALTER TABLE `kledingstukken`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
