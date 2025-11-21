package com.nclan.data;

public class Game {
    //    private static int id = 0;
    private String name;
    private String console;
    //    private int gameID;
    private int releaseYear;
    private int quantity;
    private double price;

//    public static int getGameID() {
//        return ++id;
//    }
//    public static void resetID() {
//        id = 0;
//    }

    public Game(String name, String console, int releaseYear, int quantity, double price) {
        this.name = name;
        this.console = console;
//        gameID = getGameID();
        this.releaseYear = releaseYear;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getConsole() {
        return console;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = Math.min(quantity, 10);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
