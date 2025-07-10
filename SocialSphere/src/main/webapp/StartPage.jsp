<!DOCTYPE html>
<html>
<head><title>SocialSphere - Login/Register</title></head>
<body>
<% String loginError = request.getParameter("loginError"); %>
<% String registerError = request.getParameter("registerError"); %>
<h2>Login</h2>
<form action="LoginServlet" method="post">
    Username: <input type="text" name="username"><br>
    Password: <input type="password" name="password"><br>
    <input type="submit" value="Login">
</form>
<% if (loginError != null) { %>
    <div style="color:red;">Invalid credentials! Please try again.</div>
<% } %>
<br>
<h2>Register</h2>
<form action="RegisterServlet" method="post">
    Username: <input type="text" name="username"><br>
    Password: <input type="password" name="password"><br>
    Email: <input type="email" name="email"><br>
    <input type="submit" value="Register">
</form>
<% if (registerError != null) { %>
    <div style="color:red;">Username or email already exists. Please login.</div>
<% } %>
</body>
</html>
