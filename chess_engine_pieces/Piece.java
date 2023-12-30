package chess_engine_pieces;

import chess_engine_main.Team;
import chess_engine_board.Move;
import chess_engine_board.Board;
import java.util.List;

public abstract class Piece {
    
    protected final int piecePosition;
    protected final Team pieceTeam;

    public Piece(final int piecePosition, final Team pieceTeam) {
        this.piecePosition = piecePosition;
        this.pieceTeam = pieceTeam;
    }

    public abstract List<Move> getPossibleMoves(final Board board);



}
