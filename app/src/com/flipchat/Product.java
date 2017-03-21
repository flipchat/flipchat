package com.flipchat;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Data class representing a product
 *
 * @author Alex Ilea, Jonathan Lucuix-Andre, Kevin Tran, Zain, Virani
 * @version 1.0
 */
public class Product {

    private long pid;
    private String title;
    private String description;
    private BigDecimal price;
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

    /**
     * Custom constructor allowing to create dummy product for easy ArrayList search
     * @param pid
     */
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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

    /**
     * Simple equals method, comparing only on product ID
     *
     * @param obj
     * @return
     */
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
