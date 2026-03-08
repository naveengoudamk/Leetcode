# Write your MySQL query statement below
Select  email as Email
From Person
group by email
having count(*)>1;