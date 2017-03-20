package com.flipchat;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

/**
 * Data Access implementation for FlipChat database
 *
 * @author Alex Ilea, Jonathan Lucuix-Andre, Kevin Tran, Zain, Virani
 * @version 1.0
 */
public class Database {

    private Connection conn;

    /**
     * Public constructor.
     *
     * Read database credentials from properties file and
     * attempt to connect to the database.
     */
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

    /**
     * Add a new product to the products table
     *
     * @param title
     * @param description
     * @param price
     * @param userID
     * @param categoryID
     * @return 0 or 1 for success, -1 for failure
     */
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

    /**
     * Return all categories from category table
     *
     * @return categories list
     */
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

    /**
     * Return all products that belong to a specific category
     *
     * @param catID
     * @return product list
     */
    public ArrayList<Product> getCategoryProducts(long catID) {
        ArrayList<Product> results = new ArrayList<>();
        String selectSQL = "SELECT * FROM product WHERE cat_id = ? ORDER BY datetime DESC";

        try (PreparedStatement ps =  this.conn.prepareStatement(selectSQL)) {
            ps.setLong(1, catID);
            this.getProducts(results, ps);
        } catch (SQLException e) {
            System.out.println(e);
        }

        return results;
    }

    /**
     * Return all products that have comments
     *
     * @return product list
     */
    public ArrayList<Product> getCommentProducts() {
        ArrayList<Product> results = new ArrayList<>();
        String selectSQL = "SELECT p.p_id, p.title, p.description, p.price, p.image, p.is_sold, p.datetime, p.expiry, " +
                "p.u_id, p.cat_id, p.bid_id FROM product p, comment c WHERE p.p_id = c.p_id";

        try (PreparedStatement ps =  this.conn.prepareStatement(selectSQL)) {
            this.getProducts(results, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }


    /**
     * Return products posted by a given user
     * @param userID
     * @return product list
     */
    public ArrayList<Product> getUserProducts(long userID) {
        ArrayList<Product> results = new ArrayList<>();
        String selectSQL = "SELECT * FROM product WHERE u_id = ?";

        try (PreparedStatement ps =  this.conn.prepareStatement(selectSQL)) {
            ps.setLong(1, userID);
            this.getProducts(results, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

    /**
     * Return a list of comments for a particular product
     *
     * @param productID
     * @return comment list
     */
    public ArrayList<Comment> getComments(long productID) {
        ArrayList<Comment> results = new ArrayList<>();
        String selectSQL = "SELECT c_id, content, datetime FROM comment WHERE p_id = ?";

        try (PreparedStatement ps =  this.conn.prepareStatement(selectSQL)) {
            ps.setLong(1, productID);
            try (ResultSet resultSet = ps.executeQuery();) {
                while (resultSet.next()) {
                    Comment comment = new Comment();
                    comment.setCid(resultSet.getLong("c_id"));
                    comment.setContent(resultSet.getString("content"));
                    comment.setDatetime(resultSet.getDate("datetime"));
                    results.add(comment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

    /**
     * Add a bid record placed by a specific user on a given product with a specific amont
     *
     * @param userID
     * @param productID
     * @param bidAmount
     * @return 0 or 1 for success, -1 for failure
     */
    public int placeBid(long userID, long productID, BigDecimal bidAmount) {

        String selectSQL = "INSERT INTO bid (datetime, price, u_id, p_id) VALUES (?, ?, ?, ?)";


        try (PreparedStatement ps = conn.prepareStatement(selectSQL)) {
            ps.setTimestamp(1, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            ps.setBigDecimal(2, bidAmount);
            ps.setLong(3, userID);
            ps.setLong(4, productID);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * Build product list based on the results of an executed query.
     * Used internally by Database methods.
     *
     * @param results
     * @param ps
     * @return product list
     * @throws SQLException
     */
    private ArrayList<Product> getProducts(ArrayList<Product> results, PreparedStatement ps) throws SQLException {

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

        return results;
    }

}
