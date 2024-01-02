package chess_engine_board;

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
            return null;
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
