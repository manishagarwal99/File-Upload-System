--
-- Table structure for table `patient1`
--

DROP TABLE IF EXISTS `patient1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient1` (
  `file_id` int NOT NULL AUTO_INCREMENT,
  `patient_id` int NOT NULL,
  `doctor_id` int NOT NULL,
  `appointment_date` varchar(45) NOT NULL,
  `appointment_time` varchar(45) NOT NULL,
  `file_path` varchar(512) NOT NULL,
  `upload_ts` timestamp(6) NOT NULL,
  `file_title` varchar(256) NOT NULL,
  `file_description` varchar(1024) NOT NULL,
  `newfile_title` varchar(45) NOT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient1`
--

LOCK TABLES `patient1` WRITE;
/*!40000 ALTER TABLE `patient1` DISABLE KEYS */;
INSERT INTO `patient1` VALUES (1,1,52,'2021-03-29','11:00','\\\\AppointmentSystem\\uploadedFiles\\\\DSCN2976-001.JPG','2021-02-23 14:48:59.394000','DSCN2976-001.JPG','Moon pictures','1614091739394_1_52.txt'),(2,1,52,'2021-03-29','11:00','\\\\AppointmentSystem\\uploadedFiles\\\\DSCN2977-001.JPG','2021-02-23 14:49:05.593000','DSCN2977-001.JPG','Moon pictures/','1614091745593_1_52.txt'),(3,1,52,'2021-03-29','11:00','\\\\AppointmentSystem\\uploadedFiles\\\\DSCN2973-001.JPG','2021-02-23 14:51:58.685000','DSCN2973-001.JPG','moon','1614091918685_1_52.txt');
/*!40000 ALTER TABLE `patient1` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-21 18:20:27
