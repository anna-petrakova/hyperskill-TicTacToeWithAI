package tictactoe;

public interface PlayerStrategy {
    boolean computeMove();
    int getX();
    int getY();
    char getPlayerSymbol();
    String getMoveAnnouncement();
}
