create sequence revinfo_seq start with 1 increment by 50;
create table address_aud (rev integer not null, revtype smallint, id bigint not null, city varchar(255), country varchar(255), street varchar(255), zip varchar(255), primary key (rev, id));
create table airport_aud (rev integer not null, revtype smallint, address_id bigint, id bigint not null, iata varchar(255), name varchar(255), primary key (rev, id));
create table flight_aud (delay integer, rev integer not null, revtype smallint, id bigint not null, landing_id bigint, takeoff_id bigint, takeoff_time timestamp(6), flight_number varchar(255), primary key (rev, id));
create table revinfo (rev integer not null, revtstmp bigint, primary key (rev));
alter table if exists address_aud add constraint FK_address_aud_rev foreign key (rev) references revinfo;
alter table if exists airport_aud add constraint FK_airport_aud_rev foreign key (rev) references revinfo;
alter table if exists flight_aud add constraint FK_flight_aud_rev foreign key (rev) references revinfo;