# tiPod BACKEND

### Technologies

- Java 8
- Spring Boot
- Swagger, Lombok
- PostgreSQL, Liquibase
- Maven, Docker

### Requirements

- JDK 8
- Any IDE of your choice (Lombok plugin required)
- PostgreSQL
- Docker v20.10.10+

### Docker => DB

docker run --name tipod-postgresql --restart=always -p 5432:5432 -e POSTGRES_PASSWORD=YDRPj943KOtoWOhy -e POSTGRES_USER=tipod -e POSTGRES_DB=tipod -d postgres:13

### Build Application

- Navigate to project directory
- mvn clean install -Plocal I -Pdev I -Pstage I -Pprod

### Run Application

- Navigate to project directory
- java -jar target/tipod-1.0.0.jar

### Swagger

http://localhost:8080/swagger-ui.html
