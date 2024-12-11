create table kickscooter (
	id serial primary key,
	model text
);

create table rider (
	id serial primary key,
	name text
);

create table rent_history (
	id serial primary key,
	rent_date date,
	kickscooter_id int references kickscooter(id),
	rider_id int references rider(id)
);

insert into kickscooter (model)
values ('Jet');

insert into kickscooter (model)
values ('Yandex');

insert into rider (name)
values ('Irina');

insert into rider (name)
values ('Stepan');

insert into rider (name)
values ('Altynai');

insert into rent_history (rent_date, kickscooter_id, rider_id)
values (
	'2024-05-19',
	2,
	3
);

insert into rent_history (rent_date, kickscooter_id, rider_id)
values (
	'2024-05-23',
	1,
	1
);

insert into rent_history (rent_date, kickscooter_id, rider_id)
values (
	'2024-05-24',
	2,
	1
);

insert into rent_history (rent_date, kickscooter_id, rider_id)
values (
	'2024-05-24',
	1,
	2
);

insert into rent_history (rent_date, kickscooter_id, rider_id)
values (
	'2024-05-25',
	2,
	2
);
