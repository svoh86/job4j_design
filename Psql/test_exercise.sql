create table company
(
    id integer not null,
    name character varying,
    constraint company_pkey primary key (id)
);

create table person
(
    id integer not null,
    name character varying,
    company_id integer references company(id),
    constraint person_pkey primary key (id)
);

insert into company(id, name) values (1, 'Oracle');
insert into company(id, name) values (2, 'Microsoft');
insert into company(id, name) values (3, 'VK');
insert into company(id, name) values (4, 'Google');
insert into company(id, name) values (5, 'Yandex');

insert into person(id, name, company_id) values (1, 'Petr', 1);
insert into person(id, name, company_id) values (2, 'Ivan', 1);
insert into person(id, name, company_id) values (3, 'Anna', 2);
insert into person(id, name, company_id) values (4, 'Olga', 2);
insert into person(id, name, company_id) values (5, 'Alex', 3);
insert into person(id, name, company_id) values (6, 'Misha', 4);
insert into person(id, name, company_id) values (7, 'Tanya', 4);
insert into person(id, name, company_id) values (8, 'Nik', 5);
insert into person(id, name, company_id) values (9, 'Dan', 5);
insert into person(id, name, company_id) values (10, 'Sasha', 4);
insert into person(id, name, company_id) values (11, 'Artem', 5);

--В одном запросе получить
--- имена всех person, которые не состоят в компании с id = 5;
--- название компании для каждого человека.
select p.name, c.name from person p 
left join company c on p.company_id = c.id 
where p.company_id != 5;

--Необходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании
--(нужно учесть, что таких компаний может быть несколько).
select c.name, count(p.name) from person p
left join company c on p.company_id = c.id
group by c.name
having count(p.name) = (
    select count(p.name) from person p 
    left join company c on p.company_id = c.id
    group by c.name
    order by count desc
    limit 1);
