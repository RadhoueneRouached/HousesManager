delete
from house;
ALTER TABLE house MODIFY id int NOT NULL AUTO_INCREMENT;
INSERT INTO house(id, house_number, street, city, postal_code, owner_name, number_bedrooms, construction_date, price)
values (1, 12, 'stachus', 'Munich', 80330,'Radhouene Rouached', 4, '2001-01-12', 61.000),
       (2, 12, 'pasing', 'Munich', 80265, 'Radhouene Rouached', 2, '2011-04-10', 15.000),
       (3, 12, 'berg am laim', 'Munich', 80346, 'Radhouene Rouached', 1, '2006-11-02', 7.000);

