<%@ page import="java.util.*, SocialSphere.dao.*, SocialSphere.model.*, java.sql.*, java.time.format.DateTimeFormatter" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("StartPage.html");
        return;
    }
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
<head><title>Home</title></head>
<body>
<h2>Welcome, <%= user.getUserName() %>!</h2>
<a href="LogoutServlet">Logout</a>
<p>Total Posts: <%= postCount %> | Total Comments: <%= commentCount %></p>

<h3>Create a Post</h3>
<form action="CreatePostServlet" method="post">
    Title: <input type="text" name="title"><br>
    Content:<br>
    <textarea name="content" rows="4" cols="40"></textarea><br>
    <input type="submit" value="Post">
</form>
<hr>

<h3>All Posts</h3>
<%
    for (Post post : posts) {
        User postUser = userDAO.getUserById(post.getUserId());
        String postUserName = (postUser != null) ? postUser.getUserName() : ("User ID " + post.getUserId());
        out.println("<div><b>" + post.getPostTitle() + "</b> by " + postUserName + " at " + post.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "<br>" + post.getPostContent() + "</div>");
        out.println("<form action='CommentServlet' method='post'>");
        out.println("<input type='hidden' name='postId' value='" + post.getPostId() + "'/>");
        out.println("Comment: <input type='text' name='comment'/><input type='submit' value='Add Comment'/></form>");

        List<Comment> comments = commentDAO.getCommentsByPostId(post.getPostId());
        for (Comment c : comments) {
            User commentUser = userDAO.getUserById(c.getUserId());
            String commentUserName = (commentUser != null) ? commentUser.getUserName() : ("User ID " + c.getUserId());
            out.println("<div style='margin-left:20px;'>Comment by " + commentUserName + ": " + c.getCommentContent() + "</div>");
        }
        out.println("<hr>");
    }
%>
</body>
</html>
