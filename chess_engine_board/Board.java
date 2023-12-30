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

        public static final boolean[] FIRST_COLUMN = null;
        public static final boolean[] SECOND_COLUMN = null;
        public static final boolean[] SEVENTH_COLUMN = null;
        public static final boolean[] EIGHTH_COLUMN = null;

        public static boolean isValidSquarePosition(int position) {
            return position >= 0 && position < 64;
        }


    }

}
