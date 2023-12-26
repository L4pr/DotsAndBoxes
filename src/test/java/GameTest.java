import game.Board;
import game.Game;
import player.RandomPlayer;

public class GameTest {

    public static void main(String[] args) {
        Game game = new Game();
        game.runGame(new RandomPlayer("player1"), new RandomPlayer("player2"));

    }

}
