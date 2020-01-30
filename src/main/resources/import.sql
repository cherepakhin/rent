insert into status(id,name) values(0,'Свободен');
insert into status(id,name) values(1,'Арендован');

insert into model_car(id,name) values(0,'-');
insert into model_car(id,name) values(1,'Toyota');

insert into rental_point(id,name) values(0,'-');
insert into rental_point(id,name) values(1,'Пункт-0');
insert into rental_point(id,name) values(2,'Пункт-1');

insert into car(label,model_id,rental_point_id,status_id) values ('111',0,0,0);
insert into car(label,model_id,rental_point_id,status_id) values ('222',0,0,1);
insert into car(label,model_id,rental_point_id,status_id) values ('333',0,1,0);