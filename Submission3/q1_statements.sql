--
-- COMP 421: Submission 03
-- Question 01
-- Group 40
--

-- Looks for the id of the highest bidder of a selected product and assigns it to the product
CREATE OR REPLACE FUNCTION updateHighestBid (product_id INT)
RETURNS INT AS $what$
  DECLARE
  b_id INT;
  b_price MONEY;
  p_price MONEY;
  max_b_id INT;
  max_amount MONEY;
  DECLARE C1 CURSOR FOR
    SELECT bid_id, price FROM bid WHERE p_id = product_id;
  BEGIN
        SELECT price INTO p_price FROM product where p_id = product_id;
        OPEN C1;
        FETCH C1 INTO b_id, b_price;
        max_amount := 0;
        max_b_id := 0;
        LOOP
            BEGIN
              IF (b_price > max_amount)
                THEN max_amount := b_price; max_b_id := b_id;
              END IF;
              FETCH C1 INTO b_id, b_price;
              EXIT WHEN NOT FOUND;
            END;
        END LOOP;
        CLOSE C1;
        UPDATE product SET bid_id = max_b_id WHERE p_id = product_id;
        RETURN max_b_id;
  END;
  $what$ LANGUAGE plpgsql;

-- Updates the highest bid id associated with product id 61
SELECT updateHighestBid(61);