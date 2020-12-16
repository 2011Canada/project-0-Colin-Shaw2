drop schema if exists project0colin cascade;
create schema project0colin;
set schema 'project0colin';


--all of the generic fields
--every row will have a one to one with either a movie or book or a something table
create table customers(
	"username" text primary key,
	"password" text not null 
);



create table employees( 
	"username" text primary key,
	"password" text not null
);


create table accounts(
	accountuser text references customers("username"),
	balance int not null check (balance >= 0),
	account_state text check (account_state like 'PENDING' or account_state like 'APPROVED' or account_state like 'DENIED') not null,
	account_id serial primary key
);

--to put all of the crew memebers in a movies credits
--join table
create table transfers (
	date_made timestamp not null,
	ammount int not null check (ammount >= 0),
	sending_customer text references customers("username") not null,
	sending_account_id int not null,
	receiving_customer text references customers("username") not null,
	receiving_account_id int not null,
	transfer_state text check (transfer_state like 'PENDING' or transfer_state like 'APPROVED' or transfer_state like 'DENIED') not null,
	transfers_id serial primary key
);

begin;
insert into customers("username", "password")
	values 
	('kyle', 'g'),
	('erica', 's')
	returning "username";

insert into employees("username", "password")
	values 
	('kurtis', 'g'),
	('colin', 's')
	returning "username";


insert into accounts(accountuser, balance, account_state)
	values 
	('kyle', 100, 'APPROVED'),
	('erica', 200,  'PENDING'),
	('kyle', 101, 'PENDING'),
	('erica', 201,  'PENDING'),
	('kyle', 102, 'DENIED'),
	('erica', 202,  'PENDING')
	returning accountuser;

insert into transfers(
date_made, ammount, sending_customer, sending_account_id, receiving_customer,receiving_account_id, transfer_state)
	values 
	(current_timestamp, 1, 'kyle', 1, 'erica', 2, 'APPROVED'),
	(current_timestamp, 2, 'kyle', 1, 'erica', 2, 'PENDING'),
	(current_timestamp, 3, 'kyle', 1, 'erica', 2, 'DENIED'),
	(current_timestamp, 4, 'erica', 2, 'kyle', 1, 'APPROVED'),
	(current_timestamp, 5, 'erica', 2, 'kyle', 1, 'PENDING'),
	(current_timestamp, 6, 'erica', 2, 'kyle', 1, 'DENIED'),
	(current_timestamp, 3729, 'kyle', 1, 'erica', 2, 'PENDING')
	;

commit;

