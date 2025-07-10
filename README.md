# SocialSphere

A Java EE web application for social media interactions, built with servlets, JSP, and H2 database.

## 🚀 Features

- User registration and authentication
- Session management with persistent login
- Create and view posts with timestamps
- Comment system for posts
- Real-time social interactions
- Responsive web interface

## 🛠️ Technology Stack

- **Backend**: Java EE, Servlets, JSP
- **Database**: H2 Database
- **Server**: Apache Tomcat
- **Frontend**: HTML, CSS, JavaScript
- **Build Tool**: Maven/Gradle (as applicable)

## 📋 Prerequisites

- Java 8 or higher
- Apache Tomcat 9.0+
- H2 Database
- Web browser

## 🔧 Installation & Setup

### 1. Database Setup

1. Place the JAR file in your SocialSphere project directory
2. Open H2 Console by running:
   ```bash
   java -jar <filename>.jar
   ```
3. Connect to the database using:
   - **JDBC URL**: `jdbc:h2:file:C:/Users/dheer/IdeaProjects/SocialSphere/SocialSphere`
   - This will create a `.mv` file - ensure your connection points to this file (don't copy the `.mv` extension)

### 2. Database Schema

Execute the following SQL commands to set up the database tables:

```sql
-- Clear existing data (if needed)
DELETE FROM COMMENTS;

-- Users Table
CREATE TABLE IF NOT EXISTS Users (
    UserId INT AUTO_INCREMENT PRIMARY KEY,
    UserName VARCHAR(255) NOT NULL,
    UserPassword VARCHAR(255) NOT NULL,
    UserEmail VARCHAR(255) NOT NULL UNIQUE
);

-- Posts Table
CREATE TABLE IF NOT EXISTS Post (
    PostId INT AUTO_INCREMENT PRIMARY KEY,
    PostTitle VARCHAR(255) NOT NULL,
    PostContent TEXT NOT NULL,
    UserId INT NOT NULL,
    Timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserId) REFERENCES Users(UserId) ON DELETE CASCADE
);

-- Comments Table
CREATE TABLE IF NOT EXISTS Comments (
    CommentId INT AUTO_INCREMENT PRIMARY KEY,
    CommentContent TEXT NOT NULL,
    UserId INT NOT NULL,
    PostId INT NOT NULL,
    Timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserId) REFERENCES Users(UserId) ON DELETE CASCADE,
    FOREIGN KEY (PostId) REFERENCES Post(PostId) ON DELETE CASCADE
);
```

### 3. Application Deployment

1. Deploy the WAR file to your Tomcat server
2. Start the Tomcat server
3. Access the application at `http://localhost:8080/SocialSphere`

## 🏗️ Architecture Overview

### Application Structure

- **Servlets**: Handle HTTP requests and responses, acting as controllers
- **JSP Pages**: Render dynamic HTML content with embedded Java code
- **HTML Forms**: Capture user input for login, registration, posts, and comments
- **DAO Pattern**: Encapsulate database operations for each entity

### Key Components

#### 1. Session Management
- **HttpSession**: Maintains user state across requests
- **Session Persistence**: Users remain logged in across browser tabs
- **Logout**: Session invalidation clears user data

#### 2. Database Access
- **JDBC**: Java Database Connectivity for H2 database operations
- **DBUtil**: Utility class for database connection management
- **DAO Classes**: `UserDAOImpl`, `PostDAOImpl`, `CommentDAOImpl` for CRUD operations
- **Connection Handling**: Try-with-resources for automatic resource management

#### 3. Security Features
- **Authentication**: Username/password validation against database
- **Authorization**: Session-based access control for protected resources
- **Registration Validation**: Duplicate username/email prevention

## 📱 Functionality

### User Management
- **Registration**: New user account creation with validation
- **Login**: Secure authentication with session management
- **Profile Management**: User account information handling

### Social Features
- **Posts**: Create and view posts with titles, content, and timestamps
- **Comments**: Add comments to posts with user attribution
- **Timeline**: Chronological display of posts and interactions

### Error Handling
- **User-Friendly Messages**: Clear error communication
- **Database Error Recovery**: Graceful handling of connection issues
- **Input Validation**: Form data verification and sanitization

## 🔒 Security Considerations

- Session-based authentication
- Protected routes require valid sessions
- Database connection security
- Input sanitization to prevent SQL injection

## 📝 Best Practices Implemented

- **Separation of Concerns**: Clean separation between presentation, business logic, and data access
- **Resource Management**: Proper JDBC connection handling
- **Error Handling**: Consistent user feedback for all operations
- **Code Organization**: Modular design with clear responsibilities

## 🚀 Getting Started

1. Clone the repository
2. Set up the H2 database following the installation steps
3. Deploy to your Tomcat server
4. Register a new user account
5. Start creating posts and interacting with the community!

## 📧 Support

For issues or questions, please check the project documentation or contact the development team.

---

**Note**: This application is designed for educational purposes and demonstrates core Java EE web development concepts including servlets, JSP, JDBC, and session management.
