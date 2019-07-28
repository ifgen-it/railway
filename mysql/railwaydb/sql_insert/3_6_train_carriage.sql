select * from locomotive;
select * from train;
select * from carriage;
select * from carriagetype;
select * from train_carriage;

-- Train 1
insert into train_carriage(train_id, carriage_id, carriage_number) values(1, 1, 1);
insert into train_carriage(train_id, carriage_id, carriage_number) values(1, 2, 2);
insert into train_carriage(train_id, carriage_id, carriage_number) values(1, 11, 3);

-- Train 2
insert into train_carriage(train_id, carriage_id, carriage_number) values(2, 3, 1);
insert into train_carriage(train_id, carriage_id, carriage_number) values(2, 4, 2);
insert into train_carriage(train_id, carriage_id, carriage_number) values(2, 12, 3);

-- Train 3
insert into train_carriage(train_id, carriage_id, carriage_number) values(3, 5, 1);
insert into train_carriage(train_id, carriage_id, carriage_number) values(3, 6, 2);
insert into train_carriage(train_id, carriage_id, carriage_number) values(3, 13, 3);

-- Train 4
insert into train_carriage(train_id, carriage_id, carriage_number) values(4, 7, 1);
insert into train_carriage(train_id, carriage_id, carriage_number) values(4, 8, 2);
insert into train_carriage(train_id, carriage_id, carriage_number) values(4, 14, 3);

-- Train 5
insert into train_carriage(train_id, carriage_id, carriage_number) values(5, 9, 1);
insert into train_carriage(train_id, carriage_id, carriage_number) values(5, 10, 2);
insert into train_carriage(train_id, carriage_id, carriage_number) values(5, 15, 3);

 
 commit;