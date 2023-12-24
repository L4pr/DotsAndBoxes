package ui;

import game.Game;

import java.util.Scanner;

public class TUI implements UI {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int determineMove(Game game) {
        // board toString in makeMove in player?
        System.out.println("Make your next move");
        String input = scanner.next();
        while (!isNumeric(input)) {
            System.out.println("Please type a number");
            input = scanner.next();
        }
        return Integer.parseInt(input);
    }

    private boolean isNumeric(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public GameType determineGameType() {
        System.out.println("Do you want to play local or online?");
        String input = scanner.nextLine();
        while (!input.equals("local") && ! input.equals("online")) {
            System.out.println("Please type either 'local' or 'online'");
            input = scanner.nextLine();
        }
        return input.equals("local") ? GameType.LOCAL: GameType.ONLINE;
    }

    @Override
    public void showBoard(Game game) {
        System.out.println(game.toString());
    }

    @Override
    public String[] playerInfo() {
        System.out.print("Enter your username: ");
        String userName = scanner.nextLine();
        System.out.println("Are you playing as an AI? y/n");
        String isAI = scanner.nextLine();
        while (!isAI.equals("y") && !isAI.equals("yes") && !isAI.equals("n") && !isAI.equals("no")) {
            System.out.println("Are you playing as an AI? Please enter 'yes' or 'no'");
            isAI = scanner.nextLine();
        }
        if (isAI.equals("y") || isAI.equals("yes")) {
            System.out.println("What kind of AI? simple or smart?");
            String kind = scanner.nextLine();
            while (!kind.equals("simple") && !kind.equals("smart")) {
                System.out.println("Please enter 'simple' or 'smart'");
                kind = scanner.nextLine();
            }
            return new String[]{userName, isAI, kind};
        }
        return new String[]{userName, isAI};
    }
}
