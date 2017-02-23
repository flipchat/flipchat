-- Outputs all the name of each admin user that has created a category

SELECT *
FROM users, category
WHERE users.u_id = category.created_by;

-- Prints the number of notifications triggered by a transactions that have been paid
SELECT COUNT(*)
FROM notification, transaction
WHERE notification.t_id = transaction.t_id AND transaction.paid = TRUE;

-- Outputs the user name and content of each comment written by a user whose name contains an "a"
SELECT users.full_name, comment.content
FROM users, comment
WHERE users.u_id = comment.u_id AND users.full_name LIKE '%i%';

-- Prints the bid_id, product title and price for the bids that were placed on products with a price that is lower
-- than the average price of all products
SELECT bid.bid_id, product.title, product.price
FROM bid, product
WHERE bid.p_id = product.p_id AND product.price::numeric < (
  SELECT AVG(price::numeric)
    FROM product
  );

