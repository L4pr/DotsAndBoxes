package player;

import game.Game;

import java.util.Random;

public class RandomPlayer extends Player{
    public RandomPlayer(String name) {
        super(name);
    }


    Random random = new Random();

    @Override
    int determineMove(Game game) {
        while (true) {
            long move = game.getValidMoves(game.getBoard()) & (1L << random.nextInt(60));

            for (int i = 0; i < 60; i++) {
                if ((move & (1L << (59 - i))) != 0) {
                    return i;
                }
            }
        }
    }
}
