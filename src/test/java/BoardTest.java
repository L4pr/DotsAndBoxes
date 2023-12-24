import game.Board;

public class BoardTest {
    public static void main(String[] args) {
        // Create a new Board with an initial state
        long initialState = 0x0L;
        Board board = new Board(initialState);

        // Optionally set some lines
        board.setLine(0);
        board.setLine(1);
        board.setLine(5);
        board.setLine(6);
        board.setLine(31);
        board.setLine(32);

        System.out.println(board.moveMakesBox(30));


        // Print the board
        System.out.println(board.toString());
    }
}

