# customer-rewards


Running application (BACK END)

cd customer-rewards

mvn clean install

java -jar target/customer-rewards-0.0.1-SNAPSHOT.jar

Application will launch at 8080 port.

#Exposed services:
GET /api/customers # List all customers

POST /api/customer # Create a customer

DELETE /api/customer/{id} #delete customer

GET /api/customers/name/{name} #Get customer by name

PUT /api/customer/{id} #Update customer

GET /api/purchases #List all purchases

POST /api/purchase #Create purchase

DELETE /api/purchase/{id} #Delete purchase

GET /api/purchases/customer/{customerId} #Get purchases by customer id

GET /api/purchases/customer/points/{customerId} #Get monthly points by customer id

PUT /api/purchase/{id} #Update purchase






#Running application (FrontEnd)
 cd customer-rewards/src/frontend/

 npm i #(fisrt time)

 npm run serve

 #Previous step will launch app at port 4200

 Navigate to http://localhost:4200/

Any problems? Contact me.

 Enjoy.
