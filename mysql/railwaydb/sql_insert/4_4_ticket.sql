
select * from user;
select * from seat;
select * from ticket;
select * from station order by 1;
select * from routepath;

truncate table ticket;
-- =============================================================

insert into ticket(route_id, start_station, finish_station, start_time, finish_time,
seat_number, price, user_id)
values(1, 2, 1, '2019-08-10 11:05:00', '2019-08-10 15:15:00', 6, 4000, 1);

-- ===========================================

alter table ticket
drop foreign key fk_Ticket_Train1;

alter table ticket
drop column train_id;
