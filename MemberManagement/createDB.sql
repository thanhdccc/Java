create database memberdb;

use memberdb;

create table members (
	id int not null auto_increment,
    username varchar(20) not null,
    password varchar(200) not null,
    name varchar(100) not null,
    dob Date not null,
    email varchar(100) not null,
    phone varchar(100) not null,
    address varchar(2000) not null,
    primary key (id)
);

drop database memberdb;

INSERT INTO members (username, password, name, dob, email, phone, address) VALUES (?, ?, ?, ?, ?, ?, ?);

UPDATE members SET username = ?, password = ?, name = ?, dob = ?, email = ?, phone = ?, address = ? WHERE id = ?;

SELECT id, username, password, name, dob, email, phone, address FROM members WHERE id = ?;