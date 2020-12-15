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


select * from accounts where accountuser like 'kyle' and account_id = 3;



select * from transfers;

insert into transfers values (current_timestamp, 17, 'kyle', 18, 'erica', 80, 'APPROVED') returning transfers_id;

update transfers set transfer_state='APPROVED' where transfers_id = 10;

select * from transfers  where transfer_state like 'PENDING' and (receiving_customer like 'kyle' or sending_customer like 'kyle');

select * from transfers where transfers_id = 1;