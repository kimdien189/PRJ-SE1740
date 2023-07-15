/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

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
}
