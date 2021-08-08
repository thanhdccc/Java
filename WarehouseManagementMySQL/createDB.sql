create database warehouse;

use warehouse;

create table categories (
	category_id int not null auto_increment,
    category_name varchar(100) not null,
    primary key (category_id)
);

create table products (
	product_id int not null auto_increment,
    product_name varchar(100) not null,
    product_price float not null,
    product_quantity int not null,
    category_id int not null,
    primary key (product_id),
    foreign key (category_id) references categories (category_id)
);

create table orders (
	order_id int not null auto_increment,
    order_name varchar(100) not null,
    order_price float,
    primary key (order_id)
);

create table product_order(
	order_id int not null,
	product_id int not null,
    product_quantity int not null,
    product_price float not null,
    foreign key (order_id) references orders (order_id),
    foreign key (product_id) references products (product_id)
);