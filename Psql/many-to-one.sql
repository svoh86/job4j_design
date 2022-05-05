create table bank_user(
	id serial primary key,
	name text
);

create table bank_accounts(
	id serial primary key,
	number_acc bigint,
	user_id int references bank_user(id)
);

insert into bank_user(name) values('Ivanov');
insert into bank_accounts(number_acc, user_id) values (456432, 1);

select*from bank_accounts;
select*from bank_user where id in (select id from bank_accounts);