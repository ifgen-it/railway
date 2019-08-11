select * from routepath;
select * from arc;

 -- TIMETABLE ARRIVAL
select r.route_path_id, r.route_id, r.arc_id,  r.arrival_time, 
  a.end_station
from routepath r
join arc a on (r.arc_id = a.arc_id)
where a.end_station = 3;

 -- TIMETABLE DEPARTURE
select r.route_path_id, r.route_id, r.arc_id, r.departure_time,  
a.begin_station
from routepath r
join arc a on (r.arc_id = a.arc_id)
where a.begin_station = 3;

order by r.arrival_time;