
--Create view containing the average price of products for each category

CREATE VIEW view1 (cat_id, avgPrice)
AS SELECT cat_id, AVG (price::money::numeric::float8) AS avgPrice
FROM product
GROUP BY cat_id;

--Select the category with the smallest average price

SELECT cat_id, avgPrice FROM view1 WHERE avgPrice = (SELECT MIN (avgPrice) FROM view1);


--Create view containing the max bid for each product

CREATE VIEW view2 (p_id, price)
AS SELECT p_id, MAX(price::money::numeric::float8) AS price
FROM bid
GROUP BY p_id;


--Select the products with a bid higher than the average value of all max bids

SELECT p_id, price FROM view2 WHERE price > (SELECT AVG (price) FROM view2);



UPDATE view1 SET avgPrice = 123.45 WHERE cat_id = 2;
UPDATE view2 SET price = 123.45 WHERE p_id = 2;


--The updates to these views do not work. Views containing GROUP BY are not automatically updatable in PostgreSQL 9.1.
--Updating the views requires an INSTEAD OF UPDATE trigger or an unconditional ON UPDATE DO INSTEAD rule.