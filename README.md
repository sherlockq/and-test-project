## How to Run
This project is assembled by Gradle, and based on Spring Boot 2. Java 8 is required for compilation and execution. An internet connection is required to resolve gradle library and maven repository.

run `./gradlew build` to compile and run testcases.
run `./gradlew bootRun` to start up the application and visit at http://localhost:8080/

## API Contract
Visit http://localhost:8080/swagger-ui.html to get full API descriptions.

## Project Code Introduction
It's a typical Gradle java project. See codes in src/main/java, testcases in src/test/java. Package names are straightforward.

### Restful Controller
`PhoneNumberController` It's in Restful style. 

### Repository
`PhoneNumberRepositoryFake` Fake datas in memory. Data can be modified.

### Service
`PhoneNumberService`

### Testcase
`PhoneNumberIntegrationTest` An integration testcase to test all layers of the application.
`PhoneNumberControllerTest` An testcase to test controller layer with a mock of service. Data is mocked from `PhoneNumberRepositoryFake`.  

