package game;

import ui.GUI;
import ui.TUI;
import ui.UI;

import java.util.Scanner;

public class Client {
    private UI ui;

    private void setUI() {
        System.out.println("Do you prefer a GUI or a TUI?");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equalsIgnoreCase("GUI") && !input.equalsIgnoreCase("TUI")) {
            input = sc.nextLine();
        }
        ui = input.equalsIgnoreCase("gui") ? new GUI(): new TUI();
    }


}
