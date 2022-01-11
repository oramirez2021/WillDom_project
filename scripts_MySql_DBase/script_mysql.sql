CREATE DATABASE `HulkStore` /*!40100 DEFAULT CHARACTER SET latin1 */;

-- HulkStore.CATEGORY definition

CREATE TABLE `CATEGORY` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;


-- HulkStore.PRODUCT definition

CREATE TABLE `PRODUCT` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) NOT NULL,
  `category_id` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `image_path` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- HulkStore.`USER` definition

CREATE TABLE `USER` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `user_last_name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- HulkStore.KARDEX definition

CREATE TABLE `KARDEX` (
  `id` int(11) NOT NULL,
  `product_id` varchar(100) NOT NULL,
  `transaction_date_time` varchar(100) NOT NULL,
  `ope_type` varchar(100) NOT NULL COMMENT '"E" entrata, "S" salida',
  `cant` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--INSERTAR TABLA PRODUCT

INSERT INTO HulkStore.PRODUCT
(product_id, product_name, category_id, stock)
VALUES(1, 'GILDAN CAMISETA T-SHIRT ADULTO', 1, 20);
INSERT INTO HulkStore.PRODUCT
(product_id, product_name, category_id, stock)
VALUES(2, 'GILDAN CAMISETA POLO ADULTO', 1, 25);
INSERT INTO HulkStore.PRODUCT
(product_id, product_name, category_id, stock)
VALUES(3, 'GILDAN BUSO CAPOTA ADULTO', 1, 60);
INSERT INTO HulkStore.PRODUCT
(product_id, product_name, category_id, stock)
VALUES(4, 'GILDAN BUSO SENCILLO ADULTO', 1, 100);
INSERT INTO HulkStore.PRODUCT
(product_id, product_name, category_id, stock)
VALUES(5, 'GILDAN BUSO POLO DAMA', 1, 55);
INSERT INTO HulkStore.PRODUCT
(product_id, product_name, category_id, stock)
VALUES(6, 'T-SHIRT INFANTIL', 1, 30);


--INSERTAR TABLA CATEGORY


INSERT INTO HulkStore.CATEGORY
(category_id, category_name)
VALUES(1, 'CAMISETAS');
INSERT INTO HulkStore.CATEGORY
(category_id, category_name)
VALUES(2, 'COMICS');
INSERT INTO HulkStore.CATEGORY
(category_id, category_name)
VALUES(3, 'VASOS');
INSERT INTO HulkStore.CATEGORY
(category_id, category_name)
VALUES(4, 'JUGUETES');





