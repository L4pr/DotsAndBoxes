package player;

import game.Board;
import game.Game;

import java.util.HashMap;

public class ComputerPlayer2 extends Player {

    HashMap<Board, Integer> transpositionTable = new HashMap<>();

    public ComputerPlayer2(String name) {
        super(name);
    }
    @Override
    int determineMove(Game game) {
        Game game2 = game.getClone();
        for (int i = 0; i < 60; i++) {
            if (game2.isValidMove(i, game2.getBoard())) {
                if (game2.getBoard().moveMakesBox(i) != 0) {
                    return i;
                }
            }
        }



        return new RandomPlayer("dummy").determineMove(game);
    }
}
