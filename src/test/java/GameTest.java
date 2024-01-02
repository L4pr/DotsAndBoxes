import game.Board;
import game.Game;
import player.*;

public class GameTest {

    public static void main(String[] args) {
        int scorePlayer1 = 0;
        int scorePlayer2 = 0;


        Game game;

        for (int i = 0; i < 100; i++) {
            game = new Game();
            int result = game.runGame(new ComputerPlayer2("player1"), new ComputerPlayer4("player2"));
            if (result == 0) {
                scorePlayer1 += 1;
            } else {
                scorePlayer2 += 1;
            }
        }

        System.out.println("Score player 1: " + scorePlayer1);
        System.out.println("Score player 2: " + scorePlayer2);
    }

}
