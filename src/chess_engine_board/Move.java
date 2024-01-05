package chess_engine_board;

import chess_engine_board.Board.Builder;
import chess_engine_pieces.Pawn;
import chess_engine_pieces.Piece;

public abstract class Move {
    
    final Board board;
    final Piece movedPiece;
    final int endPosition;

    public static final Move NULL_MOVE = new NullMove();

    private Move(final Board b, final Piece p, final int endPos) {
        board = b;
        movedPiece = p;
        endPosition = endPos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + this.endPosition;
        result = prime * result + this.movedPiece.hashCode();

        return result;
    }

    @Override
    public boolean equals(final Object other) {
        if(this == other) 
            return true;

        if(!(other instanceof Move))
            return false;

        final Move otherMove = (Move)other;

        return getEndPosition() == otherMove.getEndPosition() &&
               getMovedPiece().equals(otherMove.getMovedPiece());
    }

    public int getCurrentCoordinate() {
        return this.getMovedPiece().getPiecePosition();
    }

    public int getEndPosition() {
        return this.endPosition;
    }

    public Piece getMovedPiece() {
        return this.movedPiece;
    }

    public boolean isAttack() {
        return false;
    }

    public boolean isCastlingMove() {
        return false;
    }

    public Piece getAttackedPiece() {
        return null;
    }

    public Board execute() {
        final Builder newMoveBoardBuilder = new Builder();

        //Traverse through all the player pieces that aren't moved and for the new transition 
        //board set the piece as it already is (since they are not changing)
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

        //actual piece getting moved
        newMoveBoardBuilder.setPiece(this.movedPiece.movePiece(this));
        newMoveBoardBuilder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getTeam());

        return newMoveBoardBuilder.build();
    }

    //////////////////////CLASSES////////////////////////

    public static final class MajorPieceMove extends Move {
        public MajorPieceMove(final Board board, final Piece movedPiece, final int endPosition) {
            super(board, movedPiece, endPosition);
        }
    }

    public static class AttackMove extends Move {

        final Piece attackedPiece;

        public AttackMove(final Board board, final Piece movedPiece, final int endPosition, final Piece attackPiece) {
            super(board, movedPiece, endPosition);
            this.attackedPiece = attackPiece;
        }

        @Override
        public int hashCode() {
            return this.attackedPiece.hashCode() + super.hashCode();
        }

        @Override
        public boolean equals(final Object other) {
            if(this == other)
                return true;

            if(!(other instanceof AttackMove))
                return false;

            final AttackMove otherAttackMove = (AttackMove)other;

            return super.equals(otherAttackMove) && getAttackedPiece().equals(otherAttackMove.getAttackedPiece());
        }

        @Override
        public Board execute() {
            return null;
        }   

        @Override
        public boolean isAttack() {
            return true;
        }

        @Override
        public Piece getAttackedPiece() {
            return this.attackedPiece;
        }
    }

    public static final class PawnMove extends Move {
        public PawnMove(final Board board, final Piece movedPiece, final int endPosition) {
            super(board, movedPiece, endPosition);
        }
    }

    public static class PawnAttackMove extends AttackMove {
        public PawnAttackMove(final Board board, final Piece movedPiece, final int endPosition, final Piece attackedPiece) {
            super(board, movedPiece, endPosition, attackedPiece);
        }
    }

    public static final class PawnEnPassantAttackMove extends PawnAttackMove {
        public PawnEnPassantAttackMove(final Board board, final Piece movedPiece, final int endPosition, final Piece attackedPiece) {
            super(board, movedPiece, endPosition, attackedPiece);
        }
    }

    public static final class PawnJump extends Move {
        public PawnJump(final Board board, final Piece movedPiece, final int endPosition) {
            super(board, movedPiece, endPosition);
        }

        @Override
        public Board execute() {
            final Builder builder = new Builder();

            for(final Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
                if(!this.movedPiece.equals(piece))
                    builder.setPiece(piece);
            }

            for(final Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces()) {
                builder.setPiece(piece);
            }

            final Pawn movedPawn = (Pawn)this.movedPiece.movePiece(this);
            builder.setPiece(movedPawn);
            builder.setEnPassantPawn(movedPawn);
            builder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getTeam());

            return builder.build();
        }
    }

    static abstract class CastleMove extends Move {
        public CastleMove(final Board board, final Piece movedPiece, final int endPosition) {
            super(board, movedPiece, endPosition);
        }
    }

    public static final class KingSideCastleMove extends CastleMove {
        public KingSideCastleMove(final Board board, final Piece movedPiece, final int endPosition) {
            super(board, movedPiece, endPosition);
        }
    }

    public static final class QueenSideCastleMove extends CastleMove {
        public QueenSideCastleMove(final Board board, final Piece movedPiece, final int endPosition) {
            super(board, movedPiece, endPosition);
        }
    }

    public static final class NullMove extends Move {
        public NullMove() {
            super(null , null , -1);
        }

        @Override
        public Board execute() {
            throw new RuntimeException("Cannot execute the null move!");
        }
    }

    public static class MoveFactory {
        private MoveFactory() {
            throw new RuntimeException("Not Instantiable");
        }

        public static Move createMove(final Board board, final int currentCoordinate , final int endCoordinate) {
            for(final Move move : board.getAllLegalMoves()) {
                if(move.getCurrentCoordinate() == currentCoordinate && move.getEndPosition() == endCoordinate) 
                    return move;
                
            }
            return NULL_MOVE;
        }
    }
}
