# Setup

To run this project you need Docker running on your local machine,
after that just run the following command in the root of the project:
```
gradlew bootRun
```
Create a new order
```shell
curl --location 'http://localhost:8080/api/v1/orders' \
--header 'Idempotency-Key: f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454' \
--header 'Content-Type: application/json' \
--data '{
    "totalAmount":1111
}'
```

Change status to paid
```shell
curl --location --request PUT 'http://localhost:8080/api/v1/orders/1c80fedf-a0b1-4603-a9f2-210039b7c6c9/pay'
```
Change status to cancelled
```shell
curl --location --request PUT 'http://localhost:8080/api/v1/orders/1c80fedf-a0b1-4603-a9f2-210039b7c6c9/cancel'
```

## Technology Rationale

**Java 21** - Latest LTS version providing modern language features, improved performance, and long-term support for enterprise applications.

**Spring Boot 3.5.3** - Chosen for its mature ecosystem, comprehensive auto-configuration, and excellent support for RESTful microservices. Leveraging 5+ years of experience with the framework for rapid, reliable development.

**PostgreSQL** - Selected to maintain compatibility with the existing legacy system's database layer, minimizing migration complexity while providing robust ACID compliance and scalability.

**Spring Data JPA** - Provides simplified data access layer with automatic repository implementation, reducing boilerplate code and accelerating development of CRUD operations.

**Flyway** - Database migration tool ensuring version control and consistent schema changes across environments, critical for maintaining data integrity during legacy system modernization.

**Lombok** - Reduces Java boilerplate code (getters, setters, constructors) improving code readability and maintainability.

**MapStruct** - Compile-time mapping framework for efficient DTO-to-Entity conversions, providing type-safe mappings with better performance than reflection-based alternatives.

**Docker Compose Integration** - Simplifies local development setup with containerized PostgreSQL, ensuring consistent development environments across the team.