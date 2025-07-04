# 🚀 TreeBars Backend – Spring Boot API for Fitness App

This is the backend of **TreeBars**, a full stack fitness application. It provides secure RESTful APIs for user authentication, workout routine management, and muscle zone tracking. Built with Java 17 and Spring Boot 3, it includes JWT-based security and full MySQL database integration.

---

## ✅ Features

- 🔐 User registration and login with JWT token
- Role-based authorization (`ROLE_USER`, `ROLE_ADMIN`)
- Password encryption with BCrypt
- Full CRUD for workout routines
- Assign exercises to routines by day and muscle group
- Protect API endpoints with token verification
- Connection with React frontend via Axios
- CORS configured for production (Render frontend)

---

## 🛠 Technologies Used

- Java 17
- Spring Boot 3
- Spring Security
- JWT (JSON Web Token)
- MySQL + JPA + Hibernate
- Maven
- Render (for deployment)

---

## 🔗 Live Backend API

- 🌐 [Backend API on Render](https://treebars-backend-666.onrender.com)

- 🔗 Frontend: [`treebars-frontend`](https://github.com/Colin252/treebars-frontend)

---

## ⚙️ How to Run Locally

1. Clone this repository:
```bash
2.Configure your application.properties file with your MySQL credentials:

git clone https://github.com/Colin252/treebars-backend.git
spring.datasource.url=jdbc:mysql://localhost:3306/treebars
spring.datasource.username=your_user
spring.datasource.password=your_password

3../mvnw spring-boot:run

👨‍💻 Developed by
Helton Emerson Quiroz López
📧 heltonquiroz@gmail.com
📱 +506 61730792 | 🇨🇷 Costa Rica
Full Stack Developer – Java & React



