drop database if exists racingEvent;
create database racingEvent DEFAULT CHARACTER SET utf8;

use racingEvent;

drop table if exists racing_event;

drop table if exists sponsor;

 drop table if exists racer;
 
 drop table if exists racer_event;

 drop table if exists viewer;
 
 drop table if exists viewer_event;

create table racing_event (
    event_id bigint not null auto_increment,
    event_name varchar(45) null comment '',
    event_date date null comment '',
    location varchar(60) null comment '',
    primary key (event_id) comment '');

insert into racing_event (event_id, event_name, event_date, location) values (1, 'Formula1', '2024-10-28', 'Vitebsk');
insert into racing_event (event_id, event_name, event_date, location) values (2, 'Drift', '2024-07-14', 'Minsk');

create table sponsor (
    sp_id bigint not null auto_increment,
    sp_name varchar(45) null comment '',
    sp_budget float null comment '',
    date_contract date null comment '',
    event_id bigint null comment '',
    primary key (sp_id) comment '',
    foreign key (event_id)
    references racing_event(event_id)
    on delete no action
    on update no action);

insert into sponsor (sp_id, sp_name, sp_budget, date_contract, event_id) values ('1','Westmotors','100000','2024-10-14','1');
insert into sponsor (sp_id, sp_name, sp_budget, date_contract, event_id) values ('2','Евроопт','69999.9','2024-10-10','1');

create table racer (
rc_id bigint not null auto_increment,
rc_name varchar(30) null comment '',
car_model varchar(30) null comment '',
team_name varchar(30) null comment '',
event_id bigint null comment '',
primary key (rc_id) comment '',
foreign key (event_id) 
references racing_event(event_id)
on delete no action
on update no action);

insert into racer (rc_id, rc_name, car_model, team_name, event_id) values ('1','Yujin','BMW','RBRacers','1');
insert into racer (rc_id, rc_name, car_model, team_name, event_id) values ('2','Arthur','Audi','RoadRippers','1');

create table racer_event (
rc_id bigint null comment '',
event_id bigint null comment '',
foreign key (rc_id) references racer(rc_id) on delete no action on update no action,
foreign key (event_id) references racing_event(event_id) on delete no action on update no action); 

insert into racer_event (rc_id, event_id) values ('1', '1');
insert into racer_event (rc_id, event_id) values ('1', '1');
insert into racer_event (rc_id, event_id) values ('2', '2');
insert into racer_event (rc_id, event_id) values ('1', '2');


create table viewer (
vw_id bigint not null auto_increment,
vw_name varchar(30) null comment '',
ticket_type varchar(30) null comment '',
event_id bigint null comment '',
primary key (vw_id) comment '',
foreign key (event_id) 
references racing_event(event_id)
on delete no action
on update no action);

insert into viewer (vw_id, vw_name, ticket_type, event_id) values ('1','Глеб','VIP','1');
insert into viewer (vw_id, vw_name, ticket_type, event_id) values ('2','Robert','Normal','1');
insert into viewer (vw_id, vw_name, ticket_type, event_id) values ('3','Andrew','VIP','2');
insert into viewer (vw_id, vw_name, ticket_type, event_id) values ('4','Zach','Normal','2');

create table viewer_event (
vw_id bigint null comment '',
event_id bigint null comment '',
foreign key (vw_id) references viewer(vw_id) on delete no action on update no action,
foreign key (event_id) references racing_event(event_id) on delete no action on update no action); 

insert into viewer_event (vw_id, event_id) values ('1', '1');
insert into viewer_event (vw_id, event_id) values ('1', '2');
insert into viewer_event (vw_id, event_id) values ('2', '1');
insert into viewer_event (vw_id, event_id) values ('3', '1');

