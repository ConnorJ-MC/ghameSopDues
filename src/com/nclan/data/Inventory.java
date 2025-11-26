package com.nclan.data;

import java.util.ArrayList;
import java.util.Comparator;

public class Inventory {

    private final ArrayList<Game> allGames;
    private static Inventory inventoryDB;
    public Inventory() {
        allGames = new ArrayList<>();
    }
    public static Inventory getInstance() {
        //check if an instance of the class has already been created.
        if (inventoryDB == null) {
            inventoryDB = new Inventory();
        }
        return inventoryDB;
    }

    public boolean addStock(Game g) {
        for (Game b : allGames) {
            if (g.getName().equals(b.getName()) && g.getConsole().equals(b.getConsole())) {
                b.setQuantity(Math.min(b.getQuantity() + 1, 10));
                return true;
            }
        }
        allGames.add(g);
        return true;
    }

    public boolean addStock(Game g, int amount) {
        for (Game b : allGames) {
            if (g.getName().equals(b.getName()) && g.getConsole().equals(b.getConsole())) {
                b.setQuantity(Math.min(b.getQuantity() + amount, 10));
                return true;
            }
        }
        g.setQuantity(amount);
        allGames.add(g);
        return true;
    }

    public boolean removeStock(Game g) {
        for (Game b : allGames) {
            if (g.getName().equals(b.getName()) && g.getConsole().equals(b.getConsole())) {
                if (b.getQuantity() - 1 > 0) b.setQuantity(b.getQuantity() - 1);
                else allGames.remove(b);
                return true;
            }
        }
        return false;
    }

    public boolean removeStock(Game g, int amount) {
        for (Game b : allGames) {
            if (g.getName().equals(b.getName()) && g.getConsole().equals(b.getConsole())) {
                if (b.getQuantity() - amount > 0) b.setQuantity(b.getQuantity() - amount);
                else allGames.remove(b);
                return true;
            }
        }
        return false;
    }

    public String provideAllGames() {
        allGames.sort(Comparator.comparing(Game::getName).thenComparing(Game::getConsole));
        StringBuilder sb = new StringBuilder();
        for (Game b : allGames) {
            sb.append(b.getName()).append(" on ").append(b.getConsole());
            sb.append(": released in ").append(b.getReleaseYear()).append(": ");
            if (b.getQuantity() == 1) sb.append(b.getQuantity()).append(" copy, selling for £").append(b.getPrice());
            else sb.append(b.getQuantity()).append(" copies, selling for £").append(b.getPrice());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public ArrayList<Game> getAllGames() {
        return allGames;
    }

    public Game individualSearch(Game g) {
        for (Game b : allGames) {
            if (g.getName().equals(b.getName()) && g.getConsole().equals(b.getConsole())) {
                return b;
            }
        }
        return g;
    }
}
