package com.flipchat;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

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
                    addCategory();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    showCategories();
                    break;
                case 4:
                    showProducts(1);
                    break;
                case 5:
                    placeBid(1, 10.1f);
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid action, please choose an action from 1 to 6");
            }
        }

    }

    public static void addCategory() {

    }

    public static void addProduct() {

    }

    public static Object showCategories() {
        return null;
    }

    public static Object showProducts(int category) {
        return null;

    }

    public static void placeBid(int productID, float amount) {

    }
}
