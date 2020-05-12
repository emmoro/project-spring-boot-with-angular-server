## Spring Boot API with Angular
Project using Angular as a front-end and Spring boot restful API as a backend.
</br>
Backend: Project Spring Boot with Java 8 using Docker, Rest, Junit 5, Swagger and Database mysql.
</br>
Front-end: Angular
</br>

## Prerequisites
You will need to following tools in order to work with this project and code
</br>
* git (http://git-scm.com/)
* JDK 1.8+ (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Maven 3.x+ (http://maven.apache.org/)
* An IDE of your choice.  (Eclipse, IntelliJ, Spring STS, Netbeans, Angular, etc.)

## Considerations
IDE used was the eclipse(Java) and Sublime(Angular)
</br>

## Tests
Unit testing was used with the resources of JUnit 5, MockMvc, TestRestTemplate.
</br>

## Swagger information
Address for accessing Swagger in the application when it is up: http://localhost:8180/swagger-ui.html
</br>

## Getting Started
To run this project locally, perform the following steps:
</br>
* Use this command to download the project to your machine: git clone https://github.com/emmoro/project-spring-boot-with-angular-server.git
</br>
To install all of its dependencies and start each app, follow the instructions below:
</br>
* To run the server. Application address: http://localhost:8180/
```bash
mvnw spring-boot:run
```
</br>
* To run the client. Application address: http://localhost:4200/
```bash
npm start
```
</br>

## Note
* This project is the server in Spring-boot, it´s necessary to do download the part of the Client, at the following address:
* Front-end in Angular: <a href="https://github.com/emmoro/project-spring-boot-with-angular-client">project-spring-boot-with-angular-client</a>