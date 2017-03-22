--
-- COMP 421: Submission 03
-- Question 01
-- Group 40
--

-- Looks at all bids of a certain product and see if their own amounts are at least their products listed price.
-- If not, update their amount to be their product's listed amount.
CREATE OR REPLACE FUNCTION FIXBID (product_id INT)
RETURNS void AS $what$
  DECLARE
  b_id INT;
  b_price MONEY;
  p_price MONEY;
  at_end INT;
  DECLARE C1 CURSOR FOR
    SELECT bid_id, price FROM bid WHERE p_id = product_id;
  BEGIN
        SELECT price INTO p_price FROM product where p_id = product_id;
        OPEN C1;
        FETCH C1 INTO b_id, b_price;
        at_end := 0;
        LOOP
            IF at_end = 1 THEN RETURN;
            END IF;
            BEGIN
              IF (b_price < p_price)
                THEN UPDATE bid SET price = p_price WHERE bid_id = b_id;
              END IF;
              FETCH C1 INTO b_id, b_price;
              EXIT WHEN NOT FOUND;
            END;
        END LOOP;
        CLOSE C1;
  END;
  $what$ LANGUAGE plpgsql;

-- Fixes the bids for product 61
SELECT FIXBID(61);