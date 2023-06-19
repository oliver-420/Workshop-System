INSERT INTO MECHANIC (NAME, HOURLY_WAGE)
VALUES ('John', 20.00);
INSERT INTO MECHANIC (NAME, HOURLY_WAGE)
VALUES ('Oli', 20.00);
INSERT INTO MECHANIC (NAME, HOURLY_WAGE)
VALUES ('Linus', 20.00);
INSERT INTO MECHANIC (NAME, HOURLY_WAGE)
VALUES ('Bajtik', 20.00);

INSERT INTO CAR (MODEL, MANUFACTURER, PRODUCTION_YEAR, REGISTRATION_YEAR, OWNER_NAME, FUEL_TYPE, NUMBER_PLATE)
VALUES ('X5', 'BMW', 2018, 2018, 'John', 'Diesel', '123456789');
INSERT INTO CAR (MODEL, MANUFACTURER, PRODUCTION_YEAR, REGISTRATION_YEAR, OWNER_NAME, FUEL_TYPE, NUMBER_PLATE)
VALUES ('Golf', 'Volkswagen', 2018, 2018, 'John', 'Diesel', '123456789');

INSERT INTO CUSTOMERCARD (discount, joined_at)
VALUES (0.1, TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));

Insert Into CUSTOMER (NAME, PHONE_NUMBER, EMAIL, CUSTOMER_CARD_ID)
VALUES ('John', '123456789', 'l@l', 1);

INSERT INTO TASK (NAME, START_DATE, CAR_ID, MECHANIC_ID, CUSTOMER_ID)
VALUES ('Fix Engine', TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), 1, 2, 1);
INSERT INTO TASK (NAME, START_DATE, CAR_ID, MECHANIC_ID, CUSTOMER_ID)
VALUES ('Change Oil', TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), 2, 3, 1);

INSERT INTO SUBTASK (DESCRIPTION, DURATION, TASK_ID, IS_DONE)
VALUES ('Repair crankshaft', 3, 1, 0);
INSERT INTO SUBTASK (DESCRIPTION, DURATION, TASK_ID, IS_DONE)
VALUES ('Change piston rings', 1, 1, 0);
INSERT INTO SUBTASK (DESCRIPTION, DURATION, TASK_ID, IS_DONE)
VALUES ('Change oil', 0.7, 1, 0);
INSERT INTO SUBTASK (DESCRIPTION, DURATION, TASK_ID, IS_DONE)
VALUES ('Change oil filter', 0.1, 1, 0);
INSERT INTO SUBTASK (DESCRIPTION, DURATION, TASK_ID, IS_DONE)
VALUES ('Replace oil filter', 0.1, 2, 0);
INSERT INTO SUBTASK (DESCRIPTION, DURATION, TASK_ID, IS_DONE)
VALUES ('Fill in new oil', 0.2, 2, 0);

INSERT INTO MECHANICTASKMAPPING (MECHANIC_ID, TASK_ID)
VALUES (1, 1);
INSERT INTO MECHANICTASKMAPPING (MECHANIC_ID, TASK_ID)
VALUES (2, 2);
INSERT INTO MECHANICTASKMAPPING (MECHANIC_ID, TASK_ID)
VALUES (3, 2);

Insert Into INVOICE (ISSUE_DATE, AMOUNT, IS_PAID, TASK_ID, CUSTOMER_ID)
VALUES (TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), 100, 0, 1, 1);

Insert Into PART (SERIAL_NUMBER, NAME, MANUFACTURER, PRICE, ADDITIONAL_CHARGE, QUANTITY)
VALUES (1, 'Oil', 'Shell', 10, 0, 10);
INSERT INTO PART (SERIAL_NUMBER, NAME, MANUFACTURER, PRICE, ADDITIONAL_CHARGE, QUANTITY)
VALUES (2, 'Motor', 'BMW', 100, 0, 1);
INSERT INTO PART (SERIAL_NUMBER, NAME, MANUFACTURER, PRICE, ADDITIONAL_CHARGE, QUANTITY)
VALUES (3, 'Screw', 'Daxi-gmbh', 10, 0, 1100);

INSERT INTO TASKPARTMAPPING (TASK_ID, PART_ID, QUANTITY, PRICE)
VALUES (2, 2, 1, 10);

INSERT INTO COMPATIBLECARS (CAR_ID, PART_SERIAL_NUM)
VALUES (1, 2);

COMMIT /*Suicide*/ WORK;
