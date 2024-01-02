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
            int moveIndex = random.nextInt(60);

            if (game.isValidMove(moveIndex, game.getBoard())) {
                return moveIndex;
            }
        }
    }
}
