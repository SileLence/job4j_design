create database job4j;

create table musical_instrument (
	id serial primary key,
	name text,
	manufacture_date date,
	used boolean,
	price decimal(8,2)
);

insert into musical_instrument (name, manufacture_date, used, price)
values ('Paino', '27.11.1983', true, 27890);

update musical_instrument
set 
	manufacture_date = '16.04.2024',
	used = false;

delete from musical_instrument;