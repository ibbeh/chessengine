package chess_engine_main;

import java.util.Collection;

import chess_engine_board.Board;
import chess_engine_board.Move;
import chess_engine_pieces.Piece;

public class BlackPlayer extends Player {

    public BlackPlayer(final Board board, final Collection<Move> whiteStandardLegalMoves, final Collection<Move> blackStandardLegalMoves) {
        super(board, blackStandardLegalMoves, whiteStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Team getTeam() {
        return Team.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.getWhitePlayer();
    }
    
}
