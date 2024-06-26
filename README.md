# Course Project

This is a demo project for Spring Boot, developed in Java and built with Maven.

## Description

This project is a simple Spring Boot application that provides a RESTful API for managing users and
orders. It
includes features such as creating, retrieving, updating, and deleting users, products, categories
and orders.

## Technologies

- Java 21
- Spring Boot 3.3.0-SNAPSHOT
- Maven
- PostgreSQL 42.7.3
- H2 Database
- JaCoCo 0.8.12 for code coverage

## Setup

To run this project, you need to have Java and Maven installed on your machine.

1. Clone the repository:

```bash
git clone https://github.com/wesley-prado/workshop-springboot3-jpa.git
```

2. Navigate to the project directory:

```bash
cd course
```

3. Build the project:

```bash
mvn clean install
```

4. Run the project:

```bash
mvn spring-boot:run
```

## Testing

This project uses JUnit and Mockito for unit testing. To run the tests, use the following command:

```bash
mvn test
```

To generate a JaCoCo coverage report, use the following command:

```bash
mvn clean org.jacoco:jacoco-maven-plugin:0.8.12:prepare-agent  verify org.jacoco:jacoco-maven-plugin:0.8.12:report
```

The report will be generated in the target/site/jacoco directory.

## License

The source code for the site is licensed under the MIT license, which you can find in the
MIT-LICENSE.txt file.
