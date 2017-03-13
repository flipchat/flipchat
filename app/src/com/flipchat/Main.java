package com.flipchat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Database db = new Database();

        while(true) {
            showMenu();
            int n = readInt();
            switch (n) {
                case 1:
                    // Get categories
                    showCategoryList(db.getCategories());
                    // Get products for selected category
                    System.out.print("Enter category ID to select a category: ");
                    showProductList(db.getCategoryProducts(readInt()));
                    break;
                case 2:
                    // Add a new product
                    System.out.print("Enter product title: ");
                    String title = readString();
                    System.out.print("Enter product description: ");
                    String desc = readString();
                    System.out.print("Enter product price: ");
                    BigDecimal price = new BigDecimal(readDouble());
                    System.out.print("Enter user id: ");
                    long userID = readInt();
                    System.out.print("Enter categoty id: ");
                    long catID = readInt();
                    db.addProduct(title, desc, price, userID, catID);
                    break;
                case 3:
                    // Get my products (posted by me)
                    showProductList(db.getUserProducts(94));
                    break;
                case 4:
                    // Place a bid
                    break;
                case 5:
                    // Get product comments
                    break;
                case 6:
                    System.out.println("Bye-bye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid action, please choose an action from 1 to 6");
            }
        }

    }

    public static int readInt() {
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static double readDouble() {
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public static String readString() {
        Scanner reader = new Scanner(System.in);
        return reader.nextLine();
    }

    public static void showProductList(ArrayList<Product> products) {
        System.out.format("%3s | %-20s | %-20s | %-20s\n", "ID", "Product", "Price", "Expires");
        System.out.println("=================================================================");
        for (Product p : products) {
            System.out.format("%3s | %-20s | %-20s | %-20s\n", p.getPid(), p.getTitle(), p.getPrice(), p.getExpiry());
        }
    }

    public static void showCategoryList(ArrayList<Category> categories) {
        System.out.format("%3s | %-20s\n", "ID", "Category");
        System.out.println("=================================================================");
        for (Category cat : categories) {
            System.out.format("%3d | %-20s\n", cat.getCatID(), cat.getName());
        }
    }

    public static void showMenu() {
        System.out.println("\nWelcome to Flipchat!");
        System.out.println("========================================");
        System.out.println("1) Show new products");
        System.out.println("2) Add a new product");
        System.out.println("3) Show user products");
        System.out.println("4) Place a bid");
        System.out.println("5) Get product comments");
        System.out.println("6) Exit");
        System.out.print("Please choose an option: ");
    }


}
