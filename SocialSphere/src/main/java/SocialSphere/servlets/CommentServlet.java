package SocialSphere.servlets;

import SocialSphere.dao.CommentDAOImpl;
import SocialSphere.model.Comment;
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

@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            res.sendRedirect("StartPage.html");
            return;
        }

        int postId = Integer.parseInt(req.getParameter("postId"));
        String commentText = req.getParameter("comment");
        User user = (User) session.getAttribute("user");

        try (Connection conn = DBUtil.getConnection()) {
            if (conn == null) {
                res.getWriter().println("Database connection error. Please try again later.");
                return;
            }
            CommentDAOImpl commentDAO = new CommentDAOImpl(conn);
            Comment comment = new Comment();
            comment.setPostId(postId);
            comment.setUserId(user.getUserId());
            comment.setCommentContent(commentText);
            comment.setTimestamp(LocalDateTime.now());

            commentDAO.addComment(comment);
            res.sendRedirect("index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}