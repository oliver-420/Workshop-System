CREATE TABLE Mechanic
(
    ID          Number GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    NAME        varchar(20),
    HOURLY_WAGE float
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
    CUSTOMER_CARD_ID INT NULL
        CONSTRAINT C_FR_CK REFERENCES CustomerCard (ID)
);

CREATE TABLE Task
(
    ID          Number GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    NAME        varchar(50),
    START_DATE  DATE,
    CAR_ID      Number NOT NULL,
    MECHANIC_ID Number NOT NULL,
    CUSTOMER_ID  Number NOT NULL,
    CONSTRAINT TASK_CAR_FK FOREIGN KEY (CAR_ID) REFERENCES Car (ID)
        on delete cascade,
    CONSTRAINT TASK_MECHANIC_FK FOREIGN KEY (MECHANIC_ID) REFERENCES Mechanic (ID)
        on delete cascade,
    CONSTRAINT TASK_CUSTOMER_FK FOREIGN KEY (CUSTOMER_ID) REFERENCES Customer (ID)
        on delete cascade
);

CREATE TABLE SubTask
(
    ID          Number GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    DESCRIPTION varchar(100),
    DURATION    BINARY_DOUBLE,
    TASK_ID     Number NOT NULL,
    IS_DONE     NUMBER(1),
    CONSTRAINT SUBTASK_TASK_FK FOREIGN KEY (TASK_ID) REFERENCES Task (ID)
        on delete cascade
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

CREATE TABLE Part
(
    SERIAL_NUMBER     VARCHAR(50) NOT NULL PRIMARY KEY,
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
    PART_ID  VARCHAR(50) NOT NULL,
    CONSTRAINT TASK_PART_MAPPING_TASK_FK FOREIGN KEY (TASK_ID) REFERENCES Task (ID) on delete cascade,
    CONSTRAINT TASK_PART_MAPPING_PART_FK FOREIGN KEY (PART_ID) REFERENCES Part (SERIAL_NUMBER) ON DELETE cascade ,
    QUANTITY INT,
    PRICE    numeric
);

CREATE TABLE Invoice
(
    ID          Number GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    MECHANIC    VARCHAR(20),
    CUSTOMER    VARCHAR(20),
    CAR         VARCHAR(20),
    TOTAL_DURATION VARCHAR(20),
    TOTAL_PRICE VARCHAR(20),
    ISSUE_DATE  VARCHAR(20),
    TASK_ID     Number NOT NULL,
    CONSTRAINT TASK_FK FOREIGN KEY (TASK_ID) REFERENCES Task (ID) on delete cascade
);

CREATE TABLE CompatibleCars
(
    ID              Number GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    CAR_ID          Number      NOT NULL,
    PART_SERIAL_NUM VARCHAR(20) NOT NULL,
    CONSTRAINT CAR_PART_MAPPING_CAR_FK FOREIGN KEY (CAR_ID) REFERENCES Car (ID) on delete cascade,
    CONSTRAINT CAR_PART_MAPPING_PART_FK FOREIGN KEY (PART_SERIAL_NUM) REFERENCES Part (SERIAL_NUMBER) ON DELETE cascade
);

commit /*Suicide*/ work;