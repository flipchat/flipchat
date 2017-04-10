--
-- COMP 421: Assignment 4 - MapReduce
-- Question 04
-- Group 40
--

-- Load the data from HDFS and define the schema
raw = LOAD '/data2/cl03.csv' USING PigStorage(',') AS  (date, type:chararray, parl:int, prov:chararray, riding:chararray, lastname:chararray, firstname:chararray, gender:chararray, occupation:chararray, party:chararray, votes:int, percent:double, elected:int);

-- Filter the candidates by those that won an election
fltrd = FILTER raw BY elected == 1;

-- Group the data by parliament number
grp = GROUP fltrd BY parl;

-- Generate the number of elected candidates per parliament
parl_count = FOREACH grp GENERATE ($0) as Parliament, COUNT($1) as parliament_count;

-- Project parliament number and party
party_info = FOREACH raw GENERATE parl,party;

-- Join the parliament counts with the raw data
jn = JOIN parl_count BY Parliament, party_info BY parl;

-- Group those in the same parliament and party together
grpd = GROUP jn BY (Parliament,party);

-- Print the number of members in each party, and the total number of members in the parliament
result = FOREACH grpd Generate
		 FLATTEN(group) AS (Parliament,party),
		 COUNT(jn.party) AS party_count,
		 MAX(jn.parliament_count) AS parl_count;

-- Print results to screen
dump result;

-- Store the results into 'q4'
store result into 'q4' using PigStorage(',');
