/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Account {

    private int user_id;
    private String username;
    private String password;
    private String displayname;

    public Account() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Account(int user_id, String username, String password, String displayname) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.displayname = displayname;
    }

    public Account(String username, String password, String displayname) {
        this.username = username;
        this.password = password;
        this.displayname = displayname;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
