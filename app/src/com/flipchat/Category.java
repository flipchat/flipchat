package com.flipchat;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Alister on 2017-03-12.
 */
public class Category {

    private long catID;
    private String name;
    private String description;
    private int productCount;
    private int userCount;
    private Connection conn;

    public Category() {
        super();
    }

    public Category(Connection conn) {
        this.conn = conn;
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

    public ArrayList<Category> getCategories() {

        ArrayList<Category> results = new ArrayList<>();
        String selectSQL = "SELECT * FROM category";

        try (
                PreparedStatement pStatement =  conn.prepareStatement(selectSQL);
                ResultSet resultSet = pStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                Category cat = new Category();
                cat.setCatID(resultSet.getLong("cat_id"));
                cat.setName(resultSet.getString("name"));
                cat.setDescription(resultSet.getString("description"));
                cat.setProductCount(resultSet.getInt("product_count"));
                cat.setUserCount(resultSet.getInt("user_count"));
                results.add(cat);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return results;
    }
}
