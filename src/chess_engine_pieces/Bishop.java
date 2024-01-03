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

    public Bishop(final Team pieceTeam, final int piecePosition) {
        super(PieceType.BISHOP, pieceTeam, piecePosition);
    }

    @Override
    public Collection<Move> getPossibleMoves(final Board board) {
       
        final List<Move> legalMoves = new ArrayList<Move>();

        for(final int currentPositionOffset: possibleVectorPositions) {

            int possibleDestinationPosition = this.piecePosition;

            while(BoardUtil.isValidSquarePosition(possibleDestinationPosition)) {

                if(firstColumnExclusion(this.piecePosition, currentPositionOffset) || eighthColumnExclusion(this.piecePosition, currentPositionOffset)) {
                    break;
                }

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

    @Override
    public String toString() {
        return PieceType.BISHOP.toString();
    }


    //Edge cases
    private static boolean firstColumnExclusion (final int currnetPos, final int bishopOffset) {
        return BoardUtil.FIRST_COLUMN[currnetPos] && (bishopOffset == -9 || bishopOffset == 7);
    }

    private static boolean eighthColumnExclusion (final int currnetPos, final int bishopOffset) {
        return BoardUtil.EIGHTH_COLUMN[currnetPos] && (bishopOffset == -7 || bishopOffset == 9);
    }
    
    
    
}
//ADILET ISHENBEKOV
//IBRAHEEM REFAI 
