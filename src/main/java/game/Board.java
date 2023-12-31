package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Board {

    // first 30 bits horizontal, next 30 bits horizontal
    private long lines = 0;
    public int[] playerPoints = new int[2];

    public Board(long board) {
        lines = board;
    }

    public Board getClone() {
        Board cloneBoard = new Board(lines);
        cloneBoard.playerPoints[0] = this.playerPoints[0];
        cloneBoard.playerPoints[1] = this.playerPoints[1];
        return cloneBoard;
    }

    public int moveMakesBox(int index) {
        int boxesMade = 0;
        if (index < 30) {
//            System.out.println(index + " " + (index >= 5) + " " + (index - 5) + " " + (index + 24 + ((index) / 5)) + " " + (index + 25 + ((index) / 5)));
            if (index >= 5 && isLineSet(index - 5) && isLineSet(index + 24 + ((index) / 5)) &&
                    isLineSet(index + 25 + ((index) / 5))) {
                boxesMade++;
            }
//            System.out.println(index + " " + (index <= 24) + " " + (index + 5) + " " + (index + 30 + ((index) / 5)) + " " + (index + 31 + ((index) / 5)));
            if (index <= 24 && isLineSet(index + 5) && isLineSet(index + 30 + ((index) / 5)) &&
                    isLineSet(index + 31 + ((index) / 5))) {
                boxesMade++;
            }
        }
        else {
//            System.out.println(index + " " + (index % 6 != 0) + " " + (index - 1) + " " + (index - 31 - ((index - 30) / 6)) + " " + (index - 26 - ((index - 30) / 6)));
            if (index % 6 != 0 && isLineSet(index - 1) && isLineSet(index - 31 - ((index - 30) / 6)) &&
                    isLineSet(index - 26 - ((index - 30) / 6))) {
                boxesMade++;
            }
//            System.out.println(index + " " + ((index - 5) % 6 != 0) + " " + (index + 1) + " " + (index - 30 - ((index - 30) / 6)) + " " + (index - 25 - ((index - 30) / 6)));
            if ((index - 5) % 6 != 0 && isLineSet(index + 1) && isLineSet(index - 30 - ((index - 30) / 6)) &&
                    isLineSet(index - 25 - ((index - 30) / 6))) {
                boxesMade++;
            }
        }
        return boxesMade;
    }

    private boolean isLineSet(int index) {
        return (lines & (1L << (59 - index))) != 0;
    }

    public void setLine(int index) {
        lines = lines | (1L << (59 - index));
    }

    public long getBoard() {
        return lines;
    }

    public long getLineOnIndex(int index) {
        return lines & (1L << (59 - index));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Board board = (Board) obj;
        return lines == board.lines &&
                Arrays.equals(playerPoints, board.playerPoints);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(lines); // Compute hash code for longValue
        result = 31 * result + Arrays.hashCode(playerPoints); // Combine it with the hash code of the array
        return result;
    }

    //following methods are for the toString method
    public String getLineDots(int line) {
        int startIndex = line * 5;
        int endIndex = (line * 5) + 4;
        String output = " .";
        for (int i = startIndex; i <= endIndex; i++) {
            if (isLineSet(i)) {
                output += "_.";
            }
            else {
                output += " .";
            }
        }
        return output;
    }

    public String getLineVerticals(int line) {
        int startIndex = (line * 6) + 30;
        int endIndex = (line * 6) + 35;
        String output = " ";
        for (int i = startIndex; i <= endIndex; i++) {
            if (isLineSet(i)) {
                output += "| ";
            }
            else {
                output += "  ";
            }
        }
        return output;
    }

    @Override
    public String toString() {
        String output = "";
        output += getLineDots(0) + "\n";
        output += getLineVerticals(0) + "\n";
        output += getLineDots(1) + "\n";
        output += getLineVerticals(1) + "\n";
        output += getLineDots(2) + "\n";
        output += getLineVerticals(2) + "\n";
        output += getLineDots(3) + "\n";
        output += getLineVerticals(3) + "\n";
        output += getLineDots(4) + "\n";
        output += getLineVerticals(4) + "\n";
        output += getLineDots(5) + "\n";

        return output;
    }

    public int evaluate(int maxPlayer) {
        return (maxPlayer == 1) ? playerPoints[1] - playerPoints[0] : playerPoints[0] - playerPoints[1];
    }




    public List<Board> generateMoves() {
        Game game = new Game();
        List<Board> boards = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            if (game.isValidMove(i, this)) {
                boards.add(game.makeMove(i, this.getClone()));
            }
        }

        return boards;
    }
}
