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

    private final static int[] possibleMovePositions = {7, 8, 9, 16};

    public Pawn(final Team pieceTeam, final int piecePosition) {
        super(PieceType.PAWN, pieceTeam, piecePosition);
    }

    @Override
    public Collection<Move> getPossibleMoves(final Board board) {
     
        final List<Move> legalMoves = new ArrayList<Move>();

        for(final int currentPositionOffset : possibleMovePositions) {

            //Direction changes for white pawns and black pawns
            final int possibleDestinationPosition = this.piecePosition + (this.pieceTeam.getDirection() * currentPositionOffset);

            if(!BoardUtil.isValidSquarePosition(possibleDestinationPosition)) {
                continue;
            }

            //If the pawn is moving 1 square forward and the square that it is moving to is not occupied
            if(currentPositionOffset == 8 && !board.getSquare(possibleDestinationPosition).isSquareOccupied()) {
                //Need to deal with promotions here still
                legalMoves.add(new MajorPieceMove(board, this, possibleDestinationPosition));
            }
            //Pawn jump
            else if(currentPositionOffset == 16 && this.isFirstMove() && ((BoardUtil.SECOND_ROW[this.piecePosition] && this.pieceTeam.isBlack()) ||
                    BoardUtil.SEVENTH_ROW[this.piecePosition] && this.pieceTeam.isWhite())) {
                
                final int positionBehindDestination = this.piecePosition + (this.pieceTeam.getDirection() * 8);
                if(!board.getSquare(positionBehindDestination).isSquareOccupied() && 
                    !board.getSquare(possibleDestinationPosition).isSquareOccupied()) {
                    legalMoves.add(new MajorPieceMove(board, this, possibleDestinationPosition));
                }
            }
            else if(currentPositionOffset == 7 && !((BoardUtil.EIGHTH_COLUMN[this.piecePosition] && this.pieceTeam.isWhite())
                    || (BoardUtil.FIRST_COLUMN[this.piecePosition] && this.pieceTeam.isBlack()))) {

                if(board.getSquare(possibleDestinationPosition).isSquareOccupied()) {
                    final Piece pieceOnDestination = board.getSquare(possibleDestinationPosition).getPiece();
                    if(this.pieceTeam != pieceOnDestination.getPieceTeam()) {
                        legalMoves.add(new MajorPieceMove(board, this, possibleDestinationPosition));
                    }
                }
            }
            else if(currentPositionOffset == 9 && !((BoardUtil.FIRST_COLUMN[this.piecePosition] && this.pieceTeam.isWhite())
                    || (BoardUtil.EIGHTH_COLUMN[this.piecePosition] && this.pieceTeam.isBlack()))) {
                    
                  if(board.getSquare(possibleDestinationPosition).isSquareOccupied()) {
                    final Piece pieceOnDestination = board.getSquare(possibleDestinationPosition).getPiece();
                    if(this.pieceTeam != pieceOnDestination.getPieceTeam()) {
                        legalMoves.add(new MajorPieceMove(board, this, possibleDestinationPosition));
                    }
                }
            }
        }

        return Collections.unmodifiableList(legalMoves);
    }

    @Override
    public String toString() {
        return PieceType.PAWN.toString();
    }
    
}
