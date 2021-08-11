create database studentdb;

use studentdb;

create table classes(
	id int not null auto_increment,
    name varchar(100) not null,
    primary key (id)
);

create table students(
	id int not null auto_increment,
    rollnumber varchar(10) not null,
    name varchar(150) not null,
    dob date not null,
    gender varchar(10) not null,
    address varchar(2000) not null,
    hobby varchar(2000) not null,
    class_id int not null,
    primary key (id),
    foreign key (class_id) references classes (id)
);

drop database studentdb