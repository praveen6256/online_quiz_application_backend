# 🧠 Online Quiz Application - Backend

This is the backend of an **Online Quiz Application** developed in **Java** using **JDBC** for database connectivity and **MySQL** for data storage. It supports user authentication, quiz creation, quiz-taking, scoring, and result tracking.

---

## 📌 Features

- 🔐 User registration and login with password hashing
- 📝 Admin quiz management (CRUD for quizzes and questions)
- 🎯 Multiple-choice quiz-taking with instant feedback
- 📊 Score calculation and history tracking
- 🏆 Leaderboard (optional)
- 💾 Persistent data storage with MySQL
- ☑️ Input validation and error handling

---

## 🛠️ Tech Stack

- **Language**: Java (JDK 17+ recommended)
- **Database**: MySQL 8+
- **Database Connectivity**: JDBC
- **Password Security**: SHA-256 with salt (or Bcrypt)
- **Optional UI**: JavaFX or Swing (Frontend not included here)

---

## 🗃️ Database Setup

### 1. Create the Database

Run the SQL script provided in the `/sql` folder or use this:

```sql
CREATE DATABASE IF NOT EXISTS quiz_app_db;
USE quiz_app_db;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL
);

CREATE TABLE quizzes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL
);

CREATE TABLE questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quiz_id INT NOT NULL,
    question_text TEXT NOT NULL,
    FOREIGN KEY (quiz_id) REFERENCES quizzes(id) ON DELETE CASCADE
);

CREATE TABLE options (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question_id INT NOT NULL,
    option_text TEXT NOT NULL,
    is_correct BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE
);

CREATE TABLE attempts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    quiz_id INT NOT NULL,
    score INT NOT NULL,
    attempt_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (quiz_id) REFERENCES quizzes(id)
);

🔧 Project Structure
src/
├── controller/      # Business logic (login, quiz, scoring)
├── dao/             # JDBC operations for each table
├── model/           # Plain Java classes (User, Quiz, Question)
├── utils/           # Utility functions (password hashing, DB connector)
└── Main.java        # Application entry point


Author : PRAVEEN S
