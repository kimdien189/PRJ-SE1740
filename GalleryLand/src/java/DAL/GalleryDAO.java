package DAL;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import model.Gallery;

public class GalleryDAO extends BaseDAO {

    private static final String EXCLUDED_IDS_COOKIE_NAME = "excludedIds";

    public List<Gallery> getRandomImages() throws SQLException {
        List<String> excludedIds = getExcludedIdsFromCookie();
        String query = generateQuery(excludedIds);
        List<Gallery> images = executeQuery(query);
        addExcludedIdsToCookie(images);
        return images;
    }

    private List<String> getExcludedIdsFromCookie() {
        List<String> ids = new ArrayList<>();
        HttpServletRequest request = getRequest();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(EXCLUDED_IDS_COOKIE_NAME)) {
                    String[] idStrings = cookie.getValue().split(",");
                    for (String idString : idStrings) {
                        ids.add(idString);
                    }
                    break;
                }
            }
        }
        return ids;
    }

    private String generateQuery(List<String> excludedIds) {
        StringJoiner joiner = new StringJoiner(",");
        excludedIds.forEach(id -> joiner.add(id.toString()));
        String excludedIdsString = joiner.toString();
        String query = String.format("SELECT * FROM images WHERE id NOT IN (%s) ORDER BY RAND() LIMIT 10", excludedIdsString);
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
                Array tagsArray = resultSet.getArray("tags");
                boolean[] tags = (boolean[]) tagsArray.getArray();
                Gallery image = new Gallery(id, url, name, creator, dateCreated, likes, tags);
                images.add(image);
            }
        } catch (Exception ex) {

        }

        return images;
    }

    private void addExcludedIdsToCookie(List<Gallery> images) {
        List<String> excludedIds;
        excludedIds = images.stream().map(gallery -> gallery.getID()).collect(Collectors.toList());
        HttpServletResponse response = getResponse();
        Cookie cookie = new Cookie(EXCLUDED_IDS_COOKIE_NAME, joinExcludedIds(excludedIds));
        cookie.setMaxAge(3600); // 1 hour
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    private String joinExcludedIds(List<String> ids) {
        StringJoiner joiner = new StringJoiner(",");
        for (String id : ids) {
            joiner.add(id.toString());
        }
        return joiner.toString();
    }

    private HttpServletRequest getRequest() {
        return (HttpServletRequest) ThreadLocalServlet.getRequest();
    }

    private HttpServletResponse getResponse() {
        return (HttpServletResponse) ThreadLocalServlet.getResponse();

    }

    private static class ThreadLocalServlet {

        private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<>();
        private static final ThreadLocal<HttpServletResponse> responseHolder = new ThreadLocal<>();

        public static void set(HttpServletRequest request, HttpServletResponse response) {
            requestHolder.set(request);
            responseHolder.set(response);
        }

        public static void unset() {
            requestHolder.remove();
            responseHolder.remove();
        }

        public static HttpServletRequest getRequest() {
            return requestHolder.get();
        }

        public static HttpServletResponse getResponse() {
            return responseHolder.get();
        }
    }
    public static void main(String[] args) {
    try {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ThreadLocalServlet.set(request, response);
        GalleryDAO galleryDAO = new GalleryDAO();
        List<Gallery> images = galleryDAO.getRandomImages();
        for (Gallery image : images) {
            System.out.println(image.getID() + ": " + image.getname());
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {
        ThreadLocalServlet.unset();
    }
}
}
