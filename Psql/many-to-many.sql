create table cars(
	id serial primary key,
	brand text
);

create table colors(
	id serial primary key,
	color text
);

create table cars_colors(
	id serial primary key,
	brand_id int references cars(id),
	color_id int references colors(id)
);

insert into cars(brand) values('BMW');
insert into cars(brand) values('AUDI');
insert into cars(brand) values('LADA');

insert into colors(color) values('Red');
insert into colors(color) values('Green');
insert into colors(color) values('Blue');

insert into cars_colors(brand_id, color_id) values(1, 1);
insert into cars_colors(brand_id, color_id) values(1, 3);
insert into cars_colors(brand_id, color_id) values(2, 1);
insert into cars_colors(brand_id, color_id) values(3, 1);
insert into cars_colors(brand_id, color_id) values(3, 2);
insert into cars_colors(brand_id, color_id) values(3, 3);
