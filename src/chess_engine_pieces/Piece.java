package chess_engine_pieces;

import chess_engine_board.Move;
import chess_engine_board.Board;
import chess_engine_main.Team;

import java.util.Collection;


public abstract class Piece {
    
    protected final PieceType pieceType;
    protected final int piecePosition;
    protected final Team pieceTeam;
    protected final boolean isFirstMove;
    private final int cachedHashCode;

    public Piece(final PieceType pieceType, final Team pieceTeam, final int piecePosition) {
        this.pieceType = pieceType;
        this.piecePosition = piecePosition;
        this.pieceTeam = pieceTeam;
        this.isFirstMove = false;
        this.cachedHashCode = computedHashCode();
    }

    private int computedHashCode() {
        int result = pieceType.hashCode();
        
        result = 31 * result + pieceTeam.hashCode();
        result = 31 * result + piecePosition;
        result = 31 * result + (isFirstMove ? 1 : 0);

        return result;
    }

    //Override equals() method to compare if 2 Pieces are equal
    @Override
    public boolean equals(final Object other) {
        if(this == other)
            return true;

        if(!(other instanceof Piece))
            return false;

        final Piece otherPiece = (Piece)other;

        return piecePosition == otherPiece.getPiecePosition() && 
               pieceType == otherPiece.getPieceType() &&
               pieceTeam == otherPiece.getPieceTeam() && 
               isFirstMove == otherPiece.isFirstMove();
    }

    @Override
    public int hashCode() {
        return this.cachedHashCode;
    }

    public int getPiecePosition() {
        return this.piecePosition;
    }

    //Given the board passed in, this abstract method will calculate the legal moves for the corresponding piece
    public abstract Collection<Move> getPossibleMoves(final Board board);

    public abstract Piece movePiece(Move move);

    public Team getPieceTeam() {
        return this.pieceTeam;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    public PieceType getPieceType() {
        return this.pieceType;
    }

    public enum PieceType {

        PAWN("P") {
            @Override
            public boolean isKing() {
                return false;
            }
        }, 
        KNIGHT("K") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        BISHOP("B") {
            @Override
            public boolean isKing() {
               return false;
            }
        },
        ROOK("R") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        QUEEN("Q") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        KING("K") {
            @Override
            public boolean isKing() {
                return true;
            }
        };

        private String pieceName;

        PieceType(final String pieceName) {
            this.pieceName = pieceName;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }

        public abstract boolean isKing();
    }
}
