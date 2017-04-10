--load the data from HDFS and define the schema
raw = LOAD '/data2/emp.csv' USING PigStorage(',') AS  (empid:int, fname:chararray, lname:chararray, deptname:chararray, isManager:chararray, mgrid:int, salary:int);

--get all the Finance managers (flat hierarchy, managers do not have managers)
managers = FILTER raw BY deptname == 'Finance' and isManager == 'Y';

--get all employees who are not managers
underlings = FILTER raw by isManager == 'N';

--group by their manager's ID (even if their manager is not in Finance)
grp = GROUP underlings BY mgrid;

--for each manager, count the # of employees
cntd = FOREACH grp GENERATE ($0) AS mgrEmpid, COUNT($1) AS empCnt;

--take all tuples of cntd s.t. the manager is in Finance
jnd2 = JOIN managers BY empid, cntd BY mgrEmpid;

--take the employee id, last name, and employee count of the managers in finance
res = FOREACH jnd2 GENERATE empid, lname, empCnt;

--print the result tuple to the screen
dump res;

--store results under home directory as 'q5'
STORE res INTO 'q5' using PigStorage(',');
