-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 03-12-2022 a las 06:46:33
-- Versión del servidor: 8.0.17
-- Versión de PHP: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bodyhealth`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE `administrador` (
  `id_admin` int(15) NOT NULL,
  `documentoA` int(11) DEFAULT NULL,
  `tipodocumento` varchar(50) DEFAULT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `apellido` varchar(50) DEFAULT NULL,
  `telefono` varchar(30) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `id_rol` int(11) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `administrador`
--

INSERT INTO `administrador` (`id_admin`, `documentoA`, `tipodocumento`, `nombre`, `apellido`, `telefono`, `fecha_nacimiento`, `email`, `password`, `id_rol`) VALUES
(1, 100451, 'CC', 'Admin', 'Uno', '3212323322', '2022-07-10', 'admin@bodyhealth.com', '15948', 1),
(2, 1004926016, 'CC', 'Nataly', 'Gutierrez Altuve', '3219238493', '2000-07-07', 'admin@prueba.com', '$2a$10$lA301vp6BVf4woK8RJJCHeysEJ6SlRmGsYZ1ZtLOtzpbAsLDOsZo2', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` int(15) NOT NULL,
  `documentoC` int(15) DEFAULT NULL,
  `tipo_documento` varchar(15) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `apellido` varchar(50) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `fechaN` date DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `foto` varchar(150) DEFAULT NULL,
  `jornada` varchar(50) DEFAULT NULL,
  `comentario` varchar(400) DEFAULT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT '1',
  `id_rol` int(11) DEFAULT '2'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `documentoC`, `tipo_documento`, `nombre`, `apellido`, `telefono`, `fechaN`, `email`, `password`, `foto`, `jornada`, `comentario`, `estado`, `id_rol`) VALUES
