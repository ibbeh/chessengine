package chess_engine_main;

import chess_engine_board.Board;

public class JChess {
    public static void main(String[] args) {
        Board board = Board.createStandardBoard();

        System.out.println(board);

    }
}
