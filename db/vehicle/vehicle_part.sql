create table vehicle (
	id serial primary key,
	name text
);

create table part (
	id serial primary key,
	name text,
	vehicle_id int references vehicle(id)
);

insert into vehicle (name)
values (
	'Car'
);

insert into vehicle (name)
values (
	'Bike'
);

insert into part (name, vehicle_id)
values (
	'Engine',
	1
);

insert into part (name, vehicle_id)
values (
	'Engine',
	2
);