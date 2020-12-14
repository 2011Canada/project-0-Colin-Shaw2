set schema 'project0colin';

select * from customers where username like 'kyle';

select * from transfers;
select * from customers;
select * from employees;
select * from accounts;

select * from accounts where accountuser like 'kyle';

insert into customers values
('holly', 'j');


insert into employees values
('aaron', 'j');


update employees set 
"password" = 'asdf'
where username like 'kyle'