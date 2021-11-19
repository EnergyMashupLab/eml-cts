CREATE DATABASE IF NOT EXISTS nist_cts_eml;
USE nist_cts_eml;

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
);

DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `counter_id` bigint NOT NULL,
  `end_time` datetime NOT NULL,
  `position_party` bigint DEFAULT NULL,
  `quantity` bigint DEFAULT NULL,
  `start_time` datetime NOT NULL,
  `transaction_id` bigint DEFAULT NULL,
  PRIMARY KEY (`counter_id`)
);


LOCK TABLES `hibernate_sequence` WRITE;
INSERT INTO `hibernate_sequence` VALUES (1);
UNLOCK TABLES;
