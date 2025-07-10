jar file paste it in SocialSphere
open h2 console
  java -jar <filename>.jar  (in terminal)

in console path => jdbc:h2:file:C:/Users/dheer/IdeaProjects/SocialSphere/SocialSphere
it will create .mv file, make sure to make the connection to that file(do not copy .mv)

DELETE FROM COMMENTS;

1. Java EE Web Application Structure
Servlets: Java classes that handle HTTP requests and responses. They act as controllers in your app, processing form data, managing sessions, and interacting with the database.
JSP (JavaServer Pages): Used for rendering dynamic HTML content. JSPs can use Java code (scriptlets) to display data from the backend.
HTML Forms: Used for user input (login, registration, posts, comments). Forms send data to servlets via HTTP POST.

2. Session Management
HttpSession: Each user gets a session object. When a user logs in, their info is stored in the session (session.setAttribute("user", user)).
Session Persistence: As long as the session is active (not logged out or expired), the user remains logged in—even in new tabs.
Logout: Invalidate the session (session.invalidate()) to log the user out everywhere.

3. JDBC and Database Access
JDBC (Java Database Connectivity): Java API for connecting to relational databases (H2 in your case).
DBUtil: Utility class to get a database connection. Uses H2 driver and connection string.
DAO Pattern (Data Access Object): Classes like UserDAOImpl, PostDAOImpl, and CommentDAOImpl encapsulate all database operations (CRUD) for each entity.
Connection Handling: Always check for a valid connection before using it. Use try-with-resources to auto-close connections.

4. User Authentication and Registration
Registration: Checks if username/email already exists before inserting a new user. If exists, shows an error on the registration page.
Login: Authenticates user by checking credentials in the database. If invalid, shows an error on the login page.
Security: Only logged-in users can create posts or comments. All protected pages check for a valid session.

5. Posts and Comments
Create Post: Logged-in users can submit a post (title, content). The post is saved with user ID and timestamp.
View Posts: All posts are displayed in reverse chronological order, showing username, title, content, and date/time.
Comments: Each post has a form to add comments. Comments are saved with user ID, post ID, and timestamp, and displayed under the relevant post.

6. Error Handling and User Experience
Friendly Errors: If login or registration fails, the user stays on the same page and sees a clear error message.
Database Errors: If the database connection fails, a user-friendly message is shown instead of a server error.

7. Deployment and Configuration
Tomcat: Java EE web server that runs your WAR file.
web.xml: (Optional) Used for servlet mappings if not using annotations.
@WebServlet Annotation: Modern way to map servlets to URLs.

8. Best Practices
Separation of Concerns: Servlets handle logic, JSPs handle presentation, DAOs handle data access.
Session Checks: Always check for a valid session on protected pages.
Resource Management: Use try-with-resources for JDBC to avoid leaks.
Consistent Error Handling: Always provide user feedback for errors.
