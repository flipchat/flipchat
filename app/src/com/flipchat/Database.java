package com.flipchat;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

/**
 * Created by Alister on 2017-03-13.
 */
public class Database {

    private Connection conn;

    public Database() {

        try {
            Properties config = new Properties();
            config.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));

            String url = config.getProperty("url");
            String user = config.getProperty("user");
            String pass = config.getProperty("pass");

            this.conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("Make sure you have a config.properties file with correct URL, username and password!");
            e.printStackTrace();
        }

    }

    public ArrayList<Category> getCategories() {

        ArrayList<Category> results = new ArrayList<>();
        String selectSQL = "SELECT * FROM category ORDER BY cat_id";

        try (
                PreparedStatement ps =  this.conn.prepareStatement(selectSQL);
                ResultSet resultSet = ps.executeQuery()
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

    public ArrayList<Product> getCategoryProducts(int catID) {
        ArrayList<Product> results = new ArrayList<>();
        String selectSQL = "SELECT * FROM product WHERE cat_id = ?";

        try (PreparedStatement ps =  this.conn.prepareStatement(selectSQL)) {
            ps.setInt(1, catID);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setPid(resultSet.getLong("p_id"));
                    product.setTitle(resultSet.getString("title"));
                    product.setDescription(resultSet.getString("description"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setImage(resultSet.getString("image"));
                    product.setSold(resultSet.getBoolean("is_sold"));
                    product.setDate(resultSet.getDate("datetime"));
                    product.setExpiry(resultSet.getDate("expiry"));
                    product.setUserID(resultSet.getLong("u_id"));
                    product.setCatID(resultSet.getLong("cat_id"));
                    product.setBidID(resultSet.getLong("bid_id"));
                    results.add(product);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return results;
    }

    public int addProduct(String title, String description, BigDecimal price, long userID, long categoryID) {

        String selectSQL = "INSERT INTO product (title, description, price, image, is_sold, datetime, expiry, u_id, cat_id, bid_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(selectSQL)) {
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setBigDecimal(3, price);
            ps.setString(4, "http://dummyimage.com/250x250.jpg/dddddd/000000");
            ps.setBoolean(5, false);
            ps.setTimestamp(6, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            ps.setTimestamp(7, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            ps.setLong(8, userID);
            ps.setLong(9, categoryID);
            ps.setLong(10, 0);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public ArrayList<Product> getUserProducts(long userID) {
        ArrayList<Product> results = new ArrayList<>();
        String selectSQL = "SELECT * FROM product WHERE u_id = ?";

        try (PreparedStatement ps =  this.conn.prepareStatement(selectSQL)) {
            ps.setLong(1, userID);
            try (ResultSet resultSet = ps.executeQuery();) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setPid(resultSet.getLong("p_id"));
                    product.setTitle(resultSet.getString("title"));
                    product.setDescription(resultSet.getString("description"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setImage(resultSet.getString("image"));
                    product.setSold(resultSet.getBoolean("is_sold"));
                    product.setDate(resultSet.getDate("datetime"));
                    product.setExpiry(resultSet.getDate("expiry"));
                    product.setUserID(resultSet.getLong("u_id"));
                    product.setCatID(resultSet.getLong("cat_id"));
                    product.setBidID(resultSet.getLong("bid_id"));
                    results.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }


}
