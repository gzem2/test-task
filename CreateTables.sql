CREATE TABLE BANK (
    ID BINARY(255) NOT NULL PRIMARY KEY,
    BANKNAME VARCHAR(255)
)

CREATE TABLE BANKCUSTOMERS (
    BANKID BINARY(255) NOT NULL,
    CUSTOMERID BINARY(255) NOT NULL,
    PRIMARY KEY(BANKID,CUSTOMERID),
    CONSTRAINT FKGN2M9ITI202QC30F9NUQDSMXM FOREIGN KEY(BANKID) REFERENCES BANK(ID)
)

CREATE TABLE CREDIT (
    ID BINARY(255) NOT NULL PRIMARY KEY,
    CREDITLIMIT NUMERIC(19,2),
    INTERESTRATE DOUBLE,
    MONTHSTERM INTEGER,
    BANKID BINARY(255),
    STARTDATE DATE,
    CONSTRAINT FKEHTBFNJ9QHJQ42SHXGHEF7TQ4 FOREIGN KEY(BANKID) REFERENCES BANK(ID)
)

CREATE TABLE CREDITOFFER (
    ID BINARY(255) NOT NULL PRIMARY KEY,
    CREDITSUM NUMERIC(19,2),
    CREDITID BINARY(255),
    CUSTOMERID BINARY(255),
    STARTDATE DATE,
    INTERESTDEBT NUMERIC(19,2),
    MAINDEBT NUMERIC(19,2),
    MONTHSTERM INTEGER,
    CONSTRAINT FKFPX8OR1E8NG4N7JNPVE524CX6 FOREIGN KEY(CREDITID) REFERENCES CREDIT(ID)
)

CREATE TABLE CUSTOMER (
    ID BINARY(255) NOT NULL PRIMARY KEY,
    CUSTOMERNAME VARCHAR(255),
    EMAIL VARCHAR(255),
    PASSPORT VARCHAR(255),
    PHONE VARCHAR(255)
)

CREATE TABLE PAYMENT (
    ID BINARY(255) NOT NULL PRIMARY KEY,
    DATEOFPAYMENT DATE,
    INTERESTDEBT NUMERIC(19,2),
    MAINDEBT NUMERIC(19,2),
    PAYMENT NUMERIC(19,2),
    CREDITOFFERID BINARY(255),
    PAYMENTNUM INTEGER,
    CONSTRAINT FKOWF4XJXW8I9P3U8MAFQY3G2CM FOREIGN KEY(CREDITOFFERID) REFERENCES CREDITOFFER(ID)
)

CREATE TABLE BANK_CREDIT (
    BANK_ID BINARY(255) NOT NULL,
    CREDITS_ID BINARY(255) NOT NULL,
    PRIMARY KEY(BANK_ID,CREDITS_ID),
    CONSTRAINT FK5BM5SPSE3DB5D8G1VGYCROEVJ FOREIGN KEY(CREDITS_ID) REFERENCES CREDIT(ID),
    CONSTRAINT FKKOQBUU8JV1OGM6MCWGM187701 FOREIGN KEY(BANK_ID) REFERENCES BANK(ID),
    CONSTRAINT UK_IQEPOTT2VS955SQEC2HP72PHO UNIQUE(CREDITS_ID)
)

ALTER TABLE BANKCUSTOMERS ADD CONSTRAINT FK5WSRK40D6UIC38A48O26IOXML FOREIGN KEY(CUSTOMERID) REFERENCES CUSTOMER(ID)

ALTER TABLE CREDITOFFER ADD CONSTRAINT FKFFJ56JJCWXJ9NNTOI7QEHA6WI FOREIGN KEY(CUSTOMERID) REFERENCES CUSTOMER(ID)