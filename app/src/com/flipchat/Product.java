package com.flipchat;

import java.sql.Date;

/**
 * Created by Alister on 2017-03-13.
 */
public class Product {

    private long pid;
    private String title;
    private String description;
    private double price;
    private String image;
    private boolean isSold;
    private Date date;
    private Date expiry;
    private long userID;
    private long catID;
    private long bidID; // winning bid


    public Product() {
        super();
    }

    public Product(long pid) {
        this.pid = pid;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getCatID() {
        return catID;
    }

    public void setCatID(long catID) {
        this.catID = catID;
    }

    public long getBidID() {
        return bidID;
    }

    public void setBidID(long bidID) {
        this.bidID = bidID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        if (this.getPid() == ((Product) obj).pid) {
            return true;
        } else {
            return super.equals(obj);
        }

    }
}
