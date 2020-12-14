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
	balance int not null,
	account_state text check (account_state like 'PENDING' or account_state like 'APPROVED' or account_state like 'DENIED') not null,
	account_id serial primary key
);

--to put all of the crew memebers in a movies credits
--join table
create table transfers (
	date_made timestamp,
	ammount int not null,
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
	('erica', 200,  'APPROVED'),
	('kyle', 101, 'PENDING'),
	('erica', 201,  'PENDING'),
	('kyle', 102, 'DENIED'),
	('erica', 202,  'DENIED')
	returning accountuser;

insert into transfers(
date_made, ammount, sending_customer, sending_account_id, receiving_customer,receiving_account_id, transfer_state)
	values 
	(current_timestamp, 1, 'kyle', 1, 'erica', 0, 'APPROVED'),
	(current_timestamp, 2, 'kyle', 0, 'erica', 0, 'PENDING'),
	(current_timestamp, 3, 'kyle', 0, 'erica', 1, 'DENIED'),
	(current_timestamp, 4, 'erica', 0, 'kyle', 1, 'APPROVED'),
	(current_timestamp, 5, 'erica', 0, 'kyle', 0, 'PENDING'),
	(current_timestamp, 6, 'erica', 1, 'kyle', 0, 'DENIED')
	;

commit;

/*  			     

select m.price , m."type" , m."name" , m.copyright_owner , m.release_date, m.rating, m.max_age , m.min_age , m.media_id , m2.runtime , m2.movie_id , array_agg(c."name") as credits from media m 
			inner join movie m2 on m.media_id = m2.main_media 
			inner join movie_crew mc on m2.movie_id = mc.movie_id 
			inner join crew c on mc.crew_id = c.crew_id
			group by m.media_id, m2.movie_id;
		
		
create view all_movies as select m.price , m."type" , m."name" , m.copyright_owner , m.release_date, m.rating, m.max_age , m.min_age , m.media_id , m2.runtime , m2.movie_id , array_agg(c."name") as credits from media m 
			inner join movie m2 on m.media_id = m2.main_media 
			inner join movie_crew mc on m2.movie_id = mc.movie_id 
			inner join crew c on mc.crew_id = c.crew_id
			group by m.media_id, m2.movie_id;
		
		
		
select * from all_movies;

select * from media m;

insert into users ("username", "password", "first_name", "last_name")
			values ('AB', 'password', 'Alec', 'Batson');
*/
