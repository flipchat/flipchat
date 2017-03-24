package com.flipchat;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Entry point for Flipchat application
 *
 * Shows a text-based menu to the user and executes database queries
 * according to selected option.
 *
 * @author Alex Ilea, Jonathan Lucuix-Andre, Kevin Tran, Zain, Virani
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {

        Database db = new Database();

        while(true) {
            showMenu();
            int n = readInt();
            long userID = 94;
            long productID;
            long catID;
            ArrayList<Category> categories = db.getCategories();

            switch (n) {
                // Get products for selected category
                case 1:
                    // Get categories
                    showCategoryList(categories);
                    catID = validateCategoryID(categories);
                    showProductList(db.getCategoryProducts(catID));
                    break;
                // Add a new product
                case 2:

                    // Show categories
                    showCategoryList(categories);

                    catID = validateCategoryID(categories);

                    System.out.print("Enter product title: ");
                    String title = readString();

                    System.out.print("Enter product description: ");
                    String desc = readString();

                    BigDecimal price = validateAmount("Enter product price: ");

                    db.addProduct(title, desc, price, userID, catID);
                    System.out.printf("Congrats! %s was added to category %d!", title, catID);
                    break;
                // Get my products (posted by current user)
                case 3:
                    showProductList(db.getUserProducts(userID));
                    break;
                // Place a bid
                case 4:
                    // Get categories
                    showCategoryList(categories);
                    // Get products for selected category
                    catID = validateCategoryID(categories);
                    ArrayList<Product> catProducts = db.getCategoryProducts(catID);
                    showProductList(catProducts);
                    productID = validateProductID(catProducts);
                    BigDecimal bidAmount = validateAmount("Enter a bid for product " + productID + ": ");
                    db.placeBid(userID, productID, bidAmount);
                    System.out.printf("Congrats! You placed %s on product %d", formatCurrency(bidAmount), productID);
                    break;
                // Get product comments
                case 5:
                    // Get product comments
                    ArrayList<Product> commentProducts = db.getCommentProducts();
                    showProductList(commentProducts);
                    productID = validateProductID(commentProducts);
                    showCommentList(db.getComments(productID));
                    break;
                // Exit
                case 6:
                    System.out.println("Bye-bye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid action, please choose an action from 1 to 6");
            }
        }

    }

    /**
     * Helper method to validate category ID.
     * Stay in the loop, until user provides a valid category or exits the app.
     *
     * @param categories
     * @return catID
     */
    public static long validateCategoryID(ArrayList<Category> categories) {
        long catID;
        // Validate category input
        while (true) {
            System.out.print("Enter category ID to select a category: ");
            catID = readInt();
            if (catID > 0 && catID <= categories.size()) {
                return catID;
            }
            System.out.println("\nPlease choose a valid category ID from the table!");
        }
    }

    /**
     * Helper method to validate product ID.
     * Stay in the loop, until user provides a valid product or exits the app.
     *
     * @param products
     * @return productID
     */
    public static long validateProductID(ArrayList<Product> products) {
        long productID;
        // Validate product input
        while (true) {
            System.out.print("Enter product ID to select a product: ");
            productID = readInt();
            Product p = new Product(productID);
            if (productID > 0 && products.contains(p)) {
                return productID;
            }
            System.out.println("\nPlease choose a valid category ID from the table!");
        }
    }


    /**
     * Helper method to validate input amount.
     * Stay in the loop, until user provides a valid amount or exits the app.
     *
     * @param message input prompt
     * @return amount entered
     */
    public static BigDecimal validateAmount(String message) {
        // Validate product input
        while(true) {
            System.out.print(message);
            double priceInput = readDouble();
            if (priceInput > -1) {
                return new BigDecimal(priceInput);
            }
            System.out.println("Please enter a valid amount!");
        }
    }


    public static int readInt() {
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static double readDouble() {
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static String readString() {
        Scanner reader = new Scanner(System.in);
        return reader.nextLine();
    }

    public static void showProductList(ArrayList<Product> products) {
        System.out.println();
        System.out.format("%3s | %-25s | %-35s | %-10s | %-20s\n", "ID", "Product", "Description", "Price", "Expires");
        System.out.println("==============================================================================================");
        for (Product p : products) {
            System.out.format("%3s | %-25s | %-35s | %-10s | %-20s\n", p.getPid(), truncate(p.getTitle(), 25),
                    truncate(p.getDescription(), 35), p.getPrice(), p.getExpiry());
        }
        System.out.println();
    }

    public static void showCategoryList(ArrayList<Category> categories) {
        System.out.println();
        System.out.format("%3s | %-20s\n", "ID", "Category");
        System.out.println("=================================================================");
        for (Category cat : categories) {
            System.out.format("%3d | %-20s\n", cat.getCatID(), cat.getName());
        }
        System.out.println();
    }

    public static void showCommentList(ArrayList<Comment> comments) {
        System.out.println();
        System.out.format("%3s | %-12s | %-30s\n", "ID", "Date", "Comment");
        System.out.println("=================================================================");
        for (Comment c : comments) {
            System.out.format("%3d | %-12s | %-30s\n", c.getCid(), c.getDatetime(), truncate(c.getContent(), 30));
        }
        System.out.println();
    }

    public static void showMenu() {
        System.out.println("\n\nWelcome to Flipchat!");
        System.out.println("========================================");
        System.out.println("1) Show new products");
        System.out.println("2) Add a new product");
        System.out.println("3) Show user products");
        System.out.println("4) Place a bid");
        System.out.println("5) Get product comments");
        System.out.println("6) Exit");
        System.out.print("\nPlease choose an option: ");
    }

    /**
     * Helper method that formats a BigDecimal value to a local currency
     * Used to display prices in correct format
     * @param amount
     * @return string representation of price
     */
    public static String formatCurrency(BigDecimal amount) {
        return NumberFormat.getCurrencyInstance().format(amount);
    }


    /**
     * Truncates a string to fit in the output table
     * @param input
     * @param maxLength
     * @return
     */
    public static String truncate(String input, int maxLength) {
        if (input.length() <= maxLength) {
            return input;
        } else {
            return input.substring(0, maxLength - 3) + "...";
        }
    }

}
