insert into role(name) values('admin');
insert into role(name) values('user');
insert into role(name) values('guest');

insert into users(name, role_id) values('Ivan', 2);
insert into users(name, role_id) values('Petr', 2);
insert into users(name, role_id) values('Misha', 1);
insert into users(name, role_id) values('Roma', 3);
insert into users(name, role_id) values('Alex', 3);

insert into rules(name) values('rwx');
insert into rules(name) values('rw-');
insert into rules(name) values('r--');

insert into role_rules(role_id, rules_id) values(1, 1);
insert into role_rules(role_id, rules_id) values(1, 2);
insert into role_rules(role_id, rules_id) values(1, 3);
insert into role_rules(role_id, rules_id) values(2, 2);
insert into role_rules(role_id, rules_id) values(2, 3);
insert into role_rules(role_id, rules_id) values(3, 3);

insert into category(name) values('first');
insert into category(name) values('second');

insert into state(name) values('done');
insert into state(name) values('in_progress');

insert into item(name, item_users_id, item_category_id, item_state_id) values('update user', 2, 1, 1);
insert into item(name, item_users_id, item_category_id, item_state_id) values('delete user', 5, 2, 2);

insert into comments(name, comments_id) values('ok', 1);

insert into attachs(name, attachs_id) values('reason', 2);












