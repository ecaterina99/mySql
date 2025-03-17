use work;

select * from salary s left join employees e  on e.id = s.employee_id;

select * from employees e left join salary s on e.id = s.employee_id;



INSERT INTO work.salary (pay_date, gross_salary, net_salary, tax_amount, employee_id)
VALUES ('2021-03-25', 152.00, 101.00, 51.00, 7);

Select * from employees where gender ='F';
SELECT * FROM salary s left join employees e on e.id=s.employee_id WHERE gross_salary BETWEEN 100 AND 150;
SELECT * FROM salary s left join employees e on e.id=s.employee_id WHERE e.id = 6 OR e.id BETWEEN 7 AND 8 OR e.id BETWEEN 10 AND 12;

alter table employees add column password varchar(15);
alter table employees drop column password;

drop database if exists my_first_db;
create database my_first_db;
create table computer_parts(id int primary key auto_increment, type int , price int);
use my_first_db;
drop table if exists computer_parts;
create table computer_parts
(
computer_part_id int primary key auto_increment,
computer_part_code varchar(5),
computer_part_type int,
computer_part_name varchar(256),
computer_part_price decimal(7,2)
);
insert into my_first_db.computer_parts(computer_part_code, computer_part_type, computer_part_name, computer_part_price)
values(123,52015,'monitor',200.5);

use sakila;
SELECT * from film WHERE title LIKE '%bird%' AND rental_duration > 5;
SELECT COUNT(DISTINCT rating) FROM film;
SELECT * from actor WHERE first_name LIKE '%John%' OR first_name LIKE '%Lucille%';
SELECT * from address WHERE address LIKE '%1%' LIMIT 10,  3;
SELECT COUNT(DISTINCT first_name) AS number_first_name_distinct
FROM customer;

SELECT film.title , language.name
FROM film INNER JOIN language
ON film.language_id = language.language_id;


SELECT customer.first_name,
       customer.last_name,
       staff.first_name,
       staff.last_name,
       store.address_id
FROM customer INNER JOIN store ON customer.store_id = store.store_id
INNER JOIN staff ON staff.staff_id = store.manager_staff_id
AND staff.store_id = store.store_id;