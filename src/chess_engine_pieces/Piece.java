package chess_engine_pieces;

import chess_engine_board.Move;
import chess_engine_board.Board;
import chess_engine_main.Team;

import java.util.Collection;


public abstract class Piece {
    
    protected final int piecePosition;
    protected final Team pieceTeam;
    protected final boolean isFirstMove;

    public Piece(final Team pieceTeam, final int piecePosition) {
        this.piecePosition = piecePosition;
        this.pieceTeam = pieceTeam;
        this.isFirstMove = false;
    }

    public int getPiecePosition() {
        return this.piecePosition;
    }

    //Given the board passed in, this abstract method will calculate the legal moves for the corresponding piece
    public abstract Collection<Move> getPossibleMoves(final Board board);

    public Team getPieceTeam() {
        return this.pieceTeam;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    public enum PieceType {

        PAWN("P"), 
        KNIGHT("K"),
        BISHOP("B"),
        ROOK("R"),
        QUEEN("Q"),
        KING("K");

        private String pieceName;

        PieceType(final String pieceName) {
            this.pieceName = pieceName;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }
    }
}
