BEGIN TRANSACTION;

DROP TABLE IF EXISTS sale_item;
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

-- Insert Test Data

INSERT INTO product(name, description, price, category)
VALUES
 ('Product 1', 'Description 1',   9.99, 'books' ),
 ('Product 2', 'Description 2',  19.00, 'appliances' ),
 ('Product 3', 'Description 3', 123.45, 'electronics' ),
 ('Product 4', 'Description 4',   0.99, 'books' );

INSERT INTO customer (first_name, last_name, st_address, city, state, zip_code)
VALUES
 ('Customer1', 'Customer1', 'Addr 1-1', 'City 1', 'S1', 11111),
 ('Customer2', 'Customer2', 'Addr 2-1', 'City 2', 'S2', 22222),
 ('Customer3', 'Customer3', 'Addr 3-1', 'City 3', 'S3', 33333),
 ('Customer4', 'Customer4', 'Addr 4-1', 'City 4', 'S4', 44444);


INSERT INTO sale (customer_id, sale_date, ship_date)
VALUES
 (1, '2022-01-01', null),
 (1, '2022-02-01', '2022-02-02'),
 (2, '2022-03-01', null),
 (2, '2022-01-01', '2022-01-02');

INSERT INTO sale_item (	sale_id, product_id, quantity)
VALUES
 (1, 1, 1),
 (1, 2, 1),
 (1, 4, 1),
 (2, 4, 10),
 (2, 1, 10),
 (3, 1, 100);

COMMIT TRANSACTION;