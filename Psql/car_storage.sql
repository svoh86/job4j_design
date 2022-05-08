create table body(
    id serial primary key,
    name text
);

create table engine(
    id serial primary key,
    name text
);

create table gearbox(
    id serial primary key,
    name text
);

create table car(
    id serial primary key,
    name text,
    body_id int references body(id),
    engine_id int references engine(id),
    gearbox_id int references gearbox(id)
);

insert into body(name)
values ('crossover'), ('sedan'), ('hatchback'), ('minivan');
insert into engine(name)
values ('petrol'), ('diesel'), ('electric');
insert into gearbox(name)
values ('mechanical'), ('automatic'), ('robot');
insert into car(name, body_id, engine_id, gearbox_id)
values('bmw', 1, 1, 1), ('audi', 2, 2, 2), ('toyota', 3, 2, 2), ('lada', 2, 1, 1);
--Вывести список всех машин и все привязанные к ним детали.
--Нужно учесть, что каких-то деталей машина может и не содержать.
select c.id, c.name, b.name, e.name, g.name from car c
left join body b on c.body_id = b.id
left join engine e on c.engine_id = e.id
left join gearbox g on c.gearbox_id = g.id;

--Вывести отдельно детали (1 деталь - 1 запрос),
--которые не используются НИ в одной машине, кузова, двигатели, коробки передач.
select * from body b
left join car c on b.id = c.body_id
where c.name is null;

select * from engine e
left join car c on e.id = c.engine_id
where c.name is null;

select * from gearbox g
left join car c on g.id = c.gearbox_id
where c.name is null;