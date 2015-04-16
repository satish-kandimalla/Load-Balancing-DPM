DROP SCHEMA CMPE283PROJ2;
CREATE SCHEMA `CMPE283PROJ2` ;

DROP TABLE VM1;

CREATE TABLE `CMPE283PROJ2`.`VM1` (
  
  `vmname` VARCHAR(45) NOT NULL,
  `cpu_usage` DOUBLE NULL,
  `cpu_usagemhz` DOUBLE NULL,
  `mem_usage` DOUBLE NULL,
  `mem_granted` DOUBLE NULL,
  `mem_active` DOUBLE NULL,
  `mem_consumed` DOUBLE NULL,
  `disk_usage` DOUBLE NULL,
  `disk_read` DOUBLE NULL,
  `disk_write` DOUBLE NULL,
  `net_usage` DOUBLE NULL,
  `net_received` DOUBLE NULL,
  `net_transmitted` DOUBLE NULL,
  `sys_uptime_latest` DOUBLE NULL,
  `timestamp` VARCHAR(45) NOT NULL,
  
  PRIMARY KEY (`Timestamp`));

  
  
  CREATE TABLE `CMPE283`.`VM05_Ubuntu_01_Hourly` (
  
  `vmname` VARCHAR(45) NOT NULL,
  `cpu_usage` DOUBLE NULL,
  `cpu_usagemhz` DOUBLE NULL,
  `mem_usage` DOUBLE NULL,
  `mem_granted` DOUBLE NULL,
  `mem_active` DOUBLE NULL,
  `mem_consumed` DOUBLE NULL,
  `disk_usage` DOUBLE NULL,
  `disk_read` DOUBLE NULL,
  `disk_write` DOUBLE NULL,
  `net_usage` DOUBLE NULL,
  `net_received` DOUBLE NULL,
  `net_transmitted` DOUBLE NULL,
  `sys_uptime_latest` DOUBLE NULL,
  `timestamp` TIMESTAMP NOT NULL,
  
  PRIMARY KEY (`Timestamp`));