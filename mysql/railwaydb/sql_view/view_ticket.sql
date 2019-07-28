select * from timeline;
select * from route order by 1;
select * from train;
select * from locomotive;
select * from user;
select * from seat;
select * from ticket;
select * from station order by 1;

-- =============================================================
select 
 r.route_name "РЕЙС",
 s1.station_name "ОТКУДА", t.departure_time "ОТПРАВЛЕНИЕ",
 s2.station_name "КУДА", t.arrival_time "ПРИБЫТИЕ",
 locomotivetype.locomotive_type "ТИП_ПОЕЗДА",
 tc.carriage_number "№_ВАГОНА",
 carriagetype.carriage_type "ТИП_ВАГОНА",
 se.seat "МЕСТО",
 concat(concat(u.first_name, ' '), u.first_name) "ПАССАЖИР",
 t.fix_price "ЦЕНА"

from ticket t
join user u on(t.user_id = u.user_id)
join station s1 on(t.start_station = s1.station_id)
join station s2 on(t.finish_station = s2.station_id)
join seat se on(t.seat_id = se.seat_id)
join train_carriage tc on(se.train_carriage_id = tc.train_carriage_id)
join train on(tc.train_id = train.train_id)
join locomotive on(train.locomotive_id = locomotive.locomotive_id)
join locomotivetype on(locomotive.locomotive_type_id = locomotivetype.locomotive_type_id)
join carriage on (carriage.carriage_id = tc.carriage_id)
join carriagetype on(carriage.carriage_type_id = carriagetype.carriage_type_id)
join timeline ti on(se.timeline_id = ti.timeline_id)
join route r on(ti.route_id = r.route_id);



select * from route;


