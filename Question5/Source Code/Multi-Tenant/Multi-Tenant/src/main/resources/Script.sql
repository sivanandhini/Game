CREATE TABLE IF NOT EXISTS `clt_data_sources` (
  `clt_id` int(11) NOT NULL AUTO_INCREMENT,
  `clt_ds_url` varchar(50) DEFAULT NULL,
  `clt_ds_username` varchar(50) DEFAULT NULL,
  `clt_ds_password` varchar(50) DEFAULT NULL,
  `clt_ds_tenant_id` varchar(50) DEFAULT NULL,
  `clt_ds_active` enum('Y','N') DEFAULT NULL,
  PRIMARY KEY (`clt_id`),
  UNIQUE KEY `clt_ds_tenant_id` (`clt_ds_tenant_id`)
);


DELETE FROM `clt_data_sources`;

INSERT INTO `clt_data_sources` (`clt_id`, `clt_ds_url`, `clt_ds_username`, `clt_ds_password`, `clt_ds_tenant_id`, `clt_ds_active`) VALUES
	(1, 'jdbc:mysql://10.100.4.52:3306/PCS_DIRECTPAY', 'arm_dev', 'arm_dev@314', 'GSS', 'Y'),
	(2, 'jdbc:mysql://10.100.4.52:3306/HERO_COURTPAY', 'arm_dev', 'arm_dev@314', 'KITE', 'Y'),
	(3, 'jdbc:mysql://10.100.4.52:3306/DocuTrail', 'arm_dev', 'arm_dev@314', 'CAS', 'Y');

CREATE TABLE IF NOT EXISTS `usr_t_user` (
  `usr_id` int(11) NOT NULL AUTO_INCREMENT,
  `usr_user_name` varchar(50) DEFAULT NULL,
  `usr_password` varchar(50) DEFAULT NULL,
  `usr_gender` varchar(10) DEFAULT NULL,
  `usr_phonenumber` varchar(10) DEFAULT NULL,
  `usr_email` varchar(50) DEFAULT NULL,
  `usr_active` enum('Y','N') DEFAULT NULL,
  PRIMARY KEY (`usr_id`)
);

DELETE FROM `usr_t_user`;

INSERT INTO `usr_t_user` (`usr_id`, `usr_user_name`, `usr_password`, `usr_gender`, `usr_phonenumber`, `usr_email`, `usr_active`) VALUES
	(1, 'Siva', 'Siva@123', 'FEMALE', '1234567', 'siva@gmail.com', 'Y'),
	(2, 'John', 'John@123', 'MALE', '5435324', 'john@gmail.com', 'Y');


CREATE TABLE `usr_web_punch` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`usr_user_id` INT(11) NULL DEFAULT NULL,
	`punch_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
);

DELETE FROM `usr_web_punch`;

