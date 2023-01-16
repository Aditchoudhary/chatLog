create table using below Script

I have used MYSQL

++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


CREATE TABLE `chatlog` (
  `chatlogid` int NOT NULL AUTO_INCREMENT,
  `user` varchar(16) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `issent` bit(1) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`chatlogid`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++





update connections props in app.props