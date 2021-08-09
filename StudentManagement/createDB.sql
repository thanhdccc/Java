create database studentdb;

use studentdb;

create table classes(
	class_id int not null auto_increment,
    class_name varchar(100) not null,
    primary key (class_id)
);

create table students(
	student_id int not null auto_increment,
    rollnumber varchar(10) not null,
    student_name varchar(150) not null,
    dob date not null,
    gender varchar(10) not null,
    student_address varchar(2000) not null,
    student_hobby varchar(2000) not null,
    class_id int not null,
    primary key (student_id),
    foreign key (class_id) references classes (class_id)
);