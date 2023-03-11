CREATE TABLE `registered_users2` (
  `id` int NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `date_of_birth` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(100) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(100) NOT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `user_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY(`email`)
);

CREATE TABLE `vehicles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `air_condition` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `fuel_type` varchar(255) DEFAULT NULL,
  `luggage_capacity` varchar(255) DEFAULT NULL,
  `mileage` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `seating_capacity` varchar(255) DEFAULT NULL,
  `transmission` varchar(255) DEFAULT NULL,
  `vehicle_image` varchar(255) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `vehiclercimage` varchar(255) DEFAULT NULL,
  `vehiclepucimage` varchar(255) DEFAULT NULL,
  `vehicle_insurance_image` varchar(255) DEFAULT NULL,
  `vehicle_agreement_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
 FOREIGN KEY (`user_id`) REFERENCES `registered_users2` (`id`),
   FOREIGN KEY (`category_id`) REFERENCES `vehicle_categories` (`category_id`)
);

CREATE TABLE `ratings` (
  `rating_id` int NOT NULL AUTO_INCREMENT,
  `no_of_stars` int NOT NULL,
  `vehicle_id` int DEFAULT NULL,
  PRIMARY KEY (`rating_id`),
  CONSTRAINT  FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`id`)
);

CREATE TABLE `vehicle_categories` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  PRIMARY KEY (`category_id`)
);

CREATE TABLE `drivers` (
  `d_id` int NOT NULL AUTO_INCREMENT,
  `d_address` varchar(255) DEFAULT NULL,
  `d_alt_mob_no` varchar(255) DEFAULT NULL,
  `d_blood_group` varchar(255) DEFAULT NULL,
  `d_city` varchar(255) DEFAULT NULL,
  `d_dob` varchar(255) DEFAULT NULL,
  `d_education` varchar(255) DEFAULT NULL,
  `d_first_name` varchar(255) DEFAULT NULL,
  `d_gender` varchar(255) DEFAULT NULL,
  `d_known_languages` varchar(255) DEFAULT NULL,
  `d_last_name` varchar(255) DEFAULT NULL,
  `d_ratings` varchar(255) DEFAULT NULL,
  `d_riding_experience` varchar(255) DEFAULT NULL,
  `driver_image` varchar(255) DEFAULT NULL,
  `driver_category_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `driver_fitness_certificate_image` varchar(255) DEFAULT NULL,
  `driver_agreement_image` varchar(255) DEFAULT NULL,
  `driving_license_image` varchar(255) DEFAULT NULL,
  `about` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`d_id`),
  CONSTRAINT  FOREIGN KEY (`user_id`) REFERENCES `registered_users2` (`id`),
  CONSTRAINT  FOREIGN KEY (`driver_category_id`) REFERENCES `driver_categories` (`d_category_id`)
);

CREATE TABLE `driver_ratings` (
  `d_rating_id` int NOT NULL AUTO_INCREMENT,
  `d_no_of_stars` int NOT NULL,
  `driver_d_id` int DEFAULT NULL,
  PRIMARY KEY (`d_rating_id`),
  CONSTRAINT  FOREIGN KEY (`driver_d_id`) REFERENCES `drivers` (`d_id`)
);

CREATE TABLE `driver_categories` (
  `d_category_id` int NOT NULL AUTO_INCREMENT,
  `driver_category_description` varchar(255) DEFAULT NULL,
  `driver_category_title` varchar(100) NOT NULL,
  PRIMARY KEY (`d_category_id`)
) ;