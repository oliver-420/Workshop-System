CREATE TABLE Mechanic
(
    ID         INT NOT NULL
        CONSTRAINT M_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    FIRST_NAME varchar(20),
    LAST_NAME  varchar(20),
    HOURLY_PAY numeric
);

CREATE TABLE Task
(
    ID         INT NOT NULL
        CONSTRAINT T_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    START_DATE DATE,
    HOURS      NUMERIC
);

CREATE TABLE FuelType
(
    TYPE varchar(20) NOT NULL
        CONSTRAINT FT_PK PRIMARY KEY
);

CREATE TABLE Car
(
    ID                INT NOT NULL
        CONSTRAINT C_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    MANUFACTURER      varchar(50),
    MODEL             varchar(50),
    PRODUCTION_YEAR   numeric,
    REGISTRATION_YEAR numeric,
    OWNER_NAME        varchar(50),
    FUEL_TYPE         varchar(20)
        CONSTRAINT C_FR_FK REFERENCES FuelType (TYPE)

);


CREATE TABLE Part
(
    ID        INT NOT NULL
        CONSTRAINT P_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    NAME      varchar(20),
    PRICE     numeric,
    UP_CHARGE numeric
);

CREATE TABLE Invoice
(
    ID INT NOT NULL
        CONSTRAINT I_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY
);

CREATE TABLE CustomerCard
(
    ID         INT NOT NULL
        CONSTRAINT CC_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    FIRST_NAME varchar(20),
    LAST_NAME  varchar(20),
    DISCOUNT   numeric,
    JOINED_AT  DATE

);

CREATE TABLE Customer
(
    ID         INT NOT NULL
        CONSTRAINT Cu_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    FIRST_NAME varchar(20),
    LAST_NAME  varchar(20)
);

/*drop table Car;
drop table FuelType;
drop table Task;
drop table Mechanic;
drop table Customer;
drop table CustomerCard;
drop table Invoice;
drop table Part;


CREATE TABLE Mechanic
(
    ID         INT GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY ,
    FIRST_NAME varchar(20),
    LAST_NAME  varchar(20),
    HOURLY_PAY numeric
);

CREATE TABLE Task
(
    ID         INT GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY ,
    START_DATE DATE,
    HOURS      NUMERIC
);

CREATE TABLE FuelType
(
    TYPE varchar(20) NOT NULL
        CONSTRAINT FT_PK PRIMARY KEY
);

CREATE TABLE Car
(
    ID         INT GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY ,
    MANUFACTURER      varchar(50),
    MODEL             varchar(50),
    PRODUCTION_YEAR   numeric,
    REGISTRATION_YEAR numeric,
    OWNER_NAME        varchar(50),
    FUEL_TYPE         varchar(20)
        CONSTRAINT C_FR_FK REFERENCES FuelType (TYPE)

);


CREATE TABLE Part
(
    ID         INT GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY ,
    NAME      varchar(20),
    PRICE     numeric,
    UP_CHARGE numeric
);

CREATE TABLE Invoice
(
    ID         INT GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY
);

CREATE TABLE CustomerCard
(
    ID         INT GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY ,
    FIRST_NAME varchar(20),
    LAST_NAME  varchar(20),
    DISCOUNT   numeric,
    JOINED_AT  DATE

);

CREATE TABLE Customer
(
    ID         INT GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY ,
    FIRST_NAME varchar(20),
    LAST_NAME  varchar(20)
);*/