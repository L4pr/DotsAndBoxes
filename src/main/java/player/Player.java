package player;

import game.Game;

abstract class Player {
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public int makeMove(Game game) {
        return determineMove(game);
    }

    abstract int determineMove(Game game);
}
