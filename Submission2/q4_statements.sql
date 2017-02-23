--
-- COMP 421 - Submission 02
-- Question 04
-- Group 40
--
-- Note: insert statements for 'users', 'category', 'product' and 'bid' tables are in file q9_statements.sql
--

-- Insert 5 achievements

INSERT INTO achievement (a_type, label, condition, description) VALUES (1, 'First Post', 'Post a product', 'You have posted your first product!');
INSERT INTO achievement (a_type, label, condition, description) VALUES (1, 'First Purchase', 'Purchase a product', 'You have purchased your first product!');
INSERT INTO achievement (a_type, label, condition, description) VALUES (1, 'First Sale', 'Close a sale', 'You have posted sold first product!');
INSERT INTO achievement (a_type, label, condition, description) VALUES (1, 'Organized', 'Follow a category', 'You have followed your first category!');
INSERT INTO achievement (a_type, label, condition, description) VALUES (2, 'Popular', 'Receive 100 messages and comments', 'You are popular!');

-- Insert 5 comments

INSERT INTO comment (content, datetime, u_id, p_id) VALUES ('Interested', CURRENT_TIMESTAMP, 3, 8);
INSERT INTO comment (content, datetime, u_id, p_id) VALUES ('Looks great', CURRENT_TIMESTAMP, 74, 95);
INSERT INTO comment (content, datetime, u_id, p_id) VALUES ('Can you ship to Canada?', CURRENT_TIMESTAMP, 33, 57);
INSERT INTO comment (content, datetime, u_id, p_id) VALUES ('How old is it?', CURRENT_TIMESTAMP, 74, 95);
INSERT INTO comment (content, datetime, u_id, p_id) VALUES ('I can vouch for this seller!', CURRENT_TIMESTAMP, 61, 41);

-- Insert 5 user_achievements

INSERT INTO user_achievements VALUES (3, 86);
INSERT INTO user_achievements VALUES (2, 20);
INSERT INTO user_achievements VALUES (2, 15);
INSERT INTO user_achievements VALUES (1, 84);
INSERT INTO user_achievements VALUES (4, 6);

-- Insert 5 user_categories

INSERT INTO user_categories VALUES (8, 45);
INSERT INTO user_categories VALUES (4, 77);
INSERT INTO user_categories VALUES (7, 24);
INSERT INTO user_categories VALUES (6, 36);
INSERT INTO user_categories VALUES (3, 60);

-- Insert 5 transactions

INSERT INTO transaction (paid, datetime, u_id, bid_id) VALUES (false, CURRENT_TIMESTAMP, 14, 50);
INSERT INTO transaction (paid, datetime, u_id, bid_id) VALUES (true, CURRENT_TIMESTAMP, 4, 71);
INSERT INTO transaction (paid, datetime, u_id, bid_id) VALUES (true, CURRENT_TIMESTAMP, 26, 9);
INSERT INTO transaction (paid, datetime, u_id, bid_id) VALUES (false, CURRENT_TIMESTAMP, 96, 46);
INSERT INTO transaction (paid, datetime, u_id, bid_id) VALUES (false, CURRENT_TIMESTAMP, 9, 104);

-- Insert 15 notifications

INSERT INTO notification (content, type, p_id, t_id) VALUES ('comment', 0, 8, NULL);
INSERT INTO notification (content, type, p_id, t_id) VALUES ('comment', 0, 95, NULL);
INSERT INTO notification (content, type, p_id, t_id) VALUES ('comment', 0, 57, NULL);
INSERT INTO notification (content, type, p_id, t_id) VALUES ('comment', 0, 95, NULL);
INSERT INTO notification (content, type, p_id, t_id) VALUES ('comment', 0, 41, NULL);
INSERT INTO notification (content, type, p_id, t_id) VALUES ('transaction', 0, 5, 1);
INSERT INTO notification (content, type, p_id, t_id) VALUES ('transaction', 1, 58, 2);
INSERT INTO notification (content, type, p_id, t_id) VALUES ('transaction', 1, 8, 3);
INSERT INTO notification (content, type, p_id, t_id) VALUES ('transaction', 0, 65, 4);
INSERT INTO notification (content, type, p_id, t_id) VALUES ('transaction', 0, 99, 5);
INSERT INTO notification (content, type, p_id, t_id) VALUES ('bid', 0, 99, NULL);
INSERT INTO notification (content, type, p_id, t_id) VALUES ('bid', 0, 10, NULL);
INSERT INTO notification (content, type, p_id, t_id) VALUES ('bid', 0, 27, NULL);
INSERT INTO notification (content, type, p_id, t_id) VALUES ('bid', 0, 42, NULL);
INSERT INTO notification (content, type, p_id, t_id) VALUES ('bid', 0, 87, NULL);