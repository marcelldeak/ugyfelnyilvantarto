INSERT INTO `CRM_DB`.`Application_user` (`id`, `first_name`, `last_name`, `picture`, `active`, `date_of_birth`, `email`, `expiration_date`, `password`) VALUES ('0', 'Normal', 'User', 'null', 1, '1990-01-01', 'user@clientbase.hu', '2100-01-01', 'BPiZbadjt6lpsQKO4wB1aerzpjVIbdqyEdUSyFud+Ps=');
INSERT INTO `CRM_DB`.`Application_user` (`id`, `first_name`, `last_name`, `picture`, `active`, `date_of_birth`, `email`, `expiration_date`, `password`) VALUES ('1', 'Admin', 'User', 'null', 1, '1990-01-01', 'admin@clientbase.hu', '2100-01-01', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=');

INSERT INTO `CRM_DB`.`Application_user_User_role` (`user_id`, `roles_id`) VALUES ('0', '1');
INSERT INTO `CRM_DB`.`Application_user_User_role` (`user_id`, `roles_id`) VALUES ('1', '0');


