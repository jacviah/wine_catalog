drop table if exists wine_recomendation;
drop table if exists recommendation;
drop table if exists bottle;
drop table if exists wine;
drop table if exists region;
drop table if exists country;
drop table if exists grape;
drop table if exists auth_user;
drop table if exists user;

create table country (
  id   bigint primary key auto_increment,
  name varchar(128) not null unique
);

create table region (
  id   bigint primary key auto_increment,
  country_id bigint not null,
  name varchar(128) not null unique,
  constraint region_country_id_fk foreign key (country_id) references country (id)
);
  
create table grape (
  id   bigint primary key auto_increment,
  name varchar(128) not null unique
);

create table wine (
  id   bigint primary key auto_increment,
  region_id bigint not null,
  grape_id bigint not null,
  name varchar(128) not null,
  winery varchar(128) not null,
  avg_rate double,
  constraint UQ_wine_winery unique(name, winery),
  constraint wine_region_id_fk foreign key (region_id) references region (id),
  constraint wine_grape_id_fk foreign key (grape_id) references grape (id)  
);

create table user (
  id   bigint primary key auto_increment,
  login varchar(64) not null unique,
  role varchar(64) not null,
  password varchar(64) not null
);

create table auth_user (
  user_id   bigint primary key,
  uuid varchar(64) not null,
  constraint auth_user_user_id_fk foreign key (user_id) references user (id)
);

create table bottle (
  id   bigint primary key auto_increment,
  wine_id bigint not null,
  user_id bigint not null,
  year varchar(4) not null,
  rate int,
  status boolean not null default false,
  date date,
  constraint if_status_then_date_is_not_null check ((not status) or (date is not null)),
  constraint boottle_wine_id_fk foreign key (wine_id) references wine (id),
  constraint bottle_wine_id_fk foreign key (user_id) references user (id)
);

create table recommendation (
  id   bigint primary key auto_increment,
  sommelier_id bigint not null,
  user_id bigint not null,
  message text,
  constraint sommelier_recommendation_id_fk foreign key (sommelier_id) references user (id),
  constraint user_recommendation_id_fk foreign key (user_id) references user (id)
);

create table wine_recomendation (
  id   bigint primary key auto_increment,
  recommendation_id bigint not null,
  bottle_id bigint not null,
  constraint wine_recomendation_recommendation_id_fk foreign key (recommendation_id) references recommendation (id),
  constraint wine_recomendation_bottle_id_fk foreign key (bottle_id) references bottle (id)
)