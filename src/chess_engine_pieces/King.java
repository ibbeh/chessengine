package chess_engine_pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import chess_engine_board.Board;
import chess_engine_board.Move;
import chess_engine_main.Team;

public class King extends Piece {

    private final static int[] possibleMovePositions = {-9, -8, -7, -1, 1, 7, 8, 9};

    public King(final int piecePosition, final Team pieceTeam) {
        super(piecePosition, pieceTeam);
    }

    @Override
    public Collection<Move> getPossibleMoves(Board board) {
        
        final List<Move> legalMoves = new ArrayList<>();

        return Collections.unmodifiableList(legalMoves);
    }

    
    
}
