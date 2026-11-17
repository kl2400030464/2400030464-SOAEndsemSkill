# Tourism Management System — Spring Boot Microservices

A runnable starter application for Spring Tool Suite (STS) / Eclipse.

## Technology
- Java 17
- Spring Boot 3.3.5
- Spring Cloud 2023.0.3
- Maven
- Spring Cloud Netflix Eureka
- Spring Cloud Gateway
- Spring Data JPA + H2 (in-memory demo databases)
- REST APIs

## Services and ports

| Application | Port | Purpose |
|---|---:|---|
| discovery-server | 8761 | Eureka registry |
| api-gateway | 8080 | Single API entry point |
| auth-service | 8081 | Demo registration/login |
| user-service | 8082 | Customer profiles |
| destination-service | 8083 | Destination catalog |
| booking-service | 8084 | Tour bookings |
| payment-service | 8085 | Demo payment processing |
| notification-service | 8086 | Notification records |

Each service has its own H2 in-memory database. Data is reset when that service restarts.

## Requirements
- JDK 17 (JAVA_HOME configured)
- Maven 3.8+
- Spring Tool Suite 4 / Eclipse with Spring Tools

## Import into STS
1. Extract the ZIP.
2. Open STS.
3. Choose **File → Import → Maven → Existing Maven Projects**.
4. Select the extracted `tourism-management-microservices` folder.
5. Select all discovered projects and finish.
6. Wait for Maven dependencies to download.

Each folder is a separate Maven/Spring Boot application. Run them in this order:
1. `discovery-server` → `DiscoveryServerApplication`
2. `auth-service` → `AuthServiceApplication`
3. `user-service` → `UserServiceApplication`
4. `destination-service` → `DestinationServiceApplication`
5. `booking-service` → `BookingServiceApplication`
6. `payment-service` → `PaymentServiceApplication`
7. `notification-service` → `NotificationServiceApplication`
8. `api-gateway` → `ApiGatewayApplication`

You can also run each project from its folder with `mvn spring-boot:run`.

Open Eureka: http://localhost:8761

## API examples (use API Gateway on port 8080)

### Destinations
- `GET http://localhost:8080/api/destinations`
- `POST http://localhost:8080/api/destinations`
```json
{
  "name": "Goa",
  "country": "India",
  "description": "Beaches, heritage and local cuisine",
  "pricePerPerson": 8500.0,
  "available": true
}
```
- `GET http://localhost:8080/api/destinations/1`
- `PUT http://localhost:8080/api/destinations/1`
- `DELETE http://localhost:8080/api/destinations/1`

### Users
- `POST http://localhost:8080/api/users`
```json
{
  "name": "Asha",
  "email": "asha@example.com",
  "phone": "9876543210"
}
```
- `GET http://localhost:8080/api/users`

### Bookings
- `POST http://localhost:8080/api/bookings`
```json
{
  "userId": 1,
  "destinationId": 1,
  "travelDate": "2026-12-20",
  "numberOfPeople": 2,
  "totalAmount": 17000.0,
  "status": "PENDING"
}
```
- `GET http://localhost:8080/api/bookings`

### Payments (demo only; no real money is charged)
- `POST http://localhost:8080/api/payments`
```json
{
  "bookingId": 1,
  "amount": 17000.0,
  "method": "UPI"
}
```
- `GET http://localhost:8080/api/payments`

### Notifications
- `POST http://localhost:8080/api/notifications`
```json
{
  "userId": 1,
  "message": "Your booking has been received",
  "channel": "EMAIL"
}
```
- `GET http://localhost:8080/api/notifications`

### Auth demo
- `POST http://localhost:8080/api/auth/register`
```json
{"name":"Asha","email":"asha@example.com","password":"change-me"}
```
- `POST http://localhost:8080/api/auth/login`
```json
{"email":"asha@example.com","password":"change-me"}
```
The auth service hashes passwords with BCrypt and returns a demo session token. This starter does **not** enforce token validation at the gateway; add JWT validation/role-based authorization before production use.

## H2 database consoles
For each service, open `http://localhost:<service-port>/h2-console`
- JDBC URL: `jdbc:h2:mem:<service-db>`
- Username: `sa`
- Password: (blank)

Database names:
- user-service: `jdbc:h2:mem:userdb`
- destination-service: `jdbc:h2:mem:destinationdb`
- booking-service: `jdbc:h2:mem:bookingdb`
- payment-service: `jdbc:h2:mem:paymentdb`
- notification-service: `jdbc:h2:mem:notificationdb`
- auth-service: `jdbc:h2:mem:authdb`

## Notes
- This is a complete runnable learning/demo baseline with independent services, REST CRUD, Eureka registration, gateway routing and H2 persistence.
- H2 is in-memory. Configure PostgreSQL/MySQL and persistent volumes for deployment.
- Booking/payment/notification are separate services but this demo does not yet implement distributed transactions, a message broker, retries, or real email/SMS/payment provider integrations.
- For production: add JWT verification at the gateway, role-based access, DTOs, centralized config, tracing, rate limiting, migrations (Flyway), secrets management, tests, Docker/Kubernetes and observability.
