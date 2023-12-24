package game;

import player.Player;

public class Game {

    Board board = new Board(0);
    int current = 0;

    public Board getBoard() {
        return board;
    }

    public long getValidMoves(Board board) {
        return (~board.getBoard() & 0xFFFFFFFFFFFFFFFL);
    }

    public Boolean isValidMove(int index, Board board) {
        return ((getValidMoves(board) & (1L << (59 - index))) != 0);
    }

    public void makeMove(int index) {
        board.playerPoints[current] = board.moveMakesBox(index);
        if (board.moveMakesBox(index) == 0) {
            current = (current == 0) ? 1 : 0;
        }

        board.setLine(index);
    }

    public Boolean isGameOver(Board board) {
        return (getValidMoves(board) == 0);
    }

    public int getWinner() {
        return (board.playerPoints[0] > board.playerPoints[1]) ? 0 : 1;
    }

    public int runGame(Player player0, Player player1) {
        while (!isGameOver(board)) {
            int move = (current == 0) ? player0.makeMove(this) : player1.makeMove(this);
            makeMove(move);
        }

        return getWinner();
    }

    @Override
    public String toString() {
        return board.toString() + "\n" + "player 1: " + board.playerPoints[0] + "\n" + "player 2: " + board.playerPoints[1];
    }
}
