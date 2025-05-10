# 🚀 Spring Boot Microservices Application

## Overview
This project is designed using **Spring Boot microservices architecture**, ensuring modularity, scalability, and flexibility.

## Features
✅ **Service Discovery**: Managed via **Netflix Eureka Server** for dynamic service registration.  
✅ **API Gateway**: Routes requests and secures endpoints using **Spring Cloud Gateway**.  
✅ **Authentication & Security**: All API requests are secured using **Spring Security** and **JWT Tokens**.  

## Microservices 🏗️
- **Customer Service** 🧑‍💼 → Manages user data and authentication.
- **Restaurant Service** 🍽 → Manages restaurant data, menus, and availability.
- **Order Service** 📦 → Handles order placements and status tracking.
- **Delivery Service** 🚚 → Manages delivery logistics and tracking.
- **Payment Service** 💳 → Processes payments and transactions securely.


## 🛠️ Technologies Used
- **Spring Boot**
- **Spring Cloud (Eureka, Gateway, Config)**
- **Spring Security & JWT**
- **Spring Data JPA & Hibernate**
- **MySQL**

## 🌐 Microservices Architecture
Each microservice is **independent** and communicates via **REST APIs**.

---

## 🚀 How to Run

### 1️⃣ Clone the repository:
```bash
git clone https://github.com/your-repo/spring-boot-microservices.git
cd spring-boot-microservices
mvn spring-boot:run -pl eureka-server
mvn spring-boot:run -pl api-gateway

mvn spring-boot:run -pl customer-service
mvn spring-boot:run -pl order-service
mvn spring-boot:run -pl delivery-service
mvn spring-boot:run -pl payment-service
mvn spring-boot:run -pl restaurant-service

