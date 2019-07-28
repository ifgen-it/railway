select * from timeline;
select * from route order by 1;
select * from train;
select * from locomotive;
select * from user;

insert into timeline(route_id, train_id, start_datetime) values(1, 4, '2019-07-29 11:05:00');
insert into timeline(route_id, train_id, start_datetime) values(2, 2, '2019-07-29 13:10:00');
insert into timeline(route_id, train_id, start_datetime) values(3, 5, '2019-07-29 06:55:00');
insert into timeline(route_id, train_id, start_datetime) values(4, 3, '2019-07-29 16:45:00');
insert into timeline(route_id, train_id, start_datetime) values(5, 1, '2019-07-29 18:30:00');

 
 commit;