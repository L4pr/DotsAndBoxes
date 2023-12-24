package player;

import game.Game;

abstract class Player {
    private final String name;

    Player(String name) {
        this.name = name;
    }

    public int makeMove(Game game) {
        return determineMove(game);
    }

    abstract int determineMove(Game game);
}
