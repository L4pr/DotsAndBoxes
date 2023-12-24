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

        // There are 5 horizontal and 5 vertical lines in each row and column
        for (int row = 0; row < 6; row++) {
            // Horizontal lines and dots for this row
            for (int col = 0; col < 6; col++) {
                builder.append(".");
                if (row < 5 && col < 6) {
                    int hLineIndex = row * 5 + col;
                    builder.append((lines & (1L << (59 - hLineIndex))) != 0 ? "-" : " ");
                }
            }
            builder.append("\n");

            // Vertical lines for this row, if not the last row
            if (row < 5) {
                for (int col = 0; col < 6; col++) {
                    int vLineIndex = 30 + row * 5 + col;
                    builder.append((lines & (1L << (59 - vLineIndex))) != 0 ? "|" : " ");
                    if (col < 5) {
                        builder.append(" ");
                    }
                }
                builder.append("\n");
            }
        }

        return builder.toString();
    }

}
