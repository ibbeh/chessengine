package chess_engine_main;

import chess_engine_board.Board;
import chess_engine_board.Move;

public class MoveTransition {

    private final Board transitionBoard;
    private final Move moveMade;
    private final MoveStatus moveStatus;

    public MoveTransition(final Board transitionBoard, final Move move, final MoveStatus moveStatus) {
        this.transitionBoard = transitionBoard;
        moveMade = move;
        this.moveStatus = moveStatus;
    }

    public MoveStatus getMoveStatus() {
        return moveStatus;
    }

    public Board getTransitionBoard() {
        return transitionBoard;
    }

    public Move getMoveMade() {
        return moveMade;
    }

}
