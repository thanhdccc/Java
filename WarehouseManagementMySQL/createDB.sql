create database warehouse;

use warehouse;

create table categories (
	id int not null auto_increment,
    name varchar(100) not null,
    primary key (id)
);

create table products (
	id int not null auto_increment,
    name varchar(100) not null,
    price float not null,
    quantity int not null,
    category_id int not null,
    primary key (id),
    foreign key (category_id) references categories (id)
);

create table orders (
	id int not null auto_increment,
    name varchar(100) not null,
    price float,
    primary key (id)
);

create table orderdetail(
	id int not null auto_increment,
	order_id int not null,
	product_id int not null,
    product_quantity int not null,
    product_price float not null,
    primary key (id),
    foreign key (order_id) references orders (id),
    foreign key (product_id) references products (id)
);

drop database warehouse