create table role(
	id serial primary key,
	name text
);

create table users(
	id serial primary key,
	name text,
	role_id int references role(id)
);

create table rules(
	id serial primary key,
	name text
);

create table role_rules(
	id serial primary key,
	role_id int references role(id),
	rules_id int references rules(id)
);

create table category(
	id serial primary key,
	name text
);

create table state(
	id serial primary key,
	name text
);

create table item(
	id serial primary key,
	name text,
	item_users_id int references users(id),
	item_category_id int references category(id),
	item_state_id int references state(id)
);

create table comments(
	id serial primary key,
	name text,
	comments_id int references item(id)
);

create table attachs(
	id serial primary key,
	name text,
	attachs_id int references item(id)
);
