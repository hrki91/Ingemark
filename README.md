# Description

Test assignment #1 

# Requirements

Test assignment #1 requires 
	- Java 1.8 or higher
	- Postgresql database
	
# Installation
	- Create database 
	- Execute script from /resources/sql
	- Configure application.properties
		- spring.datasource.url -> database url
		- spring.datasource.username -> provide valid database username
		- spring.datasource.password -> provide valid database user password
		- spring.datasource.driver-class-name -> currently only possible "org.postgresql.Driver"
		- spring.jpa.database-platform -> currently only possible "org.hibernate.dialect.PostgreSQLDialect"
		- spring.jpa.hibernate.ddl-auto -> use none if table is created from sql script, create if table is missing or update for new constrains,columns...  
	- Create root folder
		- Copy target/zadatak-0.0.1-SNAPSHOT.jar
		- Copy modified application.properties
		- Run application with command java -jar zadatak-0.0.1-SNAPSHOT.jar 
		