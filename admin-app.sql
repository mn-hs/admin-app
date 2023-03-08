BEGIN TRANSACTION;

DROP TABLE IF EXISTS line_item;
DROP TABLE IF EXISTS sale;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS product;

CREATE TABLE product (
	product_id serial,
	name varchar(128) NOT NULL,
	description varchar NOT NULL,
	price decimal (10,2) NOT NULL,
	category varchar(128) NOT NULL,
	CONSTRAINT PK_product PRIMARY KEY (product_id)
);

CREATE TABLE customer (
	customer_id serial,
	first_name varchar(70) NOT NULL,
	last_name varchar(70) NOT NULL,
	st_address varchar(128) NOT NULL,
	city varchar(64) NOT NULL,
	state char(2) NOT NULL,
	zip_code char(5) NOT NULL,
	CONSTRAINT PK_customer PRIMARY KEY (customer_id)
);

CREATE TABLE sale (
	sale_id serial,
	customer_id INT NOT NULL,
	sale_date date NOT NULL,
	ship_date date,
	CONSTRAINT PK_sale PRIMARY KEY (sale_id),
	CONSTRAINT FK_sale_customer FOREIGN KEY(customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE sale_item (
	sale_item_id serial,
	sale_id INT NOT NULL,
	product_id INT NOT NULL,
	quantity INT NOT NULL,
	CONSTRAINT PK_sale_item PRIMARY KEY (sale_item_id),
	CONSTRAINT FK_sale_item_sale FOREIGN KEY(sale_id) REFERENCES sale(sale_id),
	CONSTRAINT FK_sale_item_id FOREIGN KEY(product_id) REFERENCES product(product_id)
);

COMMIT TRANSACTION;