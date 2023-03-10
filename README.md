E-Commerce-Admin Application:
---

Uses Java, postgreSQL, and Spring Boot to manage customer, product, and sale data in a database using basic CRUD methods. Functionality is accessible through written REST api’s.
------------------------------------------------------
API Endpoints:

(With endpoints that accept RequestBody's make sure input field names match defined names exactly)

Base URL: http://localhost:8080/ecommerce/admin

Get all Products: BaseUrl + "/products"

Get Products with no sales: + BaseUrl "/noSales"

Get Product by Id: BaseUrl + "/products/{productId}"

Create Product: BaseUrl + "/products/create" (accepts RequestBody with fields name, description, price, category)

Update Product: BaseUrl + "/products/update" (accepts RequestBody with fields name, description, price, category)

Delete Product: BaseUrl + "/products/delete/{productId}" (Only deletes products with no sales)

Get all Customers: BaseUrl + "/customers" 

Get Customer by Id: BaseUrl + "/customers/{customerId}"

Create Customer: BaseUrl + "/customers/create" (accepts RequestBody with fields firstName, lastName, stAddress, city, state, zipcode)

Update Customer: BaseUrl + "/customers/update" (accepts RequestBody with fields firstName, lastName, stAddress, city, state, zipcode)

Delete Customer: BaseUrl + "/customers/delete/{customerId}"

Get Sale by Id: BaseUrl + "/sales/{saleId}"

Filter Sales by productId OR customerId (does not accept both):
BaseUrl + "/products?productId={productId}" -OR- /products?customerId={customerId}" (returns all sales if neither requestParam is present)

Get Unshipped Sales: BaseUrl + "/products/unshipped"

Create Sale: BaseUrl + "/products/create" (accepts RequestBody with fields customerId, saleDate, shipDate)

Update Sale: BaseUrl + "/products/update" (accepts RequestBody with fields customerId, saleDate, shipDate)

Delete Sale: BaseUrl + "/products/delete/{saleId}"

Get SaleItems by SaleId: BaseUrl + "/saleItems/{saleId}"

