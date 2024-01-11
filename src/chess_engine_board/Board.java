package chess_engine_board;

import chess_engine_main.BlackPlayer;
import chess_engine_main.Player;
import chess_engine_main.Team;
import chess_engine_main.WhitePlayer;
import chess_engine_pieces.Piece;
import chess_engine_pieces.Rook;   
import chess_engine_pieces.Knight; 
import chess_engine_pieces.Bishop;  
import chess_engine_pieces.Queen;   
import chess_engine_pieces.King;   
import chess_engine_pieces.Pawn;  
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap; 
import java.util.Arrays;

public class Board {

    private final List<Square> gameBoard;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;

    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;
    private final Player currentPlayer;

    private Board(final Builder builder) {
        this.gameBoard = createGameBoard(builder);
        this.whitePieces = calculateActivePieces(this.gameBoard, Team.WHITE);
        this.blackPieces = calculateActivePieces(this.gameBoard, Team.BLACK);

        final Collection<Move> whiteStandardLegalMoves = calculateLegalMoves(this.whitePieces);
        final Collection<Move> blackStandardLegalMoves = calculateLegalMoves(this.blackPieces);

        this.whitePlayer = new WhitePlayer(this, whiteStandardLegalMoves, blackStandardLegalMoves);
        this.blackPlayer = new BlackPlayer(this, whiteStandardLegalMoves, blackStandardLegalMoves);
        this.currentPlayer = builder.nextMoveMaker.choosePlayer(this.whitePlayer, this.blackPlayer);

    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("\n");
        for(int i = 0; i < BoardUtil.NUM_SQUARES; i++) {
            final String squareText = this.gameBoard.get(i).toString();
            builder.append(String.format("%3s", squareText));
            if((i + 1) % BoardUtil.NUM_SQUARES_PER_ROW == 0) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }
    
    public Player getWhitePlayer() {
        return this.whitePlayer;
    }

    public Player getBlackPlayer() {
        return this.blackPlayer;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public Collection<Piece> getBlackPieces() {
        return this.blackPieces;
    }

    public Collection<Piece> getWhitePieces() {
        return this.whitePieces;
    }


    private Collection<Move> calculateLegalMoves(Collection<Piece> pieces) {
        final List<Move> legalMoves = new ArrayList<>();
        for(final Piece piece : pieces) {
            legalMoves.addAll(piece.getPossibleMoves(this));
        }
        return Collections.unmodifiableList(legalMoves);
    }

    private static Collection<Piece> calculateActivePieces(final List<Square> gameBoard, final Team team) {

        final List<Piece> activePieces = new ArrayList<>();
        for(final Square square : gameBoard) {
            if(square.isSquareOccupied()) {
                final Piece piece = square.getPiece();
                if(piece.getPieceTeam() == team) {
                    activePieces.add(piece);
                }
            }
        }

        return Collections.unmodifiableList(activePieces);

    }

    public Square getSquare(final int squareCoordinate) {
        return gameBoard.get(squareCoordinate);
    }

    private static List<Square> createGameBoard(final Builder builder) {
        final Square[] squares = new Square[BoardUtil.NUM_SQUARES];
        for(int i = 0 ; i < BoardUtil.NUM_SQUARES; i++) {
            squares[i] = Square.createSquare(i, builder.boardConfig.get(i));
        }
        return Collections.unmodifiableList(Arrays.asList(squares));
    }

    public static Board createStandardBoard() {
        final Builder builder = new Builder();
        // Black Layout
        builder.setPiece(new Rook(Team.BLACK, 0));
        builder.setPiece(new Knight(Team.BLACK, 1));
        builder.setPiece(new Bishop(Team.BLACK, 2));
        builder.setPiece(new Queen(Team.BLACK, 3));
        builder.setPiece(new King(Team.BLACK, 4));
        builder.setPiece(new Bishop(Team.BLACK, 5));
        builder.setPiece(new Knight(Team.BLACK, 6));
        builder.setPiece(new Rook(Team.BLACK, 7));
        builder.setPiece(new Pawn(Team.BLACK, 8));
        builder.setPiece(new Pawn(Team.BLACK, 9));
        builder.setPiece(new Pawn(Team.BLACK, 10));
        builder.setPiece(new Pawn(Team.BLACK, 11));
        builder.setPiece(new Pawn(Team.BLACK, 12));
        builder.setPiece(new Pawn(Team.BLACK, 13));
        builder.setPiece(new Pawn(Team.BLACK, 14));
        builder.setPiece(new Pawn(Team.BLACK, 15));
        // White Layout
        builder.setPiece(new Pawn(Team.WHITE, 48));
        builder.setPiece(new Pawn(Team.WHITE, 49));
        builder.setPiece(new Pawn(Team.WHITE, 50));
        builder.setPiece(new Pawn(Team.WHITE, 51));
        builder.setPiece(new Pawn(Team.WHITE, 52));
        builder.setPiece(new Pawn(Team.WHITE, 53));
        builder.setPiece(new Pawn(Team.WHITE, 54));
        builder.setPiece(new Pawn(Team.WHITE, 55));
        builder.setPiece(new Rook(Team.WHITE, 56));
        builder.setPiece(new Knight(Team.WHITE, 57));
        builder.setPiece(new Bishop(Team.WHITE, 58));
        builder.setPiece(new Queen(Team.WHITE, 59));
        builder.setPiece(new King(Team.WHITE, 60));
        builder.setPiece(new Bishop(Team.WHITE, 61));
        builder.setPiece(new Knight(Team.WHITE, 62));
        builder.setPiece(new Rook(Team.WHITE, 63));
        //white to move
        builder.setMoveMaker(Team.WHITE);
        return builder.build();
    }


    //NEED TO ADD GUAVA LIBRARY FOR ITERABLES
    public Iterable<Move> getAllLegalMoves() {
        //return Iterables.unmodifiableIterable(Iterables.concat(this.whitePlayer.getLegalMoves() , this.blackPlayer.getLegalMoves()));

        return null;
    }

    public static class Builder {

        Map<Integer, Piece> boardConfig;
        Team nextMoveMaker; 
        Pawn enPassantPawn;

        public Builder() {
            this.boardConfig = new HashMap<>();
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

        public void setEnPassantPawn(Pawn enPassantPawn) {
            this.enPassantPawn = enPassantPawn;
        }

    }

    public static class BoardUtil {

        public static final boolean[] FIRST_COLUMN = initColumn(0);
        public static final boolean[] SECOND_COLUMN = initColumn(1);
        public static final boolean[] SEVENTH_COLUMN = initColumn(6);
        public static final boolean[] EIGHTH_COLUMN = initColumn(7);

        public static final boolean[] SECOND_ROW = initRow(8);
        public static final boolean[] SEVENTH_ROW = initRow(48);

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

        private static boolean[] initRow(int rowNum) {
            final boolean[] row = new boolean[NUM_SQUARES];
            do {
                row[rowNum] = true;
                rowNum++;
            } while(rowNum % NUM_SQUARES_PER_ROW != 0);
            return row;
        }


    }

}
