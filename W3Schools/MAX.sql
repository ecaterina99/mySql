SELECT MAX(Price) AS BiggestPrice, CategoryID
FROM Products
GROUP BY CategoryID; 
