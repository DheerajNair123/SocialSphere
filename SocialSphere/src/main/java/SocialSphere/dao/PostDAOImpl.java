package SocialSphere.dao;


import java.sql.*;
import java.util.*;
import SocialSphere.model.Post;

public class PostDAOImpl {
    private Connection conn;

    public PostDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public boolean createPost(Post post) throws SQLException {
        String sql = "INSERT INTO Post(PostTitle, PostContent, UserId, Timestamp) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, post.getPostTitle());
        ps.setString(2, post.getPostContent());
        ps.setInt(3, post.getUserId());
        ps.setTimestamp(4, Timestamp.valueOf(post.getTimestamp()));
        return ps.executeUpdate() > 0;
    }

    public List<Post> getAllPosts() throws SQLException {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM Post ORDER BY Timestamp DESC";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Post post = new Post();
            post.setPostId(rs.getInt("PostId"));
            post.setPostTitle(rs.getString("PostTitle"));
            post.setPostContent(rs.getString("PostContent"));
            post.setUserId(rs.getInt("UserId"));
            post.setTimestamp(rs.getTimestamp("Timestamp").toLocalDateTime());
            posts.add(post);
        }
        return posts;
    }
}