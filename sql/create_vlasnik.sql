CREATE TABLE `vlasnik` (
  `id` int NOT NULL AUTO_INCREMENT,
  `brojVozackeDozvole` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `ime` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `prezime` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `starost` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '18',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `brojVozackeDozvole_UNIQUE` (`brojVozackeDozvole`)
) ENGINE=InnoDB AUTO_INCREMENT=2520 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
