# Smart Clinic - MySQL Database Schema Design

This document outlines the relational database schema for the Smart Clinic Management System. The design uses five core tables to manage users, doctors, patients, appointments, and prescriptions, ensuring data integrity through foreign key relationships.

---

## ERD (Entity-Relationship Diagram)

```mermaid
erDiagram
    USERS ||--o{ DOCTORS : "is a"
    USERS ||--o{ PATIENTS : "is a"
    DOCTORS ||--|{ APPOINTMENTS : "schedules"
    PATIENTS ||--|{ APPOINTMENTS : "books"
    APPOINTMENTS ||--o{ PRESCRIPTIONS : "results in"

    USERS {
        BIGINT id PK
        VARCHAR(255) email
        VARCHAR(255) password
        VARCHAR(50) role
    }
    DOCTORS {
        BIGINT id PK
        VARCHAR(100) name
        VARCHAR(100) specialty
        BIGINT user_id FK
    }
    PATIENTS {
        BIGINT id PK
        VARCHAR(100) name
        VARCHAR(15) phone_number
        DATE date_of_birth
        BIGINT user_id FK
    }
    APPOINTMENTS {
        BIGINT id PK
        BIGINT doctor_id FK
        BIGINT patient_id FK
        DATETIME appointment_time
        VARCHAR(50) status
        TEXT reason
    }
    PRESCRIPTIONS {
        BIGINT id PK
        BIGINT appointment_id FK
        VARCHAR(255) medication
        VARCHAR(255) dosage
        TEXT notes
        DATE issue_date
    }