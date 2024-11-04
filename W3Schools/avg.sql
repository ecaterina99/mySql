SELECT * FROM Products
WHERE price > (SELECT AVG(price) FROM Products);
