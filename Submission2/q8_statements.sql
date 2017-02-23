ALTER TABLE product
ADD CONSTRAINT chk_Price CHECK (price::numeric >= 0);

ALTER TABLE comment
ADD CONSTRAINT chk_Date CHECK (datetime <= now());
