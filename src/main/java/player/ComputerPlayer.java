package player;

import game.Game;

public class ComputerPlayer extends Player {

    public ComputerPlayer(String name) {
        super(name);
    }
    @Override
    int determineMove(Game game) {
        return 0;
    }
}
