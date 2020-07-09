-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 09, 2020 at 08:33 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sablon`
--

-- --------------------------------------------------------

--
-- Table structure for table `bahan`
--

CREATE TABLE `bahan` (
  `idBahan` varchar(2) NOT NULL,
  `namaBahan` varchar(12) DEFAULT NULL,
  `deskripsi` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bahan`
--

INSERT INTO `bahan` (`idBahan`, `namaBahan`, `deskripsi`) VALUES
('01', 'Plastisol', 'Terbuat dari minyak atau PVC sehingga memiliki daya rekat paling kuat dibanding jenis lainnya. Sangat cocok untuk detailing'),
('02', 'Rubber', 'Menutup serat kain, timbul pada permukaan dan elastis seperti karet. Kepekatan tinta sangat cocok untuk kain berwarna gelap'),
('03', 'Polyflex', 'Sangat unggul dalam menciptakan warna-warna padat yang membutuhkan ketajaman warna tinggi. Dapat diaplikasikan di mana saja alias fleksibel'),
('04', 'DTG', 'Digital printing sangat jelas dan detail dalam segi warna maupun tekstur. Proses pengerjaan tergolong cepat');

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `idPelanggan` varchar(4) NOT NULL,
  `namaPelanggan` varchar(32) DEFAULT NULL,
  `alamatPelanggan` text,
  `telpPelanggan` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`idPelanggan`, `namaPelanggan`, `alamatPelanggan`, `telpPelanggan`) VALUES
('0001', 'Muhammad', 'Surakarta', '081259420478'),
('0002', 'Yasir', 'Surabaya', '081259420479'),
('0003', 'Choiri', 'Jayapura', '081259420480');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `idTransaksi` varchar(8) NOT NULL,
  `namaPelanggan` varchar(32) DEFAULT NULL,
  `telpPelanggan` text,
  `jenisPesanan` text,
  `namaBahan` varchar(12) DEFAULT NULL,
  `jmlPesanan` int(4) DEFAULT NULL,
  `tglPesananMasuk` date DEFAULT NULL,
  `tglPesananJadi` date DEFAULT NULL,
  `totalBayar` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`idTransaksi`, `namaPelanggan`, `telpPelanggan`, `jenisPesanan`, `namaBahan`, `jmlPesanan`, `tglPesananMasuk`, `tglPesananJadi`, `totalBayar`) VALUES
('TEST92', 'Yasir', '69696969', 'Kaos', 'Rubber', 1, '2020-07-03', '2020-07-07', '52000'),
('TEST94', 'Choiri', '7003', 'Hoodie', 'Plastisol', 7, '2020-07-03', '2020-07-13', '630000');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bahan`
--
ALTER TABLE `bahan`
  ADD PRIMARY KEY (`idBahan`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`idPelanggan`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`idTransaksi`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