(1, 999, 'CC', 'Anderson', 'Orozco', '3219238493', '2022-11-09', 'anderson07rolon@gmail.com', '1234', '261278641_626726082271096_3891881707494189074_n.jpg', 'mañana', NULL, 1, 2),
(5, 134324, 'cedula', 'prueba', 'Cáceres Becerra', '3208381557', '2022-11-09', 'andresncb03@gmail.com', 'sasda', 'WhatsApp Image 2022-11-21 at 12.41.23 PM.jpeg', 'Mañana', NULL, 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente_detalle`
--

CREATE TABLE `cliente_detalle` (
  `id_factura` int(11) NOT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `id_detalle` int(11) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `id_metodopago` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `cliente_detalle`
--

INSERT INTO `cliente_detalle` (`id_factura`, `id_cliente`, `id_detalle`, `fecha_inicio`, `fecha_fin`, `id_metodopago`) VALUES
(13, 1, 2, '2022-12-01', '2023-03-01', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente_rutina`
--

CREATE TABLE `cliente_rutina` (
  `id_clienterutina` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_rutina` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `cliente_rutina`
--

INSERT INTO `cliente_rutina` (`id_clienterutina`, `id_cliente`, `id_rutina`) VALUES
(28, 5, 14);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente_rutina_ejercicio`
--

CREATE TABLE `cliente_rutina_ejercicio` (
  `id_cliente_rutina_ejercicio` int(11) NOT NULL,
  `id_cliente_rutina` int(11) DEFAULT NULL,
  `id_rutina_ejercicio` int(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `cliente_rutina_ejercicio`
--

INSERT INTO `cliente_rutina_ejercicio` (`id_cliente_rutina_ejercicio`, `id_cliente_rutina`, `id_rutina_ejercicio`) VALUES
(102, 28, 12),
(103, 28, 13),
(104, 28, 14),
(105, 28, 15),
(106, 28, 16),
(107, 28, 17),
(108, 28, 18),
(109, 28, 19),
(110, 28, 20),
(111, 28, 21),
(112, 28, 22);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

CREATE TABLE `compra` (
  `id_compra` int(11) NOT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `id_metodopago` int(15) DEFAULT NULL,
  `fecha_compra` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `compra`
--

INSERT INTO `compra` (`id_compra`, `id_cliente`, `id_metodopago`, `fecha_compra`) VALUES
(8585, 1, 2, '2022-11-27');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `control_cliente`
--

CREATE TABLE `control_cliente` (
  `id_controlcliente` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `peso` double DEFAULT NULL,
  `estatura` double DEFAULT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `control_cliente`
--

INSERT INTO `control_cliente` (`id_controlcliente`, `id_cliente`, `peso`, `estatura`, `fecha`) VALUES
(9, 5, 23, 23, '2022-11-22'),
(14, 5, 50, 60, '2022-11-30'),
(15, 1, 100, 100, '2022-11-24'),
(17, 5, 80, 180, '2022-11-30'),
(19, 1, 70, 170, '2022-12-01'),
(20, 1, 72, 170, '2022-12-01');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `control_entrenador`
--

CREATE TABLE `control_entrenador` (
  `id_entrenador` int(11) NOT NULL,
  `peso` double DEFAULT NULL,
  `estatura` double DEFAULT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle`
--

CREATE TABLE `detalle` (
  `id_detalle` int(11) NOT NULL,
  `plan` varchar(60) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `meses` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `detalle`
--

INSERT INTO `detalle` (`id_detalle`, `plan`, `precio`, `meses`) VALUES
(1, 'Plan Avanzado', 80000, 3),
(2, 'Plan Basico', 80000, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ejercicio`
--

CREATE TABLE `ejercicio` (
  `id_ejercicio` int(11) NOT NULL,
  `id_musculo` int(11) DEFAULT NULL,
  `descripcion` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `series` varchar(50) DEFAULT NULL,
  `repeticiones` int(2) DEFAULT NULL,
  `url_video` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `ejercicio`
--

INSERT INTO `ejercicio` (`id_ejercicio`, `id_musculo`, `descripcion`, `series`, `repeticiones`, `url_video`) VALUES
(14, 1, 'Ejercicios triceps', '4', 15, 'https://www.youtube.com/watch?v=XSQrTe0mDGo'),
(15, 2, 'Ejercicios biceps', '4', 12, 'https://www.youtube.com/watch?v=XSQrTe0mDGo'),
(16, 3, 'Ejercicios cuadriceps', '4', 14, 'https://www.youtube.com/watch?v=XSQrTe0mDGo'),
(17, 4, 'Ejercicios abdomen', '4', 14, 'https://www.youtube.com/watch?v=XSQrTe0mDGo'),
(18, 5, 'Ejercicios hombros', '3', 20, 'https://www.youtube.com/watch?v=XSQrTe0mDGo'),
(19, 6, 'Ejercicios Aductores', '4', 8, 'https://www.youtube.com/watch?v=XSQrTe0mDGo'),
(20, 7, 'Ejercicios pecho', '4', 14, 'https://www.youtube.com/watch?v=XSQrTe0mDGo'),
(21, 8, 'Ejercicios pantorrilla', '4', 13, 'https://www.youtube.com/watch?v=XSQrTe0mDGo'),
(22, 9, 'Ejercicios trapecio', '3', 15, 'https://www.youtube.com/watch?v=XSQrTe0mDGo'),
(23, 10, 'Ejercicios espalda', '4', 15, 'https://www.youtube.com/watch?v=XSQrTe0mDGo'),
(24, 11, 'Ejercicios gluteos', '4', 12, 'https://www.youtube.com/watch?v=XSQrTe0mDGo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrenador`
--

CREATE TABLE `entrenador` (
  `id_entrenador` int(15) NOT NULL,
  `documentoE` int(15) DEFAULT NULL,
  `tipo_documento` varchar(30) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `apellido` varchar(50) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `fechaN` date DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `foto` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `experiencia` varchar(15) DEFAULT NULL,
  `hoja_vida` varchar(100) DEFAULT NULL,
  `titulo_academico` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `jornada` varchar(50) DEFAULT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT '1',
  `id_admin` int(11) DEFAULT NULL,
  `id_rol` int(11) DEFAULT '3'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `entrenador`
--

INSERT INTO `entrenador` (`id_entrenador`, `documentoE`, `tipo_documento`, `nombre`, `apellido`, `telefono`, `fechaN`, `email`, `password`, `foto`, `experiencia`, `hoja_vida`, `titulo_academico`, `jornada`, `estado`, `id_admin`, `id_rol`) VALUES
(1, 1004926016, 'CC', 'Anderson', 'Orozco', '3219238493', '2022-11-23', 'anderson07rolon@gmail.com', '1234', 'PERFIL.jpeg', '4', 'Hoja de Vida', 'entrenador profesional', 'Mañana', 1, 1, 3),
(2, 10254, 'CC', 'Anderson', 'Orozco', '3219238493', '1999-02-02', 'anderson07rolon@gmail.com', '1234', 'perfil-ruben.png', '1', 'Sin Subir', 'Entrenador Profesional', 'Mañana', 0, 1, 3),
(4, 32424, 'cedula', 'Androlo', 'dads', 'sa23', '2022-11-16', 'andresncb03@gmail.com', 'Carolooo', 'sfsfs', 'sfdsf', 'sfdsfd', 'sfdsf', 'Mañana', 0, 1, 3),
(5, 1245, 'cedula', 'Andrés Norberto', 'Cáceres Becerra', '3208381557', '2022-11-16', 'andresncb03@gmail.com', 'sdas', '261278641_626726082271096_3891881707494189074_n.jpg', 'sfdsf', 'sfdsfd', 'sfdsf', 'Tarde', 1, 1, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrenador_cliente`
--

CREATE TABLE `entrenador_cliente` (
  `id_asignacion` int(11) NOT NULL,
  `id_entrenador` int(15) NOT NULL,
  `id_cliente` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `entrenador_cliente`
--

INSERT INTO `entrenador_cliente` (`id_asignacion`, `id_entrenador`, `id_cliente`) VALUES
(8, 1, 5),
(10, 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `maquina`
--

CREATE TABLE `maquina` (
  `id_maquina` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL,
  `observacion` varchar(50) DEFAULT NULL,
  `id_proveedor` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `maquina`
--

INSERT INTO `maquina` (`id_maquina`, `nombre`, `estado`, `observacion`, `id_proveedor`) VALUES
(1, 'Maquina', 'Nuevo', 'N/A', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodo_pago`
--

CREATE TABLE `metodo_pago` (
  `id_metodopago` int(11) NOT NULL,
  `descripcion` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `metodo_pago`
--

INSERT INTO `metodo_pago` (`id_metodopago`, `descripcion`) VALUES
(1, 'PSE'),
(2, 'Tarjeta de Credito'),
(3, 'Pago en efectivo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `musculo`
--

CREATE TABLE `musculo` (
  `id_musculo` int(15) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `musculo`
--

INSERT INTO `musculo` (`id_musculo`, `descripcion`) VALUES
(1, 'Triceps'),
(2, 'Biceps'),
(3, 'Cuadriceps'),
(4, 'Abdomen'),
(5, 'Hombros'),
(6, 'Aductores'),
(7, 'Pecho'),
(8, 'Pantorrilla'),
(9, 'Trapecio'),
(10, 'Espalda'),
(11, 'Gluteos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id_pedido` int(11) NOT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `id_compra` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id_pedido`, `id_producto`, `id_compra`, `cantidad`) VALUES
(99, 1, 8585, 5),
(100, 2, 8585, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `stock` int(4) DEFAULT NULL,
  `precio` int(15) DEFAULT NULL,
  `foto` varchar(150) NOT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  `id_proveedor` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `nombre`, `stock`, `precio`, `foto`, `estado`, `id_proveedor`) VALUES
(1, 'Proteina Bipro', 24, 15000, 'bipro_2lbs_chocolate_mutantes_180x.avif', 1, 1),
(2, 'Creatina', 30, 80000, '1647534457-41UoQGPKAL._SL500_.jpg', 1, 1),
(3, 'Bcaa Express', 10, 30000, '1647534352-41Tp711NJeL._SL500_.jpg', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `id_proveedor` int(11) NOT NULL,
  `nombre_empresa` varchar(50) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `direccion` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`id_proveedor`, `nombre_empresa`, `telefono`, `direccion`) VALUES
(1, 'UFPS', '3219238493', 'Calle 1 #4-49 Carlos Ramirez Paris');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id_rol` int(11) NOT NULL,
  `descripcion` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id_rol`, `descripcion`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER'),
(3, 'ROLE_TRAINER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rutina`
--

CREATE TABLE `rutina` (
  `id_rutina` int(15) NOT NULL,
  `nombre_rutina` varchar(100) DEFAULT NULL,
  `descripcion` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `rutina`
--

INSERT INTO `rutina` (`id_rutina`, `nombre_rutina`, `descripcion`) VALUES
(14, 'Numero 1', 'Esta es la rutina numero 1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rutina_ejercicio`
--

CREATE TABLE `rutina_ejercicio` (
  `id_rutina_ejercicio` int(11) NOT NULL,
  `id_rutina` int(15) DEFAULT NULL,
  `id_ejercicio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `rutina_ejercicio`
--

INSERT INTO `rutina_ejercicio` (`id_rutina_ejercicio`, `id_rutina`, `id_ejercicio`) VALUES
(12, 14, 14),
(13, 14, 15),
(14, 14, 16),
(15, 14, 17),
(16, 14, 18),
(17, 14, 19),
(18, 14, 20),
(19, 14, 21),
(20, 14, 22),
(21, 14, 23),
(22, 14, 24);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD PRIMARY KEY (`id_admin`),
  ADD UNIQUE KEY `documentoA` (`documentoA`),
  ADD KEY `id_rol` (`id_rol`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`),
  ADD KEY `id_rol` (`id_rol`),
  ADD KEY `UK` (`documentoC`);

--
-- Indices de la tabla `cliente_detalle`
--
ALTER TABLE `cliente_detalle`
  ADD PRIMARY KEY (`id_factura`),
  ADD KEY `id_detalle` (`id_detalle`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_metodopago` (`id_metodopago`);

--
-- Indices de la tabla `cliente_rutina`
--
ALTER TABLE `cliente_rutina`
  ADD PRIMARY KEY (`id_clienterutina`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_rutina` (`id_rutina`);

--
-- Indices de la tabla `cliente_rutina_ejercicio`
--
ALTER TABLE `cliente_rutina_ejercicio`
  ADD PRIMARY KEY (`id_cliente_rutina_ejercicio`),
  ADD KEY `id_cliente_rutina` (`id_cliente_rutina`),
  ADD KEY `id_rutina_ejercicio` (`id_rutina_ejercicio`);

--
-- Indices de la tabla `compra`
--
ALTER TABLE `compra`
  ADD PRIMARY KEY (`id_compra`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_metodopago` (`id_metodopago`);

--
-- Indices de la tabla `control_cliente`
--
ALTER TABLE `control_cliente`
  ADD PRIMARY KEY (`id_controlcliente`) USING BTREE,
  ADD KEY `id_cliente` (`id_cliente`);

--
-- Indices de la tabla `control_entrenador`
--
ALTER TABLE `control_entrenador`
  ADD PRIMARY KEY (`id_entrenador`,`fecha`);

--
-- Indices de la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD PRIMARY KEY (`id_detalle`);

--
-- Indices de la tabla `ejercicio`
--
ALTER TABLE `ejercicio`
  ADD PRIMARY KEY (`id_ejercicio`),
  ADD KEY `id_musculo` (`id_musculo`);

--
-- Indices de la tabla `entrenador`
--
ALTER TABLE `entrenador`
  ADD PRIMARY KEY (`id_entrenador`),
  ADD KEY `id_admin` (`id_admin`),
  ADD KEY `id_rol` (`id_rol`),
  ADD KEY `UK` (`documentoE`);

--
-- Indices de la tabla `entrenador_cliente`
--
ALTER TABLE `entrenador_cliente`
  ADD PRIMARY KEY (`id_asignacion`) USING BTREE,
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_entrenador` (`id_entrenador`);

--
-- Indices de la tabla `maquina`
--
ALTER TABLE `maquina`
  ADD PRIMARY KEY (`id_maquina`),
  ADD KEY `id_proveedor` (`id_proveedor`);

--
-- Indices de la tabla `metodo_pago`
--
ALTER TABLE `metodo_pago`
  ADD PRIMARY KEY (`id_metodopago`);

--
-- Indices de la tabla `musculo`
--
ALTER TABLE `musculo`
  ADD PRIMARY KEY (`id_musculo`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id_pedido`),
  ADD KEY `id_compra` (`id_compra`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `id_proveedor` (`id_proveedor`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`id_proveedor`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id_rol`);

--
-- Indices de la tabla `rutina`
--
ALTER TABLE `rutina`
  ADD PRIMARY KEY (`id_rutina`);

--
-- Indices de la tabla `rutina_ejercicio`
--
ALTER TABLE `rutina_ejercicio`
  ADD PRIMARY KEY (`id_rutina_ejercicio`),
  ADD KEY `id_ejercicio` (`id_ejercicio`),
  ADD KEY `id_rutina` (`id_rutina`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `administrador`
--
ALTER TABLE `administrador`
  MODIFY `id_admin` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `cliente_detalle`
--
ALTER TABLE `cliente_detalle`
  MODIFY `id_factura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `cliente_rutina`
--
ALTER TABLE `cliente_rutina`
  MODIFY `id_clienterutina` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT de la tabla `cliente_rutina_ejercicio`
--
ALTER TABLE `cliente_rutina_ejercicio`
  MODIFY `id_cliente_rutina_ejercicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=113;

--
-- AUTO_INCREMENT de la tabla `compra`
--
ALTER TABLE `compra`
  MODIFY `id_compra` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8586;

--
-- AUTO_INCREMENT de la tabla `control_cliente`
--
ALTER TABLE `control_cliente`
  MODIFY `id_controlcliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `control_entrenador`
--
ALTER TABLE `control_entrenador`
  MODIFY `id_entrenador` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detalle`
--
ALTER TABLE `detalle`
  MODIFY `id_detalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `ejercicio`
--
ALTER TABLE `ejercicio`
  MODIFY `id_ejercicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `entrenador`
--
ALTER TABLE `entrenador`
  MODIFY `id_entrenador` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `entrenador_cliente`
--
ALTER TABLE `entrenador_cliente`
  MODIFY `id_asignacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `maquina`
--
ALTER TABLE `maquina`
  MODIFY `id_maquina` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `metodo_pago`
--
ALTER TABLE `metodo_pago`
  MODIFY `id_metodopago` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `musculo`
--
ALTER TABLE `musculo`
  MODIFY `id_musculo` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id_pedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `id_proveedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `rutina`
--
ALTER TABLE `rutina`
  MODIFY `id_rutina` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `rutina_ejercicio`
--
ALTER TABLE `rutina_ejercicio`
  MODIFY `id_rutina_ejercicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD CONSTRAINT `administrador_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`);

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`);

--
-- Filtros para la tabla `cliente_detalle`
--
ALTER TABLE `cliente_detalle`
  ADD CONSTRAINT `cliente_detalle_ibfk_1` FOREIGN KEY (`id_detalle`) REFERENCES `detalle` (`id_detalle`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cliente_detalle_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cliente_detalle_ibfk_3` FOREIGN KEY (`id_metodopago`) REFERENCES `metodo_pago` (`id_metodopago`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `cliente_rutina`
--
ALTER TABLE `cliente_rutina`
  ADD CONSTRAINT `cliente_fk` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `rutina_fk` FOREIGN KEY (`id_rutina`) REFERENCES `rutina` (`id_rutina`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `cliente_rutina_ejercicio`
--
ALTER TABLE `cliente_rutina_ejercicio`
  ADD CONSTRAINT `cliente_rutina_ejercicio_ibfk` FOREIGN KEY (`id_cliente_rutina`) REFERENCES `cliente_rutina` (`id_clienterutina`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cliente_rutina_ejercicio_ibfk_2` FOREIGN KEY (`id_rutina_ejercicio`) REFERENCES `rutina_ejercicio` (`id_rutina_ejercicio`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `compra_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  ADD CONSTRAINT `compra_ibfk_2` FOREIGN KEY (`id_metodopago`) REFERENCES `metodo_pago` (`id_metodopago`);

--
-- Filtros para la tabla `control_cliente`
--
ALTER TABLE `control_cliente`
  ADD CONSTRAINT `control_cliente_fk` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `control_entrenador`
--
ALTER TABLE `control_entrenador`
  ADD CONSTRAINT `control_entrenador_ibfk_1` FOREIGN KEY (`id_entrenador`) REFERENCES `entrenador` (`id_entrenador`);

--
-- Filtros para la tabla `ejercicio`
--
ALTER TABLE `ejercicio`
  ADD CONSTRAINT `ejercicio_ibfk_1` FOREIGN KEY (`id_musculo`) REFERENCES `musculo` (`id_musculo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `entrenador`
--
ALTER TABLE `entrenador`
  ADD CONSTRAINT `entrenador_ibfk_1` FOREIGN KEY (`id_admin`) REFERENCES `administrador` (`id_admin`),
  ADD CONSTRAINT `entrenador_ibfk_2` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`);

--
-- Filtros para la tabla `entrenador_cliente`
--
ALTER TABLE `entrenador_cliente`
  ADD CONSTRAINT `ec_cliente_fk` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ec_entrenador_fk` FOREIGN KEY (`id_entrenador`) REFERENCES `entrenador` (`id_entrenador`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `maquina`
--
ALTER TABLE `maquina`
  ADD CONSTRAINT `maquina_ibfk_1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`);

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`id_compra`) REFERENCES `compra` (`id_compra`),
  ADD CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`);

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`);

--
-- Filtros para la tabla `rutina_ejercicio`
--
ALTER TABLE `rutina_ejercicio`
  ADD CONSTRAINT `rutina_ejercicio_ibfk_1` FOREIGN KEY (`id_ejercicio`) REFERENCES `ejercicio` (`id_ejercicio`),
  ADD CONSTRAINT `rutina_ejercicio_ibfk_2` FOREIGN KEY (`id_rutina`) REFERENCES `rutina` (`id_rutina`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
