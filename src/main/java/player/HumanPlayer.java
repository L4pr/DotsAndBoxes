package player;

import game.Game;

public class HumanPlayer extends Player {
    public HumanPlayer(String name) {
        super(name);
    }
    @Override
    int determineMove(Game game) {
        return 0;
    }
}
