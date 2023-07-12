package DAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import model.Gallery;

public class GalleryDAO extends BaseDAO {

    private static final String EXCLUDED_IDS_COOKIE_NAME = "excludedIds";

    public List<Gallery> getRandomImages(List<String> excludedIds) throws SQLException {
        String query = generateQuery(excludedIds);
        List<Gallery> images = executeQuery(query);
        return images;
    }

    private String generateQuery(List<String> excludedIds) {
        StringJoiner joiner = new StringJoiner(",");
        excludedIds.forEach(id -> joiner.add("'" + id + "'"));
        String excludedIdsString = joiner.toString();
        String query = "SELECT * FROM images";
        if (!excludedIds.isEmpty()) {
            query += " WHERE id NOT IN (" + excludedIdsString + ")";
        }
        query += " ORDER BY RAND()";
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
}
