create sequence image_seq start with 1 increment by 50;
create table airport_image_aud (rev integer not null, revtype smallint, airport_id bigint not null, id bigint not null, primary key (rev, airport_id, id));
create table image (airport_id bigint, id bigint not null, file_name varchar(255), data bytea, primary key (id));
create table image_aud (rev integer not null, revtype smallint, id bigint not null, file_name varchar(255), data bytea, primary key (rev, id));
alter table if exists airport_image_aud add constraint FK57vbhpo86af1sgnb6wifqrsc foreign key (rev) references revinfo;
alter table if exists image add constraint FKr3v11sq0fbqjqhb5xenrcob3c foreign key (airport_id) references airport;
alter table if exists image_aud add constraint FKetc5y2t13bkdk5yuj4eswagd4 foreign key (rev) references revinfo;
