--
-- COMP 421 - Submission 02
-- Question 06
-- Group 40
--


-- Deletes achievements from users who haven't paid their transaction
DELETE FROM user_achievements WHERE u_id IN (SELECT t.u_id FROM transaction t WHERE paid = false);

--Update product count of all categories
UPDATE category SET product_count = (SELECT COUNT(*) FROM product WHERE product.cat_id = category.cat_id);

--Update sold status of products when their transaction is paid
UPDATE product SET is_sold = true WHERE bid_id IN (SELECT t.bid_id FROM transaction t WHERE paid = true);

--Adds notification for users whose products have been sold
INSERT INTO notification (content, type, p_id, t_id)
(SELECT 'transaction' as content, 0 as type, p.p_id, t.t_id FROM product p, transaction t WHERE p.bid_id = t.bid_id AND p.is_sold = true);