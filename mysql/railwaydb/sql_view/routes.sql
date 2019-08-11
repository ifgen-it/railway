select *
from routepath
where route_id = 2;

select route_path_id, route_id, arc_id, departure_time, arrival_time
from routepath
where route_id = 2 and departure_time = (
	select min(departure_time) start_time
	from routepath
	where route_id = 2
);

 -- FIND START TIME OF ROUTE
select min(departure_time) start_time
from routepath
where route_id = 2;

 -- FIND FINISH TIME OF ROUTE
select max(arrival_time) finish_time
from routepath
where route_id = 2;
