drop table if exists country;
create table country (
  id   int primary key auto_increment,
  name varchar(128) not null unique
);

drop table if exists region;
create table region (
  id   int primary key auto_increment,
  country_id int not null,
  name varchar(128) not null unique,
  constraint region_country_id_fk foreign key (country_id) references country (id)
);
  
drop table if exists grapes;
create table grapes (
  id   int primary key auto_increment,
  name varchar(128) not null unique
);

drop table if exists wine;
create table wine (
  id   int primary key auto_increment,
  region_id int not null,
  grapes_id int not null,
  name varchar(128) not null,
  winery varchar(128) not null,
  avg_rate double,
  constraint UQ_wine_winery unique(name, winery),
  constraint wine_region_id_fk foreign key (region_id) references region (id),
  constraint wine_grapes_id_fk foreign key (grapes_id) references grapes (id)  
);

drop table if exists user;
create table user (
  id   int primary key auto_increment,
  login varchar(64) not null unique,
  role varchar(64) not null,
  password varchar(64) not null
);

drop table if exists bottle;
create table bottle (
  id   int primary key auto_increment,
  wine_id int not null,
  user_id int not null,
  year YEAR not null,
  rate int not null,
  status boolean not null default false,
  date date,
  constraint if_status_then_date_is_not_null check ((not status) or (date is not null)),
  constraint boottle_wine_id_fk foreign key (wine_id) references wine (id),
  constraint bottle_wine_id_fk foreign key (user_id) references user (id)
);

drop table if exists recommendation;
create table recommendation (
  id   int primary key auto_increment,
  sommelier_id int not null,
  user_id int not null,
  message text,
  constraint sommelier_recommendation_id_fk foreign key (sommelier_id) references user (id),
  constraint user_recommendation_id_fk foreign key (user_id) references user (id)
);

drop table if exists wine_recomendation;
create table wine_recomendation (
  id   int primary key auto_increment,
  recommendation_id int not null,
  bottle_id int not null,
  constraint wine_recomendation_recommendation_id_fk foreign key (recommendation_id) references recommendation (id),
  constraint wine_recomendation_bottle_id_fk foreign key (bottle_id) references bottle (id)
)