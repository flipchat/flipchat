--
-- COMP 421: Assignment 4 - MapReduce
-- Question 03
-- Group 40
--

-- Load the data from HDFS and define the schema
raw = LOAD '/data2/cl03.csv' USING PigStorage(',') AS  (date, type:chararray, parl:int, prov:chararray, riding:chararray, lastname:chararray, firstname:chararray, gender:chararray, occupation:chararray, party:chararray, votes:int, percent:double, elected:int);

-- Eliminate all candidates who didn't win a general election
fltrd = FILTER raw BY type == 'Gen' AND elected == 1;

-- Group those in the same parliament together
grpd = GROUP fltrd BY parl;

-- Generate two tables of number of elected candidates per parliament
smmd = FOREACH grpd GENERATE ($0) as Parliament, COUNT($1) as count;
smmd2 = FOREACH smmd GENERATE ($0) as Parliament2, ($1) as count2;

-- Join entries of smmd together with corresponding entries of smmd2 having their parliament number-1
jnd = JOIN smmd BY Parliament, smmd2 by Parliament2+1;

-- Calculate the difference between number of members in a parliament and number of members in the parliament before that.
result = FOREACH jnd GENERATE Parliament, count-count2 AS count;

-- Print results to screen
DUMP result;


