
CREATE TABLE PRODUCT (
	PRODUCT_ID 					INT NOT NULL AUTO_INCREMENT,
	DEPARTMENT_ID 				INT,
	BARCODE 					VARCHAR2(100),
	FULL_NAME 					VARCHAR2(100),
	SHORT_NAME_ENG 				VARCHAR2(100),
	SHORT_NAME_OTHER 			VARCHAR2(100),
	INVENTORY_ON_HAND 			NUMBER(10,0),
	SAFETY_INVENTORY_ON_HAND 	NUMBER(10,0),
	COST_AMT 					NUMBER(10,2),
	SALES_AMT 					NUMBER(10,2),
	PRIMARY KEY (PRODUCT_ID)
);

CREATE TABLE DEPARTMENT (
	DEPARTMENT_ID 				INT NOT NULL AUTO_INCREMENT,
	DEPARTMENT_NAME				VARCHAR2(100),
	PRIMARY KEY (DEPARTMENT_ID)
);