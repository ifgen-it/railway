select * from routepath;
truncate routepath;

-- Рейс 1 - Мск-СПБ
insert into routepath(route_id, arc_id, departure_time, arrival_time)
values(1, 2, '2019-08-16 00:05:00', '2019-08-16 8:15:00');

-- Рейс 2 - СПб-Мск-2
insert into routepath(route_id, arc_id, departure_time, arrival_time)
values(2, 3, '2019-08-20 08:00:00', '2019-08-20 12:25:00');
insert into routepath(route_id, arc_id, departure_time, arrival_time)
values(2, 10, '2019-08-21 12:35:00', '2019-08-21 14:40:00');

-- Рейс 3 - СПБ-Вол
insert into routepath(route_id, arc_id, departure_time, arrival_time)
values(3, 6, '2019-08-15 21:10:00', '2019-08-16 06:30:00');

-- Рейс 4 - СПБ-Мск
insert into routepath(route_id, arc_id, departure_time, arrival_time)
values(4, 1, '2019-08-25 16:00:00', '2019-08-25 20:30:00');

-- Рейс 5 - Мск-Вол
insert into routepath(route_id, arc_id, departure_time, arrival_time)
values(5, 9, '2019-08-10 18:35:00', '2019-08-10 21:20:00');
insert into routepath(route_id, arc_id, departure_time, arrival_time)
values(5, 13, '2019-08-10 21:35:00', '2019-08-11 9:05:00');

-- Рейс 6 - СПБ-Кировск
insert into routepath(route_id, arc_id, departure_time, arrival_time)
values(6, 14, '2018-12-31 21:25:00', '2019-01-01 20:30:00');

-- Рейс 7 - СПБ-Кировск-2
insert into routepath(route_id, arc_id, departure_time, arrival_time)
values(7, 14, '2019-12-31 22:22:00', '2020-01-01 21:15:00');




select * from arc;
truncate ticket;
select * from routepath;
truncate routepath;
-- =============================================================

delete from routepath
where route_path_id = 2;

commit;