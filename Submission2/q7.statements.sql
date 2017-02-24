
--
-- COMP 421 - Submission 02
-- Question 06
-- Group 40
--

-- Create a view containing the average price of products for each category
CREATE VIEW avg_price_category (cat_id, avg_price)
AS SELECT cat_id, AVG (price::numeric) AS avg_price
FROM product
GROUP BY cat_id;

-- Select the category with the smallest average price
SELECT cat_id, avg_price FROM avg_price_category WHERE avg_price = (SELECT MIN (avg_price) FROM avg_price_category);



-- Create a view containing the max bid for each product
CREATE VIEW max_bid (p_id, price)
AS SELECT p_id, MAX(price::numeric) AS price
FROM bid
GROUP BY p_id;

-- Select the products with a bid higher than the average value of all max bids
SELECT p_id, price FROM max_bid WHERE price > (SELECT AVG (price) FROM max_bid) LIMIT 5;



-- Update views
UPDATE avg_price_category SET avg_price = 123.45 WHERE cat_id = 2;
UPDATE max_bid SET price = 123.45 WHERE p_id = 2;

-- NOTE: The updates to these views do not work.
-- Views containing GROUP BY are not automatically updatable in PostgreSQL 9.1.
-- Updating the views requires an INSTEAD OF UPDATE trigger or an unconditional ON UPDATE DO INSTEAD rule.