# Student Management System

A console-based Student Management System developed using Java, OOP, JDBC, and MySQL.

## Features

- Add Student
- View Students
- Search Student by ID
- Update Student
- Delete Student
- MySQL database integration

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- JDBC
- MySQL
- VS Code
- Git & GitHub

## Database

Database Name:
`student_management`

Table:
`students`

### Table Columns

| Column | Type |
|--------|------|
| id | INT |
| name | VARCHAR(100) |
| course | VARCHAR(50) |
| marks | DOUBLE |

## Project Structure

```text
StudentManagementSystem
├── .vscode
│   └── settings.json
├── lib
│   └── mysql-connector-j-26.7.0.jar
├── DBConnection.java
├── Main.java
├── Student.java
└── README.md