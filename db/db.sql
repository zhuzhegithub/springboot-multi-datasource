
create database test1;
create database test2;

use test1;

CREATE TABLE t_user (
id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
`username` VARCHAR(20)
)

use test2;

CREATE TABLE t_user (
id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20)
)

