import game.Board;

public class BoardTest {
    public static void main(String[] args) {
        // Create a new Board with an initial state
        long initialState = 0x0L;
        Board board = new Board(initialState);

        // Optionally set some lines
        board.setLine(3); // Set a horizontal line
        board.setLine(35); // Set a vertical line

        // Print the board
        System.out.println(board.toString());
    }
}

