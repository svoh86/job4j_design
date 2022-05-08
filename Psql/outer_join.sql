create table departments(
	id serial primary key,
	name text
);

create table employees(
	id serial primary key,
	name text,
	departments_id int references departments(id)
);

insert into departments(name)
values('dep1'), ('dep2'), ('dep3'), ('dep4');

insert into employees(name, departments_id)
values('Alex', 1), ('Anna', 1), ('Anton', 1);

insert into employees(name, departments_id)
values('Boris', 2);

insert into employees(name, departments_id)
values('Dima', 3), ('Dasha', 3);

insert into employees(name)
values('Petr');
-- запросы с left, rigth, full, cross соединениями
select*from employees e 
left join departments d 
on e.departments_id = d.id;

select*from employees e 
right join departments d 
on e.departments_id = d.id;

select*from employees e 
full join departments d 
on e.departments_id = d.id;

select*from employees e 
cross join departments d;
-- Используя left join найти департаменты, у которых нет работников
select * from departments d 
left join employees e 
on d.id = e.departments_id 
where e.name is null;
-- Используя left и right join написать запросы, 
-- которые давали бы одинаковый результат (порядок вывода колонок в эти запросах также должен быть идентичный). 
select*from employees e 
left join departments d 
on e.departments_id = d.id
where d.name is not null;
select*from employees e 
right join departments d 
on e.departments_id = d.id 
where e.name is not null;
-- Создать таблицу teens с атрибутами name, gender и заполнить ее. 
-- Используя cross join составить все возможные разнополые пары
create table teens(
	id serial primary key,
	name text,
	gender text
);

insert into teens(name, gender) 
values('Ivan', 'M'), ('Igor','M');

insert into teens(name, gender) 
values('Anna', 'F'), ('Kate','F'), ('Masha', 'F');

select t1.name, t2.name 
from teens t1 
cross join teens t2 
where t1.gender != t2.gender;
