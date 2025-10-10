<%@ page import="java.util.*, SocialSphere.dao.*, SocialSphere.model.*, java.sql.*, java.time.format.DateTimeFormatter" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("StartPage.html");
        return;
    }
    String postError = request.getParameter("postError");
    Connection conn = SocialSphere.util.DBUtil.getConnection();
    PostDAOImpl postDAO = new PostDAOImpl(conn);
    CommentDAOImpl commentDAO = new CommentDAOImpl(conn);
    UserDAOImpl userDAO = new UserDAOImpl(conn);
    List<Post> posts = postDAO.getAllPosts();
    int postCount = posts.size();
    int commentCount = 0;
    for (Post post : posts) {
        commentCount += commentDAO.getCommentsByPostId(post.getPostId()).size();
    }
%>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="css/style.css">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    </head>
<body>
<div class="app-container">
    <div class="app-header">
        <h1>SocialSphere</h1>
        <div>
            <span class="app-meta">Welcome, <%= user.getUserName() %>!</span>
            &nbsp;&nbsp;|&nbsp;&nbsp;<a class="logout" href="LogoutServlet">Logout</a>
        </div>
    </div>

    <div class="card">
        <div class="app-meta">Total Posts: <%= postCount %> &nbsp;|&nbsp; Total Comments: <%= commentCount %></div>
        <h3 style="margin-top:12px;">Create a Post</h3>
        <form action="CreatePostServlet" method="post">
            <div class="form-row">Title: <input type="text" name="title"></div>
            <% if (postError != null) { %>
                <div style="color:var(--danger); margin-top:6px;">Post title is required.</div>
            <% } %>
            <div style="margin-top:8px;">Content:<br>
                <textarea name="content" rows="4" cols="40"></textarea>
            </div>
            <div style="margin-top:8px;"><input class="btn" type="submit" value="Post"></div>
        </form>
    </div>

    <h3 style="margin-top:16px;">All Posts</h3>
    <%
        for (Post post : posts) {
            User postUser = userDAO.getUserById(post.getUserId());
            String postUserName = (postUser != null) ? postUser.getUserName() : ("User ID " + post.getUserId());
    %>
        <div class="card">
            <div class="post-title"><b><%= post.getPostTitle() %></b></div>
            <div class="post-meta">by <%= postUserName %> at <%= post.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) %></div>
            <div class="post-content"><%= post.getPostContent() %></div>

            <form action="CommentServlet" method="post" class="comment-form">
                <input type="hidden" name="postId" value="<%= post.getPostId() %>"/>
                <div class="form-row">
                    <input type="text" name="comment" placeholder="Add a comment..." />
                    <input class="btn" type="submit" value="Add" />
                </div>
            </form>

            <div class="comments">
                <div class="app-meta">Comments for this post: <%= commentDAO.getCommentCountByPostId(post.getPostId()) %></div>
                <%
                    List<Comment> comments = commentDAO.getCommentsByPostId(post.getPostId());
                    for (Comment c : comments) {
                        User commentUser = userDAO.getUserById(c.getUserId());
                        String commentUserName = (commentUser != null) ? commentUser.getUserName() : ("User ID " + c.getUserId());
                %>
                    <div class="comment"><small>Comment by <%= commentUserName %></small><div><%= c.getCommentContent() %></div></div>
                <%
                    }
                %>
            </div>
        </div>
    <%
        }
    %>

</div>
</body>
</html>
