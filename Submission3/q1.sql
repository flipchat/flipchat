CREATE PROCEDURE FIXBID (IN product_id INT)
    LANGUAGE SQL
    BEGIN
        DECLARE @p_price MONEY;
        DECLARE at_end INT DEFAULT 0;
        DECLARE not_found CONDITION FOR SQLSTATE '02000';
        DECLARE C1 CURSOR FOR
            SELECT bid_id, price FROM bid WHERE p_id = product_id;
        DECLARE CONTINUE HANDLER FOR not_found SET at_end = 1;
        SELECT @p_price = price FROM product where p_id = product_id;
        DECLARE b_id INT;
        DECLARE b_price MONEY;
        OPEN C1;
        FETCH C1 INTO b_id, b_price;
        WHILE at_end = 0 DO
            IF (b_price < @p_price)
                THEN UPDATE bid SET price = @p_price WHERE bid_id = b_id;
            END IF;
            FETCH C1 INTO b_id, b_price;
        END WHILE;
        CLOSE C1;
    END