package com.nclan.data;

import java.time.LocalDate;
import java.util.ArrayList;

public class Transaction {
    public String getTransactionType() {
        return transactionType;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public double getGamePrice() {
        return gamePrice;
    }

    private String transactionType;
    private String transactionDate;
    private String customerName;
    private String gameTitle;
    private double gamePrice;
    private final ArrayList<Transaction> allTransactions = new ArrayList<>();

    public Transaction(String transactionType,
                       String transactionDate,
                       String customerName,
                       String gameTitle,
                       double gamePrice) {
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.customerName = customerName;
        this.gameTitle = gameTitle;
        this.gamePrice = gamePrice;
    }

    public Transaction() {
    }

    private static Transaction transactionDB;

    public static Transaction getInstance() {
        //check if an instance of the class has already been created.
        if (transactionDB == null) {
            transactionDB = new Transaction();
        }
        return transactionDB;
    }


    public boolean tradeIn(Game g, Customer c) {
        Inventory inv = Inventory.getInstance();
        Game b = inv.individualSearch(g);

        if (!inv.addStock(g)) return false;
        docTransactions("Trade in", b, c, b.getPrice());
        c.setDiscountEligible(true);
        return true;
    }

    public boolean purchase(Game g, Customer c) {
        Inventory inv = Inventory.getInstance();
        Game b = inv.individualSearch(g);
        double discount = c.isDiscountEligible()
                ? b.getPrice() * 0.90
                : b.getPrice();

        if (!inv.removeStock(b)) return false;
        docTransactions("Purchase", b, c, discount);
        c.setDiscountEligible(false);
        return true;
    }

    private void docTransactions(String type, Game g, Customer c, double price) {
        LocalDate localDate = LocalDate.now();
        String date = localDate.toString();

        Transaction transDoc;
        if (type.equals("Trade in")) {
            transDoc = new Transaction(type, date, c.getName(), g.getName(), g.getPrice());
        } else {
            transDoc = new Transaction(type, date, c.getName(), g.getName(), price);
        }
        allTransactions.add(transDoc);
    }

    public String provideAllTransactions() {
        StringBuilder sb = new StringBuilder();
        for (Transaction t : allTransactions) {
            sb.append(t.getTransactionType());
            sb.append(" at ").append(t.getTransactionDate());
            sb.append(", by ").append(t.getCustomerName()).append(": ");
            sb.append(t.getGameTitle()).append(" for ").append(t.getGamePrice());
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    public boolean managePassword(int p) {
        return p == 3020;
    }
}
