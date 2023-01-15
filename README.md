# WorkFlowManagement
WorkFlowManagement app in Spring Boot, has ability to create users, products, orders. Orders have basic state management.


To Compile:
mvn clean package

To Run :
Update these three properties with an existing MySQL DB details
spring.datasource.url=jdbc:mysql://localhost:3306/<DB_NAME>

spring.datasource.username=<DB_USER>

spring.datasource.password=<DB_PASS>

The Application will create the neccessary schema and objects on an exising DB

Run the following command on CMD:

mvnw spring-boot:run

Application also runs with Intellij. Build while running for hot reload.

GUI Home Page:
With default port:
http://localhost:8080/