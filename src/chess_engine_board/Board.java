package chess_engine_board;

public class Board {
    Square[][] squares;

    //Constructor(s)
    public Board(){
        this.setBoard();
    }

    private void setBoard() {

    }

    public Square getSquare(final int squarePosition) {
        return null;
    }



    public abstract static class BoardUtil {

        public static final boolean[] FIRST_COLUMN = initColumn(0);
        public static final boolean[] SECOND_COLUMN = initColumn(1);
        public static final boolean[] SEVENTH_COLUMN = initColumn(6);
        public static final boolean[] EIGHTH_COLUMN = initColumn(7);

        public static final int NUM_SQUARES = 64;
        public static final int NUM_SQUARES_PER_ROW = 8;

        public static boolean isValidSquarePosition(final int position) {
            return position >= 0 && position < NUM_SQUARES;
        }

        private static boolean[] initColumn(int columnNum) {
            final boolean[] column = new boolean[NUM_SQUARES];

            while(columnNum < NUM_SQUARES) {
                column[columnNum] = true;
                columnNum += NUM_SQUARES_PER_ROW;
            }

            return column;
        }


    }

}
