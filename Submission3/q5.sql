-- increase the appropriate category count and create a notification for subscribers of that category
CREATE OR REPLACE FUNCTION update_category() RETURNS trigger AS $newProduct$
    BEGIN
        UPDATE category SET product_count = product_count + 1 WHERE cat_id = NEW.cat_id;
        INSERT INTO notification (content, type, p_id) VALUES('new product', 1, NEW.p_id);
        RETURN NEW;
    END;
$newProduct$ LANGUAGE plpgsql;

-- when a new product is inserted, call the procedure update_category()
CREATE TRIGGER update_cat AFTER INSERT ON product
    FOR EACH ROW EXECUTE PROCEDURE update_category();

INSERT INTO product (title, description, price, is_sold, datetime, expiry, u_id, cat_id) VALUES ('the other shoe', 'comes with 2 laces', 421, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 75, 4);