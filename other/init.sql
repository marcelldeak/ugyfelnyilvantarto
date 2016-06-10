DROP DATABASE IF EXISTS CRM_DB;
CREATE DATABASE CRM_DB;
USE CRM_DB;

CREATE TABLE `Address` (
  `id` bigint(20) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Application_user` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `active` bit(1) NOT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `expiration_date` date DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Contact` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Contact_channel` (
  `id` bigint(20) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `contact_channel_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Customer` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `VAT_number` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY (`address_id`),
  FOREIGN KEY (`address_id`) REFERENCES `Address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Event` (
  `id` bigint(20) NOT NULL,
  `date_of_end` datetime DEFAULT NULL,
  `date_of_start` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Invitation` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `event_id` bigint(20) DEFAULT NULL,
  `recipient_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY (`event_id`),
  KEY (`recipient_id`),
  FOREIGN KEY (`event_id`) REFERENCES `Event` (`id`),
  FOREIGN KEY (`recipient_id`) REFERENCES `Application_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Note` (
  `id` bigint(20) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Pending_registrations` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY (`user_id`),
  FOREIGN KEY (`user_id`) REFERENCES `Application_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Project` (
  `id` bigint(20) NOT NULL,
  `deadline` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `User_role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `Application_user_Contact_channel` (
  `User_id` bigint(20) NOT NULL,
  `contactChannels_id` bigint(20) NOT NULL,
  UNIQUE KEY (`contactChannels_id`),
  KEY (`User_id`),
  FOREIGN KEY (`User_id`) REFERENCES `Application_user` (`id`),
  FOREIGN KEY (`contactChannels_id`) REFERENCES `Contact_channel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Application_user_Event` (
  `User_id` bigint(20) NOT NULL,
  `events_id` bigint(20) NOT NULL,
  KEY (`events_id`),
  KEY (`User_id`),
  FOREIGN KEY (`User_id`) REFERENCES `Application_user` (`id`),
  FOREIGN KEY (`events_id`) REFERENCES `Event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Application_user_User_role` (
  `User_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  KEY (`roles_id`),
  KEY (`User_id`),
  FOREIGN KEY (`roles_id`) REFERENCES `User_role` (`id`),
  FOREIGN KEY (`User_id`) REFERENCES `Application_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Contact_Contact_channel` (
  `Contact_id` bigint(20) NOT NULL,
  `contactChannels_id` bigint(20) NOT NULL,
  UNIQUE KEY (`contactChannels_id`),
  KEY (`Contact_id`),
  FOREIGN KEY (`Contact_id`) REFERENCES `Contact` (`id`),
  FOREIGN KEY (`contactChannels_id`) REFERENCES `Contact_channel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Customer_Contact` (
  `Customer_id` bigint(20) NOT NULL,
  `contacts_id` bigint(20) NOT NULL,
  UNIQUE KEY (`contacts_id`),
  KEY (`Customer_id`),
  FOREIGN KEY (`Customer_id`) REFERENCES `Customer` (`id`),
  FOREIGN KEY (`contacts_id`) REFERENCES `Contact` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Customer_Event` (
  `Customer_id` bigint(20) NOT NULL,
  `events_id` bigint(20) NOT NULL,
  UNIQUE KEY (`events_id`),
  KEY (`Customer_id`),
  FOREIGN KEY (`Customer_id`) REFERENCES `Customer` (`id`),
  FOREIGN KEY (`events_id`) REFERENCES `Event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Customer_Project` (
  `Customer_id` bigint(20) NOT NULL,
  `projects_id` bigint(20) NOT NULL,
  UNIQUE KEY (`projects_id`),
  KEY (`Customer_id`),
  FOREIGN KEY (`Customer_id`) REFERENCES `Customer` (`id`),
  FOREIGN KEY (`projects_id`) REFERENCES `Project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Event_Note` (
  `Event_id` bigint(20) NOT NULL,
  `notes_id` bigint(20) NOT NULL,
  UNIQUE KEY (`notes_id`),
  KEY (`Event_id`),
  FOREIGN KEY (`notes_id`) REFERENCES `Note` (`id`),
  FOREIGN KEY (`Event_id`) REFERENCES `Event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `CRM_DB`.`User_role` (`id`, `name`) VALUES ('0', 'ADMIN');
INSERT INTO `CRM_DB`.`User_role` (`id`, `name`) VALUES ('1', 'USER');
INSERT INTO `CRM_DB`.`hibernate_sequence` (`next_val`) VALUES (2);
