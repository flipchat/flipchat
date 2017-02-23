-- Outputs full names of all admin users that have created a category
SELECT users.full_name
FROM users, category
WHERE users.u_id = category.created_by;

-- Outputs the number of notifications triggered by a transactions that have been paid
SELECT COUNT(*) AS paid_transaction_notifications
FROM notification, transaction
WHERE notification.t_id = transaction.t_id AND transaction.paid = TRUE;

-- Outputs full names and content of each comment written by a user whose name contains an "a"
SELECT users.full_name, comment.content
FROM users, comment
WHERE users.u_id = comment.u_id AND users.full_name LIKE '%i%';

-- Outputs the bid_id, product title and price for the bids that were placed on products with a price that is lower
-- than the average price of all products (Limited to first 30 rows)
SELECT bid.bid_id, product.title, product.price
FROM bid, product
WHERE bid.p_id = product.p_id AND product.price::numeric < (
  SELECT AVG(price::numeric) FROM product
) LIMIT 30;

-- Outputs a mapping between achievement types and the number of achievements that have this type

SELECT achievement.a_type, COUNT(*) as type_count
FROM achievement
GROUP BY achievement.a_type;
