set schema 'project0colin';

select * from customers where username like 'kyle';

select * from transfers;

select * from customers;

select * from employees;

select * from accounts;


insert into customers values
('holly', 'j');





insert into employees values
('aaron', 'j');

update employees set 
"username" = 'asd', "password" = 'asdf'
where username like 'ds';




insert into accounts values
('kyle', 100, 'PENDING')
returning account_id;

update accounts set 
balance = 155, account_state='APPROVED'
where account_id = 4;


select * from accounts where accountuser like 'kyle';


select * from accounts where username like ? and account_id = 1;




