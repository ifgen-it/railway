select * from ticket
where route_id = 2;

truncate ticket;


select t.route_id, t.start_station, t.finish_station, t.start_time, t.finish_time, t.seat_number
from ticket t
where route_id = 1
and (
	(t.start_time >= '2019-08-10 11:05:00' and t.start_time < '2019-08-10 15:15:00')
    or
    (t.finish_time > '2019-08-10 11:05:00' and t.finish_time <= '2019-08-10 15:15:00')
    or
    (t.start_time <= '2019-08-10 11:05:00' and t.finish_time >= '2019-08-10 15:15:00')
	);
    
    
    
    select *
    from train;
    
    update train
    set seats_amount = 2
    where train_id = 5;
    
    
    -- ========================
    --  TWIN USER ON THE TRAIN
    
    select *
    from ticket
    where route_id = 1 and user_id = 1;
    
 delete from ticket
 where ticket_id = 1;