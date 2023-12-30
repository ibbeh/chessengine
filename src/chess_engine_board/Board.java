package chess_engine_board;

import chess_engine_main.Team;
import chess_engine_pieces_Bishop;
import chess_engine_pieces_Knight;
import chess_engine_pieces_Rook;
import chess_engine_pieces_Piece;
//import ImmutableList;

import java.util.List;
import java.util.Map;




public class Board {

    private final List<Square> gameBoard;

    private Board(Builder builder) {
        this.gameBoard = createGameBoard(builder);
    }

    public Square getSquare(final int titleCoordinate) {
        return null;
    }

    private static List<Square> createGameBoard(final Builder builder) {
        final Square[] squares = new Square[BoardUtil.NUM_SQUARES];
        for(int i = 0 ; i < BoardUtil.NUM_SQUARES; i++) {
            squares[i] = Square.createSquare(i, builder.boardConfig.get(i));
        }
        return squares;
    }

    public static Board createStandardBoard() {
        final Builder builder = new Builder();
        // Black Layout
        builder.setPiece(new Rook(Alliance.BLACK, 0));
        builder.setPiece(new Knight(Alliance.BLACK, 1));
        builder.setPiece(new Bishop(Alliance.BLACK, 2));
        builder.setPiece(new Queen(Alliance.BLACK, 3));
        builder.setPiece(new King(Alliance.BLACK, 4));
        builder.setPiece(new Bishop(Alliance.BLACK, 5));
        builder.setPiece(new Knight(Alliance.BLACK, 6));
        builder.setPiece(new Rook(Alliance.BLACK, 7));
        builder.setPiece(new Pawn(Alliance.BLACK, 8));
        builder.setPiece(new Pawn(Alliance.BLACK, 9));
        builder.setPiece(new Pawn(Alliance.BLACK, 10));
        builder.setPiece(new Pawn(Alliance.BLACK, 11));
        builder.setPiece(new Pawn(Alliance.BLACK, 12));
        builder.setPiece(new Pawn(Alliance.BLACK, 13));
        builder.setPiece(new Pawn(Alliance.BLACK, 14));
        builder.setPiece(new Pawn(Alliance.BLACK, 15));
        // White Layout
        builder.setPiece(new Pawn(Alliance.WHITE, 48));
        builder.setPiece(new Pawn(Alliance.WHITE, 49));
        builder.setPiece(new Pawn(Alliance.WHITE, 50));
        builder.setPiece(new Pawn(Alliance.WHITE, 51));
        builder.setPiece(new Pawn(Alliance.WHITE, 52));
        builder.setPiece(new Pawn(Alliance.WHITE, 53));
        builder.setPiece(new Pawn(Alliance.WHITE, 54));
        builder.setPiece(new Pawn(Alliance.WHITE, 55));
        builder.setPiece(new Rook(Alliance.WHITE, 56));
        builder.setPiece(new Knight(Alliance.WHITE, 57));
        builder.setPiece(new Bishop(Alliance.WHITE, 58));
        builder.setPiece(new Queen(Alliance.WHITE, 59));
        builder.setPiece(new King(Alliance.WHITE, 60));
        builder.setPiece(new Bishop(Alliance.WHITE, 61));
        builder.setPiece(new Knight(Alliance.WHITE, 62));
        builder.setPiece(new Rook(Alliance.WHITE, 63));
        //white to move
        builder.setMoveMaker(Alliance.WHITE);
        return builder.build();
    }

    public static class Builder {

        Map<Integer, Piece> boardConfig;
        Team nextMoveMaker; 

        public Builder() {
        }

        public Builder setPiece(final Piece piece) {
            this.boardConfig.put(piece.getPiecePosition(), piece);
            return this;
        }

        public Builder setMoveMaker(final Team nextMoveMaker) {
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }

        public Board build() {
            return new Board(this);
        }

    }

    public static class BoardUtil {

        public static final boolean[] FIRST_COLUMN = initColumn(0);
        public static final boolean[] SECOND_COLUMN = initColumn(1);
        public static final boolean[] SEVENTH_COLUMN = initColumn(6);
        public static final boolean[] EIGHTH_COLUMN = initColumn(7);

        public static final boolean[] SECOND_ROW = null;
        public static final boolean[] SEVENTH_ROW = null;

        public static final int NUM_SQUARES = 64;
        public static final int NUM_SQUARES_PER_ROW = 8;

        //Private constructor to prevent instantiation
        private BoardUtil() {}

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
