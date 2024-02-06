# Case Study: Flight Search API (Backend Developer)

## Overview
This project involves the development of a backend API for a flight search application. The primary objectives include data modeling, CRUD operations, implementing a search API, RESTful service exposure, Java programming (using Spring/Spring Boot), authentication, scheduled background jobs, and proper documentation using Swagger.

## How to Run

This application is packaged as a war which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar -Dspring.profiles.active=test target/spring-boot-rest-example-0.5.0.war
or
        mvn spring-boot:run -Drun.arguments="spring.profiles.active=test"
```
* Check the stdout or boot_example.log file to make sure no exceptions are thrown

Once the application runs you should see something like this

```
2017-08-29 17:31:23.091  INFO 19387 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8090 (http)
2017-08-29 17:31:23.097  INFO 19387 --- [           main] com.khoubyari.example.Application        : Started Application in 22.285 seconds (JVM running for 23.032)
```
# Data Modeling
The data for the application needs to be stored and modeled in a database. You can choose either a relational (SQL) or NoSQL database. The following information should be stored:

## Flights
* ID
* Departure airport
* Arrival airport
* Departure date/time
* Return date/time
* Price

## Airports
* ID
* City

# CRUD Structure
Implement a CRUD (Create, Read, Update, Delete) structure for the following resources:

* Flights
* Airports

This structure ensures consistent and organized management of data, allowing users to create, read, update, and delete information as needed.

## About the Service

The service is just a simple flight search review REST service. It uses an in-memory database (H2) to store the data. You can also do with a relational database MySQL. If your database connection properties work, you can call some REST endpoints defined in ```com.amadeus.nturkoglu.FlightSearchAPI.controller. auth&flight&airport;``` on **port 8080**. (see below)

More interestingly, you can start calling some of the operational endpoints (see full list below) like ```/flight``` and ```/airport``` (these are available on **port 8080**)
http://localhost:8080/swagger-ui/index.html#/

<img width="1336" alt="swagger" src="https://github.com/nurullahturkoglu/nurullahturkoglu/assets/73299153/5184e7c4-4c18-4394-8931-cb2f59417830">


You can use this sample service to understand the conventions and configurations that allow you to create a DB-backed RESTful service. Once you understand and get comfortable with the sample app you can add your own services following the same patterns as the sample service.

Here is what this little application demonstrates:

* Full integration with the latest **Spring** Framework: inversion of control, dependency injection, etc.
* Packaging as a single war with embedded container (tomcat 8): No need to install a container separately on the host just run using the ``java -jar`` command
* Demonstrates how to set up healthcheck, metrics, info, environment, etc. endpoints automatically on a configured port. Inject your own health / metrics info with a few lines of code.
* Writing a RESTful service using annotation: supports both XML and JSON request / response; simply use desired ``Accept`` header in your request
* Exception mapping from application exceptions to the right HTTP response with exception details in the body
* *Spring Data* Integration with JPA/Hibernate with just a few lines of configuration and familiar annotations.
* Automatic CRUD functionality against the data source using Spring *Repository* pattern
* Demonstrates MockMVC test framework with associated libraries
* All APIs are "self-documented" by Swagger3 using annotations

## Scheduled Cron Job for Mock API Data Update
As part of the Flight Search API backend development, a scheduled cron job has been implemented to simulate the daily update of flight information from a mock third-party API. This cron job ensures that the database remains up-to-date with artificial data for testing and development purposes.

## Here are some endpoints you can call:

### Get information about system flights, airports, and authentication

```
http://localhost:8080/flight
http://localhost:8080/flight/create
http://localhost:8080/flight/search?departureCity=Istanbul&arrivalCity=Madrid&departureDateTime='2023-09-05'
http://localhost:8080/airport
http://localhost:8080/auth
```

### Create a flight resource

```
POST /flight/create
Accept: application/json
Content-Type: application/json

{
    "departureAirport":1,
    "arrivalAirport":5,
    "departureDateTime":"2024-02-06",
    "arrivalDateTime":"2024-02-06"
}

RESPONSE: HTTP 200 (Created)
Location header: http://localhost:8080/flight/1
```

### Retrieve a paginated list of airport

```
http://localhost:8080/airport

Response: HTTP 200
Content: paginated list 
```

### To view Swagger 3 API docs

Run the server and browse to localhost:8080/swagger-ui.html

# Running the project with MySQL

This project uses real MySQL database. You need to have MySQL server installed on your local environment or have access to a MySQL server. You also need to create a database schema named `bootexample`. You can change the database name in the `application.yml` file as per your environment.

### Append this to the end of application.yml:

```
---
spring:
  profiles: mysql

  datasource:
    driverClassName: com.mysql.jdbc.Driver
    url: jdbc:mysql://<your_mysql_host_or_ip>/bootexample
    username: <your_mysql_username>
    password: <your_mysql_password>

  jpa:
    hibernate:
      dialect: org.hibernate.dialect.MySQLInnoDBDialect
      ddl-auto: update # todo: in non-dev environments, comment this out:

```

### Then run is using the 'mysql' profile:

```
        java -jar -Dspring.profiles.active=mysql target/spring-boot-rest-example-0.5.0.war
or
        mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=mysql"
```

# Attaching to the app remotely from your IDE

Run the service with these command line options:

```
mvn spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"
or
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -Dspring.profiles.active=test -Ddebug -jar target/spring-boot-rest-example-0.5.0.war
```

# Questions and Comments: nurullahtrkglu@gmail.com