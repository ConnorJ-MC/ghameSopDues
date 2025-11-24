package com.nclan.CLI;

import com.nclan.data.*;

import java.util.Scanner;


public class CLI {
    private final String[] MainItems = {"Purchase", "Trade in", "display games", "Manager options", "change user", "Exit"};
    private final String[] ManageItems = {"view transactions", "add stock", "remove stock", "back to menu"};
    private Inventory inv;
    private Transaction train;
    private Customer crust;

    Customer currentUser;

    public void mainMenu() {
        inv = Inventory.getInstance();
        train = Transaction.getInstance();
        crust = Customer.getInstance();
        boolean endApp = false;
        logIn();
        do {
            showMenu(MainItems);
            int choice = getUserInput(1, MainItems.length);

            switch (choice) {
                case 1 -> buy();

                case 2 -> sell();

                case 3 -> displayGames();

                case 4 -> manageMenu();

                case 5 -> logIn();

                case 6 -> {
                    System.out.println("bye");
                    endApp = true;
                }

                default -> {
                    System.err.println("select a valid number...");
                }
            }
        } while (!endApp);
    }

    public void logIn() {
        Scanner readIn = new Scanner(System.in);
        String name;
        String address;

        System.out.println("login: ");
        System.out.println("enter your name");
        name = readIn.nextLine();
        if (name.isBlank()) {
            System.err.println("you need to enter your name");
        } else {
            System.out.println("enter your address");
            address = readIn.nextLine();
            if (address.isBlank()) {
                System.err.println("you need to enter your address");
            }

            Customer c = new Customer(name, address, false);
            currentUser = crust.login(c);
        }
    }

    public void displayGames() {
        String allGames = String.valueOf(inv.provideAllGames());
        System.out.println(allGames);
    }

    private void buy() {
        Scanner readIn = new Scanner(System.in);

        do {
//            System.out.println("enter the games name: ");
//            String name = readIn.nextLine();
//            if (name.isBlank()) {
//                System.err.println("you need to enter the games name");
//            } else {
//                System.out.println("what is it played on?");
//                String console = readIn.nextLine();
//                if (console.isBlank()) {
//                    System.err.println("you need to enter a device");
//                } else {
//                    System.out.println("what year was it released in?");
//                    String release = Integer.parseInt(readIn.nextLine());
//                    if (release.isBlank()) {
//                        System.err.println("you need to enter a year");
//                    } else {
//                        System.out.println("what was its price");
//                        String price = Double.parseDouble(readIn.nextLine());
//                        if (price.isBlank()) {
//                            System.err.println("you need to enter a price");
//                        }
//
//                        Game g = new Game (name,
//                                console,
//                                release,
//                                1,
//                                price);
//                        train.purchase(g, currentUser);
//                    }
//                }
//            }
            System.out.println("enter the games name: ");
            String name = readIn.nextLine();
            if (name.isBlank()) {
                System.err.println("you need to enter the games name");
                return;
            }

            System.out.println("what is it played on?");
            String console = readIn.nextLine();
            if (console.isBlank()) {
                System.err.println("you need to enter a device");
                return;
            }
//            System.out.println("when was it released");
//            String releaseIn = readIn.nextLine();
//            if (releaseIn.isBlank() || !releaseIn.matches("-?\\d+")) {
//                System.err.println("you need to enter a valid year");
//                return;
//            }
//            int release = Integer.parseInt(releaseIn);
//
//            System.out.println("what is its price?");
//            String priceIn = readIn.nextLine();
//            if (priceIn.isBlank() || !priceIn.matches("-?\\d+(\\.\\d+)?")) {
//                System.err.println("you need to enter a valid price");
//                return;
//            }
//            double price = Double.parseDouble(priceIn);
            Game g = new Game(name, console, 0, 1, 0);
            if (train.purchase(g, currentUser)) System.out.println("game bought!");
            else System.out.println("game isn't in stock, sorry!");


        } while (YesNoUserResponse("buy another game?"));
    }

    public void sell() {
        Scanner readIn = new Scanner(System.in);

        do {
            System.out.println("enter the games name: ");
            String name = readIn.nextLine();
            if (name.isBlank()) {
                System.err.println("you need to enter the games name");
                return;
            }

            System.out.println("what is it played on?");
            String console = readIn.nextLine();
            if (console.isBlank()) {
                System.err.println("you need to enter a device");
                return;
            }

            System.out.println("when was it released");
            String releaseIn = readIn.nextLine();
            if (releaseIn.isBlank() || !releaseIn.matches("-?\\d+")) {
                System.err.println("you need to enter a valid year");
                return;
            }
            int release = Integer.parseInt(releaseIn);

            System.out.println("what is its price?");
            String priceIn = readIn.nextLine();
            if (priceIn.isBlank() || !priceIn.matches("-?\\d+(\\.\\d+)?")) {
                System.err.println("you need to enter a valid price");
                return;
            }
            double price = Double.parseDouble(priceIn);

            Game g = new Game(name, console, release, 1, price);
            if (train.tradeIn(g, currentUser)) System.out.println("game traded!");

        } while (YesNoUserResponse("sell another game?"));
    }

