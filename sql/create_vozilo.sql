CREATE TABLE `vozilo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `regbroj` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `godisteProizvodnje` int NOT NULL,
  `status` tinyint NOT NULL DEFAULT '0',
  `vlasnikId` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `regbroj_UNIQUE` (`regbroj`)
) ENGINE=InnoDB AUTO_INCREMENT=1307 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
