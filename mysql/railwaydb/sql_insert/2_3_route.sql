set foreign_key_checks = 0;
set foreign_key_checks = 1;

insert into route(route_name, train_id) values('Мск-СПб', 1); -- 1
insert into route(route_name, train_id) values('СПб-Мск-2', 2); -- 2
insert into route(route_name, train_id) values('СПб-Вол', 3); -- 3
insert into route(route_name, train_id) values('СПб-Мск', 4); -- 4
insert into route(route_name, train_id) values('Мск-Вол', 5); -- 5

insert into route(route_name, train_id) values('СПб-Кир', 1); -- 6
insert into route(route_name, train_id) values('СПб-Кир-2', 2); -- 7

select * from ticket;

truncate route;

delete from route
where route_id = 2;

select * from ticket;

select * from user;