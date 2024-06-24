package dal;

import model.Rating;
import model.Comment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class commentRatingDAO extends DBContext {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

   

    public void addRating(String productId, String userId, int rating) {
        String sql = "INSERT INTO product_rating (product_id, user_id, rating) VALUES (?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, productId);
            ps.setString(2, userId);
            ps.setInt(3, rating);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addComment(String productId, String userId, String comment) {
        String sql = "INSERT INTO product_comment (product_id, user_id, comment) VALUES (?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, productId);
            ps.setString(2, userId);
            ps.setString(3, comment);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Comment> getCommentsByProductId(String productId) {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT c.*, u.user_name FROM product_comment c JOIN users u ON c.user_id = u.user_id WHERE c.product_id = ? ORDER BY c.created_at DESC";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setProductId(rs.getString("product_id"));
                comment.setUserId(rs.getInt("user_id"));
                comment.setComment(rs.getString("comment"));
                comment.setCreatedAt(rs.getTimestamp("created_at"));
                comments.add(comment);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return comments;
    }

    public List<Rating> getRatingsByProductId(String productId) {
        List<Rating> ratings = new ArrayList<>();
        String sql = "SELECT r.*, u.user_name FROM product_rating r JOIN users u ON r.user_id = u.user_id WHERE r.product_id = ? ORDER BY r.created_at DESC";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Rating rating = new Rating();
                rating.setId(rs.getInt("id"));
                rating.setProductId(rs.getString("product_id"));
                rating.setUserId(rs.getInt("user_id"));
                rating.setRating(rs.getInt("rating"));
                rating.setCreatedAt(rs.getTimestamp("created_at"));
                ratings.add(rating);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ratings;
    }

    public double getAverageRatingForProduct(String productId) {
        String sql = "SELECT AVG(rating) as avg_rating FROM product_rating WHERE product_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, productId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("avg_rating");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
 public boolean hasUserCommented(String productId, String userId){
    String sql = "SELECT COUNT(*) FROM product_comment WHERE product_id = ? AND user_id = ?";
    try {
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, productId);
        ps.setString(2, userId);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }   catch (Exception ex) {
            Logger.getLogger(commentRatingDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        closeConnection();
    }
    return false;
}
 public boolean hasUserRated(String productId, String userId){
    String sql = "SELECT COUNT(*) FROM product_rating WHERE product_id = ? AND user_id = ?";
    try {
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, productId);
        ps.setString(2, userId);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }   catch (Exception ex) {
            Logger.getLogger(commentRatingDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        closeConnection();
    }
    return false;
}

// Helper method to close connection, PreparedStatement, and ResultSet
private void closeConnection() {
    try {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (conn != null) {
            conn.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

 
}
