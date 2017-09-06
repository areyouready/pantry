create table hibernate_sequence (next_val bigint);
insert into hibernate_sequence values ( 1 );
create table FreezerSupply (freezeDate timestamp not null, id bigint not null, primary key (id));
create table PantrySupply (id bigint not null, primary key (id));
create table Supply (SUPPLY_TYPE varchar(31) not null, id bigint not null, expiryDate timestamp,
  item varchar(256) not null, quantity integer not null, version bigint not null, weight integer not null,
  primary key (id));