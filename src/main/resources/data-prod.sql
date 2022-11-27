INSERT INTO CITY (name)
VALUES ('Bydgoszcz'),
       ('Warszawa');
INSERT INTO DEPARTMENT (name, department_code, city_id)
VALUES ('Bydgoski związek motorowy', 'BYD01', 1),
       ('Warszawska spółka Adaxora', 'WAW350', 2);
insert into phone_number (area, exchange, subscriber)
VALUES ('733', '232', '500');
INSERT INTO STUDENTS (ADMITTED_EXAM, BIRTH_DATE, EMAIL, FIRST_NAME, LAST_NAME, REMAINING_HOURS, UUID, DEPARTMENT_ID,
                      PHONE_NUMBER_ID)
VALUES (FALSE, '2022-08-06', 'adax324@gmail.com', 'Adam', 'Hrycenko', 30.0, 'f63325c2-f951-4b41-8a7c-edc06f4973f8', 1,
        1)