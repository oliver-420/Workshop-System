CREATE TABLE Mechanic
(
    ID          Number GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    NAME        varchar(20),
    HOURLY_WAGE float
);

CREATE TABLE Task
(
    ID          Number GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    NAME        varchar(20),
    DESCRIPTION varchar(100),
    START_DATE  DATE,
    DURATION    NUMERIC
);

create TABLE MechanicTaskMapping
(
    ID          Number GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    MECHANIC_ID Number NOT NULL,
    TASK_ID     Number NOT NULL,
    CONSTRAINT MECHANIC_TASK_MAPPING_MECHANIC_FK FOREIGN KEY (MECHANIC_ID) REFERENCES Mechanic (ID)
        on delete cascade,
    CONSTRAINT MECHANIC_TASK_MAPPING_TASK_FK FOREIGN KEY (TASK_ID) REFERENCES Task (ID)
        on delete cascade

);

CREATE TABLE CustomerCard
(
    ID        Number GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    DISCOUNT  numeric,
    JOINED_AT DATE

);

CREATE TABLE Customer
(
    ID               Number GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    NAME             varchar(20),
    EMAIL            varchar(20),
    PHONE_NUMBER     varchar(20),
    DATE_OF_BIRTH    DATE,
    CUSTOMER_CARD_ID INT NOT NULL
        CONSTRAINT C_FR_CK REFERENCES CustomerCard (ID)
);


CREATE TABLE Part
(
    SERIAL_NUMBER     VARCHAR(20) NOT NULL PRIMARY KEY,
    NAME              varchar(20),
    MANUFACTURER      varchar(20),
    PRICE             numeric,
    ADDITIONAL_CHARGE numeric,
    QUANTITY          INT
);

create TABLE TaskPartMapping
(
    ID       Number GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    TASK_ID  Number      NOT NULL,
    PART_ID  VARCHAR(20) NOT NULL,
    CONSTRAINT TASK_PART_MAPPING_TASK_FK FOREIGN KEY (TASK_ID) REFERENCES Task (ID),
    CONSTRAINT TASK_PART_MAPPING_PART_FK FOREIGN KEY (PART_ID) REFERENCES Part (SERIAL_NUMBER),
    QUANTITY INT,
    PRICE    numeric
);

CREATE TABLE Car
(
    ID                Number GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    MODEL             varchar(50),
    MANUFACTURER      varchar(50),
    PRODUCTION_YEAR   numeric,
    REGISTRATION_YEAR numeric,
    OWNER_NAME        varchar(50),
    FUEL_TYPE         varchar(20),
    NUMBER_PLATE      varchar(20)
);

CREATE TABLE Invoice
(
    ID          Number GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    ISSUE_DATE  DATE,
    AMOUNT      numeric,
    IS_PAID     NUMBER(1),
    TASK_ID     INT NOT NULL
        CONSTRAINT T_FR_CK REFERENCES Task (ID),
    CUSTOMER_ID INT NOT NULL
        CONSTRAINT I_FR_CK REFERENCES Customer (ID)
);

CREATE TABLE CompatibleCars
(
    ID              Number GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    CAR_ID          Number      NOT NULL,
    PART_SERIAL_NUM VARCHAR(20) NOT NULL,
    CONSTRAINT CAR_PART_MAPPING_CAR_FK FOREIGN KEY (CAR_ID) REFERENCES Car (ID),
    CONSTRAINT CAR_PART_MAPPING_PART_FK FOREIGN KEY (PART_SERIAL_NUM) REFERENCES Part (SERIAL_NUMBER)
);

commit /*Suicide*/ work;