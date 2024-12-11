create table inn (
	id serial primary key,
	number int unique
);

create table person (
	id serial primary key,
	name text
);

create table inn_person (
	id serial primary key,
	person_id int references person(id) unique,
	inn_id int references inn(id) unique
);

insert into inn (number)
values (4536);

insert into inn (number)
values (2145);

insert into person (name)
values ('Egor');

insert into person (name)
values ('Irina');

insert into inn_person (person_id, inn_id)
values (1, 1);

insert into inn_person (person_id, inn_id)
values (2, 2);
