select * from timeline;
select * from route order by 1;
select * from train;
select * from locomotive;
select * from user;
select * from seat;
select * from ticket;
select * from station order by 1;
select * from routepath;

truncate table ticket;
-- =============================================================

insert into ticket(start_station, finish_station, user_id, seat_id, fix_price, departure_time, arrival_time)
values(2, 1, 1, 1, 5250, '2019-07-29 11:05:00', '2019-07-29 14:05:00');


insert into ticket(start_station, finish_station, user_id, seat_id, fix_price, departure_time, arrival_time)
values(2, 1, 2, 2, 7350, '2019-07-29 11:05:00', '2019-07-29 14:05:00');

-- ===========================================


