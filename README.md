
# 🛒 GadgetBay Microservices System

This is a microservices-based e-commerce system built with **Spring Boot**, **Docker**, **Kafka**, **MongoDB**, **MySQL**, **Prometheus**, **Grafana**, **Loki**, and **Resilience4j**. The system is composed of the following services:

- API Gateway
- Product Service
- Order Service
- Inventory Service
- Notification Service

---

## 📌 Architecture Overview

![Architecture](https://github.com/user-attachments/assets/457b32af-88cf-4434-834c-b193b3194633)

### Key Features

- **Microservice Architecture** with Docker
- **Resilience4j** for circuit breaker patterns
- **Kafka** for async communication
- **MongoDB** for NoSQL data (Product Service)
- **MySQL** for relational data (Order, Inventory)
- **API Gateway** for routing requests
- **Prometheus + Grafana + Loki** for monitoring & logging ( Currently Working on it)
- **Dockerized** for easy deployment

---

## 🧩 Microservices Breakdown

### 1. 🔀 API Gateway
- Routes all client requests to corresponding services.
- Integrated with **Resilience4j** for fault tolerance.

### 2. 📦 Product Service
- Manages product catalog.
- Uses **MongoDB**.
- Provides endpoints to add, view, and list products.

### 3. 🧾 Order Service
- Manages order creation and processing.
- Uses **MySQL**.
- Communicates synchronously with **Inventory Service** and asynchronously with **Notification Service** using **Kafka**.

### 4. 🏷️ Inventory Service
- Manages stock levels.
- Uses **MySQL**.
- Provides stock validation for orders.
- Uses **Resilience4j** for failover and circuit breaker.

### 5. ✉️ Notification Service
- Sends notifications (eemails).
- Consumes Kafka events from **Order Service**.

---

## 🔧 Technologies Used

| Tech             | Description                            |
|------------------|----------------------------------------|
| Spring Boot      | Java microservice framework            |
| Spring Cloud     | API Gateway, Resilience4j, Config      |
| Kafka            | Async messaging between services       |
| MongoDB          | NoSQL database for Product Service     |
| MySQL            | Relational DB for Order & Inventory    |
| Docker           | Containerization                       |
| Prometheus       | Monitoring metrics                     |
| Grafana          | Visualization dashboard                |
| Loki             | Logging system                         |

---



