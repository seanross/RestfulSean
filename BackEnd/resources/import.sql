INSERT INTO `sean_db`.`role` (`code`, `description`) VALUES ('ROLE_ADMIN', 'Administrator');
INSERT INTO `sean_db`.`role` (`code`, `description`) VALUES ('ROLE_USER', 'User');
INSERT INTO `sean_db`.`user` (`username`, `password`) VALUES ('seabby', 'pogi');
INSERT INTO `sean_db`.`user` (`username`, `password`) VALUES ('abigail', 'ganda');
INSERT INTO `sean_db`.`user_details` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `sean_db`.`user_details` (`user_id`, `role_id`) VALUES (1, 2);
INSERT INTO `sean_db`.`user_details` (`user_id`, `role_id`) VALUES (2, 2);
