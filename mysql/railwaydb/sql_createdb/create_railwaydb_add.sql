-- Это обязательно сделать после Форварда:
-- ==================================================

alter table seat
add constraint uq_seat unique (timeline_id, train_carriage_id, seat);

alter table routepath 
add constraint uq_routepath unique (route_id, station, station_next);


-- Это чтобы заново не делать ФорвардИнжиниринг
-- ==================================================


alter table routepath
drop index uq_routepath;

alter table routepath
modify station int(10) unsigned NULL;

alter table routepath
modify station_next int(10) unsigned NULL;

alter table ticket
add departure_time datetime not null;

alter table ticket
add arrival_time datetime not null;


commit;