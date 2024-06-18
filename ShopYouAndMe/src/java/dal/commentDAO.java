package dal;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class commentDAO extends DBContext{

//    public void addComment(String productId, String name, String comment) {
//        try (Connection conn = Database.getConnection()) {
//            String sql = "INSERT INTO comments (product_id, name, comment) VALUES (?, ?, ?)";
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            stmt.setString(1, productId);
//            stmt.setString(2, name);
//            stmt.setString(3, comment);
//            stmt.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<Comment> getCommentsByProductId(String productId) {
//        List<Comment> comments = new ArrayList<>();
//        try (Connection conn = Database.getConnection()) {
//            String sql = "SELECT * FROM comments WHERE product_id = ?";
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            stmt.setString(1, productId);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                Comment comment = new Comment();
//                comment.setName(rs.getString("name"));
//                comment.setText(rs.getString("comment"));
//                comments.add(comment);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return comments;
//    }
}
