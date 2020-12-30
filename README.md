# volvo

Enpoints:

GET: /Customer (List all Customers).
GET: /Customer/{id} (List the Customer with the given Id)
GET: /Customer/zipcode/{zipCode} (List all the Customers with the given zipCode).
Post: /Customer (Create a Customer, requires a body)
Put: /Customer/{id} (Updates the Customer with the Ii provided in the path)
Delete /Customer/{id} (Deletes the Customer with the given Id).

{
"documentId": "documentId",
"name": "name",
"age" : 30,
"addressBook" : [{"zipCode": "99999-000", "number": 000 }]
}
