package SocialSphere.servlets;

import SocialSphere.dao.PostDAOImpl;
import SocialSphere.model.Post;
import SocialSphere.model.User;
import SocialSphere.util.DBUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;

@WebServlet("/CreatePostServlet")
public class CreatePostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            res.sendRedirect("StartPage.html");
            return;
        }

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        User user = (User) session.getAttribute("user");

        try (Connection conn = DBUtil.getConnection()) {
            if (conn == null) {
                res.getWriter().println("Database connection error. Please try again later.");
                return;
            }
            PostDAOImpl postDAO = new PostDAOImpl(conn);
            Post post = new Post();
            post.setPostTitle(title);
            post.setPostContent(content);
            post.setUserId(user.getUserId());
            post.setTimestamp(LocalDateTime.now());

            if (postDAO.createPost(post)) {
                res.sendRedirect("index.jsp");
            } else {
                res.getWriter().println("Failed to create post");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
