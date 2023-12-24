package game;

import player.ComputerPlayer;
import player.HumanPlayer;
import player.Player;
import ui.GUI;
import ui.GameType;
import ui.TUI;
import ui.UI;

import java.util.Scanner;

public class Client {
    private UI ui;

    public UI getUi() {
        return ui;
    }

    public static void main(String[] args) {
        Client c = new Client();
        c.setUI();
        // online: username, netwerk info
        // local: p1 (bot or human), p2 (bot or human)
        GameType type = c.getUi().determineGameType();
        if (type == GameType.LOCAL) {
            c.startLocalGame();
        } else {
            // online -> later
        }
    }

    void startLocalGame() {
        String[] p1 = ui.playerInfo();
        String[] p2 = ui.playerInfo();
        Player player1 = p1[1].equals("y") || p1[1].equals("yes") ? new ComputerPlayer(p1[0]) : new HumanPlayer(p1[0], ui);
        Player player2 = p2[1].equals("y") || p2[1].equals("yes") ? new ComputerPlayer(p2[0]) : new HumanPlayer(p2[0], ui);
        Game game = new Game();
        game.runGame(player1, player2);
    }


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
