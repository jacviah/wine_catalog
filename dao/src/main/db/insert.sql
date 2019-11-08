insert into country (name)
values ('Italy'),
       ('France'),
       ('Spain'),
       ('Portugal'),
       ('Chile'),
	   ('United States'),
       ('Argentina'),
       ('Australia'),
       ('South Africa'),
       ('Georgia'),
       ('New Zeland'),
       ('Greece');       
insert into region (country_id, name)
values (1, 'Piedmont'),
       (1, 'Tuscany'),
       (1, 'Veneto'),
       (1, 'Emilia-Romagna'),
       (1, 'Sicily'),
	   (2, 'Bordeaux'),
       (2, 'Burgundy'),
       (2, 'The Rhone Valley '),
       (2, 'The Loire Valley '),
       (2, 'Provence');     
       
insert into grape (name)
values ('Cabernet Sauvignon'),
       ('Cabernet Franc'),
       ('Merlot'),
	   ('Syrah'),
       ('Tempranillo'),
       ('Chardonnay');
       
insert into wine (region_id, grape_id, name, winery)
values (1, 1, 'wine', 'chateau'),
		(1, 1, 'wine', 'winery'),
		(1, 2, 'wine', 'chateau2'),
        (1, 2, 'wine2', 'chateau');
    
insert into user (login, role, password)
values ('sommelier', 'sommelier', 'sommelier'),
       ('user', 'user', 'user');
       
insert into auth_user (user_id, uuid)
values (1, uuid()),
       (2, uuid());
       
insert into user_detail (id, user_id, description)
values (1, 1,'sommelier'),
       (2, 2, 'user');
       
insert into bottle (wine_id, user_id, year, rate, status, date)
values (1, 1, 2011, 3, 1, '2019-09-11'),
       (1, 1, 2012, 4, 1, '2019-09-12'),
       (2, 2, 2010, 2, 1, '2019-09-11'),
       (2, 1, 2014, 2, 1, '2019-09-15');
       
SET SQL_SAFE_UPDATES = 0;   
update wine_catalog.wine w
set w.avg_rate = 
    (SELECT AVG(rate)
     FROM bottle b 
     where  w.id=b.wine_id); 
SET SQL_SAFE_UPDATES = 1;

