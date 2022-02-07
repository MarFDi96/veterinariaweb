-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 17, 2021 at 06:01 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `veterinaria`
--

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `productos`
--

CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(30) NOT NULL,
  `cantidad` int(20) NOT NULL,
  `categoria` varchar(20) NOT NULL,
  `precio` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `productos`
--

INSERT INTO `productos` (`id`, `descripcion`, `cantidad`, `categoria`, `precio`) VALUES
(2, 'test1', 985, 'medicamentos', 50.25),
(3, 'algoregular', 204, 'regulares', 15),
(4, 'algonotanregular', 972, 'regulares', 15.1),
(5, 'testmedicamento', 200, 'medicamentos', 20.12),
(6, 'testfinal', 315, 'medicamentos', 50.25),
(7, 'testfinal2', 316, 'regulares', 15),
(8, 'testfinallong', 200, 'medicamentos', 15);

-- --------------------------------------------------------

--
-- Table structure for table `turnos`
--

CREATE TABLE `turnos` (
  `iddoctor` varchar(30) NOT NULL,
  `horario` varchar(100) NOT NULL,
  `nombrepropietario` varchar(30) NOT NULL,
  `nombremascota` varchar(30) NOT NULL,
  `tipoanimal` varchar(20) NOT NULL,
  `numerocontacto` varchar(20) NOT NULL,
  `dia` varchar(20) NOT NULL,
  `id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `turnos`
--

INSERT INTO `turnos` (`iddoctor`, `horario`, `nombrepropietario`, `nombremascota`, `tipoanimal`, `numerocontacto`, `dia`, `id`) VALUES
('rober', '14:00', 'feito', 'sarasa', 'gato', '11111111', 'lunes', 2),
('rober', '15:30', 'TESTER', 'HUGO', 'gato', '12312345', 'lunes', 4),
('rober', '14:00', 'TESTER', 'blanquito', 'gato', '12345678', 'miercoles', 7);

-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

CREATE TABLE `usuarios` (
  `rol` varchar(35) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `password` varchar(25) NOT NULL,
  `id` varchar(30) NOT NULL,
  `diaslaborales` varchar(40) NOT NULL DEFAULT 'N/A',
  `manejoanimal` varchar(40) DEFAULT 'N/A'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` (`rol`, `nombre`, `password`, `id`, `diaslaborales`, `manejoanimal`) VALUES
('Recepcionista', 'diego', '123', 'diego', 'N/A', ''),
('Admin', 'Matias', '123', 'Mati', 'N/A', ''),
('Veterinario', 'testnombre', '123', 'nuevotest', 'N/A', NULL),
('Recepcionista', 'nuevo', '123', 'pruebanuevo', 'N/A', ''),
('Veterinario', 'roberto', '123', 'rober', 'lunes,martes,miercoles', 'gato, perro'),
('Veterinario', 'sarasa', '123', 'test2401', 'lunes,martes,miercoles', 'gato, perro'),
('Veterinario', 'lean', '123', 'testfinal', 'lunes,martes,miercoles', 'gato, tortuga');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `turnos`
--
ALTER TABLE `turnos`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `turnos`
--
ALTER TABLE `turnos`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
