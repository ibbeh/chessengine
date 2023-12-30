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

public class Bishop extends Piece {

    private final static int[] possibleVectorPositions = {-9, -7, 7, 9};

    public Bishop(int piecePosition, Team pieceTeam) {
        super(piecePosition, pieceTeam);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Collection<Move> getPossibleMoves(Board board) {
       
        final List<Move> legalMoves = new ArrayList<Move>();

        for(final int currentPositionOffset: possibleVectorPositions) {

            int possibleDestinationPosition = this.piecePosition;

            while(BoardUtil.isValidSquarePosition(possibleDestinationPosition)) {

                possibleDestinationPosition += currentPositionOffset;

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
                        //For blocking pieces
                        break;
                    }
                }
            }
        }

        return Collections.unmodifiableList(legalMoves);
    }
    
    
}
