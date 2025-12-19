# SocialSphere

## 🚀 Overview
SocialSphere is a Java-based backend web application that demonstrates
end-to-end web development using Java EE. The project focuses on
server-side logic, database interaction, and clean architectural design
using Servlets, JSP, and JDBC.

It simulates core social platform features such as user management and
content interaction while emphasizing backend fundamentals over UI-heavy
frameworks.

## 🧠 Problem Statement
Most beginner web projects focus heavily on frontend frameworks while
ignoring how requests are handled, data is stored, and business logic is
structured on the server.

## 💡 Solution
SocialSphere is designed as a backend-centric web application that
handles HTTP requests, processes business logic using Servlets,
persists data using JDBC, and renders responses via JSP.

The project follows a layered architecture to ensure maintainability,
scalability, and clarity of responsibilities.

## 🧩 Architecture
- **Controller Layer:** Java Servlets handle HTTP requests and responses
- **Service / DAO Layer:** Encapsulates database operations using JDBC
- **Database Layer:** H2 relational database for data persistence
- **View Layer:** JSP pages for rendering dynamic content
- **Server:** Apache Tomcat
- **Build Tool:** Maven / Gradle

## 🔄 Request Flow
1. Client sends HTTP request
2. Request is received by a Servlet
3. Servlet processes logic and interacts with DAO layer
4. DAO executes SQL queries on H2 database
5. Response is forwarded to JSP for rendering
6. Final HTML response is sent back to client

## ✨ Features
- User registration and authentication
- Server-side session management
- CRUD operations using JDBC
- MVC-based separation of concerns
- Deployed and tested on Apache Tomcat

## 🛠 Tech Stack
- Java
- Java EE (Servlets, JSP)
- JDBC
- H2 Database
- Apache Tomcat
- HTML, CSS, JavaScript
- Maven / Gradle

## ⚙️ Setup & Run
1. Clone the repository
2. Import project into IntelliJ / Eclipse
3. Configure Apache Tomcat server
4. Run the application on Tomcat
5. Access via browser

## 📈 Learning Outcomes
- Deep understanding of request–response lifecycle
- Hands-on experience with MVC architecture
- Practical use of JDBC and SQL
- Server deployment and debugging using Tomcat

## 📄 License
MIT License
