SELECT firstName,lastName,city,Address.state
FROM Person, Address
WHERE Person.personId = Address.personId(+);