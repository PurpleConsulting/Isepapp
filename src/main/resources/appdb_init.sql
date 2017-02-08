
-- alows accesse$ outside of the container

USE `APPDB`;

CREATE USER 'mysql'@'%' IDENTIFIED BY 'paris06';

GRANT ALL PRIVILEGES ON APPDB.* TO 'mysql'@'%' IDENTIFIED BY 'paris06' WITH GRANT OPTION;
