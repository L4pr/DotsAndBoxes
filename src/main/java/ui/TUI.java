package ui;

import game.Game;

import java.util.Scanner;

public class TUI implements UI {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int determineMove(Game game) {
        return 0;
    }

    @Override
    public GameType determineGameType() {
        System.out.println("Do you want to play local or online?");
        String input = scanner.nextLine();
        while (!input.equals("local") && ! input.equals("online")) {
            input = scanner.nextLine();
        }
        return input.equals("local") ? GameType.LOCAL: GameType.ONLINE;
    }
}
