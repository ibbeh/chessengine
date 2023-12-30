package chess_engine_pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import chess_engine_board.Board;
import chess_engine_board.Move;
import chess_engine_board.Board.BoardUtil;
import chess_engine_board.Move.MajorPieceMove;
import chess_engine_main.Team;


public class Pawn extends Piece {

    private final static int[] possibleMovePositions = {8};

    public Pawn(final int piecePosition, final int pieceTeam) {
        super(piecePosition, pieceTeam);
    }

    @Override
    public Collection<Move> getPossibleMoves(Board board) {
     
        final List<Move> legalMoves = new ArrayList<Move>();

        for(final int currentPositionOffset : possibleMovePositions) {

            //Direction changes for white pawns and black pawns
            int possibleDestinationPosition = this.piecePosition + Team.getDirection(this.getPieceTeam()) * currentPositionOffset;

            if(!BoardUtil.isValidSquarePosition(possibleDestinationPosition)) {
                continue;
            }

            //If the pawn is moving 1 square forward and the square that it is moving to is not occupied
            if(currentPositionOffset == 8 && !board.getSquare(possibleDestinationPosition).isSquareOccupied()) {
                legalMoves.add(new MajorPieceMove(board, this, possibleDestinationPosition));
            }
        }

        return Collections.unmodifiableList(legalMoves);
    }
    
}
