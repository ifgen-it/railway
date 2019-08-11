-- TEST

select r.route_path_id, r.route_id, r.arc_id, a.begin_station, a.end_station
from routepath r
join arc a on ( r.arc_id = a.arc_id)
where a.begin_station = 1;

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

