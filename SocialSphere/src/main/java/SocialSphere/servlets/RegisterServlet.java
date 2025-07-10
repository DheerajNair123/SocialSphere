package SocialSphere.servlets;

import SocialSphere.dao.UserDAOImpl;
import SocialSphere.model.User;
import SocialSphere.util.DBUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        try (Connection conn = DBUtil.getConnection()) {
            if (conn == null) {
                res.getWriter().println("Database connection error. Please try again later.");
                return;
            }
            UserDAOImpl userDAO = new UserDAOImpl(conn);
            if (userDAO.userExists(username, email)) {
                res.sendRedirect("StartPage.jsp?registerError=1");
                return;
            }
            User user = new User();
            user.setUserName(username);
            user.setUserPassword(password);
            user.setUserEmail(email);
            boolean registered = userDAO.register(user);
            if (registered) {
                res.sendRedirect("StartPage.html"); // Redirect to login page after registration
            } else {
                res.getWriter().println("Registration failed. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            res.getWriter().println("Error: " + e.getMessage());
        }
    }
}
