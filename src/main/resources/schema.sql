DROP TABLE IF EXISTS groups, module, module_group, users cascade;
create table groups (id bigint generated by default as identity, created_date timestamp, last_modified_date timestamp, version bigint, name varchar(255), primary key (id));
create table module (id bigint generated by default as identity, created_date timestamp, last_modified_date timestamp, version bigint, name varchar(255), primary key (id));
create table module_group (id bigint generated by default as identity, created_date timestamp, last_modified_date timestamp, version bigint, orders bigint, groups_id bigint not null, module_id bigint, primary key (id));
create table users (id bigint generated by default as identity, created_date timestamp, last_modified_date timestamp, version bigint, name varchar(255), groups_id bigint, primary key (id));
alter table module_group add constraint FK8ssb5yrj5fml54p5k2qanynbv foreign key (groups_id) references groups;
alter table module_group add constraint FKct78eiuksl8gpgf7m6ycxbcyr foreign key (module_id) references module;
alter table users add constraint FKi2nkkbwd8q35sw10modgkvwfe foreign key (groups_id) references groups;