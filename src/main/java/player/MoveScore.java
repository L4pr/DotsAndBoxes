package player;

import game.Board;

public class MoveScore {
    private Board board;
    private int score;

    public MoveScore(Board board, int score) {
        this.board = board;
        this.score = score;
    }

    public Board getBoard() {
        return board;
    }

    public int getScore() {
        return score;
    }
}
