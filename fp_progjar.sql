-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 17 Des 2017 pada 14.05
-- Versi Server: 10.1.16-MariaDB
-- PHP Version: 7.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fp_progjar`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `jawaban`
--

CREATE TABLE `jawaban` (
  `NOMOR` varchar(2) NOT NULL,
  `jawaban` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `jawaban`
--

INSERT INTO `jawaban` (`NOMOR`, `jawaban`) VALUES
('1', 'A'),
('1', 'A'),
('2', 'B'),
('3', 'B'),
('4', 'C');

-- --------------------------------------------------------

--
-- Struktur dari tabel `soal`
--

CREATE TABLE `soal` (
  `nomor` int(11) DEFAULT NULL,
  `soal` varchar(100) DEFAULT NULL,
  `jawaban1` varchar(15) DEFAULT NULL,
  `jawaban2` varchar(15) DEFAULT NULL,
  `jawaban3` varchar(15) DEFAULT NULL,
  `jawaban4` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `soal`
--

INSERT INTO `soal` (`nomor`, `soal`, `jawaban1`, `jawaban2`, `jawaban3`, `jawaban4`) VALUES
(1, 'Siapakah Presiden ke 1?', NULL, NULL, NULL, NULL),
(2, 'Siapakah Orang Terkaya di dunia?', NULL, NULL, NULL, NULL),
(3, 'Siapakah Orang Tercerdas di Indonesia?', NULL, NULL, NULL, NULL),
(4, 'Siapakah Orang Tertinggi di dunia?', NULL, NULL, NULL, NULL),
(5, 'Siapakah Orang Termiskin di Malaysia?', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `soal2`
--

CREATE TABLE `soal2` (
  `nomor` varchar(3) NOT NULL,
  `soal` varchar(100) DEFAULT NULL,
  `jawaban1` varchar(15) DEFAULT NULL,
  `jawaban2` varchar(15) DEFAULT NULL,
  `jawaban3` varchar(15) DEFAULT NULL,
  `jawaban4` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `username` varchar(20) NOT NULL,
  `email` varchar(25) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`username`, `email`, `password`) VALUES
('User1', 'User1@gmail.com', 'user1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `soal2`
--
ALTER TABLE `soal2`
  ADD PRIMARY KEY (`nomor`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
