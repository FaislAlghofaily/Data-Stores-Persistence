# Data-Stores-Persistence
this is my Third project in my Nanodegree journey for udacity java web Devoleper.

here i connected my application to external mySQL database and used an in memory databse H2 for unit testing, used DTOs to protect my tables.
tables are created using hibernate, used CrudRepository to manage my request to the database, used @Transactional.

before runnning the application make sure that you connect to your mySQL in the "application.properties" file
spring.datasource.url=jdbc:mysql://localhost:3306/t //here add the name of your schema
spring.datasource.username=root // here add the username
spring.datasource.password=aec@12345 // here add the password

no ne

technologies used:
mySQL,
hibernate,
CrudRepository,
@ManytoMany @ManyToOne @OneToMany @OneToOne,
@Transactional,
DTO,
Postman

