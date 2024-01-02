package chess_engine_board;

import chess_engine_board.Board.Builder;
import chess_engine_pieces.Piece;

public abstract class Move {
    
    final Board board;
    final Piece movedPiece;
    final int endPosition;

    private Move(final Board b, final Piece p, final int endPos) {
        board = b;
        movedPiece = p;
        endPosition = endPos;
    }

    public int getEndPosition() {
        return this.endPosition;
    }

    public abstract Board execute();

    public static final class MajorPieceMove extends Move {

        public MajorPieceMove(final Board board, final Piece movedPiece, final int endPosition) {
            super(board, movedPiece, endPosition);
        }
        
         @Override
        public Board execute() {

            final Builder newMoveBoardBuilder = new Builder();
            //Traverse through all the player pieces that aren't moved and for the new transition board set the piece as it already is (since they are not changing)
            for(final Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
                //Equals method not overridden for pieces classes yet
                if(!this.movedPiece.equals(piece)) {
                    newMoveBoardBuilder.setPiece(piece);
                }
            }
            //Traverse through the opponent pieces and for the new transition board set the piece at it already is (since none of the opponents pieces are moving)
            for(final Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces()) {
                newMoveBoardBuilder.setPiece(piece);
            }

            newMoveBoardBuilder.setMoveMaker(null);
            newMoveBoardBuilder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getTeam());

            return newMoveBoardBuilder.build();
        }
        
    }

    public static final class AttackMove extends Move {

        final Piece attackedPiece;

        public AttackMove(final Board board, final Piece movedPiece, final int endPosition, final Piece attackPiece) {
            super(board, movedPiece, endPosition);
            this.attackedPiece = attackPiece;
        }

        @Override
        public Board execute() {
            return null;
        }
        
    }



}
