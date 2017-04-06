--
-- COMP 421: Assignment 4 - MapReduce
-- Question 01
-- Group 40
--

-- Load the data from HDFS and define the schema
raw = LOAD '/data2/cl03.csv' USING PigStorage(',') AS  (date, type:chararray, parl:int, prov:chararray, riding:chararray, lastname:chararray, firstname:chararray, gender:chararray, occupation:chararray, party:chararray, votes:int, percent:double, elected:int);

-- Eliminate rows with percentages less than 60
fltrd = FILTER raw BY percent >= 60.0;

-- Project only the needed fields
gen = FOREACH fltrd GENERATE CONCAT(firstname, CONCAT(' ', lastname));

-- Remove duplicates
result = DISTINCT gen;

-- Print the results to the screen
DUMP result;

-- Displays a step-by-step execution of a sequence of statements
ILLUSTRATE result;

-- Store the results into 'q1' file
STORE result INTO 'q1';

