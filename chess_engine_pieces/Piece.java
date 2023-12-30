package chess_engine_pieces;

import chess_engine_main.Team;
import chess_engine_board.Move;
import chess_engine_board.Board;
import java.util.Collection;


public abstract class Piece {
    
    protected final int piecePosition;
    protected final Team pieceTeam;

    public Piece(final int piecePosition, final Team pieceTeam) {
        this.piecePosition = piecePosition;
        this.pieceTeam = pieceTeam;
    }

    //Given the board passed in, this abstract method will calculate the legal moves for the corresponding piece
    public abstract Collection<Move> getPossibleMoves(final Board board);

    public Team getPieceTeam() {
        return this.pieceTeam;
    }



}
