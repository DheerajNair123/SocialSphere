package SocialSphere.servlets;

import SocialSphere.dao.UserDAOImpl;
import SocialSphere.model.User;
import SocialSphere.util.DBUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.*;
import java.sql.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try (Connection conn = DBUtil.getConnection()) {
            if (conn == null) {
                res.getWriter().println("Database connection error. Please try again later.");
                return;
            }
            UserDAOImpl userDAO = new UserDAOImpl(conn);
            User user = userDAO.login(username, password);

            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                res.sendRedirect("index.jsp"); // Redirect to main page after login
            } else {
                res.sendRedirect("StartPage.jsp?loginError=0");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
