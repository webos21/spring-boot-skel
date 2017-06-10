SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS test_user;




/* Create Tables */

CREATE TABLE test_user
(
	-- Acount Identification String
	id varchar(32) NOT NULL COMMENT 'Acount Identification String',
	-- the name of test user
	name varchar(64) COMMENT 'the name of test user',
	PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;