    private void manageMenu() {
        Scanner readIn = new Scanner(System.in);
        System.out.println("enter manager password:");
        int password = Integer.parseInt(readIn.nextLine());

        if (train.managePassword(password)) {
            showMenu(ManageItems);
            int choice = getUserInput(1, ManageItems.length);

            switch (choice) {
                case 1 -> viewTranscriptions();

                case 2 -> addGame();

                case 3 -> removeGame();

                case 4 -> {
                    int h = 1 + 1;
                    //don't know how to make it go back to the main menu items;
                    //without it making, you log in again
                }

                default -> {
                    System.err.println("select a valid number...");
                }
            }
        } else System.out.println("wrong password");
    }

    private void removeGame() {
        Scanner readIn = new Scanner(System.in);

        do {
            System.out.println("enter the games name: ");
            String name = readIn.nextLine();
            if (name.isBlank()) {
                System.err.println("you need to enter the games name");
                return;
            }

            System.out.println("what is it played on?");
            String console = readIn.nextLine();
            if (console.isBlank()) {
                System.err.println("you need to enter a device");
                return;
            }

            System.out.println("how many copies");
            String quantityIn = readIn.nextLine();
            if (quantityIn.isBlank() || !quantityIn.matches("-?\\d+")) {
                System.err.println("you need to enter a valid amount");
                return;
            }
            int quantity = Integer.parseInt(quantityIn);

            Game g = new Game(name, console, 0, 1, 0);
            if (inv.removeStock(g, quantity)) System.out.println("stock removed!");

        } while (YesNoUserResponse("remove more stock?"));
    }

    private void addGame() {
        Scanner readIn = new Scanner(System.in);

        do {
            System.out.println("enter the games name: ");
            String name = readIn.nextLine();
            if (name.isBlank()) {
                System.err.println("you need to enter the games name");
                return;
            }

            System.out.println("what is it played on?");
            String console = readIn.nextLine();
            if (console.isBlank()) {
                System.err.println("you need to enter a device");
                return;
            }

            System.out.println("when was it released");
            String releaseIn = readIn.nextLine();
            if (releaseIn.isBlank() || !releaseIn.matches("-?\\d+")) {
                System.err.println("you need to enter a valid year");
                return;
            }
            int release = Integer.parseInt(releaseIn);

            System.out.println("how many copies");
            String quantityIn = readIn.nextLine();
            if (quantityIn.isBlank() || !quantityIn.matches("-?\\d+")) {
                System.err.println("you need to enter a valid amount");
                return;
            }
            int quantity = Integer.parseInt(quantityIn);

            System.out.println("what is its price?");
            String priceIn = readIn.nextLine();
            if (priceIn.isBlank() || !priceIn.matches("-?\\d+(\\.\\d+)?")) {
                System.err.println("you need to enter a valid price");
                return;
            }
            double price = Double.parseDouble(priceIn);

            Game g = new Game(name, console, release, 1, price);
            if (inv.addStock(g, quantity)) System.out.println("stock added!");

        } while (YesNoUserResponse("add more stock?"));
    }

    private void viewTranscriptions() {
        String allTransactions = String.valueOf(train.provideAllTransactions());
        System.out.println(allTransactions);
    }

    private boolean YesNoUserResponse(String query) {
        Scanner input = new Scanner(System.in);

        //get the user's response
        System.out.println(query + "\n(yes/no)");
        String response = input.nextLine();

        //if the user never entered a name, then give them a default name
        if (response.isEmpty()) {
            return false;
        } else {
            response = response.toLowerCase();
            return (!response.startsWith("n") && response.startsWith("y"));
        }
    }

    public int getUserInput(int min, int max) {
        int returnChoice = -1;
        boolean keepAsking = true;
        Scanner readIn = new Scanner(System.in);
        {
            do {
                System.out.print(">:");
                try {
                    String option = readIn.nextLine();
                    int number = Integer.parseInt(option);
                    //is the choice within the range?
                    if (number >= min && number <= max) {
                        returnChoice = number;
                        keepAsking = false;
                    } else
                        System.err.printf("Choice must be between %d and %d", min, max);
                } catch (NumberFormatException nfe) {
                    //tell user only numbers are accepted, can't parse letters to numbers!
                    System.err.println("Only numbers are accepted...");
                }
            } while (keepAsking);
            return returnChoice;
        }
    }

    public void showMenu(String[] items) {
        System.out.println("select an option");
        for (int i = 1; i <= items.length; i++)
            System.out.println(i + " " + items[i - 1]);
    }
}