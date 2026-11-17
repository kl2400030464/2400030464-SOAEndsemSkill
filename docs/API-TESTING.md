# Quick API testing sequence

Start all services as described in README.md, then run these requests in order.

1. Create a destination:
```bash
curl -X POST http://localhost:8080/api/destinations -H "Content-Type: application/json" -d '{"name":"Goa","country":"India","description":"Beaches and heritage","pricePerPerson":8500,"available":true}'
```
2. Create a tourist:
```bash
curl -X POST http://localhost:8080/api/users -H "Content-Type: application/json" -d '{"name":"Asha","email":"asha@example.com","phone":"9876543210"}'
```
3. Create a booking:
```bash
curl -X POST http://localhost:8080/api/bookings -H "Content-Type: application/json" -d '{"userId":1,"destinationId":1,"travelDate":"2026-12-20","numberOfPeople":2,"totalAmount":17000,"status":"PENDING"}'
```
4. Record a demo payment:
```bash
curl -X POST http://localhost:8080/api/payments -H "Content-Type: application/json" -d '{"bookingId":1,"amount":17000,"method":"UPI"}'
```
5. Create a notification record:
```bash
curl -X POST http://localhost:8080/api/notifications -H "Content-Type: application/json" -d '{"userId":1,"message":"Booking received","channel":"EMAIL"}'
```
6. Verify records:
```bash
curl http://localhost:8080/api/destinations
curl http://localhost:8080/api/users
curl http://localhost:8080/api/bookings
curl http://localhost:8080/api/payments
curl http://localhost:8080/api/notifications
```
