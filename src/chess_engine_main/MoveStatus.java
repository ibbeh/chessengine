package chess_engine_main;

public enum MoveStatus {
    DONE {
        @Override
        boolean isDone() {
            return true;
        }
    };


    abstract boolean isDone();
}
