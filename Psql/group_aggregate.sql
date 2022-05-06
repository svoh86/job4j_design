create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values('Phone', 8999.99), ('Laptop', 14999.99), ('Mouse', 2100), ('Player', 4500);
insert into people(name) values('Ivan'), ('Petr'), ('Alex');
insert into devices_people(device_id, people_id) values(1, 1), (1, 2), (1, 3), (2, 2), (3, 1), (3, 2), (4, 3);

select avg(price) from devices;

select p.name, avg(d.price) 
from devices_people as dp
join people as p on dp.people_id = p.id
join devices as d on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price) 
from devices_people as dp
join people as p on dp.people_id = p.id
join devices as d on dp.device_id = d.id
group by p.name
having avg(d.price) > 7000;
