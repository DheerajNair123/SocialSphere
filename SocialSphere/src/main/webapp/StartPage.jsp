<!DOCTYPE html>
<html>
<head>
    <title>SocialSphere - Login/Register</title>
    <link rel="stylesheet" href="css/style.css">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    </head>
<body>
<div class="app-container">
    <div class="app-header">
        <h1>SocialSphere</h1>
        <div class="app-meta">A tiny social demo</div>
    </div>

    <% String loginError = request.getParameter("loginError"); %>
    <% String registerError = request.getParameter("registerError"); %>

    <div class="card">
        <h2>Login</h2>
        <form action="LoginServlet" method="post" class="login-form">
            <div class="form-row">Username: <input type="text" name="username"></div>
            <div class="form-row">Password: <input type="password" name="password"></div>
            <div style="margin-top:8px;"><input class="btn" type="submit" value="Login"></div>
        </form>
        <% if (loginError != null) { %>
            <div style="color:var(--danger); margin-top:8px;">Invalid credentials! Please try again.</div>
        <% } %>
    </div>

    <div class="card">
        <h2>Register</h2>
        <form action="RegisterServlet" method="post" class="register-form">
            <div class="form-row">Username: <input type="text" name="username"></div>
            <div class="form-row">Password: <input type="password" name="password"></div>
            <div class="form-row">Email: <input type="email" name="email"></div>
            <div style="margin-top:8px;"><input class="btn" type="submit" value="Register"></div>
        </form>
        <% if (registerError != null) { %>
            <div style="color:var(--danger); margin-top:8px;">Username or email already exists. Please login.</div>
        <% } %>
    </div>
</div>
</body>
</html>
