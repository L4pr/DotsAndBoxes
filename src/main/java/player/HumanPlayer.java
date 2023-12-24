package player;

import game.Game;
import ui.UI;

public class HumanPlayer extends Player {
    private final UI ui;
    public HumanPlayer(String name, UI ui) {
        super(name);
        this.ui = ui;
    }
    @Override
    int determineMove(Game game) {

        int move = ui.determineMove(game);
        while (!game.isValidMove(move, game.getBoard())) {
            move = ui.determineMove(game);
        }
        return move;
    }
}
