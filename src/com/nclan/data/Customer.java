package com.nclan.data;

import java.util.ArrayList;

public class Customer {
//    private static int id = 0;
    private String name;
    private String address;
//    private int customerID;
    private boolean discountEligible;
    private final ArrayList<Customer> customers = new ArrayList<>();
    public Customer(String name, String address, boolean discountEligible) {
        this.name = name;
        this.address = address;
//        customerID = getCustomerID();
        this.discountEligible = discountEligible;
    }
    private static Customer customerDB;
    public Customer() {

    }
    public static Customer getInstance() {
        //check if an instance of the class has already been created.
        if (customerDB == null) {
            customerDB = new Customer();
        }
        return customerDB;
    }
//    public static void resetID() {
//        id = 0;
//    }
//    public static int getCustomerID() {
//        return ++id;
//    }
//    public int getId() {
//        return customerID;
//    }
    public String getAddress() {
        return address;
    }
    public String getName() {
        return name;
    }
    public boolean isDiscountEligible() {
        return discountEligible;
    }
    public void setDiscountEligible(boolean eligible) {
        this.discountEligible = eligible;
    }
//    public String getFormattedUser() {
//        String formatId = String.format("%03d", customerID);
//        return getName() + ", (" + formatId + ")";
//    }

    public Customer login(Customer c) {
        for (Customer b : customers) {
            if (c.getName().equals(b.getName()) && c.getAddress().equals(b.getAddress())) {
//                --id; //if it doesn't work, just remove
                return b;
            }
        }
        customers.add(c);
        return c;
    }

}

