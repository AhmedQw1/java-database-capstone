# Smart Clinic Management System

A full-stack web application designed to streamline clinic operations, providing distinct, role-based portals for administrators, doctors, and patients. Built from the ground up with a robust Java Spring Boot backend and a dynamic vanilla JavaScript frontend, this project serves as a comprehensive demonstration of modern software engineering principles, from secure API design to containerization and CI/CD.

---

## ✨ Key Features

This application is divided into three distinct user experiences, each with its own set of functionalities:

### 👨‍⚕️ Admin Portal
* **Secure Login:** Admins have a dedicated, secure login portal.
* **Doctor Management:** Admins can add new doctor profiles to the system, making them available for patient bookings.

### 🩺 Doctor Portal
* **Secure Login:** Doctors can log into their personal dashboard to manage their schedule.
* **Appointment Viewing:** Doctors can view a list of all their scheduled appointments for any selected date, complete with patient details.

### ❤️ Patient Portal
* **Self-Registration & Login:** New patients can create an account and log in securely.
* **Doctor Search:** Patients can search for doctors by name to find the right specialist.
* **View Availability:** After finding a doctor, patients can see their general available time slots for any future date.
* **Appointment Booking:** Patients can book an appointment with a specific doctor on a future date.

---

## 📸 Application Screenshots

Here's a glimpse of the Smart Clinic Management System in action:

#### **Patient Portal**
![Patient Portal](images/Patient%20Portal.png)

#### **Doctor Portal**
![Doctor Portal](images/Doctor%20Portal.png)

#### **Admin Portal**
![Admin Portal](images/Admin%20Portal.png)

---

## 🛠️ Tech Stack & Architecture

This project was built using a modern, industry-standard technology stack to ensure scalability, security, and maintainability.

| Category      | Technology                 | Description                                                                    |
| :------------ | :------------------------- | :----------------------------------------------------------------------------- |
| **Backend** | **Java 21 & Spring Boot** | For building a robust, secure, and scalable RESTful API.                         |
|               | **Spring Security** | Implemented for authentication and role-based authorization using JWTs.          |
|               | **Spring Data JPA & Hibernate** | Used for object-relational mapping (ORM) to interact with the database.         |
| **Frontend** | **HTML5, CSS3, Vanilla JS** | A clean, lightweight, and framework-free frontend for maximum performance.       |
| **Database** | **MySQL** | A reliable relational database for storing all application data.                 |
| **DevOps** | **Docker** | The application is containerized using a multi-stage `Dockerfile` for optimized, lightweight images. |
|               | **GitHub Actions** | A CI/CD workflow is configured to automatically compile the backend on every push and pull request. |

### Architectural Concepts Demonstrated
* **RESTful API Design:** The backend exposes a clean, well-structured set of REST endpoints.
* **JWT-Based Authentication:** User sessions are managed stateless-ly and securely using JSON Web Tokens.
* **Role-Based Access Control (RBAC):** Endpoints are protected based on user roles (Admin, Doctor, Patient).
* **Data Transfer Objects (DTOs):** Used to control the shape of API responses and prevent leaking internal data structures.
* **Dependency Injection:** Leveraged throughout the Spring Boot application to manage components and services.

---

## 🚀 How to Run Locally

To get the project running on your local machine, follow these steps:

**Prerequisites:**
* Java JDK 21
* Maven
* MySQL Server
* Docker (Optional, for containerized deployment)

**1. Clone the Repository**
```bash
git clone [https://github.com/AhmedQw1/java-database-capstone.git](https://github.com/AhmedQw1/java-database-capstone.git)
cd java-database-capstone