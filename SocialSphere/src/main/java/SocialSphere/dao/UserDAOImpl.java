package SocialSphere.dao;

import SocialSphere.model.User;
import java.sql.*;

public class UserDAOImpl {
    private Connection conn;

    public UserDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public boolean register(User user) throws SQLException {
        if (userExists(user.getUserName(), user.getUserEmail())) {
            return false;
        }
        String sql = "INSERT INTO Users(UserName, UserPassword, UserEmail) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getUserName());
        ps.setString(2, user.getUserPassword());
        ps.setString(3, user.getUserEmail());
        return ps.executeUpdate() > 0;
    }

    public boolean userExists(String username, String email) throws SQLException {
        String sql = "SELECT 1 FROM Users WHERE UserName=? OR UserEmail=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, email);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public User getUserById(int userId) throws SQLException {
        String sql = "SELECT * FROM Users WHERE UserId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            User user = new User();
            user.setUserId(rs.getInt("UserId"));
            user.setUserName(rs.getString("UserName"));
            user.setUserEmail(rs.getString("UserEmail"));
            return user;
        }
        return null;
    }

    public User login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM Users WHERE UserName=? AND UserPassword=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            User user = new User();
            user.setUserId(rs.getInt("UserId"));
            user.setUserName(rs.getString("UserName"));
            user.setUserEmail(rs.getString("UserEmail"));
            return user;
        }
        return null;
    }
}
