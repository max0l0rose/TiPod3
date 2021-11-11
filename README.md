README.md
OctiPod BACKEND

Technologies
- Java 8
- Spring Boot
- Swagger, Lombok
- PostgreSQL, Liquibase
- Maven, Docker

Requirements
- JDK 8
- Any IDE of your choice (Lombok plugin required). 
  For IntelliJ IDEA: Build, Execution, Deployment -> Compiler -> Annotation processors -> Enable annotation processing
- PostgreSQL
- Docker 19+

[comment]: <> (Database &#40;PostgreSQL, MySQL&#41;)

[comment]: <> (• docker run --name gtasearch-dev-postgresql --restart=always -p 5441:5432 -e POSTGRES_PASSWORD=LfOyjS42uG4nxQdM -e POSTGRES_USER=gtasearch -e POSTGRES_DB=gtasearch -d postgres:13)

[comment]: <> (• docker run --name gtasearch-stage-postgresql --restart=always -p 5442:5432 -e -e -e -d postgres:13)

Build Application
- Navigate to project directory
- mvn clean install -Plocal I -Pdev I -Pstage I -Pprod

Run Application
- Navigate to project directory
- java -jar target/gtasearch-backend-1.O.O.jar

Swagger. REST APIs Documentation
[openapi.json](openapi.json)

[Swagger IJI endpoint](http://localhost:8080/swagger-ui.html)
