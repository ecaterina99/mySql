SELECT * FROM Customers
WHERE (Country = 'Germany' OR Country = 'Spain')
AND PostalCode > '10000';