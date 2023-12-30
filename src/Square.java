package src;

public abstract class Square {

    int squarePosition;

    public Square(int squarePos) {
        squarePos = squarePosition;
    }
    
    public abstract boolean isSquareOccupied();

    public abstract Piece getPiece();

        public static final class EmptySquare extends Square {
            EmptySquare(int position) {
                super(position);
            }

            @Override
            public boolean isSquareOccupied() {
                return false;
            }

            @Override
            public Piece getPiece() {
                return null;
            }
            
        }

        public static final class OccupiedSquare extends Square {

            Piece pieceOnSquare;

            public OccupiedSquare(int position, Piece piece) {
                super(position);
                pieceOnSquare = piece;
            }


            @Override
            public boolean isSquareOccupied() {
                return true;
            }

            @Override
            public Piece getPiece() {
                return pieceOnSquare;
            }
        }
}
