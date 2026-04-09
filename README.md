# Distributed E-Commerce Microservices

A scalable, distributed e-commerce backend system built with **Java** and **Spring Boot**, utilizing a microservices architecture. This project demonstrates service discovery and registration using Netflix Eureka.

---

## 🏗️ Architecture & Services

The system is broken down into independent microservices, ensuring high availability and loose coupling:

* **`discovery-service`**: The Eureka Server. It acts as the central registry where all other services register themselves so they can find and communicate with each other dynamically.
* **`order-service`**: Manages customer orders, checkout processes, and communicates with the inventory service.
* **`inventory-service`**: Manages product stock levels and verifies availability before orders are placed.

---

## 🛠️ Tech Stack

* **Language:** Java 
* **Framework:** Spring Boot 
* **Service Discovery:** Spring Cloud Netflix Eureka
* **Build Tool:** Maven

---

## 🚀 Getting Started

To run this project locally, follow these steps:

### Prerequisites
* Java 17 or higher
* Maven or Gradle (depending on your setup)
* An IDE like IntelliJ IDEA

### Installation & Running

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/your-username/your-repo-name.git](https://github.com/your-username/your-repo-name.git)
   cd your-repo-name
