package com.flipchat;

/**
 * Created by Alister on 2017-03-12.
 */
public class Category {

    private long catID;
    private String name;
    private String description;
    private int productCount;
    private int userCount;

    public Category() {
        super();
    }

    public long getCatID() {
        return catID;
    }

    public void setCatID(long catID) {
        this.catID = catID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }


}
