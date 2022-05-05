create table vin(
	id serial primary key,
	number int
);

create table car(
	id serial primary key,
	brand text,
	name text,
	vin_id int references vin(id) unique
);
