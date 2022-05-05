create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna (name, avg_age, discovery_date) values ('swordfish', 11000, date '01.11.1920');
insert into fauna (name, avg_age, discovery_date) values ('tiger', 20000, date '29.07.1070');
insert into fauna (name, avg_age, discovery_date) values ('lion', 23000, date '19.05.2020');
insert into fauna (name, avg_age) values ('volf', 9000);

select*from fauna;
select*from fauna where name like '%fish%';
select*from fauna where avg_age > 10000 and avg_age <21000;
select*from fauna where discovery_date is null;
select*from fauna where discovery_date < '01.01.1950';
