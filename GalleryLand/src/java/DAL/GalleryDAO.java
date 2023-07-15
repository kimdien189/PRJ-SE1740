package DAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Gallery;

public class GalleryDAO extends BaseDAO {

    private static final String EXCLUDED_IDS_COOKIE_NAME = "excludedIds";

    public List<Gallery> getRandomImages(List<String> excludedIds) throws SQLException {
        String query = generateQuery(excludedIds);
        List<Gallery> images = executeQuery(query);
        return images;
    }

    public int getImageLike(String imageId) {
        try {
            String sql = "select likes from images where [id] = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, imageId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int likes = rs.getInt("likes");
                return likes;
            }
        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return -1;
    }

    public int incrementLikeCount(String imageId) {
        int likes = getImageLike(imageId);
        try {
            String sql = "UPDATE images SET likes = likes + 1 WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, imageId);
            ResultSet rsAdd = statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(GalleryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return likes;
    }

    public int decrementLikeCount(String imageId) {
        int likes = getImageLike(imageId);
        try {
            String sql = "UPDATE images SET likes = likes - 1 WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, imageId);
            ResultSet rsAdd = statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(GalleryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return likes;
    }

    private String generateQuery(List<String> excludedIds) {
        StringJoiner joiner = new StringJoiner(",");
        excludedIds.forEach(id -> joiner.add("'" + id + "'"));
        String excludedIdsString = joiner.toString();
        String query = "SELECT TOP 60 * FROM images";
        if (!excludedIds.isEmpty()) {
            query += " WHERE id NOT IN (" + excludedIdsString + ")";
        }
        query += " ORDER BY NEWID()";
        return query;
    }

    private List<Gallery> executeQuery(String query) throws SQLException {
        List<Gallery> images = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String url = resultSet.getString("url");
                String name = resultSet.getString("name");
                String creator = resultSet.getString("creator");
                Date dateCreated = resultSet.getDate("dateCreated");
                int likes = resultSet.getInt("likes");
                String tagsString = resultSet.getString("tags");
                boolean[] tagsBooleanArray = new boolean[tagsString.length()];
                for (int i = 0; i < tagsString.length(); i++) {
                    tagsBooleanArray[i] = (tagsString.charAt(i) == '1');
                }
                Gallery image = new Gallery(id, url, name, creator, dateCreated, likes, tagsBooleanArray);
                images.add(image);
            }
        } catch (SQLException ex) {
            System.err.println("An error occurred while executing the query: " + ex.getMessage());
            ex.printStackTrace();
        }

        return images;
    }

    /*
    public static void main(String[] args) {
        try {
            GalleryDAO galleryDAO = new GalleryDAO();
            List<String> excludedIds = new ArrayList<>();
            excludedIds.add("1");
            excludedIds.add("2");
            List<Gallery> images = galleryDAO.getRandomImages(excludedIds);
            for (Gallery image : images) {
                System.out.println(image.getURL());
            }
        } catch (SQLException ex) {
            System.err.println("An error occurred while retrieving images: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
     */
}
