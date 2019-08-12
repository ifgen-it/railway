-- TEST

select r.route_path_id, r.route_id, r.arc_id, a.begin_station, a.end_station, r.departure_time
from routepath r
join arc a on ( r.arc_id = a.arc_id)
where a.begin_station = 1
and departure_time > (str_to_date('2019-08-10', '%Y-%m-%d'))
and departure_time < (str_to_date('2019-08-11', '%Y-%m-%d'))
;

-- GET ROUTE_ID FOR START STATION  ( START-STATION-ID = 1 )

select r.route_id 
from routepath r
join arc a on ( r.arc_id = a.arc_id)
where a.begin_station = 1;

-- ============================================
-- TEST
select r.route_path_id, r.route_id, r.arc_id, a.begin_station, a.end_station
from routepath r
join arc a on ( r.arc_id = a.arc_id)
where a.end_station = 2;

-- GET ROUTE_ID FOR START STATION  ( END-STATION-ID = 2 )

select r.route_id
from routepath r
join arc a on ( r.arc_id = a.arc_id)
where a.end_station = 2;


--  INTERSECT ======================================

select r.route_id 
from routepath r
join arc a on ( r.arc_id = a.arc_id)
where a.begin_station = 1
and r.route_id in (
	select r.route_id
	from routepath r
	join arc a on ( r.arc_id = a.arc_id)
	where a.end_station = 2
);


-- ===================================
-- GET DEPARTURE TIME BY STATION AND ROUTE
-- TEST

select r.route_path_id, r.route_id, r.arc_id, a.begin_station, a.end_station, r.departure_time, r.arrival_time
from routepath r
join arc a on ( r.arc_id = a.arc_id)
where r.route_id = 2 and a.end_station = 3
;

-- get
select r.arrival_time
from routepath r
join arc a on ( r.arc_id = a.arc_id)
where r.route_id = 2 and a.end_station = 3
;


-- ======================
-- PRICE

select r.route_path_id, r.route_id, r.arc_id, a.begin_station, a.end_station, r.departure_time, r.arrival_time, a.length
from routepath r
join arc a on ( r.arc_id = a.arc_id)
where r.route_id = 2
and r.departure_time >= '2019-08-10 08:00:00'
and r.arrival_time <= '2019-08-10 12:25:00'
;

-- ===
select sum(a.length)
from routepath r
join arc a on ( r.arc_id = a.arc_id)
where r.route_id = 2
and r.departure_time >= '2019-08-10 08:00:00'
and r.arrival_time <= '2019-08-10 12:25:00'
;

