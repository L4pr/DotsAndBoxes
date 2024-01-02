import game.Board;

public class BoardTest {
    public static void main(String[] args) {
        // Create a new Board with an initial state
        long initialState = 0x5L;
        Board board = new Board(initialState);
        Board board2 = new Board(initialState);
        // Optionally set some lines
//        for (int i = 0; i < 60; i++) {
//            if (i != 31) {
//                board.setLine(i);
//            }
//        }

//        board.setLine(0);
//        board.setLine(1);
//        board.setLine(5);
//        board.setLine(6);
//        board.setLine(31);
//        board.setLine(32);


        System.out.println(board == board2);

        // Print the board
        System.out.println(board.toString());

        System.out.println((~board.getBoard() & 0xFFFFFFFFFFFFFFFL) == 0);

        System.out.println(board.moveMakesBox(1));



    }

}

