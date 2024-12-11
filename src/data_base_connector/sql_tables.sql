-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: gestaocasamento
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cartorio`
--

DROP TABLE IF EXISTS `cartorio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cartorio` (
  `id_cartorio` bigint NOT NULL AUTO_INCREMENT,
  `nome_cartorio` varchar(100) NOT NULL,
  `endereco_cartorio` varchar(100) NOT NULL,
  `telefone_cartorio` varchar(100) NOT NULL,
  `cep_cartorio` varchar(100) NOT NULL,
  PRIMARY KEY (`id_cartorio`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartorio`
--

LOCK TABLES `cartorio` WRITE;
/*!40000 ALTER TABLE `cartorio` DISABLE KEYS */;
INSERT INTO `cartorio` VALUES (1,'Cartório do Norte','Avenida Brasil, 456','(21) 91234-5678','87654-321');
/*!40000 ALTER TABLE `cartorio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cerimonial`
--

DROP TABLE IF EXISTS `cerimonial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cerimonial` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `funcao` varchar(100) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cerimonial`
--

LOCK TABLES `cerimonial` WRITE;
/*!40000 ALTER TABLE `cerimonial` DISABLE KEYS */;
INSERT INTO `cerimonial` VALUES (1,'Carlos Silva','Mestre de Cerimônias','2024-12-09 00:18:33',NULL),(2,'Maria Oliveira','Coordenadora de Logística','2024-12-09 00:18:33',NULL);
/*!40000 ALTER TABLE `cerimonial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `convidado_familia`
--

DROP TABLE IF EXISTS `convidado_familia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `convidado_familia` (
  `id_convidado_familia` bigint NOT NULL AUTO_INCREMENT,
  `nome_familia` varchar(100) NOT NULL,
  `acesso` varchar(100) DEFAULT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime DEFAULT NULL,
  PRIMARY KEY (`id_convidado_familia`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `convidado_familia`
--

LOCK TABLES `convidado_familia` WRITE;
/*!40000 ALTER TABLE `convidado_familia` DISABLE KEYS */;
INSERT INTO `convidado_familia` VALUES (1,'Fornecedores',NULL,'2024-12-09 00:18:01',NULL),(2,'Família Dantas',NULL,'2024-12-09 00:18:01',NULL),(3,'Família Silva',NULL,'2024-12-09 00:18:01',NULL);
/*!40000 ALTER TABLE `convidado_familia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `convidado_individual`
--

DROP TABLE IF EXISTS `convidado_individual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `convidado_individual` (
  `id_convidado_individual` bigint NOT NULL AUTO_INCREMENT,
  `id_pessoa_fk` bigint NOT NULL,
  `id_convidado_familia_fk` bigint NOT NULL,
  `parentesco` varchar(500) NOT NULL,
  `confirmacao` tinyint(1) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime DEFAULT NULL,
  PRIMARY KEY (`id_convidado_individual`),
  KEY `fk_convidado_pessoa` (`id_pessoa_fk`),
  KEY `fk_convidado_familia` (`id_convidado_familia_fk`),
  CONSTRAINT `fk_convidado_familia` FOREIGN KEY (`id_convidado_familia_fk`) REFERENCES `convidado_familia` (`id_convidado_familia`),
  CONSTRAINT `fk_convidado_pessoa` FOREIGN KEY (`id_pessoa_fk`) REFERENCES `pessoa` (`id_pessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `convidado_individual`
--

LOCK TABLES `convidado_individual` WRITE;
/*!40000 ALTER TABLE `convidado_individual` DISABLE KEYS */;
INSERT INTO `convidado_individual` VALUES (1,1,2,'Primo',1,'2024-12-09 00:18:14',NULL),(2,2,1,'Amigo da familia Silva',1,'2024-12-09 00:18:14',NULL);
/*!40000 ALTER TABLE `convidado_individual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evento` (
  `id_evento` bigint NOT NULL AUTO_INCREMENT,
  `data_evento_fk` varchar(100) NOT NULL,
  `igreja_evento_fk` bigint NOT NULL,
  `cartorio_evento_fk` bigint NOT NULL,
  `pessoa_noivo_fk` bigint NOT NULL,
  `pessoa_noiva_fk` bigint NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime DEFAULT NULL,
  PRIMARY KEY (`id_evento`),
  KEY `fk_igreja_evento` (`igreja_evento_fk`),
  KEY `fk_cartorio_evento` (`cartorio_evento_fk`),
  KEY `fk_pessoa_noivo` (`pessoa_noivo_fk`),
  KEY `fk_pessoa_noiva` (`pessoa_noiva_fk`),
  CONSTRAINT `fk_cartorio_evento` FOREIGN KEY (`cartorio_evento_fk`) REFERENCES `cartorio` (`id_cartorio`),
  CONSTRAINT `fk_igreja_evento` FOREIGN KEY (`igreja_evento_fk`) REFERENCES `igreja` (`id_igreja`),
  CONSTRAINT `fk_pessoa_noiva` FOREIGN KEY (`pessoa_noiva_fk`) REFERENCES `pessoa` (`id_pessoa`),
  CONSTRAINT `fk_pessoa_noivo` FOREIGN KEY (`pessoa_noivo_fk`) REFERENCES `pessoa` (`id_pessoa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fornecedor` (
  `id_fornecedor` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `cnpj` varchar(50) NOT NULL,
  `telefone` varchar(50) NOT NULL,
  `valorAPagar` decimal(10,2) NOT NULL,
  `parcelas` int NOT NULL,
  `estado_fornecedor` tinyint(1) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime DEFAULT NULL,
  PRIMARY KEY (`id_fornecedor`),
  UNIQUE KEY `cnpj` (`cnpj`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
INSERT INTO `fornecedor` VALUES (1,'Fotografo','12.345.678/0001-90','(11) 98765-4321',5000.00,5,1,'2024-11-29 10:30:45',NULL),(2,'Padre','98.765.432/0001-12','(21) 99876-5432',7500.50,3,0,'2024-11-28 14:20:30',NULL);
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `igreja`
--

DROP TABLE IF EXISTS `igreja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `igreja` (
  `id_igreja` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `endereco` varchar(100) NOT NULL,
  `CEP` varchar(100) NOT NULL,
  PRIMARY KEY (`id_igreja`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `igreja`
--

LOCK TABLES `igreja` WRITE;
/*!40000 ALTER TABLE `igreja` DISABLE KEYS */;
INSERT INTO `igreja` VALUES (1,'Igreja Nossa Senhora da Paz','Rua Central, 789','65432-100');
/*!40000 ALTER TABLE `igreja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensagens`
--

DROP TABLE IF EXISTS `mensagens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mensagens` (
  `id_mensagem` bigint NOT NULL AUTO_INCREMENT,
  `mensagem` varchar(100) NOT NULL,
  `nome_mensageiro` varchar(100) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime DEFAULT NULL,
  PRIMARY KEY (`id_mensagem`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensagens`
--

LOCK TABLES `mensagens` WRITE;
/*!40000 ALTER TABLE `mensagens` DISABLE KEYS */;
INSERT INTO `mensagens` VALUES (1,'Feliz casamento, que seja um dia inesquecível!','Ana Banana','2024-12-09 00:18:26',NULL),(2,'Parabéns aos noivos, que a felicidade acompanhe vocês sempre!','Clebin Legal','2024-12-09 00:18:26',NULL);
/*!40000 ALTER TABLE `mensagens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamento`
--

DROP TABLE IF EXISTS `pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagamento` (
  `id_pagamento` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) NOT NULL,
  `id_fornecedor_fk` bigint NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime DEFAULT NULL,
  PRIMARY KEY (`id_pagamento`),
  KEY `fk_pagamento_fornecedor` (`id_fornecedor_fk`),
  CONSTRAINT `fk_pagamento_fornecedor` FOREIGN KEY (`id_fornecedor_fk`) REFERENCES `fornecedor` (`id_fornecedor`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamento`
--

LOCK TABLES `pagamento` WRITE;
/*!40000 ALTER TABLE `pagamento` DISABLE KEYS */;
INSERT INTO `pagamento` VALUES (1,'Fotografo',1,'2024-11-29 10:30:00',NULL),(2,'Padre',2,'2024-11-29 10:35:00',NULL);
/*!40000 ALTER TABLE `pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parcela`
--

DROP TABLE IF EXISTS `parcela`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parcela` (
  `id_parcela` bigint NOT NULL AUTO_INCREMENT,
  `id_pagamento_fk` bigint NOT NULL,
  `id_pessoa_pagante_fk` bigint NOT NULL,
  `parcela` int NOT NULL,
  `parcela_data` datetime NOT NULL,
  `valor_parcela` decimal(10,2) NOT NULL,
  `estado_pagamento` tinyint(1) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime DEFAULT NULL,
  PRIMARY KEY (`id_parcela`),
  KEY `fk_parcela_pagamento` (`id_pagamento_fk`),
  KEY `fk_parcela_pessoa` (`id_pessoa_pagante_fk`),
  CONSTRAINT `fk_parcela_pagamento` FOREIGN KEY (`id_pagamento_fk`) REFERENCES `pagamento` (`id_pagamento`),
  CONSTRAINT `fk_parcela_pessoa` FOREIGN KEY (`id_pessoa_pagante_fk`) REFERENCES `pessoa` (`id_pessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parcela`
--

LOCK TABLES `parcela` WRITE;
/*!40000 ALTER TABLE `parcela` DISABLE KEYS */;
INSERT INTO `parcela` VALUES (1,1,1,1,'2024-11-29 10:30:45',1000.00,0,'2024-12-09 00:17:41',NULL),(2,1,1,2,'2024-12-29 10:30:45',1000.00,0,'2024-12-09 00:17:41',NULL),(3,1,1,3,'2025-01-29 10:30:45',1000.00,0,'2024-12-09 00:17:41',NULL),(4,1,1,4,'2025-02-28 10:30:45',1000.00,0,'2024-12-09 00:17:41',NULL),(5,1,1,5,'2025-03-29 10:30:45',1000.00,0,'2024-12-09 00:17:41',NULL);
/*!40000 ALTER TABLE `parcela` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoa` (
  `id_pessoa` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `nascimento` date NOT NULL,
  `telefone` varchar(100) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime DEFAULT NULL,
  PRIMARY KEY (`id_pessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (1,'João','1990-05-15','(11) 99999-1111','2024-11-19 10:30:00',NULL),(2,'Maria','2004-11-12','(34) 98889-2555','2024-11-19 10:49:00',NULL);
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presentes`
--

DROP TABLE IF EXISTS `presentes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `presentes` (
  `id_presente` bigint NOT NULL AUTO_INCREMENT,
  `nome_presente` varchar(100) NOT NULL,
  `tipo_presente` varchar(100) NOT NULL,
  `valor_presente` decimal(10,2) NOT NULL,
  `comprador_presente` varchar(100) DEFAULT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime DEFAULT NULL,
  PRIMARY KEY (`id_presente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presentes`
--

LOCK TABLES `presentes` WRITE;
/*!40000 ALTER TABLE `presentes` DISABLE KEYS */;
/*!40000 ALTER TABLE `presentes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` bigint NOT NULL AUTO_INCREMENT,
  `id_pessoa_fk` bigint NOT NULL,
  `tipo` varchar(100) NOT NULL,
  `login` varchar(100) NOT NULL,
  `senha` varchar(100) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `dataModificacao` datetime DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `fk_usuario_pessoa` (`id_pessoa_fk`),
  CONSTRAINT `fk_usuario_pessoa` FOREIGN KEY (`id_pessoa_fk`) REFERENCES `pessoa` (`id_pessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,1,'admin','joao_admin','senha123','2024-11-20 11:00:00',NULL),(2,2,'admin','maria_admin','senha456','2024-11-19 11:00:00',NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-09  0:19:47
