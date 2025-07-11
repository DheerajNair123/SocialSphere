package SocialSphere.dao;

import java.sql.*;
import java.util.*;
import SocialSphere.model.Comment;

public class CommentDAOImpl {
    private Connection conn;

    public CommentDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public boolean addComment(Comment comment) throws SQLException {
        String sql = "INSERT INTO Comments(CommentContent, UserId, PostId, Timestamp) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, comment.getCommentContent());
        ps.setInt(2, comment.getUserId());
        ps.setInt(3, comment.getPostId());
        ps.setTimestamp(4, Timestamp.valueOf(comment.getTimestamp()));
        return ps.executeUpdate() > 0;
    }

    public List<Comment> getCommentsByPostId(int postId) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT * FROM Comments WHERE PostId=? ORDER BY Timestamp";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, postId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Comment comment = new Comment();
            comment.setCommentId(rs.getInt("CommentId"));
            comment.setPostId(rs.getInt("PostId"));
            comment.setUserId(rs.getInt("UserId"));
            comment.setCommentContent(rs.getString("CommentContent"));
            comment.setTimestamp(rs.getTimestamp("Timestamp").toLocalDateTime());
            comments.add(comment);
        }
        return comments;
    }

    public int getCommentCountByPostId(int postId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Comments WHERE PostId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, postId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }
}

