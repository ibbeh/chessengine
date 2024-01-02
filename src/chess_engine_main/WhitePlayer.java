package chess_engine_main;

import java.util.Collection;

import chess_engine_board.Board;
import chess_engine_board.Move;
import chess_engine_pieces.Piece;

public class WhitePlayer extends Player {

    public WhitePlayer(Board board, Collection<Move> whiteStandardLegalMoves, Collection<Move> blackStandardLegalMoves) {
        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }
    
}
