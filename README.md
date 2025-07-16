# Sweet Shop Management System

A simple Sweet Shop Management System built with **Java**, **Spring Boot**, **H2 Database**, and **TDD** approach.

---

## 📌 Project Overview

This project is part of a coding assessment to show:
- CRUD operations for sweets (Add, View, Delete)
- Search sweets by name, category, or price range
- Purchase sweets (decrease stock) and restock (increase stock)
- Follow **Test-Driven Development (TDD)** — tests first, then code
- **Basic HTML/CSS/JS frontend** (optional) — built with **AI assistance**

---

## ✅ Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA (Hibernate)
- H2 In-Memory Database
- JUnit 5
- MockMvc
- IntelliJ IDEA

---

## ⚙️ How to Run (IntelliJ IDEA)

1️⃣ **Clone this repo**
```bash
git clone https://github.com/popat-modhwadiya/SweetShop.git
cd SweetShop
```

2️⃣ Open in IntelliJ IDEA

Click Open → select the SweetShop folder.

Let Maven import dependencies.

3️⃣ Run the application

Right-click SweetShopApplication.java → click Run.

Or use terminal:

```bash
./mvnw spring-boot:run
```

4️⃣ Access

Frontend URL: http://localhost:8080/index.html

API Base URL: http://localhost:8080/sweets

H2 Console: http://localhost:8080/h2-console

---

## ✅ API Endpoints

| Method | Endpoint                | Description                                     |
|--------|-------------------------|-------------------------------------------------|
| POST   | `/sweets`               | Add a new sweet                                 |
| GET    | `/sweets`               | Get all sweets                                  |
| DELETE | `/sweets/{id}`          | Delete sweet by ID                              |
| GET    | `/sweets/search`        | Search sweets by name, category or price range  |
| POST   | `/sweets/{id}/purchase` | Purchase sweet (reduce stock quantity)          |
| POST   | `/sweets/{id}/restock`  | Restock sweet (increase stock quantity)         |

---

## ✅ Sample Data

When you run the project, it pre-loads **5 sweets** into the database automatically:

| ID   | Name             | Category        | Price | Quantity |
|------|------------------|-----------------|-------|----------|
| 1    | Kaju Katli       | Nut-Based       | 50.0  | 20       |
| 2    | Gajar Halwa      | Vegetable-Based | 30.0  | 15       |
| 3    | Gulab Jamun      | Milk-Based      | 10.0  | 50       |
| 4    | Chocolate Barfi  | Chocolate       | 40.0  | 25       |
| 5    | Candy Floss      | Candy           | 5.0   | 100      |

This data is inserted using the `DataInitializer` class when the app starts.  


