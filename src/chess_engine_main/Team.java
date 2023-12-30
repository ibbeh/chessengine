package chess_engine_main;

public class Team {
    public static final int WHITE = 0;
    public static final int BLACK = 1;

    //Private constructor to prevent instantiation
    private Team() {}

    public static int getDirection(int team) {
        if(team == WHITE) {
            return -1;
        }
        else {
            return 1;
        }
    }
}

