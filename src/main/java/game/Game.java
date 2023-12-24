package game;

public class Game {

    Board board = new Board(0);

    public Board getBoard() {
        return board;
    }

    public long getValidMoves(Board board) {
        return (~board.getBoard() & 0xFFFFFFFFFFFFFFFL);
    }

    public Boolean isValidMove(int index, Board board) {
        return ((getValidMoves(board) & (1L << (59 - index))) != 0);
    }


}
