create table students(
	id serial primary key,
	name text,
	surname varchar(255),
	class integer,
	average numeric(2,1)
);
insert into students(name, surname, class, average) values('Ivan', 'Ivanov', '1', '4.6');
update students set surname='Petrov';
delete from students;
select*from students;