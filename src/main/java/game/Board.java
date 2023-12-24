package game;

public class Board {

    // first 30 bits horizontal, next 30 bits horizontal
    private long lines = 0;
    public int[] playerPoints = new int[2];

    public Board(long board) {
        lines = board;
    }

    public int moveMakesBox(int index) {
        int boxesMade = 0;
        if (index < 30) {
            if (isLineSet(index - 5) && isLineSet(index + 25) && isLineSet(index + 26)) {
                boxesMade++;
            }
            if (isLineSet(index + 5) && isLineSet(index + 35) && isLineSet(index + 36)) {
                boxesMade++;
            }
        }
        else {
            if (isLineSet(index - 1) && isLineSet(index - 31) && isLineSet(index - 26)) {
                boxesMade++;
            }
            if (isLineSet(index + 1) && isLineSet(index - 30) && isLineSet(index - 25)) {
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
}
