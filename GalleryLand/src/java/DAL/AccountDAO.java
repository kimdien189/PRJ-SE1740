/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Gallery;

/**
 *
 * @author macbookair
 */
public class AccountDAO extends BaseDAO {

    public Account getAccountByUsernameAndPassword(String username, String password) {
        try {
            Account user = new Account();
            String sql = "select * from UserTBL where username = ? and password = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user.setUser_id(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setDisplayname(rs.getString("displayname"));
                return user;
            }

        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;

    }

    public Account getAccountInfoByID(int user_ID) {
        String sql = "select * from UserTBL where id = ?";
        Account user = new Account();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(user_ID));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int user_id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String displayname = resultSet.getString("displayname");
                user = new Account(user_id, username, password, displayname);
            }
        } catch (SQLException ex) {
            System.err.println("An error occurred while executing the query: " + ex.getMessage());
            ex.printStackTrace();
        }
        return user;
    }

    public String signupToDatabase(Account newUser) {
        try {
            String report = "";
            String username = newUser.getUsername();
            String password = newUser.getPassword();
            String displayname = newUser.getDisplayname();
            String sql = "select * from UserTBL where [username] = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                report = "User already exist";
            } else {
                String sqlAdd = "INSERT INTO UserTBL (username, password, displayname) VALUES (?, ?, ?)";
                PreparedStatement statementAdd = connection.prepareStatement(sqlAdd);
                statementAdd.setString(1, username);
                statementAdd.setString(2, password);
                statementAdd.setString(3, displayname);
                statementAdd.executeUpdate();
                report = "success";
            }
            return report;
        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;

    }

    public String changeInfoDatabase(Account oldUser, Account newUser) {
        try {
            String report = "";
            String usernameOld = oldUser.getUsername();
            String username = newUser.getUsername();
            String password = newUser.getPassword();
            String displayname = newUser.getDisplayname();
            if (usernameOld.equals(username)) {
                String sqlAdd = "UPDATE UserTBL SET username = ?, password = ?, displayname = ? WHERE username = ?";
                PreparedStatement statementAdd = connection.prepareStatement(sqlAdd);
                statementAdd.setString(1, username);
                statementAdd.setString(2, password);
                statementAdd.setString(3, displayname);
                statementAdd.setString(4, usernameOld);
                statementAdd.executeUpdate();
                int rowsUpdated = statementAdd.executeUpdate();
                System.out.println("Rows updated: " + rowsUpdated);
                if (rowsUpdated == 0) {
                    report = "User not found";
                } else {
                    report = "success";
                }
            } else {
                String sql = "select * from UserTBL where [username] = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    report = "User already exist";
                } else {
                    String sqlAdd = "UPDATE UserTBL SET username = ?, password = ?, displayname = ? WHERE username = ?";
                    PreparedStatement statementAdd = connection.prepareStatement(sqlAdd);
                    statementAdd.setString(1, username);
                    statementAdd.setString(2, password);
                    statementAdd.setString(3, displayname);
                    statementAdd.setString(4, usernameOld);
                    statementAdd.executeUpdate();
                    report = "success";
                }
            }
            return report;
        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;

    }
}
