package tictactoe;

public enum GameState {
    NOT_STARTED("Not started"),
    IMPOSSIBLE("Impossible state"),
    X_WINS("X wins"),
    O_WINS("O wins"),
    DRAW("Draw"),
    FINISHED_EARLY("Finished early"),
    GAME_NOT_FINISHED("Game continues");

    private String announcement;

    GameState(String announcement) {
        this.announcement = announcement;
    }

    public String getAnnouncement() {
        return announcement;
    }
}
