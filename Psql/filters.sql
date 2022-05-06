create table type(
	id serial primary key,
	name text
);

create table product(
	id serial primary key,
	name text,
	type_id int references type(id),
	expired_date timestamp,
	price float
);

insert into type(name) values ('СЫР'), ('МОЛОКО'), ('МОРОЖЕНОЕ'), ('ХЛЕБ');
insert into product(name, type_id, expired_date, price) 
values('Сыр плавленный', 1, date '10.05.2022', 50), ('Сыр моцарелла', 1, date '12.05.2022', 60), ('Сыр сливочный', 1, date '01.05.2022', 20);
insert into product(name, type_id, expired_date, price) 
values('Пискаревское', 2, date '09.05.2022', 66), ('Домик в деревне', 2, date '29.04.2022', 70);
insert into product(name, type_id, expired_date, price) 
values('Эскимо мороженое', 3, date '20.05.2022', 89), ('Пломбир', 3, date '29.06.2022', 90), ('Лакомка мороженое', 3, date '08.07.2022', 120);
insert into product(name, type_id, expired_date, price) 
values('Батон', 4, date '13.05.2022', 45), ('Бородинский', 4, date '11.05.2022', 78);
-- запрос получения всех продуктов с типом "СЫР"
select*from product as p join type as t on p.type_id = t.id where t.name = 'СЫР';
-- запрос получения всех продуктов, у кого в имени есть слово "мороженое"
select*from product as p where p.name like '%мороженое%';
-- запрос, который выводит все продукты, срок годности которых уже истек
select*from product as p where current_date > p.expired_date;
-- запрос, который выводит самый дорогой продукт.
select*from product where price = (select max(price) from product);
-- запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество
select t.name as имя_типа, count(p.id) as количество from product as p join type as t on p.type_id = t.id group by t.name;
-- запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select*from product as p join type as t on p.type_id = t.id where t.name = 'СЫР' or t.name = 'МОЛОКО';
-- запрос, который выводит тип продуктов, которых осталось меньше 10 штук. 
select t.name, count(p.id) from product as p join type as t on p.type_id = t.id group by t.name having count(p.id) < 10;
-- Вывести все продукты и их тип
select p.name, t.name from product as p join type as t on p.type_id = t.id;
