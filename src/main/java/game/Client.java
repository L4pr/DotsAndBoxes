package game;

import player.ComputerPlayer;
import player.HumanPlayer;
import player.Player;
import player.RandomPlayer;
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

    private void startLocalGame() {
        Player player1 = createPlayer(ui.playerInfo());
        Player player2 = createPlayer(ui.playerInfo());
        Game game = new Game();
        game.runGame(player1, player2);
    }

    private Player createPlayer(String[] info) {
        String name = info[0];
        boolean AI = info[1].equals("y") || info[1].equals("yes");
        if (AI) {
            String kind = info[2];
            return kind.equals("simple") ? new RandomPlayer(name) : new ComputerPlayer(name);
        }
        return new HumanPlayer(name, ui);
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
