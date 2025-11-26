package com.nclan;

import com.nclan.CLI.CLI;
import com.nclan.data.Game;
import com.nclan.data.Inventory;

/**
 * Class to rune the game shop app
 */
public class Main {
    /**
     *
     *
     * @param args to run the program
     */
    public static void main(String[] args) {
        System.out.println("Simple Library singleton");
        Main app = new Main();

        //load sample date
        app.loadData();

        //uncomment this line to use the CLI
        app.runCLI();
    }


    private void runCLI() {
        CLI interactive = new CLI();
        interactive.mainMenu();
    }

    public void loadData() {
        Inventory inv = Inventory.getInstance();
        Game g1 = new Game("Zelda", "NES", 1987, 11, 20.00);
        Game g2 = new Game("Mario Kart", "SNES", 1992, 3, 30.00);
        Game g3 = new Game("Sonic", "Mega Drive", 1991, 0, 10.75);
        Game g4 = new Game("Street Fighter II", "SNES", 1992, 0, 12.00);
        Game g5 = new Game("Banjo-Kazooie", "N64", 1998, 1, 20.50);

        inv.addStock(g1,11);
        inv.addStock(g2, 3);
        inv.addStock(g3, 6);
        inv.addStock(g4, 9);
        inv.addStock(g5, 1);
    }
}
