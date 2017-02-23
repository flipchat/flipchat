--
-- COMP 421 - Submission 02
-- Question 08
-- Group 40
--

-- Ensure that product prices are $0 or higher
ALTER TABLE product
ADD CONSTRAINT chk_Price CHECK (price::numeric >= 0);

-- Ensure that comment datetime is less or equal than curent datetime
ALTER TABLE comment
ADD CONSTRAINT chk_Date CHECK (datetime <= now());


-- Note test INSERT statements are inside the output file.