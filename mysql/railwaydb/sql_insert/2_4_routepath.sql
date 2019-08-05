select * from routepath;

-- Рейс 1 - Мск-СПБ
insert into routepath(route_id, arc_id, departure_time, arrival_time)
values(1, 2, '2019-08-10 11:05:00', '2019-08-10 15:15:00');

-- Рейс 2 - СПб-Мск-2
insert into routepath(route_id, arc_id, departure_time, arrival_time)
values(2, 3, '2019-08-10 08:00:00', '2019-08-10 12:25:00');
insert into routepath(route_id, arc_id, departure_time, arrival_time)
values(2, 10, '2019-08-10 12:35:00', '2019-08-10 14:40:00');

-- Рейс 3 - СПБ-Вол
insert into routepath(route_id, arc_id, departure_time, arrival_time)
values(3, 6, '2019-08-10 21:10:00', '2019-08-11 06:30:00');

-- Рейс 4 - СПБ-Мск
insert into routepath(route_id, arc_id, departure_time, arrival_time)
values(4, 1, '2019-08-10 16:00:00', '2019-08-10 20:30:00');

-- Рейс 5 - Мск-Вол
insert into routepath(route_id, arc_id, departure_time, arrival_time)
values(5, 9, '2019-08-10 18:35:00', '2019-08-10 21:20:00');
insert into routepath(route_id, arc_id, departure_time, arrival_time)
values(5, 13, '2019-08-10 21:35:00', '2019-08-11 9:05:00');

-- =============================================================

delete from routepath
where route_path_id = 2;

commit;