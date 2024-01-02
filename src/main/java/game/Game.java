package game;

import player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Game {

    public Game() {
        this.board = new Board(0);
    }

    public Game(Board board) {
        this.board = board;
    }


    Board board;
    public int current = 0;

    public Board getBoard() {
        return board;
    }

    public long getValidMoves(Board board) {
        return (~board.getBoard() & 0xFFFFFFFFFFFFFFFL);
    }

    public Boolean isValidMove(int index, Board board) {
        return ((getValidMoves(board) & (1L << (59 - index))) != 0);
    }

    public int makeMove(int index) {
        int pointsMade = board.moveMakesBox(index);
        board.playerPoints[current] += pointsMade;

        if (pointsMade == 0) {
            current = (current == 0) ? 1 : 0;
        }

        board.setLine(index);
        return pointsMade;
    }

    public Board makeMove(int index, Board board) {
        int pointsMade = board.moveMakesBox(index);
        board.playerPoints[current] += pointsMade;
        board.setLine(index);
        return board;
    }

    public Boolean isGameOver(Board board) {
        return (getValidMoves(board) == 0);
    }

    public int getWinner() {
        return (board.playerPoints[0] > board.playerPoints[1]) ? 0 : 1;
    }

    public int runGame(Player player0, Player player1) {
        while (true) {
            int move = (current == 0) ? player0.makeMove(this) : player1.makeMove(this);
            makeMove(move);
            System.out.println(this.toString());


            if (isGameOver(board)) {
                System.out.println(this.toString());
                break;
            }
        }

        return getWinner();
    }

    @Override
    public String toString() {
        return board.toString() + "\n" + "player 1: " + board.playerPoints[0] + "\n" + "player 2: " + board.playerPoints[1] + "\n" + "turn: player  " + (this.current + 1);
    }

    public Game getClone() {
        Game game = new Game(board.getClone());
        game.current = this.current;
        return game;
    }

    public List<Game> generateMoves() {
        List<Game> states = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            if (this.isValidMove(i, this.getBoard())) {
                Game game = this.getClone();
                game.makeMove(i);
                states.add(game);
            }
        }

        return states;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Game game = (Game) obj;
        return board.equals(game.getBoard()) &&
                current == game.current;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(board); // Compute hash code for longValue
        result = 31 * result + Integer.hashCode(current); // Combine it with the hash code of the array
        return result;
    }
}
