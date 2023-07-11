/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author kimdi
 */
public class Gallery {

    private String ID;
    private String URL;
    private String name;
    private String creator;
    private Date dateCreated;
    private int likes;
    //1 isColorful;
    //2 isYellow;
    //3 isGreen;
    //4 isBlue;
    //5 isPurple;
    //6 isRed;
    //7 isOrange;
    //8 isPhoto;
    //9 isArt;
    //10 isLandscape;
    //11 isPortrait;
    //12 isNature;
    //13 isHistoric;
    //14 isModern;
    //15 isAbstract;
    private boolean[] tags = new boolean[20];

    public Gallery(String ID, String URL, String name, String creator, Date dateCreated, int likes, boolean[] tags) {
        this.ID = ID;
        this.URL = URL;
        this.name = name;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.likes = likes;
        this.tags = tags;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public boolean[] getTags() {
        return tags;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Gallery{" + "ID=" + ID + ", URL=" + URL + ", name=" + name + ", creator=" + creator + ", dateCreated=" + dateCreated + ", likes=" + likes + ", tags=" + tags + '}';
    }


}
