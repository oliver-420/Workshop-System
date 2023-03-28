INSERT INTO FUELTYPE (TYPE)
VALUES ('Diesel');
INSERT INTO FUELTYPE (TYPE)
VALUES ('Benzin');
INSERT INTO FUELTYPE (TYPE)
VALUES ('Elektro');
INSERT INTO FUELTYPE (TYPE)
VALUES ('Hybrid');
INSERT INTO FUELTYPE (TYPE)
VALUES ('Wasserstoff');


INSERT INTO CAR (MANUFACTURER, MODEL, PRODUCTION_YEAR, REGISTRATION_YEAR, OWNER_NAME, FUEL_TYPE)
VALUES ('Volkswagen', 'Golf', 2005, 2005, 'John Smith', 'Benzin');
INSERT INTO Car (Manufacturer, Model, Production_Year, Registration_Year, Owner_Name, Fuel_Type)
VALUES ('Volkswagen', 'Passerati', 2006, 2006, 'Nicht Oliver', 'Diesel');

Insert Into CUSTOMER (FIRST_NAME, LAST_NAME)
VALUES ('Oliver', 'Kraus');
INSERT INTO CUSTOMER (FIRST_NAME, LAST_NAME)
VALUES ('John', 'Smith');
INSERT INTO CUSTOMER (FIRST_NAME, LAST_NAME)
VALUES ('Max', 'Mustermann');

INSERT INTO MECHANIC (FIRST_NAME, LAST_NAME, HOURLY_PAY)
VALUES ('Oliver', 'Daxi', 10);
INSERT INTO MECHANIC (FIRST_NAME, LAST_NAME, HOURLY_PAY)
VALUES ('Linus', 'Nesti', 15);

Insert Into CUSTOMERCARD (FIRST_NAME, LAST_NAME, DISCOUNT, JOINED_AT)
VALUES ('Niemand', 'Nix', 3, '2019-01-01');

Insert Into PART (NAME, PRICE, UP_CHARGE)
VALUES ('Reifen', 100, 10);
Insert Into PART (NAME, PRICE, UP_CHARGE)
VALUES ('Bremsscheibe', 200, 20);
INSERT INTO PART (NAME, PRICE, UP_CHARGE)
VALUES ('Bremsbelag', 300, 30);
INSERT INTO PART (NAME, PRICE, UP_CHARGE)
VALUES ('Kupplung', 400, 40);

Insert Into TASK (START_DATE, HOURS)
VALUES ('2019-01-01', 1);
Insert Into TASK (START_DATE, HOURS)
VALUES ('2019-01-01', 2);