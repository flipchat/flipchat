--
-- COMP 421: Assignment 4 - MapReduce
-- Question 02
-- Group 40
--

-- Load the data from HDFS and define the schema
raw = LOAD '/data2/cl03.csv' USING PigStorage(',') AS  (date, type:chararray, parl:int, prov:chararray, riding:chararray, lastname:chararray, firstname:chararray, gender:chararray, occupation:chararray, party:chararray, votes:int, percent:double, elected:int);

-- Eliminate all candidates with less than 100 votes
fltrd = FILTER raw by votes > 100;

-- Split the relation into those who won and those who lost
SPLIT fltrd INTO winners IF elected == 1, losers IF elected == 0;

-- Join winners and losers by date of election, election type, parliament type, province and riding
result = JOIN winners BY date, losers BY date;
result = FILTER result BY winners::type == losers::type AND winners::parl == losers::parl AND winners::prov == losers::prov AND winners::riding == losers::riding;

-- Project lastnames of winner and loser and the vote difference
result = FOREACH result GENERATE winners::lastname, losers::lastname, (winners::votes - losers::votes) AS votediff;

-- Include only tuples where the difference is less than 10.
result = FILTER result BY votediff < 10;

-- Print results to screen
DUMP result;

-- Store the results into 'q2' file
STORE result INTO 'q2';
