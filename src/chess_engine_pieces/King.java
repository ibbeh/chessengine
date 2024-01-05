package chess_engine_pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import chess_engine_board.Board;
import chess_engine_board.Move;
import chess_engine_board.Move.AttackMove;
import chess_engine_board.Move.MajorPieceMove;
import chess_engine_board.Square;
import chess_engine_board.Board.BoardUtil;
import chess_engine_main.Team;

public class King extends Piece {

    private final static int[] possibleMovePositions = {-9, -8, -7, -1, 1, 7, 8, 9};

    public King(final Team pieceTeam, final int piecePosition) {
        super(PieceType.KING, pieceTeam, piecePosition);
    }

    @Override
    public Collection<Move> getPossibleMoves(final Board board) {
        
        final List<Move> legalMoves = new ArrayList<>();

    
        for(final int currentPositionOffset: possibleMovePositions) {

            final int possibleDestinationPosition = this.piecePosition + currentPositionOffset;

            if(firstColumnExclusion(this.piecePosition, currentPositionOffset) || eighthColumnExclusion(this.piecePosition, currentPositionOffset)) {
                continue;
            }

            if(BoardUtil.isValidSquarePosition(possibleDestinationPosition)) {
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

    @Override
    public King movePiece(Move move) {
        return new King(move.getMovedPiece().getPieceTeam() , move.getEndPosition());
    }

    @Override
    public String toString() {
        return PieceType.KING.toString();
    }

    //Edge cases for possible moves
    private static boolean firstColumnExclusion(final int currentPos, final int kingOffset) {
        return BoardUtil.FIRST_COLUMN[currentPos] && (kingOffset == -9 || kingOffset == -1 || kingOffset == 7);
    }

    private static boolean eighthColumnExclusion(final int currentPos, final int kingOffset) {
        return BoardUtil.EIGHTH_COLUMN[currentPos] && (kingOffset == -7 || kingOffset == 1 || kingOffset == 9);
    }

    
    
}
