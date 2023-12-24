package game;

public class Board {

    // first 30 bits horizontal, next 30 bits horizontal
    private long lines = 0;
    private int player1Points = 0;
    private int player2Points = 0;

    public Board(long board) {
        lines = board;
    }

    public int moveMakesBox(int index) {
        int boxesMade = 0;
        if (index < 30) {

        }
        else {


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

    public String getLineDots(int line) {
        int startIndex = line * 5;
        int endIndex = (line * 5) + 4;
        String output = " .";
        for (int i = startIndex; i <= endIndex; i++) {
            if (getLineOnIndex(i) != 0) {
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
            if (getLineOnIndex(i) != 0) {
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
}
