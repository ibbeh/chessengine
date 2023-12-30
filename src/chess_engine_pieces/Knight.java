package chess_engine_pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import chess_engine_board.Board;
import chess_engine_board.Move;
import chess_engine_board.Square;
import chess_engine_board.Board.BoardUtil;
import chess_engine_board.Move.AttackMove;
import chess_engine_board.Move.MajorPieceMove;
import chess_engine_main.Team;


public class Knight extends Piece {

    //Relative to the knight's position, these are the possible positions where the knight can move (i.e. 17 squares to the right or 15 squares to the right etc...)
    private final static int[] possibleMovePositions = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(final Team pieceTeam, final int piecePosition) {
        super(piecePosition, pieceTeam);
    }

    @Override
    public Collection<Move> getPossibleMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<Move>();

        for(final int currentPositionOffset: possibleMovePositions) {
            int possibleDestinationPosition = this.piecePosition + currentPositionOffset;

            if(BoardUtil.isValidSquarePosition(possibleDestinationPosition)) {

                if(firstColumnExclusion(this.piecePosition, currentPositionOffset) || secondColumnExclusion(this.piecePosition, currentPositionOffset) ||
                    seventhColumnExclusion(this.piecePosition, currentPositionOffset) || eighthColumnExclusion(this.piecePosition, currentPositionOffset)) {
                    continue;
                }

                final Square possibleDestinationSquare = board.getSquare(possibleDestinationPosition);

                if(!possibleDestinationSquare.isSquareOccupied()) {
                    legalMoves.add(new MajorPieceMove(board, this, possibleDestinationPosition));
                }
                else{
                    final Piece pieceAtDestination = possibleDestinationSquare.getPiece();
                    final Team pieceAtDestinationTeam = pieceAtDestination.getPieceTeam();

                    if(this.pieceTeam != pieceAtDestinationTeam) {
                        legalMoves.add(new AttackMove(board, this, possibleDestinationPosition, pieceAtDestination));
                    }
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }


    //Edge cases for possible moves
    private static boolean firstColumnExclusion(final int currentPos, final int knightOffset) {
        return BoardUtil.FIRST_COLUMN[currentPos] && (knightOffset == -17 || knightOffset == -10 ||
            knightOffset == 6 || knightOffset == 15);
    }

    private static boolean secondColumnExclusion(final int currentPos, final int knightOffset) {
        return BoardUtil.SECOND_COLUMN[currentPos] && (knightOffset == -10 || knightOffset == 6);
    }

    private static boolean seventhColumnExclusion(final int currentPos, final int knightOffset) {
        return BoardUtil.SEVENTH_COLUMN[currentPos] && (knightOffset == -6 || knightOffset == 10);
    }

      private static boolean eighthColumnExclusion(final int currentPos, final int knightOffset) {
        return BoardUtil.EIGHTH_COLUMN[currentPos] && (knightOffset == -15 || knightOffset == -6 ||
            knightOffset == 10 || knightOffset == 17);
    }
    
}
