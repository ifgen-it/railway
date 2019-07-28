select * from routepath;

-- Рейс 1 - Мск-СПБ
insert into routepath(route_id, station, station_next, arc_time, stay_time)
values(1, null, 2, null, null);
insert into routepath(route_id, station, station_next, arc_time, stay_time)
values(1, 2, 1, '3:00:00', null);
insert into routepath(route_id, station, station_next, arc_time, stay_time)
values(1, 1, null, null, null);

-- Рейс 3 - СПБ-Вол
insert into routepath(route_id, station, station_next, arc_time, stay_time)
values(3, null, 1, null, null);
insert into routepath(route_id, station, station_next, arc_time, stay_time)
values(3, 1, 5, '6:45:00', null);
insert into routepath(route_id, station, station_next, arc_time, stay_time)
values(3, 5, null, null, null);

-- Рейс 4 - СПБ-Мск
insert into routepath(route_id, station, station_next, arc_time, stay_time)
values(4, null, 1, null, null);
insert into routepath(route_id, station, station_next, arc_time, stay_time)
values(4, 1, 2, '3:20:00', null);
insert into routepath(route_id, station, station_next, arc_time, stay_time)
values(4, 2, null, null, null);

-- Рейс 2 - СПб-Мск-2
insert into routepath(route_id, station, station_next, arc_time, stay_time)
values(2, null, 1, null, null);
insert into routepath(route_id, station, station_next, arc_time, stay_time)
values(2, 1, 3, '4:10:00', null);
insert into routepath(route_id, station, station_next, arc_time, stay_time)
values(2, 3, 2, '1:30:00', '0:15:00');
insert into routepath(route_id, station, station_next, arc_time, stay_time)
values(2, 2, null, null, null);

-- Рейс 5 - Мск-Вол
insert into routepath(route_id, station, station_next, arc_time, stay_time)
values(5, null, 2, null, null);
insert into routepath(route_id, station, station_next, arc_time, stay_time)
values(5, 2, 3, '1:15:00', null);
insert into routepath(route_id, station, station_next, arc_time, stay_time)
values(5, 3, 5, '7:22:00', '0:20:00');
insert into routepath(route_id, station, station_next, arc_time, stay_time)
values(5, 5, null, null, null);

-- =============================================================

delete from routepath
where route_path_id = 2;

commit;