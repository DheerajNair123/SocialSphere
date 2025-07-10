package SocialSphere.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getConnection() {
        int retries = 3;
        while (retries-- > 0) {
            try {
                Class.forName("org.h2.Driver");
                Connection conn = DriverManager.getConnection("jdbc:h2:file:C:/Users/dheer/IdeaProjects/SocialSphere/SocialSphere", "sa", "");
                if (conn != null && !conn.isClosed()) {
                    return conn;
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                // Wait a bit before retrying
                try { Thread.sleep(200); } catch (InterruptedException ignored) {}
            }
        }
        return null;
    }
}