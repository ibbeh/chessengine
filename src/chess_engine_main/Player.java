package chess_engine_main;

import java.util.Collection;

import chess_engine_board.Board;
import chess_engine_board.Move;
import chess_engine_pieces.King;
import chess_engine_pieces.Piece;

public abstract class Player {
    
    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;

    public Player(final Board board, final Collection<Move> legalMoves, final Collection<Move> opponentMoves) {
        this.board = board;
        this.playerKing = establishKing();
        this.legalMoves = legalMoves;
    }

    private King establishKing() {
        for(final Piece piece : getActivePieces()) {
            if(piece.getPieceType().isKing()) {
                return (King) piece;
            }
        }
        throw new RuntimeException("Error: Not a valid board as there is a missing King!");
    }

    public abstract Collection<Piece> getActivePieces();
}
