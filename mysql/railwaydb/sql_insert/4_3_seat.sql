select * from timeline;
select * from route order by 1;
select * from train;
select * from locomotive;
select * from user;
select * from seat;

-- =============================================================

insert into seat(timeline_id, train_carriage_id, seat, seat_status_id) values(1, 11, 32, 2);
insert into seat(timeline_id, train_carriage_id, seat, seat_status_id) values(1, 12, 6, 2);

-- ================================================================
select * from train_carriage
where train_id = 4;
 
 commit;