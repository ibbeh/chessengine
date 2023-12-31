package chess_engine_board;

import chess_engine_pieces.Piece;

import java.util.HashMap;
import java.util.Map;

import chess_engine_board.Board.BoardUtil;

import java.util.Collections;

public abstract class Square {

    protected final int squarePosition;

    private static final Map<Integer, EmptySquare> emptySquares = createAllPossibleEmptySquares();

    private Square(final int squarePos) {
        squarePosition = squarePos;
    }
    
    private static Map<Integer, EmptySquare> createAllPossibleEmptySquares() {

        final Map<Integer, EmptySquare> emptySquareMap = new HashMap<Integer, EmptySquare>();

        for(int i = 0; i < BoardUtil.NUM_SQUARES; i++) {
            emptySquareMap.put(i, new EmptySquare(i));
        }
        
        return Collections.unmodifiableMap(emptySquareMap);
        
    }

    public static Square createSquare(final int position, final Piece piece) {
        if(piece != null) {
            return new OccupiedSquare(position, piece);
        }
        else {
            return emptySquares.get(position);
        }
    }

    public abstract boolean isSquareOccupied();

    public abstract Piece getPiece();

        public static final class EmptySquare extends Square {
            
            private EmptySquare(final int position) {
                super(position);
            }

            @Override
            public String toString() {
                return "-";
            }

            @Override
            public boolean isSquareOccupied() {
                return false;
            }

            @Override
            public Piece getPiece() {
                return null;
            }
            
        }

        public static final class OccupiedSquare extends Square {

            private final Piece pieceOnSquare;

            private OccupiedSquare(final int position, final Piece piece) {
                super(position);
                pieceOnSquare = piece;
            }

            @Override
            public String toString() {
                return getPiece().getPieceTeam().isBlack() ? getPiece().toString().toLowerCase() : getPiece().toString();
            }

            @Override
            public boolean isSquareOccupied() {
                return true;
            }

            @Override
            public Piece getPiece() {
                return pieceOnSquare;
            }
        }
}
