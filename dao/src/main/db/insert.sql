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
    
insert into user (login, role, password, sommelier_id)
values ('sommelier', 'SOMMELIER', 'sommelier', null),
       ('expert', 'SOMMELIER', 'expert', null),
       ('user', 'USER', 'user', 1),
       ('amateur', 'USER', 'amateur', 2);
       
insert into auth_user (user_id, uuid)
values (1, uuid()),
       (2, uuid());
       
insert into user_detail (id, user_id, description)
values (1, 1,'first sommelier'),
       (2, 3, 'first user'),
       (3, 2,'expert - second sommelier'),
       (4, 4, 'amateur - second user');
       
insert into wine (region_id, grape_id, name, winery)
values (1, 1, 'wineItaly1', 'chateau'),
		(2, 1, 'wineItaly2', 'winery'),
		(5, 3, 'wineItaly3', 'chateau2'),
        (1, 2, 'wineItaly4', 'chateau'),
        (6, 3, 'wineFrance1', 'chateau'),
		(7, 1, 'wineFrance2', 'winery'),
		(9, 4, 'wineFrance3', 'chateau2'),
        (2, 2, 'wineFrance4', 'chateau');
        
insert into bottle (wine_id, user_id, year, rate, status, date)
values (1, 1, 2011, 3, 1, '2019-09-11'),
       (3, 1, 2012, 4, 1, '2019-09-12'),
       (4, 2, 2010, 2, 1, '2019-09-11'),
       (3, 1, 2014, 2, 1, '2019-09-15'),
       (4, 1, 2011, 3, 1, '2019-09-11'),
       (5, 1, 2012, 4, 1, '2019-09-12'),
       (6, 2, 2010, 2, 1, '2019-09-11'),
       (7, 1, 2014, 2, 1, '2019-09-15');
       
SET SQL_SAFE_UPDATES = 0;   
update wine_catalog.wine w
set w.avg_rate = 
    (SELECT AVG(rate)
     FROM bottle b 
     where  w.id=b.wine_id); 
SET SQL_SAFE_UPDATES = 1;

