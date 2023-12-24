package game;

public class Board {

    // first 30 bits horizontal, next 30 bits horizontal
    private long lines = 0;
    private int player1Points = 0;
    private int player2Points = 0;

    public Board(long board) {
        lines = board;
    }


    public void setLine(int index) {
        lines = lines & (1L << (59 - index));
    }

    public long getBoard() {
        return lines;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int row = 0; row < 6; row++) {
            // Print dots and horizontal lines
            for (int col = 0; col < 5; col++) {
                builder.append(".");
                if ((lines & (1L << (59 - (row * 5 + col)))) != 0) {
                    builder.append("-");
                } else {
                    builder.append(" ");
                }
            }
            builder.append(".\n");

            // Print vertical lines if not the last row
            if (row < 5) {
                for (int col = 0; col < 6; col++) {
                    if (col < 5 && (lines & (1L << (29 - (row * 5 + col)))) != 0) {
                        builder.append("|");
                    } else {
                        builder.append(" ");
                    }
                    builder.append(" ");
                }
                builder.append("\n");
            }
        }

        return builder.toString();
    }

}
