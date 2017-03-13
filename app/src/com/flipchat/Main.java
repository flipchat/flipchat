package com.flipchat;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Connection conn = null;

        try {

            Properties config = new Properties();
            config.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));

            String url = config.getProperty("url");
            String user = config.getProperty("user");
            String pass = config.getProperty("pass");

            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("Make sure you have a config.properties file with correct URL, username and password!");
            e.printStackTrace();
        }

        while(true) {
            System.out.println("Please choose an option: ");
            System.out.println("1) Add category");
            System.out.println("2) Add product");
            System.out.println("3) Show categories");
            System.out.println("4) Show products in category");
            System.out.println("5) Bid on a product");
            System.out.println("6) Exit");
            Scanner reader = new Scanner(System.in);
            int n = reader.nextInt();
            switch (n) {
                case 1:
                    // todo
                    break;
                case 2:
                    // todo
                    break;
                case 3:
                        Category c = new Category(conn);
                        ArrayList<Category> categories = c.getCategories();
                        System.out.println("List of categories:");
                        for(Category cat : categories) {
                            System.out.println(cat.getName());
                        }
                    break;
                case 4:
                    // todo
                    break;
                case 5:
                    // todo
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid action, please choose an action from 1 to 6");
            }
        }

    }
}
