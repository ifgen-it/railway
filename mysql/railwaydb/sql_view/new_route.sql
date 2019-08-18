select *
from ticket
where route_id = 2;

select * from user;

select arc_id, begin_station, end_station, length
from arc
where begin_station = (
		select end_station
		from arc where arc_id = 9
	);



-- =============================
-- FIND BUSY ROUTES

select route_id, route_path_id, departure_time, arrival_time
 from routepath
 where 
( arrival_time >= '2019-08-14 00:00:00' and arrival_time <= '2019-08-17 00:00:00' )
or
( departure_time >= '2019-08-14 00:00:00' and departure_time <= '2019-08-17 00:00:00' )
or
( departure_time < '2019-08-14 00:00:00' and arrival_time > '2019-08-17 00:00:00' )
;

 -- ADD TRAINS - BUSY TRAINS
 select rp.route_id, r.train_id, rp.route_path_id, rp.departure_time, rp.arrival_time
 from routepath rp
 join route r on (r.route_id = rp.route_id)
 where 
( rp.arrival_time >= '2019-08-14 00:00:00' and rp.arrival_time <= '2019-08-17 00:00:00' )
or
( rp.departure_time >= '2019-08-14 00:00:00' and rp.departure_time <= '2019-08-17 00:00:00' )
or
( rp.departure_time < '2019-08-14 00:00:00' and rp.arrival_time > '2019-08-17 00:00:00' )
;

 -- ADD TRAINS - FREE TRAINS
 
 select train_id from train
 where train_id not in (
		 select distinct r.train_id
		 from routepath rp
		 join route r on (r.route_id = rp.route_id)
		 where 
		( rp.arrival_time >= '2019-08-14 00:00:00' and rp.arrival_time <= '2019-08-17 00:00:00' )
		or
		( rp.departure_time >= '2019-08-14 00:00:00' and rp.departure_time <= '2019-08-17 00:00:00' )
		or
		( rp.departure_time < '2019-08-14 00:00:00' and rp.arrival_time > '2019-08-17 00:00:00' )
	)
	;


-- =========================
select end_station
from arc where arc_id = 3;