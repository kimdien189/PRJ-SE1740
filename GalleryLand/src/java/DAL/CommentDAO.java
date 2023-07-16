/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Comment;

/**
 *
 * @author kimdi
 */
public class CommentDAO extends BaseDAO {

    public List<Comment> getCommentsByID(int image_ID) {
        String sql = "select * from CommentTBL where image_id = ?";
        List<Comment> comments = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(image_ID));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int comment_id = resultSet.getInt("comment_id");
                int user_id = resultSet.getInt("user_id");
                int image_id = resultSet.getInt("image_id");
                String comment_text = resultSet.getString("comment_text");
                Date comment_date = resultSet.getDate("comment_date");
                Comment comment = new Comment(comment_id, user_id, image_id, comment_text, comment_date);
                comments.add(comment);
            }
        } catch (SQLException ex) {
            System.err.println("An error occurred while executing the query: " + ex.getMessage());
            ex.printStackTrace();
        }

        return comments;
    }
    public static void main(String[] args) {
    CommentDAO commentDAO = new CommentDAO();
    List<Comment> comments = commentDAO.getCommentsByID(1); // Replace 1 with the image ID that you want to retrieve comments for
    for (Comment comment : comments) {
        System.out.println(comment.getComment_text());
    }
}
}
