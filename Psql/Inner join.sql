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
insert into bank_user(name) values('Petrov');
insert into bank_user(name) values('Sidorov');
insert into bank_accounts(number_acc, user_id) values(111111, 1);
insert into bank_accounts(number_acc, user_id) values(111222, 1);
insert into bank_accounts(number_acc, user_id) values(111333, 1);
insert into bank_accounts(number_acc, user_id) values(222111, 2);
insert into bank_accounts(number_acc, user_id) values(333111, 3);
insert into bank_accounts(number_acc, user_id) values(333222, 3);

select*from bank_user as bu
join bank_accounts as ba
on bu.id = ba.user_id;

select bu.name, ba.number_acc from bank_user as bu
join bank_accounts as ba
on bu.id = ba.user_id;

select bu.name as surname, ba.number_acc as account from bank_user as bu
join bank_accounts as ba
on bu.id = ba.user_id;